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

import com.liferay.osb.NoSuchAssetListAssetEntryException;
import com.liferay.osb.model.AssetListAssetEntry;
import com.liferay.osb.model.impl.AssetListAssetEntryImpl;
import com.liferay.osb.model.impl.AssetListAssetEntryModelImpl;

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

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the asset list asset entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListAssetEntryPersistence
 * @see AssetListAssetEntryUtil
 * @generated
 */
public class AssetListAssetEntryPersistenceImpl extends BasePersistenceImpl<AssetListAssetEntry>
	implements AssetListAssetEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetListAssetEntryUtil} to access the asset list asset entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetListAssetEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETLISTID =
		new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetListAssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetListId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTID =
		new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetListAssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetListId",
			new String[] { Long.class.getName() },
			AssetListAssetEntryModelImpl.ASSETLISTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETLISTID = new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetListId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetListAssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetListAssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetEntryId",
			new String[] { Long.class.getName() },
			AssetListAssetEntryModelImpl.ASSETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETENTRYID = new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_ALI_AEI = new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetListAssetEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByALI_AEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetListAssetEntryModelImpl.ASSETLISTID_COLUMN_BITMASK |
			AssetListAssetEntryModelImpl.ASSETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ALI_AEI = new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByALI_AEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetListAssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetListAssetEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset list asset entry in the entity cache if it is enabled.
	 *
	 * @param assetListAssetEntry the asset list asset entry
	 */
	public void cacheResult(AssetListAssetEntry assetListAssetEntry) {
		EntityCacheUtil.putResult(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryImpl.class, assetListAssetEntry.getPrimaryKey(),
			assetListAssetEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ALI_AEI,
			new Object[] {
				Long.valueOf(assetListAssetEntry.getAssetListId()),
				Long.valueOf(assetListAssetEntry.getAssetEntryId())
			}, assetListAssetEntry);

		assetListAssetEntry.resetOriginalValues();
	}

	/**
	 * Caches the asset list asset entries in the entity cache if it is enabled.
	 *
	 * @param assetListAssetEntries the asset list asset entries
	 */
	public void cacheResult(List<AssetListAssetEntry> assetListAssetEntries) {
		for (AssetListAssetEntry assetListAssetEntry : assetListAssetEntries) {
			if (EntityCacheUtil.getResult(
						AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
						AssetListAssetEntryImpl.class,
						assetListAssetEntry.getPrimaryKey()) == null) {
				cacheResult(assetListAssetEntry);
			}
			else {
				assetListAssetEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset list asset entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetListAssetEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetListAssetEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset list asset entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetListAssetEntry assetListAssetEntry) {
		EntityCacheUtil.removeResult(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryImpl.class, assetListAssetEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetListAssetEntry);
	}

	@Override
	public void clearCache(List<AssetListAssetEntry> assetListAssetEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetListAssetEntry assetListAssetEntry : assetListAssetEntries) {
			EntityCacheUtil.removeResult(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
				AssetListAssetEntryImpl.class,
				assetListAssetEntry.getPrimaryKey());

			clearUniqueFindersCache(assetListAssetEntry);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetListAssetEntry assetListAssetEntry) {
		if (assetListAssetEntry.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(assetListAssetEntry.getAssetListId()),
					Long.valueOf(assetListAssetEntry.getAssetEntryId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALI_AEI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ALI_AEI, args,
				assetListAssetEntry);
		}
		else {
			AssetListAssetEntryModelImpl assetListAssetEntryModelImpl = (AssetListAssetEntryModelImpl)assetListAssetEntry;

			if ((assetListAssetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ALI_AEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetListAssetEntry.getAssetListId()),
						Long.valueOf(assetListAssetEntry.getAssetEntryId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALI_AEI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ALI_AEI, args,
					assetListAssetEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(
		AssetListAssetEntry assetListAssetEntry) {
		AssetListAssetEntryModelImpl assetListAssetEntryModelImpl = (AssetListAssetEntryModelImpl)assetListAssetEntry;

		Object[] args = new Object[] {
				Long.valueOf(assetListAssetEntry.getAssetListId()),
				Long.valueOf(assetListAssetEntry.getAssetEntryId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALI_AEI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ALI_AEI, args);

		if ((assetListAssetEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ALI_AEI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(assetListAssetEntryModelImpl.getOriginalAssetListId()),
					Long.valueOf(assetListAssetEntryModelImpl.getOriginalAssetEntryId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALI_AEI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ALI_AEI, args);
		}
	}

	/**
	 * Creates a new asset list asset entry with the primary key. Does not add the asset list asset entry to the database.
	 *
	 * @param assetListAssetEntryId the primary key for the new asset list asset entry
	 * @return the new asset list asset entry
	 */
	public AssetListAssetEntry create(long assetListAssetEntryId) {
		AssetListAssetEntry assetListAssetEntry = new AssetListAssetEntryImpl();

		assetListAssetEntry.setNew(true);
		assetListAssetEntry.setPrimaryKey(assetListAssetEntryId);

		return assetListAssetEntry;
	}

	/**
	 * Removes the asset list asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListAssetEntryId the primary key of the asset list asset entry
	 * @return the asset list asset entry that was removed
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry remove(long assetListAssetEntryId)
		throws NoSuchAssetListAssetEntryException, SystemException {
		return remove(Long.valueOf(assetListAssetEntryId));
	}

	/**
	 * Removes the asset list asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset list asset entry
	 * @return the asset list asset entry that was removed
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetListAssetEntry remove(Serializable primaryKey)
		throws NoSuchAssetListAssetEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetListAssetEntry assetListAssetEntry = (AssetListAssetEntry)session.get(AssetListAssetEntryImpl.class,
					primaryKey);

			if (assetListAssetEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetListAssetEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetListAssetEntry);
		}
		catch (NoSuchAssetListAssetEntryException nsee) {
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
	protected AssetListAssetEntry removeImpl(
		AssetListAssetEntry assetListAssetEntry) throws SystemException {
		assetListAssetEntry = toUnwrappedModel(assetListAssetEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetListAssetEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetListAssetEntry);

		return assetListAssetEntry;
	}

	@Override
	public AssetListAssetEntry updateImpl(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry,
		boolean merge) throws SystemException {
		assetListAssetEntry = toUnwrappedModel(assetListAssetEntry);

		boolean isNew = assetListAssetEntry.isNew();

		AssetListAssetEntryModelImpl assetListAssetEntryModelImpl = (AssetListAssetEntryModelImpl)assetListAssetEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetListAssetEntry, merge);

			assetListAssetEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetListAssetEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetListAssetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetListAssetEntryModelImpl.getOriginalAssetListId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETLISTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTID,
					args);

				args = new Object[] {
						Long.valueOf(assetListAssetEntryModelImpl.getAssetListId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETLISTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTID,
					args);
			}

			if ((assetListAssetEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetListAssetEntryModelImpl.getOriginalAssetEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(assetListAssetEntryModelImpl.getAssetEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetListAssetEntryImpl.class, assetListAssetEntry.getPrimaryKey(),
			assetListAssetEntry);

		clearUniqueFindersCache(assetListAssetEntry);
		cacheUniqueFindersCache(assetListAssetEntry);

		return assetListAssetEntry;
	}

	protected AssetListAssetEntry toUnwrappedModel(
		AssetListAssetEntry assetListAssetEntry) {
		if (assetListAssetEntry instanceof AssetListAssetEntryImpl) {
			return assetListAssetEntry;
		}

		AssetListAssetEntryImpl assetListAssetEntryImpl = new AssetListAssetEntryImpl();

		assetListAssetEntryImpl.setNew(assetListAssetEntry.isNew());
		assetListAssetEntryImpl.setPrimaryKey(assetListAssetEntry.getPrimaryKey());

		assetListAssetEntryImpl.setAssetListAssetEntryId(assetListAssetEntry.getAssetListAssetEntryId());
		assetListAssetEntryImpl.setAssetListId(assetListAssetEntry.getAssetListId());
		assetListAssetEntryImpl.setAssetEntryId(assetListAssetEntry.getAssetEntryId());

		return assetListAssetEntryImpl;
	}

	/**
	 * Returns the asset list asset entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset list asset entry
	 * @return the asset list asset entry
	 * @throws com.liferay.portal.NoSuchModelException if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetListAssetEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset list asset entry with the primary key or throws a {@link com.liferay.osb.NoSuchAssetListAssetEntryException} if it could not be found.
	 *
	 * @param assetListAssetEntryId the primary key of the asset list asset entry
	 * @return the asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry findByPrimaryKey(long assetListAssetEntryId)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = fetchByPrimaryKey(assetListAssetEntryId);

		if (assetListAssetEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assetListAssetEntryId);
			}

			throw new NoSuchAssetListAssetEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetListAssetEntryId);
		}

		return assetListAssetEntry;
	}

	/**
	 * Returns the asset list asset entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset list asset entry
	 * @return the asset list asset entry, or <code>null</code> if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetListAssetEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset list asset entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetListAssetEntryId the primary key of the asset list asset entry
	 * @return the asset list asset entry, or <code>null</code> if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry fetchByPrimaryKey(long assetListAssetEntryId)
		throws SystemException {
		AssetListAssetEntry assetListAssetEntry = (AssetListAssetEntry)EntityCacheUtil.getResult(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
				AssetListAssetEntryImpl.class, assetListAssetEntryId);

		if (assetListAssetEntry == _nullAssetListAssetEntry) {
			return null;
		}

		if (assetListAssetEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetListAssetEntry = (AssetListAssetEntry)session.get(AssetListAssetEntryImpl.class,
						Long.valueOf(assetListAssetEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetListAssetEntry != null) {
					cacheResult(assetListAssetEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetListAssetEntryModelImpl.ENTITY_CACHE_ENABLED,
						AssetListAssetEntryImpl.class, assetListAssetEntryId,
						_nullAssetListAssetEntry);
				}

				closeSession(session);
			}
		}

		return assetListAssetEntry;
	}

	/**
	 * Returns all the asset list asset entries where assetListId = &#63;.
	 *
	 * @param assetListId the asset list ID
	 * @return the matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findByAssetListId(long assetListId)
		throws SystemException {
		return findByAssetListId(assetListId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list asset entries where assetListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetListId the asset list ID
	 * @param start the lower bound of the range of asset list asset entries
	 * @param end the upper bound of the range of asset list asset entries (not inclusive)
	 * @return the range of matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findByAssetListId(long assetListId,
		int start, int end) throws SystemException {
		return findByAssetListId(assetListId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list asset entries where assetListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetListId the asset list ID
	 * @param start the lower bound of the range of asset list asset entries
	 * @param end the upper bound of the range of asset list asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findByAssetListId(long assetListId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLISTID;
			finderArgs = new Object[] { assetListId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETLISTID;
			finderArgs = new Object[] { assetListId, start, end, orderByComparator };
		}

		List<AssetListAssetEntry> list = (List<AssetListAssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetListAssetEntry assetListAssetEntry : list) {
				if ((assetListId != assetListAssetEntry.getAssetListId())) {
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

			query.append(_SQL_SELECT_ASSETLISTASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSETLISTID_ASSETLISTID_2);

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

				qPos.add(assetListId);

				list = (List<AssetListAssetEntry>)QueryUtil.list(q,
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
	 * Returns the first asset list asset entry in the ordered set where assetListId = &#63;.
	 *
	 * @param assetListId the asset list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry findByAssetListId_First(long assetListId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = fetchByAssetListId_First(assetListId,
				orderByComparator);

		if (assetListAssetEntry != null) {
			return assetListAssetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetListId=");
		msg.append(assetListId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetListAssetEntryException(msg.toString());
	}

	/**
	 * Returns the first asset list asset entry in the ordered set where assetListId = &#63;.
	 *
	 * @param assetListId the asset list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry fetchByAssetListId_First(long assetListId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetListAssetEntry> list = findByAssetListId(assetListId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset list asset entry in the ordered set where assetListId = &#63;.
	 *
	 * @param assetListId the asset list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry findByAssetListId_Last(long assetListId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = fetchByAssetListId_Last(assetListId,
				orderByComparator);

		if (assetListAssetEntry != null) {
			return assetListAssetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetListId=");
		msg.append(assetListId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetListAssetEntryException(msg.toString());
	}

	/**
	 * Returns the last asset list asset entry in the ordered set where assetListId = &#63;.
	 *
	 * @param assetListId the asset list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry fetchByAssetListId_Last(long assetListId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetListId(assetListId);

		List<AssetListAssetEntry> list = findByAssetListId(assetListId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset list asset entries before and after the current asset list asset entry in the ordered set where assetListId = &#63;.
	 *
	 * @param assetListAssetEntryId the primary key of the current asset list asset entry
	 * @param assetListId the asset list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry[] findByAssetListId_PrevAndNext(
		long assetListAssetEntryId, long assetListId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = findByPrimaryKey(assetListAssetEntryId);

		Session session = null;

		try {
			session = openSession();

			AssetListAssetEntry[] array = new AssetListAssetEntryImpl[3];

			array[0] = getByAssetListId_PrevAndNext(session,
					assetListAssetEntry, assetListId, orderByComparator, true);

			array[1] = assetListAssetEntry;

			array[2] = getByAssetListId_PrevAndNext(session,
					assetListAssetEntry, assetListId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetListAssetEntry getByAssetListId_PrevAndNext(
		Session session, AssetListAssetEntry assetListAssetEntry,
		long assetListId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLISTASSETENTRY_WHERE);

		query.append(_FINDER_COLUMN_ASSETLISTID_ASSETLISTID_2);

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

		qPos.add(assetListId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetListAssetEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetListAssetEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset list asset entries where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findByAssetEntryId(long assetEntryId)
		throws SystemException {
		return findByAssetEntryId(assetEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list asset entries where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset list asset entries
	 * @param end the upper bound of the range of asset list asset entries (not inclusive)
	 * @return the range of matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findByAssetEntryId(long assetEntryId,
		int start, int end) throws SystemException {
		return findByAssetEntryId(assetEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list asset entries where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset list asset entries
	 * @param end the upper bound of the range of asset list asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findByAssetEntryId(long assetEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID;
			finderArgs = new Object[] { assetEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYID;
			finderArgs = new Object[] {
					assetEntryId,
					
					start, end, orderByComparator
				};
		}

		List<AssetListAssetEntry> list = (List<AssetListAssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetListAssetEntry assetListAssetEntry : list) {
				if ((assetEntryId != assetListAssetEntry.getAssetEntryId())) {
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

			query.append(_SQL_SELECT_ASSETLISTASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

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

				qPos.add(assetEntryId);

				list = (List<AssetListAssetEntry>)QueryUtil.list(q,
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
	 * Returns the first asset list asset entry in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry findByAssetEntryId_First(long assetEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = fetchByAssetEntryId_First(assetEntryId,
				orderByComparator);

		if (assetListAssetEntry != null) {
			return assetListAssetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetListAssetEntryException(msg.toString());
	}

	/**
	 * Returns the first asset list asset entry in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry fetchByAssetEntryId_First(long assetEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetListAssetEntry> list = findByAssetEntryId(assetEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset list asset entry in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry findByAssetEntryId_Last(long assetEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = fetchByAssetEntryId_Last(assetEntryId,
				orderByComparator);

		if (assetListAssetEntry != null) {
			return assetListAssetEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetListAssetEntryException(msg.toString());
	}

	/**
	 * Returns the last asset list asset entry in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry fetchByAssetEntryId_Last(long assetEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetEntryId(assetEntryId);

		List<AssetListAssetEntry> list = findByAssetEntryId(assetEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset list asset entries before and after the current asset list asset entry in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetListAssetEntryId the primary key of the current asset list asset entry
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a asset list asset entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry[] findByAssetEntryId_PrevAndNext(
		long assetListAssetEntryId, long assetEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = findByPrimaryKey(assetListAssetEntryId);

		Session session = null;

		try {
			session = openSession();

			AssetListAssetEntry[] array = new AssetListAssetEntryImpl[3];

			array[0] = getByAssetEntryId_PrevAndNext(session,
					assetListAssetEntry, assetEntryId, orderByComparator, true);

			array[1] = assetListAssetEntry;

			array[2] = getByAssetEntryId_PrevAndNext(session,
					assetListAssetEntry, assetEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetListAssetEntry getByAssetEntryId_PrevAndNext(
		Session session, AssetListAssetEntry assetListAssetEntry,
		long assetEntryId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLISTASSETENTRY_WHERE);

		query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

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

		qPos.add(assetEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetListAssetEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetListAssetEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchAssetListAssetEntryException} if it could not be found.
	 *
	 * @param assetListId the asset list ID
	 * @param assetEntryId the asset entry ID
	 * @return the matching asset list asset entry
	 * @throws com.liferay.osb.NoSuchAssetListAssetEntryException if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry findByALI_AEI(long assetListId, long assetEntryId)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = fetchByALI_AEI(assetListId,
				assetEntryId);

		if (assetListAssetEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assetListId=");
			msg.append(assetListId);

			msg.append(", assetEntryId=");
			msg.append(assetEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetListAssetEntryException(msg.toString());
		}

		return assetListAssetEntry;
	}

	/**
	 * Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param assetListId the asset list ID
	 * @param assetEntryId the asset entry ID
	 * @return the matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry fetchByALI_AEI(long assetListId,
		long assetEntryId) throws SystemException {
		return fetchByALI_AEI(assetListId, assetEntryId, true);
	}

	/**
	 * Returns the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param assetListId the asset list ID
	 * @param assetEntryId the asset entry ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset list asset entry, or <code>null</code> if a matching asset list asset entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry fetchByALI_AEI(long assetListId,
		long assetEntryId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { assetListId, assetEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ALI_AEI,
					finderArgs, this);
		}

		if (result instanceof AssetListAssetEntry) {
			AssetListAssetEntry assetListAssetEntry = (AssetListAssetEntry)result;

			if ((assetListId != assetListAssetEntry.getAssetListId()) ||
					(assetEntryId != assetListAssetEntry.getAssetEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ASSETLISTASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ALI_AEI_ASSETLISTID_2);

			query.append(_FINDER_COLUMN_ALI_AEI_ASSETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListId);

				qPos.add(assetEntryId);

				List<AssetListAssetEntry> list = q.list();

				result = list;

				AssetListAssetEntry assetListAssetEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ALI_AEI,
						finderArgs, list);
				}
				else {
					assetListAssetEntry = list.get(0);

					cacheResult(assetListAssetEntry);

					if ((assetListAssetEntry.getAssetListId() != assetListId) ||
							(assetListAssetEntry.getAssetEntryId() != assetEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ALI_AEI,
							finderArgs, assetListAssetEntry);
					}
				}

				return assetListAssetEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ALI_AEI,
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
				return (AssetListAssetEntry)result;
			}
		}
	}

	/**
	 * Returns all the asset list asset entries.
	 *
	 * @return the asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset list asset entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list asset entries
	 * @param end the upper bound of the range of asset list asset entries (not inclusive)
	 * @return the range of asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset list asset entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list asset entries
	 * @param end the upper bound of the range of asset list asset entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetListAssetEntry> findAll(int start, int end,
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

		List<AssetListAssetEntry> list = (List<AssetListAssetEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETLISTASSETENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETLISTASSETENTRY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetListAssetEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetListAssetEntry>)QueryUtil.list(q,
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
	 * Removes all the asset list asset entries where assetListId = &#63; from the database.
	 *
	 * @param assetListId the asset list ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetListId(long assetListId) throws SystemException {
		for (AssetListAssetEntry assetListAssetEntry : findByAssetListId(
				assetListId)) {
			remove(assetListAssetEntry);
		}
	}

	/**
	 * Removes all the asset list asset entries where assetEntryId = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetEntryId(long assetEntryId)
		throws SystemException {
		for (AssetListAssetEntry assetListAssetEntry : findByAssetEntryId(
				assetEntryId)) {
			remove(assetListAssetEntry);
		}
	}

	/**
	 * Removes the asset list asset entry where assetListId = &#63; and assetEntryId = &#63; from the database.
	 *
	 * @param assetListId the asset list ID
	 * @param assetEntryId the asset entry ID
	 * @return the asset list asset entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetListAssetEntry removeByALI_AEI(long assetListId,
		long assetEntryId)
		throws NoSuchAssetListAssetEntryException, SystemException {
		AssetListAssetEntry assetListAssetEntry = findByALI_AEI(assetListId,
				assetEntryId);

		return remove(assetListAssetEntry);
	}

	/**
	 * Removes all the asset list asset entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetListAssetEntry assetListAssetEntry : findAll()) {
			remove(assetListAssetEntry);
		}
	}

	/**
	 * Returns the number of asset list asset entries where assetListId = &#63;.
	 *
	 * @param assetListId the asset list ID
	 * @return the number of matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetListId(long assetListId) throws SystemException {
		Object[] finderArgs = new Object[] { assetListId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETLISTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETLISTASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSETLISTID_ASSETLISTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETLISTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset list asset entries where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the number of matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetEntryId(long assetEntryId) throws SystemException {
		Object[] finderArgs = new Object[] { assetEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETLISTASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset list asset entries where assetListId = &#63; and assetEntryId = &#63;.
	 *
	 * @param assetListId the asset list ID
	 * @param assetEntryId the asset entry ID
	 * @return the number of matching asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByALI_AEI(long assetListId, long assetEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetListId, assetEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ALI_AEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLISTASSETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ALI_AEI_ASSETLISTID_2);

			query.append(_FINDER_COLUMN_ALI_AEI_ASSETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetListId);

				qPos.add(assetEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALI_AEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset list asset entries.
	 *
	 * @return the number of asset list asset entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETLISTASSETENTRY);

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
	 * Initializes the asset list asset entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetListAssetEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetListAssetEntry>> listenersList = new ArrayList<ModelListener<AssetListAssetEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetListAssetEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetListAssetEntryImpl.class.getName());
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
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	private static final String _SQL_SELECT_ASSETLISTASSETENTRY = "SELECT assetListAssetEntry FROM AssetListAssetEntry assetListAssetEntry";
	private static final String _SQL_SELECT_ASSETLISTASSETENTRY_WHERE = "SELECT assetListAssetEntry FROM AssetListAssetEntry assetListAssetEntry WHERE ";
	private static final String _SQL_COUNT_ASSETLISTASSETENTRY = "SELECT COUNT(assetListAssetEntry) FROM AssetListAssetEntry assetListAssetEntry";
	private static final String _SQL_COUNT_ASSETLISTASSETENTRY_WHERE = "SELECT COUNT(assetListAssetEntry) FROM AssetListAssetEntry assetListAssetEntry WHERE ";
	private static final String _FINDER_COLUMN_ASSETLISTID_ASSETLISTID_2 = "assetListAssetEntry.assetListId = ?";
	private static final String _FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2 = "assetListAssetEntry.assetEntryId = ?";
	private static final String _FINDER_COLUMN_ALI_AEI_ASSETLISTID_2 = "assetListAssetEntry.assetListId = ? AND ";
	private static final String _FINDER_COLUMN_ALI_AEI_ASSETENTRYID_2 = "assetListAssetEntry.assetEntryId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetListAssetEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetListAssetEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetListAssetEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetListAssetEntryPersistenceImpl.class);
	private static AssetListAssetEntry _nullAssetListAssetEntry = new AssetListAssetEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetListAssetEntry> toCacheModel() {
				return _nullAssetListAssetEntryCacheModel;
			}
		};

	private static CacheModel<AssetListAssetEntry> _nullAssetListAssetEntryCacheModel =
		new CacheModel<AssetListAssetEntry>() {
			public AssetListAssetEntry toEntityModel() {
				return _nullAssetListAssetEntry;
			}
		};
}
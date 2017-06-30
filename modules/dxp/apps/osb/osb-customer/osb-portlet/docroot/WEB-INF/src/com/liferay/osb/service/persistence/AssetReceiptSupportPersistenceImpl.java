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

import com.liferay.osb.NoSuchAssetReceiptSupportException;
import com.liferay.osb.model.AssetReceiptSupport;
import com.liferay.osb.model.impl.AssetReceiptSupportImpl;
import com.liferay.osb.model.impl.AssetReceiptSupportModelImpl;

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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the asset receipt support service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptSupportPersistence
 * @see AssetReceiptSupportUtil
 * @generated
 */
public class AssetReceiptSupportPersistenceImpl extends BasePersistenceImpl<AssetReceiptSupport>
	implements AssetReceiptSupportPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetReceiptSupportUtil} to access the asset receipt support persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetReceiptSupportImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AssetReceiptSupportModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETRECEIPTID =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetReceiptId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetReceiptId",
			new String[] { Long.class.getName() },
			AssetReceiptSupportModelImpl.ASSETRECEIPTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETRECEIPTID = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetReceiptId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_PCNI_PCPK =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARI_PCNI_PCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARI_PCNI_PCPK =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARI_PCNI_PCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AssetReceiptSupportModelImpl.ASSETRECEIPTID_COLUMN_BITMASK |
			AssetReceiptSupportModelImpl.PRODUCTCLASSNAMEID_COLUMN_BITMASK |
			AssetReceiptSupportModelImpl.PRODUCTCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ARI_PCNI_PCPK = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARI_PCNI_PCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_GTSD_UT =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARI_GtSD_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_GTSD_UT =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByARI_GtSD_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_LTSD_GTED_UT =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARI_LtSD_GtED_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_LTSD_GTED_UT =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByARI_LtSD_GtED_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_PCNI_PCPK_LTSD_GTED =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByARI_PCNI_PCPK_LtSD_GtED",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_PCNI_PCPK_LTSD_GTED =
		new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByARI_PCNI_PCPK_LtSD_GtED",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName(), Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptSupportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset receipt support in the entity cache if it is enabled.
	 *
	 * @param assetReceiptSupport the asset receipt support
	 */
	public void cacheResult(AssetReceiptSupport assetReceiptSupport) {
		EntityCacheUtil.putResult(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportImpl.class, assetReceiptSupport.getPrimaryKey(),
			assetReceiptSupport);

		assetReceiptSupport.resetOriginalValues();
	}

	/**
	 * Caches the asset receipt supports in the entity cache if it is enabled.
	 *
	 * @param assetReceiptSupports the asset receipt supports
	 */
	public void cacheResult(List<AssetReceiptSupport> assetReceiptSupports) {
		for (AssetReceiptSupport assetReceiptSupport : assetReceiptSupports) {
			if (EntityCacheUtil.getResult(
						AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptSupportImpl.class,
						assetReceiptSupport.getPrimaryKey()) == null) {
				cacheResult(assetReceiptSupport);
			}
			else {
				assetReceiptSupport.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset receipt supports.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetReceiptSupportImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetReceiptSupportImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset receipt support.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetReceiptSupport assetReceiptSupport) {
		EntityCacheUtil.removeResult(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportImpl.class, assetReceiptSupport.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetReceiptSupport> assetReceiptSupports) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetReceiptSupport assetReceiptSupport : assetReceiptSupports) {
			EntityCacheUtil.removeResult(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptSupportImpl.class,
				assetReceiptSupport.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset receipt support with the primary key. Does not add the asset receipt support to the database.
	 *
	 * @param assetReceiptSupportId the primary key for the new asset receipt support
	 * @return the new asset receipt support
	 */
	public AssetReceiptSupport create(long assetReceiptSupportId) {
		AssetReceiptSupport assetReceiptSupport = new AssetReceiptSupportImpl();

		assetReceiptSupport.setNew(true);
		assetReceiptSupport.setPrimaryKey(assetReceiptSupportId);

		String uuid = PortalUUIDUtil.generate();

		assetReceiptSupport.setUuid(uuid);

		return assetReceiptSupport;
	}

	/**
	 * Removes the asset receipt support with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetReceiptSupportId the primary key of the asset receipt support
	 * @return the asset receipt support that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport remove(long assetReceiptSupportId)
		throws NoSuchAssetReceiptSupportException, SystemException {
		return remove(Long.valueOf(assetReceiptSupportId));
	}

	/**
	 * Removes the asset receipt support with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset receipt support
	 * @return the asset receipt support that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptSupport remove(Serializable primaryKey)
		throws NoSuchAssetReceiptSupportException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetReceiptSupport assetReceiptSupport = (AssetReceiptSupport)session.get(AssetReceiptSupportImpl.class,
					primaryKey);

			if (assetReceiptSupport == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetReceiptSupportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetReceiptSupport);
		}
		catch (NoSuchAssetReceiptSupportException nsee) {
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
	protected AssetReceiptSupport removeImpl(
		AssetReceiptSupport assetReceiptSupport) throws SystemException {
		assetReceiptSupport = toUnwrappedModel(assetReceiptSupport);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetReceiptSupport);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetReceiptSupport);

		return assetReceiptSupport;
	}

	@Override
	public AssetReceiptSupport updateImpl(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport,
		boolean merge) throws SystemException {
		assetReceiptSupport = toUnwrappedModel(assetReceiptSupport);

		boolean isNew = assetReceiptSupport.isNew();

		AssetReceiptSupportModelImpl assetReceiptSupportModelImpl = (AssetReceiptSupportModelImpl)assetReceiptSupport;

		if (Validator.isNull(assetReceiptSupport.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetReceiptSupport.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetReceiptSupport, merge);

			assetReceiptSupport.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetReceiptSupportModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetReceiptSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetReceiptSupportModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { assetReceiptSupportModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((assetReceiptSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptSupportModelImpl.getOriginalAssetReceiptId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETRECEIPTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptSupportModelImpl.getAssetReceiptId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETRECEIPTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID,
					args);
			}

			if ((assetReceiptSupportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARI_PCNI_PCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptSupportModelImpl.getOriginalAssetReceiptId()),
						Long.valueOf(assetReceiptSupportModelImpl.getOriginalProductClassNameId()),
						Long.valueOf(assetReceiptSupportModelImpl.getOriginalProductClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARI_PCNI_PCPK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARI_PCNI_PCPK,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptSupportModelImpl.getAssetReceiptId()),
						Long.valueOf(assetReceiptSupportModelImpl.getProductClassNameId()),
						Long.valueOf(assetReceiptSupportModelImpl.getProductClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARI_PCNI_PCPK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARI_PCNI_PCPK,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptSupportImpl.class, assetReceiptSupport.getPrimaryKey(),
			assetReceiptSupport);

		return assetReceiptSupport;
	}

	protected AssetReceiptSupport toUnwrappedModel(
		AssetReceiptSupport assetReceiptSupport) {
		if (assetReceiptSupport instanceof AssetReceiptSupportImpl) {
			return assetReceiptSupport;
		}

		AssetReceiptSupportImpl assetReceiptSupportImpl = new AssetReceiptSupportImpl();

		assetReceiptSupportImpl.setNew(assetReceiptSupport.isNew());
		assetReceiptSupportImpl.setPrimaryKey(assetReceiptSupport.getPrimaryKey());

		assetReceiptSupportImpl.setUuid(assetReceiptSupport.getUuid());
		assetReceiptSupportImpl.setAssetReceiptSupportId(assetReceiptSupport.getAssetReceiptSupportId());
		assetReceiptSupportImpl.setUserId(assetReceiptSupport.getUserId());
		assetReceiptSupportImpl.setUserName(assetReceiptSupport.getUserName());
		assetReceiptSupportImpl.setCreateDate(assetReceiptSupport.getCreateDate());
		assetReceiptSupportImpl.setModifiedDate(assetReceiptSupport.getModifiedDate());
		assetReceiptSupportImpl.setAssetReceiptId(assetReceiptSupport.getAssetReceiptId());
		assetReceiptSupportImpl.setAssetLicenseId(assetReceiptSupport.getAssetLicenseId());
		assetReceiptSupportImpl.setAssetEntryId(assetReceiptSupport.getAssetEntryId());
		assetReceiptSupportImpl.setOwnerClassNameId(assetReceiptSupport.getOwnerClassNameId());
		assetReceiptSupportImpl.setOwnerClassPK(assetReceiptSupport.getOwnerClassPK());
		assetReceiptSupportImpl.setProductClassNameId(assetReceiptSupport.getProductClassNameId());
		assetReceiptSupportImpl.setProductClassPK(assetReceiptSupport.getProductClassPK());
		assetReceiptSupportImpl.setProductId(assetReceiptSupport.getProductId());
		assetReceiptSupportImpl.setStartDate(assetReceiptSupport.getStartDate());
		assetReceiptSupportImpl.setEndDate(assetReceiptSupport.getEndDate());
		assetReceiptSupportImpl.setUsageType(assetReceiptSupport.getUsageType());
		assetReceiptSupportImpl.setSupportLifetime(assetReceiptSupport.getSupportLifetime());

		return assetReceiptSupportImpl;
	}

	/**
	 * Returns the asset receipt support with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt support
	 * @return the asset receipt support
	 * @throws com.liferay.portal.NoSuchModelException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptSupport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt support with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptSupportException} if it could not be found.
	 *
	 * @param assetReceiptSupportId the primary key of the asset receipt support
	 * @return the asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByPrimaryKey(long assetReceiptSupportId)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByPrimaryKey(assetReceiptSupportId);

		if (assetReceiptSupport == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assetReceiptSupportId);
			}

			throw new NoSuchAssetReceiptSupportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetReceiptSupportId);
		}

		return assetReceiptSupport;
	}

	/**
	 * Returns the asset receipt support with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt support
	 * @return the asset receipt support, or <code>null</code> if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptSupport fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt support with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetReceiptSupportId the primary key of the asset receipt support
	 * @return the asset receipt support, or <code>null</code> if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByPrimaryKey(long assetReceiptSupportId)
		throws SystemException {
		AssetReceiptSupport assetReceiptSupport = (AssetReceiptSupport)EntityCacheUtil.getResult(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptSupportImpl.class, assetReceiptSupportId);

		if (assetReceiptSupport == _nullAssetReceiptSupport) {
			return null;
		}

		if (assetReceiptSupport == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetReceiptSupport = (AssetReceiptSupport)session.get(AssetReceiptSupportImpl.class,
						Long.valueOf(assetReceiptSupportId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetReceiptSupport != null) {
					cacheResult(assetReceiptSupport);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetReceiptSupportModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptSupportImpl.class, assetReceiptSupportId,
						_nullAssetReceiptSupport);
				}

				closeSession(session);
			}
		}

		return assetReceiptSupport;
	}

	/**
	 * Returns all the asset receipt supports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @return the range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByUuid(String uuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<AssetReceiptSupport> list = (List<AssetReceiptSupport>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptSupport assetReceiptSupport : list) {
				if (!Validator.equals(uuid, assetReceiptSupport.getUuid())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

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

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<AssetReceiptSupport>)QueryUtil.list(q,
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
	 * Returns the first asset receipt support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByUuid_First(uuid,
				orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the first asset receipt support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceiptSupport> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByUuid_Last(uuid,
				orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the last asset receipt support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<AssetReceiptSupport> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt supports before and after the current asset receipt support in the ordered set where uuid = &#63;.
	 *
	 * @param assetReceiptSupportId the primary key of the current asset receipt support
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport[] findByUuid_PrevAndNext(
		long assetReceiptSupportId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = findByPrimaryKey(assetReceiptSupportId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptSupport[] array = new AssetReceiptSupportImpl[3];

			array[0] = getByUuid_PrevAndNext(session, assetReceiptSupport,
					uuid, orderByComparator, true);

			array[1] = assetReceiptSupport;

			array[2] = getByUuid_PrevAndNext(session, assetReceiptSupport,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceiptSupport getByUuid_PrevAndNext(Session session,
		AssetReceiptSupport assetReceiptSupport, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt supports where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @return the matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByAssetReceiptId(long assetReceiptId)
		throws SystemException {
		return findByAssetReceiptId(assetReceiptId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt supports where assetReceiptId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @return the range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByAssetReceiptId(long assetReceiptId,
		int start, int end) throws SystemException {
		return findByAssetReceiptId(assetReceiptId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByAssetReceiptId(long assetReceiptId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID;
			finderArgs = new Object[] { assetReceiptId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETRECEIPTID;
			finderArgs = new Object[] {
					assetReceiptId,
					
					start, end, orderByComparator
				};
		}

		List<AssetReceiptSupport> list = (List<AssetReceiptSupport>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptSupport assetReceiptSupport : list) {
				if ((assetReceiptId != assetReceiptSupport.getAssetReceiptId())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ASSETRECEIPTID_ASSETRECEIPTID_2);

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

				qPos.add(assetReceiptId);

				list = (List<AssetReceiptSupport>)QueryUtil.list(q,
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
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByAssetReceiptId_First(long assetReceiptId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByAssetReceiptId_First(assetReceiptId,
				orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByAssetReceiptId_First(
		long assetReceiptId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptSupport> list = findByAssetReceiptId(assetReceiptId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByAssetReceiptId_Last(long assetReceiptId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByAssetReceiptId_Last(assetReceiptId,
				orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByAssetReceiptId_Last(long assetReceiptId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetReceiptId(assetReceiptId);

		List<AssetReceiptSupport> list = findByAssetReceiptId(assetReceiptId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptSupportId the primary key of the current asset receipt support
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport[] findByAssetReceiptId_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = findByPrimaryKey(assetReceiptSupportId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptSupport[] array = new AssetReceiptSupportImpl[3];

			array[0] = getByAssetReceiptId_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, orderByComparator, true);

			array[1] = assetReceiptSupport;

			array[2] = getByAssetReceiptId_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, orderByComparator,
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

	protected AssetReceiptSupport getByAssetReceiptId_PrevAndNext(
		Session session, AssetReceiptSupport assetReceiptSupport,
		long assetReceiptId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

		query.append(_FINDER_COLUMN_ASSETRECEIPTID_ASSETRECEIPTID_2);

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

		qPos.add(assetReceiptId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK) throws SystemException {
		return findByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
			productClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @return the range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK, int start, int end)
		throws SystemException {
		return findByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
			productClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARI_PCNI_PCPK;
			finderArgs = new Object[] {
					assetReceiptId, productClassNameId, productClassPK
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_PCNI_PCPK;
			finderArgs = new Object[] {
					assetReceiptId, productClassNameId, productClassPK,
					
					start, end, orderByComparator
				};
		}

		List<AssetReceiptSupport> list = (List<AssetReceiptSupport>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptSupport assetReceiptSupport : list) {
				if ((assetReceiptId != assetReceiptSupport.getAssetReceiptId()) ||
						(productClassNameId != assetReceiptSupport.getProductClassNameId()) ||
						(productClassPK != assetReceiptSupport.getProductClassPK())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_ASSETRECEIPTID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSPK_2);

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

				qPos.add(assetReceiptId);

				qPos.add(productClassNameId);

				qPos.add(productClassPK);

				list = (List<AssetReceiptSupport>)QueryUtil.list(q,
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
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_PCNI_PCPK_First(long assetReceiptId,
		long productClassNameId, long productClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_PCNI_PCPK_First(assetReceiptId,
				productClassNameId, productClassPK, orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", productClassNameId=");
		msg.append(productClassNameId);

		msg.append(", productClassPK=");
		msg.append(productClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_PCNI_PCPK_First(long assetReceiptId,
		long productClassNameId, long productClassPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceiptSupport> list = findByARI_PCNI_PCPK(assetReceiptId,
				productClassNameId, productClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_PCNI_PCPK_Last(long assetReceiptId,
		long productClassNameId, long productClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_PCNI_PCPK_Last(assetReceiptId,
				productClassNameId, productClassPK, orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", productClassNameId=");
		msg.append(productClassNameId);

		msg.append(", productClassPK=");
		msg.append(productClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_PCNI_PCPK_Last(long assetReceiptId,
		long productClassNameId, long productClassPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByARI_PCNI_PCPK(assetReceiptId, productClassNameId,
				productClassPK);

		List<AssetReceiptSupport> list = findByARI_PCNI_PCPK(assetReceiptId,
				productClassNameId, productClassPK, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param assetReceiptSupportId the primary key of the current asset receipt support
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport[] findByARI_PCNI_PCPK_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		long productClassNameId, long productClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = findByPrimaryKey(assetReceiptSupportId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptSupport[] array = new AssetReceiptSupportImpl[3];

			array[0] = getByARI_PCNI_PCPK_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, productClassNameId,
					productClassPK, orderByComparator, true);

			array[1] = assetReceiptSupport;

			array[2] = getByARI_PCNI_PCPK_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, productClassNameId,
					productClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceiptSupport getByARI_PCNI_PCPK_PrevAndNext(
		Session session, AssetReceiptSupport assetReceiptSupport,
		long assetReceiptId, long productClassNameId, long productClassPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

		query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_ASSETRECEIPTID_2);

		query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSPK_2);

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

		qPos.add(assetReceiptId);

		qPos.add(productClassNameId);

		qPos.add(productClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @return the matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_GtSD_UT(long assetReceiptId,
		Date startDate, int usageType) throws SystemException {
		return findByARI_GtSD_UT(assetReceiptId, startDate, usageType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @return the range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_GtSD_UT(long assetReceiptId,
		Date startDate, int usageType, int start, int end)
		throws SystemException {
		return findByARI_GtSD_UT(assetReceiptId, startDate, usageType, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_GtSD_UT(long assetReceiptId,
		Date startDate, int usageType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_GTSD_UT;
		finderArgs = new Object[] {
				assetReceiptId, startDate, usageType,
				
				start, end, orderByComparator
			};

		List<AssetReceiptSupport> list = (List<AssetReceiptSupport>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptSupport assetReceiptSupport : list) {
				if ((assetReceiptId != assetReceiptSupport.getAssetReceiptId()) ||
						!Validator.equals(startDate,
							assetReceiptSupport.getStartDate()) ||
						(usageType != assetReceiptSupport.getUsageType())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_GTSD_UT_ASSETRECEIPTID_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_2);
			}

			query.append(_FINDER_COLUMN_ARI_GTSD_UT_USAGETYPE_2);

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

				qPos.add(assetReceiptId);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				qPos.add(usageType);

				list = (List<AssetReceiptSupport>)QueryUtil.list(q,
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
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_GtSD_UT_First(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_GtSD_UT_First(assetReceiptId,
				startDate, usageType, orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_GtSD_UT_First(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptSupport> list = findByARI_GtSD_UT(assetReceiptId,
				startDate, usageType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_GtSD_UT_Last(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_GtSD_UT_Last(assetReceiptId,
				startDate, usageType, orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_GtSD_UT_Last(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByARI_GtSD_UT(assetReceiptId, startDate, usageType);

		List<AssetReceiptSupport> list = findByARI_GtSD_UT(assetReceiptId,
				startDate, usageType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptSupportId the primary key of the current asset receipt support
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport[] findByARI_GtSD_UT_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId, Date startDate,
		int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = findByPrimaryKey(assetReceiptSupportId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptSupport[] array = new AssetReceiptSupportImpl[3];

			array[0] = getByARI_GtSD_UT_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, startDate, usageType,
					orderByComparator, true);

			array[1] = assetReceiptSupport;

			array[2] = getByARI_GtSD_UT_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, startDate, usageType,
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

	protected AssetReceiptSupport getByARI_GtSD_UT_PrevAndNext(
		Session session, AssetReceiptSupport assetReceiptSupport,
		long assetReceiptId, Date startDate, int usageType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

		query.append(_FINDER_COLUMN_ARI_GTSD_UT_ASSETRECEIPTID_2);

		if (startDate == null) {
			query.append(_FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_2);
		}

		query.append(_FINDER_COLUMN_ARI_GTSD_UT_USAGETYPE_2);

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

		qPos.add(assetReceiptId);

		if (startDate != null) {
			qPos.add(CalendarUtil.getTimestamp(startDate));
		}

		qPos.add(usageType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @return the matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, Date startDate, Date endDate, int usageType)
		throws SystemException {
		return findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @return the range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		int start, int end) throws SystemException {
		return findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_LtSD_GtED_UT(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_LTSD_GTED_UT;
		finderArgs = new Object[] {
				assetReceiptId, startDate, endDate, usageType,
				
				start, end, orderByComparator
			};

		List<AssetReceiptSupport> list = (List<AssetReceiptSupport>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptSupport assetReceiptSupport : list) {
				if ((assetReceiptId != assetReceiptSupport.getAssetReceiptId()) ||
						!Validator.equals(startDate,
							assetReceiptSupport.getStartDate()) ||
						!Validator.equals(endDate,
							assetReceiptSupport.getEndDate()) ||
						(usageType != assetReceiptSupport.getUsageType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ASSETRECEIPTID_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_2);
			}

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_2);
			}

			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_USAGETYPE_2);

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

				qPos.add(assetReceiptId);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				if (endDate != null) {
					qPos.add(CalendarUtil.getTimestamp(endDate));
				}

				qPos.add(usageType);

				list = (List<AssetReceiptSupport>)QueryUtil.list(q,
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
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_LtSD_GtED_UT_First(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_LtSD_GtED_UT_First(assetReceiptId,
				startDate, endDate, usageType, orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_LtSD_GtED_UT_First(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceiptSupport> list = findByARI_LtSD_GtED_UT(assetReceiptId,
				startDate, endDate, usageType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_LtSD_GtED_UT_Last(assetReceiptId,
				startDate, endDate, usageType, orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
				usageType);

		List<AssetReceiptSupport> list = findByARI_LtSD_GtED_UT(assetReceiptId,
				startDate, endDate, usageType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptSupportId the primary key of the current asset receipt support
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport[] findByARI_LtSD_GtED_UT_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId, Date startDate,
		Date endDate, int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = findByPrimaryKey(assetReceiptSupportId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptSupport[] array = new AssetReceiptSupportImpl[3];

			array[0] = getByARI_LtSD_GtED_UT_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, startDate, endDate,
					usageType, orderByComparator, true);

			array[1] = assetReceiptSupport;

			array[2] = getByARI_LtSD_GtED_UT_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, startDate, endDate,
					usageType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceiptSupport getByARI_LtSD_GtED_UT_PrevAndNext(
		Session session, AssetReceiptSupport assetReceiptSupport,
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

		query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ASSETRECEIPTID_2);

		if (startDate == null) {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_2);
		}

		if (endDate == null) {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_2);
		}

		query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_USAGETYPE_2);

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

		qPos.add(assetReceiptId);

		if (startDate != null) {
			qPos.add(CalendarUtil.getTimestamp(startDate));
		}

		if (endDate != null) {
			qPos.add(CalendarUtil.getTimestamp(endDate));
		}

		qPos.add(usageType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate) throws SystemException {
		return findByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @return the range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate, int start, int end)
		throws SystemException {
		return findByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
			productClassNameId, productClassPK, startDate, endDate, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findByARI_PCNI_PCPK_LtSD_GtED(
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_PCNI_PCPK_LTSD_GTED;
		finderArgs = new Object[] {
				assetReceiptId, productClassNameId, productClassPK, startDate,
				endDate,
				
				start, end, orderByComparator
			};

		List<AssetReceiptSupport> list = (List<AssetReceiptSupport>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptSupport assetReceiptSupport : list) {
				if ((assetReceiptId != assetReceiptSupport.getAssetReceiptId()) ||
						(productClassNameId != assetReceiptSupport.getProductClassNameId()) ||
						(productClassPK != assetReceiptSupport.getProductClassPK()) ||
						!Validator.equals(startDate,
							assetReceiptSupport.getStartDate()) ||
						!Validator.equals(endDate,
							assetReceiptSupport.getEndDate())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ASSETRECEIPTID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSPK_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_2);
			}

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_2);
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

				qPos.add(assetReceiptId);

				qPos.add(productClassNameId);

				qPos.add(productClassPK);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				if (endDate != null) {
					qPos.add(CalendarUtil.getTimestamp(endDate));
				}

				list = (List<AssetReceiptSupport>)QueryUtil.list(q,
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
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_PCNI_PCPK_LtSD_GtED_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_PCNI_PCPK_LtSD_GtED_First(assetReceiptId,
				productClassNameId, productClassPK, startDate, endDate,
				orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", productClassNameId=");
		msg.append(productClassNameId);

		msg.append(", productClassPK=");
		msg.append(productClassPK);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the first asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_PCNI_PCPK_LtSD_GtED_First(
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptSupport> list = findByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
				productClassNameId, productClassPK, startDate, endDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport findByARI_PCNI_PCPK_LtSD_GtED_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = fetchByARI_PCNI_PCPK_LtSD_GtED_Last(assetReceiptId,
				productClassNameId, productClassPK, startDate, endDate,
				orderByComparator);

		if (assetReceiptSupport != null) {
			return assetReceiptSupport;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", productClassNameId=");
		msg.append(productClassNameId);

		msg.append(", productClassPK=");
		msg.append(productClassPK);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptSupportException(msg.toString());
	}

	/**
	 * Returns the last asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt support, or <code>null</code> if a matching asset receipt support could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport fetchByARI_PCNI_PCPK_LtSD_GtED_Last(
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
				productClassNameId, productClassPK, startDate, endDate);

		List<AssetReceiptSupport> list = findByARI_PCNI_PCPK_LtSD_GtED(assetReceiptId,
				productClassNameId, productClassPK, startDate, endDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt supports before and after the current asset receipt support in the ordered set where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptSupportId the primary key of the current asset receipt support
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt support
	 * @throws com.liferay.osb.NoSuchAssetReceiptSupportException if a asset receipt support with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptSupport[] findByARI_PCNI_PCPK_LtSD_GtED_PrevAndNext(
		long assetReceiptSupportId, long assetReceiptId,
		long productClassNameId, long productClassPK, Date startDate,
		Date endDate, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptSupportException, SystemException {
		AssetReceiptSupport assetReceiptSupport = findByPrimaryKey(assetReceiptSupportId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptSupport[] array = new AssetReceiptSupportImpl[3];

			array[0] = getByARI_PCNI_PCPK_LtSD_GtED_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, productClassNameId,
					productClassPK, startDate, endDate, orderByComparator, true);

			array[1] = assetReceiptSupport;

			array[2] = getByARI_PCNI_PCPK_LtSD_GtED_PrevAndNext(session,
					assetReceiptSupport, assetReceiptId, productClassNameId,
					productClassPK, startDate, endDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceiptSupport getByARI_PCNI_PCPK_LtSD_GtED_PrevAndNext(
		Session session, AssetReceiptSupport assetReceiptSupport,
		long assetReceiptId, long productClassNameId, long productClassPK,
		Date startDate, Date endDate, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE);

		query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ASSETRECEIPTID_2);

		query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSPK_2);

		if (startDate == null) {
			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_2);
		}

		if (endDate == null) {
			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_2);
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

		qPos.add(assetReceiptId);

		qPos.add(productClassNameId);

		qPos.add(productClassPK);

		if (startDate != null) {
			qPos.add(CalendarUtil.getTimestamp(startDate));
		}

		if (endDate != null) {
			qPos.add(CalendarUtil.getTimestamp(endDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptSupport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptSupport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt supports.
	 *
	 * @return the asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @return the range of asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt supports
	 * @param end the upper bound of the range of asset receipt supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptSupport> findAll(int start, int end,
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

		List<AssetReceiptSupport> list = (List<AssetReceiptSupport>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETRECEIPTSUPPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETRECEIPTSUPPORT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetReceiptSupport>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetReceiptSupport>)QueryUtil.list(q,
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
	 * Removes all the asset receipt supports where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (AssetReceiptSupport assetReceiptSupport : findByUuid(uuid)) {
			remove(assetReceiptSupport);
		}
	}

	/**
	 * Removes all the asset receipt supports where assetReceiptId = &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetReceiptId(long assetReceiptId)
		throws SystemException {
		for (AssetReceiptSupport assetReceiptSupport : findByAssetReceiptId(
				assetReceiptId)) {
			remove(assetReceiptSupport);
		}
	}

	/**
	 * Removes all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK) throws SystemException {
		for (AssetReceiptSupport assetReceiptSupport : findByARI_PCNI_PCPK(
				assetReceiptId, productClassNameId, productClassPK)) {
			remove(assetReceiptSupport);
		}
	}

	/**
	 * Removes all the asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARI_GtSD_UT(long assetReceiptId, Date startDate,
		int usageType) throws SystemException {
		for (AssetReceiptSupport assetReceiptSupport : findByARI_GtSD_UT(
				assetReceiptId, startDate, usageType)) {
			remove(assetReceiptSupport);
		}
	}

	/**
	 * Removes all the asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARI_LtSD_GtED_UT(long assetReceiptId, Date startDate,
		Date endDate, int usageType) throws SystemException {
		for (AssetReceiptSupport assetReceiptSupport : findByARI_LtSD_GtED_UT(
				assetReceiptId, startDate, endDate, usageType)) {
			remove(assetReceiptSupport);
		}
	}

	/**
	 * Removes all the asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARI_PCNI_PCPK_LtSD_GtED(long assetReceiptId,
		long productClassNameId, long productClassPK, Date startDate,
		Date endDate) throws SystemException {
		for (AssetReceiptSupport assetReceiptSupport : findByARI_PCNI_PCPK_LtSD_GtED(
				assetReceiptId, productClassNameId, productClassPK, startDate,
				endDate)) {
			remove(assetReceiptSupport);
		}
	}

	/**
	 * Removes all the asset receipt supports from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetReceiptSupport assetReceiptSupport : findAll()) {
			remove(assetReceiptSupport);
		}
	}

	/**
	 * Returns the number of asset receipt supports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPTSUPPORT_WHERE);

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
	 * Returns the number of asset receipt supports where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @return the number of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetReceiptId(long assetReceiptId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetReceiptId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETRECEIPTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ASSETRECEIPTID_ASSETRECEIPTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETRECEIPTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the number of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARI_PCNI_PCPK(long assetReceiptId,
		long productClassNameId, long productClassPK) throws SystemException {
		Object[] finderArgs = new Object[] {
				assetReceiptId, productClassNameId, productClassPK
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ARI_PCNI_PCPK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_ASSETRECEIPTID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptId);

				qPos.add(productClassNameId);

				qPos.add(productClassPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ARI_PCNI_PCPK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt supports where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @return the number of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARI_GtSD_UT(long assetReceiptId, Date startDate,
		int usageType) throws SystemException {
		Object[] finderArgs = new Object[] { assetReceiptId, startDate, usageType };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_GTSD_UT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_GTSD_UT_ASSETRECEIPTID_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_2);
			}

			query.append(_FINDER_COLUMN_ARI_GTSD_UT_USAGETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptId);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				qPos.add(usageType);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_GTSD_UT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt supports where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @return the number of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARI_LtSD_GtED_UT(long assetReceiptId, Date startDate,
		Date endDate, int usageType) throws SystemException {
		Object[] finderArgs = new Object[] {
				assetReceiptId, startDate, endDate, usageType
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_LTSD_GTED_UT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ASSETRECEIPTID_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_2);
			}

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_2);
			}

			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_UT_USAGETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptId);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				if (endDate != null) {
					qPos.add(CalendarUtil.getTimestamp(endDate));
				}

				qPos.add(usageType);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_LTSD_GTED_UT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt supports where assetReceiptId = &#63; and productClassNameId = &#63; and productClassPK = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the number of matching asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARI_PCNI_PCPK_LtSD_GtED(long assetReceiptId,
		long productClassNameId, long productClassPK, Date startDate,
		Date endDate) throws SystemException {
		Object[] finderArgs = new Object[] {
				assetReceiptId, productClassNameId, productClassPK, startDate,
				endDate
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_PCNI_PCPK_LTSD_GTED,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ASSETRECEIPTSUPPORT_WHERE);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ASSETRECEIPTID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSPK_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_2);
			}

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptId);

				qPos.add(productClassNameId);

				qPos.add(productClassPK);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				if (endDate != null) {
					qPos.add(CalendarUtil.getTimestamp(endDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_PCNI_PCPK_LTSD_GTED,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt supports.
	 *
	 * @return the number of asset receipt supports
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETRECEIPTSUPPORT);

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
	 * Initializes the asset receipt support persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetReceiptSupport")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetReceiptSupport>> listenersList = new ArrayList<ModelListener<AssetReceiptSupport>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetReceiptSupport>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetReceiptSupportImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETRECEIPTSUPPORT = "SELECT assetReceiptSupport FROM AssetReceiptSupport assetReceiptSupport";
	private static final String _SQL_SELECT_ASSETRECEIPTSUPPORT_WHERE = "SELECT assetReceiptSupport FROM AssetReceiptSupport assetReceiptSupport WHERE ";
	private static final String _SQL_COUNT_ASSETRECEIPTSUPPORT = "SELECT COUNT(assetReceiptSupport) FROM AssetReceiptSupport assetReceiptSupport";
	private static final String _SQL_COUNT_ASSETRECEIPTSUPPORT_WHERE = "SELECT COUNT(assetReceiptSupport) FROM AssetReceiptSupport assetReceiptSupport WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "assetReceiptSupport.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "assetReceiptSupport.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(assetReceiptSupport.uuid IS NULL OR assetReceiptSupport.uuid = ?)";
	private static final String _FINDER_COLUMN_ASSETRECEIPTID_ASSETRECEIPTID_2 = "assetReceiptSupport.assetReceiptId = ?";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_ASSETRECEIPTID_2 = "assetReceiptSupport.assetReceiptId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSNAMEID_2 =
		"assetReceiptSupport.productClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_PRODUCTCLASSPK_2 = "assetReceiptSupport.productClassPK = ?";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_ASSETRECEIPTID_2 = "assetReceiptSupport.assetReceiptId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_1 = "assetReceiptSupport.startDate > NULL AND ";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_2 = "assetReceiptSupport.startDate > ? AND ";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_USAGETYPE_2 = "assetReceiptSupport.usageType = ?";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_ASSETRECEIPTID_2 =
		"assetReceiptSupport.assetReceiptId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_1 = "assetReceiptSupport.startDate < NULL AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_2 = "assetReceiptSupport.startDate < ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_1 = "assetReceiptSupport.endDate > NULL AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_2 = "assetReceiptSupport.endDate > ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_USAGETYPE_2 = "assetReceiptSupport.usageType = ?";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ASSETRECEIPTID_2 =
		"assetReceiptSupport.assetReceiptId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSNAMEID_2 =
		"assetReceiptSupport.productClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_PRODUCTCLASSPK_2 =
		"assetReceiptSupport.productClassPK = ? AND ";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_1 =
		"assetReceiptSupport.startDate < NULL AND ";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_STARTDATE_2 =
		"assetReceiptSupport.startDate < ? AND ";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_1 =
		"assetReceiptSupport.endDate > NULL";
	private static final String _FINDER_COLUMN_ARI_PCNI_PCPK_LTSD_GTED_ENDDATE_2 =
		"assetReceiptSupport.endDate > ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetReceiptSupport.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetReceiptSupport exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetReceiptSupport exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetReceiptSupportPersistenceImpl.class);
	private static AssetReceiptSupport _nullAssetReceiptSupport = new AssetReceiptSupportImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetReceiptSupport> toCacheModel() {
				return _nullAssetReceiptSupportCacheModel;
			}
		};

	private static CacheModel<AssetReceiptSupport> _nullAssetReceiptSupportCacheModel =
		new CacheModel<AssetReceiptSupport>() {
			public AssetReceiptSupport toEntityModel() {
				return _nullAssetReceiptSupport;
			}
		};
}
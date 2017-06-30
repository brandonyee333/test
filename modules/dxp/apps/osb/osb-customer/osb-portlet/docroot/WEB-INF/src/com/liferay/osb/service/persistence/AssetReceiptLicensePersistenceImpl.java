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

import com.liferay.osb.NoSuchAssetReceiptLicenseException;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.impl.AssetReceiptLicenseImpl;
import com.liferay.osb.model.impl.AssetReceiptLicenseModelImpl;

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
 * The persistence implementation for the asset receipt license service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicensePersistence
 * @see AssetReceiptLicenseUtil
 * @generated
 */
public class AssetReceiptLicensePersistenceImpl extends BasePersistenceImpl<AssetReceiptLicense>
	implements AssetReceiptLicensePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetReceiptLicenseUtil} to access the asset receipt license persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetReceiptLicenseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AssetReceiptLicenseModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETRECEIPTID =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetReceiptId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetReceiptId",
			new String[] { Long.class.getName() },
			AssetReceiptLicenseModelImpl.ASSETRECEIPTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETRECEIPTID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetReceiptId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETLICENSEID =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetLicenseId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetLicenseId",
			new String[] { Long.class.getName() },
			AssetReceiptLicenseModelImpl.ASSETLICENSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETLICENSEID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetLicenseId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_LTSD_GTED =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARI_LtSD_GtED",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_LTSD_GTED =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByARI_LtSD_GtED",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_GTSD_UT =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARI_GtSD_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_GTSD_UT =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByARI_GtSD_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OCN_OCP_PCN =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOCN_OCP_PCN",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PCN =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOCN_OCP_PCN",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AssetReceiptLicenseModelImpl.OWNERCLASSNAMEID_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.OWNERCLASSPK_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.PRODUCTCLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OCN_OCP_PCN = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOCN_OCP_PCN",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OCN_OCP_PI =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOCN_OCP_PI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOCN_OCP_PI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			AssetReceiptLicenseModelImpl.OWNERCLASSNAMEID_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.OWNERCLASSPK_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.PRODUCTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OCN_OCP_PI = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOCN_OCP_PI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_LTSD_GTED_UT =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARI_LtSD_GtED_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_LTSD_GTED_UT =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByARI_LtSD_GtED_UT",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OCN_OCP_PI_LT_LL =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOCN_OCP_PI_LT_LL",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI_LT_LL =
		new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOCN_OCP_PI_LT_LL",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			AssetReceiptLicenseModelImpl.OWNERCLASSNAMEID_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.OWNERCLASSPK_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.PRODUCTID_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetReceiptLicenseModelImpl.LICENSELIFETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OCN_OCP_PI_LT_LL = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOCN_OCP_PI_LT_LL",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset receipt license in the entity cache if it is enabled.
	 *
	 * @param assetReceiptLicense the asset receipt license
	 */
	public void cacheResult(AssetReceiptLicense assetReceiptLicense) {
		EntityCacheUtil.putResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class, assetReceiptLicense.getPrimaryKey(),
			assetReceiptLicense);

		assetReceiptLicense.resetOriginalValues();
	}

	/**
	 * Caches the asset receipt licenses in the entity cache if it is enabled.
	 *
	 * @param assetReceiptLicenses the asset receipt licenses
	 */
	public void cacheResult(List<AssetReceiptLicense> assetReceiptLicenses) {
		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			if (EntityCacheUtil.getResult(
						AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptLicenseImpl.class,
						assetReceiptLicense.getPrimaryKey()) == null) {
				cacheResult(assetReceiptLicense);
			}
			else {
				assetReceiptLicense.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset receipt licenses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetReceiptLicenseImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetReceiptLicenseImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset receipt license.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetReceiptLicense assetReceiptLicense) {
		EntityCacheUtil.removeResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class, assetReceiptLicense.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetReceiptLicense> assetReceiptLicenses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			EntityCacheUtil.removeResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptLicenseImpl.class,
				assetReceiptLicense.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	 *
	 * @param assetReceiptLicenseId the primary key for the new asset receipt license
	 * @return the new asset receipt license
	 */
	public AssetReceiptLicense create(long assetReceiptLicenseId) {
		AssetReceiptLicense assetReceiptLicense = new AssetReceiptLicenseImpl();

		assetReceiptLicense.setNew(true);
		assetReceiptLicense.setPrimaryKey(assetReceiptLicenseId);

		String uuid = PortalUUIDUtil.generate();

		assetReceiptLicense.setUuid(uuid);

		return assetReceiptLicense;
	}

	/**
	 * Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetReceiptLicenseId the primary key of the asset receipt license
	 * @return the asset receipt license that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense remove(long assetReceiptLicenseId)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		return remove(Long.valueOf(assetReceiptLicenseId));
	}

	/**
	 * Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset receipt license
	 * @return the asset receipt license that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptLicense remove(Serializable primaryKey)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense assetReceiptLicense = (AssetReceiptLicense)session.get(AssetReceiptLicenseImpl.class,
					primaryKey);

			if (assetReceiptLicense == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetReceiptLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetReceiptLicense);
		}
		catch (NoSuchAssetReceiptLicenseException nsee) {
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
	protected AssetReceiptLicense removeImpl(
		AssetReceiptLicense assetReceiptLicense) throws SystemException {
		assetReceiptLicense = toUnwrappedModel(assetReceiptLicense);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetReceiptLicense);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetReceiptLicense);

		return assetReceiptLicense;
	}

	@Override
	public AssetReceiptLicense updateImpl(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense,
		boolean merge) throws SystemException {
		assetReceiptLicense = toUnwrappedModel(assetReceiptLicense);

		boolean isNew = assetReceiptLicense.isNew();

		AssetReceiptLicenseModelImpl assetReceiptLicenseModelImpl = (AssetReceiptLicenseModelImpl)assetReceiptLicense;

		if (Validator.isNull(assetReceiptLicense.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetReceiptLicense.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetReceiptLicense, merge);

			assetReceiptLicense.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetReceiptLicenseModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetReceiptLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetReceiptLicenseModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { assetReceiptLicenseModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((assetReceiptLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalAssetReceiptId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETRECEIPTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getAssetReceiptId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETRECEIPTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETRECEIPTID,
					args);
			}

			if ((assetReceiptLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalAssetLicenseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETLICENSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getAssetLicenseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETLICENSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETLICENSEID,
					args);
			}

			if ((assetReceiptLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PCN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalOwnerClassNameId()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalOwnerClassPK()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalProductClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCN_OCP_PCN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PCN,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOwnerClassNameId()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOwnerClassPK()),
						Long.valueOf(assetReceiptLicenseModelImpl.getProductClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCN_OCP_PCN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PCN,
					args);
			}

			if ((assetReceiptLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalOwnerClassNameId()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalOwnerClassPK()),
						
						assetReceiptLicenseModelImpl.getOriginalProductId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOwnerClassNameId()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOwnerClassPK()),
						
						assetReceiptLicenseModelImpl.getProductId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI,
					args);
			}

			if ((assetReceiptLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI_LT_LL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalOwnerClassNameId()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalOwnerClassPK()),
						
						assetReceiptLicenseModelImpl.getOriginalProductId(),
						Integer.valueOf(assetReceiptLicenseModelImpl.getOriginalLicenseType()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOriginalLicenseLifetime())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI_LT_LL,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI_LT_LL,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptLicenseModelImpl.getOwnerClassNameId()),
						Long.valueOf(assetReceiptLicenseModelImpl.getOwnerClassPK()),
						
						assetReceiptLicenseModelImpl.getProductId(),
						Integer.valueOf(assetReceiptLicenseModelImpl.getLicenseType()),
						Long.valueOf(assetReceiptLicenseModelImpl.getLicenseLifetime())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI_LT_LL,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI_LT_LL,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class, assetReceiptLicense.getPrimaryKey(),
			assetReceiptLicense);

		return assetReceiptLicense;
	}

	protected AssetReceiptLicense toUnwrappedModel(
		AssetReceiptLicense assetReceiptLicense) {
		if (assetReceiptLicense instanceof AssetReceiptLicenseImpl) {
			return assetReceiptLicense;
		}

		AssetReceiptLicenseImpl assetReceiptLicenseImpl = new AssetReceiptLicenseImpl();

		assetReceiptLicenseImpl.setNew(assetReceiptLicense.isNew());
		assetReceiptLicenseImpl.setPrimaryKey(assetReceiptLicense.getPrimaryKey());

		assetReceiptLicenseImpl.setUuid(assetReceiptLicense.getUuid());
		assetReceiptLicenseImpl.setAssetReceiptLicenseId(assetReceiptLicense.getAssetReceiptLicenseId());
		assetReceiptLicenseImpl.setUserId(assetReceiptLicense.getUserId());
		assetReceiptLicenseImpl.setUserName(assetReceiptLicense.getUserName());
		assetReceiptLicenseImpl.setCreateDate(assetReceiptLicense.getCreateDate());
		assetReceiptLicenseImpl.setModifiedDate(assetReceiptLicense.getModifiedDate());
		assetReceiptLicenseImpl.setAssetReceiptId(assetReceiptLicense.getAssetReceiptId());
		assetReceiptLicenseImpl.setAssetLicenseId(assetReceiptLicense.getAssetLicenseId());
		assetReceiptLicenseImpl.setAssetEntryId(assetReceiptLicense.getAssetEntryId());
		assetReceiptLicenseImpl.setOwnerClassNameId(assetReceiptLicense.getOwnerClassNameId());
		assetReceiptLicenseImpl.setOwnerClassPK(assetReceiptLicense.getOwnerClassPK());
		assetReceiptLicenseImpl.setProductClassNameId(assetReceiptLicense.getProductClassNameId());
		assetReceiptLicenseImpl.setProductClassPK(assetReceiptLicense.getProductClassPK());
		assetReceiptLicenseImpl.setProductId(assetReceiptLicense.getProductId());
		assetReceiptLicenseImpl.setStartDate(assetReceiptLicense.getStartDate());
		assetReceiptLicenseImpl.setEndDate(assetReceiptLicense.getEndDate());
		assetReceiptLicenseImpl.setUsageType(assetReceiptLicense.getUsageType());
		assetReceiptLicenseImpl.setLicenseType(assetReceiptLicense.getLicenseType());
		assetReceiptLicenseImpl.setLicenseTypeAllotment(assetReceiptLicense.getLicenseTypeAllotment());
		assetReceiptLicenseImpl.setLicenseLifetime(assetReceiptLicense.getLicenseLifetime());

		return assetReceiptLicenseImpl;
	}

	/**
	 * Returns the asset receipt license with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt license
	 * @return the asset receipt license
	 * @throws com.liferay.portal.NoSuchModelException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptLicense findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt license with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptLicenseException} if it could not be found.
	 *
	 * @param assetReceiptLicenseId the primary key of the asset receipt license
	 * @return the asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByPrimaryKey(long assetReceiptLicenseId)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByPrimaryKey(assetReceiptLicenseId);

		if (assetReceiptLicense == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assetReceiptLicenseId);
			}

			throw new NoSuchAssetReceiptLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetReceiptLicenseId);
		}

		return assetReceiptLicense;
	}

	/**
	 * Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt license
	 * @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptLicense fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetReceiptLicenseId the primary key of the asset receipt license
	 * @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByPrimaryKey(long assetReceiptLicenseId)
		throws SystemException {
		AssetReceiptLicense assetReceiptLicense = (AssetReceiptLicense)EntityCacheUtil.getResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptLicenseImpl.class, assetReceiptLicenseId);

		if (assetReceiptLicense == _nullAssetReceiptLicense) {
			return null;
		}

		if (assetReceiptLicense == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetReceiptLicense = (AssetReceiptLicense)session.get(AssetReceiptLicenseImpl.class,
						Long.valueOf(assetReceiptLicenseId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetReceiptLicense != null) {
					cacheResult(assetReceiptLicense);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptLicenseImpl.class, assetReceiptLicenseId,
						_nullAssetReceiptLicense);
				}

				closeSession(session);
			}
		}

		return assetReceiptLicense;
	}

	/**
	 * Returns all the asset receipt licenses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByUuid(String uuid, int start,
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

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if (!Validator.equals(uuid, assetReceiptLicense.getUuid())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByUuid_First(uuid,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceiptLicense> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByUuid_Last(uuid,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<AssetReceiptLicense> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByUuid_PrevAndNext(
		long assetReceiptLicenseId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByUuid_PrevAndNext(session, assetReceiptLicense,
					uuid, orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByUuid_PrevAndNext(session, assetReceiptLicense,
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

	protected AssetReceiptLicense getByUuid_PrevAndNext(Session session,
		AssetReceiptLicense assetReceiptLicense, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByAssetReceiptId(long assetReceiptId)
		throws SystemException {
		return findByAssetReceiptId(assetReceiptId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where assetReceiptId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByAssetReceiptId(long assetReceiptId,
		int start, int end) throws SystemException {
		return findByAssetReceiptId(assetReceiptId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByAssetReceiptId(long assetReceiptId,
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

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((assetReceiptId != assetReceiptLicense.getAssetReceiptId())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByAssetReceiptId_First(long assetReceiptId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByAssetReceiptId_First(assetReceiptId,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByAssetReceiptId_First(
		long assetReceiptId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptLicense> list = findByAssetReceiptId(assetReceiptId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByAssetReceiptId_Last(long assetReceiptId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByAssetReceiptId_Last(assetReceiptId,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByAssetReceiptId_Last(long assetReceiptId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetReceiptId(assetReceiptId);

		List<AssetReceiptLicense> list = findByAssetReceiptId(assetReceiptId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param assetReceiptId the asset receipt ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByAssetReceiptId_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByAssetReceiptId_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByAssetReceiptId_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, orderByComparator,
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

	protected AssetReceiptLicense getByAssetReceiptId_PrevAndNext(
		Session session, AssetReceiptLicense assetReceiptLicense,
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

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByAssetLicenseId(long assetLicenseId)
		throws SystemException {
		return findByAssetLicenseId(assetLicenseId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where assetLicenseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetLicenseId the asset license ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByAssetLicenseId(long assetLicenseId,
		int start, int end) throws SystemException {
		return findByAssetLicenseId(assetLicenseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where assetLicenseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetLicenseId the asset license ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByAssetLicenseId(long assetLicenseId,
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

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((assetLicenseId != assetReceiptLicense.getAssetLicenseId())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByAssetLicenseId_First(long assetLicenseId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByAssetLicenseId_First(assetLicenseId,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetLicenseId=");
		msg.append(assetLicenseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByAssetLicenseId_First(
		long assetLicenseId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptLicense> list = findByAssetLicenseId(assetLicenseId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByAssetLicenseId_Last(long assetLicenseId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByAssetLicenseId_Last(assetLicenseId,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetLicenseId=");
		msg.append(assetLicenseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByAssetLicenseId_Last(long assetLicenseId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetLicenseId(assetLicenseId);

		List<AssetReceiptLicense> list = findByAssetLicenseId(assetLicenseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetLicenseId = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param assetLicenseId the asset license ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByAssetLicenseId_PrevAndNext(
		long assetReceiptLicenseId, long assetLicenseId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByAssetLicenseId_PrevAndNext(session,
					assetReceiptLicense, assetLicenseId, orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByAssetLicenseId_PrevAndNext(session,
					assetReceiptLicense, assetLicenseId, orderByComparator,
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

	protected AssetReceiptLicense getByAssetLicenseId_PrevAndNext(
		Session session, AssetReceiptLicense assetReceiptLicense,
		long assetLicenseId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_LtSD_GtED(long assetReceiptId,
		Date startDate, Date endDate) throws SystemException {
		return findByARI_LtSD_GtED(assetReceiptId, startDate, endDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_LtSD_GtED(long assetReceiptId,
		Date startDate, Date endDate, int start, int end)
		throws SystemException {
		return findByARI_LtSD_GtED(assetReceiptId, startDate, endDate, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_LtSD_GtED(long assetReceiptId,
		Date startDate, Date endDate, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_LTSD_GTED;
		finderArgs = new Object[] {
				assetReceiptId, startDate, endDate,
				
				start, end, orderByComparator
			};

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((assetReceiptId != assetReceiptLicense.getAssetReceiptId()) ||
						!Validator.equals(startDate,
							assetReceiptLicense.getStartDate()) ||
						!Validator.equals(endDate,
							assetReceiptLicense.getEndDate())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ASSETRECEIPTID_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_2);
			}

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_2);
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

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				if (endDate != null) {
					qPos.add(CalendarUtil.getTimestamp(endDate));
				}

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByARI_LtSD_GtED_First(long assetReceiptId,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByARI_LtSD_GtED_First(assetReceiptId,
				startDate, endDate, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByARI_LtSD_GtED_First(long assetReceiptId,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptLicense> list = findByARI_LtSD_GtED(assetReceiptId,
				startDate, endDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByARI_LtSD_GtED_Last(long assetReceiptId,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByARI_LtSD_GtED_Last(assetReceiptId,
				startDate, endDate, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptId=");
		msg.append(assetReceiptId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByARI_LtSD_GtED_Last(long assetReceiptId,
		Date startDate, Date endDate, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByARI_LtSD_GtED(assetReceiptId, startDate, endDate);

		List<AssetReceiptLicense> list = findByARI_LtSD_GtED(assetReceiptId,
				startDate, endDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByARI_LtSD_GtED_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId, Date startDate,
		Date endDate, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByARI_LtSD_GtED_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, startDate, endDate,
					orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByARI_LtSD_GtED_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, startDate, endDate,
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

	protected AssetReceiptLicense getByARI_LtSD_GtED_PrevAndNext(
		Session session, AssetReceiptLicense assetReceiptLicense,
		long assetReceiptId, Date startDate, Date endDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

		query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ASSETRECEIPTID_2);

		if (startDate == null) {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_2);
		}

		if (endDate == null) {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_2);
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

		if (startDate != null) {
			qPos.add(CalendarUtil.getTimestamp(startDate));
		}

		if (endDate != null) {
			qPos.add(CalendarUtil.getTimestamp(endDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_GtSD_UT(long assetReceiptId,
		Date startDate, int usageType) throws SystemException {
		return findByARI_GtSD_UT(assetReceiptId, startDate, usageType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_GtSD_UT(long assetReceiptId,
		Date startDate, int usageType, int start, int end)
		throws SystemException {
		return findByARI_GtSD_UT(assetReceiptId, startDate, usageType, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_GtSD_UT(long assetReceiptId,
		Date startDate, int usageType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARI_GTSD_UT;
		finderArgs = new Object[] {
				assetReceiptId, startDate, usageType,
				
				start, end, orderByComparator
			};

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((assetReceiptId != assetReceiptLicense.getAssetReceiptId()) ||
						!Validator.equals(startDate,
							assetReceiptLicense.getStartDate()) ||
						(usageType != assetReceiptLicense.getUsageType())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByARI_GtSD_UT_First(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByARI_GtSD_UT_First(assetReceiptId,
				startDate, usageType, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
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

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByARI_GtSD_UT_First(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptLicense> list = findByARI_GtSD_UT(assetReceiptId,
				startDate, usageType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByARI_GtSD_UT_Last(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByARI_GtSD_UT_Last(assetReceiptId,
				startDate, usageType, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
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

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByARI_GtSD_UT_Last(long assetReceiptId,
		Date startDate, int usageType, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByARI_GtSD_UT(assetReceiptId, startDate, usageType);

		List<AssetReceiptLicense> list = findByARI_GtSD_UT(assetReceiptId,
				startDate, usageType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByARI_GtSD_UT_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId, Date startDate,
		int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByARI_GtSD_UT_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, startDate, usageType,
					orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByARI_GtSD_UT_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, startDate, usageType,
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

	protected AssetReceiptLicense getByARI_GtSD_UT_PrevAndNext(
		Session session, AssetReceiptLicense assetReceiptLicense,
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

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PCN(long ownerClassNameId,
		long ownerClassPK, long productClassNameId) throws SystemException {
		return findByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
			productClassNameId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PCN(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, int start, int end)
		throws SystemException {
		return findByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
			productClassNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PCN(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PCN;
			finderArgs = new Object[] {
					ownerClassNameId, ownerClassPK, productClassNameId
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OCN_OCP_PCN;
			finderArgs = new Object[] {
					ownerClassNameId, ownerClassPK, productClassNameId,
					
					start, end, orderByComparator
				};
		}

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((ownerClassNameId != assetReceiptLicense.getOwnerClassNameId()) ||
						(ownerClassPK != assetReceiptLicense.getOwnerClassPK()) ||
						(productClassNameId != assetReceiptLicense.getProductClassNameId())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSPK_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PCN_PRODUCTCLASSNAMEID_2);

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

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

				qPos.add(productClassNameId);

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByOCN_OCP_PCN_First(long ownerClassNameId,
		long ownerClassPK, long productClassNameId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByOCN_OCP_PCN_First(ownerClassNameId,
				ownerClassPK, productClassNameId, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerClassNameId=");
		msg.append(ownerClassNameId);

		msg.append(", ownerClassPK=");
		msg.append(ownerClassPK);

		msg.append(", productClassNameId=");
		msg.append(productClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByOCN_OCP_PCN_First(long ownerClassNameId,
		long ownerClassPK, long productClassNameId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceiptLicense> list = findByOCN_OCP_PCN(ownerClassNameId,
				ownerClassPK, productClassNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByOCN_OCP_PCN_Last(long ownerClassNameId,
		long ownerClassPK, long productClassNameId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByOCN_OCP_PCN_Last(ownerClassNameId,
				ownerClassPK, productClassNameId, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerClassNameId=");
		msg.append(ownerClassNameId);

		msg.append(", ownerClassPK=");
		msg.append(ownerClassPK);

		msg.append(", productClassNameId=");
		msg.append(productClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByOCN_OCP_PCN_Last(long ownerClassNameId,
		long ownerClassPK, long productClassNameId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOCN_OCP_PCN(ownerClassNameId, ownerClassPK,
				productClassNameId);

		List<AssetReceiptLicense> list = findByOCN_OCP_PCN(ownerClassNameId,
				ownerClassPK, productClassNameId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByOCN_OCP_PCN_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		long productClassNameId, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByOCN_OCP_PCN_PrevAndNext(session,
					assetReceiptLicense, ownerClassNameId, ownerClassPK,
					productClassNameId, orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByOCN_OCP_PCN_PrevAndNext(session,
					assetReceiptLicense, ownerClassNameId, ownerClassPK,
					productClassNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceiptLicense getByOCN_OCP_PCN_PrevAndNext(
		Session session, AssetReceiptLicense assetReceiptLicense,
		long ownerClassNameId, long ownerClassPK, long productClassNameId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

		query.append(_FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSPK_2);

		query.append(_FINDER_COLUMN_OCN_OCP_PCN_PRODUCTCLASSNAMEID_2);

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

		qPos.add(ownerClassNameId);

		qPos.add(ownerClassPK);

		qPos.add(productClassNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PI(long ownerClassNameId,
		long ownerClassPK, String productId) throws SystemException {
		return findByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PI(long ownerClassNameId,
		long ownerClassPK, String productId, int start, int end)
		throws SystemException {
		return findByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PI(long ownerClassNameId,
		long ownerClassPK, String productId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI;
			finderArgs = new Object[] { ownerClassNameId, ownerClassPK, productId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OCN_OCP_PI;
			finderArgs = new Object[] {
					ownerClassNameId, ownerClassPK, productId,
					
					start, end, orderByComparator
				};
		}

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((ownerClassNameId != assetReceiptLicense.getOwnerClassNameId()) ||
						(ownerClassPK != assetReceiptLicense.getOwnerClassPK()) ||
						!Validator.equals(productId,
							assetReceiptLicense.getProductId())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSPK_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_2);
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

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

				if (productId != null) {
					qPos.add(productId);
				}

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByOCN_OCP_PI_First(long ownerClassNameId,
		long ownerClassPK, String productId, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByOCN_OCP_PI_First(ownerClassNameId,
				ownerClassPK, productId, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerClassNameId=");
		msg.append(ownerClassNameId);

		msg.append(", ownerClassPK=");
		msg.append(ownerClassPK);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByOCN_OCP_PI_First(long ownerClassNameId,
		long ownerClassPK, String productId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetReceiptLicense> list = findByOCN_OCP_PI(ownerClassNameId,
				ownerClassPK, productId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByOCN_OCP_PI_Last(long ownerClassNameId,
		long ownerClassPK, String productId, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByOCN_OCP_PI_Last(ownerClassNameId,
				ownerClassPK, productId, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerClassNameId=");
		msg.append(ownerClassNameId);

		msg.append(", ownerClassPK=");
		msg.append(ownerClassPK);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByOCN_OCP_PI_Last(long ownerClassNameId,
		long ownerClassPK, String productId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByOCN_OCP_PI(ownerClassNameId, ownerClassPK, productId);

		List<AssetReceiptLicense> list = findByOCN_OCP_PI(ownerClassNameId,
				ownerClassPK, productId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByOCN_OCP_PI_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		String productId, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByOCN_OCP_PI_PrevAndNext(session,
					assetReceiptLicense, ownerClassNameId, ownerClassPK,
					productId, orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByOCN_OCP_PI_PrevAndNext(session,
					assetReceiptLicense, ownerClassNameId, ownerClassPK,
					productId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceiptLicense getByOCN_OCP_PI_PrevAndNext(Session session,
		AssetReceiptLicense assetReceiptLicense, long ownerClassNameId,
		long ownerClassPK, String productId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

		query.append(_FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSPK_2);

		if (productId == null) {
			query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_1);
		}
		else {
			if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_2);
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

		qPos.add(ownerClassNameId);

		qPos.add(ownerClassPK);

		if (productId != null) {
			qPos.add(productId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, Date startDate, Date endDate, int usageType)
		throws SystemException {
		return findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_LtSD_GtED_UT(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		int start, int end) throws SystemException {
		return findByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
			usageType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByARI_LtSD_GtED_UT(
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

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((assetReceiptId != assetReceiptLicense.getAssetReceiptId()) ||
						!Validator.equals(startDate,
							assetReceiptLicense.getStartDate()) ||
						!Validator.equals(endDate,
							assetReceiptLicense.getEndDate()) ||
						(usageType != assetReceiptLicense.getUsageType())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByARI_LtSD_GtED_UT_First(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByARI_LtSD_GtED_UT_First(assetReceiptId,
				startDate, endDate, usageType, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
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

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByARI_LtSD_GtED_UT_First(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceiptLicense> list = findByARI_LtSD_GtED_UT(assetReceiptId,
				startDate, endDate, usageType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByARI_LtSD_GtED_UT_Last(assetReceiptId,
				startDate, endDate, usageType, orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
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

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByARI_LtSD_GtED_UT_Last(
		long assetReceiptId, Date startDate, Date endDate, int usageType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByARI_LtSD_GtED_UT(assetReceiptId, startDate, endDate,
				usageType);

		List<AssetReceiptLicense> list = findByARI_LtSD_GtED_UT(assetReceiptId,
				startDate, endDate, usageType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByARI_LtSD_GtED_UT_PrevAndNext(
		long assetReceiptLicenseId, long assetReceiptId, Date startDate,
		Date endDate, int usageType, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByARI_LtSD_GtED_UT_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, startDate, endDate,
					usageType, orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByARI_LtSD_GtED_UT_PrevAndNext(session,
					assetReceiptLicense, assetReceiptId, startDate, endDate,
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

	protected AssetReceiptLicense getByARI_LtSD_GtED_UT_PrevAndNext(
		Session session, AssetReceiptLicense assetReceiptLicense,
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

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @return the matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime) throws SystemException {
		return findByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime, int start, int end)
		throws SystemException {
		return findByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
			productId, licenseType, licenseLifetime, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findByOCN_OCP_PI_LT_LL(
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OCN_OCP_PI_LT_LL;
			finderArgs = new Object[] {
					ownerClassNameId, ownerClassPK, productId, licenseType,
					licenseLifetime
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OCN_OCP_PI_LT_LL;
			finderArgs = new Object[] {
					ownerClassNameId, ownerClassPK, productId, licenseType,
					licenseLifetime,
					
					start, end, orderByComparator
				};
		}

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceiptLicense assetReceiptLicense : list) {
				if ((ownerClassNameId != assetReceiptLicense.getOwnerClassNameId()) ||
						(ownerClassPK != assetReceiptLicense.getOwnerClassPK()) ||
						!Validator.equals(productId,
							assetReceiptLicense.getProductId()) ||
						(licenseType != assetReceiptLicense.getLicenseType()) ||
						(licenseLifetime != assetReceiptLicense.getLicenseLifetime())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSPK_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_2);
				}
			}

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSELIFETIME_2);

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

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

				if (productId != null) {
					qPos.add(productId);
				}

				qPos.add(licenseType);

				qPos.add(licenseLifetime);

				list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByOCN_OCP_PI_LT_LL_First(
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByOCN_OCP_PI_LT_LL_First(ownerClassNameId,
				ownerClassPK, productId, licenseType, licenseLifetime,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerClassNameId=");
		msg.append(ownerClassNameId);

		msg.append(", ownerClassPK=");
		msg.append(ownerClassPK);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseLifetime=");
		msg.append(licenseLifetime);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByOCN_OCP_PI_LT_LL_First(
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceiptLicense> list = findByOCN_OCP_PI_LT_LL(ownerClassNameId,
				ownerClassPK, productId, licenseType, licenseLifetime, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense findByOCN_OCP_PI_LT_LL_Last(
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = fetchByOCN_OCP_PI_LT_LL_Last(ownerClassNameId,
				ownerClassPK, productId, licenseType, licenseLifetime,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ownerClassNameId=");
		msg.append(ownerClassNameId);

		msg.append(", ownerClassPK=");
		msg.append(ownerClassPK);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseLifetime=");
		msg.append(licenseLifetime);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense fetchByOCN_OCP_PI_LT_LL_Last(
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOCN_OCP_PI_LT_LL(ownerClassNameId, ownerClassPK,
				productId, licenseType, licenseLifetime);

		List<AssetReceiptLicense> list = findByOCN_OCP_PI_LT_LL(ownerClassNameId,
				ownerClassPK, productId, licenseType, licenseLifetime,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws com.liferay.osb.NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptLicense[] findByOCN_OCP_PI_LT_LL_PrevAndNext(
		long assetReceiptLicenseId, long ownerClassNameId, long ownerClassPK,
		String productId, int licenseType, long licenseLifetime,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptLicenseException, SystemException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByOCN_OCP_PI_LT_LL_PrevAndNext(session,
					assetReceiptLicense, ownerClassNameId, ownerClassPK,
					productId, licenseType, licenseLifetime, orderByComparator,
					true);

			array[1] = assetReceiptLicense;

			array[2] = getByOCN_OCP_PI_LT_LL_PrevAndNext(session,
					assetReceiptLicense, ownerClassNameId, ownerClassPK,
					productId, licenseType, licenseLifetime, orderByComparator,
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

	protected AssetReceiptLicense getByOCN_OCP_PI_LT_LL_PrevAndNext(
		Session session, AssetReceiptLicense assetReceiptLicense,
		long ownerClassNameId, long ownerClassPK, String productId,
		int licenseType, long licenseLifetime,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

		query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSPK_2);

		if (productId == null) {
			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_1);
		}
		else {
			if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_3);
			}
			else {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_2);
			}
		}

		query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSETYPE_2);

		query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSELIFETIME_2);

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

		qPos.add(ownerClassNameId);

		qPos.add(ownerClassPK);

		if (productId != null) {
			qPos.add(productId);
		}

		qPos.add(licenseType);

		qPos.add(licenseLifetime);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset receipt licenses.
	 *
	 * @return the asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptLicense> findAll(int start, int end,
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

		List<AssetReceiptLicense> list = (List<AssetReceiptLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETRECEIPTLICENSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETRECEIPTLICENSE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetReceiptLicense>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Removes all the asset receipt licenses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByUuid(uuid)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where assetReceiptId = &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetReceiptId(long assetReceiptId)
		throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByAssetReceiptId(
				assetReceiptId)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where assetLicenseId = &#63; from the database.
	 *
	 * @param assetLicenseId the asset license ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetLicenseId(long assetLicenseId)
		throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByAssetLicenseId(
				assetLicenseId)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARI_LtSD_GtED(long assetReceiptId, Date startDate,
		Date endDate) throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByARI_LtSD_GtED(
				assetReceiptId, startDate, endDate)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARI_GtSD_UT(long assetReceiptId, Date startDate,
		int usageType) throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByARI_GtSD_UT(
				assetReceiptId, startDate, usageType)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; from the database.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOCN_OCP_PCN(long ownerClassNameId, long ownerClassPK,
		long productClassNameId) throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByOCN_OCP_PCN(
				ownerClassNameId, ownerClassPK, productClassNameId)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; from the database.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOCN_OCP_PI(long ownerClassNameId, long ownerClassPK,
		String productId) throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByOCN_OCP_PI(
				ownerClassNameId, ownerClassPK, productId)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63; from the database.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARI_LtSD_GtED_UT(long assetReceiptId, Date startDate,
		Date endDate, int usageType) throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByARI_LtSD_GtED_UT(
				assetReceiptId, startDate, endDate, usageType)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63; from the database.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOCN_OCP_PI_LT_LL(long ownerClassNameId,
		long ownerClassPK, String productId, int licenseType,
		long licenseLifetime) throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findByOCN_OCP_PI_LT_LL(
				ownerClassNameId, ownerClassPK, productId, licenseType,
				licenseLifetime)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Removes all the asset receipt licenses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetReceiptLicense assetReceiptLicense : findAll()) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Returns the number of asset receipt licenses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

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
	 * Returns the number of asset receipt licenses where assetReceiptId = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetReceiptId(long assetReceiptId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetReceiptId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETRECEIPTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

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
	 * Returns the number of asset receipt licenses where assetLicenseId = &#63;.
	 *
	 * @param assetLicenseId the asset license ID
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetLicenseId(long assetLicenseId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetLicenseId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETLICENSEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

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
	 * Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARI_LtSD_GtED(long assetReceiptId, Date startDate,
		Date endDate) throws SystemException {
		Object[] finderArgs = new Object[] { assetReceiptId, startDate, endDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_LTSD_GTED,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ASSETRECEIPTID_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_2);
			}

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_2);
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

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_LTSD_GTED,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param usageType the usage type
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARI_GtSD_UT(long assetReceiptId, Date startDate,
		int usageType) throws SystemException {
		Object[] finderArgs = new Object[] { assetReceiptId, startDate, usageType };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ARI_GTSD_UT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

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
	 * Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOCN_OCP_PCN(long ownerClassNameId, long ownerClassPK,
		long productClassNameId) throws SystemException {
		Object[] finderArgs = new Object[] {
				ownerClassNameId, ownerClassPK, productClassNameId
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OCN_OCP_PCN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSPK_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PCN_PRODUCTCLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

				qPos.add(productClassNameId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OCN_OCP_PCN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOCN_OCP_PI(long ownerClassNameId, long ownerClassPK,
		String productId) throws SystemException {
		Object[] finderArgs = new Object[] {
				ownerClassNameId, ownerClassPK, productId
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSPK_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

				if (productId != null) {
					qPos.add(productId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt licenses where assetReceiptId = &#63; and startDate &lt; &#63; and endDate &gt; &#63; and usageType = &#63;.
	 *
	 * @param assetReceiptId the asset receipt ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param usageType the usage type
	 * @return the number of matching asset receipt licenses
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

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

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
	 * Returns the number of asset receipt licenses where ownerClassNameId = &#63; and ownerClassPK = &#63; and productId = &#63; and licenseType = &#63; and licenseLifetime = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productId the product ID
	 * @param licenseType the license type
	 * @param licenseLifetime the license lifetime
	 * @return the number of matching asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOCN_OCP_PI_LT_LL(long ownerClassNameId,
		long ownerClassPK, String productId, int licenseType,
		long licenseLifetime) throws SystemException {
		Object[] finderArgs = new Object[] {
				ownerClassNameId, ownerClassPK, productId, licenseType,
				licenseLifetime
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI_LT_LL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSPK_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_2);
				}
			}

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSELIFETIME_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

				if (productId != null) {
					qPos.add(productId);
				}

				qPos.add(licenseType);

				qPos.add(licenseLifetime);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OCN_OCP_PI_LT_LL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt licenses.
	 *
	 * @return the number of asset receipt licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETRECEIPTLICENSE);

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
	 * Initializes the asset receipt license persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetReceiptLicense")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetReceiptLicense>> listenersList = new ArrayList<ModelListener<AssetReceiptLicense>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetReceiptLicense>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetReceiptLicenseImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETRECEIPTLICENSE = "SELECT assetReceiptLicense FROM AssetReceiptLicense assetReceiptLicense";
	private static final String _SQL_SELECT_ASSETRECEIPTLICENSE_WHERE = "SELECT assetReceiptLicense FROM AssetReceiptLicense assetReceiptLicense WHERE ";
	private static final String _SQL_COUNT_ASSETRECEIPTLICENSE = "SELECT COUNT(assetReceiptLicense) FROM AssetReceiptLicense assetReceiptLicense";
	private static final String _SQL_COUNT_ASSETRECEIPTLICENSE_WHERE = "SELECT COUNT(assetReceiptLicense) FROM AssetReceiptLicense assetReceiptLicense WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "assetReceiptLicense.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "assetReceiptLicense.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(assetReceiptLicense.uuid IS NULL OR assetReceiptLicense.uuid = ?)";
	private static final String _FINDER_COLUMN_ASSETRECEIPTID_ASSETRECEIPTID_2 = "assetReceiptLicense.assetReceiptId = ?";
	private static final String _FINDER_COLUMN_ASSETLICENSEID_ASSETLICENSEID_2 = "assetReceiptLicense.assetLicenseId = ?";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_ASSETRECEIPTID_2 = "assetReceiptLicense.assetReceiptId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_1 = "assetReceiptLicense.startDate < NULL AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_STARTDATE_2 = "assetReceiptLicense.startDate < ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_1 = "assetReceiptLicense.endDate > NULL";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_ENDDATE_2 = "assetReceiptLicense.endDate > ?";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_ASSETRECEIPTID_2 = "assetReceiptLicense.assetReceiptId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_1 = "assetReceiptLicense.startDate > NULL AND ";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_STARTDATE_2 = "assetReceiptLicense.startDate > ? AND ";
	private static final String _FINDER_COLUMN_ARI_GTSD_UT_USAGETYPE_2 = "assetReceiptLicense.usageType = ?";
	private static final String _FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSNAMEID_2 = "assetReceiptLicense.ownerClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PCN_OWNERCLASSPK_2 = "assetReceiptLicense.ownerClassPK = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PCN_PRODUCTCLASSNAMEID_2 = "assetReceiptLicense.productClassNameId = ?";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSNAMEID_2 = "assetReceiptLicense.ownerClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_OWNERCLASSPK_2 = "assetReceiptLicense.ownerClassPK = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_1 = "assetReceiptLicense.productId IS NULL";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_2 = "assetReceiptLicense.productId = ?";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_PRODUCTID_3 = "(assetReceiptLicense.productId IS NULL OR assetReceiptLicense.productId = ?)";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_ASSETRECEIPTID_2 =
		"assetReceiptLicense.assetReceiptId = ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_1 = "assetReceiptLicense.startDate < NULL AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_STARTDATE_2 = "assetReceiptLicense.startDate < ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_1 = "assetReceiptLicense.endDate > NULL AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_ENDDATE_2 = "assetReceiptLicense.endDate > ? AND ";
	private static final String _FINDER_COLUMN_ARI_LTSD_GTED_UT_USAGETYPE_2 = "assetReceiptLicense.usageType = ?";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSNAMEID_2 =
		"assetReceiptLicense.ownerClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_LT_LL_OWNERCLASSPK_2 = "assetReceiptLicense.ownerClassPK = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_1 = "assetReceiptLicense.productId IS NULL AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_2 = "assetReceiptLicense.productId = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_LT_LL_PRODUCTID_3 = "(assetReceiptLicense.productId IS NULL OR assetReceiptLicense.productId = ?) AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSETYPE_2 = "assetReceiptLicense.licenseType = ? AND ";
	private static final String _FINDER_COLUMN_OCN_OCP_PI_LT_LL_LICENSELIFETIME_2 =
		"assetReceiptLicense.licenseLifetime = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetReceiptLicense.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetReceiptLicense exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetReceiptLicense exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetReceiptLicensePersistenceImpl.class);
	private static AssetReceiptLicense _nullAssetReceiptLicense = new AssetReceiptLicenseImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetReceiptLicense> toCacheModel() {
				return _nullAssetReceiptLicenseCacheModel;
			}
		};

	private static CacheModel<AssetReceiptLicense> _nullAssetReceiptLicenseCacheModel =
		new CacheModel<AssetReceiptLicense>() {
			public AssetReceiptLicense toEntityModel() {
				return _nullAssetReceiptLicense;
			}
		};
}
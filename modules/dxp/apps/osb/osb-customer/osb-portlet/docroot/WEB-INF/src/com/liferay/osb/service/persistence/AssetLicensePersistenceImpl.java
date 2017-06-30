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

import com.liferay.osb.NoSuchAssetLicenseException;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.impl.AssetLicenseImpl;
import com.liferay.osb.model.impl.AssetLicenseModelImpl;

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
 * The persistence implementation for the asset license service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicensePersistence
 * @see AssetLicenseUtil
 * @generated
 */
public class AssetLicensePersistenceImpl extends BasePersistenceImpl<AssetLicense>
	implements AssetLicensePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetLicenseUtil} to access the asset license persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetLicenseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_UT_LT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT_LTA",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT_LTA",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_UT_LT_LTA",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_UT_LT_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT_LTA_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT_LTA_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(), Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK |
			AssetLicenseModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_C_UT_LT_LTA_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset license in the entity cache if it is enabled.
	 *
	 * @param assetLicense the asset license
	 */
	public void cacheResult(AssetLicense assetLicense) {
		EntityCacheUtil.putResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseImpl.class, assetLicense.getPrimaryKey(), assetLicense);

		assetLicense.resetOriginalValues();
	}

	/**
	 * Caches the asset licenses in the entity cache if it is enabled.
	 *
	 * @param assetLicenses the asset licenses
	 */
	public void cacheResult(List<AssetLicense> assetLicenses) {
		for (AssetLicense assetLicense : assetLicenses) {
			if (EntityCacheUtil.getResult(
						AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetLicenseImpl.class, assetLicense.getPrimaryKey()) == null) {
				cacheResult(assetLicense);
			}
			else {
				assetLicense.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset licenses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetLicenseImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetLicenseImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset license.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetLicense assetLicense) {
		EntityCacheUtil.removeResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseImpl.class, assetLicense.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetLicense> assetLicenses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetLicense assetLicense : assetLicenses) {
			EntityCacheUtil.removeResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetLicenseImpl.class, assetLicense.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset license with the primary key. Does not add the asset license to the database.
	 *
	 * @param assetLicenseId the primary key for the new asset license
	 * @return the new asset license
	 */
	public AssetLicense create(long assetLicenseId) {
		AssetLicense assetLicense = new AssetLicenseImpl();

		assetLicense.setNew(true);
		assetLicense.setPrimaryKey(assetLicenseId);

		return assetLicense;
	}

	/**
	 * Removes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetLicenseId the primary key of the asset license
	 * @return the asset license that was removed
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense remove(long assetLicenseId)
		throws NoSuchAssetLicenseException, SystemException {
		return remove(Long.valueOf(assetLicenseId));
	}

	/**
	 * Removes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset license
	 * @return the asset license that was removed
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetLicense remove(Serializable primaryKey)
		throws NoSuchAssetLicenseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetLicense assetLicense = (AssetLicense)session.get(AssetLicenseImpl.class,
					primaryKey);

			if (assetLicense == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetLicense);
		}
		catch (NoSuchAssetLicenseException nsee) {
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
	protected AssetLicense removeImpl(AssetLicense assetLicense)
		throws SystemException {
		assetLicense = toUnwrappedModel(assetLicense);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetLicense);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetLicense);

		return assetLicense;
	}

	@Override
	public AssetLicense updateImpl(
		com.liferay.osb.model.AssetLicense assetLicense, boolean merge)
		throws SystemException {
		assetLicense = toUnwrappedModel(assetLicense);

		boolean isNew = assetLicense.isNew();

		AssetLicenseModelImpl assetLicenseModelImpl = (AssetLicenseModelImpl)assetLicense;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetLicense, merge);

			assetLicense.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetLicenseModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);

				args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalLicenseType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT,
					args);

				args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getLicenseType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalLicenseType()),
						Long.valueOf(assetLicenseModelImpl.getOriginalLicenseTypeAllotment())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA,
					args);

				args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getLicenseType()),
						Long.valueOf(assetLicenseModelImpl.getLicenseTypeAllotment())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalLicenseType()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S,
					args);

				args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getLicenseType()),
						Integer.valueOf(assetLicenseModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalLicenseType()),
						Long.valueOf(assetLicenseModelImpl.getOriginalLicenseTypeAllotment()),
						Integer.valueOf(assetLicenseModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S,
					args);

				args = new Object[] {
						Long.valueOf(assetLicenseModelImpl.getClassNameId()),
						Long.valueOf(assetLicenseModelImpl.getClassPK()),
						Integer.valueOf(assetLicenseModelImpl.getUsageType()),
						Integer.valueOf(assetLicenseModelImpl.getLicenseType()),
						Long.valueOf(assetLicenseModelImpl.getLicenseTypeAllotment()),
						Integer.valueOf(assetLicenseModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseImpl.class, assetLicense.getPrimaryKey(), assetLicense);

		return assetLicense;
	}

	protected AssetLicense toUnwrappedModel(AssetLicense assetLicense) {
		if (assetLicense instanceof AssetLicenseImpl) {
			return assetLicense;
		}

		AssetLicenseImpl assetLicenseImpl = new AssetLicenseImpl();

		assetLicenseImpl.setNew(assetLicense.isNew());
		assetLicenseImpl.setPrimaryKey(assetLicense.getPrimaryKey());

		assetLicenseImpl.setAssetLicenseId(assetLicense.getAssetLicenseId());
		assetLicenseImpl.setUserId(assetLicense.getUserId());
		assetLicenseImpl.setCreateDate(assetLicense.getCreateDate());
		assetLicenseImpl.setModifiedDate(assetLicense.getModifiedDate());
		assetLicenseImpl.setClassNameId(assetLicense.getClassNameId());
		assetLicenseImpl.setClassPK(assetLicense.getClassPK());
		assetLicenseImpl.setLicenseId(assetLicense.getLicenseId());
		assetLicenseImpl.setName(assetLicense.getName());
		assetLicenseImpl.setRequiredVersion(assetLicense.getRequiredVersion());
		assetLicenseImpl.setUsageType(assetLicense.getUsageType());
		assetLicenseImpl.setLicenseType(assetLicense.getLicenseType());
		assetLicenseImpl.setLicenseTypeAllotment(assetLicense.getLicenseTypeAllotment());
		assetLicenseImpl.setLifetime(assetLicense.getLifetime());
		assetLicenseImpl.setStatus(assetLicense.getStatus());

		return assetLicenseImpl;
	}

	/**
	 * Returns the asset license with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset license
	 * @return the asset license
	 * @throws com.liferay.portal.NoSuchModelException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetLicense findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset license with the primary key or throws a {@link com.liferay.osb.NoSuchAssetLicenseException} if it could not be found.
	 *
	 * @param assetLicenseId the primary key of the asset license
	 * @return the asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByPrimaryKey(long assetLicenseId)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByPrimaryKey(assetLicenseId);

		if (assetLicense == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetLicenseId);
			}

			throw new NoSuchAssetLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetLicenseId);
		}

		return assetLicense;
	}

	/**
	 * Returns the asset license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset license
	 * @return the asset license, or <code>null</code> if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetLicense fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetLicenseId the primary key of the asset license
	 * @return the asset license, or <code>null</code> if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByPrimaryKey(long assetLicenseId)
		throws SystemException {
		AssetLicense assetLicense = (AssetLicense)EntityCacheUtil.getResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetLicenseImpl.class, assetLicenseId);

		if (assetLicense == _nullAssetLicense) {
			return null;
		}

		if (assetLicense == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetLicense = (AssetLicense)session.get(AssetLicenseImpl.class,
						Long.valueOf(assetLicenseId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetLicense != null) {
					cacheResult(assetLicense);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetLicenseImpl.class, assetLicenseId,
						_nullAssetLicense);
				}

				closeSession(session);
			}
		}

		return assetLicense;
	}

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C(long classNameId, long classPK,
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

		List<AssetLicense> list = (List<AssetLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetLicense assetLicense : list) {
				if ((classNameId != assetLicense.getClassNameId()) ||
						(classPK != assetLicense.getClassPK())) {
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

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetLicense> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<AssetLicense> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense[] findByC_C_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_PrevAndNext(session, assetLicense, classNameId,
					classPK, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_PrevAndNext(session, assetLicense, classNameId,
					classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

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

		else {
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @return the matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_S(long classNameId, long classPK,
		int status) throws SystemException {
		return findByC_C_S(classNameId, classPK, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_S(long classNameId, long classPK,
		int status, int start, int end) throws SystemException {
		return findByC_C_S(classNameId, classPK, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_S(long classNameId, long classPK,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] { classNameId, classPK, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] {
					classNameId, classPK, status,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = (List<AssetLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetLicense assetLicense : list) {
				if ((classNameId != assetLicense.getClassNameId()) ||
						(classPK != assetLicense.getClassPK()) ||
						(status != assetLicense.getStatus())) {
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
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(status);

				list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_S_First(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_S_First(classNameId, classPK,
				status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_S_First(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetLicense> list = findByC_C_S(classNameId, classPK, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_S_Last(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_S_Last(classNameId, classPK,
				status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_S_Last(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_S(classNameId, classPK, status);

		List<AssetLicense> list = findByC_C_S(classNameId, classPK, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense[] findByC_C_S_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, status, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_S_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_S_STATUS_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @return the matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType) throws SystemException {
		return findByC_C_UT_LT(classNameId, classPK, usageType, licenseType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType, int start, int end)
		throws SystemException {
		return findByC_C_UT_LT(classNameId, classPK, usageType, licenseType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = (List<AssetLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetLicense assetLicense : list) {
				if ((classNameId != assetLicense.getClassNameId()) ||
						(classPK != assetLicense.getClassPK()) ||
						(usageType != assetLicense.getUsageType()) ||
						(licenseType != assetLicense.getLicenseType())) {
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
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_First(long classNameId, long classPK,
		int usageType, int licenseType, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_First(classNameId,
				classPK, usageType, licenseType, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_First(long classNameId, long classPK,
		int usageType, int licenseType, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetLicense> list = findByC_C_UT_LT(classNameId, classPK,
				usageType, licenseType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_Last(long classNameId, long classPK,
		int usageType, int licenseType, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_Last(classNameId, classPK,
				usageType, licenseType, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_Last(long classNameId, long classPK,
		int usageType, int licenseType, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_UT_LT(classNameId, classPK, usageType,
				licenseType);

		List<AssetLicense> list = findByC_C_UT_LT(classNameId, classPK,
				usageType, licenseType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense[] findByC_C_UT_LT_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
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

	protected AssetLicense getByC_C_UT_LT_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @return the matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType, long licenseTypeAllotment)
		throws SystemException {
		return findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end)
		throws SystemException {
		return findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = (List<AssetLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetLicense assetLicense : list) {
				if ((classNameId != assetLicense.getClassNameId()) ||
						(classPK != assetLicense.getClassPK()) ||
						(usageType != assetLicense.getUsageType()) ||
						(licenseType != assetLicense.getLicenseType()) ||
						(licenseTypeAllotment != assetLicense.getLicenseTypeAllotment())) {
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
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

				list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_First(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetLicense> list = findByC_C_UT_LT_LTA(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_Last(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_UT_LT_LTA(classNameId, classPK, usageType,
				licenseType, licenseTypeAllotment);

		List<AssetLicense> list = findByC_C_UT_LT_LTA(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense[] findByC_C_UT_LT_LTA_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_LTA_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_LTA_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_UT_LT_LTA_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		qPos.add(licenseTypeAllotment);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @return the matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) throws SystemException {
		return findByC_C_UT_LT_S(classNameId, classPK, usageType, licenseType,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status, int start, int end)
		throws SystemException {
		return findByC_C_UT_LT_S(classNameId, classPK, usageType, licenseType,
			status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType, status
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType, status,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = (List<AssetLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetLicense assetLicense : list) {
				if ((classNameId != assetLicense.getClassNameId()) ||
						(classPK != assetLicense.getClassPK()) ||
						(usageType != assetLicense.getUsageType()) ||
						(licenseType != assetLicense.getLicenseType()) ||
						(status != assetLicense.getStatus())) {
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
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(status);

				list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_S_First(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_S_First(classNameId,
				classPK, usageType, licenseType, status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_S_First(long classNameId,
		long classPK, int usageType, int licenseType, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetLicense> list = findByC_C_UT_LT_S(classNameId, classPK,
				usageType, licenseType, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_S_Last(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_S_Last(classNameId,
				classPK, usageType, licenseType, status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_S_Last(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C_UT_LT_S(classNameId, classPK, usageType,
				licenseType, status);

		List<AssetLicense> list = findByC_C_UT_LT_S(classNameId, classPK,
				usageType, licenseType, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense[] findByC_C_UT_LT_S_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		int status, OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType, status,
					orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType, status,
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

	protected AssetLicense getByC_C_UT_LT_S_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_STATUS_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @return the matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status) throws SystemException {
		return findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end)
		throws SystemException {
		return findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = (List<AssetLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetLicense assetLicense : list) {
				if ((classNameId != assetLicense.getClassNameId()) ||
						(classPK != assetLicense.getClassPK()) ||
						(usageType != assetLicense.getUsageType()) ||
						(licenseType != assetLicense.getLicenseType()) ||
						(licenseTypeAllotment != assetLicense.getLicenseTypeAllotment()) ||
						(status != assetLicense.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

				qPos.add(status);

				list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_S_First(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment, status,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetLicense> list = findByC_C_UT_LT_LTA_S(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense findByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_S_Last(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment, status,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense fetchByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
				licenseType, licenseTypeAllotment, status);

		List<AssetLicense> list = findByC_C_UT_LT_LTA_S(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws com.liferay.osb.NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetLicense[] findByC_C_UT_LT_LTA_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment, int status,
		OrderByComparator orderByComparator)
		throws NoSuchAssetLicenseException, SystemException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_LTA_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_LTA_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_UT_LT_LTA_S_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		qPos.add(licenseTypeAllotment);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset licenses.
	 *
	 * @return the asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetLicense> findAll(int start, int end,
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

		List<AssetLicense> list = (List<AssetLicense>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETLICENSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETLICENSE.concat(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (AssetLicense assetLicense : findByC_C(classNameId, classPK)) {
			remove(assetLicense);
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_S(long classNameId, long classPK, int status)
		throws SystemException {
		for (AssetLicense assetLicense : findByC_C_S(classNameId, classPK,
				status)) {
			remove(assetLicense);
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType) throws SystemException {
		for (AssetLicense assetLicense : findByC_C_UT_LT(classNameId, classPK,
				usageType, licenseType)) {
			remove(assetLicense);
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment)
		throws SystemException {
		for (AssetLicense assetLicense : findByC_C_UT_LT_LTA(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment)) {
			remove(assetLicense);
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) throws SystemException {
		for (AssetLicense assetLicense : findByC_C_UT_LT_S(classNameId,
				classPK, usageType, licenseType, status)) {
			remove(assetLicense);
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status)
		throws SystemException {
		for (AssetLicense assetLicense : findByC_C_UT_LT_LTA_S(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment, status)) {
			remove(assetLicense);
		}
	}

	/**
	 * Removes all the asset licenses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetLicense assetLicense : findAll()) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

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
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @return the number of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_S(long classNameId, long classPK, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @return the number of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_UT_LT(long classNameId, long classPK, int usageType,
		int licenseType) throws SystemException {
		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_UT_LT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_UT_LT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @return the number of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType,
				licenseTypeAllotment
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @return the number of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @return the number of matching asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType,
				licenseTypeAllotment, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset licenses.
	 *
	 * @return the number of asset licenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETLICENSE);

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
	 * Initializes the asset license persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetLicense")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetLicense>> listenersList = new ArrayList<ModelListener<AssetLicense>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetLicense>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetLicenseImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETLICENSE = "SELECT assetLicense FROM AssetLicense assetLicense";
	private static final String _SQL_SELECT_ASSETLICENSE_WHERE = "SELECT assetLicense FROM AssetLicense assetLicense WHERE ";
	private static final String _SQL_COUNT_ASSETLICENSE = "SELECT COUNT(assetLicense) FROM AssetLicense assetLicense";
	private static final String _SQL_COUNT_ASSETLICENSE_WHERE = "SELECT COUNT(assetLicense) FROM AssetLicense assetLicense WHERE ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetLicense.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_S_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_STATUS_2 = "assetLicense.status = ?";
	private static final String _FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2 = "assetLicense.licenseType = ?";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2 = "assetLicense.licenseType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2 =
		"assetLicense.licenseTypeAllotment = ?";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2 = "assetLicense.licenseType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_STATUS_2 = "assetLicense.status = ?";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2 = "assetLicense.licenseType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2 =
		"assetLicense.licenseTypeAllotment = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2 = "assetLicense.status = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetLicense.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetLicense exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetLicense exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetLicensePersistenceImpl.class);
	private static AssetLicense _nullAssetLicense = new AssetLicenseImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetLicense> toCacheModel() {
				return _nullAssetLicenseCacheModel;
			}
		};

	private static CacheModel<AssetLicense> _nullAssetLicenseCacheModel = new CacheModel<AssetLicense>() {
			public AssetLicense toEntityModel() {
				return _nullAssetLicense;
			}
		};
}
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

import com.liferay.osb.NoSuchAssetReceiptException;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.impl.AssetReceiptImpl;
import com.liferay.osb.model.impl.AssetReceiptModelImpl;

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
import com.liferay.portal.service.persistence.CountryPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the asset receipt service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptPersistence
 * @see AssetReceiptUtil
 * @generated
 */
public class AssetReceiptPersistenceImpl extends BasePersistenceImpl<AssetReceipt>
	implements AssetReceiptPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetReceiptUtil} to access the asset receipt persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetReceiptImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, AssetReceiptImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, AssetReceiptImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetEntryId",
			new String[] { Long.class.getName() },
			AssetReceiptModelImpl.ASSETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETENTRYID = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UI_PCNI_PCPK = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, AssetReceiptImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUI_PCNI_PCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AssetReceiptModelImpl.USERID_COLUMN_BITMASK |
			AssetReceiptModelImpl.PRODUCTCLASSNAMEID_COLUMN_BITMASK |
			AssetReceiptModelImpl.PRODUCTCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UI_PCNI_PCPK = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUI_PCNI_PCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, AssetReceiptImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByOCNI_OCPK_PCNI_PCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			AssetReceiptModelImpl.OWNERCLASSNAMEID_COLUMN_BITMASK |
			AssetReceiptModelImpl.OWNERCLASSPK_COLUMN_BITMASK |
			AssetReceiptModelImpl.PRODUCTCLASSNAMEID_COLUMN_BITMASK |
			AssetReceiptModelImpl.PRODUCTCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OCNI_OCPK_PCNI_PCPK = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOCNI_OCPK_PCNI_PCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, AssetReceiptImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, AssetReceiptImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset receipt in the entity cache if it is enabled.
	 *
	 * @param assetReceipt the asset receipt
	 */
	public void cacheResult(AssetReceipt assetReceipt) {
		EntityCacheUtil.putResult(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptImpl.class, assetReceipt.getPrimaryKey(), assetReceipt);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK,
			new Object[] {
				Long.valueOf(assetReceipt.getUserId()),
				Long.valueOf(assetReceipt.getProductClassNameId()),
				Long.valueOf(assetReceipt.getProductClassPK())
			}, assetReceipt);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
			new Object[] {
				Long.valueOf(assetReceipt.getOwnerClassNameId()),
				Long.valueOf(assetReceipt.getOwnerClassPK()),
				Long.valueOf(assetReceipt.getProductClassNameId()),
				Long.valueOf(assetReceipt.getProductClassPK())
			}, assetReceipt);

		assetReceipt.resetOriginalValues();
	}

	/**
	 * Caches the asset receipts in the entity cache if it is enabled.
	 *
	 * @param assetReceipts the asset receipts
	 */
	public void cacheResult(List<AssetReceipt> assetReceipts) {
		for (AssetReceipt assetReceipt : assetReceipts) {
			if (EntityCacheUtil.getResult(
						AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptImpl.class, assetReceipt.getPrimaryKey()) == null) {
				cacheResult(assetReceipt);
			}
			else {
				assetReceipt.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset receipts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetReceiptImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetReceiptImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset receipt.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetReceipt assetReceipt) {
		EntityCacheUtil.removeResult(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptImpl.class, assetReceipt.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetReceipt);
	}

	@Override
	public void clearCache(List<AssetReceipt> assetReceipts) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetReceipt assetReceipt : assetReceipts) {
			EntityCacheUtil.removeResult(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptImpl.class, assetReceipt.getPrimaryKey());

			clearUniqueFindersCache(assetReceipt);
		}
	}

	protected void cacheUniqueFindersCache(AssetReceipt assetReceipt) {
		if (assetReceipt.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(assetReceipt.getUserId()),
					Long.valueOf(assetReceipt.getProductClassNameId()),
					Long.valueOf(assetReceipt.getProductClassPK())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UI_PCNI_PCPK, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK, args,
				assetReceipt);

			args = new Object[] {
					Long.valueOf(assetReceipt.getOwnerClassNameId()),
					Long.valueOf(assetReceipt.getOwnerClassPK()),
					Long.valueOf(assetReceipt.getProductClassNameId()),
					Long.valueOf(assetReceipt.getProductClassPK())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OCNI_OCPK_PCNI_PCPK,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
				args, assetReceipt);
		}
		else {
			AssetReceiptModelImpl assetReceiptModelImpl = (AssetReceiptModelImpl)assetReceipt;

			if ((assetReceiptModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UI_PCNI_PCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceipt.getUserId()),
						Long.valueOf(assetReceipt.getProductClassNameId()),
						Long.valueOf(assetReceipt.getProductClassPK())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UI_PCNI_PCPK,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK,
					args, assetReceipt);
			}

			if ((assetReceiptModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceipt.getOwnerClassNameId()),
						Long.valueOf(assetReceipt.getOwnerClassPK()),
						Long.valueOf(assetReceipt.getProductClassNameId()),
						Long.valueOf(assetReceipt.getProductClassPK())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OCNI_OCPK_PCNI_PCPK,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
					args, assetReceipt);
			}
		}
	}

	protected void clearUniqueFindersCache(AssetReceipt assetReceipt) {
		AssetReceiptModelImpl assetReceiptModelImpl = (AssetReceiptModelImpl)assetReceipt;

		Object[] args = new Object[] {
				Long.valueOf(assetReceipt.getUserId()),
				Long.valueOf(assetReceipt.getProductClassNameId()),
				Long.valueOf(assetReceipt.getProductClassPK())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UI_PCNI_PCPK, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK, args);

		if ((assetReceiptModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UI_PCNI_PCPK.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(assetReceiptModelImpl.getOriginalUserId()),
					Long.valueOf(assetReceiptModelImpl.getOriginalProductClassNameId()),
					Long.valueOf(assetReceiptModelImpl.getOriginalProductClassPK())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UI_PCNI_PCPK, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK, args);
		}

		args = new Object[] {
				Long.valueOf(assetReceipt.getOwnerClassNameId()),
				Long.valueOf(assetReceipt.getOwnerClassPK()),
				Long.valueOf(assetReceipt.getProductClassNameId()),
				Long.valueOf(assetReceipt.getProductClassPK())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCNI_OCPK_PCNI_PCPK,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
			args);

		if ((assetReceiptModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(assetReceiptModelImpl.getOriginalOwnerClassNameId()),
					Long.valueOf(assetReceiptModelImpl.getOriginalOwnerClassPK()),
					Long.valueOf(assetReceiptModelImpl.getOriginalProductClassNameId()),
					Long.valueOf(assetReceiptModelImpl.getOriginalProductClassPK())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OCNI_OCPK_PCNI_PCPK,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
				args);
		}
	}

	/**
	 * Creates a new asset receipt with the primary key. Does not add the asset receipt to the database.
	 *
	 * @param assetReceiptId the primary key for the new asset receipt
	 * @return the new asset receipt
	 */
	public AssetReceipt create(long assetReceiptId) {
		AssetReceipt assetReceipt = new AssetReceiptImpl();

		assetReceipt.setNew(true);
		assetReceipt.setPrimaryKey(assetReceiptId);

		return assetReceipt;
	}

	/**
	 * Removes the asset receipt with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetReceiptId the primary key of the asset receipt
	 * @return the asset receipt that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt remove(long assetReceiptId)
		throws NoSuchAssetReceiptException, SystemException {
		return remove(Long.valueOf(assetReceiptId));
	}

	/**
	 * Removes the asset receipt with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset receipt
	 * @return the asset receipt that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceipt remove(Serializable primaryKey)
		throws NoSuchAssetReceiptException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetReceipt assetReceipt = (AssetReceipt)session.get(AssetReceiptImpl.class,
					primaryKey);

			if (assetReceipt == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetReceiptException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetReceipt);
		}
		catch (NoSuchAssetReceiptException nsee) {
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
	protected AssetReceipt removeImpl(AssetReceipt assetReceipt)
		throws SystemException {
		assetReceipt = toUnwrappedModel(assetReceipt);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetReceipt);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetReceipt);

		return assetReceipt;
	}

	@Override
	public AssetReceipt updateImpl(
		com.liferay.osb.model.AssetReceipt assetReceipt, boolean merge)
		throws SystemException {
		assetReceipt = toUnwrappedModel(assetReceipt);

		boolean isNew = assetReceipt.isNew();

		AssetReceiptModelImpl assetReceiptModelImpl = (AssetReceiptModelImpl)assetReceipt;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetReceipt, merge);

			assetReceipt.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetReceiptModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetReceiptModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetReceiptModelImpl.getOriginalAssetEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(assetReceiptModelImpl.getAssetEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptImpl.class, assetReceipt.getPrimaryKey(), assetReceipt);

		clearUniqueFindersCache(assetReceipt);
		cacheUniqueFindersCache(assetReceipt);

		return assetReceipt;
	}

	protected AssetReceipt toUnwrappedModel(AssetReceipt assetReceipt) {
		if (assetReceipt instanceof AssetReceiptImpl) {
			return assetReceipt;
		}

		AssetReceiptImpl assetReceiptImpl = new AssetReceiptImpl();

		assetReceiptImpl.setNew(assetReceipt.isNew());
		assetReceiptImpl.setPrimaryKey(assetReceipt.getPrimaryKey());

		assetReceiptImpl.setAssetReceiptId(assetReceipt.getAssetReceiptId());
		assetReceiptImpl.setUserId(assetReceipt.getUserId());
		assetReceiptImpl.setUserName(assetReceipt.getUserName());
		assetReceiptImpl.setCreateDate(assetReceipt.getCreateDate());
		assetReceiptImpl.setAssetEntryId(assetReceipt.getAssetEntryId());
		assetReceiptImpl.setOwnerClassNameId(assetReceipt.getOwnerClassNameId());
		assetReceiptImpl.setOwnerClassPK(assetReceipt.getOwnerClassPK());
		assetReceiptImpl.setLegalEntityName(assetReceipt.getLegalEntityName());
		assetReceiptImpl.setProductClassNameId(assetReceipt.getProductClassNameId());
		assetReceiptImpl.setProductClassPK(assetReceipt.getProductClassPK());
		assetReceiptImpl.setType(assetReceipt.getType());
		assetReceiptImpl.setCurrencyEntryId(assetReceipt.getCurrencyEntryId());
		assetReceiptImpl.setActualPrice(assetReceipt.getActualPrice());

		return assetReceiptImpl;
	}

	/**
	 * Returns the asset receipt with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt
	 * @return the asset receipt
	 * @throws com.liferay.portal.NoSuchModelException if a asset receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceipt findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptException} if it could not be found.
	 *
	 * @param assetReceiptId the primary key of the asset receipt
	 * @return the asset receipt
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt findByPrimaryKey(long assetReceiptId)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = fetchByPrimaryKey(assetReceiptId);

		if (assetReceipt == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetReceiptId);
			}

			throw new NoSuchAssetReceiptException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetReceiptId);
		}

		return assetReceipt;
	}

	/**
	 * Returns the asset receipt with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt
	 * @return the asset receipt, or <code>null</code> if a asset receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceipt fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetReceiptId the primary key of the asset receipt
	 * @return the asset receipt, or <code>null</code> if a asset receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt fetchByPrimaryKey(long assetReceiptId)
		throws SystemException {
		AssetReceipt assetReceipt = (AssetReceipt)EntityCacheUtil.getResult(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptImpl.class, assetReceiptId);

		if (assetReceipt == _nullAssetReceipt) {
			return null;
		}

		if (assetReceipt == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetReceipt = (AssetReceipt)session.get(AssetReceiptImpl.class,
						Long.valueOf(assetReceiptId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetReceipt != null) {
					cacheResult(assetReceipt);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetReceiptModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptImpl.class, assetReceiptId,
						_nullAssetReceipt);
				}

				closeSession(session);
			}
		}

		return assetReceipt;
	}

	/**
	 * Returns all the asset receipts where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the matching asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceipt> findByAssetEntryId(long assetEntryId)
		throws SystemException {
		return findByAssetEntryId(assetEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipts where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset receipts
	 * @param end the upper bound of the range of asset receipts (not inclusive)
	 * @return the range of matching asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceipt> findByAssetEntryId(long assetEntryId, int start,
		int end) throws SystemException {
		return findByAssetEntryId(assetEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipts where assetEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param start the lower bound of the range of asset receipts
	 * @param end the upper bound of the range of asset receipts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceipt> findByAssetEntryId(long assetEntryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<AssetReceipt> list = (List<AssetReceipt>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetReceipt assetReceipt : list) {
				if ((assetEntryId != assetReceipt.getAssetEntryId())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPT_WHERE);

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

				list = (List<AssetReceipt>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset receipt in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt findByAssetEntryId_First(long assetEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = fetchByAssetEntryId_First(assetEntryId,
				orderByComparator);

		if (assetReceipt != null) {
			return assetReceipt;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptException(msg.toString());
	}

	/**
	 * Returns the first asset receipt in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt fetchByAssetEntryId_First(long assetEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetReceipt> list = findByAssetEntryId(assetEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt findByAssetEntryId_Last(long assetEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = fetchByAssetEntryId_Last(assetEntryId,
				orderByComparator);

		if (assetReceipt != null) {
			return assetReceipt;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptException(msg.toString());
	}

	/**
	 * Returns the last asset receipt in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt fetchByAssetEntryId_Last(long assetEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetEntryId(assetEntryId);

		List<AssetReceipt> list = findByAssetEntryId(assetEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipts before and after the current asset receipt in the ordered set where assetEntryId = &#63;.
	 *
	 * @param assetReceiptId the primary key of the current asset receipt
	 * @param assetEntryId the asset entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a asset receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt[] findByAssetEntryId_PrevAndNext(long assetReceiptId,
		long assetEntryId, OrderByComparator orderByComparator)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = findByPrimaryKey(assetReceiptId);

		Session session = null;

		try {
			session = openSession();

			AssetReceipt[] array = new AssetReceiptImpl[3];

			array[0] = getByAssetEntryId_PrevAndNext(session, assetReceipt,
					assetEntryId, orderByComparator, true);

			array[1] = assetReceipt;

			array[2] = getByAssetEntryId_PrevAndNext(session, assetReceipt,
					assetEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceipt getByAssetEntryId_PrevAndNext(Session session,
		AssetReceipt assetReceipt, long assetEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETRECEIPT_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceipt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceipt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the matching asset receipt
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt findByUI_PCNI_PCPK(long userId,
		long productClassNameId, long productClassPK)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = fetchByUI_PCNI_PCPK(userId,
				productClassNameId, productClassPK);

		if (assetReceipt == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", productClassNameId=");
			msg.append(productClassNameId);

			msg.append(", productClassPK=");
			msg.append(productClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetReceiptException(msg.toString());
		}

		return assetReceipt;
	}

	/**
	 * Returns the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt fetchByUI_PCNI_PCPK(long userId,
		long productClassNameId, long productClassPK) throws SystemException {
		return fetchByUI_PCNI_PCPK(userId, productClassNameId, productClassPK,
			true);
	}

	/**
	 * Returns the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt fetchByUI_PCNI_PCPK(long userId,
		long productClassNameId, long productClassPK, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, productClassNameId, productClassPK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK,
					finderArgs, this);
		}

		if (result instanceof AssetReceipt) {
			AssetReceipt assetReceipt = (AssetReceipt)result;

			if ((userId != assetReceipt.getUserId()) ||
					(productClassNameId != assetReceipt.getProductClassNameId()) ||
					(productClassPK != assetReceipt.getProductClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASSETRECEIPT_WHERE);

			query.append(_FINDER_COLUMN_UI_PCNI_PCPK_USERID_2);

			query.append(_FINDER_COLUMN_UI_PCNI_PCPK_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_UI_PCNI_PCPK_PRODUCTCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(productClassNameId);

				qPos.add(productClassPK);

				List<AssetReceipt> list = q.list();

				result = list;

				AssetReceipt assetReceipt = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK,
						finderArgs, list);
				}
				else {
					assetReceipt = list.get(0);

					cacheResult(assetReceipt);

					if ((assetReceipt.getUserId() != userId) ||
							(assetReceipt.getProductClassNameId() != productClassNameId) ||
							(assetReceipt.getProductClassPK() != productClassPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK,
							finderArgs, assetReceipt);
					}
				}

				return assetReceipt;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UI_PCNI_PCPK,
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
				return (AssetReceipt)result;
			}
		}
	}

	/**
	 * Returns the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptException} if it could not be found.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the matching asset receipt
	 * @throws com.liferay.osb.NoSuchAssetReceiptException if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt findByOCNI_OCPK_PCNI_PCPK(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, long productClassPK)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = fetchByOCNI_OCPK_PCNI_PCPK(ownerClassNameId,
				ownerClassPK, productClassNameId, productClassPK);

		if (assetReceipt == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ownerClassNameId=");
			msg.append(ownerClassNameId);

			msg.append(", ownerClassPK=");
			msg.append(ownerClassPK);

			msg.append(", productClassNameId=");
			msg.append(productClassNameId);

			msg.append(", productClassPK=");
			msg.append(productClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetReceiptException(msg.toString());
		}

		return assetReceipt;
	}

	/**
	 * Returns the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt fetchByOCNI_OCPK_PCNI_PCPK(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, long productClassPK)
		throws SystemException {
		return fetchByOCNI_OCPK_PCNI_PCPK(ownerClassNameId, ownerClassPK,
			productClassNameId, productClassPK, true);
	}

	/**
	 * Returns the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset receipt, or <code>null</code> if a matching asset receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt fetchByOCNI_OCPK_PCNI_PCPK(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, long productClassPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				ownerClassNameId, ownerClassPK, productClassNameId,
				productClassPK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
					finderArgs, this);
		}

		if (result instanceof AssetReceipt) {
			AssetReceipt assetReceipt = (AssetReceipt)result;

			if ((ownerClassNameId != assetReceipt.getOwnerClassNameId()) ||
					(ownerClassPK != assetReceipt.getOwnerClassPK()) ||
					(productClassNameId != assetReceipt.getProductClassNameId()) ||
					(productClassPK != assetReceipt.getProductClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ASSETRECEIPT_WHERE);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_OWNERCLASSPK_2);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_PRODUCTCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

				qPos.add(productClassNameId);

				qPos.add(productClassPK);

				List<AssetReceipt> list = q.list();

				result = list;

				AssetReceipt assetReceipt = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
						finderArgs, list);
				}
				else {
					assetReceipt = list.get(0);

					cacheResult(assetReceipt);

					if ((assetReceipt.getOwnerClassNameId() != ownerClassNameId) ||
							(assetReceipt.getOwnerClassPK() != ownerClassPK) ||
							(assetReceipt.getProductClassNameId() != productClassNameId) ||
							(assetReceipt.getProductClassPK() != productClassPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
							finderArgs, assetReceipt);
					}
				}

				return assetReceipt;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_OCNI_OCPK_PCNI_PCPK,
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
				return (AssetReceipt)result;
			}
		}
	}

	/**
	 * Returns all the asset receipts.
	 *
	 * @return the asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceipt> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipts
	 * @param end the upper bound of the range of asset receipts (not inclusive)
	 * @return the range of asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceipt> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipts
	 * @param end the upper bound of the range of asset receipts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceipt> findAll(int start, int end,
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

		List<AssetReceipt> list = (List<AssetReceipt>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETRECEIPT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETRECEIPT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetReceipt>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetReceipt>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the asset receipts where assetEntryId = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetEntryId(long assetEntryId)
		throws SystemException {
		for (AssetReceipt assetReceipt : findByAssetEntryId(assetEntryId)) {
			remove(assetReceipt);
		}
	}

	/**
	 * Removes the asset receipt where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the asset receipt that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt removeByUI_PCNI_PCPK(long userId,
		long productClassNameId, long productClassPK)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = findByUI_PCNI_PCPK(userId,
				productClassNameId, productClassPK);

		return remove(assetReceipt);
	}

	/**
	 * Removes the asset receipt where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63; from the database.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the asset receipt that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceipt removeByOCNI_OCPK_PCNI_PCPK(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, long productClassPK)
		throws NoSuchAssetReceiptException, SystemException {
		AssetReceipt assetReceipt = findByOCNI_OCPK_PCNI_PCPK(ownerClassNameId,
				ownerClassPK, productClassNameId, productClassPK);

		return remove(assetReceipt);
	}

	/**
	 * Removes all the asset receipts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetReceipt assetReceipt : findAll()) {
			remove(assetReceipt);
		}
	}

	/**
	 * Returns the number of asset receipts where assetEntryId = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @return the number of matching asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetEntryId(long assetEntryId) throws SystemException {
		Object[] finderArgs = new Object[] { assetEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPT_WHERE);

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
	 * Returns the number of asset receipts where userId = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the number of matching asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUI_PCNI_PCPK(long userId, long productClassNameId,
		long productClassPK) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, productClassNameId, productClassPK
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UI_PCNI_PCPK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETRECEIPT_WHERE);

			query.append(_FINDER_COLUMN_UI_PCNI_PCPK_USERID_2);

			query.append(_FINDER_COLUMN_UI_PCNI_PCPK_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_UI_PCNI_PCPK_PRODUCTCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UI_PCNI_PCPK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipts where ownerClassNameId = &#63; and ownerClassPK = &#63; and productClassNameId = &#63; and productClassPK = &#63;.
	 *
	 * @param ownerClassNameId the owner class name ID
	 * @param ownerClassPK the owner class p k
	 * @param productClassNameId the product class name ID
	 * @param productClassPK the product class p k
	 * @return the number of matching asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOCNI_OCPK_PCNI_PCPK(long ownerClassNameId,
		long ownerClassPK, long productClassNameId, long productClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				ownerClassNameId, ownerClassPK, productClassNameId,
				productClassPK
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OCNI_OCPK_PCNI_PCPK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETRECEIPT_WHERE);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_OWNERCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_OWNERCLASSPK_2);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_PRODUCTCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_PRODUCTCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerClassNameId);

				qPos.add(ownerClassPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OCNI_OCPK_PCNI_PCPK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipts.
	 *
	 * @return the number of asset receipts
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETRECEIPT);

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
	 * Initializes the asset receipt persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetReceipt")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetReceipt>> listenersList = new ArrayList<ModelListener<AssetReceipt>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetReceipt>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetReceiptImpl.class.getName());
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
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	private static final String _SQL_SELECT_ASSETRECEIPT = "SELECT assetReceipt FROM AssetReceipt assetReceipt";
	private static final String _SQL_SELECT_ASSETRECEIPT_WHERE = "SELECT assetReceipt FROM AssetReceipt assetReceipt WHERE ";
	private static final String _SQL_COUNT_ASSETRECEIPT = "SELECT COUNT(assetReceipt) FROM AssetReceipt assetReceipt";
	private static final String _SQL_COUNT_ASSETRECEIPT_WHERE = "SELECT COUNT(assetReceipt) FROM AssetReceipt assetReceipt WHERE ";
	private static final String _FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2 = "assetReceipt.assetEntryId = ?";
	private static final String _FINDER_COLUMN_UI_PCNI_PCPK_USERID_2 = "assetReceipt.userId = ? AND ";
	private static final String _FINDER_COLUMN_UI_PCNI_PCPK_PRODUCTCLASSNAMEID_2 =
		"assetReceipt.productClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_UI_PCNI_PCPK_PRODUCTCLASSPK_2 = "assetReceipt.productClassPK = ?";
	private static final String _FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_OWNERCLASSNAMEID_2 =
		"assetReceipt.ownerClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_OWNERCLASSPK_2 =
		"assetReceipt.ownerClassPK = ? AND ";
	private static final String _FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_PRODUCTCLASSNAMEID_2 =
		"assetReceipt.productClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_OCNI_OCPK_PCNI_PCPK_PRODUCTCLASSPK_2 =
		"assetReceipt.productClassPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetReceipt.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetReceipt exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetReceipt exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetReceiptPersistenceImpl.class);
	private static AssetReceipt _nullAssetReceipt = new AssetReceiptImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetReceipt> toCacheModel() {
				return _nullAssetReceiptCacheModel;
			}
		};

	private static CacheModel<AssetReceipt> _nullAssetReceiptCacheModel = new CacheModel<AssetReceipt>() {
			public AssetReceipt toEntityModel() {
				return _nullAssetReceipt;
			}
		};
}
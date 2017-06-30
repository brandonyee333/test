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

import com.liferay.osb.NoSuchAssetAuditException;
import com.liferay.osb.model.AssetAudit;
import com.liferay.osb.model.impl.AssetAuditImpl;
import com.liferay.osb.model.impl.AssetAuditModelImpl;

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
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the asset audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAuditPersistence
 * @see AssetAuditUtil
 * @generated
 */
public class AssetAuditPersistenceImpl extends BasePersistenceImpl<AssetAudit>
	implements AssetAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetAuditUtil} to access the asset audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetAuditModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetAuditModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C_C_T = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T =
		new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AssetAuditModelImpl.USERID_COLUMN_BITMASK |
			AssetAuditModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetAuditModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetAuditModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C_C_T = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_C_T = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_C_T",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C_T =
		new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_C_T",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AssetAuditModelImpl.CREATEDATE_COLUMN_BITMASK |
			AssetAuditModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetAuditModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetAuditModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_C_T = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_C_T",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, AssetAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset audit in the entity cache if it is enabled.
	 *
	 * @param assetAudit the asset audit
	 */
	public void cacheResult(AssetAudit assetAudit) {
		EntityCacheUtil.putResult(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditImpl.class, assetAudit.getPrimaryKey(), assetAudit);

		assetAudit.resetOriginalValues();
	}

	/**
	 * Caches the asset audits in the entity cache if it is enabled.
	 *
	 * @param assetAudits the asset audits
	 */
	public void cacheResult(List<AssetAudit> assetAudits) {
		for (AssetAudit assetAudit : assetAudits) {
			if (EntityCacheUtil.getResult(
						AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
						AssetAuditImpl.class, assetAudit.getPrimaryKey()) == null) {
				cacheResult(assetAudit);
			}
			else {
				assetAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset audits.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetAuditImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetAuditImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset audit.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetAudit assetAudit) {
		EntityCacheUtil.removeResult(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditImpl.class, assetAudit.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetAudit> assetAudits) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetAudit assetAudit : assetAudits) {
			EntityCacheUtil.removeResult(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
				AssetAuditImpl.class, assetAudit.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset audit with the primary key. Does not add the asset audit to the database.
	 *
	 * @param assetAuditId the primary key for the new asset audit
	 * @return the new asset audit
	 */
	public AssetAudit create(long assetAuditId) {
		AssetAudit assetAudit = new AssetAuditImpl();

		assetAudit.setNew(true);
		assetAudit.setPrimaryKey(assetAuditId);

		return assetAudit;
	}

	/**
	 * Removes the asset audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetAuditId the primary key of the asset audit
	 * @return the asset audit that was removed
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit remove(long assetAuditId)
		throws NoSuchAssetAuditException, SystemException {
		return remove(Long.valueOf(assetAuditId));
	}

	/**
	 * Removes the asset audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset audit
	 * @return the asset audit that was removed
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetAudit remove(Serializable primaryKey)
		throws NoSuchAssetAuditException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetAudit assetAudit = (AssetAudit)session.get(AssetAuditImpl.class,
					primaryKey);

			if (assetAudit == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetAudit);
		}
		catch (NoSuchAssetAuditException nsee) {
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
	protected AssetAudit removeImpl(AssetAudit assetAudit)
		throws SystemException {
		assetAudit = toUnwrappedModel(assetAudit);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetAudit);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetAudit);

		return assetAudit;
	}

	@Override
	public AssetAudit updateImpl(com.liferay.osb.model.AssetAudit assetAudit,
		boolean merge) throws SystemException {
		assetAudit = toUnwrappedModel(assetAudit);

		boolean isNew = assetAudit.isNew();

		AssetAuditModelImpl assetAuditModelImpl = (AssetAuditModelImpl)assetAudit;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetAudit, merge);

			assetAudit.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetAuditModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetAuditModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetAuditModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(assetAuditModelImpl.getClassNameId()),
						Long.valueOf(assetAuditModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((assetAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetAuditModelImpl.getOriginalUserId()),
						Long.valueOf(assetAuditModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetAuditModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetAuditModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T,
					args);

				args = new Object[] {
						Long.valueOf(assetAuditModelImpl.getUserId()),
						Long.valueOf(assetAuditModelImpl.getClassNameId()),
						Long.valueOf(assetAuditModelImpl.getClassPK()),
						Integer.valueOf(assetAuditModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T,
					args);
			}

			if ((assetAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetAuditModelImpl.getOriginalCreateDate(),
						Long.valueOf(assetAuditModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetAuditModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetAuditModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C_T,
					args);

				args = new Object[] {
						assetAuditModelImpl.getCreateDate(),
						Long.valueOf(assetAuditModelImpl.getClassNameId()),
						Long.valueOf(assetAuditModelImpl.getClassPK()),
						Integer.valueOf(assetAuditModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C_T,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
			AssetAuditImpl.class, assetAudit.getPrimaryKey(), assetAudit);

		return assetAudit;
	}

	protected AssetAudit toUnwrappedModel(AssetAudit assetAudit) {
		if (assetAudit instanceof AssetAuditImpl) {
			return assetAudit;
		}

		AssetAuditImpl assetAuditImpl = new AssetAuditImpl();

		assetAuditImpl.setNew(assetAudit.isNew());
		assetAuditImpl.setPrimaryKey(assetAudit.getPrimaryKey());

		assetAuditImpl.setAssetAuditId(assetAudit.getAssetAuditId());
		assetAuditImpl.setCompanyId(assetAudit.getCompanyId());
		assetAuditImpl.setUserId(assetAudit.getUserId());
		assetAuditImpl.setUserName(assetAudit.getUserName());
		assetAuditImpl.setCreateDate(assetAudit.getCreateDate());
		assetAuditImpl.setModifiedDate(assetAudit.getModifiedDate());
		assetAuditImpl.setLegalEntityName(assetAudit.getLegalEntityName());
		assetAuditImpl.setClassNameId(assetAudit.getClassNameId());
		assetAuditImpl.setClassPK(assetAudit.getClassPK());
		assetAuditImpl.setVendorClassNameId(assetAudit.getVendorClassNameId());
		assetAuditImpl.setVendorClassPK(assetAudit.getVendorClassPK());
		assetAuditImpl.setType(assetAudit.getType());
		assetAuditImpl.setDomain(assetAudit.getDomain());
		assetAuditImpl.setTime(assetAudit.getTime());
		assetAuditImpl.setCurrencyCode(assetAudit.getCurrencyCode());
		assetAuditImpl.setPrice(assetAudit.getPrice());

		return assetAuditImpl;
	}

	/**
	 * Returns the asset audit with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset audit
	 * @return the asset audit
	 * @throws com.liferay.portal.NoSuchModelException if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset audit with the primary key or throws a {@link com.liferay.osb.NoSuchAssetAuditException} if it could not be found.
	 *
	 * @param assetAuditId the primary key of the asset audit
	 * @return the asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit findByPrimaryKey(long assetAuditId)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = fetchByPrimaryKey(assetAuditId);

		if (assetAudit == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetAuditId);
			}

			throw new NoSuchAssetAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetAuditId);
		}

		return assetAudit;
	}

	/**
	 * Returns the asset audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset audit
	 * @return the asset audit, or <code>null</code> if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetAudit fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetAuditId the primary key of the asset audit
	 * @return the asset audit, or <code>null</code> if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit fetchByPrimaryKey(long assetAuditId)
		throws SystemException {
		AssetAudit assetAudit = (AssetAudit)EntityCacheUtil.getResult(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
				AssetAuditImpl.class, assetAuditId);

		if (assetAudit == _nullAssetAudit) {
			return null;
		}

		if (assetAudit == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetAudit = (AssetAudit)session.get(AssetAuditImpl.class,
						Long.valueOf(assetAuditId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetAudit != null) {
					cacheResult(assetAudit);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetAuditModelImpl.ENTITY_CACHE_ENABLED,
						AssetAuditImpl.class, assetAuditId, _nullAssetAudit);
				}

				closeSession(session);
			}
		}

		return assetAudit;
	}

	/**
	 * Returns all the asset audits where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset audits where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @return the range of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset audits where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByC_C(long classNameId, long classPK,
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

		List<AssetAudit> list = (List<AssetAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetAudit assetAudit : list) {
				if ((classNameId != assetAudit.getClassNameId()) ||
						(classPK != assetAudit.getClassPK())) {
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

			query.append(_SQL_SELECT_ASSETAUDIT_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<AssetAudit>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (assetAudit != null) {
			return assetAudit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAuditException(msg.toString());
	}

	/**
	 * Returns the first asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset audit, or <code>null</code> if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetAudit> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (assetAudit != null) {
			return assetAudit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAuditException(msg.toString());
	}

	/**
	 * Returns the last asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset audit, or <code>null</code> if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<AssetAudit> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset audits before and after the current asset audit in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetAuditId the primary key of the current asset audit
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit[] findByC_C_PrevAndNext(long assetAuditId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = findByPrimaryKey(assetAuditId);

		Session session = null;

		try {
			session = openSession();

			AssetAudit[] array = new AssetAuditImpl[3];

			array[0] = getByC_C_PrevAndNext(session, assetAudit, classNameId,
					classPK, orderByComparator, true);

			array[1] = assetAudit;

			array[2] = getByC_C_PrevAndNext(session, assetAudit, classNameId,
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

	protected AssetAudit getByC_C_PrevAndNext(Session session,
		AssetAudit assetAudit, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETAUDIT_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByU_C_C_T(long userId, long classNameId,
		long classPK, int type) throws SystemException {
		return findByU_C_C_T(userId, classNameId, classPK, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @return the range of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByU_C_C_T(long userId, long classNameId,
		long classPK, int type, int start, int end) throws SystemException {
		return findByU_C_C_T(userId, classNameId, classPK, type, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByU_C_C_T(long userId, long classNameId,
		long classPK, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T;
			finderArgs = new Object[] { userId, classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C_C_T;
			finderArgs = new Object[] {
					userId, classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<AssetAudit> list = (List<AssetAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetAudit assetAudit : list) {
				if ((userId != assetAudit.getUserId()) ||
						(classNameId != assetAudit.getClassNameId()) ||
						(classPK != assetAudit.getClassPK()) ||
						(type != assetAudit.getType())) {
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

			query.append(_SQL_SELECT_ASSETAUDIT_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

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

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				list = (List<AssetAudit>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit findByU_C_C_T_First(long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = fetchByU_C_C_T_First(userId, classNameId,
				classPK, type, orderByComparator);

		if (assetAudit != null) {
			return assetAudit;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAuditException(msg.toString());
	}

	/**
	 * Returns the first asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset audit, or <code>null</code> if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit fetchByU_C_C_T_First(long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetAudit> list = findByU_C_C_T(userId, classNameId, classPK,
				type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit findByU_C_C_T_Last(long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = fetchByU_C_C_T_Last(userId, classNameId,
				classPK, type, orderByComparator);

		if (assetAudit != null) {
			return assetAudit;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAuditException(msg.toString());
	}

	/**
	 * Returns the last asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset audit, or <code>null</code> if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit fetchByU_C_C_T_Last(long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByU_C_C_T(userId, classNameId, classPK, type);

		List<AssetAudit> list = findByU_C_C_T(userId, classNameId, classPK,
				type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset audits before and after the current asset audit in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param assetAuditId the primary key of the current asset audit
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit[] findByU_C_C_T_PrevAndNext(long assetAuditId,
		long userId, long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = findByPrimaryKey(assetAuditId);

		Session session = null;

		try {
			session = openSession();

			AssetAudit[] array = new AssetAuditImpl[3];

			array[0] = getByU_C_C_T_PrevAndNext(session, assetAudit, userId,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = assetAudit;

			array[2] = getByU_C_C_T_PrevAndNext(session, assetAudit, userId,
					classNameId, classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetAudit getByU_C_C_T_PrevAndNext(Session session,
		AssetAudit assetAudit, long userId, long classNameId, long classPK,
		int type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETAUDIT_WHERE);

		query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

		query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

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

		qPos.add(userId);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByC_C_C_T(Date createDate, long classNameId,
		long classPK, int type) throws SystemException {
		return findByC_C_C_T(createDate, classNameId, classPK, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @return the range of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByC_C_C_T(Date createDate, long classNameId,
		long classPK, int type, int start, int end) throws SystemException {
		return findByC_C_C_T(createDate, classNameId, classPK, type, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findByC_C_C_T(Date createDate, long classNameId,
		long classPK, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C_T;
			finderArgs = new Object[] { createDate, classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_C_T;
			finderArgs = new Object[] {
					createDate, classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<AssetAudit> list = (List<AssetAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetAudit assetAudit : list) {
				if (!Validator.equals(createDate, assetAudit.getCreateDate()) ||
						(classNameId != assetAudit.getClassNameId()) ||
						(classPK != assetAudit.getClassPK()) ||
						(type != assetAudit.getType())) {
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

			query.append(_SQL_SELECT_ASSETAUDIT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_C_C_C_T_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_C_C_C_T_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_C_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_C_T_TYPE_2);

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

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				list = (List<AssetAudit>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit findByC_C_C_T_First(Date createDate, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = fetchByC_C_C_T_First(createDate, classNameId,
				classPK, type, orderByComparator);

		if (assetAudit != null) {
			return assetAudit;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAuditException(msg.toString());
	}

	/**
	 * Returns the first asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset audit, or <code>null</code> if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit fetchByC_C_C_T_First(Date createDate, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetAudit> list = findByC_C_C_T(createDate, classNameId, classPK,
				type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit findByC_C_C_T_Last(Date createDate, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = fetchByC_C_C_T_Last(createDate, classNameId,
				classPK, type, orderByComparator);

		if (assetAudit != null) {
			return assetAudit;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAuditException(msg.toString());
	}

	/**
	 * Returns the last asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset audit, or <code>null</code> if a matching asset audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit fetchByC_C_C_T_Last(Date createDate, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_C_T(createDate, classNameId, classPK, type);

		List<AssetAudit> list = findByC_C_C_T(createDate, classNameId, classPK,
				type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset audits before and after the current asset audit in the ordered set where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param assetAuditId the primary key of the current asset audit
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset audit
	 * @throws com.liferay.osb.NoSuchAssetAuditException if a asset audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAudit[] findByC_C_C_T_PrevAndNext(long assetAuditId,
		Date createDate, long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAuditException, SystemException {
		AssetAudit assetAudit = findByPrimaryKey(assetAuditId);

		Session session = null;

		try {
			session = openSession();

			AssetAudit[] array = new AssetAuditImpl[3];

			array[0] = getByC_C_C_T_PrevAndNext(session, assetAudit,
					createDate, classNameId, classPK, type, orderByComparator,
					true);

			array[1] = assetAudit;

			array[2] = getByC_C_C_T_PrevAndNext(session, assetAudit,
					createDate, classNameId, classPK, type, orderByComparator,
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

	protected AssetAudit getByC_C_C_T_PrevAndNext(Session session,
		AssetAudit assetAudit, Date createDate, long classNameId, long classPK,
		int type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETAUDIT_WHERE);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_C_C_C_T_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_C_C_C_T_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_C_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_C_T_TYPE_2);

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

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset audits.
	 *
	 * @return the asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @return the range of asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset audits
	 * @param end the upper bound of the range of asset audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAudit> findAll(int start, int end,
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

		List<AssetAudit> list = (List<AssetAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETAUDIT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetAudit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetAudit>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the asset audits where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (AssetAudit assetAudit : findByC_C(classNameId, classPK)) {
			remove(assetAudit);
		}
	}

	/**
	 * Removes all the asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws SystemException {
		for (AssetAudit assetAudit : findByU_C_C_T(userId, classNameId,
				classPK, type)) {
			remove(assetAudit);
		}
	}

	/**
	 * Removes all the asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_C_T(Date createDate, long classNameId,
		long classPK, int type) throws SystemException {
		for (AssetAudit assetAudit : findByC_C_C_T(createDate, classNameId,
				classPK, type)) {
			remove(assetAudit);
		}
	}

	/**
	 * Removes all the asset audits from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetAudit assetAudit : findAll()) {
			remove(assetAudit);
		}
	}

	/**
	 * Returns the number of asset audits where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETAUDIT_WHERE);

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
	 * Returns the number of asset audits where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId, classPK, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETAUDIT_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset audits where createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_C_T(Date createDate, long classNameId, long classPK,
		int type) throws SystemException {
		Object[] finderArgs = new Object[] {
				createDate, classNameId, classPK, type
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETAUDIT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_C_C_C_T_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_C_C_C_T_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_C_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_C_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset audits.
	 *
	 * @return the number of asset audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETAUDIT);

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
	 * Initializes the asset audit persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetAudit")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetAudit>> listenersList = new ArrayList<ModelListener<AssetAudit>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetAudit>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetAuditImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETAUDIT = "SELECT assetAudit FROM AssetAudit assetAudit";
	private static final String _SQL_SELECT_ASSETAUDIT_WHERE = "SELECT assetAudit FROM AssetAudit assetAudit WHERE ";
	private static final String _SQL_COUNT_ASSETAUDIT = "SELECT COUNT(assetAudit) FROM AssetAudit assetAudit";
	private static final String _SQL_COUNT_ASSETAUDIT_WHERE = "SELECT COUNT(assetAudit) FROM AssetAudit assetAudit WHERE ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetAudit.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetAudit.classPK = ?";
	private static final String _FINDER_COLUMN_U_C_C_T_USERID_2 = "assetAudit.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2 = "assetAudit.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_CLASSPK_2 = "assetAudit.classPK = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_TYPE_2 = "assetAudit.type = ?";
	private static final String _FINDER_COLUMN_C_C_C_T_CREATEDATE_1 = "assetAudit.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_C_C_C_T_CREATEDATE_2 = "assetAudit.createDate = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_T_CLASSNAMEID_2 = "assetAudit.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_T_CLASSPK_2 = "assetAudit.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_T_TYPE_2 = "assetAudit.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetAudit exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetAudit exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetAuditPersistenceImpl.class);
	private static AssetAudit _nullAssetAudit = new AssetAuditImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetAudit> toCacheModel() {
				return _nullAssetAuditCacheModel;
			}
		};

	private static CacheModel<AssetAudit> _nullAssetAuditCacheModel = new CacheModel<AssetAudit>() {
			public AssetAudit toEntityModel() {
				return _nullAssetAudit;
			}
		};
}
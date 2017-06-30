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

import com.liferay.osb.NoSuchLicenseEntryException;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.impl.LicenseEntryImpl;
import com.liferay.osb.model.impl.LicenseEntryModelImpl;

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
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the license entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryPersistence
 * @see LicenseEntryUtil
 * @generated
 */
public class LicenseEntryPersistenceImpl extends BasePersistenceImpl<LicenseEntry>
	implements LicenseEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LicenseEntryUtil} to access the license entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LicenseEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductEntryId",
			new String[] { Long.class.getName() },
			LicenseEntryModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRODUCTENTRYID = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { String.class.getName() },
			LicenseEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_PEI_T = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPEI_T",
			new String[] { Long.class.getName(), String.class.getName() },
			LicenseEntryModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			LicenseEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_T = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEI_T",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_PV = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPEI_PV",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_PV = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPEI_PV",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the license entry in the entity cache if it is enabled.
	 *
	 * @param licenseEntry the license entry
	 */
	public void cacheResult(LicenseEntry licenseEntry) {
		EntityCacheUtil.putResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryImpl.class, licenseEntry.getPrimaryKey(), licenseEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_T,
			new Object[] {
				Long.valueOf(licenseEntry.getProductEntryId()),
				
			licenseEntry.getType()
			}, licenseEntry);

		licenseEntry.resetOriginalValues();
	}

	/**
	 * Caches the license entries in the entity cache if it is enabled.
	 *
	 * @param licenseEntries the license entries
	 */
	public void cacheResult(List<LicenseEntry> licenseEntries) {
		for (LicenseEntry licenseEntry : licenseEntries) {
			if (EntityCacheUtil.getResult(
						LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
						LicenseEntryImpl.class, licenseEntry.getPrimaryKey()) == null) {
				cacheResult(licenseEntry);
			}
			else {
				licenseEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LicenseEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LicenseEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseEntry licenseEntry) {
		EntityCacheUtil.removeResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryImpl.class, licenseEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(licenseEntry);
	}

	@Override
	public void clearCache(List<LicenseEntry> licenseEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseEntry licenseEntry : licenseEntries) {
			EntityCacheUtil.removeResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
				LicenseEntryImpl.class, licenseEntry.getPrimaryKey());

			clearUniqueFindersCache(licenseEntry);
		}
	}

	protected void cacheUniqueFindersCache(LicenseEntry licenseEntry) {
		if (licenseEntry.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(licenseEntry.getProductEntryId()),
					
					licenseEntry.getType()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_T, args,
				licenseEntry);
		}
		else {
			LicenseEntryModelImpl licenseEntryModelImpl = (LicenseEntryModelImpl)licenseEntry;

			if ((licenseEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_PEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseEntry.getProductEntryId()),
						
						licenseEntry.getType()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_T, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_T, args,
					licenseEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(LicenseEntry licenseEntry) {
		LicenseEntryModelImpl licenseEntryModelImpl = (LicenseEntryModelImpl)licenseEntry;

		Object[] args = new Object[] {
				Long.valueOf(licenseEntry.getProductEntryId()),
				
				licenseEntry.getType()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PEI_T, args);

		if ((licenseEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PEI_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(licenseEntryModelImpl.getOriginalProductEntryId()),
					
					licenseEntryModelImpl.getOriginalType()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PEI_T, args);
		}
	}

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	public LicenseEntry create(long licenseEntryId) {
		LicenseEntry licenseEntry = new LicenseEntryImpl();

		licenseEntry.setNew(true);
		licenseEntry.setPrimaryKey(licenseEntryId);

		return licenseEntry;
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry remove(long licenseEntryId)
		throws NoSuchLicenseEntryException, SystemException {
		return remove(Long.valueOf(licenseEntryId));
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseEntry remove(Serializable primaryKey)
		throws NoSuchLicenseEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			LicenseEntry licenseEntry = (LicenseEntry)session.get(LicenseEntryImpl.class,
					primaryKey);

			if (licenseEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(licenseEntry);
		}
		catch (NoSuchLicenseEntryException nsee) {
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
	protected LicenseEntry removeImpl(LicenseEntry licenseEntry)
		throws SystemException {
		licenseEntry = toUnwrappedModel(licenseEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, licenseEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(licenseEntry);

		return licenseEntry;
	}

	@Override
	public LicenseEntry updateImpl(
		com.liferay.osb.model.LicenseEntry licenseEntry, boolean merge)
		throws SystemException {
		licenseEntry = toUnwrappedModel(licenseEntry);

		boolean isNew = licenseEntry.isNew();

		LicenseEntryModelImpl licenseEntryModelImpl = (LicenseEntryModelImpl)licenseEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, licenseEntry, merge);

			licenseEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LicenseEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((licenseEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseEntryModelImpl.getOriginalProductEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(licenseEntryModelImpl.getProductEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);
			}

			if ((licenseEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseEntryModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] { licenseEntryModelImpl.getType() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}
		}

		EntityCacheUtil.putResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
			LicenseEntryImpl.class, licenseEntry.getPrimaryKey(), licenseEntry);

		clearUniqueFindersCache(licenseEntry);
		cacheUniqueFindersCache(licenseEntry);

		return licenseEntry;
	}

	protected LicenseEntry toUnwrappedModel(LicenseEntry licenseEntry) {
		if (licenseEntry instanceof LicenseEntryImpl) {
			return licenseEntry;
		}

		LicenseEntryImpl licenseEntryImpl = new LicenseEntryImpl();

		licenseEntryImpl.setNew(licenseEntry.isNew());
		licenseEntryImpl.setPrimaryKey(licenseEntry.getPrimaryKey());

		licenseEntryImpl.setLicenseEntryId(licenseEntry.getLicenseEntryId());
		licenseEntryImpl.setUserId(licenseEntry.getUserId());
		licenseEntryImpl.setUserName(licenseEntry.getUserName());
		licenseEntryImpl.setCreateDate(licenseEntry.getCreateDate());
		licenseEntryImpl.setModifiedDate(licenseEntry.getModifiedDate());
		licenseEntryImpl.setProductEntryId(licenseEntry.getProductEntryId());
		licenseEntryImpl.setName(licenseEntry.getName());
		licenseEntryImpl.setType(licenseEntry.getType());
		licenseEntryImpl.setPortalVersionMin(licenseEntry.getPortalVersionMin());
		licenseEntryImpl.setPortalVersionMax(licenseEntry.getPortalVersionMax());

		return licenseEntryImpl;
	}

	/**
	 * Returns the license entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry
	 * @throws com.liferay.portal.NoSuchModelException if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the license entry with the primary key or throws a {@link com.liferay.osb.NoSuchLicenseEntryException} if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByPrimaryKey(licenseEntryId);

		if (licenseEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + licenseEntryId);
			}

			throw new NoSuchLicenseEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				licenseEntryId);
		}

		return licenseEntry;
	}

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByPrimaryKey(long licenseEntryId)
		throws SystemException {
		LicenseEntry licenseEntry = (LicenseEntry)EntityCacheUtil.getResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
				LicenseEntryImpl.class, licenseEntryId);

		if (licenseEntry == _nullLicenseEntry) {
			return null;
		}

		if (licenseEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				licenseEntry = (LicenseEntry)session.get(LicenseEntryImpl.class,
						Long.valueOf(licenseEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (licenseEntry != null) {
					cacheResult(licenseEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(LicenseEntryModelImpl.ENTITY_CACHE_ENABLED,
						LicenseEntryImpl.class, licenseEntryId,
						_nullLicenseEntry);
				}

				closeSession(session);
			}
		}

		return licenseEntry;
	}

	/**
	 * Returns all the license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByProductEntryId(long productEntryId)
		throws SystemException {
		return findByProductEntryId(productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end) throws SystemException {
		return findByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] { productEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] {
					productEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseEntry> list = (List<LicenseEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseEntry licenseEntry : list) {
				if ((productEntryId != licenseEntry.getProductEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByProductEntryId_First(long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByProductEntryId_First(productEntryId,
				orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByProductEntryId_First(long productEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseEntry> list = findByProductEntryId(productEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByProductEntryId_Last(long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByProductEntryId_Last(productEntryId,
				orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByProductEntryId_Last(long productEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByProductEntryId(productEntryId);

		List<LicenseEntry> list = findByProductEntryId(productEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry[] findByProductEntryId_PrevAndNext(
		long licenseEntryId, long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByProductEntryId_PrevAndNext(session, licenseEntry,
					productEntryId, orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByProductEntryId_PrevAndNext(session, licenseEntry,
					productEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseEntry getByProductEntryId_PrevAndNext(Session session,
		LicenseEntry licenseEntry, long productEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

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
			query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license entries where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByType(String type) throws SystemException {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByType(String type, int start, int end)
		throws SystemException {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByType(String type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<LicenseEntry> list = (List<LicenseEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseEntry licenseEntry : list) {
				if (!Validator.equals(type, licenseEntry.getType())) {
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

			query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TYPE_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByType_First(String type,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByType_First(type, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByType_First(String type,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseEntry> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByType_Last(String type,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByType_Last(type, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByType_Last(String type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByType(type);

		List<LicenseEntry> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where type = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry[] findByType_PrevAndNext(long licenseEntryId,
		String type, OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByType_PrevAndNext(session, licenseEntry, type,
					orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByType_PrevAndNext(session, licenseEntry, type,
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

	protected LicenseEntry getByType_PrevAndNext(Session session,
		LicenseEntry licenseEntry, String type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		if (type == null) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_1);
		}
		else {
			if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
			query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (type != null) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchLicenseEntryException} if it could not be found.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByPEI_T(long productEntryId, String type)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByPEI_T(productEntryId, type);

		if (licenseEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("productEntryId=");
			msg.append(productEntryId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchLicenseEntryException(msg.toString());
		}

		return licenseEntry;
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByPEI_T(long productEntryId, String type)
		throws SystemException {
		return fetchByPEI_T(productEntryId, type, true);
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByPEI_T(long productEntryId, String type,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { productEntryId, type };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PEI_T,
					finderArgs, this);
		}

		if (result instanceof LicenseEntry) {
			LicenseEntry licenseEntry = (LicenseEntry)result;

			if ((productEntryId != licenseEntry.getProductEntryId()) ||
					!Validator.equals(type, licenseEntry.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_T_PRODUCTENTRYID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_PEI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEI_T_TYPE_2);
				}
			}

			query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				if (type != null) {
					qPos.add(type);
				}

				List<LicenseEntry> list = q.list();

				result = list;

				LicenseEntry licenseEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_T,
						finderArgs, list);
				}
				else {
					licenseEntry = list.get(0);

					cacheResult(licenseEntry);

					if ((licenseEntry.getProductEntryId() != productEntryId) ||
							(licenseEntry.getType() == null) ||
							!licenseEntry.getType().equals(type)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_T,
							finderArgs, licenseEntry);
					}
				}

				return licenseEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PEI_T,
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
				return (LicenseEntry)result;
			}
		}
	}

	/**
	 * Returns all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @return the matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin) throws SystemException {
		return findByPEI_PV(productEntryId, portalVersionMin,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end) throws SystemException {
		return findByPEI_PV(productEntryId, portalVersionMin, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_PV;
		finderArgs = new Object[] {
				productEntryId, portalVersionMin,
				
				start, end, orderByComparator
			};

		List<LicenseEntry> list = (List<LicenseEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseEntry licenseEntry : list) {
				if ((productEntryId != licenseEntry.getProductEntryId()) ||
						(portalVersionMin != licenseEntry.getPortalVersionMin())) {
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

			query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(portalVersionMin);

				list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByPEI_PV_First(long productEntryId,
		int portalVersionMin, OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByPEI_PV_First(productEntryId,
				portalVersionMin, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", portalVersionMin=");
		msg.append(portalVersionMin);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByPEI_PV_First(long productEntryId,
		int portalVersionMin, OrderByComparator orderByComparator)
		throws SystemException {
		List<LicenseEntry> list = findByPEI_PV(productEntryId,
				portalVersionMin, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry findByPEI_PV_Last(long productEntryId,
		int portalVersionMin, OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = fetchByPEI_PV_Last(productEntryId,
				portalVersionMin, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", portalVersionMin=");
		msg.append(portalVersionMin);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseEntryException(msg.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry fetchByPEI_PV_Last(long productEntryId,
		int portalVersionMin, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPEI_PV(productEntryId, portalVersionMin);

		List<LicenseEntry> list = findByPEI_PV(productEntryId,
				portalVersionMin, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws com.liferay.osb.NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry[] findByPEI_PV_PrevAndNext(long licenseEntryId,
		long productEntryId, int portalVersionMin,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByPEI_PV_PrevAndNext(session, licenseEntry,
					productEntryId, portalVersionMin, orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByPEI_PV_PrevAndNext(session, licenseEntry,
					productEntryId, portalVersionMin, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseEntry getByPEI_PV_PrevAndNext(Session session,
		LicenseEntry licenseEntry, long productEntryId, int portalVersionMin,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		query.append(_FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2);

		query.append(_FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2);

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
			query.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		qPos.add(portalVersionMin);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license entries.
	 *
	 * @return the license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseEntry> findAll(int start, int end,
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

		List<LicenseEntry> list = (List<LicenseEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LICENSEENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEENTRY.concat(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<LicenseEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the license entries where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByProductEntryId(long productEntryId)
		throws SystemException {
		for (LicenseEntry licenseEntry : findByProductEntryId(productEntryId)) {
			remove(licenseEntry);
		}
	}

	/**
	 * Removes all the license entries where type = &#63; from the database.
	 *
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByType(String type) throws SystemException {
		for (LicenseEntry licenseEntry : findByType(type)) {
			remove(licenseEntry);
		}
	}

	/**
	 * Removes the license entry where productEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the license entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseEntry removeByPEI_T(long productEntryId, String type)
		throws NoSuchLicenseEntryException, SystemException {
		LicenseEntry licenseEntry = findByPEI_T(productEntryId, type);

		return remove(licenseEntry);
	}

	/**
	 * Removes all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPEI_PV(long productEntryId, int portalVersionMin)
		throws SystemException {
		for (LicenseEntry licenseEntry : findByPEI_PV(productEntryId,
				portalVersionMin)) {
			remove(licenseEntry);
		}
	}

	/**
	 * Removes all the license entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (LicenseEntry licenseEntry : findAll()) {
			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByProductEntryId(long productEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { productEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license entries where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(String type) throws SystemException {
		Object[] finderArgs = new Object[] { type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TYPE_TYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63; and type = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the number of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEI_T(long productEntryId, String type)
		throws SystemException {
		Object[] finderArgs = new Object[] { productEntryId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PEI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_T_PRODUCTENTRYID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_PEI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEI_T_TYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				if (type != null) {
					qPos.add(type);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @return the number of matching license entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEI_PV(long productEntryId, int portalVersionMin)
		throws SystemException {
		Object[] finderArgs = new Object[] { productEntryId, portalVersionMin };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_PV,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			query.append(_FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(portalVersionMin);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_PV,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LICENSEENTRY);

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
	 * Initializes the license entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.LicenseEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<LicenseEntry>> listenersList = new ArrayList<ModelListener<LicenseEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<LicenseEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(LicenseEntryImpl.class.getName());
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
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_LICENSEENTRY = "SELECT licenseEntry FROM LicenseEntry licenseEntry";
	private static final String _SQL_SELECT_LICENSEENTRY_WHERE = "SELECT licenseEntry FROM LicenseEntry licenseEntry WHERE ";
	private static final String _SQL_COUNT_LICENSEENTRY = "SELECT COUNT(licenseEntry) FROM LicenseEntry licenseEntry";
	private static final String _SQL_COUNT_LICENSEENTRY_WHERE = "SELECT COUNT(licenseEntry) FROM LicenseEntry licenseEntry WHERE ";
	private static final String _FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2 = "licenseEntry.productEntryId = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_1 = "licenseEntry.type IS NULL";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "licenseEntry.type = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_3 = "(licenseEntry.type IS NULL OR licenseEntry.type = ?)";
	private static final String _FINDER_COLUMN_PEI_T_PRODUCTENTRYID_2 = "licenseEntry.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_T_TYPE_1 = "licenseEntry.type IS NULL";
	private static final String _FINDER_COLUMN_PEI_T_TYPE_2 = "licenseEntry.type = ?";
	private static final String _FINDER_COLUMN_PEI_T_TYPE_3 = "(licenseEntry.type IS NULL OR licenseEntry.type = ?)";
	private static final String _FINDER_COLUMN_PEI_PV_PRODUCTENTRYID_2 = "licenseEntry.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_PV_PORTALVERSIONMIN_2 = "licenseEntry.portalVersionMin <= ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LicenseEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LicenseEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(LicenseEntryPersistenceImpl.class);
	private static LicenseEntry _nullLicenseEntry = new LicenseEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<LicenseEntry> toCacheModel() {
				return _nullLicenseEntryCacheModel;
			}
		};

	private static CacheModel<LicenseEntry> _nullLicenseEntryCacheModel = new CacheModel<LicenseEntry>() {
			public LicenseEntry toEntityModel() {
				return _nullLicenseEntry;
			}
		};
}
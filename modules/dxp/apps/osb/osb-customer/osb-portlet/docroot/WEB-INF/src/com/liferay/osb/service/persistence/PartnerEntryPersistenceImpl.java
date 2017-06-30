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

import com.liferay.osb.NoSuchPartnerEntryException;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.impl.PartnerEntryImpl;
import com.liferay.osb.model.impl.PartnerEntryModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.AddressPersistence;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the partner entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryPersistence
 * @see PartnerEntryUtil
 * @generated
 */
public class PartnerEntryPersistenceImpl extends BasePersistenceImpl<PartnerEntry>
	implements PartnerEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PartnerEntryUtil} to access the partner entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PartnerEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPARTNERENTRYID =
		new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentPartnerEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID =
		new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentPartnerEntryId",
			new String[] { Long.class.getName() },
			PartnerEntryModelImpl.PARENTPARTNERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentPartnerEntryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDossieraAccountKey",
			new String[] { String.class.getName() },
			PartnerEntryModelImpl.DOSSIERAACCOUNTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDossieraAccountKey", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCode",
			new String[] { String.class.getName() },
			PartnerEntryModelImpl.CODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCode",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the partner entry in the entity cache if it is enabled.
	 *
	 * @param partnerEntry the partner entry
	 */
	public void cacheResult(PartnerEntry partnerEntry) {
		EntityCacheUtil.putResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryImpl.class, partnerEntry.getPrimaryKey(), partnerEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
			new Object[] { partnerEntry.getDossieraAccountKey() }, partnerEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { partnerEntry.getCode() }, partnerEntry);

		partnerEntry.resetOriginalValues();
	}

	/**
	 * Caches the partner entries in the entity cache if it is enabled.
	 *
	 * @param partnerEntries the partner entries
	 */
	public void cacheResult(List<PartnerEntry> partnerEntries) {
		for (PartnerEntry partnerEntry : partnerEntries) {
			if (EntityCacheUtil.getResult(
						PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
						PartnerEntryImpl.class, partnerEntry.getPrimaryKey()) == null) {
				cacheResult(partnerEntry);
			}
			else {
				partnerEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all partner entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PartnerEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PartnerEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the partner entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PartnerEntry partnerEntry) {
		EntityCacheUtil.removeResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryImpl.class, partnerEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(partnerEntry);
	}

	@Override
	public void clearCache(List<PartnerEntry> partnerEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PartnerEntry partnerEntry : partnerEntries) {
			EntityCacheUtil.removeResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
				PartnerEntryImpl.class, partnerEntry.getPrimaryKey());

			clearUniqueFindersCache(partnerEntry);
		}
	}

	protected void cacheUniqueFindersCache(PartnerEntry partnerEntry) {
		if (partnerEntry.isNew()) {
			Object[] args = new Object[] { partnerEntry.getDossieraAccountKey() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args, partnerEntry);

			args = new Object[] { partnerEntry.getCode() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args,
				partnerEntry);
		}
		else {
			PartnerEntryModelImpl partnerEntryModelImpl = (PartnerEntryModelImpl)partnerEntry;

			if ((partnerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						partnerEntry.getDossieraAccountKey()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					args, partnerEntry);
			}

			if ((partnerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { partnerEntry.getCode() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args,
					partnerEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(PartnerEntry partnerEntry) {
		PartnerEntryModelImpl partnerEntryModelImpl = (PartnerEntryModelImpl)partnerEntry;

		Object[] args = new Object[] { partnerEntry.getDossieraAccountKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
			args);

		if ((partnerEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					partnerEntryModelImpl.getOriginalDossieraAccountKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args);
		}

		args = new Object[] { partnerEntry.getCode() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);

		if ((partnerEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
			args = new Object[] { partnerEntryModelImpl.getOriginalCode() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
		}
	}

	/**
	 * Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	 *
	 * @param partnerEntryId the primary key for the new partner entry
	 * @return the new partner entry
	 */
	public PartnerEntry create(long partnerEntryId) {
		PartnerEntry partnerEntry = new PartnerEntryImpl();

		partnerEntry.setNew(true);
		partnerEntry.setPrimaryKey(partnerEntryId);

		return partnerEntry;
	}

	/**
	 * Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param partnerEntryId the primary key of the partner entry
	 * @return the partner entry that was removed
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry remove(long partnerEntryId)
		throws NoSuchPartnerEntryException, SystemException {
		return remove(Long.valueOf(partnerEntryId));
	}

	/**
	 * Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the partner entry
	 * @return the partner entry that was removed
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PartnerEntry remove(Serializable primaryKey)
		throws NoSuchPartnerEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PartnerEntry partnerEntry = (PartnerEntry)session.get(PartnerEntryImpl.class,
					primaryKey);

			if (partnerEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPartnerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(partnerEntry);
		}
		catch (NoSuchPartnerEntryException nsee) {
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
	protected PartnerEntry removeImpl(PartnerEntry partnerEntry)
		throws SystemException {
		partnerEntry = toUnwrappedModel(partnerEntry);

		try {
			clearSupportRegions.clear(partnerEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, partnerEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(partnerEntry);

		return partnerEntry;
	}

	@Override
	public PartnerEntry updateImpl(
		com.liferay.osb.model.PartnerEntry partnerEntry, boolean merge)
		throws SystemException {
		partnerEntry = toUnwrappedModel(partnerEntry);

		boolean isNew = partnerEntry.isNew();

		PartnerEntryModelImpl partnerEntryModelImpl = (PartnerEntryModelImpl)partnerEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, partnerEntry, merge);

			partnerEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PartnerEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((partnerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(partnerEntryModelImpl.getOriginalParentPartnerEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(partnerEntryModelImpl.getParentPartnerEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryImpl.class, partnerEntry.getPrimaryKey(), partnerEntry);

		clearUniqueFindersCache(partnerEntry);
		cacheUniqueFindersCache(partnerEntry);

		return partnerEntry;
	}

	protected PartnerEntry toUnwrappedModel(PartnerEntry partnerEntry) {
		if (partnerEntry instanceof PartnerEntryImpl) {
			return partnerEntry;
		}

		PartnerEntryImpl partnerEntryImpl = new PartnerEntryImpl();

		partnerEntryImpl.setNew(partnerEntry.isNew());
		partnerEntryImpl.setPrimaryKey(partnerEntry.getPrimaryKey());

		partnerEntryImpl.setPartnerEntryId(partnerEntry.getPartnerEntryId());
		partnerEntryImpl.setUserId(partnerEntry.getUserId());
		partnerEntryImpl.setUserName(partnerEntry.getUserName());
		partnerEntryImpl.setCreateDate(partnerEntry.getCreateDate());
		partnerEntryImpl.setModifiedUserId(partnerEntry.getModifiedUserId());
		partnerEntryImpl.setModifiedUserName(partnerEntry.getModifiedUserName());
		partnerEntryImpl.setModifiedDate(partnerEntry.getModifiedDate());
		partnerEntryImpl.setParentPartnerEntryId(partnerEntry.getParentPartnerEntryId());
		partnerEntryImpl.setDossieraAccountKey(partnerEntry.getDossieraAccountKey());
		partnerEntryImpl.setCode(partnerEntry.getCode());
		partnerEntryImpl.setNotes(partnerEntry.getNotes());
		partnerEntryImpl.setStatus(partnerEntry.getStatus());

		return partnerEntryImpl;
	}

	/**
	 * Returns the partner entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner entry
	 * @return the partner entry
	 * @throws com.liferay.portal.NoSuchModelException if a partner entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PartnerEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the partner entry with the primary key or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	 *
	 * @param partnerEntryId the primary key of the partner entry
	 * @return the partner entry
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry findByPrimaryKey(long partnerEntryId)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = fetchByPrimaryKey(partnerEntryId);

		if (partnerEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + partnerEntryId);
			}

			throw new NoSuchPartnerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				partnerEntryId);
		}

		return partnerEntry;
	}

	/**
	 * Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner entry
	 * @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PartnerEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param partnerEntryId the primary key of the partner entry
	 * @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry fetchByPrimaryKey(long partnerEntryId)
		throws SystemException {
		PartnerEntry partnerEntry = (PartnerEntry)EntityCacheUtil.getResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
				PartnerEntryImpl.class, partnerEntryId);

		if (partnerEntry == _nullPartnerEntry) {
			return null;
		}

		if (partnerEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				partnerEntry = (PartnerEntry)session.get(PartnerEntryImpl.class,
						Long.valueOf(partnerEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (partnerEntry != null) {
					cacheResult(partnerEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
						PartnerEntryImpl.class, partnerEntryId,
						_nullPartnerEntry);
				}

				closeSession(session);
			}
		}

		return partnerEntry;
	}

	/**
	 * Returns all the partner entries where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @return the matching partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId) throws SystemException {
		return findByParentPartnerEntryId(parentPartnerEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner entries where parentPartnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @return the range of matching partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end)
		throws SystemException {
		return findByParentPartnerEntryId(parentPartnerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID;
			finderArgs = new Object[] { parentPartnerEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPARTNERENTRYID;
			finderArgs = new Object[] {
					parentPartnerEntryId,
					
					start, end, orderByComparator
				};
		}

		List<PartnerEntry> list = (List<PartnerEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PartnerEntry partnerEntry : list) {
				if ((parentPartnerEntryId != partnerEntry.getParentPartnerEntryId())) {
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

			query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PartnerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPartnerEntryId);

				list = (List<PartnerEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner entry
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry findByParentPartnerEntryId_First(
		long parentPartnerEntryId, OrderByComparator orderByComparator)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = fetchByParentPartnerEntryId_First(parentPartnerEntryId,
				orderByComparator);

		if (partnerEntry != null) {
			return partnerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPartnerEntryId=");
		msg.append(parentPartnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerEntryException(msg.toString());
	}

	/**
	 * Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry fetchByParentPartnerEntryId_First(
		long parentPartnerEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		List<PartnerEntry> list = findByParentPartnerEntryId(parentPartnerEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner entry
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry findByParentPartnerEntryId_Last(
		long parentPartnerEntryId, OrderByComparator orderByComparator)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = fetchByParentPartnerEntryId_Last(parentPartnerEntryId,
				orderByComparator);

		if (partnerEntry != null) {
			return partnerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPartnerEntryId=");
		msg.append(parentPartnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerEntryException(msg.toString());
	}

	/**
	 * Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry fetchByParentPartnerEntryId_Last(
		long parentPartnerEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByParentPartnerEntryId(parentPartnerEntryId);

		List<PartnerEntry> list = findByParentPartnerEntryId(parentPartnerEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner entries before and after the current partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the primary key of the current partner entry
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner entry
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry[] findByParentPartnerEntryId_PrevAndNext(
		long partnerEntryId, long parentPartnerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = findByPrimaryKey(partnerEntryId);

		Session session = null;

		try {
			session = openSession();

			PartnerEntry[] array = new PartnerEntryImpl[3];

			array[0] = getByParentPartnerEntryId_PrevAndNext(session,
					partnerEntry, parentPartnerEntryId, orderByComparator, true);

			array[1] = partnerEntry;

			array[2] = getByParentPartnerEntryId_PrevAndNext(session,
					partnerEntry, parentPartnerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerEntry getByParentPartnerEntryId_PrevAndNext(
		Session session, PartnerEntry partnerEntry, long parentPartnerEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

		query.append(_FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2);

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
			query.append(PartnerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentPartnerEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the partner entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching partner entry
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry findByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = fetchByDossieraAccountKey(dossieraAccountKey);

		if (partnerEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossieraAccountKey=");
			msg.append(dossieraAccountKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPartnerEntryException(msg.toString());
		}

		return partnerEntry;
	}

	/**
	 * Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry fetchByDossieraAccountKey(String dossieraAccountKey)
		throws SystemException {
		return fetchByDossieraAccountKey(dossieraAccountKey, true);
	}

	/**
	 * Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry fetchByDossieraAccountKey(String dossieraAccountKey,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					finderArgs, this);
		}

		if (result instanceof PartnerEntry) {
			PartnerEntry partnerEntry = (PartnerEntry)result;

			if (!Validator.equals(dossieraAccountKey,
						partnerEntry.getDossieraAccountKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

			if (dossieraAccountKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1);
			}
			else {
				if (dossieraAccountKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2);
				}
			}

			query.append(PartnerEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (dossieraAccountKey != null) {
					qPos.add(dossieraAccountKey);
				}

				List<PartnerEntry> list = q.list();

				result = list;

				PartnerEntry partnerEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
						finderArgs, list);
				}
				else {
					partnerEntry = list.get(0);

					cacheResult(partnerEntry);

					if ((partnerEntry.getDossieraAccountKey() == null) ||
							!partnerEntry.getDossieraAccountKey()
											 .equals(dossieraAccountKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
							finderArgs, partnerEntry);
					}
				}

				return partnerEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
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
				return (PartnerEntry)result;
			}
		}
	}

	/**
	 * Returns the partner entry where code = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	 *
	 * @param code the code
	 * @return the matching partner entry
	 * @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry findByCode(String code)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = fetchByCode(code);

		if (partnerEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPartnerEntryException(msg.toString());
		}

		return partnerEntry;
	}

	/**
	 * Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry fetchByCode(String code) throws SystemException {
		return fetchByCode(code, true);
	}

	/**
	 * Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry fetchByCode(String code, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CODE,
					finderArgs, this);
		}

		if (result instanceof PartnerEntry) {
			PartnerEntry partnerEntry = (PartnerEntry)result;

			if (!Validator.equals(code, partnerEntry.getCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CODE_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CODE_CODE_2);
				}
			}

			query.append(PartnerEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (code != null) {
					qPos.add(code);
				}

				List<PartnerEntry> list = q.list();

				result = list;

				PartnerEntry partnerEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
						finderArgs, list);
				}
				else {
					partnerEntry = list.get(0);

					cacheResult(partnerEntry);

					if ((partnerEntry.getCode() == null) ||
							!partnerEntry.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
							finderArgs, partnerEntry);
					}
				}

				return partnerEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
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
				return (PartnerEntry)result;
			}
		}
	}

	/**
	 * Returns all the partner entries.
	 *
	 * @return the partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @return the range of partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerEntry> findAll(int start, int end,
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

		List<PartnerEntry> list = (List<PartnerEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PARTNERENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PARTNERENTRY.concat(PartnerEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PartnerEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PartnerEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the partner entries where parentPartnerEntryId = &#63; from the database.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByParentPartnerEntryId(long parentPartnerEntryId)
		throws SystemException {
		for (PartnerEntry partnerEntry : findByParentPartnerEntryId(
				parentPartnerEntryId)) {
			remove(partnerEntry);
		}
	}

	/**
	 * Removes the partner entry where dossieraAccountKey = &#63; from the database.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the partner entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry removeByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = findByDossieraAccountKey(dossieraAccountKey);

		return remove(partnerEntry);
	}

	/**
	 * Removes the partner entry where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the partner entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerEntry removeByCode(String code)
		throws NoSuchPartnerEntryException, SystemException {
		PartnerEntry partnerEntry = findByCode(code);

		return remove(partnerEntry);
	}

	/**
	 * Removes all the partner entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PartnerEntry partnerEntry : findAll()) {
			remove(partnerEntry);
		}
	}

	/**
	 * Returns the number of partner entries where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @return the number of matching partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByParentPartnerEntryId(long parentPartnerEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentPartnerEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPartnerEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the number of matching partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDossieraAccountKey(String dossieraAccountKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERENTRY_WHERE);

			if (dossieraAccountKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1);
			}
			else {
				if (dossieraAccountKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (dossieraAccountKey != null) {
					qPos.add(dossieraAccountKey);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner entries where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCode(String code) throws SystemException {
		Object[] finderArgs = new Object[] { code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CODE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERENTRY_WHERE);

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CODE_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CODE_CODE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (code != null) {
					qPos.add(code);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner entries.
	 *
	 * @return the number of partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PARTNERENTRY);

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
	 * Returns all the support regions associated with the partner entry.
	 *
	 * @param pk the primary key of the partner entry
	 * @return the support regions associated with the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(long pk)
		throws SystemException {
		return getSupportRegions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support regions associated with the partner entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the partner entry
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @return the range of support regions associated with the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) throws SystemException {
		return getSupportRegions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTREGIONS = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED_OSB_PARTNERENTRIES_SUPPORTREGIONS,
			com.liferay.osb.model.impl.SupportRegionImpl.class,
			PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME,
			"getSupportRegions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_SUPPORTREGIONS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the support regions associated with the partner entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the partner entry
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions associated with the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.SupportRegion> list = (List<com.liferay.osb.model.SupportRegion>)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTREGIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETSUPPORTREGIONS.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETSUPPORTREGIONS.concat(com.liferay.osb.model.impl.SupportRegionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_SupportRegion",
					com.liferay.osb.model.impl.SupportRegionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.SupportRegion>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_SUPPORTREGIONS,
						finderArgs);
				}
				else {
					supportRegionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTREGIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTREGIONS_SIZE = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED_OSB_PARTNERENTRIES_SUPPORTREGIONS,
			Long.class,
			PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME,
			"getSupportRegionsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_SUPPORTREGIONS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of support regions associated with the partner entry.
	 *
	 * @param pk the primary key of the partner entry
	 * @return the number of support regions associated with the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public int getSupportRegionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTREGIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSUPPORTREGIONSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTREGIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_SUPPORTREGION = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED_OSB_PARTNERENTRIES_SUPPORTREGIONS,
			Boolean.class,
			PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME,
			"containsSupportRegion",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the support region is associated with the partner entry.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPK the primary key of the support region
	 * @return <code>true</code> if the support region is associated with the partner entry; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, supportRegionPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_SUPPORTREGION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsSupportRegion.contains(pk,
							supportRegionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_SUPPORTREGION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the partner entry has any support regions associated with it.
	 *
	 * @param pk the primary key of the partner entry to check for associations with support regions
	 * @return <code>true</code> if the partner entry has any support regions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportRegions(long pk) throws SystemException {
		if (getSupportRegionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPK the primary key of the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		try {
			addSupportRegion.add(pk, supportRegionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegion the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws SystemException {
		try {
			addSupportRegion.add(pk, supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPKs the primary keys of the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			for (long supportRegionPK : supportRegionPKs) {
				addSupportRegion.add(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegions the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				addSupportRegion.add(pk, supportRegion.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the partner entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry to clear the associated support regions from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearSupportRegions(long pk) throws SystemException {
		try {
			clearSupportRegions.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPK the primary key of the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		try {
			removeSupportRegion.remove(pk, supportRegionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegion the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws SystemException {
		try {
			removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPKs the primary keys of the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			for (long supportRegionPK : supportRegionPKs) {
				removeSupportRegion.remove(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegions the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPKs the primary keys of the support regions to be associated with the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			Set<Long> supportRegionPKSet = SetUtil.fromArray(supportRegionPKs);

			List<com.liferay.osb.model.SupportRegion> supportRegions = getSupportRegions(pk);

			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				if (!supportRegionPKSet.remove(supportRegion.getPrimaryKey())) {
					removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
				}
			}

			for (Long supportRegionPK : supportRegionPKSet) {
				addSupportRegion.add(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegions the support regions to be associated with the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			long[] supportRegionPKs = new long[supportRegions.size()];

			for (int i = 0; i < supportRegions.size(); i++) {
				com.liferay.osb.model.SupportRegion supportRegion = supportRegions.get(i);

				supportRegionPKs[i] = supportRegion.getPrimaryKey();
			}

			setSupportRegions(pk, supportRegionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PartnerEntryModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Initializes the partner entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.PartnerEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PartnerEntry>> listenersList = new ArrayList<ModelListener<PartnerEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<PartnerEntry>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsSupportRegion = new ContainsSupportRegion();

		addSupportRegion = new AddSupportRegion();
		clearSupportRegions = new ClearSupportRegions();
		removeSupportRegion = new RemoveSupportRegion();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PartnerEntryImpl.class.getName());
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
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected ContainsSupportRegion containsSupportRegion;
	protected AddSupportRegion addSupportRegion;
	protected ClearSupportRegions clearSupportRegions;
	protected RemoveSupportRegion removeSupportRegion;

	protected class ContainsSupportRegion {
		protected ContainsSupportRegion() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSUPPORTREGION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long partnerEntryId, long supportRegionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(partnerEntryId), new Long(supportRegionId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddSupportRegion {
		protected AddSupportRegion() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_PartnerEntries_SupportRegions (partnerEntryId, supportRegionId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long partnerEntryId, long supportRegionId)
			throws SystemException {
			if (!containsSupportRegion.contains(partnerEntryId, supportRegionId)) {
				ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
					supportRegionPersistence.getListeners();

				for (ModelListener<PartnerEntry> listener : listeners) {
					listener.onBeforeAddAssociation(partnerEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onBeforeAddAssociation(supportRegionId,
						PartnerEntry.class.getName(), partnerEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(partnerEntryId), new Long(supportRegionId)
					});

				for (ModelListener<PartnerEntry> listener : listeners) {
					listener.onAfterAddAssociation(partnerEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onAfterAddAssociation(supportRegionId,
						PartnerEntry.class.getName(), partnerEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearSupportRegions {
		protected ClearSupportRegions() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_PartnerEntries_SupportRegions WHERE partnerEntryId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long partnerEntryId) throws SystemException {
			ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
				supportRegionPersistence.getListeners();

			List<com.liferay.osb.model.SupportRegion> supportRegions = null;

			if ((listeners.length > 0) || (supportRegionListeners.length > 0)) {
				supportRegions = getSupportRegions(partnerEntryId);

				for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
					for (ModelListener<PartnerEntry> listener : listeners) {
						listener.onBeforeRemoveAssociation(partnerEntryId,
							com.liferay.osb.model.SupportRegion.class.getName(),
							supportRegion.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
						listener.onBeforeRemoveAssociation(supportRegion.getPrimaryKey(),
							PartnerEntry.class.getName(), partnerEntryId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(partnerEntryId) });

			if ((listeners.length > 0) || (supportRegionListeners.length > 0)) {
				for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
					for (ModelListener<PartnerEntry> listener : listeners) {
						listener.onAfterRemoveAssociation(partnerEntryId,
							com.liferay.osb.model.SupportRegion.class.getName(),
							supportRegion.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
						listener.onAfterRemoveAssociation(supportRegion.getPrimaryKey(),
							PartnerEntry.class.getName(), partnerEntryId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSupportRegion {
		protected RemoveSupportRegion() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_PartnerEntries_SupportRegions WHERE partnerEntryId = ? AND supportRegionId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long partnerEntryId, long supportRegionId)
			throws SystemException {
			if (containsSupportRegion.contains(partnerEntryId, supportRegionId)) {
				ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
					supportRegionPersistence.getListeners();

				for (ModelListener<PartnerEntry> listener : listeners) {
					listener.onBeforeRemoveAssociation(partnerEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onBeforeRemoveAssociation(supportRegionId,
						PartnerEntry.class.getName(), partnerEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(partnerEntryId), new Long(supportRegionId)
					});

				for (ModelListener<PartnerEntry> listener : listeners) {
					listener.onAfterRemoveAssociation(partnerEntryId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onAfterRemoveAssociation(supportRegionId,
						PartnerEntry.class.getName(), partnerEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_PARTNERENTRY = "SELECT partnerEntry FROM PartnerEntry partnerEntry";
	private static final String _SQL_SELECT_PARTNERENTRY_WHERE = "SELECT partnerEntry FROM PartnerEntry partnerEntry WHERE ";
	private static final String _SQL_COUNT_PARTNERENTRY = "SELECT COUNT(partnerEntry) FROM PartnerEntry partnerEntry";
	private static final String _SQL_COUNT_PARTNERENTRY_WHERE = "SELECT COUNT(partnerEntry) FROM PartnerEntry partnerEntry WHERE ";
	private static final String _SQL_GETSUPPORTREGIONS = "SELECT {OSB_SupportRegion.*} FROM OSB_SupportRegion INNER JOIN OSB_PartnerEntries_SupportRegions ON (OSB_PartnerEntries_SupportRegions.supportRegionId = OSB_SupportRegion.supportRegionId) WHERE (OSB_PartnerEntries_SupportRegions.partnerEntryId = ?)";
	private static final String _SQL_GETSUPPORTREGIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_PartnerEntries_SupportRegions WHERE partnerEntryId = ?";
	private static final String _SQL_CONTAINSSUPPORTREGION = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_PartnerEntries_SupportRegions WHERE partnerEntryId = ? AND supportRegionId = ?";
	private static final String _FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2 =
		"partnerEntry.parentPartnerEntryId = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1 =
		"partnerEntry.dossieraAccountKey IS NULL";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2 =
		"partnerEntry.dossieraAccountKey = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3 =
		"(partnerEntry.dossieraAccountKey IS NULL OR partnerEntry.dossieraAccountKey = ?)";
	private static final String _FINDER_COLUMN_CODE_CODE_1 = "partnerEntry.code IS NULL";
	private static final String _FINDER_COLUMN_CODE_CODE_2 = "partnerEntry.code = ?";
	private static final String _FINDER_COLUMN_CODE_CODE_3 = "(partnerEntry.code IS NULL OR partnerEntry.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "partnerEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PartnerEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PartnerEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PartnerEntryPersistenceImpl.class);
	private static PartnerEntry _nullPartnerEntry = new PartnerEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PartnerEntry> toCacheModel() {
				return _nullPartnerEntryCacheModel;
			}
		};

	private static CacheModel<PartnerEntry> _nullPartnerEntryCacheModel = new CacheModel<PartnerEntry>() {
			public PartnerEntry toEntityModel() {
				return _nullPartnerEntry;
			}
		};
}
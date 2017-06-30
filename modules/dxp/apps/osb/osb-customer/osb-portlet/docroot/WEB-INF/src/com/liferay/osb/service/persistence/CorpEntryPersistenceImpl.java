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

import com.liferay.osb.NoSuchCorpEntryException;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.impl.CorpEntryImpl;
import com.liferay.osb.model.impl.CorpEntryModelImpl;

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
import com.liferay.portal.service.persistence.CountryPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the corp entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpEntryPersistence
 * @see CorpEntryUtil
 * @generated
 */
public class CorpEntryPersistenceImpl extends BasePersistenceImpl<CorpEntry>
	implements CorpEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpEntryUtil} to access the corp entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, CorpEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, CorpEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			CorpEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_ORGANIZATIONID = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, CorpEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByOrganizationId",
			new String[] { Long.class.getName() },
			CorpEntryModelImpl.ORGANIZATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrganizationId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, CorpEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDossieraAccountKey",
			new String[] { String.class.getName() },
			CorpEntryModelImpl.DOSSIERAACCOUNTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDossieraAccountKey", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, CorpEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, CorpEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the corp entry in the entity cache if it is enabled.
	 *
	 * @param corpEntry the corp entry
	 */
	public void cacheResult(CorpEntry corpEntry) {
		EntityCacheUtil.putResult(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryImpl.class, corpEntry.getPrimaryKey(), corpEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
			new Object[] { Long.valueOf(corpEntry.getOrganizationId()) },
			corpEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
			new Object[] { corpEntry.getDossieraAccountKey() }, corpEntry);

		corpEntry.resetOriginalValues();
	}

	/**
	 * Caches the corp entries in the entity cache if it is enabled.
	 *
	 * @param corpEntries the corp entries
	 */
	public void cacheResult(List<CorpEntry> corpEntries) {
		for (CorpEntry corpEntry : corpEntries) {
			if (EntityCacheUtil.getResult(
						CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
						CorpEntryImpl.class, corpEntry.getPrimaryKey()) == null) {
				cacheResult(corpEntry);
			}
			else {
				corpEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CorpEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CorpEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpEntry corpEntry) {
		EntityCacheUtil.removeResult(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryImpl.class, corpEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(corpEntry);
	}

	@Override
	public void clearCache(List<CorpEntry> corpEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpEntry corpEntry : corpEntries) {
			EntityCacheUtil.removeResult(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
				CorpEntryImpl.class, corpEntry.getPrimaryKey());

			clearUniqueFindersCache(corpEntry);
		}
	}

	protected void cacheUniqueFindersCache(CorpEntry corpEntry) {
		if (corpEntry.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(corpEntry.getOrganizationId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
				args, corpEntry);

			args = new Object[] { corpEntry.getDossieraAccountKey() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args, corpEntry);
		}
		else {
			CorpEntryModelImpl corpEntryModelImpl = (CorpEntryModelImpl)corpEntry;

			if ((corpEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(corpEntry.getOrganizationId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					args, corpEntry);
			}

			if ((corpEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { corpEntry.getDossieraAccountKey() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					args, corpEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(CorpEntry corpEntry) {
		CorpEntryModelImpl corpEntryModelImpl = (CorpEntryModelImpl)corpEntry;

		Object[] args = new Object[] { Long.valueOf(corpEntry.getOrganizationId()) };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args);

		if ((corpEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(corpEntryModelImpl.getOriginalOrganizationId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
				args);
		}

		args = new Object[] { corpEntry.getDossieraAccountKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
			args);

		if ((corpEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					corpEntryModelImpl.getOriginalDossieraAccountKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args);
		}
	}

	/**
	 * Creates a new corp entry with the primary key. Does not add the corp entry to the database.
	 *
	 * @param corpEntryId the primary key for the new corp entry
	 * @return the new corp entry
	 */
	public CorpEntry create(long corpEntryId) {
		CorpEntry corpEntry = new CorpEntryImpl();

		corpEntry.setNew(true);
		corpEntry.setPrimaryKey(corpEntryId);

		return corpEntry;
	}

	/**
	 * Removes the corp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpEntryId the primary key of the corp entry
	 * @return the corp entry that was removed
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry remove(long corpEntryId)
		throws NoSuchCorpEntryException, SystemException {
		return remove(Long.valueOf(corpEntryId));
	}

	/**
	 * Removes the corp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp entry
	 * @return the corp entry that was removed
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpEntry remove(Serializable primaryKey)
		throws NoSuchCorpEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CorpEntry corpEntry = (CorpEntry)session.get(CorpEntryImpl.class,
					primaryKey);

			if (corpEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpEntry);
		}
		catch (NoSuchCorpEntryException nsee) {
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
	protected CorpEntry removeImpl(CorpEntry corpEntry)
		throws SystemException {
		corpEntry = toUnwrappedModel(corpEntry);

		try {
			clearCorpGroups.clear(corpEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, corpEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(corpEntry);

		return corpEntry;
	}

	@Override
	public CorpEntry updateImpl(com.liferay.osb.model.CorpEntry corpEntry,
		boolean merge) throws SystemException {
		corpEntry = toUnwrappedModel(corpEntry);

		boolean isNew = corpEntry.isNew();

		CorpEntryModelImpl corpEntryModelImpl = (CorpEntryModelImpl)corpEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, corpEntry, merge);

			corpEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CorpEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((corpEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						corpEntryModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { corpEntryModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}
		}

		EntityCacheUtil.putResult(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryImpl.class, corpEntry.getPrimaryKey(), corpEntry);

		clearUniqueFindersCache(corpEntry);
		cacheUniqueFindersCache(corpEntry);

		return corpEntry;
	}

	protected CorpEntry toUnwrappedModel(CorpEntry corpEntry) {
		if (corpEntry instanceof CorpEntryImpl) {
			return corpEntry;
		}

		CorpEntryImpl corpEntryImpl = new CorpEntryImpl();

		corpEntryImpl.setNew(corpEntry.isNew());
		corpEntryImpl.setPrimaryKey(corpEntry.getPrimaryKey());

		corpEntryImpl.setCorpEntryId(corpEntry.getCorpEntryId());
		corpEntryImpl.setUserId(corpEntry.getUserId());
		corpEntryImpl.setUserName(corpEntry.getUserName());
		corpEntryImpl.setCreateDate(corpEntry.getCreateDate());
		corpEntryImpl.setModifiedDate(corpEntry.getModifiedDate());
		corpEntryImpl.setName(corpEntry.getName());
		corpEntryImpl.setDescription(corpEntry.getDescription());
		corpEntryImpl.setOrganizationId(corpEntry.getOrganizationId());
		corpEntryImpl.setLogoId(corpEntry.getLogoId());
		corpEntryImpl.setAddressId(corpEntry.getAddressId());
		corpEntryImpl.setContactEmailAddress(corpEntry.getContactEmailAddress());
		corpEntryImpl.setProfileEmailAddress(corpEntry.getProfileEmailAddress());
		corpEntryImpl.setPhoneNumber(corpEntry.getPhoneNumber());
		corpEntryImpl.setFaxNumber(corpEntry.getFaxNumber());
		corpEntryImpl.setWebsite(corpEntry.getWebsite());
		corpEntryImpl.setDossieraAccountKey(corpEntry.getDossieraAccountKey());
		corpEntryImpl.setStatus(corpEntry.getStatus());
		corpEntryImpl.setStatusByUserId(corpEntry.getStatusByUserId());
		corpEntryImpl.setStatusByUserName(corpEntry.getStatusByUserName());
		corpEntryImpl.setStatusDate(corpEntry.getStatusDate());
		corpEntryImpl.setStatusMessage(corpEntry.getStatusMessage());

		return corpEntryImpl;
	}

	/**
	 * Returns the corp entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp entry
	 * @return the corp entry
	 * @throws com.liferay.portal.NoSuchModelException if a corp entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp entry with the primary key or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	 *
	 * @param corpEntryId the primary key of the corp entry
	 * @return the corp entry
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry findByPrimaryKey(long corpEntryId)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = fetchByPrimaryKey(corpEntryId);

		if (corpEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + corpEntryId);
			}

			throw new NoSuchCorpEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				corpEntryId);
		}

		return corpEntry;
	}

	/**
	 * Returns the corp entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp entry
	 * @return the corp entry, or <code>null</code> if a corp entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpEntryId the primary key of the corp entry
	 * @return the corp entry, or <code>null</code> if a corp entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry fetchByPrimaryKey(long corpEntryId)
		throws SystemException {
		CorpEntry corpEntry = (CorpEntry)EntityCacheUtil.getResult(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
				CorpEntryImpl.class, corpEntryId);

		if (corpEntry == _nullCorpEntry) {
			return null;
		}

		if (corpEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				corpEntry = (CorpEntry)session.get(CorpEntryImpl.class,
						Long.valueOf(corpEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (corpEntry != null) {
					cacheResult(corpEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CorpEntryModelImpl.ENTITY_CACHE_ENABLED,
						CorpEntryImpl.class, corpEntryId, _nullCorpEntry);
				}

				closeSession(session);
			}
		}

		return corpEntry;
	}

	/**
	 * Returns all the corp entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpEntry> findByName(String name) throws SystemException {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp entries
	 * @param end the upper bound of the range of corp entries (not inclusive)
	 * @return the range of matching corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpEntry> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp entries where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp entries
	 * @param end the upper bound of the range of corp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpEntry> findByName(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<CorpEntry> list = (List<CorpEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpEntry corpEntry : list) {
				if (!Validator.equals(name, corpEntry.getName())) {
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

			query.append(_SQL_SELECT_CORPENTRY_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
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

				if (name != null) {
					qPos.add(name);
				}

				list = (List<CorpEntry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first corp entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp entry
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry findByName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = fetchByName_First(name, orderByComparator);

		if (corpEntry != null) {
			return corpEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpEntryException(msg.toString());
	}

	/**
	 * Returns the first corp entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp entry, or <code>null</code> if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry fetchByName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<CorpEntry> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp entry
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry findByName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = fetchByName_Last(name, orderByComparator);

		if (corpEntry != null) {
			return corpEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpEntryException(msg.toString());
	}

	/**
	 * Returns the last corp entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp entry, or <code>null</code> if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry fetchByName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByName(name);

		List<CorpEntry> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp entries before and after the current corp entry in the ordered set where name = &#63;.
	 *
	 * @param corpEntryId the primary key of the current corp entry
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp entry
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry[] findByName_PrevAndNext(long corpEntryId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = findByPrimaryKey(corpEntryId);

		Session session = null;

		try {
			session = openSession();

			CorpEntry[] array = new CorpEntryImpl[3];

			array[0] = getByName_PrevAndNext(session, corpEntry, name,
					orderByComparator, true);

			array[1] = corpEntry;

			array[2] = getByName_PrevAndNext(session, corpEntry, name,
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

	protected CorpEntry getByName_PrevAndNext(Session session,
		CorpEntry corpEntry, String name, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPENTRY_WHERE);

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_NAME_NAME_2);
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

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the corp entry where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp entry
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry findByOrganizationId(long organizationId)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = fetchByOrganizationId(organizationId);

		if (corpEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationId=");
			msg.append(organizationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCorpEntryException(msg.toString());
		}

		return corpEntry;
	}

	/**
	 * Returns the corp entry where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry fetchByOrganizationId(long organizationId)
		throws SystemException {
		return fetchByOrganizationId(organizationId, true);
	}

	/**
	 * Returns the corp entry where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { organizationId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					finderArgs, this);
		}

		if (result instanceof CorpEntry) {
			CorpEntry corpEntry = (CorpEntry)result;

			if ((organizationId != corpEntry.getOrganizationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_CORPENTRY_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				List<CorpEntry> list = q.list();

				result = list;

				CorpEntry corpEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
						finderArgs, list);
				}
				else {
					corpEntry = list.get(0);

					cacheResult(corpEntry);

					if ((corpEntry.getOrganizationId() != organizationId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
							finderArgs, corpEntry);
					}
				}

				return corpEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
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
				return (CorpEntry)result;
			}
		}
	}

	/**
	 * Returns the corp entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching corp entry
	 * @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry findByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = fetchByDossieraAccountKey(dossieraAccountKey);

		if (corpEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossieraAccountKey=");
			msg.append(dossieraAccountKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCorpEntryException(msg.toString());
		}

		return corpEntry;
	}

	/**
	 * Returns the corp entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry fetchByDossieraAccountKey(String dossieraAccountKey)
		throws SystemException {
		return fetchByDossieraAccountKey(dossieraAccountKey, true);
	}

	/**
	 * Returns the corp entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry fetchByDossieraAccountKey(String dossieraAccountKey,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					finderArgs, this);
		}

		if (result instanceof CorpEntry) {
			CorpEntry corpEntry = (CorpEntry)result;

			if (!Validator.equals(dossieraAccountKey,
						corpEntry.getDossieraAccountKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_CORPENTRY_WHERE);

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

				List<CorpEntry> list = q.list();

				result = list;

				CorpEntry corpEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
						finderArgs, list);
				}
				else {
					corpEntry = list.get(0);

					cacheResult(corpEntry);

					if ((corpEntry.getDossieraAccountKey() == null) ||
							!corpEntry.getDossieraAccountKey()
										  .equals(dossieraAccountKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
							finderArgs, corpEntry);
					}
				}

				return corpEntry;
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
				return (CorpEntry)result;
			}
		}
	}

	/**
	 * Returns all the corp entries.
	 *
	 * @return the corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp entries
	 * @param end the upper bound of the range of corp entries (not inclusive)
	 * @return the range of corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp entries
	 * @param end the upper bound of the range of corp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpEntry> findAll(int start, int end,
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

		List<CorpEntry> list = (List<CorpEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CORPENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPENTRY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CorpEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CorpEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the corp entries where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByName(String name) throws SystemException {
		for (CorpEntry corpEntry : findByName(name)) {
			remove(corpEntry);
		}
	}

	/**
	 * Removes the corp entry where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the corp entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry removeByOrganizationId(long organizationId)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = findByOrganizationId(organizationId);

		return remove(corpEntry);
	}

	/**
	 * Removes the corp entry where dossieraAccountKey = &#63; from the database.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the corp entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CorpEntry removeByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchCorpEntryException, SystemException {
		CorpEntry corpEntry = findByDossieraAccountKey(dossieraAccountKey);

		return remove(corpEntry);
	}

	/**
	 * Removes all the corp entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CorpEntry corpEntry : findAll()) {
			remove(corpEntry);
		}
	}

	/**
	 * Returns the number of corp entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPENTRY_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp entries where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationId(long organizationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { organizationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPENTRY_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the number of matching corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDossieraAccountKey(String dossieraAccountKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPENTRY_WHERE);

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
	 * Returns the number of corp entries.
	 *
	 * @return the number of corp entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPENTRY);

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
	 * Returns all the corp groups associated with the corp entry.
	 *
	 * @param pk the primary key of the corp entry
	 * @return the corp groups associated with the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.CorpGroup> getCorpGroups(long pk)
		throws SystemException {
		return getCorpGroups(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the corp groups associated with the corp entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the corp entry
	 * @param start the lower bound of the range of corp entries
	 * @param end the upper bound of the range of corp entries (not inclusive)
	 * @return the range of corp groups associated with the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.CorpGroup> getCorpGroups(long pk,
		int start, int end) throws SystemException {
		return getCorpGroups(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_CORPGROUPS = new FinderPath(com.liferay.osb.model.impl.CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED_OSB_CORPENTRY_CORPGROUP,
			com.liferay.osb.model.impl.CorpGroupImpl.class,
			CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME,
			"getCorpGroups",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_CORPGROUPS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the corp groups associated with the corp entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the corp entry
	 * @param start the lower bound of the range of corp entries
	 * @param end the upper bound of the range of corp entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp groups associated with the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.CorpGroup> getCorpGroups(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.CorpGroup> list = (List<com.liferay.osb.model.CorpGroup>)FinderCacheUtil.getResult(FINDER_PATH_GET_CORPGROUPS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETCORPGROUPS.concat(ORDER_BY_CLAUSE)
											.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETCORPGROUPS;
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_CorpGroup",
					com.liferay.osb.model.impl.CorpGroupImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.CorpGroup>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_CORPGROUPS,
						finderArgs);
				}
				else {
					corpGroupPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_CORPGROUPS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_CORPGROUPS_SIZE = new FinderPath(com.liferay.osb.model.impl.CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED_OSB_CORPENTRY_CORPGROUP,
			Long.class,
			CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME,
			"getCorpGroupsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_CORPGROUPS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of corp groups associated with the corp entry.
	 *
	 * @param pk the primary key of the corp entry
	 * @return the number of corp groups associated with the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public int getCorpGroupsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_CORPGROUPS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETCORPGROUPSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_CORPGROUPS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_CORPGROUP = new FinderPath(com.liferay.osb.model.impl.CorpGroupModelImpl.ENTITY_CACHE_ENABLED,
			CorpEntryModelImpl.FINDER_CACHE_ENABLED_OSB_CORPENTRY_CORPGROUP,
			Boolean.class,
			CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME,
			"containsCorpGroup",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the corp group is associated with the corp entry.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroupPK the primary key of the corp group
	 * @return <code>true</code> if the corp group is associated with the corp entry; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsCorpGroup(long pk, long corpGroupPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, corpGroupPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_CORPGROUP,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsCorpGroup.contains(pk,
							corpGroupPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_CORPGROUP,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the corp entry has any corp groups associated with it.
	 *
	 * @param pk the primary key of the corp entry to check for associations with corp groups
	 * @return <code>true</code> if the corp entry has any corp groups associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsCorpGroups(long pk) throws SystemException {
		if (getCorpGroupsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroupPK the primary key of the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpGroup(long pk, long corpGroupPK)
		throws SystemException {
		try {
			addCorpGroup.add(pk, corpGroupPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Adds an association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroup the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpGroup(long pk, com.liferay.osb.model.CorpGroup corpGroup)
		throws SystemException {
		try {
			addCorpGroup.add(pk, corpGroup.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Adds an association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroupPKs the primary keys of the corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpGroups(long pk, long[] corpGroupPKs)
		throws SystemException {
		try {
			for (long corpGroupPK : corpGroupPKs) {
				addCorpGroup.add(pk, corpGroupPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Adds an association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroups the corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public void addCorpGroups(long pk,
		List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws SystemException {
		try {
			for (com.liferay.osb.model.CorpGroup corpGroup : corpGroups) {
				addCorpGroup.add(pk, corpGroup.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Clears all associations between the corp entry and its corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry to clear the associated corp groups from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearCorpGroups(long pk) throws SystemException {
		try {
			clearCorpGroups.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroupPK the primary key of the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpGroup(long pk, long corpGroupPK)
		throws SystemException {
		try {
			removeCorpGroup.remove(pk, corpGroupPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroup the corp group
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpGroup(long pk,
		com.liferay.osb.model.CorpGroup corpGroup) throws SystemException {
		try {
			removeCorpGroup.remove(pk, corpGroup.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroupPKs the primary keys of the corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpGroups(long pk, long[] corpGroupPKs)
		throws SystemException {
		try {
			for (long corpGroupPK : corpGroupPKs) {
				removeCorpGroup.remove(pk, corpGroupPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Removes the association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroups the corp groups
	 * @throws SystemException if a system exception occurred
	 */
	public void removeCorpGroups(long pk,
		List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws SystemException {
		try {
			for (com.liferay.osb.model.CorpGroup corpGroup : corpGroups) {
				removeCorpGroup.remove(pk, corpGroup.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Sets the corp groups associated with the corp entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroupPKs the primary keys of the corp groups to be associated with the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setCorpGroups(long pk, long[] corpGroupPKs)
		throws SystemException {
		try {
			Set<Long> corpGroupPKSet = SetUtil.fromArray(corpGroupPKs);

			List<com.liferay.osb.model.CorpGroup> corpGroups = getCorpGroups(pk);

			for (com.liferay.osb.model.CorpGroup corpGroup : corpGroups) {
				if (!corpGroupPKSet.remove(corpGroup.getPrimaryKey())) {
					removeCorpGroup.remove(pk, corpGroup.getPrimaryKey());
				}
			}

			for (Long corpGroupPK : corpGroupPKSet) {
				addCorpGroup.add(pk, corpGroupPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Sets the corp groups associated with the corp entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the corp entry
	 * @param corpGroups the corp groups to be associated with the corp entry
	 * @throws SystemException if a system exception occurred
	 */
	public void setCorpGroups(long pk,
		List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws SystemException {
		try {
			long[] corpGroupPKs = new long[corpGroups.size()];

			for (int i = 0; i < corpGroups.size(); i++) {
				com.liferay.osb.model.CorpGroup corpGroup = corpGroups.get(i);

				corpGroupPKs[i] = corpGroup.getPrimaryKey();
			}

			setCorpGroups(pk, corpGroupPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(CorpEntryModelImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_NAME);
		}
	}

	/**
	 * Initializes the corp entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.CorpEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CorpEntry>> listenersList = new ArrayList<ModelListener<CorpEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<CorpEntry>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsCorpGroup = new ContainsCorpGroup();

		addCorpGroup = new AddCorpGroup();
		clearCorpGroups = new ClearCorpGroups();
		removeCorpGroup = new RemoveCorpGroup();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CorpEntryImpl.class.getName());
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
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	protected ContainsCorpGroup containsCorpGroup;
	protected AddCorpGroup addCorpGroup;
	protected ClearCorpGroups clearCorpGroups;
	protected RemoveCorpGroup removeCorpGroup;

	protected class ContainsCorpGroup {
		protected ContainsCorpGroup() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSCORPGROUP,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long corpEntryId, long corpGroupId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(corpEntryId), new Long(corpGroupId)
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

	protected class AddCorpGroup {
		protected AddCorpGroup() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_CorpEntry_CorpGroup (corpEntryId, corpGroupId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long corpEntryId, long corpGroupId)
			throws SystemException {
			if (!containsCorpGroup.contains(corpEntryId, corpGroupId)) {
				ModelListener<com.liferay.osb.model.CorpGroup>[] corpGroupListeners =
					corpGroupPersistence.getListeners();

				for (ModelListener<CorpEntry> listener : listeners) {
					listener.onBeforeAddAssociation(corpEntryId,
						com.liferay.osb.model.CorpGroup.class.getName(),
						corpGroupId);
				}

				for (ModelListener<com.liferay.osb.model.CorpGroup> listener : corpGroupListeners) {
					listener.onBeforeAddAssociation(corpGroupId,
						CorpEntry.class.getName(), corpEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(corpEntryId), new Long(corpGroupId)
					});

				for (ModelListener<CorpEntry> listener : listeners) {
					listener.onAfterAddAssociation(corpEntryId,
						com.liferay.osb.model.CorpGroup.class.getName(),
						corpGroupId);
				}

				for (ModelListener<com.liferay.osb.model.CorpGroup> listener : corpGroupListeners) {
					listener.onAfterAddAssociation(corpGroupId,
						CorpEntry.class.getName(), corpEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearCorpGroups {
		protected ClearCorpGroups() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_CorpEntry_CorpGroup WHERE corpEntryId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long corpEntryId) throws SystemException {
			ModelListener<com.liferay.osb.model.CorpGroup>[] corpGroupListeners = corpGroupPersistence.getListeners();

			List<com.liferay.osb.model.CorpGroup> corpGroups = null;

			if ((listeners.length > 0) || (corpGroupListeners.length > 0)) {
				corpGroups = getCorpGroups(corpEntryId);

				for (com.liferay.osb.model.CorpGroup corpGroup : corpGroups) {
					for (ModelListener<CorpEntry> listener : listeners) {
						listener.onBeforeRemoveAssociation(corpEntryId,
							com.liferay.osb.model.CorpGroup.class.getName(),
							corpGroup.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.CorpGroup> listener : corpGroupListeners) {
						listener.onBeforeRemoveAssociation(corpGroup.getPrimaryKey(),
							CorpEntry.class.getName(), corpEntryId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(corpEntryId) });

			if ((listeners.length > 0) || (corpGroupListeners.length > 0)) {
				for (com.liferay.osb.model.CorpGroup corpGroup : corpGroups) {
					for (ModelListener<CorpEntry> listener : listeners) {
						listener.onAfterRemoveAssociation(corpEntryId,
							com.liferay.osb.model.CorpGroup.class.getName(),
							corpGroup.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.CorpGroup> listener : corpGroupListeners) {
						listener.onAfterRemoveAssociation(corpGroup.getPrimaryKey(),
							CorpEntry.class.getName(), corpEntryId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveCorpGroup {
		protected RemoveCorpGroup() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_CorpEntry_CorpGroup WHERE corpEntryId = ? AND corpGroupId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long corpEntryId, long corpGroupId)
			throws SystemException {
			if (containsCorpGroup.contains(corpEntryId, corpGroupId)) {
				ModelListener<com.liferay.osb.model.CorpGroup>[] corpGroupListeners =
					corpGroupPersistence.getListeners();

				for (ModelListener<CorpEntry> listener : listeners) {
					listener.onBeforeRemoveAssociation(corpEntryId,
						com.liferay.osb.model.CorpGroup.class.getName(),
						corpGroupId);
				}

				for (ModelListener<com.liferay.osb.model.CorpGroup> listener : corpGroupListeners) {
					listener.onBeforeRemoveAssociation(corpGroupId,
						CorpEntry.class.getName(), corpEntryId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(corpEntryId), new Long(corpGroupId)
					});

				for (ModelListener<CorpEntry> listener : listeners) {
					listener.onAfterRemoveAssociation(corpEntryId,
						com.liferay.osb.model.CorpGroup.class.getName(),
						corpGroupId);
				}

				for (ModelListener<com.liferay.osb.model.CorpGroup> listener : corpGroupListeners) {
					listener.onAfterRemoveAssociation(corpGroupId,
						CorpEntry.class.getName(), corpEntryId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_CORPENTRY = "SELECT corpEntry FROM CorpEntry corpEntry";
	private static final String _SQL_SELECT_CORPENTRY_WHERE = "SELECT corpEntry FROM CorpEntry corpEntry WHERE ";
	private static final String _SQL_COUNT_CORPENTRY = "SELECT COUNT(corpEntry) FROM CorpEntry corpEntry";
	private static final String _SQL_COUNT_CORPENTRY_WHERE = "SELECT COUNT(corpEntry) FROM CorpEntry corpEntry WHERE ";
	private static final String _SQL_GETCORPGROUPS = "SELECT {OSB_CorpGroup.*} FROM OSB_CorpGroup INNER JOIN OSB_CorpEntry_CorpGroup ON (OSB_CorpEntry_CorpGroup.corpGroupId = OSB_CorpGroup.corpGroupId) WHERE (OSB_CorpEntry_CorpGroup.corpEntryId = ?)";
	private static final String _SQL_GETCORPGROUPSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_CorpEntry_CorpGroup WHERE corpEntryId = ?";
	private static final String _SQL_CONTAINSCORPGROUP = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_CorpEntry_CorpGroup WHERE corpEntryId = ? AND corpGroupId = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "corpEntry.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "corpEntry.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(corpEntry.name IS NULL OR corpEntry.name = ?)";
	private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "corpEntry.organizationId = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1 =
		"corpEntry.dossieraAccountKey IS NULL";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2 =
		"corpEntry.dossieraAccountKey = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3 =
		"(corpEntry.dossieraAccountKey IS NULL OR corpEntry.dossieraAccountKey = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "corpEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CorpEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CorpEntryPersistenceImpl.class);
	private static CorpEntry _nullCorpEntry = new CorpEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CorpEntry> toCacheModel() {
				return _nullCorpEntryCacheModel;
			}
		};

	private static CacheModel<CorpEntry> _nullCorpEntryCacheModel = new CacheModel<CorpEntry>() {
			public CorpEntry toEntityModel() {
				return _nullCorpEntry;
			}
		};
}
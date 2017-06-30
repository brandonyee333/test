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

import com.liferay.osb.NoSuchDeveloperEntryException;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.model.impl.DeveloperEntryImpl;
import com.liferay.osb.model.impl.DeveloperEntryModelImpl;

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
import com.liferay.portal.service.persistence.AddressPersistence;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.CountryPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.RegionPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the developer entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DeveloperEntryPersistence
 * @see DeveloperEntryUtil
 * @generated
 */
public class DeveloperEntryPersistenceImpl extends BasePersistenceImpl<DeveloperEntry>
	implements DeveloperEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeveloperEntryUtil} to access the developer entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeveloperEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			DeveloperEntryModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDossieraAccountKey",
			new String[] { String.class.getName() },
			DeveloperEntryModelImpl.DOSSIERAACCOUNTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDossieraAccountKey", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatus",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			DeveloperEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UI_T = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUI_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DeveloperEntryModelImpl.USERID_COLUMN_BITMASK |
			DeveloperEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UI_T = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DN_DS = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDN_DS",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_DS = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDN_DS",
			new String[] { String.class.getName(), Integer.class.getName() },
			DeveloperEntryModelImpl.DOMAINNAME_COLUMN_BITMASK |
			DeveloperEntryModelImpl.DOMAINSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DN_DS = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDN_DS",
			new String[] { String.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_S = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_S",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByT_S",
			new String[] { Integer.class.getName(), Integer.class.getName() },
			DeveloperEntryModelImpl.TYPE_COLUMN_BITMASK |
			DeveloperEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_S = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_S",
			new String[] { Integer.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UI_T_S = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T_S =
		new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			DeveloperEntryModelImpl.USERID_COLUMN_BITMASK |
			DeveloperEntryModelImpl.TYPE_COLUMN_BITMASK |
			DeveloperEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UI_T_S = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LEN_T_S = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLEN_T_S",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEN_T_S =
		new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLEN_T_S",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			DeveloperEntryModelImpl.LEGALENTITYNAME_COLUMN_BITMASK |
			DeveloperEntryModelImpl.TYPE_COLUMN_BITMASK |
			DeveloperEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LEN_T_S = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLEN_T_S",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED,
			DeveloperEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the developer entry in the entity cache if it is enabled.
	 *
	 * @param developerEntry the developer entry
	 */
	public void cacheResult(DeveloperEntry developerEntry) {
		EntityCacheUtil.putResult(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryImpl.class, developerEntry.getPrimaryKey(),
			developerEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
			new Object[] { developerEntry.getDossieraAccountKey() },
			developerEntry);

		developerEntry.resetOriginalValues();
	}

	/**
	 * Caches the developer entries in the entity cache if it is enabled.
	 *
	 * @param developerEntries the developer entries
	 */
	public void cacheResult(List<DeveloperEntry> developerEntries) {
		for (DeveloperEntry developerEntry : developerEntries) {
			if (EntityCacheUtil.getResult(
						DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
						DeveloperEntryImpl.class, developerEntry.getPrimaryKey()) == null) {
				cacheResult(developerEntry);
			}
			else {
				developerEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all developer entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DeveloperEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DeveloperEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the developer entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeveloperEntry developerEntry) {
		EntityCacheUtil.removeResult(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryImpl.class, developerEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(developerEntry);
	}

	@Override
	public void clearCache(List<DeveloperEntry> developerEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeveloperEntry developerEntry : developerEntries) {
			EntityCacheUtil.removeResult(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
				DeveloperEntryImpl.class, developerEntry.getPrimaryKey());

			clearUniqueFindersCache(developerEntry);
		}
	}

	protected void cacheUniqueFindersCache(DeveloperEntry developerEntry) {
		if (developerEntry.isNew()) {
			Object[] args = new Object[] { developerEntry.getDossieraAccountKey() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args, developerEntry);
		}
		else {
			DeveloperEntryModelImpl developerEntryModelImpl = (DeveloperEntryModelImpl)developerEntry;

			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						developerEntry.getDossieraAccountKey()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					args, developerEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(DeveloperEntry developerEntry) {
		DeveloperEntryModelImpl developerEntryModelImpl = (DeveloperEntryModelImpl)developerEntry;

		Object[] args = new Object[] { developerEntry.getDossieraAccountKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
			args);

		if ((developerEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					developerEntryModelImpl.getOriginalDossieraAccountKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args);
		}
	}

	/**
	 * Creates a new developer entry with the primary key. Does not add the developer entry to the database.
	 *
	 * @param developerEntryId the primary key for the new developer entry
	 * @return the new developer entry
	 */
	public DeveloperEntry create(long developerEntryId) {
		DeveloperEntry developerEntry = new DeveloperEntryImpl();

		developerEntry.setNew(true);
		developerEntry.setPrimaryKey(developerEntryId);

		return developerEntry;
	}

	/**
	 * Removes the developer entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param developerEntryId the primary key of the developer entry
	 * @return the developer entry that was removed
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry remove(long developerEntryId)
		throws NoSuchDeveloperEntryException, SystemException {
		return remove(Long.valueOf(developerEntryId));
	}

	/**
	 * Removes the developer entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the developer entry
	 * @return the developer entry that was removed
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DeveloperEntry remove(Serializable primaryKey)
		throws NoSuchDeveloperEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DeveloperEntry developerEntry = (DeveloperEntry)session.get(DeveloperEntryImpl.class,
					primaryKey);

			if (developerEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeveloperEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(developerEntry);
		}
		catch (NoSuchDeveloperEntryException nsee) {
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
	protected DeveloperEntry removeImpl(DeveloperEntry developerEntry)
		throws SystemException {
		developerEntry = toUnwrappedModel(developerEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, developerEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(developerEntry);

		return developerEntry;
	}

	@Override
	public DeveloperEntry updateImpl(
		com.liferay.osb.model.DeveloperEntry developerEntry, boolean merge)
		throws SystemException {
		developerEntry = toUnwrappedModel(developerEntry);

		boolean isNew = developerEntry.isNew();

		DeveloperEntryModelImpl developerEntryModelImpl = (DeveloperEntryModelImpl)developerEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, developerEntry, merge);

			developerEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DeveloperEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(developerEntryModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(developerEntryModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(developerEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] {
						Integer.valueOf(developerEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(developerEntryModelImpl.getOriginalUserId()),
						Integer.valueOf(developerEntryModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T,
					args);

				args = new Object[] {
						Long.valueOf(developerEntryModelImpl.getUserId()),
						Integer.valueOf(developerEntryModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T,
					args);
			}

			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_DS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						developerEntryModelImpl.getOriginalDomainName(),
						Integer.valueOf(developerEntryModelImpl.getOriginalDomainStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DN_DS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_DS,
					args);

				args = new Object[] {
						developerEntryModelImpl.getDomainName(),
						Integer.valueOf(developerEntryModelImpl.getDomainStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DN_DS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_DS,
					args);
			}

			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(developerEntryModelImpl.getOriginalType()),
						Integer.valueOf(developerEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S,
					args);

				args = new Object[] {
						Integer.valueOf(developerEntryModelImpl.getType()),
						Integer.valueOf(developerEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S,
					args);
			}

			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(developerEntryModelImpl.getOriginalUserId()),
						Integer.valueOf(developerEntryModelImpl.getOriginalType()),
						Integer.valueOf(developerEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UI_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T_S,
					args);

				args = new Object[] {
						Long.valueOf(developerEntryModelImpl.getUserId()),
						Integer.valueOf(developerEntryModelImpl.getType()),
						Integer.valueOf(developerEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UI_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T_S,
					args);
			}

			if ((developerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEN_T_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						developerEntryModelImpl.getOriginalLegalEntityName(),
						Integer.valueOf(developerEntryModelImpl.getOriginalType()),
						Integer.valueOf(developerEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEN_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEN_T_S,
					args);

				args = new Object[] {
						developerEntryModelImpl.getLegalEntityName(),
						Integer.valueOf(developerEntryModelImpl.getType()),
						Integer.valueOf(developerEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LEN_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEN_T_S,
					args);
			}
		}

		EntityCacheUtil.putResult(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
			DeveloperEntryImpl.class, developerEntry.getPrimaryKey(),
			developerEntry);

		clearUniqueFindersCache(developerEntry);
		cacheUniqueFindersCache(developerEntry);

		return developerEntry;
	}

	protected DeveloperEntry toUnwrappedModel(DeveloperEntry developerEntry) {
		if (developerEntry instanceof DeveloperEntryImpl) {
			return developerEntry;
		}

		DeveloperEntryImpl developerEntryImpl = new DeveloperEntryImpl();

		developerEntryImpl.setNew(developerEntry.isNew());
		developerEntryImpl.setPrimaryKey(developerEntry.getPrimaryKey());

		developerEntryImpl.setDeveloperEntryId(developerEntry.getDeveloperEntryId());
		developerEntryImpl.setUserId(developerEntry.getUserId());
		developerEntryImpl.setUserName(developerEntry.getUserName());
		developerEntryImpl.setCreateDate(developerEntry.getCreateDate());
		developerEntryImpl.setScreenName(developerEntry.getScreenName());
		developerEntryImpl.setFirstName(developerEntry.getFirstName());
		developerEntryImpl.setMiddleName(developerEntry.getMiddleName());
		developerEntryImpl.setLastName(developerEntry.getLastName());
		developerEntryImpl.setLegalEntityName(developerEntry.getLegalEntityName());
		developerEntryImpl.setEmailAddress(developerEntry.getEmailAddress());
		developerEntryImpl.setContractEntryId(developerEntry.getContractEntryId());
		developerEntryImpl.setPhoneNumber(developerEntry.getPhoneNumber());
		developerEntryImpl.setFaxNumber(developerEntry.getFaxNumber());
		developerEntryImpl.setDomainName(developerEntry.getDomainName());
		developerEntryImpl.setDomainStatus(developerEntry.getDomainStatus());
		developerEntryImpl.setAddressId(developerEntry.getAddressId());
		developerEntryImpl.setCountryId(developerEntry.getCountryId());
		developerEntryImpl.setProfileDescription(developerEntry.getProfileDescription());
		developerEntryImpl.setProfileEmailAddress(developerEntry.getProfileEmailAddress());
		developerEntryImpl.setProfileLogoId(developerEntry.getProfileLogoId());
		developerEntryImpl.setProfileWebsite(developerEntry.getProfileWebsite());
		developerEntryImpl.setPaymentEmailAddress(developerEntry.getPaymentEmailAddress());
		developerEntryImpl.setGoogleAnalyticsKey(developerEntry.getGoogleAnalyticsKey());
		developerEntryImpl.setSubscriptionExpirationDate(developerEntry.getSubscriptionExpirationDate());
		developerEntryImpl.setSubscriptionStatus(developerEntry.getSubscriptionStatus());
		developerEntryImpl.setFatcaWithholdingPercentage(developerEntry.getFatcaWithholdingPercentage());
		developerEntryImpl.setDossieraAccountKey(developerEntry.getDossieraAccountKey());
		developerEntryImpl.setType(developerEntry.getType());
		developerEntryImpl.setStatus(developerEntry.getStatus());
		developerEntryImpl.setStatusByUserId(developerEntry.getStatusByUserId());
		developerEntryImpl.setStatusByUserName(developerEntry.getStatusByUserName());
		developerEntryImpl.setStatusDate(developerEntry.getStatusDate());
		developerEntryImpl.setStatusMessage(developerEntry.getStatusMessage());

		return developerEntryImpl;
	}

	/**
	 * Returns the developer entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the developer entry
	 * @return the developer entry
	 * @throws com.liferay.portal.NoSuchModelException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DeveloperEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the developer entry with the primary key or throws a {@link com.liferay.osb.NoSuchDeveloperEntryException} if it could not be found.
	 *
	 * @param developerEntryId the primary key of the developer entry
	 * @return the developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByPrimaryKey(long developerEntryId)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByPrimaryKey(developerEntryId);

		if (developerEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + developerEntryId);
			}

			throw new NoSuchDeveloperEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				developerEntryId);
		}

		return developerEntry;
	}

	/**
	 * Returns the developer entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the developer entry
	 * @return the developer entry, or <code>null</code> if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DeveloperEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the developer entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param developerEntryId the primary key of the developer entry
	 * @return the developer entry, or <code>null</code> if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByPrimaryKey(long developerEntryId)
		throws SystemException {
		DeveloperEntry developerEntry = (DeveloperEntry)EntityCacheUtil.getResult(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
				DeveloperEntryImpl.class, developerEntryId);

		if (developerEntry == _nullDeveloperEntry) {
			return null;
		}

		if (developerEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				developerEntry = (DeveloperEntry)session.get(DeveloperEntryImpl.class,
						Long.valueOf(developerEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (developerEntry != null) {
					cacheResult(developerEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(DeveloperEntryModelImpl.ENTITY_CACHE_ENABLED,
						DeveloperEntryImpl.class, developerEntryId,
						_nullDeveloperEntry);
				}

				closeSession(session);
			}
		}

		return developerEntry;
	}

	/**
	 * Returns all the developer entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the developer entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DeveloperEntry developerEntry : list) {
				if ((userId != developerEntry.getUserId())) {
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

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

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

				list = (List<DeveloperEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first developer entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByUserId_First(userId,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the first developer entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DeveloperEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last developer entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByUserId_Last(userId,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the last developer entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<DeveloperEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the developer entries before and after the current developer entry in the ordered set where userId = &#63;.
	 *
	 * @param developerEntryId the primary key of the current developer entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry[] findByUserId_PrevAndNext(long developerEntryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByPrimaryKey(developerEntryId);

		Session session = null;

		try {
			session = openSession();

			DeveloperEntry[] array = new DeveloperEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, developerEntry, userId,
					orderByComparator, true);

			array[1] = developerEntry;

			array[2] = getByUserId_PrevAndNext(session, developerEntry, userId,
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

	protected DeveloperEntry getByUserId_PrevAndNext(Session session,
		DeveloperEntry developerEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(developerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeveloperEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the developer entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchDeveloperEntryException} if it could not be found.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByDossieraAccountKey(dossieraAccountKey);

		if (developerEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossieraAccountKey=");
			msg.append(dossieraAccountKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDeveloperEntryException(msg.toString());
		}

		return developerEntry;
	}

	/**
	 * Returns the developer entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByDossieraAccountKey(String dossieraAccountKey)
		throws SystemException {
		return fetchByDossieraAccountKey(dossieraAccountKey, true);
	}

	/**
	 * Returns the developer entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByDossieraAccountKey(String dossieraAccountKey,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					finderArgs, this);
		}

		if (result instanceof DeveloperEntry) {
			DeveloperEntry developerEntry = (DeveloperEntry)result;

			if (!Validator.equals(dossieraAccountKey,
						developerEntry.getDossieraAccountKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

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

				List<DeveloperEntry> list = q.list();

				result = list;

				DeveloperEntry developerEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
						finderArgs, list);
				}
				else {
					developerEntry = list.get(0);

					cacheResult(developerEntry);

					if ((developerEntry.getDossieraAccountKey() == null) ||
							!developerEntry.getDossieraAccountKey()
											   .equals(dossieraAccountKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
							finderArgs, developerEntry);
					}
				}

				return developerEntry;
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
				return (DeveloperEntry)result;
			}
		}
	}

	/**
	 * Returns all the developer entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByStatus(int status)
		throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the developer entries where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByStatus(int status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByStatus(int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DeveloperEntry developerEntry : list) {
				if ((status != developerEntry.getStatus())) {
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

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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

				qPos.add(status);

				list = (List<DeveloperEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first developer entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByStatus_First(int status,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByStatus_First(status,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the first developer entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByStatus_First(int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<DeveloperEntry> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last developer entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByStatus_Last(int status,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByStatus_Last(status,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the last developer entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByStatus_Last(int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		List<DeveloperEntry> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the developer entries before and after the current developer entry in the ordered set where status = &#63;.
	 *
	 * @param developerEntryId the primary key of the current developer entry
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry[] findByStatus_PrevAndNext(long developerEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByPrimaryKey(developerEntryId);

		Session session = null;

		try {
			session = openSession();

			DeveloperEntry[] array = new DeveloperEntryImpl[3];

			array[0] = getByStatus_PrevAndNext(session, developerEntry, status,
					orderByComparator, true);

			array[1] = developerEntry;

			array[2] = getByStatus_PrevAndNext(session, developerEntry, status,
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

	protected DeveloperEntry getByStatus_PrevAndNext(Session session,
		DeveloperEntry developerEntry, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(developerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeveloperEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the developer entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUI_T(long userId, int type)
		throws SystemException {
		return findByUI_T(userId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the developer entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUI_T(long userId, int type, int start,
		int end) throws SystemException {
		return findByUI_T(userId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUI_T(long userId, int type, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T;
			finderArgs = new Object[] { userId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UI_T;
			finderArgs = new Object[] {
					userId, type,
					
					start, end, orderByComparator
				};
		}

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DeveloperEntry developerEntry : list) {
				if ((userId != developerEntry.getUserId()) ||
						(type != developerEntry.getType())) {
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

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_UI_T_USERID_2);

			query.append(_FINDER_COLUMN_UI_T_TYPE_2);

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

				qPos.add(type);

				list = (List<DeveloperEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first developer entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByUI_T_First(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByUI_T_First(userId, type,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the first developer entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByUI_T_First(long userId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<DeveloperEntry> list = findByUI_T(userId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last developer entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByUI_T_Last(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByUI_T_Last(userId, type,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the last developer entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByUI_T_Last(long userId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUI_T(userId, type);

		List<DeveloperEntry> list = findByUI_T(userId, type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the developer entries before and after the current developer entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * @param developerEntryId the primary key of the current developer entry
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry[] findByUI_T_PrevAndNext(long developerEntryId,
		long userId, int type, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByPrimaryKey(developerEntryId);

		Session session = null;

		try {
			session = openSession();

			DeveloperEntry[] array = new DeveloperEntryImpl[3];

			array[0] = getByUI_T_PrevAndNext(session, developerEntry, userId,
					type, orderByComparator, true);

			array[1] = developerEntry;

			array[2] = getByUI_T_PrevAndNext(session, developerEntry, userId,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeveloperEntry getByUI_T_PrevAndNext(Session session,
		DeveloperEntry developerEntry, long userId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

		query.append(_FINDER_COLUMN_UI_T_USERID_2);

		query.append(_FINDER_COLUMN_UI_T_TYPE_2);

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

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(developerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeveloperEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the developer entries where domainName = &#63; and domainStatus = &#63;.
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @return the matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByDN_DS(String domainName, int domainStatus)
		throws SystemException {
		return findByDN_DS(domainName, domainStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the developer entries where domainName = &#63; and domainStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByDN_DS(String domainName,
		int domainStatus, int start, int end) throws SystemException {
		return findByDN_DS(domainName, domainStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries where domainName = &#63; and domainStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByDN_DS(String domainName,
		int domainStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DN_DS;
			finderArgs = new Object[] { domainName, domainStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DN_DS;
			finderArgs = new Object[] {
					domainName, domainStatus,
					
					start, end, orderByComparator
				};
		}

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DeveloperEntry developerEntry : list) {
				if (!Validator.equals(domainName, developerEntry.getDomainName()) ||
						(domainStatus != developerEntry.getDomainStatus())) {
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

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

			if (domainName == null) {
				query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_1);
			}
			else {
				if (domainName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_DN_DS_DOMAINSTATUS_2);

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

				if (domainName != null) {
					qPos.add(domainName);
				}

				qPos.add(domainStatus);

				list = (List<DeveloperEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByDN_DS_First(String domainName,
		int domainStatus, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByDN_DS_First(domainName,
				domainStatus, orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("domainName=");
		msg.append(domainName);

		msg.append(", domainStatus=");
		msg.append(domainStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the first developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByDN_DS_First(String domainName,
		int domainStatus, OrderByComparator orderByComparator)
		throws SystemException {
		List<DeveloperEntry> list = findByDN_DS(domainName, domainStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByDN_DS_Last(String domainName, int domainStatus,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByDN_DS_Last(domainName,
				domainStatus, orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("domainName=");
		msg.append(domainName);

		msg.append(", domainStatus=");
		msg.append(domainStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the last developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByDN_DS_Last(String domainName,
		int domainStatus, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDN_DS(domainName, domainStatus);

		List<DeveloperEntry> list = findByDN_DS(domainName, domainStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the developer entries before and after the current developer entry in the ordered set where domainName = &#63; and domainStatus = &#63;.
	 *
	 * @param developerEntryId the primary key of the current developer entry
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry[] findByDN_DS_PrevAndNext(long developerEntryId,
		String domainName, int domainStatus, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByPrimaryKey(developerEntryId);

		Session session = null;

		try {
			session = openSession();

			DeveloperEntry[] array = new DeveloperEntryImpl[3];

			array[0] = getByDN_DS_PrevAndNext(session, developerEntry,
					domainName, domainStatus, orderByComparator, true);

			array[1] = developerEntry;

			array[2] = getByDN_DS_PrevAndNext(session, developerEntry,
					domainName, domainStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeveloperEntry getByDN_DS_PrevAndNext(Session session,
		DeveloperEntry developerEntry, String domainName, int domainStatus,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

		if (domainName == null) {
			query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_1);
		}
		else {
			if (domainName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_DN_DS_DOMAINSTATUS_2);

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

		if (domainName != null) {
			qPos.add(domainName);
		}

		qPos.add(domainStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(developerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeveloperEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the developer entries where type = &#63; and status = &#63;.
	 *
	 * @param type the type
	 * @param status the status
	 * @return the matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByT_S(int type, int status)
		throws SystemException {
		return findByT_S(type, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the developer entries where type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByT_S(int type, int status, int start,
		int end) throws SystemException {
		return findByT_S(type, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries where type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByT_S(int type, int status, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_S;
			finderArgs = new Object[] { type, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_S;
			finderArgs = new Object[] {
					type, status,
					
					start, end, orderByComparator
				};
		}

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DeveloperEntry developerEntry : list) {
				if ((type != developerEntry.getType()) ||
						(status != developerEntry.getStatus())) {
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

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_T_S_STATUS_2);

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

				qPos.add(type);

				qPos.add(status);

				list = (List<DeveloperEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first developer entry in the ordered set where type = &#63; and status = &#63;.
	 *
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByT_S_First(int type, int status,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByT_S_First(type, status,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the first developer entry in the ordered set where type = &#63; and status = &#63;.
	 *
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByT_S_First(int type, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<DeveloperEntry> list = findByT_S(type, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last developer entry in the ordered set where type = &#63; and status = &#63;.
	 *
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByT_S_Last(int type, int status,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByT_S_Last(type, status,
				orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the last developer entry in the ordered set where type = &#63; and status = &#63;.
	 *
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByT_S_Last(int type, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByT_S(type, status);

		List<DeveloperEntry> list = findByT_S(type, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the developer entries before and after the current developer entry in the ordered set where type = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the primary key of the current developer entry
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry[] findByT_S_PrevAndNext(long developerEntryId,
		int type, int status, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByPrimaryKey(developerEntryId);

		Session session = null;

		try {
			session = openSession();

			DeveloperEntry[] array = new DeveloperEntryImpl[3];

			array[0] = getByT_S_PrevAndNext(session, developerEntry, type,
					status, orderByComparator, true);

			array[1] = developerEntry;

			array[2] = getByT_S_PrevAndNext(session, developerEntry, type,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeveloperEntry getByT_S_PrevAndNext(Session session,
		DeveloperEntry developerEntry, int type, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_S_TYPE_2);

		query.append(_FINDER_COLUMN_T_S_STATUS_2);

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

		qPos.add(type);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(developerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeveloperEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the developer entries where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @return the matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUI_T_S(long userId, int type, int status)
		throws SystemException {
		return findByUI_T_S(userId, type, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the developer entries where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUI_T_S(long userId, int type, int status,
		int start, int end) throws SystemException {
		return findByUI_T_S(userId, type, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByUI_T_S(long userId, int type, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UI_T_S;
			finderArgs = new Object[] { userId, type, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UI_T_S;
			finderArgs = new Object[] {
					userId, type, status,
					
					start, end, orderByComparator
				};
		}

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DeveloperEntry developerEntry : list) {
				if ((userId != developerEntry.getUserId()) ||
						(type != developerEntry.getType()) ||
						(status != developerEntry.getStatus())) {
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

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_UI_T_S_USERID_2);

			query.append(_FINDER_COLUMN_UI_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_UI_T_S_STATUS_2);

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

				qPos.add(type);

				qPos.add(status);

				list = (List<DeveloperEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByUI_T_S_First(long userId, int type, int status,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByUI_T_S_First(userId, type,
				status, orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the first developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByUI_T_S_First(long userId, int type,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<DeveloperEntry> list = findByUI_T_S(userId, type, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByUI_T_S_Last(long userId, int type, int status,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByUI_T_S_Last(userId, type,
				status, orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the last developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByUI_T_S_Last(long userId, int type, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUI_T_S(userId, type, status);

		List<DeveloperEntry> list = findByUI_T_S(userId, type, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the developer entries before and after the current developer entry in the ordered set where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the primary key of the current developer entry
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry[] findByUI_T_S_PrevAndNext(long developerEntryId,
		long userId, int type, int status, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByPrimaryKey(developerEntryId);

		Session session = null;

		try {
			session = openSession();

			DeveloperEntry[] array = new DeveloperEntryImpl[3];

			array[0] = getByUI_T_S_PrevAndNext(session, developerEntry, userId,
					type, status, orderByComparator, true);

			array[1] = developerEntry;

			array[2] = getByUI_T_S_PrevAndNext(session, developerEntry, userId,
					type, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeveloperEntry getByUI_T_S_PrevAndNext(Session session,
		DeveloperEntry developerEntry, long userId, int type, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

		query.append(_FINDER_COLUMN_UI_T_S_USERID_2);

		query.append(_FINDER_COLUMN_UI_T_S_TYPE_2);

		query.append(_FINDER_COLUMN_UI_T_S_STATUS_2);

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

		qPos.add(type);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(developerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeveloperEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @return the matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByLEN_T_S(String legalEntityName, int type,
		int status) throws SystemException {
		return findByLEN_T_S(legalEntityName, type, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByLEN_T_S(String legalEntityName, int type,
		int status, int start, int end) throws SystemException {
		return findByLEN_T_S(legalEntityName, type, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findByLEN_T_S(String legalEntityName, int type,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEN_T_S;
			finderArgs = new Object[] { legalEntityName, type, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LEN_T_S;
			finderArgs = new Object[] {
					legalEntityName, type, status,
					
					start, end, orderByComparator
				};
		}

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DeveloperEntry developerEntry : list) {
				if (!Validator.equals(legalEntityName,
							developerEntry.getLegalEntityName()) ||
						(type != developerEntry.getType()) ||
						(status != developerEntry.getStatus())) {
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

			query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

			if (legalEntityName == null) {
				query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_1);
			}
			else {
				if (legalEntityName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_LEN_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_LEN_T_S_STATUS_2);

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

				if (legalEntityName != null) {
					qPos.add(legalEntityName);
				}

				qPos.add(type);

				qPos.add(status);

				list = (List<DeveloperEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByLEN_T_S_First(String legalEntityName, int type,
		int status, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByLEN_T_S_First(legalEntityName,
				type, status, orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("legalEntityName=");
		msg.append(legalEntityName);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the first developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByLEN_T_S_First(String legalEntityName,
		int type, int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<DeveloperEntry> list = findByLEN_T_S(legalEntityName, type,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry findByLEN_T_S_Last(String legalEntityName, int type,
		int status, OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = fetchByLEN_T_S_Last(legalEntityName,
				type, status, orderByComparator);

		if (developerEntry != null) {
			return developerEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("legalEntityName=");
		msg.append(legalEntityName);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeveloperEntryException(msg.toString());
	}

	/**
	 * Returns the last developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching developer entry, or <code>null</code> if a matching developer entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry fetchByLEN_T_S_Last(String legalEntityName, int type,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByLEN_T_S(legalEntityName, type, status);

		List<DeveloperEntry> list = findByLEN_T_S(legalEntityName, type,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the developer entries before and after the current developer entry in the ordered set where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param developerEntryId the primary key of the current developer entry
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next developer entry
	 * @throws com.liferay.osb.NoSuchDeveloperEntryException if a developer entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry[] findByLEN_T_S_PrevAndNext(long developerEntryId,
		String legalEntityName, int type, int status,
		OrderByComparator orderByComparator)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByPrimaryKey(developerEntryId);

		Session session = null;

		try {
			session = openSession();

			DeveloperEntry[] array = new DeveloperEntryImpl[3];

			array[0] = getByLEN_T_S_PrevAndNext(session, developerEntry,
					legalEntityName, type, status, orderByComparator, true);

			array[1] = developerEntry;

			array[2] = getByLEN_T_S_PrevAndNext(session, developerEntry,
					legalEntityName, type, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeveloperEntry getByLEN_T_S_PrevAndNext(Session session,
		DeveloperEntry developerEntry, String legalEntityName, int type,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DEVELOPERENTRY_WHERE);

		if (legalEntityName == null) {
			query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_1);
		}
		else {
			if (legalEntityName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_LEN_T_S_TYPE_2);

		query.append(_FINDER_COLUMN_LEN_T_S_STATUS_2);

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

		if (legalEntityName != null) {
			qPos.add(legalEntityName);
		}

		qPos.add(type);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(developerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeveloperEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the developer entries.
	 *
	 * @return the developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the developer entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @return the range of developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the developer entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of developer entries
	 * @param end the upper bound of the range of developer entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DeveloperEntry> findAll(int start, int end,
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

		List<DeveloperEntry> list = (List<DeveloperEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DEVELOPERENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DEVELOPERENTRY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<DeveloperEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<DeveloperEntry>)QueryUtil.list(q,
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
	 * Removes all the developer entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (DeveloperEntry developerEntry : findByUserId(userId)) {
			remove(developerEntry);
		}
	}

	/**
	 * Removes the developer entry where dossieraAccountKey = &#63; from the database.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the developer entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DeveloperEntry removeByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchDeveloperEntryException, SystemException {
		DeveloperEntry developerEntry = findByDossieraAccountKey(dossieraAccountKey);

		return remove(developerEntry);
	}

	/**
	 * Removes all the developer entries where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStatus(int status) throws SystemException {
		for (DeveloperEntry developerEntry : findByStatus(status)) {
			remove(developerEntry);
		}
	}

	/**
	 * Removes all the developer entries where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUI_T(long userId, int type) throws SystemException {
		for (DeveloperEntry developerEntry : findByUI_T(userId, type)) {
			remove(developerEntry);
		}
	}

	/**
	 * Removes all the developer entries where domainName = &#63; and domainStatus = &#63; from the database.
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDN_DS(String domainName, int domainStatus)
		throws SystemException {
		for (DeveloperEntry developerEntry : findByDN_DS(domainName,
				domainStatus)) {
			remove(developerEntry);
		}
	}

	/**
	 * Removes all the developer entries where type = &#63; and status = &#63; from the database.
	 *
	 * @param type the type
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_S(int type, int status) throws SystemException {
		for (DeveloperEntry developerEntry : findByT_S(type, status)) {
			remove(developerEntry);
		}
	}

	/**
	 * Removes all the developer entries where userId = &#63; and type = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUI_T_S(long userId, int type, int status)
		throws SystemException {
		for (DeveloperEntry developerEntry : findByUI_T_S(userId, type, status)) {
			remove(developerEntry);
		}
	}

	/**
	 * Removes all the developer entries where legalEntityName = &#63; and type = &#63; and status = &#63; from the database.
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLEN_T_S(String legalEntityName, int type, int status)
		throws SystemException {
		for (DeveloperEntry developerEntry : findByLEN_T_S(legalEntityName,
				type, status)) {
			remove(developerEntry);
		}
	}

	/**
	 * Removes all the developer entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DeveloperEntry developerEntry : findAll()) {
			remove(developerEntry);
		}
	}

	/**
	 * Returns the number of developer entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of developer entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDossieraAccountKey(String dossieraAccountKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

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
	 * Returns the number of developer entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStatus(int status) throws SystemException {
		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_STATUS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_STATUS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of developer entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUI_T(long userId, int type) throws SystemException {
		Object[] finderArgs = new Object[] { userId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_UI_T_USERID_2);

			query.append(_FINDER_COLUMN_UI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of developer entries where domainName = &#63; and domainStatus = &#63;.
	 *
	 * @param domainName the domain name
	 * @param domainStatus the domain status
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDN_DS(String domainName, int domainStatus)
		throws SystemException {
		Object[] finderArgs = new Object[] { domainName, domainStatus };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DN_DS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

			if (domainName == null) {
				query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_1);
			}
			else {
				if (domainName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_DN_DS_DOMAINNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_DN_DS_DOMAINSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (domainName != null) {
					qPos.add(domainName);
				}

				qPos.add(domainStatus);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DN_DS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of developer entries where type = &#63; and status = &#63;.
	 *
	 * @param type the type
	 * @param status the status
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_S(int type, int status) throws SystemException {
		Object[] finderArgs = new Object[] { type, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_T_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_T_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of developer entries where userId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param status the status
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUI_T_S(long userId, int type, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, type, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UI_T_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

			query.append(_FINDER_COLUMN_UI_T_S_USERID_2);

			query.append(_FINDER_COLUMN_UI_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_UI_T_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(type);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UI_T_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of developer entries where legalEntityName = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param legalEntityName the legal entity name
	 * @param type the type
	 * @param status the status
	 * @return the number of matching developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLEN_T_S(String legalEntityName, int type, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { legalEntityName, type, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_LEN_T_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DEVELOPERENTRY_WHERE);

			if (legalEntityName == null) {
				query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_1);
			}
			else {
				if (legalEntityName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_LEN_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_LEN_T_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (legalEntityName != null) {
					qPos.add(legalEntityName);
				}

				qPos.add(type);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_LEN_T_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of developer entries.
	 *
	 * @return the number of developer entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DEVELOPERENTRY);

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
	 * Initializes the developer entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.DeveloperEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DeveloperEntry>> listenersList = new ArrayList<ModelListener<DeveloperEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<DeveloperEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DeveloperEntryImpl.class.getName());
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
	@BeanReference(type = RegionPersistence.class)
	protected RegionPersistence regionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	@BeanReference(type = MBMessagePersistence.class)
	protected MBMessagePersistence mbMessagePersistence;
	private static final String _SQL_SELECT_DEVELOPERENTRY = "SELECT developerEntry FROM DeveloperEntry developerEntry";
	private static final String _SQL_SELECT_DEVELOPERENTRY_WHERE = "SELECT developerEntry FROM DeveloperEntry developerEntry WHERE ";
	private static final String _SQL_COUNT_DEVELOPERENTRY = "SELECT COUNT(developerEntry) FROM DeveloperEntry developerEntry";
	private static final String _SQL_COUNT_DEVELOPERENTRY_WHERE = "SELECT COUNT(developerEntry) FROM DeveloperEntry developerEntry WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "developerEntry.userId = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1 =
		"developerEntry.dossieraAccountKey IS NULL";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2 =
		"developerEntry.dossieraAccountKey = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3 =
		"(developerEntry.dossieraAccountKey IS NULL OR developerEntry.dossieraAccountKey = ?)";
	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "developerEntry.status = ?";
	private static final String _FINDER_COLUMN_UI_T_USERID_2 = "developerEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_UI_T_TYPE_2 = "developerEntry.type = ?";
	private static final String _FINDER_COLUMN_DN_DS_DOMAINNAME_1 = "developerEntry.domainName IS NULL AND ";
	private static final String _FINDER_COLUMN_DN_DS_DOMAINNAME_2 = "developerEntry.domainName = ? AND ";
	private static final String _FINDER_COLUMN_DN_DS_DOMAINNAME_3 = "(developerEntry.domainName IS NULL OR developerEntry.domainName = ?) AND ";
	private static final String _FINDER_COLUMN_DN_DS_DOMAINSTATUS_2 = "developerEntry.domainStatus = ?";
	private static final String _FINDER_COLUMN_T_S_TYPE_2 = "developerEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_T_S_STATUS_2 = "developerEntry.status = ?";
	private static final String _FINDER_COLUMN_UI_T_S_USERID_2 = "developerEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_UI_T_S_TYPE_2 = "developerEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_UI_T_S_STATUS_2 = "developerEntry.status = ?";
	private static final String _FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_1 = "developerEntry.legalEntityName IS NULL AND ";
	private static final String _FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_2 = "developerEntry.legalEntityName = ? AND ";
	private static final String _FINDER_COLUMN_LEN_T_S_LEGALENTITYNAME_3 = "(developerEntry.legalEntityName IS NULL OR developerEntry.legalEntityName = ?) AND ";
	private static final String _FINDER_COLUMN_LEN_T_S_TYPE_2 = "developerEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_LEN_T_S_STATUS_2 = "developerEntry.status = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "developerEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeveloperEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DeveloperEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DeveloperEntryPersistenceImpl.class);
	private static DeveloperEntry _nullDeveloperEntry = new DeveloperEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DeveloperEntry> toCacheModel() {
				return _nullDeveloperEntryCacheModel;
			}
		};

	private static CacheModel<DeveloperEntry> _nullDeveloperEntryCacheModel = new CacheModel<DeveloperEntry>() {
			public DeveloperEntry toEntityModel() {
				return _nullDeveloperEntry;
			}
		};
}
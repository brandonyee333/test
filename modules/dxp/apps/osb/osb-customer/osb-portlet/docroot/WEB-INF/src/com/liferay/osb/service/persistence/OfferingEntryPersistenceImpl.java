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

import com.liferay.osb.NoSuchOfferingEntryException;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.impl.OfferingEntryImpl;
import com.liferay.osb.model.impl.OfferingEntryModelImpl;

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
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the offering entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryPersistence
 * @see OfferingEntryUtil
 * @generated
 */
public class OfferingEntryPersistenceImpl extends BasePersistenceImpl<OfferingEntry>
	implements OfferingEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OfferingEntryUtil} to access the offering entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OfferingEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEntryId", new String[] { Long.class.getName() },
			OfferingEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByOrderEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOrderEntryId", new String[] { Long.class.getName() },
			OfferingEntryModelImpl.ORDERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERENTRYID = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrderEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VERSION = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByVersion",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VERSION =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByVersion", new String[] { Integer.class.getName() },
			OfferingEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VERSION = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByVersion",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTENDDATE =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySupportEndDate",
			new String[] {
				Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTENDDATE =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySupportEndDate", new String[] { Date.class.getName() },
			OfferingEntryModelImpl.SUPPORTENDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTENDDATE = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportEndDate",
			new String[] { Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatus",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByStatus", new String[] { Integer.class.getName() },
			OfferingEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_S = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			OfferingEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			OfferingEntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_S = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_OEI_T =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_AEI_OEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T =
		new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_AEI_OEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			OfferingEntryModelImpl.USERID_COLUMN_BITMASK |
			OfferingEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			OfferingEntryModelImpl.ORDERENTRYID_COLUMN_BITMASK |
			OfferingEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI_OEI_T = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI_OEI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED,
			OfferingEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the offering entry in the entity cache if it is enabled.
	 *
	 * @param offeringEntry the offering entry
	 */
	public void cacheResult(OfferingEntry offeringEntry) {
		EntityCacheUtil.putResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryImpl.class, offeringEntry.getPrimaryKey(),
			offeringEntry);

		offeringEntry.resetOriginalValues();
	}

	/**
	 * Caches the offering entries in the entity cache if it is enabled.
	 *
	 * @param offeringEntries the offering entries
	 */
	public void cacheResult(List<OfferingEntry> offeringEntries) {
		for (OfferingEntry offeringEntry : offeringEntries) {
			if (EntityCacheUtil.getResult(
						OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
						OfferingEntryImpl.class, offeringEntry.getPrimaryKey()) == null) {
				cacheResult(offeringEntry);
			}
			else {
				offeringEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all offering entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OfferingEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OfferingEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the offering entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfferingEntry offeringEntry) {
		EntityCacheUtil.removeResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryImpl.class, offeringEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OfferingEntry> offeringEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfferingEntry offeringEntry : offeringEntries) {
			EntityCacheUtil.removeResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
				OfferingEntryImpl.class, offeringEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	 *
	 * @param offeringEntryId the primary key for the new offering entry
	 * @return the new offering entry
	 */
	public OfferingEntry create(long offeringEntryId) {
		OfferingEntry offeringEntry = new OfferingEntryImpl();

		offeringEntry.setNew(true);
		offeringEntry.setPrimaryKey(offeringEntryId);

		return offeringEntry;
	}

	/**
	 * Removes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offeringEntryId the primary key of the offering entry
	 * @return the offering entry that was removed
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry remove(long offeringEntryId)
		throws NoSuchOfferingEntryException, SystemException {
		return remove(Long.valueOf(offeringEntryId));
	}

	/**
	 * Removes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the offering entry
	 * @return the offering entry that was removed
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingEntry remove(Serializable primaryKey)
		throws NoSuchOfferingEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OfferingEntry offeringEntry = (OfferingEntry)session.get(OfferingEntryImpl.class,
					primaryKey);

			if (offeringEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfferingEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(offeringEntry);
		}
		catch (NoSuchOfferingEntryException nsee) {
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
	protected OfferingEntry removeImpl(OfferingEntry offeringEntry)
		throws SystemException {
		offeringEntry = toUnwrappedModel(offeringEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, offeringEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(offeringEntry);

		return offeringEntry;
	}

	@Override
	public OfferingEntry updateImpl(
		com.liferay.osb.model.OfferingEntry offeringEntry, boolean merge)
		throws SystemException {
		offeringEntry = toUnwrappedModel(offeringEntry);

		boolean isNew = offeringEntry.isNew();

		OfferingEntryModelImpl offeringEntryModelImpl = (OfferingEntryModelImpl)offeringEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, offeringEntry, merge);

			offeringEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OfferingEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getOriginalOrderEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getOrderEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VERSION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(offeringEntryModelImpl.getOriginalVersion())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VERSION, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VERSION,
					args);

				args = new Object[] {
						Integer.valueOf(offeringEntryModelImpl.getVersion())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_VERSION, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VERSION,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTENDDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						offeringEntryModelImpl.getOriginalSupportEndDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTENDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTENDDATE,
					args);

				args = new Object[] { offeringEntryModelImpl.getSupportEndDate() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTENDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTENDDATE,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(offeringEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] {
						Integer.valueOf(offeringEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getOriginalAccountEntryId()),
						Integer.valueOf(offeringEntryModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S,
					args);

				args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getAccountEntryId()),
						Integer.valueOf(offeringEntryModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S,
					args);
			}

			if ((offeringEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getOriginalUserId()),
						Long.valueOf(offeringEntryModelImpl.getOriginalAccountEntryId()),
						Long.valueOf(offeringEntryModelImpl.getOriginalOrderEntryId()),
						Integer.valueOf(offeringEntryModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI_OEI_T,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T,
					args);

				args = new Object[] {
						Long.valueOf(offeringEntryModelImpl.getUserId()),
						Long.valueOf(offeringEntryModelImpl.getAccountEntryId()),
						Long.valueOf(offeringEntryModelImpl.getOrderEntryId()),
						Integer.valueOf(offeringEntryModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI_OEI_T,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T,
					args);
			}
		}

		EntityCacheUtil.putResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
			OfferingEntryImpl.class, offeringEntry.getPrimaryKey(),
			offeringEntry);

		return offeringEntry;
	}

	protected OfferingEntry toUnwrappedModel(OfferingEntry offeringEntry) {
		if (offeringEntry instanceof OfferingEntryImpl) {
			return offeringEntry;
		}

		OfferingEntryImpl offeringEntryImpl = new OfferingEntryImpl();

		offeringEntryImpl.setNew(offeringEntry.isNew());
		offeringEntryImpl.setPrimaryKey(offeringEntry.getPrimaryKey());

		offeringEntryImpl.setOfferingEntryId(offeringEntry.getOfferingEntryId());
		offeringEntryImpl.setUserId(offeringEntry.getUserId());
		offeringEntryImpl.setUserName(offeringEntry.getUserName());
		offeringEntryImpl.setCreateDate(offeringEntry.getCreateDate());
		offeringEntryImpl.setModifiedDate(offeringEntry.getModifiedDate());
		offeringEntryImpl.setAccountEntryId(offeringEntry.getAccountEntryId());
		offeringEntryImpl.setOrderEntryId(offeringEntry.getOrderEntryId());
		offeringEntryImpl.setProductEntryId(offeringEntry.getProductEntryId());
		offeringEntryImpl.setSupportResponseId(offeringEntry.getSupportResponseId());
		offeringEntryImpl.setProductDescription(offeringEntry.getProductDescription());
		offeringEntryImpl.setType(offeringEntry.getType());
		offeringEntryImpl.setVersion(offeringEntry.getVersion());
		offeringEntryImpl.setPlatform(offeringEntry.getPlatform());
		offeringEntryImpl.setPlatformVersion(offeringEntry.getPlatformVersion());
		offeringEntryImpl.setLicenses(offeringEntry.isLicenses());
		offeringEntryImpl.setLicenseLifetime(offeringEntry.getLicenseLifetime());
		offeringEntryImpl.setMaxConcurrentUsers(offeringEntry.getMaxConcurrentUsers());
		offeringEntryImpl.setMaxUsers(offeringEntry.getMaxUsers());
		offeringEntryImpl.setSupportTickets(offeringEntry.isSupportTickets());
		offeringEntryImpl.setSupportLifetime(offeringEntry.getSupportLifetime());
		offeringEntryImpl.setSupportEndDate(offeringEntry.getSupportEndDate());
		offeringEntryImpl.setSizing(offeringEntry.getSizing());
		offeringEntryImpl.setQuantity(offeringEntry.getQuantity());
		offeringEntryImpl.setStatus(offeringEntry.getStatus());

		return offeringEntryImpl;
	}

	/**
	 * Returns the offering entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering entry
	 * @return the offering entry
	 * @throws com.liferay.portal.NoSuchModelException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the offering entry with the primary key or throws a {@link com.liferay.osb.NoSuchOfferingEntryException} if it could not be found.
	 *
	 * @param offeringEntryId the primary key of the offering entry
	 * @return the offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByPrimaryKey(long offeringEntryId)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByPrimaryKey(offeringEntryId);

		if (offeringEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + offeringEntryId);
			}

			throw new NoSuchOfferingEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				offeringEntryId);
		}

		return offeringEntry;
	}

	/**
	 * Returns the offering entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering entry
	 * @return the offering entry, or <code>null</code> if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the offering entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offeringEntryId the primary key of the offering entry
	 * @return the offering entry, or <code>null</code> if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByPrimaryKey(long offeringEntryId)
		throws SystemException {
		OfferingEntry offeringEntry = (OfferingEntry)EntityCacheUtil.getResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
				OfferingEntryImpl.class, offeringEntryId);

		if (offeringEntry == _nullOfferingEntry) {
			return null;
		}

		if (offeringEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				offeringEntry = (OfferingEntry)session.get(OfferingEntryImpl.class,
						Long.valueOf(offeringEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (offeringEntry != null) {
					cacheResult(offeringEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OfferingEntryModelImpl.ENTITY_CACHE_ENABLED,
						OfferingEntryImpl.class, offeringEntryId,
						_nullOfferingEntry);
				}

				closeSession(session);
			}
		}

		return offeringEntry;
	}

	/**
	 * Returns all the offering entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] { accountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] {
					accountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingEntry offeringEntry : list) {
				if ((accountEntryId != offeringEntry.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingEntry> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<OfferingEntry> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry[] findByAccountEntryId_PrevAndNext(
		long offeringEntryId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, offeringEntry,
					accountEntryId, orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByAccountEntryId_PrevAndNext(session, offeringEntry,
					accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingEntry getByAccountEntryId_PrevAndNext(Session session,
		OfferingEntry offeringEntry, long accountEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering entries where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @return the matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByOrderEntryId(long orderEntryId)
		throws SystemException {
		return findByOrderEntryId(orderEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where orderEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByOrderEntryId(long orderEntryId, int start,
		int end) throws SystemException {
		return findByOrderEntryId(orderEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where orderEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByOrderEntryId(long orderEntryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID;
			finderArgs = new Object[] { orderEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERENTRYID;
			finderArgs = new Object[] {
					orderEntryId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingEntry offeringEntry : list) {
				if ((orderEntryId != offeringEntry.getOrderEntryId())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByOrderEntryId_First(long orderEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByOrderEntryId_First(orderEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByOrderEntryId_First(long orderEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingEntry> list = findByOrderEntryId(orderEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByOrderEntryId_Last(long orderEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByOrderEntryId_Last(orderEntryId,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByOrderEntryId_Last(long orderEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOrderEntryId(orderEntryId);

		List<OfferingEntry> list = findByOrderEntryId(orderEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where orderEntryId = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry[] findByOrderEntryId_PrevAndNext(
		long offeringEntryId, long orderEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByOrderEntryId_PrevAndNext(session, offeringEntry,
					orderEntryId, orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByOrderEntryId_PrevAndNext(session, offeringEntry,
					orderEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingEntry getByOrderEntryId_PrevAndNext(Session session,
		OfferingEntry offeringEntry, long orderEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(orderEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering entries where version = &#63;.
	 *
	 * @param version the version
	 * @return the matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByVersion(int version)
		throws SystemException {
		return findByVersion(version, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param version the version
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByVersion(int version, int start, int end)
		throws SystemException {
		return findByVersion(version, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param version the version
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByVersion(int version, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VERSION;
			finderArgs = new Object[] { version };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VERSION;
			finderArgs = new Object[] { version, start, end, orderByComparator };
		}

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingEntry offeringEntry : list) {
				if ((version != offeringEntry.getVersion())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_VERSION_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(version);

				list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where version = &#63;.
	 *
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByVersion_First(int version,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByVersion_First(version,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("version=");
		msg.append(version);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where version = &#63;.
	 *
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByVersion_First(int version,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingEntry> list = findByVersion(version, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where version = &#63;.
	 *
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByVersion_Last(int version,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByVersion_Last(version,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("version=");
		msg.append(version);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where version = &#63;.
	 *
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByVersion_Last(int version,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByVersion(version);

		List<OfferingEntry> list = findByVersion(version, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where version = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry[] findByVersion_PrevAndNext(long offeringEntryId,
		int version, OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByVersion_PrevAndNext(session, offeringEntry,
					version, orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByVersion_PrevAndNext(session, offeringEntry,
					version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingEntry getByVersion_PrevAndNext(Session session,
		OfferingEntry offeringEntry, int version,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_VERSION_VERSION_2);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(version);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering entries where supportEndDate = &#63;.
	 *
	 * @param supportEndDate the support end date
	 * @return the matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findBySupportEndDate(Date supportEndDate)
		throws SystemException {
		return findBySupportEndDate(supportEndDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where supportEndDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportEndDate the support end date
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findBySupportEndDate(Date supportEndDate,
		int start, int end) throws SystemException {
		return findBySupportEndDate(supportEndDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where supportEndDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportEndDate the support end date
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findBySupportEndDate(Date supportEndDate,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTENDDATE;
			finderArgs = new Object[] { supportEndDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTENDDATE;
			finderArgs = new Object[] {
					supportEndDate,
					
					start, end, orderByComparator
				};
		}

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingEntry offeringEntry : list) {
				if (!Validator.equals(supportEndDate,
							offeringEntry.getSupportEndDate())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			if (supportEndDate == null) {
				query.append(_FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (supportEndDate != null) {
					qPos.add(CalendarUtil.getTimestamp(supportEndDate));
				}

				list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where supportEndDate = &#63;.
	 *
	 * @param supportEndDate the support end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findBySupportEndDate_First(Date supportEndDate,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchBySupportEndDate_First(supportEndDate,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportEndDate=");
		msg.append(supportEndDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where supportEndDate = &#63;.
	 *
	 * @param supportEndDate the support end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchBySupportEndDate_First(Date supportEndDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingEntry> list = findBySupportEndDate(supportEndDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where supportEndDate = &#63;.
	 *
	 * @param supportEndDate the support end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findBySupportEndDate_Last(Date supportEndDate,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchBySupportEndDate_Last(supportEndDate,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportEndDate=");
		msg.append(supportEndDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where supportEndDate = &#63;.
	 *
	 * @param supportEndDate the support end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchBySupportEndDate_Last(Date supportEndDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySupportEndDate(supportEndDate);

		List<OfferingEntry> list = findBySupportEndDate(supportEndDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where supportEndDate = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param supportEndDate the support end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry[] findBySupportEndDate_PrevAndNext(
		long offeringEntryId, Date supportEndDate,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getBySupportEndDate_PrevAndNext(session, offeringEntry,
					supportEndDate, orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getBySupportEndDate_PrevAndNext(session, offeringEntry,
					supportEndDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingEntry getBySupportEndDate_PrevAndNext(Session session,
		OfferingEntry offeringEntry, Date supportEndDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		if (supportEndDate == null) {
			query.append(_FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_2);
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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (supportEndDate != null) {
			qPos.add(CalendarUtil.getTimestamp(supportEndDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByStatus(int status)
		throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByStatus(int status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByStatus(int status, int start, int end,
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

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingEntry offeringEntry : list) {
				if ((status != offeringEntry.getStatus())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByStatus_First(int status,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByStatus_First(status,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByStatus_First(int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingEntry> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByStatus_Last(int status,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByStatus_Last(status,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByStatus_Last(int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		List<OfferingEntry> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where status = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry[] findByStatus_PrevAndNext(long offeringEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByStatus_PrevAndNext(session, offeringEntry, status,
					orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByStatus_PrevAndNext(session, offeringEntry, status,
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

	protected OfferingEntry getByStatus_PrevAndNext(Session session,
		OfferingEntry offeringEntry, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

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

		else {
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering entries where accountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @return the matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByAEI_S(long accountEntryId, int status)
		throws SystemException {
		return findByAEI_S(accountEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where accountEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByAEI_S(long accountEntryId, int status,
		int start, int end) throws SystemException {
		return findByAEI_S(accountEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where accountEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByAEI_S(long accountEntryId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_S;
			finderArgs = new Object[] { accountEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_S;
			finderArgs = new Object[] {
					accountEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingEntry offeringEntry : list) {
				if ((accountEntryId != offeringEntry.getAccountEntryId()) ||
						(status != offeringEntry.getStatus())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_AEI_S_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(status);

				list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByAEI_S_First(long accountEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByAEI_S_First(accountEntryId,
				status, orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByAEI_S_First(long accountEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingEntry> list = findByAEI_S(accountEntryId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByAEI_S_Last(long accountEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByAEI_S_Last(accountEntryId, status,
				orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByAEI_S_Last(long accountEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI_S(accountEntryId, status);

		List<OfferingEntry> list = findByAEI_S(accountEntryId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry[] findByAEI_S_PrevAndNext(long offeringEntryId,
		long accountEntryId, int status, OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByAEI_S_PrevAndNext(session, offeringEntry,
					accountEntryId, status, orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByAEI_S_PrevAndNext(session, offeringEntry,
					accountEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingEntry getByAEI_S_PrevAndNext(Session session,
		OfferingEntry offeringEntry, long accountEntryId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_AEI_S_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_AEI_S_STATUS_2);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @return the matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type)
		throws SystemException {
		return findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end)
		throws SystemException {
		return findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_OEI_T;
			finderArgs = new Object[] { userId, accountEntryId, orderEntryId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_OEI_T;
			finderArgs = new Object[] {
					userId, accountEntryId, orderEntryId, type,
					
					start, end, orderByComparator
				};
		}

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingEntry offeringEntry : list) {
				if ((userId != offeringEntry.getUserId()) ||
						(accountEntryId != offeringEntry.getAccountEntryId()) ||
						(orderEntryId != offeringEntry.getOrderEntryId()) ||
						(type != offeringEntry.getType())) {
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

			query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				qPos.add(orderEntryId);

				qPos.add(type);

				list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByU_AEI_OEI_T_First(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByU_AEI_OEI_T_First(userId,
				accountEntryId, orderEntryId, type, orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByU_AEI_OEI_T_First(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingEntry> list = findByU_AEI_OEI_T(userId, accountEntryId,
				orderEntryId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry findByU_AEI_OEI_T_Last(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = fetchByU_AEI_OEI_T_Last(userId,
				accountEntryId, orderEntryId, type, orderByComparator);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingEntryException(msg.toString());
	}

	/**
	 * Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry fetchByU_AEI_OEI_T_Last(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
				type);

		List<OfferingEntry> list = findByU_AEI_OEI_T(userId, accountEntryId,
				orderEntryId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering entries before and after the current offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param offeringEntryId the primary key of the current offering entry
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering entry
	 * @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingEntry[] findByU_AEI_OEI_T_PrevAndNext(long offeringEntryId,
		long userId, long accountEntryId, long orderEntryId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingEntryException, SystemException {
		OfferingEntry offeringEntry = findByPrimaryKey(offeringEntryId);

		Session session = null;

		try {
			session = openSession();

			OfferingEntry[] array = new OfferingEntryImpl[3];

			array[0] = getByU_AEI_OEI_T_PrevAndNext(session, offeringEntry,
					userId, accountEntryId, orderEntryId, type,
					orderByComparator, true);

			array[1] = offeringEntry;

			array[2] = getByU_AEI_OEI_T_PrevAndNext(session, offeringEntry,
					userId, accountEntryId, orderEntryId, type,
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

	protected OfferingEntry getByU_AEI_OEI_T_PrevAndNext(Session session,
		OfferingEntry offeringEntry, long userId, long accountEntryId,
		long orderEntryId, int type, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_USERID_2);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2);

		query.append(_FINDER_COLUMN_U_AEI_OEI_T_TYPE_2);

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
			query.append(OfferingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(accountEntryId);

		qPos.add(orderEntryId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering entries.
	 *
	 * @return the offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @return the range of offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering entries
	 * @param end the upper bound of the range of offering entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingEntry> findAll(int start, int end,
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

		List<OfferingEntry> list = (List<OfferingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OFFERINGENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OFFERINGENTRY.concat(OfferingEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OfferingEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the offering entries where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (OfferingEntry offeringEntry : findByAccountEntryId(accountEntryId)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Removes all the offering entries where orderEntryId = &#63; from the database.
	 *
	 * @param orderEntryId the order entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOrderEntryId(long orderEntryId)
		throws SystemException {
		for (OfferingEntry offeringEntry : findByOrderEntryId(orderEntryId)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Removes all the offering entries where version = &#63; from the database.
	 *
	 * @param version the version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByVersion(int version) throws SystemException {
		for (OfferingEntry offeringEntry : findByVersion(version)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Removes all the offering entries where supportEndDate = &#63; from the database.
	 *
	 * @param supportEndDate the support end date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportEndDate(Date supportEndDate)
		throws SystemException {
		for (OfferingEntry offeringEntry : findBySupportEndDate(supportEndDate)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Removes all the offering entries where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStatus(int status) throws SystemException {
		for (OfferingEntry offeringEntry : findByStatus(status)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Removes all the offering entries where accountEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_S(long accountEntryId, int status)
		throws SystemException {
		for (OfferingEntry offeringEntry : findByAEI_S(accountEntryId, status)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Removes all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type) throws SystemException {
		for (OfferingEntry offeringEntry : findByU_AEI_OEI_T(userId,
				accountEntryId, orderEntryId, type)) {
			remove(offeringEntry);
		}
	}

	/**
	 * Removes all the offering entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OfferingEntry offeringEntry : findAll()) {
			remove(offeringEntry);
		}
	}

	/**
	 * Returns the number of offering entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering entries where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @return the number of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrderEntryId(long orderEntryId) throws SystemException {
		Object[] finderArgs = new Object[] { orderEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering entries where version = &#63;.
	 *
	 * @param version the version
	 * @return the number of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByVersion(int version) throws SystemException {
		Object[] finderArgs = new Object[] { version };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_VERSION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_VERSION_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(version);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_VERSION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering entries where supportEndDate = &#63;.
	 *
	 * @param supportEndDate the support end date
	 * @return the number of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportEndDate(Date supportEndDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { supportEndDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTENDDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			if (supportEndDate == null) {
				query.append(_FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (supportEndDate != null) {
					qPos.add(CalendarUtil.getTimestamp(supportEndDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTENDDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStatus(int status) throws SystemException {
		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_STATUS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

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
	 * Returns the number of offering entries where accountEntryId = &#63; and status = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param status the status
	 * @return the number of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_S(long accountEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_AEI_S_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderEntryId the order entry ID
	 * @param type the type
	 * @return the number of matching offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, accountEntryId, orderEntryId, type
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_AEI_OEI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OFFERINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2);

			query.append(_FINDER_COLUMN_U_AEI_OEI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				qPos.add(orderEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI_OEI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering entries.
	 *
	 * @return the number of offering entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OFFERINGENTRY);

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
	 * Initializes the offering entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.OfferingEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OfferingEntry>> listenersList = new ArrayList<ModelListener<OfferingEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<OfferingEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OfferingEntryImpl.class.getName());
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
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_OFFERINGENTRY = "SELECT offeringEntry FROM OfferingEntry offeringEntry";
	private static final String _SQL_SELECT_OFFERINGENTRY_WHERE = "SELECT offeringEntry FROM OfferingEntry offeringEntry WHERE ";
	private static final String _SQL_COUNT_OFFERINGENTRY = "SELECT COUNT(offeringEntry) FROM OfferingEntry offeringEntry";
	private static final String _SQL_COUNT_OFFERINGENTRY_WHERE = "SELECT COUNT(offeringEntry) FROM OfferingEntry offeringEntry WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "offeringEntry.accountEntryId = ?";
	private static final String _FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2 = "offeringEntry.orderEntryId = ?";
	private static final String _FINDER_COLUMN_VERSION_VERSION_2 = "offeringEntry.version = ?";
	private static final String _FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_1 = "offeringEntry.supportEndDate IS NULL";
	private static final String _FINDER_COLUMN_SUPPORTENDDATE_SUPPORTENDDATE_2 = "offeringEntry.supportEndDate = ?";
	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "offeringEntry.status = ?";
	private static final String _FINDER_COLUMN_AEI_S_ACCOUNTENTRYID_2 = "offeringEntry.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_S_STATUS_2 = "offeringEntry.status = ?";
	private static final String _FINDER_COLUMN_U_AEI_OEI_T_USERID_2 = "offeringEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_OEI_T_ACCOUNTENTRYID_2 = "offeringEntry.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_OEI_T_ORDERENTRYID_2 = "offeringEntry.orderEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_OEI_T_TYPE_2 = "offeringEntry.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "offeringEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OfferingEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OfferingEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OfferingEntryPersistenceImpl.class);
	private static OfferingEntry _nullOfferingEntry = new OfferingEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OfferingEntry> toCacheModel() {
				return _nullOfferingEntryCacheModel;
			}
		};

	private static CacheModel<OfferingEntry> _nullOfferingEntryCacheModel = new CacheModel<OfferingEntry>() {
			public OfferingEntry toEntityModel() {
				return _nullOfferingEntry;
			}
		};
}
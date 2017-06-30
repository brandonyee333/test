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

import com.liferay.osb.NoSuchTicketEntryException;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.impl.TicketEntryImpl;
import com.liferay.osb.model.impl.TicketEntryModelImpl;

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
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.SubscriptionPersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the ticket entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryPersistence
 * @see TicketEntryUtil
 * @generated
 */
public class TicketEntryPersistenceImpl extends BasePersistenceImpl<TicketEntry>
	implements TicketEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketEntryUtil} to access the ticket entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMODIFIEDDATE =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtModifiedDate",
			new String[] {
				Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMODIFIEDDATE =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtModifiedDate",
			new String[] { Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			TicketEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOfferingEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOfferingEntryId",
			new String[] { Long.class.getName() },
			TicketEntryModelImpl.OFFERINGENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OFFERINGENTRYID = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOfferingEntryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI_TI = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAEI_TI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			TicketEntryModelImpl.TICKETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_TI = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_TI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTR = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_NotR",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTR =
		new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_NotR",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, TicketEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket entry in the entity cache if it is enabled.
	 *
	 * @param ticketEntry the ticket entry
	 */
	public void cacheResult(TicketEntry ticketEntry) {
		EntityCacheUtil.putResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryImpl.class, ticketEntry.getPrimaryKey(), ticketEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_TI,
			new Object[] {
				Long.valueOf(ticketEntry.getAccountEntryId()),
				Long.valueOf(ticketEntry.getTicketId())
			}, ticketEntry);

		ticketEntry.resetOriginalValues();
	}

	/**
	 * Caches the ticket entries in the entity cache if it is enabled.
	 *
	 * @param ticketEntries the ticket entries
	 */
	public void cacheResult(List<TicketEntry> ticketEntries) {
		for (TicketEntry ticketEntry : ticketEntries) {
			if (EntityCacheUtil.getResult(
						TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
						TicketEntryImpl.class, ticketEntry.getPrimaryKey()) == null) {
				cacheResult(ticketEntry);
			}
			else {
				ticketEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketEntry ticketEntry) {
		EntityCacheUtil.removeResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryImpl.class, ticketEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(ticketEntry);
	}

	@Override
	public void clearCache(List<TicketEntry> ticketEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketEntry ticketEntry : ticketEntries) {
			EntityCacheUtil.removeResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
				TicketEntryImpl.class, ticketEntry.getPrimaryKey());

			clearUniqueFindersCache(ticketEntry);
		}
	}

	protected void cacheUniqueFindersCache(TicketEntry ticketEntry) {
		if (ticketEntry.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(ticketEntry.getAccountEntryId()),
					Long.valueOf(ticketEntry.getTicketId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_TI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_TI, args,
				ticketEntry);
		}
		else {
			TicketEntryModelImpl ticketEntryModelImpl = (TicketEntryModelImpl)ticketEntry;

			if ((ticketEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI_TI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketEntry.getAccountEntryId()),
						Long.valueOf(ticketEntry.getTicketId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_TI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_TI, args,
					ticketEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(TicketEntry ticketEntry) {
		TicketEntryModelImpl ticketEntryModelImpl = (TicketEntryModelImpl)ticketEntry;

		Object[] args = new Object[] {
				Long.valueOf(ticketEntry.getAccountEntryId()),
				Long.valueOf(ticketEntry.getTicketId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_TI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_TI, args);

		if ((ticketEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI_TI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(ticketEntryModelImpl.getOriginalAccountEntryId()),
					Long.valueOf(ticketEntryModelImpl.getOriginalTicketId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_TI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_TI, args);
		}
	}

	/**
	 * Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	 *
	 * @param ticketEntryId the primary key for the new ticket entry
	 * @return the new ticket entry
	 */
	public TicketEntry create(long ticketEntryId) {
		TicketEntry ticketEntry = new TicketEntryImpl();

		ticketEntry.setNew(true);
		ticketEntry.setPrimaryKey(ticketEntryId);

		return ticketEntry;
	}

	/**
	 * Removes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketEntryId the primary key of the ticket entry
	 * @return the ticket entry that was removed
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry remove(long ticketEntryId)
		throws NoSuchTicketEntryException, SystemException {
		return remove(Long.valueOf(ticketEntryId));
	}

	/**
	 * Removes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket entry
	 * @return the ticket entry that was removed
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketEntry remove(Serializable primaryKey)
		throws NoSuchTicketEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketEntry ticketEntry = (TicketEntry)session.get(TicketEntryImpl.class,
					primaryKey);

			if (ticketEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketEntry);
		}
		catch (NoSuchTicketEntryException nsee) {
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
	protected TicketEntry removeImpl(TicketEntry ticketEntry)
		throws SystemException {
		ticketEntry = toUnwrappedModel(ticketEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketEntry);

		return ticketEntry;
	}

	@Override
	public TicketEntry updateImpl(
		com.liferay.osb.model.TicketEntry ticketEntry, boolean merge)
		throws SystemException {
		ticketEntry = toUnwrappedModel(ticketEntry);

		boolean isNew = ticketEntry.isNew();

		TicketEntryModelImpl ticketEntryModelImpl = (TicketEntryModelImpl)ticketEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketEntry, merge);

			ticketEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketEntryModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketEntryModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((ticketEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketEntryModelImpl.getOriginalOfferingEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketEntryModelImpl.getOfferingEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
			TicketEntryImpl.class, ticketEntry.getPrimaryKey(), ticketEntry);

		clearUniqueFindersCache(ticketEntry);
		cacheUniqueFindersCache(ticketEntry);

		return ticketEntry;
	}

	protected TicketEntry toUnwrappedModel(TicketEntry ticketEntry) {
		if (ticketEntry instanceof TicketEntryImpl) {
			return ticketEntry;
		}

		TicketEntryImpl ticketEntryImpl = new TicketEntryImpl();

		ticketEntryImpl.setNew(ticketEntry.isNew());
		ticketEntryImpl.setPrimaryKey(ticketEntry.getPrimaryKey());

		ticketEntryImpl.setTicketEntryId(ticketEntry.getTicketEntryId());
		ticketEntryImpl.setUserId(ticketEntry.getUserId());
		ticketEntryImpl.setUserName(ticketEntry.getUserName());
		ticketEntryImpl.setCreateDate(ticketEntry.getCreateDate());
		ticketEntryImpl.setModifiedDate(ticketEntry.getModifiedDate());
		ticketEntryImpl.setAccountEntryId(ticketEntry.getAccountEntryId());
		ticketEntryImpl.setOrderEntryId(ticketEntry.getOrderEntryId());
		ticketEntryImpl.setProductEntryId(ticketEntry.getProductEntryId());
		ticketEntryImpl.setSupportResponseId(ticketEntry.getSupportResponseId());
		ticketEntryImpl.setOfferingEntryId(ticketEntry.getOfferingEntryId());
		ticketEntryImpl.setSupportRegionId(ticketEntry.getSupportRegionId());
		ticketEntryImpl.setLanguageId(ticketEntry.getLanguageId());
		ticketEntryImpl.setTicketId(ticketEntry.getTicketId());
		ticketEntryImpl.setSubject(ticketEntry.getSubject());
		ticketEntryImpl.setDescription(ticketEntry.getDescription());
		ticketEntryImpl.setReproductionSteps(ticketEntry.getReproductionSteps());
		ticketEntryImpl.setSeverity(ticketEntry.getSeverity());
		ticketEntryImpl.setStatus(ticketEntry.getStatus());
		ticketEntryImpl.setWeight(ticketEntry.getWeight());
		ticketEntryImpl.setEscalationLevel(ticketEntry.getEscalationLevel());
		ticketEntryImpl.setEnvName(ticketEntry.getEnvName());
		ticketEntryImpl.setEnvOS(ticketEntry.getEnvOS());
		ticketEntryImpl.setEnvOSCustom(ticketEntry.getEnvOSCustom());
		ticketEntryImpl.setEnvDB(ticketEntry.getEnvDB());
		ticketEntryImpl.setEnvJVM(ticketEntry.getEnvJVM());
		ticketEntryImpl.setEnvAS(ticketEntry.getEnvAS());
		ticketEntryImpl.setEnvLFR(ticketEntry.getEnvLFR());
		ticketEntryImpl.setEnvBrowser(ticketEntry.getEnvBrowser());
		ticketEntryImpl.setEnvBrowserCustom(ticketEntry.getEnvBrowserCustom());
		ticketEntryImpl.setEnvCS(ticketEntry.getEnvCS());
		ticketEntryImpl.setEnvSearch(ticketEntry.getEnvSearch());
		ticketEntryImpl.setComponent(ticketEntry.getComponent());
		ticketEntryImpl.setSubcomponent(ticketEntry.getSubcomponent());
		ticketEntryImpl.setSubcomponentCustom(ticketEntry.getSubcomponentCustom());
		ticketEntryImpl.setResolution(ticketEntry.getResolution());
		ticketEntryImpl.setHoldDate(ticketEntry.getHoldDate());
		ticketEntryImpl.setClosedDate(ticketEntry.getClosedDate());
		ticketEntryImpl.setDueDate(ticketEntry.getDueDate());
		ticketEntryImpl.setIgnoreDueDate(ticketEntry.isIgnoreDueDate());
		ticketEntryImpl.setCustomerModifiedDate(ticketEntry.getCustomerModifiedDate());
		ticketEntryImpl.setWorkerModifiedDate(ticketEntry.getWorkerModifiedDate());

		return ticketEntryImpl;
	}

	/**
	 * Returns the ticket entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket entry
	 * @return the ticket entry
	 * @throws com.liferay.portal.NoSuchModelException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket entry with the primary key or throws a {@link com.liferay.osb.NoSuchTicketEntryException} if it could not be found.
	 *
	 * @param ticketEntryId the primary key of the ticket entry
	 * @return the ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByPrimaryKey(long ticketEntryId)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByPrimaryKey(ticketEntryId);

		if (ticketEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ticketEntryId);
			}

			throw new NoSuchTicketEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketEntryId);
		}

		return ticketEntry;
	}

	/**
	 * Returns the ticket entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket entry
	 * @return the ticket entry, or <code>null</code> if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketEntryId the primary key of the ticket entry
	 * @return the ticket entry, or <code>null</code> if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByPrimaryKey(long ticketEntryId)
		throws SystemException {
		TicketEntry ticketEntry = (TicketEntry)EntityCacheUtil.getResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
				TicketEntryImpl.class, ticketEntryId);

		if (ticketEntry == _nullTicketEntry) {
			return null;
		}

		if (ticketEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketEntry = (TicketEntry)session.get(TicketEntryImpl.class,
						Long.valueOf(ticketEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketEntry != null) {
					cacheResult(ticketEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketEntryModelImpl.ENTITY_CACHE_ENABLED,
						TicketEntryImpl.class, ticketEntryId, _nullTicketEntry);
				}

				closeSession(session);
			}
		}

		return ticketEntry;
	}

	/**
	 * Returns all the ticket entries where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByGtModifiedDate(Date modifiedDate)
		throws SystemException {
		return findByGtModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByGtModifiedDate(Date modifiedDate, int start,
		int end) throws SystemException {
		return findByGtModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where modifiedDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByGtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMODIFIEDDATE;
		finderArgs = new Object[] { modifiedDate, start, end, orderByComparator };

		List<TicketEntry> list = (List<TicketEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketEntry ticketEntry : list) {
				if (!Validator.equals(modifiedDate,
							ticketEntry.getModifiedDate())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByGtModifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByGtModifiedDate_First(modifiedDate,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByGtModifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketEntry> list = findByGtModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByGtModifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByGtModifiedDate_Last(modifiedDate,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByGtModifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGtModifiedDate(modifiedDate);

		List<TicketEntry> list = findByGtModifiedDate(modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where modifiedDate &ge; &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry[] findByGtModifiedDate_PrevAndNext(long ticketEntryId,
		Date modifiedDate, OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByGtModifiedDate_PrevAndNext(session, ticketEntry,
					modifiedDate, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByGtModifiedDate_PrevAndNext(session, ticketEntry,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketEntry getByGtModifiedDate_PrevAndNext(Session session,
		TicketEntry ticketEntry, Date modifiedDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2);
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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (modifiedDate != null) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByAccountEntryId(long accountEntryId,
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

		List<TicketEntry> list = (List<TicketEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketEntry ticketEntry : list) {
				if ((accountEntryId != ticketEntry.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketEntry> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<TicketEntry> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where accountEntryId = &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry[] findByAccountEntryId_PrevAndNext(long ticketEntryId,
		long accountEntryId, OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, ticketEntry,
					accountEntryId, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByAccountEntryId_PrevAndNext(session, ticketEntry,
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

	protected TicketEntry getByAccountEntryId_PrevAndNext(Session session,
		TicketEntry ticketEntry, long accountEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket entries where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByOfferingEntryId(long offeringEntryId)
		throws SystemException {
		return findByOfferingEntryId(offeringEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByOfferingEntryId(long offeringEntryId,
		int start, int end) throws SystemException {
		return findByOfferingEntryId(offeringEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID;
			finderArgs = new Object[] { offeringEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OFFERINGENTRYID;
			finderArgs = new Object[] {
					offeringEntryId,
					
					start, end, orderByComparator
				};
		}

		List<TicketEntry> list = (List<TicketEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketEntry ticketEntry : list) {
				if ((offeringEntryId != ticketEntry.getOfferingEntryId())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByOfferingEntryId_First(offeringEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketEntry> list = findByOfferingEntryId(offeringEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByOfferingEntryId_Last(offeringEntryId,
				orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOfferingEntryId(offeringEntryId);

		List<TicketEntry> list = findByOfferingEntryId(offeringEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry[] findByOfferingEntryId_PrevAndNext(long ticketEntryId,
		long offeringEntryId, OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByOfferingEntryId_PrevAndNext(session, ticketEntry,
					offeringEntryId, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByOfferingEntryId_PrevAndNext(session, ticketEntry,
					offeringEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketEntry getByOfferingEntryId_PrevAndNext(Session session,
		TicketEntry ticketEntry, long offeringEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

		query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or throws a {@link com.liferay.osb.NoSuchTicketEntryException} if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByAEI_TI(long accountEntryId, long ticketId)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByAEI_TI(accountEntryId, ticketId);

		if (ticketEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append(", ticketId=");
			msg.append(ticketId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTicketEntryException(msg.toString());
		}

		return ticketEntry;
	}

	/**
	 * Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId)
		throws SystemException {
		return fetchByAEI_TI(accountEntryId, ticketId, true);
	}

	/**
	 * Returns the ticket entry where accountEntryId = &#63; and ticketId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByAEI_TI(long accountEntryId, long ticketId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, ticketId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI_TI,
					finderArgs, this);
		}

		if (result instanceof TicketEntry) {
			TicketEntry ticketEntry = (TicketEntry)result;

			if ((accountEntryId != ticketEntry.getAccountEntryId()) ||
					(ticketId != ticketEntry.getTicketId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_AEI_TI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_TI_TICKETID_2);

			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(ticketId);

				List<TicketEntry> list = q.list();

				result = list;

				TicketEntry ticketEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_TI,
						finderArgs, list);
				}
				else {
					ticketEntry = list.get(0);

					cacheResult(ticketEntry);

					if ((ticketEntry.getAccountEntryId() != accountEntryId) ||
							(ticketEntry.getTicketId() != ticketId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI_TI,
							finderArgs, ticketEntry);
					}
				}

				return ticketEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI_TI,
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
				return (TicketEntry)result;
			}
		}
	}

	/**
	 * Returns all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @return the matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByOEI_NotR(long offeringEntryId, int resolution)
		throws SystemException {
		return findByOEI_NotR(offeringEntryId, resolution, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end) throws SystemException {
		return findByOEI_NotR(offeringEntryId, resolution, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findByOEI_NotR(long offeringEntryId,
		int resolution, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTR;
		finderArgs = new Object[] {
				offeringEntryId, resolution,
				
				start, end, orderByComparator
			};

		List<TicketEntry> list = (List<TicketEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketEntry ticketEntry : list) {
				if ((offeringEntryId != ticketEntry.getOfferingEntryId()) ||
						(resolution != ticketEntry.getResolution())) {
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

			query.append(_SQL_SELECT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_NOTR_RESOLUTION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(resolution);

				list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByOEI_NotR_First(long offeringEntryId,
		int resolution, OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByOEI_NotR_First(offeringEntryId,
				resolution, orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", resolution=");
		msg.append(resolution);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the first ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByOEI_NotR_First(long offeringEntryId,
		int resolution, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketEntry> list = findByOEI_NotR(offeringEntryId, resolution, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry findByOEI_NotR_Last(long offeringEntryId,
		int resolution, OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = fetchByOEI_NotR_Last(offeringEntryId,
				resolution, orderByComparator);

		if (ticketEntry != null) {
			return ticketEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", resolution=");
		msg.append(resolution);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketEntryException(msg.toString());
	}

	/**
	 * Returns the last ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket entry, or <code>null</code> if a matching ticket entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry fetchByOEI_NotR_Last(long offeringEntryId,
		int resolution, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByOEI_NotR(offeringEntryId, resolution);

		List<TicketEntry> list = findByOEI_NotR(offeringEntryId, resolution,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket entries before and after the current ticket entry in the ordered set where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param ticketEntryId the primary key of the current ticket entry
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket entry
	 * @throws com.liferay.osb.NoSuchTicketEntryException if a ticket entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry[] findByOEI_NotR_PrevAndNext(long ticketEntryId,
		long offeringEntryId, int resolution,
		OrderByComparator orderByComparator)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = findByPrimaryKey(ticketEntryId);

		Session session = null;

		try {
			session = openSession();

			TicketEntry[] array = new TicketEntryImpl[3];

			array[0] = getByOEI_NotR_PrevAndNext(session, ticketEntry,
					offeringEntryId, resolution, orderByComparator, true);

			array[1] = ticketEntry;

			array[2] = getByOEI_NotR_PrevAndNext(session, ticketEntry,
					offeringEntryId, resolution, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketEntry getByOEI_NotR_PrevAndNext(Session session,
		TicketEntry ticketEntry, long offeringEntryId, int resolution,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETENTRY_WHERE);

		query.append(_FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_NOTR_RESOLUTION_2);

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
			query.append(TicketEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(resolution);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket entries.
	 *
	 * @return the ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @return the range of ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket entries
	 * @param end the upper bound of the range of ticket entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketEntry> findAll(int start, int end,
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

		List<TicketEntry> list = (List<TicketEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETENTRY.concat(TicketEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket entries where modifiedDate &ge; &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGtModifiedDate(Date modifiedDate)
		throws SystemException {
		for (TicketEntry ticketEntry : findByGtModifiedDate(modifiedDate)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Removes all the ticket entries where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (TicketEntry ticketEntry : findByAccountEntryId(accountEntryId)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Removes all the ticket entries where offeringEntryId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOfferingEntryId(long offeringEntryId)
		throws SystemException {
		for (TicketEntry ticketEntry : findByOfferingEntryId(offeringEntryId)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Removes the ticket entry where accountEntryId = &#63; and ticketId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the ticket entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TicketEntry removeByAEI_TI(long accountEntryId, long ticketId)
		throws NoSuchTicketEntryException, SystemException {
		TicketEntry ticketEntry = findByAEI_TI(accountEntryId, ticketId);

		return remove(ticketEntry);
	}

	/**
	 * Removes all the ticket entries where offeringEntryId = &#63; and resolution &ne; &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOEI_NotR(long offeringEntryId, int resolution)
		throws SystemException {
		for (TicketEntry ticketEntry : findByOEI_NotR(offeringEntryId,
				resolution)) {
			remove(ticketEntry);
		}
	}

	/**
	 * Removes all the ticket entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketEntry ticketEntry : findAll()) {
			remove(ticketEntry);
		}
	}

	/**
	 * Returns the number of ticket entries where modifiedDate &ge; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGtModifiedDate(Date modifiedDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMODIFIEDDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMODIFIEDDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

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
	 * Returns the number of ticket entries where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the number of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOfferingEntryId(long offeringEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { offeringEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket entries where accountEntryId = &#63; and ticketId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param ticketId the ticket ID
	 * @return the number of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_TI(long accountEntryId, long ticketId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, ticketId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_TI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_AEI_TI_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_AEI_TI_TICKETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(ticketId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_TI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket entries where offeringEntryId = &#63; and resolution &ne; &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param resolution the resolution
	 * @return the number of matching ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_NotR(long offeringEntryId, int resolution)
		throws SystemException {
		Object[] finderArgs = new Object[] { offeringEntryId, resolution };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTR,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETENTRY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_NOTR_RESOLUTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(resolution);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTR,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket entries.
	 *
	 * @return the number of ticket entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETENTRY);

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
	 * Initializes the ticket entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketEntry>> listenersList = new ArrayList<ModelListener<TicketEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TicketEntryImpl.class.getName());
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
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TICKETENTRY = "SELECT ticketEntry FROM TicketEntry ticketEntry";
	private static final String _SQL_SELECT_TICKETENTRY_WHERE = "SELECT ticketEntry FROM TicketEntry ticketEntry WHERE ";
	private static final String _SQL_COUNT_TICKETENTRY = "SELECT COUNT(ticketEntry) FROM TicketEntry ticketEntry";
	private static final String _SQL_COUNT_TICKETENTRY_WHERE = "SELECT COUNT(ticketEntry) FROM TicketEntry ticketEntry WHERE ";
	private static final String _FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_1 = "ticketEntry.modifiedDate >= NULL";
	private static final String _FINDER_COLUMN_GTMODIFIEDDATE_MODIFIEDDATE_2 = "ticketEntry.modifiedDate >= ?";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "ticketEntry.accountEntryId = ?";
	private static final String _FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2 =
		"ticketEntry.offeringEntryId = ?";
	private static final String _FINDER_COLUMN_AEI_TI_ACCOUNTENTRYID_2 = "ticketEntry.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_TI_TICKETID_2 = "ticketEntry.ticketId = ?";
	private static final String _FINDER_COLUMN_OEI_NOTR_OFFERINGENTRYID_2 = "ticketEntry.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTR_RESOLUTION_2 = "ticketEntry.resolution != ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketEntryPersistenceImpl.class);
	private static TicketEntry _nullTicketEntry = new TicketEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketEntry> toCacheModel() {
				return _nullTicketEntryCacheModel;
			}
		};

	private static CacheModel<TicketEntry> _nullTicketEntryCacheModel = new CacheModel<TicketEntry>() {
			public TicketEntry toEntityModel() {
				return _nullTicketEntry;
			}
		};
}
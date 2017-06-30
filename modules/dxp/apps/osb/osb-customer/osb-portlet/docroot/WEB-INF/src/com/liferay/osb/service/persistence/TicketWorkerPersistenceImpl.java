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

import com.liferay.osb.NoSuchTicketWorkerException;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.impl.TicketWorkerImpl;
import com.liferay.osb.model.impl.TicketWorkerModelImpl;

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
 * The persistence implementation for the ticket worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerPersistence
 * @see TicketWorkerUtil
 * @generated
 */
public class TicketWorkerPersistenceImpl extends BasePersistenceImpl<TicketWorker>
	implements TicketWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketWorkerUtil} to access the ticket worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			TicketWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketWorkerModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_TEI = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_TEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketWorkerModelImpl.USERID_COLUMN_BITMASK |
			TicketWorkerModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_TEI = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_TEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_TEI_P = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByTEI_P",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			TicketWorkerModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketWorkerModelImpl.PRIMARY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_P = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_P",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCNI_SCPK =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySCNI_SCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK =
		new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySCNI_SCPK",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketWorkerModelImpl.SOURCECLASSNAMEID_COLUMN_BITMASK |
			TicketWorkerModelImpl.SOURCECLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCNI_SCPK = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySCNI_SCPK",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, TicketWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket worker in the entity cache if it is enabled.
	 *
	 * @param ticketWorker the ticket worker
	 */
	public void cacheResult(TicketWorker ticketWorker) {
		EntityCacheUtil.putResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerImpl.class, ticketWorker.getPrimaryKey(), ticketWorker);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TEI,
			new Object[] {
				Long.valueOf(ticketWorker.getUserId()),
				Long.valueOf(ticketWorker.getTicketEntryId())
			}, ticketWorker);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_P,
			new Object[] {
				Long.valueOf(ticketWorker.getTicketEntryId()),
				Boolean.valueOf(ticketWorker.getPrimary())
			}, ticketWorker);

		ticketWorker.resetOriginalValues();
	}

	/**
	 * Caches the ticket workers in the entity cache if it is enabled.
	 *
	 * @param ticketWorkers the ticket workers
	 */
	public void cacheResult(List<TicketWorker> ticketWorkers) {
		for (TicketWorker ticketWorker : ticketWorkers) {
			if (EntityCacheUtil.getResult(
						TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
						TicketWorkerImpl.class, ticketWorker.getPrimaryKey()) == null) {
				cacheResult(ticketWorker);
			}
			else {
				ticketWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket workers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketWorkerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketWorkerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket worker.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketWorker ticketWorker) {
		EntityCacheUtil.removeResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerImpl.class, ticketWorker.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(ticketWorker);
	}

	@Override
	public void clearCache(List<TicketWorker> ticketWorkers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketWorker ticketWorker : ticketWorkers) {
			EntityCacheUtil.removeResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
				TicketWorkerImpl.class, ticketWorker.getPrimaryKey());

			clearUniqueFindersCache(ticketWorker);
		}
	}

	protected void cacheUniqueFindersCache(TicketWorker ticketWorker) {
		if (ticketWorker.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(ticketWorker.getUserId()),
					Long.valueOf(ticketWorker.getTicketEntryId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_TEI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TEI, args,
				ticketWorker);

			args = new Object[] {
					Long.valueOf(ticketWorker.getTicketEntryId()),
					Boolean.valueOf(ticketWorker.getPrimary())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_P, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_P, args,
				ticketWorker);
		}
		else {
			TicketWorkerModelImpl ticketWorkerModelImpl = (TicketWorkerModelImpl)ticketWorker;

			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_TEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketWorker.getUserId()),
						Long.valueOf(ticketWorker.getTicketEntryId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_TEI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TEI, args,
					ticketWorker);
			}

			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TEI_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketWorker.getTicketEntryId()),
						Boolean.valueOf(ticketWorker.getPrimary())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_P, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_P, args,
					ticketWorker);
			}
		}
	}

	protected void clearUniqueFindersCache(TicketWorker ticketWorker) {
		TicketWorkerModelImpl ticketWorkerModelImpl = (TicketWorkerModelImpl)ticketWorker;

		Object[] args = new Object[] {
				Long.valueOf(ticketWorker.getUserId()),
				Long.valueOf(ticketWorker.getTicketEntryId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_TEI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_TEI, args);

		if ((ticketWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_TEI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(ticketWorkerModelImpl.getOriginalUserId()),
					Long.valueOf(ticketWorkerModelImpl.getOriginalTicketEntryId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_TEI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_TEI, args);
		}

		args = new Object[] {
				Long.valueOf(ticketWorker.getTicketEntryId()),
				Boolean.valueOf(ticketWorker.getPrimary())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_P, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_P, args);

		if ((ticketWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TEI_P.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(ticketWorkerModelImpl.getOriginalTicketEntryId()),
					Boolean.valueOf(ticketWorkerModelImpl.getOriginalPrimary())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_P, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_P, args);
		}
	}

	/**
	 * Creates a new ticket worker with the primary key. Does not add the ticket worker to the database.
	 *
	 * @param ticketWorkerId the primary key for the new ticket worker
	 * @return the new ticket worker
	 */
	public TicketWorker create(long ticketWorkerId) {
		TicketWorker ticketWorker = new TicketWorkerImpl();

		ticketWorker.setNew(true);
		ticketWorker.setPrimaryKey(ticketWorkerId);

		return ticketWorker;
	}

	/**
	 * Removes the ticket worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketWorkerId the primary key of the ticket worker
	 * @return the ticket worker that was removed
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker remove(long ticketWorkerId)
		throws NoSuchTicketWorkerException, SystemException {
		return remove(Long.valueOf(ticketWorkerId));
	}

	/**
	 * Removes the ticket worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket worker
	 * @return the ticket worker that was removed
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketWorker remove(Serializable primaryKey)
		throws NoSuchTicketWorkerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketWorker ticketWorker = (TicketWorker)session.get(TicketWorkerImpl.class,
					primaryKey);

			if (ticketWorker == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketWorker);
		}
		catch (NoSuchTicketWorkerException nsee) {
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
	protected TicketWorker removeImpl(TicketWorker ticketWorker)
		throws SystemException {
		ticketWorker = toUnwrappedModel(ticketWorker);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketWorker);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketWorker);

		return ticketWorker;
	}

	@Override
	public TicketWorker updateImpl(
		com.liferay.osb.model.TicketWorker ticketWorker, boolean merge)
		throws SystemException {
		ticketWorker = toUnwrappedModel(ticketWorker);

		boolean isNew = ticketWorker.isNew();

		TicketWorkerModelImpl ticketWorkerModelImpl = (TicketWorkerModelImpl)ticketWorker;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketWorker, merge);

			ticketWorker.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketWorkerModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(ticketWorkerModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketWorkerModelImpl.getOriginalTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketWorkerModelImpl.getTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}

			if ((ticketWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketWorkerModelImpl.getOriginalSourceClassNameId()),
						Long.valueOf(ticketWorkerModelImpl.getOriginalSourceClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCNI_SCPK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK,
					args);

				args = new Object[] {
						Long.valueOf(ticketWorkerModelImpl.getSourceClassNameId()),
						Long.valueOf(ticketWorkerModelImpl.getSourceClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCNI_SCPK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TicketWorkerImpl.class, ticketWorker.getPrimaryKey(), ticketWorker);

		clearUniqueFindersCache(ticketWorker);
		cacheUniqueFindersCache(ticketWorker);

		return ticketWorker;
	}

	protected TicketWorker toUnwrappedModel(TicketWorker ticketWorker) {
		if (ticketWorker instanceof TicketWorkerImpl) {
			return ticketWorker;
		}

		TicketWorkerImpl ticketWorkerImpl = new TicketWorkerImpl();

		ticketWorkerImpl.setNew(ticketWorker.isNew());
		ticketWorkerImpl.setPrimaryKey(ticketWorker.getPrimaryKey());

		ticketWorkerImpl.setTicketWorkerId(ticketWorker.getTicketWorkerId());
		ticketWorkerImpl.setUserId(ticketWorker.getUserId());
		ticketWorkerImpl.setTicketEntryId(ticketWorker.getTicketEntryId());
		ticketWorkerImpl.setSourceClassNameId(ticketWorker.getSourceClassNameId());
		ticketWorkerImpl.setSourceClassPK(ticketWorker.getSourceClassPK());
		ticketWorkerImpl.setRole(ticketWorker.getRole());
		ticketWorkerImpl.setPrimary(ticketWorker.isPrimary());

		return ticketWorkerImpl;
	}

	/**
	 * Returns the ticket worker with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket worker
	 * @return the ticket worker
	 * @throws com.liferay.portal.NoSuchModelException if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket worker with the primary key or throws a {@link com.liferay.osb.NoSuchTicketWorkerException} if it could not be found.
	 *
	 * @param ticketWorkerId the primary key of the ticket worker
	 * @return the ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findByPrimaryKey(long ticketWorkerId)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchByPrimaryKey(ticketWorkerId);

		if (ticketWorker == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ticketWorkerId);
			}

			throw new NoSuchTicketWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketWorkerId);
		}

		return ticketWorker;
	}

	/**
	 * Returns the ticket worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket worker
	 * @return the ticket worker, or <code>null</code> if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketWorker fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketWorkerId the primary key of the ticket worker
	 * @return the ticket worker, or <code>null</code> if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByPrimaryKey(long ticketWorkerId)
		throws SystemException {
		TicketWorker ticketWorker = (TicketWorker)EntityCacheUtil.getResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
				TicketWorkerImpl.class, ticketWorkerId);

		if (ticketWorker == _nullTicketWorker) {
			return null;
		}

		if (ticketWorker == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketWorker = (TicketWorker)session.get(TicketWorkerImpl.class,
						Long.valueOf(ticketWorkerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketWorker != null) {
					cacheResult(ticketWorker);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketWorkerModelImpl.ENTITY_CACHE_ENABLED,
						TicketWorkerImpl.class, ticketWorkerId,
						_nullTicketWorker);
				}

				closeSession(session);
			}
		}

		return ticketWorker;
	}

	/**
	 * Returns all the ticket workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findByUserId(long userId, int start, int end,
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

		List<TicketWorker> list = (List<TicketWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketWorker ticketWorker : list) {
				if ((userId != ticketWorker.getUserId())) {
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

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

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

				list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the first ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchByUserId_Last(userId, orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the last ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<TicketWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket workers before and after the current ticket worker in the ordered set where userId = &#63;.
	 *
	 * @param ticketWorkerId the primary key of the current ticket worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker[] findByUserId_PrevAndNext(long ticketWorkerId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = findByPrimaryKey(ticketWorkerId);

		Session session = null;

		try {
			session = openSession();

			TicketWorker[] array = new TicketWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, ticketWorker, userId,
					orderByComparator, true);

			array[1] = ticketWorker;

			array[2] = getByUserId_PrevAndNext(session, ticketWorker, userId,
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

	protected TicketWorker getByUserId_PrevAndNext(Session session,
		TicketWorker ticketWorker, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETWORKER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(ticketWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket workers where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findByTicketEntryId(long ticketEntryId)
		throws SystemException {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end) throws SystemException {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket workers where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] { ticketEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] {
					ticketEntryId,
					
					start, end, orderByComparator
				};
		}

		List<TicketWorker> list = (List<TicketWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketWorker ticketWorker : list) {
				if ((ticketEntryId != ticketWorker.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

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

				qPos.add(ticketEntryId);

				list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the first ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketWorker> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the last ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTicketEntryId(ticketEntryId);

		List<TicketWorker> list = findByTicketEntryId(ticketEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket workers before and after the current ticket worker in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketWorkerId the primary key of the current ticket worker
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker[] findByTicketEntryId_PrevAndNext(long ticketWorkerId,
		long ticketEntryId, OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = findByPrimaryKey(ticketWorkerId);

		Session session = null;

		try {
			session = openSession();

			TicketWorker[] array = new TicketWorkerImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketWorker,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketWorker;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketWorker,
					ticketEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketWorker getByTicketEntryId_PrevAndNext(Session session,
		TicketWorker ticketWorker, long ticketEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETWORKER_WHERE);

		query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

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

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchTicketWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findByU_TEI(long userId, long ticketEntryId)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchByU_TEI(userId, ticketEntryId);

		if (ticketWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTicketWorkerException(msg.toString());
		}

		return ticketWorker;
	}

	/**
	 * Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByU_TEI(long userId, long ticketEntryId)
		throws SystemException {
		return fetchByU_TEI(userId, ticketEntryId, true);
	}

	/**
	 * Returns the ticket worker where userId = &#63; and ticketEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByU_TEI(long userId, long ticketEntryId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, ticketEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_TEI,
					finderArgs, this);
		}

		if (result instanceof TicketWorker) {
			TicketWorker ticketWorker = (TicketWorker)result;

			if ((userId != ticketWorker.getUserId()) ||
					(ticketEntryId != ticketWorker.getTicketEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				List<TicketWorker> list = q.list();

				result = list;

				TicketWorker ticketWorker = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TEI,
						finderArgs, list);
				}
				else {
					ticketWorker = list.get(0);

					cacheResult(ticketWorker);

					if ((ticketWorker.getUserId() != userId) ||
							(ticketWorker.getTicketEntryId() != ticketEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TEI,
							finderArgs, ticketWorker);
					}
				}

				return ticketWorker;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_TEI,
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
				return (TicketWorker)result;
			}
		}
	}

	/**
	 * Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or throws a {@link com.liferay.osb.NoSuchTicketWorkerException} if it could not be found.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findByTEI_P(long ticketEntryId, boolean primary)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchByTEI_P(ticketEntryId, primary);

		if (ticketWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(", primary=");
			msg.append(primary);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTicketWorkerException(msg.toString());
		}

		return ticketWorker;
	}

	/**
	 * Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByTEI_P(long ticketEntryId, boolean primary)
		throws SystemException {
		return fetchByTEI_P(ticketEntryId, primary, true);
	}

	/**
	 * Returns the ticket worker where ticketEntryId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchByTEI_P(long ticketEntryId, boolean primary,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, primary };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TEI_P,
					finderArgs, this);
		}

		if (result instanceof TicketWorker) {
			TicketWorker ticketWorker = (TicketWorker)result;

			if ((ticketEntryId != ticketWorker.getTicketEntryId()) ||
					(primary != ticketWorker.getPrimary())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TEI_P_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(primary);

				List<TicketWorker> list = q.list();

				result = list;

				TicketWorker ticketWorker = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_P,
						finderArgs, list);
				}
				else {
					ticketWorker = list.get(0);

					cacheResult(ticketWorker);

					if ((ticketWorker.getTicketEntryId() != ticketEntryId) ||
							(ticketWorker.getPrimary() != primary)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_P,
							finderArgs, ticketWorker);
					}
				}

				return ticketWorker;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_P,
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
				return (TicketWorker)result;
			}
		}
	}

	/**
	 * Returns all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @return the matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK) throws SystemException {
		return findBySCNI_SCPK(sourceClassNameId, sourceClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end) throws SystemException {
		return findBySCNI_SCPK(sourceClassNameId, sourceClassPK, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findBySCNI_SCPK(long sourceClassNameId,
		long sourceClassPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCNI_SCPK;
			finderArgs = new Object[] { sourceClassNameId, sourceClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCNI_SCPK;
			finderArgs = new Object[] {
					sourceClassNameId, sourceClassPK,
					
					start, end, orderByComparator
				};
		}

		List<TicketWorker> list = (List<TicketWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketWorker ticketWorker : list) {
				if ((sourceClassNameId != ticketWorker.getSourceClassNameId()) ||
						(sourceClassPK != ticketWorker.getSourceClassPK())) {
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

			query.append(_SQL_SELECT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2);

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

				qPos.add(sourceClassNameId);

				qPos.add(sourceClassPK);

				list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findBySCNI_SCPK_First(long sourceClassNameId,
		long sourceClassPK, OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchBySCNI_SCPK_First(sourceClassNameId,
				sourceClassPK, orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sourceClassNameId=");
		msg.append(sourceClassNameId);

		msg.append(", sourceClassPK=");
		msg.append(sourceClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the first ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchBySCNI_SCPK_First(long sourceClassNameId,
		long sourceClassPK, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketWorker> list = findBySCNI_SCPK(sourceClassNameId,
				sourceClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker findBySCNI_SCPK_Last(long sourceClassNameId,
		long sourceClassPK, OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = fetchBySCNI_SCPK_Last(sourceClassNameId,
				sourceClassPK, orderByComparator);

		if (ticketWorker != null) {
			return ticketWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sourceClassNameId=");
		msg.append(sourceClassNameId);

		msg.append(", sourceClassPK=");
		msg.append(sourceClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketWorkerException(msg.toString());
	}

	/**
	 * Returns the last ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket worker, or <code>null</code> if a matching ticket worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker fetchBySCNI_SCPK_Last(long sourceClassNameId,
		long sourceClassPK, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySCNI_SCPK(sourceClassNameId, sourceClassPK);

		List<TicketWorker> list = findBySCNI_SCPK(sourceClassNameId,
				sourceClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket workers before and after the current ticket worker in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param ticketWorkerId the primary key of the current ticket worker
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket worker
	 * @throws com.liferay.osb.NoSuchTicketWorkerException if a ticket worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker[] findBySCNI_SCPK_PrevAndNext(long ticketWorkerId,
		long sourceClassNameId, long sourceClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = findByPrimaryKey(ticketWorkerId);

		Session session = null;

		try {
			session = openSession();

			TicketWorker[] array = new TicketWorkerImpl[3];

			array[0] = getBySCNI_SCPK_PrevAndNext(session, ticketWorker,
					sourceClassNameId, sourceClassPK, orderByComparator, true);

			array[1] = ticketWorker;

			array[2] = getBySCNI_SCPK_PrevAndNext(session, ticketWorker,
					sourceClassNameId, sourceClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketWorker getBySCNI_SCPK_PrevAndNext(Session session,
		TicketWorker ticketWorker, long sourceClassNameId, long sourceClassPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETWORKER_WHERE);

		query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2);

		query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2);

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

		qPos.add(sourceClassNameId);

		qPos.add(sourceClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket workers.
	 *
	 * @return the ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @return the range of ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket workers
	 * @param end the upper bound of the range of ticket workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketWorker> findAll(int start, int end,
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

		List<TicketWorker> list = (List<TicketWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETWORKER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketWorker>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (TicketWorker ticketWorker : findByUserId(userId)) {
			remove(ticketWorker);
		}
	}

	/**
	 * Removes all the ticket workers where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTicketEntryId(long ticketEntryId)
		throws SystemException {
		for (TicketWorker ticketWorker : findByTicketEntryId(ticketEntryId)) {
			remove(ticketWorker);
		}
	}

	/**
	 * Removes the ticket worker where userId = &#63; and ticketEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the ticket worker that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker removeByU_TEI(long userId, long ticketEntryId)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = findByU_TEI(userId, ticketEntryId);

		return remove(ticketWorker);
	}

	/**
	 * Removes the ticket worker where ticketEntryId = &#63; and primary = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the ticket worker that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TicketWorker removeByTEI_P(long ticketEntryId, boolean primary)
		throws NoSuchTicketWorkerException, SystemException {
		TicketWorker ticketWorker = findByTEI_P(ticketEntryId, primary);

		return remove(ticketWorker);
	}

	/**
	 * Removes all the ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySCNI_SCPK(long sourceClassNameId, long sourceClassPK)
		throws SystemException {
		for (TicketWorker ticketWorker : findBySCNI_SCPK(sourceClassNameId,
				sourceClassPK)) {
			remove(ticketWorker);
		}
	}

	/**
	 * Removes all the ticket workers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketWorker ticketWorker : findAll()) {
			remove(ticketWorker);
		}
	}

	/**
	 * Returns the number of ticket workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

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
	 * Returns the number of ticket workers where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTicketEntryId(long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket workers where userId = &#63; and ticketEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_TEI(long userId, long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_TEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_TEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket workers where ticketEntryId = &#63; and primary = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param primary the primary
	 * @return the number of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_P(long ticketEntryId, boolean primary)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, primary };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_P,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_TEI_P_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(primary);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_P,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket workers where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class p k
	 * @return the number of matching ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySCNI_SCPK(long sourceClassNameId, long sourceClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { sourceClassNameId, sourceClassPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SCNI_SCPK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETWORKER_WHERE);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2);

			query.append(_FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sourceClassNameId);

				qPos.add(sourceClassPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SCNI_SCPK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket workers.
	 *
	 * @return the number of ticket workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETWORKER);

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
	 * Initializes the ticket worker persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketWorker")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketWorker>> listenersList = new ArrayList<ModelListener<TicketWorker>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketWorker>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TicketWorkerImpl.class.getName());
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
	private static final String _SQL_SELECT_TICKETWORKER = "SELECT ticketWorker FROM TicketWorker ticketWorker";
	private static final String _SQL_SELECT_TICKETWORKER_WHERE = "SELECT ticketWorker FROM TicketWorker ticketWorker WHERE ";
	private static final String _SQL_COUNT_TICKETWORKER = "SELECT COUNT(ticketWorker) FROM TicketWorker ticketWorker";
	private static final String _SQL_COUNT_TICKETWORKER_WHERE = "SELECT COUNT(ticketWorker) FROM TicketWorker ticketWorker WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "ticketWorker.userId = ?";
	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketWorker.ticketEntryId = ?";
	private static final String _FINDER_COLUMN_U_TEI_USERID_2 = "ticketWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_TICKETENTRYID_2 = "ticketWorker.ticketEntryId = ?";
	private static final String _FINDER_COLUMN_TEI_P_TICKETENTRYID_2 = "ticketWorker.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_P_PRIMARY_2 = "ticketWorker.primary = ?";
	private static final String _FINDER_COLUMN_SCNI_SCPK_SOURCECLASSNAMEID_2 = "ticketWorker.sourceClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_SCNI_SCPK_SOURCECLASSPK_2 = "ticketWorker.sourceClassPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketWorker exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketWorkerPersistenceImpl.class);
	private static TicketWorker _nullTicketWorker = new TicketWorkerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketWorker> toCacheModel() {
				return _nullTicketWorkerCacheModel;
			}
		};

	private static CacheModel<TicketWorker> _nullTicketWorkerCacheModel = new CacheModel<TicketWorker>() {
			public TicketWorker toEntityModel() {
				return _nullTicketWorker;
			}
		};
}
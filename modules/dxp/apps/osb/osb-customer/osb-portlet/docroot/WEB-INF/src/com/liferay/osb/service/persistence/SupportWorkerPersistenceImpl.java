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

import com.liferay.osb.NoSuchSupportWorkerException;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.impl.SupportWorkerImpl;
import com.liferay.osb.model.impl.SupportWorkerModelImpl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
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
 * The persistence implementation for the support worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerPersistence
 * @see SupportWorkerUtil
 * @generated
 */
public class SupportWorkerPersistenceImpl extends BasePersistenceImpl<SupportWorker>
	implements SupportWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportWorkerUtil} to access the support worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] { Long.class.getName() },
			SupportWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySupportTeamId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySupportTeamId", new String[] { Long.class.getName() },
			SupportWorkerModelImpl.SUPPORTTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTTEAMID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportTeamId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySupportLaborId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySupportLaborId", new String[] { Long.class.getName() },
			SupportWorkerModelImpl.SUPPORTLABORID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTLABORID = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportLaborId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_STI = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_STI",
			new String[] { Long.class.getName(), Long.class.getName() },
			SupportWorkerModelImpl.USERID_COLUMN_BITMASK |
			SupportWorkerModelImpl.SUPPORTTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_STI = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_STI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_MW_R = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_MW_R",
			new String[] {
				Long.class.getName(), Double.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_MW_R = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_MW_R",
			new String[] {
				Long.class.getName(), Double.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED,
			SupportWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the support worker in the entity cache if it is enabled.
	 *
	 * @param supportWorker the support worker
	 */
	public void cacheResult(SupportWorker supportWorker) {
		EntityCacheUtil.putResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerImpl.class, supportWorker.getPrimaryKey(),
			supportWorker);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_STI,
			new Object[] {
				Long.valueOf(supportWorker.getUserId()),
				Long.valueOf(supportWorker.getSupportTeamId())
			}, supportWorker);

		supportWorker.resetOriginalValues();
	}

	/**
	 * Caches the support workers in the entity cache if it is enabled.
	 *
	 * @param supportWorkers the support workers
	 */
	public void cacheResult(List<SupportWorker> supportWorkers) {
		for (SupportWorker supportWorker : supportWorkers) {
			if (EntityCacheUtil.getResult(
						SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerImpl.class, supportWorker.getPrimaryKey()) == null) {
				cacheResult(supportWorker);
			}
			else {
				supportWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support workers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SupportWorkerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SupportWorkerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support worker.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportWorker supportWorker) {
		EntityCacheUtil.removeResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerImpl.class, supportWorker.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(supportWorker);
	}

	@Override
	public void clearCache(List<SupportWorker> supportWorkers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportWorker supportWorker : supportWorkers) {
			EntityCacheUtil.removeResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerImpl.class, supportWorker.getPrimaryKey());

			clearUniqueFindersCache(supportWorker);
		}
	}

	protected void cacheUniqueFindersCache(SupportWorker supportWorker) {
		if (supportWorker.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(supportWorker.getUserId()),
					Long.valueOf(supportWorker.getSupportTeamId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_STI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_STI, args,
				supportWorker);
		}
		else {
			SupportWorkerModelImpl supportWorkerModelImpl = (SupportWorkerModelImpl)supportWorker;

			if ((supportWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_STI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportWorker.getUserId()),
						Long.valueOf(supportWorker.getSupportTeamId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_STI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_STI, args,
					supportWorker);
			}
		}
	}

	protected void clearUniqueFindersCache(SupportWorker supportWorker) {
		SupportWorkerModelImpl supportWorkerModelImpl = (SupportWorkerModelImpl)supportWorker;

		Object[] args = new Object[] {
				Long.valueOf(supportWorker.getUserId()),
				Long.valueOf(supportWorker.getSupportTeamId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_STI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_STI, args);

		if ((supportWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_STI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(supportWorkerModelImpl.getOriginalUserId()),
					Long.valueOf(supportWorkerModelImpl.getOriginalSupportTeamId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_STI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_STI, args);
		}
	}

	/**
	 * Creates a new support worker with the primary key. Does not add the support worker to the database.
	 *
	 * @param supportWorkerId the primary key for the new support worker
	 * @return the new support worker
	 */
	public SupportWorker create(long supportWorkerId) {
		SupportWorker supportWorker = new SupportWorkerImpl();

		supportWorker.setNew(true);
		supportWorker.setPrimaryKey(supportWorkerId);

		return supportWorker;
	}

	/**
	 * Removes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportWorkerId the primary key of the support worker
	 * @return the support worker that was removed
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker remove(long supportWorkerId)
		throws NoSuchSupportWorkerException, SystemException {
		return remove(Long.valueOf(supportWorkerId));
	}

	/**
	 * Removes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support worker
	 * @return the support worker that was removed
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportWorker remove(Serializable primaryKey)
		throws NoSuchSupportWorkerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SupportWorker supportWorker = (SupportWorker)session.get(SupportWorkerImpl.class,
					primaryKey);

			if (supportWorker == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportWorker);
		}
		catch (NoSuchSupportWorkerException nsee) {
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
	protected SupportWorker removeImpl(SupportWorker supportWorker)
		throws SystemException {
		supportWorker = toUnwrappedModel(supportWorker);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, supportWorker);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(supportWorker);

		return supportWorker;
	}

	@Override
	public SupportWorker updateImpl(
		com.liferay.osb.model.SupportWorker supportWorker, boolean merge)
		throws SystemException {
		supportWorker = toUnwrappedModel(supportWorker);

		boolean isNew = supportWorker.isNew();

		SupportWorkerModelImpl supportWorkerModelImpl = (SupportWorkerModelImpl)supportWorker;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, supportWorker, merge);

			supportWorker.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SupportWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((supportWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportWorkerModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(supportWorkerModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((supportWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportWorkerModelImpl.getOriginalSupportTeamId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);

				args = new Object[] {
						Long.valueOf(supportWorkerModelImpl.getSupportTeamId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID,
					args);
			}

			if ((supportWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportWorkerModelImpl.getOriginalSupportLaborId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);

				args = new Object[] {
						Long.valueOf(supportWorkerModelImpl.getSupportLaborId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);
			}
		}

		EntityCacheUtil.putResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
			SupportWorkerImpl.class, supportWorker.getPrimaryKey(),
			supportWorker);

		clearUniqueFindersCache(supportWorker);
		cacheUniqueFindersCache(supportWorker);

		return supportWorker;
	}

	protected SupportWorker toUnwrappedModel(SupportWorker supportWorker) {
		if (supportWorker instanceof SupportWorkerImpl) {
			return supportWorker;
		}

		SupportWorkerImpl supportWorkerImpl = new SupportWorkerImpl();

		supportWorkerImpl.setNew(supportWorker.isNew());
		supportWorkerImpl.setPrimaryKey(supportWorker.getPrimaryKey());

		supportWorkerImpl.setSupportWorkerId(supportWorker.getSupportWorkerId());
		supportWorkerImpl.setUserId(supportWorker.getUserId());
		supportWorkerImpl.setSupportTeamId(supportWorker.getSupportTeamId());
		supportWorkerImpl.setSupportLaborId(supportWorker.getSupportLaborId());
		supportWorkerImpl.setAutoAssign(supportWorker.isAutoAssign());
		supportWorkerImpl.setAssignedWork(supportWorker.getAssignedWork());
		supportWorkerImpl.setMaxWork(supportWorker.getMaxWork());
		supportWorkerImpl.setEscalationLevel(supportWorker.getEscalationLevel());
		supportWorkerImpl.setRole(supportWorker.getRole());
		supportWorkerImpl.setEscalationLevel2Role(supportWorker.getEscalationLevel2Role());
		supportWorkerImpl.setNotifications(supportWorker.getNotifications());
		supportWorkerImpl.setClockedIn(supportWorker.isClockedIn());

		return supportWorkerImpl;
	}

	/**
	 * Returns the support worker with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker
	 * @return the support worker
	 * @throws com.liferay.portal.NoSuchModelException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support worker with the primary key or throws a {@link com.liferay.osb.NoSuchSupportWorkerException} if it could not be found.
	 *
	 * @param supportWorkerId the primary key of the support worker
	 * @return the support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findByPrimaryKey(long supportWorkerId)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchByPrimaryKey(supportWorkerId);

		if (supportWorker == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + supportWorkerId);
			}

			throw new NoSuchSupportWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				supportWorkerId);
		}

		return supportWorker;
	}

	/**
	 * Returns the support worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support worker
	 * @return the support worker, or <code>null</code> if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportWorker fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportWorkerId the primary key of the support worker
	 * @return the support worker, or <code>null</code> if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchByPrimaryKey(long supportWorkerId)
		throws SystemException {
		SupportWorker supportWorker = (SupportWorker)EntityCacheUtil.getResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
				SupportWorkerImpl.class, supportWorkerId);

		if (supportWorker == _nullSupportWorker) {
			return null;
		}

		if (supportWorker == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				supportWorker = (SupportWorker)session.get(SupportWorkerImpl.class,
						Long.valueOf(supportWorkerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (supportWorker != null) {
					cacheResult(supportWorker);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SupportWorkerModelImpl.ENTITY_CACHE_ENABLED,
						SupportWorkerImpl.class, supportWorkerId,
						_nullSupportWorker);
				}

				closeSession(session);
			}
		}

		return supportWorker;
	}

	/**
	 * Returns all the support workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByUserId(long userId, int start, int end,
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

		List<SupportWorker> list = (List<SupportWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportWorker supportWorker : list) {
				if ((userId != supportWorker.getUserId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

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

				list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the first support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SupportWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchByUserId_Last(userId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the last support worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<SupportWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support workers before and after the current support worker in the ordered set where userId = &#63;.
	 *
	 * @param supportWorkerId the primary key of the current support worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker[] findByUserId_PrevAndNext(long supportWorkerId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = findByPrimaryKey(supportWorkerId);

		Session session = null;

		try {
			session = openSession();

			SupportWorker[] array = new SupportWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, supportWorker, userId,
					orderByComparator, true);

			array[1] = supportWorker;

			array[2] = getByUserId_PrevAndNext(session, supportWorker, userId,
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

	protected SupportWorker getByUserId_PrevAndNext(Session session,
		SupportWorker supportWorker, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the support workers where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findBySupportTeamId(long supportTeamId)
		throws SystemException {
		return findBySupportTeamId(supportTeamId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findBySupportTeamId(long supportTeamId,
		int start, int end) throws SystemException {
		return findBySupportTeamId(supportTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where supportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportTeamId the support team ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findBySupportTeamId(long supportTeamId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTTEAMID;
			finderArgs = new Object[] { supportTeamId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTTEAMID;
			finderArgs = new Object[] {
					supportTeamId,
					
					start, end, orderByComparator
				};
		}

		List<SupportWorker> list = (List<SupportWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportWorker supportWorker : list) {
				if ((supportTeamId != supportWorker.getSupportTeamId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

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

				qPos.add(supportTeamId);

				list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findBySupportTeamId_First(long supportTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchBySupportTeamId_First(supportTeamId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportTeamId=");
		msg.append(supportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the first support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchBySupportTeamId_First(long supportTeamId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SupportWorker> list = findBySupportTeamId(supportTeamId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findBySupportTeamId_Last(long supportTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchBySupportTeamId_Last(supportTeamId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportTeamId=");
		msg.append(supportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the last support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchBySupportTeamId_Last(long supportTeamId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySupportTeamId(supportTeamId);

		List<SupportWorker> list = findBySupportTeamId(supportTeamId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support workers before and after the current support worker in the ordered set where supportTeamId = &#63;.
	 *
	 * @param supportWorkerId the primary key of the current support worker
	 * @param supportTeamId the support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker[] findBySupportTeamId_PrevAndNext(
		long supportWorkerId, long supportTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = findByPrimaryKey(supportWorkerId);

		Session session = null;

		try {
			session = openSession();

			SupportWorker[] array = new SupportWorkerImpl[3];

			array[0] = getBySupportTeamId_PrevAndNext(session, supportWorker,
					supportTeamId, orderByComparator, true);

			array[1] = supportWorker;

			array[2] = getBySupportTeamId_PrevAndNext(session, supportWorker,
					supportTeamId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportWorker getBySupportTeamId_PrevAndNext(Session session,
		SupportWorker supportWorker, long supportTeamId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

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

		qPos.add(supportTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the support workers where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findBySupportLaborId(long supportLaborId)
		throws SystemException {
		return findBySupportLaborId(supportLaborId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findBySupportLaborId(long supportLaborId,
		int start, int end) throws SystemException {
		return findBySupportLaborId(supportLaborId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findBySupportLaborId(long supportLaborId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] { supportLaborId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] {
					supportLaborId,
					
					start, end, orderByComparator
				};
		}

		List<SupportWorker> list = (List<SupportWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportWorker supportWorker : list) {
				if ((supportLaborId != supportWorker.getSupportLaborId())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

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

				qPos.add(supportLaborId);

				list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findBySupportLaborId_First(long supportLaborId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchBySupportLaborId_First(supportLaborId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the first support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchBySupportLaborId_First(long supportLaborId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SupportWorker> list = findBySupportLaborId(supportLaborId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findBySupportLaborId_Last(long supportLaborId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchBySupportLaborId_Last(supportLaborId,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the last support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchBySupportLaborId_Last(long supportLaborId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySupportLaborId(supportLaborId);

		List<SupportWorker> list = findBySupportLaborId(supportLaborId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support workers before and after the current support worker in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportWorkerId the primary key of the current support worker
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker[] findBySupportLaborId_PrevAndNext(
		long supportWorkerId, long supportLaborId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = findByPrimaryKey(supportWorkerId);

		Session session = null;

		try {
			session = openSession();

			SupportWorker[] array = new SupportWorkerImpl[3];

			array[0] = getBySupportLaborId_PrevAndNext(session, supportWorker,
					supportLaborId, orderByComparator, true);

			array[1] = supportWorker;

			array[2] = getBySupportLaborId_PrevAndNext(session, supportWorker,
					supportLaborId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportWorker getBySupportLaborId_PrevAndNext(Session session,
		SupportWorker supportWorker, long supportLaborId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

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

		qPos.add(supportLaborId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the support worker where userId = &#63; and supportTeamId = &#63; or throws a {@link com.liferay.osb.NoSuchSupportWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findByU_STI(long userId, long supportTeamId)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchByU_STI(userId, supportTeamId);

		if (supportWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", supportTeamId=");
			msg.append(supportTeamId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSupportWorkerException(msg.toString());
		}

		return supportWorker;
	}

	/**
	 * Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchByU_STI(long userId, long supportTeamId)
		throws SystemException {
		return fetchByU_STI(userId, supportTeamId, true);
	}

	/**
	 * Returns the support worker where userId = &#63; and supportTeamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchByU_STI(long userId, long supportTeamId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, supportTeamId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_STI,
					finderArgs, this);
		}

		if (result instanceof SupportWorker) {
			SupportWorker supportWorker = (SupportWorker)result;

			if ((userId != supportWorker.getUserId()) ||
					(supportTeamId != supportWorker.getSupportTeamId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_STI_USERID_2);

			query.append(_FINDER_COLUMN_U_STI_SUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(supportTeamId);

				List<SupportWorker> list = q.list();

				result = list;

				SupportWorker supportWorker = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_STI,
						finderArgs, list);
				}
				else {
					supportWorker = list.get(0);

					cacheResult(supportWorker);

					if ((supportWorker.getUserId() != userId) ||
							(supportWorker.getSupportTeamId() != supportTeamId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_STI,
							finderArgs, supportWorker);
					}
				}

				return supportWorker;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_STI,
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
				return (SupportWorker)result;
			}
		}
	}

	/**
	 * Returns all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @return the matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByU_MW_R(long userId, double maxWork,
		int role) throws SystemException {
		return findByU_MW_R(userId, maxWork, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByU_MW_R(long userId, double maxWork,
		int role, int start, int end) throws SystemException {
		return findByU_MW_R(userId, maxWork, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByU_MW_R(long userId, double maxWork,
		int role, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_MW_R;
		finderArgs = new Object[] {
				userId, maxWork, role,
				
				start, end, orderByComparator
			};

		List<SupportWorker> list = (List<SupportWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportWorker supportWorker : list) {
				if ((userId != supportWorker.getUserId()) ||
						(maxWork != supportWorker.getMaxWork()) ||
						(role != supportWorker.getRole())) {
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

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_MW_R_USERID_2);

			query.append(_FINDER_COLUMN_U_MW_R_MAXWORK_2);

			query.append(_FINDER_COLUMN_U_MW_R_ROLE_2);

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

				qPos.add(maxWork);

				qPos.add(role);

				list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findByU_MW_R_First(long userId, double maxWork,
		int role, OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchByU_MW_R_First(userId, maxWork,
				role, orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", maxWork=");
		msg.append(maxWork);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the first support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchByU_MW_R_First(long userId, double maxWork,
		int role, OrderByComparator orderByComparator)
		throws SystemException {
		List<SupportWorker> list = findByU_MW_R(userId, maxWork, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker findByU_MW_R_Last(long userId, double maxWork,
		int role, OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = fetchByU_MW_R_Last(userId, maxWork, role,
				orderByComparator);

		if (supportWorker != null) {
			return supportWorker;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", maxWork=");
		msg.append(maxWork);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportWorkerException(msg.toString());
	}

	/**
	 * Returns the last support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support worker, or <code>null</code> if a matching support worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker fetchByU_MW_R_Last(long userId, double maxWork,
		int role, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByU_MW_R(userId, maxWork, role);

		List<SupportWorker> list = findByU_MW_R(userId, maxWork, role,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support workers before and after the current support worker in the ordered set where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * @param supportWorkerId the primary key of the current support worker
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support worker
	 * @throws com.liferay.osb.NoSuchSupportWorkerException if a support worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker[] findByU_MW_R_PrevAndNext(long supportWorkerId,
		long userId, double maxWork, int role,
		OrderByComparator orderByComparator)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = findByPrimaryKey(supportWorkerId);

		Session session = null;

		try {
			session = openSession();

			SupportWorker[] array = new SupportWorkerImpl[3];

			array[0] = getByU_MW_R_PrevAndNext(session, supportWorker, userId,
					maxWork, role, orderByComparator, true);

			array[1] = supportWorker;

			array[2] = getByU_MW_R_PrevAndNext(session, supportWorker, userId,
					maxWork, role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportWorker getByU_MW_R_PrevAndNext(Session session,
		SupportWorker supportWorker, long userId, double maxWork, int role,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

		query.append(_FINDER_COLUMN_U_MW_R_USERID_2);

		query.append(_FINDER_COLUMN_U_MW_R_MAXWORK_2);

		query.append(_FINDER_COLUMN_U_MW_R_ROLE_2);

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

		qPos.add(maxWork);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param maxWork the max work
	 * @param roles the roles
	 * @return the matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByU_MW_R(long[] userIds, double maxWork,
		int[] roles) throws SystemException {
		return findByU_MW_R(userIds, maxWork, roles, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param maxWork the max work
	 * @param roles the roles
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByU_MW_R(long[] userIds, double maxWork,
		int[] roles, int start, int end) throws SystemException {
		return findByU_MW_R(userIds, maxWork, roles, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param maxWork the max work
	 * @param roles the roles
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findByU_MW_R(long[] userIds, double maxWork,
		int[] roles, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_MW_R;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(userIds), maxWork, StringUtil.merge(roles)
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(userIds), maxWork, StringUtil.merge(roles),
					
					start, end, orderByComparator
				};
		}

		List<SupportWorker> list = (List<SupportWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportWorker supportWorker : list) {
				if (!ArrayUtil.contains(userIds, supportWorker.getUserId()) ||
						(maxWork != supportWorker.getMaxWork()) ||
						!ArrayUtil.contains(roles, supportWorker.getRole())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SUPPORTWORKER_WHERE);

			boolean conjunctionable = false;

			if ((userIds == null) || (userIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < userIds.length; i++) {
					query.append(_FINDER_COLUMN_U_MW_R_USERID_5);

					if ((i + 1) < userIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_MW_R_MAXWORK_5);

			conjunctionable = true;

			if ((roles == null) || (roles.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < roles.length; i++) {
					query.append(_FINDER_COLUMN_U_MW_R_ROLE_5);

					if ((i + 1) < roles.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
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

				if (userIds != null) {
					qPos.add(userIds);
				}

				qPos.add(maxWork);

				if (roles != null) {
					qPos.add(roles);
				}

				list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the support workers.
	 *
	 * @return the support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @return the range of support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support workers
	 * @param end the upper bound of the range of support workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportWorker> findAll(int start, int end,
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

		List<SupportWorker> list = (List<SupportWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SUPPORTWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTWORKER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SupportWorker>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the support workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (SupportWorker supportWorker : findByUserId(userId)) {
			remove(supportWorker);
		}
	}

	/**
	 * Removes all the support workers where supportTeamId = &#63; from the database.
	 *
	 * @param supportTeamId the support team ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportTeamId(long supportTeamId)
		throws SystemException {
		for (SupportWorker supportWorker : findBySupportTeamId(supportTeamId)) {
			remove(supportWorker);
		}
	}

	/**
	 * Removes all the support workers where supportLaborId = &#63; from the database.
	 *
	 * @param supportLaborId the support labor ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportLaborId(long supportLaborId)
		throws SystemException {
		for (SupportWorker supportWorker : findBySupportLaborId(supportLaborId)) {
			remove(supportWorker);
		}
	}

	/**
	 * Removes the support worker where userId = &#63; and supportTeamId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the support worker that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public SupportWorker removeByU_STI(long userId, long supportTeamId)
		throws NoSuchSupportWorkerException, SystemException {
		SupportWorker supportWorker = findByU_STI(userId, supportTeamId);

		return remove(supportWorker);
	}

	/**
	 * Removes all the support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_MW_R(long userId, double maxWork, int role)
		throws SystemException {
		for (SupportWorker supportWorker : findByU_MW_R(userId, maxWork, role)) {
			remove(supportWorker);
		}
	}

	/**
	 * Removes all the support workers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SupportWorker supportWorker : findAll()) {
			remove(supportWorker);
		}
	}

	/**
	 * Returns the number of support workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

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
	 * Returns the number of support workers where supportTeamId = &#63;.
	 *
	 * @param supportTeamId the support team ID
	 * @return the number of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportTeamId(long supportTeamId)
		throws SystemException {
		Object[] finderArgs = new Object[] { supportTeamId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportTeamId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTTEAMID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support workers where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the number of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportLaborId(long supportLaborId)
		throws SystemException {
		Object[] finderArgs = new Object[] { supportLaborId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLaborId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support workers where userId = &#63; and supportTeamId = &#63;.
	 *
	 * @param userId the user ID
	 * @param supportTeamId the support team ID
	 * @return the number of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_STI(long userId, long supportTeamId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, supportTeamId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_STI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_STI_USERID_2);

			query.append(_FINDER_COLUMN_U_STI_SUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(supportTeamId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_STI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support workers where userId = &#63; and maxWork &ne; &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param maxWork the max work
	 * @param role the role
	 * @return the number of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_MW_R(long userId, double maxWork, int role)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, maxWork, role };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_MW_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_MW_R_USERID_2);

			query.append(_FINDER_COLUMN_U_MW_R_MAXWORK_2);

			query.append(_FINDER_COLUMN_U_MW_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(maxWork);

				qPos.add(role);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_MW_R,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support workers where userId = any &#63; and maxWork &ne; &#63; and role = any &#63;.
	 *
	 * @param userIds the user IDs
	 * @param maxWork the max work
	 * @param roles the roles
	 * @return the number of matching support workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_MW_R(long[] userIds, double maxWork, int[] roles)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(userIds), maxWork, StringUtil.merge(roles)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_MW_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SUPPORTWORKER_WHERE);

			boolean conjunctionable = false;

			if ((userIds == null) || (userIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < userIds.length; i++) {
					query.append(_FINDER_COLUMN_U_MW_R_USERID_5);

					if ((i + 1) < userIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_MW_R_MAXWORK_5);

			conjunctionable = true;

			if ((roles == null) || (roles.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < roles.length; i++) {
					query.append(_FINDER_COLUMN_U_MW_R_ROLE_5);

					if ((i + 1) < roles.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userIds != null) {
					qPos.add(userIds);
				}

				qPos.add(maxWork);

				if (roles != null) {
					qPos.add(roles);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_MW_R,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support workers.
	 *
	 * @return the number of support workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTWORKER);

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
	 * Initializes the support worker persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.SupportWorker")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SupportWorker>> listenersList = new ArrayList<ModelListener<SupportWorker>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SupportWorker>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SupportWorkerImpl.class.getName());
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
	private static final String _SQL_SELECT_SUPPORTWORKER = "SELECT supportWorker FROM SupportWorker supportWorker";
	private static final String _SQL_SELECT_SUPPORTWORKER_WHERE = "SELECT supportWorker FROM SupportWorker supportWorker WHERE ";
	private static final String _SQL_COUNT_SUPPORTWORKER = "SELECT COUNT(supportWorker) FROM SupportWorker supportWorker";
	private static final String _SQL_COUNT_SUPPORTWORKER_WHERE = "SELECT COUNT(supportWorker) FROM SupportWorker supportWorker WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "supportWorker.userId = ?";
	private static final String _FINDER_COLUMN_SUPPORTTEAMID_SUPPORTTEAMID_2 = "supportWorker.supportTeamId = ?";
	private static final String _FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2 = "supportWorker.supportLaborId = ?";
	private static final String _FINDER_COLUMN_U_STI_USERID_2 = "supportWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_STI_SUPPORTTEAMID_2 = "supportWorker.supportTeamId = ?";
	private static final String _FINDER_COLUMN_U_MW_R_USERID_2 = "supportWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_MW_R_USERID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_MW_R_USERID_2) + ")";
	private static final String _FINDER_COLUMN_U_MW_R_MAXWORK_2 = "supportWorker.maxWork != ? AND ";
	private static final String _FINDER_COLUMN_U_MW_R_MAXWORK_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_MW_R_MAXWORK_2) + ")";
	private static final String _FINDER_COLUMN_U_MW_R_ROLE_2 = "supportWorker.role = ?";
	private static final String _FINDER_COLUMN_U_MW_R_ROLE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_MW_R_ROLE_2) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "supportWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportWorker exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SupportWorkerPersistenceImpl.class);
	private static SupportWorker _nullSupportWorker = new SupportWorkerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SupportWorker> toCacheModel() {
				return _nullSupportWorkerCacheModel;
			}
		};

	private static CacheModel<SupportWorker> _nullSupportWorkerCacheModel = new CacheModel<SupportWorker>() {
			public SupportWorker toEntityModel() {
				return _nullSupportWorker;
			}
		};
}
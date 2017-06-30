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

import com.liferay.osb.NoSuchTrainingWorkerException;
import com.liferay.osb.model.TrainingWorker;
import com.liferay.osb.model.impl.TrainingWorkerImpl;
import com.liferay.osb.model.impl.TrainingWorkerModelImpl;

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
 * The persistence implementation for the training worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingWorkerPersistence
 * @see TrainingWorkerUtil
 * @generated
 */
public class TrainingWorkerPersistenceImpl extends BasePersistenceImpl<TrainingWorker>
	implements TrainingWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingWorkerUtil} to access the training worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED,
			TrainingWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED,
			TrainingWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			TrainingWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED,
			TrainingWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED,
			TrainingWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			TrainingWorkerModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			TrainingWorkerModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_C_C = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED,
			TrainingWorkerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			TrainingWorkerModelImpl.USERID_COLUMN_BITMASK |
			TrainingWorkerModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			TrainingWorkerModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C_C = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED,
			TrainingWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED,
			TrainingWorkerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the training worker in the entity cache if it is enabled.
	 *
	 * @param trainingWorker the training worker
	 */
	public void cacheResult(TrainingWorker trainingWorker) {
		EntityCacheUtil.putResult(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerImpl.class, trainingWorker.getPrimaryKey(),
			trainingWorker);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C,
			new Object[] {
				Long.valueOf(trainingWorker.getUserId()),
				Long.valueOf(trainingWorker.getClassNameId()),
				Long.valueOf(trainingWorker.getClassPK())
			}, trainingWorker);

		trainingWorker.resetOriginalValues();
	}

	/**
	 * Caches the training workers in the entity cache if it is enabled.
	 *
	 * @param trainingWorkers the training workers
	 */
	public void cacheResult(List<TrainingWorker> trainingWorkers) {
		for (TrainingWorker trainingWorker : trainingWorkers) {
			if (EntityCacheUtil.getResult(
						TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
						TrainingWorkerImpl.class, trainingWorker.getPrimaryKey()) == null) {
				cacheResult(trainingWorker);
			}
			else {
				trainingWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training workers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingWorkerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingWorkerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training worker.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingWorker trainingWorker) {
		EntityCacheUtil.removeResult(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerImpl.class, trainingWorker.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(trainingWorker);
	}

	@Override
	public void clearCache(List<TrainingWorker> trainingWorkers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingWorker trainingWorker : trainingWorkers) {
			EntityCacheUtil.removeResult(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
				TrainingWorkerImpl.class, trainingWorker.getPrimaryKey());

			clearUniqueFindersCache(trainingWorker);
		}
	}

	protected void cacheUniqueFindersCache(TrainingWorker trainingWorker) {
		if (trainingWorker.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(trainingWorker.getUserId()),
					Long.valueOf(trainingWorker.getClassNameId()),
					Long.valueOf(trainingWorker.getClassPK())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C, args,
				trainingWorker);
		}
		else {
			TrainingWorkerModelImpl trainingWorkerModelImpl = (TrainingWorkerModelImpl)trainingWorker;

			if ((trainingWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingWorker.getUserId()),
						Long.valueOf(trainingWorker.getClassNameId()),
						Long.valueOf(trainingWorker.getClassPK())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C, args,
					trainingWorker);
			}
		}
	}

	protected void clearUniqueFindersCache(TrainingWorker trainingWorker) {
		TrainingWorkerModelImpl trainingWorkerModelImpl = (TrainingWorkerModelImpl)trainingWorker;

		Object[] args = new Object[] {
				Long.valueOf(trainingWorker.getUserId()),
				Long.valueOf(trainingWorker.getClassNameId()),
				Long.valueOf(trainingWorker.getClassPK())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_C_C, args);

		if ((trainingWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_C_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(trainingWorkerModelImpl.getOriginalUserId()),
					Long.valueOf(trainingWorkerModelImpl.getOriginalClassNameId()),
					Long.valueOf(trainingWorkerModelImpl.getOriginalClassPK())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_C_C, args);
		}
	}

	/**
	 * Creates a new training worker with the primary key. Does not add the training worker to the database.
	 *
	 * @param trainingWorkerId the primary key for the new training worker
	 * @return the new training worker
	 */
	public TrainingWorker create(long trainingWorkerId) {
		TrainingWorker trainingWorker = new TrainingWorkerImpl();

		trainingWorker.setNew(true);
		trainingWorker.setPrimaryKey(trainingWorkerId);

		return trainingWorker;
	}

	/**
	 * Removes the training worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingWorkerId the primary key of the training worker
	 * @return the training worker that was removed
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker remove(long trainingWorkerId)
		throws NoSuchTrainingWorkerException, SystemException {
		return remove(Long.valueOf(trainingWorkerId));
	}

	/**
	 * Removes the training worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training worker
	 * @return the training worker that was removed
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingWorker remove(Serializable primaryKey)
		throws NoSuchTrainingWorkerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingWorker trainingWorker = (TrainingWorker)session.get(TrainingWorkerImpl.class,
					primaryKey);

			if (trainingWorker == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingWorker);
		}
		catch (NoSuchTrainingWorkerException nsee) {
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
	protected TrainingWorker removeImpl(TrainingWorker trainingWorker)
		throws SystemException {
		trainingWorker = toUnwrappedModel(trainingWorker);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingWorker);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingWorker);

		return trainingWorker;
	}

	@Override
	public TrainingWorker updateImpl(
		com.liferay.osb.model.TrainingWorker trainingWorker, boolean merge)
		throws SystemException {
		trainingWorker = toUnwrappedModel(trainingWorker);

		boolean isNew = trainingWorker.isNew();

		TrainingWorkerModelImpl trainingWorkerModelImpl = (TrainingWorkerModelImpl)trainingWorker;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingWorker, merge);

			trainingWorker.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrainingWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((trainingWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingWorkerModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(trainingWorkerModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((trainingWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingWorkerModelImpl.getOriginalClassNameId()),
						Long.valueOf(trainingWorkerModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(trainingWorkerModelImpl.getClassNameId()),
						Long.valueOf(trainingWorkerModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}
		}

		EntityCacheUtil.putResult(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingWorkerImpl.class, trainingWorker.getPrimaryKey(),
			trainingWorker);

		clearUniqueFindersCache(trainingWorker);
		cacheUniqueFindersCache(trainingWorker);

		return trainingWorker;
	}

	protected TrainingWorker toUnwrappedModel(TrainingWorker trainingWorker) {
		if (trainingWorker instanceof TrainingWorkerImpl) {
			return trainingWorker;
		}

		TrainingWorkerImpl trainingWorkerImpl = new TrainingWorkerImpl();

		trainingWorkerImpl.setNew(trainingWorker.isNew());
		trainingWorkerImpl.setPrimaryKey(trainingWorker.getPrimaryKey());

		trainingWorkerImpl.setTrainingWorkerId(trainingWorker.getTrainingWorkerId());
		trainingWorkerImpl.setUserId(trainingWorker.getUserId());
		trainingWorkerImpl.setClassNameId(trainingWorker.getClassNameId());
		trainingWorkerImpl.setClassPK(trainingWorker.getClassPK());
		trainingWorkerImpl.setUserProfileHistoryId(trainingWorker.getUserProfileHistoryId());

		return trainingWorkerImpl;
	}

	/**
	 * Returns the training worker with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training worker
	 * @return the training worker
	 * @throws com.liferay.portal.NoSuchModelException if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training worker with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingWorkerException} if it could not be found.
	 *
	 * @param trainingWorkerId the primary key of the training worker
	 * @return the training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker findByPrimaryKey(long trainingWorkerId)
		throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = fetchByPrimaryKey(trainingWorkerId);

		if (trainingWorker == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + trainingWorkerId);
			}

			throw new NoSuchTrainingWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingWorkerId);
		}

		return trainingWorker;
	}

	/**
	 * Returns the training worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training worker
	 * @return the training worker, or <code>null</code> if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingWorker fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingWorkerId the primary key of the training worker
	 * @return the training worker, or <code>null</code> if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker fetchByPrimaryKey(long trainingWorkerId)
		throws SystemException {
		TrainingWorker trainingWorker = (TrainingWorker)EntityCacheUtil.getResult(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
				TrainingWorkerImpl.class, trainingWorkerId);

		if (trainingWorker == _nullTrainingWorker) {
			return null;
		}

		if (trainingWorker == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingWorker = (TrainingWorker)session.get(TrainingWorkerImpl.class,
						Long.valueOf(trainingWorkerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingWorker != null) {
					cacheResult(trainingWorker);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingWorkerModelImpl.ENTITY_CACHE_ENABLED,
						TrainingWorkerImpl.class, trainingWorkerId,
						_nullTrainingWorker);
				}

				closeSession(session);
			}
		}

		return trainingWorker;
	}

	/**
	 * Returns all the training workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of training workers
	 * @param end the upper bound of the range of training workers (not inclusive)
	 * @return the range of matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of training workers
	 * @param end the upper bound of the range of training workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findByUserId(long userId, int start, int end,
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

		List<TrainingWorker> list = (List<TrainingWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingWorker trainingWorker : list) {
				if ((userId != trainingWorker.getUserId())) {
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

			query.append(_SQL_SELECT_TRAININGWORKER_WHERE);

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

				list = (List<TrainingWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (trainingWorker != null) {
			return trainingWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingWorkerException(msg.toString());
	}

	/**
	 * Returns the first training worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training worker, or <code>null</code> if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = fetchByUserId_Last(userId,
				orderByComparator);

		if (trainingWorker != null) {
			return trainingWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingWorkerException(msg.toString());
	}

	/**
	 * Returns the last training worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training worker, or <code>null</code> if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<TrainingWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training workers before and after the current training worker in the ordered set where userId = &#63;.
	 *
	 * @param trainingWorkerId the primary key of the current training worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker[] findByUserId_PrevAndNext(long trainingWorkerId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = findByPrimaryKey(trainingWorkerId);

		Session session = null;

		try {
			session = openSession();

			TrainingWorker[] array = new TrainingWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, trainingWorker, userId,
					orderByComparator, true);

			array[1] = trainingWorker;

			array[2] = getByUserId_PrevAndNext(session, trainingWorker, userId,
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

	protected TrainingWorker getByUserId_PrevAndNext(Session session,
		TrainingWorker trainingWorker, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGWORKER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(trainingWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training workers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training workers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of training workers
	 * @param end the upper bound of the range of training workers (not inclusive)
	 * @return the range of matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training workers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of training workers
	 * @param end the upper bound of the range of training workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findByC_C(long classNameId, long classPK,
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

		List<TrainingWorker> list = (List<TrainingWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingWorker trainingWorker : list) {
				if ((classNameId != trainingWorker.getClassNameId()) ||
						(classPK != trainingWorker.getClassPK())) {
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

			query.append(_SQL_SELECT_TRAININGWORKER_WHERE);

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

				list = (List<TrainingWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (trainingWorker != null) {
			return trainingWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingWorkerException(msg.toString());
	}

	/**
	 * Returns the first training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training worker, or <code>null</code> if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingWorker> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (trainingWorker != null) {
			return trainingWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingWorkerException(msg.toString());
	}

	/**
	 * Returns the last training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training worker, or <code>null</code> if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<TrainingWorker> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training workers before and after the current training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param trainingWorkerId the primary key of the current training worker
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker[] findByC_C_PrevAndNext(long trainingWorkerId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = findByPrimaryKey(trainingWorkerId);

		Session session = null;

		try {
			session = openSession();

			TrainingWorker[] array = new TrainingWorkerImpl[3];

			array[0] = getByC_C_PrevAndNext(session, trainingWorker,
					classNameId, classPK, orderByComparator, true);

			array[1] = trainingWorker;

			array[2] = getByC_C_PrevAndNext(session, trainingWorker,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingWorker getByC_C_PrevAndNext(Session session,
		TrainingWorker trainingWorker, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGWORKER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(trainingWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching training worker
	 * @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker findByU_C_C(long userId, long classNameId,
		long classPK) throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = fetchByU_C_C(userId, classNameId,
				classPK);

		if (trainingWorker == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTrainingWorkerException(msg.toString());
		}

		return trainingWorker;
	}

	/**
	 * Returns the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching training worker, or <code>null</code> if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker fetchByU_C_C(long userId, long classNameId,
		long classPK) throws SystemException {
		return fetchByU_C_C(userId, classNameId, classPK, true);
	}

	/**
	 * Returns the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching training worker, or <code>null</code> if a matching training worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker fetchByU_C_C(long userId, long classNameId,
		long classPK, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_C_C,
					finderArgs, this);
		}

		if (result instanceof TrainingWorker) {
			TrainingWorker trainingWorker = (TrainingWorker)result;

			if ((userId != trainingWorker.getUserId()) ||
					(classNameId != trainingWorker.getClassNameId()) ||
					(classPK != trainingWorker.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TRAININGWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<TrainingWorker> list = q.list();

				result = list;

				TrainingWorker trainingWorker = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C,
						finderArgs, list);
				}
				else {
					trainingWorker = list.get(0);

					cacheResult(trainingWorker);

					if ((trainingWorker.getUserId() != userId) ||
							(trainingWorker.getClassNameId() != classNameId) ||
							(trainingWorker.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C,
							finderArgs, trainingWorker);
					}
				}

				return trainingWorker;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_C_C,
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
				return (TrainingWorker)result;
			}
		}
	}

	/**
	 * Returns all the training workers.
	 *
	 * @return the training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training workers
	 * @param end the upper bound of the range of training workers (not inclusive)
	 * @return the range of training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training workers
	 * @param end the upper bound of the range of training workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingWorker> findAll(int start, int end,
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

		List<TrainingWorker> list = (List<TrainingWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGWORKER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingWorker>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingWorker>)QueryUtil.list(q,
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
	 * Removes all the training workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (TrainingWorker trainingWorker : findByUserId(userId)) {
			remove(trainingWorker);
		}
	}

	/**
	 * Removes all the training workers where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (TrainingWorker trainingWorker : findByC_C(classNameId, classPK)) {
			remove(trainingWorker);
		}
	}

	/**
	 * Removes the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the training worker that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingWorker removeByU_C_C(long userId, long classNameId,
		long classPK) throws NoSuchTrainingWorkerException, SystemException {
		TrainingWorker trainingWorker = findByU_C_C(userId, classNameId, classPK);

		return remove(trainingWorker);
	}

	/**
	 * Removes all the training workers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingWorker trainingWorker : findAll()) {
			remove(trainingWorker);
		}
	}

	/**
	 * Returns the number of training workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGWORKER_WHERE);

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
	 * Returns the number of training workers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRAININGWORKER_WHERE);

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
	 * Returns the number of training workers where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching training workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_C_C(long userId, long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TRAININGWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training workers.
	 *
	 * @return the number of training workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGWORKER);

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
	 * Initializes the training worker persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingWorker")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingWorker>> listenersList = new ArrayList<ModelListener<TrainingWorker>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingWorker>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingWorkerImpl.class.getName());
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
	private static final String _SQL_SELECT_TRAININGWORKER = "SELECT trainingWorker FROM TrainingWorker trainingWorker";
	private static final String _SQL_SELECT_TRAININGWORKER_WHERE = "SELECT trainingWorker FROM TrainingWorker trainingWorker WHERE ";
	private static final String _SQL_COUNT_TRAININGWORKER = "SELECT COUNT(trainingWorker) FROM TrainingWorker trainingWorker";
	private static final String _SQL_COUNT_TRAININGWORKER_WHERE = "SELECT COUNT(trainingWorker) FROM TrainingWorker trainingWorker WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "trainingWorker.userId = ?";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "trainingWorker.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "trainingWorker.classPK = ?";
	private static final String _FINDER_COLUMN_U_C_C_USERID_2 = "trainingWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_CLASSNAMEID_2 = "trainingWorker.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_CLASSPK_2 = "trainingWorker.classPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingWorker exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingWorkerPersistenceImpl.class);
	private static TrainingWorker _nullTrainingWorker = new TrainingWorkerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingWorker> toCacheModel() {
				return _nullTrainingWorkerCacheModel;
			}
		};

	private static CacheModel<TrainingWorker> _nullTrainingWorkerCacheModel = new CacheModel<TrainingWorker>() {
			public TrainingWorker toEntityModel() {
				return _nullTrainingWorker;
			}
		};
}
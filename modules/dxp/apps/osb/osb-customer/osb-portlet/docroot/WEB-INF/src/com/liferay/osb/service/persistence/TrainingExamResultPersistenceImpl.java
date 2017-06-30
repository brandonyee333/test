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

import com.liferay.osb.NoSuchTrainingExamResultException;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.impl.TrainingExamResultImpl;
import com.liferay.osb.model.impl.TrainingExamResultModelImpl;

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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the training exam result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultPersistence
 * @see TrainingExamResultUtil
 * @generated
 */
public class TrainingExamResultPersistenceImpl extends BasePersistenceImpl<TrainingExamResult>
	implements TrainingExamResultPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingExamResultUtil} to access the training exam result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingExamResultImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTCREATEDATE =
		new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtCreateDate",
			new String[] {
				Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCREATEDATE =
		new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtCreateDate",
			new String[] { Date.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER = new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByRegistrationNumber",
			new String[] { String.class.getName() },
			TrainingExamResultModelImpl.REGISTRATIONNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REGISTRATIONNUMBER = new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByRegistrationNumber", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTSD_G_NOTS =
		new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtSD_G_NotS",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTSD_G_NOTS =
		new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtSD_G_NotS",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the training exam result in the entity cache if it is enabled.
	 *
	 * @param trainingExamResult the training exam result
	 */
	public void cacheResult(TrainingExamResult trainingExamResult) {
		EntityCacheUtil.putResult(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultImpl.class, trainingExamResult.getPrimaryKey(),
			trainingExamResult);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
			new Object[] { trainingExamResult.getRegistrationNumber() },
			trainingExamResult);

		trainingExamResult.resetOriginalValues();
	}

	/**
	 * Caches the training exam results in the entity cache if it is enabled.
	 *
	 * @param trainingExamResults the training exam results
	 */
	public void cacheResult(List<TrainingExamResult> trainingExamResults) {
		for (TrainingExamResult trainingExamResult : trainingExamResults) {
			if (EntityCacheUtil.getResult(
						TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
						TrainingExamResultImpl.class,
						trainingExamResult.getPrimaryKey()) == null) {
				cacheResult(trainingExamResult);
			}
			else {
				trainingExamResult.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training exam results.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingExamResultImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingExamResultImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training exam result.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingExamResult trainingExamResult) {
		EntityCacheUtil.removeResult(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultImpl.class, trainingExamResult.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(trainingExamResult);
	}

	@Override
	public void clearCache(List<TrainingExamResult> trainingExamResults) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingExamResult trainingExamResult : trainingExamResults) {
			EntityCacheUtil.removeResult(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
				TrainingExamResultImpl.class, trainingExamResult.getPrimaryKey());

			clearUniqueFindersCache(trainingExamResult);
		}
	}

	protected void cacheUniqueFindersCache(
		TrainingExamResult trainingExamResult) {
		if (trainingExamResult.isNew()) {
			Object[] args = new Object[] {
					trainingExamResult.getRegistrationNumber()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REGISTRATIONNUMBER,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
				args, trainingExamResult);
		}
		else {
			TrainingExamResultModelImpl trainingExamResultModelImpl = (TrainingExamResultModelImpl)trainingExamResult;

			if ((trainingExamResultModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trainingExamResult.getRegistrationNumber()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REGISTRATIONNUMBER,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
					args, trainingExamResult);
			}
		}
	}

	protected void clearUniqueFindersCache(
		TrainingExamResult trainingExamResult) {
		TrainingExamResultModelImpl trainingExamResultModelImpl = (TrainingExamResultModelImpl)trainingExamResult;

		Object[] args = new Object[] { trainingExamResult.getRegistrationNumber() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REGISTRATIONNUMBER,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
			args);

		if ((trainingExamResultModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER.getColumnBitmask()) != 0) {
			args = new Object[] {
					trainingExamResultModelImpl.getOriginalRegistrationNumber()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REGISTRATIONNUMBER,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
				args);
		}
	}

	/**
	 * Creates a new training exam result with the primary key. Does not add the training exam result to the database.
	 *
	 * @param trainingExamResultId the primary key for the new training exam result
	 * @return the new training exam result
	 */
	public TrainingExamResult create(long trainingExamResultId) {
		TrainingExamResult trainingExamResult = new TrainingExamResultImpl();

		trainingExamResult.setNew(true);
		trainingExamResult.setPrimaryKey(trainingExamResultId);

		return trainingExamResult;
	}

	/**
	 * Removes the training exam result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingExamResultId the primary key of the training exam result
	 * @return the training exam result that was removed
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult remove(long trainingExamResultId)
		throws NoSuchTrainingExamResultException, SystemException {
		return remove(Long.valueOf(trainingExamResultId));
	}

	/**
	 * Removes the training exam result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training exam result
	 * @return the training exam result that was removed
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExamResult remove(Serializable primaryKey)
		throws NoSuchTrainingExamResultException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingExamResult trainingExamResult = (TrainingExamResult)session.get(TrainingExamResultImpl.class,
					primaryKey);

			if (trainingExamResult == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingExamResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingExamResult);
		}
		catch (NoSuchTrainingExamResultException nsee) {
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
	protected TrainingExamResult removeImpl(
		TrainingExamResult trainingExamResult) throws SystemException {
		trainingExamResult = toUnwrappedModel(trainingExamResult);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingExamResult);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingExamResult);

		return trainingExamResult;
	}

	@Override
	public TrainingExamResult updateImpl(
		com.liferay.osb.model.TrainingExamResult trainingExamResult,
		boolean merge) throws SystemException {
		trainingExamResult = toUnwrappedModel(trainingExamResult);

		boolean isNew = trainingExamResult.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingExamResult, merge);

			trainingExamResult.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrainingExamResultModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultImpl.class, trainingExamResult.getPrimaryKey(),
			trainingExamResult);

		clearUniqueFindersCache(trainingExamResult);
		cacheUniqueFindersCache(trainingExamResult);

		return trainingExamResult;
	}

	protected TrainingExamResult toUnwrappedModel(
		TrainingExamResult trainingExamResult) {
		if (trainingExamResult instanceof TrainingExamResultImpl) {
			return trainingExamResult;
		}

		TrainingExamResultImpl trainingExamResultImpl = new TrainingExamResultImpl();

		trainingExamResultImpl.setNew(trainingExamResult.isNew());
		trainingExamResultImpl.setPrimaryKey(trainingExamResult.getPrimaryKey());

		trainingExamResultImpl.setTrainingExamResultId(trainingExamResult.getTrainingExamResultId());
		trainingExamResultImpl.setCreateDate(trainingExamResult.getCreateDate());
		trainingExamResultImpl.setTrainingExamId(trainingExamResult.getTrainingExamId());
		trainingExamResultImpl.setRecordType(trainingExamResult.getRecordType());
		trainingExamResultImpl.setRegistrationNumber(trainingExamResult.getRegistrationNumber());
		trainingExamResultImpl.setFormKey(trainingExamResult.getFormKey());
		trainingExamResultImpl.setStartDate(trainingExamResult.getStartDate());
		trainingExamResultImpl.setTestScore(trainingExamResult.getTestScore());
		trainingExamResultImpl.setCorrectCount(trainingExamResult.getCorrectCount());
		trainingExamResultImpl.setIncorrectCount(trainingExamResult.getIncorrectCount());
		trainingExamResultImpl.setSkippedCount(trainingExamResult.getSkippedCount());
		trainingExamResultImpl.setGrade(trainingExamResult.getGrade());
		trainingExamResultImpl.setStatus(trainingExamResult.getStatus());

		return trainingExamResultImpl;
	}

	/**
	 * Returns the training exam result with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training exam result
	 * @return the training exam result
	 * @throws com.liferay.portal.NoSuchModelException if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExamResult findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training exam result with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamResultException} if it could not be found.
	 *
	 * @param trainingExamResultId the primary key of the training exam result
	 * @return the training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult findByPrimaryKey(long trainingExamResultId)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = fetchByPrimaryKey(trainingExamResultId);

		if (trainingExamResult == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					trainingExamResultId);
			}

			throw new NoSuchTrainingExamResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingExamResultId);
		}

		return trainingExamResult;
	}

	/**
	 * Returns the training exam result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training exam result
	 * @return the training exam result, or <code>null</code> if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExamResult fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training exam result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingExamResultId the primary key of the training exam result
	 * @return the training exam result, or <code>null</code> if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult fetchByPrimaryKey(long trainingExamResultId)
		throws SystemException {
		TrainingExamResult trainingExamResult = (TrainingExamResult)EntityCacheUtil.getResult(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
				TrainingExamResultImpl.class, trainingExamResultId);

		if (trainingExamResult == _nullTrainingExamResult) {
			return null;
		}

		if (trainingExamResult == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingExamResult = (TrainingExamResult)session.get(TrainingExamResultImpl.class,
						Long.valueOf(trainingExamResultId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingExamResult != null) {
					cacheResult(trainingExamResult);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingExamResultModelImpl.ENTITY_CACHE_ENABLED,
						TrainingExamResultImpl.class, trainingExamResultId,
						_nullTrainingExamResult);
				}

				closeSession(session);
			}
		}

		return trainingExamResult;
	}

	/**
	 * Returns all the training exam results where createDate &gt; &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findByGtCreateDate(Date createDate)
		throws SystemException {
		return findByGtCreateDate(createDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exam results where createDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of training exam results
	 * @param end the upper bound of the range of training exam results (not inclusive)
	 * @return the range of matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findByGtCreateDate(Date createDate,
		int start, int end) throws SystemException {
		return findByGtCreateDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exam results where createDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of training exam results
	 * @param end the upper bound of the range of training exam results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findByGtCreateDate(Date createDate,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTCREATEDATE;
		finderArgs = new Object[] { createDate, start, end, orderByComparator };

		List<TrainingExamResult> list = (List<TrainingExamResult>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingExamResult trainingExamResult : list) {
				if (!Validator.equals(createDate,
							trainingExamResult.getCreateDate())) {
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

			query.append(_SQL_SELECT_TRAININGEXAMRESULT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_GTCREATEDATE_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTCREATEDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingExamResultModelImpl.ORDER_BY_JPQL);
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

				list = (List<TrainingExamResult>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first training exam result in the ordered set where createDate &gt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult findByGtCreateDate_First(Date createDate,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = fetchByGtCreateDate_First(createDate,
				orderByComparator);

		if (trainingExamResult != null) {
			return trainingExamResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamResultException(msg.toString());
	}

	/**
	 * Returns the first training exam result in the ordered set where createDate &gt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam result, or <code>null</code> if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult fetchByGtCreateDate_First(Date createDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingExamResult> list = findByGtCreateDate(createDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training exam result in the ordered set where createDate &gt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult findByGtCreateDate_Last(Date createDate,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = fetchByGtCreateDate_Last(createDate,
				orderByComparator);

		if (trainingExamResult != null) {
			return trainingExamResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamResultException(msg.toString());
	}

	/**
	 * Returns the last training exam result in the ordered set where createDate &gt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam result, or <code>null</code> if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult fetchByGtCreateDate_Last(Date createDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGtCreateDate(createDate);

		List<TrainingExamResult> list = findByGtCreateDate(createDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training exam results before and after the current training exam result in the ordered set where createDate &gt; &#63;.
	 *
	 * @param trainingExamResultId the primary key of the current training exam result
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult[] findByGtCreateDate_PrevAndNext(
		long trainingExamResultId, Date createDate,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = findByPrimaryKey(trainingExamResultId);

		Session session = null;

		try {
			session = openSession();

			TrainingExamResult[] array = new TrainingExamResultImpl[3];

			array[0] = getByGtCreateDate_PrevAndNext(session,
					trainingExamResult, createDate, orderByComparator, true);

			array[1] = trainingExamResult;

			array[2] = getByGtCreateDate_PrevAndNext(session,
					trainingExamResult, createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingExamResult getByGtCreateDate_PrevAndNext(
		Session session, TrainingExamResult trainingExamResult,
		Date createDate, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEXAMRESULT_WHERE);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_GTCREATEDATE_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_GTCREATEDATE_CREATEDATE_2);
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
			query.append(TrainingExamResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingExamResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingExamResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the training exam result where registrationNumber = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingExamResultException} if it could not be found.
	 *
	 * @param registrationNumber the registration number
	 * @return the matching training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult findByRegistrationNumber(
		String registrationNumber)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = fetchByRegistrationNumber(registrationNumber);

		if (trainingExamResult == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("registrationNumber=");
			msg.append(registrationNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTrainingExamResultException(msg.toString());
		}

		return trainingExamResult;
	}

	/**
	 * Returns the training exam result where registrationNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param registrationNumber the registration number
	 * @return the matching training exam result, or <code>null</code> if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult fetchByRegistrationNumber(
		String registrationNumber) throws SystemException {
		return fetchByRegistrationNumber(registrationNumber, true);
	}

	/**
	 * Returns the training exam result where registrationNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param registrationNumber the registration number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching training exam result, or <code>null</code> if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult fetchByRegistrationNumber(
		String registrationNumber, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { registrationNumber };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
					finderArgs, this);
		}

		if (result instanceof TrainingExamResult) {
			TrainingExamResult trainingExamResult = (TrainingExamResult)result;

			if (!Validator.equals(registrationNumber,
						trainingExamResult.getRegistrationNumber())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TRAININGEXAMRESULT_WHERE);

			if (registrationNumber == null) {
				query.append(_FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_1);
			}
			else {
				if (registrationNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_2);
				}
			}

			query.append(TrainingExamResultModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (registrationNumber != null) {
					qPos.add(registrationNumber);
				}

				List<TrainingExamResult> list = q.list();

				result = list;

				TrainingExamResult trainingExamResult = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
						finderArgs, list);
				}
				else {
					trainingExamResult = list.get(0);

					cacheResult(trainingExamResult);

					if ((trainingExamResult.getRegistrationNumber() == null) ||
							!trainingExamResult.getRegistrationNumber()
												   .equals(registrationNumber)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
							finderArgs, trainingExamResult);
					}
				}

				return trainingExamResult;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REGISTRATIONNUMBER,
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
				return (TrainingExamResult)result;
			}
		}
	}

	/**
	 * Returns all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @return the matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findByGtSD_G_NotS(Date startDate,
		int grade, int status) throws SystemException {
		return findByGtSD_G_NotS(startDate, grade, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @param start the lower bound of the range of training exam results
	 * @param end the upper bound of the range of training exam results (not inclusive)
	 * @return the range of matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findByGtSD_G_NotS(Date startDate,
		int grade, int status, int start, int end) throws SystemException {
		return findByGtSD_G_NotS(startDate, grade, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @param start the lower bound of the range of training exam results
	 * @param end the upper bound of the range of training exam results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findByGtSD_G_NotS(Date startDate,
		int grade, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTSD_G_NOTS;
		finderArgs = new Object[] {
				startDate, grade, status,
				
				start, end, orderByComparator
			};

		List<TrainingExamResult> list = (List<TrainingExamResult>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingExamResult trainingExamResult : list) {
				if (!Validator.equals(startDate,
							trainingExamResult.getStartDate()) ||
						(grade != trainingExamResult.getGrade()) ||
						(status != trainingExamResult.getStatus())) {
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
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TRAININGEXAMRESULT_WHERE);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_2);
			}

			query.append(_FINDER_COLUMN_GTSD_G_NOTS_GRADE_2);

			query.append(_FINDER_COLUMN_GTSD_G_NOTS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingExamResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				qPos.add(grade);

				qPos.add(status);

				list = (List<TrainingExamResult>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult findByGtSD_G_NotS_First(Date startDate,
		int grade, int status, OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = fetchByGtSD_G_NotS_First(startDate,
				grade, status, orderByComparator);

		if (trainingExamResult != null) {
			return trainingExamResult;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("startDate=");
		msg.append(startDate);

		msg.append(", grade=");
		msg.append(grade);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamResultException(msg.toString());
	}

	/**
	 * Returns the first training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam result, or <code>null</code> if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult fetchByGtSD_G_NotS_First(Date startDate,
		int grade, int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<TrainingExamResult> list = findByGtSD_G_NotS(startDate, grade,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult findByGtSD_G_NotS_Last(Date startDate, int grade,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = fetchByGtSD_G_NotS_Last(startDate,
				grade, status, orderByComparator);

		if (trainingExamResult != null) {
			return trainingExamResult;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("startDate=");
		msg.append(startDate);

		msg.append(", grade=");
		msg.append(grade);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamResultException(msg.toString());
	}

	/**
	 * Returns the last training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam result, or <code>null</code> if a matching training exam result could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult fetchByGtSD_G_NotS_Last(Date startDate,
		int grade, int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGtSD_G_NotS(startDate, grade, status);

		List<TrainingExamResult> list = findByGtSD_G_NotS(startDate, grade,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training exam results before and after the current training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * @param trainingExamResultId the primary key of the current training exam result
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training exam result
	 * @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult[] findByGtSD_G_NotS_PrevAndNext(
		long trainingExamResultId, Date startDate, int grade, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = findByPrimaryKey(trainingExamResultId);

		Session session = null;

		try {
			session = openSession();

			TrainingExamResult[] array = new TrainingExamResultImpl[3];

			array[0] = getByGtSD_G_NotS_PrevAndNext(session,
					trainingExamResult, startDate, grade, status,
					orderByComparator, true);

			array[1] = trainingExamResult;

			array[2] = getByGtSD_G_NotS_PrevAndNext(session,
					trainingExamResult, startDate, grade, status,
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

	protected TrainingExamResult getByGtSD_G_NotS_PrevAndNext(Session session,
		TrainingExamResult trainingExamResult, Date startDate, int grade,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEXAMRESULT_WHERE);

		if (startDate == null) {
			query.append(_FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_2);
		}

		query.append(_FINDER_COLUMN_GTSD_G_NOTS_GRADE_2);

		query.append(_FINDER_COLUMN_GTSD_G_NOTS_STATUS_2);

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
			query.append(TrainingExamResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (startDate != null) {
			qPos.add(CalendarUtil.getTimestamp(startDate));
		}

		qPos.add(grade);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingExamResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingExamResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training exam results.
	 *
	 * @return the training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exam results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training exam results
	 * @param end the upper bound of the range of training exam results (not inclusive)
	 * @return the range of training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exam results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training exam results
	 * @param end the upper bound of the range of training exam results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResult> findAll(int start, int end,
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

		List<TrainingExamResult> list = (List<TrainingExamResult>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGEXAMRESULT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGEXAMRESULT.concat(TrainingExamResultModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingExamResult>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingExamResult>)QueryUtil.list(q,
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
	 * Removes all the training exam results where createDate &gt; &#63; from the database.
	 *
	 * @param createDate the create date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGtCreateDate(Date createDate) throws SystemException {
		for (TrainingExamResult trainingExamResult : findByGtCreateDate(
				createDate)) {
			remove(trainingExamResult);
		}
	}

	/**
	 * Removes the training exam result where registrationNumber = &#63; from the database.
	 *
	 * @param registrationNumber the registration number
	 * @return the training exam result that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResult removeByRegistrationNumber(
		String registrationNumber)
		throws NoSuchTrainingExamResultException, SystemException {
		TrainingExamResult trainingExamResult = findByRegistrationNumber(registrationNumber);

		return remove(trainingExamResult);
	}

	/**
	 * Removes all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63; from the database.
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGtSD_G_NotS(Date startDate, int grade, int status)
		throws SystemException {
		for (TrainingExamResult trainingExamResult : findByGtSD_G_NotS(
				startDate, grade, status)) {
			remove(trainingExamResult);
		}
	}

	/**
	 * Removes all the training exam results from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingExamResult trainingExamResult : findAll()) {
			remove(trainingExamResult);
		}
	}

	/**
	 * Returns the number of training exam results where createDate &gt; &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGtCreateDate(Date createDate) throws SystemException {
		Object[] finderArgs = new Object[] { createDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCREATEDATE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEXAMRESULT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_GTCREATEDATE_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTCREATEDATE_CREATEDATE_2);
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

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCREATEDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training exam results where registrationNumber = &#63;.
	 *
	 * @param registrationNumber the registration number
	 * @return the number of matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRegistrationNumber(String registrationNumber)
		throws SystemException {
		Object[] finderArgs = new Object[] { registrationNumber };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REGISTRATIONNUMBER,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEXAMRESULT_WHERE);

			if (registrationNumber == null) {
				query.append(_FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_1);
			}
			else {
				if (registrationNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (registrationNumber != null) {
					qPos.add(registrationNumber);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REGISTRATIONNUMBER,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	 *
	 * @param startDate the start date
	 * @param grade the grade
	 * @param status the status
	 * @return the number of matching training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGtSD_G_NotS(Date startDate, int grade, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { startDate, grade, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTSD_G_NOTS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TRAININGEXAMRESULT_WHERE);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_2);
			}

			query.append(_FINDER_COLUMN_GTSD_G_NOTS_GRADE_2);

			query.append(_FINDER_COLUMN_GTSD_G_NOTS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				qPos.add(grade);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTSD_G_NOTS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training exam results.
	 *
	 * @return the number of training exam results
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGEXAMRESULT);

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
	 * Initializes the training exam result persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingExamResult")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingExamResult>> listenersList = new ArrayList<ModelListener<TrainingExamResult>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingExamResult>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingExamResultImpl.class.getName());
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
	private static final String _SQL_SELECT_TRAININGEXAMRESULT = "SELECT trainingExamResult FROM TrainingExamResult trainingExamResult";
	private static final String _SQL_SELECT_TRAININGEXAMRESULT_WHERE = "SELECT trainingExamResult FROM TrainingExamResult trainingExamResult WHERE ";
	private static final String _SQL_COUNT_TRAININGEXAMRESULT = "SELECT COUNT(trainingExamResult) FROM TrainingExamResult trainingExamResult";
	private static final String _SQL_COUNT_TRAININGEXAMRESULT_WHERE = "SELECT COUNT(trainingExamResult) FROM TrainingExamResult trainingExamResult WHERE ";
	private static final String _FINDER_COLUMN_GTCREATEDATE_CREATEDATE_1 = "trainingExamResult.createDate > NULL";
	private static final String _FINDER_COLUMN_GTCREATEDATE_CREATEDATE_2 = "trainingExamResult.createDate > ?";
	private static final String _FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_1 =
		"trainingExamResult.registrationNumber IS NULL";
	private static final String _FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_2 =
		"trainingExamResult.registrationNumber = ?";
	private static final String _FINDER_COLUMN_REGISTRATIONNUMBER_REGISTRATIONNUMBER_3 =
		"(trainingExamResult.registrationNumber IS NULL OR trainingExamResult.registrationNumber = ?)";
	private static final String _FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_1 = "trainingExamResult.startDate > NULL AND ";
	private static final String _FINDER_COLUMN_GTSD_G_NOTS_STARTDATE_2 = "trainingExamResult.startDate > ? AND ";
	private static final String _FINDER_COLUMN_GTSD_G_NOTS_GRADE_2 = "trainingExamResult.grade = ? AND ";
	private static final String _FINDER_COLUMN_GTSD_G_NOTS_STATUS_2 = "trainingExamResult.status != ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingExamResult.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingExamResult exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingExamResult exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingExamResultPersistenceImpl.class);
	private static TrainingExamResult _nullTrainingExamResult = new TrainingExamResultImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingExamResult> toCacheModel() {
				return _nullTrainingExamResultCacheModel;
			}
		};

	private static CacheModel<TrainingExamResult> _nullTrainingExamResultCacheModel =
		new CacheModel<TrainingExamResult>() {
			public TrainingExamResult toEntityModel() {
				return _nullTrainingExamResult;
			}
		};
}
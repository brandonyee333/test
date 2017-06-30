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

import com.liferay.osb.NoSuchTrainingExamException;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.impl.TrainingExamImpl;
import com.liferay.osb.model.impl.TrainingExamModelImpl;

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
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the training exam service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamPersistence
 * @see TrainingExamUtil
 * @generated
 */
public class TrainingExamPersistenceImpl extends BasePersistenceImpl<TrainingExam>
	implements TrainingExamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingExamUtil} to access the training exam persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingExamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID =
		new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, TrainingExamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTrainingCertificateTemplateId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID =
		new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, TrainingExamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTrainingCertificateTemplateId",
			new String[] { Long.class.getName() },
			TrainingExamModelImpl.TRAININGCERTIFICATETEMPLATEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID =
		new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingCertificateTemplateId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, TrainingExamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, TrainingExamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			TrainingExamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, TrainingExamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, TrainingExamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the training exam in the entity cache if it is enabled.
	 *
	 * @param trainingExam the training exam
	 */
	public void cacheResult(TrainingExam trainingExam) {
		EntityCacheUtil.putResult(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamImpl.class, trainingExam.getPrimaryKey(), trainingExam);

		trainingExam.resetOriginalValues();
	}

	/**
	 * Caches the training exams in the entity cache if it is enabled.
	 *
	 * @param trainingExams the training exams
	 */
	public void cacheResult(List<TrainingExam> trainingExams) {
		for (TrainingExam trainingExam : trainingExams) {
			if (EntityCacheUtil.getResult(
						TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
						TrainingExamImpl.class, trainingExam.getPrimaryKey()) == null) {
				cacheResult(trainingExam);
			}
			else {
				trainingExam.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training exams.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingExamImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingExamImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training exam.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingExam trainingExam) {
		EntityCacheUtil.removeResult(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamImpl.class, trainingExam.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TrainingExam> trainingExams) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingExam trainingExam : trainingExams) {
			EntityCacheUtil.removeResult(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
				TrainingExamImpl.class, trainingExam.getPrimaryKey());
		}
	}

	/**
	 * Creates a new training exam with the primary key. Does not add the training exam to the database.
	 *
	 * @param trainingExamId the primary key for the new training exam
	 * @return the new training exam
	 */
	public TrainingExam create(long trainingExamId) {
		TrainingExam trainingExam = new TrainingExamImpl();

		trainingExam.setNew(true);
		trainingExam.setPrimaryKey(trainingExamId);

		return trainingExam;
	}

	/**
	 * Removes the training exam with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingExamId the primary key of the training exam
	 * @return the training exam that was removed
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam remove(long trainingExamId)
		throws NoSuchTrainingExamException, SystemException {
		return remove(Long.valueOf(trainingExamId));
	}

	/**
	 * Removes the training exam with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training exam
	 * @return the training exam that was removed
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExam remove(Serializable primaryKey)
		throws NoSuchTrainingExamException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingExam trainingExam = (TrainingExam)session.get(TrainingExamImpl.class,
					primaryKey);

			if (trainingExam == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingExamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingExam);
		}
		catch (NoSuchTrainingExamException nsee) {
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
	protected TrainingExam removeImpl(TrainingExam trainingExam)
		throws SystemException {
		trainingExam = toUnwrappedModel(trainingExam);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingExam);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingExam);

		return trainingExam;
	}

	@Override
	public TrainingExam updateImpl(
		com.liferay.osb.model.TrainingExam trainingExam, boolean merge)
		throws SystemException {
		trainingExam = toUnwrappedModel(trainingExam);

		boolean isNew = trainingExam.isNew();

		TrainingExamModelImpl trainingExamModelImpl = (TrainingExamModelImpl)trainingExam;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingExam, merge);

			trainingExam.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrainingExamModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((trainingExamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingExamModelImpl.getOriginalTrainingCertificateTemplateId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID,
					args);

				args = new Object[] {
						Long.valueOf(trainingExamModelImpl.getTrainingCertificateTemplateId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID,
					args);
			}

			if ((trainingExamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trainingExamModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { trainingExamModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}
		}

		EntityCacheUtil.putResult(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamImpl.class, trainingExam.getPrimaryKey(), trainingExam);

		return trainingExam;
	}

	protected TrainingExam toUnwrappedModel(TrainingExam trainingExam) {
		if (trainingExam instanceof TrainingExamImpl) {
			return trainingExam;
		}

		TrainingExamImpl trainingExamImpl = new TrainingExamImpl();

		trainingExamImpl.setNew(trainingExam.isNew());
		trainingExamImpl.setPrimaryKey(trainingExam.getPrimaryKey());

		trainingExamImpl.setTrainingExamId(trainingExam.getTrainingExamId());
		trainingExamImpl.setTrainingCertificateTemplateId(trainingExam.getTrainingCertificateTemplateId());
		trainingExamImpl.setName(trainingExam.getName());

		return trainingExamImpl;
	}

	/**
	 * Returns the training exam with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training exam
	 * @return the training exam
	 * @throws com.liferay.portal.NoSuchModelException if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExam findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training exam with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamException} if it could not be found.
	 *
	 * @param trainingExamId the primary key of the training exam
	 * @return the training exam
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam findByPrimaryKey(long trainingExamId)
		throws NoSuchTrainingExamException, SystemException {
		TrainingExam trainingExam = fetchByPrimaryKey(trainingExamId);

		if (trainingExam == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + trainingExamId);
			}

			throw new NoSuchTrainingExamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingExamId);
		}

		return trainingExam;
	}

	/**
	 * Returns the training exam with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training exam
	 * @return the training exam, or <code>null</code> if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExam fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training exam with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingExamId the primary key of the training exam
	 * @return the training exam, or <code>null</code> if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam fetchByPrimaryKey(long trainingExamId)
		throws SystemException {
		TrainingExam trainingExam = (TrainingExam)EntityCacheUtil.getResult(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
				TrainingExamImpl.class, trainingExamId);

		if (trainingExam == _nullTrainingExam) {
			return null;
		}

		if (trainingExam == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingExam = (TrainingExam)session.get(TrainingExamImpl.class,
						Long.valueOf(trainingExamId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingExam != null) {
					cacheResult(trainingExam);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingExamModelImpl.ENTITY_CACHE_ENABLED,
						TrainingExamImpl.class, trainingExamId,
						_nullTrainingExam);
				}

				closeSession(session);
			}
		}

		return trainingExam;
	}

	/**
	 * Returns all the training exams where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @return the matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) throws SystemException {
		return findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exams where trainingCertificateTemplateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param start the lower bound of the range of training exams
	 * @param end the upper bound of the range of training exams (not inclusive)
	 * @return the range of matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end)
		throws SystemException {
		return findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exams where trainingCertificateTemplateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param start the lower bound of the range of training exams
	 * @param end the upper bound of the range of training exams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID;
			finderArgs = new Object[] { trainingCertificateTemplateId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID;
			finderArgs = new Object[] {
					trainingCertificateTemplateId,
					
					start, end, orderByComparator
				};
		}

		List<TrainingExam> list = (List<TrainingExam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingExam trainingExam : list) {
				if ((trainingCertificateTemplateId != trainingExam.getTrainingCertificateTemplateId())) {
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

			query.append(_SQL_SELECT_TRAININGEXAM_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2);

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

				qPos.add(trainingCertificateTemplateId);

				list = (List<TrainingExam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam findByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws NoSuchTrainingExamException, SystemException {
		TrainingExam trainingExam = fetchByTrainingCertificateTemplateId_First(trainingCertificateTemplateId,
				orderByComparator);

		if (trainingExam != null) {
			return trainingExam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCertificateTemplateId=");
		msg.append(trainingCertificateTemplateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamException(msg.toString());
	}

	/**
	 * Returns the first training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam, or <code>null</code> if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam fetchByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TrainingExam> list = findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam findByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws NoSuchTrainingExamException, SystemException {
		TrainingExam trainingExam = fetchByTrainingCertificateTemplateId_Last(trainingCertificateTemplateId,
				orderByComparator);

		if (trainingExam != null) {
			return trainingExam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCertificateTemplateId=");
		msg.append(trainingCertificateTemplateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamException(msg.toString());
	}

	/**
	 * Returns the last training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam, or <code>null</code> if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam fetchByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTrainingCertificateTemplateId(trainingCertificateTemplateId);

		List<TrainingExam> list = findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training exams before and after the current training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingExamId the primary key of the current training exam
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training exam
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam[] findByTrainingCertificateTemplateId_PrevAndNext(
		long trainingExamId, long trainingCertificateTemplateId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamException, SystemException {
		TrainingExam trainingExam = findByPrimaryKey(trainingExamId);

		Session session = null;

		try {
			session = openSession();

			TrainingExam[] array = new TrainingExamImpl[3];

			array[0] = getByTrainingCertificateTemplateId_PrevAndNext(session,
					trainingExam, trainingCertificateTemplateId,
					orderByComparator, true);

			array[1] = trainingExam;

			array[2] = getByTrainingCertificateTemplateId_PrevAndNext(session,
					trainingExam, trainingCertificateTemplateId,
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

	protected TrainingExam getByTrainingCertificateTemplateId_PrevAndNext(
		Session session, TrainingExam trainingExam,
		long trainingCertificateTemplateId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEXAM_WHERE);

		query.append(_FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2);

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

		qPos.add(trainingCertificateTemplateId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingExam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingExam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training exams where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findByName(String name) throws SystemException {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exams where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of training exams
	 * @param end the upper bound of the range of training exams (not inclusive)
	 * @return the range of matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exams where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of training exams
	 * @param end the upper bound of the range of training exams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findByName(String name, int start, int end,
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

		List<TrainingExam> list = (List<TrainingExam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingExam trainingExam : list) {
				if (!Validator.equals(name, trainingExam.getName())) {
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

			query.append(_SQL_SELECT_TRAININGEXAM_WHERE);

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

				list = (List<TrainingExam>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training exam in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam findByName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamException, SystemException {
		TrainingExam trainingExam = fetchByName_First(name, orderByComparator);

		if (trainingExam != null) {
			return trainingExam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamException(msg.toString());
	}

	/**
	 * Returns the first training exam in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam, or <code>null</code> if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam fetchByName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingExam> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training exam in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam findByName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamException, SystemException {
		TrainingExam trainingExam = fetchByName_Last(name, orderByComparator);

		if (trainingExam != null) {
			return trainingExam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamException(msg.toString());
	}

	/**
	 * Returns the last training exam in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam, or <code>null</code> if a matching training exam could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam fetchByName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByName(name);

		List<TrainingExam> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training exams before and after the current training exam in the ordered set where name = &#63;.
	 *
	 * @param trainingExamId the primary key of the current training exam
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training exam
	 * @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExam[] findByName_PrevAndNext(long trainingExamId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchTrainingExamException, SystemException {
		TrainingExam trainingExam = findByPrimaryKey(trainingExamId);

		Session session = null;

		try {
			session = openSession();

			TrainingExam[] array = new TrainingExamImpl[3];

			array[0] = getByName_PrevAndNext(session, trainingExam, name,
					orderByComparator, true);

			array[1] = trainingExam;

			array[2] = getByName_PrevAndNext(session, trainingExam, name,
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

	protected TrainingExam getByName_PrevAndNext(Session session,
		TrainingExam trainingExam, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEXAM_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(trainingExam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingExam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training exams.
	 *
	 * @return the training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training exams
	 * @param end the upper bound of the range of training exams (not inclusive)
	 * @return the range of training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training exams
	 * @param end the upper bound of the range of training exams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training exams
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExam> findAll(int start, int end,
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

		List<TrainingExam> list = (List<TrainingExam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGEXAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGEXAM;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingExam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingExam>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the training exams where trainingCertificateTemplateId = &#63; from the database.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) throws SystemException {
		for (TrainingExam trainingExam : findByTrainingCertificateTemplateId(
				trainingCertificateTemplateId)) {
			remove(trainingExam);
		}
	}

	/**
	 * Removes all the training exams where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByName(String name) throws SystemException {
		for (TrainingExam trainingExam : findByName(name)) {
			remove(trainingExam);
		}
	}

	/**
	 * Removes all the training exams from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingExam trainingExam : findAll()) {
			remove(trainingExam);
		}
	}

	/**
	 * Returns the number of training exams where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @return the number of matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) throws SystemException {
		Object[] finderArgs = new Object[] { trainingCertificateTemplateId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEXAM_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCertificateTemplateId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training exams where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching training exams
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEXAM_WHERE);

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
	 * Returns the number of training exams.
	 *
	 * @return the number of training exams
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGEXAM);

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
	 * Initializes the training exam persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingExam")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingExam>> listenersList = new ArrayList<ModelListener<TrainingExam>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingExam>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingExamImpl.class.getName());
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
	private static final String _SQL_SELECT_TRAININGEXAM = "SELECT trainingExam FROM TrainingExam trainingExam";
	private static final String _SQL_SELECT_TRAININGEXAM_WHERE = "SELECT trainingExam FROM TrainingExam trainingExam WHERE ";
	private static final String _SQL_COUNT_TRAININGEXAM = "SELECT COUNT(trainingExam) FROM TrainingExam trainingExam";
	private static final String _SQL_COUNT_TRAININGEXAM_WHERE = "SELECT COUNT(trainingExam) FROM TrainingExam trainingExam WHERE ";
	private static final String _FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2 =
		"trainingExam.trainingCertificateTemplateId = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "trainingExam.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "trainingExam.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(trainingExam.name IS NULL OR trainingExam.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingExam.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingExam exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingExam exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingExamPersistenceImpl.class);
	private static TrainingExam _nullTrainingExam = new TrainingExamImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingExam> toCacheModel() {
				return _nullTrainingExamCacheModel;
			}
		};

	private static CacheModel<TrainingExam> _nullTrainingExamCacheModel = new CacheModel<TrainingExam>() {
			public TrainingExam toEntityModel() {
				return _nullTrainingExam;
			}
		};
}
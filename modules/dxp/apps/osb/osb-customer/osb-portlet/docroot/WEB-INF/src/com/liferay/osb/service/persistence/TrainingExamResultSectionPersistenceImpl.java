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

import com.liferay.osb.NoSuchTrainingExamResultSectionException;
import com.liferay.osb.model.TrainingExamResultSection;
import com.liferay.osb.model.impl.TrainingExamResultSectionImpl;
import com.liferay.osb.model.impl.TrainingExamResultSectionModelImpl;

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
 * The persistence implementation for the training exam result section service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultSectionPersistence
 * @see TrainingExamResultSectionUtil
 * @generated
 */
public class TrainingExamResultSectionPersistenceImpl
	extends BasePersistenceImpl<TrainingExamResultSection>
	implements TrainingExamResultSectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingExamResultSectionUtil} to access the training exam result section persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingExamResultSectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGEXAMRESULTID =
		new FinderPath(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultSectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTrainingExamResultId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGEXAMRESULTID =
		new FinderPath(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultSectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTrainingExamResultId",
			new String[] { Long.class.getName() },
			TrainingExamResultSectionModelImpl.TRAININGEXAMRESULTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRAININGEXAMRESULTID = new FinderPath(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingExamResultId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultSectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionModelImpl.FINDER_CACHE_ENABLED,
			TrainingExamResultSectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	/**
	 * Caches the training exam result section in the entity cache if it is enabled.
	 *
	 * @param trainingExamResultSection the training exam result section
	 */
	public void cacheResult(TrainingExamResultSection trainingExamResultSection) {
		EntityCacheUtil.putResult(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionImpl.class,
			trainingExamResultSection.getPrimaryKey(), trainingExamResultSection);

		trainingExamResultSection.resetOriginalValues();
	}

	/**
	 * Caches the training exam result sections in the entity cache if it is enabled.
	 *
	 * @param trainingExamResultSections the training exam result sections
	 */
	public void cacheResult(
		List<TrainingExamResultSection> trainingExamResultSections) {
		for (TrainingExamResultSection trainingExamResultSection : trainingExamResultSections) {
			if (EntityCacheUtil.getResult(
						TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
						TrainingExamResultSectionImpl.class,
						trainingExamResultSection.getPrimaryKey()) == null) {
				cacheResult(trainingExamResultSection);
			}
			else {
				trainingExamResultSection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training exam result sections.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingExamResultSectionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingExamResultSectionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training exam result section.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingExamResultSection trainingExamResultSection) {
		EntityCacheUtil.removeResult(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionImpl.class,
			trainingExamResultSection.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<TrainingExamResultSection> trainingExamResultSections) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingExamResultSection trainingExamResultSection : trainingExamResultSections) {
			EntityCacheUtil.removeResult(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
				TrainingExamResultSectionImpl.class,
				trainingExamResultSection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new training exam result section with the primary key. Does not add the training exam result section to the database.
	 *
	 * @param trainingExamResultSectionId the primary key for the new training exam result section
	 * @return the new training exam result section
	 */
	public TrainingExamResultSection create(long trainingExamResultSectionId) {
		TrainingExamResultSection trainingExamResultSection = new TrainingExamResultSectionImpl();

		trainingExamResultSection.setNew(true);
		trainingExamResultSection.setPrimaryKey(trainingExamResultSectionId);

		return trainingExamResultSection;
	}

	/**
	 * Removes the training exam result section with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingExamResultSectionId the primary key of the training exam result section
	 * @return the training exam result section that was removed
	 * @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection remove(long trainingExamResultSectionId)
		throws NoSuchTrainingExamResultSectionException, SystemException {
		return remove(Long.valueOf(trainingExamResultSectionId));
	}

	/**
	 * Removes the training exam result section with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training exam result section
	 * @return the training exam result section that was removed
	 * @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExamResultSection remove(Serializable primaryKey)
		throws NoSuchTrainingExamResultSectionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingExamResultSection trainingExamResultSection = (TrainingExamResultSection)session.get(TrainingExamResultSectionImpl.class,
					primaryKey);

			if (trainingExamResultSection == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingExamResultSectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingExamResultSection);
		}
		catch (NoSuchTrainingExamResultSectionException nsee) {
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
	protected TrainingExamResultSection removeImpl(
		TrainingExamResultSection trainingExamResultSection)
		throws SystemException {
		trainingExamResultSection = toUnwrappedModel(trainingExamResultSection);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingExamResultSection);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingExamResultSection);

		return trainingExamResultSection;
	}

	@Override
	public TrainingExamResultSection updateImpl(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection,
		boolean merge) throws SystemException {
		trainingExamResultSection = toUnwrappedModel(trainingExamResultSection);

		boolean isNew = trainingExamResultSection.isNew();

		TrainingExamResultSectionModelImpl trainingExamResultSectionModelImpl = (TrainingExamResultSectionModelImpl)trainingExamResultSection;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingExamResultSection, merge);

			trainingExamResultSection.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!TrainingExamResultSectionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((trainingExamResultSectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGEXAMRESULTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingExamResultSectionModelImpl.getOriginalTrainingExamResultId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGEXAMRESULTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGEXAMRESULTID,
					args);

				args = new Object[] {
						Long.valueOf(trainingExamResultSectionModelImpl.getTrainingExamResultId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGEXAMRESULTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGEXAMRESULTID,
					args);
			}
		}

		EntityCacheUtil.putResult(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
			TrainingExamResultSectionImpl.class,
			trainingExamResultSection.getPrimaryKey(), trainingExamResultSection);

		return trainingExamResultSection;
	}

	protected TrainingExamResultSection toUnwrappedModel(
		TrainingExamResultSection trainingExamResultSection) {
		if (trainingExamResultSection instanceof TrainingExamResultSectionImpl) {
			return trainingExamResultSection;
		}

		TrainingExamResultSectionImpl trainingExamResultSectionImpl = new TrainingExamResultSectionImpl();

		trainingExamResultSectionImpl.setNew(trainingExamResultSection.isNew());
		trainingExamResultSectionImpl.setPrimaryKey(trainingExamResultSection.getPrimaryKey());

		trainingExamResultSectionImpl.setTrainingExamResultSectionId(trainingExamResultSection.getTrainingExamResultSectionId());
		trainingExamResultSectionImpl.setTrainingExamResultId(trainingExamResultSection.getTrainingExamResultId());
		trainingExamResultSectionImpl.setTitle(trainingExamResultSection.getTitle());
		trainingExamResultSectionImpl.setSectionKey(trainingExamResultSection.getSectionKey());
		trainingExamResultSectionImpl.setScoreIndicator(trainingExamResultSection.isScoreIndicator());
		trainingExamResultSectionImpl.setScoringAlgorithm(trainingExamResultSection.getScoringAlgorithm());
		trainingExamResultSectionImpl.setMasteryScore(trainingExamResultSection.getMasteryScore());
		trainingExamResultSectionImpl.setScore(trainingExamResultSection.getScore());
		trainingExamResultSectionImpl.setStandardErrorOfEstimate(trainingExamResultSection.getStandardErrorOfEstimate());
		trainingExamResultSectionImpl.setCorrectCount(trainingExamResultSection.getCorrectCount());
		trainingExamResultSectionImpl.setIncorrectCount(trainingExamResultSection.getIncorrectCount());
		trainingExamResultSectionImpl.setSkippedCount(trainingExamResultSection.getSkippedCount());
		trainingExamResultSectionImpl.setGrade(trainingExamResultSection.getGrade());

		return trainingExamResultSectionImpl;
	}

	/**
	 * Returns the training exam result section with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training exam result section
	 * @return the training exam result section
	 * @throws com.liferay.portal.NoSuchModelException if a training exam result section with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExamResultSection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training exam result section with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamResultSectionException} if it could not be found.
	 *
	 * @param trainingExamResultSectionId the primary key of the training exam result section
	 * @return the training exam result section
	 * @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection findByPrimaryKey(
		long trainingExamResultSectionId)
		throws NoSuchTrainingExamResultSectionException, SystemException {
		TrainingExamResultSection trainingExamResultSection = fetchByPrimaryKey(trainingExamResultSectionId);

		if (trainingExamResultSection == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					trainingExamResultSectionId);
			}

			throw new NoSuchTrainingExamResultSectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingExamResultSectionId);
		}

		return trainingExamResultSection;
	}

	/**
	 * Returns the training exam result section with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training exam result section
	 * @return the training exam result section, or <code>null</code> if a training exam result section with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingExamResultSection fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training exam result section with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingExamResultSectionId the primary key of the training exam result section
	 * @return the training exam result section, or <code>null</code> if a training exam result section with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection fetchByPrimaryKey(
		long trainingExamResultSectionId) throws SystemException {
		TrainingExamResultSection trainingExamResultSection = (TrainingExamResultSection)EntityCacheUtil.getResult(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
				TrainingExamResultSectionImpl.class, trainingExamResultSectionId);

		if (trainingExamResultSection == _nullTrainingExamResultSection) {
			return null;
		}

		if (trainingExamResultSection == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingExamResultSection = (TrainingExamResultSection)session.get(TrainingExamResultSectionImpl.class,
						Long.valueOf(trainingExamResultSectionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingExamResultSection != null) {
					cacheResult(trainingExamResultSection);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingExamResultSectionModelImpl.ENTITY_CACHE_ENABLED,
						TrainingExamResultSectionImpl.class,
						trainingExamResultSectionId,
						_nullTrainingExamResultSection);
				}

				closeSession(session);
			}
		}

		return trainingExamResultSection;
	}

	/**
	 * Returns all the training exam result sections where trainingExamResultId = &#63;.
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @return the matching training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId) throws SystemException {
		return findByTrainingExamResultId(trainingExamResultId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exam result sections where trainingExamResultId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @param start the lower bound of the range of training exam result sections
	 * @param end the upper bound of the range of training exam result sections (not inclusive)
	 * @return the range of matching training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end)
		throws SystemException {
		return findByTrainingExamResultId(trainingExamResultId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exam result sections where trainingExamResultId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @param start the lower bound of the range of training exam result sections
	 * @param end the upper bound of the range of training exam result sections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGEXAMRESULTID;
			finderArgs = new Object[] { trainingExamResultId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGEXAMRESULTID;
			finderArgs = new Object[] {
					trainingExamResultId,
					
					start, end, orderByComparator
				};
		}

		List<TrainingExamResultSection> list = (List<TrainingExamResultSection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingExamResultSection trainingExamResultSection : list) {
				if ((trainingExamResultId != trainingExamResultSection.getTrainingExamResultId())) {
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

			query.append(_SQL_SELECT_TRAININGEXAMRESULTSECTION_WHERE);

			query.append(_FINDER_COLUMN_TRAININGEXAMRESULTID_TRAININGEXAMRESULTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingExamResultSectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingExamResultId);

				list = (List<TrainingExamResultSection>)QueryUtil.list(q,
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
	 * Returns the first training exam result section in the ordered set where trainingExamResultId = &#63;.
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam result section
	 * @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a matching training exam result section could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection findByTrainingExamResultId_First(
		long trainingExamResultId, OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultSectionException, SystemException {
		TrainingExamResultSection trainingExamResultSection = fetchByTrainingExamResultId_First(trainingExamResultId,
				orderByComparator);

		if (trainingExamResultSection != null) {
			return trainingExamResultSection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingExamResultId=");
		msg.append(trainingExamResultId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamResultSectionException(msg.toString());
	}

	/**
	 * Returns the first training exam result section in the ordered set where trainingExamResultId = &#63;.
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training exam result section, or <code>null</code> if a matching training exam result section could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection fetchByTrainingExamResultId_First(
		long trainingExamResultId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TrainingExamResultSection> list = findByTrainingExamResultId(trainingExamResultId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training exam result section in the ordered set where trainingExamResultId = &#63;.
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam result section
	 * @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a matching training exam result section could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection findByTrainingExamResultId_Last(
		long trainingExamResultId, OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultSectionException, SystemException {
		TrainingExamResultSection trainingExamResultSection = fetchByTrainingExamResultId_Last(trainingExamResultId,
				orderByComparator);

		if (trainingExamResultSection != null) {
			return trainingExamResultSection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingExamResultId=");
		msg.append(trainingExamResultId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingExamResultSectionException(msg.toString());
	}

	/**
	 * Returns the last training exam result section in the ordered set where trainingExamResultId = &#63;.
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training exam result section, or <code>null</code> if a matching training exam result section could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection fetchByTrainingExamResultId_Last(
		long trainingExamResultId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTrainingExamResultId(trainingExamResultId);

		List<TrainingExamResultSection> list = findByTrainingExamResultId(trainingExamResultId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training exam result sections before and after the current training exam result section in the ordered set where trainingExamResultId = &#63;.
	 *
	 * @param trainingExamResultSectionId the primary key of the current training exam result section
	 * @param trainingExamResultId the training exam result ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training exam result section
	 * @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingExamResultSection[] findByTrainingExamResultId_PrevAndNext(
		long trainingExamResultSectionId, long trainingExamResultId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingExamResultSectionException, SystemException {
		TrainingExamResultSection trainingExamResultSection = findByPrimaryKey(trainingExamResultSectionId);

		Session session = null;

		try {
			session = openSession();

			TrainingExamResultSection[] array = new TrainingExamResultSectionImpl[3];

			array[0] = getByTrainingExamResultId_PrevAndNext(session,
					trainingExamResultSection, trainingExamResultId,
					orderByComparator, true);

			array[1] = trainingExamResultSection;

			array[2] = getByTrainingExamResultId_PrevAndNext(session,
					trainingExamResultSection, trainingExamResultId,
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

	protected TrainingExamResultSection getByTrainingExamResultId_PrevAndNext(
		Session session, TrainingExamResultSection trainingExamResultSection,
		long trainingExamResultId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEXAMRESULTSECTION_WHERE);

		query.append(_FINDER_COLUMN_TRAININGEXAMRESULTID_TRAININGEXAMRESULTID_2);

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
			query.append(TrainingExamResultSectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(trainingExamResultId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingExamResultSection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingExamResultSection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training exam result sections.
	 *
	 * @return the training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResultSection> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training exam result sections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training exam result sections
	 * @param end the upper bound of the range of training exam result sections (not inclusive)
	 * @return the range of training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResultSection> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training exam result sections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training exam result sections
	 * @param end the upper bound of the range of training exam result sections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingExamResultSection> findAll(int start, int end,
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

		List<TrainingExamResultSection> list = (List<TrainingExamResultSection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGEXAMRESULTSECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGEXAMRESULTSECTION.concat(TrainingExamResultSectionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingExamResultSection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingExamResultSection>)QueryUtil.list(q,
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
	 * Removes all the training exam result sections where trainingExamResultId = &#63; from the database.
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTrainingExamResultId(long trainingExamResultId)
		throws SystemException {
		for (TrainingExamResultSection trainingExamResultSection : findByTrainingExamResultId(
				trainingExamResultId)) {
			remove(trainingExamResultSection);
		}
	}

	/**
	 * Removes all the training exam result sections from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingExamResultSection trainingExamResultSection : findAll()) {
			remove(trainingExamResultSection);
		}
	}

	/**
	 * Returns the number of training exam result sections where trainingExamResultId = &#63;.
	 *
	 * @param trainingExamResultId the training exam result ID
	 * @return the number of matching training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTrainingExamResultId(long trainingExamResultId)
		throws SystemException {
		Object[] finderArgs = new Object[] { trainingExamResultId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRAININGEXAMRESULTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEXAMRESULTSECTION_WHERE);

			query.append(_FINDER_COLUMN_TRAININGEXAMRESULTID_TRAININGEXAMRESULTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingExamResultId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGEXAMRESULTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training exam result sections.
	 *
	 * @return the number of training exam result sections
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGEXAMRESULTSECTION);

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
	 * Initializes the training exam result section persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingExamResultSection")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingExamResultSection>> listenersList = new ArrayList<ModelListener<TrainingExamResultSection>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingExamResultSection>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingExamResultSectionImpl.class.getName());
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
	private static final String _SQL_SELECT_TRAININGEXAMRESULTSECTION = "SELECT trainingExamResultSection FROM TrainingExamResultSection trainingExamResultSection";
	private static final String _SQL_SELECT_TRAININGEXAMRESULTSECTION_WHERE = "SELECT trainingExamResultSection FROM TrainingExamResultSection trainingExamResultSection WHERE ";
	private static final String _SQL_COUNT_TRAININGEXAMRESULTSECTION = "SELECT COUNT(trainingExamResultSection) FROM TrainingExamResultSection trainingExamResultSection";
	private static final String _SQL_COUNT_TRAININGEXAMRESULTSECTION_WHERE = "SELECT COUNT(trainingExamResultSection) FROM TrainingExamResultSection trainingExamResultSection WHERE ";
	private static final String _FINDER_COLUMN_TRAININGEXAMRESULTID_TRAININGEXAMRESULTID_2 =
		"trainingExamResultSection.trainingExamResultId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingExamResultSection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingExamResultSection exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingExamResultSection exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingExamResultSectionPersistenceImpl.class);
	private static TrainingExamResultSection _nullTrainingExamResultSection = new TrainingExamResultSectionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingExamResultSection> toCacheModel() {
				return _nullTrainingExamResultSectionCacheModel;
			}
		};

	private static CacheModel<TrainingExamResultSection> _nullTrainingExamResultSectionCacheModel =
		new CacheModel<TrainingExamResultSection>() {
			public TrainingExamResultSection toEntityModel() {
				return _nullTrainingExamResultSection;
			}
		};
}
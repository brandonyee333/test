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

import com.liferay.osb.NoSuchTrainingCertificateException;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.impl.TrainingCertificateImpl;
import com.liferay.osb.model.impl.TrainingCertificateModelImpl;

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
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the training certificate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCertificatePersistence
 * @see TrainingCertificateUtil
 * @generated
 */
public class TrainingCertificatePersistenceImpl extends BasePersistenceImpl<TrainingCertificate>
	implements TrainingCertificatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingCertificateUtil} to access the training certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingCertificateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID = new FinderPath(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateModelImpl.FINDER_CACHE_ENABLED,
			TrainingCertificateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTrainingCustomerId", new String[] { Long.class.getName() },
			TrainingCertificateModelImpl.TRAININGCUSTOMERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRAININGCUSTOMERID = new FinderPath(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingCustomerId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateModelImpl.FINDER_CACHE_ENABLED,
			TrainingCertificateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKey", new String[] { String.class.getName() },
			TrainingCertificateModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateModelImpl.FINDER_CACHE_ENABLED,
			TrainingCertificateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateModelImpl.FINDER_CACHE_ENABLED,
			TrainingCertificateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the training certificate in the entity cache if it is enabled.
	 *
	 * @param trainingCertificate the training certificate
	 */
	public void cacheResult(TrainingCertificate trainingCertificate) {
		EntityCacheUtil.putResult(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateImpl.class, trainingCertificate.getPrimaryKey(),
			trainingCertificate);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
			new Object[] {
				Long.valueOf(trainingCertificate.getTrainingCustomerId())
			}, trainingCertificate);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { trainingCertificate.getKey() }, trainingCertificate);

		trainingCertificate.resetOriginalValues();
	}

	/**
	 * Caches the training certificates in the entity cache if it is enabled.
	 *
	 * @param trainingCertificates the training certificates
	 */
	public void cacheResult(List<TrainingCertificate> trainingCertificates) {
		for (TrainingCertificate trainingCertificate : trainingCertificates) {
			if (EntityCacheUtil.getResult(
						TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
						TrainingCertificateImpl.class,
						trainingCertificate.getPrimaryKey()) == null) {
				cacheResult(trainingCertificate);
			}
			else {
				trainingCertificate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training certificates.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingCertificateImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingCertificateImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training certificate.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingCertificate trainingCertificate) {
		EntityCacheUtil.removeResult(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateImpl.class, trainingCertificate.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(trainingCertificate);
	}

	@Override
	public void clearCache(List<TrainingCertificate> trainingCertificates) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingCertificate trainingCertificate : trainingCertificates) {
			EntityCacheUtil.removeResult(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
				TrainingCertificateImpl.class,
				trainingCertificate.getPrimaryKey());

			clearUniqueFindersCache(trainingCertificate);
		}
	}

	protected void cacheUniqueFindersCache(
		TrainingCertificate trainingCertificate) {
		if (trainingCertificate.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(trainingCertificate.getTrainingCustomerId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGCUSTOMERID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
				args, trainingCertificate);

			args = new Object[] { trainingCertificate.getKey() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY, args,
				trainingCertificate);
		}
		else {
			TrainingCertificateModelImpl trainingCertificateModelImpl = (TrainingCertificateModelImpl)trainingCertificate;

			if ((trainingCertificateModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingCertificate.getTrainingCustomerId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGCUSTOMERID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
					args, trainingCertificate);
			}

			if ((trainingCertificateModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { trainingCertificate.getKey() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY, args,
					trainingCertificate);
			}
		}
	}

	protected void clearUniqueFindersCache(
		TrainingCertificate trainingCertificate) {
		TrainingCertificateModelImpl trainingCertificateModelImpl = (TrainingCertificateModelImpl)trainingCertificate;

		Object[] args = new Object[] {
				Long.valueOf(trainingCertificate.getTrainingCustomerId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCUSTOMERID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
			args);

		if ((trainingCertificateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(trainingCertificateModelImpl.getOriginalTrainingCustomerId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCUSTOMERID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
				args);
		}

		args = new Object[] { trainingCertificate.getKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY, args);

		if ((trainingCertificateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
			args = new Object[] { trainingCertificateModelImpl.getOriginalKey() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY, args);
		}
	}

	/**
	 * Creates a new training certificate with the primary key. Does not add the training certificate to the database.
	 *
	 * @param trainingCertificateId the primary key for the new training certificate
	 * @return the new training certificate
	 */
	public TrainingCertificate create(long trainingCertificateId) {
		TrainingCertificate trainingCertificate = new TrainingCertificateImpl();

		trainingCertificate.setNew(true);
		trainingCertificate.setPrimaryKey(trainingCertificateId);

		return trainingCertificate;
	}

	/**
	 * Removes the training certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingCertificateId the primary key of the training certificate
	 * @return the training certificate that was removed
	 * @throws com.liferay.osb.NoSuchTrainingCertificateException if a training certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate remove(long trainingCertificateId)
		throws NoSuchTrainingCertificateException, SystemException {
		return remove(Long.valueOf(trainingCertificateId));
	}

	/**
	 * Removes the training certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training certificate
	 * @return the training certificate that was removed
	 * @throws com.liferay.osb.NoSuchTrainingCertificateException if a training certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingCertificate remove(Serializable primaryKey)
		throws NoSuchTrainingCertificateException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingCertificate trainingCertificate = (TrainingCertificate)session.get(TrainingCertificateImpl.class,
					primaryKey);

			if (trainingCertificate == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingCertificate);
		}
		catch (NoSuchTrainingCertificateException nsee) {
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
	protected TrainingCertificate removeImpl(
		TrainingCertificate trainingCertificate) throws SystemException {
		trainingCertificate = toUnwrappedModel(trainingCertificate);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingCertificate);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingCertificate);

		return trainingCertificate;
	}

	@Override
	public TrainingCertificate updateImpl(
		com.liferay.osb.model.TrainingCertificate trainingCertificate,
		boolean merge) throws SystemException {
		trainingCertificate = toUnwrappedModel(trainingCertificate);

		boolean isNew = trainingCertificate.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingCertificate, merge);

			trainingCertificate.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrainingCertificateModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCertificateImpl.class, trainingCertificate.getPrimaryKey(),
			trainingCertificate);

		clearUniqueFindersCache(trainingCertificate);
		cacheUniqueFindersCache(trainingCertificate);

		return trainingCertificate;
	}

	protected TrainingCertificate toUnwrappedModel(
		TrainingCertificate trainingCertificate) {
		if (trainingCertificate instanceof TrainingCertificateImpl) {
			return trainingCertificate;
		}

		TrainingCertificateImpl trainingCertificateImpl = new TrainingCertificateImpl();

		trainingCertificateImpl.setNew(trainingCertificate.isNew());
		trainingCertificateImpl.setPrimaryKey(trainingCertificate.getPrimaryKey());

		trainingCertificateImpl.setTrainingCertificateId(trainingCertificate.getTrainingCertificateId());
		trainingCertificateImpl.setUserId(trainingCertificate.getUserId());
		trainingCertificateImpl.setUserName(trainingCertificate.getUserName());
		trainingCertificateImpl.setCreateDate(trainingCertificate.getCreateDate());
		trainingCertificateImpl.setModifiedDate(trainingCertificate.getModifiedDate());
		trainingCertificateImpl.setTrainingCustomerId(trainingCertificate.getTrainingCustomerId());
		trainingCertificateImpl.setUserProfileHistoryId(trainingCertificate.getUserProfileHistoryId());
		trainingCertificateImpl.setKey(trainingCertificate.getKey());
		trainingCertificateImpl.setCertifiedDate(trainingCertificate.getCertifiedDate());
		trainingCertificateImpl.setComments(trainingCertificate.getComments());
		trainingCertificateImpl.setSurveyStatus(trainingCertificate.getSurveyStatus());

		return trainingCertificateImpl;
	}

	/**
	 * Returns the training certificate with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training certificate
	 * @return the training certificate
	 * @throws com.liferay.portal.NoSuchModelException if a training certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingCertificate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training certificate with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	 *
	 * @param trainingCertificateId the primary key of the training certificate
	 * @return the training certificate
	 * @throws com.liferay.osb.NoSuchTrainingCertificateException if a training certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate findByPrimaryKey(long trainingCertificateId)
		throws NoSuchTrainingCertificateException, SystemException {
		TrainingCertificate trainingCertificate = fetchByPrimaryKey(trainingCertificateId);

		if (trainingCertificate == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					trainingCertificateId);
			}

			throw new NoSuchTrainingCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingCertificateId);
		}

		return trainingCertificate;
	}

	/**
	 * Returns the training certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training certificate
	 * @return the training certificate, or <code>null</code> if a training certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingCertificate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingCertificateId the primary key of the training certificate
	 * @return the training certificate, or <code>null</code> if a training certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate fetchByPrimaryKey(long trainingCertificateId)
		throws SystemException {
		TrainingCertificate trainingCertificate = (TrainingCertificate)EntityCacheUtil.getResult(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
				TrainingCertificateImpl.class, trainingCertificateId);

		if (trainingCertificate == _nullTrainingCertificate) {
			return null;
		}

		if (trainingCertificate == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingCertificate = (TrainingCertificate)session.get(TrainingCertificateImpl.class,
						Long.valueOf(trainingCertificateId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingCertificate != null) {
					cacheResult(trainingCertificate);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingCertificateModelImpl.ENTITY_CACHE_ENABLED,
						TrainingCertificateImpl.class, trainingCertificateId,
						_nullTrainingCertificate);
				}

				closeSession(session);
			}
		}

		return trainingCertificate;
	}

	/**
	 * Returns the training certificate where trainingCustomerId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	 *
	 * @param trainingCustomerId the training customer ID
	 * @return the matching training certificate
	 * @throws com.liferay.osb.NoSuchTrainingCertificateException if a matching training certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate findByTrainingCustomerId(long trainingCustomerId)
		throws NoSuchTrainingCertificateException, SystemException {
		TrainingCertificate trainingCertificate = fetchByTrainingCustomerId(trainingCustomerId);

		if (trainingCertificate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("trainingCustomerId=");
			msg.append(trainingCustomerId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTrainingCertificateException(msg.toString());
		}

		return trainingCertificate;
	}

	/**
	 * Returns the training certificate where trainingCustomerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param trainingCustomerId the training customer ID
	 * @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate fetchByTrainingCustomerId(
		long trainingCustomerId) throws SystemException {
		return fetchByTrainingCustomerId(trainingCustomerId, true);
	}

	/**
	 * Returns the training certificate where trainingCustomerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param trainingCustomerId the training customer ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate fetchByTrainingCustomerId(
		long trainingCustomerId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { trainingCustomerId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
					finderArgs, this);
		}

		if (result instanceof TrainingCertificate) {
			TrainingCertificate trainingCertificate = (TrainingCertificate)result;

			if ((trainingCustomerId != trainingCertificate.getTrainingCustomerId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_TRAININGCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCUSTOMERID_TRAININGCUSTOMERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCustomerId);

				List<TrainingCertificate> list = q.list();

				result = list;

				TrainingCertificate trainingCertificate = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
						finderArgs, list);
				}
				else {
					trainingCertificate = list.get(0);

					cacheResult(trainingCertificate);

					if ((trainingCertificate.getTrainingCustomerId() != trainingCustomerId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
							finderArgs, trainingCertificate);
					}
				}

				return trainingCertificate;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRAININGCUSTOMERID,
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
				return (TrainingCertificate)result;
			}
		}
	}

	/**
	 * Returns the training certificate where key = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	 *
	 * @param key the key
	 * @return the matching training certificate
	 * @throws com.liferay.osb.NoSuchTrainingCertificateException if a matching training certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate findByKey(String key)
		throws NoSuchTrainingCertificateException, SystemException {
		TrainingCertificate trainingCertificate = fetchByKey(key);

		if (trainingCertificate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTrainingCertificateException(msg.toString());
		}

		return trainingCertificate;
	}

	/**
	 * Returns the training certificate where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate fetchByKey(String key) throws SystemException {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the training certificate where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate fetchByKey(String key, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result instanceof TrainingCertificate) {
			TrainingCertificate trainingCertificate = (TrainingCertificate)result;

			if (!Validator.equals(key, trainingCertificate.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_TRAININGCERTIFICATE_WHERE);

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_KEY_KEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				List<TrainingCertificate> list = q.list();

				result = list;

				TrainingCertificate trainingCertificate = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs, list);
				}
				else {
					trainingCertificate = list.get(0);

					cacheResult(trainingCertificate);

					if ((trainingCertificate.getKey() == null) ||
							!trainingCertificate.getKey().equals(key)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, trainingCertificate);
					}
				}

				return trainingCertificate;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
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
				return (TrainingCertificate)result;
			}
		}
	}

	/**
	 * Returns all the training certificates.
	 *
	 * @return the training certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCertificate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training certificates
	 * @param end the upper bound of the range of training certificates (not inclusive)
	 * @return the range of training certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCertificate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training certificates
	 * @param end the upper bound of the range of training certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCertificate> findAll(int start, int end,
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

		List<TrainingCertificate> list = (List<TrainingCertificate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGCERTIFICATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGCERTIFICATE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingCertificate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingCertificate>)QueryUtil.list(q,
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
	 * Removes the training certificate where trainingCustomerId = &#63; from the database.
	 *
	 * @param trainingCustomerId the training customer ID
	 * @return the training certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate removeByTrainingCustomerId(
		long trainingCustomerId)
		throws NoSuchTrainingCertificateException, SystemException {
		TrainingCertificate trainingCertificate = findByTrainingCustomerId(trainingCustomerId);

		return remove(trainingCertificate);
	}

	/**
	 * Removes the training certificate where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the training certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCertificate removeByKey(String key)
		throws NoSuchTrainingCertificateException, SystemException {
		TrainingCertificate trainingCertificate = findByKey(key);

		return remove(trainingCertificate);
	}

	/**
	 * Removes all the training certificates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingCertificate trainingCertificate : findAll()) {
			remove(trainingCertificate);
		}
	}

	/**
	 * Returns the number of training certificates where trainingCustomerId = &#63;.
	 *
	 * @param trainingCustomerId the training customer ID
	 * @return the number of matching training certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTrainingCustomerId(long trainingCustomerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { trainingCustomerId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRAININGCUSTOMERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCUSTOMERID_TRAININGCUSTOMERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCustomerId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGCUSTOMERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training certificates where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching training certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKey(String key) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGCERTIFICATE_WHERE);

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_KEY_KEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training certificates.
	 *
	 * @return the number of training certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGCERTIFICATE);

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
	 * Initializes the training certificate persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingCertificate")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingCertificate>> listenersList = new ArrayList<ModelListener<TrainingCertificate>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingCertificate>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingCertificateImpl.class.getName());
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
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TRAININGCERTIFICATE = "SELECT trainingCertificate FROM TrainingCertificate trainingCertificate";
	private static final String _SQL_SELECT_TRAININGCERTIFICATE_WHERE = "SELECT trainingCertificate FROM TrainingCertificate trainingCertificate WHERE ";
	private static final String _SQL_COUNT_TRAININGCERTIFICATE = "SELECT COUNT(trainingCertificate) FROM TrainingCertificate trainingCertificate";
	private static final String _SQL_COUNT_TRAININGCERTIFICATE_WHERE = "SELECT COUNT(trainingCertificate) FROM TrainingCertificate trainingCertificate WHERE ";
	private static final String _FINDER_COLUMN_TRAININGCUSTOMERID_TRAININGCUSTOMERID_2 =
		"trainingCertificate.trainingCustomerId = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_1 = "trainingCertificate.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "trainingCertificate.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(trainingCertificate.key IS NULL OR trainingCertificate.key = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingCertificate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingCertificate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingCertificate exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingCertificatePersistenceImpl.class);
	private static TrainingCertificate _nullTrainingCertificate = new TrainingCertificateImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingCertificate> toCacheModel() {
				return _nullTrainingCertificateCacheModel;
			}
		};

	private static CacheModel<TrainingCertificate> _nullTrainingCertificateCacheModel =
		new CacheModel<TrainingCertificate>() {
			public TrainingCertificate toEntityModel() {
				return _nullTrainingCertificate;
			}
		};
}
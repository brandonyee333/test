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

import com.liferay.osb.NoSuchTrainingLinkedUserException;
import com.liferay.osb.model.TrainingLinkedUser;
import com.liferay.osb.model.impl.TrainingLinkedUserImpl;
import com.liferay.osb.model.impl.TrainingLinkedUserModelImpl;

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
 * The persistence implementation for the training linked user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingLinkedUserPersistence
 * @see TrainingLinkedUserUtil
 * @generated
 */
public class TrainingLinkedUserPersistenceImpl extends BasePersistenceImpl<TrainingLinkedUser>
	implements TrainingLinkedUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingLinkedUserUtil} to access the training linked user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingLinkedUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_USERID = new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED,
			TrainingLinkedUserImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUserId", new String[] { Long.class.getName() },
			TrainingLinkedUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRIMARYUSERID =
		new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED,
			TrainingLinkedUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPrimaryUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUSERID =
		new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED,
			TrainingLinkedUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPrimaryUserId",
			new String[] { Long.class.getName() },
			TrainingLinkedUserModelImpl.PRIMARYUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRIMARYUSERID = new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPrimaryUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED,
			TrainingLinkedUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED,
			TrainingLinkedUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the training linked user in the entity cache if it is enabled.
	 *
	 * @param trainingLinkedUser the training linked user
	 */
	public void cacheResult(TrainingLinkedUser trainingLinkedUser) {
		EntityCacheUtil.putResult(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserImpl.class, trainingLinkedUser.getPrimaryKey(),
			trainingLinkedUser);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
			new Object[] { Long.valueOf(trainingLinkedUser.getUserId()) },
			trainingLinkedUser);

		trainingLinkedUser.resetOriginalValues();
	}

	/**
	 * Caches the training linked users in the entity cache if it is enabled.
	 *
	 * @param trainingLinkedUsers the training linked users
	 */
	public void cacheResult(List<TrainingLinkedUser> trainingLinkedUsers) {
		for (TrainingLinkedUser trainingLinkedUser : trainingLinkedUsers) {
			if (EntityCacheUtil.getResult(
						TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
						TrainingLinkedUserImpl.class,
						trainingLinkedUser.getPrimaryKey()) == null) {
				cacheResult(trainingLinkedUser);
			}
			else {
				trainingLinkedUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training linked users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingLinkedUserImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingLinkedUserImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training linked user.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingLinkedUser trainingLinkedUser) {
		EntityCacheUtil.removeResult(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserImpl.class, trainingLinkedUser.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(trainingLinkedUser);
	}

	@Override
	public void clearCache(List<TrainingLinkedUser> trainingLinkedUsers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingLinkedUser trainingLinkedUser : trainingLinkedUsers) {
			EntityCacheUtil.removeResult(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
				TrainingLinkedUserImpl.class, trainingLinkedUser.getPrimaryKey());

			clearUniqueFindersCache(trainingLinkedUser);
		}
	}

	protected void cacheUniqueFindersCache(
		TrainingLinkedUser trainingLinkedUser) {
		if (trainingLinkedUser.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(trainingLinkedUser.getUserId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID, args,
				trainingLinkedUser);
		}
		else {
			TrainingLinkedUserModelImpl trainingLinkedUserModelImpl = (TrainingLinkedUserModelImpl)trainingLinkedUser;

			if ((trainingLinkedUserModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingLinkedUser.getUserId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID, args,
					trainingLinkedUser);
			}
		}
	}

	protected void clearUniqueFindersCache(
		TrainingLinkedUser trainingLinkedUser) {
		TrainingLinkedUserModelImpl trainingLinkedUserModelImpl = (TrainingLinkedUserModelImpl)trainingLinkedUser;

		Object[] args = new Object[] {
				Long.valueOf(trainingLinkedUser.getUserId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID, args);

		if ((trainingLinkedUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(trainingLinkedUserModelImpl.getOriginalUserId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID, args);
		}
	}

	/**
	 * Creates a new training linked user with the primary key. Does not add the training linked user to the database.
	 *
	 * @param trainingLinkedUserId the primary key for the new training linked user
	 * @return the new training linked user
	 */
	public TrainingLinkedUser create(long trainingLinkedUserId) {
		TrainingLinkedUser trainingLinkedUser = new TrainingLinkedUserImpl();

		trainingLinkedUser.setNew(true);
		trainingLinkedUser.setPrimaryKey(trainingLinkedUserId);

		return trainingLinkedUser;
	}

	/**
	 * Removes the training linked user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingLinkedUserId the primary key of the training linked user
	 * @return the training linked user that was removed
	 * @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser remove(long trainingLinkedUserId)
		throws NoSuchTrainingLinkedUserException, SystemException {
		return remove(Long.valueOf(trainingLinkedUserId));
	}

	/**
	 * Removes the training linked user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training linked user
	 * @return the training linked user that was removed
	 * @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingLinkedUser remove(Serializable primaryKey)
		throws NoSuchTrainingLinkedUserException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingLinkedUser trainingLinkedUser = (TrainingLinkedUser)session.get(TrainingLinkedUserImpl.class,
					primaryKey);

			if (trainingLinkedUser == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingLinkedUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingLinkedUser);
		}
		catch (NoSuchTrainingLinkedUserException nsee) {
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
	protected TrainingLinkedUser removeImpl(
		TrainingLinkedUser trainingLinkedUser) throws SystemException {
		trainingLinkedUser = toUnwrappedModel(trainingLinkedUser);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingLinkedUser);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingLinkedUser);

		return trainingLinkedUser;
	}

	@Override
	public TrainingLinkedUser updateImpl(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser,
		boolean merge) throws SystemException {
		trainingLinkedUser = toUnwrappedModel(trainingLinkedUser);

		boolean isNew = trainingLinkedUser.isNew();

		TrainingLinkedUserModelImpl trainingLinkedUserModelImpl = (TrainingLinkedUserModelImpl)trainingLinkedUser;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingLinkedUser, merge);

			trainingLinkedUser.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrainingLinkedUserModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((trainingLinkedUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingLinkedUserModelImpl.getOriginalPrimaryUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRIMARYUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUSERID,
					args);

				args = new Object[] {
						Long.valueOf(trainingLinkedUserModelImpl.getPrimaryUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRIMARYUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUSERID,
					args);
			}
		}

		EntityCacheUtil.putResult(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
			TrainingLinkedUserImpl.class, trainingLinkedUser.getPrimaryKey(),
			trainingLinkedUser);

		clearUniqueFindersCache(trainingLinkedUser);
		cacheUniqueFindersCache(trainingLinkedUser);

		return trainingLinkedUser;
	}

	protected TrainingLinkedUser toUnwrappedModel(
		TrainingLinkedUser trainingLinkedUser) {
		if (trainingLinkedUser instanceof TrainingLinkedUserImpl) {
			return trainingLinkedUser;
		}

		TrainingLinkedUserImpl trainingLinkedUserImpl = new TrainingLinkedUserImpl();

		trainingLinkedUserImpl.setNew(trainingLinkedUser.isNew());
		trainingLinkedUserImpl.setPrimaryKey(trainingLinkedUser.getPrimaryKey());

		trainingLinkedUserImpl.setTrainingLinkedUserId(trainingLinkedUser.getTrainingLinkedUserId());
		trainingLinkedUserImpl.setUserId(trainingLinkedUser.getUserId());
		trainingLinkedUserImpl.setPrimaryUserId(trainingLinkedUser.getPrimaryUserId());

		return trainingLinkedUserImpl;
	}

	/**
	 * Returns the training linked user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training linked user
	 * @return the training linked user
	 * @throws com.liferay.portal.NoSuchModelException if a training linked user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingLinkedUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training linked user with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingLinkedUserException} if it could not be found.
	 *
	 * @param trainingLinkedUserId the primary key of the training linked user
	 * @return the training linked user
	 * @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser findByPrimaryKey(long trainingLinkedUserId)
		throws NoSuchTrainingLinkedUserException, SystemException {
		TrainingLinkedUser trainingLinkedUser = fetchByPrimaryKey(trainingLinkedUserId);

		if (trainingLinkedUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					trainingLinkedUserId);
			}

			throw new NoSuchTrainingLinkedUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingLinkedUserId);
		}

		return trainingLinkedUser;
	}

	/**
	 * Returns the training linked user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training linked user
	 * @return the training linked user, or <code>null</code> if a training linked user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingLinkedUser fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training linked user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingLinkedUserId the primary key of the training linked user
	 * @return the training linked user, or <code>null</code> if a training linked user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser fetchByPrimaryKey(long trainingLinkedUserId)
		throws SystemException {
		TrainingLinkedUser trainingLinkedUser = (TrainingLinkedUser)EntityCacheUtil.getResult(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
				TrainingLinkedUserImpl.class, trainingLinkedUserId);

		if (trainingLinkedUser == _nullTrainingLinkedUser) {
			return null;
		}

		if (trainingLinkedUser == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingLinkedUser = (TrainingLinkedUser)session.get(TrainingLinkedUserImpl.class,
						Long.valueOf(trainingLinkedUserId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingLinkedUser != null) {
					cacheResult(trainingLinkedUser);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingLinkedUserModelImpl.ENTITY_CACHE_ENABLED,
						TrainingLinkedUserImpl.class, trainingLinkedUserId,
						_nullTrainingLinkedUser);
				}

				closeSession(session);
			}
		}

		return trainingLinkedUser;
	}

	/**
	 * Returns the training linked user where userId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingLinkedUserException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching training linked user
	 * @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser findByUserId(long userId)
		throws NoSuchTrainingLinkedUserException, SystemException {
		TrainingLinkedUser trainingLinkedUser = fetchByUserId(userId);

		if (trainingLinkedUser == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTrainingLinkedUserException(msg.toString());
		}

		return trainingLinkedUser;
	}

	/**
	 * Returns the training linked user where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching training linked user, or <code>null</code> if a matching training linked user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser fetchByUserId(long userId)
		throws SystemException {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the training linked user where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching training linked user, or <code>null</code> if a matching training linked user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser fetchByUserId(long userId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERID,
					finderArgs, this);
		}

		if (result instanceof TrainingLinkedUser) {
			TrainingLinkedUser trainingLinkedUser = (TrainingLinkedUser)result;

			if ((userId != trainingLinkedUser.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_TRAININGLINKEDUSER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<TrainingLinkedUser> list = q.list();

				result = list;

				TrainingLinkedUser trainingLinkedUser = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs, list);
				}
				else {
					trainingLinkedUser = list.get(0);

					cacheResult(trainingLinkedUser);

					if ((trainingLinkedUser.getUserId() != userId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
							finderArgs, trainingLinkedUser);
					}
				}

				return trainingLinkedUser;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID,
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
				return (TrainingLinkedUser)result;
			}
		}
	}

	/**
	 * Returns all the training linked users where primaryUserId = &#63;.
	 *
	 * @param primaryUserId the primary user ID
	 * @return the matching training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingLinkedUser> findByPrimaryUserId(long primaryUserId)
		throws SystemException {
		return findByPrimaryUserId(primaryUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training linked users where primaryUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param primaryUserId the primary user ID
	 * @param start the lower bound of the range of training linked users
	 * @param end the upper bound of the range of training linked users (not inclusive)
	 * @return the range of matching training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingLinkedUser> findByPrimaryUserId(long primaryUserId,
		int start, int end) throws SystemException {
		return findByPrimaryUserId(primaryUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training linked users where primaryUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param primaryUserId the primary user ID
	 * @param start the lower bound of the range of training linked users
	 * @param end the upper bound of the range of training linked users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingLinkedUser> findByPrimaryUserId(long primaryUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUSERID;
			finderArgs = new Object[] { primaryUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRIMARYUSERID;
			finderArgs = new Object[] {
					primaryUserId,
					
					start, end, orderByComparator
				};
		}

		List<TrainingLinkedUser> list = (List<TrainingLinkedUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingLinkedUser trainingLinkedUser : list) {
				if ((primaryUserId != trainingLinkedUser.getPrimaryUserId())) {
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

			query.append(_SQL_SELECT_TRAININGLINKEDUSER_WHERE);

			query.append(_FINDER_COLUMN_PRIMARYUSERID_PRIMARYUSERID_2);

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

				qPos.add(primaryUserId);

				list = (List<TrainingLinkedUser>)QueryUtil.list(q,
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
	 * Returns the first training linked user in the ordered set where primaryUserId = &#63;.
	 *
	 * @param primaryUserId the primary user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training linked user
	 * @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser findByPrimaryUserId_First(long primaryUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingLinkedUserException, SystemException {
		TrainingLinkedUser trainingLinkedUser = fetchByPrimaryUserId_First(primaryUserId,
				orderByComparator);

		if (trainingLinkedUser != null) {
			return trainingLinkedUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("primaryUserId=");
		msg.append(primaryUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingLinkedUserException(msg.toString());
	}

	/**
	 * Returns the first training linked user in the ordered set where primaryUserId = &#63;.
	 *
	 * @param primaryUserId the primary user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training linked user, or <code>null</code> if a matching training linked user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser fetchByPrimaryUserId_First(long primaryUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingLinkedUser> list = findByPrimaryUserId(primaryUserId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training linked user in the ordered set where primaryUserId = &#63;.
	 *
	 * @param primaryUserId the primary user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training linked user
	 * @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser findByPrimaryUserId_Last(long primaryUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingLinkedUserException, SystemException {
		TrainingLinkedUser trainingLinkedUser = fetchByPrimaryUserId_Last(primaryUserId,
				orderByComparator);

		if (trainingLinkedUser != null) {
			return trainingLinkedUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("primaryUserId=");
		msg.append(primaryUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingLinkedUserException(msg.toString());
	}

	/**
	 * Returns the last training linked user in the ordered set where primaryUserId = &#63;.
	 *
	 * @param primaryUserId the primary user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training linked user, or <code>null</code> if a matching training linked user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser fetchByPrimaryUserId_Last(long primaryUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPrimaryUserId(primaryUserId);

		List<TrainingLinkedUser> list = findByPrimaryUserId(primaryUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training linked users before and after the current training linked user in the ordered set where primaryUserId = &#63;.
	 *
	 * @param trainingLinkedUserId the primary key of the current training linked user
	 * @param primaryUserId the primary user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training linked user
	 * @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser[] findByPrimaryUserId_PrevAndNext(
		long trainingLinkedUserId, long primaryUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingLinkedUserException, SystemException {
		TrainingLinkedUser trainingLinkedUser = findByPrimaryKey(trainingLinkedUserId);

		Session session = null;

		try {
			session = openSession();

			TrainingLinkedUser[] array = new TrainingLinkedUserImpl[3];

			array[0] = getByPrimaryUserId_PrevAndNext(session,
					trainingLinkedUser, primaryUserId, orderByComparator, true);

			array[1] = trainingLinkedUser;

			array[2] = getByPrimaryUserId_PrevAndNext(session,
					trainingLinkedUser, primaryUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingLinkedUser getByPrimaryUserId_PrevAndNext(
		Session session, TrainingLinkedUser trainingLinkedUser,
		long primaryUserId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGLINKEDUSER_WHERE);

		query.append(_FINDER_COLUMN_PRIMARYUSERID_PRIMARYUSERID_2);

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

		qPos.add(primaryUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingLinkedUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingLinkedUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training linked users.
	 *
	 * @return the training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingLinkedUser> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training linked users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training linked users
	 * @param end the upper bound of the range of training linked users (not inclusive)
	 * @return the range of training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingLinkedUser> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training linked users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training linked users
	 * @param end the upper bound of the range of training linked users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingLinkedUser> findAll(int start, int end,
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

		List<TrainingLinkedUser> list = (List<TrainingLinkedUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGLINKEDUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGLINKEDUSER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingLinkedUser>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingLinkedUser>)QueryUtil.list(q,
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
	 * Removes the training linked user where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the training linked user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingLinkedUser removeByUserId(long userId)
		throws NoSuchTrainingLinkedUserException, SystemException {
		TrainingLinkedUser trainingLinkedUser = findByUserId(userId);

		return remove(trainingLinkedUser);
	}

	/**
	 * Removes all the training linked users where primaryUserId = &#63; from the database.
	 *
	 * @param primaryUserId the primary user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPrimaryUserId(long primaryUserId)
		throws SystemException {
		for (TrainingLinkedUser trainingLinkedUser : findByPrimaryUserId(
				primaryUserId)) {
			remove(trainingLinkedUser);
		}
	}

	/**
	 * Removes all the training linked users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingLinkedUser trainingLinkedUser : findAll()) {
			remove(trainingLinkedUser);
		}
	}

	/**
	 * Returns the number of training linked users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGLINKEDUSER_WHERE);

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
	 * Returns the number of training linked users where primaryUserId = &#63;.
	 *
	 * @param primaryUserId the primary user ID
	 * @return the number of matching training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPrimaryUserId(long primaryUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { primaryUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PRIMARYUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGLINKEDUSER_WHERE);

			query.append(_FINDER_COLUMN_PRIMARYUSERID_PRIMARYUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(primaryUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PRIMARYUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training linked users.
	 *
	 * @return the number of training linked users
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGLINKEDUSER);

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
	 * Initializes the training linked user persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingLinkedUser")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingLinkedUser>> listenersList = new ArrayList<ModelListener<TrainingLinkedUser>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingLinkedUser>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingLinkedUserImpl.class.getName());
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
	private static final String _SQL_SELECT_TRAININGLINKEDUSER = "SELECT trainingLinkedUser FROM TrainingLinkedUser trainingLinkedUser";
	private static final String _SQL_SELECT_TRAININGLINKEDUSER_WHERE = "SELECT trainingLinkedUser FROM TrainingLinkedUser trainingLinkedUser WHERE ";
	private static final String _SQL_COUNT_TRAININGLINKEDUSER = "SELECT COUNT(trainingLinkedUser) FROM TrainingLinkedUser trainingLinkedUser";
	private static final String _SQL_COUNT_TRAININGLINKEDUSER_WHERE = "SELECT COUNT(trainingLinkedUser) FROM TrainingLinkedUser trainingLinkedUser WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "trainingLinkedUser.userId = ?";
	private static final String _FINDER_COLUMN_PRIMARYUSERID_PRIMARYUSERID_2 = "trainingLinkedUser.primaryUserId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingLinkedUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingLinkedUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingLinkedUser exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingLinkedUserPersistenceImpl.class);
	private static TrainingLinkedUser _nullTrainingLinkedUser = new TrainingLinkedUserImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingLinkedUser> toCacheModel() {
				return _nullTrainingLinkedUserCacheModel;
			}
		};

	private static CacheModel<TrainingLinkedUser> _nullTrainingLinkedUserCacheModel =
		new CacheModel<TrainingLinkedUser>() {
			public TrainingLinkedUser toEntityModel() {
				return _nullTrainingLinkedUser;
			}
		};
}
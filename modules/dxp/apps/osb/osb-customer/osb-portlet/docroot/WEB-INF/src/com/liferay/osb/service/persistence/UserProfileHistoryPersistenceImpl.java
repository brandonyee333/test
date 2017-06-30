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

import com.liferay.osb.NoSuchUserProfileHistoryException;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.model.impl.UserProfileHistoryImpl;
import com.liferay.osb.model.impl.UserProfileHistoryModelImpl;

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
 * The persistence implementation for the user profile history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileHistoryPersistence
 * @see UserProfileHistoryUtil
 * @generated
 */
public class UserProfileHistoryPersistenceImpl extends BasePersistenceImpl<UserProfileHistory>
	implements UserProfileHistoryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserProfileHistoryUtil} to access the user profile history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserProfileHistoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED,
			UserProfileHistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED,
			UserProfileHistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			UserProfileHistoryModelImpl.USERID_COLUMN_BITMASK |
			UserProfileHistoryModelImpl.CLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED,
			UserProfileHistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED,
			UserProfileHistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			UserProfileHistoryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			UserProfileHistoryModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED,
			UserProfileHistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED,
			UserProfileHistoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the user profile history in the entity cache if it is enabled.
	 *
	 * @param userProfileHistory the user profile history
	 */
	public void cacheResult(UserProfileHistory userProfileHistory) {
		EntityCacheUtil.putResult(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryImpl.class, userProfileHistory.getPrimaryKey(),
			userProfileHistory);

		userProfileHistory.resetOriginalValues();
	}

	/**
	 * Caches the user profile histories in the entity cache if it is enabled.
	 *
	 * @param userProfileHistories the user profile histories
	 */
	public void cacheResult(List<UserProfileHistory> userProfileHistories) {
		for (UserProfileHistory userProfileHistory : userProfileHistories) {
			if (EntityCacheUtil.getResult(
						UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
						UserProfileHistoryImpl.class,
						userProfileHistory.getPrimaryKey()) == null) {
				cacheResult(userProfileHistory);
			}
			else {
				userProfileHistory.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user profile histories.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(UserProfileHistoryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(UserProfileHistoryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user profile history.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserProfileHistory userProfileHistory) {
		EntityCacheUtil.removeResult(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryImpl.class, userProfileHistory.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UserProfileHistory> userProfileHistories) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserProfileHistory userProfileHistory : userProfileHistories) {
			EntityCacheUtil.removeResult(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
				UserProfileHistoryImpl.class, userProfileHistory.getPrimaryKey());
		}
	}

	/**
	 * Creates a new user profile history with the primary key. Does not add the user profile history to the database.
	 *
	 * @param userProfileHistoryId the primary key for the new user profile history
	 * @return the new user profile history
	 */
	public UserProfileHistory create(long userProfileHistoryId) {
		UserProfileHistory userProfileHistory = new UserProfileHistoryImpl();

		userProfileHistory.setNew(true);
		userProfileHistory.setPrimaryKey(userProfileHistoryId);

		return userProfileHistory;
	}

	/**
	 * Removes the user profile history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userProfileHistoryId the primary key of the user profile history
	 * @return the user profile history that was removed
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory remove(long userProfileHistoryId)
		throws NoSuchUserProfileHistoryException, SystemException {
		return remove(Long.valueOf(userProfileHistoryId));
	}

	/**
	 * Removes the user profile history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user profile history
	 * @return the user profile history that was removed
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserProfileHistory remove(Serializable primaryKey)
		throws NoSuchUserProfileHistoryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			UserProfileHistory userProfileHistory = (UserProfileHistory)session.get(UserProfileHistoryImpl.class,
					primaryKey);

			if (userProfileHistory == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserProfileHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userProfileHistory);
		}
		catch (NoSuchUserProfileHistoryException nsee) {
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
	protected UserProfileHistory removeImpl(
		UserProfileHistory userProfileHistory) throws SystemException {
		userProfileHistory = toUnwrappedModel(userProfileHistory);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, userProfileHistory);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(userProfileHistory);

		return userProfileHistory;
	}

	@Override
	public UserProfileHistory updateImpl(
		com.liferay.osb.model.UserProfileHistory userProfileHistory,
		boolean merge) throws SystemException {
		userProfileHistory = toUnwrappedModel(userProfileHistory);

		boolean isNew = userProfileHistory.isNew();

		UserProfileHistoryModelImpl userProfileHistoryModelImpl = (UserProfileHistoryModelImpl)userProfileHistory;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, userProfileHistory, merge);

			userProfileHistory.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserProfileHistoryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((userProfileHistoryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(userProfileHistoryModelImpl.getOriginalUserId()),
						Long.valueOf(userProfileHistoryModelImpl.getOriginalClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C,
					args);

				args = new Object[] {
						Long.valueOf(userProfileHistoryModelImpl.getUserId()),
						Long.valueOf(userProfileHistoryModelImpl.getClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C,
					args);
			}

			if ((userProfileHistoryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(userProfileHistoryModelImpl.getOriginalClassNameId()),
						Long.valueOf(userProfileHistoryModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(userProfileHistoryModelImpl.getClassNameId()),
						Long.valueOf(userProfileHistoryModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}
		}

		EntityCacheUtil.putResult(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
			UserProfileHistoryImpl.class, userProfileHistory.getPrimaryKey(),
			userProfileHistory);

		return userProfileHistory;
	}

	protected UserProfileHistory toUnwrappedModel(
		UserProfileHistory userProfileHistory) {
		if (userProfileHistory instanceof UserProfileHistoryImpl) {
			return userProfileHistory;
		}

		UserProfileHistoryImpl userProfileHistoryImpl = new UserProfileHistoryImpl();

		userProfileHistoryImpl.setNew(userProfileHistory.isNew());
		userProfileHistoryImpl.setPrimaryKey(userProfileHistory.getPrimaryKey());

		userProfileHistoryImpl.setUserProfileHistoryId(userProfileHistory.getUserProfileHistoryId());
		userProfileHistoryImpl.setUserId(userProfileHistory.getUserId());
		userProfileHistoryImpl.setCreateDate(userProfileHistory.getCreateDate());
		userProfileHistoryImpl.setClassNameId(userProfileHistory.getClassNameId());
		userProfileHistoryImpl.setClassPK(userProfileHistory.getClassPK());
		userProfileHistoryImpl.setEmailAddress(userProfileHistory.getEmailAddress());
		userProfileHistoryImpl.setFirstName(userProfileHistory.getFirstName());
		userProfileHistoryImpl.setLastName(userProfileHistory.getLastName());
		userProfileHistoryImpl.setLegalEntityName(userProfileHistory.getLegalEntityName());

		return userProfileHistoryImpl;
	}

	/**
	 * Returns the user profile history with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user profile history
	 * @return the user profile history
	 * @throws com.liferay.portal.NoSuchModelException if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserProfileHistory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user profile history with the primary key or throws a {@link com.liferay.osb.NoSuchUserProfileHistoryException} if it could not be found.
	 *
	 * @param userProfileHistoryId the primary key of the user profile history
	 * @return the user profile history
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory findByPrimaryKey(long userProfileHistoryId)
		throws NoSuchUserProfileHistoryException, SystemException {
		UserProfileHistory userProfileHistory = fetchByPrimaryKey(userProfileHistoryId);

		if (userProfileHistory == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userProfileHistoryId);
			}

			throw new NoSuchUserProfileHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userProfileHistoryId);
		}

		return userProfileHistory;
	}

	/**
	 * Returns the user profile history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user profile history
	 * @return the user profile history, or <code>null</code> if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserProfileHistory fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user profile history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userProfileHistoryId the primary key of the user profile history
	 * @return the user profile history, or <code>null</code> if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory fetchByPrimaryKey(long userProfileHistoryId)
		throws SystemException {
		UserProfileHistory userProfileHistory = (UserProfileHistory)EntityCacheUtil.getResult(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
				UserProfileHistoryImpl.class, userProfileHistoryId);

		if (userProfileHistory == _nullUserProfileHistory) {
			return null;
		}

		if (userProfileHistory == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				userProfileHistory = (UserProfileHistory)session.get(UserProfileHistoryImpl.class,
						Long.valueOf(userProfileHistoryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (userProfileHistory != null) {
					cacheResult(userProfileHistory);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(UserProfileHistoryModelImpl.ENTITY_CACHE_ENABLED,
						UserProfileHistoryImpl.class, userProfileHistoryId,
						_nullUserProfileHistory);
				}

				closeSession(session);
			}
		}

		return userProfileHistory;
	}

	/**
	 * Returns all the user profile histories where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findByU_C(long userId, long classNameId)
		throws SystemException {
		return findByU_C(userId, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user profile histories where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of user profile histories
	 * @param end the upper bound of the range of user profile histories (not inclusive)
	 * @return the range of matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findByU_C(long userId, long classNameId,
		int start, int end) throws SystemException {
		return findByU_C(userId, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user profile histories where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of user profile histories
	 * @param end the upper bound of the range of user profile histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findByU_C(long userId, long classNameId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C;
			finderArgs = new Object[] { userId, classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C;
			finderArgs = new Object[] {
					userId, classNameId,
					
					start, end, orderByComparator
				};
		}

		List<UserProfileHistory> list = (List<UserProfileHistory>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserProfileHistory userProfileHistory : list) {
				if ((userId != userProfileHistory.getUserId()) ||
						(classNameId != userProfileHistory.getClassNameId())) {
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

			query.append(_SQL_SELECT_USERPROFILEHISTORY_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

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

				qPos.add(classNameId);

				list = (List<UserProfileHistory>)QueryUtil.list(q,
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
	 * Returns the first user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile history
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory findByU_C_First(long userId, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchUserProfileHistoryException, SystemException {
		UserProfileHistory userProfileHistory = fetchByU_C_First(userId,
				classNameId, orderByComparator);

		if (userProfileHistory != null) {
			return userProfileHistory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserProfileHistoryException(msg.toString());
	}

	/**
	 * Returns the first user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile history, or <code>null</code> if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory fetchByU_C_First(long userId, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserProfileHistory> list = findByU_C(userId, classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile history
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory findByU_C_Last(long userId, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchUserProfileHistoryException, SystemException {
		UserProfileHistory userProfileHistory = fetchByU_C_Last(userId,
				classNameId, orderByComparator);

		if (userProfileHistory != null) {
			return userProfileHistory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserProfileHistoryException(msg.toString());
	}

	/**
	 * Returns the last user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile history, or <code>null</code> if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory fetchByU_C_Last(long userId, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_C(userId, classNameId);

		List<UserProfileHistory> list = findByU_C(userId, classNameId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user profile histories before and after the current user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userProfileHistoryId the primary key of the current user profile history
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user profile history
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory[] findByU_C_PrevAndNext(
		long userProfileHistoryId, long userId, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchUserProfileHistoryException, SystemException {
		UserProfileHistory userProfileHistory = findByPrimaryKey(userProfileHistoryId);

		Session session = null;

		try {
			session = openSession();

			UserProfileHistory[] array = new UserProfileHistoryImpl[3];

			array[0] = getByU_C_PrevAndNext(session, userProfileHistory,
					userId, classNameId, orderByComparator, true);

			array[1] = userProfileHistory;

			array[2] = getByU_C_PrevAndNext(session, userProfileHistory,
					userId, classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserProfileHistory getByU_C_PrevAndNext(Session session,
		UserProfileHistory userProfileHistory, long userId, long classNameId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERPROFILEHISTORY_WHERE);

		query.append(_FINDER_COLUMN_U_C_USERID_2);

		query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

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

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userProfileHistory);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserProfileHistory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the user profile histories where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user profile histories where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of user profile histories
	 * @param end the upper bound of the range of user profile histories (not inclusive)
	 * @return the range of matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user profile histories where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of user profile histories
	 * @param end the upper bound of the range of user profile histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findByC_C(long classNameId, long classPK,
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

		List<UserProfileHistory> list = (List<UserProfileHistory>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserProfileHistory userProfileHistory : list) {
				if ((classNameId != userProfileHistory.getClassNameId()) ||
						(classPK != userProfileHistory.getClassPK())) {
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

			query.append(_SQL_SELECT_USERPROFILEHISTORY_WHERE);

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

				list = (List<UserProfileHistory>)QueryUtil.list(q,
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
	 * Returns the first user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile history
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchUserProfileHistoryException, SystemException {
		UserProfileHistory userProfileHistory = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (userProfileHistory != null) {
			return userProfileHistory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserProfileHistoryException(msg.toString());
	}

	/**
	 * Returns the first user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile history, or <code>null</code> if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserProfileHistory> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile history
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchUserProfileHistoryException, SystemException {
		UserProfileHistory userProfileHistory = fetchByC_C_Last(classNameId,
				classPK, orderByComparator);

		if (userProfileHistory != null) {
			return userProfileHistory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserProfileHistoryException(msg.toString());
	}

	/**
	 * Returns the last user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile history, or <code>null</code> if a matching user profile history could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<UserProfileHistory> list = findByC_C(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user profile histories before and after the current user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userProfileHistoryId the primary key of the current user profile history
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user profile history
	 * @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserProfileHistory[] findByC_C_PrevAndNext(
		long userProfileHistoryId, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchUserProfileHistoryException, SystemException {
		UserProfileHistory userProfileHistory = findByPrimaryKey(userProfileHistoryId);

		Session session = null;

		try {
			session = openSession();

			UserProfileHistory[] array = new UserProfileHistoryImpl[3];

			array[0] = getByC_C_PrevAndNext(session, userProfileHistory,
					classNameId, classPK, orderByComparator, true);

			array[1] = userProfileHistory;

			array[2] = getByC_C_PrevAndNext(session, userProfileHistory,
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

	protected UserProfileHistory getByC_C_PrevAndNext(Session session,
		UserProfileHistory userProfileHistory, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERPROFILEHISTORY_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(userProfileHistory);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserProfileHistory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the user profile histories.
	 *
	 * @return the user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user profile histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profile histories
	 * @param end the upper bound of the range of user profile histories (not inclusive)
	 * @return the range of user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user profile histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profile histories
	 * @param end the upper bound of the range of user profile histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserProfileHistory> findAll(int start, int end,
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

		List<UserProfileHistory> list = (List<UserProfileHistory>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_USERPROFILEHISTORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERPROFILEHISTORY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<UserProfileHistory>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<UserProfileHistory>)QueryUtil.list(q,
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
	 * Removes all the user profile histories where userId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_C(long userId, long classNameId)
		throws SystemException {
		for (UserProfileHistory userProfileHistory : findByU_C(userId,
				classNameId)) {
			remove(userProfileHistory);
		}
	}

	/**
	 * Removes all the user profile histories where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (UserProfileHistory userProfileHistory : findByC_C(classNameId,
				classPK)) {
			remove(userProfileHistory);
		}
	}

	/**
	 * Removes all the user profile histories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (UserProfileHistory userProfileHistory : findAll()) {
			remove(userProfileHistory);
		}
	}

	/**
	 * Returns the number of user profile histories where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the number of matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_C(long userId, long classNameId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERPROFILEHISTORY_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user profile histories where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERPROFILEHISTORY_WHERE);

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
	 * Returns the number of user profile histories.
	 *
	 * @return the number of user profile histories
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERPROFILEHISTORY);

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
	 * Initializes the user profile history persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.UserProfileHistory")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<UserProfileHistory>> listenersList = new ArrayList<ModelListener<UserProfileHistory>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<UserProfileHistory>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(UserProfileHistoryImpl.class.getName());
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
	private static final String _SQL_SELECT_USERPROFILEHISTORY = "SELECT userProfileHistory FROM UserProfileHistory userProfileHistory";
	private static final String _SQL_SELECT_USERPROFILEHISTORY_WHERE = "SELECT userProfileHistory FROM UserProfileHistory userProfileHistory WHERE ";
	private static final String _SQL_COUNT_USERPROFILEHISTORY = "SELECT COUNT(userProfileHistory) FROM UserProfileHistory userProfileHistory";
	private static final String _SQL_COUNT_USERPROFILEHISTORY_WHERE = "SELECT COUNT(userProfileHistory) FROM UserProfileHistory userProfileHistory WHERE ";
	private static final String _FINDER_COLUMN_U_C_USERID_2 = "userProfileHistory.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_CLASSNAMEID_2 = "userProfileHistory.classNameId = ?";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "userProfileHistory.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "userProfileHistory.classPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userProfileHistory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserProfileHistory exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserProfileHistory exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(UserProfileHistoryPersistenceImpl.class);
	private static UserProfileHistory _nullUserProfileHistory = new UserProfileHistoryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UserProfileHistory> toCacheModel() {
				return _nullUserProfileHistoryCacheModel;
			}
		};

	private static CacheModel<UserProfileHistory> _nullUserProfileHistoryCacheModel =
		new CacheModel<UserProfileHistory>() {
			public UserProfileHistory toEntityModel() {
				return _nullUserProfileHistory;
			}
		};
}
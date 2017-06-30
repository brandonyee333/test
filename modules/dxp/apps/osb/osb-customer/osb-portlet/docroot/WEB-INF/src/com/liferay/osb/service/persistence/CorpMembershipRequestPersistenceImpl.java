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

import com.liferay.osb.NoSuchCorpMembershipRequestException;
import com.liferay.osb.model.CorpMembershipRequest;
import com.liferay.osb.model.impl.CorpMembershipRequestImpl;
import com.liferay.osb.model.impl.CorpMembershipRequestModelImpl;

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
 * The persistence implementation for the corp membership request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpMembershipRequestPersistence
 * @see CorpMembershipRequestUtil
 * @generated
 */
public class CorpMembershipRequestPersistenceImpl extends BasePersistenceImpl<CorpMembershipRequest>
	implements CorpMembershipRequestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpMembershipRequestUtil} to access the corp membership request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpMembershipRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			CorpMembershipRequestImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKey", new String[] { String.class.getName() },
			CorpMembershipRequestModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CEI_S = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_S = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			CorpMembershipRequestModelImpl.CORPENTRYID_COLUMN_BITMASK |
			CorpMembershipRequestModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CEI_S = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CEI_S = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_CEI_S = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_CEI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI_S =
		new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_CEI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			CorpMembershipRequestModelImpl.USERID_COLUMN_BITMASK |
			CorpMembershipRequestModelImpl.CORPENTRYID_COLUMN_BITMASK |
			CorpMembershipRequestModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_CEI_S = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_CEI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_CEI_S = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_CEI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the corp membership request in the entity cache if it is enabled.
	 *
	 * @param corpMembershipRequest the corp membership request
	 */
	public void cacheResult(CorpMembershipRequest corpMembershipRequest) {
		EntityCacheUtil.putResult(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			corpMembershipRequest.getPrimaryKey(), corpMembershipRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { corpMembershipRequest.getKey() },
			corpMembershipRequest);

		corpMembershipRequest.resetOriginalValues();
	}

	/**
	 * Caches the corp membership requests in the entity cache if it is enabled.
	 *
	 * @param corpMembershipRequests the corp membership requests
	 */
	public void cacheResult(List<CorpMembershipRequest> corpMembershipRequests) {
		for (CorpMembershipRequest corpMembershipRequest : corpMembershipRequests) {
			if (EntityCacheUtil.getResult(
						CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
						CorpMembershipRequestImpl.class,
						corpMembershipRequest.getPrimaryKey()) == null) {
				cacheResult(corpMembershipRequest);
			}
			else {
				corpMembershipRequest.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp membership requests.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CorpMembershipRequestImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CorpMembershipRequestImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp membership request.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpMembershipRequest corpMembershipRequest) {
		EntityCacheUtil.removeResult(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			corpMembershipRequest.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(corpMembershipRequest);
	}

	@Override
	public void clearCache(List<CorpMembershipRequest> corpMembershipRequests) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpMembershipRequest corpMembershipRequest : corpMembershipRequests) {
			EntityCacheUtil.removeResult(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
				CorpMembershipRequestImpl.class,
				corpMembershipRequest.getPrimaryKey());

			clearUniqueFindersCache(corpMembershipRequest);
		}
	}

	protected void cacheUniqueFindersCache(
		CorpMembershipRequest corpMembershipRequest) {
		if (corpMembershipRequest.isNew()) {
			Object[] args = new Object[] { corpMembershipRequest.getKey() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY, args,
				corpMembershipRequest);
		}
		else {
			CorpMembershipRequestModelImpl corpMembershipRequestModelImpl = (CorpMembershipRequestModelImpl)corpMembershipRequest;

			if ((corpMembershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { corpMembershipRequest.getKey() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY, args,
					corpMembershipRequest);
			}
		}
	}

	protected void clearUniqueFindersCache(
		CorpMembershipRequest corpMembershipRequest) {
		CorpMembershipRequestModelImpl corpMembershipRequestModelImpl = (CorpMembershipRequestModelImpl)corpMembershipRequest;

		Object[] args = new Object[] { corpMembershipRequest.getKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY, args);

		if ((corpMembershipRequestModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
			args = new Object[] { corpMembershipRequestModelImpl.getOriginalKey() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY, args);
		}
	}

	/**
	 * Creates a new corp membership request with the primary key. Does not add the corp membership request to the database.
	 *
	 * @param corpMembershipRequestId the primary key for the new corp membership request
	 * @return the new corp membership request
	 */
	public CorpMembershipRequest create(long corpMembershipRequestId) {
		CorpMembershipRequest corpMembershipRequest = new CorpMembershipRequestImpl();

		corpMembershipRequest.setNew(true);
		corpMembershipRequest.setPrimaryKey(corpMembershipRequestId);

		return corpMembershipRequest;
	}

	/**
	 * Removes the corp membership request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpMembershipRequestId the primary key of the corp membership request
	 * @return the corp membership request that was removed
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest remove(long corpMembershipRequestId)
		throws NoSuchCorpMembershipRequestException, SystemException {
		return remove(Long.valueOf(corpMembershipRequestId));
	}

	/**
	 * Removes the corp membership request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp membership request
	 * @return the corp membership request that was removed
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpMembershipRequest remove(Serializable primaryKey)
		throws NoSuchCorpMembershipRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CorpMembershipRequest corpMembershipRequest = (CorpMembershipRequest)session.get(CorpMembershipRequestImpl.class,
					primaryKey);

			if (corpMembershipRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpMembershipRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpMembershipRequest);
		}
		catch (NoSuchCorpMembershipRequestException nsee) {
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
	protected CorpMembershipRequest removeImpl(
		CorpMembershipRequest corpMembershipRequest) throws SystemException {
		corpMembershipRequest = toUnwrappedModel(corpMembershipRequest);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, corpMembershipRequest);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(corpMembershipRequest);

		return corpMembershipRequest;
	}

	@Override
	public CorpMembershipRequest updateImpl(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest,
		boolean merge) throws SystemException {
		corpMembershipRequest = toUnwrappedModel(corpMembershipRequest);

		boolean isNew = corpMembershipRequest.isNew();

		CorpMembershipRequestModelImpl corpMembershipRequestModelImpl = (CorpMembershipRequestModelImpl)corpMembershipRequest;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, corpMembershipRequest, merge);

			corpMembershipRequest.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CorpMembershipRequestModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((corpMembershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(corpMembershipRequestModelImpl.getOriginalCorpEntryId()),
						Integer.valueOf(corpMembershipRequestModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_S,
					args);

				args = new Object[] {
						Long.valueOf(corpMembershipRequestModelImpl.getCorpEntryId()),
						Integer.valueOf(corpMembershipRequestModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_S,
					args);
			}

			if ((corpMembershipRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(corpMembershipRequestModelImpl.getOriginalUserId()),
						Long.valueOf(corpMembershipRequestModelImpl.getOriginalCorpEntryId()),
						Integer.valueOf(corpMembershipRequestModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_CEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI_S,
					args);

				args = new Object[] {
						Long.valueOf(corpMembershipRequestModelImpl.getUserId()),
						Long.valueOf(corpMembershipRequestModelImpl.getCorpEntryId()),
						Integer.valueOf(corpMembershipRequestModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_CEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI_S,
					args);
			}
		}

		EntityCacheUtil.putResult(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
			CorpMembershipRequestImpl.class,
			corpMembershipRequest.getPrimaryKey(), corpMembershipRequest);

		clearUniqueFindersCache(corpMembershipRequest);
		cacheUniqueFindersCache(corpMembershipRequest);

		return corpMembershipRequest;
	}

	protected CorpMembershipRequest toUnwrappedModel(
		CorpMembershipRequest corpMembershipRequest) {
		if (corpMembershipRequest instanceof CorpMembershipRequestImpl) {
			return corpMembershipRequest;
		}

		CorpMembershipRequestImpl corpMembershipRequestImpl = new CorpMembershipRequestImpl();

		corpMembershipRequestImpl.setNew(corpMembershipRequest.isNew());
		corpMembershipRequestImpl.setPrimaryKey(corpMembershipRequest.getPrimaryKey());

		corpMembershipRequestImpl.setCorpMembershipRequestId(corpMembershipRequest.getCorpMembershipRequestId());
		corpMembershipRequestImpl.setUserId(corpMembershipRequest.getUserId());
		corpMembershipRequestImpl.setUserName(corpMembershipRequest.getUserName());
		corpMembershipRequestImpl.setCreateDate(corpMembershipRequest.getCreateDate());
		corpMembershipRequestImpl.setModifiedDate(corpMembershipRequest.getModifiedDate());
		corpMembershipRequestImpl.setCorpEntryId(corpMembershipRequest.getCorpEntryId());
		corpMembershipRequestImpl.setKey(corpMembershipRequest.getKey());
		corpMembershipRequestImpl.setEmailAddress(corpMembershipRequest.getEmailAddress());
		corpMembershipRequestImpl.setStatus(corpMembershipRequest.getStatus());

		return corpMembershipRequestImpl;
	}

	/**
	 * Returns the corp membership request with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp membership request
	 * @return the corp membership request
	 * @throws com.liferay.portal.NoSuchModelException if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpMembershipRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp membership request with the primary key or throws a {@link com.liferay.osb.NoSuchCorpMembershipRequestException} if it could not be found.
	 *
	 * @param corpMembershipRequestId the primary key of the corp membership request
	 * @return the corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest findByPrimaryKey(long corpMembershipRequestId)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = fetchByPrimaryKey(corpMembershipRequestId);

		if (corpMembershipRequest == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					corpMembershipRequestId);
			}

			throw new NoSuchCorpMembershipRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				corpMembershipRequestId);
		}

		return corpMembershipRequest;
	}

	/**
	 * Returns the corp membership request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp membership request
	 * @return the corp membership request, or <code>null</code> if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpMembershipRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp membership request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpMembershipRequestId the primary key of the corp membership request
	 * @return the corp membership request, or <code>null</code> if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest fetchByPrimaryKey(long corpMembershipRequestId)
		throws SystemException {
		CorpMembershipRequest corpMembershipRequest = (CorpMembershipRequest)EntityCacheUtil.getResult(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
				CorpMembershipRequestImpl.class, corpMembershipRequestId);

		if (corpMembershipRequest == _nullCorpMembershipRequest) {
			return null;
		}

		if (corpMembershipRequest == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				corpMembershipRequest = (CorpMembershipRequest)session.get(CorpMembershipRequestImpl.class,
						Long.valueOf(corpMembershipRequestId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (corpMembershipRequest != null) {
					cacheResult(corpMembershipRequest);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CorpMembershipRequestModelImpl.ENTITY_CACHE_ENABLED,
						CorpMembershipRequestImpl.class,
						corpMembershipRequestId, _nullCorpMembershipRequest);
				}

				closeSession(session);
			}
		}

		return corpMembershipRequest;
	}

	/**
	 * Returns the corp membership request where key = &#63; or throws a {@link com.liferay.osb.NoSuchCorpMembershipRequestException} if it could not be found.
	 *
	 * @param key the key
	 * @return the matching corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest findByKey(String key)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = fetchByKey(key);

		if (corpMembershipRequest == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCorpMembershipRequestException(msg.toString());
		}

		return corpMembershipRequest;
	}

	/**
	 * Returns the corp membership request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest fetchByKey(String key)
		throws SystemException {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the corp membership request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest fetchByKey(String key,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result instanceof CorpMembershipRequest) {
			CorpMembershipRequest corpMembershipRequest = (CorpMembershipRequest)result;

			if (!Validator.equals(key, corpMembershipRequest.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE);

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

				List<CorpMembershipRequest> list = q.list();

				result = list;

				CorpMembershipRequest corpMembershipRequest = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs, list);
				}
				else {
					corpMembershipRequest = list.get(0);

					cacheResult(corpMembershipRequest);

					if ((corpMembershipRequest.getKey() == null) ||
							!corpMembershipRequest.getKey().equals(key)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, corpMembershipRequest);
					}
				}

				return corpMembershipRequest;
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
				return (CorpMembershipRequest)result;
			}
		}
	}

	/**
	 * Returns all the corp membership requests where corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @return the matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByCEI_S(long corpEntryId, int status)
		throws SystemException {
		return findByCEI_S(corpEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp membership requests where corpEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @return the range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByCEI_S(long corpEntryId,
		int status, int start, int end) throws SystemException {
		return findByCEI_S(corpEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp membership requests where corpEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByCEI_S(long corpEntryId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_S;
			finderArgs = new Object[] { corpEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CEI_S;
			finderArgs = new Object[] {
					corpEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<CorpMembershipRequest> list = (List<CorpMembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpMembershipRequest corpMembershipRequest : list) {
				if ((corpEntryId != corpMembershipRequest.getCorpEntryId()) ||
						(status != corpMembershipRequest.getStatus())) {
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

			query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_CEI_S_CORPENTRYID_2);

			query.append(_FINDER_COLUMN_CEI_S_STATUS_2);

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

				qPos.add(corpEntryId);

				qPos.add(status);

				list = (List<CorpMembershipRequest>)QueryUtil.list(q,
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
	 * Returns the first corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest findByCEI_S_First(long corpEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = fetchByCEI_S_First(corpEntryId,
				status, orderByComparator);

		if (corpMembershipRequest != null) {
			return corpMembershipRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpEntryId=");
		msg.append(corpEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest fetchByCEI_S_First(long corpEntryId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<CorpMembershipRequest> list = findByCEI_S(corpEntryId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest findByCEI_S_Last(long corpEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = fetchByCEI_S_Last(corpEntryId,
				status, orderByComparator);

		if (corpMembershipRequest != null) {
			return corpMembershipRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpEntryId=");
		msg.append(corpEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest fetchByCEI_S_Last(long corpEntryId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByCEI_S(corpEntryId, status);

		List<CorpMembershipRequest> list = findByCEI_S(corpEntryId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp membership requests before and after the current corp membership request in the ordered set where corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpMembershipRequestId the primary key of the current corp membership request
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest[] findByCEI_S_PrevAndNext(
		long corpMembershipRequestId, long corpEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = findByPrimaryKey(corpMembershipRequestId);

		Session session = null;

		try {
			session = openSession();

			CorpMembershipRequest[] array = new CorpMembershipRequestImpl[3];

			array[0] = getByCEI_S_PrevAndNext(session, corpMembershipRequest,
					corpEntryId, status, orderByComparator, true);

			array[1] = corpMembershipRequest;

			array[2] = getByCEI_S_PrevAndNext(session, corpMembershipRequest,
					corpEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpMembershipRequest getByCEI_S_PrevAndNext(Session session,
		CorpMembershipRequest corpMembershipRequest, long corpEntryId,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_CEI_S_CORPENTRYID_2);

		query.append(_FINDER_COLUMN_CEI_S_STATUS_2);

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

		qPos.add(corpEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpMembershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpMembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the corp membership requests where corpEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @return the matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByCEI_S(long corpEntryId,
		int[] statuses) throws SystemException {
		return findByCEI_S(corpEntryId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp membership requests where corpEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @return the range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByCEI_S(long corpEntryId,
		int[] statuses, int start, int end) throws SystemException {
		return findByCEI_S(corpEntryId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp membership requests where corpEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByCEI_S(long corpEntryId,
		int[] statuses, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CEI_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] { corpEntryId, StringUtil.merge(statuses) };
		}
		else {
			finderArgs = new Object[] {
					corpEntryId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<CorpMembershipRequest> list = (List<CorpMembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpMembershipRequest corpMembershipRequest : list) {
				if ((corpEntryId != corpMembershipRequest.getCorpEntryId()) ||
						!ArrayUtil.contains(statuses,
							corpMembershipRequest.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_CEI_S_CORPENTRYID_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_CEI_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

				qPos.add(corpEntryId);

				if (statuses != null) {
					qPos.add(statuses);
				}

				list = (List<CorpMembershipRequest>)QueryUtil.list(q,
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
	 * Returns all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @return the matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByU_CEI_S(long userId,
		long corpEntryId, int status) throws SystemException {
		return findByU_CEI_S(userId, corpEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @return the range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByU_CEI_S(long userId,
		long corpEntryId, int status, int start, int end)
		throws SystemException {
		return findByU_CEI_S(userId, corpEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByU_CEI_S(long userId,
		long corpEntryId, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI_S;
			finderArgs = new Object[] { userId, corpEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_CEI_S;
			finderArgs = new Object[] {
					userId, corpEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<CorpMembershipRequest> list = (List<CorpMembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpMembershipRequest corpMembershipRequest : list) {
				if ((userId != corpMembershipRequest.getUserId()) ||
						(corpEntryId != corpMembershipRequest.getCorpEntryId()) ||
						(status != corpMembershipRequest.getStatus())) {
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

			query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_U_CEI_S_USERID_2);

			query.append(_FINDER_COLUMN_U_CEI_S_CORPENTRYID_2);

			query.append(_FINDER_COLUMN_U_CEI_S_STATUS_2);

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

				qPos.add(corpEntryId);

				qPos.add(status);

				list = (List<CorpMembershipRequest>)QueryUtil.list(q,
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
	 * Returns the first corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest findByU_CEI_S_First(long userId,
		long corpEntryId, int status, OrderByComparator orderByComparator)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = fetchByU_CEI_S_First(userId,
				corpEntryId, status, orderByComparator);

		if (corpMembershipRequest != null) {
			return corpMembershipRequest;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", corpEntryId=");
		msg.append(corpEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the first corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest fetchByU_CEI_S_First(long userId,
		long corpEntryId, int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<CorpMembershipRequest> list = findByU_CEI_S(userId, corpEntryId,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest findByU_CEI_S_Last(long userId,
		long corpEntryId, int status, OrderByComparator orderByComparator)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = fetchByU_CEI_S_Last(userId,
				corpEntryId, status, orderByComparator);

		if (corpMembershipRequest != null) {
			return corpMembershipRequest;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", corpEntryId=");
		msg.append(corpEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpMembershipRequestException(msg.toString());
	}

	/**
	 * Returns the last corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp membership request, or <code>null</code> if a matching corp membership request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest fetchByU_CEI_S_Last(long userId,
		long corpEntryId, int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByU_CEI_S(userId, corpEntryId, status);

		List<CorpMembershipRequest> list = findByU_CEI_S(userId, corpEntryId,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp membership requests before and after the current corp membership request in the ordered set where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpMembershipRequestId the primary key of the current corp membership request
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp membership request
	 * @throws com.liferay.osb.NoSuchCorpMembershipRequestException if a corp membership request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest[] findByU_CEI_S_PrevAndNext(
		long corpMembershipRequestId, long userId, long corpEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = findByPrimaryKey(corpMembershipRequestId);

		Session session = null;

		try {
			session = openSession();

			CorpMembershipRequest[] array = new CorpMembershipRequestImpl[3];

			array[0] = getByU_CEI_S_PrevAndNext(session, corpMembershipRequest,
					userId, corpEntryId, status, orderByComparator, true);

			array[1] = corpMembershipRequest;

			array[2] = getByU_CEI_S_PrevAndNext(session, corpMembershipRequest,
					userId, corpEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpMembershipRequest getByU_CEI_S_PrevAndNext(Session session,
		CorpMembershipRequest corpMembershipRequest, long userId,
		long corpEntryId, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE);

		query.append(_FINDER_COLUMN_U_CEI_S_USERID_2);

		query.append(_FINDER_COLUMN_U_CEI_S_CORPENTRYID_2);

		query.append(_FINDER_COLUMN_U_CEI_S_STATUS_2);

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

		qPos.add(corpEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpMembershipRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpMembershipRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @return the matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByU_CEI_S(long userId,
		long corpEntryId, int[] statuses) throws SystemException {
		return findByU_CEI_S(userId, corpEntryId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @return the range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByU_CEI_S(long userId,
		long corpEntryId, int[] statuses, int start, int end)
		throws SystemException {
		return findByU_CEI_S(userId, corpEntryId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findByU_CEI_S(long userId,
		long corpEntryId, int[] statuses, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_CEI_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					userId, corpEntryId, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					userId, corpEntryId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<CorpMembershipRequest> list = (List<CorpMembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpMembershipRequest corpMembershipRequest : list) {
				if ((userId != corpMembershipRequest.getUserId()) ||
						(corpEntryId != corpMembershipRequest.getCorpEntryId()) ||
						!ArrayUtil.contains(statuses,
							corpMembershipRequest.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_CEI_S_USERID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_CEI_S_CORPENTRYID_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_U_CEI_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

				qPos.add(userId);

				qPos.add(corpEntryId);

				if (statuses != null) {
					qPos.add(statuses);
				}

				list = (List<CorpMembershipRequest>)QueryUtil.list(q,
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
	 * Returns all the corp membership requests.
	 *
	 * @return the corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp membership requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @return the range of corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp membership requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp membership requests
	 * @param end the upper bound of the range of corp membership requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpMembershipRequest> findAll(int start, int end,
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

		List<CorpMembershipRequest> list = (List<CorpMembershipRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CORPMEMBERSHIPREQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPMEMBERSHIPREQUEST;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CorpMembershipRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CorpMembershipRequest>)QueryUtil.list(q,
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
	 * Removes the corp membership request where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the corp membership request that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CorpMembershipRequest removeByKey(String key)
		throws NoSuchCorpMembershipRequestException, SystemException {
		CorpMembershipRequest corpMembershipRequest = findByKey(key);

		return remove(corpMembershipRequest);
	}

	/**
	 * Removes all the corp membership requests where corpEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCEI_S(long corpEntryId, int status)
		throws SystemException {
		for (CorpMembershipRequest corpMembershipRequest : findByCEI_S(
				corpEntryId, status)) {
			remove(corpMembershipRequest);
		}
	}

	/**
	 * Removes all the corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_CEI_S(long userId, long corpEntryId, int status)
		throws SystemException {
		for (CorpMembershipRequest corpMembershipRequest : findByU_CEI_S(
				userId, corpEntryId, status)) {
			remove(corpMembershipRequest);
		}
	}

	/**
	 * Removes all the corp membership requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CorpMembershipRequest corpMembershipRequest : findAll()) {
			remove(corpMembershipRequest);
		}
	}

	/**
	 * Returns the number of corp membership requests where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKey(String key) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPMEMBERSHIPREQUEST_WHERE);

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
	 * Returns the number of corp membership requests where corpEntryId = &#63; and status = &#63;.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @return the number of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCEI_S(long corpEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { corpEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CORPMEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_CEI_S_CORPENTRYID_2);

			query.append(_FINDER_COLUMN_CEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp membership requests where corpEntryId = &#63; and status = any &#63;.
	 *
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @return the number of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCEI_S(long corpEntryId, int[] statuses)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				corpEntryId, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_CEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_CORPMEMBERSHIPREQUEST_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_CEI_S_CORPENTRYID_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_CEI_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

				qPos.add(corpEntryId);

				if (statuses != null) {
					qPos.add(statuses);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_CEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp membership requests where userId = &#63; and corpEntryId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param status the status
	 * @return the number of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_CEI_S(long userId, long corpEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, corpEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_CEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CORPMEMBERSHIPREQUEST_WHERE);

			query.append(_FINDER_COLUMN_U_CEI_S_USERID_2);

			query.append(_FINDER_COLUMN_U_CEI_S_CORPENTRYID_2);

			query.append(_FINDER_COLUMN_U_CEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(corpEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_CEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp membership requests where userId = &#63; and corpEntryId = &#63; and status = any &#63;.
	 *
	 * @param userId the user ID
	 * @param corpEntryId the corp entry ID
	 * @param statuses the statuses
	 * @return the number of matching corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_CEI_S(long userId, long corpEntryId, int[] statuses)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, corpEntryId, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_CEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_CORPMEMBERSHIPREQUEST_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_CEI_S_USERID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_U_CEI_S_CORPENTRYID_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_U_CEI_S_STATUS_5);

					if ((i + 1) < statuses.length) {
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

				qPos.add(userId);

				qPos.add(corpEntryId);

				if (statuses != null) {
					qPos.add(statuses);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_CEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp membership requests.
	 *
	 * @return the number of corp membership requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPMEMBERSHIPREQUEST);

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
	 * Initializes the corp membership request persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.CorpMembershipRequest")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CorpMembershipRequest>> listenersList = new ArrayList<ModelListener<CorpMembershipRequest>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<CorpMembershipRequest>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CorpMembershipRequestImpl.class.getName());
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
	private static final String _SQL_SELECT_CORPMEMBERSHIPREQUEST = "SELECT corpMembershipRequest FROM CorpMembershipRequest corpMembershipRequest";
	private static final String _SQL_SELECT_CORPMEMBERSHIPREQUEST_WHERE = "SELECT corpMembershipRequest FROM CorpMembershipRequest corpMembershipRequest WHERE ";
	private static final String _SQL_COUNT_CORPMEMBERSHIPREQUEST = "SELECT COUNT(corpMembershipRequest) FROM CorpMembershipRequest corpMembershipRequest";
	private static final String _SQL_COUNT_CORPMEMBERSHIPREQUEST_WHERE = "SELECT COUNT(corpMembershipRequest) FROM CorpMembershipRequest corpMembershipRequest WHERE ";
	private static final String _FINDER_COLUMN_KEY_KEY_1 = "corpMembershipRequest.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "corpMembershipRequest.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(corpMembershipRequest.key IS NULL OR corpMembershipRequest.key = ?)";
	private static final String _FINDER_COLUMN_CEI_S_CORPENTRYID_2 = "corpMembershipRequest.corpEntryId = ? AND ";
	private static final String _FINDER_COLUMN_CEI_S_CORPENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_CEI_S_CORPENTRYID_2) + ")";
	private static final String _FINDER_COLUMN_CEI_S_STATUS_2 = "corpMembershipRequest.status = ?";
	private static final String _FINDER_COLUMN_CEI_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_CEI_S_STATUS_2) + ")";
	private static final String _FINDER_COLUMN_U_CEI_S_USERID_2 = "corpMembershipRequest.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_CEI_S_USERID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_CEI_S_USERID_2) + ")";
	private static final String _FINDER_COLUMN_U_CEI_S_CORPENTRYID_2 = "corpMembershipRequest.corpEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_CEI_S_CORPENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_CEI_S_CORPENTRYID_2) + ")";
	private static final String _FINDER_COLUMN_U_CEI_S_STATUS_2 = "corpMembershipRequest.status = ?";
	private static final String _FINDER_COLUMN_U_CEI_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_CEI_S_STATUS_2) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "corpMembershipRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpMembershipRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CorpMembershipRequest exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CorpMembershipRequestPersistenceImpl.class);
	private static CorpMembershipRequest _nullCorpMembershipRequest = new CorpMembershipRequestImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CorpMembershipRequest> toCacheModel() {
				return _nullCorpMembershipRequestCacheModel;
			}
		};

	private static CacheModel<CorpMembershipRequest> _nullCorpMembershipRequestCacheModel =
		new CacheModel<CorpMembershipRequest>() {
			public CorpMembershipRequest toEntityModel() {
				return _nullCorpMembershipRequest;
			}
		};
}
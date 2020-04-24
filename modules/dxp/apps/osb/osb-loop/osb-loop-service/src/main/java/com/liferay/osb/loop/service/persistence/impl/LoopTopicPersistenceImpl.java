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

package com.liferay.osb.loop.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopTopicException;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.impl.LoopTopicImpl;
import com.liferay.osb.loop.model.impl.LoopTopicModelImpl;
import com.liferay.osb.loop.service.persistence.LoopTopicPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the loop topic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopicPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopTopicUtil
 * @generated
 */
@ProviderType
public class LoopTopicPersistenceImpl extends BasePersistenceImpl<LoopTopic>
	implements LoopTopicPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopTopicUtil} to access the loop topic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopTopicImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicModelImpl.FINDER_CACHE_ENABLED, LoopTopicImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicModelImpl.FINDER_CACHE_ENABLED, LoopTopicImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N = new FinderPath(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicModelImpl.FINDER_CACHE_ENABLED, LoopTopicImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_N",
			new String[] { Long.class.getName(), String.class.getName() },
			LoopTopicModelImpl.COMPANYID_COLUMN_BITMASK |
			LoopTopicModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N = new FinderPath(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or throws a {@link NoSuchLoopTopicException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching loop topic
	 * @throws NoSuchLoopTopicException if a matching loop topic could not be found
	 */
	@Override
	public LoopTopic findByC_N(long companyId, String name)
		throws NoSuchLoopTopicException {
		LoopTopic loopTopic = fetchByC_N(companyId, name);

		if (loopTopic == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopTopicException(msg.toString());
		}

		return loopTopic;
	}

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching loop topic, or <code>null</code> if a matching loop topic could not be found
	 */
	@Override
	public LoopTopic fetchByC_N(long companyId, String name) {
		return fetchByC_N(companyId, name, true);
	}

	/**
	 * Returns the loop topic where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop topic, or <code>null</code> if a matching loop topic could not be found
	 */
	@Override
	public LoopTopic fetchByC_N(long companyId, String name,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_N,
					finderArgs, this);
		}

		if (result instanceof LoopTopic) {
			LoopTopic loopTopic = (LoopTopic)result;

			if ((companyId != loopTopic.getCompanyId()) ||
					!Objects.equals(name, loopTopic.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LOOPTOPIC_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				List<LoopTopic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_N, finderArgs,
						list);
				}
				else {
					LoopTopic loopTopic = list.get(0);

					result = loopTopic;

					cacheResult(loopTopic);

					if ((loopTopic.getCompanyId() != companyId) ||
							(loopTopic.getName() == null) ||
							!loopTopic.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_N,
							finderArgs, loopTopic);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LoopTopic)result;
		}
	}

	/**
	 * Removes the loop topic where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the loop topic that was removed
	 */
	@Override
	public LoopTopic removeByC_N(long companyId, String name)
		throws NoSuchLoopTopicException {
		LoopTopic loopTopic = findByC_N(companyId, name);

		return remove(loopTopic);
	}

	/**
	 * Returns the number of loop topics where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching loop topics
	 */
	@Override
	public int countByC_N(long companyId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N;

		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOOPTOPIC_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 = "loopTopic.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_NAME_1 = "loopTopic.name IS NULL";
	private static final String _FINDER_COLUMN_C_N_NAME_2 = "loopTopic.name = ?";
	private static final String _FINDER_COLUMN_C_N_NAME_3 = "(loopTopic.name IS NULL OR loopTopic.name = '')";

	public LoopTopicPersistenceImpl() {
		setModelClass(LoopTopic.class);
	}

	/**
	 * Caches the loop topic in the entity cache if it is enabled.
	 *
	 * @param loopTopic the loop topic
	 */
	@Override
	public void cacheResult(LoopTopic loopTopic) {
		entityCache.putResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicImpl.class, loopTopic.getPrimaryKey(), loopTopic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_N,
			new Object[] { loopTopic.getCompanyId(), loopTopic.getName() },
			loopTopic);

		loopTopic.resetOriginalValues();
	}

	/**
	 * Caches the loop topics in the entity cache if it is enabled.
	 *
	 * @param loopTopics the loop topics
	 */
	@Override
	public void cacheResult(List<LoopTopic> loopTopics) {
		for (LoopTopic loopTopic : loopTopics) {
			if (entityCache.getResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
						LoopTopicImpl.class, loopTopic.getPrimaryKey()) == null) {
				cacheResult(loopTopic);
			}
			else {
				loopTopic.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop topics.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopTopicImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop topic.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopTopic loopTopic) {
		entityCache.removeResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicImpl.class, loopTopic.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopTopicModelImpl)loopTopic, true);
	}

	@Override
	public void clearCache(List<LoopTopic> loopTopics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopTopic loopTopic : loopTopics) {
			entityCache.removeResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
				LoopTopicImpl.class, loopTopic.getPrimaryKey());

			clearUniqueFindersCache((LoopTopicModelImpl)loopTopic, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopTopicModelImpl loopTopicModelImpl) {
		Object[] args = new Object[] {
				loopTopicModelImpl.getCompanyId(), loopTopicModelImpl.getName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_N, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_N, args,
			loopTopicModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopTopicModelImpl loopTopicModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					loopTopicModelImpl.getCompanyId(),
					loopTopicModelImpl.getName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N, args);
		}

		if ((loopTopicModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_N.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopTopicModelImpl.getOriginalCompanyId(),
					loopTopicModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N, args);
		}
	}

	/**
	 * Creates a new loop topic with the primary key. Does not add the loop topic to the database.
	 *
	 * @param loopTopicId the primary key for the new loop topic
	 * @return the new loop topic
	 */
	@Override
	public LoopTopic create(long loopTopicId) {
		LoopTopic loopTopic = new LoopTopicImpl();

		loopTopic.setNew(true);
		loopTopic.setPrimaryKey(loopTopicId);

		loopTopic.setCompanyId(companyProvider.getCompanyId());

		return loopTopic;
	}

	/**
	 * Removes the loop topic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic that was removed
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	@Override
	public LoopTopic remove(long loopTopicId) throws NoSuchLoopTopicException {
		return remove((Serializable)loopTopicId);
	}

	/**
	 * Removes the loop topic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop topic
	 * @return the loop topic that was removed
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	@Override
	public LoopTopic remove(Serializable primaryKey)
		throws NoSuchLoopTopicException {
		Session session = null;

		try {
			session = openSession();

			LoopTopic loopTopic = (LoopTopic)session.get(LoopTopicImpl.class,
					primaryKey);

			if (loopTopic == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopTopicException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopTopic);
		}
		catch (NoSuchLoopTopicException nsee) {
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
	protected LoopTopic removeImpl(LoopTopic loopTopic) {
		loopTopic = toUnwrappedModel(loopTopic);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopTopic)) {
				loopTopic = (LoopTopic)session.get(LoopTopicImpl.class,
						loopTopic.getPrimaryKeyObj());
			}

			if (loopTopic != null) {
				session.delete(loopTopic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopTopic != null) {
			clearCache(loopTopic);
		}

		return loopTopic;
	}

	@Override
	public LoopTopic updateImpl(LoopTopic loopTopic) {
		loopTopic = toUnwrappedModel(loopTopic);

		boolean isNew = loopTopic.isNew();

		LoopTopicModelImpl loopTopicModelImpl = (LoopTopicModelImpl)loopTopic;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (loopTopic.getCreateDate() == null)) {
			if (serviceContext == null) {
				loopTopic.setCreateDate(now);
			}
			else {
				loopTopic.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!loopTopicModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				loopTopic.setModifiedDate(now);
			}
			else {
				loopTopic.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (loopTopic.isNew()) {
				session.save(loopTopic);

				loopTopic.setNew(false);
			}
			else {
				loopTopic = (LoopTopic)session.merge(loopTopic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopTopicModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicImpl.class, loopTopic.getPrimaryKey(), loopTopic, false);

		clearUniqueFindersCache(loopTopicModelImpl, false);
		cacheUniqueFindersCache(loopTopicModelImpl);

		loopTopic.resetOriginalValues();

		return loopTopic;
	}

	protected LoopTopic toUnwrappedModel(LoopTopic loopTopic) {
		if (loopTopic instanceof LoopTopicImpl) {
			return loopTopic;
		}

		LoopTopicImpl loopTopicImpl = new LoopTopicImpl();

		loopTopicImpl.setNew(loopTopic.isNew());
		loopTopicImpl.setPrimaryKey(loopTopic.getPrimaryKey());

		loopTopicImpl.setLoopTopicId(loopTopic.getLoopTopicId());
		loopTopicImpl.setCompanyId(loopTopic.getCompanyId());
		loopTopicImpl.setUserId(loopTopic.getUserId());
		loopTopicImpl.setUserName(loopTopic.getUserName());
		loopTopicImpl.setCreateDate(loopTopic.getCreateDate());
		loopTopicImpl.setModifiedDate(loopTopic.getModifiedDate());
		loopTopicImpl.setParentLoopTopicId(loopTopic.getParentLoopTopicId());
		loopTopicImpl.setName(loopTopic.getName());
		loopTopicImpl.setDescription(loopTopic.getDescription());
		loopTopicImpl.setImagePayload(loopTopic.getImagePayload());
		loopTopicImpl.setMergeTime(loopTopic.getMergeTime());

		return loopTopicImpl;
	}

	/**
	 * Returns the loop topic with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop topic
	 * @return the loop topic
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	@Override
	public LoopTopic findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopTopicException {
		LoopTopic loopTopic = fetchByPrimaryKey(primaryKey);

		if (loopTopic == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopTopicException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopTopic;
	}

	/**
	 * Returns the loop topic with the primary key or throws a {@link NoSuchLoopTopicException} if it could not be found.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic
	 * @throws NoSuchLoopTopicException if a loop topic with the primary key could not be found
	 */
	@Override
	public LoopTopic findByPrimaryKey(long loopTopicId)
		throws NoSuchLoopTopicException {
		return findByPrimaryKey((Serializable)loopTopicId);
	}

	/**
	 * Returns the loop topic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop topic
	 * @return the loop topic, or <code>null</code> if a loop topic with the primary key could not be found
	 */
	@Override
	public LoopTopic fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
				LoopTopicImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopTopic loopTopic = (LoopTopic)serializable;

		if (loopTopic == null) {
			Session session = null;

			try {
				session = openSession();

				loopTopic = (LoopTopic)session.get(LoopTopicImpl.class,
						primaryKey);

				if (loopTopic != null) {
					cacheResult(loopTopic);
				}
				else {
					entityCache.putResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
						LoopTopicImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
					LoopTopicImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopTopic;
	}

	/**
	 * Returns the loop topic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopTopicId the primary key of the loop topic
	 * @return the loop topic, or <code>null</code> if a loop topic with the primary key could not be found
	 */
	@Override
	public LoopTopic fetchByPrimaryKey(long loopTopicId) {
		return fetchByPrimaryKey((Serializable)loopTopicId);
	}

	@Override
	public Map<Serializable, LoopTopic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopTopic> map = new HashMap<Serializable, LoopTopic>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopTopic loopTopic = fetchByPrimaryKey(primaryKey);

			if (loopTopic != null) {
				map.put(primaryKey, loopTopic);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
					LoopTopicImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopTopic)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPTOPIC_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (LoopTopic loopTopic : (List<LoopTopic>)q.list()) {
				map.put(loopTopic.getPrimaryKeyObj(), loopTopic);

				cacheResult(loopTopic);

				uncachedPrimaryKeys.remove(loopTopic.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopTopicModelImpl.ENTITY_CACHE_ENABLED,
					LoopTopicImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the loop topics.
	 *
	 * @return the loop topics
	 */
	@Override
	public List<LoopTopic> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @return the range of loop topics
	 */
	@Override
	public List<LoopTopic> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop topics
	 */
	@Override
	public List<LoopTopic> findAll(int start, int end,
		OrderByComparator<LoopTopic> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop topics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topics
	 * @param end the upper bound of the range of loop topics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop topics
	 */
	@Override
	public List<LoopTopic> findAll(int start, int end,
		OrderByComparator<LoopTopic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<LoopTopic> list = null;

		if (retrieveFromCache) {
			list = (List<LoopTopic>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPTOPIC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPTOPIC;

				if (pagination) {
					sql = sql.concat(LoopTopicModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopTopic>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopTopic>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the loop topics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopTopic loopTopic : findAll()) {
			remove(loopTopic);
		}
	}

	/**
	 * Returns the number of loop topics.
	 *
	 * @return the number of loop topics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPTOPIC);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LoopTopicModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop topic persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopTopicImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPTOPIC = "SELECT loopTopic FROM LoopTopic loopTopic";
	private static final String _SQL_SELECT_LOOPTOPIC_WHERE_PKS_IN = "SELECT loopTopic FROM LoopTopic loopTopic WHERE loopTopicId IN (";
	private static final String _SQL_SELECT_LOOPTOPIC_WHERE = "SELECT loopTopic FROM LoopTopic loopTopic WHERE ";
	private static final String _SQL_COUNT_LOOPTOPIC = "SELECT COUNT(loopTopic) FROM LoopTopic loopTopic";
	private static final String _SQL_COUNT_LOOPTOPIC_WHERE = "SELECT COUNT(loopTopic) FROM LoopTopic loopTopic WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopTopic.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopTopic exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopTopic exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopTopicPersistenceImpl.class);
}
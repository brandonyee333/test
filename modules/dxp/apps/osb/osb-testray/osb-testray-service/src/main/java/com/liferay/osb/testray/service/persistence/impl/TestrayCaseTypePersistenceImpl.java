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

package com.liferay.osb.testray.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayCaseTypeException;
import com.liferay.osb.testray.model.TestrayCaseType;
import com.liferay.osb.testray.model.impl.TestrayCaseTypeImpl;
import com.liferay.osb.testray.model.impl.TestrayCaseTypeModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayCaseTypePersistence;
import com.liferay.osb.testray.service.persistence.TestrayTaskPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
 * The persistence implementation for the testray case type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseTypePersistence
 * @see com.liferay.osb.testray.service.persistence.TestrayCaseTypeUtil
 * @generated
 */
@ProviderType
public class TestrayCaseTypePersistenceImpl extends BasePersistenceImpl<TestrayCaseType>
	implements TestrayCaseTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TestrayCaseTypeUtil} to access the testray case type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TestrayCaseTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeModelImpl.FINDER_CACHE_ENABLED,
			TestrayCaseTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeModelImpl.FINDER_CACHE_ENABLED,
			TestrayCaseTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_GI_N = new FinderPath(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeModelImpl.FINDER_CACHE_ENABLED,
			TestrayCaseTypeImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByGI_N",
			new String[] { Long.class.getName(), String.class.getName() },
			TestrayCaseTypeModelImpl.GROUPID_COLUMN_BITMASK |
			TestrayCaseTypeModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GI_N = new FinderPath(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGI_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or throws a {@link NoSuchTestrayCaseTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray case type
	 * @throws NoSuchTestrayCaseTypeException if a matching testray case type could not be found
	 */
	@Override
	public TestrayCaseType findByGI_N(long groupId, String name)
		throws NoSuchTestrayCaseTypeException {
		TestrayCaseType testrayCaseType = fetchByGI_N(groupId, name);

		if (testrayCaseType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTestrayCaseTypeException(msg.toString());
		}

		return testrayCaseType;
	}

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching testray case type, or <code>null</code> if a matching testray case type could not be found
	 */
	@Override
	public TestrayCaseType fetchByGI_N(long groupId, String name) {
		return fetchByGI_N(groupId, name, true);
	}

	/**
	 * Returns the testray case type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray case type, or <code>null</code> if a matching testray case type could not be found
	 */
	@Override
	public TestrayCaseType fetchByGI_N(long groupId, String name,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GI_N,
					finderArgs, this);
		}

		if (result instanceof TestrayCaseType) {
			TestrayCaseType testrayCaseType = (TestrayCaseType)result;

			if ((groupId != testrayCaseType.getGroupId()) ||
					!Objects.equals(name, testrayCaseType.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TESTRAYCASETYPE_WHERE);

			query.append(_FINDER_COLUMN_GI_N_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_GI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_GI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				List<TestrayCaseType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GI_N,
						finderArgs, list);
				}
				else {
					TestrayCaseType testrayCaseType = list.get(0);

					result = testrayCaseType;

					cacheResult(testrayCaseType);

					if ((testrayCaseType.getGroupId() != groupId) ||
							(testrayCaseType.getName() == null) ||
							!testrayCaseType.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_GI_N,
							finderArgs, testrayCaseType);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GI_N, finderArgs);

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
			return (TestrayCaseType)result;
		}
	}

	/**
	 * Removes the testray case type where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the testray case type that was removed
	 */
	@Override
	public TestrayCaseType removeByGI_N(long groupId, String name)
		throws NoSuchTestrayCaseTypeException {
		TestrayCaseType testrayCaseType = findByGI_N(groupId, name);

		return remove(testrayCaseType);
	}

	/**
	 * Returns the number of testray case types where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching testray case types
	 */
	@Override
	public int countByGI_N(long groupId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GI_N;

		Object[] finderArgs = new Object[] { groupId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TESTRAYCASETYPE_WHERE);

			query.append(_FINDER_COLUMN_GI_N_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_GI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_GI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GI_N_GROUPID_2 = "testrayCaseType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GI_N_NAME_1 = "testrayCaseType.name IS NULL";
	private static final String _FINDER_COLUMN_GI_N_NAME_2 = "testrayCaseType.name = ?";
	private static final String _FINDER_COLUMN_GI_N_NAME_3 = "(testrayCaseType.name IS NULL OR testrayCaseType.name = '')";

	public TestrayCaseTypePersistenceImpl() {
		setModelClass(TestrayCaseType.class);
	}

	/**
	 * Caches the testray case type in the entity cache if it is enabled.
	 *
	 * @param testrayCaseType the testray case type
	 */
	@Override
	public void cacheResult(TestrayCaseType testrayCaseType) {
		entityCache.putResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeImpl.class, testrayCaseType.getPrimaryKey(),
			testrayCaseType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GI_N,
			new Object[] { testrayCaseType.getGroupId(), testrayCaseType.getName() },
			testrayCaseType);

		testrayCaseType.resetOriginalValues();
	}

	/**
	 * Caches the testray case types in the entity cache if it is enabled.
	 *
	 * @param testrayCaseTypes the testray case types
	 */
	@Override
	public void cacheResult(List<TestrayCaseType> testrayCaseTypes) {
		for (TestrayCaseType testrayCaseType : testrayCaseTypes) {
			if (entityCache.getResult(
						TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
						TestrayCaseTypeImpl.class,
						testrayCaseType.getPrimaryKey()) == null) {
				cacheResult(testrayCaseType);
			}
			else {
				testrayCaseType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray case types.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayCaseTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray case type.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayCaseType testrayCaseType) {
		entityCache.removeResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeImpl.class, testrayCaseType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TestrayCaseTypeModelImpl)testrayCaseType, true);
	}

	@Override
	public void clearCache(List<TestrayCaseType> testrayCaseTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayCaseType testrayCaseType : testrayCaseTypes) {
			entityCache.removeResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
				TestrayCaseTypeImpl.class, testrayCaseType.getPrimaryKey());

			clearUniqueFindersCache((TestrayCaseTypeModelImpl)testrayCaseType,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		TestrayCaseTypeModelImpl testrayCaseTypeModelImpl) {
		Object[] args = new Object[] {
				testrayCaseTypeModelImpl.getGroupId(),
				testrayCaseTypeModelImpl.getName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GI_N, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GI_N, args,
			testrayCaseTypeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TestrayCaseTypeModelImpl testrayCaseTypeModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					testrayCaseTypeModelImpl.getGroupId(),
					testrayCaseTypeModelImpl.getName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GI_N, args);
		}

		if ((testrayCaseTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GI_N.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					testrayCaseTypeModelImpl.getOriginalGroupId(),
					testrayCaseTypeModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GI_N, args);
		}
	}

	/**
	 * Creates a new testray case type with the primary key. Does not add the testray case type to the database.
	 *
	 * @param testrayCaseTypeId the primary key for the new testray case type
	 * @return the new testray case type
	 */
	@Override
	public TestrayCaseType create(long testrayCaseTypeId) {
		TestrayCaseType testrayCaseType = new TestrayCaseTypeImpl();

		testrayCaseType.setNew(true);
		testrayCaseType.setPrimaryKey(testrayCaseTypeId);

		testrayCaseType.setCompanyId(companyProvider.getCompanyId());

		return testrayCaseType;
	}

	/**
	 * Removes the testray case type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type that was removed
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	@Override
	public TestrayCaseType remove(long testrayCaseTypeId)
		throws NoSuchTestrayCaseTypeException {
		return remove((Serializable)testrayCaseTypeId);
	}

	/**
	 * Removes the testray case type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray case type
	 * @return the testray case type that was removed
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	@Override
	public TestrayCaseType remove(Serializable primaryKey)
		throws NoSuchTestrayCaseTypeException {
		Session session = null;

		try {
			session = openSession();

			TestrayCaseType testrayCaseType = (TestrayCaseType)session.get(TestrayCaseTypeImpl.class,
					primaryKey);

			if (testrayCaseType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayCaseTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(testrayCaseType);
		}
		catch (NoSuchTestrayCaseTypeException nsee) {
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
	protected TestrayCaseType removeImpl(TestrayCaseType testrayCaseType) {
		testrayCaseType = toUnwrappedModel(testrayCaseType);

		testrayCaseTypeToTestrayTaskTableMapper.deleteLeftPrimaryKeyTableMappings(testrayCaseType.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayCaseType)) {
				testrayCaseType = (TestrayCaseType)session.get(TestrayCaseTypeImpl.class,
						testrayCaseType.getPrimaryKeyObj());
			}

			if (testrayCaseType != null) {
				session.delete(testrayCaseType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayCaseType != null) {
			clearCache(testrayCaseType);
		}

		return testrayCaseType;
	}

	@Override
	public TestrayCaseType updateImpl(TestrayCaseType testrayCaseType) {
		testrayCaseType = toUnwrappedModel(testrayCaseType);

		boolean isNew = testrayCaseType.isNew();

		TestrayCaseTypeModelImpl testrayCaseTypeModelImpl = (TestrayCaseTypeModelImpl)testrayCaseType;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayCaseType.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayCaseType.setCreateDate(now);
			}
			else {
				testrayCaseType.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!testrayCaseTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayCaseType.setModifiedDate(now);
			}
			else {
				testrayCaseType.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayCaseType.isNew()) {
				session.save(testrayCaseType);

				testrayCaseType.setNew(false);
			}
			else {
				testrayCaseType = (TestrayCaseType)session.merge(testrayCaseType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TestrayCaseTypeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
			TestrayCaseTypeImpl.class, testrayCaseType.getPrimaryKey(),
			testrayCaseType, false);

		clearUniqueFindersCache(testrayCaseTypeModelImpl, false);
		cacheUniqueFindersCache(testrayCaseTypeModelImpl);

		testrayCaseType.resetOriginalValues();

		return testrayCaseType;
	}

	protected TestrayCaseType toUnwrappedModel(TestrayCaseType testrayCaseType) {
		if (testrayCaseType instanceof TestrayCaseTypeImpl) {
			return testrayCaseType;
		}

		TestrayCaseTypeImpl testrayCaseTypeImpl = new TestrayCaseTypeImpl();

		testrayCaseTypeImpl.setNew(testrayCaseType.isNew());
		testrayCaseTypeImpl.setPrimaryKey(testrayCaseType.getPrimaryKey());

		testrayCaseTypeImpl.setTestrayCaseTypeId(testrayCaseType.getTestrayCaseTypeId());
		testrayCaseTypeImpl.setGroupId(testrayCaseType.getGroupId());
		testrayCaseTypeImpl.setCompanyId(testrayCaseType.getCompanyId());
		testrayCaseTypeImpl.setUserId(testrayCaseType.getUserId());
		testrayCaseTypeImpl.setUserName(testrayCaseType.getUserName());
		testrayCaseTypeImpl.setCreateDate(testrayCaseType.getCreateDate());
		testrayCaseTypeImpl.setModifiedDate(testrayCaseType.getModifiedDate());
		testrayCaseTypeImpl.setName(testrayCaseType.getName());

		return testrayCaseTypeImpl;
	}

	/**
	 * Returns the testray case type with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray case type
	 * @return the testray case type
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	@Override
	public TestrayCaseType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayCaseTypeException {
		TestrayCaseType testrayCaseType = fetchByPrimaryKey(primaryKey);

		if (testrayCaseType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayCaseTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return testrayCaseType;
	}

	/**
	 * Returns the testray case type with the primary key or throws a {@link NoSuchTestrayCaseTypeException} if it could not be found.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type
	 * @throws NoSuchTestrayCaseTypeException if a testray case type with the primary key could not be found
	 */
	@Override
	public TestrayCaseType findByPrimaryKey(long testrayCaseTypeId)
		throws NoSuchTestrayCaseTypeException {
		return findByPrimaryKey((Serializable)testrayCaseTypeId);
	}

	/**
	 * Returns the testray case type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray case type
	 * @return the testray case type, or <code>null</code> if a testray case type with the primary key could not be found
	 */
	@Override
	public TestrayCaseType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
				TestrayCaseTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayCaseType testrayCaseType = (TestrayCaseType)serializable;

		if (testrayCaseType == null) {
			Session session = null;

			try {
				session = openSession();

				testrayCaseType = (TestrayCaseType)session.get(TestrayCaseTypeImpl.class,
						primaryKey);

				if (testrayCaseType != null) {
					cacheResult(testrayCaseType);
				}
				else {
					entityCache.putResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
						TestrayCaseTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
					TestrayCaseTypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayCaseType;
	}

	/**
	 * Returns the testray case type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type, or <code>null</code> if a testray case type with the primary key could not be found
	 */
	@Override
	public TestrayCaseType fetchByPrimaryKey(long testrayCaseTypeId) {
		return fetchByPrimaryKey((Serializable)testrayCaseTypeId);
	}

	@Override
	public Map<Serializable, TestrayCaseType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayCaseType> map = new HashMap<Serializable, TestrayCaseType>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayCaseType testrayCaseType = fetchByPrimaryKey(primaryKey);

			if (testrayCaseType != null) {
				map.put(primaryKey, testrayCaseType);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
					TestrayCaseTypeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayCaseType)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TESTRAYCASETYPE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (TestrayCaseType testrayCaseType : (List<TestrayCaseType>)q.list()) {
				map.put(testrayCaseType.getPrimaryKeyObj(), testrayCaseType);

				cacheResult(testrayCaseType);

				uncachedPrimaryKeys.remove(testrayCaseType.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TestrayCaseTypeModelImpl.ENTITY_CACHE_ENABLED,
					TestrayCaseTypeImpl.class, primaryKey, nullModel);
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
	 * Returns all the testray case types.
	 *
	 * @return the testray case types
	 */
	@Override
	public List<TestrayCaseType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @return the range of testray case types
	 */
	@Override
	public List<TestrayCaseType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray case types
	 */
	@Override
	public List<TestrayCaseType> findAll(int start, int end,
		OrderByComparator<TestrayCaseType> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray case types
	 */
	@Override
	public List<TestrayCaseType> findAll(int start, int end,
		OrderByComparator<TestrayCaseType> orderByComparator,
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

		List<TestrayCaseType> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayCaseType>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYCASETYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYCASETYPE;

				if (pagination) {
					sql = sql.concat(TestrayCaseTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayCaseType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayCaseType>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the testray case types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayCaseType testrayCaseType : findAll()) {
			remove(testrayCaseType);
		}
	}

	/**
	 * Returns the number of testray case types.
	 *
	 * @return the number of testray case types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYCASETYPE);

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

	/**
	 * Returns the primaryKeys of testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return long[] of the primaryKeys of testray tasks associated with the testray case type
	 */
	@Override
	public long[] getTestrayTaskPrimaryKeys(long pk) {
		long[] pks = testrayCaseTypeToTestrayTaskTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return the testray tasks associated with the testray case type
	 */
	@Override
	public List<com.liferay.osb.testray.model.TestrayTask> getTestrayTasks(
		long pk) {
		return getTestrayTasks(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the testray tasks associated with the testray case type.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case type
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @return the range of testray tasks associated with the testray case type
	 */
	@Override
	public List<com.liferay.osb.testray.model.TestrayTask> getTestrayTasks(
		long pk, int start, int end) {
		return getTestrayTasks(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray tasks associated with the testray case type.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayCaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray case type
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray tasks associated with the testray case type
	 */
	@Override
	public List<com.liferay.osb.testray.model.TestrayTask> getTestrayTasks(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.testray.model.TestrayTask> orderByComparator) {
		return testrayCaseTypeToTestrayTaskTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray tasks associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @return the number of testray tasks associated with the testray case type
	 */
	@Override
	public int getTestrayTasksSize(long pk) {
		long[] pks = testrayCaseTypeToTestrayTaskTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the testray task is associated with the testray case type.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 * @return <code>true</code> if the testray task is associated with the testray case type; <code>false</code> otherwise
	 */
	@Override
	public boolean containsTestrayTask(long pk, long testrayTaskPK) {
		return testrayCaseTypeToTestrayTaskTableMapper.containsTableMapping(pk,
			testrayTaskPK);
	}

	/**
	 * Returns <code>true</code> if the testray case type has any testray tasks associated with it.
	 *
	 * @param pk the primary key of the testray case type to check for associations with testray tasks
	 * @return <code>true</code> if the testray case type has any testray tasks associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsTestrayTasks(long pk) {
		if (getTestrayTasksSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 */
	@Override
	public void addTestrayTask(long pk, long testrayTaskPK) {
		TestrayCaseType testrayCaseType = fetchByPrimaryKey(pk);

		if (testrayCaseType == null) {
			testrayCaseTypeToTestrayTaskTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, testrayTaskPK);
		}
		else {
			testrayCaseTypeToTestrayTaskTableMapper.addTableMapping(testrayCaseType.getCompanyId(),
				pk, testrayTaskPK);
		}
	}

	/**
	 * Adds an association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTask the testray task
	 */
	@Override
	public void addTestrayTask(long pk,
		com.liferay.osb.testray.model.TestrayTask testrayTask) {
		TestrayCaseType testrayCaseType = fetchByPrimaryKey(pk);

		if (testrayCaseType == null) {
			testrayCaseTypeToTestrayTaskTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, testrayTask.getPrimaryKey());
		}
		else {
			testrayCaseTypeToTestrayTaskTableMapper.addTableMapping(testrayCaseType.getCompanyId(),
				pk, testrayTask.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks
	 */
	@Override
	public void addTestrayTasks(long pk, long[] testrayTaskPKs) {
		long companyId = 0;

		TestrayCaseType testrayCaseType = fetchByPrimaryKey(pk);

		if (testrayCaseType == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = testrayCaseType.getCompanyId();
		}

		testrayCaseTypeToTestrayTaskTableMapper.addTableMappings(companyId, pk,
			testrayTaskPKs);
	}

	/**
	 * Adds an association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks
	 */
	@Override
	public void addTestrayTasks(long pk,
		List<com.liferay.osb.testray.model.TestrayTask> testrayTasks) {
		addTestrayTasks(pk,
			ListUtil.toLongArray(testrayTasks,
				com.liferay.osb.testray.model.TestrayTask.TESTRAY_TASK_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the testray case type and its testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type to clear the associated testray tasks from
	 */
	@Override
	public void clearTestrayTasks(long pk) {
		testrayCaseTypeToTestrayTaskTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPK the primary key of the testray task
	 */
	@Override
	public void removeTestrayTask(long pk, long testrayTaskPK) {
		testrayCaseTypeToTestrayTaskTableMapper.deleteTableMapping(pk,
			testrayTaskPK);
	}

	/**
	 * Removes the association between the testray case type and the testray task. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTask the testray task
	 */
	@Override
	public void removeTestrayTask(long pk,
		com.liferay.osb.testray.model.TestrayTask testrayTask) {
		testrayCaseTypeToTestrayTaskTableMapper.deleteTableMapping(pk,
			testrayTask.getPrimaryKey());
	}

	/**
	 * Removes the association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks
	 */
	@Override
	public void removeTestrayTasks(long pk, long[] testrayTaskPKs) {
		testrayCaseTypeToTestrayTaskTableMapper.deleteTableMappings(pk,
			testrayTaskPKs);
	}

	/**
	 * Removes the association between the testray case type and the testray tasks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks
	 */
	@Override
	public void removeTestrayTasks(long pk,
		List<com.liferay.osb.testray.model.TestrayTask> testrayTasks) {
		removeTestrayTasks(pk,
			ListUtil.toLongArray(testrayTasks,
				com.liferay.osb.testray.model.TestrayTask.TESTRAY_TASK_ID_ACCESSOR));
	}

	/**
	 * Sets the testray tasks associated with the testray case type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTaskPKs the primary keys of the testray tasks to be associated with the testray case type
	 */
	@Override
	public void setTestrayTasks(long pk, long[] testrayTaskPKs) {
		Set<Long> newTestrayTaskPKsSet = SetUtil.fromArray(testrayTaskPKs);
		Set<Long> oldTestrayTaskPKsSet = SetUtil.fromArray(testrayCaseTypeToTestrayTaskTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeTestrayTaskPKsSet = new HashSet<Long>(oldTestrayTaskPKsSet);

		removeTestrayTaskPKsSet.removeAll(newTestrayTaskPKsSet);

		testrayCaseTypeToTestrayTaskTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeTestrayTaskPKsSet));

		newTestrayTaskPKsSet.removeAll(oldTestrayTaskPKsSet);

		long companyId = 0;

		TestrayCaseType testrayCaseType = fetchByPrimaryKey(pk);

		if (testrayCaseType == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = testrayCaseType.getCompanyId();
		}

		testrayCaseTypeToTestrayTaskTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newTestrayTaskPKsSet));
	}

	/**
	 * Sets the testray tasks associated with the testray case type, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray case type
	 * @param testrayTasks the testray tasks to be associated with the testray case type
	 */
	@Override
	public void setTestrayTasks(long pk,
		List<com.liferay.osb.testray.model.TestrayTask> testrayTasks) {
		try {
			long[] testrayTaskPKs = new long[testrayTasks.size()];

			for (int i = 0; i < testrayTasks.size(); i++) {
				com.liferay.osb.testray.model.TestrayTask testrayTask = testrayTasks.get(i);

				testrayTaskPKs[i] = testrayTask.getPrimaryKey();
			}

			setTestrayTasks(pk, testrayTaskPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TestrayCaseTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray case type persistence.
	 */
	public void afterPropertiesSet() {
		testrayCaseTypeToTestrayTaskTableMapper = TableMapperFactory.getTableMapper("OSB_TestrayTasks_TestrayCaseTypes",
				"companyId", "testrayCaseTypeId", "testrayTaskId", this,
				testrayTaskPersistence);
	}

	public void destroy() {
		entityCache.removeCache(TestrayCaseTypeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_TestrayTasks_TestrayCaseTypes");
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	@BeanReference(type = TestrayTaskPersistence.class)
	protected TestrayTaskPersistence testrayTaskPersistence;
	protected TableMapper<TestrayCaseType, com.liferay.osb.testray.model.TestrayTask> testrayCaseTypeToTestrayTaskTableMapper;
	private static final String _SQL_SELECT_TESTRAYCASETYPE = "SELECT testrayCaseType FROM TestrayCaseType testrayCaseType";
	private static final String _SQL_SELECT_TESTRAYCASETYPE_WHERE_PKS_IN = "SELECT testrayCaseType FROM TestrayCaseType testrayCaseType WHERE testrayCaseTypeId IN (";
	private static final String _SQL_SELECT_TESTRAYCASETYPE_WHERE = "SELECT testrayCaseType FROM TestrayCaseType testrayCaseType WHERE ";
	private static final String _SQL_COUNT_TESTRAYCASETYPE = "SELECT COUNT(testrayCaseType) FROM TestrayCaseType testrayCaseType";
	private static final String _SQL_COUNT_TESTRAYCASETYPE_WHERE = "SELECT COUNT(testrayCaseType) FROM TestrayCaseType testrayCaseType WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayCaseType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TestrayCaseType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TestrayCaseType exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TestrayCaseTypePersistenceImpl.class);
}
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

import com.liferay.osb.loop.exception.NoSuchLoopJobTitleException;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.impl.LoopJobTitleImpl;
import com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl;
import com.liferay.osb.loop.service.persistence.LoopJobTitlePersistence;

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
 * The persistence implementation for the loop job title service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopJobTitlePersistence
 * @see com.liferay.osb.loop.service.persistence.LoopJobTitleUtil
 * @generated
 */
@ProviderType
public class LoopJobTitlePersistenceImpl extends BasePersistenceImpl<LoopJobTitle>
	implements LoopJobTitlePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopJobTitleUtil} to access the loop job title persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopJobTitleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleModelImpl.FINDER_CACHE_ENABLED, LoopJobTitleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleModelImpl.FINDER_CACHE_ENABLED, LoopJobTitleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleModelImpl.FINDER_CACHE_ENABLED, LoopJobTitleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			LoopJobTitleModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the loop job title where name = &#63; or throws a {@link NoSuchLoopJobTitleException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching loop job title
	 * @throws NoSuchLoopJobTitleException if a matching loop job title could not be found
	 */
	@Override
	public LoopJobTitle findByName(String name)
		throws NoSuchLoopJobTitleException {
		LoopJobTitle loopJobTitle = fetchByName(name);

		if (loopJobTitle == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopJobTitleException(msg.toString());
		}

		return loopJobTitle;
	}

	/**
	 * Returns the loop job title where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching loop job title, or <code>null</code> if a matching loop job title could not be found
	 */
	@Override
	public LoopJobTitle fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the loop job title where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop job title, or <code>null</code> if a matching loop job title could not be found
	 */
	@Override
	public LoopJobTitle fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof LoopJobTitle) {
			LoopJobTitle loopJobTitle = (LoopJobTitle)result;

			if (!Objects.equals(name, loopJobTitle.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LOOPJOBTITLE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<LoopJobTitle> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					LoopJobTitle loopJobTitle = list.get(0);

					result = loopJobTitle;

					cacheResult(loopJobTitle);

					if ((loopJobTitle.getName() == null) ||
							!loopJobTitle.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, loopJobTitle);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, finderArgs);

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
			return (LoopJobTitle)result;
		}
	}

	/**
	 * Removes the loop job title where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the loop job title that was removed
	 */
	@Override
	public LoopJobTitle removeByName(String name)
		throws NoSuchLoopJobTitleException {
		LoopJobTitle loopJobTitle = findByName(name);

		return remove(loopJobTitle);
	}

	/**
	 * Returns the number of loop job titles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching loop job titles
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LOOPJOBTITLE_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "loopJobTitle.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "loopJobTitle.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(loopJobTitle.name IS NULL OR loopJobTitle.name = '')";

	public LoopJobTitlePersistenceImpl() {
		setModelClass(LoopJobTitle.class);
	}

	/**
	 * Caches the loop job title in the entity cache if it is enabled.
	 *
	 * @param loopJobTitle the loop job title
	 */
	@Override
	public void cacheResult(LoopJobTitle loopJobTitle) {
		entityCache.putResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleImpl.class, loopJobTitle.getPrimaryKey(), loopJobTitle);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { loopJobTitle.getName() }, loopJobTitle);

		loopJobTitle.resetOriginalValues();
	}

	/**
	 * Caches the loop job titles in the entity cache if it is enabled.
	 *
	 * @param loopJobTitles the loop job titles
	 */
	@Override
	public void cacheResult(List<LoopJobTitle> loopJobTitles) {
		for (LoopJobTitle loopJobTitle : loopJobTitles) {
			if (entityCache.getResult(
						LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
						LoopJobTitleImpl.class, loopJobTitle.getPrimaryKey()) == null) {
				cacheResult(loopJobTitle);
			}
			else {
				loopJobTitle.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop job titles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopJobTitleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop job title.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopJobTitle loopJobTitle) {
		entityCache.removeResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleImpl.class, loopJobTitle.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopJobTitleModelImpl)loopJobTitle, true);
	}

	@Override
	public void clearCache(List<LoopJobTitle> loopJobTitles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopJobTitle loopJobTitle : loopJobTitles) {
			entityCache.removeResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
				LoopJobTitleImpl.class, loopJobTitle.getPrimaryKey());

			clearUniqueFindersCache((LoopJobTitleModelImpl)loopJobTitle, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopJobTitleModelImpl loopJobTitleModelImpl) {
		Object[] args = new Object[] { loopJobTitleModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			loopJobTitleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopJobTitleModelImpl loopJobTitleModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { loopJobTitleModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((loopJobTitleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { loopJobTitleModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new loop job title with the primary key. Does not add the loop job title to the database.
	 *
	 * @param loopJobTitleId the primary key for the new loop job title
	 * @return the new loop job title
	 */
	@Override
	public LoopJobTitle create(long loopJobTitleId) {
		LoopJobTitle loopJobTitle = new LoopJobTitleImpl();

		loopJobTitle.setNew(true);
		loopJobTitle.setPrimaryKey(loopJobTitleId);

		loopJobTitle.setCompanyId(companyProvider.getCompanyId());

		return loopJobTitle;
	}

	/**
	 * Removes the loop job title with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title that was removed
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	@Override
	public LoopJobTitle remove(long loopJobTitleId)
		throws NoSuchLoopJobTitleException {
		return remove((Serializable)loopJobTitleId);
	}

	/**
	 * Removes the loop job title with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop job title
	 * @return the loop job title that was removed
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	@Override
	public LoopJobTitle remove(Serializable primaryKey)
		throws NoSuchLoopJobTitleException {
		Session session = null;

		try {
			session = openSession();

			LoopJobTitle loopJobTitle = (LoopJobTitle)session.get(LoopJobTitleImpl.class,
					primaryKey);

			if (loopJobTitle == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopJobTitleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopJobTitle);
		}
		catch (NoSuchLoopJobTitleException nsee) {
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
	protected LoopJobTitle removeImpl(LoopJobTitle loopJobTitle) {
		loopJobTitle = toUnwrappedModel(loopJobTitle);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopJobTitle)) {
				loopJobTitle = (LoopJobTitle)session.get(LoopJobTitleImpl.class,
						loopJobTitle.getPrimaryKeyObj());
			}

			if (loopJobTitle != null) {
				session.delete(loopJobTitle);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopJobTitle != null) {
			clearCache(loopJobTitle);
		}

		return loopJobTitle;
	}

	@Override
	public LoopJobTitle updateImpl(LoopJobTitle loopJobTitle) {
		loopJobTitle = toUnwrappedModel(loopJobTitle);

		boolean isNew = loopJobTitle.isNew();

		LoopJobTitleModelImpl loopJobTitleModelImpl = (LoopJobTitleModelImpl)loopJobTitle;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (loopJobTitle.getCreateDate() == null)) {
			if (serviceContext == null) {
				loopJobTitle.setCreateDate(now);
			}
			else {
				loopJobTitle.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!loopJobTitleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				loopJobTitle.setModifiedDate(now);
			}
			else {
				loopJobTitle.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (loopJobTitle.isNew()) {
				session.save(loopJobTitle);

				loopJobTitle.setNew(false);
			}
			else {
				loopJobTitle = (LoopJobTitle)session.merge(loopJobTitle);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopJobTitleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			LoopJobTitleImpl.class, loopJobTitle.getPrimaryKey(), loopJobTitle,
			false);

		clearUniqueFindersCache(loopJobTitleModelImpl, false);
		cacheUniqueFindersCache(loopJobTitleModelImpl);

		loopJobTitle.resetOriginalValues();

		return loopJobTitle;
	}

	protected LoopJobTitle toUnwrappedModel(LoopJobTitle loopJobTitle) {
		if (loopJobTitle instanceof LoopJobTitleImpl) {
			return loopJobTitle;
		}

		LoopJobTitleImpl loopJobTitleImpl = new LoopJobTitleImpl();

		loopJobTitleImpl.setNew(loopJobTitle.isNew());
		loopJobTitleImpl.setPrimaryKey(loopJobTitle.getPrimaryKey());

		loopJobTitleImpl.setLoopJobTitleId(loopJobTitle.getLoopJobTitleId());
		loopJobTitleImpl.setCompanyId(loopJobTitle.getCompanyId());
		loopJobTitleImpl.setUserId(loopJobTitle.getUserId());
		loopJobTitleImpl.setUserName(loopJobTitle.getUserName());
		loopJobTitleImpl.setCreateDate(loopJobTitle.getCreateDate());
		loopJobTitleImpl.setModifiedDate(loopJobTitle.getModifiedDate());
		loopJobTitleImpl.setName(loopJobTitle.getName());
		loopJobTitleImpl.setDescription(loopJobTitle.getDescription());
		loopJobTitleImpl.setStatus(loopJobTitle.getStatus());

		return loopJobTitleImpl;
	}

	/**
	 * Returns the loop job title with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop job title
	 * @return the loop job title
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	@Override
	public LoopJobTitle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopJobTitleException {
		LoopJobTitle loopJobTitle = fetchByPrimaryKey(primaryKey);

		if (loopJobTitle == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopJobTitleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopJobTitle;
	}

	/**
	 * Returns the loop job title with the primary key or throws a {@link NoSuchLoopJobTitleException} if it could not be found.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title
	 * @throws NoSuchLoopJobTitleException if a loop job title with the primary key could not be found
	 */
	@Override
	public LoopJobTitle findByPrimaryKey(long loopJobTitleId)
		throws NoSuchLoopJobTitleException {
		return findByPrimaryKey((Serializable)loopJobTitleId);
	}

	/**
	 * Returns the loop job title with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop job title
	 * @return the loop job title, or <code>null</code> if a loop job title with the primary key could not be found
	 */
	@Override
	public LoopJobTitle fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
				LoopJobTitleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopJobTitle loopJobTitle = (LoopJobTitle)serializable;

		if (loopJobTitle == null) {
			Session session = null;

			try {
				session = openSession();

				loopJobTitle = (LoopJobTitle)session.get(LoopJobTitleImpl.class,
						primaryKey);

				if (loopJobTitle != null) {
					cacheResult(loopJobTitle);
				}
				else {
					entityCache.putResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
						LoopJobTitleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
					LoopJobTitleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopJobTitle;
	}

	/**
	 * Returns the loop job title with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopJobTitleId the primary key of the loop job title
	 * @return the loop job title, or <code>null</code> if a loop job title with the primary key could not be found
	 */
	@Override
	public LoopJobTitle fetchByPrimaryKey(long loopJobTitleId) {
		return fetchByPrimaryKey((Serializable)loopJobTitleId);
	}

	@Override
	public Map<Serializable, LoopJobTitle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopJobTitle> map = new HashMap<Serializable, LoopJobTitle>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopJobTitle loopJobTitle = fetchByPrimaryKey(primaryKey);

			if (loopJobTitle != null) {
				map.put(primaryKey, loopJobTitle);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
					LoopJobTitleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopJobTitle)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPJOBTITLE_WHERE_PKS_IN);

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

			for (LoopJobTitle loopJobTitle : (List<LoopJobTitle>)q.list()) {
				map.put(loopJobTitle.getPrimaryKeyObj(), loopJobTitle);

				cacheResult(loopJobTitle);

				uncachedPrimaryKeys.remove(loopJobTitle.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopJobTitleModelImpl.ENTITY_CACHE_ENABLED,
					LoopJobTitleImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop job titles.
	 *
	 * @return the loop job titles
	 */
	@Override
	public List<LoopJobTitle> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopJobTitleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop job titles
	 * @param end the upper bound of the range of loop job titles (not inclusive)
	 * @return the range of loop job titles
	 */
	@Override
	public List<LoopJobTitle> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopJobTitleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop job titles
	 * @param end the upper bound of the range of loop job titles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop job titles
	 */
	@Override
	public List<LoopJobTitle> findAll(int start, int end,
		OrderByComparator<LoopJobTitle> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopJobTitleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop job titles
	 * @param end the upper bound of the range of loop job titles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop job titles
	 */
	@Override
	public List<LoopJobTitle> findAll(int start, int end,
		OrderByComparator<LoopJobTitle> orderByComparator,
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

		List<LoopJobTitle> list = null;

		if (retrieveFromCache) {
			list = (List<LoopJobTitle>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPJOBTITLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPJOBTITLE;

				if (pagination) {
					sql = sql.concat(LoopJobTitleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopJobTitle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopJobTitle>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the loop job titles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopJobTitle loopJobTitle : findAll()) {
			remove(loopJobTitle);
		}
	}

	/**
	 * Returns the number of loop job titles.
	 *
	 * @return the number of loop job titles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPJOBTITLE);

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
		return LoopJobTitleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop job title persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopJobTitleImpl.class.getName());
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
	private static final String _SQL_SELECT_LOOPJOBTITLE = "SELECT loopJobTitle FROM LoopJobTitle loopJobTitle";
	private static final String _SQL_SELECT_LOOPJOBTITLE_WHERE_PKS_IN = "SELECT loopJobTitle FROM LoopJobTitle loopJobTitle WHERE loopJobTitleId IN (";
	private static final String _SQL_SELECT_LOOPJOBTITLE_WHERE = "SELECT loopJobTitle FROM LoopJobTitle loopJobTitle WHERE ";
	private static final String _SQL_COUNT_LOOPJOBTITLE = "SELECT COUNT(loopJobTitle) FROM LoopJobTitle loopJobTitle";
	private static final String _SQL_COUNT_LOOPJOBTITLE_WHERE = "SELECT COUNT(loopJobTitle) FROM LoopJobTitle loopJobTitle WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopJobTitle.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopJobTitle exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopJobTitle exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopJobTitlePersistenceImpl.class);
}
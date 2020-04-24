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

import com.liferay.osb.loop.exception.NoSuchLoopPersonException;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.impl.LoopPersonImpl;
import com.liferay.osb.loop.model.impl.LoopPersonModelImpl;
import com.liferay.osb.loop.service.persistence.LoopPersonPersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the loop person service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPersonPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopPersonUtil
 * @generated
 */
@ProviderType
public class LoopPersonPersistenceImpl extends BasePersistenceImpl<LoopPerson>
	implements LoopPersonPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopPersonUtil} to access the loop person persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopPersonImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonModelImpl.FINDER_CACHE_ENABLED, LoopPersonImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonModelImpl.FINDER_CACHE_ENABLED, LoopPersonImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_PERSONUSERID = new FinderPath(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonModelImpl.FINDER_CACHE_ENABLED, LoopPersonImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPersonUserId",
			new String[] { Long.class.getName() },
			LoopPersonModelImpl.PERSONUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PERSONUSERID = new FinderPath(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPersonUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the loop person where personUserId = &#63; or throws a {@link NoSuchLoopPersonException} if it could not be found.
	 *
	 * @param personUserId the person user ID
	 * @return the matching loop person
	 * @throws NoSuchLoopPersonException if a matching loop person could not be found
	 */
	@Override
	public LoopPerson findByPersonUserId(long personUserId)
		throws NoSuchLoopPersonException {
		LoopPerson loopPerson = fetchByPersonUserId(personUserId);

		if (loopPerson == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("personUserId=");
			msg.append(personUserId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopPersonException(msg.toString());
		}

		return loopPerson;
	}

	/**
	 * Returns the loop person where personUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param personUserId the person user ID
	 * @return the matching loop person, or <code>null</code> if a matching loop person could not be found
	 */
	@Override
	public LoopPerson fetchByPersonUserId(long personUserId) {
		return fetchByPersonUserId(personUserId, true);
	}

	/**
	 * Returns the loop person where personUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param personUserId the person user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop person, or <code>null</code> if a matching loop person could not be found
	 */
	@Override
	public LoopPerson fetchByPersonUserId(long personUserId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { personUserId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PERSONUSERID,
					finderArgs, this);
		}

		if (result instanceof LoopPerson) {
			LoopPerson loopPerson = (LoopPerson)result;

			if ((personUserId != loopPerson.getPersonUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LOOPPERSON_WHERE);

			query.append(_FINDER_COLUMN_PERSONUSERID_PERSONUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(personUserId);

				List<LoopPerson> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PERSONUSERID,
						finderArgs, list);
				}
				else {
					LoopPerson loopPerson = list.get(0);

					result = loopPerson;

					cacheResult(loopPerson);

					if ((loopPerson.getPersonUserId() != personUserId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PERSONUSERID,
							finderArgs, loopPerson);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PERSONUSERID,
					finderArgs);

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
			return (LoopPerson)result;
		}
	}

	/**
	 * Removes the loop person where personUserId = &#63; from the database.
	 *
	 * @param personUserId the person user ID
	 * @return the loop person that was removed
	 */
	@Override
	public LoopPerson removeByPersonUserId(long personUserId)
		throws NoSuchLoopPersonException {
		LoopPerson loopPerson = findByPersonUserId(personUserId);

		return remove(loopPerson);
	}

	/**
	 * Returns the number of loop persons where personUserId = &#63;.
	 *
	 * @param personUserId the person user ID
	 * @return the number of matching loop persons
	 */
	@Override
	public int countByPersonUserId(long personUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PERSONUSERID;

		Object[] finderArgs = new Object[] { personUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LOOPPERSON_WHERE);

			query.append(_FINDER_COLUMN_PERSONUSERID_PERSONUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(personUserId);

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

	private static final String _FINDER_COLUMN_PERSONUSERID_PERSONUSERID_2 = "loopPerson.personUserId = ?";

	public LoopPersonPersistenceImpl() {
		setModelClass(LoopPerson.class);
	}

	/**
	 * Caches the loop person in the entity cache if it is enabled.
	 *
	 * @param loopPerson the loop person
	 */
	@Override
	public void cacheResult(LoopPerson loopPerson) {
		entityCache.putResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonImpl.class, loopPerson.getPrimaryKey(), loopPerson);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PERSONUSERID,
			new Object[] { loopPerson.getPersonUserId() }, loopPerson);

		loopPerson.resetOriginalValues();
	}

	/**
	 * Caches the loop persons in the entity cache if it is enabled.
	 *
	 * @param loopPersons the loop persons
	 */
	@Override
	public void cacheResult(List<LoopPerson> loopPersons) {
		for (LoopPerson loopPerson : loopPersons) {
			if (entityCache.getResult(
						LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
						LoopPersonImpl.class, loopPerson.getPrimaryKey()) == null) {
				cacheResult(loopPerson);
			}
			else {
				loopPerson.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop persons.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopPersonImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop person.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopPerson loopPerson) {
		entityCache.removeResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonImpl.class, loopPerson.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopPersonModelImpl)loopPerson, true);
	}

	@Override
	public void clearCache(List<LoopPerson> loopPersons) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopPerson loopPerson : loopPersons) {
			entityCache.removeResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
				LoopPersonImpl.class, loopPerson.getPrimaryKey());

			clearUniqueFindersCache((LoopPersonModelImpl)loopPerson, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopPersonModelImpl loopPersonModelImpl) {
		Object[] args = new Object[] { loopPersonModelImpl.getPersonUserId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_PERSONUSERID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PERSONUSERID, args,
			loopPersonModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopPersonModelImpl loopPersonModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { loopPersonModelImpl.getPersonUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PERSONUSERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PERSONUSERID, args);
		}

		if ((loopPersonModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PERSONUSERID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopPersonModelImpl.getOriginalPersonUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PERSONUSERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PERSONUSERID, args);
		}
	}

	/**
	 * Creates a new loop person with the primary key. Does not add the loop person to the database.
	 *
	 * @param loopPersonId the primary key for the new loop person
	 * @return the new loop person
	 */
	@Override
	public LoopPerson create(long loopPersonId) {
		LoopPerson loopPerson = new LoopPersonImpl();

		loopPerson.setNew(true);
		loopPerson.setPrimaryKey(loopPersonId);

		loopPerson.setCompanyId(companyProvider.getCompanyId());

		return loopPerson;
	}

	/**
	 * Removes the loop person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopPersonId the primary key of the loop person
	 * @return the loop person that was removed
	 * @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	 */
	@Override
	public LoopPerson remove(long loopPersonId)
		throws NoSuchLoopPersonException {
		return remove((Serializable)loopPersonId);
	}

	/**
	 * Removes the loop person with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop person
	 * @return the loop person that was removed
	 * @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	 */
	@Override
	public LoopPerson remove(Serializable primaryKey)
		throws NoSuchLoopPersonException {
		Session session = null;

		try {
			session = openSession();

			LoopPerson loopPerson = (LoopPerson)session.get(LoopPersonImpl.class,
					primaryKey);

			if (loopPerson == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopPersonException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopPerson);
		}
		catch (NoSuchLoopPersonException nsee) {
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
	protected LoopPerson removeImpl(LoopPerson loopPerson) {
		loopPerson = toUnwrappedModel(loopPerson);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopPerson)) {
				loopPerson = (LoopPerson)session.get(LoopPersonImpl.class,
						loopPerson.getPrimaryKeyObj());
			}

			if (loopPerson != null) {
				session.delete(loopPerson);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopPerson != null) {
			clearCache(loopPerson);
		}

		return loopPerson;
	}

	@Override
	public LoopPerson updateImpl(LoopPerson loopPerson) {
		loopPerson = toUnwrappedModel(loopPerson);

		boolean isNew = loopPerson.isNew();

		LoopPersonModelImpl loopPersonModelImpl = (LoopPersonModelImpl)loopPerson;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (loopPerson.getCreateDate() == null)) {
			if (serviceContext == null) {
				loopPerson.setCreateDate(now);
			}
			else {
				loopPerson.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!loopPersonModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				loopPerson.setModifiedDate(now);
			}
			else {
				loopPerson.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (loopPerson.isNew()) {
				session.save(loopPerson);

				loopPerson.setNew(false);
			}
			else {
				loopPerson = (LoopPerson)session.merge(loopPerson);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopPersonModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
			LoopPersonImpl.class, loopPerson.getPrimaryKey(), loopPerson, false);

		clearUniqueFindersCache(loopPersonModelImpl, false);
		cacheUniqueFindersCache(loopPersonModelImpl);

		loopPerson.resetOriginalValues();

		return loopPerson;
	}

	protected LoopPerson toUnwrappedModel(LoopPerson loopPerson) {
		if (loopPerson instanceof LoopPersonImpl) {
			return loopPerson;
		}

		LoopPersonImpl loopPersonImpl = new LoopPersonImpl();

		loopPersonImpl.setNew(loopPerson.isNew());
		loopPersonImpl.setPrimaryKey(loopPerson.getPrimaryKey());

		loopPersonImpl.setLoopPersonId(loopPerson.getLoopPersonId());
		loopPersonImpl.setCompanyId(loopPerson.getCompanyId());
		loopPersonImpl.setUserId(loopPerson.getUserId());
		loopPersonImpl.setUserName(loopPerson.getUserName());
		loopPersonImpl.setCreateDate(loopPerson.getCreateDate());
		loopPersonImpl.setModifiedDate(loopPerson.getModifiedDate());
		loopPersonImpl.setLoopJobTitleId(loopPerson.getLoopJobTitleId());
		loopPersonImpl.setManagerLoopPersonId(loopPerson.getManagerLoopPersonId());
		loopPersonImpl.setPersonUserId(loopPerson.getPersonUserId());
		loopPersonImpl.setExtraData(loopPerson.getExtraData());
		loopPersonImpl.setGroupedUserNotificationEventsCount(loopPerson.getGroupedUserNotificationEventsCount());
		loopPersonImpl.setImagePayload(loopPerson.getImagePayload());

		return loopPersonImpl;
	}

	/**
	 * Returns the loop person with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop person
	 * @return the loop person
	 * @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	 */
	@Override
	public LoopPerson findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopPersonException {
		LoopPerson loopPerson = fetchByPrimaryKey(primaryKey);

		if (loopPerson == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopPersonException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopPerson;
	}

	/**
	 * Returns the loop person with the primary key or throws a {@link NoSuchLoopPersonException} if it could not be found.
	 *
	 * @param loopPersonId the primary key of the loop person
	 * @return the loop person
	 * @throws NoSuchLoopPersonException if a loop person with the primary key could not be found
	 */
	@Override
	public LoopPerson findByPrimaryKey(long loopPersonId)
		throws NoSuchLoopPersonException {
		return findByPrimaryKey((Serializable)loopPersonId);
	}

	/**
	 * Returns the loop person with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop person
	 * @return the loop person, or <code>null</code> if a loop person with the primary key could not be found
	 */
	@Override
	public LoopPerson fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
				LoopPersonImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopPerson loopPerson = (LoopPerson)serializable;

		if (loopPerson == null) {
			Session session = null;

			try {
				session = openSession();

				loopPerson = (LoopPerson)session.get(LoopPersonImpl.class,
						primaryKey);

				if (loopPerson != null) {
					cacheResult(loopPerson);
				}
				else {
					entityCache.putResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
						LoopPersonImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
					LoopPersonImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopPerson;
	}

	/**
	 * Returns the loop person with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopPersonId the primary key of the loop person
	 * @return the loop person, or <code>null</code> if a loop person with the primary key could not be found
	 */
	@Override
	public LoopPerson fetchByPrimaryKey(long loopPersonId) {
		return fetchByPrimaryKey((Serializable)loopPersonId);
	}

	@Override
	public Map<Serializable, LoopPerson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopPerson> map = new HashMap<Serializable, LoopPerson>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopPerson loopPerson = fetchByPrimaryKey(primaryKey);

			if (loopPerson != null) {
				map.put(primaryKey, loopPerson);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
					LoopPersonImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopPerson)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPPERSON_WHERE_PKS_IN);

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

			for (LoopPerson loopPerson : (List<LoopPerson>)q.list()) {
				map.put(loopPerson.getPrimaryKeyObj(), loopPerson);

				cacheResult(loopPerson);

				uncachedPrimaryKeys.remove(loopPerson.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopPersonModelImpl.ENTITY_CACHE_ENABLED,
					LoopPersonImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop persons.
	 *
	 * @return the loop persons
	 */
	@Override
	public List<LoopPerson> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop persons
	 * @param end the upper bound of the range of loop persons (not inclusive)
	 * @return the range of loop persons
	 */
	@Override
	public List<LoopPerson> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop persons
	 * @param end the upper bound of the range of loop persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop persons
	 */
	@Override
	public List<LoopPerson> findAll(int start, int end,
		OrderByComparator<LoopPerson> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop persons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop persons
	 * @param end the upper bound of the range of loop persons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop persons
	 */
	@Override
	public List<LoopPerson> findAll(int start, int end,
		OrderByComparator<LoopPerson> orderByComparator,
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

		List<LoopPerson> list = null;

		if (retrieveFromCache) {
			list = (List<LoopPerson>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPPERSON);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPPERSON;

				if (pagination) {
					sql = sql.concat(LoopPersonModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopPerson>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopPerson>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the loop persons from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopPerson loopPerson : findAll()) {
			remove(loopPerson);
		}
	}

	/**
	 * Returns the number of loop persons.
	 *
	 * @return the number of loop persons
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPPERSON);

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
		return LoopPersonModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop person persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopPersonImpl.class.getName());
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
	private static final String _SQL_SELECT_LOOPPERSON = "SELECT loopPerson FROM LoopPerson loopPerson";
	private static final String _SQL_SELECT_LOOPPERSON_WHERE_PKS_IN = "SELECT loopPerson FROM LoopPerson loopPerson WHERE loopPersonId IN (";
	private static final String _SQL_SELECT_LOOPPERSON_WHERE = "SELECT loopPerson FROM LoopPerson loopPerson WHERE ";
	private static final String _SQL_COUNT_LOOPPERSON = "SELECT COUNT(loopPerson) FROM LoopPerson loopPerson";
	private static final String _SQL_COUNT_LOOPPERSON_WHERE = "SELECT COUNT(loopPerson) FROM LoopPerson loopPerson WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopPerson.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopPerson exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopPerson exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopPersonPersistenceImpl.class);
}
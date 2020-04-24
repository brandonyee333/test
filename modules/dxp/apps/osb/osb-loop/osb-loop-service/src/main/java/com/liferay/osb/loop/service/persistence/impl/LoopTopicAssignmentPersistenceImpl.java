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

import com.liferay.osb.loop.exception.NoSuchLoopTopicAssignmentException;
import com.liferay.osb.loop.model.LoopTopicAssignment;
import com.liferay.osb.loop.model.impl.LoopTopicAssignmentImpl;
import com.liferay.osb.loop.model.impl.LoopTopicAssignmentModelImpl;
import com.liferay.osb.loop.service.persistence.LoopTopicAssignmentPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the loop topic assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopicAssignmentPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopTopicAssignmentUtil
 * @generated
 */
@ProviderType
public class LoopTopicAssignmentPersistenceImpl extends BasePersistenceImpl<LoopTopicAssignment>
	implements LoopTopicAssignmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopTopicAssignmentUtil} to access the loop topic assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopTopicAssignmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicAssignmentModelImpl.FINDER_CACHE_ENABLED,
			LoopTopicAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicAssignmentModelImpl.FINDER_CACHE_ENABLED,
			LoopTopicAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public LoopTopicAssignmentPersistenceImpl() {
		setModelClass(LoopTopicAssignment.class);
	}

	/**
	 * Caches the loop topic assignment in the entity cache if it is enabled.
	 *
	 * @param loopTopicAssignment the loop topic assignment
	 */
	@Override
	public void cacheResult(LoopTopicAssignment loopTopicAssignment) {
		entityCache.putResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicAssignmentImpl.class, loopTopicAssignment.getPrimaryKey(),
			loopTopicAssignment);

		loopTopicAssignment.resetOriginalValues();
	}

	/**
	 * Caches the loop topic assignments in the entity cache if it is enabled.
	 *
	 * @param loopTopicAssignments the loop topic assignments
	 */
	@Override
	public void cacheResult(List<LoopTopicAssignment> loopTopicAssignments) {
		for (LoopTopicAssignment loopTopicAssignment : loopTopicAssignments) {
			if (entityCache.getResult(
						LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						LoopTopicAssignmentImpl.class,
						loopTopicAssignment.getPrimaryKey()) == null) {
				cacheResult(loopTopicAssignment);
			}
			else {
				loopTopicAssignment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop topic assignments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopTopicAssignmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop topic assignment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopTopicAssignment loopTopicAssignment) {
		entityCache.removeResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicAssignmentImpl.class, loopTopicAssignment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LoopTopicAssignment> loopTopicAssignments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopTopicAssignment loopTopicAssignment : loopTopicAssignments) {
			entityCache.removeResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				LoopTopicAssignmentImpl.class,
				loopTopicAssignment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new loop topic assignment with the primary key. Does not add the loop topic assignment to the database.
	 *
	 * @param loopTopicAssignmentId the primary key for the new loop topic assignment
	 * @return the new loop topic assignment
	 */
	@Override
	public LoopTopicAssignment create(long loopTopicAssignmentId) {
		LoopTopicAssignment loopTopicAssignment = new LoopTopicAssignmentImpl();

		loopTopicAssignment.setNew(true);
		loopTopicAssignment.setPrimaryKey(loopTopicAssignmentId);

		return loopTopicAssignment;
	}

	/**
	 * Removes the loop topic assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment that was removed
	 * @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public LoopTopicAssignment remove(long loopTopicAssignmentId)
		throws NoSuchLoopTopicAssignmentException {
		return remove((Serializable)loopTopicAssignmentId);
	}

	/**
	 * Removes the loop topic assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop topic assignment
	 * @return the loop topic assignment that was removed
	 * @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public LoopTopicAssignment remove(Serializable primaryKey)
		throws NoSuchLoopTopicAssignmentException {
		Session session = null;

		try {
			session = openSession();

			LoopTopicAssignment loopTopicAssignment = (LoopTopicAssignment)session.get(LoopTopicAssignmentImpl.class,
					primaryKey);

			if (loopTopicAssignment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopTopicAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopTopicAssignment);
		}
		catch (NoSuchLoopTopicAssignmentException nsee) {
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
	protected LoopTopicAssignment removeImpl(
		LoopTopicAssignment loopTopicAssignment) {
		loopTopicAssignment = toUnwrappedModel(loopTopicAssignment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopTopicAssignment)) {
				loopTopicAssignment = (LoopTopicAssignment)session.get(LoopTopicAssignmentImpl.class,
						loopTopicAssignment.getPrimaryKeyObj());
			}

			if (loopTopicAssignment != null) {
				session.delete(loopTopicAssignment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopTopicAssignment != null) {
			clearCache(loopTopicAssignment);
		}

		return loopTopicAssignment;
	}

	@Override
	public LoopTopicAssignment updateImpl(
		LoopTopicAssignment loopTopicAssignment) {
		loopTopicAssignment = toUnwrappedModel(loopTopicAssignment);

		boolean isNew = loopTopicAssignment.isNew();

		Session session = null;

		try {
			session = openSession();

			if (loopTopicAssignment.isNew()) {
				session.save(loopTopicAssignment);

				loopTopicAssignment.setNew(false);
			}
			else {
				loopTopicAssignment = (LoopTopicAssignment)session.merge(loopTopicAssignment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopTopicAssignmentImpl.class, loopTopicAssignment.getPrimaryKey(),
			loopTopicAssignment, false);

		loopTopicAssignment.resetOriginalValues();

		return loopTopicAssignment;
	}

	protected LoopTopicAssignment toUnwrappedModel(
		LoopTopicAssignment loopTopicAssignment) {
		if (loopTopicAssignment instanceof LoopTopicAssignmentImpl) {
			return loopTopicAssignment;
		}

		LoopTopicAssignmentImpl loopTopicAssignmentImpl = new LoopTopicAssignmentImpl();

		loopTopicAssignmentImpl.setNew(loopTopicAssignment.isNew());
		loopTopicAssignmentImpl.setPrimaryKey(loopTopicAssignment.getPrimaryKey());

		loopTopicAssignmentImpl.setLoopTopicAssignmentId(loopTopicAssignment.getLoopTopicAssignmentId());
		loopTopicAssignmentImpl.setLoopPersonId(loopTopicAssignment.getLoopPersonId());
		loopTopicAssignmentImpl.setLoopTopicId(loopTopicAssignment.getLoopTopicId());
		loopTopicAssignmentImpl.setStatus(loopTopicAssignment.getStatus());
		loopTopicAssignmentImpl.setStatusByUserId(loopTopicAssignment.getStatusByUserId());
		loopTopicAssignmentImpl.setStatusByUserName(loopTopicAssignment.getStatusByUserName());
		loopTopicAssignmentImpl.setStatusByDate(loopTopicAssignment.getStatusByDate());

		return loopTopicAssignmentImpl;
	}

	/**
	 * Returns the loop topic assignment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop topic assignment
	 * @return the loop topic assignment
	 * @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public LoopTopicAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopTopicAssignmentException {
		LoopTopicAssignment loopTopicAssignment = fetchByPrimaryKey(primaryKey);

		if (loopTopicAssignment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopTopicAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopTopicAssignment;
	}

	/**
	 * Returns the loop topic assignment with the primary key or throws a {@link NoSuchLoopTopicAssignmentException} if it could not be found.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment
	 * @throws NoSuchLoopTopicAssignmentException if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public LoopTopicAssignment findByPrimaryKey(long loopTopicAssignmentId)
		throws NoSuchLoopTopicAssignmentException {
		return findByPrimaryKey((Serializable)loopTopicAssignmentId);
	}

	/**
	 * Returns the loop topic assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop topic assignment
	 * @return the loop topic assignment, or <code>null</code> if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public LoopTopicAssignment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				LoopTopicAssignmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopTopicAssignment loopTopicAssignment = (LoopTopicAssignment)serializable;

		if (loopTopicAssignment == null) {
			Session session = null;

			try {
				session = openSession();

				loopTopicAssignment = (LoopTopicAssignment)session.get(LoopTopicAssignmentImpl.class,
						primaryKey);

				if (loopTopicAssignment != null) {
					cacheResult(loopTopicAssignment);
				}
				else {
					entityCache.putResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						LoopTopicAssignmentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					LoopTopicAssignmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopTopicAssignment;
	}

	/**
	 * Returns the loop topic assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment, or <code>null</code> if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public LoopTopicAssignment fetchByPrimaryKey(long loopTopicAssignmentId) {
		return fetchByPrimaryKey((Serializable)loopTopicAssignmentId);
	}

	@Override
	public Map<Serializable, LoopTopicAssignment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopTopicAssignment> map = new HashMap<Serializable, LoopTopicAssignment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopTopicAssignment loopTopicAssignment = fetchByPrimaryKey(primaryKey);

			if (loopTopicAssignment != null) {
				map.put(primaryKey, loopTopicAssignment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					LoopTopicAssignmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopTopicAssignment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPTOPICASSIGNMENT_WHERE_PKS_IN);

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

			for (LoopTopicAssignment loopTopicAssignment : (List<LoopTopicAssignment>)q.list()) {
				map.put(loopTopicAssignment.getPrimaryKeyObj(),
					loopTopicAssignment);

				cacheResult(loopTopicAssignment);

				uncachedPrimaryKeys.remove(loopTopicAssignment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopTopicAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					LoopTopicAssignmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop topic assignments.
	 *
	 * @return the loop topic assignments
	 */
	@Override
	public List<LoopTopicAssignment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop topic assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topic assignments
	 * @param end the upper bound of the range of loop topic assignments (not inclusive)
	 * @return the range of loop topic assignments
	 */
	@Override
	public List<LoopTopicAssignment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop topic assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topic assignments
	 * @param end the upper bound of the range of loop topic assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop topic assignments
	 */
	@Override
	public List<LoopTopicAssignment> findAll(int start, int end,
		OrderByComparator<LoopTopicAssignment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop topic assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopTopicAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topic assignments
	 * @param end the upper bound of the range of loop topic assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop topic assignments
	 */
	@Override
	public List<LoopTopicAssignment> findAll(int start, int end,
		OrderByComparator<LoopTopicAssignment> orderByComparator,
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

		List<LoopTopicAssignment> list = null;

		if (retrieveFromCache) {
			list = (List<LoopTopicAssignment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPTOPICASSIGNMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPTOPICASSIGNMENT;

				if (pagination) {
					sql = sql.concat(LoopTopicAssignmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopTopicAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopTopicAssignment>)QueryUtil.list(q,
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
	 * Removes all the loop topic assignments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopTopicAssignment loopTopicAssignment : findAll()) {
			remove(loopTopicAssignment);
		}
	}

	/**
	 * Returns the number of loop topic assignments.
	 *
	 * @return the number of loop topic assignments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPTOPICASSIGNMENT);

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
		return LoopTopicAssignmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop topic assignment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopTopicAssignmentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPTOPICASSIGNMENT = "SELECT loopTopicAssignment FROM LoopTopicAssignment loopTopicAssignment";
	private static final String _SQL_SELECT_LOOPTOPICASSIGNMENT_WHERE_PKS_IN = "SELECT loopTopicAssignment FROM LoopTopicAssignment loopTopicAssignment WHERE loopTopicAssignmentId IN (";
	private static final String _SQL_COUNT_LOOPTOPICASSIGNMENT = "SELECT COUNT(loopTopicAssignment) FROM LoopTopicAssignment loopTopicAssignment";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopTopicAssignment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopTopicAssignment exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LoopTopicAssignmentPersistenceImpl.class);
}
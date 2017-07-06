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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchCorpProjectException;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.impl.CorpProjectImpl;
import com.liferay.osb.model.impl.CorpProjectModelImpl;
import com.liferay.osb.service.persistence.CorpProjectPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

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
 * The persistence implementation for the corp project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectPersistence
 * @see com.liferay.osb.service.persistence.CorpProjectUtil
 * @generated
 */
@ProviderType
public class CorpProjectPersistenceImpl extends BasePersistenceImpl<CorpProject>
	implements CorpProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpProjectUtil} to access the corp project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CorpProjectPersistenceImpl() {
		setModelClass(CorpProject.class);
	}

	/**
	 * Caches the corp project in the entity cache if it is enabled.
	 *
	 * @param corpProject the corp project
	 */
	@Override
	public void cacheResult(CorpProject corpProject) {
		entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey(), corpProject);

		corpProject.resetOriginalValues();
	}

	/**
	 * Caches the corp projects in the entity cache if it is enabled.
	 *
	 * @param corpProjects the corp projects
	 */
	@Override
	public void cacheResult(List<CorpProject> corpProjects) {
		for (CorpProject corpProject : corpProjects) {
			if (entityCache.getResult(
						CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectImpl.class, corpProject.getPrimaryKey()) == null) {
				cacheResult(corpProject);
			}
			else {
				corpProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp projects.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CorpProjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp project.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpProject corpProject) {
		entityCache.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CorpProject> corpProjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpProject corpProject : corpProjects) {
			entityCache.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectImpl.class, corpProject.getPrimaryKey());
		}
	}

	/**
	 * Creates a new corp project with the primary key. Does not add the corp project to the database.
	 *
	 * @param corpProjectId the primary key for the new corp project
	 * @return the new corp project
	 */
	@Override
	public CorpProject create(long corpProjectId) {
		CorpProject corpProject = new CorpProjectImpl();

		corpProject.setNew(true);
		corpProject.setPrimaryKey(corpProjectId);

		return corpProject;
	}

	/**
	 * Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project that was removed
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject remove(long corpProjectId)
		throws NoSuchCorpProjectException {
		return remove((Serializable)corpProjectId);
	}

	/**
	 * Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project that was removed
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject remove(Serializable primaryKey)
		throws NoSuchCorpProjectException {
		Session session = null;

		try {
			session = openSession();

			CorpProject corpProject = (CorpProject)session.get(CorpProjectImpl.class,
					primaryKey);

			if (corpProject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpProject);
		}
		catch (NoSuchCorpProjectException nsee) {
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
	protected CorpProject removeImpl(CorpProject corpProject) {
		corpProject = toUnwrappedModel(corpProject);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(corpProject)) {
				corpProject = (CorpProject)session.get(CorpProjectImpl.class,
						corpProject.getPrimaryKeyObj());
			}

			if (corpProject != null) {
				session.delete(corpProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (corpProject != null) {
			clearCache(corpProject);
		}

		return corpProject;
	}

	@Override
	public CorpProject updateImpl(CorpProject corpProject) {
		corpProject = toUnwrappedModel(corpProject);

		boolean isNew = corpProject.isNew();

		CorpProjectModelImpl corpProjectModelImpl = (CorpProjectModelImpl)corpProject;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (corpProject.getCreateDate() == null)) {
			if (serviceContext == null) {
				corpProject.setCreateDate(now);
			}
			else {
				corpProject.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!corpProjectModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				corpProject.setModifiedDate(now);
			}
			else {
				corpProject.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (corpProject.isNew()) {
				session.save(corpProject);

				corpProject.setNew(false);
			}
			else {
				corpProject = (CorpProject)session.merge(corpProject);
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

		entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey(), corpProject,
			false);

		corpProject.resetOriginalValues();

		return corpProject;
	}

	protected CorpProject toUnwrappedModel(CorpProject corpProject) {
		if (corpProject instanceof CorpProjectImpl) {
			return corpProject;
		}

		CorpProjectImpl corpProjectImpl = new CorpProjectImpl();

		corpProjectImpl.setNew(corpProject.isNew());
		corpProjectImpl.setPrimaryKey(corpProject.getPrimaryKey());

		corpProjectImpl.setCorpProjectId(corpProject.getCorpProjectId());
		corpProjectImpl.setUserId(corpProject.getUserId());
		corpProjectImpl.setUserName(corpProject.getUserName());
		corpProjectImpl.setCreateDate(corpProject.getCreateDate());
		corpProjectImpl.setModifiedDate(corpProject.getModifiedDate());
		corpProjectImpl.setDossieraProjectKey(corpProject.getDossieraProjectKey());
		corpProjectImpl.setSalesforceProjectKey(corpProject.getSalesforceProjectKey());
		corpProjectImpl.setName(corpProject.getName());
		corpProjectImpl.setOrganizationId(corpProject.getOrganizationId());

		return corpProjectImpl;
	}

	/**
	 * Returns the corp project with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByPrimaryKey(primaryKey);

		if (corpProject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCorpProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return corpProject;
	}

	/**
	 * Returns the corp project with the primary key or throws a {@link NoSuchCorpProjectException} if it could not be found.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject findByPrimaryKey(long corpProjectId)
		throws NoSuchCorpProjectException {
		return findByPrimaryKey((Serializable)corpProjectId);
	}

	/**
	 * Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CorpProject corpProject = (CorpProject)serializable;

		if (corpProject == null) {
			Session session = null;

			try {
				session = openSession();

				corpProject = (CorpProject)session.get(CorpProjectImpl.class,
						primaryKey);

				if (corpProject != null) {
					cacheResult(corpProject);
				}
				else {
					entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return corpProject;
	}

	/**
	 * Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject fetchByPrimaryKey(long corpProjectId) {
		return fetchByPrimaryKey((Serializable)corpProjectId);
	}

	@Override
	public Map<Serializable, CorpProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CorpProject> map = new HashMap<Serializable, CorpProject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CorpProject corpProject = fetchByPrimaryKey(primaryKey);

			if (corpProject != null) {
				map.put(primaryKey, corpProject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CorpProject)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CORPPROJECT_WHERE_PKS_IN);

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

			for (CorpProject corpProject : (List<CorpProject>)q.list()) {
				map.put(corpProject.getPrimaryKeyObj(), corpProject);

				cacheResult(corpProject);

				uncachedPrimaryKeys.remove(corpProject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectImpl.class, primaryKey, nullModel);
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
	 * Returns all the corp projects.
	 *
	 * @return the corp projects
	 */
	@Override
	public List<CorpProject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @return the range of corp projects
	 */
	@Override
	public List<CorpProject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp projects
	 */
	@Override
	public List<CorpProject> findAll(int start, int end,
		OrderByComparator<CorpProject> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of corp projects
	 */
	@Override
	public List<CorpProject> findAll(int start, int end,
		OrderByComparator<CorpProject> orderByComparator,
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

		List<CorpProject> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProject>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CORPPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPPROJECT;

				if (pagination) {
					sql = sql.concat(CorpProjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the corp projects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CorpProject corpProject : findAll()) {
			remove(corpProject);
		}
	}

	/**
	 * Returns the number of corp projects.
	 *
	 * @return the number of corp projects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPPROJECT);

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
		return CorpProjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the corp project persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CorpProjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CORPPROJECT = "SELECT corpProject FROM CorpProject corpProject";
	private static final String _SQL_SELECT_CORPPROJECT_WHERE_PKS_IN = "SELECT corpProject FROM CorpProject corpProject WHERE corpProjectId IN (";
	private static final String _SQL_COUNT_CORPPROJECT = "SELECT COUNT(corpProject) FROM CorpProject corpProject";
	private static final String _ORDER_BY_ENTITY_ALIAS = "corpProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpProject exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CorpProjectPersistenceImpl.class);
}
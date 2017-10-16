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

package com.liferay.osb.customer.release.notes.jira.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectVersionException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionImpl;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAProjectVersionPersistence;

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
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the jira project version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectVersionPersistence
 * @see com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAProjectVersionUtil
 * @generated
 */
@ProviderType
public class JIRAProjectVersionPersistenceImpl extends BasePersistenceImpl<JIRAProjectVersion>
	implements JIRAProjectVersionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAProjectVersionUtil} to access the jira project version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAProjectVersionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionModelImpl.FINDER_CACHE_ENABLED,
			JIRAProjectVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionModelImpl.FINDER_CACHE_ENABLED,
			JIRAProjectVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public JIRAProjectVersionPersistenceImpl() {
		setModelClass(JIRAProjectVersion.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("jiraProjectVersionId", "id");
			dbColumnNames.put("jiraProjectId", "project");
			dbColumnNames.put("name", "vname");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the jira project version in the entity cache if it is enabled.
	 *
	 * @param jiraProjectVersion the jira project version
	 */
	@Override
	public void cacheResult(JIRAProjectVersion jiraProjectVersion) {
		entityCache.putResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionImpl.class, jiraProjectVersion.getPrimaryKey(),
			jiraProjectVersion);

		jiraProjectVersion.resetOriginalValues();
	}

	/**
	 * Caches the jira project versions in the entity cache if it is enabled.
	 *
	 * @param jiraProjectVersions the jira project versions
	 */
	@Override
	public void cacheResult(List<JIRAProjectVersion> jiraProjectVersions) {
		for (JIRAProjectVersion jiraProjectVersion : jiraProjectVersions) {
			if (entityCache.getResult(
						JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
						JIRAProjectVersionImpl.class,
						jiraProjectVersion.getPrimaryKey()) == null) {
				cacheResult(jiraProjectVersion);
			}
			else {
				jiraProjectVersion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all jira project versions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JIRAProjectVersionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the jira project version.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAProjectVersion jiraProjectVersion) {
		entityCache.removeResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionImpl.class, jiraProjectVersion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAProjectVersion> jiraProjectVersions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAProjectVersion jiraProjectVersion : jiraProjectVersions) {
			entityCache.removeResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
				JIRAProjectVersionImpl.class, jiraProjectVersion.getPrimaryKey());
		}
	}

	/**
	 * Creates a new jira project version with the primary key. Does not add the jira project version to the database.
	 *
	 * @param jiraProjectVersionId the primary key for the new jira project version
	 * @return the new jira project version
	 */
	@Override
	public JIRAProjectVersion create(long jiraProjectVersionId) {
		JIRAProjectVersion jiraProjectVersion = new JIRAProjectVersionImpl();

		jiraProjectVersion.setNew(true);
		jiraProjectVersion.setPrimaryKey(jiraProjectVersionId);

		return jiraProjectVersion;
	}

	/**
	 * Removes the jira project version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectVersionId the primary key of the jira project version
	 * @return the jira project version that was removed
	 * @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	 */
	@Override
	public JIRAProjectVersion remove(long jiraProjectVersionId)
		throws NoSuchJIRAProjectVersionException {
		return remove((Serializable)jiraProjectVersionId);
	}

	/**
	 * Removes the jira project version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the jira project version
	 * @return the jira project version that was removed
	 * @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	 */
	@Override
	public JIRAProjectVersion remove(Serializable primaryKey)
		throws NoSuchJIRAProjectVersionException {
		Session session = null;

		try {
			session = openSession();

			JIRAProjectVersion jiraProjectVersion = (JIRAProjectVersion)session.get(JIRAProjectVersionImpl.class,
					primaryKey);

			if (jiraProjectVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAProjectVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jiraProjectVersion);
		}
		catch (NoSuchJIRAProjectVersionException nsee) {
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
	protected JIRAProjectVersion removeImpl(
		JIRAProjectVersion jiraProjectVersion) {
		jiraProjectVersion = toUnwrappedModel(jiraProjectVersion);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraProjectVersion)) {
				jiraProjectVersion = (JIRAProjectVersion)session.get(JIRAProjectVersionImpl.class,
						jiraProjectVersion.getPrimaryKeyObj());
			}

			if (jiraProjectVersion != null) {
				session.delete(jiraProjectVersion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jiraProjectVersion != null) {
			clearCache(jiraProjectVersion);
		}

		return jiraProjectVersion;
	}

	@Override
	public JIRAProjectVersion updateImpl(JIRAProjectVersion jiraProjectVersion) {
		jiraProjectVersion = toUnwrappedModel(jiraProjectVersion);

		boolean isNew = jiraProjectVersion.isNew();

		Session session = null;

		try {
			session = openSession();

			if (jiraProjectVersion.isNew()) {
				session.save(jiraProjectVersion);

				jiraProjectVersion.setNew(false);
			}
			else {
				jiraProjectVersion = (JIRAProjectVersion)session.merge(jiraProjectVersion);
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

		entityCache.putResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionImpl.class, jiraProjectVersion.getPrimaryKey(),
			jiraProjectVersion, false);

		jiraProjectVersion.resetOriginalValues();

		return jiraProjectVersion;
	}

	protected JIRAProjectVersion toUnwrappedModel(
		JIRAProjectVersion jiraProjectVersion) {
		if (jiraProjectVersion instanceof JIRAProjectVersionImpl) {
			return jiraProjectVersion;
		}

		JIRAProjectVersionImpl jiraProjectVersionImpl = new JIRAProjectVersionImpl();

		jiraProjectVersionImpl.setNew(jiraProjectVersion.isNew());
		jiraProjectVersionImpl.setPrimaryKey(jiraProjectVersion.getPrimaryKey());

		jiraProjectVersionImpl.setJiraProjectVersionId(jiraProjectVersion.getJiraProjectVersionId());
		jiraProjectVersionImpl.setJiraProjectId(jiraProjectVersion.getJiraProjectId());
		jiraProjectVersionImpl.setName(jiraProjectVersion.getName());
		jiraProjectVersionImpl.setReleased(jiraProjectVersion.getReleased());
		jiraProjectVersionImpl.setArchived(jiraProjectVersion.getArchived());

		return jiraProjectVersionImpl;
	}

	/**
	 * Returns the jira project version with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira project version
	 * @return the jira project version
	 * @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	 */
	@Override
	public JIRAProjectVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAProjectVersionException {
		JIRAProjectVersion jiraProjectVersion = fetchByPrimaryKey(primaryKey);

		if (jiraProjectVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAProjectVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jiraProjectVersion;
	}

	/**
	 * Returns the jira project version with the primary key or throws a {@link NoSuchJIRAProjectVersionException} if it could not be found.
	 *
	 * @param jiraProjectVersionId the primary key of the jira project version
	 * @return the jira project version
	 * @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	 */
	@Override
	public JIRAProjectVersion findByPrimaryKey(long jiraProjectVersionId)
		throws NoSuchJIRAProjectVersionException {
		return findByPrimaryKey((Serializable)jiraProjectVersionId);
	}

	/**
	 * Returns the jira project version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira project version
	 * @return the jira project version, or <code>null</code> if a jira project version with the primary key could not be found
	 */
	@Override
	public JIRAProjectVersion fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
				JIRAProjectVersionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JIRAProjectVersion jiraProjectVersion = (JIRAProjectVersion)serializable;

		if (jiraProjectVersion == null) {
			Session session = null;

			try {
				session = openSession();

				jiraProjectVersion = (JIRAProjectVersion)session.get(JIRAProjectVersionImpl.class,
						primaryKey);

				if (jiraProjectVersion != null) {
					cacheResult(jiraProjectVersion);
				}
				else {
					entityCache.putResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
						JIRAProjectVersionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectVersionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraProjectVersion;
	}

	/**
	 * Returns the jira project version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraProjectVersionId the primary key of the jira project version
	 * @return the jira project version, or <code>null</code> if a jira project version with the primary key could not be found
	 */
	@Override
	public JIRAProjectVersion fetchByPrimaryKey(long jiraProjectVersionId) {
		return fetchByPrimaryKey((Serializable)jiraProjectVersionId);
	}

	@Override
	public Map<Serializable, JIRAProjectVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAProjectVersion> map = new HashMap<Serializable, JIRAProjectVersion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAProjectVersion jiraProjectVersion = fetchByPrimaryKey(primaryKey);

			if (jiraProjectVersion != null) {
				map.put(primaryKey, jiraProjectVersion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectVersionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JIRAProjectVersion)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JIRAPROJECTVERSION_WHERE_PKS_IN);

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

			for (JIRAProjectVersion jiraProjectVersion : (List<JIRAProjectVersion>)q.list()) {
				map.put(jiraProjectVersion.getPrimaryKeyObj(),
					jiraProjectVersion);

				cacheResult(jiraProjectVersion);

				uncachedPrimaryKeys.remove(jiraProjectVersion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectVersionImpl.class, primaryKey, nullModel);
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
	 * Returns all the jira project versions.
	 *
	 * @return the jira project versions
	 */
	@Override
	public List<JIRAProjectVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira project versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira project versions
	 * @param end the upper bound of the range of jira project versions (not inclusive)
	 * @return the range of jira project versions
	 */
	@Override
	public List<JIRAProjectVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira project versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira project versions
	 * @param end the upper bound of the range of jira project versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira project versions
	 */
	@Override
	public List<JIRAProjectVersion> findAll(int start, int end,
		OrderByComparator<JIRAProjectVersion> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira project versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira project versions
	 * @param end the upper bound of the range of jira project versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of jira project versions
	 */
	@Override
	public List<JIRAProjectVersion> findAll(int start, int end,
		OrderByComparator<JIRAProjectVersion> orderByComparator,
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

		List<JIRAProjectVersion> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAProjectVersion>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_JIRAPROJECTVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAPROJECTVERSION;

				if (pagination) {
					sql = sql.concat(JIRAProjectVersionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JIRAProjectVersion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAProjectVersion>)QueryUtil.list(q,
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
	 * Removes all the jira project versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAProjectVersion jiraProjectVersion : findAll()) {
			remove(jiraProjectVersion);
		}
	}

	/**
	 * Returns the number of jira project versions.
	 *
	 * @return the number of jira project versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRAPROJECTVERSION);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return JIRAProjectVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jira project version persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(JIRAProjectVersionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_JIRAPROJECTVERSION = "SELECT jiraProjectVersion FROM JIRAProjectVersion jiraProjectVersion";
	private static final String _SQL_SELECT_JIRAPROJECTVERSION_WHERE_PKS_IN = "SELECT jiraProjectVersion FROM JIRAProjectVersion jiraProjectVersion WHERE id IN (";
	private static final String _SQL_COUNT_JIRAPROJECTVERSION = "SELECT COUNT(jiraProjectVersion) FROM JIRAProjectVersion jiraProjectVersion";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraProjectVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAProjectVersion exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(JIRAProjectVersionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"jiraProjectVersionId", "jiraProjectId", "name"
			});
}
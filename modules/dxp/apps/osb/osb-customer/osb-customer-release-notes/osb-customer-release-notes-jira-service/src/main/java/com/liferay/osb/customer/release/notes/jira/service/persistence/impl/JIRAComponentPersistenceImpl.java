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

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAComponentException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAComponentImpl;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAComponentModelImpl;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAComponentPersistence;

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
 * The persistence implementation for the jira component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentPersistence
 * @see com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAComponentUtil
 * @generated
 */
@ProviderType
public class JIRAComponentPersistenceImpl extends BasePersistenceImpl<JIRAComponent>
	implements JIRAComponentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAComponentUtil} to access the jira component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAComponentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public JIRAComponentPersistenceImpl() {
		setModelClass(JIRAComponent.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("jiraComponentId", "id");
			dbColumnNames.put("jiraProjectId", "project");
			dbColumnNames.put("name", "cname");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the jira component in the entity cache if it is enabled.
	 *
	 * @param jiraComponent the jira component
	 */
	@Override
	public void cacheResult(JIRAComponent jiraComponent) {
		entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey(),
			jiraComponent);

		jiraComponent.resetOriginalValues();
	}

	/**
	 * Caches the jira components in the entity cache if it is enabled.
	 *
	 * @param jiraComponents the jira components
	 */
	@Override
	public void cacheResult(List<JIRAComponent> jiraComponents) {
		for (JIRAComponent jiraComponent : jiraComponents) {
			if (entityCache.getResult(
						JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
						JIRAComponentImpl.class, jiraComponent.getPrimaryKey()) == null) {
				cacheResult(jiraComponent);
			}
			else {
				jiraComponent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all jira components.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JIRAComponentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the jira component.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAComponent jiraComponent) {
		entityCache.removeResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAComponent> jiraComponents) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAComponent jiraComponent : jiraComponents) {
			entityCache.removeResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
				JIRAComponentImpl.class, jiraComponent.getPrimaryKey());
		}
	}

	/**
	 * Creates a new jira component with the primary key. Does not add the jira component to the database.
	 *
	 * @param jiraComponentId the primary key for the new jira component
	 * @return the new jira component
	 */
	@Override
	public JIRAComponent create(long jiraComponentId) {
		JIRAComponent jiraComponent = new JIRAComponentImpl();

		jiraComponent.setNew(true);
		jiraComponent.setPrimaryKey(jiraComponentId);

		return jiraComponent;
	}

	/**
	 * Removes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component that was removed
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent remove(long jiraComponentId)
		throws NoSuchJIRAComponentException {
		return remove((Serializable)jiraComponentId);
	}

	/**
	 * Removes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the jira component
	 * @return the jira component that was removed
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent remove(Serializable primaryKey)
		throws NoSuchJIRAComponentException {
		Session session = null;

		try {
			session = openSession();

			JIRAComponent jiraComponent = (JIRAComponent)session.get(JIRAComponentImpl.class,
					primaryKey);

			if (jiraComponent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jiraComponent);
		}
		catch (NoSuchJIRAComponentException nsee) {
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
	protected JIRAComponent removeImpl(JIRAComponent jiraComponent) {
		jiraComponent = toUnwrappedModel(jiraComponent);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraComponent)) {
				jiraComponent = (JIRAComponent)session.get(JIRAComponentImpl.class,
						jiraComponent.getPrimaryKeyObj());
			}

			if (jiraComponent != null) {
				session.delete(jiraComponent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jiraComponent != null) {
			clearCache(jiraComponent);
		}

		return jiraComponent;
	}

	@Override
	public JIRAComponent updateImpl(JIRAComponent jiraComponent) {
		jiraComponent = toUnwrappedModel(jiraComponent);

		boolean isNew = jiraComponent.isNew();

		Session session = null;

		try {
			session = openSession();

			if (jiraComponent.isNew()) {
				session.save(jiraComponent);

				jiraComponent.setNew(false);
			}
			else {
				jiraComponent = (JIRAComponent)session.merge(jiraComponent);
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

		entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey(),
			jiraComponent, false);

		jiraComponent.resetOriginalValues();

		return jiraComponent;
	}

	protected JIRAComponent toUnwrappedModel(JIRAComponent jiraComponent) {
		if (jiraComponent instanceof JIRAComponentImpl) {
			return jiraComponent;
		}

		JIRAComponentImpl jiraComponentImpl = new JIRAComponentImpl();

		jiraComponentImpl.setNew(jiraComponent.isNew());
		jiraComponentImpl.setPrimaryKey(jiraComponent.getPrimaryKey());

		jiraComponentImpl.setJiraComponentId(jiraComponent.getJiraComponentId());
		jiraComponentImpl.setJiraProjectId(jiraComponent.getJiraProjectId());
		jiraComponentImpl.setName(jiraComponent.getName());

		return jiraComponentImpl;
	}

	/**
	 * Returns the jira component with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira component
	 * @return the jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = fetchByPrimaryKey(primaryKey);

		if (jiraComponent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jiraComponent;
	}

	/**
	 * Returns the jira component with the primary key or throws a {@link NoSuchJIRAComponentException} if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent findByPrimaryKey(long jiraComponentId)
		throws NoSuchJIRAComponentException {
		return findByPrimaryKey((Serializable)jiraComponentId);
	}

	/**
	 * Returns the jira component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira component
	 * @return the jira component, or <code>null</code> if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
				JIRAComponentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JIRAComponent jiraComponent = (JIRAComponent)serializable;

		if (jiraComponent == null) {
			Session session = null;

			try {
				session = openSession();

				jiraComponent = (JIRAComponent)session.get(JIRAComponentImpl.class,
						primaryKey);

				if (jiraComponent != null) {
					cacheResult(jiraComponent);
				}
				else {
					entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
						JIRAComponentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraComponent;
	}

	/**
	 * Returns the jira component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component, or <code>null</code> if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent fetchByPrimaryKey(long jiraComponentId) {
		return fetchByPrimaryKey((Serializable)jiraComponentId);
	}

	@Override
	public Map<Serializable, JIRAComponent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAComponent> map = new HashMap<Serializable, JIRAComponent>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAComponent jiraComponent = fetchByPrimaryKey(primaryKey);

			if (jiraComponent != null) {
				map.put(primaryKey, jiraComponent);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JIRAComponent)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JIRACOMPONENT_WHERE_PKS_IN);

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

			for (JIRAComponent jiraComponent : (List<JIRAComponent>)q.list()) {
				map.put(jiraComponent.getPrimaryKeyObj(), jiraComponent);

				cacheResult(jiraComponent);

				uncachedPrimaryKeys.remove(jiraComponent.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, primaryKey, nullModel);
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
	 * Returns all the jira components.
	 *
	 * @return the jira components
	 */
	@Override
	public List<JIRAComponent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator,
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

		List<JIRAComponent> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAComponent>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_JIRACOMPONENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRACOMPONENT;

				if (pagination) {
					sql = sql.concat(JIRAComponentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the jira components from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAComponent jiraComponent : findAll()) {
			remove(jiraComponent);
		}
	}

	/**
	 * Returns the number of jira components.
	 *
	 * @return the number of jira components
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRACOMPONENT);

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
		return JIRAComponentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jira component persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(JIRAComponentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_JIRACOMPONENT = "SELECT jiraComponent FROM JIRAComponent jiraComponent";
	private static final String _SQL_SELECT_JIRACOMPONENT_WHERE_PKS_IN = "SELECT jiraComponent FROM JIRAComponent jiraComponent WHERE id IN (";
	private static final String _SQL_COUNT_JIRACOMPONENT = "SELECT COUNT(jiraComponent) FROM JIRAComponent jiraComponent";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraComponent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAComponent exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(JIRAComponentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"jiraComponentId", "jiraProjectId", "name"
			});
}
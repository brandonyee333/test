/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.release.notes.jira.service.persistence.impl;

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProject;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectImpl;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectModelImpl;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAProjectPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the jira project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAProjectPersistenceImpl
	extends BasePersistenceImpl<JIRAProject> implements JIRAProjectPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>JIRAProjectUtil</code> to access the jira project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		JIRAProjectImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByKey;
	private FinderPath _finderPathCountByKey;

	/**
	 * Returns the jira project where key = &#63; or throws a <code>NoSuchJIRAProjectException</code> if it could not be found.
	 *
	 * @param key the key
	 * @return the matching jira project
	 * @throws NoSuchJIRAProjectException if a matching jira project could not be found
	 */
	@Override
	public JIRAProject findByKey(String key) throws NoSuchJIRAProjectException {
		JIRAProject jiraProject = fetchByKey(key);

		if (jiraProject == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("key=");
			sb.append(key);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchJIRAProjectException(sb.toString());
		}

		return jiraProject;
	}

	/**
	 * Returns the jira project where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching jira project, or <code>null</code> if a matching jira project could not be found
	 */
	@Override
	public JIRAProject fetchByKey(String key) {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the jira project where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching jira project, or <code>null</code> if a matching jira project could not be found
	 */
	@Override
	public JIRAProject fetchByKey(String key, boolean useFinderCache) {
		key = Objects.toString(key, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {key};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByKey, finderArgs, this);
		}

		if (result instanceof JIRAProject) {
			JIRAProject jiraProject = (JIRAProject)result;

			if (!Objects.equals(key, jiraProject.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_JIRAPROJECT_WHERE);

			boolean bindKey = false;

			if (key.isEmpty()) {
				sb.append(_FINDER_COLUMN_KEY_KEY_3);
			}
			else {
				bindKey = true;

				sb.append(_FINDER_COLUMN_KEY_KEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKey) {
					queryPos.add(key);
				}

				List<JIRAProject> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByKey, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {key};
							}

							_log.warn(
								"JIRAProjectPersistenceImpl.fetchByKey(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					JIRAProject jiraProject = list.get(0);

					result = jiraProject;

					cacheResult(jiraProject);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(_finderPathFetchByKey, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (JIRAProject)result;
		}
	}

	/**
	 * Removes the jira project where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the jira project that was removed
	 */
	@Override
	public JIRAProject removeByKey(String key)
		throws NoSuchJIRAProjectException {

		JIRAProject jiraProject = findByKey(key);

		return remove(jiraProject);
	}

	/**
	 * Returns the number of jira projects where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching jira projects
	 */
	@Override
	public int countByKey(String key) {
		key = Objects.toString(key, "");

		FinderPath finderPath = _finderPathCountByKey;

		Object[] finderArgs = new Object[] {key};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_JIRAPROJECT_WHERE);

			boolean bindKey = false;

			if (key.isEmpty()) {
				sb.append(_FINDER_COLUMN_KEY_KEY_3);
			}
			else {
				bindKey = true;

				sb.append(_FINDER_COLUMN_KEY_KEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKey) {
					queryPos.add(key);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KEY_KEY_2 =
		"jiraProject.key = ?";

	private static final String _FINDER_COLUMN_KEY_KEY_3 =
		"(jiraProject.key IS NULL OR jiraProject.key = '')";

	public JIRAProjectPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("jiraProjectId", "id");
		dbColumnNames.put("key", "pkey");
		dbColumnNames.put("name", "pname");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(JIRAProject.class);
	}

	/**
	 * Caches the jira project in the entity cache if it is enabled.
	 *
	 * @param jiraProject the jira project
	 */
	@Override
	public void cacheResult(JIRAProject jiraProject) {
		entityCache.putResult(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED, JIRAProjectImpl.class,
			jiraProject.getPrimaryKey(), jiraProject);

		finderCache.putResult(
			_finderPathFetchByKey, new Object[] {jiraProject.getKey()},
			jiraProject);

		jiraProject.resetOriginalValues();
	}

	/**
	 * Caches the jira projects in the entity cache if it is enabled.
	 *
	 * @param jiraProjects the jira projects
	 */
	@Override
	public void cacheResult(List<JIRAProject> jiraProjects) {
		for (JIRAProject jiraProject : jiraProjects) {
			if (entityCache.getResult(
					JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectImpl.class, jiraProject.getPrimaryKey()) ==
						null) {

				cacheResult(jiraProject);
			}
			else {
				jiraProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all jira projects.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JIRAProjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the jira project.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAProject jiraProject) {
		entityCache.removeResult(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED, JIRAProjectImpl.class,
			jiraProject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((JIRAProjectModelImpl)jiraProject, true);
	}

	@Override
	public void clearCache(List<JIRAProject> jiraProjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAProject jiraProject : jiraProjects) {
			entityCache.removeResult(
				JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
				JIRAProjectImpl.class, jiraProject.getPrimaryKey());

			clearUniqueFindersCache((JIRAProjectModelImpl)jiraProject, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
				JIRAProjectImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		JIRAProjectModelImpl jiraProjectModelImpl) {

		Object[] args = new Object[] {jiraProjectModelImpl.getKey()};

		finderCache.putResult(
			_finderPathCountByKey, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByKey, args, jiraProjectModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		JIRAProjectModelImpl jiraProjectModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {jiraProjectModelImpl.getKey()};

			finderCache.removeResult(_finderPathCountByKey, args);
			finderCache.removeResult(_finderPathFetchByKey, args);
		}

		if ((jiraProjectModelImpl.getColumnBitmask() &
			 _finderPathFetchByKey.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				jiraProjectModelImpl.getOriginalKey()
			};

			finderCache.removeResult(_finderPathCountByKey, args);
			finderCache.removeResult(_finderPathFetchByKey, args);
		}
	}

	/**
	 * Creates a new jira project with the primary key. Does not add the jira project to the database.
	 *
	 * @param jiraProjectId the primary key for the new jira project
	 * @return the new jira project
	 */
	@Override
	public JIRAProject create(long jiraProjectId) {
		JIRAProject jiraProject = new JIRAProjectImpl();

		jiraProject.setNew(true);
		jiraProject.setPrimaryKey(jiraProjectId);

		return jiraProject;
	}

	/**
	 * Removes the jira project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project that was removed
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	@Override
	public JIRAProject remove(long jiraProjectId)
		throws NoSuchJIRAProjectException {

		return remove((Serializable)jiraProjectId);
	}

	/**
	 * Removes the jira project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the jira project
	 * @return the jira project that was removed
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	@Override
	public JIRAProject remove(Serializable primaryKey)
		throws NoSuchJIRAProjectException {

		Session session = null;

		try {
			session = openSession();

			JIRAProject jiraProject = (JIRAProject)session.get(
				JIRAProjectImpl.class, primaryKey);

			if (jiraProject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAProjectException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(jiraProject);
		}
		catch (NoSuchJIRAProjectException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected JIRAProject removeImpl(JIRAProject jiraProject) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraProject)) {
				jiraProject = (JIRAProject)session.get(
					JIRAProjectImpl.class, jiraProject.getPrimaryKeyObj());
			}

			if (jiraProject != null) {
				session.delete(jiraProject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (jiraProject != null) {
			clearCache(jiraProject);
		}

		return jiraProject;
	}

	@Override
	public JIRAProject updateImpl(JIRAProject jiraProject) {
		boolean isNew = jiraProject.isNew();

		if (!(jiraProject instanceof JIRAProjectModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(jiraProject.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(jiraProject);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in jiraProject proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom JIRAProject implementation " +
					jiraProject.getClass());
		}

		JIRAProjectModelImpl jiraProjectModelImpl =
			(JIRAProjectModelImpl)jiraProject;

		Session session = null;

		try {
			session = openSession();

			if (jiraProject.isNew()) {
				session.save(jiraProject);

				jiraProject.setNew(false);
			}
			else {
				jiraProject = (JIRAProject)session.merge(jiraProject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!JIRAProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED, JIRAProjectImpl.class,
			jiraProject.getPrimaryKey(), jiraProject, false);

		clearUniqueFindersCache(jiraProjectModelImpl, false);
		cacheUniqueFindersCache(jiraProjectModelImpl);

		jiraProject.resetOriginalValues();

		return jiraProject;
	}

	/**
	 * Returns the jira project with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira project
	 * @return the jira project
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	@Override
	public JIRAProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAProjectException {

		JIRAProject jiraProject = fetchByPrimaryKey(primaryKey);

		if (jiraProject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAProjectException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return jiraProject;
	}

	/**
	 * Returns the jira project with the primary key or throws a <code>NoSuchJIRAProjectException</code> if it could not be found.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project
	 * @throws NoSuchJIRAProjectException if a jira project with the primary key could not be found
	 */
	@Override
	public JIRAProject findByPrimaryKey(long jiraProjectId)
		throws NoSuchJIRAProjectException {

		return findByPrimaryKey((Serializable)jiraProjectId);
	}

	/**
	 * Returns the jira project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira project
	 * @return the jira project, or <code>null</code> if a jira project with the primary key could not be found
	 */
	@Override
	public JIRAProject fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED, JIRAProjectImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JIRAProject jiraProject = (JIRAProject)serializable;

		if (jiraProject == null) {
			Session session = null;

			try {
				session = openSession();

				jiraProject = (JIRAProject)session.get(
					JIRAProjectImpl.class, primaryKey);

				if (jiraProject != null) {
					cacheResult(jiraProject);
				}
				else {
					entityCache.putResult(
						JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
						JIRAProjectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraProject;
	}

	/**
	 * Returns the jira project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraProjectId the primary key of the jira project
	 * @return the jira project, or <code>null</code> if a jira project with the primary key could not be found
	 */
	@Override
	public JIRAProject fetchByPrimaryKey(long jiraProjectId) {
		return fetchByPrimaryKey((Serializable)jiraProjectId);
	}

	@Override
	public Map<Serializable, JIRAProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAProject> map =
			new HashMap<Serializable, JIRAProject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAProject jiraProject = fetchByPrimaryKey(primaryKey);

			if (jiraProject != null) {
				map.put(primaryKey, jiraProject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
				JIRAProjectImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JIRAProject)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_JIRAPROJECT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (JIRAProject jiraProject : (List<JIRAProject>)query.list()) {
				map.put(jiraProject.getPrimaryKeyObj(), jiraProject);

				cacheResult(jiraProject);

				uncachedPrimaryKeys.remove(jiraProject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the jira projects.
	 *
	 * @return the jira projects
	 */
	@Override
	public List<JIRAProject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira projects
	 * @param end the upper bound of the range of jira projects (not inclusive)
	 * @return the range of jira projects
	 */
	@Override
	public List<JIRAProject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira projects
	 * @param end the upper bound of the range of jira projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira projects
	 */
	@Override
	public List<JIRAProject> findAll(
		int start, int end, OrderByComparator<JIRAProject> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira projects
	 * @param end the upper bound of the range of jira projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jira projects
	 */
	@Override
	public List<JIRAProject> findAll(
		int start, int end, OrderByComparator<JIRAProject> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<JIRAProject> list = null;

		if (useFinderCache) {
			list = (List<JIRAProject>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_JIRAPROJECT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAPROJECT;

				sql = sql.concat(JIRAProjectModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<JIRAProject>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the jira projects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAProject jiraProject : findAll()) {
			remove(jiraProject);
		}
	}

	/**
	 * Returns the number of jira projects.
	 *
	 * @return the number of jira projects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_JIRAPROJECT);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
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
		return JIRAProjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jira project persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectModelImpl.FINDER_CACHE_ENABLED, JIRAProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectModelImpl.FINDER_CACHE_ENABLED, JIRAProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByKey = new FinderPath(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectModelImpl.FINDER_CACHE_ENABLED, JIRAProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByKey",
			new String[] {String.class.getName()},
			JIRAProjectModelImpl.KEY_COLUMN_BITMASK);

		_finderPathCountByKey = new FinderPath(
			JIRAProjectModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKey",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(JIRAProjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_JIRAPROJECT =
		"SELECT jiraProject FROM JIRAProject jiraProject";

	private static final String _SQL_SELECT_JIRAPROJECT_WHERE_PKS_IN =
		"SELECT jiraProject FROM JIRAProject jiraProject WHERE id IN (";

	private static final String _SQL_SELECT_JIRAPROJECT_WHERE =
		"SELECT jiraProject FROM JIRAProject jiraProject WHERE ";

	private static final String _SQL_COUNT_JIRAPROJECT =
		"SELECT COUNT(jiraProject) FROM JIRAProject jiraProject";

	private static final String _SQL_COUNT_JIRAPROJECT_WHERE =
		"SELECT COUNT(jiraProject) FROM JIRAProject jiraProject WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraProject.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No JIRAProject exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No JIRAProject exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		JIRAProjectPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"jiraProjectId", "key", "name"});

}
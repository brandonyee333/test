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

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAIssueException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueImpl;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueModelImpl;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAIssuePersistence;
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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
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
 * The persistence implementation for the jira issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAIssuePersistenceImpl
	extends BasePersistenceImpl<JIRAIssue> implements JIRAIssuePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>JIRAIssueUtil</code> to access the jira issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		JIRAIssueImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public JIRAIssuePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("jiraIssueId", "id");
		dbColumnNames.put("jiraProjectId", "project");
		dbColumnNames.put("issueNumber", "issuenum");
		dbColumnNames.put("type", "issuetype");

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

		setModelClass(JIRAIssue.class);
	}

	/**
	 * Caches the jira issue in the entity cache if it is enabled.
	 *
	 * @param jiraIssue the jira issue
	 */
	@Override
	public void cacheResult(JIRAIssue jiraIssue) {
		entityCache.putResult(
			JIRAIssueModelImpl.ENTITY_CACHE_ENABLED, JIRAIssueImpl.class,
			jiraIssue.getPrimaryKey(), jiraIssue);

		jiraIssue.resetOriginalValues();
	}

	/**
	 * Caches the jira issues in the entity cache if it is enabled.
	 *
	 * @param jiraIssues the jira issues
	 */
	@Override
	public void cacheResult(List<JIRAIssue> jiraIssues) {
		for (JIRAIssue jiraIssue : jiraIssues) {
			if (entityCache.getResult(
					JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
					JIRAIssueImpl.class, jiraIssue.getPrimaryKey()) == null) {

				cacheResult(jiraIssue);
			}
			else {
				jiraIssue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all jira issues.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JIRAIssueImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the jira issue.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAIssue jiraIssue) {
		entityCache.removeResult(
			JIRAIssueModelImpl.ENTITY_CACHE_ENABLED, JIRAIssueImpl.class,
			jiraIssue.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAIssue> jiraIssues) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAIssue jiraIssue : jiraIssues) {
			entityCache.removeResult(
				JIRAIssueModelImpl.ENTITY_CACHE_ENABLED, JIRAIssueImpl.class,
				jiraIssue.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				JIRAIssueModelImpl.ENTITY_CACHE_ENABLED, JIRAIssueImpl.class,
				primaryKey);
		}
	}

	/**
	 * Creates a new jira issue with the primary key. Does not add the jira issue to the database.
	 *
	 * @param jiraIssueId the primary key for the new jira issue
	 * @return the new jira issue
	 */
	@Override
	public JIRAIssue create(long jiraIssueId) {
		JIRAIssue jiraIssue = new JIRAIssueImpl();

		jiraIssue.setNew(true);
		jiraIssue.setPrimaryKey(jiraIssueId);

		return jiraIssue;
	}

	/**
	 * Removes the jira issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue that was removed
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue remove(long jiraIssueId) throws NoSuchJIRAIssueException {
		return remove((Serializable)jiraIssueId);
	}

	/**
	 * Removes the jira issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the jira issue
	 * @return the jira issue that was removed
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue remove(Serializable primaryKey)
		throws NoSuchJIRAIssueException {

		Session session = null;

		try {
			session = openSession();

			JIRAIssue jiraIssue = (JIRAIssue)session.get(
				JIRAIssueImpl.class, primaryKey);

			if (jiraIssue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAIssueException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(jiraIssue);
		}
		catch (NoSuchJIRAIssueException noSuchEntityException) {
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
	protected JIRAIssue removeImpl(JIRAIssue jiraIssue) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraIssue)) {
				jiraIssue = (JIRAIssue)session.get(
					JIRAIssueImpl.class, jiraIssue.getPrimaryKeyObj());
			}

			if (jiraIssue != null) {
				session.delete(jiraIssue);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (jiraIssue != null) {
			clearCache(jiraIssue);
		}

		return jiraIssue;
	}

	@Override
	public JIRAIssue updateImpl(JIRAIssue jiraIssue) {
		boolean isNew = jiraIssue.isNew();

		Session session = null;

		try {
			session = openSession();

			if (jiraIssue.isNew()) {
				session.save(jiraIssue);

				jiraIssue.setNew(false);
			}
			else {
				jiraIssue = (JIRAIssue)session.merge(jiraIssue);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			JIRAIssueModelImpl.ENTITY_CACHE_ENABLED, JIRAIssueImpl.class,
			jiraIssue.getPrimaryKey(), jiraIssue, false);

		jiraIssue.resetOriginalValues();

		return jiraIssue;
	}

	/**
	 * Returns the jira issue with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira issue
	 * @return the jira issue
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAIssueException {

		JIRAIssue jiraIssue = fetchByPrimaryKey(primaryKey);

		if (jiraIssue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAIssueException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return jiraIssue;
	}

	/**
	 * Returns the jira issue with the primary key or throws a <code>NoSuchJIRAIssueException</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue
	 * @throws NoSuchJIRAIssueException if a jira issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue findByPrimaryKey(long jiraIssueId)
		throws NoSuchJIRAIssueException {

		return findByPrimaryKey((Serializable)jiraIssueId);
	}

	/**
	 * Returns the jira issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira issue
	 * @return the jira issue, or <code>null</code> if a jira issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			JIRAIssueModelImpl.ENTITY_CACHE_ENABLED, JIRAIssueImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JIRAIssue jiraIssue = (JIRAIssue)serializable;

		if (jiraIssue == null) {
			Session session = null;

			try {
				session = openSession();

				jiraIssue = (JIRAIssue)session.get(
					JIRAIssueImpl.class, primaryKey);

				if (jiraIssue != null) {
					cacheResult(jiraIssue);
				}
				else {
					entityCache.putResult(
						JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
						JIRAIssueImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
					JIRAIssueImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraIssue;
	}

	/**
	 * Returns the jira issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the jira issue
	 * @return the jira issue, or <code>null</code> if a jira issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue fetchByPrimaryKey(long jiraIssueId) {
		return fetchByPrimaryKey((Serializable)jiraIssueId);
	}

	@Override
	public Map<Serializable, JIRAIssue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAIssue> map =
			new HashMap<Serializable, JIRAIssue>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAIssue jiraIssue = fetchByPrimaryKey(primaryKey);

			if (jiraIssue != null) {
				map.put(primaryKey, jiraIssue);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				JIRAIssueModelImpl.ENTITY_CACHE_ENABLED, JIRAIssueImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JIRAIssue)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_JIRAISSUE_WHERE_PKS_IN);

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

			for (JIRAIssue jiraIssue : (List<JIRAIssue>)query.list()) {
				map.put(jiraIssue.getPrimaryKeyObj(), jiraIssue);

				cacheResult(jiraIssue);

				uncachedPrimaryKeys.remove(jiraIssue.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
					JIRAIssueImpl.class, primaryKey, nullModel);
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
	 * Returns all the jira issues.
	 *
	 * @return the jira issues
	 */
	@Override
	public List<JIRAIssue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira issues
	 * @param end the upper bound of the range of jira issues (not inclusive)
	 * @return the range of jira issues
	 */
	@Override
	public List<JIRAIssue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira issues
	 * @param end the upper bound of the range of jira issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira issues
	 */
	@Override
	public List<JIRAIssue> findAll(
		int start, int end, OrderByComparator<JIRAIssue> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira issues
	 * @param end the upper bound of the range of jira issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jira issues
	 */
	@Override
	public List<JIRAIssue> findAll(
		int start, int end, OrderByComparator<JIRAIssue> orderByComparator,
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

		List<JIRAIssue> list = null;

		if (useFinderCache) {
			list = (List<JIRAIssue>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_JIRAISSUE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAISSUE;

				sql = sql.concat(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<JIRAIssue>)QueryUtil.list(
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
	 * Removes all the jira issues from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAIssue jiraIssue : findAll()) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of jira issues.
	 *
	 * @return the number of jira issues
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_JIRAISSUE);

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
		return JIRAIssueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jira issue persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(JIRAIssueImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_JIRAISSUE =
		"SELECT jiraIssue FROM JIRAIssue jiraIssue";

	private static final String _SQL_SELECT_JIRAISSUE_WHERE_PKS_IN =
		"SELECT jiraIssue FROM JIRAIssue jiraIssue WHERE id IN (";

	private static final String _SQL_COUNT_JIRAISSUE =
		"SELECT COUNT(jiraIssue) FROM JIRAIssue jiraIssue";

	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraIssue.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No JIRAIssue exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		JIRAIssuePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"jiraIssueId", "jiraProjectId", "issueNumber", "type"});

}
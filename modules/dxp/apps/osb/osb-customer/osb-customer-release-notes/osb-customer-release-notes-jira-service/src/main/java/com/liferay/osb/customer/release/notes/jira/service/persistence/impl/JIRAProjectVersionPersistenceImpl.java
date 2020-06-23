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
 * The persistence implementation for the jira project version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAProjectVersionPersistenceImpl
	extends BasePersistenceImpl<JIRAProjectVersion>
	implements JIRAProjectVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>JIRAProjectVersionUtil</code> to access the jira project version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		JIRAProjectVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public JIRAProjectVersionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("jiraProjectVersionId", "id");
		dbColumnNames.put("jiraProjectId", "project");
		dbColumnNames.put("name", "vname");

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

		setModelClass(JIRAProjectVersion.class);
	}

	/**
	 * Caches the jira project version in the entity cache if it is enabled.
	 *
	 * @param jiraProjectVersion the jira project version
	 */
	@Override
	public void cacheResult(JIRAProjectVersion jiraProjectVersion) {
		entityCache.putResult(
			JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAProjectVersion jiraProjectVersion) {
		entityCache.removeResult(
			JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionImpl.class, jiraProjectVersion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAProjectVersion> jiraProjectVersions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAProjectVersion jiraProjectVersion : jiraProjectVersions) {
			entityCache.removeResult(
				JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
				JIRAProjectVersionImpl.class,
				jiraProjectVersion.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
				JIRAProjectVersionImpl.class, primaryKey);
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

			JIRAProjectVersion jiraProjectVersion =
				(JIRAProjectVersion)session.get(
					JIRAProjectVersionImpl.class, primaryKey);

			if (jiraProjectVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAProjectVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(jiraProjectVersion);
		}
		catch (NoSuchJIRAProjectVersionException noSuchEntityException) {
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
	protected JIRAProjectVersion removeImpl(
		JIRAProjectVersion jiraProjectVersion) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraProjectVersion)) {
				jiraProjectVersion = (JIRAProjectVersion)session.get(
					JIRAProjectVersionImpl.class,
					jiraProjectVersion.getPrimaryKeyObj());
			}

			if (jiraProjectVersion != null) {
				session.delete(jiraProjectVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
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
	public JIRAProjectVersion updateImpl(
		JIRAProjectVersion jiraProjectVersion) {

		boolean isNew = jiraProjectVersion.isNew();

		Session session = null;

		try {
			session = openSession();

			if (jiraProjectVersion.isNew()) {
				session.save(jiraProjectVersion);

				jiraProjectVersion.setNew(false);
			}
			else {
				jiraProjectVersion = (JIRAProjectVersion)session.merge(
					jiraProjectVersion);
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
			JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionImpl.class, jiraProjectVersion.getPrimaryKey(),
			jiraProjectVersion, false);

		jiraProjectVersion.resetOriginalValues();

		return jiraProjectVersion;
	}

	/**
	 * Returns the jira project version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
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

			throw new NoSuchJIRAProjectVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return jiraProjectVersion;
	}

	/**
	 * Returns the jira project version with the primary key or throws a <code>NoSuchJIRAProjectVersionException</code> if it could not be found.
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
		Serializable serializable = entityCache.getResult(
			JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JIRAProjectVersion jiraProjectVersion =
			(JIRAProjectVersion)serializable;

		if (jiraProjectVersion == null) {
			Session session = null;

			try {
				session = openSession();

				jiraProjectVersion = (JIRAProjectVersion)session.get(
					JIRAProjectVersionImpl.class, primaryKey);

				if (jiraProjectVersion != null) {
					cacheResult(jiraProjectVersion);
				}
				else {
					entityCache.putResult(
						JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
						JIRAProjectVersionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectVersionImpl.class, primaryKey);

				throw processException(exception);
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

		Map<Serializable, JIRAProjectVersion> map =
			new HashMap<Serializable, JIRAProjectVersion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAProjectVersion jiraProjectVersion = fetchByPrimaryKey(
				primaryKey);

			if (jiraProjectVersion != null) {
				map.put(primaryKey, jiraProjectVersion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_JIRAPROJECTVERSION_WHERE_PKS_IN);

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

			for (JIRAProjectVersion jiraProjectVersion :
					(List<JIRAProjectVersion>)query.list()) {

				map.put(
					jiraProjectVersion.getPrimaryKeyObj(), jiraProjectVersion);

				cacheResult(jiraProjectVersion);

				uncachedPrimaryKeys.remove(
					jiraProjectVersion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAProjectVersionImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectVersionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira project versions
	 * @param end the upper bound of the range of jira project versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira project versions
	 */
	@Override
	public List<JIRAProjectVersion> findAll(
		int start, int end,
		OrderByComparator<JIRAProjectVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira project versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAProjectVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira project versions
	 * @param end the upper bound of the range of jira project versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jira project versions
	 */
	@Override
	public List<JIRAProjectVersion> findAll(
		int start, int end,
		OrderByComparator<JIRAProjectVersion> orderByComparator,
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

		List<JIRAProjectVersion> list = null;

		if (useFinderCache) {
			list = (List<JIRAProjectVersion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_JIRAPROJECTVERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAPROJECTVERSION;

				sql = sql.concat(JIRAProjectVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<JIRAProjectVersion>)QueryUtil.list(
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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_JIRAPROJECTVERSION);

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
		return JIRAProjectVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jira project version persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionModelImpl.FINDER_CACHE_ENABLED,
			JIRAProjectVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionModelImpl.FINDER_CACHE_ENABLED,
			JIRAProjectVersionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			JIRAProjectVersionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAProjectVersionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
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

	private static final String _SQL_SELECT_JIRAPROJECTVERSION =
		"SELECT jiraProjectVersion FROM JIRAProjectVersion jiraProjectVersion";

	private static final String _SQL_SELECT_JIRAPROJECTVERSION_WHERE_PKS_IN =
		"SELECT jiraProjectVersion FROM JIRAProjectVersion jiraProjectVersion WHERE id IN (";

	private static final String _SQL_COUNT_JIRAPROJECTVERSION =
		"SELECT COUNT(jiraProjectVersion) FROM JIRAProjectVersion jiraProjectVersion";

	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraProjectVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No JIRAProjectVersion exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		JIRAProjectVersionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"jiraProjectVersionId", "jiraProjectId", "name"});

}
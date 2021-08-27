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

package com.liferay.osb.customer.admin.service.persistence.impl;

import com.liferay.osb.customer.admin.exception.NoSuchProjectSolutionException;
import com.liferay.osb.customer.admin.model.ProjectSolution;
import com.liferay.osb.customer.admin.model.impl.ProjectSolutionImpl;
import com.liferay.osb.customer.admin.model.impl.ProjectSolutionModelImpl;
import com.liferay.osb.customer.admin.service.persistence.ProjectSolutionPersistence;
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
 * The persistence implementation for the project solution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProjectSolutionPersistenceImpl
	extends BasePersistenceImpl<ProjectSolution>
	implements ProjectSolutionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProjectSolutionUtil</code> to access the project solution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProjectSolutionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ProjectSolutionPersistenceImpl() {
		setModelClass(ProjectSolution.class);
	}

	/**
	 * Caches the project solution in the entity cache if it is enabled.
	 *
	 * @param projectSolution the project solution
	 */
	@Override
	public void cacheResult(ProjectSolution projectSolution) {
		entityCache.putResult(
			ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
			ProjectSolutionImpl.class, projectSolution.getPrimaryKey(),
			projectSolution);

		projectSolution.resetOriginalValues();
	}

	/**
	 * Caches the project solutions in the entity cache if it is enabled.
	 *
	 * @param projectSolutions the project solutions
	 */
	@Override
	public void cacheResult(List<ProjectSolution> projectSolutions) {
		for (ProjectSolution projectSolution : projectSolutions) {
			if (entityCache.getResult(
					ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
					ProjectSolutionImpl.class,
					projectSolution.getPrimaryKey()) == null) {

				cacheResult(projectSolution);
			}
			else {
				projectSolution.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all project solutions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectSolutionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the project solution.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectSolution projectSolution) {
		entityCache.removeResult(
			ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
			ProjectSolutionImpl.class, projectSolution.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectSolution> projectSolutions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectSolution projectSolution : projectSolutions) {
			entityCache.removeResult(
				ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
				ProjectSolutionImpl.class, projectSolution.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
				ProjectSolutionImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new project solution with the primary key. Does not add the project solution to the database.
	 *
	 * @param salesforceProjectKey the primary key for the new project solution
	 * @return the new project solution
	 */
	@Override
	public ProjectSolution create(String salesforceProjectKey) {
		ProjectSolution projectSolution = new ProjectSolutionImpl();

		projectSolution.setNew(true);
		projectSolution.setPrimaryKey(salesforceProjectKey);

		return projectSolution;
	}

	/**
	 * Removes the project solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution that was removed
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	@Override
	public ProjectSolution remove(String salesforceProjectKey)
		throws NoSuchProjectSolutionException {

		return remove((Serializable)salesforceProjectKey);
	}

	/**
	 * Removes the project solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the project solution
	 * @return the project solution that was removed
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	@Override
	public ProjectSolution remove(Serializable primaryKey)
		throws NoSuchProjectSolutionException {

		Session session = null;

		try {
			session = openSession();

			ProjectSolution projectSolution = (ProjectSolution)session.get(
				ProjectSolutionImpl.class, primaryKey);

			if (projectSolution == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectSolutionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(projectSolution);
		}
		catch (NoSuchProjectSolutionException noSuchEntityException) {
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
	protected ProjectSolution removeImpl(ProjectSolution projectSolution) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectSolution)) {
				projectSolution = (ProjectSolution)session.get(
					ProjectSolutionImpl.class,
					projectSolution.getPrimaryKeyObj());
			}

			if (projectSolution != null) {
				session.delete(projectSolution);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (projectSolution != null) {
			clearCache(projectSolution);
		}

		return projectSolution;
	}

	@Override
	public ProjectSolution updateImpl(ProjectSolution projectSolution) {
		boolean isNew = projectSolution.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(projectSolution);

				projectSolution.setNew(false);
			}
			else {
				projectSolution = (ProjectSolution)session.merge(
					projectSolution);
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
			ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
			ProjectSolutionImpl.class, projectSolution.getPrimaryKey(),
			projectSolution, false);

		projectSolution.resetOriginalValues();

		return projectSolution;
	}

	/**
	 * Returns the project solution with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the project solution
	 * @return the project solution
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	@Override
	public ProjectSolution findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectSolutionException {

		ProjectSolution projectSolution = fetchByPrimaryKey(primaryKey);

		if (projectSolution == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectSolutionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return projectSolution;
	}

	/**
	 * Returns the project solution with the primary key or throws a <code>NoSuchProjectSolutionException</code> if it could not be found.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	@Override
	public ProjectSolution findByPrimaryKey(String salesforceProjectKey)
		throws NoSuchProjectSolutionException {

		return findByPrimaryKey((Serializable)salesforceProjectKey);
	}

	/**
	 * Returns the project solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the project solution
	 * @return the project solution, or <code>null</code> if a project solution with the primary key could not be found
	 */
	@Override
	public ProjectSolution fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
			ProjectSolutionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectSolution projectSolution = (ProjectSolution)serializable;

		if (projectSolution == null) {
			Session session = null;

			try {
				session = openSession();

				projectSolution = (ProjectSolution)session.get(
					ProjectSolutionImpl.class, primaryKey);

				if (projectSolution != null) {
					cacheResult(projectSolution);
				}
				else {
					entityCache.putResult(
						ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
						ProjectSolutionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
					ProjectSolutionImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return projectSolution;
	}

	/**
	 * Returns the project solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution, or <code>null</code> if a project solution with the primary key could not be found
	 */
	@Override
	public ProjectSolution fetchByPrimaryKey(String salesforceProjectKey) {
		return fetchByPrimaryKey((Serializable)salesforceProjectKey);
	}

	@Override
	public Map<Serializable, ProjectSolution> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectSolution> map =
			new HashMap<Serializable, ProjectSolution>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectSolution projectSolution = fetchByPrimaryKey(primaryKey);

			if (projectSolution != null) {
				map.put(primaryKey, projectSolution);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
				ProjectSolutionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectSolution)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			(uncachedPrimaryKeys.size() * 2) + 1);

		sb.append(_SQL_SELECT_PROJECTSOLUTION_WHERE_PKS_IN);

		for (int i = 0; i < uncachedPrimaryKeys.size(); i++) {
			sb.append("?");

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			QueryPos queryPos = QueryPos.getInstance(query);

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				queryPos.add((String)primaryKey);
			}

			for (ProjectSolution projectSolution :
					(List<ProjectSolution>)query.list()) {

				map.put(projectSolution.getPrimaryKeyObj(), projectSolution);

				cacheResult(projectSolution);

				uncachedPrimaryKeys.remove(projectSolution.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
					ProjectSolutionImpl.class, primaryKey, nullModel);
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
	 * Returns all the project solutions.
	 *
	 * @return the project solutions
	 */
	@Override
	public List<ProjectSolution> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the project solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project solutions
	 * @param end the upper bound of the range of project solutions (not inclusive)
	 * @return the range of project solutions
	 */
	@Override
	public List<ProjectSolution> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the project solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project solutions
	 * @param end the upper bound of the range of project solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of project solutions
	 */
	@Override
	public List<ProjectSolution> findAll(
		int start, int end,
		OrderByComparator<ProjectSolution> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the project solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project solutions
	 * @param end the upper bound of the range of project solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of project solutions
	 */
	@Override
	public List<ProjectSolution> findAll(
		int start, int end,
		OrderByComparator<ProjectSolution> orderByComparator,
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

		List<ProjectSolution> list = null;

		if (useFinderCache) {
			list = (List<ProjectSolution>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROJECTSOLUTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTSOLUTION;

				sql = sql.concat(ProjectSolutionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProjectSolution>)QueryUtil.list(
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
	 * Removes all the project solutions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectSolution projectSolution : findAll()) {
			remove(projectSolution);
		}
	}

	/**
	 * Returns the number of project solutions.
	 *
	 * @return the number of project solutions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROJECTSOLUTION);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return ProjectSolutionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the project solution persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
			ProjectSolutionModelImpl.FINDER_CACHE_ENABLED,
			ProjectSolutionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
			ProjectSolutionModelImpl.FINDER_CACHE_ENABLED,
			ProjectSolutionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ProjectSolutionModelImpl.ENTITY_CACHE_ENABLED,
			ProjectSolutionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(ProjectSolutionImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PROJECTSOLUTION =
		"SELECT projectSolution FROM ProjectSolution projectSolution";

	private static final String _SQL_SELECT_PROJECTSOLUTION_WHERE_PKS_IN =
		"SELECT projectSolution FROM ProjectSolution projectSolution WHERE salesforceProjectKey IN (";

	private static final String _SQL_COUNT_PROJECTSOLUTION =
		"SELECT COUNT(projectSolution) FROM ProjectSolution projectSolution";

	private static final String _ORDER_BY_ENTITY_ALIAS = "projectSolution.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProjectSolution exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ProjectSolutionPersistenceImpl.class);

}
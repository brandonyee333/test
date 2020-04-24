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

package com.liferay.osb.testray.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayRoutineException;
import com.liferay.osb.testray.model.TestrayRoutine;
import com.liferay.osb.testray.model.impl.TestrayRoutineImpl;
import com.liferay.osb.testray.model.impl.TestrayRoutineModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayRoutinePersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the testray routine service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayRoutinePersistenceImpl
	extends BasePersistenceImpl<TestrayRoutine>
	implements TestrayRoutinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TestrayRoutineUtil</code> to access the testray routine persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TestrayRoutineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TestrayRoutinePersistenceImpl() {
		setModelClass(TestrayRoutine.class);
	}

	/**
	 * Caches the testray routine in the entity cache if it is enabled.
	 *
	 * @param testrayRoutine the testray routine
	 */
	@Override
	public void cacheResult(TestrayRoutine testrayRoutine) {
		entityCache.putResult(
			TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRoutineImpl.class, testrayRoutine.getPrimaryKey(),
			testrayRoutine);

		testrayRoutine.resetOriginalValues();
	}

	/**
	 * Caches the testray routines in the entity cache if it is enabled.
	 *
	 * @param testrayRoutines the testray routines
	 */
	@Override
	public void cacheResult(List<TestrayRoutine> testrayRoutines) {
		for (TestrayRoutine testrayRoutine : testrayRoutines) {
			if (entityCache.getResult(
					TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRoutineImpl.class, testrayRoutine.getPrimaryKey()) ==
						null) {

				cacheResult(testrayRoutine);
			}
			else {
				testrayRoutine.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray routines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayRoutineImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray routine.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayRoutine testrayRoutine) {
		entityCache.removeResult(
			TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRoutineImpl.class, testrayRoutine.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TestrayRoutine> testrayRoutines) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayRoutine testrayRoutine : testrayRoutines) {
			entityCache.removeResult(
				TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
				TestrayRoutineImpl.class, testrayRoutine.getPrimaryKey());
		}
	}

	/**
	 * Creates a new testray routine with the primary key. Does not add the testray routine to the database.
	 *
	 * @param testrayRoutineId the primary key for the new testray routine
	 * @return the new testray routine
	 */
	@Override
	public TestrayRoutine create(long testrayRoutineId) {
		TestrayRoutine testrayRoutine = new TestrayRoutineImpl();

		testrayRoutine.setNew(true);
		testrayRoutine.setPrimaryKey(testrayRoutineId);

		testrayRoutine.setCompanyId(companyProvider.getCompanyId());

		return testrayRoutine;
	}

	/**
	 * Removes the testray routine with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRoutineId the primary key of the testray routine
	 * @return the testray routine that was removed
	 * @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	 */
	@Override
	public TestrayRoutine remove(long testrayRoutineId)
		throws NoSuchTestrayRoutineException {

		return remove((Serializable)testrayRoutineId);
	}

	/**
	 * Removes the testray routine with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray routine
	 * @return the testray routine that was removed
	 * @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	 */
	@Override
	public TestrayRoutine remove(Serializable primaryKey)
		throws NoSuchTestrayRoutineException {

		Session session = null;

		try {
			session = openSession();

			TestrayRoutine testrayRoutine = (TestrayRoutine)session.get(
				TestrayRoutineImpl.class, primaryKey);

			if (testrayRoutine == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayRoutineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(testrayRoutine);
		}
		catch (NoSuchTestrayRoutineException nsee) {
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
	protected TestrayRoutine removeImpl(TestrayRoutine testrayRoutine) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayRoutine)) {
				testrayRoutine = (TestrayRoutine)session.get(
					TestrayRoutineImpl.class,
					testrayRoutine.getPrimaryKeyObj());
			}

			if (testrayRoutine != null) {
				session.delete(testrayRoutine);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayRoutine != null) {
			clearCache(testrayRoutine);
		}

		return testrayRoutine;
	}

	@Override
	public TestrayRoutine updateImpl(TestrayRoutine testrayRoutine) {
		boolean isNew = testrayRoutine.isNew();

		if (!(testrayRoutine instanceof TestrayRoutineModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(testrayRoutine.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					testrayRoutine);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in testrayRoutine proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TestrayRoutine implementation " +
					testrayRoutine.getClass());
		}

		TestrayRoutineModelImpl testrayRoutineModelImpl =
			(TestrayRoutineModelImpl)testrayRoutine;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayRoutine.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayRoutine.setCreateDate(now);
			}
			else {
				testrayRoutine.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!testrayRoutineModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayRoutine.setModifiedDate(now);
			}
			else {
				testrayRoutine.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayRoutine.isNew()) {
				session.save(testrayRoutine);

				testrayRoutine.setNew(false);
			}
			else {
				testrayRoutine = (TestrayRoutine)session.merge(testrayRoutine);
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
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRoutineImpl.class, testrayRoutine.getPrimaryKey(),
			testrayRoutine, false);

		testrayRoutine.resetOriginalValues();

		return testrayRoutine;
	}

	/**
	 * Returns the testray routine with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray routine
	 * @return the testray routine
	 * @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	 */
	@Override
	public TestrayRoutine findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayRoutineException {

		TestrayRoutine testrayRoutine = fetchByPrimaryKey(primaryKey);

		if (testrayRoutine == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayRoutineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return testrayRoutine;
	}

	/**
	 * Returns the testray routine with the primary key or throws a <code>NoSuchTestrayRoutineException</code> if it could not be found.
	 *
	 * @param testrayRoutineId the primary key of the testray routine
	 * @return the testray routine
	 * @throws NoSuchTestrayRoutineException if a testray routine with the primary key could not be found
	 */
	@Override
	public TestrayRoutine findByPrimaryKey(long testrayRoutineId)
		throws NoSuchTestrayRoutineException {

		return findByPrimaryKey((Serializable)testrayRoutineId);
	}

	/**
	 * Returns the testray routine with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray routine
	 * @return the testray routine, or <code>null</code> if a testray routine with the primary key could not be found
	 */
	@Override
	public TestrayRoutine fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRoutineImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayRoutine testrayRoutine = (TestrayRoutine)serializable;

		if (testrayRoutine == null) {
			Session session = null;

			try {
				session = openSession();

				testrayRoutine = (TestrayRoutine)session.get(
					TestrayRoutineImpl.class, primaryKey);

				if (testrayRoutine != null) {
					cacheResult(testrayRoutine);
				}
				else {
					entityCache.putResult(
						TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
						TestrayRoutineImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRoutineImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayRoutine;
	}

	/**
	 * Returns the testray routine with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayRoutineId the primary key of the testray routine
	 * @return the testray routine, or <code>null</code> if a testray routine with the primary key could not be found
	 */
	@Override
	public TestrayRoutine fetchByPrimaryKey(long testrayRoutineId) {
		return fetchByPrimaryKey((Serializable)testrayRoutineId);
	}

	@Override
	public Map<Serializable, TestrayRoutine> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayRoutine> map =
			new HashMap<Serializable, TestrayRoutine>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayRoutine testrayRoutine = fetchByPrimaryKey(primaryKey);

			if (testrayRoutine != null) {
				map.put(primaryKey, testrayRoutine);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
				TestrayRoutineImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayRoutine)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_TESTRAYROUTINE_WHERE_PKS_IN);

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

			for (TestrayRoutine testrayRoutine :
					(List<TestrayRoutine>)q.list()) {

				map.put(testrayRoutine.getPrimaryKeyObj(), testrayRoutine);

				cacheResult(testrayRoutine);

				uncachedPrimaryKeys.remove(testrayRoutine.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRoutineImpl.class, primaryKey, nullModel);
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
	 * Returns all the testray routines.
	 *
	 * @return the testray routines
	 */
	@Override
	public List<TestrayRoutine> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray routines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @return the range of testray routines
	 */
	@Override
	public List<TestrayRoutine> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray routines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray routines
	 */
	@Override
	public List<TestrayRoutine> findAll(
		int start, int end,
		OrderByComparator<TestrayRoutine> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray routines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRoutineModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray routines
	 */
	@Override
	public List<TestrayRoutine> findAll(
		int start, int end, OrderByComparator<TestrayRoutine> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<TestrayRoutine> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayRoutine>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYROUTINE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYROUTINE;

				if (pagination) {
					sql = sql.concat(TestrayRoutineModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayRoutine>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayRoutine>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Removes all the testray routines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayRoutine testrayRoutine : findAll()) {
			remove(testrayRoutine);
		}
	}

	/**
	 * Returns the number of testray routines.
	 *
	 * @return the number of testray routines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYROUTINE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

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
		return TestrayRoutineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray routine persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRoutineModelImpl.FINDER_CACHE_ENABLED,
			TestrayRoutineImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRoutineModelImpl.FINDER_CACHE_ENABLED,
			TestrayRoutineImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			TestrayRoutineModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRoutineModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(TestrayRoutineImpl.class.getName());
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

	private static final String _SQL_SELECT_TESTRAYROUTINE =
		"SELECT testrayRoutine FROM TestrayRoutine testrayRoutine";

	private static final String _SQL_SELECT_TESTRAYROUTINE_WHERE_PKS_IN =
		"SELECT testrayRoutine FROM TestrayRoutine testrayRoutine WHERE testrayRoutineId IN (";

	private static final String _SQL_COUNT_TESTRAYROUTINE =
		"SELECT COUNT(testrayRoutine) FROM TestrayRoutine testrayRoutine";

	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayRoutine.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TestrayRoutine exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TestrayRoutinePersistenceImpl.class);

}
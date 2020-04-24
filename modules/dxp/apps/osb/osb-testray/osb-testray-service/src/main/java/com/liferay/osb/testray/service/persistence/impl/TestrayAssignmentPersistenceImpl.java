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

import com.liferay.osb.testray.exception.NoSuchTestrayAssignmentException;
import com.liferay.osb.testray.model.TestrayAssignment;
import com.liferay.osb.testray.model.impl.TestrayAssignmentImpl;
import com.liferay.osb.testray.model.impl.TestrayAssignmentModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayAssignmentPersistence;
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
 * The persistence implementation for the testray assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayAssignmentPersistenceImpl
	extends BasePersistenceImpl<TestrayAssignment>
	implements TestrayAssignmentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TestrayAssignmentUtil</code> to access the testray assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TestrayAssignmentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public TestrayAssignmentPersistenceImpl() {
		setModelClass(TestrayAssignment.class);
	}

	/**
	 * Caches the testray assignment in the entity cache if it is enabled.
	 *
	 * @param testrayAssignment the testray assignment
	 */
	@Override
	public void cacheResult(TestrayAssignment testrayAssignment) {
		entityCache.putResult(
			TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			TestrayAssignmentImpl.class, testrayAssignment.getPrimaryKey(),
			testrayAssignment);

		testrayAssignment.resetOriginalValues();
	}

	/**
	 * Caches the testray assignments in the entity cache if it is enabled.
	 *
	 * @param testrayAssignments the testray assignments
	 */
	@Override
	public void cacheResult(List<TestrayAssignment> testrayAssignments) {
		for (TestrayAssignment testrayAssignment : testrayAssignments) {
			if (entityCache.getResult(
					TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					TestrayAssignmentImpl.class,
					testrayAssignment.getPrimaryKey()) == null) {

				cacheResult(testrayAssignment);
			}
			else {
				testrayAssignment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray assignments.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayAssignmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray assignment.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayAssignment testrayAssignment) {
		entityCache.removeResult(
			TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			TestrayAssignmentImpl.class, testrayAssignment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TestrayAssignment> testrayAssignments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayAssignment testrayAssignment : testrayAssignments) {
			entityCache.removeResult(
				TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				TestrayAssignmentImpl.class, testrayAssignment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new testray assignment with the primary key. Does not add the testray assignment to the database.
	 *
	 * @param testrayAssignmentId the primary key for the new testray assignment
	 * @return the new testray assignment
	 */
	@Override
	public TestrayAssignment create(long testrayAssignmentId) {
		TestrayAssignment testrayAssignment = new TestrayAssignmentImpl();

		testrayAssignment.setNew(true);
		testrayAssignment.setPrimaryKey(testrayAssignmentId);

		testrayAssignment.setCompanyId(companyProvider.getCompanyId());

		return testrayAssignment;
	}

	/**
	 * Removes the testray assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayAssignmentId the primary key of the testray assignment
	 * @return the testray assignment that was removed
	 * @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	 */
	@Override
	public TestrayAssignment remove(long testrayAssignmentId)
		throws NoSuchTestrayAssignmentException {

		return remove((Serializable)testrayAssignmentId);
	}

	/**
	 * Removes the testray assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray assignment
	 * @return the testray assignment that was removed
	 * @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	 */
	@Override
	public TestrayAssignment remove(Serializable primaryKey)
		throws NoSuchTestrayAssignmentException {

		Session session = null;

		try {
			session = openSession();

			TestrayAssignment testrayAssignment =
				(TestrayAssignment)session.get(
					TestrayAssignmentImpl.class, primaryKey);

			if (testrayAssignment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayAssignmentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(testrayAssignment);
		}
		catch (NoSuchTestrayAssignmentException nsee) {
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
	protected TestrayAssignment removeImpl(
		TestrayAssignment testrayAssignment) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayAssignment)) {
				testrayAssignment = (TestrayAssignment)session.get(
					TestrayAssignmentImpl.class,
					testrayAssignment.getPrimaryKeyObj());
			}

			if (testrayAssignment != null) {
				session.delete(testrayAssignment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayAssignment != null) {
			clearCache(testrayAssignment);
		}

		return testrayAssignment;
	}

	@Override
	public TestrayAssignment updateImpl(TestrayAssignment testrayAssignment) {
		boolean isNew = testrayAssignment.isNew();

		if (!(testrayAssignment instanceof TestrayAssignmentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(testrayAssignment.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					testrayAssignment);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in testrayAssignment proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TestrayAssignment implementation " +
					testrayAssignment.getClass());
		}

		TestrayAssignmentModelImpl testrayAssignmentModelImpl =
			(TestrayAssignmentModelImpl)testrayAssignment;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayAssignment.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayAssignment.setCreateDate(now);
			}
			else {
				testrayAssignment.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!testrayAssignmentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayAssignment.setModifiedDate(now);
			}
			else {
				testrayAssignment.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayAssignment.isNew()) {
				session.save(testrayAssignment);

				testrayAssignment.setNew(false);
			}
			else {
				testrayAssignment = (TestrayAssignment)session.merge(
					testrayAssignment);
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
			TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			TestrayAssignmentImpl.class, testrayAssignment.getPrimaryKey(),
			testrayAssignment, false);

		testrayAssignment.resetOriginalValues();

		return testrayAssignment;
	}

	/**
	 * Returns the testray assignment with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray assignment
	 * @return the testray assignment
	 * @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	 */
	@Override
	public TestrayAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayAssignmentException {

		TestrayAssignment testrayAssignment = fetchByPrimaryKey(primaryKey);

		if (testrayAssignment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayAssignmentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return testrayAssignment;
	}

	/**
	 * Returns the testray assignment with the primary key or throws a <code>NoSuchTestrayAssignmentException</code> if it could not be found.
	 *
	 * @param testrayAssignmentId the primary key of the testray assignment
	 * @return the testray assignment
	 * @throws NoSuchTestrayAssignmentException if a testray assignment with the primary key could not be found
	 */
	@Override
	public TestrayAssignment findByPrimaryKey(long testrayAssignmentId)
		throws NoSuchTestrayAssignmentException {

		return findByPrimaryKey((Serializable)testrayAssignmentId);
	}

	/**
	 * Returns the testray assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray assignment
	 * @return the testray assignment, or <code>null</code> if a testray assignment with the primary key could not be found
	 */
	@Override
	public TestrayAssignment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			TestrayAssignmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayAssignment testrayAssignment = (TestrayAssignment)serializable;

		if (testrayAssignment == null) {
			Session session = null;

			try {
				session = openSession();

				testrayAssignment = (TestrayAssignment)session.get(
					TestrayAssignmentImpl.class, primaryKey);

				if (testrayAssignment != null) {
					cacheResult(testrayAssignment);
				}
				else {
					entityCache.putResult(
						TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						TestrayAssignmentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					TestrayAssignmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayAssignment;
	}

	/**
	 * Returns the testray assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayAssignmentId the primary key of the testray assignment
	 * @return the testray assignment, or <code>null</code> if a testray assignment with the primary key could not be found
	 */
	@Override
	public TestrayAssignment fetchByPrimaryKey(long testrayAssignmentId) {
		return fetchByPrimaryKey((Serializable)testrayAssignmentId);
	}

	@Override
	public Map<Serializable, TestrayAssignment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayAssignment> map =
			new HashMap<Serializable, TestrayAssignment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayAssignment testrayAssignment = fetchByPrimaryKey(primaryKey);

			if (testrayAssignment != null) {
				map.put(primaryKey, testrayAssignment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				TestrayAssignmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayAssignment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_TESTRAYASSIGNMENT_WHERE_PKS_IN);

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

			for (TestrayAssignment testrayAssignment :
					(List<TestrayAssignment>)q.list()) {

				map.put(
					testrayAssignment.getPrimaryKeyObj(), testrayAssignment);

				cacheResult(testrayAssignment);

				uncachedPrimaryKeys.remove(
					testrayAssignment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					TestrayAssignmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the testray assignments.
	 *
	 * @return the testray assignments
	 */
	@Override
	public List<TestrayAssignment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayAssignmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray assignments
	 * @param end the upper bound of the range of testray assignments (not inclusive)
	 * @return the range of testray assignments
	 */
	@Override
	public List<TestrayAssignment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayAssignmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray assignments
	 * @param end the upper bound of the range of testray assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray assignments
	 */
	@Override
	public List<TestrayAssignment> findAll(
		int start, int end,
		OrderByComparator<TestrayAssignment> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayAssignmentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray assignments
	 * @param end the upper bound of the range of testray assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray assignments
	 */
	@Override
	public List<TestrayAssignment> findAll(
		int start, int end,
		OrderByComparator<TestrayAssignment> orderByComparator,
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

		List<TestrayAssignment> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayAssignment>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYASSIGNMENT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYASSIGNMENT;

				if (pagination) {
					sql = sql.concat(TestrayAssignmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayAssignment>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayAssignment>)QueryUtil.list(
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
	 * Removes all the testray assignments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayAssignment testrayAssignment : findAll()) {
			remove(testrayAssignment);
		}
	}

	/**
	 * Returns the number of testray assignments.
	 *
	 * @return the number of testray assignments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYASSIGNMENT);

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
		return TestrayAssignmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray assignment persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			TestrayAssignmentModelImpl.FINDER_CACHE_ENABLED,
			TestrayAssignmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			TestrayAssignmentModelImpl.FINDER_CACHE_ENABLED,
			TestrayAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			TestrayAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			TestrayAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(TestrayAssignmentImpl.class.getName());
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

	private static final String _SQL_SELECT_TESTRAYASSIGNMENT =
		"SELECT testrayAssignment FROM TestrayAssignment testrayAssignment";

	private static final String _SQL_SELECT_TESTRAYASSIGNMENT_WHERE_PKS_IN =
		"SELECT testrayAssignment FROM TestrayAssignment testrayAssignment WHERE testrayAssignmentId IN (";

	private static final String _SQL_COUNT_TESTRAYASSIGNMENT =
		"SELECT COUNT(testrayAssignment) FROM TestrayAssignment testrayAssignment";

	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayAssignment.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TestrayAssignment exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		TestrayAssignmentPersistenceImpl.class);

}
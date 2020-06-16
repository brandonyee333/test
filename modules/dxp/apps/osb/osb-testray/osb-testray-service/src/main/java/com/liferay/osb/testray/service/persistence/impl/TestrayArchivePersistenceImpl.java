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

package com.liferay.osb.testray.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayArchiveException;
import com.liferay.osb.testray.model.TestrayArchive;
import com.liferay.osb.testray.model.impl.TestrayArchiveImpl;
import com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayArchivePersistence;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

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
 * The persistence implementation for the testray archive service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayArchivePersistence
 * @see com.liferay.osb.testray.service.persistence.TestrayArchiveUtil
 * @generated
 */
@ProviderType
public class TestrayArchivePersistenceImpl extends BasePersistenceImpl<TestrayArchive>
	implements TestrayArchivePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TestrayArchiveUtil} to access the testray archive persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TestrayArchiveImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
			TestrayArchiveModelImpl.FINDER_CACHE_ENABLED,
			TestrayArchiveImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
			TestrayArchiveModelImpl.FINDER_CACHE_ENABLED,
			TestrayArchiveImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
			TestrayArchiveModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public TestrayArchivePersistenceImpl() {
		setModelClass(TestrayArchive.class);
	}

	/**
	 * Caches the testray archive in the entity cache if it is enabled.
	 *
	 * @param testrayArchive the testray archive
	 */
	@Override
	public void cacheResult(TestrayArchive testrayArchive) {
		entityCache.putResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
			TestrayArchiveImpl.class, testrayArchive.getPrimaryKey(),
			testrayArchive);

		testrayArchive.resetOriginalValues();
	}

	/**
	 * Caches the testray archives in the entity cache if it is enabled.
	 *
	 * @param testrayArchives the testray archives
	 */
	@Override
	public void cacheResult(List<TestrayArchive> testrayArchives) {
		for (TestrayArchive testrayArchive : testrayArchives) {
			if (entityCache.getResult(
						TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
						TestrayArchiveImpl.class, testrayArchive.getPrimaryKey()) == null) {
				cacheResult(testrayArchive);
			}
			else {
				testrayArchive.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray archives.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayArchiveImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray archive.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayArchive testrayArchive) {
		entityCache.removeResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
			TestrayArchiveImpl.class, testrayArchive.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TestrayArchive> testrayArchives) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayArchive testrayArchive : testrayArchives) {
			entityCache.removeResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
				TestrayArchiveImpl.class, testrayArchive.getPrimaryKey());
		}
	}

	/**
	 * Creates a new testray archive with the primary key. Does not add the testray archive to the database.
	 *
	 * @param testrayArchiveId the primary key for the new testray archive
	 * @return the new testray archive
	 */
	@Override
	public TestrayArchive create(long testrayArchiveId) {
		TestrayArchive testrayArchive = new TestrayArchiveImpl();

		testrayArchive.setNew(true);
		testrayArchive.setPrimaryKey(testrayArchiveId);

		testrayArchive.setCompanyId(companyProvider.getCompanyId());

		return testrayArchive;
	}

	/**
	 * Removes the testray archive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive that was removed
	 * @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	 */
	@Override
	public TestrayArchive remove(long testrayArchiveId)
		throws NoSuchTestrayArchiveException {
		return remove((Serializable)testrayArchiveId);
	}

	/**
	 * Removes the testray archive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray archive
	 * @return the testray archive that was removed
	 * @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	 */
	@Override
	public TestrayArchive remove(Serializable primaryKey)
		throws NoSuchTestrayArchiveException {
		Session session = null;

		try {
			session = openSession();

			TestrayArchive testrayArchive = (TestrayArchive)session.get(TestrayArchiveImpl.class,
					primaryKey);

			if (testrayArchive == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayArchiveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(testrayArchive);
		}
		catch (NoSuchTestrayArchiveException nsee) {
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
	protected TestrayArchive removeImpl(TestrayArchive testrayArchive) {
		testrayArchive = toUnwrappedModel(testrayArchive);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayArchive)) {
				testrayArchive = (TestrayArchive)session.get(TestrayArchiveImpl.class,
						testrayArchive.getPrimaryKeyObj());
			}

			if (testrayArchive != null) {
				session.delete(testrayArchive);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayArchive != null) {
			clearCache(testrayArchive);
		}

		return testrayArchive;
	}

	@Override
	public TestrayArchive updateImpl(TestrayArchive testrayArchive) {
		testrayArchive = toUnwrappedModel(testrayArchive);

		boolean isNew = testrayArchive.isNew();

		TestrayArchiveModelImpl testrayArchiveModelImpl = (TestrayArchiveModelImpl)testrayArchive;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayArchive.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayArchive.setCreateDate(now);
			}
			else {
				testrayArchive.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!testrayArchiveModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayArchive.setModifiedDate(now);
			}
			else {
				testrayArchive.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayArchive.isNew()) {
				session.save(testrayArchive);

				testrayArchive.setNew(false);
			}
			else {
				session.evict(testrayArchive);
				session.saveOrUpdate(testrayArchive);
			}

			session.flush();
			session.clear();
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

		entityCache.putResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
			TestrayArchiveImpl.class, testrayArchive.getPrimaryKey(),
			testrayArchive, false);

		testrayArchive.resetOriginalValues();

		return testrayArchive;
	}

	protected TestrayArchive toUnwrappedModel(TestrayArchive testrayArchive) {
		if (testrayArchive instanceof TestrayArchiveImpl) {
			return testrayArchive;
		}

		TestrayArchiveImpl testrayArchiveImpl = new TestrayArchiveImpl();

		testrayArchiveImpl.setNew(testrayArchive.isNew());
		testrayArchiveImpl.setPrimaryKey(testrayArchive.getPrimaryKey());

		testrayArchiveImpl.setTestrayArchiveId(testrayArchive.getTestrayArchiveId());
		testrayArchiveImpl.setGroupId(testrayArchive.getGroupId());
		testrayArchiveImpl.setCompanyId(testrayArchive.getCompanyId());
		testrayArchiveImpl.setUserId(testrayArchive.getUserId());
		testrayArchiveImpl.setUserName(testrayArchive.getUserName());
		testrayArchiveImpl.setCreateDate(testrayArchive.getCreateDate());
		testrayArchiveImpl.setModifiedDate(testrayArchive.getModifiedDate());
		testrayArchiveImpl.setClassNameId(testrayArchive.getClassNameId());
		testrayArchiveImpl.setClassPK(testrayArchive.getClassPK());
		testrayArchiveImpl.setCompressedData(testrayArchive.getCompressedData());

		return testrayArchiveImpl;
	}

	/**
	 * Returns the testray archive with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray archive
	 * @return the testray archive
	 * @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	 */
	@Override
	public TestrayArchive findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayArchiveException {
		TestrayArchive testrayArchive = fetchByPrimaryKey(primaryKey);

		if (testrayArchive == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayArchiveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return testrayArchive;
	}

	/**
	 * Returns the testray archive with the primary key or throws a {@link NoSuchTestrayArchiveException} if it could not be found.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive
	 * @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	 */
	@Override
	public TestrayArchive findByPrimaryKey(long testrayArchiveId)
		throws NoSuchTestrayArchiveException {
		return findByPrimaryKey((Serializable)testrayArchiveId);
	}

	/**
	 * Returns the testray archive with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray archive
	 * @return the testray archive, or <code>null</code> if a testray archive with the primary key could not be found
	 */
	@Override
	public TestrayArchive fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
				TestrayArchiveImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayArchive testrayArchive = (TestrayArchive)serializable;

		if (testrayArchive == null) {
			Session session = null;

			try {
				session = openSession();

				testrayArchive = (TestrayArchive)session.get(TestrayArchiveImpl.class,
						primaryKey);

				if (testrayArchive != null) {
					cacheResult(testrayArchive);
				}
				else {
					entityCache.putResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
						TestrayArchiveImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
					TestrayArchiveImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayArchive;
	}

	/**
	 * Returns the testray archive with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive, or <code>null</code> if a testray archive with the primary key could not be found
	 */
	@Override
	public TestrayArchive fetchByPrimaryKey(long testrayArchiveId) {
		return fetchByPrimaryKey((Serializable)testrayArchiveId);
	}

	@Override
	public Map<Serializable, TestrayArchive> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayArchive> map = new HashMap<Serializable, TestrayArchive>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayArchive testrayArchive = fetchByPrimaryKey(primaryKey);

			if (testrayArchive != null) {
				map.put(primaryKey, testrayArchive);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
					TestrayArchiveImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayArchive)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TESTRAYARCHIVE_WHERE_PKS_IN);

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

			for (TestrayArchive testrayArchive : (List<TestrayArchive>)q.list()) {
				map.put(testrayArchive.getPrimaryKeyObj(), testrayArchive);

				cacheResult(testrayArchive);

				uncachedPrimaryKeys.remove(testrayArchive.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TestrayArchiveModelImpl.ENTITY_CACHE_ENABLED,
					TestrayArchiveImpl.class, primaryKey, nullModel);
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
	 * Returns all the testray archives.
	 *
	 * @return the testray archives
	 */
	@Override
	public List<TestrayArchive> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayArchiveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @return the range of testray archives
	 */
	@Override
	public List<TestrayArchive> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayArchiveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray archives
	 */
	@Override
	public List<TestrayArchive> findAll(int start, int end,
		OrderByComparator<TestrayArchive> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayArchiveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray archives
	 */
	@Override
	public List<TestrayArchive> findAll(int start, int end,
		OrderByComparator<TestrayArchive> orderByComparator,
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

		List<TestrayArchive> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayArchive>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYARCHIVE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYARCHIVE;

				if (pagination) {
					sql = sql.concat(TestrayArchiveModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayArchive>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayArchive>)QueryUtil.list(q,
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
	 * Removes all the testray archives from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayArchive testrayArchive : findAll()) {
			remove(testrayArchive);
		}
	}

	/**
	 * Returns the number of testray archives.
	 *
	 * @return the number of testray archives
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYARCHIVE);

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
		return TestrayArchiveModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray archive persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TestrayArchiveImpl.class.getName());
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
	private static final String _SQL_SELECT_TESTRAYARCHIVE = "SELECT testrayArchive FROM TestrayArchive testrayArchive";
	private static final String _SQL_SELECT_TESTRAYARCHIVE_WHERE_PKS_IN = "SELECT testrayArchive FROM TestrayArchive testrayArchive WHERE testrayArchiveId IN (";
	private static final String _SQL_COUNT_TESTRAYARCHIVE = "SELECT COUNT(testrayArchive) FROM TestrayArchive testrayArchive";
	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayArchive.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TestrayArchive exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(TestrayArchivePersistenceImpl.class);
}
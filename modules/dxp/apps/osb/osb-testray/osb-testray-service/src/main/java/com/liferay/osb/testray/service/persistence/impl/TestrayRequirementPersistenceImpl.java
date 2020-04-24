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

import com.liferay.osb.testray.exception.NoSuchTestrayRequirementException;
import com.liferay.osb.testray.model.TestrayRequirement;
import com.liferay.osb.testray.model.impl.TestrayRequirementImpl;
import com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayCasePersistence;
import com.liferay.osb.testray.service.persistence.TestrayRequirementPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the testray requirement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayRequirementPersistenceImpl
	extends BasePersistenceImpl<TestrayRequirement>
	implements TestrayRequirementPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TestrayRequirementUtil</code> to access the testray requirement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TestrayRequirementImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByTPI_K;
	private FinderPath _finderPathCountByTPI_K;

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or throws a <code>NoSuchTestrayRequirementException</code> if it could not be found.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the matching testray requirement
	 * @throws NoSuchTestrayRequirementException if a matching testray requirement could not be found
	 */
	@Override
	public TestrayRequirement findByTPI_K(long testrayProjectId, String key)
		throws NoSuchTestrayRequirementException {

		TestrayRequirement testrayRequirement = fetchByTPI_K(
			testrayProjectId, key);

		if (testrayRequirement == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("testrayProjectId=");
			msg.append(testrayProjectId);

			msg.append(", key=");
			msg.append(key);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTestrayRequirementException(msg.toString());
		}

		return testrayRequirement;
	}

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the matching testray requirement, or <code>null</code> if a matching testray requirement could not be found
	 */
	@Override
	public TestrayRequirement fetchByTPI_K(long testrayProjectId, String key) {
		return fetchByTPI_K(testrayProjectId, key, true);
	}

	/**
	 * Returns the testray requirement where testrayProjectId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray requirement, or <code>null</code> if a matching testray requirement could not be found
	 */
	@Override
	public TestrayRequirement fetchByTPI_K(
		long testrayProjectId, String key, boolean retrieveFromCache) {

		key = Objects.toString(key, "");

		Object[] finderArgs = new Object[] {testrayProjectId, key};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByTPI_K, finderArgs, this);
		}

		if (result instanceof TestrayRequirement) {
			TestrayRequirement testrayRequirement = (TestrayRequirement)result;

			if ((testrayProjectId !=
					testrayRequirement.getTestrayProjectId()) ||
				!Objects.equals(key, testrayRequirement.getKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TESTRAYREQUIREMENT_WHERE);

			query.append(_FINDER_COLUMN_TPI_K_TESTRAYPROJECTID_2);

			boolean bindKey = false;

			if (key.isEmpty()) {
				query.append(_FINDER_COLUMN_TPI_K_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_TPI_K_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(testrayProjectId);

				if (bindKey) {
					qPos.add(key);
				}

				List<TestrayRequirement> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByTPI_K, finderArgs, list);
				}
				else {
					TestrayRequirement testrayRequirement = list.get(0);

					result = testrayRequirement;

					cacheResult(testrayRequirement);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByTPI_K, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (TestrayRequirement)result;
		}
	}

	/**
	 * Removes the testray requirement where testrayProjectId = &#63; and key = &#63; from the database.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the testray requirement that was removed
	 */
	@Override
	public TestrayRequirement removeByTPI_K(long testrayProjectId, String key)
		throws NoSuchTestrayRequirementException {

		TestrayRequirement testrayRequirement = findByTPI_K(
			testrayProjectId, key);

		return remove(testrayRequirement);
	}

	/**
	 * Returns the number of testray requirements where testrayProjectId = &#63; and key = &#63;.
	 *
	 * @param testrayProjectId the testray project ID
	 * @param key the key
	 * @return the number of matching testray requirements
	 */
	@Override
	public int countByTPI_K(long testrayProjectId, String key) {
		key = Objects.toString(key, "");

		FinderPath finderPath = _finderPathCountByTPI_K;

		Object[] finderArgs = new Object[] {testrayProjectId, key};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TESTRAYREQUIREMENT_WHERE);

			query.append(_FINDER_COLUMN_TPI_K_TESTRAYPROJECTID_2);

			boolean bindKey = false;

			if (key.isEmpty()) {
				query.append(_FINDER_COLUMN_TPI_K_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_TPI_K_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(testrayProjectId);

				if (bindKey) {
					qPos.add(key);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TPI_K_TESTRAYPROJECTID_2 =
		"testrayRequirement.testrayProjectId = ? AND ";

	private static final String _FINDER_COLUMN_TPI_K_KEY_2 =
		"testrayRequirement.key = ?";

	private static final String _FINDER_COLUMN_TPI_K_KEY_3 =
		"(testrayRequirement.key IS NULL OR testrayRequirement.key = '')";

	public TestrayRequirementPersistenceImpl() {
		setModelClass(TestrayRequirement.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("key", "key_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the testray requirement in the entity cache if it is enabled.
	 *
	 * @param testrayRequirement the testray requirement
	 */
	@Override
	public void cacheResult(TestrayRequirement testrayRequirement) {
		entityCache.putResult(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementImpl.class, testrayRequirement.getPrimaryKey(),
			testrayRequirement);

		finderCache.putResult(
			_finderPathFetchByTPI_K,
			new Object[] {
				testrayRequirement.getTestrayProjectId(),
				testrayRequirement.getKey()
			},
			testrayRequirement);

		testrayRequirement.resetOriginalValues();
	}

	/**
	 * Caches the testray requirements in the entity cache if it is enabled.
	 *
	 * @param testrayRequirements the testray requirements
	 */
	@Override
	public void cacheResult(List<TestrayRequirement> testrayRequirements) {
		for (TestrayRequirement testrayRequirement : testrayRequirements) {
			if (entityCache.getResult(
					TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRequirementImpl.class,
					testrayRequirement.getPrimaryKey()) == null) {

				cacheResult(testrayRequirement);
			}
			else {
				testrayRequirement.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray requirements.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayRequirementImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray requirement.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayRequirement testrayRequirement) {
		entityCache.removeResult(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementImpl.class, testrayRequirement.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(TestrayRequirementModelImpl)testrayRequirement, true);
	}

	@Override
	public void clearCache(List<TestrayRequirement> testrayRequirements) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayRequirement testrayRequirement : testrayRequirements) {
			entityCache.removeResult(
				TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
				TestrayRequirementImpl.class,
				testrayRequirement.getPrimaryKey());

			clearUniqueFindersCache(
				(TestrayRequirementModelImpl)testrayRequirement, true);
		}
	}

	protected void cacheUniqueFindersCache(
		TestrayRequirementModelImpl testrayRequirementModelImpl) {

		Object[] args = new Object[] {
			testrayRequirementModelImpl.getTestrayProjectId(),
			testrayRequirementModelImpl.getKey()
		};

		finderCache.putResult(
			_finderPathCountByTPI_K, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByTPI_K, args, testrayRequirementModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TestrayRequirementModelImpl testrayRequirementModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				testrayRequirementModelImpl.getTestrayProjectId(),
				testrayRequirementModelImpl.getKey()
			};

			finderCache.removeResult(_finderPathCountByTPI_K, args);
			finderCache.removeResult(_finderPathFetchByTPI_K, args);
		}

		if ((testrayRequirementModelImpl.getColumnBitmask() &
			 _finderPathFetchByTPI_K.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				testrayRequirementModelImpl.getOriginalTestrayProjectId(),
				testrayRequirementModelImpl.getOriginalKey()
			};

			finderCache.removeResult(_finderPathCountByTPI_K, args);
			finderCache.removeResult(_finderPathFetchByTPI_K, args);
		}
	}

	/**
	 * Creates a new testray requirement with the primary key. Does not add the testray requirement to the database.
	 *
	 * @param testrayRequirementId the primary key for the new testray requirement
	 * @return the new testray requirement
	 */
	@Override
	public TestrayRequirement create(long testrayRequirementId) {
		TestrayRequirement testrayRequirement = new TestrayRequirementImpl();

		testrayRequirement.setNew(true);
		testrayRequirement.setPrimaryKey(testrayRequirementId);

		testrayRequirement.setCompanyId(companyProvider.getCompanyId());

		return testrayRequirement;
	}

	/**
	 * Removes the testray requirement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	@Override
	public TestrayRequirement remove(long testrayRequirementId)
		throws NoSuchTestrayRequirementException {

		return remove((Serializable)testrayRequirementId);
	}

	/**
	 * Removes the testray requirement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	@Override
	public TestrayRequirement remove(Serializable primaryKey)
		throws NoSuchTestrayRequirementException {

		Session session = null;

		try {
			session = openSession();

			TestrayRequirement testrayRequirement =
				(TestrayRequirement)session.get(
					TestrayRequirementImpl.class, primaryKey);

			if (testrayRequirement == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayRequirementException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(testrayRequirement);
		}
		catch (NoSuchTestrayRequirementException nsee) {
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
	protected TestrayRequirement removeImpl(
		TestrayRequirement testrayRequirement) {

		testrayRequirementToTestrayCaseTableMapper.
			deleteLeftPrimaryKeyTableMappings(
				testrayRequirement.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayRequirement)) {
				testrayRequirement = (TestrayRequirement)session.get(
					TestrayRequirementImpl.class,
					testrayRequirement.getPrimaryKeyObj());
			}

			if (testrayRequirement != null) {
				session.delete(testrayRequirement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayRequirement != null) {
			clearCache(testrayRequirement);
		}

		return testrayRequirement;
	}

	@Override
	public TestrayRequirement updateImpl(
		TestrayRequirement testrayRequirement) {

		boolean isNew = testrayRequirement.isNew();

		if (!(testrayRequirement instanceof TestrayRequirementModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(testrayRequirement.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					testrayRequirement);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in testrayRequirement proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TestrayRequirement implementation " +
					testrayRequirement.getClass());
		}

		TestrayRequirementModelImpl testrayRequirementModelImpl =
			(TestrayRequirementModelImpl)testrayRequirement;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayRequirement.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayRequirement.setCreateDate(now);
			}
			else {
				testrayRequirement.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!testrayRequirementModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayRequirement.setModifiedDate(now);
			}
			else {
				testrayRequirement.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayRequirement.isNew()) {
				session.save(testrayRequirement);

				testrayRequirement.setNew(false);
			}
			else {
				testrayRequirement = (TestrayRequirement)session.merge(
					testrayRequirement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TestrayRequirementModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementImpl.class, testrayRequirement.getPrimaryKey(),
			testrayRequirement, false);

		clearUniqueFindersCache(testrayRequirementModelImpl, false);
		cacheUniqueFindersCache(testrayRequirementModelImpl);

		testrayRequirement.resetOriginalValues();

		return testrayRequirement;
	}

	/**
	 * Returns the testray requirement with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray requirement
	 * @return the testray requirement
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	@Override
	public TestrayRequirement findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayRequirementException {

		TestrayRequirement testrayRequirement = fetchByPrimaryKey(primaryKey);

		if (testrayRequirement == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayRequirementException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return testrayRequirement;
	}

	/**
	 * Returns the testray requirement with the primary key or throws a <code>NoSuchTestrayRequirementException</code> if it could not be found.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement
	 * @throws NoSuchTestrayRequirementException if a testray requirement with the primary key could not be found
	 */
	@Override
	public TestrayRequirement findByPrimaryKey(long testrayRequirementId)
		throws NoSuchTestrayRequirementException {

		return findByPrimaryKey((Serializable)testrayRequirementId);
	}

	/**
	 * Returns the testray requirement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray requirement
	 * @return the testray requirement, or <code>null</code> if a testray requirement with the primary key could not be found
	 */
	@Override
	public TestrayRequirement fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayRequirement testrayRequirement =
			(TestrayRequirement)serializable;

		if (testrayRequirement == null) {
			Session session = null;

			try {
				session = openSession();

				testrayRequirement = (TestrayRequirement)session.get(
					TestrayRequirementImpl.class, primaryKey);

				if (testrayRequirement != null) {
					cacheResult(testrayRequirement);
				}
				else {
					entityCache.putResult(
						TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
						TestrayRequirementImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRequirementImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayRequirement;
	}

	/**
	 * Returns the testray requirement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement, or <code>null</code> if a testray requirement with the primary key could not be found
	 */
	@Override
	public TestrayRequirement fetchByPrimaryKey(long testrayRequirementId) {
		return fetchByPrimaryKey((Serializable)testrayRequirementId);
	}

	@Override
	public Map<Serializable, TestrayRequirement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayRequirement> map =
			new HashMap<Serializable, TestrayRequirement>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayRequirement testrayRequirement = fetchByPrimaryKey(
				primaryKey);

			if (testrayRequirement != null) {
				map.put(primaryKey, testrayRequirement);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
				TestrayRequirementImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayRequirement)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_TESTRAYREQUIREMENT_WHERE_PKS_IN);

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

			for (TestrayRequirement testrayRequirement :
					(List<TestrayRequirement>)q.list()) {

				map.put(
					testrayRequirement.getPrimaryKeyObj(), testrayRequirement);

				cacheResult(testrayRequirement);

				uncachedPrimaryKeys.remove(
					testrayRequirement.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRequirementImpl.class, primaryKey, nullModel);
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
	 * Returns all the testray requirements.
	 *
	 * @return the testray requirements
	 */
	@Override
	public List<TestrayRequirement> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray requirements
	 */
	@Override
	public List<TestrayRequirement> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray requirements
	 */
	@Override
	public List<TestrayRequirement> findAll(
		int start, int end,
		OrderByComparator<TestrayRequirement> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray requirements
	 */
	@Override
	public List<TestrayRequirement> findAll(
		int start, int end,
		OrderByComparator<TestrayRequirement> orderByComparator,
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

		List<TestrayRequirement> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayRequirement>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYREQUIREMENT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYREQUIREMENT;

				if (pagination) {
					sql = sql.concat(TestrayRequirementModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayRequirement>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayRequirement>)QueryUtil.list(
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
	 * Removes all the testray requirements from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayRequirement testrayRequirement : findAll()) {
			remove(testrayRequirement);
		}
	}

	/**
	 * Returns the number of testray requirements.
	 *
	 * @return the number of testray requirements
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYREQUIREMENT);

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

	/**
	 * Returns the primaryKeys of testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return long[] of the primaryKeys of testray cases associated with the testray requirement
	 */
	@Override
	public long[] getTestrayCasePrimaryKeys(long pk) {
		long[] pks =
			testrayRequirementToTestrayCaseTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return the testray cases associated with the testray requirement
	 */
	@Override
	public List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk) {

		return getTestrayCases(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the testray cases associated with the testray requirement.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray requirement
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray cases associated with the testray requirement
	 */
	@Override
	public List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk, int start, int end) {

		return getTestrayCases(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray cases associated with the testray requirement.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the testray requirement
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray cases associated with the testray requirement
	 */
	@Override
	public List<com.liferay.osb.testray.model.TestrayCase> getTestrayCases(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.testray.model.TestrayCase>
			orderByComparator) {

		return testrayRequirementToTestrayCaseTableMapper.getRightBaseModels(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of testray cases associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @return the number of testray cases associated with the testray requirement
	 */
	@Override
	public int getTestrayCasesSize(long pk) {
		long[] pks =
			testrayRequirementToTestrayCaseTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the testray case is associated with the testray requirement.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 * @return <code>true</code> if the testray case is associated with the testray requirement; <code>false</code> otherwise
	 */
	@Override
	public boolean containsTestrayCase(long pk, long testrayCasePK) {
		return testrayRequirementToTestrayCaseTableMapper.containsTableMapping(
			pk, testrayCasePK);
	}

	/**
	 * Returns <code>true</code> if the testray requirement has any testray cases associated with it.
	 *
	 * @param pk the primary key of the testray requirement to check for associations with testray cases
	 * @return <code>true</code> if the testray requirement has any testray cases associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsTestrayCases(long pk) {
		if (getTestrayCasesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 */
	@Override
	public void addTestrayCase(long pk, long testrayCasePK) {
		TestrayRequirement testrayRequirement = fetchByPrimaryKey(pk);

		if (testrayRequirement == null) {
			testrayRequirementToTestrayCaseTableMapper.addTableMapping(
				companyProvider.getCompanyId(), pk, testrayCasePK);
		}
		else {
			testrayRequirementToTestrayCaseTableMapper.addTableMapping(
				testrayRequirement.getCompanyId(), pk, testrayCasePK);
		}
	}

	/**
	 * Adds an association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCase the testray case
	 */
	@Override
	public void addTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		TestrayRequirement testrayRequirement = fetchByPrimaryKey(pk);

		if (testrayRequirement == null) {
			testrayRequirementToTestrayCaseTableMapper.addTableMapping(
				companyProvider.getCompanyId(), pk,
				testrayCase.getPrimaryKey());
		}
		else {
			testrayRequirementToTestrayCaseTableMapper.addTableMapping(
				testrayRequirement.getCompanyId(), pk,
				testrayCase.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	@Override
	public void addTestrayCases(long pk, long[] testrayCasePKs) {
		long companyId = 0;

		TestrayRequirement testrayRequirement = fetchByPrimaryKey(pk);

		if (testrayRequirement == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = testrayRequirement.getCompanyId();
		}

		testrayRequirementToTestrayCaseTableMapper.addTableMappings(
			companyId, pk, testrayCasePKs);
	}

	/**
	 * Adds an association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases
	 */
	@Override
	public void addTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		addTestrayCases(
			pk,
			ListUtil.toLongArray(
				testrayCases,
				com.liferay.osb.testray.model.TestrayCase.
					TESTRAY_CASE_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the testray requirement and its testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement to clear the associated testray cases from
	 */
	@Override
	public void clearTestrayCases(long pk) {
		testrayRequirementToTestrayCaseTableMapper.
			deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePK the primary key of the testray case
	 */
	@Override
	public void removeTestrayCase(long pk, long testrayCasePK) {
		testrayRequirementToTestrayCaseTableMapper.deleteTableMapping(
			pk, testrayCasePK);
	}

	/**
	 * Removes the association between the testray requirement and the testray case. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCase the testray case
	 */
	@Override
	public void removeTestrayCase(
		long pk, com.liferay.osb.testray.model.TestrayCase testrayCase) {

		testrayRequirementToTestrayCaseTableMapper.deleteTableMapping(
			pk, testrayCase.getPrimaryKey());
	}

	/**
	 * Removes the association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases
	 */
	@Override
	public void removeTestrayCases(long pk, long[] testrayCasePKs) {
		testrayRequirementToTestrayCaseTableMapper.deleteTableMappings(
			pk, testrayCasePKs);
	}

	/**
	 * Removes the association between the testray requirement and the testray cases. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases
	 */
	@Override
	public void removeTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		removeTestrayCases(
			pk,
			ListUtil.toLongArray(
				testrayCases,
				com.liferay.osb.testray.model.TestrayCase.
					TESTRAY_CASE_ID_ACCESSOR));
	}

	/**
	 * Sets the testray cases associated with the testray requirement, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCasePKs the primary keys of the testray cases to be associated with the testray requirement
	 */
	@Override
	public void setTestrayCases(long pk, long[] testrayCasePKs) {
		Set<Long> newTestrayCasePKsSet = SetUtil.fromArray(testrayCasePKs);
		Set<Long> oldTestrayCasePKsSet = SetUtil.fromArray(
			testrayRequirementToTestrayCaseTableMapper.getRightPrimaryKeys(pk));

		Set<Long> removeTestrayCasePKsSet = new HashSet<Long>(
			oldTestrayCasePKsSet);

		removeTestrayCasePKsSet.removeAll(newTestrayCasePKsSet);

		testrayRequirementToTestrayCaseTableMapper.deleteTableMappings(
			pk, ArrayUtil.toLongArray(removeTestrayCasePKsSet));

		newTestrayCasePKsSet.removeAll(oldTestrayCasePKsSet);

		long companyId = 0;

		TestrayRequirement testrayRequirement = fetchByPrimaryKey(pk);

		if (testrayRequirement == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = testrayRequirement.getCompanyId();
		}

		testrayRequirementToTestrayCaseTableMapper.addTableMappings(
			companyId, pk, ArrayUtil.toLongArray(newTestrayCasePKsSet));
	}

	/**
	 * Sets the testray cases associated with the testray requirement, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the testray requirement
	 * @param testrayCases the testray cases to be associated with the testray requirement
	 */
	@Override
	public void setTestrayCases(
		long pk, List<com.liferay.osb.testray.model.TestrayCase> testrayCases) {

		try {
			long[] testrayCasePKs = new long[testrayCases.size()];

			for (int i = 0; i < testrayCases.size(); i++) {
				com.liferay.osb.testray.model.TestrayCase testrayCase =
					testrayCases.get(i);

				testrayCasePKs[i] = testrayCase.getPrimaryKey();
			}

			setTestrayCases(pk, testrayCasePKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TestrayRequirementModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray requirement persistence.
	 */
	public void afterPropertiesSet() {
		testrayRequirementToTestrayCaseTableMapper =
			TableMapperFactory.getTableMapper(
				"OSB_TestrayRequirements_TestrayCases", "companyId",
				"testrayRequirementId", "testrayCaseId", this,
				testrayCasePersistence);

		_finderPathWithPaginationFindAll = new FinderPath(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementModelImpl.FINDER_CACHE_ENABLED,
			TestrayRequirementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementModelImpl.FINDER_CACHE_ENABLED,
			TestrayRequirementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByTPI_K = new FinderPath(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementModelImpl.FINDER_CACHE_ENABLED,
			TestrayRequirementImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTPI_K",
			new String[] {Long.class.getName(), String.class.getName()},
			TestrayRequirementModelImpl.TESTRAYPROJECTID_COLUMN_BITMASK |
			TestrayRequirementModelImpl.KEY_COLUMN_BITMASK);

		_finderPathCountByTPI_K = new FinderPath(
			TestrayRequirementModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRequirementModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTPI_K",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(TestrayRequirementImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_TestrayRequirements_TestrayCases");
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	@BeanReference(type = TestrayCasePersistence.class)
	protected TestrayCasePersistence testrayCasePersistence;

	protected TableMapper
		<TestrayRequirement, com.liferay.osb.testray.model.TestrayCase>
			testrayRequirementToTestrayCaseTableMapper;

	private static final String _SQL_SELECT_TESTRAYREQUIREMENT =
		"SELECT testrayRequirement FROM TestrayRequirement testrayRequirement";

	private static final String _SQL_SELECT_TESTRAYREQUIREMENT_WHERE_PKS_IN =
		"SELECT testrayRequirement FROM TestrayRequirement testrayRequirement WHERE testrayRequirementId IN (";

	private static final String _SQL_SELECT_TESTRAYREQUIREMENT_WHERE =
		"SELECT testrayRequirement FROM TestrayRequirement testrayRequirement WHERE ";

	private static final String _SQL_COUNT_TESTRAYREQUIREMENT =
		"SELECT COUNT(testrayRequirement) FROM TestrayRequirement testrayRequirement";

	private static final String _SQL_COUNT_TESTRAYREQUIREMENT_WHERE =
		"SELECT COUNT(testrayRequirement) FROM TestrayRequirement testrayRequirement WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayRequirement.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TestrayRequirement exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TestrayRequirement exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TestrayRequirementPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"key"});

}
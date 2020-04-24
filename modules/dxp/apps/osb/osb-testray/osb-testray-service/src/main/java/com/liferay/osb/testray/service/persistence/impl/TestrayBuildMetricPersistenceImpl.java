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

import com.liferay.osb.testray.exception.NoSuchTestrayBuildMetricException;
import com.liferay.osb.testray.model.TestrayBuildMetric;
import com.liferay.osb.testray.model.impl.TestrayBuildMetricImpl;
import com.liferay.osb.testray.model.impl.TestrayBuildMetricModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayBuildMetricPersistence;

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
 * The persistence implementation for the testray build metric service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayBuildMetricPersistence
 * @see com.liferay.osb.testray.service.persistence.TestrayBuildMetricUtil
 * @generated
 */
@ProviderType
public class TestrayBuildMetricPersistenceImpl extends BasePersistenceImpl<TestrayBuildMetric>
	implements TestrayBuildMetricPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TestrayBuildMetricUtil} to access the testray build metric persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TestrayBuildMetricImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricModelImpl.FINDER_CACHE_ENABLED,
			TestrayBuildMetricImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricModelImpl.FINDER_CACHE_ENABLED,
			TestrayBuildMetricImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_TBI_TCTI_S = new FinderPath(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricModelImpl.FINDER_CACHE_ENABLED,
			TestrayBuildMetricImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTBI_TCTI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TestrayBuildMetricModelImpl.TESTRAYBUILDID_COLUMN_BITMASK |
			TestrayBuildMetricModelImpl.TESTRAYCASETYPEID_COLUMN_BITMASK |
			TestrayBuildMetricModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TBI_TCTI_S = new FinderPath(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTBI_TCTI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or throws a {@link NoSuchTestrayBuildMetricException} if it could not be found.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param testrayCaseTypeId the testray case type ID
	 * @param status the status
	 * @return the matching testray build metric
	 * @throws NoSuchTestrayBuildMetricException if a matching testray build metric could not be found
	 */
	@Override
	public TestrayBuildMetric findByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status)
		throws NoSuchTestrayBuildMetricException {
		TestrayBuildMetric testrayBuildMetric = fetchByTBI_TCTI_S(testrayBuildId,
				testrayCaseTypeId, status);

		if (testrayBuildMetric == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("testrayBuildId=");
			msg.append(testrayBuildId);

			msg.append(", testrayCaseTypeId=");
			msg.append(testrayCaseTypeId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTestrayBuildMetricException(msg.toString());
		}

		return testrayBuildMetric;
	}

	/**
	 * Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param testrayCaseTypeId the testray case type ID
	 * @param status the status
	 * @return the matching testray build metric, or <code>null</code> if a matching testray build metric could not be found
	 */
	@Override
	public TestrayBuildMetric fetchByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status) {
		return fetchByTBI_TCTI_S(testrayBuildId, testrayCaseTypeId, status, true);
	}

	/**
	 * Returns the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param testrayCaseTypeId the testray case type ID
	 * @param status the status
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray build metric, or <code>null</code> if a matching testray build metric could not be found
	 */
	@Override
	public TestrayBuildMetric fetchByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				testrayBuildId, testrayCaseTypeId, status
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S,
					finderArgs, this);
		}

		if (result instanceof TestrayBuildMetric) {
			TestrayBuildMetric testrayBuildMetric = (TestrayBuildMetric)result;

			if ((testrayBuildId != testrayBuildMetric.getTestrayBuildId()) ||
					(testrayCaseTypeId != testrayBuildMetric.getTestrayCaseTypeId()) ||
					(status != testrayBuildMetric.getStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_TESTRAYBUILDMETRIC_WHERE);

			query.append(_FINDER_COLUMN_TBI_TCTI_S_TESTRAYBUILDID_2);

			query.append(_FINDER_COLUMN_TBI_TCTI_S_TESTRAYCASETYPEID_2);

			query.append(_FINDER_COLUMN_TBI_TCTI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(testrayBuildId);

				qPos.add(testrayCaseTypeId);

				qPos.add(status);

				List<TestrayBuildMetric> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S,
						finderArgs, list);
				}
				else {
					TestrayBuildMetric testrayBuildMetric = list.get(0);

					result = testrayBuildMetric;

					cacheResult(testrayBuildMetric);

					if ((testrayBuildMetric.getTestrayBuildId() != testrayBuildId) ||
							(testrayBuildMetric.getTestrayCaseTypeId() != testrayCaseTypeId) ||
							(testrayBuildMetric.getStatus() != status)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S,
							finderArgs, testrayBuildMetric);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S,
					finderArgs);

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
			return (TestrayBuildMetric)result;
		}
	}

	/**
	 * Removes the testray build metric where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63; from the database.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param testrayCaseTypeId the testray case type ID
	 * @param status the status
	 * @return the testray build metric that was removed
	 */
	@Override
	public TestrayBuildMetric removeByTBI_TCTI_S(long testrayBuildId,
		long testrayCaseTypeId, int status)
		throws NoSuchTestrayBuildMetricException {
		TestrayBuildMetric testrayBuildMetric = findByTBI_TCTI_S(testrayBuildId,
				testrayCaseTypeId, status);

		return remove(testrayBuildMetric);
	}

	/**
	 * Returns the number of testray build metrics where testrayBuildId = &#63; and testrayCaseTypeId = &#63; and status = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param testrayCaseTypeId the testray case type ID
	 * @param status the status
	 * @return the number of matching testray build metrics
	 */
	@Override
	public int countByTBI_TCTI_S(long testrayBuildId, long testrayCaseTypeId,
		int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TBI_TCTI_S;

		Object[] finderArgs = new Object[] {
				testrayBuildId, testrayCaseTypeId, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TESTRAYBUILDMETRIC_WHERE);

			query.append(_FINDER_COLUMN_TBI_TCTI_S_TESTRAYBUILDID_2);

			query.append(_FINDER_COLUMN_TBI_TCTI_S_TESTRAYCASETYPEID_2);

			query.append(_FINDER_COLUMN_TBI_TCTI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(testrayBuildId);

				qPos.add(testrayCaseTypeId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_TBI_TCTI_S_TESTRAYBUILDID_2 = "testrayBuildMetric.testrayBuildId = ? AND ";
	private static final String _FINDER_COLUMN_TBI_TCTI_S_TESTRAYCASETYPEID_2 = "testrayBuildMetric.testrayCaseTypeId = ? AND ";
	private static final String _FINDER_COLUMN_TBI_TCTI_S_STATUS_2 = "testrayBuildMetric.status = ?";

	public TestrayBuildMetricPersistenceImpl() {
		setModelClass(TestrayBuildMetric.class);
	}

	/**
	 * Caches the testray build metric in the entity cache if it is enabled.
	 *
	 * @param testrayBuildMetric the testray build metric
	 */
	@Override
	public void cacheResult(TestrayBuildMetric testrayBuildMetric) {
		entityCache.putResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricImpl.class, testrayBuildMetric.getPrimaryKey(),
			testrayBuildMetric);

		finderCache.putResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S,
			new Object[] {
				testrayBuildMetric.getTestrayBuildId(),
				testrayBuildMetric.getTestrayCaseTypeId(),
				testrayBuildMetric.getStatus()
			}, testrayBuildMetric);

		testrayBuildMetric.resetOriginalValues();
	}

	/**
	 * Caches the testray build metrics in the entity cache if it is enabled.
	 *
	 * @param testrayBuildMetrics the testray build metrics
	 */
	@Override
	public void cacheResult(List<TestrayBuildMetric> testrayBuildMetrics) {
		for (TestrayBuildMetric testrayBuildMetric : testrayBuildMetrics) {
			if (entityCache.getResult(
						TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
						TestrayBuildMetricImpl.class,
						testrayBuildMetric.getPrimaryKey()) == null) {
				cacheResult(testrayBuildMetric);
			}
			else {
				testrayBuildMetric.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray build metrics.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayBuildMetricImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray build metric.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayBuildMetric testrayBuildMetric) {
		entityCache.removeResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricImpl.class, testrayBuildMetric.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TestrayBuildMetricModelImpl)testrayBuildMetric,
			true);
	}

	@Override
	public void clearCache(List<TestrayBuildMetric> testrayBuildMetrics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayBuildMetric testrayBuildMetric : testrayBuildMetrics) {
			entityCache.removeResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
				TestrayBuildMetricImpl.class, testrayBuildMetric.getPrimaryKey());

			clearUniqueFindersCache((TestrayBuildMetricModelImpl)testrayBuildMetric,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		TestrayBuildMetricModelImpl testrayBuildMetricModelImpl) {
		Object[] args = new Object[] {
				testrayBuildMetricModelImpl.getTestrayBuildId(),
				testrayBuildMetricModelImpl.getTestrayCaseTypeId(),
				testrayBuildMetricModelImpl.getStatus()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_TBI_TCTI_S, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S, args,
			testrayBuildMetricModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TestrayBuildMetricModelImpl testrayBuildMetricModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					testrayBuildMetricModelImpl.getTestrayBuildId(),
					testrayBuildMetricModelImpl.getTestrayCaseTypeId(),
					testrayBuildMetricModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TBI_TCTI_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S, args);
		}

		if ((testrayBuildMetricModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TBI_TCTI_S.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					testrayBuildMetricModelImpl.getOriginalTestrayBuildId(),
					testrayBuildMetricModelImpl.getOriginalTestrayCaseTypeId(),
					testrayBuildMetricModelImpl.getOriginalStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TBI_TCTI_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TBI_TCTI_S, args);
		}
	}

	/**
	 * Creates a new testray build metric with the primary key. Does not add the testray build metric to the database.
	 *
	 * @param testrayBuildMetricId the primary key for the new testray build metric
	 * @return the new testray build metric
	 */
	@Override
	public TestrayBuildMetric create(long testrayBuildMetricId) {
		TestrayBuildMetric testrayBuildMetric = new TestrayBuildMetricImpl();

		testrayBuildMetric.setNew(true);
		testrayBuildMetric.setPrimaryKey(testrayBuildMetricId);

		testrayBuildMetric.setCompanyId(companyProvider.getCompanyId());

		return testrayBuildMetric;
	}

	/**
	 * Removes the testray build metric with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayBuildMetricId the primary key of the testray build metric
	 * @return the testray build metric that was removed
	 * @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	 */
	@Override
	public TestrayBuildMetric remove(long testrayBuildMetricId)
		throws NoSuchTestrayBuildMetricException {
		return remove((Serializable)testrayBuildMetricId);
	}

	/**
	 * Removes the testray build metric with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray build metric
	 * @return the testray build metric that was removed
	 * @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	 */
	@Override
	public TestrayBuildMetric remove(Serializable primaryKey)
		throws NoSuchTestrayBuildMetricException {
		Session session = null;

		try {
			session = openSession();

			TestrayBuildMetric testrayBuildMetric = (TestrayBuildMetric)session.get(TestrayBuildMetricImpl.class,
					primaryKey);

			if (testrayBuildMetric == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayBuildMetricException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(testrayBuildMetric);
		}
		catch (NoSuchTestrayBuildMetricException nsee) {
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
	protected TestrayBuildMetric removeImpl(
		TestrayBuildMetric testrayBuildMetric) {
		testrayBuildMetric = toUnwrappedModel(testrayBuildMetric);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayBuildMetric)) {
				testrayBuildMetric = (TestrayBuildMetric)session.get(TestrayBuildMetricImpl.class,
						testrayBuildMetric.getPrimaryKeyObj());
			}

			if (testrayBuildMetric != null) {
				session.delete(testrayBuildMetric);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayBuildMetric != null) {
			clearCache(testrayBuildMetric);
		}

		return testrayBuildMetric;
	}

	@Override
	public TestrayBuildMetric updateImpl(TestrayBuildMetric testrayBuildMetric) {
		testrayBuildMetric = toUnwrappedModel(testrayBuildMetric);

		boolean isNew = testrayBuildMetric.isNew();

		TestrayBuildMetricModelImpl testrayBuildMetricModelImpl = (TestrayBuildMetricModelImpl)testrayBuildMetric;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayBuildMetric.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayBuildMetric.setCreateDate(now);
			}
			else {
				testrayBuildMetric.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!testrayBuildMetricModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayBuildMetric.setModifiedDate(now);
			}
			else {
				testrayBuildMetric.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayBuildMetric.isNew()) {
				session.save(testrayBuildMetric);

				testrayBuildMetric.setNew(false);
			}
			else {
				testrayBuildMetric = (TestrayBuildMetric)session.merge(testrayBuildMetric);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TestrayBuildMetricModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
			TestrayBuildMetricImpl.class, testrayBuildMetric.getPrimaryKey(),
			testrayBuildMetric, false);

		clearUniqueFindersCache(testrayBuildMetricModelImpl, false);
		cacheUniqueFindersCache(testrayBuildMetricModelImpl);

		testrayBuildMetric.resetOriginalValues();

		return testrayBuildMetric;
	}

	protected TestrayBuildMetric toUnwrappedModel(
		TestrayBuildMetric testrayBuildMetric) {
		if (testrayBuildMetric instanceof TestrayBuildMetricImpl) {
			return testrayBuildMetric;
		}

		TestrayBuildMetricImpl testrayBuildMetricImpl = new TestrayBuildMetricImpl();

		testrayBuildMetricImpl.setNew(testrayBuildMetric.isNew());
		testrayBuildMetricImpl.setPrimaryKey(testrayBuildMetric.getPrimaryKey());

		testrayBuildMetricImpl.setTestrayBuildMetricId(testrayBuildMetric.getTestrayBuildMetricId());
		testrayBuildMetricImpl.setGroupId(testrayBuildMetric.getGroupId());
		testrayBuildMetricImpl.setCompanyId(testrayBuildMetric.getCompanyId());
		testrayBuildMetricImpl.setUserId(testrayBuildMetric.getUserId());
		testrayBuildMetricImpl.setUserName(testrayBuildMetric.getUserName());
		testrayBuildMetricImpl.setCreateDate(testrayBuildMetric.getCreateDate());
		testrayBuildMetricImpl.setModifiedDate(testrayBuildMetric.getModifiedDate());
		testrayBuildMetricImpl.setTestrayBuildId(testrayBuildMetric.getTestrayBuildId());
		testrayBuildMetricImpl.setTestrayCaseTypeId(testrayBuildMetric.getTestrayCaseTypeId());
		testrayBuildMetricImpl.setStatus(testrayBuildMetric.getStatus());
		testrayBuildMetricImpl.setCount(testrayBuildMetric.getCount());

		return testrayBuildMetricImpl;
	}

	/**
	 * Returns the testray build metric with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray build metric
	 * @return the testray build metric
	 * @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	 */
	@Override
	public TestrayBuildMetric findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayBuildMetricException {
		TestrayBuildMetric testrayBuildMetric = fetchByPrimaryKey(primaryKey);

		if (testrayBuildMetric == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayBuildMetricException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return testrayBuildMetric;
	}

	/**
	 * Returns the testray build metric with the primary key or throws a {@link NoSuchTestrayBuildMetricException} if it could not be found.
	 *
	 * @param testrayBuildMetricId the primary key of the testray build metric
	 * @return the testray build metric
	 * @throws NoSuchTestrayBuildMetricException if a testray build metric with the primary key could not be found
	 */
	@Override
	public TestrayBuildMetric findByPrimaryKey(long testrayBuildMetricId)
		throws NoSuchTestrayBuildMetricException {
		return findByPrimaryKey((Serializable)testrayBuildMetricId);
	}

	/**
	 * Returns the testray build metric with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray build metric
	 * @return the testray build metric, or <code>null</code> if a testray build metric with the primary key could not be found
	 */
	@Override
	public TestrayBuildMetric fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
				TestrayBuildMetricImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayBuildMetric testrayBuildMetric = (TestrayBuildMetric)serializable;

		if (testrayBuildMetric == null) {
			Session session = null;

			try {
				session = openSession();

				testrayBuildMetric = (TestrayBuildMetric)session.get(TestrayBuildMetricImpl.class,
						primaryKey);

				if (testrayBuildMetric != null) {
					cacheResult(testrayBuildMetric);
				}
				else {
					entityCache.putResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
						TestrayBuildMetricImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
					TestrayBuildMetricImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayBuildMetric;
	}

	/**
	 * Returns the testray build metric with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayBuildMetricId the primary key of the testray build metric
	 * @return the testray build metric, or <code>null</code> if a testray build metric with the primary key could not be found
	 */
	@Override
	public TestrayBuildMetric fetchByPrimaryKey(long testrayBuildMetricId) {
		return fetchByPrimaryKey((Serializable)testrayBuildMetricId);
	}

	@Override
	public Map<Serializable, TestrayBuildMetric> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayBuildMetric> map = new HashMap<Serializable, TestrayBuildMetric>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayBuildMetric testrayBuildMetric = fetchByPrimaryKey(primaryKey);

			if (testrayBuildMetric != null) {
				map.put(primaryKey, testrayBuildMetric);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
					TestrayBuildMetricImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayBuildMetric)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TESTRAYBUILDMETRIC_WHERE_PKS_IN);

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

			for (TestrayBuildMetric testrayBuildMetric : (List<TestrayBuildMetric>)q.list()) {
				map.put(testrayBuildMetric.getPrimaryKeyObj(),
					testrayBuildMetric);

				cacheResult(testrayBuildMetric);

				uncachedPrimaryKeys.remove(testrayBuildMetric.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TestrayBuildMetricModelImpl.ENTITY_CACHE_ENABLED,
					TestrayBuildMetricImpl.class, primaryKey, nullModel);
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
	 * Returns all the testray build metrics.
	 *
	 * @return the testray build metrics
	 */
	@Override
	public List<TestrayBuildMetric> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray build metrics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray build metrics
	 * @param end the upper bound of the range of testray build metrics (not inclusive)
	 * @return the range of testray build metrics
	 */
	@Override
	public List<TestrayBuildMetric> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray build metrics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray build metrics
	 * @param end the upper bound of the range of testray build metrics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray build metrics
	 */
	@Override
	public List<TestrayBuildMetric> findAll(int start, int end,
		OrderByComparator<TestrayBuildMetric> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray build metrics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayBuildMetricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray build metrics
	 * @param end the upper bound of the range of testray build metrics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray build metrics
	 */
	@Override
	public List<TestrayBuildMetric> findAll(int start, int end,
		OrderByComparator<TestrayBuildMetric> orderByComparator,
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

		List<TestrayBuildMetric> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayBuildMetric>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYBUILDMETRIC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYBUILDMETRIC;

				if (pagination) {
					sql = sql.concat(TestrayBuildMetricModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayBuildMetric>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayBuildMetric>)QueryUtil.list(q,
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
	 * Removes all the testray build metrics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayBuildMetric testrayBuildMetric : findAll()) {
			remove(testrayBuildMetric);
		}
	}

	/**
	 * Returns the number of testray build metrics.
	 *
	 * @return the number of testray build metrics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYBUILDMETRIC);

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
		return TestrayBuildMetricModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray build metric persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TestrayBuildMetricImpl.class.getName());
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
	private static final String _SQL_SELECT_TESTRAYBUILDMETRIC = "SELECT testrayBuildMetric FROM TestrayBuildMetric testrayBuildMetric";
	private static final String _SQL_SELECT_TESTRAYBUILDMETRIC_WHERE_PKS_IN = "SELECT testrayBuildMetric FROM TestrayBuildMetric testrayBuildMetric WHERE testrayBuildMetricId IN (";
	private static final String _SQL_SELECT_TESTRAYBUILDMETRIC_WHERE = "SELECT testrayBuildMetric FROM TestrayBuildMetric testrayBuildMetric WHERE ";
	private static final String _SQL_COUNT_TESTRAYBUILDMETRIC = "SELECT COUNT(testrayBuildMetric) FROM TestrayBuildMetric testrayBuildMetric";
	private static final String _SQL_COUNT_TESTRAYBUILDMETRIC_WHERE = "SELECT COUNT(testrayBuildMetric) FROM TestrayBuildMetric testrayBuildMetric WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayBuildMetric.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TestrayBuildMetric exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TestrayBuildMetric exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TestrayBuildMetricPersistenceImpl.class);
}
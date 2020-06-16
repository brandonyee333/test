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

import com.liferay.osb.testray.exception.NoSuchTestrayRunException;
import com.liferay.osb.testray.model.TestrayRun;
import com.liferay.osb.testray.model.impl.TestrayRunImpl;
import com.liferay.osb.testray.model.impl.TestrayRunModelImpl;
import com.liferay.osb.testray.service.persistence.TestrayRunPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the testray run service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayRunPersistence
 * @see com.liferay.osb.testray.service.persistence.TestrayRunUtil
 * @generated
 */
@ProviderType
public class TestrayRunPersistenceImpl extends BasePersistenceImpl<TestrayRun>
	implements TestrayRunPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TestrayRunUtil} to access the testray run persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TestrayRunImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunModelImpl.FINDER_CACHE_ENABLED, TestrayRunImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunModelImpl.FINDER_CACHE_ENABLED, TestrayRunImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_T_E_E = new FinderPath(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunModelImpl.FINDER_CACHE_ENABLED, TestrayRunImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByT_E_E",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			TestrayRunModelImpl.TESTRAYBUILDID_COLUMN_BITMASK |
			TestrayRunModelImpl.EXTERNALREFERENCEPK_COLUMN_BITMASK |
			TestrayRunModelImpl.EXTERNALREFERENCETYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_E_E = new FinderPath(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_E_E",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or throws a {@link NoSuchTestrayRunException} if it could not be found.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the matching testray run
	 * @throws NoSuchTestrayRunException if a matching testray run could not be found
	 */
	@Override
	public TestrayRun findByT_E_E(long testrayBuildId,
		String externalReferencePK, int externalReferenceType)
		throws NoSuchTestrayRunException {
		TestrayRun testrayRun = fetchByT_E_E(testrayBuildId,
				externalReferencePK, externalReferenceType);

		if (testrayRun == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("testrayBuildId=");
			msg.append(testrayBuildId);

			msg.append(", externalReferencePK=");
			msg.append(externalReferencePK);

			msg.append(", externalReferenceType=");
			msg.append(externalReferenceType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTestrayRunException(msg.toString());
		}

		return testrayRun;
	}

	/**
	 * Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the matching testray run, or <code>null</code> if a matching testray run could not be found
	 */
	@Override
	public TestrayRun fetchByT_E_E(long testrayBuildId,
		String externalReferencePK, int externalReferenceType) {
		return fetchByT_E_E(testrayBuildId, externalReferencePK,
			externalReferenceType, true);
	}

	/**
	 * Returns the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching testray run, or <code>null</code> if a matching testray run could not be found
	 */
	@Override
	public TestrayRun fetchByT_E_E(long testrayBuildId,
		String externalReferencePK, int externalReferenceType,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				testrayBuildId, externalReferencePK, externalReferenceType
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_T_E_E,
					finderArgs, this);
		}

		if (result instanceof TestrayRun) {
			TestrayRun testrayRun = (TestrayRun)result;

			if ((testrayBuildId != testrayRun.getTestrayBuildId()) ||
					!Objects.equals(externalReferencePK,
						testrayRun.getExternalReferencePK()) ||
					(externalReferenceType != testrayRun.getExternalReferenceType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_TESTRAYRUN_WHERE);

			query.append(_FINDER_COLUMN_T_E_E_TESTRAYBUILDID_2);

			boolean bindExternalReferencePK = false;

			if (externalReferencePK == null) {
				query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_1);
			}
			else if (externalReferencePK.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_3);
			}
			else {
				bindExternalReferencePK = true;

				query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_2);
			}

			query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(testrayBuildId);

				if (bindExternalReferencePK) {
					qPos.add(externalReferencePK);
				}

				qPos.add(externalReferenceType);

				List<TestrayRun> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_T_E_E,
						finderArgs, list);
				}
				else {
					TestrayRun testrayRun = list.get(0);

					result = testrayRun;

					cacheResult(testrayRun);

					if ((testrayRun.getTestrayBuildId() != testrayBuildId) ||
							(testrayRun.getExternalReferencePK() == null) ||
							!testrayRun.getExternalReferencePK()
										   .equals(externalReferencePK) ||
							(testrayRun.getExternalReferenceType() != externalReferenceType)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_T_E_E,
							finderArgs, testrayRun);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_T_E_E, finderArgs);

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
			return (TestrayRun)result;
		}
	}

	/**
	 * Removes the testray run where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63; from the database.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the testray run that was removed
	 */
	@Override
	public TestrayRun removeByT_E_E(long testrayBuildId,
		String externalReferencePK, int externalReferenceType)
		throws NoSuchTestrayRunException {
		TestrayRun testrayRun = findByT_E_E(testrayBuildId,
				externalReferencePK, externalReferenceType);

		return remove(testrayRun);
	}

	/**
	 * Returns the number of testray runs where testrayBuildId = &#63; and externalReferencePK = &#63; and externalReferenceType = &#63;.
	 *
	 * @param testrayBuildId the testray build ID
	 * @param externalReferencePK the external reference pk
	 * @param externalReferenceType the external reference type
	 * @return the number of matching testray runs
	 */
	@Override
	public int countByT_E_E(long testrayBuildId, String externalReferencePK,
		int externalReferenceType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_T_E_E;

		Object[] finderArgs = new Object[] {
				testrayBuildId, externalReferencePK, externalReferenceType
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TESTRAYRUN_WHERE);

			query.append(_FINDER_COLUMN_T_E_E_TESTRAYBUILDID_2);

			boolean bindExternalReferencePK = false;

			if (externalReferencePK == null) {
				query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_1);
			}
			else if (externalReferencePK.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_3);
			}
			else {
				bindExternalReferencePK = true;

				query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_2);
			}

			query.append(_FINDER_COLUMN_T_E_E_EXTERNALREFERENCETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(testrayBuildId);

				if (bindExternalReferencePK) {
					qPos.add(externalReferencePK);
				}

				qPos.add(externalReferenceType);

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

	private static final String _FINDER_COLUMN_T_E_E_TESTRAYBUILDID_2 = "testrayRun.testrayBuildId = ? AND ";
	private static final String _FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_1 = "testrayRun.externalReferencePK IS NULL AND ";
	private static final String _FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_2 = "testrayRun.externalReferencePK = ? AND ";
	private static final String _FINDER_COLUMN_T_E_E_EXTERNALREFERENCEPK_3 = "(testrayRun.externalReferencePK IS NULL OR testrayRun.externalReferencePK = '') AND ";
	private static final String _FINDER_COLUMN_T_E_E_EXTERNALREFERENCETYPE_2 = "testrayRun.externalReferenceType = ?";

	public TestrayRunPersistenceImpl() {
		setModelClass(TestrayRun.class);
	}

	/**
	 * Caches the testray run in the entity cache if it is enabled.
	 *
	 * @param testrayRun the testray run
	 */
	@Override
	public void cacheResult(TestrayRun testrayRun) {
		entityCache.putResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunImpl.class, testrayRun.getPrimaryKey(), testrayRun);

		finderCache.putResult(FINDER_PATH_FETCH_BY_T_E_E,
			new Object[] {
				testrayRun.getTestrayBuildId(),
				testrayRun.getExternalReferencePK(),
				testrayRun.getExternalReferenceType()
			}, testrayRun);

		testrayRun.resetOriginalValues();
	}

	/**
	 * Caches the testray runs in the entity cache if it is enabled.
	 *
	 * @param testrayRuns the testray runs
	 */
	@Override
	public void cacheResult(List<TestrayRun> testrayRuns) {
		for (TestrayRun testrayRun : testrayRuns) {
			if (entityCache.getResult(
						TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
						TestrayRunImpl.class, testrayRun.getPrimaryKey()) == null) {
				cacheResult(testrayRun);
			}
			else {
				testrayRun.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all testray runs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TestrayRunImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the testray run.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestrayRun testrayRun) {
		entityCache.removeResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunImpl.class, testrayRun.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TestrayRunModelImpl)testrayRun, true);
	}

	@Override
	public void clearCache(List<TestrayRun> testrayRuns) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestrayRun testrayRun : testrayRuns) {
			entityCache.removeResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
				TestrayRunImpl.class, testrayRun.getPrimaryKey());

			clearUniqueFindersCache((TestrayRunModelImpl)testrayRun, true);
		}
	}

	protected void cacheUniqueFindersCache(
		TestrayRunModelImpl testrayRunModelImpl) {
		Object[] args = new Object[] {
				testrayRunModelImpl.getTestrayBuildId(),
				testrayRunModelImpl.getExternalReferencePK(),
				testrayRunModelImpl.getExternalReferenceType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_T_E_E, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_T_E_E, args,
			testrayRunModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TestrayRunModelImpl testrayRunModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					testrayRunModelImpl.getTestrayBuildId(),
					testrayRunModelImpl.getExternalReferencePK(),
					testrayRunModelImpl.getExternalReferenceType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_T_E_E, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_T_E_E, args);
		}

		if ((testrayRunModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_T_E_E.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					testrayRunModelImpl.getOriginalTestrayBuildId(),
					testrayRunModelImpl.getOriginalExternalReferencePK(),
					testrayRunModelImpl.getOriginalExternalReferenceType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_T_E_E, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_T_E_E, args);
		}
	}

	/**
	 * Creates a new testray run with the primary key. Does not add the testray run to the database.
	 *
	 * @param testrayRunId the primary key for the new testray run
	 * @return the new testray run
	 */
	@Override
	public TestrayRun create(long testrayRunId) {
		TestrayRun testrayRun = new TestrayRunImpl();

		testrayRun.setNew(true);
		testrayRun.setPrimaryKey(testrayRunId);

		testrayRun.setCompanyId(companyProvider.getCompanyId());

		return testrayRun;
	}

	/**
	 * Removes the testray run with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run that was removed
	 * @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	 */
	@Override
	public TestrayRun remove(long testrayRunId)
		throws NoSuchTestrayRunException {
		return remove((Serializable)testrayRunId);
	}

	/**
	 * Removes the testray run with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the testray run
	 * @return the testray run that was removed
	 * @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	 */
	@Override
	public TestrayRun remove(Serializable primaryKey)
		throws NoSuchTestrayRunException {
		Session session = null;

		try {
			session = openSession();

			TestrayRun testrayRun = (TestrayRun)session.get(TestrayRunImpl.class,
					primaryKey);

			if (testrayRun == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTestrayRunException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(testrayRun);
		}
		catch (NoSuchTestrayRunException nsee) {
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
	protected TestrayRun removeImpl(TestrayRun testrayRun) {
		testrayRun = toUnwrappedModel(testrayRun);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testrayRun)) {
				testrayRun = (TestrayRun)session.get(TestrayRunImpl.class,
						testrayRun.getPrimaryKeyObj());
			}

			if (testrayRun != null) {
				session.delete(testrayRun);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testrayRun != null) {
			clearCache(testrayRun);
		}

		return testrayRun;
	}

	@Override
	public TestrayRun updateImpl(TestrayRun testrayRun) {
		testrayRun = toUnwrappedModel(testrayRun);

		boolean isNew = testrayRun.isNew();

		TestrayRunModelImpl testrayRunModelImpl = (TestrayRunModelImpl)testrayRun;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (testrayRun.getCreateDate() == null)) {
			if (serviceContext == null) {
				testrayRun.setCreateDate(now);
			}
			else {
				testrayRun.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!testrayRunModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				testrayRun.setModifiedDate(now);
			}
			else {
				testrayRun.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (testrayRun.isNew()) {
				session.save(testrayRun);

				testrayRun.setNew(false);
			}
			else {
				testrayRun = (TestrayRun)session.merge(testrayRun);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TestrayRunModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
			TestrayRunImpl.class, testrayRun.getPrimaryKey(), testrayRun, false);

		clearUniqueFindersCache(testrayRunModelImpl, false);
		cacheUniqueFindersCache(testrayRunModelImpl);

		testrayRun.resetOriginalValues();

		return testrayRun;
	}

	protected TestrayRun toUnwrappedModel(TestrayRun testrayRun) {
		if (testrayRun instanceof TestrayRunImpl) {
			return testrayRun;
		}

		TestrayRunImpl testrayRunImpl = new TestrayRunImpl();

		testrayRunImpl.setNew(testrayRun.isNew());
		testrayRunImpl.setPrimaryKey(testrayRun.getPrimaryKey());

		testrayRunImpl.setTestrayRunId(testrayRun.getTestrayRunId());
		testrayRunImpl.setGroupId(testrayRun.getGroupId());
		testrayRunImpl.setCompanyId(testrayRun.getCompanyId());
		testrayRunImpl.setUserId(testrayRun.getUserId());
		testrayRunImpl.setUserName(testrayRun.getUserName());
		testrayRunImpl.setCreateDate(testrayRun.getCreateDate());
		testrayRunImpl.setModifiedDate(testrayRun.getModifiedDate());
		testrayRunImpl.setTestrayBuildId(testrayRun.getTestrayBuildId());
		testrayRunImpl.setName(testrayRun.getName());
		testrayRunImpl.setDescription(testrayRun.getDescription());
		testrayRunImpl.setExternalReferencePK(testrayRun.getExternalReferencePK());
		testrayRunImpl.setExternalReferenceType(testrayRun.getExternalReferenceType());
		testrayRunImpl.setJenkinsJobKey(testrayRun.getJenkinsJobKey());
		testrayRunImpl.setNumber(testrayRun.getNumber());
		testrayRunImpl.setEnvironmentHash(testrayRun.getEnvironmentHash());

		return testrayRunImpl;
	}

	/**
	 * Returns the testray run with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray run
	 * @return the testray run
	 * @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	 */
	@Override
	public TestrayRun findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTestrayRunException {
		TestrayRun testrayRun = fetchByPrimaryKey(primaryKey);

		if (testrayRun == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTestrayRunException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return testrayRun;
	}

	/**
	 * Returns the testray run with the primary key or throws a {@link NoSuchTestrayRunException} if it could not be found.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run
	 * @throws NoSuchTestrayRunException if a testray run with the primary key could not be found
	 */
	@Override
	public TestrayRun findByPrimaryKey(long testrayRunId)
		throws NoSuchTestrayRunException {
		return findByPrimaryKey((Serializable)testrayRunId);
	}

	/**
	 * Returns the testray run with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the testray run
	 * @return the testray run, or <code>null</code> if a testray run with the primary key could not be found
	 */
	@Override
	public TestrayRun fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
				TestrayRunImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TestrayRun testrayRun = (TestrayRun)serializable;

		if (testrayRun == null) {
			Session session = null;

			try {
				session = openSession();

				testrayRun = (TestrayRun)session.get(TestrayRunImpl.class,
						primaryKey);

				if (testrayRun != null) {
					cacheResult(testrayRun);
				}
				else {
					entityCache.putResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
						TestrayRunImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRunImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testrayRun;
	}

	/**
	 * Returns the testray run with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run, or <code>null</code> if a testray run with the primary key could not be found
	 */
	@Override
	public TestrayRun fetchByPrimaryKey(long testrayRunId) {
		return fetchByPrimaryKey((Serializable)testrayRunId);
	}

	@Override
	public Map<Serializable, TestrayRun> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TestrayRun> map = new HashMap<Serializable, TestrayRun>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TestrayRun testrayRun = fetchByPrimaryKey(primaryKey);

			if (testrayRun != null) {
				map.put(primaryKey, testrayRun);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRunImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TestrayRun)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TESTRAYRUN_WHERE_PKS_IN);

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

			for (TestrayRun testrayRun : (List<TestrayRun>)q.list()) {
				map.put(testrayRun.getPrimaryKeyObj(), testrayRun);

				cacheResult(testrayRun);

				uncachedPrimaryKeys.remove(testrayRun.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TestrayRunModelImpl.ENTITY_CACHE_ENABLED,
					TestrayRunImpl.class, primaryKey, nullModel);
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
	 * Returns all the testray runs.
	 *
	 * @return the testray runs
	 */
	@Override
	public List<TestrayRun> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the testray runs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray runs
	 * @param end the upper bound of the range of testray runs (not inclusive)
	 * @return the range of testray runs
	 */
	@Override
	public List<TestrayRun> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the testray runs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray runs
	 * @param end the upper bound of the range of testray runs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray runs
	 */
	@Override
	public List<TestrayRun> findAll(int start, int end,
		OrderByComparator<TestrayRun> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the testray runs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray runs
	 * @param end the upper bound of the range of testray runs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of testray runs
	 */
	@Override
	public List<TestrayRun> findAll(int start, int end,
		OrderByComparator<TestrayRun> orderByComparator,
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

		List<TestrayRun> list = null;

		if (retrieveFromCache) {
			list = (List<TestrayRun>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TESTRAYRUN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTRAYRUN;

				if (pagination) {
					sql = sql.concat(TestrayRunModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestrayRun>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TestrayRun>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the testray runs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TestrayRun testrayRun : findAll()) {
			remove(testrayRun);
		}
	}

	/**
	 * Returns the number of testray runs.
	 *
	 * @return the number of testray runs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TESTRAYRUN);

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
		return TestrayRunModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the testray run persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TestrayRunImpl.class.getName());
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
	private static final String _SQL_SELECT_TESTRAYRUN = "SELECT testrayRun FROM TestrayRun testrayRun";
	private static final String _SQL_SELECT_TESTRAYRUN_WHERE_PKS_IN = "SELECT testrayRun FROM TestrayRun testrayRun WHERE testrayRunId IN (";
	private static final String _SQL_SELECT_TESTRAYRUN_WHERE = "SELECT testrayRun FROM TestrayRun testrayRun WHERE ";
	private static final String _SQL_COUNT_TESTRAYRUN = "SELECT COUNT(testrayRun) FROM TestrayRun testrayRun";
	private static final String _SQL_COUNT_TESTRAYRUN_WHERE = "SELECT COUNT(testrayRun) FROM TestrayRun testrayRun WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "testrayRun.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TestrayRun exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TestrayRun exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TestrayRunPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"number"
			});
}
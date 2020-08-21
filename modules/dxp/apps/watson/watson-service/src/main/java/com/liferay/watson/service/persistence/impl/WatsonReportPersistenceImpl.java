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

package com.liferay.watson.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.watson.exception.NoSuchReportException;
import com.liferay.watson.model.WatsonReport;
import com.liferay.watson.model.impl.WatsonReportImpl;
import com.liferay.watson.model.impl.WatsonReportModelImpl;
import com.liferay.watson.service.persistence.WatsonReportPersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the watson report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonReportPersistenceImpl
	extends BasePersistenceImpl<WatsonReport>
	implements WatsonReportPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonReportUtil</code> to access the watson report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonReportImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonReportPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("key", "key_");

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

		setModelClass(WatsonReport.class);
	}

	/**
	 * Caches the watson report in the entity cache if it is enabled.
	 *
	 * @param watsonReport the watson report
	 */
	@Override
	public void cacheResult(WatsonReport watsonReport) {
		entityCache.putResult(
			WatsonReportModelImpl.ENTITY_CACHE_ENABLED, WatsonReportImpl.class,
			watsonReport.getPrimaryKey(), watsonReport);

		watsonReport.resetOriginalValues();
	}

	/**
	 * Caches the watson reports in the entity cache if it is enabled.
	 *
	 * @param watsonReports the watson reports
	 */
	@Override
	public void cacheResult(List<WatsonReport> watsonReports) {
		for (WatsonReport watsonReport : watsonReports) {
			if (entityCache.getResult(
					WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
					WatsonReportImpl.class, watsonReport.getPrimaryKey()) ==
						null) {

				cacheResult(watsonReport);
			}
			else {
				watsonReport.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson reports.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonReportImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson report.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonReport watsonReport) {
		entityCache.removeResult(
			WatsonReportModelImpl.ENTITY_CACHE_ENABLED, WatsonReportImpl.class,
			watsonReport.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonReport> watsonReports) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonReport watsonReport : watsonReports) {
			entityCache.removeResult(
				WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
				WatsonReportImpl.class, watsonReport.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
				WatsonReportImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson report with the primary key. Does not add the watson report to the database.
	 *
	 * @param watsonReportId the primary key for the new watson report
	 * @return the new watson report
	 */
	@Override
	public WatsonReport create(long watsonReportId) {
		WatsonReport watsonReport = new WatsonReportImpl();

		watsonReport.setNew(true);
		watsonReport.setPrimaryKey(watsonReportId);

		watsonReport.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonReport;
	}

	/**
	 * Removes the watson report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportId the primary key of the watson report
	 * @return the watson report that was removed
	 * @throws NoSuchReportException if a watson report with the primary key could not be found
	 */
	@Override
	public WatsonReport remove(long watsonReportId)
		throws NoSuchReportException {

		return remove((Serializable)watsonReportId);
	}

	/**
	 * Removes the watson report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson report
	 * @return the watson report that was removed
	 * @throws NoSuchReportException if a watson report with the primary key could not be found
	 */
	@Override
	public WatsonReport remove(Serializable primaryKey)
		throws NoSuchReportException {

		Session session = null;

		try {
			session = openSession();

			WatsonReport watsonReport = (WatsonReport)session.get(
				WatsonReportImpl.class, primaryKey);

			if (watsonReport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReportException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonReport);
		}
		catch (NoSuchReportException noSuchEntityException) {
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
	protected WatsonReport removeImpl(WatsonReport watsonReport) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonReport)) {
				watsonReport = (WatsonReport)session.get(
					WatsonReportImpl.class, watsonReport.getPrimaryKeyObj());
			}

			if (watsonReport != null) {
				session.delete(watsonReport);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonReport != null) {
			clearCache(watsonReport);
		}

		return watsonReport;
	}

	@Override
	public WatsonReport updateImpl(WatsonReport watsonReport) {
		boolean isNew = watsonReport.isNew();

		if (!(watsonReport instanceof WatsonReportModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonReport.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonReport);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonReport proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonReport implementation " +
					watsonReport.getClass());
		}

		WatsonReportModelImpl watsonReportModelImpl =
			(WatsonReportModelImpl)watsonReport;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonReport.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonReport.setCreateDate(now);
			}
			else {
				watsonReport.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonReportModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonReport.setModifiedDate(now);
			}
			else {
				watsonReport.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonReport);

				watsonReport.setNew(false);
			}
			else {
				watsonReport = (WatsonReport)session.merge(watsonReport);
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
			WatsonReportModelImpl.ENTITY_CACHE_ENABLED, WatsonReportImpl.class,
			watsonReport.getPrimaryKey(), watsonReport, false);

		watsonReport.resetOriginalValues();

		return watsonReport;
	}

	/**
	 * Returns the watson report with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson report
	 * @return the watson report
	 * @throws NoSuchReportException if a watson report with the primary key could not be found
	 */
	@Override
	public WatsonReport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReportException {

		WatsonReport watsonReport = fetchByPrimaryKey(primaryKey);

		if (watsonReport == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReportException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonReport;
	}

	/**
	 * Returns the watson report with the primary key or throws a <code>NoSuchReportException</code> if it could not be found.
	 *
	 * @param watsonReportId the primary key of the watson report
	 * @return the watson report
	 * @throws NoSuchReportException if a watson report with the primary key could not be found
	 */
	@Override
	public WatsonReport findByPrimaryKey(long watsonReportId)
		throws NoSuchReportException {

		return findByPrimaryKey((Serializable)watsonReportId);
	}

	/**
	 * Returns the watson report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson report
	 * @return the watson report, or <code>null</code> if a watson report with the primary key could not be found
	 */
	@Override
	public WatsonReport fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonReportModelImpl.ENTITY_CACHE_ENABLED, WatsonReportImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonReport watsonReport = (WatsonReport)serializable;

		if (watsonReport == null) {
			Session session = null;

			try {
				session = openSession();

				watsonReport = (WatsonReport)session.get(
					WatsonReportImpl.class, primaryKey);

				if (watsonReport != null) {
					cacheResult(watsonReport);
				}
				else {
					entityCache.putResult(
						WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
						WatsonReportImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
					WatsonReportImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonReport;
	}

	/**
	 * Returns the watson report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonReportId the primary key of the watson report
	 * @return the watson report, or <code>null</code> if a watson report with the primary key could not be found
	 */
	@Override
	public WatsonReport fetchByPrimaryKey(long watsonReportId) {
		return fetchByPrimaryKey((Serializable)watsonReportId);
	}

	@Override
	public Map<Serializable, WatsonReport> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonReport> map =
			new HashMap<Serializable, WatsonReport>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonReport watsonReport = fetchByPrimaryKey(primaryKey);

			if (watsonReport != null) {
				map.put(primaryKey, watsonReport);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
				WatsonReportImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonReport)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONREPORT_WHERE_PKS_IN);

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

			for (WatsonReport watsonReport : (List<WatsonReport>)query.list()) {
				map.put(watsonReport.getPrimaryKeyObj(), watsonReport);

				cacheResult(watsonReport);

				uncachedPrimaryKeys.remove(watsonReport.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
					WatsonReportImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson reports.
	 *
	 * @return the watson reports
	 */
	@Override
	public List<WatsonReport> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson reports
	 * @param end the upper bound of the range of watson reports (not inclusive)
	 * @return the range of watson reports
	 */
	@Override
	public List<WatsonReport> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson reports
	 * @param end the upper bound of the range of watson reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson reports
	 */
	@Override
	public List<WatsonReport> findAll(
		int start, int end, OrderByComparator<WatsonReport> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson reports
	 * @param end the upper bound of the range of watson reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson reports
	 */
	@Override
	public List<WatsonReport> findAll(
		int start, int end, OrderByComparator<WatsonReport> orderByComparator,
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

		List<WatsonReport> list = null;

		if (useFinderCache) {
			list = (List<WatsonReport>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONREPORT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONREPORT;

				sql = sql.concat(WatsonReportModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonReport>)QueryUtil.list(
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
	 * Removes all the watson reports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonReport watsonReport : findAll()) {
			remove(watsonReport);
		}
	}

	/**
	 * Returns the number of watson reports.
	 *
	 * @return the number of watson reports
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONREPORT);

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
		return WatsonReportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson report persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportModelImpl.FINDER_CACHE_ENABLED, WatsonReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportModelImpl.FINDER_CACHE_ENABLED, WatsonReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonReportModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonReportImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONREPORT =
		"SELECT watsonReport FROM WatsonReport watsonReport";

	private static final String _SQL_SELECT_WATSONREPORT_WHERE_PKS_IN =
		"SELECT watsonReport FROM WatsonReport watsonReport WHERE watsonReportId IN (";

	private static final String _SQL_COUNT_WATSONREPORT =
		"SELECT COUNT(watsonReport) FROM WatsonReport watsonReport";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonReport.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonReport exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonReportPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"key"});

}
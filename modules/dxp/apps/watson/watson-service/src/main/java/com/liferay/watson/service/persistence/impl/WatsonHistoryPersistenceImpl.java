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
import com.liferay.watson.exception.NoSuchHistoryException;
import com.liferay.watson.model.WatsonHistory;
import com.liferay.watson.model.impl.WatsonHistoryImpl;
import com.liferay.watson.model.impl.WatsonHistoryModelImpl;
import com.liferay.watson.service.persistence.WatsonHistoryPersistence;

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
 * The persistence implementation for the watson history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonHistoryPersistenceImpl
	extends BasePersistenceImpl<WatsonHistory>
	implements WatsonHistoryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonHistoryUtil</code> to access the watson history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonHistoryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonHistoryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

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

		setModelClass(WatsonHistory.class);
	}

	/**
	 * Caches the watson history in the entity cache if it is enabled.
	 *
	 * @param watsonHistory the watson history
	 */
	@Override
	public void cacheResult(WatsonHistory watsonHistory) {
		entityCache.putResult(
			WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryImpl.class, watsonHistory.getPrimaryKey(),
			watsonHistory);

		watsonHistory.resetOriginalValues();
	}

	/**
	 * Caches the watson histories in the entity cache if it is enabled.
	 *
	 * @param watsonHistories the watson histories
	 */
	@Override
	public void cacheResult(List<WatsonHistory> watsonHistories) {
		for (WatsonHistory watsonHistory : watsonHistories) {
			if (entityCache.getResult(
					WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryImpl.class, watsonHistory.getPrimaryKey()) ==
						null) {

				cacheResult(watsonHistory);
			}
			else {
				watsonHistory.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson histories.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonHistoryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson history.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonHistory watsonHistory) {
		entityCache.removeResult(
			WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryImpl.class, watsonHistory.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonHistory> watsonHistories) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonHistory watsonHistory : watsonHistories) {
			entityCache.removeResult(
				WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryImpl.class, watsonHistory.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson history with the primary key. Does not add the watson history to the database.
	 *
	 * @param watsonHistoryId the primary key for the new watson history
	 * @return the new watson history
	 */
	@Override
	public WatsonHistory create(long watsonHistoryId) {
		WatsonHistory watsonHistory = new WatsonHistoryImpl();

		watsonHistory.setNew(true);
		watsonHistory.setPrimaryKey(watsonHistoryId);

		watsonHistory.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonHistory;
	}

	/**
	 * Removes the watson history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history that was removed
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory remove(long watsonHistoryId)
		throws NoSuchHistoryException {

		return remove((Serializable)watsonHistoryId);
	}

	/**
	 * Removes the watson history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson history
	 * @return the watson history that was removed
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory remove(Serializable primaryKey)
		throws NoSuchHistoryException {

		Session session = null;

		try {
			session = openSession();

			WatsonHistory watsonHistory = (WatsonHistory)session.get(
				WatsonHistoryImpl.class, primaryKey);

			if (watsonHistory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistoryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonHistory);
		}
		catch (NoSuchHistoryException noSuchEntityException) {
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
	protected WatsonHistory removeImpl(WatsonHistory watsonHistory) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonHistory)) {
				watsonHistory = (WatsonHistory)session.get(
					WatsonHistoryImpl.class, watsonHistory.getPrimaryKeyObj());
			}

			if (watsonHistory != null) {
				session.delete(watsonHistory);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonHistory != null) {
			clearCache(watsonHistory);
		}

		return watsonHistory;
	}

	@Override
	public WatsonHistory updateImpl(WatsonHistory watsonHistory) {
		boolean isNew = watsonHistory.isNew();

		if (!(watsonHistory instanceof WatsonHistoryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonHistory.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonHistory);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonHistory proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonHistory implementation " +
					watsonHistory.getClass());
		}

		WatsonHistoryModelImpl watsonHistoryModelImpl =
			(WatsonHistoryModelImpl)watsonHistory;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonHistory.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonHistory.setCreateDate(now);
			}
			else {
				watsonHistory.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonHistoryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonHistory.setModifiedDate(now);
			}
			else {
				watsonHistory.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonHistory);

				watsonHistory.setNew(false);
			}
			else {
				watsonHistory = (WatsonHistory)session.merge(watsonHistory);
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
			WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryImpl.class, watsonHistory.getPrimaryKey(),
			watsonHistory, false);

		watsonHistory.resetOriginalValues();

		return watsonHistory;
	}

	/**
	 * Returns the watson history with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson history
	 * @return the watson history
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistoryException {

		WatsonHistory watsonHistory = fetchByPrimaryKey(primaryKey);

		if (watsonHistory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistoryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonHistory;
	}

	/**
	 * Returns the watson history with the primary key or throws a <code>NoSuchHistoryException</code> if it could not be found.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history
	 * @throws NoSuchHistoryException if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory findByPrimaryKey(long watsonHistoryId)
		throws NoSuchHistoryException {

		return findByPrimaryKey((Serializable)watsonHistoryId);
	}

	/**
	 * Returns the watson history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson history
	 * @return the watson history, or <code>null</code> if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonHistory watsonHistory = (WatsonHistory)serializable;

		if (watsonHistory == null) {
			Session session = null;

			try {
				session = openSession();

				watsonHistory = (WatsonHistory)session.get(
					WatsonHistoryImpl.class, primaryKey);

				if (watsonHistory != null) {
					cacheResult(watsonHistory);
				}
				else {
					entityCache.putResult(
						WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
						WatsonHistoryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonHistory;
	}

	/**
	 * Returns the watson history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history, or <code>null</code> if a watson history with the primary key could not be found
	 */
	@Override
	public WatsonHistory fetchByPrimaryKey(long watsonHistoryId) {
		return fetchByPrimaryKey((Serializable)watsonHistoryId);
	}

	@Override
	public Map<Serializable, WatsonHistory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonHistory> map =
			new HashMap<Serializable, WatsonHistory>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonHistory watsonHistory = fetchByPrimaryKey(primaryKey);

			if (watsonHistory != null) {
				map.put(primaryKey, watsonHistory);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonHistory)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONHISTORY_WHERE_PKS_IN);

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

			for (WatsonHistory watsonHistory :
					(List<WatsonHistory>)query.list()) {

				map.put(watsonHistory.getPrimaryKeyObj(), watsonHistory);

				cacheResult(watsonHistory);

				uncachedPrimaryKeys.remove(watsonHistory.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson histories.
	 *
	 * @return the watson histories
	 */
	@Override
	public List<WatsonHistory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson histories
	 * @param end the upper bound of the range of watson histories (not inclusive)
	 * @return the range of watson histories
	 */
	@Override
	public List<WatsonHistory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson histories
	 * @param end the upper bound of the range of watson histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson histories
	 */
	@Override
	public List<WatsonHistory> findAll(
		int start, int end,
		OrderByComparator<WatsonHistory> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson histories
	 * @param end the upper bound of the range of watson histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson histories
	 */
	@Override
	public List<WatsonHistory> findAll(
		int start, int end, OrderByComparator<WatsonHistory> orderByComparator,
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

		List<WatsonHistory> list = null;

		if (useFinderCache) {
			list = (List<WatsonHistory>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONHISTORY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONHISTORY;

				sql = sql.concat(WatsonHistoryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonHistory>)QueryUtil.list(
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
	 * Removes all the watson histories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonHistory watsonHistory : findAll()) {
			remove(watsonHistory);
		}
	}

	/**
	 * Returns the number of watson histories.
	 *
	 * @return the number of watson histories
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONHISTORY);

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
		return WatsonHistoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson history persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryModelImpl.FINDER_CACHE_ENABLED,
			WatsonHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryModelImpl.FINDER_CACHE_ENABLED,
			WatsonHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonHistoryModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonHistoryImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONHISTORY =
		"SELECT watsonHistory FROM WatsonHistory watsonHistory";

	private static final String _SQL_SELECT_WATSONHISTORY_WHERE_PKS_IN =
		"SELECT watsonHistory FROM WatsonHistory watsonHistory WHERE watsonHistoryId IN (";

	private static final String _SQL_COUNT_WATSONHISTORY =
		"SELECT COUNT(watsonHistory) FROM WatsonHistory watsonHistory";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonHistory.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonHistory exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonHistoryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}
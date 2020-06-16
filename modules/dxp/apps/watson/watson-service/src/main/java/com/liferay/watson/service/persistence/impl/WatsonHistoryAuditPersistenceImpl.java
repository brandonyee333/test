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
import com.liferay.watson.exception.NoSuchHistoryAuditException;
import com.liferay.watson.model.WatsonHistoryAudit;
import com.liferay.watson.model.impl.WatsonHistoryAuditImpl;
import com.liferay.watson.model.impl.WatsonHistoryAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonHistoryAuditPersistence;

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
 * The persistence implementation for the watson history audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonHistoryAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonHistoryAudit>
	implements WatsonHistoryAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonHistoryAuditUtil</code> to access the watson history audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonHistoryAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonHistoryAuditPersistenceImpl() {
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

		setModelClass(WatsonHistoryAudit.class);
	}

	/**
	 * Caches the watson history audit in the entity cache if it is enabled.
	 *
	 * @param watsonHistoryAudit the watson history audit
	 */
	@Override
	public void cacheResult(WatsonHistoryAudit watsonHistoryAudit) {
		entityCache.putResult(
			WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryAuditImpl.class, watsonHistoryAudit.getPrimaryKey(),
			watsonHistoryAudit);

		watsonHistoryAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson history audits in the entity cache if it is enabled.
	 *
	 * @param watsonHistoryAudits the watson history audits
	 */
	@Override
	public void cacheResult(List<WatsonHistoryAudit> watsonHistoryAudits) {
		for (WatsonHistoryAudit watsonHistoryAudit : watsonHistoryAudits) {
			if (entityCache.getResult(
					WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryAuditImpl.class,
					watsonHistoryAudit.getPrimaryKey()) == null) {

				cacheResult(watsonHistoryAudit);
			}
			else {
				watsonHistoryAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson history audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonHistoryAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson history audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonHistoryAudit watsonHistoryAudit) {
		entityCache.removeResult(
			WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryAuditImpl.class, watsonHistoryAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonHistoryAudit> watsonHistoryAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonHistoryAudit watsonHistoryAudit : watsonHistoryAudits) {
			entityCache.removeResult(
				WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryAuditImpl.class,
				watsonHistoryAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson history audit with the primary key. Does not add the watson history audit to the database.
	 *
	 * @param watsonHistoryAuditId the primary key for the new watson history audit
	 * @return the new watson history audit
	 */
	@Override
	public WatsonHistoryAudit create(long watsonHistoryAuditId) {
		WatsonHistoryAudit watsonHistoryAudit = new WatsonHistoryAuditImpl();

		watsonHistoryAudit.setNew(true);
		watsonHistoryAudit.setPrimaryKey(watsonHistoryAuditId);

		watsonHistoryAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonHistoryAudit;
	}

	/**
	 * Removes the watson history audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistoryAuditId the primary key of the watson history audit
	 * @return the watson history audit that was removed
	 * @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	 */
	@Override
	public WatsonHistoryAudit remove(long watsonHistoryAuditId)
		throws NoSuchHistoryAuditException {

		return remove((Serializable)watsonHistoryAuditId);
	}

	/**
	 * Removes the watson history audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson history audit
	 * @return the watson history audit that was removed
	 * @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	 */
	@Override
	public WatsonHistoryAudit remove(Serializable primaryKey)
		throws NoSuchHistoryAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonHistoryAudit watsonHistoryAudit =
				(WatsonHistoryAudit)session.get(
					WatsonHistoryAuditImpl.class, primaryKey);

			if (watsonHistoryAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistoryAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonHistoryAudit);
		}
		catch (NoSuchHistoryAuditException noSuchEntityException) {
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
	protected WatsonHistoryAudit removeImpl(
		WatsonHistoryAudit watsonHistoryAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonHistoryAudit)) {
				watsonHistoryAudit = (WatsonHistoryAudit)session.get(
					WatsonHistoryAuditImpl.class,
					watsonHistoryAudit.getPrimaryKeyObj());
			}

			if (watsonHistoryAudit != null) {
				session.delete(watsonHistoryAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonHistoryAudit != null) {
			clearCache(watsonHistoryAudit);
		}

		return watsonHistoryAudit;
	}

	@Override
	public WatsonHistoryAudit updateImpl(
		WatsonHistoryAudit watsonHistoryAudit) {

		boolean isNew = watsonHistoryAudit.isNew();

		if (!(watsonHistoryAudit instanceof WatsonHistoryAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonHistoryAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonHistoryAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonHistoryAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonHistoryAudit implementation " +
					watsonHistoryAudit.getClass());
		}

		WatsonHistoryAuditModelImpl watsonHistoryAuditModelImpl =
			(WatsonHistoryAuditModelImpl)watsonHistoryAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonHistoryAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonHistoryAudit.setCreateDate(now);
			}
			else {
				watsonHistoryAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonHistoryAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonHistoryAudit.setModifiedDate(now);
			}
			else {
				watsonHistoryAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonHistoryAudit.isNew()) {
				session.save(watsonHistoryAudit);

				watsonHistoryAudit.setNew(false);
			}
			else {
				watsonHistoryAudit = (WatsonHistoryAudit)session.merge(
					watsonHistoryAudit);
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
			WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryAuditImpl.class, watsonHistoryAudit.getPrimaryKey(),
			watsonHistoryAudit, false);

		watsonHistoryAudit.resetOriginalValues();

		return watsonHistoryAudit;
	}

	/**
	 * Returns the watson history audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson history audit
	 * @return the watson history audit
	 * @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	 */
	@Override
	public WatsonHistoryAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistoryAuditException {

		WatsonHistoryAudit watsonHistoryAudit = fetchByPrimaryKey(primaryKey);

		if (watsonHistoryAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistoryAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonHistoryAudit;
	}

	/**
	 * Returns the watson history audit with the primary key or throws a <code>NoSuchHistoryAuditException</code> if it could not be found.
	 *
	 * @param watsonHistoryAuditId the primary key of the watson history audit
	 * @return the watson history audit
	 * @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	 */
	@Override
	public WatsonHistoryAudit findByPrimaryKey(long watsonHistoryAuditId)
		throws NoSuchHistoryAuditException {

		return findByPrimaryKey((Serializable)watsonHistoryAuditId);
	}

	/**
	 * Returns the watson history audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson history audit
	 * @return the watson history audit, or <code>null</code> if a watson history audit with the primary key could not be found
	 */
	@Override
	public WatsonHistoryAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonHistoryAudit watsonHistoryAudit =
			(WatsonHistoryAudit)serializable;

		if (watsonHistoryAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonHistoryAudit = (WatsonHistoryAudit)session.get(
					WatsonHistoryAuditImpl.class, primaryKey);

				if (watsonHistoryAudit != null) {
					cacheResult(watsonHistoryAudit);
				}
				else {
					entityCache.putResult(
						WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonHistoryAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonHistoryAudit;
	}

	/**
	 * Returns the watson history audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonHistoryAuditId the primary key of the watson history audit
	 * @return the watson history audit, or <code>null</code> if a watson history audit with the primary key could not be found
	 */
	@Override
	public WatsonHistoryAudit fetchByPrimaryKey(long watsonHistoryAuditId) {
		return fetchByPrimaryKey((Serializable)watsonHistoryAuditId);
	}

	@Override
	public Map<Serializable, WatsonHistoryAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonHistoryAudit> map =
			new HashMap<Serializable, WatsonHistoryAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonHistoryAudit watsonHistoryAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonHistoryAudit != null) {
				map.put(primaryKey, watsonHistoryAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonHistoryAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonHistoryAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONHISTORYAUDIT_WHERE_PKS_IN);

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

			for (WatsonHistoryAudit watsonHistoryAudit :
					(List<WatsonHistoryAudit>)query.list()) {

				map.put(
					watsonHistoryAudit.getPrimaryKeyObj(), watsonHistoryAudit);

				cacheResult(watsonHistoryAudit);

				uncachedPrimaryKeys.remove(
					watsonHistoryAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonHistoryAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson history audits.
	 *
	 * @return the watson history audits
	 */
	@Override
	public List<WatsonHistoryAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson history audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson history audits
	 * @param end the upper bound of the range of watson history audits (not inclusive)
	 * @return the range of watson history audits
	 */
	@Override
	public List<WatsonHistoryAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson history audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson history audits
	 * @param end the upper bound of the range of watson history audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson history audits
	 */
	@Override
	public List<WatsonHistoryAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonHistoryAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson history audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson history audits
	 * @param end the upper bound of the range of watson history audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson history audits
	 */
	@Override
	public List<WatsonHistoryAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonHistoryAudit> orderByComparator,
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

		List<WatsonHistoryAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonHistoryAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONHISTORYAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONHISTORYAUDIT;

				sql = sql.concat(WatsonHistoryAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonHistoryAudit>)QueryUtil.list(
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
	 * Removes all the watson history audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonHistoryAudit watsonHistoryAudit : findAll()) {
			remove(watsonHistoryAudit);
		}
	}

	/**
	 * Returns the number of watson history audits.
	 *
	 * @return the number of watson history audits
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
					_SQL_COUNT_WATSONHISTORYAUDIT);

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
		return WatsonHistoryAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson history audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonHistoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonHistoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonHistoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonHistoryAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonHistoryAuditImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONHISTORYAUDIT =
		"SELECT watsonHistoryAudit FROM WatsonHistoryAudit watsonHistoryAudit";

	private static final String _SQL_SELECT_WATSONHISTORYAUDIT_WHERE_PKS_IN =
		"SELECT watsonHistoryAudit FROM WatsonHistoryAudit watsonHistoryAudit WHERE watsonHistoryAuditId IN (";

	private static final String _SQL_COUNT_WATSONHISTORYAUDIT =
		"SELECT COUNT(watsonHistoryAudit) FROM WatsonHistoryAudit watsonHistoryAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonHistoryAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonHistoryAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonHistoryAuditPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}
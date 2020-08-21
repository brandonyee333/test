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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.watson.exception.NoSuchChildAuditException;
import com.liferay.watson.model.WatsonChildAudit;
import com.liferay.watson.model.impl.WatsonChildAuditImpl;
import com.liferay.watson.model.impl.WatsonChildAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonChildAuditPersistence;

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
 * The persistence implementation for the watson child audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonChildAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonChildAudit>
	implements WatsonChildAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonChildAuditUtil</code> to access the watson child audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonChildAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonChildAuditPersistenceImpl() {
		setModelClass(WatsonChildAudit.class);
	}

	/**
	 * Caches the watson child audit in the entity cache if it is enabled.
	 *
	 * @param watsonChildAudit the watson child audit
	 */
	@Override
	public void cacheResult(WatsonChildAudit watsonChildAudit) {
		entityCache.putResult(
			WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildAuditImpl.class, watsonChildAudit.getPrimaryKey(),
			watsonChildAudit);

		watsonChildAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson child audits in the entity cache if it is enabled.
	 *
	 * @param watsonChildAudits the watson child audits
	 */
	@Override
	public void cacheResult(List<WatsonChildAudit> watsonChildAudits) {
		for (WatsonChildAudit watsonChildAudit : watsonChildAudits) {
			if (entityCache.getResult(
					WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonChildAuditImpl.class,
					watsonChildAudit.getPrimaryKey()) == null) {

				cacheResult(watsonChildAudit);
			}
			else {
				watsonChildAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson child audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonChildAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson child audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonChildAudit watsonChildAudit) {
		entityCache.removeResult(
			WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildAuditImpl.class, watsonChildAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonChildAudit> watsonChildAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonChildAudit watsonChildAudit : watsonChildAudits) {
			entityCache.removeResult(
				WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonChildAuditImpl.class, watsonChildAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonChildAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson child audit with the primary key. Does not add the watson child audit to the database.
	 *
	 * @param watsonChildAuditId the primary key for the new watson child audit
	 * @return the new watson child audit
	 */
	@Override
	public WatsonChildAudit create(long watsonChildAuditId) {
		WatsonChildAudit watsonChildAudit = new WatsonChildAuditImpl();

		watsonChildAudit.setNew(true);
		watsonChildAudit.setPrimaryKey(watsonChildAuditId);

		watsonChildAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonChildAudit;
	}

	/**
	 * Removes the watson child audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAuditId the primary key of the watson child audit
	 * @return the watson child audit that was removed
	 * @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	 */
	@Override
	public WatsonChildAudit remove(long watsonChildAuditId)
		throws NoSuchChildAuditException {

		return remove((Serializable)watsonChildAuditId);
	}

	/**
	 * Removes the watson child audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson child audit
	 * @return the watson child audit that was removed
	 * @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	 */
	@Override
	public WatsonChildAudit remove(Serializable primaryKey)
		throws NoSuchChildAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonChildAudit watsonChildAudit = (WatsonChildAudit)session.get(
				WatsonChildAuditImpl.class, primaryKey);

			if (watsonChildAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChildAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonChildAudit);
		}
		catch (NoSuchChildAuditException noSuchEntityException) {
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
	protected WatsonChildAudit removeImpl(WatsonChildAudit watsonChildAudit) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonChildAudit)) {
				watsonChildAudit = (WatsonChildAudit)session.get(
					WatsonChildAuditImpl.class,
					watsonChildAudit.getPrimaryKeyObj());
			}

			if (watsonChildAudit != null) {
				session.delete(watsonChildAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonChildAudit != null) {
			clearCache(watsonChildAudit);
		}

		return watsonChildAudit;
	}

	@Override
	public WatsonChildAudit updateImpl(WatsonChildAudit watsonChildAudit) {
		boolean isNew = watsonChildAudit.isNew();

		if (!(watsonChildAudit instanceof WatsonChildAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonChildAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonChildAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonChildAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonChildAudit implementation " +
					watsonChildAudit.getClass());
		}

		WatsonChildAuditModelImpl watsonChildAuditModelImpl =
			(WatsonChildAuditModelImpl)watsonChildAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonChildAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonChildAudit.setCreateDate(now);
			}
			else {
				watsonChildAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonChildAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonChildAudit.setModifiedDate(now);
			}
			else {
				watsonChildAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonChildAudit);

				watsonChildAudit.setNew(false);
			}
			else {
				watsonChildAudit = (WatsonChildAudit)session.merge(
					watsonChildAudit);
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
			WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildAuditImpl.class, watsonChildAudit.getPrimaryKey(),
			watsonChildAudit, false);

		watsonChildAudit.resetOriginalValues();

		return watsonChildAudit;
	}

	/**
	 * Returns the watson child audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson child audit
	 * @return the watson child audit
	 * @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	 */
	@Override
	public WatsonChildAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChildAuditException {

		WatsonChildAudit watsonChildAudit = fetchByPrimaryKey(primaryKey);

		if (watsonChildAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChildAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonChildAudit;
	}

	/**
	 * Returns the watson child audit with the primary key or throws a <code>NoSuchChildAuditException</code> if it could not be found.
	 *
	 * @param watsonChildAuditId the primary key of the watson child audit
	 * @return the watson child audit
	 * @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	 */
	@Override
	public WatsonChildAudit findByPrimaryKey(long watsonChildAuditId)
		throws NoSuchChildAuditException {

		return findByPrimaryKey((Serializable)watsonChildAuditId);
	}

	/**
	 * Returns the watson child audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson child audit
	 * @return the watson child audit, or <code>null</code> if a watson child audit with the primary key could not be found
	 */
	@Override
	public WatsonChildAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonChildAudit watsonChildAudit = (WatsonChildAudit)serializable;

		if (watsonChildAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonChildAudit = (WatsonChildAudit)session.get(
					WatsonChildAuditImpl.class, primaryKey);

				if (watsonChildAudit != null) {
					cacheResult(watsonChildAudit);
				}
				else {
					entityCache.putResult(
						WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonChildAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonChildAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonChildAudit;
	}

	/**
	 * Returns the watson child audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonChildAuditId the primary key of the watson child audit
	 * @return the watson child audit, or <code>null</code> if a watson child audit with the primary key could not be found
	 */
	@Override
	public WatsonChildAudit fetchByPrimaryKey(long watsonChildAuditId) {
		return fetchByPrimaryKey((Serializable)watsonChildAuditId);
	}

	@Override
	public Map<Serializable, WatsonChildAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonChildAudit> map =
			new HashMap<Serializable, WatsonChildAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonChildAudit watsonChildAudit = fetchByPrimaryKey(primaryKey);

			if (watsonChildAudit != null) {
				map.put(primaryKey, watsonChildAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonChildAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonChildAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONCHILDAUDIT_WHERE_PKS_IN);

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

			for (WatsonChildAudit watsonChildAudit :
					(List<WatsonChildAudit>)query.list()) {

				map.put(watsonChildAudit.getPrimaryKeyObj(), watsonChildAudit);

				cacheResult(watsonChildAudit);

				uncachedPrimaryKeys.remove(watsonChildAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonChildAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson child audits.
	 *
	 * @return the watson child audits
	 */
	@Override
	public List<WatsonChildAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson child audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonChildAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson child audits
	 * @param end the upper bound of the range of watson child audits (not inclusive)
	 * @return the range of watson child audits
	 */
	@Override
	public List<WatsonChildAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson child audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonChildAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson child audits
	 * @param end the upper bound of the range of watson child audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson child audits
	 */
	@Override
	public List<WatsonChildAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonChildAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson child audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonChildAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson child audits
	 * @param end the upper bound of the range of watson child audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson child audits
	 */
	@Override
	public List<WatsonChildAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonChildAudit> orderByComparator,
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

		List<WatsonChildAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonChildAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONCHILDAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONCHILDAUDIT;

				sql = sql.concat(WatsonChildAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonChildAudit>)QueryUtil.list(
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
	 * Removes all the watson child audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonChildAudit watsonChildAudit : findAll()) {
			remove(watsonChildAudit);
		}
	}

	/**
	 * Returns the number of watson child audits.
	 *
	 * @return the number of watson child audits
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONCHILDAUDIT);

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
		return WatsonChildAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson child audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonChildAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonChildAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonChildAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonChildAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONCHILDAUDIT =
		"SELECT watsonChildAudit FROM WatsonChildAudit watsonChildAudit";

	private static final String _SQL_SELECT_WATSONCHILDAUDIT_WHERE_PKS_IN =
		"SELECT watsonChildAudit FROM WatsonChildAudit watsonChildAudit WHERE watsonChildAuditId IN (";

	private static final String _SQL_COUNT_WATSONCHILDAUDIT =
		"SELECT COUNT(watsonChildAudit) FROM WatsonChildAudit watsonChildAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonChildAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonChildAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonChildAuditPersistenceImpl.class);

}
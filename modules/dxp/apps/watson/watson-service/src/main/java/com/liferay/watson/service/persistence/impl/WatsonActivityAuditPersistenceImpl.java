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
import com.liferay.watson.exception.NoSuchActivityAuditException;
import com.liferay.watson.model.WatsonActivityAudit;
import com.liferay.watson.model.impl.WatsonActivityAuditImpl;
import com.liferay.watson.model.impl.WatsonActivityAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonActivityAuditPersistence;

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
 * The persistence implementation for the watson activity audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonActivityAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonActivityAudit>
	implements WatsonActivityAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonActivityAuditUtil</code> to access the watson activity audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonActivityAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonActivityAuditPersistenceImpl() {
		setModelClass(WatsonActivityAudit.class);
	}

	/**
	 * Caches the watson activity audit in the entity cache if it is enabled.
	 *
	 * @param watsonActivityAudit the watson activity audit
	 */
	@Override
	public void cacheResult(WatsonActivityAudit watsonActivityAudit) {
		entityCache.putResult(
			WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityAuditImpl.class, watsonActivityAudit.getPrimaryKey(),
			watsonActivityAudit);

		watsonActivityAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson activity audits in the entity cache if it is enabled.
	 *
	 * @param watsonActivityAudits the watson activity audits
	 */
	@Override
	public void cacheResult(List<WatsonActivityAudit> watsonActivityAudits) {
		for (WatsonActivityAudit watsonActivityAudit : watsonActivityAudits) {
			if (entityCache.getResult(
					WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityAuditImpl.class,
					watsonActivityAudit.getPrimaryKey()) == null) {

				cacheResult(watsonActivityAudit);
			}
			else {
				watsonActivityAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson activity audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonActivityAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson activity audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonActivityAudit watsonActivityAudit) {
		entityCache.removeResult(
			WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityAuditImpl.class, watsonActivityAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonActivityAudit> watsonActivityAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonActivityAudit watsonActivityAudit : watsonActivityAudits) {
			entityCache.removeResult(
				WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityAuditImpl.class,
				watsonActivityAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson activity audit with the primary key. Does not add the watson activity audit to the database.
	 *
	 * @param watsonActivityAuditId the primary key for the new watson activity audit
	 * @return the new watson activity audit
	 */
	@Override
	public WatsonActivityAudit create(long watsonActivityAuditId) {
		WatsonActivityAudit watsonActivityAudit = new WatsonActivityAuditImpl();

		watsonActivityAudit.setNew(true);
		watsonActivityAudit.setPrimaryKey(watsonActivityAuditId);

		watsonActivityAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonActivityAudit;
	}

	/**
	 * Removes the watson activity audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityAuditId the primary key of the watson activity audit
	 * @return the watson activity audit that was removed
	 * @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	 */
	@Override
	public WatsonActivityAudit remove(long watsonActivityAuditId)
		throws NoSuchActivityAuditException {

		return remove((Serializable)watsonActivityAuditId);
	}

	/**
	 * Removes the watson activity audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson activity audit
	 * @return the watson activity audit that was removed
	 * @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	 */
	@Override
	public WatsonActivityAudit remove(Serializable primaryKey)
		throws NoSuchActivityAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonActivityAudit watsonActivityAudit =
				(WatsonActivityAudit)session.get(
					WatsonActivityAuditImpl.class, primaryKey);

			if (watsonActivityAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonActivityAudit);
		}
		catch (NoSuchActivityAuditException noSuchEntityException) {
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
	protected WatsonActivityAudit removeImpl(
		WatsonActivityAudit watsonActivityAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonActivityAudit)) {
				watsonActivityAudit = (WatsonActivityAudit)session.get(
					WatsonActivityAuditImpl.class,
					watsonActivityAudit.getPrimaryKeyObj());
			}

			if (watsonActivityAudit != null) {
				session.delete(watsonActivityAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonActivityAudit != null) {
			clearCache(watsonActivityAudit);
		}

		return watsonActivityAudit;
	}

	@Override
	public WatsonActivityAudit updateImpl(
		WatsonActivityAudit watsonActivityAudit) {

		boolean isNew = watsonActivityAudit.isNew();

		if (!(watsonActivityAudit instanceof WatsonActivityAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonActivityAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonActivityAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonActivityAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonActivityAudit implementation " +
					watsonActivityAudit.getClass());
		}

		WatsonActivityAuditModelImpl watsonActivityAuditModelImpl =
			(WatsonActivityAuditModelImpl)watsonActivityAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonActivityAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonActivityAudit.setCreateDate(now);
			}
			else {
				watsonActivityAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonActivityAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonActivityAudit.setModifiedDate(now);
			}
			else {
				watsonActivityAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonActivityAudit);

				watsonActivityAudit.setNew(false);
			}
			else {
				watsonActivityAudit = (WatsonActivityAudit)session.merge(
					watsonActivityAudit);
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
			WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityAuditImpl.class, watsonActivityAudit.getPrimaryKey(),
			watsonActivityAudit, false);

		watsonActivityAudit.resetOriginalValues();

		return watsonActivityAudit;
	}

	/**
	 * Returns the watson activity audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson activity audit
	 * @return the watson activity audit
	 * @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	 */
	@Override
	public WatsonActivityAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityAuditException {

		WatsonActivityAudit watsonActivityAudit = fetchByPrimaryKey(primaryKey);

		if (watsonActivityAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonActivityAudit;
	}

	/**
	 * Returns the watson activity audit with the primary key or throws a <code>NoSuchActivityAuditException</code> if it could not be found.
	 *
	 * @param watsonActivityAuditId the primary key of the watson activity audit
	 * @return the watson activity audit
	 * @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	 */
	@Override
	public WatsonActivityAudit findByPrimaryKey(long watsonActivityAuditId)
		throws NoSuchActivityAuditException {

		return findByPrimaryKey((Serializable)watsonActivityAuditId);
	}

	/**
	 * Returns the watson activity audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson activity audit
	 * @return the watson activity audit, or <code>null</code> if a watson activity audit with the primary key could not be found
	 */
	@Override
	public WatsonActivityAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonActivityAudit watsonActivityAudit =
			(WatsonActivityAudit)serializable;

		if (watsonActivityAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonActivityAudit = (WatsonActivityAudit)session.get(
					WatsonActivityAuditImpl.class, primaryKey);

				if (watsonActivityAudit != null) {
					cacheResult(watsonActivityAudit);
				}
				else {
					entityCache.putResult(
						WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonActivityAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonActivityAudit;
	}

	/**
	 * Returns the watson activity audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonActivityAuditId the primary key of the watson activity audit
	 * @return the watson activity audit, or <code>null</code> if a watson activity audit with the primary key could not be found
	 */
	@Override
	public WatsonActivityAudit fetchByPrimaryKey(long watsonActivityAuditId) {
		return fetchByPrimaryKey((Serializable)watsonActivityAuditId);
	}

	@Override
	public Map<Serializable, WatsonActivityAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonActivityAudit> map =
			new HashMap<Serializable, WatsonActivityAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonActivityAudit watsonActivityAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonActivityAudit != null) {
				map.put(primaryKey, watsonActivityAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonActivityAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONACTIVITYAUDIT_WHERE_PKS_IN);

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

			for (WatsonActivityAudit watsonActivityAudit :
					(List<WatsonActivityAudit>)query.list()) {

				map.put(
					watsonActivityAudit.getPrimaryKeyObj(),
					watsonActivityAudit);

				cacheResult(watsonActivityAudit);

				uncachedPrimaryKeys.remove(
					watsonActivityAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson activity audits.
	 *
	 * @return the watson activity audits
	 */
	@Override
	public List<WatsonActivityAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson activity audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonActivityAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activity audits
	 * @param end the upper bound of the range of watson activity audits (not inclusive)
	 * @return the range of watson activity audits
	 */
	@Override
	public List<WatsonActivityAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson activity audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonActivityAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activity audits
	 * @param end the upper bound of the range of watson activity audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson activity audits
	 */
	@Override
	public List<WatsonActivityAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonActivityAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson activity audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonActivityAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activity audits
	 * @param end the upper bound of the range of watson activity audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson activity audits
	 */
	@Override
	public List<WatsonActivityAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonActivityAudit> orderByComparator,
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

		List<WatsonActivityAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonActivityAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONACTIVITYAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONACTIVITYAUDIT;

				sql = sql.concat(WatsonActivityAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonActivityAudit>)QueryUtil.list(
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
	 * Removes all the watson activity audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonActivityAudit watsonActivityAudit : findAll()) {
			remove(watsonActivityAudit);
		}
	}

	/**
	 * Returns the number of watson activity audits.
	 *
	 * @return the number of watson activity audits
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
					_SQL_COUNT_WATSONACTIVITYAUDIT);

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
		return WatsonActivityAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson activity audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonActivityAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonActivityAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonActivityAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonActivityAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONACTIVITYAUDIT =
		"SELECT watsonActivityAudit FROM WatsonActivityAudit watsonActivityAudit";

	private static final String _SQL_SELECT_WATSONACTIVITYAUDIT_WHERE_PKS_IN =
		"SELECT watsonActivityAudit FROM WatsonActivityAudit watsonActivityAudit WHERE watsonActivityAuditId IN (";

	private static final String _SQL_COUNT_WATSONACTIVITYAUDIT =
		"SELECT COUNT(watsonActivityAudit) FROM WatsonActivityAudit watsonActivityAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonActivityAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonActivityAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonActivityAuditPersistenceImpl.class);

}
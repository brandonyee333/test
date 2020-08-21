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
import com.liferay.watson.exception.NoSuchPersonAuditException;
import com.liferay.watson.model.WatsonPersonAudit;
import com.liferay.watson.model.impl.WatsonPersonAuditImpl;
import com.liferay.watson.model.impl.WatsonPersonAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonPersonAuditPersistence;

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
 * The persistence implementation for the watson person audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonPersonAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonPersonAudit>
	implements WatsonPersonAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonPersonAuditUtil</code> to access the watson person audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonPersonAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonPersonAuditPersistenceImpl() {
		setModelClass(WatsonPersonAudit.class);
	}

	/**
	 * Caches the watson person audit in the entity cache if it is enabled.
	 *
	 * @param watsonPersonAudit the watson person audit
	 */
	@Override
	public void cacheResult(WatsonPersonAudit watsonPersonAudit) {
		entityCache.putResult(
			WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonAuditImpl.class, watsonPersonAudit.getPrimaryKey(),
			watsonPersonAudit);

		watsonPersonAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson person audits in the entity cache if it is enabled.
	 *
	 * @param watsonPersonAudits the watson person audits
	 */
	@Override
	public void cacheResult(List<WatsonPersonAudit> watsonPersonAudits) {
		for (WatsonPersonAudit watsonPersonAudit : watsonPersonAudits) {
			if (entityCache.getResult(
					WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonPersonAuditImpl.class,
					watsonPersonAudit.getPrimaryKey()) == null) {

				cacheResult(watsonPersonAudit);
			}
			else {
				watsonPersonAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson person audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonPersonAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson person audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonPersonAudit watsonPersonAudit) {
		entityCache.removeResult(
			WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonAuditImpl.class, watsonPersonAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonPersonAudit> watsonPersonAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonPersonAudit watsonPersonAudit : watsonPersonAudits) {
			entityCache.removeResult(
				WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonPersonAuditImpl.class, watsonPersonAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonPersonAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson person audit with the primary key. Does not add the watson person audit to the database.
	 *
	 * @param watsonPersonAuditId the primary key for the new watson person audit
	 * @return the new watson person audit
	 */
	@Override
	public WatsonPersonAudit create(long watsonPersonAuditId) {
		WatsonPersonAudit watsonPersonAudit = new WatsonPersonAuditImpl();

		watsonPersonAudit.setNew(true);
		watsonPersonAudit.setPrimaryKey(watsonPersonAuditId);

		watsonPersonAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonPersonAudit;
	}

	/**
	 * Removes the watson person audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit that was removed
	 * @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	 */
	@Override
	public WatsonPersonAudit remove(long watsonPersonAuditId)
		throws NoSuchPersonAuditException {

		return remove((Serializable)watsonPersonAuditId);
	}

	/**
	 * Removes the watson person audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson person audit
	 * @return the watson person audit that was removed
	 * @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	 */
	@Override
	public WatsonPersonAudit remove(Serializable primaryKey)
		throws NoSuchPersonAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonPersonAudit watsonPersonAudit =
				(WatsonPersonAudit)session.get(
					WatsonPersonAuditImpl.class, primaryKey);

			if (watsonPersonAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPersonAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonPersonAudit);
		}
		catch (NoSuchPersonAuditException noSuchEntityException) {
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
	protected WatsonPersonAudit removeImpl(
		WatsonPersonAudit watsonPersonAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonPersonAudit)) {
				watsonPersonAudit = (WatsonPersonAudit)session.get(
					WatsonPersonAuditImpl.class,
					watsonPersonAudit.getPrimaryKeyObj());
			}

			if (watsonPersonAudit != null) {
				session.delete(watsonPersonAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonPersonAudit != null) {
			clearCache(watsonPersonAudit);
		}

		return watsonPersonAudit;
	}

	@Override
	public WatsonPersonAudit updateImpl(WatsonPersonAudit watsonPersonAudit) {
		boolean isNew = watsonPersonAudit.isNew();

		if (!(watsonPersonAudit instanceof WatsonPersonAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonPersonAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonPersonAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonPersonAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonPersonAudit implementation " +
					watsonPersonAudit.getClass());
		}

		WatsonPersonAuditModelImpl watsonPersonAuditModelImpl =
			(WatsonPersonAuditModelImpl)watsonPersonAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonPersonAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonPersonAudit.setCreateDate(now);
			}
			else {
				watsonPersonAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonPersonAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonPersonAudit.setModifiedDate(now);
			}
			else {
				watsonPersonAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonPersonAudit);

				watsonPersonAudit.setNew(false);
			}
			else {
				watsonPersonAudit = (WatsonPersonAudit)session.merge(
					watsonPersonAudit);
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
			WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonAuditImpl.class, watsonPersonAudit.getPrimaryKey(),
			watsonPersonAudit, false);

		watsonPersonAudit.resetOriginalValues();

		return watsonPersonAudit;
	}

	/**
	 * Returns the watson person audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson person audit
	 * @return the watson person audit
	 * @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	 */
	@Override
	public WatsonPersonAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPersonAuditException {

		WatsonPersonAudit watsonPersonAudit = fetchByPrimaryKey(primaryKey);

		if (watsonPersonAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPersonAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonPersonAudit;
	}

	/**
	 * Returns the watson person audit with the primary key or throws a <code>NoSuchPersonAuditException</code> if it could not be found.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit
	 * @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	 */
	@Override
	public WatsonPersonAudit findByPrimaryKey(long watsonPersonAuditId)
		throws NoSuchPersonAuditException {

		return findByPrimaryKey((Serializable)watsonPersonAuditId);
	}

	/**
	 * Returns the watson person audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson person audit
	 * @return the watson person audit, or <code>null</code> if a watson person audit with the primary key could not be found
	 */
	@Override
	public WatsonPersonAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonPersonAudit watsonPersonAudit = (WatsonPersonAudit)serializable;

		if (watsonPersonAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonPersonAudit = (WatsonPersonAudit)session.get(
					WatsonPersonAuditImpl.class, primaryKey);

				if (watsonPersonAudit != null) {
					cacheResult(watsonPersonAudit);
				}
				else {
					entityCache.putResult(
						WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonPersonAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonPersonAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonPersonAudit;
	}

	/**
	 * Returns the watson person audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit, or <code>null</code> if a watson person audit with the primary key could not be found
	 */
	@Override
	public WatsonPersonAudit fetchByPrimaryKey(long watsonPersonAuditId) {
		return fetchByPrimaryKey((Serializable)watsonPersonAuditId);
	}

	@Override
	public Map<Serializable, WatsonPersonAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonPersonAudit> map =
			new HashMap<Serializable, WatsonPersonAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonPersonAudit watsonPersonAudit = fetchByPrimaryKey(primaryKey);

			if (watsonPersonAudit != null) {
				map.put(primaryKey, watsonPersonAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonPersonAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonPersonAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONPERSONAUDIT_WHERE_PKS_IN);

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

			for (WatsonPersonAudit watsonPersonAudit :
					(List<WatsonPersonAudit>)query.list()) {

				map.put(
					watsonPersonAudit.getPrimaryKeyObj(), watsonPersonAudit);

				cacheResult(watsonPersonAudit);

				uncachedPrimaryKeys.remove(
					watsonPersonAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonPersonAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson person audits.
	 *
	 * @return the watson person audits
	 */
	@Override
	public List<WatsonPersonAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson person audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonPersonAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson person audits
	 * @param end the upper bound of the range of watson person audits (not inclusive)
	 * @return the range of watson person audits
	 */
	@Override
	public List<WatsonPersonAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson person audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonPersonAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson person audits
	 * @param end the upper bound of the range of watson person audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson person audits
	 */
	@Override
	public List<WatsonPersonAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonPersonAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson person audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonPersonAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson person audits
	 * @param end the upper bound of the range of watson person audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson person audits
	 */
	@Override
	public List<WatsonPersonAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonPersonAudit> orderByComparator,
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

		List<WatsonPersonAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonPersonAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONPERSONAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONPERSONAUDIT;

				sql = sql.concat(WatsonPersonAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonPersonAudit>)QueryUtil.list(
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
	 * Removes all the watson person audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonPersonAudit watsonPersonAudit : findAll()) {
			remove(watsonPersonAudit);
		}
	}

	/**
	 * Returns the number of watson person audits.
	 *
	 * @return the number of watson person audits
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONPERSONAUDIT);

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
		return WatsonPersonAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson person audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonPersonAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonPersonAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonPersonAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonPersonAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonPersonAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONPERSONAUDIT =
		"SELECT watsonPersonAudit FROM WatsonPersonAudit watsonPersonAudit";

	private static final String _SQL_SELECT_WATSONPERSONAUDIT_WHERE_PKS_IN =
		"SELECT watsonPersonAudit FROM WatsonPersonAudit watsonPersonAudit WHERE watsonPersonAuditId IN (";

	private static final String _SQL_COUNT_WATSONPERSONAUDIT =
		"SELECT COUNT(watsonPersonAudit) FROM WatsonPersonAudit watsonPersonAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonPersonAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonPersonAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonPersonAuditPersistenceImpl.class);

}
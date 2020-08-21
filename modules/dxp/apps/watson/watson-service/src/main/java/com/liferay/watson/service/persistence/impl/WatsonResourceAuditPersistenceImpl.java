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
import com.liferay.watson.exception.NoSuchResourceAuditException;
import com.liferay.watson.model.WatsonResourceAudit;
import com.liferay.watson.model.impl.WatsonResourceAuditImpl;
import com.liferay.watson.model.impl.WatsonResourceAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonResourceAuditPersistence;

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
 * The persistence implementation for the watson resource audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonResourceAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonResourceAudit>
	implements WatsonResourceAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonResourceAuditUtil</code> to access the watson resource audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonResourceAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonResourceAuditPersistenceImpl() {
		setModelClass(WatsonResourceAudit.class);
	}

	/**
	 * Caches the watson resource audit in the entity cache if it is enabled.
	 *
	 * @param watsonResourceAudit the watson resource audit
	 */
	@Override
	public void cacheResult(WatsonResourceAudit watsonResourceAudit) {
		entityCache.putResult(
			WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceAuditImpl.class, watsonResourceAudit.getPrimaryKey(),
			watsonResourceAudit);

		watsonResourceAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson resource audits in the entity cache if it is enabled.
	 *
	 * @param watsonResourceAudits the watson resource audits
	 */
	@Override
	public void cacheResult(List<WatsonResourceAudit> watsonResourceAudits) {
		for (WatsonResourceAudit watsonResourceAudit : watsonResourceAudits) {
			if (entityCache.getResult(
					WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonResourceAuditImpl.class,
					watsonResourceAudit.getPrimaryKey()) == null) {

				cacheResult(watsonResourceAudit);
			}
			else {
				watsonResourceAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson resource audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonResourceAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson resource audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonResourceAudit watsonResourceAudit) {
		entityCache.removeResult(
			WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceAuditImpl.class, watsonResourceAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonResourceAudit> watsonResourceAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonResourceAudit watsonResourceAudit : watsonResourceAudits) {
			entityCache.removeResult(
				WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonResourceAuditImpl.class,
				watsonResourceAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonResourceAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson resource audit with the primary key. Does not add the watson resource audit to the database.
	 *
	 * @param watsonResourceAuditId the primary key for the new watson resource audit
	 * @return the new watson resource audit
	 */
	@Override
	public WatsonResourceAudit create(long watsonResourceAuditId) {
		WatsonResourceAudit watsonResourceAudit = new WatsonResourceAuditImpl();

		watsonResourceAudit.setNew(true);
		watsonResourceAudit.setPrimaryKey(watsonResourceAuditId);

		watsonResourceAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonResourceAudit;
	}

	/**
	 * Removes the watson resource audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonResourceAuditId the primary key of the watson resource audit
	 * @return the watson resource audit that was removed
	 * @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	 */
	@Override
	public WatsonResourceAudit remove(long watsonResourceAuditId)
		throws NoSuchResourceAuditException {

		return remove((Serializable)watsonResourceAuditId);
	}

	/**
	 * Removes the watson resource audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson resource audit
	 * @return the watson resource audit that was removed
	 * @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	 */
	@Override
	public WatsonResourceAudit remove(Serializable primaryKey)
		throws NoSuchResourceAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonResourceAudit watsonResourceAudit =
				(WatsonResourceAudit)session.get(
					WatsonResourceAuditImpl.class, primaryKey);

			if (watsonResourceAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResourceAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonResourceAudit);
		}
		catch (NoSuchResourceAuditException noSuchEntityException) {
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
	protected WatsonResourceAudit removeImpl(
		WatsonResourceAudit watsonResourceAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonResourceAudit)) {
				watsonResourceAudit = (WatsonResourceAudit)session.get(
					WatsonResourceAuditImpl.class,
					watsonResourceAudit.getPrimaryKeyObj());
			}

			if (watsonResourceAudit != null) {
				session.delete(watsonResourceAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonResourceAudit != null) {
			clearCache(watsonResourceAudit);
		}

		return watsonResourceAudit;
	}

	@Override
	public WatsonResourceAudit updateImpl(
		WatsonResourceAudit watsonResourceAudit) {

		boolean isNew = watsonResourceAudit.isNew();

		if (!(watsonResourceAudit instanceof WatsonResourceAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonResourceAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonResourceAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonResourceAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonResourceAudit implementation " +
					watsonResourceAudit.getClass());
		}

		WatsonResourceAuditModelImpl watsonResourceAuditModelImpl =
			(WatsonResourceAuditModelImpl)watsonResourceAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonResourceAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonResourceAudit.setCreateDate(now);
			}
			else {
				watsonResourceAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonResourceAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonResourceAudit.setModifiedDate(now);
			}
			else {
				watsonResourceAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonResourceAudit);

				watsonResourceAudit.setNew(false);
			}
			else {
				watsonResourceAudit = (WatsonResourceAudit)session.merge(
					watsonResourceAudit);
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
			WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceAuditImpl.class, watsonResourceAudit.getPrimaryKey(),
			watsonResourceAudit, false);

		watsonResourceAudit.resetOriginalValues();

		return watsonResourceAudit;
	}

	/**
	 * Returns the watson resource audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson resource audit
	 * @return the watson resource audit
	 * @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	 */
	@Override
	public WatsonResourceAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResourceAuditException {

		WatsonResourceAudit watsonResourceAudit = fetchByPrimaryKey(primaryKey);

		if (watsonResourceAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResourceAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonResourceAudit;
	}

	/**
	 * Returns the watson resource audit with the primary key or throws a <code>NoSuchResourceAuditException</code> if it could not be found.
	 *
	 * @param watsonResourceAuditId the primary key of the watson resource audit
	 * @return the watson resource audit
	 * @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	 */
	@Override
	public WatsonResourceAudit findByPrimaryKey(long watsonResourceAuditId)
		throws NoSuchResourceAuditException {

		return findByPrimaryKey((Serializable)watsonResourceAuditId);
	}

	/**
	 * Returns the watson resource audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson resource audit
	 * @return the watson resource audit, or <code>null</code> if a watson resource audit with the primary key could not be found
	 */
	@Override
	public WatsonResourceAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonResourceAudit watsonResourceAudit =
			(WatsonResourceAudit)serializable;

		if (watsonResourceAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonResourceAudit = (WatsonResourceAudit)session.get(
					WatsonResourceAuditImpl.class, primaryKey);

				if (watsonResourceAudit != null) {
					cacheResult(watsonResourceAudit);
				}
				else {
					entityCache.putResult(
						WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonResourceAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonResourceAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonResourceAudit;
	}

	/**
	 * Returns the watson resource audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonResourceAuditId the primary key of the watson resource audit
	 * @return the watson resource audit, or <code>null</code> if a watson resource audit with the primary key could not be found
	 */
	@Override
	public WatsonResourceAudit fetchByPrimaryKey(long watsonResourceAuditId) {
		return fetchByPrimaryKey((Serializable)watsonResourceAuditId);
	}

	@Override
	public Map<Serializable, WatsonResourceAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonResourceAudit> map =
			new HashMap<Serializable, WatsonResourceAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonResourceAudit watsonResourceAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonResourceAudit != null) {
				map.put(primaryKey, watsonResourceAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonResourceAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonResourceAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONRESOURCEAUDIT_WHERE_PKS_IN);

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

			for (WatsonResourceAudit watsonResourceAudit :
					(List<WatsonResourceAudit>)query.list()) {

				map.put(
					watsonResourceAudit.getPrimaryKeyObj(),
					watsonResourceAudit);

				cacheResult(watsonResourceAudit);

				uncachedPrimaryKeys.remove(
					watsonResourceAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonResourceAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson resource audits.
	 *
	 * @return the watson resource audits
	 */
	@Override
	public List<WatsonResourceAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson resource audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonResourceAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson resource audits
	 * @param end the upper bound of the range of watson resource audits (not inclusive)
	 * @return the range of watson resource audits
	 */
	@Override
	public List<WatsonResourceAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson resource audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonResourceAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson resource audits
	 * @param end the upper bound of the range of watson resource audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson resource audits
	 */
	@Override
	public List<WatsonResourceAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonResourceAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson resource audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonResourceAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson resource audits
	 * @param end the upper bound of the range of watson resource audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson resource audits
	 */
	@Override
	public List<WatsonResourceAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonResourceAudit> orderByComparator,
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

		List<WatsonResourceAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonResourceAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONRESOURCEAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONRESOURCEAUDIT;

				sql = sql.concat(WatsonResourceAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonResourceAudit>)QueryUtil.list(
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
	 * Removes all the watson resource audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonResourceAudit watsonResourceAudit : findAll()) {
			remove(watsonResourceAudit);
		}
	}

	/**
	 * Returns the number of watson resource audits.
	 *
	 * @return the number of watson resource audits
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
					_SQL_COUNT_WATSONRESOURCEAUDIT);

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
		return WatsonResourceAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson resource audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonResourceAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonResourceAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonResourceAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonResourceAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONRESOURCEAUDIT =
		"SELECT watsonResourceAudit FROM WatsonResourceAudit watsonResourceAudit";

	private static final String _SQL_SELECT_WATSONRESOURCEAUDIT_WHERE_PKS_IN =
		"SELECT watsonResourceAudit FROM WatsonResourceAudit watsonResourceAudit WHERE watsonResourceAuditId IN (";

	private static final String _SQL_COUNT_WATSONRESOURCEAUDIT =
		"SELECT COUNT(watsonResourceAudit) FROM WatsonResourceAudit watsonResourceAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonResourceAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonResourceAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonResourceAuditPersistenceImpl.class);

}
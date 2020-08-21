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
import com.liferay.watson.exception.NoSuchVehicleAuditException;
import com.liferay.watson.model.WatsonVehicleAudit;
import com.liferay.watson.model.impl.WatsonVehicleAuditImpl;
import com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonVehicleAuditPersistence;

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
 * The persistence implementation for the watson vehicle audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonVehicleAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonVehicleAudit>
	implements WatsonVehicleAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonVehicleAuditUtil</code> to access the watson vehicle audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonVehicleAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonVehicleAuditPersistenceImpl() {
		setModelClass(WatsonVehicleAudit.class);
	}

	/**
	 * Caches the watson vehicle audit in the entity cache if it is enabled.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 */
	@Override
	public void cacheResult(WatsonVehicleAudit watsonVehicleAudit) {
		entityCache.putResult(
			WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleAuditImpl.class, watsonVehicleAudit.getPrimaryKey(),
			watsonVehicleAudit);

		watsonVehicleAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson vehicle audits in the entity cache if it is enabled.
	 *
	 * @param watsonVehicleAudits the watson vehicle audits
	 */
	@Override
	public void cacheResult(List<WatsonVehicleAudit> watsonVehicleAudits) {
		for (WatsonVehicleAudit watsonVehicleAudit : watsonVehicleAudits) {
			if (entityCache.getResult(
					WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonVehicleAuditImpl.class,
					watsonVehicleAudit.getPrimaryKey()) == null) {

				cacheResult(watsonVehicleAudit);
			}
			else {
				watsonVehicleAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson vehicle audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonVehicleAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson vehicle audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonVehicleAudit watsonVehicleAudit) {
		entityCache.removeResult(
			WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleAuditImpl.class, watsonVehicleAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonVehicleAudit> watsonVehicleAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonVehicleAudit watsonVehicleAudit : watsonVehicleAudits) {
			entityCache.removeResult(
				WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonVehicleAuditImpl.class,
				watsonVehicleAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonVehicleAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson vehicle audit with the primary key. Does not add the watson vehicle audit to the database.
	 *
	 * @param watsonVehicleAuditId the primary key for the new watson vehicle audit
	 * @return the new watson vehicle audit
	 */
	@Override
	public WatsonVehicleAudit create(long watsonVehicleAuditId) {
		WatsonVehicleAudit watsonVehicleAudit = new WatsonVehicleAuditImpl();

		watsonVehicleAudit.setNew(true);
		watsonVehicleAudit.setPrimaryKey(watsonVehicleAuditId);

		watsonVehicleAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonVehicleAudit;
	}

	/**
	 * Removes the watson vehicle audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit that was removed
	 * @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public WatsonVehicleAudit remove(long watsonVehicleAuditId)
		throws NoSuchVehicleAuditException {

		return remove((Serializable)watsonVehicleAuditId);
	}

	/**
	 * Removes the watson vehicle audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson vehicle audit
	 * @return the watson vehicle audit that was removed
	 * @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public WatsonVehicleAudit remove(Serializable primaryKey)
		throws NoSuchVehicleAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonVehicleAudit watsonVehicleAudit =
				(WatsonVehicleAudit)session.get(
					WatsonVehicleAuditImpl.class, primaryKey);

			if (watsonVehicleAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVehicleAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonVehicleAudit);
		}
		catch (NoSuchVehicleAuditException noSuchEntityException) {
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
	protected WatsonVehicleAudit removeImpl(
		WatsonVehicleAudit watsonVehicleAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonVehicleAudit)) {
				watsonVehicleAudit = (WatsonVehicleAudit)session.get(
					WatsonVehicleAuditImpl.class,
					watsonVehicleAudit.getPrimaryKeyObj());
			}

			if (watsonVehicleAudit != null) {
				session.delete(watsonVehicleAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonVehicleAudit != null) {
			clearCache(watsonVehicleAudit);
		}

		return watsonVehicleAudit;
	}

	@Override
	public WatsonVehicleAudit updateImpl(
		WatsonVehicleAudit watsonVehicleAudit) {

		boolean isNew = watsonVehicleAudit.isNew();

		if (!(watsonVehicleAudit instanceof WatsonVehicleAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonVehicleAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonVehicleAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonVehicleAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonVehicleAudit implementation " +
					watsonVehicleAudit.getClass());
		}

		WatsonVehicleAuditModelImpl watsonVehicleAuditModelImpl =
			(WatsonVehicleAuditModelImpl)watsonVehicleAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonVehicleAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonVehicleAudit.setCreateDate(now);
			}
			else {
				watsonVehicleAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonVehicleAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonVehicleAudit.setModifiedDate(now);
			}
			else {
				watsonVehicleAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonVehicleAudit);

				watsonVehicleAudit.setNew(false);
			}
			else {
				watsonVehicleAudit = (WatsonVehicleAudit)session.merge(
					watsonVehicleAudit);
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
			WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleAuditImpl.class, watsonVehicleAudit.getPrimaryKey(),
			watsonVehicleAudit, false);

		watsonVehicleAudit.resetOriginalValues();

		return watsonVehicleAudit;
	}

	/**
	 * Returns the watson vehicle audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson vehicle audit
	 * @return the watson vehicle audit
	 * @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public WatsonVehicleAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVehicleAuditException {

		WatsonVehicleAudit watsonVehicleAudit = fetchByPrimaryKey(primaryKey);

		if (watsonVehicleAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVehicleAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonVehicleAudit;
	}

	/**
	 * Returns the watson vehicle audit with the primary key or throws a <code>NoSuchVehicleAuditException</code> if it could not be found.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit
	 * @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public WatsonVehicleAudit findByPrimaryKey(long watsonVehicleAuditId)
		throws NoSuchVehicleAuditException {

		return findByPrimaryKey((Serializable)watsonVehicleAuditId);
	}

	/**
	 * Returns the watson vehicle audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson vehicle audit
	 * @return the watson vehicle audit, or <code>null</code> if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public WatsonVehicleAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonVehicleAudit watsonVehicleAudit =
			(WatsonVehicleAudit)serializable;

		if (watsonVehicleAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonVehicleAudit = (WatsonVehicleAudit)session.get(
					WatsonVehicleAuditImpl.class, primaryKey);

				if (watsonVehicleAudit != null) {
					cacheResult(watsonVehicleAudit);
				}
				else {
					entityCache.putResult(
						WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonVehicleAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonVehicleAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonVehicleAudit;
	}

	/**
	 * Returns the watson vehicle audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit, or <code>null</code> if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public WatsonVehicleAudit fetchByPrimaryKey(long watsonVehicleAuditId) {
		return fetchByPrimaryKey((Serializable)watsonVehicleAuditId);
	}

	@Override
	public Map<Serializable, WatsonVehicleAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonVehicleAudit> map =
			new HashMap<Serializable, WatsonVehicleAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonVehicleAudit watsonVehicleAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonVehicleAudit != null) {
				map.put(primaryKey, watsonVehicleAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonVehicleAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonVehicleAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONVEHICLEAUDIT_WHERE_PKS_IN);

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

			for (WatsonVehicleAudit watsonVehicleAudit :
					(List<WatsonVehicleAudit>)query.list()) {

				map.put(
					watsonVehicleAudit.getPrimaryKeyObj(), watsonVehicleAudit);

				cacheResult(watsonVehicleAudit);

				uncachedPrimaryKeys.remove(
					watsonVehicleAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonVehicleAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson vehicle audits.
	 *
	 * @return the watson vehicle audits
	 */
	@Override
	public List<WatsonVehicleAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @return the range of watson vehicle audits
	 */
	@Override
	public List<WatsonVehicleAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson vehicle audits
	 */
	@Override
	public List<WatsonVehicleAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonVehicleAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson vehicle audits
	 */
	@Override
	public List<WatsonVehicleAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonVehicleAudit> orderByComparator,
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

		List<WatsonVehicleAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonVehicleAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONVEHICLEAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONVEHICLEAUDIT;

				sql = sql.concat(WatsonVehicleAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonVehicleAudit>)QueryUtil.list(
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
	 * Removes all the watson vehicle audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonVehicleAudit watsonVehicleAudit : findAll()) {
			remove(watsonVehicleAudit);
		}
	}

	/**
	 * Returns the number of watson vehicle audits.
	 *
	 * @return the number of watson vehicle audits
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
					_SQL_COUNT_WATSONVEHICLEAUDIT);

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
		return WatsonVehicleAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson vehicle audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonVehicleAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonVehicleAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonVehicleAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonVehicleAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONVEHICLEAUDIT =
		"SELECT watsonVehicleAudit FROM WatsonVehicleAudit watsonVehicleAudit";

	private static final String _SQL_SELECT_WATSONVEHICLEAUDIT_WHERE_PKS_IN =
		"SELECT watsonVehicleAudit FROM WatsonVehicleAudit watsonVehicleAudit WHERE watsonVehicleAuditId IN (";

	private static final String _SQL_COUNT_WATSONVEHICLEAUDIT =
		"SELECT COUNT(watsonVehicleAudit) FROM WatsonVehicleAudit watsonVehicleAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonVehicleAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonVehicleAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonVehicleAuditPersistenceImpl.class);

}
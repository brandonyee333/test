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
import com.liferay.watson.exception.NoSuchIncidentAuditException;
import com.liferay.watson.model.WatsonIncidentAudit;
import com.liferay.watson.model.impl.WatsonIncidentAuditImpl;
import com.liferay.watson.model.impl.WatsonIncidentAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonIncidentAuditPersistence;

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
 * The persistence implementation for the watson incident audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonIncidentAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonIncidentAudit>
	implements WatsonIncidentAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonIncidentAuditUtil</code> to access the watson incident audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonIncidentAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonIncidentAuditPersistenceImpl() {
		setModelClass(WatsonIncidentAudit.class);
	}

	/**
	 * Caches the watson incident audit in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentAudit the watson incident audit
	 */
	@Override
	public void cacheResult(WatsonIncidentAudit watsonIncidentAudit) {
		entityCache.putResult(
			WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentAuditImpl.class, watsonIncidentAudit.getPrimaryKey(),
			watsonIncidentAudit);

		watsonIncidentAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson incident audits in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentAudits the watson incident audits
	 */
	@Override
	public void cacheResult(List<WatsonIncidentAudit> watsonIncidentAudits) {
		for (WatsonIncidentAudit watsonIncidentAudit : watsonIncidentAudits) {
			if (entityCache.getResult(
					WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentAuditImpl.class,
					watsonIncidentAudit.getPrimaryKey()) == null) {

				cacheResult(watsonIncidentAudit);
			}
			else {
				watsonIncidentAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson incident audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonIncidentAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson incident audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonIncidentAudit watsonIncidentAudit) {
		entityCache.removeResult(
			WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentAuditImpl.class, watsonIncidentAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonIncidentAudit> watsonIncidentAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonIncidentAudit watsonIncidentAudit : watsonIncidentAudits) {
			entityCache.removeResult(
				WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentAuditImpl.class,
				watsonIncidentAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson incident audit with the primary key. Does not add the watson incident audit to the database.
	 *
	 * @param watsonIncidentAuditId the primary key for the new watson incident audit
	 * @return the new watson incident audit
	 */
	@Override
	public WatsonIncidentAudit create(long watsonIncidentAuditId) {
		WatsonIncidentAudit watsonIncidentAudit = new WatsonIncidentAuditImpl();

		watsonIncidentAudit.setNew(true);
		watsonIncidentAudit.setPrimaryKey(watsonIncidentAuditId);

		watsonIncidentAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonIncidentAudit;
	}

	/**
	 * Removes the watson incident audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentAuditId the primary key of the watson incident audit
	 * @return the watson incident audit that was removed
	 * @throws NoSuchIncidentAuditException if a watson incident audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentAudit remove(long watsonIncidentAuditId)
		throws NoSuchIncidentAuditException {

		return remove((Serializable)watsonIncidentAuditId);
	}

	/**
	 * Removes the watson incident audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson incident audit
	 * @return the watson incident audit that was removed
	 * @throws NoSuchIncidentAuditException if a watson incident audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentAudit remove(Serializable primaryKey)
		throws NoSuchIncidentAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonIncidentAudit watsonIncidentAudit =
				(WatsonIncidentAudit)session.get(
					WatsonIncidentAuditImpl.class, primaryKey);

			if (watsonIncidentAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIncidentAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonIncidentAudit);
		}
		catch (NoSuchIncidentAuditException noSuchEntityException) {
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
	protected WatsonIncidentAudit removeImpl(
		WatsonIncidentAudit watsonIncidentAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonIncidentAudit)) {
				watsonIncidentAudit = (WatsonIncidentAudit)session.get(
					WatsonIncidentAuditImpl.class,
					watsonIncidentAudit.getPrimaryKeyObj());
			}

			if (watsonIncidentAudit != null) {
				session.delete(watsonIncidentAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonIncidentAudit != null) {
			clearCache(watsonIncidentAudit);
		}

		return watsonIncidentAudit;
	}

	@Override
	public WatsonIncidentAudit updateImpl(
		WatsonIncidentAudit watsonIncidentAudit) {

		boolean isNew = watsonIncidentAudit.isNew();

		if (!(watsonIncidentAudit instanceof WatsonIncidentAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonIncidentAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonIncidentAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonIncidentAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonIncidentAudit implementation " +
					watsonIncidentAudit.getClass());
		}

		WatsonIncidentAuditModelImpl watsonIncidentAuditModelImpl =
			(WatsonIncidentAuditModelImpl)watsonIncidentAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonIncidentAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonIncidentAudit.setCreateDate(now);
			}
			else {
				watsonIncidentAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonIncidentAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonIncidentAudit.setModifiedDate(now);
			}
			else {
				watsonIncidentAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonIncidentAudit);

				watsonIncidentAudit.setNew(false);
			}
			else {
				watsonIncidentAudit = (WatsonIncidentAudit)session.merge(
					watsonIncidentAudit);
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
			WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentAuditImpl.class, watsonIncidentAudit.getPrimaryKey(),
			watsonIncidentAudit, false);

		watsonIncidentAudit.resetOriginalValues();

		return watsonIncidentAudit;
	}

	/**
	 * Returns the watson incident audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident audit
	 * @return the watson incident audit
	 * @throws NoSuchIncidentAuditException if a watson incident audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIncidentAuditException {

		WatsonIncidentAudit watsonIncidentAudit = fetchByPrimaryKey(primaryKey);

		if (watsonIncidentAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIncidentAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonIncidentAudit;
	}

	/**
	 * Returns the watson incident audit with the primary key or throws a <code>NoSuchIncidentAuditException</code> if it could not be found.
	 *
	 * @param watsonIncidentAuditId the primary key of the watson incident audit
	 * @return the watson incident audit
	 * @throws NoSuchIncidentAuditException if a watson incident audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentAudit findByPrimaryKey(long watsonIncidentAuditId)
		throws NoSuchIncidentAuditException {

		return findByPrimaryKey((Serializable)watsonIncidentAuditId);
	}

	/**
	 * Returns the watson incident audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident audit
	 * @return the watson incident audit, or <code>null</code> if a watson incident audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonIncidentAudit watsonIncidentAudit =
			(WatsonIncidentAudit)serializable;

		if (watsonIncidentAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonIncidentAudit = (WatsonIncidentAudit)session.get(
					WatsonIncidentAuditImpl.class, primaryKey);

				if (watsonIncidentAudit != null) {
					cacheResult(watsonIncidentAudit);
				}
				else {
					entityCache.putResult(
						WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonIncidentAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonIncidentAudit;
	}

	/**
	 * Returns the watson incident audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonIncidentAuditId the primary key of the watson incident audit
	 * @return the watson incident audit, or <code>null</code> if a watson incident audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentAudit fetchByPrimaryKey(long watsonIncidentAuditId) {
		return fetchByPrimaryKey((Serializable)watsonIncidentAuditId);
	}

	@Override
	public Map<Serializable, WatsonIncidentAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonIncidentAudit> map =
			new HashMap<Serializable, WatsonIncidentAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonIncidentAudit watsonIncidentAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonIncidentAudit != null) {
				map.put(primaryKey, watsonIncidentAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonIncidentAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONINCIDENTAUDIT_WHERE_PKS_IN);

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

			for (WatsonIncidentAudit watsonIncidentAudit :
					(List<WatsonIncidentAudit>)query.list()) {

				map.put(
					watsonIncidentAudit.getPrimaryKeyObj(),
					watsonIncidentAudit);

				cacheResult(watsonIncidentAudit);

				uncachedPrimaryKeys.remove(
					watsonIncidentAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson incident audits.
	 *
	 * @return the watson incident audits
	 */
	@Override
	public List<WatsonIncidentAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson incident audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident audits
	 * @param end the upper bound of the range of watson incident audits (not inclusive)
	 * @return the range of watson incident audits
	 */
	@Override
	public List<WatsonIncidentAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson incident audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident audits
	 * @param end the upper bound of the range of watson incident audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson incident audits
	 */
	@Override
	public List<WatsonIncidentAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson incident audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident audits
	 * @param end the upper bound of the range of watson incident audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson incident audits
	 */
	@Override
	public List<WatsonIncidentAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentAudit> orderByComparator,
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

		List<WatsonIncidentAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonIncidentAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONINCIDENTAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONINCIDENTAUDIT;

				sql = sql.concat(WatsonIncidentAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonIncidentAudit>)QueryUtil.list(
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
	 * Removes all the watson incident audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonIncidentAudit watsonIncidentAudit : findAll()) {
			remove(watsonIncidentAudit);
		}
	}

	/**
	 * Returns the number of watson incident audits.
	 *
	 * @return the number of watson incident audits
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
					_SQL_COUNT_WATSONINCIDENTAUDIT);

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
		return WatsonIncidentAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson incident audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonIncidentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonIncidentAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONINCIDENTAUDIT =
		"SELECT watsonIncidentAudit FROM WatsonIncidentAudit watsonIncidentAudit";

	private static final String _SQL_SELECT_WATSONINCIDENTAUDIT_WHERE_PKS_IN =
		"SELECT watsonIncidentAudit FROM WatsonIncidentAudit watsonIncidentAudit WHERE watsonIncidentAuditId IN (";

	private static final String _SQL_COUNT_WATSONINCIDENTAUDIT =
		"SELECT COUNT(watsonIncidentAudit) FROM WatsonIncidentAudit watsonIncidentAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonIncidentAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonIncidentAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonIncidentAuditPersistenceImpl.class);

}
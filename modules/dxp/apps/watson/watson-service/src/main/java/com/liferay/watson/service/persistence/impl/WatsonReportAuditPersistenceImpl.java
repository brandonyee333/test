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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.watson.exception.NoSuchReportAuditException;
import com.liferay.watson.model.WatsonReportAudit;
import com.liferay.watson.model.impl.WatsonReportAuditImpl;
import com.liferay.watson.model.impl.WatsonReportAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonReportAuditPersistence;

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
 * The persistence implementation for the watson report audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonReportAuditPersistence
 * @see com.liferay.watson.service.persistence.WatsonReportAuditUtil
 * @generated
 */
@ProviderType
public class WatsonReportAuditPersistenceImpl extends BasePersistenceImpl<WatsonReportAudit>
	implements WatsonReportAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonReportAuditUtil} to access the watson report audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonReportAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonReportAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonReportAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonReportAuditPersistenceImpl() {
		setModelClass(WatsonReportAudit.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("key", "key_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the watson report audit in the entity cache if it is enabled.
	 *
	 * @param watsonReportAudit the watson report audit
	 */
	@Override
	public void cacheResult(WatsonReportAudit watsonReportAudit) {
		entityCache.putResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportAuditImpl.class, watsonReportAudit.getPrimaryKey(),
			watsonReportAudit);

		watsonReportAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson report audits in the entity cache if it is enabled.
	 *
	 * @param watsonReportAudits the watson report audits
	 */
	@Override
	public void cacheResult(List<WatsonReportAudit> watsonReportAudits) {
		for (WatsonReportAudit watsonReportAudit : watsonReportAudits) {
			if (entityCache.getResult(
						WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonReportAuditImpl.class,
						watsonReportAudit.getPrimaryKey()) == null) {
				cacheResult(watsonReportAudit);
			}
			else {
				watsonReportAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson report audits.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonReportAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson report audit.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonReportAudit watsonReportAudit) {
		entityCache.removeResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportAuditImpl.class, watsonReportAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonReportAudit> watsonReportAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonReportAudit watsonReportAudit : watsonReportAudits) {
			entityCache.removeResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonReportAuditImpl.class, watsonReportAudit.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson report audit with the primary key. Does not add the watson report audit to the database.
	 *
	 * @param watsonReportAuditId the primary key for the new watson report audit
	 * @return the new watson report audit
	 */
	@Override
	public WatsonReportAudit create(long watsonReportAuditId) {
		WatsonReportAudit watsonReportAudit = new WatsonReportAuditImpl();

		watsonReportAudit.setNew(true);
		watsonReportAudit.setPrimaryKey(watsonReportAuditId);

		watsonReportAudit.setCompanyId(companyProvider.getCompanyId());

		return watsonReportAudit;
	}

	/**
	 * Removes the watson report audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit that was removed
	 * @throws NoSuchReportAuditException if a watson report audit with the primary key could not be found
	 */
	@Override
	public WatsonReportAudit remove(long watsonReportAuditId)
		throws NoSuchReportAuditException {
		return remove((Serializable)watsonReportAuditId);
	}

	/**
	 * Removes the watson report audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson report audit
	 * @return the watson report audit that was removed
	 * @throws NoSuchReportAuditException if a watson report audit with the primary key could not be found
	 */
	@Override
	public WatsonReportAudit remove(Serializable primaryKey)
		throws NoSuchReportAuditException {
		Session session = null;

		try {
			session = openSession();

			WatsonReportAudit watsonReportAudit = (WatsonReportAudit)session.get(WatsonReportAuditImpl.class,
					primaryKey);

			if (watsonReportAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReportAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonReportAudit);
		}
		catch (NoSuchReportAuditException nsee) {
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
	protected WatsonReportAudit removeImpl(WatsonReportAudit watsonReportAudit) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonReportAudit)) {
				watsonReportAudit = (WatsonReportAudit)session.get(WatsonReportAuditImpl.class,
						watsonReportAudit.getPrimaryKeyObj());
			}

			if (watsonReportAudit != null) {
				session.delete(watsonReportAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonReportAudit != null) {
			clearCache(watsonReportAudit);
		}

		return watsonReportAudit;
	}

	@Override
	public WatsonReportAudit updateImpl(WatsonReportAudit watsonReportAudit) {
		boolean isNew = watsonReportAudit.isNew();

		if (!(watsonReportAudit instanceof WatsonReportAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonReportAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(watsonReportAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonReportAudit proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonReportAudit implementation " +
				watsonReportAudit.getClass());
		}

		WatsonReportAuditModelImpl watsonReportAuditModelImpl = (WatsonReportAuditModelImpl)watsonReportAudit;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonReportAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonReportAudit.setCreateDate(now);
			}
			else {
				watsonReportAudit.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!watsonReportAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonReportAudit.setModifiedDate(now);
			}
			else {
				watsonReportAudit.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonReportAudit.isNew()) {
				session.save(watsonReportAudit);

				watsonReportAudit.setNew(false);
			}
			else {
				watsonReportAudit = (WatsonReportAudit)session.merge(watsonReportAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonReportAuditImpl.class, watsonReportAudit.getPrimaryKey(),
			watsonReportAudit, false);

		watsonReportAudit.resetOriginalValues();

		return watsonReportAudit;
	}

	/**
	 * Returns the watson report audit with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson report audit
	 * @return the watson report audit
	 * @throws NoSuchReportAuditException if a watson report audit with the primary key could not be found
	 */
	@Override
	public WatsonReportAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReportAuditException {
		WatsonReportAudit watsonReportAudit = fetchByPrimaryKey(primaryKey);

		if (watsonReportAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReportAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonReportAudit;
	}

	/**
	 * Returns the watson report audit with the primary key or throws a {@link NoSuchReportAuditException} if it could not be found.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit
	 * @throws NoSuchReportAuditException if a watson report audit with the primary key could not be found
	 */
	@Override
	public WatsonReportAudit findByPrimaryKey(long watsonReportAuditId)
		throws NoSuchReportAuditException {
		return findByPrimaryKey((Serializable)watsonReportAuditId);
	}

	/**
	 * Returns the watson report audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson report audit
	 * @return the watson report audit, or <code>null</code> if a watson report audit with the primary key could not be found
	 */
	@Override
	public WatsonReportAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonReportAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonReportAudit watsonReportAudit = (WatsonReportAudit)serializable;

		if (watsonReportAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonReportAudit = (WatsonReportAudit)session.get(WatsonReportAuditImpl.class,
						primaryKey);

				if (watsonReportAudit != null) {
					cacheResult(watsonReportAudit);
				}
				else {
					entityCache.putResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonReportAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonReportAuditImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonReportAudit;
	}

	/**
	 * Returns the watson report audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit, or <code>null</code> if a watson report audit with the primary key could not be found
	 */
	@Override
	public WatsonReportAudit fetchByPrimaryKey(long watsonReportAuditId) {
		return fetchByPrimaryKey((Serializable)watsonReportAuditId);
	}

	@Override
	public Map<Serializable, WatsonReportAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonReportAudit> map = new HashMap<Serializable, WatsonReportAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonReportAudit watsonReportAudit = fetchByPrimaryKey(primaryKey);

			if (watsonReportAudit != null) {
				map.put(primaryKey, watsonReportAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonReportAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonReportAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONREPORTAUDIT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (WatsonReportAudit watsonReportAudit : (List<WatsonReportAudit>)q.list()) {
				map.put(watsonReportAudit.getPrimaryKeyObj(), watsonReportAudit);

				cacheResult(watsonReportAudit);

				uncachedPrimaryKeys.remove(watsonReportAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonReportAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonReportAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson report audits.
	 *
	 * @return the watson report audits
	 */
	@Override
	public List<WatsonReportAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson report audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson report audits
	 * @param end the upper bound of the range of watson report audits (not inclusive)
	 * @return the range of watson report audits
	 */
	@Override
	public List<WatsonReportAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson report audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson report audits
	 * @param end the upper bound of the range of watson report audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson report audits
	 */
	@Override
	public List<WatsonReportAudit> findAll(int start, int end,
		OrderByComparator<WatsonReportAudit> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson report audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson report audits
	 * @param end the upper bound of the range of watson report audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson report audits
	 */
	@Override
	public List<WatsonReportAudit> findAll(int start, int end,
		OrderByComparator<WatsonReportAudit> orderByComparator,
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

		List<WatsonReportAudit> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonReportAudit>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONREPORTAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONREPORTAUDIT;

				if (pagination) {
					sql = sql.concat(WatsonReportAuditModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonReportAudit>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonReportAudit>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the watson report audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonReportAudit watsonReportAudit : findAll()) {
			remove(watsonReportAudit);
		}
	}

	/**
	 * Returns the number of watson report audits.
	 *
	 * @return the number of watson report audits
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONREPORTAUDIT);

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
		return WatsonReportAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson report audit persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonReportAuditImpl.class.getName());
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
	private static final String _SQL_SELECT_WATSONREPORTAUDIT = "SELECT watsonReportAudit FROM WatsonReportAudit watsonReportAudit";
	private static final String _SQL_SELECT_WATSONREPORTAUDIT_WHERE_PKS_IN = "SELECT watsonReportAudit FROM WatsonReportAudit watsonReportAudit WHERE watsonReportAuditId IN (";
	private static final String _SQL_COUNT_WATSONREPORTAUDIT = "SELECT COUNT(watsonReportAudit) FROM WatsonReportAudit watsonReportAudit";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonReportAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonReportAudit exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonReportAuditPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key"
			});
}
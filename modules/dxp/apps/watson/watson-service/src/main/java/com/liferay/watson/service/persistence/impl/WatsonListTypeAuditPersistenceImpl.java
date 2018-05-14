/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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

import com.liferay.watson.exception.NoSuchListTypeAuditException;
import com.liferay.watson.model.WatsonListTypeAudit;
import com.liferay.watson.model.impl.WatsonListTypeAuditImpl;
import com.liferay.watson.model.impl.WatsonListTypeAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonListTypeAuditPersistence;

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
 * The persistence implementation for the watson list type audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeAuditPersistence
 * @see com.liferay.watson.service.persistence.WatsonListTypeAuditUtil
 * @generated
 */
@ProviderType
public class WatsonListTypeAuditPersistenceImpl extends BasePersistenceImpl<WatsonListTypeAudit>
	implements WatsonListTypeAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonListTypeAuditUtil} to access the watson list type audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonListTypeAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonListTypeAuditPersistenceImpl() {
		setModelClass(WatsonListTypeAudit.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the watson list type audit in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeAudit the watson list type audit
	 */
	@Override
	public void cacheResult(WatsonListTypeAudit watsonListTypeAudit) {
		entityCache.putResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class, watsonListTypeAudit.getPrimaryKey(),
			watsonListTypeAudit);

		watsonListTypeAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson list type audits in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeAudits the watson list type audits
	 */
	@Override
	public void cacheResult(List<WatsonListTypeAudit> watsonListTypeAudits) {
		for (WatsonListTypeAudit watsonListTypeAudit : watsonListTypeAudits) {
			if (entityCache.getResult(
						WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeAuditImpl.class,
						watsonListTypeAudit.getPrimaryKey()) == null) {
				cacheResult(watsonListTypeAudit);
			}
			else {
				watsonListTypeAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson list type audits.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonListTypeAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson list type audit.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonListTypeAudit watsonListTypeAudit) {
		entityCache.removeResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class, watsonListTypeAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonListTypeAudit> watsonListTypeAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonListTypeAudit watsonListTypeAudit : watsonListTypeAudits) {
			entityCache.removeResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeAuditImpl.class,
				watsonListTypeAudit.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson list type audit with the primary key. Does not add the watson list type audit to the database.
	 *
	 * @param watsonListTypeAuditId the primary key for the new watson list type audit
	 * @return the new watson list type audit
	 */
	@Override
	public WatsonListTypeAudit create(long watsonListTypeAuditId) {
		WatsonListTypeAudit watsonListTypeAudit = new WatsonListTypeAuditImpl();

		watsonListTypeAudit.setNew(true);
		watsonListTypeAudit.setPrimaryKey(watsonListTypeAuditId);

		watsonListTypeAudit.setCompanyId(companyProvider.getCompanyId());

		return watsonListTypeAudit;
	}

	/**
	 * Removes the watson list type audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit that was removed
	 * @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeAudit remove(long watsonListTypeAuditId)
		throws NoSuchListTypeAuditException {
		return remove((Serializable)watsonListTypeAuditId);
	}

	/**
	 * Removes the watson list type audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson list type audit
	 * @return the watson list type audit that was removed
	 * @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeAudit remove(Serializable primaryKey)
		throws NoSuchListTypeAuditException {
		Session session = null;

		try {
			session = openSession();

			WatsonListTypeAudit watsonListTypeAudit = (WatsonListTypeAudit)session.get(WatsonListTypeAuditImpl.class,
					primaryKey);

			if (watsonListTypeAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchListTypeAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonListTypeAudit);
		}
		catch (NoSuchListTypeAuditException nsee) {
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
	protected WatsonListTypeAudit removeImpl(
		WatsonListTypeAudit watsonListTypeAudit) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonListTypeAudit)) {
				watsonListTypeAudit = (WatsonListTypeAudit)session.get(WatsonListTypeAuditImpl.class,
						watsonListTypeAudit.getPrimaryKeyObj());
			}

			if (watsonListTypeAudit != null) {
				session.delete(watsonListTypeAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonListTypeAudit != null) {
			clearCache(watsonListTypeAudit);
		}

		return watsonListTypeAudit;
	}

	@Override
	public WatsonListTypeAudit updateImpl(
		WatsonListTypeAudit watsonListTypeAudit) {
		boolean isNew = watsonListTypeAudit.isNew();

		if (!(watsonListTypeAudit instanceof WatsonListTypeAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonListTypeAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(watsonListTypeAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonListTypeAudit proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonListTypeAudit implementation " +
				watsonListTypeAudit.getClass());
		}

		WatsonListTypeAuditModelImpl watsonListTypeAuditModelImpl = (WatsonListTypeAuditModelImpl)watsonListTypeAudit;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonListTypeAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonListTypeAudit.setCreateDate(now);
			}
			else {
				watsonListTypeAudit.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!watsonListTypeAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonListTypeAudit.setModifiedDate(now);
			}
			else {
				watsonListTypeAudit.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonListTypeAudit.isNew()) {
				session.save(watsonListTypeAudit);

				watsonListTypeAudit.setNew(false);
			}
			else {
				watsonListTypeAudit = (WatsonListTypeAudit)session.merge(watsonListTypeAudit);
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

		entityCache.putResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class, watsonListTypeAudit.getPrimaryKey(),
			watsonListTypeAudit, false);

		watsonListTypeAudit.resetOriginalValues();

		return watsonListTypeAudit;
	}

	/**
	 * Returns the watson list type audit with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type audit
	 * @return the watson list type audit
	 * @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchListTypeAuditException {
		WatsonListTypeAudit watsonListTypeAudit = fetchByPrimaryKey(primaryKey);

		if (watsonListTypeAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchListTypeAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonListTypeAudit;
	}

	/**
	 * Returns the watson list type audit with the primary key or throws a {@link NoSuchListTypeAuditException} if it could not be found.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit
	 * @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeAudit findByPrimaryKey(long watsonListTypeAuditId)
		throws NoSuchListTypeAuditException {
		return findByPrimaryKey((Serializable)watsonListTypeAuditId);
	}

	/**
	 * Returns the watson list type audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type audit
	 * @return the watson list type audit, or <code>null</code> if a watson list type audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonListTypeAudit watsonListTypeAudit = (WatsonListTypeAudit)serializable;

		if (watsonListTypeAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonListTypeAudit = (WatsonListTypeAudit)session.get(WatsonListTypeAuditImpl.class,
						primaryKey);

				if (watsonListTypeAudit != null) {
					cacheResult(watsonListTypeAudit);
				}
				else {
					entityCache.putResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeAuditImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonListTypeAudit;
	}

	/**
	 * Returns the watson list type audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit, or <code>null</code> if a watson list type audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeAudit fetchByPrimaryKey(long watsonListTypeAuditId) {
		return fetchByPrimaryKey((Serializable)watsonListTypeAuditId);
	}

	@Override
	public Map<Serializable, WatsonListTypeAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonListTypeAudit> map = new HashMap<Serializable, WatsonListTypeAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonListTypeAudit watsonListTypeAudit = fetchByPrimaryKey(primaryKey);

			if (watsonListTypeAudit != null) {
				map.put(primaryKey, watsonListTypeAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonListTypeAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONLISTTYPEAUDIT_WHERE_PKS_IN);

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

			for (WatsonListTypeAudit watsonListTypeAudit : (List<WatsonListTypeAudit>)q.list()) {
				map.put(watsonListTypeAudit.getPrimaryKeyObj(),
					watsonListTypeAudit);

				cacheResult(watsonListTypeAudit);

				uncachedPrimaryKeys.remove(watsonListTypeAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson list type audits.
	 *
	 * @return the watson list type audits
	 */
	@Override
	public List<WatsonListTypeAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @return the range of watson list type audits
	 */
	@Override
	public List<WatsonListTypeAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list type audits
	 */
	@Override
	public List<WatsonListTypeAudit> findAll(int start, int end,
		OrderByComparator<WatsonListTypeAudit> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson list type audits
	 */
	@Override
	public List<WatsonListTypeAudit> findAll(int start, int end,
		OrderByComparator<WatsonListTypeAudit> orderByComparator,
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

		List<WatsonListTypeAudit> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonListTypeAudit>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONLISTTYPEAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONLISTTYPEAUDIT;

				if (pagination) {
					sql = sql.concat(WatsonListTypeAuditModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonListTypeAudit>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonListTypeAudit>)QueryUtil.list(q,
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
	 * Removes all the watson list type audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonListTypeAudit watsonListTypeAudit : findAll()) {
			remove(watsonListTypeAudit);
		}
	}

	/**
	 * Returns the number of watson list type audits.
	 *
	 * @return the number of watson list type audits
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONLISTTYPEAUDIT);

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
		return WatsonListTypeAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson list type audit persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonListTypeAuditImpl.class.getName());
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
	private static final String _SQL_SELECT_WATSONLISTTYPEAUDIT = "SELECT watsonListTypeAudit FROM WatsonListTypeAudit watsonListTypeAudit";
	private static final String _SQL_SELECT_WATSONLISTTYPEAUDIT_WHERE_PKS_IN = "SELECT watsonListTypeAudit FROM WatsonListTypeAudit watsonListTypeAudit WHERE watsonListTypeAuditId IN (";
	private static final String _SQL_COUNT_WATSONLISTTYPEAUDIT = "SELECT COUNT(watsonListTypeAudit) FROM WatsonListTypeAudit watsonListTypeAudit";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonListTypeAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonListTypeAudit exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonListTypeAuditPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
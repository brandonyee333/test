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
import com.liferay.watson.exception.NoSuchListTypeRelAuditException;
import com.liferay.watson.model.WatsonListTypeRelAudit;
import com.liferay.watson.model.impl.WatsonListTypeRelAuditImpl;
import com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonListTypeRelAuditPersistence;

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
 * The persistence implementation for the watson list type rel audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonListTypeRelAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonListTypeRelAudit>
	implements WatsonListTypeRelAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonListTypeRelAuditUtil</code> to access the watson list type rel audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonListTypeRelAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonListTypeRelAuditPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("primary", "primary_");
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

		setModelClass(WatsonListTypeRelAudit.class);
	}

	/**
	 * Caches the watson list type rel audit in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 */
	@Override
	public void cacheResult(WatsonListTypeRelAudit watsonListTypeRelAudit) {
		entityCache.putResult(
			WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelAuditImpl.class,
			watsonListTypeRelAudit.getPrimaryKey(), watsonListTypeRelAudit);

		watsonListTypeRelAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson list type rel audits in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRelAudits the watson list type rel audits
	 */
	@Override
	public void cacheResult(
		List<WatsonListTypeRelAudit> watsonListTypeRelAudits) {

		for (WatsonListTypeRelAudit watsonListTypeRelAudit :
				watsonListTypeRelAudits) {

			if (entityCache.getResult(
					WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelAuditImpl.class,
					watsonListTypeRelAudit.getPrimaryKey()) == null) {

				cacheResult(watsonListTypeRelAudit);
			}
			else {
				watsonListTypeRelAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson list type rel audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonListTypeRelAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson list type rel audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonListTypeRelAudit watsonListTypeRelAudit) {
		entityCache.removeResult(
			WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelAuditImpl.class,
			watsonListTypeRelAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<WatsonListTypeRelAudit> watsonListTypeRelAudits) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonListTypeRelAudit watsonListTypeRelAudit :
				watsonListTypeRelAudits) {

			entityCache.removeResult(
				WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelAuditImpl.class,
				watsonListTypeRelAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson list type rel audit with the primary key. Does not add the watson list type rel audit to the database.
	 *
	 * @param watsonListTypeRelAuditId the primary key for the new watson list type rel audit
	 * @return the new watson list type rel audit
	 */
	@Override
	public WatsonListTypeRelAudit create(long watsonListTypeRelAuditId) {
		WatsonListTypeRelAudit watsonListTypeRelAudit =
			new WatsonListTypeRelAuditImpl();

		watsonListTypeRelAudit.setNew(true);
		watsonListTypeRelAudit.setPrimaryKey(watsonListTypeRelAuditId);

		watsonListTypeRelAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonListTypeRelAudit;
	}

	/**
	 * Removes the watson list type rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit that was removed
	 * @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRelAudit remove(long watsonListTypeRelAuditId)
		throws NoSuchListTypeRelAuditException {

		return remove((Serializable)watsonListTypeRelAuditId);
	}

	/**
	 * Removes the watson list type rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson list type rel audit
	 * @return the watson list type rel audit that was removed
	 * @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRelAudit remove(Serializable primaryKey)
		throws NoSuchListTypeRelAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonListTypeRelAudit watsonListTypeRelAudit =
				(WatsonListTypeRelAudit)session.get(
					WatsonListTypeRelAuditImpl.class, primaryKey);

			if (watsonListTypeRelAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchListTypeRelAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonListTypeRelAudit);
		}
		catch (NoSuchListTypeRelAuditException noSuchEntityException) {
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
	protected WatsonListTypeRelAudit removeImpl(
		WatsonListTypeRelAudit watsonListTypeRelAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonListTypeRelAudit)) {
				watsonListTypeRelAudit = (WatsonListTypeRelAudit)session.get(
					WatsonListTypeRelAuditImpl.class,
					watsonListTypeRelAudit.getPrimaryKeyObj());
			}

			if (watsonListTypeRelAudit != null) {
				session.delete(watsonListTypeRelAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonListTypeRelAudit != null) {
			clearCache(watsonListTypeRelAudit);
		}

		return watsonListTypeRelAudit;
	}

	@Override
	public WatsonListTypeRelAudit updateImpl(
		WatsonListTypeRelAudit watsonListTypeRelAudit) {

		boolean isNew = watsonListTypeRelAudit.isNew();

		if (!(watsonListTypeRelAudit instanceof
				WatsonListTypeRelAuditModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonListTypeRelAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonListTypeRelAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonListTypeRelAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonListTypeRelAudit implementation " +
					watsonListTypeRelAudit.getClass());
		}

		WatsonListTypeRelAuditModelImpl watsonListTypeRelAuditModelImpl =
			(WatsonListTypeRelAuditModelImpl)watsonListTypeRelAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonListTypeRelAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonListTypeRelAudit.setCreateDate(now);
			}
			else {
				watsonListTypeRelAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonListTypeRelAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonListTypeRelAudit.setModifiedDate(now);
			}
			else {
				watsonListTypeRelAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonListTypeRelAudit);

				watsonListTypeRelAudit.setNew(false);
			}
			else {
				watsonListTypeRelAudit = (WatsonListTypeRelAudit)session.merge(
					watsonListTypeRelAudit);
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
			WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelAuditImpl.class,
			watsonListTypeRelAudit.getPrimaryKey(), watsonListTypeRelAudit,
			false);

		watsonListTypeRelAudit.resetOriginalValues();

		return watsonListTypeRelAudit;
	}

	/**
	 * Returns the watson list type rel audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type rel audit
	 * @return the watson list type rel audit
	 * @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRelAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchListTypeRelAuditException {

		WatsonListTypeRelAudit watsonListTypeRelAudit = fetchByPrimaryKey(
			primaryKey);

		if (watsonListTypeRelAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchListTypeRelAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonListTypeRelAudit;
	}

	/**
	 * Returns the watson list type rel audit with the primary key or throws a <code>NoSuchListTypeRelAuditException</code> if it could not be found.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit
	 * @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRelAudit findByPrimaryKey(
			long watsonListTypeRelAuditId)
		throws NoSuchListTypeRelAuditException {

		return findByPrimaryKey((Serializable)watsonListTypeRelAuditId);
	}

	/**
	 * Returns the watson list type rel audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type rel audit
	 * @return the watson list type rel audit, or <code>null</code> if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRelAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonListTypeRelAudit watsonListTypeRelAudit =
			(WatsonListTypeRelAudit)serializable;

		if (watsonListTypeRelAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonListTypeRelAudit = (WatsonListTypeRelAudit)session.get(
					WatsonListTypeRelAuditImpl.class, primaryKey);

				if (watsonListTypeRelAudit != null) {
					cacheResult(watsonListTypeRelAudit);
				}
				else {
					entityCache.putResult(
						WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeRelAuditImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonListTypeRelAudit;
	}

	/**
	 * Returns the watson list type rel audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit, or <code>null</code> if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRelAudit fetchByPrimaryKey(
		long watsonListTypeRelAuditId) {

		return fetchByPrimaryKey((Serializable)watsonListTypeRelAuditId);
	}

	@Override
	public Map<Serializable, WatsonListTypeRelAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonListTypeRelAudit> map =
			new HashMap<Serializable, WatsonListTypeRelAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonListTypeRelAudit watsonListTypeRelAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonListTypeRelAudit != null) {
				map.put(primaryKey, watsonListTypeRelAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonListTypeRelAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONLISTTYPERELAUDIT_WHERE_PKS_IN);

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

			for (WatsonListTypeRelAudit watsonListTypeRelAudit :
					(List<WatsonListTypeRelAudit>)query.list()) {

				map.put(
					watsonListTypeRelAudit.getPrimaryKeyObj(),
					watsonListTypeRelAudit);

				cacheResult(watsonListTypeRelAudit);

				uncachedPrimaryKeys.remove(
					watsonListTypeRelAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson list type rel audits.
	 *
	 * @return the watson list type rel audits
	 */
	@Override
	public List<WatsonListTypeRelAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @return the range of watson list type rel audits
	 */
	@Override
	public List<WatsonListTypeRelAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list type rel audits
	 */
	@Override
	public List<WatsonListTypeRelAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonListTypeRelAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson list type rel audits
	 */
	@Override
	public List<WatsonListTypeRelAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonListTypeRelAudit> orderByComparator,
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

		List<WatsonListTypeRelAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonListTypeRelAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONLISTTYPERELAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONLISTTYPERELAUDIT;

				sql = sql.concat(WatsonListTypeRelAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonListTypeRelAudit>)QueryUtil.list(
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
	 * Removes all the watson list type rel audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonListTypeRelAudit watsonListTypeRelAudit : findAll()) {
			remove(watsonListTypeRelAudit);
		}
	}

	/**
	 * Returns the number of watson list type rel audits.
	 *
	 * @return the number of watson list type rel audits
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
					_SQL_COUNT_WATSONLISTTYPERELAUDIT);

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
		return WatsonListTypeRelAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson list type rel audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeRelAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeRelAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonListTypeRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonListTypeRelAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONLISTTYPERELAUDIT =
		"SELECT watsonListTypeRelAudit FROM WatsonListTypeRelAudit watsonListTypeRelAudit";

	private static final String
		_SQL_SELECT_WATSONLISTTYPERELAUDIT_WHERE_PKS_IN =
			"SELECT watsonListTypeRelAudit FROM WatsonListTypeRelAudit watsonListTypeRelAudit WHERE watsonListTypeRelAuditId IN (";

	private static final String _SQL_COUNT_WATSONLISTTYPERELAUDIT =
		"SELECT COUNT(watsonListTypeRelAudit) FROM WatsonListTypeRelAudit watsonListTypeRelAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"watsonListTypeRelAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonListTypeRelAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonListTypeRelAuditPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"primary", "type"});

}
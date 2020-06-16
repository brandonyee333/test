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
 * @generated
 */
public class WatsonListTypeAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonListTypeAudit>
	implements WatsonListTypeAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonListTypeAuditUtil</code> to access the watson list type audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonListTypeAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonListTypeAuditPersistenceImpl() {
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

		setModelClass(WatsonListTypeAudit.class);
	}

	/**
	 * Caches the watson list type audit in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeAudit the watson list type audit
	 */
	@Override
	public void cacheResult(WatsonListTypeAudit watsonListTypeAudit) {
		entityCache.putResult(
			WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonListTypeAudit watsonListTypeAudit) {
		entityCache.removeResult(
			WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class, watsonListTypeAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonListTypeAudit> watsonListTypeAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonListTypeAudit watsonListTypeAudit : watsonListTypeAudits) {
			entityCache.removeResult(
				WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeAuditImpl.class,
				watsonListTypeAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeAuditImpl.class, primaryKey);
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

		watsonListTypeAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

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

			WatsonListTypeAudit watsonListTypeAudit =
				(WatsonListTypeAudit)session.get(
					WatsonListTypeAuditImpl.class, primaryKey);

			if (watsonListTypeAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchListTypeAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonListTypeAudit);
		}
		catch (NoSuchListTypeAuditException noSuchEntityException) {
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
	protected WatsonListTypeAudit removeImpl(
		WatsonListTypeAudit watsonListTypeAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonListTypeAudit)) {
				watsonListTypeAudit = (WatsonListTypeAudit)session.get(
					WatsonListTypeAuditImpl.class,
					watsonListTypeAudit.getPrimaryKeyObj());
			}

			if (watsonListTypeAudit != null) {
				session.delete(watsonListTypeAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
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
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonListTypeAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonListTypeAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonListTypeAudit implementation " +
					watsonListTypeAudit.getClass());
		}

		WatsonListTypeAuditModelImpl watsonListTypeAuditModelImpl =
			(WatsonListTypeAuditModelImpl)watsonListTypeAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonListTypeAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonListTypeAudit.setCreateDate(now);
			}
			else {
				watsonListTypeAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonListTypeAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonListTypeAudit.setModifiedDate(now);
			}
			else {
				watsonListTypeAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
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
				watsonListTypeAudit = (WatsonListTypeAudit)session.merge(
					watsonListTypeAudit);
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
			WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class, watsonListTypeAudit.getPrimaryKey(),
			watsonListTypeAudit, false);

		watsonListTypeAudit.resetOriginalValues();

		return watsonListTypeAudit;
	}

	/**
	 * Returns the watson list type audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
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

			throw new NoSuchListTypeAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonListTypeAudit;
	}

	/**
	 * Returns the watson list type audit with the primary key or throws a <code>NoSuchListTypeAuditException</code> if it could not be found.
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
		Serializable serializable = entityCache.getResult(
			WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonListTypeAudit watsonListTypeAudit =
			(WatsonListTypeAudit)serializable;

		if (watsonListTypeAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonListTypeAudit = (WatsonListTypeAudit)session.get(
					WatsonListTypeAuditImpl.class, primaryKey);

				if (watsonListTypeAudit != null) {
					cacheResult(watsonListTypeAudit);
				}
				else {
					entityCache.putResult(
						WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeAuditImpl.class, primaryKey);

				throw processException(exception);
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

		Map<Serializable, WatsonListTypeAudit> map =
			new HashMap<Serializable, WatsonListTypeAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonListTypeAudit watsonListTypeAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonListTypeAudit != null) {
				map.put(primaryKey, watsonListTypeAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONLISTTYPEAUDIT_WHERE_PKS_IN);

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

			for (WatsonListTypeAudit watsonListTypeAudit :
					(List<WatsonListTypeAudit>)query.list()) {

				map.put(
					watsonListTypeAudit.getPrimaryKeyObj(),
					watsonListTypeAudit);

				cacheResult(watsonListTypeAudit);

				uncachedPrimaryKeys.remove(
					watsonListTypeAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeAuditImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list type audits
	 */
	@Override
	public List<WatsonListTypeAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonListTypeAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson list type audits
	 */
	@Override
	public List<WatsonListTypeAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonListTypeAudit> orderByComparator,
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

		List<WatsonListTypeAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonListTypeAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONLISTTYPEAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONLISTTYPEAUDIT;

				sql = sql.concat(WatsonListTypeAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonListTypeAudit>)QueryUtil.list(
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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_WATSONLISTTYPEAUDIT);

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
		return WatsonListTypeAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson list type audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonListTypeAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonListTypeAuditImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONLISTTYPEAUDIT =
		"SELECT watsonListTypeAudit FROM WatsonListTypeAudit watsonListTypeAudit";

	private static final String _SQL_SELECT_WATSONLISTTYPEAUDIT_WHERE_PKS_IN =
		"SELECT watsonListTypeAudit FROM WatsonListTypeAudit watsonListTypeAudit WHERE watsonListTypeAuditId IN (";

	private static final String _SQL_COUNT_WATSONLISTTYPEAUDIT =
		"SELECT COUNT(watsonListTypeAudit) FROM WatsonListTypeAudit watsonListTypeAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonListTypeAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonListTypeAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonListTypeAuditPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}
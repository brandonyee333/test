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
import com.liferay.watson.exception.NoSuchAddressAuditException;
import com.liferay.watson.model.WatsonAddressAudit;
import com.liferay.watson.model.impl.WatsonAddressAuditImpl;
import com.liferay.watson.model.impl.WatsonAddressAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonAddressAuditPersistence;

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
 * The persistence implementation for the watson address audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonAddressAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonAddressAudit>
	implements WatsonAddressAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonAddressAuditUtil</code> to access the watson address audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonAddressAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonAddressAuditPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("number", "number_");

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

		setModelClass(WatsonAddressAudit.class);
	}

	/**
	 * Caches the watson address audit in the entity cache if it is enabled.
	 *
	 * @param watsonAddressAudit the watson address audit
	 */
	@Override
	public void cacheResult(WatsonAddressAudit watsonAddressAudit) {
		entityCache.putResult(
			WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressAuditImpl.class, watsonAddressAudit.getPrimaryKey(),
			watsonAddressAudit);

		watsonAddressAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson address audits in the entity cache if it is enabled.
	 *
	 * @param watsonAddressAudits the watson address audits
	 */
	@Override
	public void cacheResult(List<WatsonAddressAudit> watsonAddressAudits) {
		for (WatsonAddressAudit watsonAddressAudit : watsonAddressAudits) {
			if (entityCache.getResult(
					WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressAuditImpl.class,
					watsonAddressAudit.getPrimaryKey()) == null) {

				cacheResult(watsonAddressAudit);
			}
			else {
				watsonAddressAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson address audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonAddressAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson address audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonAddressAudit watsonAddressAudit) {
		entityCache.removeResult(
			WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressAuditImpl.class, watsonAddressAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonAddressAudit> watsonAddressAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonAddressAudit watsonAddressAudit : watsonAddressAudits) {
			entityCache.removeResult(
				WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressAuditImpl.class,
				watsonAddressAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson address audit with the primary key. Does not add the watson address audit to the database.
	 *
	 * @param watsonAddressAuditId the primary key for the new watson address audit
	 * @return the new watson address audit
	 */
	@Override
	public WatsonAddressAudit create(long watsonAddressAuditId) {
		WatsonAddressAudit watsonAddressAudit = new WatsonAddressAuditImpl();

		watsonAddressAudit.setNew(true);
		watsonAddressAudit.setPrimaryKey(watsonAddressAuditId);

		watsonAddressAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonAddressAudit;
	}

	/**
	 * Removes the watson address audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit that was removed
	 * @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	 */
	@Override
	public WatsonAddressAudit remove(long watsonAddressAuditId)
		throws NoSuchAddressAuditException {

		return remove((Serializable)watsonAddressAuditId);
	}

	/**
	 * Removes the watson address audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson address audit
	 * @return the watson address audit that was removed
	 * @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	 */
	@Override
	public WatsonAddressAudit remove(Serializable primaryKey)
		throws NoSuchAddressAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonAddressAudit watsonAddressAudit =
				(WatsonAddressAudit)session.get(
					WatsonAddressAuditImpl.class, primaryKey);

			if (watsonAddressAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAddressAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonAddressAudit);
		}
		catch (NoSuchAddressAuditException noSuchEntityException) {
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
	protected WatsonAddressAudit removeImpl(
		WatsonAddressAudit watsonAddressAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonAddressAudit)) {
				watsonAddressAudit = (WatsonAddressAudit)session.get(
					WatsonAddressAuditImpl.class,
					watsonAddressAudit.getPrimaryKeyObj());
			}

			if (watsonAddressAudit != null) {
				session.delete(watsonAddressAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonAddressAudit != null) {
			clearCache(watsonAddressAudit);
		}

		return watsonAddressAudit;
	}

	@Override
	public WatsonAddressAudit updateImpl(
		WatsonAddressAudit watsonAddressAudit) {

		boolean isNew = watsonAddressAudit.isNew();

		if (!(watsonAddressAudit instanceof WatsonAddressAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonAddressAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonAddressAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonAddressAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonAddressAudit implementation " +
					watsonAddressAudit.getClass());
		}

		WatsonAddressAuditModelImpl watsonAddressAuditModelImpl =
			(WatsonAddressAuditModelImpl)watsonAddressAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonAddressAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonAddressAudit.setCreateDate(now);
			}
			else {
				watsonAddressAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonAddressAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonAddressAudit.setModifiedDate(now);
			}
			else {
				watsonAddressAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonAddressAudit);

				watsonAddressAudit.setNew(false);
			}
			else {
				watsonAddressAudit = (WatsonAddressAudit)session.merge(
					watsonAddressAudit);
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
			WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressAuditImpl.class, watsonAddressAudit.getPrimaryKey(),
			watsonAddressAudit, false);

		watsonAddressAudit.resetOriginalValues();

		return watsonAddressAudit;
	}

	/**
	 * Returns the watson address audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson address audit
	 * @return the watson address audit
	 * @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	 */
	@Override
	public WatsonAddressAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAddressAuditException {

		WatsonAddressAudit watsonAddressAudit = fetchByPrimaryKey(primaryKey);

		if (watsonAddressAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAddressAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonAddressAudit;
	}

	/**
	 * Returns the watson address audit with the primary key or throws a <code>NoSuchAddressAuditException</code> if it could not be found.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit
	 * @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	 */
	@Override
	public WatsonAddressAudit findByPrimaryKey(long watsonAddressAuditId)
		throws NoSuchAddressAuditException {

		return findByPrimaryKey((Serializable)watsonAddressAuditId);
	}

	/**
	 * Returns the watson address audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson address audit
	 * @return the watson address audit, or <code>null</code> if a watson address audit with the primary key could not be found
	 */
	@Override
	public WatsonAddressAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonAddressAudit watsonAddressAudit =
			(WatsonAddressAudit)serializable;

		if (watsonAddressAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonAddressAudit = (WatsonAddressAudit)session.get(
					WatsonAddressAuditImpl.class, primaryKey);

				if (watsonAddressAudit != null) {
					cacheResult(watsonAddressAudit);
				}
				else {
					entityCache.putResult(
						WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonAddressAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonAddressAudit;
	}

	/**
	 * Returns the watson address audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit, or <code>null</code> if a watson address audit with the primary key could not be found
	 */
	@Override
	public WatsonAddressAudit fetchByPrimaryKey(long watsonAddressAuditId) {
		return fetchByPrimaryKey((Serializable)watsonAddressAuditId);
	}

	@Override
	public Map<Serializable, WatsonAddressAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonAddressAudit> map =
			new HashMap<Serializable, WatsonAddressAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonAddressAudit watsonAddressAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonAddressAudit != null) {
				map.put(primaryKey, watsonAddressAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonAddressAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONADDRESSAUDIT_WHERE_PKS_IN);

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

			for (WatsonAddressAudit watsonAddressAudit :
					(List<WatsonAddressAudit>)query.list()) {

				map.put(
					watsonAddressAudit.getPrimaryKeyObj(), watsonAddressAudit);

				cacheResult(watsonAddressAudit);

				uncachedPrimaryKeys.remove(
					watsonAddressAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson address audits.
	 *
	 * @return the watson address audits
	 */
	@Override
	public List<WatsonAddressAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson address audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonAddressAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson address audits
	 * @param end the upper bound of the range of watson address audits (not inclusive)
	 * @return the range of watson address audits
	 */
	@Override
	public List<WatsonAddressAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson address audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonAddressAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson address audits
	 * @param end the upper bound of the range of watson address audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson address audits
	 */
	@Override
	public List<WatsonAddressAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonAddressAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson address audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonAddressAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson address audits
	 * @param end the upper bound of the range of watson address audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson address audits
	 */
	@Override
	public List<WatsonAddressAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonAddressAudit> orderByComparator,
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

		List<WatsonAddressAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonAddressAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONADDRESSAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONADDRESSAUDIT;

				sql = sql.concat(WatsonAddressAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonAddressAudit>)QueryUtil.list(
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
	 * Removes all the watson address audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonAddressAudit watsonAddressAudit : findAll()) {
			remove(watsonAddressAudit);
		}
	}

	/**
	 * Returns the number of watson address audits.
	 *
	 * @return the number of watson address audits
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
					_SQL_COUNT_WATSONADDRESSAUDIT);

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
		return WatsonAddressAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson address audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonAddressAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonAddressAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonAddressAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonAddressAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONADDRESSAUDIT =
		"SELECT watsonAddressAudit FROM WatsonAddressAudit watsonAddressAudit";

	private static final String _SQL_SELECT_WATSONADDRESSAUDIT_WHERE_PKS_IN =
		"SELECT watsonAddressAudit FROM WatsonAddressAudit watsonAddressAudit WHERE watsonAddressAuditId IN (";

	private static final String _SQL_COUNT_WATSONADDRESSAUDIT =
		"SELECT COUNT(watsonAddressAudit) FROM WatsonAddressAudit watsonAddressAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonAddressAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonAddressAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonAddressAuditPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"number"});

}
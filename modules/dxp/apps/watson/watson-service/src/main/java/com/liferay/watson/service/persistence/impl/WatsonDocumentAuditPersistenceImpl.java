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
import com.liferay.watson.exception.NoSuchDocumentAuditException;
import com.liferay.watson.model.WatsonDocumentAudit;
import com.liferay.watson.model.impl.WatsonDocumentAuditImpl;
import com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonDocumentAuditPersistence;

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
 * The persistence implementation for the watson document audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonDocumentAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonDocumentAudit>
	implements WatsonDocumentAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonDocumentAuditUtil</code> to access the watson document audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonDocumentAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonDocumentAuditPersistenceImpl() {
		setModelClass(WatsonDocumentAudit.class);
	}

	/**
	 * Caches the watson document audit in the entity cache if it is enabled.
	 *
	 * @param watsonDocumentAudit the watson document audit
	 */
	@Override
	public void cacheResult(WatsonDocumentAudit watsonDocumentAudit) {
		entityCache.putResult(
			WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class, watsonDocumentAudit.getPrimaryKey(),
			watsonDocumentAudit);

		watsonDocumentAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson document audits in the entity cache if it is enabled.
	 *
	 * @param watsonDocumentAudits the watson document audits
	 */
	@Override
	public void cacheResult(List<WatsonDocumentAudit> watsonDocumentAudits) {
		for (WatsonDocumentAudit watsonDocumentAudit : watsonDocumentAudits) {
			if (entityCache.getResult(
					WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentAuditImpl.class,
					watsonDocumentAudit.getPrimaryKey()) == null) {

				cacheResult(watsonDocumentAudit);
			}
			else {
				watsonDocumentAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson document audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonDocumentAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson document audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonDocumentAudit watsonDocumentAudit) {
		entityCache.removeResult(
			WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class, watsonDocumentAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonDocumentAudit> watsonDocumentAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonDocumentAudit watsonDocumentAudit : watsonDocumentAudits) {
			entityCache.removeResult(
				WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentAuditImpl.class,
				watsonDocumentAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson document audit with the primary key. Does not add the watson document audit to the database.
	 *
	 * @param watsonDocumentAuditId the primary key for the new watson document audit
	 * @return the new watson document audit
	 */
	@Override
	public WatsonDocumentAudit create(long watsonDocumentAuditId) {
		WatsonDocumentAudit watsonDocumentAudit = new WatsonDocumentAuditImpl();

		watsonDocumentAudit.setNew(true);
		watsonDocumentAudit.setPrimaryKey(watsonDocumentAuditId);

		watsonDocumentAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonDocumentAudit;
	}

	/**
	 * Removes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit that was removed
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit remove(long watsonDocumentAuditId)
		throws NoSuchDocumentAuditException {

		return remove((Serializable)watsonDocumentAuditId);
	}

	/**
	 * Removes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson document audit
	 * @return the watson document audit that was removed
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit remove(Serializable primaryKey)
		throws NoSuchDocumentAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonDocumentAudit watsonDocumentAudit =
				(WatsonDocumentAudit)session.get(
					WatsonDocumentAuditImpl.class, primaryKey);

			if (watsonDocumentAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocumentAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonDocumentAudit);
		}
		catch (NoSuchDocumentAuditException noSuchEntityException) {
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
	protected WatsonDocumentAudit removeImpl(
		WatsonDocumentAudit watsonDocumentAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonDocumentAudit)) {
				watsonDocumentAudit = (WatsonDocumentAudit)session.get(
					WatsonDocumentAuditImpl.class,
					watsonDocumentAudit.getPrimaryKeyObj());
			}

			if (watsonDocumentAudit != null) {
				session.delete(watsonDocumentAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonDocumentAudit != null) {
			clearCache(watsonDocumentAudit);
		}

		return watsonDocumentAudit;
	}

	@Override
	public WatsonDocumentAudit updateImpl(
		WatsonDocumentAudit watsonDocumentAudit) {

		boolean isNew = watsonDocumentAudit.isNew();

		if (!(watsonDocumentAudit instanceof WatsonDocumentAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonDocumentAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonDocumentAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonDocumentAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonDocumentAudit implementation " +
					watsonDocumentAudit.getClass());
		}

		WatsonDocumentAuditModelImpl watsonDocumentAuditModelImpl =
			(WatsonDocumentAuditModelImpl)watsonDocumentAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonDocumentAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonDocumentAudit.setCreateDate(now);
			}
			else {
				watsonDocumentAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonDocumentAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonDocumentAudit.setModifiedDate(now);
			}
			else {
				watsonDocumentAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonDocumentAudit);

				watsonDocumentAudit.setNew(false);
			}
			else {
				watsonDocumentAudit = (WatsonDocumentAudit)session.merge(
					watsonDocumentAudit);
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
			WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class, watsonDocumentAudit.getPrimaryKey(),
			watsonDocumentAudit, false);

		watsonDocumentAudit.resetOriginalValues();

		return watsonDocumentAudit;
	}

	/**
	 * Returns the watson document audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson document audit
	 * @return the watson document audit
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocumentAuditException {

		WatsonDocumentAudit watsonDocumentAudit = fetchByPrimaryKey(primaryKey);

		if (watsonDocumentAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocumentAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonDocumentAudit;
	}

	/**
	 * Returns the watson document audit with the primary key or throws a <code>NoSuchDocumentAuditException</code> if it could not be found.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit findByPrimaryKey(long watsonDocumentAuditId)
		throws NoSuchDocumentAuditException {

		return findByPrimaryKey((Serializable)watsonDocumentAuditId);
	}

	/**
	 * Returns the watson document audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson document audit
	 * @return the watson document audit, or <code>null</code> if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonDocumentAudit watsonDocumentAudit =
			(WatsonDocumentAudit)serializable;

		if (watsonDocumentAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonDocumentAudit = (WatsonDocumentAudit)session.get(
					WatsonDocumentAuditImpl.class, primaryKey);

				if (watsonDocumentAudit != null) {
					cacheResult(watsonDocumentAudit);
				}
				else {
					entityCache.putResult(
						WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonDocumentAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonDocumentAudit;
	}

	/**
	 * Returns the watson document audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit, or <code>null</code> if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit fetchByPrimaryKey(long watsonDocumentAuditId) {
		return fetchByPrimaryKey((Serializable)watsonDocumentAuditId);
	}

	@Override
	public Map<Serializable, WatsonDocumentAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonDocumentAudit> map =
			new HashMap<Serializable, WatsonDocumentAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonDocumentAudit watsonDocumentAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonDocumentAudit != null) {
				map.put(primaryKey, watsonDocumentAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonDocumentAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONDOCUMENTAUDIT_WHERE_PKS_IN);

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

			for (WatsonDocumentAudit watsonDocumentAudit :
					(List<WatsonDocumentAudit>)query.list()) {

				map.put(
					watsonDocumentAudit.getPrimaryKeyObj(),
					watsonDocumentAudit);

				cacheResult(watsonDocumentAudit);

				uncachedPrimaryKeys.remove(
					watsonDocumentAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson document audits.
	 *
	 * @return the watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonDocumentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @return the range of watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonDocumentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonDocumentAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonDocumentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonDocumentAudit> orderByComparator,
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

		List<WatsonDocumentAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonDocumentAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONDOCUMENTAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONDOCUMENTAUDIT;

				sql = sql.concat(WatsonDocumentAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonDocumentAudit>)QueryUtil.list(
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
	 * Removes all the watson document audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonDocumentAudit watsonDocumentAudit : findAll()) {
			remove(watsonDocumentAudit);
		}
	}

	/**
	 * Returns the number of watson document audits.
	 *
	 * @return the number of watson document audits
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
					_SQL_COUNT_WATSONDOCUMENTAUDIT);

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
		return WatsonDocumentAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson document audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonDocumentAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONDOCUMENTAUDIT =
		"SELECT watsonDocumentAudit FROM WatsonDocumentAudit watsonDocumentAudit";

	private static final String _SQL_SELECT_WATSONDOCUMENTAUDIT_WHERE_PKS_IN =
		"SELECT watsonDocumentAudit FROM WatsonDocumentAudit watsonDocumentAudit WHERE watsonDocumentAuditId IN (";

	private static final String _SQL_COUNT_WATSONDOCUMENTAUDIT =
		"SELECT COUNT(watsonDocumentAudit) FROM WatsonDocumentAudit watsonDocumentAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonDocumentAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonDocumentAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonDocumentAuditPersistenceImpl.class);

}
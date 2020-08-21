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
import com.liferay.watson.exception.NoSuchRelationshipAuditException;
import com.liferay.watson.model.WatsonRelationshipAudit;
import com.liferay.watson.model.impl.WatsonRelationshipAuditImpl;
import com.liferay.watson.model.impl.WatsonRelationshipAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonRelationshipAuditPersistence;

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
 * The persistence implementation for the watson relationship audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonRelationshipAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonRelationshipAudit>
	implements WatsonRelationshipAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonRelationshipAuditUtil</code> to access the watson relationship audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonRelationshipAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonRelationshipAuditPersistenceImpl() {
		setModelClass(WatsonRelationshipAudit.class);
	}

	/**
	 * Caches the watson relationship audit in the entity cache if it is enabled.
	 *
	 * @param watsonRelationshipAudit the watson relationship audit
	 */
	@Override
	public void cacheResult(WatsonRelationshipAudit watsonRelationshipAudit) {
		entityCache.putResult(
			WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipAuditImpl.class,
			watsonRelationshipAudit.getPrimaryKey(), watsonRelationshipAudit);

		watsonRelationshipAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson relationship audits in the entity cache if it is enabled.
	 *
	 * @param watsonRelationshipAudits the watson relationship audits
	 */
	@Override
	public void cacheResult(
		List<WatsonRelationshipAudit> watsonRelationshipAudits) {

		for (WatsonRelationshipAudit watsonRelationshipAudit :
				watsonRelationshipAudits) {

			if (entityCache.getResult(
					WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipAuditImpl.class,
					watsonRelationshipAudit.getPrimaryKey()) == null) {

				cacheResult(watsonRelationshipAudit);
			}
			else {
				watsonRelationshipAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson relationship audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonRelationshipAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson relationship audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonRelationshipAudit watsonRelationshipAudit) {
		entityCache.removeResult(
			WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipAuditImpl.class,
			watsonRelationshipAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<WatsonRelationshipAudit> watsonRelationshipAudits) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonRelationshipAudit watsonRelationshipAudit :
				watsonRelationshipAudits) {

			entityCache.removeResult(
				WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipAuditImpl.class,
				watsonRelationshipAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson relationship audit with the primary key. Does not add the watson relationship audit to the database.
	 *
	 * @param watsonRelationshipAuditId the primary key for the new watson relationship audit
	 * @return the new watson relationship audit
	 */
	@Override
	public WatsonRelationshipAudit create(long watsonRelationshipAuditId) {
		WatsonRelationshipAudit watsonRelationshipAudit =
			new WatsonRelationshipAuditImpl();

		watsonRelationshipAudit.setNew(true);
		watsonRelationshipAudit.setPrimaryKey(watsonRelationshipAuditId);

		watsonRelationshipAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonRelationshipAudit;
	}

	/**
	 * Removes the watson relationship audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit that was removed
	 * @throws NoSuchRelationshipAuditException if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public WatsonRelationshipAudit remove(long watsonRelationshipAuditId)
		throws NoSuchRelationshipAuditException {

		return remove((Serializable)watsonRelationshipAuditId);
	}

	/**
	 * Removes the watson relationship audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson relationship audit
	 * @return the watson relationship audit that was removed
	 * @throws NoSuchRelationshipAuditException if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public WatsonRelationshipAudit remove(Serializable primaryKey)
		throws NoSuchRelationshipAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonRelationshipAudit watsonRelationshipAudit =
				(WatsonRelationshipAudit)session.get(
					WatsonRelationshipAuditImpl.class, primaryKey);

			if (watsonRelationshipAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRelationshipAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonRelationshipAudit);
		}
		catch (NoSuchRelationshipAuditException noSuchEntityException) {
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
	protected WatsonRelationshipAudit removeImpl(
		WatsonRelationshipAudit watsonRelationshipAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonRelationshipAudit)) {
				watsonRelationshipAudit = (WatsonRelationshipAudit)session.get(
					WatsonRelationshipAuditImpl.class,
					watsonRelationshipAudit.getPrimaryKeyObj());
			}

			if (watsonRelationshipAudit != null) {
				session.delete(watsonRelationshipAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonRelationshipAudit != null) {
			clearCache(watsonRelationshipAudit);
		}

		return watsonRelationshipAudit;
	}

	@Override
	public WatsonRelationshipAudit updateImpl(
		WatsonRelationshipAudit watsonRelationshipAudit) {

		boolean isNew = watsonRelationshipAudit.isNew();

		if (!(watsonRelationshipAudit instanceof
				WatsonRelationshipAuditModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonRelationshipAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonRelationshipAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonRelationshipAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonRelationshipAudit implementation " +
					watsonRelationshipAudit.getClass());
		}

		WatsonRelationshipAuditModelImpl watsonRelationshipAuditModelImpl =
			(WatsonRelationshipAuditModelImpl)watsonRelationshipAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonRelationshipAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonRelationshipAudit.setCreateDate(now);
			}
			else {
				watsonRelationshipAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonRelationshipAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonRelationshipAudit.setModifiedDate(now);
			}
			else {
				watsonRelationshipAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonRelationshipAudit);

				watsonRelationshipAudit.setNew(false);
			}
			else {
				watsonRelationshipAudit =
					(WatsonRelationshipAudit)session.merge(
						watsonRelationshipAudit);
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
			WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipAuditImpl.class,
			watsonRelationshipAudit.getPrimaryKey(), watsonRelationshipAudit,
			false);

		watsonRelationshipAudit.resetOriginalValues();

		return watsonRelationshipAudit;
	}

	/**
	 * Returns the watson relationship audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson relationship audit
	 * @return the watson relationship audit
	 * @throws NoSuchRelationshipAuditException if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public WatsonRelationshipAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRelationshipAuditException {

		WatsonRelationshipAudit watsonRelationshipAudit = fetchByPrimaryKey(
			primaryKey);

		if (watsonRelationshipAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRelationshipAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonRelationshipAudit;
	}

	/**
	 * Returns the watson relationship audit with the primary key or throws a <code>NoSuchRelationshipAuditException</code> if it could not be found.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit
	 * @throws NoSuchRelationshipAuditException if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public WatsonRelationshipAudit findByPrimaryKey(
			long watsonRelationshipAuditId)
		throws NoSuchRelationshipAuditException {

		return findByPrimaryKey((Serializable)watsonRelationshipAuditId);
	}

	/**
	 * Returns the watson relationship audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson relationship audit
	 * @return the watson relationship audit, or <code>null</code> if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public WatsonRelationshipAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonRelationshipAudit watsonRelationshipAudit =
			(WatsonRelationshipAudit)serializable;

		if (watsonRelationshipAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonRelationshipAudit = (WatsonRelationshipAudit)session.get(
					WatsonRelationshipAuditImpl.class, primaryKey);

				if (watsonRelationshipAudit != null) {
					cacheResult(watsonRelationshipAudit);
				}
				else {
					entityCache.putResult(
						WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonRelationshipAuditImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonRelationshipAudit;
	}

	/**
	 * Returns the watson relationship audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit, or <code>null</code> if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public WatsonRelationshipAudit fetchByPrimaryKey(
		long watsonRelationshipAuditId) {

		return fetchByPrimaryKey((Serializable)watsonRelationshipAuditId);
	}

	@Override
	public Map<Serializable, WatsonRelationshipAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonRelationshipAudit> map =
			new HashMap<Serializable, WatsonRelationshipAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonRelationshipAudit watsonRelationshipAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonRelationshipAudit != null) {
				map.put(primaryKey, watsonRelationshipAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonRelationshipAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONRELATIONSHIPAUDIT_WHERE_PKS_IN);

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

			for (WatsonRelationshipAudit watsonRelationshipAudit :
					(List<WatsonRelationshipAudit>)query.list()) {

				map.put(
					watsonRelationshipAudit.getPrimaryKeyObj(),
					watsonRelationshipAudit);

				cacheResult(watsonRelationshipAudit);

				uncachedPrimaryKeys.remove(
					watsonRelationshipAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson relationship audits.
	 *
	 * @return the watson relationship audits
	 */
	@Override
	public List<WatsonRelationshipAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson relationship audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonRelationshipAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationship audits
	 * @param end the upper bound of the range of watson relationship audits (not inclusive)
	 * @return the range of watson relationship audits
	 */
	@Override
	public List<WatsonRelationshipAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson relationship audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonRelationshipAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationship audits
	 * @param end the upper bound of the range of watson relationship audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson relationship audits
	 */
	@Override
	public List<WatsonRelationshipAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonRelationshipAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson relationship audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonRelationshipAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationship audits
	 * @param end the upper bound of the range of watson relationship audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson relationship audits
	 */
	@Override
	public List<WatsonRelationshipAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonRelationshipAudit> orderByComparator,
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

		List<WatsonRelationshipAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonRelationshipAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONRELATIONSHIPAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONRELATIONSHIPAUDIT;

				sql = sql.concat(
					WatsonRelationshipAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonRelationshipAudit>)QueryUtil.list(
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
	 * Removes all the watson relationship audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonRelationshipAudit watsonRelationshipAudit : findAll()) {
			remove(watsonRelationshipAudit);
		}
	}

	/**
	 * Returns the number of watson relationship audits.
	 *
	 * @return the number of watson relationship audits
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
					_SQL_COUNT_WATSONRELATIONSHIPAUDIT);

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
		return WatsonRelationshipAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson relationship audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonRelationshipAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonRelationshipAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonRelationshipAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonRelationshipAuditImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONRELATIONSHIPAUDIT =
		"SELECT watsonRelationshipAudit FROM WatsonRelationshipAudit watsonRelationshipAudit";

	private static final String
		_SQL_SELECT_WATSONRELATIONSHIPAUDIT_WHERE_PKS_IN =
			"SELECT watsonRelationshipAudit FROM WatsonRelationshipAudit watsonRelationshipAudit WHERE watsonRelationshipAuditId IN (";

	private static final String _SQL_COUNT_WATSONRELATIONSHIPAUDIT =
		"SELECT COUNT(watsonRelationshipAudit) FROM WatsonRelationshipAudit watsonRelationshipAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"watsonRelationshipAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonRelationshipAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonRelationshipAuditPersistenceImpl.class);

}
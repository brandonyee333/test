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
import com.liferay.watson.exception.NoSuchIncidentRelAuditException;
import com.liferay.watson.model.WatsonIncidentRelAudit;
import com.liferay.watson.model.impl.WatsonIncidentRelAuditImpl;
import com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonIncidentRelAuditPersistence;

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
 * The persistence implementation for the watson incident rel audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonIncidentRelAuditPersistenceImpl
	extends BasePersistenceImpl<WatsonIncidentRelAudit>
	implements WatsonIncidentRelAuditPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonIncidentRelAuditUtil</code> to access the watson incident rel audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonIncidentRelAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonIncidentRelAuditPersistenceImpl() {
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

		setModelClass(WatsonIncidentRelAudit.class);
	}

	/**
	 * Caches the watson incident rel audit in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRelAudit the watson incident rel audit
	 */
	@Override
	public void cacheResult(WatsonIncidentRelAudit watsonIncidentRelAudit) {
		entityCache.putResult(
			WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelAuditImpl.class,
			watsonIncidentRelAudit.getPrimaryKey(), watsonIncidentRelAudit);

		watsonIncidentRelAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson incident rel audits in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRelAudits the watson incident rel audits
	 */
	@Override
	public void cacheResult(
		List<WatsonIncidentRelAudit> watsonIncidentRelAudits) {

		for (WatsonIncidentRelAudit watsonIncidentRelAudit :
				watsonIncidentRelAudits) {

			if (entityCache.getResult(
					WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelAuditImpl.class,
					watsonIncidentRelAudit.getPrimaryKey()) == null) {

				cacheResult(watsonIncidentRelAudit);
			}
			else {
				watsonIncidentRelAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson incident rel audits.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonIncidentRelAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson incident rel audit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonIncidentRelAudit watsonIncidentRelAudit) {
		entityCache.removeResult(
			WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelAuditImpl.class,
			watsonIncidentRelAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<WatsonIncidentRelAudit> watsonIncidentRelAudits) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonIncidentRelAudit watsonIncidentRelAudit :
				watsonIncidentRelAudits) {

			entityCache.removeResult(
				WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelAuditImpl.class,
				watsonIncidentRelAudit.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelAuditImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson incident rel audit with the primary key. Does not add the watson incident rel audit to the database.
	 *
	 * @param watsonIncidentRelAuditId the primary key for the new watson incident rel audit
	 * @return the new watson incident rel audit
	 */
	@Override
	public WatsonIncidentRelAudit create(long watsonIncidentRelAuditId) {
		WatsonIncidentRelAudit watsonIncidentRelAudit =
			new WatsonIncidentRelAuditImpl();

		watsonIncidentRelAudit.setNew(true);
		watsonIncidentRelAudit.setPrimaryKey(watsonIncidentRelAuditId);

		watsonIncidentRelAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonIncidentRelAudit;
	}

	/**
	 * Removes the watson incident rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	 * @return the watson incident rel audit that was removed
	 * @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRelAudit remove(long watsonIncidentRelAuditId)
		throws NoSuchIncidentRelAuditException {

		return remove((Serializable)watsonIncidentRelAuditId);
	}

	/**
	 * Removes the watson incident rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson incident rel audit
	 * @return the watson incident rel audit that was removed
	 * @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRelAudit remove(Serializable primaryKey)
		throws NoSuchIncidentRelAuditException {

		Session session = null;

		try {
			session = openSession();

			WatsonIncidentRelAudit watsonIncidentRelAudit =
				(WatsonIncidentRelAudit)session.get(
					WatsonIncidentRelAuditImpl.class, primaryKey);

			if (watsonIncidentRelAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIncidentRelAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonIncidentRelAudit);
		}
		catch (NoSuchIncidentRelAuditException noSuchEntityException) {
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
	protected WatsonIncidentRelAudit removeImpl(
		WatsonIncidentRelAudit watsonIncidentRelAudit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonIncidentRelAudit)) {
				watsonIncidentRelAudit = (WatsonIncidentRelAudit)session.get(
					WatsonIncidentRelAuditImpl.class,
					watsonIncidentRelAudit.getPrimaryKeyObj());
			}

			if (watsonIncidentRelAudit != null) {
				session.delete(watsonIncidentRelAudit);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonIncidentRelAudit != null) {
			clearCache(watsonIncidentRelAudit);
		}

		return watsonIncidentRelAudit;
	}

	@Override
	public WatsonIncidentRelAudit updateImpl(
		WatsonIncidentRelAudit watsonIncidentRelAudit) {

		boolean isNew = watsonIncidentRelAudit.isNew();

		if (!(watsonIncidentRelAudit instanceof
				WatsonIncidentRelAuditModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonIncidentRelAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonIncidentRelAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonIncidentRelAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonIncidentRelAudit implementation " +
					watsonIncidentRelAudit.getClass());
		}

		WatsonIncidentRelAuditModelImpl watsonIncidentRelAuditModelImpl =
			(WatsonIncidentRelAuditModelImpl)watsonIncidentRelAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonIncidentRelAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonIncidentRelAudit.setCreateDate(now);
			}
			else {
				watsonIncidentRelAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonIncidentRelAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonIncidentRelAudit.setModifiedDate(now);
			}
			else {
				watsonIncidentRelAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonIncidentRelAudit.isNew()) {
				session.save(watsonIncidentRelAudit);

				watsonIncidentRelAudit.setNew(false);
			}
			else {
				watsonIncidentRelAudit = (WatsonIncidentRelAudit)session.merge(
					watsonIncidentRelAudit);
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
			WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelAuditImpl.class,
			watsonIncidentRelAudit.getPrimaryKey(), watsonIncidentRelAudit,
			false);

		watsonIncidentRelAudit.resetOriginalValues();

		return watsonIncidentRelAudit;
	}

	/**
	 * Returns the watson incident rel audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident rel audit
	 * @return the watson incident rel audit
	 * @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRelAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIncidentRelAuditException {

		WatsonIncidentRelAudit watsonIncidentRelAudit = fetchByPrimaryKey(
			primaryKey);

		if (watsonIncidentRelAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIncidentRelAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonIncidentRelAudit;
	}

	/**
	 * Returns the watson incident rel audit with the primary key or throws a <code>NoSuchIncidentRelAuditException</code> if it could not be found.
	 *
	 * @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	 * @return the watson incident rel audit
	 * @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRelAudit findByPrimaryKey(
			long watsonIncidentRelAuditId)
		throws NoSuchIncidentRelAuditException {

		return findByPrimaryKey((Serializable)watsonIncidentRelAuditId);
	}

	/**
	 * Returns the watson incident rel audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident rel audit
	 * @return the watson incident rel audit, or <code>null</code> if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRelAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonIncidentRelAudit watsonIncidentRelAudit =
			(WatsonIncidentRelAudit)serializable;

		if (watsonIncidentRelAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonIncidentRelAudit = (WatsonIncidentRelAudit)session.get(
					WatsonIncidentRelAuditImpl.class, primaryKey);

				if (watsonIncidentRelAudit != null) {
					cacheResult(watsonIncidentRelAudit);
				}
				else {
					entityCache.putResult(
						WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonIncidentRelAuditImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelAuditImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonIncidentRelAudit;
	}

	/**
	 * Returns the watson incident rel audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	 * @return the watson incident rel audit, or <code>null</code> if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRelAudit fetchByPrimaryKey(
		long watsonIncidentRelAuditId) {

		return fetchByPrimaryKey((Serializable)watsonIncidentRelAuditId);
	}

	@Override
	public Map<Serializable, WatsonIncidentRelAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonIncidentRelAudit> map =
			new HashMap<Serializable, WatsonIncidentRelAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonIncidentRelAudit watsonIncidentRelAudit = fetchByPrimaryKey(
				primaryKey);

			if (watsonIncidentRelAudit != null) {
				map.put(primaryKey, watsonIncidentRelAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonIncidentRelAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONINCIDENTRELAUDIT_WHERE_PKS_IN);

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

			for (WatsonIncidentRelAudit watsonIncidentRelAudit :
					(List<WatsonIncidentRelAudit>)query.list()) {

				map.put(
					watsonIncidentRelAudit.getPrimaryKeyObj(),
					watsonIncidentRelAudit);

				cacheResult(watsonIncidentRelAudit);

				uncachedPrimaryKeys.remove(
					watsonIncidentRelAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson incident rel audits.
	 *
	 * @return the watson incident rel audits
	 */
	@Override
	public List<WatsonIncidentRelAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson incident rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rel audits
	 * @param end the upper bound of the range of watson incident rel audits (not inclusive)
	 * @return the range of watson incident rel audits
	 */
	@Override
	public List<WatsonIncidentRelAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson incident rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rel audits
	 * @param end the upper bound of the range of watson incident rel audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson incident rel audits
	 */
	@Override
	public List<WatsonIncidentRelAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentRelAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson incident rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rel audits
	 * @param end the upper bound of the range of watson incident rel audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson incident rel audits
	 */
	@Override
	public List<WatsonIncidentRelAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentRelAudit> orderByComparator,
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

		List<WatsonIncidentRelAudit> list = null;

		if (useFinderCache) {
			list = (List<WatsonIncidentRelAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONINCIDENTRELAUDIT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONINCIDENTRELAUDIT;

				sql = sql.concat(WatsonIncidentRelAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonIncidentRelAudit>)QueryUtil.list(
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
	 * Removes all the watson incident rel audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonIncidentRelAudit watsonIncidentRelAudit : findAll()) {
			remove(watsonIncidentRelAudit);
		}
	}

	/**
	 * Returns the number of watson incident rel audits.
	 *
	 * @return the number of watson incident rel audits
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
					_SQL_COUNT_WATSONINCIDENTRELAUDIT);

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
		return WatsonIncidentRelAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson incident rel audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentRelAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentRelAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonIncidentRelAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonIncidentRelAuditImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONINCIDENTRELAUDIT =
		"SELECT watsonIncidentRelAudit FROM WatsonIncidentRelAudit watsonIncidentRelAudit";

	private static final String
		_SQL_SELECT_WATSONINCIDENTRELAUDIT_WHERE_PKS_IN =
			"SELECT watsonIncidentRelAudit FROM WatsonIncidentRelAudit watsonIncidentRelAudit WHERE watsonIncidentRelAuditId IN (";

	private static final String _SQL_COUNT_WATSONINCIDENTRELAUDIT =
		"SELECT COUNT(watsonIncidentRelAudit) FROM WatsonIncidentRelAudit watsonIncidentRelAudit";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"watsonIncidentRelAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonIncidentRelAudit exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonIncidentRelAuditPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}
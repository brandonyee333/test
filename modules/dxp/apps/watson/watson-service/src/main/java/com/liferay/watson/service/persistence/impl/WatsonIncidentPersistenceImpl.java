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
import com.liferay.watson.exception.NoSuchIncidentException;
import com.liferay.watson.model.WatsonIncident;
import com.liferay.watson.model.impl.WatsonIncidentImpl;
import com.liferay.watson.model.impl.WatsonIncidentModelImpl;
import com.liferay.watson.service.persistence.WatsonIncidentPersistence;

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
 * The persistence implementation for the watson incident service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonIncidentPersistenceImpl
	extends BasePersistenceImpl<WatsonIncident>
	implements WatsonIncidentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonIncidentUtil</code> to access the watson incident persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonIncidentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonIncidentPersistenceImpl() {
		setModelClass(WatsonIncident.class);
	}

	/**
	 * Caches the watson incident in the entity cache if it is enabled.
	 *
	 * @param watsonIncident the watson incident
	 */
	@Override
	public void cacheResult(WatsonIncident watsonIncident) {
		entityCache.putResult(
			WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentImpl.class, watsonIncident.getPrimaryKey(),
			watsonIncident);

		watsonIncident.resetOriginalValues();
	}

	/**
	 * Caches the watson incidents in the entity cache if it is enabled.
	 *
	 * @param watsonIncidents the watson incidents
	 */
	@Override
	public void cacheResult(List<WatsonIncident> watsonIncidents) {
		for (WatsonIncident watsonIncident : watsonIncidents) {
			if (entityCache.getResult(
					WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentImpl.class, watsonIncident.getPrimaryKey()) ==
						null) {

				cacheResult(watsonIncident);
			}
			else {
				watsonIncident.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson incidents.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonIncidentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson incident.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonIncident watsonIncident) {
		entityCache.removeResult(
			WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentImpl.class, watsonIncident.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonIncident> watsonIncidents) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonIncident watsonIncident : watsonIncidents) {
			entityCache.removeResult(
				WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentImpl.class, watsonIncident.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson incident with the primary key. Does not add the watson incident to the database.
	 *
	 * @param watsonIncidentId the primary key for the new watson incident
	 * @return the new watson incident
	 */
	@Override
	public WatsonIncident create(long watsonIncidentId) {
		WatsonIncident watsonIncident = new WatsonIncidentImpl();

		watsonIncident.setNew(true);
		watsonIncident.setPrimaryKey(watsonIncidentId);

		watsonIncident.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonIncident;
	}

	/**
	 * Removes the watson incident with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident that was removed
	 * @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	 */
	@Override
	public WatsonIncident remove(long watsonIncidentId)
		throws NoSuchIncidentException {

		return remove((Serializable)watsonIncidentId);
	}

	/**
	 * Removes the watson incident with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson incident
	 * @return the watson incident that was removed
	 * @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	 */
	@Override
	public WatsonIncident remove(Serializable primaryKey)
		throws NoSuchIncidentException {

		Session session = null;

		try {
			session = openSession();

			WatsonIncident watsonIncident = (WatsonIncident)session.get(
				WatsonIncidentImpl.class, primaryKey);

			if (watsonIncident == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIncidentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonIncident);
		}
		catch (NoSuchIncidentException noSuchEntityException) {
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
	protected WatsonIncident removeImpl(WatsonIncident watsonIncident) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonIncident)) {
				watsonIncident = (WatsonIncident)session.get(
					WatsonIncidentImpl.class,
					watsonIncident.getPrimaryKeyObj());
			}

			if (watsonIncident != null) {
				session.delete(watsonIncident);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonIncident != null) {
			clearCache(watsonIncident);
		}

		return watsonIncident;
	}

	@Override
	public WatsonIncident updateImpl(WatsonIncident watsonIncident) {
		boolean isNew = watsonIncident.isNew();

		if (!(watsonIncident instanceof WatsonIncidentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonIncident.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonIncident);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonIncident proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonIncident implementation " +
					watsonIncident.getClass());
		}

		WatsonIncidentModelImpl watsonIncidentModelImpl =
			(WatsonIncidentModelImpl)watsonIncident;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonIncident.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonIncident.setCreateDate(now);
			}
			else {
				watsonIncident.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonIncidentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonIncident.setModifiedDate(now);
			}
			else {
				watsonIncident.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonIncident);

				watsonIncident.setNew(false);
			}
			else {
				watsonIncident = (WatsonIncident)session.merge(watsonIncident);
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
			WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentImpl.class, watsonIncident.getPrimaryKey(),
			watsonIncident, false);

		watsonIncident.resetOriginalValues();

		return watsonIncident;
	}

	/**
	 * Returns the watson incident with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident
	 * @return the watson incident
	 * @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	 */
	@Override
	public WatsonIncident findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIncidentException {

		WatsonIncident watsonIncident = fetchByPrimaryKey(primaryKey);

		if (watsonIncident == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIncidentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonIncident;
	}

	/**
	 * Returns the watson incident with the primary key or throws a <code>NoSuchIncidentException</code> if it could not be found.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident
	 * @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	 */
	@Override
	public WatsonIncident findByPrimaryKey(long watsonIncidentId)
		throws NoSuchIncidentException {

		return findByPrimaryKey((Serializable)watsonIncidentId);
	}

	/**
	 * Returns the watson incident with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident
	 * @return the watson incident, or <code>null</code> if a watson incident with the primary key could not be found
	 */
	@Override
	public WatsonIncident fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonIncident watsonIncident = (WatsonIncident)serializable;

		if (watsonIncident == null) {
			Session session = null;

			try {
				session = openSession();

				watsonIncident = (WatsonIncident)session.get(
					WatsonIncidentImpl.class, primaryKey);

				if (watsonIncident != null) {
					cacheResult(watsonIncident);
				}
				else {
					entityCache.putResult(
						WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
						WatsonIncidentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonIncident;
	}

	/**
	 * Returns the watson incident with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident, or <code>null</code> if a watson incident with the primary key could not be found
	 */
	@Override
	public WatsonIncident fetchByPrimaryKey(long watsonIncidentId) {
		return fetchByPrimaryKey((Serializable)watsonIncidentId);
	}

	@Override
	public Map<Serializable, WatsonIncident> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonIncident> map =
			new HashMap<Serializable, WatsonIncident>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonIncident watsonIncident = fetchByPrimaryKey(primaryKey);

			if (watsonIncident != null) {
				map.put(primaryKey, watsonIncident);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonIncident)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONINCIDENT_WHERE_PKS_IN);

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

			for (WatsonIncident watsonIncident :
					(List<WatsonIncident>)query.list()) {

				map.put(watsonIncident.getPrimaryKeyObj(), watsonIncident);

				cacheResult(watsonIncident);

				uncachedPrimaryKeys.remove(watsonIncident.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson incidents.
	 *
	 * @return the watson incidents
	 */
	@Override
	public List<WatsonIncident> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson incidents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incidents
	 * @param end the upper bound of the range of watson incidents (not inclusive)
	 * @return the range of watson incidents
	 */
	@Override
	public List<WatsonIncident> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson incidents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incidents
	 * @param end the upper bound of the range of watson incidents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson incidents
	 */
	@Override
	public List<WatsonIncident> findAll(
		int start, int end,
		OrderByComparator<WatsonIncident> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson incidents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incidents
	 * @param end the upper bound of the range of watson incidents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson incidents
	 */
	@Override
	public List<WatsonIncident> findAll(
		int start, int end, OrderByComparator<WatsonIncident> orderByComparator,
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

		List<WatsonIncident> list = null;

		if (useFinderCache) {
			list = (List<WatsonIncident>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONINCIDENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONINCIDENT;

				sql = sql.concat(WatsonIncidentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonIncident>)QueryUtil.list(
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
	 * Removes all the watson incidents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonIncident watsonIncident : findAll()) {
			remove(watsonIncident);
		}
	}

	/**
	 * Returns the number of watson incidents.
	 *
	 * @return the number of watson incidents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONINCIDENT);

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
		return WatsonIncidentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson incident persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonIncidentModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonIncidentImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONINCIDENT =
		"SELECT watsonIncident FROM WatsonIncident watsonIncident";

	private static final String _SQL_SELECT_WATSONINCIDENT_WHERE_PKS_IN =
		"SELECT watsonIncident FROM WatsonIncident watsonIncident WHERE watsonIncidentId IN (";

	private static final String _SQL_COUNT_WATSONINCIDENT =
		"SELECT COUNT(watsonIncident) FROM WatsonIncident watsonIncident";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonIncident.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonIncident exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonIncidentPersistenceImpl.class);

}
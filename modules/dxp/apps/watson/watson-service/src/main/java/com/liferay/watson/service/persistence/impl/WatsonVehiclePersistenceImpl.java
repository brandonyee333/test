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
import com.liferay.watson.exception.NoSuchVehicleException;
import com.liferay.watson.model.WatsonVehicle;
import com.liferay.watson.model.impl.WatsonVehicleImpl;
import com.liferay.watson.model.impl.WatsonVehicleModelImpl;
import com.liferay.watson.service.persistence.WatsonVehiclePersistence;

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
 * The persistence implementation for the watson vehicle service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonVehiclePersistenceImpl
	extends BasePersistenceImpl<WatsonVehicle>
	implements WatsonVehiclePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonVehicleUtil</code> to access the watson vehicle persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonVehicleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonVehiclePersistenceImpl() {
		setModelClass(WatsonVehicle.class);
	}

	/**
	 * Caches the watson vehicle in the entity cache if it is enabled.
	 *
	 * @param watsonVehicle the watson vehicle
	 */
	@Override
	public void cacheResult(WatsonVehicle watsonVehicle) {
		entityCache.putResult(
			WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleImpl.class, watsonVehicle.getPrimaryKey(),
			watsonVehicle);

		watsonVehicle.resetOriginalValues();
	}

	/**
	 * Caches the watson vehicles in the entity cache if it is enabled.
	 *
	 * @param watsonVehicles the watson vehicles
	 */
	@Override
	public void cacheResult(List<WatsonVehicle> watsonVehicles) {
		for (WatsonVehicle watsonVehicle : watsonVehicles) {
			if (entityCache.getResult(
					WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
					WatsonVehicleImpl.class, watsonVehicle.getPrimaryKey()) ==
						null) {

				cacheResult(watsonVehicle);
			}
			else {
				watsonVehicle.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson vehicles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonVehicleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson vehicle.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonVehicle watsonVehicle) {
		entityCache.removeResult(
			WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleImpl.class, watsonVehicle.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonVehicle> watsonVehicles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonVehicle watsonVehicle : watsonVehicles) {
			entityCache.removeResult(
				WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
				WatsonVehicleImpl.class, watsonVehicle.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
				WatsonVehicleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson vehicle with the primary key. Does not add the watson vehicle to the database.
	 *
	 * @param watsonVehicleId the primary key for the new watson vehicle
	 * @return the new watson vehicle
	 */
	@Override
	public WatsonVehicle create(long watsonVehicleId) {
		WatsonVehicle watsonVehicle = new WatsonVehicleImpl();

		watsonVehicle.setNew(true);
		watsonVehicle.setPrimaryKey(watsonVehicleId);

		watsonVehicle.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonVehicle;
	}

	/**
	 * Removes the watson vehicle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleId the primary key of the watson vehicle
	 * @return the watson vehicle that was removed
	 * @throws NoSuchVehicleException if a watson vehicle with the primary key could not be found
	 */
	@Override
	public WatsonVehicle remove(long watsonVehicleId)
		throws NoSuchVehicleException {

		return remove((Serializable)watsonVehicleId);
	}

	/**
	 * Removes the watson vehicle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson vehicle
	 * @return the watson vehicle that was removed
	 * @throws NoSuchVehicleException if a watson vehicle with the primary key could not be found
	 */
	@Override
	public WatsonVehicle remove(Serializable primaryKey)
		throws NoSuchVehicleException {

		Session session = null;

		try {
			session = openSession();

			WatsonVehicle watsonVehicle = (WatsonVehicle)session.get(
				WatsonVehicleImpl.class, primaryKey);

			if (watsonVehicle == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVehicleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonVehicle);
		}
		catch (NoSuchVehicleException noSuchEntityException) {
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
	protected WatsonVehicle removeImpl(WatsonVehicle watsonVehicle) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonVehicle)) {
				watsonVehicle = (WatsonVehicle)session.get(
					WatsonVehicleImpl.class, watsonVehicle.getPrimaryKeyObj());
			}

			if (watsonVehicle != null) {
				session.delete(watsonVehicle);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonVehicle != null) {
			clearCache(watsonVehicle);
		}

		return watsonVehicle;
	}

	@Override
	public WatsonVehicle updateImpl(WatsonVehicle watsonVehicle) {
		boolean isNew = watsonVehicle.isNew();

		if (!(watsonVehicle instanceof WatsonVehicleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonVehicle.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonVehicle);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonVehicle proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonVehicle implementation " +
					watsonVehicle.getClass());
		}

		WatsonVehicleModelImpl watsonVehicleModelImpl =
			(WatsonVehicleModelImpl)watsonVehicle;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonVehicle.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonVehicle.setCreateDate(now);
			}
			else {
				watsonVehicle.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonVehicleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonVehicle.setModifiedDate(now);
			}
			else {
				watsonVehicle.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonVehicle);

				watsonVehicle.setNew(false);
			}
			else {
				watsonVehicle = (WatsonVehicle)session.merge(watsonVehicle);
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
			WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleImpl.class, watsonVehicle.getPrimaryKey(),
			watsonVehicle, false);

		watsonVehicle.resetOriginalValues();

		return watsonVehicle;
	}

	/**
	 * Returns the watson vehicle with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson vehicle
	 * @return the watson vehicle
	 * @throws NoSuchVehicleException if a watson vehicle with the primary key could not be found
	 */
	@Override
	public WatsonVehicle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVehicleException {

		WatsonVehicle watsonVehicle = fetchByPrimaryKey(primaryKey);

		if (watsonVehicle == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVehicleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonVehicle;
	}

	/**
	 * Returns the watson vehicle with the primary key or throws a <code>NoSuchVehicleException</code> if it could not be found.
	 *
	 * @param watsonVehicleId the primary key of the watson vehicle
	 * @return the watson vehicle
	 * @throws NoSuchVehicleException if a watson vehicle with the primary key could not be found
	 */
	@Override
	public WatsonVehicle findByPrimaryKey(long watsonVehicleId)
		throws NoSuchVehicleException {

		return findByPrimaryKey((Serializable)watsonVehicleId);
	}

	/**
	 * Returns the watson vehicle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson vehicle
	 * @return the watson vehicle, or <code>null</code> if a watson vehicle with the primary key could not be found
	 */
	@Override
	public WatsonVehicle fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonVehicle watsonVehicle = (WatsonVehicle)serializable;

		if (watsonVehicle == null) {
			Session session = null;

			try {
				session = openSession();

				watsonVehicle = (WatsonVehicle)session.get(
					WatsonVehicleImpl.class, primaryKey);

				if (watsonVehicle != null) {
					cacheResult(watsonVehicle);
				}
				else {
					entityCache.putResult(
						WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
						WatsonVehicleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
					WatsonVehicleImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonVehicle;
	}

	/**
	 * Returns the watson vehicle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonVehicleId the primary key of the watson vehicle
	 * @return the watson vehicle, or <code>null</code> if a watson vehicle with the primary key could not be found
	 */
	@Override
	public WatsonVehicle fetchByPrimaryKey(long watsonVehicleId) {
		return fetchByPrimaryKey((Serializable)watsonVehicleId);
	}

	@Override
	public Map<Serializable, WatsonVehicle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonVehicle> map =
			new HashMap<Serializable, WatsonVehicle>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonVehicle watsonVehicle = fetchByPrimaryKey(primaryKey);

			if (watsonVehicle != null) {
				map.put(primaryKey, watsonVehicle);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
				WatsonVehicleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonVehicle)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONVEHICLE_WHERE_PKS_IN);

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

			for (WatsonVehicle watsonVehicle :
					(List<WatsonVehicle>)query.list()) {

				map.put(watsonVehicle.getPrimaryKeyObj(), watsonVehicle);

				cacheResult(watsonVehicle);

				uncachedPrimaryKeys.remove(watsonVehicle.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
					WatsonVehicleImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson vehicles.
	 *
	 * @return the watson vehicles
	 */
	@Override
	public List<WatsonVehicle> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson vehicles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonVehicleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicles
	 * @param end the upper bound of the range of watson vehicles (not inclusive)
	 * @return the range of watson vehicles
	 */
	@Override
	public List<WatsonVehicle> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson vehicles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonVehicleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicles
	 * @param end the upper bound of the range of watson vehicles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson vehicles
	 */
	@Override
	public List<WatsonVehicle> findAll(
		int start, int end,
		OrderByComparator<WatsonVehicle> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson vehicles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonVehicleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicles
	 * @param end the upper bound of the range of watson vehicles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson vehicles
	 */
	@Override
	public List<WatsonVehicle> findAll(
		int start, int end, OrderByComparator<WatsonVehicle> orderByComparator,
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

		List<WatsonVehicle> list = null;

		if (useFinderCache) {
			list = (List<WatsonVehicle>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONVEHICLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONVEHICLE;

				sql = sql.concat(WatsonVehicleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonVehicle>)QueryUtil.list(
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
	 * Removes all the watson vehicles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonVehicle watsonVehicle : findAll()) {
			remove(watsonVehicle);
		}
	}

	/**
	 * Returns the number of watson vehicles.
	 *
	 * @return the number of watson vehicles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONVEHICLE);

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
		return WatsonVehicleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson vehicle persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleModelImpl.FINDER_CACHE_ENABLED,
			WatsonVehicleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleModelImpl.FINDER_CACHE_ENABLED,
			WatsonVehicleImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonVehicleModelImpl.ENTITY_CACHE_ENABLED,
			WatsonVehicleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonVehicleImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONVEHICLE =
		"SELECT watsonVehicle FROM WatsonVehicle watsonVehicle";

	private static final String _SQL_SELECT_WATSONVEHICLE_WHERE_PKS_IN =
		"SELECT watsonVehicle FROM WatsonVehicle watsonVehicle WHERE watsonVehicleId IN (";

	private static final String _SQL_COUNT_WATSONVEHICLE =
		"SELECT COUNT(watsonVehicle) FROM WatsonVehicle watsonVehicle";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonVehicle.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonVehicle exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonVehiclePersistenceImpl.class);

}
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
import com.liferay.watson.exception.NoSuchActivityException;
import com.liferay.watson.model.WatsonActivity;
import com.liferay.watson.model.impl.WatsonActivityImpl;
import com.liferay.watson.model.impl.WatsonActivityModelImpl;
import com.liferay.watson.service.persistence.WatsonActivityPersistence;

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
 * The persistence implementation for the watson activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonActivityPersistenceImpl
	extends BasePersistenceImpl<WatsonActivity>
	implements WatsonActivityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonActivityUtil</code> to access the watson activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonActivityImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonActivityPersistenceImpl() {
		setModelClass(WatsonActivity.class);
	}

	/**
	 * Caches the watson activity in the entity cache if it is enabled.
	 *
	 * @param watsonActivity the watson activity
	 */
	@Override
	public void cacheResult(WatsonActivity watsonActivity) {
		entityCache.putResult(
			WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityImpl.class, watsonActivity.getPrimaryKey(),
			watsonActivity);

		watsonActivity.resetOriginalValues();
	}

	/**
	 * Caches the watson activities in the entity cache if it is enabled.
	 *
	 * @param watsonActivities the watson activities
	 */
	@Override
	public void cacheResult(List<WatsonActivity> watsonActivities) {
		for (WatsonActivity watsonActivity : watsonActivities) {
			if (entityCache.getResult(
					WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityImpl.class, watsonActivity.getPrimaryKey()) ==
						null) {

				cacheResult(watsonActivity);
			}
			else {
				watsonActivity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson activities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonActivityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson activity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonActivity watsonActivity) {
		entityCache.removeResult(
			WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityImpl.class, watsonActivity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonActivity> watsonActivities) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonActivity watsonActivity : watsonActivities) {
			entityCache.removeResult(
				WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityImpl.class, watsonActivity.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson activity with the primary key. Does not add the watson activity to the database.
	 *
	 * @param watsonActivityId the primary key for the new watson activity
	 * @return the new watson activity
	 */
	@Override
	public WatsonActivity create(long watsonActivityId) {
		WatsonActivity watsonActivity = new WatsonActivityImpl();

		watsonActivity.setNew(true);
		watsonActivity.setPrimaryKey(watsonActivityId);

		watsonActivity.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonActivity;
	}

	/**
	 * Removes the watson activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity that was removed
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity remove(long watsonActivityId)
		throws NoSuchActivityException {

		return remove((Serializable)watsonActivityId);
	}

	/**
	 * Removes the watson activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson activity
	 * @return the watson activity that was removed
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity remove(Serializable primaryKey)
		throws NoSuchActivityException {

		Session session = null;

		try {
			session = openSession();

			WatsonActivity watsonActivity = (WatsonActivity)session.get(
				WatsonActivityImpl.class, primaryKey);

			if (watsonActivity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonActivity);
		}
		catch (NoSuchActivityException noSuchEntityException) {
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
	protected WatsonActivity removeImpl(WatsonActivity watsonActivity) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonActivity)) {
				watsonActivity = (WatsonActivity)session.get(
					WatsonActivityImpl.class,
					watsonActivity.getPrimaryKeyObj());
			}

			if (watsonActivity != null) {
				session.delete(watsonActivity);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonActivity != null) {
			clearCache(watsonActivity);
		}

		return watsonActivity;
	}

	@Override
	public WatsonActivity updateImpl(WatsonActivity watsonActivity) {
		boolean isNew = watsonActivity.isNew();

		if (!(watsonActivity instanceof WatsonActivityModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonActivity.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonActivity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonActivity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonActivity implementation " +
					watsonActivity.getClass());
		}

		WatsonActivityModelImpl watsonActivityModelImpl =
			(WatsonActivityModelImpl)watsonActivity;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonActivity.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonActivity.setCreateDate(now);
			}
			else {
				watsonActivity.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonActivityModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonActivity.setModifiedDate(now);
			}
			else {
				watsonActivity.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonActivity);

				watsonActivity.setNew(false);
			}
			else {
				watsonActivity = (WatsonActivity)session.merge(watsonActivity);
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
			WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityImpl.class, watsonActivity.getPrimaryKey(),
			watsonActivity, false);

		watsonActivity.resetOriginalValues();

		return watsonActivity;
	}

	/**
	 * Returns the watson activity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson activity
	 * @return the watson activity
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActivityException {

		WatsonActivity watsonActivity = fetchByPrimaryKey(primaryKey);

		if (watsonActivity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActivityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonActivity;
	}

	/**
	 * Returns the watson activity with the primary key or throws a <code>NoSuchActivityException</code> if it could not be found.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity
	 * @throws NoSuchActivityException if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity findByPrimaryKey(long watsonActivityId)
		throws NoSuchActivityException {

		return findByPrimaryKey((Serializable)watsonActivityId);
	}

	/**
	 * Returns the watson activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson activity
	 * @return the watson activity, or <code>null</code> if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonActivity watsonActivity = (WatsonActivity)serializable;

		if (watsonActivity == null) {
			Session session = null;

			try {
				session = openSession();

				watsonActivity = (WatsonActivity)session.get(
					WatsonActivityImpl.class, primaryKey);

				if (watsonActivity != null) {
					cacheResult(watsonActivity);
				}
				else {
					entityCache.putResult(
						WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
						WatsonActivityImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonActivity;
	}

	/**
	 * Returns the watson activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonActivityId the primary key of the watson activity
	 * @return the watson activity, or <code>null</code> if a watson activity with the primary key could not be found
	 */
	@Override
	public WatsonActivity fetchByPrimaryKey(long watsonActivityId) {
		return fetchByPrimaryKey((Serializable)watsonActivityId);
	}

	@Override
	public Map<Serializable, WatsonActivity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonActivity> map =
			new HashMap<Serializable, WatsonActivity>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonActivity watsonActivity = fetchByPrimaryKey(primaryKey);

			if (watsonActivity != null) {
				map.put(primaryKey, watsonActivity);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
				WatsonActivityImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonActivity)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONACTIVITY_WHERE_PKS_IN);

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

			for (WatsonActivity watsonActivity :
					(List<WatsonActivity>)query.list()) {

				map.put(watsonActivity.getPrimaryKeyObj(), watsonActivity);

				cacheResult(watsonActivity);

				uncachedPrimaryKeys.remove(watsonActivity.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
					WatsonActivityImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson activities.
	 *
	 * @return the watson activities
	 */
	@Override
	public List<WatsonActivity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activities
	 * @param end the upper bound of the range of watson activities (not inclusive)
	 * @return the range of watson activities
	 */
	@Override
	public List<WatsonActivity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activities
	 * @param end the upper bound of the range of watson activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson activities
	 */
	@Override
	public List<WatsonActivity> findAll(
		int start, int end,
		OrderByComparator<WatsonActivity> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activities
	 * @param end the upper bound of the range of watson activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson activities
	 */
	@Override
	public List<WatsonActivity> findAll(
		int start, int end, OrderByComparator<WatsonActivity> orderByComparator,
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

		List<WatsonActivity> list = null;

		if (useFinderCache) {
			list = (List<WatsonActivity>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONACTIVITY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONACTIVITY;

				sql = sql.concat(WatsonActivityModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonActivity>)QueryUtil.list(
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
	 * Removes all the watson activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonActivity watsonActivity : findAll()) {
			remove(watsonActivity);
		}
	}

	/**
	 * Returns the number of watson activities.
	 *
	 * @return the number of watson activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONACTIVITY);

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
		return WatsonActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson activity persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityModelImpl.FINDER_CACHE_ENABLED,
			WatsonActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityModelImpl.FINDER_CACHE_ENABLED,
			WatsonActivityImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonActivityModelImpl.ENTITY_CACHE_ENABLED,
			WatsonActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonActivityImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONACTIVITY =
		"SELECT watsonActivity FROM WatsonActivity watsonActivity";

	private static final String _SQL_SELECT_WATSONACTIVITY_WHERE_PKS_IN =
		"SELECT watsonActivity FROM WatsonActivity watsonActivity WHERE watsonActivityId IN (";

	private static final String _SQL_COUNT_WATSONACTIVITY =
		"SELECT COUNT(watsonActivity) FROM WatsonActivity watsonActivity";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonActivity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonActivity exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonActivityPersistenceImpl.class);

}
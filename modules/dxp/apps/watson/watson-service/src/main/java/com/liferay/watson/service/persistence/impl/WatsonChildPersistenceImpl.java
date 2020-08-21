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
import com.liferay.watson.exception.NoSuchChildException;
import com.liferay.watson.model.WatsonChild;
import com.liferay.watson.model.impl.WatsonChildImpl;
import com.liferay.watson.model.impl.WatsonChildModelImpl;
import com.liferay.watson.service.persistence.WatsonChildPersistence;

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
 * The persistence implementation for the watson child service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonChildPersistenceImpl
	extends BasePersistenceImpl<WatsonChild> implements WatsonChildPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonChildUtil</code> to access the watson child persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonChildImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonChildPersistenceImpl() {
		setModelClass(WatsonChild.class);
	}

	/**
	 * Caches the watson child in the entity cache if it is enabled.
	 *
	 * @param watsonChild the watson child
	 */
	@Override
	public void cacheResult(WatsonChild watsonChild) {
		entityCache.putResult(
			WatsonChildModelImpl.ENTITY_CACHE_ENABLED, WatsonChildImpl.class,
			watsonChild.getPrimaryKey(), watsonChild);

		watsonChild.resetOriginalValues();
	}

	/**
	 * Caches the watson childs in the entity cache if it is enabled.
	 *
	 * @param watsonChilds the watson childs
	 */
	@Override
	public void cacheResult(List<WatsonChild> watsonChilds) {
		for (WatsonChild watsonChild : watsonChilds) {
			if (entityCache.getResult(
					WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
					WatsonChildImpl.class, watsonChild.getPrimaryKey()) ==
						null) {

				cacheResult(watsonChild);
			}
			else {
				watsonChild.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson childs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonChildImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson child.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonChild watsonChild) {
		entityCache.removeResult(
			WatsonChildModelImpl.ENTITY_CACHE_ENABLED, WatsonChildImpl.class,
			watsonChild.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonChild> watsonChilds) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonChild watsonChild : watsonChilds) {
			entityCache.removeResult(
				WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
				WatsonChildImpl.class, watsonChild.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
				WatsonChildImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson child with the primary key. Does not add the watson child to the database.
	 *
	 * @param watsonChildId the primary key for the new watson child
	 * @return the new watson child
	 */
	@Override
	public WatsonChild create(long watsonChildId) {
		WatsonChild watsonChild = new WatsonChildImpl();

		watsonChild.setNew(true);
		watsonChild.setPrimaryKey(watsonChildId);

		watsonChild.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonChild;
	}

	/**
	 * Removes the watson child with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child that was removed
	 * @throws NoSuchChildException if a watson child with the primary key could not be found
	 */
	@Override
	public WatsonChild remove(long watsonChildId) throws NoSuchChildException {
		return remove((Serializable)watsonChildId);
	}

	/**
	 * Removes the watson child with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson child
	 * @return the watson child that was removed
	 * @throws NoSuchChildException if a watson child with the primary key could not be found
	 */
	@Override
	public WatsonChild remove(Serializable primaryKey)
		throws NoSuchChildException {

		Session session = null;

		try {
			session = openSession();

			WatsonChild watsonChild = (WatsonChild)session.get(
				WatsonChildImpl.class, primaryKey);

			if (watsonChild == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChildException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonChild);
		}
		catch (NoSuchChildException noSuchEntityException) {
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
	protected WatsonChild removeImpl(WatsonChild watsonChild) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonChild)) {
				watsonChild = (WatsonChild)session.get(
					WatsonChildImpl.class, watsonChild.getPrimaryKeyObj());
			}

			if (watsonChild != null) {
				session.delete(watsonChild);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonChild != null) {
			clearCache(watsonChild);
		}

		return watsonChild;
	}

	@Override
	public WatsonChild updateImpl(WatsonChild watsonChild) {
		boolean isNew = watsonChild.isNew();

		if (!(watsonChild instanceof WatsonChildModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonChild.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(watsonChild);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonChild proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonChild implementation " +
					watsonChild.getClass());
		}

		WatsonChildModelImpl watsonChildModelImpl =
			(WatsonChildModelImpl)watsonChild;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonChild.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonChild.setCreateDate(now);
			}
			else {
				watsonChild.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonChildModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonChild.setModifiedDate(now);
			}
			else {
				watsonChild.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonChild);

				watsonChild.setNew(false);
			}
			else {
				watsonChild = (WatsonChild)session.merge(watsonChild);
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
			WatsonChildModelImpl.ENTITY_CACHE_ENABLED, WatsonChildImpl.class,
			watsonChild.getPrimaryKey(), watsonChild, false);

		watsonChild.resetOriginalValues();

		return watsonChild;
	}

	/**
	 * Returns the watson child with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson child
	 * @return the watson child
	 * @throws NoSuchChildException if a watson child with the primary key could not be found
	 */
	@Override
	public WatsonChild findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChildException {

		WatsonChild watsonChild = fetchByPrimaryKey(primaryKey);

		if (watsonChild == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChildException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonChild;
	}

	/**
	 * Returns the watson child with the primary key or throws a <code>NoSuchChildException</code> if it could not be found.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child
	 * @throws NoSuchChildException if a watson child with the primary key could not be found
	 */
	@Override
	public WatsonChild findByPrimaryKey(long watsonChildId)
		throws NoSuchChildException {

		return findByPrimaryKey((Serializable)watsonChildId);
	}

	/**
	 * Returns the watson child with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson child
	 * @return the watson child, or <code>null</code> if a watson child with the primary key could not be found
	 */
	@Override
	public WatsonChild fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonChildModelImpl.ENTITY_CACHE_ENABLED, WatsonChildImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonChild watsonChild = (WatsonChild)serializable;

		if (watsonChild == null) {
			Session session = null;

			try {
				session = openSession();

				watsonChild = (WatsonChild)session.get(
					WatsonChildImpl.class, primaryKey);

				if (watsonChild != null) {
					cacheResult(watsonChild);
				}
				else {
					entityCache.putResult(
						WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
						WatsonChildImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
					WatsonChildImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonChild;
	}

	/**
	 * Returns the watson child with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child, or <code>null</code> if a watson child with the primary key could not be found
	 */
	@Override
	public WatsonChild fetchByPrimaryKey(long watsonChildId) {
		return fetchByPrimaryKey((Serializable)watsonChildId);
	}

	@Override
	public Map<Serializable, WatsonChild> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonChild> map =
			new HashMap<Serializable, WatsonChild>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonChild watsonChild = fetchByPrimaryKey(primaryKey);

			if (watsonChild != null) {
				map.put(primaryKey, watsonChild);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
				WatsonChildImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonChild)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONCHILD_WHERE_PKS_IN);

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

			for (WatsonChild watsonChild : (List<WatsonChild>)query.list()) {
				map.put(watsonChild.getPrimaryKeyObj(), watsonChild);

				cacheResult(watsonChild);

				uncachedPrimaryKeys.remove(watsonChild.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
					WatsonChildImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson childs.
	 *
	 * @return the watson childs
	 */
	@Override
	public List<WatsonChild> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson childs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson childs
	 * @param end the upper bound of the range of watson childs (not inclusive)
	 * @return the range of watson childs
	 */
	@Override
	public List<WatsonChild> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson childs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson childs
	 * @param end the upper bound of the range of watson childs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson childs
	 */
	@Override
	public List<WatsonChild> findAll(
		int start, int end, OrderByComparator<WatsonChild> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson childs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson childs
	 * @param end the upper bound of the range of watson childs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson childs
	 */
	@Override
	public List<WatsonChild> findAll(
		int start, int end, OrderByComparator<WatsonChild> orderByComparator,
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

		List<WatsonChild> list = null;

		if (useFinderCache) {
			list = (List<WatsonChild>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONCHILD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONCHILD;

				sql = sql.concat(WatsonChildModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonChild>)QueryUtil.list(
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
	 * Removes all the watson childs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonChild watsonChild : findAll()) {
			remove(watsonChild);
		}
	}

	/**
	 * Returns the number of watson childs.
	 *
	 * @return the number of watson childs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONCHILD);

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
		return WatsonChildModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson child persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildModelImpl.FINDER_CACHE_ENABLED, WatsonChildImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildModelImpl.FINDER_CACHE_ENABLED, WatsonChildImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonChildModelImpl.ENTITY_CACHE_ENABLED,
			WatsonChildModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonChildImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONCHILD =
		"SELECT watsonChild FROM WatsonChild watsonChild";

	private static final String _SQL_SELECT_WATSONCHILD_WHERE_PKS_IN =
		"SELECT watsonChild FROM WatsonChild watsonChild WHERE watsonChildId IN (";

	private static final String _SQL_COUNT_WATSONCHILD =
		"SELECT COUNT(watsonChild) FROM WatsonChild watsonChild";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonChild.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonChild exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonChildPersistenceImpl.class);

}
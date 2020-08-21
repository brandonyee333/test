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
import com.liferay.watson.exception.NoSuchResourceException;
import com.liferay.watson.model.WatsonResource;
import com.liferay.watson.model.impl.WatsonResourceImpl;
import com.liferay.watson.model.impl.WatsonResourceModelImpl;
import com.liferay.watson.service.persistence.WatsonResourcePersistence;

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
 * The persistence implementation for the watson resource service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonResourcePersistenceImpl
	extends BasePersistenceImpl<WatsonResource>
	implements WatsonResourcePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonResourceUtil</code> to access the watson resource persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonResourceImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonResourcePersistenceImpl() {
		setModelClass(WatsonResource.class);
	}

	/**
	 * Caches the watson resource in the entity cache if it is enabled.
	 *
	 * @param watsonResource the watson resource
	 */
	@Override
	public void cacheResult(WatsonResource watsonResource) {
		entityCache.putResult(
			WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceImpl.class, watsonResource.getPrimaryKey(),
			watsonResource);

		watsonResource.resetOriginalValues();
	}

	/**
	 * Caches the watson resources in the entity cache if it is enabled.
	 *
	 * @param watsonResources the watson resources
	 */
	@Override
	public void cacheResult(List<WatsonResource> watsonResources) {
		for (WatsonResource watsonResource : watsonResources) {
			if (entityCache.getResult(
					WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
					WatsonResourceImpl.class, watsonResource.getPrimaryKey()) ==
						null) {

				cacheResult(watsonResource);
			}
			else {
				watsonResource.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson resources.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonResourceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson resource.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonResource watsonResource) {
		entityCache.removeResult(
			WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceImpl.class, watsonResource.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonResource> watsonResources) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonResource watsonResource : watsonResources) {
			entityCache.removeResult(
				WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
				WatsonResourceImpl.class, watsonResource.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
				WatsonResourceImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson resource with the primary key. Does not add the watson resource to the database.
	 *
	 * @param watsonResourceId the primary key for the new watson resource
	 * @return the new watson resource
	 */
	@Override
	public WatsonResource create(long watsonResourceId) {
		WatsonResource watsonResource = new WatsonResourceImpl();

		watsonResource.setNew(true);
		watsonResource.setPrimaryKey(watsonResourceId);

		watsonResource.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonResource;
	}

	/**
	 * Removes the watson resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonResourceId the primary key of the watson resource
	 * @return the watson resource that was removed
	 * @throws NoSuchResourceException if a watson resource with the primary key could not be found
	 */
	@Override
	public WatsonResource remove(long watsonResourceId)
		throws NoSuchResourceException {

		return remove((Serializable)watsonResourceId);
	}

	/**
	 * Removes the watson resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson resource
	 * @return the watson resource that was removed
	 * @throws NoSuchResourceException if a watson resource with the primary key could not be found
	 */
	@Override
	public WatsonResource remove(Serializable primaryKey)
		throws NoSuchResourceException {

		Session session = null;

		try {
			session = openSession();

			WatsonResource watsonResource = (WatsonResource)session.get(
				WatsonResourceImpl.class, primaryKey);

			if (watsonResource == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResourceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonResource);
		}
		catch (NoSuchResourceException noSuchEntityException) {
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
	protected WatsonResource removeImpl(WatsonResource watsonResource) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonResource)) {
				watsonResource = (WatsonResource)session.get(
					WatsonResourceImpl.class,
					watsonResource.getPrimaryKeyObj());
			}

			if (watsonResource != null) {
				session.delete(watsonResource);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonResource != null) {
			clearCache(watsonResource);
		}

		return watsonResource;
	}

	@Override
	public WatsonResource updateImpl(WatsonResource watsonResource) {
		boolean isNew = watsonResource.isNew();

		if (!(watsonResource instanceof WatsonResourceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonResource.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonResource);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonResource proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonResource implementation " +
					watsonResource.getClass());
		}

		WatsonResourceModelImpl watsonResourceModelImpl =
			(WatsonResourceModelImpl)watsonResource;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonResource.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonResource.setCreateDate(now);
			}
			else {
				watsonResource.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonResourceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonResource.setModifiedDate(now);
			}
			else {
				watsonResource.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonResource);

				watsonResource.setNew(false);
			}
			else {
				watsonResource = (WatsonResource)session.merge(watsonResource);
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
			WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceImpl.class, watsonResource.getPrimaryKey(),
			watsonResource, false);

		watsonResource.resetOriginalValues();

		return watsonResource;
	}

	/**
	 * Returns the watson resource with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson resource
	 * @return the watson resource
	 * @throws NoSuchResourceException if a watson resource with the primary key could not be found
	 */
	@Override
	public WatsonResource findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResourceException {

		WatsonResource watsonResource = fetchByPrimaryKey(primaryKey);

		if (watsonResource == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResourceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonResource;
	}

	/**
	 * Returns the watson resource with the primary key or throws a <code>NoSuchResourceException</code> if it could not be found.
	 *
	 * @param watsonResourceId the primary key of the watson resource
	 * @return the watson resource
	 * @throws NoSuchResourceException if a watson resource with the primary key could not be found
	 */
	@Override
	public WatsonResource findByPrimaryKey(long watsonResourceId)
		throws NoSuchResourceException {

		return findByPrimaryKey((Serializable)watsonResourceId);
	}

	/**
	 * Returns the watson resource with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson resource
	 * @return the watson resource, or <code>null</code> if a watson resource with the primary key could not be found
	 */
	@Override
	public WatsonResource fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonResource watsonResource = (WatsonResource)serializable;

		if (watsonResource == null) {
			Session session = null;

			try {
				session = openSession();

				watsonResource = (WatsonResource)session.get(
					WatsonResourceImpl.class, primaryKey);

				if (watsonResource != null) {
					cacheResult(watsonResource);
				}
				else {
					entityCache.putResult(
						WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
						WatsonResourceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
					WatsonResourceImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonResource;
	}

	/**
	 * Returns the watson resource with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonResourceId the primary key of the watson resource
	 * @return the watson resource, or <code>null</code> if a watson resource with the primary key could not be found
	 */
	@Override
	public WatsonResource fetchByPrimaryKey(long watsonResourceId) {
		return fetchByPrimaryKey((Serializable)watsonResourceId);
	}

	@Override
	public Map<Serializable, WatsonResource> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonResource> map =
			new HashMap<Serializable, WatsonResource>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonResource watsonResource = fetchByPrimaryKey(primaryKey);

			if (watsonResource != null) {
				map.put(primaryKey, watsonResource);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
				WatsonResourceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonResource)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONRESOURCE_WHERE_PKS_IN);

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

			for (WatsonResource watsonResource :
					(List<WatsonResource>)query.list()) {

				map.put(watsonResource.getPrimaryKeyObj(), watsonResource);

				cacheResult(watsonResource);

				uncachedPrimaryKeys.remove(watsonResource.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
					WatsonResourceImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson resources.
	 *
	 * @return the watson resources
	 */
	@Override
	public List<WatsonResource> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonResourceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson resources
	 * @param end the upper bound of the range of watson resources (not inclusive)
	 * @return the range of watson resources
	 */
	@Override
	public List<WatsonResource> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonResourceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson resources
	 * @param end the upper bound of the range of watson resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson resources
	 */
	@Override
	public List<WatsonResource> findAll(
		int start, int end,
		OrderByComparator<WatsonResource> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonResourceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson resources
	 * @param end the upper bound of the range of watson resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson resources
	 */
	@Override
	public List<WatsonResource> findAll(
		int start, int end, OrderByComparator<WatsonResource> orderByComparator,
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

		List<WatsonResource> list = null;

		if (useFinderCache) {
			list = (List<WatsonResource>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONRESOURCE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONRESOURCE;

				sql = sql.concat(WatsonResourceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonResource>)QueryUtil.list(
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
	 * Removes all the watson resources from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonResource watsonResource : findAll()) {
			remove(watsonResource);
		}
	}

	/**
	 * Returns the number of watson resources.
	 *
	 * @return the number of watson resources
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONRESOURCE);

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
		return WatsonResourceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson resource persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceModelImpl.FINDER_CACHE_ENABLED,
			WatsonResourceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceModelImpl.FINDER_CACHE_ENABLED,
			WatsonResourceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonResourceModelImpl.ENTITY_CACHE_ENABLED,
			WatsonResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonResourceImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONRESOURCE =
		"SELECT watsonResource FROM WatsonResource watsonResource";

	private static final String _SQL_SELECT_WATSONRESOURCE_WHERE_PKS_IN =
		"SELECT watsonResource FROM WatsonResource watsonResource WHERE watsonResourceId IN (";

	private static final String _SQL_COUNT_WATSONRESOURCE =
		"SELECT COUNT(watsonResource) FROM WatsonResource watsonResource";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonResource.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonResource exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonResourcePersistenceImpl.class);

}
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
import com.liferay.watson.exception.NoSuchRelationshipException;
import com.liferay.watson.model.WatsonRelationship;
import com.liferay.watson.model.impl.WatsonRelationshipImpl;
import com.liferay.watson.model.impl.WatsonRelationshipModelImpl;
import com.liferay.watson.service.persistence.WatsonRelationshipPersistence;

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
 * The persistence implementation for the watson relationship service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonRelationshipPersistenceImpl
	extends BasePersistenceImpl<WatsonRelationship>
	implements WatsonRelationshipPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonRelationshipUtil</code> to access the watson relationship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonRelationshipImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonRelationshipPersistenceImpl() {
		setModelClass(WatsonRelationship.class);
	}

	/**
	 * Caches the watson relationship in the entity cache if it is enabled.
	 *
	 * @param watsonRelationship the watson relationship
	 */
	@Override
	public void cacheResult(WatsonRelationship watsonRelationship) {
		entityCache.putResult(
			WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipImpl.class, watsonRelationship.getPrimaryKey(),
			watsonRelationship);

		watsonRelationship.resetOriginalValues();
	}

	/**
	 * Caches the watson relationships in the entity cache if it is enabled.
	 *
	 * @param watsonRelationships the watson relationships
	 */
	@Override
	public void cacheResult(List<WatsonRelationship> watsonRelationships) {
		for (WatsonRelationship watsonRelationship : watsonRelationships) {
			if (entityCache.getResult(
					WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipImpl.class,
					watsonRelationship.getPrimaryKey()) == null) {

				cacheResult(watsonRelationship);
			}
			else {
				watsonRelationship.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson relationships.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonRelationshipImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson relationship.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonRelationship watsonRelationship) {
		entityCache.removeResult(
			WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipImpl.class, watsonRelationship.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonRelationship> watsonRelationships) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonRelationship watsonRelationship : watsonRelationships) {
			entityCache.removeResult(
				WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipImpl.class,
				watsonRelationship.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson relationship with the primary key. Does not add the watson relationship to the database.
	 *
	 * @param watsonRelationshipId the primary key for the new watson relationship
	 * @return the new watson relationship
	 */
	@Override
	public WatsonRelationship create(long watsonRelationshipId) {
		WatsonRelationship watsonRelationship = new WatsonRelationshipImpl();

		watsonRelationship.setNew(true);
		watsonRelationship.setPrimaryKey(watsonRelationshipId);

		watsonRelationship.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonRelationship;
	}

	/**
	 * Removes the watson relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipId the primary key of the watson relationship
	 * @return the watson relationship that was removed
	 * @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	 */
	@Override
	public WatsonRelationship remove(long watsonRelationshipId)
		throws NoSuchRelationshipException {

		return remove((Serializable)watsonRelationshipId);
	}

	/**
	 * Removes the watson relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson relationship
	 * @return the watson relationship that was removed
	 * @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	 */
	@Override
	public WatsonRelationship remove(Serializable primaryKey)
		throws NoSuchRelationshipException {

		Session session = null;

		try {
			session = openSession();

			WatsonRelationship watsonRelationship =
				(WatsonRelationship)session.get(
					WatsonRelationshipImpl.class, primaryKey);

			if (watsonRelationship == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRelationshipException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonRelationship);
		}
		catch (NoSuchRelationshipException noSuchEntityException) {
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
	protected WatsonRelationship removeImpl(
		WatsonRelationship watsonRelationship) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonRelationship)) {
				watsonRelationship = (WatsonRelationship)session.get(
					WatsonRelationshipImpl.class,
					watsonRelationship.getPrimaryKeyObj());
			}

			if (watsonRelationship != null) {
				session.delete(watsonRelationship);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonRelationship != null) {
			clearCache(watsonRelationship);
		}

		return watsonRelationship;
	}

	@Override
	public WatsonRelationship updateImpl(
		WatsonRelationship watsonRelationship) {

		boolean isNew = watsonRelationship.isNew();

		if (!(watsonRelationship instanceof WatsonRelationshipModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonRelationship.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonRelationship);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonRelationship proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonRelationship implementation " +
					watsonRelationship.getClass());
		}

		WatsonRelationshipModelImpl watsonRelationshipModelImpl =
			(WatsonRelationshipModelImpl)watsonRelationship;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonRelationship.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonRelationship.setCreateDate(now);
			}
			else {
				watsonRelationship.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonRelationshipModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonRelationship.setModifiedDate(now);
			}
			else {
				watsonRelationship.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonRelationship);

				watsonRelationship.setNew(false);
			}
			else {
				watsonRelationship = (WatsonRelationship)session.merge(
					watsonRelationship);
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
			WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipImpl.class, watsonRelationship.getPrimaryKey(),
			watsonRelationship, false);

		watsonRelationship.resetOriginalValues();

		return watsonRelationship;
	}

	/**
	 * Returns the watson relationship with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson relationship
	 * @return the watson relationship
	 * @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	 */
	@Override
	public WatsonRelationship findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRelationshipException {

		WatsonRelationship watsonRelationship = fetchByPrimaryKey(primaryKey);

		if (watsonRelationship == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRelationshipException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonRelationship;
	}

	/**
	 * Returns the watson relationship with the primary key or throws a <code>NoSuchRelationshipException</code> if it could not be found.
	 *
	 * @param watsonRelationshipId the primary key of the watson relationship
	 * @return the watson relationship
	 * @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	 */
	@Override
	public WatsonRelationship findByPrimaryKey(long watsonRelationshipId)
		throws NoSuchRelationshipException {

		return findByPrimaryKey((Serializable)watsonRelationshipId);
	}

	/**
	 * Returns the watson relationship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson relationship
	 * @return the watson relationship, or <code>null</code> if a watson relationship with the primary key could not be found
	 */
	@Override
	public WatsonRelationship fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonRelationship watsonRelationship =
			(WatsonRelationship)serializable;

		if (watsonRelationship == null) {
			Session session = null;

			try {
				session = openSession();

				watsonRelationship = (WatsonRelationship)session.get(
					WatsonRelationshipImpl.class, primaryKey);

				if (watsonRelationship != null) {
					cacheResult(watsonRelationship);
				}
				else {
					entityCache.putResult(
						WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
						WatsonRelationshipImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonRelationship;
	}

	/**
	 * Returns the watson relationship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonRelationshipId the primary key of the watson relationship
	 * @return the watson relationship, or <code>null</code> if a watson relationship with the primary key could not be found
	 */
	@Override
	public WatsonRelationship fetchByPrimaryKey(long watsonRelationshipId) {
		return fetchByPrimaryKey((Serializable)watsonRelationshipId);
	}

	@Override
	public Map<Serializable, WatsonRelationship> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonRelationship> map =
			new HashMap<Serializable, WatsonRelationship>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonRelationship watsonRelationship = fetchByPrimaryKey(
				primaryKey);

			if (watsonRelationship != null) {
				map.put(primaryKey, watsonRelationship);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonRelationship)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONRELATIONSHIP_WHERE_PKS_IN);

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

			for (WatsonRelationship watsonRelationship :
					(List<WatsonRelationship>)query.list()) {

				map.put(
					watsonRelationship.getPrimaryKeyObj(), watsonRelationship);

				cacheResult(watsonRelationship);

				uncachedPrimaryKeys.remove(
					watsonRelationship.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson relationships.
	 *
	 * @return the watson relationships
	 */
	@Override
	public List<WatsonRelationship> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationships
	 * @param end the upper bound of the range of watson relationships (not inclusive)
	 * @return the range of watson relationships
	 */
	@Override
	public List<WatsonRelationship> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationships
	 * @param end the upper bound of the range of watson relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson relationships
	 */
	@Override
	public List<WatsonRelationship> findAll(
		int start, int end,
		OrderByComparator<WatsonRelationship> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationships
	 * @param end the upper bound of the range of watson relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson relationships
	 */
	@Override
	public List<WatsonRelationship> findAll(
		int start, int end,
		OrderByComparator<WatsonRelationship> orderByComparator,
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

		List<WatsonRelationship> list = null;

		if (useFinderCache) {
			list = (List<WatsonRelationship>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONRELATIONSHIP);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONRELATIONSHIP;

				sql = sql.concat(WatsonRelationshipModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonRelationship>)QueryUtil.list(
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
	 * Removes all the watson relationships from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonRelationship watsonRelationship : findAll()) {
			remove(watsonRelationship);
		}
	}

	/**
	 * Returns the number of watson relationships.
	 *
	 * @return the number of watson relationships
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
					_SQL_COUNT_WATSONRELATIONSHIP);

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
		return WatsonRelationshipModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson relationship persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipModelImpl.FINDER_CACHE_ENABLED,
			WatsonRelationshipImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipModelImpl.FINDER_CACHE_ENABLED,
			WatsonRelationshipImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonRelationshipImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONRELATIONSHIP =
		"SELECT watsonRelationship FROM WatsonRelationship watsonRelationship";

	private static final String _SQL_SELECT_WATSONRELATIONSHIP_WHERE_PKS_IN =
		"SELECT watsonRelationship FROM WatsonRelationship watsonRelationship WHERE watsonRelationshipId IN (";

	private static final String _SQL_COUNT_WATSONRELATIONSHIP =
		"SELECT COUNT(watsonRelationship) FROM WatsonRelationship watsonRelationship";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonRelationship.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonRelationship exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonRelationshipPersistenceImpl.class);

}
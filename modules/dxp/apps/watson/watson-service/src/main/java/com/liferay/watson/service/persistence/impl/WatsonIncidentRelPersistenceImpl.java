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
import com.liferay.watson.exception.NoSuchIncidentRelException;
import com.liferay.watson.model.WatsonIncidentRel;
import com.liferay.watson.model.impl.WatsonIncidentRelImpl;
import com.liferay.watson.model.impl.WatsonIncidentRelModelImpl;
import com.liferay.watson.service.persistence.WatsonIncidentRelPersistence;

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
 * The persistence implementation for the watson incident rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonIncidentRelPersistenceImpl
	extends BasePersistenceImpl<WatsonIncidentRel>
	implements WatsonIncidentRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonIncidentRelUtil</code> to access the watson incident rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonIncidentRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonIncidentRelPersistenceImpl() {
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

		setModelClass(WatsonIncidentRel.class);
	}

	/**
	 * Caches the watson incident rel in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 */
	@Override
	public void cacheResult(WatsonIncidentRel watsonIncidentRel) {
		entityCache.putResult(
			WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey(),
			watsonIncidentRel);

		watsonIncidentRel.resetOriginalValues();
	}

	/**
	 * Caches the watson incident rels in the entity cache if it is enabled.
	 *
	 * @param watsonIncidentRels the watson incident rels
	 */
	@Override
	public void cacheResult(List<WatsonIncidentRel> watsonIncidentRels) {
		for (WatsonIncidentRel watsonIncidentRel : watsonIncidentRels) {
			if (entityCache.getResult(
					WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelImpl.class,
					watsonIncidentRel.getPrimaryKey()) == null) {

				cacheResult(watsonIncidentRel);
			}
			else {
				watsonIncidentRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson incident rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonIncidentRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson incident rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonIncidentRel watsonIncidentRel) {
		entityCache.removeResult(
			WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonIncidentRel> watsonIncidentRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonIncidentRel watsonIncidentRel : watsonIncidentRels) {
			entityCache.removeResult(
				WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson incident rel with the primary key. Does not add the watson incident rel to the database.
	 *
	 * @param watsonIncidentRelId the primary key for the new watson incident rel
	 * @return the new watson incident rel
	 */
	@Override
	public WatsonIncidentRel create(long watsonIncidentRelId) {
		WatsonIncidentRel watsonIncidentRel = new WatsonIncidentRelImpl();

		watsonIncidentRel.setNew(true);
		watsonIncidentRel.setPrimaryKey(watsonIncidentRelId);

		watsonIncidentRel.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonIncidentRel;
	}

	/**
	 * Removes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel that was removed
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel remove(long watsonIncidentRelId)
		throws NoSuchIncidentRelException {

		return remove((Serializable)watsonIncidentRelId);
	}

	/**
	 * Removes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson incident rel
	 * @return the watson incident rel that was removed
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel remove(Serializable primaryKey)
		throws NoSuchIncidentRelException {

		Session session = null;

		try {
			session = openSession();

			WatsonIncidentRel watsonIncidentRel =
				(WatsonIncidentRel)session.get(
					WatsonIncidentRelImpl.class, primaryKey);

			if (watsonIncidentRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIncidentRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonIncidentRel);
		}
		catch (NoSuchIncidentRelException noSuchEntityException) {
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
	protected WatsonIncidentRel removeImpl(
		WatsonIncidentRel watsonIncidentRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonIncidentRel)) {
				watsonIncidentRel = (WatsonIncidentRel)session.get(
					WatsonIncidentRelImpl.class,
					watsonIncidentRel.getPrimaryKeyObj());
			}

			if (watsonIncidentRel != null) {
				session.delete(watsonIncidentRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonIncidentRel != null) {
			clearCache(watsonIncidentRel);
		}

		return watsonIncidentRel;
	}

	@Override
	public WatsonIncidentRel updateImpl(WatsonIncidentRel watsonIncidentRel) {
		boolean isNew = watsonIncidentRel.isNew();

		if (!(watsonIncidentRel instanceof WatsonIncidentRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonIncidentRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonIncidentRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonIncidentRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonIncidentRel implementation " +
					watsonIncidentRel.getClass());
		}

		WatsonIncidentRelModelImpl watsonIncidentRelModelImpl =
			(WatsonIncidentRelModelImpl)watsonIncidentRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonIncidentRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonIncidentRel.setCreateDate(now);
			}
			else {
				watsonIncidentRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonIncidentRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonIncidentRel.setModifiedDate(now);
			}
			else {
				watsonIncidentRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonIncidentRel.isNew()) {
				session.save(watsonIncidentRel);

				watsonIncidentRel.setNew(false);
			}
			else {
				watsonIncidentRel = (WatsonIncidentRel)session.merge(
					watsonIncidentRel);
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
			WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, watsonIncidentRel.getPrimaryKey(),
			watsonIncidentRel, false);

		watsonIncidentRel.resetOriginalValues();

		return watsonIncidentRel;
	}

	/**
	 * Returns the watson incident rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident rel
	 * @return the watson incident rel
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIncidentRelException {

		WatsonIncidentRel watsonIncidentRel = fetchByPrimaryKey(primaryKey);

		if (watsonIncidentRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIncidentRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonIncidentRel;
	}

	/**
	 * Returns the watson incident rel with the primary key or throws a <code>NoSuchIncidentRelException</code> if it could not be found.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel
	 * @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel findByPrimaryKey(long watsonIncidentRelId)
		throws NoSuchIncidentRelException {

		return findByPrimaryKey((Serializable)watsonIncidentRelId);
	}

	/**
	 * Returns the watson incident rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson incident rel
	 * @return the watson incident rel, or <code>null</code> if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonIncidentRel watsonIncidentRel = (WatsonIncidentRel)serializable;

		if (watsonIncidentRel == null) {
			Session session = null;

			try {
				session = openSession();

				watsonIncidentRel = (WatsonIncidentRel)session.get(
					WatsonIncidentRelImpl.class, primaryKey);

				if (watsonIncidentRel != null) {
					cacheResult(watsonIncidentRel);
				}
				else {
					entityCache.putResult(
						WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
						WatsonIncidentRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonIncidentRel;
	}

	/**
	 * Returns the watson incident rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel, or <code>null</code> if a watson incident rel with the primary key could not be found
	 */
	@Override
	public WatsonIncidentRel fetchByPrimaryKey(long watsonIncidentRelId) {
		return fetchByPrimaryKey((Serializable)watsonIncidentRelId);
	}

	@Override
	public Map<Serializable, WatsonIncidentRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonIncidentRel> map =
			new HashMap<Serializable, WatsonIncidentRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonIncidentRel watsonIncidentRel = fetchByPrimaryKey(primaryKey);

			if (watsonIncidentRel != null) {
				map.put(primaryKey, watsonIncidentRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonIncidentRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonIncidentRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONINCIDENTREL_WHERE_PKS_IN);

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

			for (WatsonIncidentRel watsonIncidentRel :
					(List<WatsonIncidentRel>)query.list()) {

				map.put(
					watsonIncidentRel.getPrimaryKeyObj(), watsonIncidentRel);

				cacheResult(watsonIncidentRel);

				uncachedPrimaryKeys.remove(
					watsonIncidentRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonIncidentRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson incident rels.
	 *
	 * @return the watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @return the range of watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonIncidentRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson incident rels
	 */
	@Override
	public List<WatsonIncidentRel> findAll(
		int start, int end,
		OrderByComparator<WatsonIncidentRel> orderByComparator,
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

		List<WatsonIncidentRel> list = null;

		if (useFinderCache) {
			list = (List<WatsonIncidentRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONINCIDENTREL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONINCIDENTREL;

				sql = sql.concat(WatsonIncidentRelModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonIncidentRel>)QueryUtil.list(
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
	 * Removes all the watson incident rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonIncidentRel watsonIncidentRel : findAll()) {
			remove(watsonIncidentRel);
		}
	}

	/**
	 * Returns the number of watson incident rels.
	 *
	 * @return the number of watson incident rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONINCIDENTREL);

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
		return WatsonIncidentRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson incident rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonIncidentRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonIncidentRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonIncidentRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonIncidentRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONINCIDENTREL =
		"SELECT watsonIncidentRel FROM WatsonIncidentRel watsonIncidentRel";

	private static final String _SQL_SELECT_WATSONINCIDENTREL_WHERE_PKS_IN =
		"SELECT watsonIncidentRel FROM WatsonIncidentRel watsonIncidentRel WHERE watsonIncidentRelId IN (";

	private static final String _SQL_COUNT_WATSONINCIDENTREL =
		"SELECT COUNT(watsonIncidentRel) FROM WatsonIncidentRel watsonIncidentRel";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonIncidentRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonIncidentRel exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonIncidentRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}
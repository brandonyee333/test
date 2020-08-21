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
import com.liferay.watson.exception.NoSuchListTypeRelException;
import com.liferay.watson.model.WatsonListTypeRel;
import com.liferay.watson.model.impl.WatsonListTypeRelImpl;
import com.liferay.watson.model.impl.WatsonListTypeRelModelImpl;
import com.liferay.watson.service.persistence.WatsonListTypeRelPersistence;

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
 * The persistence implementation for the watson list type rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonListTypeRelPersistenceImpl
	extends BasePersistenceImpl<WatsonListTypeRel>
	implements WatsonListTypeRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonListTypeRelUtil</code> to access the watson list type rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonListTypeRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonListTypeRelPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("primary", "primary_");
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

		setModelClass(WatsonListTypeRel.class);
	}

	/**
	 * Caches the watson list type rel in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRel the watson list type rel
	 */
	@Override
	public void cacheResult(WatsonListTypeRel watsonListTypeRel) {
		entityCache.putResult(
			WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey(),
			watsonListTypeRel);

		watsonListTypeRel.resetOriginalValues();
	}

	/**
	 * Caches the watson list type rels in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRels the watson list type rels
	 */
	@Override
	public void cacheResult(List<WatsonListTypeRel> watsonListTypeRels) {
		for (WatsonListTypeRel watsonListTypeRel : watsonListTypeRels) {
			if (entityCache.getResult(
					WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelImpl.class,
					watsonListTypeRel.getPrimaryKey()) == null) {

				cacheResult(watsonListTypeRel);
			}
			else {
				watsonListTypeRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson list type rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonListTypeRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson list type rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonListTypeRel watsonListTypeRel) {
		entityCache.removeResult(
			WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonListTypeRel> watsonListTypeRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonListTypeRel watsonListTypeRel : watsonListTypeRels) {
			entityCache.removeResult(
				WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson list type rel with the primary key. Does not add the watson list type rel to the database.
	 *
	 * @param watsonListTypeRelId the primary key for the new watson list type rel
	 * @return the new watson list type rel
	 */
	@Override
	public WatsonListTypeRel create(long watsonListTypeRelId) {
		WatsonListTypeRel watsonListTypeRel = new WatsonListTypeRelImpl();

		watsonListTypeRel.setNew(true);
		watsonListTypeRel.setPrimaryKey(watsonListTypeRelId);

		watsonListTypeRel.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonListTypeRel;
	}

	/**
	 * Removes the watson list type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel that was removed
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel remove(long watsonListTypeRelId)
		throws NoSuchListTypeRelException {

		return remove((Serializable)watsonListTypeRelId);
	}

	/**
	 * Removes the watson list type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson list type rel
	 * @return the watson list type rel that was removed
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel remove(Serializable primaryKey)
		throws NoSuchListTypeRelException {

		Session session = null;

		try {
			session = openSession();

			WatsonListTypeRel watsonListTypeRel =
				(WatsonListTypeRel)session.get(
					WatsonListTypeRelImpl.class, primaryKey);

			if (watsonListTypeRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchListTypeRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonListTypeRel);
		}
		catch (NoSuchListTypeRelException noSuchEntityException) {
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
	protected WatsonListTypeRel removeImpl(
		WatsonListTypeRel watsonListTypeRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonListTypeRel)) {
				watsonListTypeRel = (WatsonListTypeRel)session.get(
					WatsonListTypeRelImpl.class,
					watsonListTypeRel.getPrimaryKeyObj());
			}

			if (watsonListTypeRel != null) {
				session.delete(watsonListTypeRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonListTypeRel != null) {
			clearCache(watsonListTypeRel);
		}

		return watsonListTypeRel;
	}

	@Override
	public WatsonListTypeRel updateImpl(WatsonListTypeRel watsonListTypeRel) {
		boolean isNew = watsonListTypeRel.isNew();

		if (!(watsonListTypeRel instanceof WatsonListTypeRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonListTypeRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonListTypeRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonListTypeRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonListTypeRel implementation " +
					watsonListTypeRel.getClass());
		}

		WatsonListTypeRelModelImpl watsonListTypeRelModelImpl =
			(WatsonListTypeRelModelImpl)watsonListTypeRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonListTypeRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonListTypeRel.setCreateDate(now);
			}
			else {
				watsonListTypeRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!watsonListTypeRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonListTypeRel.setModifiedDate(now);
			}
			else {
				watsonListTypeRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonListTypeRel);

				watsonListTypeRel.setNew(false);
			}
			else {
				watsonListTypeRel = (WatsonListTypeRel)session.merge(
					watsonListTypeRel);
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
			WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, watsonListTypeRel.getPrimaryKey(),
			watsonListTypeRel, false);

		watsonListTypeRel.resetOriginalValues();

		return watsonListTypeRel;
	}

	/**
	 * Returns the watson list type rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type rel
	 * @return the watson list type rel
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchListTypeRelException {

		WatsonListTypeRel watsonListTypeRel = fetchByPrimaryKey(primaryKey);

		if (watsonListTypeRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchListTypeRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonListTypeRel;
	}

	/**
	 * Returns the watson list type rel with the primary key or throws a <code>NoSuchListTypeRelException</code> if it could not be found.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel
	 * @throws NoSuchListTypeRelException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel findByPrimaryKey(long watsonListTypeRelId)
		throws NoSuchListTypeRelException {

		return findByPrimaryKey((Serializable)watsonListTypeRelId);
	}

	/**
	 * Returns the watson list type rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type rel
	 * @return the watson list type rel, or <code>null</code> if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonListTypeRel watsonListTypeRel = (WatsonListTypeRel)serializable;

		if (watsonListTypeRel == null) {
			Session session = null;

			try {
				session = openSession();

				watsonListTypeRel = (WatsonListTypeRel)session.get(
					WatsonListTypeRelImpl.class, primaryKey);

				if (watsonListTypeRel != null) {
					cacheResult(watsonListTypeRel);
				}
				else {
					entityCache.putResult(
						WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonListTypeRel;
	}

	/**
	 * Returns the watson list type rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel, or <code>null</code> if a watson list type rel with the primary key could not be found
	 */
	@Override
	public WatsonListTypeRel fetchByPrimaryKey(long watsonListTypeRelId) {
		return fetchByPrimaryKey((Serializable)watsonListTypeRelId);
	}

	@Override
	public Map<Serializable, WatsonListTypeRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonListTypeRel> map =
			new HashMap<Serializable, WatsonListTypeRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonListTypeRel watsonListTypeRel = fetchByPrimaryKey(primaryKey);

			if (watsonListTypeRel != null) {
				map.put(primaryKey, watsonListTypeRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonListTypeRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONLISTTYPEREL_WHERE_PKS_IN);

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

			for (WatsonListTypeRel watsonListTypeRel :
					(List<WatsonListTypeRel>)query.list()) {

				map.put(
					watsonListTypeRel.getPrimaryKeyObj(), watsonListTypeRel);

				cacheResult(watsonListTypeRel);

				uncachedPrimaryKeys.remove(
					watsonListTypeRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson list type rels.
	 *
	 * @return the watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson list type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rels
	 * @param end the upper bound of the range of watson list type rels (not inclusive)
	 * @return the range of watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson list type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rels
	 * @param end the upper bound of the range of watson list type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll(
		int start, int end,
		OrderByComparator<WatsonListTypeRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson list type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rels
	 * @param end the upper bound of the range of watson list type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson list type rels
	 */
	@Override
	public List<WatsonListTypeRel> findAll(
		int start, int end,
		OrderByComparator<WatsonListTypeRel> orderByComparator,
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

		List<WatsonListTypeRel> list = null;

		if (useFinderCache) {
			list = (List<WatsonListTypeRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONLISTTYPEREL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONLISTTYPEREL;

				sql = sql.concat(WatsonListTypeRelModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonListTypeRel>)QueryUtil.list(
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
	 * Removes all the watson list type rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonListTypeRel watsonListTypeRel : findAll()) {
			remove(watsonListTypeRel);
		}
	}

	/**
	 * Returns the number of watson list type rels.
	 *
	 * @return the number of watson list type rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONLISTTYPEREL);

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
		return WatsonListTypeRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson list type rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonListTypeRelModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonListTypeRelImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONLISTTYPEREL =
		"SELECT watsonListTypeRel FROM WatsonListTypeRel watsonListTypeRel";

	private static final String _SQL_SELECT_WATSONLISTTYPEREL_WHERE_PKS_IN =
		"SELECT watsonListTypeRel FROM WatsonListTypeRel watsonListTypeRel WHERE watsonListTypeRelId IN (";

	private static final String _SQL_COUNT_WATSONLISTTYPEREL =
		"SELECT COUNT(watsonListTypeRel) FROM WatsonListTypeRel watsonListTypeRel";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonListTypeRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonListTypeRel exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonListTypeRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"primary", "type"});

}
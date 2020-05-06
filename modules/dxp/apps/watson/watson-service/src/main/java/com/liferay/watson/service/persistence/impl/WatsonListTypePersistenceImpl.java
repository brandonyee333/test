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
import com.liferay.watson.exception.NoSuchListTypeException;
import com.liferay.watson.model.WatsonListType;
import com.liferay.watson.model.impl.WatsonListTypeImpl;
import com.liferay.watson.model.impl.WatsonListTypeModelImpl;
import com.liferay.watson.service.persistence.WatsonListTypePersistence;

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
 * The persistence implementation for the watson list type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonListTypePersistenceImpl
	extends BasePersistenceImpl<WatsonListType>
	implements WatsonListTypePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonListTypeUtil</code> to access the watson list type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonListTypeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonListTypePersistenceImpl() {
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

		setModelClass(WatsonListType.class);
	}

	/**
	 * Caches the watson list type in the entity cache if it is enabled.
	 *
	 * @param watsonListType the watson list type
	 */
	@Override
	public void cacheResult(WatsonListType watsonListType) {
		entityCache.putResult(
			WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeImpl.class, watsonListType.getPrimaryKey(),
			watsonListType);

		watsonListType.resetOriginalValues();
	}

	/**
	 * Caches the watson list types in the entity cache if it is enabled.
	 *
	 * @param watsonListTypes the watson list types
	 */
	@Override
	public void cacheResult(List<WatsonListType> watsonListTypes) {
		for (WatsonListType watsonListType : watsonListTypes) {
			if (entityCache.getResult(
					WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeImpl.class, watsonListType.getPrimaryKey()) ==
						null) {

				cacheResult(watsonListType);
			}
			else {
				watsonListType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson list types.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonListTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson list type.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonListType watsonListType) {
		entityCache.removeResult(
			WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeImpl.class, watsonListType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonListType> watsonListTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonListType watsonListType : watsonListTypes) {
			entityCache.removeResult(
				WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeImpl.class, watsonListType.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson list type with the primary key. Does not add the watson list type to the database.
	 *
	 * @param watsonListTypeId the primary key for the new watson list type
	 * @return the new watson list type
	 */
	@Override
	public WatsonListType create(long watsonListTypeId) {
		WatsonListType watsonListType = new WatsonListTypeImpl();

		watsonListType.setNew(true);
		watsonListType.setPrimaryKey(watsonListTypeId);

		watsonListType.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonListType;
	}

	/**
	 * Removes the watson list type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type that was removed
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType remove(long watsonListTypeId)
		throws NoSuchListTypeException {

		return remove((Serializable)watsonListTypeId);
	}

	/**
	 * Removes the watson list type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson list type
	 * @return the watson list type that was removed
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType remove(Serializable primaryKey)
		throws NoSuchListTypeException {

		Session session = null;

		try {
			session = openSession();

			WatsonListType watsonListType = (WatsonListType)session.get(
				WatsonListTypeImpl.class, primaryKey);

			if (watsonListType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchListTypeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonListType);
		}
		catch (NoSuchListTypeException noSuchEntityException) {
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
	protected WatsonListType removeImpl(WatsonListType watsonListType) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonListType)) {
				watsonListType = (WatsonListType)session.get(
					WatsonListTypeImpl.class,
					watsonListType.getPrimaryKeyObj());
			}

			if (watsonListType != null) {
				session.delete(watsonListType);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonListType != null) {
			clearCache(watsonListType);
		}

		return watsonListType;
	}

	@Override
	public WatsonListType updateImpl(WatsonListType watsonListType) {
		boolean isNew = watsonListType.isNew();

		if (!(watsonListType instanceof WatsonListTypeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonListType.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonListType);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonListType proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonListType implementation " +
					watsonListType.getClass());
		}

		WatsonListTypeModelImpl watsonListTypeModelImpl =
			(WatsonListTypeModelImpl)watsonListType;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonListType.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonListType.setCreateDate(now);
			}
			else {
				watsonListType.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonListTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonListType.setModifiedDate(now);
			}
			else {
				watsonListType.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonListType.isNew()) {
				session.save(watsonListType);

				watsonListType.setNew(false);
			}
			else {
				watsonListType = (WatsonListType)session.merge(watsonListType);
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
			WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeImpl.class, watsonListType.getPrimaryKey(),
			watsonListType, false);

		watsonListType.resetOriginalValues();

		return watsonListType;
	}

	/**
	 * Returns the watson list type with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type
	 * @return the watson list type
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchListTypeException {

		WatsonListType watsonListType = fetchByPrimaryKey(primaryKey);

		if (watsonListType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchListTypeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonListType;
	}

	/**
	 * Returns the watson list type with the primary key or throws a <code>NoSuchListTypeException</code> if it could not be found.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType findByPrimaryKey(long watsonListTypeId)
		throws NoSuchListTypeException {

		return findByPrimaryKey((Serializable)watsonListTypeId);
	}

	/**
	 * Returns the watson list type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson list type
	 * @return the watson list type, or <code>null</code> if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonListType watsonListType = (WatsonListType)serializable;

		if (watsonListType == null) {
			Session session = null;

			try {
				session = openSession();

				watsonListType = (WatsonListType)session.get(
					WatsonListTypeImpl.class, primaryKey);

				if (watsonListType != null) {
					cacheResult(watsonListType);
				}
				else {
					entityCache.putResult(
						WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
						WatsonListTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonListType;
	}

	/**
	 * Returns the watson list type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type, or <code>null</code> if a watson list type with the primary key could not be found
	 */
	@Override
	public WatsonListType fetchByPrimaryKey(long watsonListTypeId) {
		return fetchByPrimaryKey((Serializable)watsonListTypeId);
	}

	@Override
	public Map<Serializable, WatsonListType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonListType> map =
			new HashMap<Serializable, WatsonListType>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonListType watsonListType = fetchByPrimaryKey(primaryKey);

			if (watsonListType != null) {
				map.put(primaryKey, watsonListType);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
				WatsonListTypeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonListType)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONLISTTYPE_WHERE_PKS_IN);

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

			for (WatsonListType watsonListType :
					(List<WatsonListType>)query.list()) {

				map.put(watsonListType.getPrimaryKeyObj(), watsonListType);

				cacheResult(watsonListType);

				uncachedPrimaryKeys.remove(watsonListType.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
					WatsonListTypeImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson list types.
	 *
	 * @return the watson list types
	 */
	@Override
	public List<WatsonListType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @return the range of watson list types
	 */
	@Override
	public List<WatsonListType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list types
	 */
	@Override
	public List<WatsonListType> findAll(
		int start, int end,
		OrderByComparator<WatsonListType> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson list types
	 */
	@Override
	public List<WatsonListType> findAll(
		int start, int end, OrderByComparator<WatsonListType> orderByComparator,
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

		List<WatsonListType> list = null;

		if (useFinderCache) {
			list = (List<WatsonListType>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONLISTTYPE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONLISTTYPE;

				sql = sql.concat(WatsonListTypeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonListType>)QueryUtil.list(
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
	 * Removes all the watson list types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonListType watsonListType : findAll()) {
			remove(watsonListType);
		}
	}

	/**
	 * Returns the number of watson list types.
	 *
	 * @return the number of watson list types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONLISTTYPE);

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
		return WatsonListTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson list type persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeModelImpl.FINDER_CACHE_ENABLED,
			WatsonListTypeImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonListTypeModelImpl.ENTITY_CACHE_ENABLED,
			WatsonListTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonListTypeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONLISTTYPE =
		"SELECT watsonListType FROM WatsonListType watsonListType";

	private static final String _SQL_SELECT_WATSONLISTTYPE_WHERE_PKS_IN =
		"SELECT watsonListType FROM WatsonListType watsonListType WHERE watsonListTypeId IN (";

	private static final String _SQL_COUNT_WATSONLISTTYPE =
		"SELECT COUNT(watsonListType) FROM WatsonListType watsonListType";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonListType.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonListType exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonListTypePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}
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
import com.liferay.watson.exception.NoSuchAddressException;
import com.liferay.watson.model.WatsonAddress;
import com.liferay.watson.model.impl.WatsonAddressImpl;
import com.liferay.watson.model.impl.WatsonAddressModelImpl;
import com.liferay.watson.service.persistence.WatsonAddressPersistence;

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
 * The persistence implementation for the watson address service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @generated
 */
public class WatsonAddressPersistenceImpl
	extends BasePersistenceImpl<WatsonAddress>
	implements WatsonAddressPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>WatsonAddressUtil</code> to access the watson address persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		WatsonAddressImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public WatsonAddressPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("number", "number_");

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

		setModelClass(WatsonAddress.class);
	}

	/**
	 * Caches the watson address in the entity cache if it is enabled.
	 *
	 * @param watsonAddress the watson address
	 */
	@Override
	public void cacheResult(WatsonAddress watsonAddress) {
		entityCache.putResult(
			WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressImpl.class, watsonAddress.getPrimaryKey(),
			watsonAddress);

		watsonAddress.resetOriginalValues();
	}

	/**
	 * Caches the watson addresses in the entity cache if it is enabled.
	 *
	 * @param watsonAddresses the watson addresses
	 */
	@Override
	public void cacheResult(List<WatsonAddress> watsonAddresses) {
		for (WatsonAddress watsonAddress : watsonAddresses) {
			if (entityCache.getResult(
					WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressImpl.class, watsonAddress.getPrimaryKey()) ==
						null) {

				cacheResult(watsonAddress);
			}
			else {
				watsonAddress.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson addresses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonAddressImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson address.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonAddress watsonAddress) {
		entityCache.removeResult(
			WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressImpl.class, watsonAddress.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonAddress> watsonAddresses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonAddress watsonAddress : watsonAddresses) {
			entityCache.removeResult(
				WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressImpl.class, watsonAddress.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new watson address with the primary key. Does not add the watson address to the database.
	 *
	 * @param watsonAddressId the primary key for the new watson address
	 * @return the new watson address
	 */
	@Override
	public WatsonAddress create(long watsonAddressId) {
		WatsonAddress watsonAddress = new WatsonAddressImpl();

		watsonAddress.setNew(true);
		watsonAddress.setPrimaryKey(watsonAddressId);

		watsonAddress.setCompanyId(CompanyThreadLocal.getCompanyId());

		return watsonAddress;
	}

	/**
	 * Removes the watson address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressId the primary key of the watson address
	 * @return the watson address that was removed
	 * @throws NoSuchAddressException if a watson address with the primary key could not be found
	 */
	@Override
	public WatsonAddress remove(long watsonAddressId)
		throws NoSuchAddressException {

		return remove((Serializable)watsonAddressId);
	}

	/**
	 * Removes the watson address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson address
	 * @return the watson address that was removed
	 * @throws NoSuchAddressException if a watson address with the primary key could not be found
	 */
	@Override
	public WatsonAddress remove(Serializable primaryKey)
		throws NoSuchAddressException {

		Session session = null;

		try {
			session = openSession();

			WatsonAddress watsonAddress = (WatsonAddress)session.get(
				WatsonAddressImpl.class, primaryKey);

			if (watsonAddress == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAddressException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(watsonAddress);
		}
		catch (NoSuchAddressException noSuchEntityException) {
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
	protected WatsonAddress removeImpl(WatsonAddress watsonAddress) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonAddress)) {
				watsonAddress = (WatsonAddress)session.get(
					WatsonAddressImpl.class, watsonAddress.getPrimaryKeyObj());
			}

			if (watsonAddress != null) {
				session.delete(watsonAddress);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (watsonAddress != null) {
			clearCache(watsonAddress);
		}

		return watsonAddress;
	}

	@Override
	public WatsonAddress updateImpl(WatsonAddress watsonAddress) {
		boolean isNew = watsonAddress.isNew();

		if (!(watsonAddress instanceof WatsonAddressModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(watsonAddress.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					watsonAddress);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in watsonAddress proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WatsonAddress implementation " +
					watsonAddress.getClass());
		}

		WatsonAddressModelImpl watsonAddressModelImpl =
			(WatsonAddressModelImpl)watsonAddress;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonAddress.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonAddress.setCreateDate(now);
			}
			else {
				watsonAddress.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!watsonAddressModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonAddress.setModifiedDate(now);
			}
			else {
				watsonAddress.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(watsonAddress);

				watsonAddress.setNew(false);
			}
			else {
				watsonAddress = (WatsonAddress)session.merge(watsonAddress);
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
			WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressImpl.class, watsonAddress.getPrimaryKey(),
			watsonAddress, false);

		watsonAddress.resetOriginalValues();

		return watsonAddress;
	}

	/**
	 * Returns the watson address with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson address
	 * @return the watson address
	 * @throws NoSuchAddressException if a watson address with the primary key could not be found
	 */
	@Override
	public WatsonAddress findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAddressException {

		WatsonAddress watsonAddress = fetchByPrimaryKey(primaryKey);

		if (watsonAddress == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAddressException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return watsonAddress;
	}

	/**
	 * Returns the watson address with the primary key or throws a <code>NoSuchAddressException</code> if it could not be found.
	 *
	 * @param watsonAddressId the primary key of the watson address
	 * @return the watson address
	 * @throws NoSuchAddressException if a watson address with the primary key could not be found
	 */
	@Override
	public WatsonAddress findByPrimaryKey(long watsonAddressId)
		throws NoSuchAddressException {

		return findByPrimaryKey((Serializable)watsonAddressId);
	}

	/**
	 * Returns the watson address with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson address
	 * @return the watson address, or <code>null</code> if a watson address with the primary key could not be found
	 */
	@Override
	public WatsonAddress fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonAddress watsonAddress = (WatsonAddress)serializable;

		if (watsonAddress == null) {
			Session session = null;

			try {
				session = openSession();

				watsonAddress = (WatsonAddress)session.get(
					WatsonAddressImpl.class, primaryKey);

				if (watsonAddress != null) {
					cacheResult(watsonAddress);
				}
				else {
					entityCache.putResult(
						WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
						WatsonAddressImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonAddress;
	}

	/**
	 * Returns the watson address with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonAddressId the primary key of the watson address
	 * @return the watson address, or <code>null</code> if a watson address with the primary key could not be found
	 */
	@Override
	public WatsonAddress fetchByPrimaryKey(long watsonAddressId) {
		return fetchByPrimaryKey((Serializable)watsonAddressId);
	}

	@Override
	public Map<Serializable, WatsonAddress> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonAddress> map =
			new HashMap<Serializable, WatsonAddress>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonAddress watsonAddress = fetchByPrimaryKey(primaryKey);

			if (watsonAddress != null) {
				map.put(primaryKey, watsonAddress);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonAddress)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_WATSONADDRESS_WHERE_PKS_IN);

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

			for (WatsonAddress watsonAddress :
					(List<WatsonAddress>)query.list()) {

				map.put(watsonAddress.getPrimaryKeyObj(), watsonAddress);

				cacheResult(watsonAddress);

				uncachedPrimaryKeys.remove(watsonAddress.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson addresses.
	 *
	 * @return the watson addresses
	 */
	@Override
	public List<WatsonAddress> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson addresses
	 * @param end the upper bound of the range of watson addresses (not inclusive)
	 * @return the range of watson addresses
	 */
	@Override
	public List<WatsonAddress> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson addresses
	 * @param end the upper bound of the range of watson addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson addresses
	 */
	@Override
	public List<WatsonAddress> findAll(
		int start, int end,
		OrderByComparator<WatsonAddress> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WatsonAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson addresses
	 * @param end the upper bound of the range of watson addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of watson addresses
	 */
	@Override
	public List<WatsonAddress> findAll(
		int start, int end, OrderByComparator<WatsonAddress> orderByComparator,
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

		List<WatsonAddress> list = null;

		if (useFinderCache) {
			list = (List<WatsonAddress>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_WATSONADDRESS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONADDRESS;

				sql = sql.concat(WatsonAddressModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<WatsonAddress>)QueryUtil.list(
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
	 * Removes all the watson addresses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonAddress watsonAddress : findAll()) {
			remove(watsonAddress);
		}
	}

	/**
	 * Returns the number of watson addresses.
	 *
	 * @return the number of watson addresses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_WATSONADDRESS);

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
		return WatsonAddressModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson address persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressModelImpl.FINDER_CACHE_ENABLED,
			WatsonAddressImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressModelImpl.FINDER_CACHE_ENABLED,
			WatsonAddressImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(WatsonAddressImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_WATSONADDRESS =
		"SELECT watsonAddress FROM WatsonAddress watsonAddress";

	private static final String _SQL_SELECT_WATSONADDRESS_WHERE_PKS_IN =
		"SELECT watsonAddress FROM WatsonAddress watsonAddress WHERE watsonAddressId IN (";

	private static final String _SQL_COUNT_WATSONADDRESS =
		"SELECT COUNT(watsonAddress) FROM WatsonAddress watsonAddress";

	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonAddress.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No WatsonAddress exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonAddressPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"number"});

}
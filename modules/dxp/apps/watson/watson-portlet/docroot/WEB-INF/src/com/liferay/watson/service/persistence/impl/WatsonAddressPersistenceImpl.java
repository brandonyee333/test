/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.watson.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.watson.exception.NoSuchAddressException;
import com.liferay.watson.model.WatsonAddress;
import com.liferay.watson.model.impl.WatsonAddressImpl;
import com.liferay.watson.model.impl.WatsonAddressModelImpl;
import com.liferay.watson.service.persistence.WatsonAddressPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * @author Eddie Olson
 * @see WatsonAddressPersistence
 * @see com.liferay.watson.service.persistence.WatsonAddressUtil
 * @generated
 */
@ProviderType
public class WatsonAddressPersistenceImpl extends BasePersistenceImpl<WatsonAddress>
	implements WatsonAddressPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonAddressUtil} to access the watson address persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonAddressImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressModelImpl.FINDER_CACHE_ENABLED,
			WatsonAddressImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressModelImpl.FINDER_CACHE_ENABLED,
			WatsonAddressImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonAddressPersistenceImpl() {
		setModelClass(WatsonAddress.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("number", "number_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the watson address in the entity cache if it is enabled.
	 *
	 * @param watsonAddress the watson address
	 */
	@Override
	public void cacheResult(WatsonAddress watsonAddress) {
		entityCache.putResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
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
						WatsonAddressImpl.class, watsonAddress.getPrimaryKey()) == null) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonAddress watsonAddress) {
		entityCache.removeResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressImpl.class, watsonAddress.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonAddress> watsonAddresses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonAddress watsonAddress : watsonAddresses) {
			entityCache.removeResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressImpl.class, watsonAddress.getPrimaryKey());
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

		watsonAddress.setCompanyId(companyProvider.getCompanyId());

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

			WatsonAddress watsonAddress = (WatsonAddress)session.get(WatsonAddressImpl.class,
					primaryKey);

			if (watsonAddress == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAddressException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonAddress);
		}
		catch (NoSuchAddressException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected WatsonAddress removeImpl(WatsonAddress watsonAddress) {
		watsonAddress = toUnwrappedModel(watsonAddress);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonAddress)) {
				watsonAddress = (WatsonAddress)session.get(WatsonAddressImpl.class,
						watsonAddress.getPrimaryKeyObj());
			}

			if (watsonAddress != null) {
				session.delete(watsonAddress);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		watsonAddress = toUnwrappedModel(watsonAddress);

		boolean isNew = watsonAddress.isNew();

		WatsonAddressModelImpl watsonAddressModelImpl = (WatsonAddressModelImpl)watsonAddress;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

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
				watsonAddress.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonAddress.isNew()) {
				session.save(watsonAddress);

				watsonAddress.setNew(false);
			}
			else {
				watsonAddress = (WatsonAddress)session.merge(watsonAddress);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
			WatsonAddressImpl.class, watsonAddress.getPrimaryKey(),
			watsonAddress, false);

		watsonAddress.resetOriginalValues();

		return watsonAddress;
	}

	protected WatsonAddress toUnwrappedModel(WatsonAddress watsonAddress) {
		if (watsonAddress instanceof WatsonAddressImpl) {
			return watsonAddress;
		}

		WatsonAddressImpl watsonAddressImpl = new WatsonAddressImpl();

		watsonAddressImpl.setNew(watsonAddress.isNew());
		watsonAddressImpl.setPrimaryKey(watsonAddress.getPrimaryKey());

		watsonAddressImpl.setWatsonAddressId(watsonAddress.getWatsonAddressId());
		watsonAddressImpl.setCompanyId(watsonAddress.getCompanyId());
		watsonAddressImpl.setUserId(watsonAddress.getUserId());
		watsonAddressImpl.setUserName(watsonAddress.getUserName());
		watsonAddressImpl.setCreateDate(watsonAddress.getCreateDate());
		watsonAddressImpl.setModifiedDate(watsonAddress.getModifiedDate());
		watsonAddressImpl.setCountryId(watsonAddress.getCountryId());
		watsonAddressImpl.setDistrictWatsonListTypeId(watsonAddress.getDistrictWatsonListTypeId());
		watsonAddressImpl.setOriginalWatsonAddressId(watsonAddress.getOriginalWatsonAddressId());
		watsonAddressImpl.setProvinceWatsonListTypeId(watsonAddress.getProvinceWatsonListTypeId());
		watsonAddressImpl.setSubDistrictWatsonListTypeId(watsonAddress.getSubDistrictWatsonListTypeId());
		watsonAddressImpl.setTypeWatsonListTypeId(watsonAddress.getTypeWatsonListTypeId());
		watsonAddressImpl.setWatsonIncidentId(watsonAddress.getWatsonIncidentId());
		watsonAddressImpl.setName(watsonAddress.getName());
		watsonAddressImpl.setPostalCode(watsonAddress.getPostalCode());
		watsonAddressImpl.setRegion(watsonAddress.getRegion());
		watsonAddressImpl.setStreet(watsonAddress.getStreet());
		watsonAddressImpl.setNumber(watsonAddress.getNumber());
		watsonAddressImpl.setBuilding(watsonAddress.getBuilding());
		watsonAddressImpl.setFloor(watsonAddress.getFloor());
		watsonAddressImpl.setRoom(watsonAddress.getRoom());
		watsonAddressImpl.setDescription(watsonAddress.getDescription());
		watsonAddressImpl.setImagePayload(watsonAddress.getImagePayload());
		watsonAddressImpl.setLastSeenDate(watsonAddress.getLastSeenDate());
		watsonAddressImpl.setLatitude(watsonAddress.getLatitude());
		watsonAddressImpl.setLongitude(watsonAddress.getLongitude());
		watsonAddressImpl.setStatus(watsonAddress.getStatus());

		return watsonAddressImpl;
	}

	/**
	 * Returns the watson address with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchAddressException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonAddress;
	}

	/**
	 * Returns the watson address with the primary key or throws a {@link NoSuchAddressException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
				WatsonAddressImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonAddress watsonAddress = (WatsonAddress)serializable;

		if (watsonAddress == null) {
			Session session = null;

			try {
				session = openSession();

				watsonAddress = (WatsonAddress)session.get(WatsonAddressImpl.class,
						primaryKey);

				if (watsonAddress != null) {
					cacheResult(watsonAddress);
				}
				else {
					entityCache.putResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
						WatsonAddressImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressImpl.class, primaryKey);

				throw processException(e);
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

		Map<Serializable, WatsonAddress> map = new HashMap<Serializable, WatsonAddress>();

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
			Serializable serializable = entityCache.getResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONADDRESS_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (WatsonAddress watsonAddress : (List<WatsonAddress>)q.list()) {
				map.put(watsonAddress.getPrimaryKeyObj(), watsonAddress);

				cacheResult(watsonAddress);

				uncachedPrimaryKeys.remove(watsonAddress.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonAddressModelImpl.ENTITY_CACHE_ENABLED,
					WatsonAddressImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson addresses
	 * @param end the upper bound of the range of watson addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson addresses
	 */
	@Override
	public List<WatsonAddress> findAll(int start, int end,
		OrderByComparator<WatsonAddress> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson addresses
	 * @param end the upper bound of the range of watson addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson addresses
	 */
	@Override
	public List<WatsonAddress> findAll(int start, int end,
		OrderByComparator<WatsonAddress> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<WatsonAddress> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonAddress>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONADDRESS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONADDRESS;

				if (pagination) {
					sql = sql.concat(WatsonAddressModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonAddress>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonAddress>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONADDRESS);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
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
	}

	public void destroy() {
		entityCache.removeCache(WatsonAddressImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_WATSONADDRESS = "SELECT watsonAddress FROM WatsonAddress watsonAddress";
	private static final String _SQL_SELECT_WATSONADDRESS_WHERE_PKS_IN = "SELECT watsonAddress FROM WatsonAddress watsonAddress WHERE watsonAddressId IN (";
	private static final String _SQL_COUNT_WATSONADDRESS = "SELECT COUNT(watsonAddress) FROM WatsonAddress watsonAddress";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonAddress.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonAddress exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonAddressPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"number"
			});
}
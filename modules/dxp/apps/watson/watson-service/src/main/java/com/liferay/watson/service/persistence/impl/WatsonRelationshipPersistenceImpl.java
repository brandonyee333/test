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

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.watson.exception.NoSuchRelationshipException;
import com.liferay.watson.model.WatsonRelationship;
import com.liferay.watson.model.impl.WatsonRelationshipImpl;
import com.liferay.watson.model.impl.WatsonRelationshipModelImpl;
import com.liferay.watson.service.persistence.WatsonRelationshipPersistence;

import java.io.Serializable;

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
 * @see WatsonRelationshipPersistence
 * @see com.liferay.watson.service.persistence.WatsonRelationshipUtil
 * @generated
 */
@ProviderType
public class WatsonRelationshipPersistenceImpl extends BasePersistenceImpl<WatsonRelationship>
	implements WatsonRelationshipPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonRelationshipUtil} to access the watson relationship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonRelationshipImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipModelImpl.FINDER_CACHE_ENABLED,
			WatsonRelationshipImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipModelImpl.FINDER_CACHE_ENABLED,
			WatsonRelationshipImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

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
		entityCache.putResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonRelationship watsonRelationship) {
		entityCache.removeResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipImpl.class, watsonRelationship.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonRelationship> watsonRelationships) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonRelationship watsonRelationship : watsonRelationships) {
			entityCache.removeResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipImpl.class, watsonRelationship.getPrimaryKey());
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

		watsonRelationship.setCompanyId(companyProvider.getCompanyId());

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

			WatsonRelationship watsonRelationship = (WatsonRelationship)session.get(WatsonRelationshipImpl.class,
					primaryKey);

			if (watsonRelationship == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRelationshipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonRelationship);
		}
		catch (NoSuchRelationshipException nsee) {
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
	protected WatsonRelationship removeImpl(
		WatsonRelationship watsonRelationship) {
		watsonRelationship = toUnwrappedModel(watsonRelationship);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonRelationship)) {
				watsonRelationship = (WatsonRelationship)session.get(WatsonRelationshipImpl.class,
						watsonRelationship.getPrimaryKeyObj());
			}

			if (watsonRelationship != null) {
				session.delete(watsonRelationship);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
	public WatsonRelationship updateImpl(WatsonRelationship watsonRelationship) {
		watsonRelationship = toUnwrappedModel(watsonRelationship);

		boolean isNew = watsonRelationship.isNew();

		WatsonRelationshipModelImpl watsonRelationshipModelImpl = (WatsonRelationshipModelImpl)watsonRelationship;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonRelationship.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonRelationship.setCreateDate(now);
			}
			else {
				watsonRelationship.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!watsonRelationshipModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonRelationship.setModifiedDate(now);
			}
			else {
				watsonRelationship.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonRelationship.isNew()) {
				session.save(watsonRelationship);

				watsonRelationship.setNew(false);
			}
			else {
				watsonRelationship = (WatsonRelationship)session.merge(watsonRelationship);
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

		entityCache.putResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
			WatsonRelationshipImpl.class, watsonRelationship.getPrimaryKey(),
			watsonRelationship, false);

		watsonRelationship.resetOriginalValues();

		return watsonRelationship;
	}

	protected WatsonRelationship toUnwrappedModel(
		WatsonRelationship watsonRelationship) {
		if (watsonRelationship instanceof WatsonRelationshipImpl) {
			return watsonRelationship;
		}

		WatsonRelationshipImpl watsonRelationshipImpl = new WatsonRelationshipImpl();

		watsonRelationshipImpl.setNew(watsonRelationship.isNew());
		watsonRelationshipImpl.setPrimaryKey(watsonRelationship.getPrimaryKey());

		watsonRelationshipImpl.setWatsonRelationshipId(watsonRelationship.getWatsonRelationshipId());
		watsonRelationshipImpl.setGroupId(watsonRelationship.getGroupId());
		watsonRelationshipImpl.setCompanyId(watsonRelationship.getCompanyId());
		watsonRelationshipImpl.setUserId(watsonRelationship.getUserId());
		watsonRelationshipImpl.setUserName(watsonRelationship.getUserName());
		watsonRelationshipImpl.setCreateDate(watsonRelationship.getCreateDate());
		watsonRelationshipImpl.setModifiedDate(watsonRelationship.getModifiedDate());
		watsonRelationshipImpl.setWatsonIncidentId(watsonRelationship.getWatsonIncidentId());
		watsonRelationshipImpl.setTypeWatsonListTypeId(watsonRelationship.getTypeWatsonListTypeId());
		watsonRelationshipImpl.setClassNameId1(watsonRelationship.getClassNameId1());
		watsonRelationshipImpl.setClassPK1(watsonRelationship.getClassPK1());
		watsonRelationshipImpl.setClassNameId2(watsonRelationship.getClassNameId2());
		watsonRelationshipImpl.setClassPK2(watsonRelationship.getClassPK2());
		watsonRelationshipImpl.setDescription(watsonRelationship.getDescription());
		watsonRelationshipImpl.setStatus(watsonRelationship.getStatus());

		return watsonRelationshipImpl;
	}

	/**
	 * Returns the watson relationship with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchRelationshipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonRelationship;
	}

	/**
	 * Returns the watson relationship with the primary key or throws a {@link NoSuchRelationshipException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
				WatsonRelationshipImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonRelationship watsonRelationship = (WatsonRelationship)serializable;

		if (watsonRelationship == null) {
			Session session = null;

			try {
				session = openSession();

				watsonRelationship = (WatsonRelationship)session.get(WatsonRelationshipImpl.class,
						primaryKey);

				if (watsonRelationship != null) {
					cacheResult(watsonRelationship);
				}
				else {
					entityCache.putResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
						WatsonRelationshipImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipImpl.class, primaryKey);

				throw processException(e);
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

		Map<Serializable, WatsonRelationship> map = new HashMap<Serializable, WatsonRelationship>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonRelationship watsonRelationship = fetchByPrimaryKey(primaryKey);

			if (watsonRelationship != null) {
				map.put(primaryKey, watsonRelationship);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONRELATIONSHIP_WHERE_PKS_IN);

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

			for (WatsonRelationship watsonRelationship : (List<WatsonRelationship>)q.list()) {
				map.put(watsonRelationship.getPrimaryKeyObj(),
					watsonRelationship);

				cacheResult(watsonRelationship);

				uncachedPrimaryKeys.remove(watsonRelationship.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonRelationshipModelImpl.ENTITY_CACHE_ENABLED,
					WatsonRelationshipImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationships
	 * @param end the upper bound of the range of watson relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson relationships
	 */
	@Override
	public List<WatsonRelationship> findAll(int start, int end,
		OrderByComparator<WatsonRelationship> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationships
	 * @param end the upper bound of the range of watson relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson relationships
	 */
	@Override
	public List<WatsonRelationship> findAll(int start, int end,
		OrderByComparator<WatsonRelationship> orderByComparator,
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

		List<WatsonRelationship> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonRelationship>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONRELATIONSHIP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONRELATIONSHIP;

				if (pagination) {
					sql = sql.concat(WatsonRelationshipModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonRelationship>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonRelationship>)QueryUtil.list(q,
							getDialect(), start, end);
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONRELATIONSHIP);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return WatsonRelationshipModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson relationship persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonRelationshipImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_WATSONRELATIONSHIP = "SELECT watsonRelationship FROM WatsonRelationship watsonRelationship";
	private static final String _SQL_SELECT_WATSONRELATIONSHIP_WHERE_PKS_IN = "SELECT watsonRelationship FROM WatsonRelationship watsonRelationship WHERE watsonRelationshipId IN (";
	private static final String _SQL_COUNT_WATSONRELATIONSHIP = "SELECT COUNT(watsonRelationship) FROM WatsonRelationship watsonRelationship";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonRelationship.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonRelationship exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonRelationshipPersistenceImpl.class);
}
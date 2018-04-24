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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.watson.exception.NoSuchDocumentAuditException;
import com.liferay.watson.model.WatsonDocumentAudit;
import com.liferay.watson.model.impl.WatsonDocumentAuditImpl;
import com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl;
import com.liferay.watson.service.persistence.WatsonDocumentAuditPersistence;

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
 * The persistence implementation for the watson document audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonDocumentAuditPersistence
 * @see com.liferay.watson.service.persistence.WatsonDocumentAuditUtil
 * @generated
 */
@ProviderType
public class WatsonDocumentAuditPersistenceImpl extends BasePersistenceImpl<WatsonDocumentAudit>
	implements WatsonDocumentAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WatsonDocumentAuditUtil} to access the watson document audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WatsonDocumentAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditModelImpl.FINDER_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WatsonDocumentAuditPersistenceImpl() {
		setModelClass(WatsonDocumentAudit.class);
	}

	/**
	 * Caches the watson document audit in the entity cache if it is enabled.
	 *
	 * @param watsonDocumentAudit the watson document audit
	 */
	@Override
	public void cacheResult(WatsonDocumentAudit watsonDocumentAudit) {
		entityCache.putResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class, watsonDocumentAudit.getPrimaryKey(),
			watsonDocumentAudit);

		watsonDocumentAudit.resetOriginalValues();
	}

	/**
	 * Caches the watson document audits in the entity cache if it is enabled.
	 *
	 * @param watsonDocumentAudits the watson document audits
	 */
	@Override
	public void cacheResult(List<WatsonDocumentAudit> watsonDocumentAudits) {
		for (WatsonDocumentAudit watsonDocumentAudit : watsonDocumentAudits) {
			if (entityCache.getResult(
						WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonDocumentAuditImpl.class,
						watsonDocumentAudit.getPrimaryKey()) == null) {
				cacheResult(watsonDocumentAudit);
			}
			else {
				watsonDocumentAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all watson document audits.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WatsonDocumentAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the watson document audit.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WatsonDocumentAudit watsonDocumentAudit) {
		entityCache.removeResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class, watsonDocumentAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WatsonDocumentAudit> watsonDocumentAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WatsonDocumentAudit watsonDocumentAudit : watsonDocumentAudits) {
			entityCache.removeResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentAuditImpl.class,
				watsonDocumentAudit.getPrimaryKey());
		}
	}

	/**
	 * Creates a new watson document audit with the primary key. Does not add the watson document audit to the database.
	 *
	 * @param watsonDocumentAuditId the primary key for the new watson document audit
	 * @return the new watson document audit
	 */
	@Override
	public WatsonDocumentAudit create(long watsonDocumentAuditId) {
		WatsonDocumentAudit watsonDocumentAudit = new WatsonDocumentAuditImpl();

		watsonDocumentAudit.setNew(true);
		watsonDocumentAudit.setPrimaryKey(watsonDocumentAuditId);

		watsonDocumentAudit.setCompanyId(companyProvider.getCompanyId());

		return watsonDocumentAudit;
	}

	/**
	 * Removes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit that was removed
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit remove(long watsonDocumentAuditId)
		throws NoSuchDocumentAuditException {
		return remove((Serializable)watsonDocumentAuditId);
	}

	/**
	 * Removes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the watson document audit
	 * @return the watson document audit that was removed
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit remove(Serializable primaryKey)
		throws NoSuchDocumentAuditException {
		Session session = null;

		try {
			session = openSession();

			WatsonDocumentAudit watsonDocumentAudit = (WatsonDocumentAudit)session.get(WatsonDocumentAuditImpl.class,
					primaryKey);

			if (watsonDocumentAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocumentAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(watsonDocumentAudit);
		}
		catch (NoSuchDocumentAuditException nsee) {
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
	protected WatsonDocumentAudit removeImpl(
		WatsonDocumentAudit watsonDocumentAudit) {
		watsonDocumentAudit = toUnwrappedModel(watsonDocumentAudit);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(watsonDocumentAudit)) {
				watsonDocumentAudit = (WatsonDocumentAudit)session.get(WatsonDocumentAuditImpl.class,
						watsonDocumentAudit.getPrimaryKeyObj());
			}

			if (watsonDocumentAudit != null) {
				session.delete(watsonDocumentAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (watsonDocumentAudit != null) {
			clearCache(watsonDocumentAudit);
		}

		return watsonDocumentAudit;
	}

	@Override
	public WatsonDocumentAudit updateImpl(
		WatsonDocumentAudit watsonDocumentAudit) {
		watsonDocumentAudit = toUnwrappedModel(watsonDocumentAudit);

		boolean isNew = watsonDocumentAudit.isNew();

		WatsonDocumentAuditModelImpl watsonDocumentAuditModelImpl = (WatsonDocumentAuditModelImpl)watsonDocumentAudit;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (watsonDocumentAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				watsonDocumentAudit.setCreateDate(now);
			}
			else {
				watsonDocumentAudit.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!watsonDocumentAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				watsonDocumentAudit.setModifiedDate(now);
			}
			else {
				watsonDocumentAudit.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (watsonDocumentAudit.isNew()) {
				session.save(watsonDocumentAudit);

				watsonDocumentAudit.setNew(false);
			}
			else {
				watsonDocumentAudit = (WatsonDocumentAudit)session.merge(watsonDocumentAudit);
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

		entityCache.putResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
			WatsonDocumentAuditImpl.class, watsonDocumentAudit.getPrimaryKey(),
			watsonDocumentAudit, false);

		watsonDocumentAudit.resetOriginalValues();

		return watsonDocumentAudit;
	}

	protected WatsonDocumentAudit toUnwrappedModel(
		WatsonDocumentAudit watsonDocumentAudit) {
		if (watsonDocumentAudit instanceof WatsonDocumentAuditImpl) {
			return watsonDocumentAudit;
		}

		WatsonDocumentAuditImpl watsonDocumentAuditImpl = new WatsonDocumentAuditImpl();

		watsonDocumentAuditImpl.setNew(watsonDocumentAudit.isNew());
		watsonDocumentAuditImpl.setPrimaryKey(watsonDocumentAudit.getPrimaryKey());

		watsonDocumentAuditImpl.setWatsonDocumentAuditId(watsonDocumentAudit.getWatsonDocumentAuditId());
		watsonDocumentAuditImpl.setGroupId(watsonDocumentAudit.getGroupId());
		watsonDocumentAuditImpl.setCompanyId(watsonDocumentAudit.getCompanyId());
		watsonDocumentAuditImpl.setUserId(watsonDocumentAudit.getUserId());
		watsonDocumentAuditImpl.setUserName(watsonDocumentAudit.getUserName());
		watsonDocumentAuditImpl.setCreateDate(watsonDocumentAudit.getCreateDate());
		watsonDocumentAuditImpl.setModifiedDate(watsonDocumentAudit.getModifiedDate());
		watsonDocumentAuditImpl.setParentTypeWatsonListTypeId(watsonDocumentAudit.getParentTypeWatsonListTypeId());
		watsonDocumentAuditImpl.setSubtypeWatsonListTypeId(watsonDocumentAudit.getSubtypeWatsonListTypeId());
		watsonDocumentAuditImpl.setTypeWatsonListTypeId(watsonDocumentAudit.getTypeWatsonListTypeId());
		watsonDocumentAuditImpl.setWatsonChildId(watsonDocumentAudit.getWatsonChildId());
		watsonDocumentAuditImpl.setWatsonDocumentId(watsonDocumentAudit.getWatsonDocumentId());
		watsonDocumentAuditImpl.setOriginalDocument(watsonDocumentAudit.isOriginalDocument());
		watsonDocumentAuditImpl.setReceivedDate(watsonDocumentAudit.getReceivedDate());
		watsonDocumentAuditImpl.setImagePayload(watsonDocumentAudit.getImagePayload());
		watsonDocumentAuditImpl.setStatus(watsonDocumentAudit.getStatus());

		return watsonDocumentAuditImpl;
	}

	/**
	 * Returns the watson document audit with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson document audit
	 * @return the watson document audit
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocumentAuditException {
		WatsonDocumentAudit watsonDocumentAudit = fetchByPrimaryKey(primaryKey);

		if (watsonDocumentAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocumentAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return watsonDocumentAudit;
	}

	/**
	 * Returns the watson document audit with the primary key or throws a {@link NoSuchDocumentAuditException} if it could not be found.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit
	 * @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit findByPrimaryKey(long watsonDocumentAuditId)
		throws NoSuchDocumentAuditException {
		return findByPrimaryKey((Serializable)watsonDocumentAuditId);
	}

	/**
	 * Returns the watson document audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the watson document audit
	 * @return the watson document audit, or <code>null</code> if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
				WatsonDocumentAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WatsonDocumentAudit watsonDocumentAudit = (WatsonDocumentAudit)serializable;

		if (watsonDocumentAudit == null) {
			Session session = null;

			try {
				session = openSession();

				watsonDocumentAudit = (WatsonDocumentAudit)session.get(WatsonDocumentAuditImpl.class,
						primaryKey);

				if (watsonDocumentAudit != null) {
					cacheResult(watsonDocumentAudit);
				}
				else {
					entityCache.putResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
						WatsonDocumentAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentAuditImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return watsonDocumentAudit;
	}

	/**
	 * Returns the watson document audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit, or <code>null</code> if a watson document audit with the primary key could not be found
	 */
	@Override
	public WatsonDocumentAudit fetchByPrimaryKey(long watsonDocumentAuditId) {
		return fetchByPrimaryKey((Serializable)watsonDocumentAuditId);
	}

	@Override
	public Map<Serializable, WatsonDocumentAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WatsonDocumentAudit> map = new HashMap<Serializable, WatsonDocumentAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WatsonDocumentAudit watsonDocumentAudit = fetchByPrimaryKey(primaryKey);

			if (watsonDocumentAudit != null) {
				map.put(primaryKey, watsonDocumentAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WatsonDocumentAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WATSONDOCUMENTAUDIT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (WatsonDocumentAudit watsonDocumentAudit : (List<WatsonDocumentAudit>)q.list()) {
				map.put(watsonDocumentAudit.getPrimaryKeyObj(),
					watsonDocumentAudit);

				cacheResult(watsonDocumentAudit);

				uncachedPrimaryKeys.remove(watsonDocumentAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WatsonDocumentAuditModelImpl.ENTITY_CACHE_ENABLED,
					WatsonDocumentAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the watson document audits.
	 *
	 * @return the watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @return the range of watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll(int start, int end,
		OrderByComparator<WatsonDocumentAudit> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson document audits
	 */
	@Override
	public List<WatsonDocumentAudit> findAll(int start, int end,
		OrderByComparator<WatsonDocumentAudit> orderByComparator,
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

		List<WatsonDocumentAudit> list = null;

		if (retrieveFromCache) {
			list = (List<WatsonDocumentAudit>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WATSONDOCUMENTAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WATSONDOCUMENTAUDIT;

				if (pagination) {
					sql = sql.concat(WatsonDocumentAuditModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WatsonDocumentAudit>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WatsonDocumentAudit>)QueryUtil.list(q,
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
	 * Removes all the watson document audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WatsonDocumentAudit watsonDocumentAudit : findAll()) {
			remove(watsonDocumentAudit);
		}
	}

	/**
	 * Returns the number of watson document audits.
	 *
	 * @return the number of watson document audits
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WATSONDOCUMENTAUDIT);

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
		return WatsonDocumentAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the watson document audit persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WatsonDocumentAuditImpl.class.getName());
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
	private static final String _SQL_SELECT_WATSONDOCUMENTAUDIT = "SELECT watsonDocumentAudit FROM WatsonDocumentAudit watsonDocumentAudit";
	private static final String _SQL_SELECT_WATSONDOCUMENTAUDIT_WHERE_PKS_IN = "SELECT watsonDocumentAudit FROM WatsonDocumentAudit watsonDocumentAudit WHERE watsonDocumentAuditId IN (";
	private static final String _SQL_COUNT_WATSONDOCUMENTAUDIT = "SELECT COUNT(watsonDocumentAudit) FROM WatsonDocumentAudit watsonDocumentAudit";
	private static final String _ORDER_BY_ENTITY_ALIAS = "watsonDocumentAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WatsonDocumentAudit exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WatsonDocumentAuditPersistenceImpl.class);
}
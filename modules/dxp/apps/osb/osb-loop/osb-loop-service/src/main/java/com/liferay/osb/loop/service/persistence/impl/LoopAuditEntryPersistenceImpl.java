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

package com.liferay.osb.loop.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopAuditEntryException;
import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.osb.loop.model.impl.LoopAuditEntryImpl;
import com.liferay.osb.loop.model.impl.LoopAuditEntryModelImpl;
import com.liferay.osb.loop.service.persistence.LoopAuditEntryPersistence;

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
 * The persistence implementation for the loop audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopAuditEntryPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopAuditEntryUtil
 * @generated
 */
@ProviderType
public class LoopAuditEntryPersistenceImpl extends BasePersistenceImpl<LoopAuditEntry>
	implements LoopAuditEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopAuditEntryUtil} to access the loop audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopAuditEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopAuditEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopAuditEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopAuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopAuditEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public LoopAuditEntryPersistenceImpl() {
		setModelClass(LoopAuditEntry.class);
	}

	/**
	 * Caches the loop audit entry in the entity cache if it is enabled.
	 *
	 * @param loopAuditEntry the loop audit entry
	 */
	@Override
	public void cacheResult(LoopAuditEntry loopAuditEntry) {
		entityCache.putResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopAuditEntryImpl.class, loopAuditEntry.getPrimaryKey(),
			loopAuditEntry);

		loopAuditEntry.resetOriginalValues();
	}

	/**
	 * Caches the loop audit entries in the entity cache if it is enabled.
	 *
	 * @param loopAuditEntries the loop audit entries
	 */
	@Override
	public void cacheResult(List<LoopAuditEntry> loopAuditEntries) {
		for (LoopAuditEntry loopAuditEntry : loopAuditEntries) {
			if (entityCache.getResult(
						LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
						LoopAuditEntryImpl.class, loopAuditEntry.getPrimaryKey()) == null) {
				cacheResult(loopAuditEntry);
			}
			else {
				loopAuditEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop audit entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopAuditEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop audit entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopAuditEntry loopAuditEntry) {
		entityCache.removeResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopAuditEntryImpl.class, loopAuditEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LoopAuditEntry> loopAuditEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopAuditEntry loopAuditEntry : loopAuditEntries) {
			entityCache.removeResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
				LoopAuditEntryImpl.class, loopAuditEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new loop audit entry with the primary key. Does not add the loop audit entry to the database.
	 *
	 * @param loopAuditEntryId the primary key for the new loop audit entry
	 * @return the new loop audit entry
	 */
	@Override
	public LoopAuditEntry create(long loopAuditEntryId) {
		LoopAuditEntry loopAuditEntry = new LoopAuditEntryImpl();

		loopAuditEntry.setNew(true);
		loopAuditEntry.setPrimaryKey(loopAuditEntryId);

		loopAuditEntry.setCompanyId(companyProvider.getCompanyId());

		return loopAuditEntry;
	}

	/**
	 * Removes the loop audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry that was removed
	 * @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	 */
	@Override
	public LoopAuditEntry remove(long loopAuditEntryId)
		throws NoSuchLoopAuditEntryException {
		return remove((Serializable)loopAuditEntryId);
	}

	/**
	 * Removes the loop audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop audit entry
	 * @return the loop audit entry that was removed
	 * @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	 */
	@Override
	public LoopAuditEntry remove(Serializable primaryKey)
		throws NoSuchLoopAuditEntryException {
		Session session = null;

		try {
			session = openSession();

			LoopAuditEntry loopAuditEntry = (LoopAuditEntry)session.get(LoopAuditEntryImpl.class,
					primaryKey);

			if (loopAuditEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopAuditEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopAuditEntry);
		}
		catch (NoSuchLoopAuditEntryException nsee) {
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
	protected LoopAuditEntry removeImpl(LoopAuditEntry loopAuditEntry) {
		loopAuditEntry = toUnwrappedModel(loopAuditEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopAuditEntry)) {
				loopAuditEntry = (LoopAuditEntry)session.get(LoopAuditEntryImpl.class,
						loopAuditEntry.getPrimaryKeyObj());
			}

			if (loopAuditEntry != null) {
				session.delete(loopAuditEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopAuditEntry != null) {
			clearCache(loopAuditEntry);
		}

		return loopAuditEntry;
	}

	@Override
	public LoopAuditEntry updateImpl(LoopAuditEntry loopAuditEntry) {
		loopAuditEntry = toUnwrappedModel(loopAuditEntry);

		boolean isNew = loopAuditEntry.isNew();

		LoopAuditEntryModelImpl loopAuditEntryModelImpl = (LoopAuditEntryModelImpl)loopAuditEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (loopAuditEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				loopAuditEntry.setCreateDate(now);
			}
			else {
				loopAuditEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!loopAuditEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				loopAuditEntry.setModifiedDate(now);
			}
			else {
				loopAuditEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (loopAuditEntry.isNew()) {
				session.save(loopAuditEntry);

				loopAuditEntry.setNew(false);
			}
			else {
				loopAuditEntry = (LoopAuditEntry)session.merge(loopAuditEntry);
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

		entityCache.putResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopAuditEntryImpl.class, loopAuditEntry.getPrimaryKey(),
			loopAuditEntry, false);

		loopAuditEntry.resetOriginalValues();

		return loopAuditEntry;
	}

	protected LoopAuditEntry toUnwrappedModel(LoopAuditEntry loopAuditEntry) {
		if (loopAuditEntry instanceof LoopAuditEntryImpl) {
			return loopAuditEntry;
		}

		LoopAuditEntryImpl loopAuditEntryImpl = new LoopAuditEntryImpl();

		loopAuditEntryImpl.setNew(loopAuditEntry.isNew());
		loopAuditEntryImpl.setPrimaryKey(loopAuditEntry.getPrimaryKey());

		loopAuditEntryImpl.setLoopAuditEntryId(loopAuditEntry.getLoopAuditEntryId());
		loopAuditEntryImpl.setCompanyId(loopAuditEntry.getCompanyId());
		loopAuditEntryImpl.setUserId(loopAuditEntry.getUserId());
		loopAuditEntryImpl.setUserName(loopAuditEntry.getUserName());
		loopAuditEntryImpl.setCreateDate(loopAuditEntry.getCreateDate());
		loopAuditEntryImpl.setModifiedDate(loopAuditEntry.getModifiedDate());
		loopAuditEntryImpl.setClassNameId(loopAuditEntry.getClassNameId());
		loopAuditEntryImpl.setClassPK(loopAuditEntry.getClassPK());
		loopAuditEntryImpl.setName(loopAuditEntry.getName());

		return loopAuditEntryImpl;
	}

	/**
	 * Returns the loop audit entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop audit entry
	 * @return the loop audit entry
	 * @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	 */
	@Override
	public LoopAuditEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopAuditEntryException {
		LoopAuditEntry loopAuditEntry = fetchByPrimaryKey(primaryKey);

		if (loopAuditEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopAuditEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopAuditEntry;
	}

	/**
	 * Returns the loop audit entry with the primary key or throws a {@link NoSuchLoopAuditEntryException} if it could not be found.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry
	 * @throws NoSuchLoopAuditEntryException if a loop audit entry with the primary key could not be found
	 */
	@Override
	public LoopAuditEntry findByPrimaryKey(long loopAuditEntryId)
		throws NoSuchLoopAuditEntryException {
		return findByPrimaryKey((Serializable)loopAuditEntryId);
	}

	/**
	 * Returns the loop audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop audit entry
	 * @return the loop audit entry, or <code>null</code> if a loop audit entry with the primary key could not be found
	 */
	@Override
	public LoopAuditEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
				LoopAuditEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopAuditEntry loopAuditEntry = (LoopAuditEntry)serializable;

		if (loopAuditEntry == null) {
			Session session = null;

			try {
				session = openSession();

				loopAuditEntry = (LoopAuditEntry)session.get(LoopAuditEntryImpl.class,
						primaryKey);

				if (loopAuditEntry != null) {
					cacheResult(loopAuditEntry);
				}
				else {
					entityCache.putResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
						LoopAuditEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopAuditEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopAuditEntry;
	}

	/**
	 * Returns the loop audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry, or <code>null</code> if a loop audit entry with the primary key could not be found
	 */
	@Override
	public LoopAuditEntry fetchByPrimaryKey(long loopAuditEntryId) {
		return fetchByPrimaryKey((Serializable)loopAuditEntryId);
	}

	@Override
	public Map<Serializable, LoopAuditEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopAuditEntry> map = new HashMap<Serializable, LoopAuditEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopAuditEntry loopAuditEntry = fetchByPrimaryKey(primaryKey);

			if (loopAuditEntry != null) {
				map.put(primaryKey, loopAuditEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopAuditEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopAuditEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPAUDITENTRY_WHERE_PKS_IN);

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

			for (LoopAuditEntry loopAuditEntry : (List<LoopAuditEntry>)q.list()) {
				map.put(loopAuditEntry.getPrimaryKeyObj(), loopAuditEntry);

				cacheResult(loopAuditEntry);

				uncachedPrimaryKeys.remove(loopAuditEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopAuditEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopAuditEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop audit entries.
	 *
	 * @return the loop audit entries
	 */
	@Override
	public List<LoopAuditEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @return the range of loop audit entries
	 */
	@Override
	public List<LoopAuditEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop audit entries
	 */
	@Override
	public List<LoopAuditEntry> findAll(int start, int end,
		OrderByComparator<LoopAuditEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop audit entries
	 */
	@Override
	public List<LoopAuditEntry> findAll(int start, int end,
		OrderByComparator<LoopAuditEntry> orderByComparator,
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

		List<LoopAuditEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LoopAuditEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPAUDITENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPAUDITENTRY;

				if (pagination) {
					sql = sql.concat(LoopAuditEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopAuditEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopAuditEntry>)QueryUtil.list(q,
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
	 * Removes all the loop audit entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopAuditEntry loopAuditEntry : findAll()) {
			remove(loopAuditEntry);
		}
	}

	/**
	 * Returns the number of loop audit entries.
	 *
	 * @return the number of loop audit entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPAUDITENTRY);

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
		return LoopAuditEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop audit entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopAuditEntryImpl.class.getName());
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
	private static final String _SQL_SELECT_LOOPAUDITENTRY = "SELECT loopAuditEntry FROM LoopAuditEntry loopAuditEntry";
	private static final String _SQL_SELECT_LOOPAUDITENTRY_WHERE_PKS_IN = "SELECT loopAuditEntry FROM LoopAuditEntry loopAuditEntry WHERE loopAuditEntryId IN (";
	private static final String _SQL_COUNT_LOOPAUDITENTRY = "SELECT COUNT(loopAuditEntry) FROM LoopAuditEntry loopAuditEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopAuditEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopAuditEntry exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LoopAuditEntryPersistenceImpl.class);
}
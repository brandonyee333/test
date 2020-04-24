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

import com.liferay.osb.loop.exception.NoSuchLoopUserNotificationRecordException;
import com.liferay.osb.loop.model.LoopUserNotificationRecord;
import com.liferay.osb.loop.model.impl.LoopUserNotificationRecordImpl;
import com.liferay.osb.loop.model.impl.LoopUserNotificationRecordModelImpl;
import com.liferay.osb.loop.service.persistence.LoopUserNotificationRecordPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the loop user notification record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationRecordPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopUserNotificationRecordUtil
 * @generated
 */
@ProviderType
public class LoopUserNotificationRecordPersistenceImpl
	extends BasePersistenceImpl<LoopUserNotificationRecord>
	implements LoopUserNotificationRecordPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopUserNotificationRecordUtil} to access the loop user notification record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopUserNotificationRecordImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationRecordModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationRecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationRecordModelImpl.FINDER_CACHE_ENABLED,
			LoopUserNotificationRecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationRecordModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public LoopUserNotificationRecordPersistenceImpl() {
		setModelClass(LoopUserNotificationRecord.class);
	}

	/**
	 * Caches the loop user notification record in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationRecord the loop user notification record
	 */
	@Override
	public void cacheResult(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		entityCache.putResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationRecordImpl.class,
			loopUserNotificationRecord.getPrimaryKey(),
			loopUserNotificationRecord);

		loopUserNotificationRecord.resetOriginalValues();
	}

	/**
	 * Caches the loop user notification records in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationRecords the loop user notification records
	 */
	@Override
	public void cacheResult(
		List<LoopUserNotificationRecord> loopUserNotificationRecords) {
		for (LoopUserNotificationRecord loopUserNotificationRecord : loopUserNotificationRecords) {
			if (entityCache.getResult(
						LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
						LoopUserNotificationRecordImpl.class,
						loopUserNotificationRecord.getPrimaryKey()) == null) {
				cacheResult(loopUserNotificationRecord);
			}
			else {
				loopUserNotificationRecord.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop user notification records.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopUserNotificationRecordImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop user notification record.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		entityCache.removeResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationRecordImpl.class,
			loopUserNotificationRecord.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<LoopUserNotificationRecord> loopUserNotificationRecords) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopUserNotificationRecord loopUserNotificationRecord : loopUserNotificationRecords) {
			entityCache.removeResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
				LoopUserNotificationRecordImpl.class,
				loopUserNotificationRecord.getPrimaryKey());
		}
	}

	/**
	 * Creates a new loop user notification record with the primary key. Does not add the loop user notification record to the database.
	 *
	 * @param loopUserNotificationRecordId the primary key for the new loop user notification record
	 * @return the new loop user notification record
	 */
	@Override
	public LoopUserNotificationRecord create(long loopUserNotificationRecordId) {
		LoopUserNotificationRecord loopUserNotificationRecord = new LoopUserNotificationRecordImpl();

		loopUserNotificationRecord.setNew(true);
		loopUserNotificationRecord.setPrimaryKey(loopUserNotificationRecordId);

		return loopUserNotificationRecord;
	}

	/**
	 * Removes the loop user notification record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationRecordId the primary key of the loop user notification record
	 * @return the loop user notification record that was removed
	 * @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationRecord remove(long loopUserNotificationRecordId)
		throws NoSuchLoopUserNotificationRecordException {
		return remove((Serializable)loopUserNotificationRecordId);
	}

	/**
	 * Removes the loop user notification record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop user notification record
	 * @return the loop user notification record that was removed
	 * @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationRecord remove(Serializable primaryKey)
		throws NoSuchLoopUserNotificationRecordException {
		Session session = null;

		try {
			session = openSession();

			LoopUserNotificationRecord loopUserNotificationRecord = (LoopUserNotificationRecord)session.get(LoopUserNotificationRecordImpl.class,
					primaryKey);

			if (loopUserNotificationRecord == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopUserNotificationRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopUserNotificationRecord);
		}
		catch (NoSuchLoopUserNotificationRecordException nsee) {
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
	protected LoopUserNotificationRecord removeImpl(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		loopUserNotificationRecord = toUnwrappedModel(loopUserNotificationRecord);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopUserNotificationRecord)) {
				loopUserNotificationRecord = (LoopUserNotificationRecord)session.get(LoopUserNotificationRecordImpl.class,
						loopUserNotificationRecord.getPrimaryKeyObj());
			}

			if (loopUserNotificationRecord != null) {
				session.delete(loopUserNotificationRecord);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopUserNotificationRecord != null) {
			clearCache(loopUserNotificationRecord);
		}

		return loopUserNotificationRecord;
	}

	@Override
	public LoopUserNotificationRecord updateImpl(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		loopUserNotificationRecord = toUnwrappedModel(loopUserNotificationRecord);

		boolean isNew = loopUserNotificationRecord.isNew();

		Session session = null;

		try {
			session = openSession();

			if (loopUserNotificationRecord.isNew()) {
				session.save(loopUserNotificationRecord);

				loopUserNotificationRecord.setNew(false);
			}
			else {
				loopUserNotificationRecord = (LoopUserNotificationRecord)session.merge(loopUserNotificationRecord);
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

		entityCache.putResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
			LoopUserNotificationRecordImpl.class,
			loopUserNotificationRecord.getPrimaryKey(),
			loopUserNotificationRecord, false);

		loopUserNotificationRecord.resetOriginalValues();

		return loopUserNotificationRecord;
	}

	protected LoopUserNotificationRecord toUnwrappedModel(
		LoopUserNotificationRecord loopUserNotificationRecord) {
		if (loopUserNotificationRecord instanceof LoopUserNotificationRecordImpl) {
			return loopUserNotificationRecord;
		}

		LoopUserNotificationRecordImpl loopUserNotificationRecordImpl = new LoopUserNotificationRecordImpl();

		loopUserNotificationRecordImpl.setNew(loopUserNotificationRecord.isNew());
		loopUserNotificationRecordImpl.setPrimaryKey(loopUserNotificationRecord.getPrimaryKey());

		loopUserNotificationRecordImpl.setLoopUserNotificationRecordId(loopUserNotificationRecord.getLoopUserNotificationRecordId());
		loopUserNotificationRecordImpl.setUserId(loopUserNotificationRecord.getUserId());
		loopUserNotificationRecordImpl.setCreateTime(loopUserNotificationRecord.getCreateTime());
		loopUserNotificationRecordImpl.setClassNameId(loopUserNotificationRecord.getClassNameId());
		loopUserNotificationRecordImpl.setClassPK(loopUserNotificationRecord.getClassPK());
		loopUserNotificationRecordImpl.setDeliveryType(loopUserNotificationRecord.getDeliveryType());

		return loopUserNotificationRecordImpl;
	}

	/**
	 * Returns the loop user notification record with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop user notification record
	 * @return the loop user notification record
	 * @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationRecord findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopUserNotificationRecordException {
		LoopUserNotificationRecord loopUserNotificationRecord = fetchByPrimaryKey(primaryKey);

		if (loopUserNotificationRecord == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopUserNotificationRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopUserNotificationRecord;
	}

	/**
	 * Returns the loop user notification record with the primary key or throws a {@link NoSuchLoopUserNotificationRecordException} if it could not be found.
	 *
	 * @param loopUserNotificationRecordId the primary key of the loop user notification record
	 * @return the loop user notification record
	 * @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationRecord findByPrimaryKey(
		long loopUserNotificationRecordId)
		throws NoSuchLoopUserNotificationRecordException {
		return findByPrimaryKey((Serializable)loopUserNotificationRecordId);
	}

	/**
	 * Returns the loop user notification record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop user notification record
	 * @return the loop user notification record, or <code>null</code> if a loop user notification record with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationRecord fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
				LoopUserNotificationRecordImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopUserNotificationRecord loopUserNotificationRecord = (LoopUserNotificationRecord)serializable;

		if (loopUserNotificationRecord == null) {
			Session session = null;

			try {
				session = openSession();

				loopUserNotificationRecord = (LoopUserNotificationRecord)session.get(LoopUserNotificationRecordImpl.class,
						primaryKey);

				if (loopUserNotificationRecord != null) {
					cacheResult(loopUserNotificationRecord);
				}
				else {
					entityCache.putResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
						LoopUserNotificationRecordImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
					LoopUserNotificationRecordImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopUserNotificationRecord;
	}

	/**
	 * Returns the loop user notification record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopUserNotificationRecordId the primary key of the loop user notification record
	 * @return the loop user notification record, or <code>null</code> if a loop user notification record with the primary key could not be found
	 */
	@Override
	public LoopUserNotificationRecord fetchByPrimaryKey(
		long loopUserNotificationRecordId) {
		return fetchByPrimaryKey((Serializable)loopUserNotificationRecordId);
	}

	@Override
	public Map<Serializable, LoopUserNotificationRecord> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopUserNotificationRecord> map = new HashMap<Serializable, LoopUserNotificationRecord>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopUserNotificationRecord loopUserNotificationRecord = fetchByPrimaryKey(primaryKey);

			if (loopUserNotificationRecord != null) {
				map.put(primaryKey, loopUserNotificationRecord);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
					LoopUserNotificationRecordImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopUserNotificationRecord)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONRECORD_WHERE_PKS_IN);

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

			for (LoopUserNotificationRecord loopUserNotificationRecord : (List<LoopUserNotificationRecord>)q.list()) {
				map.put(loopUserNotificationRecord.getPrimaryKeyObj(),
					loopUserNotificationRecord);

				cacheResult(loopUserNotificationRecord);

				uncachedPrimaryKeys.remove(loopUserNotificationRecord.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopUserNotificationRecordModelImpl.ENTITY_CACHE_ENABLED,
					LoopUserNotificationRecordImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop user notification records.
	 *
	 * @return the loop user notification records
	 */
	@Override
	public List<LoopUserNotificationRecord> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop user notification records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @return the range of loop user notification records
	 */
	@Override
	public List<LoopUserNotificationRecord> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop user notification records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop user notification records
	 */
	@Override
	public List<LoopUserNotificationRecord> findAll(int start, int end,
		OrderByComparator<LoopUserNotificationRecord> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop user notification records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop user notification records
	 */
	@Override
	public List<LoopUserNotificationRecord> findAll(int start, int end,
		OrderByComparator<LoopUserNotificationRecord> orderByComparator,
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

		List<LoopUserNotificationRecord> list = null;

		if (retrieveFromCache) {
			list = (List<LoopUserNotificationRecord>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPUSERNOTIFICATIONRECORD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPUSERNOTIFICATIONRECORD;

				if (pagination) {
					sql = sql.concat(LoopUserNotificationRecordModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopUserNotificationRecord>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopUserNotificationRecord>)QueryUtil.list(q,
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
	 * Removes all the loop user notification records from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopUserNotificationRecord loopUserNotificationRecord : findAll()) {
			remove(loopUserNotificationRecord);
		}
	}

	/**
	 * Returns the number of loop user notification records.
	 *
	 * @return the number of loop user notification records
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPUSERNOTIFICATIONRECORD);

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
		return LoopUserNotificationRecordModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop user notification record persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopUserNotificationRecordImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPUSERNOTIFICATIONRECORD = "SELECT loopUserNotificationRecord FROM LoopUserNotificationRecord loopUserNotificationRecord";
	private static final String _SQL_SELECT_LOOPUSERNOTIFICATIONRECORD_WHERE_PKS_IN =
		"SELECT loopUserNotificationRecord FROM LoopUserNotificationRecord loopUserNotificationRecord WHERE loopUserNotificationRecordId IN (";
	private static final String _SQL_COUNT_LOOPUSERNOTIFICATIONRECORD = "SELECT COUNT(loopUserNotificationRecord) FROM LoopUserNotificationRecord loopUserNotificationRecord";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopUserNotificationRecord.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopUserNotificationRecord exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LoopUserNotificationRecordPersistenceImpl.class);
}
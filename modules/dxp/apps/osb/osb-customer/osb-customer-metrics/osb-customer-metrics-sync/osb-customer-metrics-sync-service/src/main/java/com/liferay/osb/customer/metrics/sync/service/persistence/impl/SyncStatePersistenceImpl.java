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

package com.liferay.osb.customer.metrics.sync.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.metrics.sync.exception.NoSuchSyncStateException;
import com.liferay.osb.customer.metrics.sync.model.SyncState;
import com.liferay.osb.customer.metrics.sync.model.impl.SyncStateImpl;
import com.liferay.osb.customer.metrics.sync.model.impl.SyncStateModelImpl;
import com.liferay.osb.customer.metrics.sync.service.persistence.SyncStatePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the sync state service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncStatePersistence
 * @see com.liferay.osb.customer.metrics.sync.service.persistence.SyncStateUtil
 * @generated
 */
@ProviderType
public class SyncStatePersistenceImpl extends BasePersistenceImpl<SyncState>
	implements SyncStatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SyncStateUtil} to access the sync state persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SyncStateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByModel;
	private FinderPath _finderPathCountByModel;

	/**
	 * Returns the sync state where model = &#63; or throws a {@link NoSuchSyncStateException} if it could not be found.
	 *
	 * @param model the model
	 * @return the matching sync state
	 * @throws NoSuchSyncStateException if a matching sync state could not be found
	 */
	@Override
	public SyncState findByModel(String model) throws NoSuchSyncStateException {
		SyncState syncState = fetchByModel(model);

		if (syncState == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("model=");
			msg.append(model);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSyncStateException(msg.toString());
		}

		return syncState;
	}

	/**
	 * Returns the sync state where model = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param model the model
	 * @return the matching sync state, or <code>null</code> if a matching sync state could not be found
	 */
	@Override
	public SyncState fetchByModel(String model) {
		return fetchByModel(model, true);
	}

	/**
	 * Returns the sync state where model = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param model the model
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync state, or <code>null</code> if a matching sync state could not be found
	 */
	@Override
	public SyncState fetchByModel(String model, boolean retrieveFromCache) {
		model = Objects.toString(model, "");

		Object[] finderArgs = new Object[] { model };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(_finderPathFetchByModel, finderArgs,
					this);
		}

		if (result instanceof SyncState) {
			SyncState syncState = (SyncState)result;

			if (!Objects.equals(model, syncState.getModel())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SYNCSTATE_WHERE);

			boolean bindModel = false;

			if (model.isEmpty()) {
				query.append(_FINDER_COLUMN_MODEL_MODEL_3);
			}
			else {
				bindModel = true;

				query.append(_FINDER_COLUMN_MODEL_MODEL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModel) {
					qPos.add(model);
				}

				List<SyncState> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(_finderPathFetchByModel, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SyncStatePersistenceImpl.fetchByModel(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SyncState syncState = list.get(0);

					result = syncState;

					cacheResult(syncState);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByModel, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SyncState)result;
		}
	}

	/**
	 * Removes the sync state where model = &#63; from the database.
	 *
	 * @param model the model
	 * @return the sync state that was removed
	 */
	@Override
	public SyncState removeByModel(String model)
		throws NoSuchSyncStateException {
		SyncState syncState = findByModel(model);

		return remove(syncState);
	}

	/**
	 * Returns the number of sync states where model = &#63;.
	 *
	 * @param model the model
	 * @return the number of matching sync states
	 */
	@Override
	public int countByModel(String model) {
		model = Objects.toString(model, "");

		FinderPath finderPath = _finderPathCountByModel;

		Object[] finderArgs = new Object[] { model };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCSTATE_WHERE);

			boolean bindModel = false;

			if (model.isEmpty()) {
				query.append(_FINDER_COLUMN_MODEL_MODEL_3);
			}
			else {
				bindModel = true;

				query.append(_FINDER_COLUMN_MODEL_MODEL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModel) {
					qPos.add(model);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MODEL_MODEL_2 = "syncState.model = ?";
	private static final String _FINDER_COLUMN_MODEL_MODEL_3 = "(syncState.model IS NULL OR syncState.model = '')";

	public SyncStatePersistenceImpl() {
		setModelClass(SyncState.class);
	}

	/**
	 * Caches the sync state in the entity cache if it is enabled.
	 *
	 * @param syncState the sync state
	 */
	@Override
	public void cacheResult(SyncState syncState) {
		entityCache.putResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
			SyncStateImpl.class, syncState.getPrimaryKey(), syncState);

		finderCache.putResult(_finderPathFetchByModel,
			new Object[] { syncState.getModel() }, syncState);

		syncState.resetOriginalValues();
	}

	/**
	 * Caches the sync states in the entity cache if it is enabled.
	 *
	 * @param syncStates the sync states
	 */
	@Override
	public void cacheResult(List<SyncState> syncStates) {
		for (SyncState syncState : syncStates) {
			if (entityCache.getResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
						SyncStateImpl.class, syncState.getPrimaryKey()) == null) {
				cacheResult(syncState);
			}
			else {
				syncState.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sync states.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SyncStateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync state.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncState syncState) {
		entityCache.removeResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
			SyncStateImpl.class, syncState.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SyncStateModelImpl)syncState, true);
	}

	@Override
	public void clearCache(List<SyncState> syncStates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncState syncState : syncStates) {
			entityCache.removeResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
				SyncStateImpl.class, syncState.getPrimaryKey());

			clearUniqueFindersCache((SyncStateModelImpl)syncState, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SyncStateModelImpl syncStateModelImpl) {
		Object[] args = new Object[] { syncStateModelImpl.getModel() };

		finderCache.putResult(_finderPathCountByModel, args, Long.valueOf(1),
			false);
		finderCache.putResult(_finderPathFetchByModel, args,
			syncStateModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SyncStateModelImpl syncStateModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { syncStateModelImpl.getModel() };

			finderCache.removeResult(_finderPathCountByModel, args);
			finderCache.removeResult(_finderPathFetchByModel, args);
		}

		if ((syncStateModelImpl.getColumnBitmask() &
				_finderPathFetchByModel.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { syncStateModelImpl.getOriginalModel() };

			finderCache.removeResult(_finderPathCountByModel, args);
			finderCache.removeResult(_finderPathFetchByModel, args);
		}
	}

	/**
	 * Creates a new sync state with the primary key. Does not add the sync state to the database.
	 *
	 * @param syncStateId the primary key for the new sync state
	 * @return the new sync state
	 */
	@Override
	public SyncState create(long syncStateId) {
		SyncState syncState = new SyncStateImpl();

		syncState.setNew(true);
		syncState.setPrimaryKey(syncStateId);

		return syncState;
	}

	/**
	 * Removes the sync state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncStateId the primary key of the sync state
	 * @return the sync state that was removed
	 * @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	 */
	@Override
	public SyncState remove(long syncStateId) throws NoSuchSyncStateException {
		return remove((Serializable)syncStateId);
	}

	/**
	 * Removes the sync state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync state
	 * @return the sync state that was removed
	 * @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	 */
	@Override
	public SyncState remove(Serializable primaryKey)
		throws NoSuchSyncStateException {
		Session session = null;

		try {
			session = openSession();

			SyncState syncState = (SyncState)session.get(SyncStateImpl.class,
					primaryKey);

			if (syncState == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSyncStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(syncState);
		}
		catch (NoSuchSyncStateException nsee) {
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
	protected SyncState removeImpl(SyncState syncState) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(syncState)) {
				syncState = (SyncState)session.get(SyncStateImpl.class,
						syncState.getPrimaryKeyObj());
			}

			if (syncState != null) {
				session.delete(syncState);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (syncState != null) {
			clearCache(syncState);
		}

		return syncState;
	}

	@Override
	public SyncState updateImpl(SyncState syncState) {
		boolean isNew = syncState.isNew();

		if (!(syncState instanceof SyncStateModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(syncState.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(syncState);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in syncState proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SyncState implementation " +
				syncState.getClass());
		}

		SyncStateModelImpl syncStateModelImpl = (SyncStateModelImpl)syncState;

		Session session = null;

		try {
			session = openSession();

			if (syncState.isNew()) {
				session.save(syncState);

				syncState.setNew(false);
			}
			else {
				syncState = (SyncState)session.merge(syncState);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SyncStateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(_finderPathWithoutPaginationFindAll,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
			SyncStateImpl.class, syncState.getPrimaryKey(), syncState, false);

		clearUniqueFindersCache(syncStateModelImpl, false);
		cacheUniqueFindersCache(syncStateModelImpl);

		syncState.resetOriginalValues();

		return syncState;
	}

	/**
	 * Returns the sync state with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync state
	 * @return the sync state
	 * @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	 */
	@Override
	public SyncState findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSyncStateException {
		SyncState syncState = fetchByPrimaryKey(primaryKey);

		if (syncState == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSyncStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return syncState;
	}

	/**
	 * Returns the sync state with the primary key or throws a {@link NoSuchSyncStateException} if it could not be found.
	 *
	 * @param syncStateId the primary key of the sync state
	 * @return the sync state
	 * @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	 */
	@Override
	public SyncState findByPrimaryKey(long syncStateId)
		throws NoSuchSyncStateException {
		return findByPrimaryKey((Serializable)syncStateId);
	}

	/**
	 * Returns the sync state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync state
	 * @return the sync state, or <code>null</code> if a sync state with the primary key could not be found
	 */
	@Override
	public SyncState fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
				SyncStateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SyncState syncState = (SyncState)serializable;

		if (syncState == null) {
			Session session = null;

			try {
				session = openSession();

				syncState = (SyncState)session.get(SyncStateImpl.class,
						primaryKey);

				if (syncState != null) {
					cacheResult(syncState);
				}
				else {
					entityCache.putResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
						SyncStateImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
					SyncStateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return syncState;
	}

	/**
	 * Returns the sync state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param syncStateId the primary key of the sync state
	 * @return the sync state, or <code>null</code> if a sync state with the primary key could not be found
	 */
	@Override
	public SyncState fetchByPrimaryKey(long syncStateId) {
		return fetchByPrimaryKey((Serializable)syncStateId);
	}

	@Override
	public Map<Serializable, SyncState> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SyncState> map = new HashMap<Serializable, SyncState>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SyncState syncState = fetchByPrimaryKey(primaryKey);

			if (syncState != null) {
				map.put(primaryKey, syncState);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
					SyncStateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SyncState)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SYNCSTATE_WHERE_PKS_IN);

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

			for (SyncState syncState : (List<SyncState>)q.list()) {
				map.put(syncState.getPrimaryKeyObj(), syncState);

				cacheResult(syncState);

				uncachedPrimaryKeys.remove(syncState.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
					SyncStateImpl.class, primaryKey, nullModel);
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
	 * Returns all the sync states.
	 *
	 * @return the sync states
	 */
	@Override
	public List<SyncState> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync states
	 * @param end the upper bound of the range of sync states (not inclusive)
	 * @return the range of sync states
	 */
	@Override
	public List<SyncState> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync states
	 * @param end the upper bound of the range of sync states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync states
	 */
	@Override
	public List<SyncState> findAll(int start, int end,
		OrderByComparator<SyncState> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync states
	 * @param end the upper bound of the range of sync states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sync states
	 */
	@Override
	public List<SyncState> findAll(int start, int end,
		OrderByComparator<SyncState> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SyncState> list = null;

		if (retrieveFromCache) {
			list = (List<SyncState>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SYNCSTATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYNCSTATE;

				if (pagination) {
					sql = sql.concat(SyncStateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SyncState>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncState>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sync states from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SyncState syncState : findAll()) {
			remove(syncState);
		}
	}

	/**
	 * Returns the number of sync states.
	 *
	 * @return the number of sync states
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(_finderPathCountAll,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SYNCSTATE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(_finderPathCountAll, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);

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
		return SyncStateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sync state persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
				SyncStateModelImpl.FINDER_CACHE_ENABLED, SyncStateImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
				SyncStateModelImpl.FINDER_CACHE_ENABLED, SyncStateImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
				new String[0]);

		_finderPathCountAll = new FinderPath(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
				SyncStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
				new String[0]);

		_finderPathFetchByModel = new FinderPath(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
				SyncStateModelImpl.FINDER_CACHE_ENABLED, SyncStateImpl.class,
				FINDER_CLASS_NAME_ENTITY, "fetchByModel",
				new String[] { String.class.getName() },
				SyncStateModelImpl.MODEL_COLUMN_BITMASK);

		_finderPathCountByModel = new FinderPath(SyncStateModelImpl.ENTITY_CACHE_ENABLED,
				SyncStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModel",
				new String[] { String.class.getName() });
	}

	public void destroy() {
		entityCache.removeCache(SyncStateImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SYNCSTATE = "SELECT syncState FROM SyncState syncState";
	private static final String _SQL_SELECT_SYNCSTATE_WHERE_PKS_IN = "SELECT syncState FROM SyncState syncState WHERE syncStateId IN (";
	private static final String _SQL_SELECT_SYNCSTATE_WHERE = "SELECT syncState FROM SyncState syncState WHERE ";
	private static final String _SQL_COUNT_SYNCSTATE = "SELECT COUNT(syncState) FROM SyncState syncState";
	private static final String _SQL_COUNT_SYNCSTATE_WHERE = "SELECT COUNT(syncState) FROM SyncState syncState WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncState.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncState exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncState exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SyncStatePersistenceImpl.class);
}
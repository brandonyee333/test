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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchTicketCallException;
import com.liferay.osb.model.TicketCall;
import com.liferay.osb.model.impl.TicketCallImpl;
import com.liferay.osb.model.impl.TicketCallModelImpl;
import com.liferay.osb.service.persistence.TicketCallPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ticket call service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCallPersistence
 * @see com.liferay.osb.service.persistence.TicketCallUtil
 * @generated
 */
@ProviderType
public class TicketCallPersistenceImpl extends BasePersistenceImpl<TicketCall>
	implements TicketCallPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketCallUtil} to access the ticket call persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketCallImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
			TicketCallModelImpl.FINDER_CACHE_ENABLED, TicketCallImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
			TicketCallModelImpl.FINDER_CACHE_ENABLED, TicketCallImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
			TicketCallModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public TicketCallPersistenceImpl() {
		setModelClass(TicketCall.class);
	}

	/**
	 * Caches the ticket call in the entity cache if it is enabled.
	 *
	 * @param ticketCall the ticket call
	 */
	@Override
	public void cacheResult(TicketCall ticketCall) {
		entityCache.putResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
			TicketCallImpl.class, ticketCall.getPrimaryKey(), ticketCall);

		ticketCall.resetOriginalValues();
	}

	/**
	 * Caches the ticket calls in the entity cache if it is enabled.
	 *
	 * @param ticketCalls the ticket calls
	 */
	@Override
	public void cacheResult(List<TicketCall> ticketCalls) {
		for (TicketCall ticketCall : ticketCalls) {
			if (entityCache.getResult(
						TicketCallModelImpl.ENTITY_CACHE_ENABLED,
						TicketCallImpl.class, ticketCall.getPrimaryKey()) == null) {
				cacheResult(ticketCall);
			}
			else {
				ticketCall.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket calls.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketCallImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket call.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketCall ticketCall) {
		entityCache.removeResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
			TicketCallImpl.class, ticketCall.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TicketCall> ticketCalls) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketCall ticketCall : ticketCalls) {
			entityCache.removeResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
				TicketCallImpl.class, ticketCall.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ticket call with the primary key. Does not add the ticket call to the database.
	 *
	 * @param ticketCallId the primary key for the new ticket call
	 * @return the new ticket call
	 */
	@Override
	public TicketCall create(long ticketCallId) {
		TicketCall ticketCall = new TicketCallImpl();

		ticketCall.setNew(true);
		ticketCall.setPrimaryKey(ticketCallId);

		return ticketCall;
	}

	/**
	 * Removes the ticket call with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketCallId the primary key of the ticket call
	 * @return the ticket call that was removed
	 * @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	 */
	@Override
	public TicketCall remove(long ticketCallId)
		throws NoSuchTicketCallException {
		return remove((Serializable)ticketCallId);
	}

	/**
	 * Removes the ticket call with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket call
	 * @return the ticket call that was removed
	 * @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	 */
	@Override
	public TicketCall remove(Serializable primaryKey)
		throws NoSuchTicketCallException {
		Session session = null;

		try {
			session = openSession();

			TicketCall ticketCall = (TicketCall)session.get(TicketCallImpl.class,
					primaryKey);

			if (ticketCall == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketCallException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketCall);
		}
		catch (NoSuchTicketCallException nsee) {
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
	protected TicketCall removeImpl(TicketCall ticketCall) {
		ticketCall = toUnwrappedModel(ticketCall);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticketCall)) {
				ticketCall = (TicketCall)session.get(TicketCallImpl.class,
						ticketCall.getPrimaryKeyObj());
			}

			if (ticketCall != null) {
				session.delete(ticketCall);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ticketCall != null) {
			clearCache(ticketCall);
		}

		return ticketCall;
	}

	@Override
	public TicketCall updateImpl(TicketCall ticketCall) {
		ticketCall = toUnwrappedModel(ticketCall);

		boolean isNew = ticketCall.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ticketCall.isNew()) {
				session.save(ticketCall);

				ticketCall.setNew(false);
			}
			else {
				ticketCall = (TicketCall)session.merge(ticketCall);
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

		entityCache.putResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
			TicketCallImpl.class, ticketCall.getPrimaryKey(), ticketCall, false);

		ticketCall.resetOriginalValues();

		return ticketCall;
	}

	protected TicketCall toUnwrappedModel(TicketCall ticketCall) {
		if (ticketCall instanceof TicketCallImpl) {
			return ticketCall;
		}

		TicketCallImpl ticketCallImpl = new TicketCallImpl();

		ticketCallImpl.setNew(ticketCall.isNew());
		ticketCallImpl.setPrimaryKey(ticketCall.getPrimaryKey());

		ticketCallImpl.setTicketCallId(ticketCall.getTicketCallId());
		ticketCallImpl.setUserId(ticketCall.getUserId());
		ticketCallImpl.setUserName(ticketCall.getUserName());
		ticketCallImpl.setCreateDate(ticketCall.getCreateDate());
		ticketCallImpl.setTicketEntryId(ticketCall.getTicketEntryId());
		ticketCallImpl.setType(ticketCall.getType());
		ticketCallImpl.setCallDate(ticketCall.getCallDate());
		ticketCallImpl.setCallLength(ticketCall.getCallLength());
		ticketCallImpl.setCustomerName(ticketCall.getCustomerName());
		ticketCallImpl.setCustomerContact(ticketCall.getCustomerContact());
		ticketCallImpl.setConfirmation(ticketCall.getConfirmation());
		ticketCallImpl.setInstructions(ticketCall.getInstructions());

		return ticketCallImpl;
	}

	/**
	 * Returns the ticket call with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket call
	 * @return the ticket call
	 * @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	 */
	@Override
	public TicketCall findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketCallException {
		TicketCall ticketCall = fetchByPrimaryKey(primaryKey);

		if (ticketCall == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketCallException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ticketCall;
	}

	/**
	 * Returns the ticket call with the primary key or throws a {@link NoSuchTicketCallException} if it could not be found.
	 *
	 * @param ticketCallId the primary key of the ticket call
	 * @return the ticket call
	 * @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	 */
	@Override
	public TicketCall findByPrimaryKey(long ticketCallId)
		throws NoSuchTicketCallException {
		return findByPrimaryKey((Serializable)ticketCallId);
	}

	/**
	 * Returns the ticket call with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket call
	 * @return the ticket call, or <code>null</code> if a ticket call with the primary key could not be found
	 */
	@Override
	public TicketCall fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
				TicketCallImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TicketCall ticketCall = (TicketCall)serializable;

		if (ticketCall == null) {
			Session session = null;

			try {
				session = openSession();

				ticketCall = (TicketCall)session.get(TicketCallImpl.class,
						primaryKey);

				if (ticketCall != null) {
					cacheResult(ticketCall);
				}
				else {
					entityCache.putResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
						TicketCallImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
					TicketCallImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ticketCall;
	}

	/**
	 * Returns the ticket call with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketCallId the primary key of the ticket call
	 * @return the ticket call, or <code>null</code> if a ticket call with the primary key could not be found
	 */
	@Override
	public TicketCall fetchByPrimaryKey(long ticketCallId) {
		return fetchByPrimaryKey((Serializable)ticketCallId);
	}

	@Override
	public Map<Serializable, TicketCall> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TicketCall> map = new HashMap<Serializable, TicketCall>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TicketCall ticketCall = fetchByPrimaryKey(primaryKey);

			if (ticketCall != null) {
				map.put(primaryKey, ticketCall);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
					TicketCallImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TicketCall)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TICKETCALL_WHERE_PKS_IN);

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

			for (TicketCall ticketCall : (List<TicketCall>)q.list()) {
				map.put(ticketCall.getPrimaryKeyObj(), ticketCall);

				cacheResult(ticketCall);

				uncachedPrimaryKeys.remove(ticketCall.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TicketCallModelImpl.ENTITY_CACHE_ENABLED,
					TicketCallImpl.class, primaryKey, nullModel);
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
	 * Returns all the ticket calls.
	 *
	 * @return the ticket calls
	 */
	@Override
	public List<TicketCall> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket calls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket calls
	 * @param end the upper bound of the range of ticket calls (not inclusive)
	 * @return the range of ticket calls
	 */
	@Override
	public List<TicketCall> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket calls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket calls
	 * @param end the upper bound of the range of ticket calls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket calls
	 */
	@Override
	public List<TicketCall> findAll(int start, int end,
		OrderByComparator<TicketCall> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ticket calls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket calls
	 * @param end the upper bound of the range of ticket calls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ticket calls
	 */
	@Override
	public List<TicketCall> findAll(int start, int end,
		OrderByComparator<TicketCall> orderByComparator,
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

		List<TicketCall> list = null;

		if (retrieveFromCache) {
			list = (List<TicketCall>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TICKETCALL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETCALL;

				if (pagination) {
					sql = sql.concat(TicketCallModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TicketCall>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TicketCall>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ticket calls from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TicketCall ticketCall : findAll()) {
			remove(ticketCall);
		}
	}

	/**
	 * Returns the number of ticket calls.
	 *
	 * @return the number of ticket calls
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETCALL);

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
		return TicketCallModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket call persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TicketCallImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_TICKETCALL = "SELECT ticketCall FROM TicketCall ticketCall";
	private static final String _SQL_SELECT_TICKETCALL_WHERE_PKS_IN = "SELECT ticketCall FROM TicketCall ticketCall WHERE ticketCallId IN (";
	private static final String _SQL_COUNT_TICKETCALL = "SELECT COUNT(ticketCall) FROM TicketCall ticketCall";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketCall.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketCall exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(TicketCallPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
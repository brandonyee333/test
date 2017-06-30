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

package com.liferay.osb.service.persistence;

import com.liferay.osb.NoSuchTicketSolutionException;
import com.liferay.osb.model.TicketSolution;
import com.liferay.osb.model.impl.TicketSolutionImpl;
import com.liferay.osb.model.impl.TicketSolutionModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the ticket solution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionPersistence
 * @see TicketSolutionUtil
 * @generated
 */
public class TicketSolutionPersistenceImpl extends BasePersistenceImpl<TicketSolution>
	implements TicketSolutionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketSolutionUtil} to access the ticket solution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketSolutionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketSolutionModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED,
			TicketSolutionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket solution in the entity cache if it is enabled.
	 *
	 * @param ticketSolution the ticket solution
	 */
	public void cacheResult(TicketSolution ticketSolution) {
		EntityCacheUtil.putResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionImpl.class, ticketSolution.getPrimaryKey(),
			ticketSolution);

		ticketSolution.resetOriginalValues();
	}

	/**
	 * Caches the ticket solutions in the entity cache if it is enabled.
	 *
	 * @param ticketSolutions the ticket solutions
	 */
	public void cacheResult(List<TicketSolution> ticketSolutions) {
		for (TicketSolution ticketSolution : ticketSolutions) {
			if (EntityCacheUtil.getResult(
						TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
						TicketSolutionImpl.class, ticketSolution.getPrimaryKey()) == null) {
				cacheResult(ticketSolution);
			}
			else {
				ticketSolution.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket solutions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketSolutionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketSolutionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket solution.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketSolution ticketSolution) {
		EntityCacheUtil.removeResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionImpl.class, ticketSolution.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TicketSolution> ticketSolutions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketSolution ticketSolution : ticketSolutions) {
			EntityCacheUtil.removeResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
				TicketSolutionImpl.class, ticketSolution.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ticket solution with the primary key. Does not add the ticket solution to the database.
	 *
	 * @param ticketSolutionId the primary key for the new ticket solution
	 * @return the new ticket solution
	 */
	public TicketSolution create(long ticketSolutionId) {
		TicketSolution ticketSolution = new TicketSolutionImpl();

		ticketSolution.setNew(true);
		ticketSolution.setPrimaryKey(ticketSolutionId);

		return ticketSolution;
	}

	/**
	 * Removes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketSolutionId the primary key of the ticket solution
	 * @return the ticket solution that was removed
	 * @throws com.liferay.osb.NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution remove(long ticketSolutionId)
		throws NoSuchTicketSolutionException, SystemException {
		return remove(Long.valueOf(ticketSolutionId));
	}

	/**
	 * Removes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket solution
	 * @return the ticket solution that was removed
	 * @throws com.liferay.osb.NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketSolution remove(Serializable primaryKey)
		throws NoSuchTicketSolutionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketSolution ticketSolution = (TicketSolution)session.get(TicketSolutionImpl.class,
					primaryKey);

			if (ticketSolution == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketSolutionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketSolution);
		}
		catch (NoSuchTicketSolutionException nsee) {
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
	protected TicketSolution removeImpl(TicketSolution ticketSolution)
		throws SystemException {
		ticketSolution = toUnwrappedModel(ticketSolution);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketSolution);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketSolution);

		return ticketSolution;
	}

	@Override
	public TicketSolution updateImpl(
		com.liferay.osb.model.TicketSolution ticketSolution, boolean merge)
		throws SystemException {
		ticketSolution = toUnwrappedModel(ticketSolution);

		boolean isNew = ticketSolution.isNew();

		TicketSolutionModelImpl ticketSolutionModelImpl = (TicketSolutionModelImpl)ticketSolution;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketSolution, merge);

			ticketSolution.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketSolutionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketSolutionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketSolutionModelImpl.getOriginalTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketSolutionModelImpl.getTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
			TicketSolutionImpl.class, ticketSolution.getPrimaryKey(),
			ticketSolution);

		return ticketSolution;
	}

	protected TicketSolution toUnwrappedModel(TicketSolution ticketSolution) {
		if (ticketSolution instanceof TicketSolutionImpl) {
			return ticketSolution;
		}

		TicketSolutionImpl ticketSolutionImpl = new TicketSolutionImpl();

		ticketSolutionImpl.setNew(ticketSolution.isNew());
		ticketSolutionImpl.setPrimaryKey(ticketSolution.getPrimaryKey());

		ticketSolutionImpl.setTicketSolutionId(ticketSolution.getTicketSolutionId());
		ticketSolutionImpl.setUserId(ticketSolution.getUserId());
		ticketSolutionImpl.setUserName(ticketSolution.getUserName());
		ticketSolutionImpl.setCreateDate(ticketSolution.getCreateDate());
		ticketSolutionImpl.setTicketEntryId(ticketSolution.getTicketEntryId());
		ticketSolutionImpl.setSummary(ticketSolution.getSummary());
		ticketSolutionImpl.setUseCustomerSummary(ticketSolution.isUseCustomerSummary());
		ticketSolutionImpl.setIssueType(ticketSolution.getIssueType());
		ticketSolutionImpl.setSolution(ticketSolution.getSolution());
		ticketSolutionImpl.setType(ticketSolution.getType());
		ticketSolutionImpl.setCustomerSpecific(ticketSolution.isCustomerSpecific());
		ticketSolutionImpl.setEnvironmentSpecific(ticketSolution.isEnvironmentSpecific());
		ticketSolutionImpl.setVersionSpecific(ticketSolution.isVersionSpecific());
		ticketSolutionImpl.setReviewForKB(ticketSolution.isReviewForKB());
		ticketSolutionImpl.setStatus(ticketSolution.getStatus());
		ticketSolutionImpl.setStatusByUserId(ticketSolution.getStatusByUserId());
		ticketSolutionImpl.setStatusByUserName(ticketSolution.getStatusByUserName());
		ticketSolutionImpl.setStatusDate(ticketSolution.getStatusDate());
		ticketSolutionImpl.setStatusMessage(ticketSolution.getStatusMessage());
		ticketSolutionImpl.setStatusReason(ticketSolution.getStatusReason());

		return ticketSolutionImpl;
	}

	/**
	 * Returns the ticket solution with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket solution
	 * @return the ticket solution
	 * @throws com.liferay.portal.NoSuchModelException if a ticket solution with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketSolution findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket solution with the primary key or throws a {@link com.liferay.osb.NoSuchTicketSolutionException} if it could not be found.
	 *
	 * @param ticketSolutionId the primary key of the ticket solution
	 * @return the ticket solution
	 * @throws com.liferay.osb.NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution findByPrimaryKey(long ticketSolutionId)
		throws NoSuchTicketSolutionException, SystemException {
		TicketSolution ticketSolution = fetchByPrimaryKey(ticketSolutionId);

		if (ticketSolution == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ticketSolutionId);
			}

			throw new NoSuchTicketSolutionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketSolutionId);
		}

		return ticketSolution;
	}

	/**
	 * Returns the ticket solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket solution
	 * @return the ticket solution, or <code>null</code> if a ticket solution with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketSolution fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketSolutionId the primary key of the ticket solution
	 * @return the ticket solution, or <code>null</code> if a ticket solution with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution fetchByPrimaryKey(long ticketSolutionId)
		throws SystemException {
		TicketSolution ticketSolution = (TicketSolution)EntityCacheUtil.getResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
				TicketSolutionImpl.class, ticketSolutionId);

		if (ticketSolution == _nullTicketSolution) {
			return null;
		}

		if (ticketSolution == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketSolution = (TicketSolution)session.get(TicketSolutionImpl.class,
						Long.valueOf(ticketSolutionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketSolution != null) {
					cacheResult(ticketSolution);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketSolutionModelImpl.ENTITY_CACHE_ENABLED,
						TicketSolutionImpl.class, ticketSolutionId,
						_nullTicketSolution);
				}

				closeSession(session);
			}
		}

		return ticketSolution;
	}

	/**
	 * Returns all the ticket solutions where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketSolution> findByTicketEntryId(long ticketEntryId)
		throws SystemException {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket solutions where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @return the range of matching ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketSolution> findByTicketEntryId(long ticketEntryId,
		int start, int end) throws SystemException {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket solutions where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketSolution> findByTicketEntryId(long ticketEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] { ticketEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID;
			finderArgs = new Object[] {
					ticketEntryId,
					
					start, end, orderByComparator
				};
		}

		List<TicketSolution> list = (List<TicketSolution>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketSolution ticketSolution : list) {
				if ((ticketEntryId != ticketSolution.getTicketEntryId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TICKETSOLUTION_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketSolutionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				list = (List<TicketSolution>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket solution
	 * @throws com.liferay.osb.NoSuchTicketSolutionException if a matching ticket solution could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketSolutionException, SystemException {
		TicketSolution ticketSolution = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketSolution != null) {
			return ticketSolution;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketSolutionException(msg.toString());
	}

	/**
	 * Returns the first ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketSolution> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket solution
	 * @throws com.liferay.osb.NoSuchTicketSolutionException if a matching ticket solution could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketSolutionException, SystemException {
		TicketSolution ticketSolution = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketSolution != null) {
			return ticketSolution;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketSolutionException(msg.toString());
	}

	/**
	 * Returns the last ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket solution, or <code>null</code> if a matching ticket solution could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTicketEntryId(ticketEntryId);

		List<TicketSolution> list = findByTicketEntryId(ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket solutions before and after the current ticket solution in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketSolutionId the primary key of the current ticket solution
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket solution
	 * @throws com.liferay.osb.NoSuchTicketSolutionException if a ticket solution with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketSolution[] findByTicketEntryId_PrevAndNext(
		long ticketSolutionId, long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketSolutionException, SystemException {
		TicketSolution ticketSolution = findByPrimaryKey(ticketSolutionId);

		Session session = null;

		try {
			session = openSession();

			TicketSolution[] array = new TicketSolutionImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketSolution,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketSolution;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketSolution,
					ticketEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketSolution getByTicketEntryId_PrevAndNext(Session session,
		TicketSolution ticketSolution, long ticketEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETSOLUTION_WHERE);

		query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TicketSolutionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketSolution);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketSolution> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket solutions.
	 *
	 * @return the ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketSolution> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @return the range of ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketSolution> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket solutions
	 * @param end the upper bound of the range of ticket solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketSolution> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<TicketSolution> list = (List<TicketSolution>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETSOLUTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETSOLUTION.concat(TicketSolutionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketSolution>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketSolution>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the ticket solutions where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTicketEntryId(long ticketEntryId)
		throws SystemException {
		for (TicketSolution ticketSolution : findByTicketEntryId(ticketEntryId)) {
			remove(ticketSolution);
		}
	}

	/**
	 * Removes all the ticket solutions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketSolution ticketSolution : findAll()) {
			remove(ticketSolution);
		}
	}

	/**
	 * Returns the number of ticket solutions where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTicketEntryId(long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETSOLUTION_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket solutions.
	 *
	 * @return the number of ticket solutions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETSOLUTION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the ticket solution persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketSolution")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketSolution>> listenersList = new ArrayList<ModelListener<TicketSolution>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketSolution>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TicketSolutionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = AppAuditPersistence.class)
	protected AppAuditPersistence appAuditPersistence;
	@BeanReference(type = AppEntryPersistence.class)
	protected AppEntryPersistence appEntryPersistence;
	@BeanReference(type = AppEntryRelPersistence.class)
	protected AppEntryRelPersistence appEntryRelPersistence;
	@BeanReference(type = AppFlagPersistence.class)
	protected AppFlagPersistence appFlagPersistence;
	@BeanReference(type = AppPackagePersistence.class)
	protected AppPackagePersistence appPackagePersistence;
	@BeanReference(type = AppPackagePluginPersistence.class)
	protected AppPackagePluginPersistence appPackagePluginPersistence;
	@BeanReference(type = AppPricingPersistence.class)
	protected AppPricingPersistence appPricingPersistence;
	@BeanReference(type = AppPricingItemPersistence.class)
	protected AppPricingItemPersistence appPricingItemPersistence;
	@BeanReference(type = AppVersionPersistence.class)
	protected AppVersionPersistence appVersionPersistence;
	@BeanReference(type = AssetAttachmentPersistence.class)
	protected AssetAttachmentPersistence assetAttachmentPersistence;
	@BeanReference(type = AssetAuditPersistence.class)
	protected AssetAuditPersistence assetAuditPersistence;
	@BeanReference(type = AssetLicensePersistence.class)
	protected AssetLicensePersistence assetLicensePersistence;
	@BeanReference(type = AssetListPersistence.class)
	protected AssetListPersistence assetListPersistence;
	@BeanReference(type = AssetListAssetEntryPersistence.class)
	protected AssetListAssetEntryPersistence assetListAssetEntryPersistence;
	@BeanReference(type = AssetReceiptPersistence.class)
	protected AssetReceiptPersistence assetReceiptPersistence;
	@BeanReference(type = AssetReceiptLicensePersistence.class)
	protected AssetReceiptLicensePersistence assetReceiptLicensePersistence;
	@BeanReference(type = AssetReceiptRedeemTokenPersistence.class)
	protected AssetReceiptRedeemTokenPersistence assetReceiptRedeemTokenPersistence;
	@BeanReference(type = AssetReceiptSupportPersistence.class)
	protected AssetReceiptSupportPersistence assetReceiptSupportPersistence;
	@BeanReference(type = AssetRecommendationEntryPersistence.class)
	protected AssetRecommendationEntryPersistence assetRecommendationEntryPersistence;
	@BeanReference(type = AssetRecommendationSetPersistence.class)
	protected AssetRecommendationSetPersistence assetRecommendationSetPersistence;
	@BeanReference(type = AssetStatsDayPersistence.class)
	protected AssetStatsDayPersistence assetStatsDayPersistence;
	@BeanReference(type = AssetStatsMonthPersistence.class)
	protected AssetStatsMonthPersistence assetStatsMonthPersistence;
	@BeanReference(type = AssetStatsWeekPersistence.class)
	protected AssetStatsWeekPersistence assetStatsWeekPersistence;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = ContractAuditPersistence.class)
	protected ContractAuditPersistence contractAuditPersistence;
	@BeanReference(type = ContractEntryPersistence.class)
	protected ContractEntryPersistence contractEntryPersistence;
	@BeanReference(type = CorpEntryPersistence.class)
	protected CorpEntryPersistence corpEntryPersistence;
	@BeanReference(type = CorpGroupPersistence.class)
	protected CorpGroupPersistence corpGroupPersistence;
	@BeanReference(type = CorpMembershipRequestPersistence.class)
	protected CorpMembershipRequestPersistence corpMembershipRequestPersistence;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = CountryAppPricingPersistence.class)
	protected CountryAppPricingPersistence countryAppPricingPersistence;
	@BeanReference(type = CurrencyEntryPersistence.class)
	protected CurrencyEntryPersistence currencyEntryPersistence;
	@BeanReference(type = DeveloperEntryPersistence.class)
	protected DeveloperEntryPersistence developerEntryPersistence;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
	@BeanReference(type = LCSSubscriptionEntryPersistence.class)
	protected LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence;
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = MarketingEventPersistence.class)
	protected MarketingEventPersistence marketingEventPersistence;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = PortalReleasePersistence.class)
	protected PortalReleasePersistence portalReleasePersistence;
	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = TicketCannedResponsePersistence.class)
	protected TicketCannedResponsePersistence ticketCannedResponsePersistence;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = TrainingCertificatePersistence.class)
	protected TrainingCertificatePersistence trainingCertificatePersistence;
	@BeanReference(type = TrainingCertificateTemplatePersistence.class)
	protected TrainingCertificateTemplatePersistence trainingCertificateTemplatePersistence;
	@BeanReference(type = TrainingCoursePersistence.class)
	protected TrainingCoursePersistence trainingCoursePersistence;
	@BeanReference(type = TrainingCustomerPersistence.class)
	protected TrainingCustomerPersistence trainingCustomerPersistence;
	@BeanReference(type = TrainingEventPersistence.class)
	protected TrainingEventPersistence trainingEventPersistence;
	@BeanReference(type = TrainingExamPersistence.class)
	protected TrainingExamPersistence trainingExamPersistence;
	@BeanReference(type = TrainingExamResultPersistence.class)
	protected TrainingExamResultPersistence trainingExamResultPersistence;
	@BeanReference(type = TrainingExamResultItemPersistence.class)
	protected TrainingExamResultItemPersistence trainingExamResultItemPersistence;
	@BeanReference(type = TrainingExamResultSectionPersistence.class)
	protected TrainingExamResultSectionPersistence trainingExamResultSectionPersistence;
	@BeanReference(type = TrainingImportLogPersistence.class)
	protected TrainingImportLogPersistence trainingImportLogPersistence;
	@BeanReference(type = TrainingLinkedUserPersistence.class)
	protected TrainingLinkedUserPersistence trainingLinkedUserPersistence;
	@BeanReference(type = TrainingLocationPersistence.class)
	protected TrainingLocationPersistence trainingLocationPersistence;
	@BeanReference(type = TrainingWorkerPersistence.class)
	protected TrainingWorkerPersistence trainingWorkerPersistence;
	@BeanReference(type = UserProfilePersistence.class)
	protected UserProfilePersistence userProfilePersistence;
	@BeanReference(type = UserProfileHistoryPersistence.class)
	protected UserProfileHistoryPersistence userProfileHistoryPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TICKETSOLUTION = "SELECT ticketSolution FROM TicketSolution ticketSolution";
	private static final String _SQL_SELECT_TICKETSOLUTION_WHERE = "SELECT ticketSolution FROM TicketSolution ticketSolution WHERE ";
	private static final String _SQL_COUNT_TICKETSOLUTION = "SELECT COUNT(ticketSolution) FROM TicketSolution ticketSolution";
	private static final String _SQL_COUNT_TICKETSOLUTION_WHERE = "SELECT COUNT(ticketSolution) FROM TicketSolution ticketSolution WHERE ";
	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketSolution.ticketEntryId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketSolution.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketSolution exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketSolution exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketSolutionPersistenceImpl.class);
	private static TicketSolution _nullTicketSolution = new TicketSolutionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketSolution> toCacheModel() {
				return _nullTicketSolutionCacheModel;
			}
		};

	private static CacheModel<TicketSolution> _nullTicketSolutionCacheModel = new CacheModel<TicketSolution>() {
			public TicketSolution toEntityModel() {
				return _nullTicketSolution;
			}
		};
}
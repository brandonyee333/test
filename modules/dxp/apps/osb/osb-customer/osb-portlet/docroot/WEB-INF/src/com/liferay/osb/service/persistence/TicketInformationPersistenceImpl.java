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

import com.liferay.osb.NoSuchTicketInformationException;
import com.liferay.osb.model.TicketInformation;
import com.liferay.osb.model.impl.TicketInformationImpl;
import com.liferay.osb.model.impl.TicketInformationModelImpl;

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
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the ticket information service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformationPersistence
 * @see TicketInformationUtil
 * @generated
 */
public class TicketInformationPersistenceImpl extends BasePersistenceImpl<TicketInformation>
	implements TicketInformationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketInformationUtil} to access the ticket information persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketInformationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED,
			TicketInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED,
			TicketInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketInformationModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_TEI_FI = new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED,
			TicketInformationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTEI_FI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketInformationModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketInformationModelImpl.FIELDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_FI = new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_FI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED,
			TicketInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED,
			TicketInformationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket information in the entity cache if it is enabled.
	 *
	 * @param ticketInformation the ticket information
	 */
	public void cacheResult(TicketInformation ticketInformation) {
		EntityCacheUtil.putResult(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationImpl.class, ticketInformation.getPrimaryKey(),
			ticketInformation);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FI,
			new Object[] {
				Long.valueOf(ticketInformation.getTicketEntryId()),
				Long.valueOf(ticketInformation.getFieldId())
			}, ticketInformation);

		ticketInformation.resetOriginalValues();
	}

	/**
	 * Caches the ticket informations in the entity cache if it is enabled.
	 *
	 * @param ticketInformations the ticket informations
	 */
	public void cacheResult(List<TicketInformation> ticketInformations) {
		for (TicketInformation ticketInformation : ticketInformations) {
			if (EntityCacheUtil.getResult(
						TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
						TicketInformationImpl.class,
						ticketInformation.getPrimaryKey()) == null) {
				cacheResult(ticketInformation);
			}
			else {
				ticketInformation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket informations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketInformationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketInformationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket information.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketInformation ticketInformation) {
		EntityCacheUtil.removeResult(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationImpl.class, ticketInformation.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(ticketInformation);
	}

	@Override
	public void clearCache(List<TicketInformation> ticketInformations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketInformation ticketInformation : ticketInformations) {
			EntityCacheUtil.removeResult(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
				TicketInformationImpl.class, ticketInformation.getPrimaryKey());

			clearUniqueFindersCache(ticketInformation);
		}
	}

	protected void cacheUniqueFindersCache(TicketInformation ticketInformation) {
		if (ticketInformation.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(ticketInformation.getTicketEntryId()),
					Long.valueOf(ticketInformation.getFieldId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_FI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FI, args,
				ticketInformation);
		}
		else {
			TicketInformationModelImpl ticketInformationModelImpl = (TicketInformationModelImpl)ticketInformation;

			if ((ticketInformationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TEI_FI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketInformation.getTicketEntryId()),
						Long.valueOf(ticketInformation.getFieldId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_FI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FI, args,
					ticketInformation);
			}
		}
	}

	protected void clearUniqueFindersCache(TicketInformation ticketInformation) {
		TicketInformationModelImpl ticketInformationModelImpl = (TicketInformationModelImpl)ticketInformation;

		Object[] args = new Object[] {
				Long.valueOf(ticketInformation.getTicketEntryId()),
				Long.valueOf(ticketInformation.getFieldId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_FI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_FI, args);

		if ((ticketInformationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TEI_FI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(ticketInformationModelImpl.getOriginalTicketEntryId()),
					Long.valueOf(ticketInformationModelImpl.getOriginalFieldId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_FI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_FI, args);
		}
	}

	/**
	 * Creates a new ticket information with the primary key. Does not add the ticket information to the database.
	 *
	 * @param ticketInformationId the primary key for the new ticket information
	 * @return the new ticket information
	 */
	public TicketInformation create(long ticketInformationId) {
		TicketInformation ticketInformation = new TicketInformationImpl();

		ticketInformation.setNew(true);
		ticketInformation.setPrimaryKey(ticketInformationId);

		return ticketInformation;
	}

	/**
	 * Removes the ticket information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketInformationId the primary key of the ticket information
	 * @return the ticket information that was removed
	 * @throws com.liferay.osb.NoSuchTicketInformationException if a ticket information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation remove(long ticketInformationId)
		throws NoSuchTicketInformationException, SystemException {
		return remove(Long.valueOf(ticketInformationId));
	}

	/**
	 * Removes the ticket information with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket information
	 * @return the ticket information that was removed
	 * @throws com.liferay.osb.NoSuchTicketInformationException if a ticket information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketInformation remove(Serializable primaryKey)
		throws NoSuchTicketInformationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketInformation ticketInformation = (TicketInformation)session.get(TicketInformationImpl.class,
					primaryKey);

			if (ticketInformation == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketInformationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketInformation);
		}
		catch (NoSuchTicketInformationException nsee) {
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
	protected TicketInformation removeImpl(TicketInformation ticketInformation)
		throws SystemException {
		ticketInformation = toUnwrappedModel(ticketInformation);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketInformation);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketInformation);

		return ticketInformation;
	}

	@Override
	public TicketInformation updateImpl(
		com.liferay.osb.model.TicketInformation ticketInformation, boolean merge)
		throws SystemException {
		ticketInformation = toUnwrappedModel(ticketInformation);

		boolean isNew = ticketInformation.isNew();

		TicketInformationModelImpl ticketInformationModelImpl = (TicketInformationModelImpl)ticketInformation;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketInformation, merge);

			ticketInformation.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketInformationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketInformationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketInformationModelImpl.getOriginalTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketInformationModelImpl.getTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
			TicketInformationImpl.class, ticketInformation.getPrimaryKey(),
			ticketInformation);

		clearUniqueFindersCache(ticketInformation);
		cacheUniqueFindersCache(ticketInformation);

		return ticketInformation;
	}

	protected TicketInformation toUnwrappedModel(
		TicketInformation ticketInformation) {
		if (ticketInformation instanceof TicketInformationImpl) {
			return ticketInformation;
		}

		TicketInformationImpl ticketInformationImpl = new TicketInformationImpl();

		ticketInformationImpl.setNew(ticketInformation.isNew());
		ticketInformationImpl.setPrimaryKey(ticketInformation.getPrimaryKey());

		ticketInformationImpl.setTicketInformationId(ticketInformation.getTicketInformationId());
		ticketInformationImpl.setCreateDate(ticketInformation.getCreateDate());
		ticketInformationImpl.setModifiedDate(ticketInformation.getModifiedDate());
		ticketInformationImpl.setTicketEntryId(ticketInformation.getTicketEntryId());
		ticketInformationImpl.setFieldId(ticketInformation.getFieldId());
		ticketInformationImpl.setData(ticketInformation.getData());

		return ticketInformationImpl;
	}

	/**
	 * Returns the ticket information with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket information
	 * @return the ticket information
	 * @throws com.liferay.portal.NoSuchModelException if a ticket information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketInformation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket information with the primary key or throws a {@link com.liferay.osb.NoSuchTicketInformationException} if it could not be found.
	 *
	 * @param ticketInformationId the primary key of the ticket information
	 * @return the ticket information
	 * @throws com.liferay.osb.NoSuchTicketInformationException if a ticket information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation findByPrimaryKey(long ticketInformationId)
		throws NoSuchTicketInformationException, SystemException {
		TicketInformation ticketInformation = fetchByPrimaryKey(ticketInformationId);

		if (ticketInformation == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					ticketInformationId);
			}

			throw new NoSuchTicketInformationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketInformationId);
		}

		return ticketInformation;
	}

	/**
	 * Returns the ticket information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket information
	 * @return the ticket information, or <code>null</code> if a ticket information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketInformation fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket information with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketInformationId the primary key of the ticket information
	 * @return the ticket information, or <code>null</code> if a ticket information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation fetchByPrimaryKey(long ticketInformationId)
		throws SystemException {
		TicketInformation ticketInformation = (TicketInformation)EntityCacheUtil.getResult(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
				TicketInformationImpl.class, ticketInformationId);

		if (ticketInformation == _nullTicketInformation) {
			return null;
		}

		if (ticketInformation == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketInformation = (TicketInformation)session.get(TicketInformationImpl.class,
						Long.valueOf(ticketInformationId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketInformation != null) {
					cacheResult(ticketInformation);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketInformationModelImpl.ENTITY_CACHE_ENABLED,
						TicketInformationImpl.class, ticketInformationId,
						_nullTicketInformation);
				}

				closeSession(session);
			}
		}

		return ticketInformation;
	}

	/**
	 * Returns all the ticket informations where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketInformation> findByTicketEntryId(long ticketEntryId)
		throws SystemException {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket informations where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket informations
	 * @param end the upper bound of the range of ticket informations (not inclusive)
	 * @return the range of matching ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketInformation> findByTicketEntryId(long ticketEntryId,
		int start, int end) throws SystemException {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket informations where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket informations
	 * @param end the upper bound of the range of ticket informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketInformation> findByTicketEntryId(long ticketEntryId,
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

		List<TicketInformation> list = (List<TicketInformation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketInformation ticketInformation : list) {
				if ((ticketEntryId != ticketInformation.getTicketEntryId())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_TICKETINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				list = (List<TicketInformation>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket information
	 * @throws com.liferay.osb.NoSuchTicketInformationException if a matching ticket information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketInformationException, SystemException {
		TicketInformation ticketInformation = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketInformation != null) {
			return ticketInformation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketInformationException(msg.toString());
	}

	/**
	 * Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket information, or <code>null</code> if a matching ticket information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketInformation> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket information
	 * @throws com.liferay.osb.NoSuchTicketInformationException if a matching ticket information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketInformationException, SystemException {
		TicketInformation ticketInformation = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketInformation != null) {
			return ticketInformation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketInformationException(msg.toString());
	}

	/**
	 * Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket information, or <code>null</code> if a matching ticket information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTicketEntryId(ticketEntryId);

		List<TicketInformation> list = findByTicketEntryId(ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket informations before and after the current ticket information in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketInformationId the primary key of the current ticket information
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket information
	 * @throws com.liferay.osb.NoSuchTicketInformationException if a ticket information with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation[] findByTicketEntryId_PrevAndNext(
		long ticketInformationId, long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketInformationException, SystemException {
		TicketInformation ticketInformation = findByPrimaryKey(ticketInformationId);

		Session session = null;

		try {
			session = openSession();

			TicketInformation[] array = new TicketInformationImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session,
					ticketInformation, ticketEntryId, orderByComparator, true);

			array[1] = ticketInformation;

			array[2] = getByTicketEntryId_PrevAndNext(session,
					ticketInformation, ticketEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketInformation getByTicketEntryId_PrevAndNext(
		Session session, TicketInformation ticketInformation,
		long ticketEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETINFORMATION_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketInformation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketInformation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or throws a {@link com.liferay.osb.NoSuchTicketInformationException} if it could not be found.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fieldId the field ID
	 * @return the matching ticket information
	 * @throws com.liferay.osb.NoSuchTicketInformationException if a matching ticket information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation findByTEI_FI(long ticketEntryId, long fieldId)
		throws NoSuchTicketInformationException, SystemException {
		TicketInformation ticketInformation = fetchByTEI_FI(ticketEntryId,
				fieldId);

		if (ticketInformation == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(", fieldId=");
			msg.append(fieldId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTicketInformationException(msg.toString());
		}

		return ticketInformation;
	}

	/**
	 * Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fieldId the field ID
	 * @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation fetchByTEI_FI(long ticketEntryId, long fieldId)
		throws SystemException {
		return fetchByTEI_FI(ticketEntryId, fieldId, true);
	}

	/**
	 * Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fieldId the field ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation fetchByTEI_FI(long ticketEntryId, long fieldId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, fieldId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TEI_FI,
					finderArgs, this);
		}

		if (result instanceof TicketInformation) {
			TicketInformation ticketInformation = (TicketInformation)result;

			if ((ticketEntryId != ticketInformation.getTicketEntryId()) ||
					(fieldId != ticketInformation.getFieldId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TICKETINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_TEI_FI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_FI_FIELDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(fieldId);

				List<TicketInformation> list = q.list();

				result = list;

				TicketInformation ticketInformation = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FI,
						finderArgs, list);
				}
				else {
					ticketInformation = list.get(0);

					cacheResult(ticketInformation);

					if ((ticketInformation.getTicketEntryId() != ticketEntryId) ||
							(ticketInformation.getFieldId() != fieldId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FI,
							finderArgs, ticketInformation);
					}
				}

				return ticketInformation;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_FI,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (TicketInformation)result;
			}
		}
	}

	/**
	 * Returns all the ticket informations.
	 *
	 * @return the ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketInformation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket informations
	 * @param end the upper bound of the range of ticket informations (not inclusive)
	 * @return the range of ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketInformation> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket informations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket informations
	 * @param end the upper bound of the range of ticket informations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketInformation> findAll(int start, int end,
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

		List<TicketInformation> list = (List<TicketInformation>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETINFORMATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETINFORMATION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketInformation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketInformation>)QueryUtil.list(q,
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
	 * Removes all the ticket informations where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTicketEntryId(long ticketEntryId)
		throws SystemException {
		for (TicketInformation ticketInformation : findByTicketEntryId(
				ticketEntryId)) {
			remove(ticketInformation);
		}
	}

	/**
	 * Removes the ticket information where ticketEntryId = &#63; and fieldId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fieldId the field ID
	 * @return the ticket information that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TicketInformation removeByTEI_FI(long ticketEntryId, long fieldId)
		throws NoSuchTicketInformationException, SystemException {
		TicketInformation ticketInformation = findByTEI_FI(ticketEntryId,
				fieldId);

		return remove(ticketInformation);
	}

	/**
	 * Removes all the ticket informations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketInformation ticketInformation : findAll()) {
			remove(ticketInformation);
		}
	}

	/**
	 * Returns the number of ticket informations where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTicketEntryId(long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETINFORMATION_WHERE);

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
	 * Returns the number of ticket informations where ticketEntryId = &#63; and fieldId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fieldId the field ID
	 * @return the number of matching ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_FI(long ticketEntryId, long fieldId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, fieldId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_FI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETINFORMATION_WHERE);

			query.append(_FINDER_COLUMN_TEI_FI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_FI_FIELDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(fieldId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_FI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket informations.
	 *
	 * @return the number of ticket informations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETINFORMATION);

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
	 * Initializes the ticket information persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketInformation")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketInformation>> listenersList = new ArrayList<ModelListener<TicketInformation>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketInformation>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TicketInformationImpl.class.getName());
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
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TICKETINFORMATION = "SELECT ticketInformation FROM TicketInformation ticketInformation";
	private static final String _SQL_SELECT_TICKETINFORMATION_WHERE = "SELECT ticketInformation FROM TicketInformation ticketInformation WHERE ";
	private static final String _SQL_COUNT_TICKETINFORMATION = "SELECT COUNT(ticketInformation) FROM TicketInformation ticketInformation";
	private static final String _SQL_COUNT_TICKETINFORMATION_WHERE = "SELECT COUNT(ticketInformation) FROM TicketInformation ticketInformation WHERE ";
	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketInformation.ticketEntryId = ?";
	private static final String _FINDER_COLUMN_TEI_FI_TICKETENTRYID_2 = "ticketInformation.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_FI_FIELDID_2 = "ticketInformation.fieldId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketInformation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketInformation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketInformation exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketInformationPersistenceImpl.class);
	private static TicketInformation _nullTicketInformation = new TicketInformationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketInformation> toCacheModel() {
				return _nullTicketInformationCacheModel;
			}
		};

	private static CacheModel<TicketInformation> _nullTicketInformationCacheModel =
		new CacheModel<TicketInformation>() {
			public TicketInformation toEntityModel() {
				return _nullTicketInformation;
			}
		};
}
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

import com.liferay.osb.NoSuchTicketLinkException;
import com.liferay.osb.model.TicketLink;
import com.liferay.osb.model.impl.TicketLinkImpl;
import com.liferay.osb.model.impl.TicketLinkModelImpl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
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
 * The persistence implementation for the ticket link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketLinkPersistence
 * @see TicketLinkUtil
 * @generated
 */
public class TicketLinkPersistenceImpl extends BasePersistenceImpl<TicketLink>
	implements TicketLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketLinkUtil} to access the ticket link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTicketEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID =
		new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTicketEntryId",
			new String[] { Long.class.getName() },
			TicketLinkModelImpl.TICKETENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TICKETENTRYID = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTicketEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_TSI = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTEI_TSI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI =
		new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_TSI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketLinkModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketLinkModelImpl.TICKETSOLUTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_TSI = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_TSI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTEI_V",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_V",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketLinkModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketLinkModelImpl.VISIBILITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_V = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_V",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_V = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_V",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, TicketLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket link in the entity cache if it is enabled.
	 *
	 * @param ticketLink the ticket link
	 */
	public void cacheResult(TicketLink ticketLink) {
		EntityCacheUtil.putResult(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkImpl.class, ticketLink.getPrimaryKey(), ticketLink);

		ticketLink.resetOriginalValues();
	}

	/**
	 * Caches the ticket links in the entity cache if it is enabled.
	 *
	 * @param ticketLinks the ticket links
	 */
	public void cacheResult(List<TicketLink> ticketLinks) {
		for (TicketLink ticketLink : ticketLinks) {
			if (EntityCacheUtil.getResult(
						TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
						TicketLinkImpl.class, ticketLink.getPrimaryKey()) == null) {
				cacheResult(ticketLink);
			}
			else {
				ticketLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket links.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketLinkImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketLinkImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket link.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketLink ticketLink) {
		EntityCacheUtil.removeResult(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkImpl.class, ticketLink.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TicketLink> ticketLinks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketLink ticketLink : ticketLinks) {
			EntityCacheUtil.removeResult(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
				TicketLinkImpl.class, ticketLink.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ticket link with the primary key. Does not add the ticket link to the database.
	 *
	 * @param ticketLinkId the primary key for the new ticket link
	 * @return the new ticket link
	 */
	public TicketLink create(long ticketLinkId) {
		TicketLink ticketLink = new TicketLinkImpl();

		ticketLink.setNew(true);
		ticketLink.setPrimaryKey(ticketLinkId);

		return ticketLink;
	}

	/**
	 * Removes the ticket link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketLinkId the primary key of the ticket link
	 * @return the ticket link that was removed
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink remove(long ticketLinkId)
		throws NoSuchTicketLinkException, SystemException {
		return remove(Long.valueOf(ticketLinkId));
	}

	/**
	 * Removes the ticket link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket link
	 * @return the ticket link that was removed
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketLink remove(Serializable primaryKey)
		throws NoSuchTicketLinkException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketLink ticketLink = (TicketLink)session.get(TicketLinkImpl.class,
					primaryKey);

			if (ticketLink == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketLink);
		}
		catch (NoSuchTicketLinkException nsee) {
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
	protected TicketLink removeImpl(TicketLink ticketLink)
		throws SystemException {
		ticketLink = toUnwrappedModel(ticketLink);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketLink);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketLink);

		return ticketLink;
	}

	@Override
	public TicketLink updateImpl(com.liferay.osb.model.TicketLink ticketLink,
		boolean merge) throws SystemException {
		ticketLink = toUnwrappedModel(ticketLink);

		boolean isNew = ticketLink.isNew();

		TicketLinkModelImpl ticketLinkModelImpl = (TicketLinkModelImpl)ticketLink;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketLink, merge);

			ticketLink.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketLinkModelImpl.getOriginalTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(ticketLinkModelImpl.getTicketEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TICKETENTRYID,
					args);
			}

			if ((ticketLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketLinkModelImpl.getOriginalTicketEntryId()),
						Long.valueOf(ticketLinkModelImpl.getOriginalTicketSolutionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_TSI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI,
					args);

				args = new Object[] {
						Long.valueOf(ticketLinkModelImpl.getTicketEntryId()),
						Long.valueOf(ticketLinkModelImpl.getTicketSolutionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_TSI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI,
					args);
			}

			if ((ticketLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketLinkModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketLinkModelImpl.getOriginalVisibility())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_V, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V,
					args);

				args = new Object[] {
						Long.valueOf(ticketLinkModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketLinkModelImpl.getVisibility())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_V, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
			TicketLinkImpl.class, ticketLink.getPrimaryKey(), ticketLink);

		return ticketLink;
	}

	protected TicketLink toUnwrappedModel(TicketLink ticketLink) {
		if (ticketLink instanceof TicketLinkImpl) {
			return ticketLink;
		}

		TicketLinkImpl ticketLinkImpl = new TicketLinkImpl();

		ticketLinkImpl.setNew(ticketLink.isNew());
		ticketLinkImpl.setPrimaryKey(ticketLink.getPrimaryKey());

		ticketLinkImpl.setTicketLinkId(ticketLink.getTicketLinkId());
		ticketLinkImpl.setUserId(ticketLink.getUserId());
		ticketLinkImpl.setUserName(ticketLink.getUserName());
		ticketLinkImpl.setCreateDate(ticketLink.getCreateDate());
		ticketLinkImpl.setTicketEntryId(ticketLink.getTicketEntryId());
		ticketLinkImpl.setTicketSolutionId(ticketLink.getTicketSolutionId());
		ticketLinkImpl.setUrl(ticketLink.getUrl());
		ticketLinkImpl.setType(ticketLink.getType());
		ticketLinkImpl.setVisibility(ticketLink.getVisibility());

		return ticketLinkImpl;
	}

	/**
	 * Returns the ticket link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket link
	 * @return the ticket link
	 * @throws com.liferay.portal.NoSuchModelException if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket link with the primary key or throws a {@link com.liferay.osb.NoSuchTicketLinkException} if it could not be found.
	 *
	 * @param ticketLinkId the primary key of the ticket link
	 * @return the ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink findByPrimaryKey(long ticketLinkId)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = fetchByPrimaryKey(ticketLinkId);

		if (ticketLink == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ticketLinkId);
			}

			throw new NoSuchTicketLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketLinkId);
		}

		return ticketLink;
	}

	/**
	 * Returns the ticket link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket link
	 * @return the ticket link, or <code>null</code> if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketLink fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketLinkId the primary key of the ticket link
	 * @return the ticket link, or <code>null</code> if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink fetchByPrimaryKey(long ticketLinkId)
		throws SystemException {
		TicketLink ticketLink = (TicketLink)EntityCacheUtil.getResult(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
				TicketLinkImpl.class, ticketLinkId);

		if (ticketLink == _nullTicketLink) {
			return null;
		}

		if (ticketLink == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketLink = (TicketLink)session.get(TicketLinkImpl.class,
						Long.valueOf(ticketLinkId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketLink != null) {
					cacheResult(ticketLink);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketLinkModelImpl.ENTITY_CACHE_ENABLED,
						TicketLinkImpl.class, ticketLinkId, _nullTicketLink);
				}

				closeSession(session);
			}
		}

		return ticketLink;
	}

	/**
	 * Returns all the ticket links where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTicketEntryId(long ticketEntryId)
		throws SystemException {
		return findByTicketEntryId(ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket links where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @return the range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTicketEntryId(long ticketEntryId, int start,
		int end) throws SystemException {
		return findByTicketEntryId(ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket links where ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTicketEntryId(long ticketEntryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<TicketLink> list = (List<TicketLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketLink ticketLink : list) {
				if ((ticketEntryId != ticketLink.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETLINK_WHERE);

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

				list = (List<TicketLink>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink findByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = fetchByTicketEntryId_First(ticketEntryId,
				orderByComparator);

		if (ticketLink != null) {
			return ticketLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketLinkException(msg.toString());
	}

	/**
	 * Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink fetchByTicketEntryId_First(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketLink> list = findByTicketEntryId(ticketEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink findByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = fetchByTicketEntryId_Last(ticketEntryId,
				orderByComparator);

		if (ticketLink != null) {
			return ticketLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketLinkException(msg.toString());
	}

	/**
	 * Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink fetchByTicketEntryId_Last(long ticketEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTicketEntryId(ticketEntryId);

		List<TicketLink> list = findByTicketEntryId(ticketEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63;.
	 *
	 * @param ticketLinkId the primary key of the current ticket link
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink[] findByTicketEntryId_PrevAndNext(long ticketLinkId,
		long ticketEntryId, OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = findByPrimaryKey(ticketLinkId);

		Session session = null;

		try {
			session = openSession();

			TicketLink[] array = new TicketLinkImpl[3];

			array[0] = getByTicketEntryId_PrevAndNext(session, ticketLink,
					ticketEntryId, orderByComparator, true);

			array[1] = ticketLink;

			array[2] = getByTicketEntryId_PrevAndNext(session, ticketLink,
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

	protected TicketLink getByTicketEntryId_PrevAndNext(Session session,
		TicketLink ticketLink, long ticketEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETLINK_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(ticketLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @return the matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId) throws SystemException {
		return findByTEI_TSI(ticketEntryId, ticketSolutionId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @return the range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end) throws SystemException {
		return findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI;
			finderArgs = new Object[] { ticketEntryId, ticketSolutionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_TSI;
			finderArgs = new Object[] {
					ticketEntryId, ticketSolutionId,
					
					start, end, orderByComparator
				};
		}

		List<TicketLink> list = (List<TicketLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketLink ticketLink : list) {
				if ((ticketEntryId != ticketLink.getTicketEntryId()) ||
						(ticketSolutionId != ticketLink.getTicketSolutionId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TICKETLINK_WHERE);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

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

				qPos.add(ticketSolutionId);

				list = (List<TicketLink>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink findByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = fetchByTEI_TSI_First(ticketEntryId,
				ticketSolutionId, orderByComparator);

		if (ticketLink != null) {
			return ticketLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", ticketSolutionId=");
		msg.append(ticketSolutionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketLinkException(msg.toString());
	}

	/**
	 * Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink fetchByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketLink> list = findByTEI_TSI(ticketEntryId, ticketSolutionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink findByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = fetchByTEI_TSI_Last(ticketEntryId,
				ticketSolutionId, orderByComparator);

		if (ticketLink != null) {
			return ticketLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", ticketSolutionId=");
		msg.append(ticketSolutionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketLinkException(msg.toString());
	}

	/**
	 * Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink fetchByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTEI_TSI(ticketEntryId, ticketSolutionId);

		List<TicketLink> list = findByTEI_TSI(ticketEntryId, ticketSolutionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketLinkId the primary key of the current ticket link
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink[] findByTEI_TSI_PrevAndNext(long ticketLinkId,
		long ticketEntryId, long ticketSolutionId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = findByPrimaryKey(ticketLinkId);

		Session session = null;

		try {
			session = openSession();

			TicketLink[] array = new TicketLinkImpl[3];

			array[0] = getByTEI_TSI_PrevAndNext(session, ticketLink,
					ticketEntryId, ticketSolutionId, orderByComparator, true);

			array[1] = ticketLink;

			array[2] = getByTEI_TSI_PrevAndNext(session, ticketLink,
					ticketEntryId, ticketSolutionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketLink getByTEI_TSI_PrevAndNext(Session session,
		TicketLink ticketLink, long ticketEntryId, long ticketSolutionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETLINK_WHERE);

		query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

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

		qPos.add(ticketSolutionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @return the matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_V(long ticketEntryId, int visibility)
		throws SystemException {
		return findByTEI_V(ticketEntryId, visibility, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @return the range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_V(long ticketEntryId, int visibility,
		int start, int end) throws SystemException {
		return findByTEI_V(ticketEntryId, visibility, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_V(long ticketEntryId, int visibility,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_V;
			finderArgs = new Object[] { ticketEntryId, visibility };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V;
			finderArgs = new Object[] {
					ticketEntryId, visibility,
					
					start, end, orderByComparator
				};
		}

		List<TicketLink> list = (List<TicketLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketLink ticketLink : list) {
				if ((ticketEntryId != ticketLink.getTicketEntryId()) ||
						(visibility != ticketLink.getVisibility())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TICKETLINK_WHERE);

			query.append(_FINDER_COLUMN_TEI_V_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_V_VISIBILITY_2);

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

				qPos.add(visibility);

				list = (List<TicketLink>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink findByTEI_V_First(long ticketEntryId, int visibility,
		OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = fetchByTEI_V_First(ticketEntryId, visibility,
				orderByComparator);

		if (ticketLink != null) {
			return ticketLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketLinkException(msg.toString());
	}

	/**
	 * Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink fetchByTEI_V_First(long ticketEntryId, int visibility,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketLink> list = findByTEI_V(ticketEntryId, visibility, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink findByTEI_V_Last(long ticketEntryId, int visibility,
		OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = fetchByTEI_V_Last(ticketEntryId, visibility,
				orderByComparator);

		if (ticketLink != null) {
			return ticketLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketLinkException(msg.toString());
	}

	/**
	 * Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink fetchByTEI_V_Last(long ticketEntryId, int visibility,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTEI_V(ticketEntryId, visibility);

		List<TicketLink> list = findByTEI_V(ticketEntryId, visibility,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * @param ticketLinkId the primary key of the current ticket link
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket link
	 * @throws com.liferay.osb.NoSuchTicketLinkException if a ticket link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketLink[] findByTEI_V_PrevAndNext(long ticketLinkId,
		long ticketEntryId, int visibility, OrderByComparator orderByComparator)
		throws NoSuchTicketLinkException, SystemException {
		TicketLink ticketLink = findByPrimaryKey(ticketLinkId);

		Session session = null;

		try {
			session = openSession();

			TicketLink[] array = new TicketLinkImpl[3];

			array[0] = getByTEI_V_PrevAndNext(session, ticketLink,
					ticketEntryId, visibility, orderByComparator, true);

			array[1] = ticketLink;

			array[2] = getByTEI_V_PrevAndNext(session, ticketLink,
					ticketEntryId, visibility, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketLink getByTEI_V_PrevAndNext(Session session,
		TicketLink ticketLink, long ticketEntryId, int visibility,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETLINK_WHERE);

		query.append(_FINDER_COLUMN_TEI_V_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_V_VISIBILITY_2);

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

		qPos.add(visibility);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @return the matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_V(long ticketEntryId, int[] visibilities)
		throws SystemException {
		return findByTEI_V(ticketEntryId, visibilities, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @return the range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_V(long ticketEntryId, int[] visibilities,
		int start, int end) throws SystemException {
		return findByTEI_V(ticketEntryId, visibilities, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findByTEI_V(long ticketEntryId, int[] visibilities,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_V;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(visibilities)
				};
		}
		else {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(visibilities),
					
					start, end, orderByComparator
				};
		}

		List<TicketLink> list = (List<TicketLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketLink ticketLink : list) {
				if ((ticketEntryId != ticketLink.getTicketEntryId()) ||
						!ArrayUtil.contains(visibilities,
							ticketLink.getVisibility())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETLINK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_V_TICKETENTRYID_5);

			conjunctionable = true;

			if ((visibilities == null) || (visibilities.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < visibilities.length; i++) {
					query.append(_FINDER_COLUMN_TEI_V_VISIBILITY_5);

					if ((i + 1) < visibilities.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

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

				if (visibilities != null) {
					qPos.add(visibilities);
				}

				list = (List<TicketLink>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the ticket links.
	 *
	 * @return the ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @return the range of ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket links
	 * @param end the upper bound of the range of ticket links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketLink> findAll(int start, int end,
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

		List<TicketLink> list = (List<TicketLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETLINK;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketLink>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketLink>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the ticket links where ticketEntryId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTicketEntryId(long ticketEntryId)
		throws SystemException {
		for (TicketLink ticketLink : findByTicketEntryId(ticketEntryId)) {
			remove(ticketLink);
		}
	}

	/**
	 * Removes all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws SystemException {
		for (TicketLink ticketLink : findByTEI_TSI(ticketEntryId,
				ticketSolutionId)) {
			remove(ticketLink);
		}
	}

	/**
	 * Removes all the ticket links where ticketEntryId = &#63; and visibility = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_V(long ticketEntryId, int visibility)
		throws SystemException {
		for (TicketLink ticketLink : findByTEI_V(ticketEntryId, visibility)) {
			remove(ticketLink);
		}
	}

	/**
	 * Removes all the ticket links from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketLink ticketLink : findAll()) {
			remove(ticketLink);
		}
	}

	/**
	 * Returns the number of ticket links where ticketEntryId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTicketEntryId(long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TICKETENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETLINK_WHERE);

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
	 * Returns the number of ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @return the number of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, ticketSolutionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_TSI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETLINK_WHERE);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(ticketSolutionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_TSI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket links where ticketEntryId = &#63; and visibility = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @return the number of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_V(long ticketEntryId, int visibility)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, visibility };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETLINK_WHERE);

			query.append(_FINDER_COLUMN_TEI_V_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_V_VISIBILITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_V,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param visibilities the visibilities
	 * @return the number of matching ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_V(long ticketEntryId, int[] visibilities)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				ticketEntryId, StringUtil.merge(visibilities)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETLINK_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_V_TICKETENTRYID_5);

			conjunctionable = true;

			if ((visibilities == null) || (visibilities.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < visibilities.length; i++) {
					query.append(_FINDER_COLUMN_TEI_V_VISIBILITY_5);

					if ((i + 1) < visibilities.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (visibilities != null) {
					qPos.add(visibilities);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_V,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket links.
	 *
	 * @return the number of ticket links
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETLINK);

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
	 * Initializes the ticket link persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketLink")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketLink>> listenersList = new ArrayList<ModelListener<TicketLink>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketLink>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TicketLinkImpl.class.getName());
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
	private static final String _SQL_SELECT_TICKETLINK = "SELECT ticketLink FROM TicketLink ticketLink";
	private static final String _SQL_SELECT_TICKETLINK_WHERE = "SELECT ticketLink FROM TicketLink ticketLink WHERE ";
	private static final String _SQL_COUNT_TICKETLINK = "SELECT COUNT(ticketLink) FROM TicketLink ticketLink";
	private static final String _SQL_COUNT_TICKETLINK_WHERE = "SELECT COUNT(ticketLink) FROM TicketLink ticketLink WHERE ";
	private static final String _FINDER_COLUMN_TICKETENTRYID_TICKETENTRYID_2 = "ticketLink.ticketEntryId = ?";
	private static final String _FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2 = "ticketLink.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2 = "ticketLink.ticketSolutionId = ?";
	private static final String _FINDER_COLUMN_TEI_V_TICKETENTRYID_2 = "ticketLink.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_V_TICKETENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_V_TICKETENTRYID_2) + ")";
	private static final String _FINDER_COLUMN_TEI_V_VISIBILITY_2 = "ticketLink.visibility = ?";
	private static final String _FINDER_COLUMN_TEI_V_VISIBILITY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_V_VISIBILITY_2) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketLink exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketLinkPersistenceImpl.class);
	private static TicketLink _nullTicketLink = new TicketLinkImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketLink> toCacheModel() {
				return _nullTicketLinkCacheModel;
			}
		};

	private static CacheModel<TicketLink> _nullTicketLinkCacheModel = new CacheModel<TicketLink>() {
			public TicketLink toEntityModel() {
				return _nullTicketLink;
			}
		};
}
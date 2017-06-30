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

import com.liferay.osb.NoSuchMarketingEventException;
import com.liferay.osb.model.MarketingEvent;
import com.liferay.osb.model.impl.MarketingEventImpl;
import com.liferay.osb.model.impl.MarketingEventModelImpl;

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
import com.liferay.portal.service.persistence.AddressPersistence;
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
 * The persistence implementation for the marketing event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketingEventPersistence
 * @see MarketingEventUtil
 * @generated
 */
public class MarketingEventPersistenceImpl extends BasePersistenceImpl<MarketingEvent>
	implements MarketingEventPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MarketingEventUtil} to access the marketing event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MarketingEventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByType",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { Integer.class.getName() },
			MarketingEventModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GLOBALREGION =
		new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGlobalRegion",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLOBALREGION =
		new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGlobalRegion",
			new String[] { Integer.class.getName() },
			MarketingEventModelImpl.GLOBALREGION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GLOBALREGION = new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGlobalRegion",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED,
			MarketingEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the marketing event in the entity cache if it is enabled.
	 *
	 * @param marketingEvent the marketing event
	 */
	public void cacheResult(MarketingEvent marketingEvent) {
		EntityCacheUtil.putResult(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventImpl.class, marketingEvent.getPrimaryKey(),
			marketingEvent);

		marketingEvent.resetOriginalValues();
	}

	/**
	 * Caches the marketing events in the entity cache if it is enabled.
	 *
	 * @param marketingEvents the marketing events
	 */
	public void cacheResult(List<MarketingEvent> marketingEvents) {
		for (MarketingEvent marketingEvent : marketingEvents) {
			if (EntityCacheUtil.getResult(
						MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
						MarketingEventImpl.class, marketingEvent.getPrimaryKey()) == null) {
				cacheResult(marketingEvent);
			}
			else {
				marketingEvent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all marketing events.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MarketingEventImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MarketingEventImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the marketing event.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MarketingEvent marketingEvent) {
		EntityCacheUtil.removeResult(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventImpl.class, marketingEvent.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MarketingEvent> marketingEvents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MarketingEvent marketingEvent : marketingEvents) {
			EntityCacheUtil.removeResult(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
				MarketingEventImpl.class, marketingEvent.getPrimaryKey());
		}
	}

	/**
	 * Creates a new marketing event with the primary key. Does not add the marketing event to the database.
	 *
	 * @param marketingEventId the primary key for the new marketing event
	 * @return the new marketing event
	 */
	public MarketingEvent create(long marketingEventId) {
		MarketingEvent marketingEvent = new MarketingEventImpl();

		marketingEvent.setNew(true);
		marketingEvent.setPrimaryKey(marketingEventId);

		return marketingEvent;
	}

	/**
	 * Removes the marketing event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param marketingEventId the primary key of the marketing event
	 * @return the marketing event that was removed
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent remove(long marketingEventId)
		throws NoSuchMarketingEventException, SystemException {
		return remove(Long.valueOf(marketingEventId));
	}

	/**
	 * Removes the marketing event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the marketing event
	 * @return the marketing event that was removed
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MarketingEvent remove(Serializable primaryKey)
		throws NoSuchMarketingEventException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MarketingEvent marketingEvent = (MarketingEvent)session.get(MarketingEventImpl.class,
					primaryKey);

			if (marketingEvent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMarketingEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(marketingEvent);
		}
		catch (NoSuchMarketingEventException nsee) {
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
	protected MarketingEvent removeImpl(MarketingEvent marketingEvent)
		throws SystemException {
		marketingEvent = toUnwrappedModel(marketingEvent);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, marketingEvent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(marketingEvent);

		return marketingEvent;
	}

	@Override
	public MarketingEvent updateImpl(
		com.liferay.osb.model.MarketingEvent marketingEvent, boolean merge)
		throws SystemException {
		marketingEvent = toUnwrappedModel(marketingEvent);

		boolean isNew = marketingEvent.isNew();

		MarketingEventModelImpl marketingEventModelImpl = (MarketingEventModelImpl)marketingEvent;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, marketingEvent, merge);

			marketingEvent.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MarketingEventModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((marketingEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(marketingEventModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] {
						Integer.valueOf(marketingEventModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}

			if ((marketingEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLOBALREGION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(marketingEventModelImpl.getOriginalGlobalRegion())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GLOBALREGION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLOBALREGION,
					args);

				args = new Object[] {
						Integer.valueOf(marketingEventModelImpl.getGlobalRegion())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GLOBALREGION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLOBALREGION,
					args);
			}
		}

		EntityCacheUtil.putResult(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
			MarketingEventImpl.class, marketingEvent.getPrimaryKey(),
			marketingEvent);

		return marketingEvent;
	}

	protected MarketingEvent toUnwrappedModel(MarketingEvent marketingEvent) {
		if (marketingEvent instanceof MarketingEventImpl) {
			return marketingEvent;
		}

		MarketingEventImpl marketingEventImpl = new MarketingEventImpl();

		marketingEventImpl.setNew(marketingEvent.isNew());
		marketingEventImpl.setPrimaryKey(marketingEvent.getPrimaryKey());

		marketingEventImpl.setMarketingEventId(marketingEvent.getMarketingEventId());
		marketingEventImpl.setCompanyId(marketingEvent.getCompanyId());
		marketingEventImpl.setUserId(marketingEvent.getUserId());
		marketingEventImpl.setUserName(marketingEvent.getUserName());
		marketingEventImpl.setCreateDate(marketingEvent.getCreateDate());
		marketingEventImpl.setModifiedDate(marketingEvent.getModifiedDate());
		marketingEventImpl.setType(marketingEvent.getType());
		marketingEventImpl.setDefaultLanguageId(marketingEvent.getDefaultLanguageId());
		marketingEventImpl.setTitle(marketingEvent.getTitle());
		marketingEventImpl.setTitleURL(marketingEvent.getTitleURL());
		marketingEventImpl.setHostedBy(marketingEvent.getHostedBy());
		marketingEventImpl.setHostedByURL(marketingEvent.getHostedByURL());
		marketingEventImpl.setSummary(marketingEvent.getSummary());
		marketingEventImpl.setImageFileEntryId(marketingEvent.getImageFileEntryId());
		marketingEventImpl.setSlidesFileEntryId(marketingEvent.getSlidesFileEntryId());
		marketingEventImpl.setVideoTitle(marketingEvent.getVideoTitle());
		marketingEventImpl.setTimeZoneId(marketingEvent.getTimeZoneId());
		marketingEventImpl.setStartDate(marketingEvent.getStartDate());
		marketingEventImpl.setEndDate(marketingEvent.getEndDate());
		marketingEventImpl.setDateTBA(marketingEvent.isDateTBA());
		marketingEventImpl.setAddressId(marketingEvent.getAddressId());
		marketingEventImpl.setGlobalRegion(marketingEvent.getGlobalRegion());
		marketingEventImpl.setOnline(marketingEvent.isOnline());
		marketingEventImpl.setRegistrationType(marketingEvent.getRegistrationType());
		marketingEventImpl.setRegistrationURL(marketingEvent.getRegistrationURL());

		return marketingEventImpl;
	}

	/**
	 * Returns the marketing event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the marketing event
	 * @return the marketing event
	 * @throws com.liferay.portal.NoSuchModelException if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MarketingEvent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the marketing event with the primary key or throws a {@link com.liferay.osb.NoSuchMarketingEventException} if it could not be found.
	 *
	 * @param marketingEventId the primary key of the marketing event
	 * @return the marketing event
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent findByPrimaryKey(long marketingEventId)
		throws NoSuchMarketingEventException, SystemException {
		MarketingEvent marketingEvent = fetchByPrimaryKey(marketingEventId);

		if (marketingEvent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + marketingEventId);
			}

			throw new NoSuchMarketingEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				marketingEventId);
		}

		return marketingEvent;
	}

	/**
	 * Returns the marketing event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the marketing event
	 * @return the marketing event, or <code>null</code> if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MarketingEvent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the marketing event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param marketingEventId the primary key of the marketing event
	 * @return the marketing event, or <code>null</code> if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent fetchByPrimaryKey(long marketingEventId)
		throws SystemException {
		MarketingEvent marketingEvent = (MarketingEvent)EntityCacheUtil.getResult(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
				MarketingEventImpl.class, marketingEventId);

		if (marketingEvent == _nullMarketingEvent) {
			return null;
		}

		if (marketingEvent == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				marketingEvent = (MarketingEvent)session.get(MarketingEventImpl.class,
						Long.valueOf(marketingEventId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (marketingEvent != null) {
					cacheResult(marketingEvent);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MarketingEventModelImpl.ENTITY_CACHE_ENABLED,
						MarketingEventImpl.class, marketingEventId,
						_nullMarketingEvent);
				}

				closeSession(session);
			}
		}

		return marketingEvent;
	}

	/**
	 * Returns all the marketing events where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findByType(int type) throws SystemException {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marketing events where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of marketing events
	 * @param end the upper bound of the range of marketing events (not inclusive)
	 * @return the range of matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findByType(int type, int start, int end)
		throws SystemException {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the marketing events where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of marketing events
	 * @param end the upper bound of the range of marketing events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findByType(int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<MarketingEvent> list = (List<MarketingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MarketingEvent marketingEvent : list) {
				if ((type != marketingEvent.getType())) {
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

			query.append(_SQL_SELECT_MARKETINGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

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

				qPos.add(type);

				list = (List<MarketingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first marketing event in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marketing event
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent findByType_First(int type,
		OrderByComparator orderByComparator)
		throws NoSuchMarketingEventException, SystemException {
		MarketingEvent marketingEvent = fetchByType_First(type,
				orderByComparator);

		if (marketingEvent != null) {
			return marketingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketingEventException(msg.toString());
	}

	/**
	 * Returns the first marketing event in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marketing event, or <code>null</code> if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent fetchByType_First(int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<MarketingEvent> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last marketing event in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marketing event
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent findByType_Last(int type,
		OrderByComparator orderByComparator)
		throws NoSuchMarketingEventException, SystemException {
		MarketingEvent marketingEvent = fetchByType_Last(type, orderByComparator);

		if (marketingEvent != null) {
			return marketingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketingEventException(msg.toString());
	}

	/**
	 * Returns the last marketing event in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marketing event, or <code>null</code> if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent fetchByType_Last(int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByType(type);

		List<MarketingEvent> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the marketing events before and after the current marketing event in the ordered set where type = &#63;.
	 *
	 * @param marketingEventId the primary key of the current marketing event
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next marketing event
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent[] findByType_PrevAndNext(long marketingEventId,
		int type, OrderByComparator orderByComparator)
		throws NoSuchMarketingEventException, SystemException {
		MarketingEvent marketingEvent = findByPrimaryKey(marketingEventId);

		Session session = null;

		try {
			session = openSession();

			MarketingEvent[] array = new MarketingEventImpl[3];

			array[0] = getByType_PrevAndNext(session, marketingEvent, type,
					orderByComparator, true);

			array[1] = marketingEvent;

			array[2] = getByType_PrevAndNext(session, marketingEvent, type,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MarketingEvent getByType_PrevAndNext(Session session,
		MarketingEvent marketingEvent, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MARKETINGEVENT_WHERE);

		query.append(_FINDER_COLUMN_TYPE_TYPE_2);

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

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(marketingEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MarketingEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the marketing events where globalRegion = &#63;.
	 *
	 * @param globalRegion the global region
	 * @return the matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findByGlobalRegion(int globalRegion)
		throws SystemException {
		return findByGlobalRegion(globalRegion, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marketing events where globalRegion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param globalRegion the global region
	 * @param start the lower bound of the range of marketing events
	 * @param end the upper bound of the range of marketing events (not inclusive)
	 * @return the range of matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findByGlobalRegion(int globalRegion, int start,
		int end) throws SystemException {
		return findByGlobalRegion(globalRegion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the marketing events where globalRegion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param globalRegion the global region
	 * @param start the lower bound of the range of marketing events
	 * @param end the upper bound of the range of marketing events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findByGlobalRegion(int globalRegion, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GLOBALREGION;
			finderArgs = new Object[] { globalRegion };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GLOBALREGION;
			finderArgs = new Object[] {
					globalRegion,
					
					start, end, orderByComparator
				};
		}

		List<MarketingEvent> list = (List<MarketingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MarketingEvent marketingEvent : list) {
				if ((globalRegion != marketingEvent.getGlobalRegion())) {
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

			query.append(_SQL_SELECT_MARKETINGEVENT_WHERE);

			query.append(_FINDER_COLUMN_GLOBALREGION_GLOBALREGION_2);

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

				qPos.add(globalRegion);

				list = (List<MarketingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first marketing event in the ordered set where globalRegion = &#63;.
	 *
	 * @param globalRegion the global region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marketing event
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent findByGlobalRegion_First(int globalRegion,
		OrderByComparator orderByComparator)
		throws NoSuchMarketingEventException, SystemException {
		MarketingEvent marketingEvent = fetchByGlobalRegion_First(globalRegion,
				orderByComparator);

		if (marketingEvent != null) {
			return marketingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("globalRegion=");
		msg.append(globalRegion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketingEventException(msg.toString());
	}

	/**
	 * Returns the first marketing event in the ordered set where globalRegion = &#63;.
	 *
	 * @param globalRegion the global region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching marketing event, or <code>null</code> if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent fetchByGlobalRegion_First(int globalRegion,
		OrderByComparator orderByComparator) throws SystemException {
		List<MarketingEvent> list = findByGlobalRegion(globalRegion, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last marketing event in the ordered set where globalRegion = &#63;.
	 *
	 * @param globalRegion the global region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marketing event
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent findByGlobalRegion_Last(int globalRegion,
		OrderByComparator orderByComparator)
		throws NoSuchMarketingEventException, SystemException {
		MarketingEvent marketingEvent = fetchByGlobalRegion_Last(globalRegion,
				orderByComparator);

		if (marketingEvent != null) {
			return marketingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("globalRegion=");
		msg.append(globalRegion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMarketingEventException(msg.toString());
	}

	/**
	 * Returns the last marketing event in the ordered set where globalRegion = &#63;.
	 *
	 * @param globalRegion the global region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching marketing event, or <code>null</code> if a matching marketing event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent fetchByGlobalRegion_Last(int globalRegion,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGlobalRegion(globalRegion);

		List<MarketingEvent> list = findByGlobalRegion(globalRegion, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the marketing events before and after the current marketing event in the ordered set where globalRegion = &#63;.
	 *
	 * @param marketingEventId the primary key of the current marketing event
	 * @param globalRegion the global region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next marketing event
	 * @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MarketingEvent[] findByGlobalRegion_PrevAndNext(
		long marketingEventId, int globalRegion,
		OrderByComparator orderByComparator)
		throws NoSuchMarketingEventException, SystemException {
		MarketingEvent marketingEvent = findByPrimaryKey(marketingEventId);

		Session session = null;

		try {
			session = openSession();

			MarketingEvent[] array = new MarketingEventImpl[3];

			array[0] = getByGlobalRegion_PrevAndNext(session, marketingEvent,
					globalRegion, orderByComparator, true);

			array[1] = marketingEvent;

			array[2] = getByGlobalRegion_PrevAndNext(session, marketingEvent,
					globalRegion, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MarketingEvent getByGlobalRegion_PrevAndNext(Session session,
		MarketingEvent marketingEvent, int globalRegion,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MARKETINGEVENT_WHERE);

		query.append(_FINDER_COLUMN_GLOBALREGION_GLOBALREGION_2);

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

		qPos.add(globalRegion);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(marketingEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MarketingEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the marketing events.
	 *
	 * @return the marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the marketing events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of marketing events
	 * @param end the upper bound of the range of marketing events (not inclusive)
	 * @return the range of marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the marketing events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of marketing events
	 * @param end the upper bound of the range of marketing events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public List<MarketingEvent> findAll(int start, int end,
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

		List<MarketingEvent> list = (List<MarketingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MARKETINGEVENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MARKETINGEVENT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MarketingEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MarketingEvent>)QueryUtil.list(q,
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
	 * Removes all the marketing events where type = &#63; from the database.
	 *
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByType(int type) throws SystemException {
		for (MarketingEvent marketingEvent : findByType(type)) {
			remove(marketingEvent);
		}
	}

	/**
	 * Removes all the marketing events where globalRegion = &#63; from the database.
	 *
	 * @param globalRegion the global region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGlobalRegion(int globalRegion)
		throws SystemException {
		for (MarketingEvent marketingEvent : findByGlobalRegion(globalRegion)) {
			remove(marketingEvent);
		}
	}

	/**
	 * Removes all the marketing events from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MarketingEvent marketingEvent : findAll()) {
			remove(marketingEvent);
		}
	}

	/**
	 * Returns the number of marketing events where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(int type) throws SystemException {
		Object[] finderArgs = new Object[] { type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MARKETINGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of marketing events where globalRegion = &#63;.
	 *
	 * @param globalRegion the global region
	 * @return the number of matching marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGlobalRegion(int globalRegion) throws SystemException {
		Object[] finderArgs = new Object[] { globalRegion };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GLOBALREGION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MARKETINGEVENT_WHERE);

			query.append(_FINDER_COLUMN_GLOBALREGION_GLOBALREGION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(globalRegion);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GLOBALREGION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of marketing events.
	 *
	 * @return the number of marketing events
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MARKETINGEVENT);

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
	 * Initializes the marketing event persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.MarketingEvent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MarketingEvent>> listenersList = new ArrayList<ModelListener<MarketingEvent>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<MarketingEvent>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MarketingEventImpl.class.getName());
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
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MARKETINGEVENT = "SELECT marketingEvent FROM MarketingEvent marketingEvent";
	private static final String _SQL_SELECT_MARKETINGEVENT_WHERE = "SELECT marketingEvent FROM MarketingEvent marketingEvent WHERE ";
	private static final String _SQL_COUNT_MARKETINGEVENT = "SELECT COUNT(marketingEvent) FROM MarketingEvent marketingEvent";
	private static final String _SQL_COUNT_MARKETINGEVENT_WHERE = "SELECT COUNT(marketingEvent) FROM MarketingEvent marketingEvent WHERE ";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "marketingEvent.type = ?";
	private static final String _FINDER_COLUMN_GLOBALREGION_GLOBALREGION_2 = "marketingEvent.globalRegion = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "marketingEvent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MarketingEvent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MarketingEvent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MarketingEventPersistenceImpl.class);
	private static MarketingEvent _nullMarketingEvent = new MarketingEventImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MarketingEvent> toCacheModel() {
				return _nullMarketingEventCacheModel;
			}
		};

	private static CacheModel<MarketingEvent> _nullMarketingEventCacheModel = new CacheModel<MarketingEvent>() {
			public MarketingEvent toEntityModel() {
				return _nullMarketingEvent;
			}
		};
}
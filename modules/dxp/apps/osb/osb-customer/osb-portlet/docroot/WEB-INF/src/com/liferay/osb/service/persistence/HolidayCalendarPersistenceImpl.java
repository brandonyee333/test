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

import com.liferay.osb.NoSuchHolidayCalendarException;
import com.liferay.osb.model.HolidayCalendar;
import com.liferay.osb.model.impl.HolidayCalendarImpl;
import com.liferay.osb.model.impl.HolidayCalendarModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
 * The persistence implementation for the holiday calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarPersistence
 * @see HolidayCalendarUtil
 * @generated
 */
public class HolidayCalendarPersistenceImpl extends BasePersistenceImpl<HolidayCalendar>
	implements HolidayCalendarPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HolidayCalendarUtil} to access the holiday calendar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HolidayCalendarImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the holiday calendar in the entity cache if it is enabled.
	 *
	 * @param holidayCalendar the holiday calendar
	 */
	public void cacheResult(HolidayCalendar holidayCalendar) {
		EntityCacheUtil.putResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey(),
			holidayCalendar);

		holidayCalendar.resetOriginalValues();
	}

	/**
	 * Caches the holiday calendars in the entity cache if it is enabled.
	 *
	 * @param holidayCalendars the holiday calendars
	 */
	public void cacheResult(List<HolidayCalendar> holidayCalendars) {
		for (HolidayCalendar holidayCalendar : holidayCalendars) {
			if (EntityCacheUtil.getResult(
						HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarImpl.class,
						holidayCalendar.getPrimaryKey()) == null) {
				cacheResult(holidayCalendar);
			}
			else {
				holidayCalendar.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all holiday calendars.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HolidayCalendarImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HolidayCalendarImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the holiday calendar.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HolidayCalendar holidayCalendar) {
		EntityCacheUtil.removeResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HolidayCalendar> holidayCalendars) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HolidayCalendar holidayCalendar : holidayCalendars) {
			EntityCacheUtil.removeResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey());
		}
	}

	/**
	 * Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	 *
	 * @param holidayCalendarId the primary key for the new holiday calendar
	 * @return the new holiday calendar
	 */
	public HolidayCalendar create(long holidayCalendarId) {
		HolidayCalendar holidayCalendar = new HolidayCalendarImpl();

		holidayCalendar.setNew(true);
		holidayCalendar.setPrimaryKey(holidayCalendarId);

		return holidayCalendar;
	}

	/**
	 * Removes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayCalendarId the primary key of the holiday calendar
	 * @return the holiday calendar that was removed
	 * @throws com.liferay.osb.NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendar remove(long holidayCalendarId)
		throws NoSuchHolidayCalendarException, SystemException {
		return remove(Long.valueOf(holidayCalendarId));
	}

	/**
	 * Removes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the holiday calendar
	 * @return the holiday calendar that was removed
	 * @throws com.liferay.osb.NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HolidayCalendar remove(Serializable primaryKey)
		throws NoSuchHolidayCalendarException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HolidayCalendar holidayCalendar = (HolidayCalendar)session.get(HolidayCalendarImpl.class,
					primaryKey);

			if (holidayCalendar == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHolidayCalendarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(holidayCalendar);
		}
		catch (NoSuchHolidayCalendarException nsee) {
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
	protected HolidayCalendar removeImpl(HolidayCalendar holidayCalendar)
		throws SystemException {
		holidayCalendar = toUnwrappedModel(holidayCalendar);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, holidayCalendar);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(holidayCalendar);

		return holidayCalendar;
	}

	@Override
	public HolidayCalendar updateImpl(
		com.liferay.osb.model.HolidayCalendar holidayCalendar, boolean merge)
		throws SystemException {
		holidayCalendar = toUnwrappedModel(holidayCalendar);

		boolean isNew = holidayCalendar.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, holidayCalendar, merge);

			holidayCalendar.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarImpl.class, holidayCalendar.getPrimaryKey(),
			holidayCalendar);

		return holidayCalendar;
	}

	protected HolidayCalendar toUnwrappedModel(HolidayCalendar holidayCalendar) {
		if (holidayCalendar instanceof HolidayCalendarImpl) {
			return holidayCalendar;
		}

		HolidayCalendarImpl holidayCalendarImpl = new HolidayCalendarImpl();

		holidayCalendarImpl.setNew(holidayCalendar.isNew());
		holidayCalendarImpl.setPrimaryKey(holidayCalendar.getPrimaryKey());

		holidayCalendarImpl.setHolidayCalendarId(holidayCalendar.getHolidayCalendarId());
		holidayCalendarImpl.setName(holidayCalendar.getName());
		holidayCalendarImpl.setDescription(holidayCalendar.getDescription());

		return holidayCalendarImpl;
	}

	/**
	 * Returns the holiday calendar with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar
	 * @return the holiday calendar
	 * @throws com.liferay.portal.NoSuchModelException if a holiday calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HolidayCalendar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the holiday calendar with the primary key or throws a {@link com.liferay.osb.NoSuchHolidayCalendarException} if it could not be found.
	 *
	 * @param holidayCalendarId the primary key of the holiday calendar
	 * @return the holiday calendar
	 * @throws com.liferay.osb.NoSuchHolidayCalendarException if a holiday calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendar findByPrimaryKey(long holidayCalendarId)
		throws NoSuchHolidayCalendarException, SystemException {
		HolidayCalendar holidayCalendar = fetchByPrimaryKey(holidayCalendarId);

		if (holidayCalendar == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + holidayCalendarId);
			}

			throw new NoSuchHolidayCalendarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				holidayCalendarId);
		}

		return holidayCalendar;
	}

	/**
	 * Returns the holiday calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar
	 * @return the holiday calendar, or <code>null</code> if a holiday calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HolidayCalendar fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the holiday calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayCalendarId the primary key of the holiday calendar
	 * @return the holiday calendar, or <code>null</code> if a holiday calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendar fetchByPrimaryKey(long holidayCalendarId)
		throws SystemException {
		HolidayCalendar holidayCalendar = (HolidayCalendar)EntityCacheUtil.getResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarImpl.class, holidayCalendarId);

		if (holidayCalendar == _nullHolidayCalendar) {
			return null;
		}

		if (holidayCalendar == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				holidayCalendar = (HolidayCalendar)session.get(HolidayCalendarImpl.class,
						Long.valueOf(holidayCalendarId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (holidayCalendar != null) {
					cacheResult(holidayCalendar);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HolidayCalendarModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarImpl.class, holidayCalendarId,
						_nullHolidayCalendar);
				}

				closeSession(session);
			}
		}

		return holidayCalendar;
	}

	/**
	 * Returns all the holiday calendars.
	 *
	 * @return the holiday calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendar> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendars
	 * @param end the upper bound of the range of holiday calendars (not inclusive)
	 * @return the range of holiday calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendar> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendars
	 * @param end the upper bound of the range of holiday calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of holiday calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendar> findAll(int start, int end,
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

		List<HolidayCalendar> list = (List<HolidayCalendar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HOLIDAYCALENDAR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HOLIDAYCALENDAR;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HolidayCalendar>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HolidayCalendar>)QueryUtil.list(q,
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
	 * Removes all the holiday calendars from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HolidayCalendar holidayCalendar : findAll()) {
			remove(holidayCalendar);
		}
	}

	/**
	 * Returns the number of holiday calendars.
	 *
	 * @return the number of holiday calendars
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HOLIDAYCALENDAR);

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
	 * Initializes the holiday calendar persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.HolidayCalendar")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HolidayCalendar>> listenersList = new ArrayList<ModelListener<HolidayCalendar>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<HolidayCalendar>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HolidayCalendarImpl.class.getName());
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
	private static final String _SQL_SELECT_HOLIDAYCALENDAR = "SELECT holidayCalendar FROM HolidayCalendar holidayCalendar";
	private static final String _SQL_COUNT_HOLIDAYCALENDAR = "SELECT COUNT(holidayCalendar) FROM HolidayCalendar holidayCalendar";
	private static final String _ORDER_BY_ENTITY_ALIAS = "holidayCalendar.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HolidayCalendar exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HolidayCalendarPersistenceImpl.class);
	private static HolidayCalendar _nullHolidayCalendar = new HolidayCalendarImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HolidayCalendar> toCacheModel() {
				return _nullHolidayCalendarCacheModel;
			}
		};

	private static CacheModel<HolidayCalendar> _nullHolidayCalendarCacheModel = new CacheModel<HolidayCalendar>() {
			public HolidayCalendar toEntityModel() {
				return _nullHolidayCalendar;
			}
		};
}
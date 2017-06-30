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

import com.liferay.osb.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.model.LCSSubscriptionEntry;
import com.liferay.osb.model.impl.LCSSubscriptionEntryImpl;
import com.liferay.osb.model.impl.LCSSubscriptionEntryModelImpl;

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
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the l c s subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntryPersistence
 * @see LCSSubscriptionEntryUtil
 * @generated
 */
public class LCSSubscriptionEntryPersistenceImpl extends BasePersistenceImpl<LCSSubscriptionEntry>
	implements LCSSubscriptionEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LCSSubscriptionEntryUtil} to access the l c s subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LCSSubscriptionEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the l c s subscription entry in the entity cache if it is enabled.
	 *
	 * @param lcsSubscriptionEntry the l c s subscription entry
	 */
	public void cacheResult(LCSSubscriptionEntry lcsSubscriptionEntry) {
		EntityCacheUtil.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			lcsSubscriptionEntry.getPrimaryKey(), lcsSubscriptionEntry);

		lcsSubscriptionEntry.resetOriginalValues();
	}

	/**
	 * Caches the l c s subscription entries in the entity cache if it is enabled.
	 *
	 * @param lcsSubscriptionEntries the l c s subscription entries
	 */
	public void cacheResult(List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsSubscriptionEntries) {
			if (EntityCacheUtil.getResult(
						LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSSubscriptionEntryImpl.class,
						lcsSubscriptionEntry.getPrimaryKey()) == null) {
				cacheResult(lcsSubscriptionEntry);
			}
			else {
				lcsSubscriptionEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all l c s subscription entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LCSSubscriptionEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LCSSubscriptionEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the l c s subscription entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LCSSubscriptionEntry lcsSubscriptionEntry) {
		EntityCacheUtil.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class, lcsSubscriptionEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsSubscriptionEntries) {
			EntityCacheUtil.removeResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSSubscriptionEntryImpl.class,
				lcsSubscriptionEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	 *
	 * @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	 * @return the new l c s subscription entry
	 */
	public LCSSubscriptionEntry create(long lcsSubscriptionEntryId) {
		LCSSubscriptionEntry lcsSubscriptionEntry = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntry.setNew(true);
		lcsSubscriptionEntry.setPrimaryKey(lcsSubscriptionEntryId);

		return lcsSubscriptionEntry;
	}

	/**
	 * Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	 * @return the l c s subscription entry that was removed
	 * @throws com.liferay.osb.NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LCSSubscriptionEntry remove(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException, SystemException {
		return remove(Long.valueOf(lcsSubscriptionEntryId));
	}

	/**
	 * Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the l c s subscription entry
	 * @return the l c s subscription entry that was removed
	 * @throws com.liferay.osb.NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LCSSubscriptionEntry remove(Serializable primaryKey)
		throws NoSuchLCSSubscriptionEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			LCSSubscriptionEntry lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
					primaryKey);

			if (lcsSubscriptionEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLCSSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lcsSubscriptionEntry);
		}
		catch (NoSuchLCSSubscriptionEntryException nsee) {
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
	protected LCSSubscriptionEntry removeImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry) throws SystemException {
		lcsSubscriptionEntry = toUnwrappedModel(lcsSubscriptionEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, lcsSubscriptionEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(lcsSubscriptionEntry);

		return lcsSubscriptionEntry;
	}

	@Override
	public LCSSubscriptionEntry updateImpl(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry,
		boolean merge) throws SystemException {
		lcsSubscriptionEntry = toUnwrappedModel(lcsSubscriptionEntry);

		boolean isNew = lcsSubscriptionEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, lcsSubscriptionEntry, merge);

			lcsSubscriptionEntry.setNew(false);
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

		EntityCacheUtil.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			LCSSubscriptionEntryImpl.class,
			lcsSubscriptionEntry.getPrimaryKey(), lcsSubscriptionEntry);

		return lcsSubscriptionEntry;
	}

	protected LCSSubscriptionEntry toUnwrappedModel(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		if (lcsSubscriptionEntry instanceof LCSSubscriptionEntryImpl) {
			return lcsSubscriptionEntry;
		}

		LCSSubscriptionEntryImpl lcsSubscriptionEntryImpl = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntryImpl.setNew(lcsSubscriptionEntry.isNew());
		lcsSubscriptionEntryImpl.setPrimaryKey(lcsSubscriptionEntry.getPrimaryKey());

		lcsSubscriptionEntryImpl.setLcsSubscriptionEntryId(lcsSubscriptionEntry.getLcsSubscriptionEntryId());
		lcsSubscriptionEntryImpl.setLcsProjectId(lcsSubscriptionEntry.getLcsProjectId());
		lcsSubscriptionEntryImpl.setProduct(lcsSubscriptionEntry.getProduct());
		lcsSubscriptionEntryImpl.setProductVersion(lcsSubscriptionEntry.getProductVersion());
		lcsSubscriptionEntryImpl.setType(lcsSubscriptionEntry.getType());
		lcsSubscriptionEntryImpl.setPlatform(lcsSubscriptionEntry.getPlatform());
		lcsSubscriptionEntryImpl.setPlatformVersion(lcsSubscriptionEntry.getPlatformVersion());
		lcsSubscriptionEntryImpl.setServersAllowed(lcsSubscriptionEntry.getServersAllowed());
		lcsSubscriptionEntryImpl.setServersUsed(lcsSubscriptionEntry.getServersUsed());
		lcsSubscriptionEntryImpl.setInstanceSize(lcsSubscriptionEntry.getInstanceSize());
		lcsSubscriptionEntryImpl.setStartDate(lcsSubscriptionEntry.getStartDate());
		lcsSubscriptionEntryImpl.setEndDate(lcsSubscriptionEntry.getEndDate());
		lcsSubscriptionEntryImpl.setSupportStartDate(lcsSubscriptionEntry.getSupportStartDate());
		lcsSubscriptionEntryImpl.setSupportEndDate(lcsSubscriptionEntry.getSupportEndDate());
		lcsSubscriptionEntryImpl.setActualPrice(lcsSubscriptionEntry.getActualPrice());
		lcsSubscriptionEntryImpl.setCurrencyCode(lcsSubscriptionEntry.getCurrencyCode());
		lcsSubscriptionEntryImpl.setActive(lcsSubscriptionEntry.isActive());

		return lcsSubscriptionEntryImpl;
	}

	/**
	 * Returns the l c s subscription entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s subscription entry
	 * @return the l c s subscription entry
	 * @throws com.liferay.portal.NoSuchModelException if a l c s subscription entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LCSSubscriptionEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the l c s subscription entry with the primary key or throws a {@link com.liferay.osb.NoSuchLCSSubscriptionEntryException} if it could not be found.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	 * @return the l c s subscription entry
	 * @throws com.liferay.osb.NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LCSSubscriptionEntry findByPrimaryKey(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException, SystemException {
		LCSSubscriptionEntry lcsSubscriptionEntry = fetchByPrimaryKey(lcsSubscriptionEntryId);

		if (lcsSubscriptionEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					lcsSubscriptionEntryId);
			}

			throw new NoSuchLCSSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				lcsSubscriptionEntryId);
		}

		return lcsSubscriptionEntry;
	}

	/**
	 * Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the l c s subscription entry
	 * @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LCSSubscriptionEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	 * @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LCSSubscriptionEntry fetchByPrimaryKey(long lcsSubscriptionEntryId)
		throws SystemException {
		LCSSubscriptionEntry lcsSubscriptionEntry = (LCSSubscriptionEntry)EntityCacheUtil.getResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				LCSSubscriptionEntryImpl.class, lcsSubscriptionEntryId);

		if (lcsSubscriptionEntry == _nullLCSSubscriptionEntry) {
			return null;
		}

		if (lcsSubscriptionEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				lcsSubscriptionEntry = (LCSSubscriptionEntry)session.get(LCSSubscriptionEntryImpl.class,
						Long.valueOf(lcsSubscriptionEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (lcsSubscriptionEntry != null) {
					cacheResult(lcsSubscriptionEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(LCSSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						LCSSubscriptionEntryImpl.class, lcsSubscriptionEntryId,
						_nullLCSSubscriptionEntry);
				}

				closeSession(session);
			}
		}

		return lcsSubscriptionEntry;
	}

	/**
	 * Returns all the l c s subscription entries.
	 *
	 * @return the l c s subscription entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LCSSubscriptionEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the l c s subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @return the range of l c s subscription entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LCSSubscriptionEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the l c s subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of l c s subscription entries
	 * @param end the upper bound of the range of l c s subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of l c s subscription entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<LCSSubscriptionEntry> findAll(int start, int end,
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

		List<LCSSubscriptionEntry> list = (List<LCSSubscriptionEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LCSSUBSCRIPTIONENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LCSSUBSCRIPTIONENTRY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<LCSSubscriptionEntry>)QueryUtil.list(q,
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
	 * Removes all the l c s subscription entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (LCSSubscriptionEntry lcsSubscriptionEntry : findAll()) {
			remove(lcsSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of l c s subscription entries.
	 *
	 * @return the number of l c s subscription entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LCSSUBSCRIPTIONENTRY);

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
	 * Initializes the l c s subscription entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.LCSSubscriptionEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<LCSSubscriptionEntry>> listenersList = new ArrayList<ModelListener<LCSSubscriptionEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<LCSSubscriptionEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(LCSSubscriptionEntryImpl.class.getName());
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
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_LCSSUBSCRIPTIONENTRY = "SELECT lcsSubscriptionEntry FROM LCSSubscriptionEntry lcsSubscriptionEntry";
	private static final String _SQL_COUNT_LCSSUBSCRIPTIONENTRY = "SELECT COUNT(lcsSubscriptionEntry) FROM LCSSubscriptionEntry lcsSubscriptionEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lcsSubscriptionEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LCSSubscriptionEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(LCSSubscriptionEntryPersistenceImpl.class);
	private static LCSSubscriptionEntry _nullLCSSubscriptionEntry = new LCSSubscriptionEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<LCSSubscriptionEntry> toCacheModel() {
				return _nullLCSSubscriptionEntryCacheModel;
			}
		};

	private static CacheModel<LCSSubscriptionEntry> _nullLCSSubscriptionEntryCacheModel =
		new CacheModel<LCSSubscriptionEntry>() {
			public LCSSubscriptionEntry toEntityModel() {
				return _nullLCSSubscriptionEntry;
			}
		};
}
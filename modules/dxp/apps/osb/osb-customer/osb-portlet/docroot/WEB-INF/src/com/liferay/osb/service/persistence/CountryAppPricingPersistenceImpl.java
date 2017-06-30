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

import com.liferay.osb.NoSuchCountryAppPricingException;
import com.liferay.osb.model.CountryAppPricing;
import com.liferay.osb.model.impl.CountryAppPricingImpl;
import com.liferay.osb.model.impl.CountryAppPricingModelImpl;

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
import com.liferay.portal.service.persistence.CountryPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the country app pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryAppPricingPersistence
 * @see CountryAppPricingUtil
 * @generated
 */
public class CountryAppPricingPersistenceImpl extends BasePersistenceImpl<CountryAppPricing>
	implements CountryAppPricingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CountryAppPricingUtil} to access the country app pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CountryAppPricingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppVersionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppVersionId",
			new String[] { Long.class.getName() },
			CountryAppPricingModelImpl.APPVERSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPVERSIONID = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppVersionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPPRICINGID =
		new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppPricingId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID =
		new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppPricingId",
			new String[] { Long.class.getName() },
			CountryAppPricingModelImpl.APPPRICINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPPRICINGID = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppPricingId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COUNTRYID =
		new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCountryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYID =
		new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCountryId",
			new String[] { Long.class.getName() },
			CountryAppPricingModelImpl.COUNTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COUNTRYID = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCountryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AVI_CI = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAVI_CI",
			new String[] { Long.class.getName(), Long.class.getName() },
			CountryAppPricingModelImpl.APPVERSIONID_COLUMN_BITMASK |
			CountryAppPricingModelImpl.COUNTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AVI_CI = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAVI_CI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED,
			CountryAppPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the country app pricing in the entity cache if it is enabled.
	 *
	 * @param countryAppPricing the country app pricing
	 */
	public void cacheResult(CountryAppPricing countryAppPricing) {
		EntityCacheUtil.putResult(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingImpl.class, countryAppPricing.getPrimaryKey(),
			countryAppPricing);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_CI,
			new Object[] {
				Long.valueOf(countryAppPricing.getAppVersionId()),
				Long.valueOf(countryAppPricing.getCountryId())
			}, countryAppPricing);

		countryAppPricing.resetOriginalValues();
	}

	/**
	 * Caches the country app pricings in the entity cache if it is enabled.
	 *
	 * @param countryAppPricings the country app pricings
	 */
	public void cacheResult(List<CountryAppPricing> countryAppPricings) {
		for (CountryAppPricing countryAppPricing : countryAppPricings) {
			if (EntityCacheUtil.getResult(
						CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
						CountryAppPricingImpl.class,
						countryAppPricing.getPrimaryKey()) == null) {
				cacheResult(countryAppPricing);
			}
			else {
				countryAppPricing.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all country app pricings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CountryAppPricingImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CountryAppPricingImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the country app pricing.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CountryAppPricing countryAppPricing) {
		EntityCacheUtil.removeResult(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingImpl.class, countryAppPricing.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(countryAppPricing);
	}

	@Override
	public void clearCache(List<CountryAppPricing> countryAppPricings) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CountryAppPricing countryAppPricing : countryAppPricings) {
			EntityCacheUtil.removeResult(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
				CountryAppPricingImpl.class, countryAppPricing.getPrimaryKey());

			clearUniqueFindersCache(countryAppPricing);
		}
	}

	protected void cacheUniqueFindersCache(CountryAppPricing countryAppPricing) {
		if (countryAppPricing.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(countryAppPricing.getAppVersionId()),
					Long.valueOf(countryAppPricing.getCountryId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AVI_CI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_CI, args,
				countryAppPricing);
		}
		else {
			CountryAppPricingModelImpl countryAppPricingModelImpl = (CountryAppPricingModelImpl)countryAppPricing;

			if ((countryAppPricingModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AVI_CI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(countryAppPricing.getAppVersionId()),
						Long.valueOf(countryAppPricing.getCountryId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AVI_CI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_CI, args,
					countryAppPricing);
			}
		}
	}

	protected void clearUniqueFindersCache(CountryAppPricing countryAppPricing) {
		CountryAppPricingModelImpl countryAppPricingModelImpl = (CountryAppPricingModelImpl)countryAppPricing;

		Object[] args = new Object[] {
				Long.valueOf(countryAppPricing.getAppVersionId()),
				Long.valueOf(countryAppPricing.getCountryId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVI_CI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AVI_CI, args);

		if ((countryAppPricingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AVI_CI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(countryAppPricingModelImpl.getOriginalAppVersionId()),
					Long.valueOf(countryAppPricingModelImpl.getOriginalCountryId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AVI_CI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AVI_CI, args);
		}
	}

	/**
	 * Creates a new country app pricing with the primary key. Does not add the country app pricing to the database.
	 *
	 * @param countryAppPricingId the primary key for the new country app pricing
	 * @return the new country app pricing
	 */
	public CountryAppPricing create(long countryAppPricingId) {
		CountryAppPricing countryAppPricing = new CountryAppPricingImpl();

		countryAppPricing.setNew(true);
		countryAppPricing.setPrimaryKey(countryAppPricingId);

		return countryAppPricing;
	}

	/**
	 * Removes the country app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param countryAppPricingId the primary key of the country app pricing
	 * @return the country app pricing that was removed
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing remove(long countryAppPricingId)
		throws NoSuchCountryAppPricingException, SystemException {
		return remove(Long.valueOf(countryAppPricingId));
	}

	/**
	 * Removes the country app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the country app pricing
	 * @return the country app pricing that was removed
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CountryAppPricing remove(Serializable primaryKey)
		throws NoSuchCountryAppPricingException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CountryAppPricing countryAppPricing = (CountryAppPricing)session.get(CountryAppPricingImpl.class,
					primaryKey);

			if (countryAppPricing == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCountryAppPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(countryAppPricing);
		}
		catch (NoSuchCountryAppPricingException nsee) {
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
	protected CountryAppPricing removeImpl(CountryAppPricing countryAppPricing)
		throws SystemException {
		countryAppPricing = toUnwrappedModel(countryAppPricing);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, countryAppPricing);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(countryAppPricing);

		return countryAppPricing;
	}

	@Override
	public CountryAppPricing updateImpl(
		com.liferay.osb.model.CountryAppPricing countryAppPricing, boolean merge)
		throws SystemException {
		countryAppPricing = toUnwrappedModel(countryAppPricing);

		boolean isNew = countryAppPricing.isNew();

		CountryAppPricingModelImpl countryAppPricingModelImpl = (CountryAppPricingModelImpl)countryAppPricing;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, countryAppPricing, merge);

			countryAppPricing.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CountryAppPricingModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((countryAppPricingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(countryAppPricingModelImpl.getOriginalAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);

				args = new Object[] {
						Long.valueOf(countryAppPricingModelImpl.getAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);
			}

			if ((countryAppPricingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(countryAppPricingModelImpl.getOriginalAppPricingId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID,
					args);

				args = new Object[] {
						Long.valueOf(countryAppPricingModelImpl.getAppPricingId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID,
					args);
			}

			if ((countryAppPricingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(countryAppPricingModelImpl.getOriginalCountryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COUNTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYID,
					args);

				args = new Object[] {
						Long.valueOf(countryAppPricingModelImpl.getCountryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COUNTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
			CountryAppPricingImpl.class, countryAppPricing.getPrimaryKey(),
			countryAppPricing);

		clearUniqueFindersCache(countryAppPricing);
		cacheUniqueFindersCache(countryAppPricing);

		return countryAppPricing;
	}

	protected CountryAppPricing toUnwrappedModel(
		CountryAppPricing countryAppPricing) {
		if (countryAppPricing instanceof CountryAppPricingImpl) {
			return countryAppPricing;
		}

		CountryAppPricingImpl countryAppPricingImpl = new CountryAppPricingImpl();

		countryAppPricingImpl.setNew(countryAppPricing.isNew());
		countryAppPricingImpl.setPrimaryKey(countryAppPricing.getPrimaryKey());

		countryAppPricingImpl.setCountryAppPricingId(countryAppPricing.getCountryAppPricingId());
		countryAppPricingImpl.setAppEntryId(countryAppPricing.getAppEntryId());
		countryAppPricingImpl.setAppVersionId(countryAppPricing.getAppVersionId());
		countryAppPricingImpl.setAppPricingId(countryAppPricing.getAppPricingId());
		countryAppPricingImpl.setCountryId(countryAppPricing.getCountryId());
		countryAppPricingImpl.setName(countryAppPricing.getName());

		return countryAppPricingImpl;
	}

	/**
	 * Returns the country app pricing with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the country app pricing
	 * @return the country app pricing
	 * @throws com.liferay.portal.NoSuchModelException if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CountryAppPricing findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the country app pricing with the primary key or throws a {@link com.liferay.osb.NoSuchCountryAppPricingException} if it could not be found.
	 *
	 * @param countryAppPricingId the primary key of the country app pricing
	 * @return the country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByPrimaryKey(long countryAppPricingId)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByPrimaryKey(countryAppPricingId);

		if (countryAppPricing == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					countryAppPricingId);
			}

			throw new NoSuchCountryAppPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				countryAppPricingId);
		}

		return countryAppPricing;
	}

	/**
	 * Returns the country app pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the country app pricing
	 * @return the country app pricing, or <code>null</code> if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CountryAppPricing fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the country app pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param countryAppPricingId the primary key of the country app pricing
	 * @return the country app pricing, or <code>null</code> if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByPrimaryKey(long countryAppPricingId)
		throws SystemException {
		CountryAppPricing countryAppPricing = (CountryAppPricing)EntityCacheUtil.getResult(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
				CountryAppPricingImpl.class, countryAppPricingId);

		if (countryAppPricing == _nullCountryAppPricing) {
			return null;
		}

		if (countryAppPricing == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				countryAppPricing = (CountryAppPricing)session.get(CountryAppPricingImpl.class,
						Long.valueOf(countryAppPricingId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (countryAppPricing != null) {
					cacheResult(countryAppPricing);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CountryAppPricingModelImpl.ENTITY_CACHE_ENABLED,
						CountryAppPricingImpl.class, countryAppPricingId,
						_nullCountryAppPricing);
				}

				closeSession(session);
			}
		}

		return countryAppPricing;
	}

	/**
	 * Returns all the country app pricings where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByAppVersionId(long appVersionId)
		throws SystemException {
		return findByAppVersionId(appVersionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the country app pricings where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @return the range of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByAppVersionId(long appVersionId,
		int start, int end) throws SystemException {
		return findByAppVersionId(appVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the country app pricings where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByAppVersionId(long appVersionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID;
			finderArgs = new Object[] { appVersionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPVERSIONID;
			finderArgs = new Object[] {
					appVersionId,
					
					start, end, orderByComparator
				};
		}

		List<CountryAppPricing> list = (List<CountryAppPricing>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CountryAppPricing countryAppPricing : list) {
				if ((appVersionId != countryAppPricing.getAppVersionId())) {
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

			query.append(_SQL_SELECT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CountryAppPricingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				list = (List<CountryAppPricing>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first country app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByAppVersionId_First(appVersionId,
				orderByComparator);

		if (countryAppPricing != null) {
			return countryAppPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCountryAppPricingException(msg.toString());
	}

	/**
	 * Returns the first country app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<CountryAppPricing> list = findByAppVersionId(appVersionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last country app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByAppVersionId_Last(appVersionId,
				orderByComparator);

		if (countryAppPricing != null) {
			return countryAppPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCountryAppPricingException(msg.toString());
	}

	/**
	 * Returns the last country app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppVersionId(appVersionId);

		List<CountryAppPricing> list = findByAppVersionId(appVersionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the country app pricings before and after the current country app pricing in the ordered set where appVersionId = &#63;.
	 *
	 * @param countryAppPricingId the primary key of the current country app pricing
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing[] findByAppVersionId_PrevAndNext(
		long countryAppPricingId, long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = findByPrimaryKey(countryAppPricingId);

		Session session = null;

		try {
			session = openSession();

			CountryAppPricing[] array = new CountryAppPricingImpl[3];

			array[0] = getByAppVersionId_PrevAndNext(session,
					countryAppPricing, appVersionId, orderByComparator, true);

			array[1] = countryAppPricing;

			array[2] = getByAppVersionId_PrevAndNext(session,
					countryAppPricing, appVersionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CountryAppPricing getByAppVersionId_PrevAndNext(Session session,
		CountryAppPricing countryAppPricing, long appVersionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COUNTRYAPPPRICING_WHERE);

		query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

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
			query.append(CountryAppPricingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appVersionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(countryAppPricing);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CountryAppPricing> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the country app pricings where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @return the matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByAppPricingId(long appPricingId)
		throws SystemException {
		return findByAppPricingId(appPricingId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the country app pricings where appPricingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPricingId the app pricing ID
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @return the range of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByAppPricingId(long appPricingId,
		int start, int end) throws SystemException {
		return findByAppPricingId(appPricingId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the country app pricings where appPricingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appPricingId the app pricing ID
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByAppPricingId(long appPricingId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPPRICINGID;
			finderArgs = new Object[] { appPricingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPPRICINGID;
			finderArgs = new Object[] {
					appPricingId,
					
					start, end, orderByComparator
				};
		}

		List<CountryAppPricing> list = (List<CountryAppPricing>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CountryAppPricing countryAppPricing : list) {
				if ((appPricingId != countryAppPricing.getAppPricingId())) {
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

			query.append(_SQL_SELECT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CountryAppPricingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPricingId);

				list = (List<CountryAppPricing>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first country app pricing in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByAppPricingId_First(long appPricingId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByAppPricingId_First(appPricingId,
				orderByComparator);

		if (countryAppPricing != null) {
			return countryAppPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPricingId=");
		msg.append(appPricingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCountryAppPricingException(msg.toString());
	}

	/**
	 * Returns the first country app pricing in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByAppPricingId_First(long appPricingId,
		OrderByComparator orderByComparator) throws SystemException {
		List<CountryAppPricing> list = findByAppPricingId(appPricingId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last country app pricing in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByAppPricingId_Last(long appPricingId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByAppPricingId_Last(appPricingId,
				orderByComparator);

		if (countryAppPricing != null) {
			return countryAppPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appPricingId=");
		msg.append(appPricingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCountryAppPricingException(msg.toString());
	}

	/**
	 * Returns the last country app pricing in the ordered set where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByAppPricingId_Last(long appPricingId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppPricingId(appPricingId);

		List<CountryAppPricing> list = findByAppPricingId(appPricingId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the country app pricings before and after the current country app pricing in the ordered set where appPricingId = &#63;.
	 *
	 * @param countryAppPricingId the primary key of the current country app pricing
	 * @param appPricingId the app pricing ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing[] findByAppPricingId_PrevAndNext(
		long countryAppPricingId, long appPricingId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = findByPrimaryKey(countryAppPricingId);

		Session session = null;

		try {
			session = openSession();

			CountryAppPricing[] array = new CountryAppPricingImpl[3];

			array[0] = getByAppPricingId_PrevAndNext(session,
					countryAppPricing, appPricingId, orderByComparator, true);

			array[1] = countryAppPricing;

			array[2] = getByAppPricingId_PrevAndNext(session,
					countryAppPricing, appPricingId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CountryAppPricing getByAppPricingId_PrevAndNext(Session session,
		CountryAppPricing countryAppPricing, long appPricingId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COUNTRYAPPPRICING_WHERE);

		query.append(_FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2);

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
			query.append(CountryAppPricingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appPricingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(countryAppPricing);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CountryAppPricing> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the country app pricings where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @return the matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByCountryId(long countryId)
		throws SystemException {
		return findByCountryId(countryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the country app pricings where countryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @return the range of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByCountryId(long countryId, int start,
		int end) throws SystemException {
		return findByCountryId(countryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the country app pricings where countryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param countryId the country ID
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findByCountryId(long countryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYID;
			finderArgs = new Object[] { countryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COUNTRYID;
			finderArgs = new Object[] { countryId, start, end, orderByComparator };
		}

		List<CountryAppPricing> list = (List<CountryAppPricing>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CountryAppPricing countryAppPricing : list) {
				if ((countryId != countryAppPricing.getCountryId())) {
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

			query.append(_SQL_SELECT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_COUNTRYID_COUNTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CountryAppPricingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(countryId);

				list = (List<CountryAppPricing>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first country app pricing in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByCountryId_First(long countryId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByCountryId_First(countryId,
				orderByComparator);

		if (countryAppPricing != null) {
			return countryAppPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("countryId=");
		msg.append(countryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCountryAppPricingException(msg.toString());
	}

	/**
	 * Returns the first country app pricing in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByCountryId_First(long countryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<CountryAppPricing> list = findByCountryId(countryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last country app pricing in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByCountryId_Last(long countryId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByCountryId_Last(countryId,
				orderByComparator);

		if (countryAppPricing != null) {
			return countryAppPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("countryId=");
		msg.append(countryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCountryAppPricingException(msg.toString());
	}

	/**
	 * Returns the last country app pricing in the ordered set where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByCountryId_Last(long countryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCountryId(countryId);

		List<CountryAppPricing> list = findByCountryId(countryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the country app pricings before and after the current country app pricing in the ordered set where countryId = &#63;.
	 *
	 * @param countryAppPricingId the primary key of the current country app pricing
	 * @param countryId the country ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a country app pricing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing[] findByCountryId_PrevAndNext(
		long countryAppPricingId, long countryId,
		OrderByComparator orderByComparator)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = findByPrimaryKey(countryAppPricingId);

		Session session = null;

		try {
			session = openSession();

			CountryAppPricing[] array = new CountryAppPricingImpl[3];

			array[0] = getByCountryId_PrevAndNext(session, countryAppPricing,
					countryId, orderByComparator, true);

			array[1] = countryAppPricing;

			array[2] = getByCountryId_PrevAndNext(session, countryAppPricing,
					countryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CountryAppPricing getByCountryId_PrevAndNext(Session session,
		CountryAppPricing countryAppPricing, long countryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COUNTRYAPPPRICING_WHERE);

		query.append(_FINDER_COLUMN_COUNTRYID_COUNTRYID_2);

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
			query.append(CountryAppPricingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(countryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(countryAppPricing);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CountryAppPricing> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the country app pricing where appVersionId = &#63; and countryId = &#63; or throws a {@link com.liferay.osb.NoSuchCountryAppPricingException} if it could not be found.
	 *
	 * @param appVersionId the app version ID
	 * @param countryId the country ID
	 * @return the matching country app pricing
	 * @throws com.liferay.osb.NoSuchCountryAppPricingException if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing findByAVI_CI(long appVersionId, long countryId)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = fetchByAVI_CI(appVersionId,
				countryId);

		if (countryAppPricing == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appVersionId=");
			msg.append(appVersionId);

			msg.append(", countryId=");
			msg.append(countryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCountryAppPricingException(msg.toString());
		}

		return countryAppPricing;
	}

	/**
	 * Returns the country app pricing where appVersionId = &#63; and countryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appVersionId the app version ID
	 * @param countryId the country ID
	 * @return the matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByAVI_CI(long appVersionId, long countryId)
		throws SystemException {
		return fetchByAVI_CI(appVersionId, countryId, true);
	}

	/**
	 * Returns the country app pricing where appVersionId = &#63; and countryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appVersionId the app version ID
	 * @param countryId the country ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching country app pricing, or <code>null</code> if a matching country app pricing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing fetchByAVI_CI(long appVersionId, long countryId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId, countryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AVI_CI,
					finderArgs, this);
		}

		if (result instanceof CountryAppPricing) {
			CountryAppPricing countryAppPricing = (CountryAppPricing)result;

			if ((appVersionId != countryAppPricing.getAppVersionId()) ||
					(countryId != countryAppPricing.getCountryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_AVI_CI_APPVERSIONID_2);

			query.append(_FINDER_COLUMN_AVI_CI_COUNTRYID_2);

			query.append(CountryAppPricingModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				qPos.add(countryId);

				List<CountryAppPricing> list = q.list();

				result = list;

				CountryAppPricing countryAppPricing = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_CI,
						finderArgs, list);
				}
				else {
					countryAppPricing = list.get(0);

					cacheResult(countryAppPricing);

					if ((countryAppPricing.getAppVersionId() != appVersionId) ||
							(countryAppPricing.getCountryId() != countryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AVI_CI,
							finderArgs, countryAppPricing);
					}
				}

				return countryAppPricing;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AVI_CI,
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
				return (CountryAppPricing)result;
			}
		}
	}

	/**
	 * Returns all the country app pricings.
	 *
	 * @return the country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the country app pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @return the range of country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the country app pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of country app pricings
	 * @param end the upper bound of the range of country app pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CountryAppPricing> findAll(int start, int end,
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

		List<CountryAppPricing> list = (List<CountryAppPricing>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_COUNTRYAPPPRICING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COUNTRYAPPPRICING.concat(CountryAppPricingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CountryAppPricing>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CountryAppPricing>)QueryUtil.list(q,
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
	 * Removes all the country app pricings where appVersionId = &#63; from the database.
	 *
	 * @param appVersionId the app version ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppVersionId(long appVersionId)
		throws SystemException {
		for (CountryAppPricing countryAppPricing : findByAppVersionId(
				appVersionId)) {
			remove(countryAppPricing);
		}
	}

	/**
	 * Removes all the country app pricings where appPricingId = &#63; from the database.
	 *
	 * @param appPricingId the app pricing ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppPricingId(long appPricingId)
		throws SystemException {
		for (CountryAppPricing countryAppPricing : findByAppPricingId(
				appPricingId)) {
			remove(countryAppPricing);
		}
	}

	/**
	 * Removes all the country app pricings where countryId = &#63; from the database.
	 *
	 * @param countryId the country ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCountryId(long countryId) throws SystemException {
		for (CountryAppPricing countryAppPricing : findByCountryId(countryId)) {
			remove(countryAppPricing);
		}
	}

	/**
	 * Removes the country app pricing where appVersionId = &#63; and countryId = &#63; from the database.
	 *
	 * @param appVersionId the app version ID
	 * @param countryId the country ID
	 * @return the country app pricing that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CountryAppPricing removeByAVI_CI(long appVersionId, long countryId)
		throws NoSuchCountryAppPricingException, SystemException {
		CountryAppPricing countryAppPricing = findByAVI_CI(appVersionId,
				countryId);

		return remove(countryAppPricing);
	}

	/**
	 * Removes all the country app pricings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CountryAppPricing countryAppPricing : findAll()) {
			remove(countryAppPricing);
		}
	}

	/**
	 * Returns the number of country app pricings where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the number of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppVersionId(long appVersionId) throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of country app pricings where appPricingId = &#63;.
	 *
	 * @param appPricingId the app pricing ID
	 * @return the number of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppPricingId(long appPricingId) throws SystemException {
		Object[] finderArgs = new Object[] { appPricingId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appPricingId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPPRICINGID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of country app pricings where countryId = &#63;.
	 *
	 * @param countryId the country ID
	 * @return the number of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCountryId(long countryId) throws SystemException {
		Object[] finderArgs = new Object[] { countryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COUNTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_COUNTRYID_COUNTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(countryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COUNTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of country app pricings where appVersionId = &#63; and countryId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param countryId the country ID
	 * @return the number of matching country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAVI_CI(long appVersionId, long countryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId, countryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AVI_CI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COUNTRYAPPPRICING_WHERE);

			query.append(_FINDER_COLUMN_AVI_CI_APPVERSIONID_2);

			query.append(_FINDER_COLUMN_AVI_CI_COUNTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appVersionId);

				qPos.add(countryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AVI_CI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of country app pricings.
	 *
	 * @return the number of country app pricings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COUNTRYAPPPRICING);

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
	 * Initializes the country app pricing persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.CountryAppPricing")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CountryAppPricing>> listenersList = new ArrayList<ModelListener<CountryAppPricing>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<CountryAppPricing>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CountryAppPricingImpl.class.getName());
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
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_COUNTRYAPPPRICING = "SELECT countryAppPricing FROM CountryAppPricing countryAppPricing";
	private static final String _SQL_SELECT_COUNTRYAPPPRICING_WHERE = "SELECT countryAppPricing FROM CountryAppPricing countryAppPricing WHERE ";
	private static final String _SQL_COUNT_COUNTRYAPPPRICING = "SELECT COUNT(countryAppPricing) FROM CountryAppPricing countryAppPricing";
	private static final String _SQL_COUNT_COUNTRYAPPPRICING_WHERE = "SELECT COUNT(countryAppPricing) FROM CountryAppPricing countryAppPricing WHERE ";
	private static final String _FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2 = "countryAppPricing.appVersionId = ?";
	private static final String _FINDER_COLUMN_APPPRICINGID_APPPRICINGID_2 = "countryAppPricing.appPricingId = ?";
	private static final String _FINDER_COLUMN_COUNTRYID_COUNTRYID_2 = "countryAppPricing.countryId = ?";
	private static final String _FINDER_COLUMN_AVI_CI_APPVERSIONID_2 = "countryAppPricing.appVersionId = ? AND ";
	private static final String _FINDER_COLUMN_AVI_CI_COUNTRYID_2 = "countryAppPricing.countryId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "countryAppPricing.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CountryAppPricing exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CountryAppPricing exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CountryAppPricingPersistenceImpl.class);
	private static CountryAppPricing _nullCountryAppPricing = new CountryAppPricingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CountryAppPricing> toCacheModel() {
				return _nullCountryAppPricingCacheModel;
			}
		};

	private static CacheModel<CountryAppPricing> _nullCountryAppPricingCacheModel =
		new CacheModel<CountryAppPricing>() {
			public CountryAppPricing toEntityModel() {
				return _nullCountryAppPricing;
			}
		};
}
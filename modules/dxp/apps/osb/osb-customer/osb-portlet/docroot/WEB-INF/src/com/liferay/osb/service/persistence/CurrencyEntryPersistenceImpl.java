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

import com.liferay.osb.NoSuchCurrencyEntryException;
import com.liferay.osb.model.CurrencyEntry;
import com.liferay.osb.model.impl.CurrencyEntryImpl;
import com.liferay.osb.model.impl.CurrencyEntryModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
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
 * The persistence implementation for the currency entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntryPersistence
 * @see CurrencyEntryUtil
 * @generated
 */
public class CurrencyEntryPersistenceImpl extends BasePersistenceImpl<CurrencyEntry>
	implements CurrencyEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CurrencyEntryUtil} to access the currency entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CurrencyEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_CURRENCYCODE = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCurrencyCode", new String[] { String.class.getName() },
			CurrencyEntryModelImpl.CURRENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CURRENCYCODE = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCurrencyCode",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETPLACEENABLED =
		new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMarketplaceEnabled",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED =
		new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMarketplaceEnabled",
			new String[] { Boolean.class.getName() },
			CurrencyEntryModelImpl.MARKETPLACEENABLED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MARKETPLACEENABLED = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMarketplaceEnabled",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED,
			CurrencyEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the currency entry in the entity cache if it is enabled.
	 *
	 * @param currencyEntry the currency entry
	 */
	public void cacheResult(CurrencyEntry currencyEntry) {
		EntityCacheUtil.putResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryImpl.class, currencyEntry.getPrimaryKey(),
			currencyEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
			new Object[] { currencyEntry.getCurrencyCode() }, currencyEntry);

		currencyEntry.resetOriginalValues();
	}

	/**
	 * Caches the currency entries in the entity cache if it is enabled.
	 *
	 * @param currencyEntries the currency entries
	 */
	public void cacheResult(List<CurrencyEntry> currencyEntries) {
		for (CurrencyEntry currencyEntry : currencyEntries) {
			if (EntityCacheUtil.getResult(
						CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
						CurrencyEntryImpl.class, currencyEntry.getPrimaryKey()) == null) {
				cacheResult(currencyEntry);
			}
			else {
				currencyEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all currency entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CurrencyEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CurrencyEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the currency entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CurrencyEntry currencyEntry) {
		EntityCacheUtil.removeResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryImpl.class, currencyEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(currencyEntry);
	}

	@Override
	public void clearCache(List<CurrencyEntry> currencyEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CurrencyEntry currencyEntry : currencyEntries) {
			EntityCacheUtil.removeResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
				CurrencyEntryImpl.class, currencyEntry.getPrimaryKey());

			clearUniqueFindersCache(currencyEntry);
		}
	}

	protected void cacheUniqueFindersCache(CurrencyEntry currencyEntry) {
		if (currencyEntry.isNew()) {
			Object[] args = new Object[] { currencyEntry.getCurrencyCode() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENCYCODE, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE, args,
				currencyEntry);
		}
		else {
			CurrencyEntryModelImpl currencyEntryModelImpl = (CurrencyEntryModelImpl)currencyEntry;

			if ((currencyEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CURRENCYCODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { currencyEntry.getCurrencyCode() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENCYCODE,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
					args, currencyEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(CurrencyEntry currencyEntry) {
		CurrencyEntryModelImpl currencyEntryModelImpl = (CurrencyEntryModelImpl)currencyEntry;

		Object[] args = new Object[] { currencyEntry.getCurrencyCode() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CURRENCYCODE, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENCYCODE, args);

		if ((currencyEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CURRENCYCODE.getColumnBitmask()) != 0) {
			args = new Object[] { currencyEntryModelImpl.getOriginalCurrencyCode() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CURRENCYCODE, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENCYCODE, args);
		}
	}

	/**
	 * Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	 *
	 * @param currencyEntryId the primary key for the new currency entry
	 * @return the new currency entry
	 */
	public CurrencyEntry create(long currencyEntryId) {
		CurrencyEntry currencyEntry = new CurrencyEntryImpl();

		currencyEntry.setNew(true);
		currencyEntry.setPrimaryKey(currencyEntryId);

		return currencyEntry;
	}

	/**
	 * Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param currencyEntryId the primary key of the currency entry
	 * @return the currency entry that was removed
	 * @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry remove(long currencyEntryId)
		throws NoSuchCurrencyEntryException, SystemException {
		return remove(Long.valueOf(currencyEntryId));
	}

	/**
	 * Removes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the currency entry
	 * @return the currency entry that was removed
	 * @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CurrencyEntry remove(Serializable primaryKey)
		throws NoSuchCurrencyEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CurrencyEntry currencyEntry = (CurrencyEntry)session.get(CurrencyEntryImpl.class,
					primaryKey);

			if (currencyEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCurrencyEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(currencyEntry);
		}
		catch (NoSuchCurrencyEntryException nsee) {
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
	protected CurrencyEntry removeImpl(CurrencyEntry currencyEntry)
		throws SystemException {
		currencyEntry = toUnwrappedModel(currencyEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, currencyEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(currencyEntry);

		return currencyEntry;
	}

	@Override
	public CurrencyEntry updateImpl(
		com.liferay.osb.model.CurrencyEntry currencyEntry, boolean merge)
		throws SystemException {
		currencyEntry = toUnwrappedModel(currencyEntry);

		boolean isNew = currencyEntry.isNew();

		CurrencyEntryModelImpl currencyEntryModelImpl = (CurrencyEntryModelImpl)currencyEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, currencyEntry, merge);

			currencyEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CurrencyEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((currencyEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Boolean.valueOf(currencyEntryModelImpl.getOriginalMarketplaceEnabled())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MARKETPLACEENABLED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED,
					args);

				args = new Object[] {
						Boolean.valueOf(currencyEntryModelImpl.getMarketplaceEnabled())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MARKETPLACEENABLED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED,
					args);
			}
		}

		EntityCacheUtil.putResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
			CurrencyEntryImpl.class, currencyEntry.getPrimaryKey(),
			currencyEntry);

		clearUniqueFindersCache(currencyEntry);
		cacheUniqueFindersCache(currencyEntry);

		return currencyEntry;
	}

	protected CurrencyEntry toUnwrappedModel(CurrencyEntry currencyEntry) {
		if (currencyEntry instanceof CurrencyEntryImpl) {
			return currencyEntry;
		}

		CurrencyEntryImpl currencyEntryImpl = new CurrencyEntryImpl();

		currencyEntryImpl.setNew(currencyEntry.isNew());
		currencyEntryImpl.setPrimaryKey(currencyEntry.getPrimaryKey());

		currencyEntryImpl.setCurrencyEntryId(currencyEntry.getCurrencyEntryId());
		currencyEntryImpl.setCountryId(currencyEntry.getCountryId());
		currencyEntryImpl.setCurrencyCode(currencyEntry.getCurrencyCode());
		currencyEntryImpl.setMarketplaceEnabled(currencyEntry.isMarketplaceEnabled());
		currencyEntryImpl.setMarketplaceMinPrice(currencyEntry.getMarketplaceMinPrice());

		return currencyEntryImpl;
	}

	/**
	 * Returns the currency entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the currency entry
	 * @return the currency entry
	 * @throws com.liferay.portal.NoSuchModelException if a currency entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CurrencyEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the currency entry with the primary key or throws a {@link com.liferay.osb.NoSuchCurrencyEntryException} if it could not be found.
	 *
	 * @param currencyEntryId the primary key of the currency entry
	 * @return the currency entry
	 * @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry findByPrimaryKey(long currencyEntryId)
		throws NoSuchCurrencyEntryException, SystemException {
		CurrencyEntry currencyEntry = fetchByPrimaryKey(currencyEntryId);

		if (currencyEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + currencyEntryId);
			}

			throw new NoSuchCurrencyEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				currencyEntryId);
		}

		return currencyEntry;
	}

	/**
	 * Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the currency entry
	 * @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CurrencyEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the currency entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param currencyEntryId the primary key of the currency entry
	 * @return the currency entry, or <code>null</code> if a currency entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry fetchByPrimaryKey(long currencyEntryId)
		throws SystemException {
		CurrencyEntry currencyEntry = (CurrencyEntry)EntityCacheUtil.getResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
				CurrencyEntryImpl.class, currencyEntryId);

		if (currencyEntry == _nullCurrencyEntry) {
			return null;
		}

		if (currencyEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				currencyEntry = (CurrencyEntry)session.get(CurrencyEntryImpl.class,
						Long.valueOf(currencyEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (currencyEntry != null) {
					cacheResult(currencyEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CurrencyEntryModelImpl.ENTITY_CACHE_ENABLED,
						CurrencyEntryImpl.class, currencyEntryId,
						_nullCurrencyEntry);
				}

				closeSession(session);
			}
		}

		return currencyEntry;
	}

	/**
	 * Returns the currency entry where currencyCode = &#63; or throws a {@link com.liferay.osb.NoSuchCurrencyEntryException} if it could not be found.
	 *
	 * @param currencyCode the currency code
	 * @return the matching currency entry
	 * @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry findByCurrencyCode(String currencyCode)
		throws NoSuchCurrencyEntryException, SystemException {
		CurrencyEntry currencyEntry = fetchByCurrencyCode(currencyCode);

		if (currencyEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("currencyCode=");
			msg.append(currencyCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCurrencyEntryException(msg.toString());
		}

		return currencyEntry;
	}

	/**
	 * Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param currencyCode the currency code
	 * @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry fetchByCurrencyCode(String currencyCode)
		throws SystemException {
		return fetchByCurrencyCode(currencyCode, true);
	}

	/**
	 * Returns the currency entry where currencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param currencyCode the currency code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry fetchByCurrencyCode(String currencyCode,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { currencyCode };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
					finderArgs, this);
		}

		if (result instanceof CurrencyEntry) {
			CurrencyEntry currencyEntry = (CurrencyEntry)result;

			if (!Validator.equals(currencyCode, currencyEntry.getCurrencyCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CURRENCYENTRY_WHERE);

			if (currencyCode == null) {
				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_1);
			}
			else {
				if (currencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_2);
				}
			}

			query.append(CurrencyEntryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (currencyCode != null) {
					qPos.add(currencyCode);
				}

				List<CurrencyEntry> list = q.list();

				result = list;

				CurrencyEntry currencyEntry = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
						finderArgs, list);
				}
				else {
					currencyEntry = list.get(0);

					cacheResult(currencyEntry);

					if ((currencyEntry.getCurrencyCode() == null) ||
							!currencyEntry.getCurrencyCode().equals(currencyCode)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
							finderArgs, currencyEntry);
					}
				}

				return currencyEntry;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENCYCODE,
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
				return (CurrencyEntry)result;
			}
		}
	}

	/**
	 * Returns all the currency entries where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @return the matching currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled) throws SystemException {
		return findByMarketplaceEnabled(marketplaceEnabled, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the currency entries where marketplaceEnabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @return the range of matching currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end)
		throws SystemException {
		return findByMarketplaceEnabled(marketplaceEnabled, start, end, null);
	}

	/**
	 * Returns an ordered range of all the currency entries where marketplaceEnabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CurrencyEntry> findByMarketplaceEnabled(
		boolean marketplaceEnabled, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACEENABLED;
			finderArgs = new Object[] { marketplaceEnabled };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETPLACEENABLED;
			finderArgs = new Object[] {
					marketplaceEnabled,
					
					start, end, orderByComparator
				};
		}

		List<CurrencyEntry> list = (List<CurrencyEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CurrencyEntry currencyEntry : list) {
				if ((marketplaceEnabled != currencyEntry.getMarketplaceEnabled())) {
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

			query.append(_SQL_SELECT_CURRENCYENTRY_WHERE);

			query.append(_FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CurrencyEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketplaceEnabled);

				list = (List<CurrencyEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching currency entry
	 * @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry findByMarketplaceEnabled_First(
		boolean marketplaceEnabled, OrderByComparator orderByComparator)
		throws NoSuchCurrencyEntryException, SystemException {
		CurrencyEntry currencyEntry = fetchByMarketplaceEnabled_First(marketplaceEnabled,
				orderByComparator);

		if (currencyEntry != null) {
			return currencyEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketplaceEnabled=");
		msg.append(marketplaceEnabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCurrencyEntryException(msg.toString());
	}

	/**
	 * Returns the first currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry fetchByMarketplaceEnabled_First(
		boolean marketplaceEnabled, OrderByComparator orderByComparator)
		throws SystemException {
		List<CurrencyEntry> list = findByMarketplaceEnabled(marketplaceEnabled,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching currency entry
	 * @throws com.liferay.osb.NoSuchCurrencyEntryException if a matching currency entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry findByMarketplaceEnabled_Last(
		boolean marketplaceEnabled, OrderByComparator orderByComparator)
		throws NoSuchCurrencyEntryException, SystemException {
		CurrencyEntry currencyEntry = fetchByMarketplaceEnabled_Last(marketplaceEnabled,
				orderByComparator);

		if (currencyEntry != null) {
			return currencyEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketplaceEnabled=");
		msg.append(marketplaceEnabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCurrencyEntryException(msg.toString());
	}

	/**
	 * Returns the last currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching currency entry, or <code>null</code> if a matching currency entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry fetchByMarketplaceEnabled_Last(
		boolean marketplaceEnabled, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByMarketplaceEnabled(marketplaceEnabled);

		List<CurrencyEntry> list = findByMarketplaceEnabled(marketplaceEnabled,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the currency entries before and after the current currency entry in the ordered set where marketplaceEnabled = &#63;.
	 *
	 * @param currencyEntryId the primary key of the current currency entry
	 * @param marketplaceEnabled the marketplace enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next currency entry
	 * @throws com.liferay.osb.NoSuchCurrencyEntryException if a currency entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry[] findByMarketplaceEnabled_PrevAndNext(
		long currencyEntryId, boolean marketplaceEnabled,
		OrderByComparator orderByComparator)
		throws NoSuchCurrencyEntryException, SystemException {
		CurrencyEntry currencyEntry = findByPrimaryKey(currencyEntryId);

		Session session = null;

		try {
			session = openSession();

			CurrencyEntry[] array = new CurrencyEntryImpl[3];

			array[0] = getByMarketplaceEnabled_PrevAndNext(session,
					currencyEntry, marketplaceEnabled, orderByComparator, true);

			array[1] = currencyEntry;

			array[2] = getByMarketplaceEnabled_PrevAndNext(session,
					currencyEntry, marketplaceEnabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CurrencyEntry getByMarketplaceEnabled_PrevAndNext(
		Session session, CurrencyEntry currencyEntry,
		boolean marketplaceEnabled, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CURRENCYENTRY_WHERE);

		query.append(_FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2);

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
			query.append(CurrencyEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(marketplaceEnabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(currencyEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CurrencyEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the currency entries.
	 *
	 * @return the currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CurrencyEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the currency entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @return the range of currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CurrencyEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the currency entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of currency entries
	 * @param end the upper bound of the range of currency entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<CurrencyEntry> findAll(int start, int end,
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

		List<CurrencyEntry> list = (List<CurrencyEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CURRENCYENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CURRENCYENTRY.concat(CurrencyEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CurrencyEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CurrencyEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes the currency entry where currencyCode = &#63; from the database.
	 *
	 * @param currencyCode the currency code
	 * @return the currency entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CurrencyEntry removeByCurrencyCode(String currencyCode)
		throws NoSuchCurrencyEntryException, SystemException {
		CurrencyEntry currencyEntry = findByCurrencyCode(currencyCode);

		return remove(currencyEntry);
	}

	/**
	 * Removes all the currency entries where marketplaceEnabled = &#63; from the database.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMarketplaceEnabled(boolean marketplaceEnabled)
		throws SystemException {
		for (CurrencyEntry currencyEntry : findByMarketplaceEnabled(
				marketplaceEnabled)) {
			remove(currencyEntry);
		}
	}

	/**
	 * Removes all the currency entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CurrencyEntry currencyEntry : findAll()) {
			remove(currencyEntry);
		}
	}

	/**
	 * Returns the number of currency entries where currencyCode = &#63;.
	 *
	 * @param currencyCode the currency code
	 * @return the number of matching currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCurrencyCode(String currencyCode)
		throws SystemException {
		Object[] finderArgs = new Object[] { currencyCode };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CURRENCYCODE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CURRENCYENTRY_WHERE);

			if (currencyCode == null) {
				query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_1);
			}
			else {
				if (currencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (currencyCode != null) {
					qPos.add(currencyCode);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENCYCODE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of currency entries where marketplaceEnabled = &#63;.
	 *
	 * @param marketplaceEnabled the marketplace enabled
	 * @return the number of matching currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMarketplaceEnabled(boolean marketplaceEnabled)
		throws SystemException {
		Object[] finderArgs = new Object[] { marketplaceEnabled };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MARKETPLACEENABLED,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CURRENCYENTRY_WHERE);

			query.append(_FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketplaceEnabled);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MARKETPLACEENABLED,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of currency entries.
	 *
	 * @return the number of currency entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CURRENCYENTRY);

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
	 * Initializes the currency entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.CurrencyEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CurrencyEntry>> listenersList = new ArrayList<ModelListener<CurrencyEntry>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<CurrencyEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CurrencyEntryImpl.class.getName());
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
	private static final String _SQL_SELECT_CURRENCYENTRY = "SELECT currencyEntry FROM CurrencyEntry currencyEntry";
	private static final String _SQL_SELECT_CURRENCYENTRY_WHERE = "SELECT currencyEntry FROM CurrencyEntry currencyEntry WHERE ";
	private static final String _SQL_COUNT_CURRENCYENTRY = "SELECT COUNT(currencyEntry) FROM CurrencyEntry currencyEntry";
	private static final String _SQL_COUNT_CURRENCYENTRY_WHERE = "SELECT COUNT(currencyEntry) FROM CurrencyEntry currencyEntry WHERE ";
	private static final String _FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_1 = "currencyEntry.currencyCode IS NULL";
	private static final String _FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_2 = "currencyEntry.currencyCode = ?";
	private static final String _FINDER_COLUMN_CURRENCYCODE_CURRENCYCODE_3 = "(currencyEntry.currencyCode IS NULL OR currencyEntry.currencyCode = ?)";
	private static final String _FINDER_COLUMN_MARKETPLACEENABLED_MARKETPLACEENABLED_2 =
		"currencyEntry.marketplaceEnabled = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "currencyEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CurrencyEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CurrencyEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CurrencyEntryPersistenceImpl.class);
	private static CurrencyEntry _nullCurrencyEntry = new CurrencyEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CurrencyEntry> toCacheModel() {
				return _nullCurrencyEntryCacheModel;
			}
		};

	private static CacheModel<CurrencyEntry> _nullCurrencyEntryCacheModel = new CacheModel<CurrencyEntry>() {
			public CurrencyEntry toEntityModel() {
				return _nullCurrencyEntry;
			}
		};
}
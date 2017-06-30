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

import com.liferay.osb.NoSuchSupportRegionException;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.impl.SupportRegionImpl;
import com.liferay.osb.model.impl.SupportRegionModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the support region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionPersistence
 * @see SupportRegionUtil
 * @generated
 */
public class SupportRegionPersistenceImpl extends BasePersistenceImpl<SupportRegion>
	implements SupportRegionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportRegionUtil} to access the support region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportRegionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED,
			SupportRegionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			SupportRegionModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED,
			SupportRegionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED,
			SupportRegionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the support region in the entity cache if it is enabled.
	 *
	 * @param supportRegion the support region
	 */
	public void cacheResult(SupportRegion supportRegion) {
		EntityCacheUtil.putResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey(),
			supportRegion);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { supportRegion.getName() }, supportRegion);

		supportRegion.resetOriginalValues();
	}

	/**
	 * Caches the support regions in the entity cache if it is enabled.
	 *
	 * @param supportRegions the support regions
	 */
	public void cacheResult(List<SupportRegion> supportRegions) {
		for (SupportRegion supportRegion : supportRegions) {
			if (EntityCacheUtil.getResult(
						SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
						SupportRegionImpl.class, supportRegion.getPrimaryKey()) == null) {
				cacheResult(supportRegion);
			}
			else {
				supportRegion.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support regions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SupportRegionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SupportRegionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support region.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportRegion supportRegion) {
		EntityCacheUtil.removeResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(supportRegion);
	}

	@Override
	public void clearCache(List<SupportRegion> supportRegions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportRegion supportRegion : supportRegions) {
			EntityCacheUtil.removeResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
				SupportRegionImpl.class, supportRegion.getPrimaryKey());

			clearUniqueFindersCache(supportRegion);
		}
	}

	protected void cacheUniqueFindersCache(SupportRegion supportRegion) {
		if (supportRegion.isNew()) {
			Object[] args = new Object[] { supportRegion.getName() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
				supportRegion);
		}
		else {
			SupportRegionModelImpl supportRegionModelImpl = (SupportRegionModelImpl)supportRegion;

			if ((supportRegionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { supportRegion.getName() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
					supportRegion);
			}
		}
	}

	protected void clearUniqueFindersCache(SupportRegion supportRegion) {
		SupportRegionModelImpl supportRegionModelImpl = (SupportRegionModelImpl)supportRegion;

		Object[] args = new Object[] { supportRegion.getName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

		if ((supportRegionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			args = new Object[] { supportRegionModelImpl.getOriginalName() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new support region with the primary key. Does not add the support region to the database.
	 *
	 * @param supportRegionId the primary key for the new support region
	 * @return the new support region
	 */
	public SupportRegion create(long supportRegionId) {
		SupportRegion supportRegion = new SupportRegionImpl();

		supportRegion.setNew(true);
		supportRegion.setPrimaryKey(supportRegionId);

		return supportRegion;
	}

	/**
	 * Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region that was removed
	 * @throws com.liferay.osb.NoSuchSupportRegionException if a support region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportRegion remove(long supportRegionId)
		throws NoSuchSupportRegionException, SystemException {
		return remove(Long.valueOf(supportRegionId));
	}

	/**
	 * Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region that was removed
	 * @throws com.liferay.osb.NoSuchSupportRegionException if a support region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportRegion remove(Serializable primaryKey)
		throws NoSuchSupportRegionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SupportRegion supportRegion = (SupportRegion)session.get(SupportRegionImpl.class,
					primaryKey);

			if (supportRegion == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportRegionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportRegion);
		}
		catch (NoSuchSupportRegionException nsee) {
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
	protected SupportRegion removeImpl(SupportRegion supportRegion)
		throws SystemException {
		supportRegion = toUnwrappedModel(supportRegion);

		try {
			clearAccountEntries.clear(supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}

		try {
			clearPartnerEntries.clear(supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}

		try {
			clearSupportTeams.clear(supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, supportRegion);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(supportRegion);

		return supportRegion;
	}

	@Override
	public SupportRegion updateImpl(
		com.liferay.osb.model.SupportRegion supportRegion, boolean merge)
		throws SystemException {
		supportRegion = toUnwrappedModel(supportRegion);

		boolean isNew = supportRegion.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, supportRegion, merge);

			supportRegion.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SupportRegionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey(),
			supportRegion);

		clearUniqueFindersCache(supportRegion);
		cacheUniqueFindersCache(supportRegion);

		return supportRegion;
	}

	protected SupportRegion toUnwrappedModel(SupportRegion supportRegion) {
		if (supportRegion instanceof SupportRegionImpl) {
			return supportRegion;
		}

		SupportRegionImpl supportRegionImpl = new SupportRegionImpl();

		supportRegionImpl.setNew(supportRegion.isNew());
		supportRegionImpl.setPrimaryKey(supportRegion.getPrimaryKey());

		supportRegionImpl.setSupportRegionId(supportRegion.getSupportRegionId());
		supportRegionImpl.setUserId(supportRegion.getUserId());
		supportRegionImpl.setUserName(supportRegion.getUserName());
		supportRegionImpl.setCreateDate(supportRegion.getCreateDate());
		supportRegionImpl.setModifiedDate(supportRegion.getModifiedDate());
		supportRegionImpl.setName(supportRegion.getName());
		supportRegionImpl.setDescription(supportRegion.getDescription());
		supportRegionImpl.setTimeZoneId(supportRegion.getTimeZoneId());
		supportRegionImpl.setManagerUserId(supportRegion.getManagerUserId());

		return supportRegionImpl;
	}

	/**
	 * Returns the support region with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region
	 * @throws com.liferay.portal.NoSuchModelException if a support region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportRegion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support region with the primary key or throws a {@link com.liferay.osb.NoSuchSupportRegionException} if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region
	 * @throws com.liferay.osb.NoSuchSupportRegionException if a support region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportRegion findByPrimaryKey(long supportRegionId)
		throws NoSuchSupportRegionException, SystemException {
		SupportRegion supportRegion = fetchByPrimaryKey(supportRegionId);

		if (supportRegion == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + supportRegionId);
			}

			throw new NoSuchSupportRegionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				supportRegionId);
		}

		return supportRegion;
	}

	/**
	 * Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region, or <code>null</code> if a support region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportRegion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region, or <code>null</code> if a support region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportRegion fetchByPrimaryKey(long supportRegionId)
		throws SystemException {
		SupportRegion supportRegion = (SupportRegion)EntityCacheUtil.getResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
				SupportRegionImpl.class, supportRegionId);

		if (supportRegion == _nullSupportRegion) {
			return null;
		}

		if (supportRegion == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				supportRegion = (SupportRegion)session.get(SupportRegionImpl.class,
						Long.valueOf(supportRegionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (supportRegion != null) {
					cacheResult(supportRegion);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
						SupportRegionImpl.class, supportRegionId,
						_nullSupportRegion);
				}

				closeSession(session);
			}
		}

		return supportRegion;
	}

	/**
	 * Returns the support region where name = &#63; or throws a {@link com.liferay.osb.NoSuchSupportRegionException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support region
	 * @throws com.liferay.osb.NoSuchSupportRegionException if a matching support region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportRegion findByName(String name)
		throws NoSuchSupportRegionException, SystemException {
		SupportRegion supportRegion = fetchByName(name);

		if (supportRegion == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSupportRegionException(msg.toString());
		}

		return supportRegion;
	}

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportRegion fetchByName(String name) throws SystemException {
		return fetchByName(name, true);
	}

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportRegion fetchByName(String name, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof SupportRegion) {
			SupportRegion supportRegion = (SupportRegion)result;

			if (!Validator.equals(name, supportRegion.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTREGION_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			query.append(SupportRegionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				List<SupportRegion> list = q.list();

				result = list;

				SupportRegion supportRegion = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					supportRegion = list.get(0);

					cacheResult(supportRegion);

					if ((supportRegion.getName() == null) ||
							!supportRegion.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, supportRegion);
					}
				}

				return supportRegion;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
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
				return (SupportRegion)result;
			}
		}
	}

	/**
	 * Returns all the support regions.
	 *
	 * @return the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportRegion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of support regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportRegion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportRegion> findAll(int start, int end,
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

		List<SupportRegion> list = (List<SupportRegion>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SUPPORTREGION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTREGION.concat(SupportRegionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SupportRegion>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SupportRegion>)QueryUtil.list(q, getDialect(),
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
	 * Removes the support region where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support region that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public SupportRegion removeByName(String name)
		throws NoSuchSupportRegionException, SystemException {
		SupportRegion supportRegion = findByName(name);

		return remove(supportRegion);
	}

	/**
	 * Removes all the support regions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SupportRegion supportRegion : findAll()) {
			remove(supportRegion);
		}
	}

	/**
	 * Returns the number of support regions where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTREGION_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support regions.
	 *
	 * @return the number of support regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTREGION);

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
	 * Returns all the account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the account entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk)
		throws SystemException {
		return getAccountEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of account entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end) throws SystemException {
		return getAccountEntries(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_ACCOUNTENTRIES = new FinderPath(com.liferay.osb.model.impl.AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTREGIONS,
			com.liferay.osb.model.impl.AccountEntryImpl.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME,
			"getAccountEntries",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_ACCOUNTENTRIES.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.AccountEntry> list = (List<com.liferay.osb.model.AccountEntry>)FinderCacheUtil.getResult(FINDER_PATH_GET_ACCOUNTENTRIES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETACCOUNTENTRIES.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETACCOUNTENTRIES.concat(com.liferay.osb.model.impl.AccountEntryModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_AccountEntry",
					com.liferay.osb.model.impl.AccountEntryImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.AccountEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_ACCOUNTENTRIES,
						finderArgs);
				}
				else {
					accountEntryPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_ACCOUNTENTRIES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_ACCOUNTENTRIES_SIZE = new FinderPath(com.liferay.osb.model.impl.AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTREGIONS,
			Long.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME,
			"getAccountEntriesSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_ACCOUNTENTRIES_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the number of account entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public int getAccountEntriesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_ACCOUNTENTRIES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETACCOUNTENTRIESSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_ACCOUNTENTRIES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_ACCOUNTENTRY = new FinderPath(com.liferay.osb.model.impl.AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTREGIONS,
			Boolean.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME,
			"containsAccountEntry",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the account entry is associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 * @return <code>true</code> if the account entry is associated with the support region; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsAccountEntry(long pk, long accountEntryPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, accountEntryPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_ACCOUNTENTRY,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsAccountEntry.contains(pk,
							accountEntryPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_ACCOUNTENTRY,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the support region has any account entries associated with it.
	 *
	 * @param pk the primary key of the support region to check for associations with account entries
	 * @return <code>true</code> if the support region has any account entries associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsAccountEntries(long pk) throws SystemException {
		if (getAccountEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntry(long pk, long accountEntryPK)
		throws SystemException {
		try {
			addAccountEntry.add(pk, accountEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws SystemException {
		try {
			addAccountEntry.add(pk, accountEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntries(long pk, long[] accountEntryPKs)
		throws SystemException {
		try {
			for (long accountEntryPK : accountEntryPKs) {
				addAccountEntry.add(pk, accountEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
				addAccountEntry.add(pk, accountEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the support region and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region to clear the associated account entries from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearAccountEntries(long pk) throws SystemException {
		try {
			clearAccountEntries.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntry(long pk, long accountEntryPK)
		throws SystemException {
		try {
			removeAccountEntry.remove(pk, accountEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws SystemException {
		try {
			removeAccountEntry.remove(pk, accountEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntries(long pk, long[] accountEntryPKs)
		throws SystemException {
		try {
			for (long accountEntryPK : accountEntryPKs) {
				removeAccountEntry.remove(pk, accountEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
				removeAccountEntry.remove(pk, accountEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries to be associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void setAccountEntries(long pk, long[] accountEntryPKs)
		throws SystemException {
		try {
			Set<Long> accountEntryPKSet = SetUtil.fromArray(accountEntryPKs);

			List<com.liferay.osb.model.AccountEntry> accountEntries = getAccountEntries(pk);

			for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
				if (!accountEntryPKSet.remove(accountEntry.getPrimaryKey())) {
					removeAccountEntry.remove(pk, accountEntry.getPrimaryKey());
				}
			}

			for (Long accountEntryPK : accountEntryPKSet) {
				addAccountEntry.add(pk, accountEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries to be associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void setAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws SystemException {
		try {
			long[] accountEntryPKs = new long[accountEntries.size()];

			for (int i = 0; i < accountEntries.size(); i++) {
				com.liferay.osb.model.AccountEntry accountEntry = accountEntries.get(i);

				accountEntryPKs[i] = accountEntry.getPrimaryKey();
			}

			setAccountEntries(pk, accountEntryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Returns all the partner entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the partner entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(long pk)
		throws SystemException {
		return getPartnerEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the partner entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of partner entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(long pk,
		int start, int end) throws SystemException {
		return getPartnerEntries(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_PARTNERENTRIES = new FinderPath(com.liferay.osb.model.impl.PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_PARTNERENTRIES_SUPPORTREGIONS,
			com.liferay.osb.model.impl.PartnerEntryImpl.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME,
			"getPartnerEntries",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_PARTNERENTRIES.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the partner entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of partner entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.PartnerEntry> list = (List<com.liferay.osb.model.PartnerEntry>)FinderCacheUtil.getResult(FINDER_PATH_GET_PARTNERENTRIES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETPARTNERENTRIES.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETPARTNERENTRIES.concat(com.liferay.osb.model.impl.PartnerEntryModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_PartnerEntry",
					com.liferay.osb.model.impl.PartnerEntryImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.PartnerEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_PARTNERENTRIES,
						finderArgs);
				}
				else {
					partnerEntryPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_PARTNERENTRIES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_PARTNERENTRIES_SIZE = new FinderPath(com.liferay.osb.model.impl.PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_PARTNERENTRIES_SUPPORTREGIONS,
			Long.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME,
			"getPartnerEntriesSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_PARTNERENTRIES_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of partner entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the number of partner entries associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public int getPartnerEntriesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_PARTNERENTRIES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETPARTNERENTRIESSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_PARTNERENTRIES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_PARTNERENTRY = new FinderPath(com.liferay.osb.model.impl.PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_PARTNERENTRIES_SUPPORTREGIONS,
			Boolean.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME,
			"containsPartnerEntry",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the partner entry is associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPK the primary key of the partner entry
	 * @return <code>true</code> if the partner entry is associated with the support region; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPartnerEntry(long pk, long partnerEntryPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, partnerEntryPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PARTNERENTRY,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsPartnerEntry.contains(pk,
							partnerEntryPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PARTNERENTRY,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the support region has any partner entries associated with it.
	 *
	 * @param pk the primary key of the support region to check for associations with partner entries
	 * @return <code>true</code> if the support region has any partner entries associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPartnerEntries(long pk) throws SystemException {
		if (getPartnerEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPK the primary key of the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addPartnerEntry(long pk, long partnerEntryPK)
		throws SystemException {
		try {
			addPartnerEntry.add(pk, partnerEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntry the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addPartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws SystemException {
		try {
			addPartnerEntry.add(pk, partnerEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPKs the primary keys of the partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addPartnerEntries(long pk, long[] partnerEntryPKs)
		throws SystemException {
		try {
			for (long partnerEntryPK : partnerEntryPKs) {
				addPartnerEntry.add(pk, partnerEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntries the partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addPartnerEntries(long pk,
		List<com.liferay.osb.model.PartnerEntry> partnerEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.PartnerEntry partnerEntry : partnerEntries) {
				addPartnerEntry.add(pk, partnerEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the support region and its partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region to clear the associated partner entries from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearPartnerEntries(long pk) throws SystemException {
		try {
			clearPartnerEntries.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPK the primary key of the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removePartnerEntry(long pk, long partnerEntryPK)
		throws SystemException {
		try {
			removePartnerEntry.remove(pk, partnerEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntry the partner entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removePartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws SystemException {
		try {
			removePartnerEntry.remove(pk, partnerEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPKs the primary keys of the partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removePartnerEntries(long pk, long[] partnerEntryPKs)
		throws SystemException {
		try {
			for (long partnerEntryPK : partnerEntryPKs) {
				removePartnerEntry.remove(pk, partnerEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntries the partner entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removePartnerEntries(long pk,
		List<com.liferay.osb.model.PartnerEntry> partnerEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.PartnerEntry partnerEntry : partnerEntries) {
				removePartnerEntry.remove(pk, partnerEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPKs the primary keys of the partner entries to be associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void setPartnerEntries(long pk, long[] partnerEntryPKs)
		throws SystemException {
		try {
			Set<Long> partnerEntryPKSet = SetUtil.fromArray(partnerEntryPKs);

			List<com.liferay.osb.model.PartnerEntry> partnerEntries = getPartnerEntries(pk);

			for (com.liferay.osb.model.PartnerEntry partnerEntry : partnerEntries) {
				if (!partnerEntryPKSet.remove(partnerEntry.getPrimaryKey())) {
					removePartnerEntry.remove(pk, partnerEntry.getPrimaryKey());
				}
			}

			for (Long partnerEntryPK : partnerEntryPKSet) {
				addPartnerEntry.add(pk, partnerEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntries the partner entries to be associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void setPartnerEntries(long pk,
		List<com.liferay.osb.model.PartnerEntry> partnerEntries)
		throws SystemException {
		try {
			long[] partnerEntryPKs = new long[partnerEntries.size()];

			for (int i = 0; i < partnerEntries.size(); i++) {
				com.liferay.osb.model.PartnerEntry partnerEntry = partnerEntries.get(i);

				partnerEntryPKs[i] = partnerEntry.getPrimaryKey();
			}

			setPartnerEntries(pk, partnerEntryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Returns all the support teams associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the support teams associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk)
		throws SystemException {
		return getSupportTeams(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support teams associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of support teams associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk,
		int start, int end) throws SystemException {
		return getSupportTeams(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTTEAMS = new FinderPath(com.liferay.osb.model.impl.SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_SUPPORTTEAMS_SUPPORTREGIONS,
			com.liferay.osb.model.impl.SupportTeamImpl.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME,
			"getSupportTeams",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_SUPPORTTEAMS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the support teams associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support teams associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportTeam> getSupportTeams(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.SupportTeam> list = (List<com.liferay.osb.model.SupportTeam>)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTTEAMS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETSUPPORTTEAMS.concat(ORDER_BY_CLAUSE)
											  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETSUPPORTTEAMS.concat(com.liferay.osb.model.impl.SupportTeamModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_SupportTeam",
					com.liferay.osb.model.impl.SupportTeamImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.SupportTeam>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_SUPPORTTEAMS,
						finderArgs);
				}
				else {
					supportTeamPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTTEAMS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTTEAMS_SIZE = new FinderPath(com.liferay.osb.model.impl.SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_SUPPORTTEAMS_SUPPORTREGIONS,
			Long.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME,
			"getSupportTeamsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_SUPPORTTEAMS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of support teams associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the number of support teams associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public int getSupportTeamsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTTEAMS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSUPPORTTEAMSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTTEAMS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_SUPPORTTEAM = new FinderPath(com.liferay.osb.model.impl.SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED_OSB_SUPPORTTEAMS_SUPPORTREGIONS,
			Boolean.class,
			SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME,
			"containsSupportTeam",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the support team is associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeamPK the primary key of the support team
	 * @return <code>true</code> if the support team is associated with the support region; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportTeam(long pk, long supportTeamPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, supportTeamPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_SUPPORTTEAM,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsSupportTeam.contains(pk,
							supportTeamPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_SUPPORTTEAM,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the support region has any support teams associated with it.
	 *
	 * @param pk the primary key of the support region to check for associations with support teams
	 * @return <code>true</code> if the support region has any support teams associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportTeams(long pk) throws SystemException {
		if (getSupportTeamsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeamPK the primary key of the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeam(long pk, long supportTeamPK)
		throws SystemException {
		try {
			addSupportTeam.add(pk, supportTeamPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeam the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws SystemException {
		try {
			addSupportTeam.add(pk, supportTeam.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeamPKs the primary keys of the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeams(long pk, long[] supportTeamPKs)
		throws SystemException {
		try {
			for (long supportTeamPK : supportTeamPKs) {
				addSupportTeam.add(pk, supportTeamPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeams the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
				addSupportTeam.add(pk, supportTeam.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the support region and its support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region to clear the associated support teams from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearSupportTeams(long pk) throws SystemException {
		try {
			clearSupportTeams.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeamPK the primary key of the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeam(long pk, long supportTeamPK)
		throws SystemException {
		try {
			removeSupportTeam.remove(pk, supportTeamPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeam the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws SystemException {
		try {
			removeSupportTeam.remove(pk, supportTeam.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeamPKs the primary keys of the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeams(long pk, long[] supportTeamPKs)
		throws SystemException {
		try {
			for (long supportTeamPK : supportTeamPKs) {
				removeSupportTeam.remove(pk, supportTeamPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeams the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
				removeSupportTeam.remove(pk, supportTeam.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support teams associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeamPKs the primary keys of the support teams to be associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportTeams(long pk, long[] supportTeamPKs)
		throws SystemException {
		try {
			Set<Long> supportTeamPKSet = SetUtil.fromArray(supportTeamPKs);

			List<com.liferay.osb.model.SupportTeam> supportTeams = getSupportTeams(pk);

			for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
				if (!supportTeamPKSet.remove(supportTeam.getPrimaryKey())) {
					removeSupportTeam.remove(pk, supportTeam.getPrimaryKey());
				}
			}

			for (Long supportTeamPK : supportTeamPKSet) {
				addSupportTeam.add(pk, supportTeamPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support teams associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param supportTeams the support teams to be associated with the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws SystemException {
		try {
			long[] supportTeamPKs = new long[supportTeams.size()];

			for (int i = 0; i < supportTeams.size(); i++) {
				com.liferay.osb.model.SupportTeam supportTeam = supportTeams.get(i);

				supportTeamPKs[i] = supportTeam.getPrimaryKey();
			}

			setSupportTeams(pk, supportTeamPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportRegionModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Initializes the support region persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.SupportRegion")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SupportRegion>> listenersList = new ArrayList<ModelListener<SupportRegion>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SupportRegion>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsAccountEntry = new ContainsAccountEntry();

		addAccountEntry = new AddAccountEntry();
		clearAccountEntries = new ClearAccountEntries();
		removeAccountEntry = new RemoveAccountEntry();

		containsPartnerEntry = new ContainsPartnerEntry();

		addPartnerEntry = new AddPartnerEntry();
		clearPartnerEntries = new ClearPartnerEntries();
		removePartnerEntry = new RemovePartnerEntry();

		containsSupportTeam = new ContainsSupportTeam();

		addSupportTeam = new AddSupportTeam();
		clearSupportTeams = new ClearSupportTeams();
		removeSupportTeam = new RemoveSupportTeam();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SupportRegionImpl.class.getName());
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
	protected ContainsAccountEntry containsAccountEntry;
	protected AddAccountEntry addAccountEntry;
	protected ClearAccountEntries clearAccountEntries;
	protected RemoveAccountEntry removeAccountEntry;
	protected ContainsPartnerEntry containsPartnerEntry;
	protected AddPartnerEntry addPartnerEntry;
	protected ClearPartnerEntries clearPartnerEntries;
	protected RemovePartnerEntry removePartnerEntry;
	protected ContainsSupportTeam containsSupportTeam;
	protected AddSupportTeam addSupportTeam;
	protected ClearSupportTeams clearSupportTeams;
	protected RemoveSupportTeam removeSupportTeam;

	protected class ContainsAccountEntry {
		protected ContainsAccountEntry() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSACCOUNTENTRY,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long supportRegionId, long accountEntryId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(supportRegionId), new Long(accountEntryId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddAccountEntry {
		protected AddAccountEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_AccountEntries_SupportRegions (supportRegionId, accountEntryId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long supportRegionId, long accountEntryId)
			throws SystemException {
			if (!containsAccountEntry.contains(supportRegionId, accountEntryId)) {
				ModelListener<com.liferay.osb.model.AccountEntry>[] accountEntryListeners =
					accountEntryPersistence.getListeners();

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onBeforeAddAssociation(supportRegionId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onBeforeAddAssociation(accountEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportRegionId), new Long(accountEntryId)
					});

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onAfterAddAssociation(supportRegionId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onAfterAddAssociation(accountEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearAccountEntries {
		protected ClearAccountEntries() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportRegions WHERE supportRegionId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long supportRegionId) throws SystemException {
			ModelListener<com.liferay.osb.model.AccountEntry>[] accountEntryListeners =
				accountEntryPersistence.getListeners();

			List<com.liferay.osb.model.AccountEntry> accountEntries = null;

			if ((listeners.length > 0) || (accountEntryListeners.length > 0)) {
				accountEntries = getAccountEntries(supportRegionId);

				for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
					for (ModelListener<SupportRegion> listener : listeners) {
						listener.onBeforeRemoveAssociation(supportRegionId,
							com.liferay.osb.model.AccountEntry.class.getName(),
							accountEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
						listener.onBeforeRemoveAssociation(accountEntry.getPrimaryKey(),
							SupportRegion.class.getName(), supportRegionId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(supportRegionId) });

			if ((listeners.length > 0) || (accountEntryListeners.length > 0)) {
				for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
					for (ModelListener<SupportRegion> listener : listeners) {
						listener.onAfterRemoveAssociation(supportRegionId,
							com.liferay.osb.model.AccountEntry.class.getName(),
							accountEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
						listener.onAfterRemoveAssociation(accountEntry.getPrimaryKey(),
							SupportRegion.class.getName(), supportRegionId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveAccountEntry {
		protected RemoveAccountEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportRegions WHERE supportRegionId = ? AND accountEntryId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long supportRegionId, long accountEntryId)
			throws SystemException {
			if (containsAccountEntry.contains(supportRegionId, accountEntryId)) {
				ModelListener<com.liferay.osb.model.AccountEntry>[] accountEntryListeners =
					accountEntryPersistence.getListeners();

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onBeforeRemoveAssociation(supportRegionId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onBeforeRemoveAssociation(accountEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportRegionId), new Long(accountEntryId)
					});

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onAfterRemoveAssociation(supportRegionId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onAfterRemoveAssociation(accountEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ContainsPartnerEntry {
		protected ContainsPartnerEntry() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSPARTNERENTRY,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long supportRegionId, long partnerEntryId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(supportRegionId), new Long(partnerEntryId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddPartnerEntry {
		protected AddPartnerEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_PartnerEntries_SupportRegions (supportRegionId, partnerEntryId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long supportRegionId, long partnerEntryId)
			throws SystemException {
			if (!containsPartnerEntry.contains(supportRegionId, partnerEntryId)) {
				ModelListener<com.liferay.osb.model.PartnerEntry>[] partnerEntryListeners =
					partnerEntryPersistence.getListeners();

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onBeforeAddAssociation(supportRegionId,
						com.liferay.osb.model.PartnerEntry.class.getName(),
						partnerEntryId);
				}

				for (ModelListener<com.liferay.osb.model.PartnerEntry> listener : partnerEntryListeners) {
					listener.onBeforeAddAssociation(partnerEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportRegionId), new Long(partnerEntryId)
					});

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onAfterAddAssociation(supportRegionId,
						com.liferay.osb.model.PartnerEntry.class.getName(),
						partnerEntryId);
				}

				for (ModelListener<com.liferay.osb.model.PartnerEntry> listener : partnerEntryListeners) {
					listener.onAfterAddAssociation(partnerEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearPartnerEntries {
		protected ClearPartnerEntries() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_PartnerEntries_SupportRegions WHERE supportRegionId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long supportRegionId) throws SystemException {
			ModelListener<com.liferay.osb.model.PartnerEntry>[] partnerEntryListeners =
				partnerEntryPersistence.getListeners();

			List<com.liferay.osb.model.PartnerEntry> partnerEntries = null;

			if ((listeners.length > 0) || (partnerEntryListeners.length > 0)) {
				partnerEntries = getPartnerEntries(supportRegionId);

				for (com.liferay.osb.model.PartnerEntry partnerEntry : partnerEntries) {
					for (ModelListener<SupportRegion> listener : listeners) {
						listener.onBeforeRemoveAssociation(supportRegionId,
							com.liferay.osb.model.PartnerEntry.class.getName(),
							partnerEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.PartnerEntry> listener : partnerEntryListeners) {
						listener.onBeforeRemoveAssociation(partnerEntry.getPrimaryKey(),
							SupportRegion.class.getName(), supportRegionId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(supportRegionId) });

			if ((listeners.length > 0) || (partnerEntryListeners.length > 0)) {
				for (com.liferay.osb.model.PartnerEntry partnerEntry : partnerEntries) {
					for (ModelListener<SupportRegion> listener : listeners) {
						listener.onAfterRemoveAssociation(supportRegionId,
							com.liferay.osb.model.PartnerEntry.class.getName(),
							partnerEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.PartnerEntry> listener : partnerEntryListeners) {
						listener.onAfterRemoveAssociation(partnerEntry.getPrimaryKey(),
							SupportRegion.class.getName(), supportRegionId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemovePartnerEntry {
		protected RemovePartnerEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_PartnerEntries_SupportRegions WHERE supportRegionId = ? AND partnerEntryId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long supportRegionId, long partnerEntryId)
			throws SystemException {
			if (containsPartnerEntry.contains(supportRegionId, partnerEntryId)) {
				ModelListener<com.liferay.osb.model.PartnerEntry>[] partnerEntryListeners =
					partnerEntryPersistence.getListeners();

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onBeforeRemoveAssociation(supportRegionId,
						com.liferay.osb.model.PartnerEntry.class.getName(),
						partnerEntryId);
				}

				for (ModelListener<com.liferay.osb.model.PartnerEntry> listener : partnerEntryListeners) {
					listener.onBeforeRemoveAssociation(partnerEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportRegionId), new Long(partnerEntryId)
					});

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onAfterRemoveAssociation(supportRegionId,
						com.liferay.osb.model.PartnerEntry.class.getName(),
						partnerEntryId);
				}

				for (ModelListener<com.liferay.osb.model.PartnerEntry> listener : partnerEntryListeners) {
					listener.onAfterRemoveAssociation(partnerEntryId,
						SupportRegion.class.getName(), supportRegionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ContainsSupportTeam {
		protected ContainsSupportTeam() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSUPPORTTEAM,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long supportRegionId, long supportTeamId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(supportRegionId), new Long(supportTeamId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddSupportTeam {
		protected AddSupportTeam() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_SupportTeams_SupportRegions (supportRegionId, supportTeamId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long supportRegionId, long supportTeamId)
			throws SystemException {
			if (!containsSupportTeam.contains(supportRegionId, supportTeamId)) {
				ModelListener<com.liferay.osb.model.SupportTeam>[] supportTeamListeners =
					supportTeamPersistence.getListeners();

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onBeforeAddAssociation(supportRegionId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onBeforeAddAssociation(supportTeamId,
						SupportRegion.class.getName(), supportRegionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportRegionId), new Long(supportTeamId)
					});

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onAfterAddAssociation(supportRegionId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onAfterAddAssociation(supportTeamId,
						SupportRegion.class.getName(), supportRegionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearSupportTeams {
		protected ClearSupportTeams() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_SupportTeams_SupportRegions WHERE supportRegionId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long supportRegionId) throws SystemException {
			ModelListener<com.liferay.osb.model.SupportTeam>[] supportTeamListeners =
				supportTeamPersistence.getListeners();

			List<com.liferay.osb.model.SupportTeam> supportTeams = null;

			if ((listeners.length > 0) || (supportTeamListeners.length > 0)) {
				supportTeams = getSupportTeams(supportRegionId);

				for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
					for (ModelListener<SupportRegion> listener : listeners) {
						listener.onBeforeRemoveAssociation(supportRegionId,
							com.liferay.osb.model.SupportTeam.class.getName(),
							supportTeam.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
						listener.onBeforeRemoveAssociation(supportTeam.getPrimaryKey(),
							SupportRegion.class.getName(), supportRegionId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(supportRegionId) });

			if ((listeners.length > 0) || (supportTeamListeners.length > 0)) {
				for (com.liferay.osb.model.SupportTeam supportTeam : supportTeams) {
					for (ModelListener<SupportRegion> listener : listeners) {
						listener.onAfterRemoveAssociation(supportRegionId,
							com.liferay.osb.model.SupportTeam.class.getName(),
							supportTeam.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
						listener.onAfterRemoveAssociation(supportTeam.getPrimaryKey(),
							SupportRegion.class.getName(), supportRegionId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSupportTeam {
		protected RemoveSupportTeam() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_SupportTeams_SupportRegions WHERE supportRegionId = ? AND supportTeamId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long supportRegionId, long supportTeamId)
			throws SystemException {
			if (containsSupportTeam.contains(supportRegionId, supportTeamId)) {
				ModelListener<com.liferay.osb.model.SupportTeam>[] supportTeamListeners =
					supportTeamPersistence.getListeners();

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onBeforeRemoveAssociation(supportRegionId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onBeforeRemoveAssociation(supportTeamId,
						SupportRegion.class.getName(), supportRegionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportRegionId), new Long(supportTeamId)
					});

				for (ModelListener<SupportRegion> listener : listeners) {
					listener.onAfterRemoveAssociation(supportRegionId,
						com.liferay.osb.model.SupportTeam.class.getName(),
						supportTeamId);
				}

				for (ModelListener<com.liferay.osb.model.SupportTeam> listener : supportTeamListeners) {
					listener.onAfterRemoveAssociation(supportTeamId,
						SupportRegion.class.getName(), supportRegionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_SUPPORTREGION = "SELECT supportRegion FROM SupportRegion supportRegion";
	private static final String _SQL_SELECT_SUPPORTREGION_WHERE = "SELECT supportRegion FROM SupportRegion supportRegion WHERE ";
	private static final String _SQL_COUNT_SUPPORTREGION = "SELECT COUNT(supportRegion) FROM SupportRegion supportRegion";
	private static final String _SQL_COUNT_SUPPORTREGION_WHERE = "SELECT COUNT(supportRegion) FROM SupportRegion supportRegion WHERE ";
	private static final String _SQL_GETACCOUNTENTRIES = "SELECT {OSB_AccountEntry.*} FROM OSB_AccountEntry INNER JOIN OSB_AccountEntries_SupportRegions ON (OSB_AccountEntries_SupportRegions.accountEntryId = OSB_AccountEntry.accountEntryId) WHERE (OSB_AccountEntries_SupportRegions.supportRegionId = ?)";
	private static final String _SQL_GETACCOUNTENTRIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportRegions WHERE supportRegionId = ?";
	private static final String _SQL_CONTAINSACCOUNTENTRY = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportRegions WHERE supportRegionId = ? AND accountEntryId = ?";
	private static final String _SQL_GETPARTNERENTRIES = "SELECT {OSB_PartnerEntry.*} FROM OSB_PartnerEntry INNER JOIN OSB_PartnerEntries_SupportRegions ON (OSB_PartnerEntries_SupportRegions.partnerEntryId = OSB_PartnerEntry.partnerEntryId) WHERE (OSB_PartnerEntries_SupportRegions.supportRegionId = ?)";
	private static final String _SQL_GETPARTNERENTRIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_PartnerEntries_SupportRegions WHERE supportRegionId = ?";
	private static final String _SQL_CONTAINSPARTNERENTRY = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_PartnerEntries_SupportRegions WHERE supportRegionId = ? AND partnerEntryId = ?";
	private static final String _SQL_GETSUPPORTTEAMS = "SELECT {OSB_SupportTeam.*} FROM OSB_SupportTeam INNER JOIN OSB_SupportTeams_SupportRegions ON (OSB_SupportTeams_SupportRegions.supportTeamId = OSB_SupportTeam.supportTeamId) WHERE (OSB_SupportTeams_SupportRegions.supportRegionId = ?)";
	private static final String _SQL_GETSUPPORTTEAMSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_SupportTeams_SupportRegions WHERE supportRegionId = ?";
	private static final String _SQL_CONTAINSSUPPORTTEAM = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_SupportTeams_SupportRegions WHERE supportRegionId = ? AND supportTeamId = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "supportRegion.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "supportRegion.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(supportRegion.name IS NULL OR supportRegion.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportRegion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportRegion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportRegion exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SupportRegionPersistenceImpl.class);
	private static SupportRegion _nullSupportRegion = new SupportRegionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SupportRegion> toCacheModel() {
				return _nullSupportRegionCacheModel;
			}
		};

	private static CacheModel<SupportRegion> _nullSupportRegionCacheModel = new CacheModel<SupportRegion>() {
			public SupportRegion toEntityModel() {
				return _nullSupportRegion;
			}
		};
}
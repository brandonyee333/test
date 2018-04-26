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

import com.liferay.osb.exception.NoSuchSupportRegionException;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.impl.SupportRegionImpl;
import com.liferay.osb.model.impl.SupportRegionModelImpl;
import com.liferay.osb.service.persistence.AccountEntryPersistence;
import com.liferay.osb.service.persistence.PartnerEntryPersistence;
import com.liferay.osb.service.persistence.SupportRegionPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
 * @see com.liferay.osb.service.persistence.SupportRegionUtil
 * @generated
 */
@ProviderType
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
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED,
			SupportRegionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			SupportRegionModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the support region where name = &#63; or throws a {@link NoSuchSupportRegionException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support region
	 * @throws NoSuchSupportRegionException if a matching support region could not be found
	 */
	@Override
	public SupportRegion findByName(String name)
		throws NoSuchSupportRegionException {
		SupportRegion supportRegion = fetchByName(name);

		if (supportRegion == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
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
	 */
	@Override
	public SupportRegion fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 */
	@Override
	public SupportRegion fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof SupportRegion) {
			SupportRegion supportRegion = (SupportRegion)result;

			if (!Objects.equals(name, supportRegion.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTREGION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<SupportRegion> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					SupportRegion supportRegion = list.get(0);

					result = supportRegion;

					cacheResult(supportRegion);

					if ((supportRegion.getName() == null) ||
							!supportRegion.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, supportRegion);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SupportRegion)result;
		}
	}

	/**
	 * Removes the support region where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support region that was removed
	 */
	@Override
	public SupportRegion removeByName(String name)
		throws NoSuchSupportRegionException {
		SupportRegion supportRegion = findByName(name);

		return remove(supportRegion);
	}

	/**
	 * Returns the number of support regions where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support regions
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTREGION_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "supportRegion.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "supportRegion.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(supportRegion.name IS NULL OR supportRegion.name = '')";

	public SupportRegionPersistenceImpl() {
		setModelClass(SupportRegion.class);
	}

	/**
	 * Caches the support region in the entity cache if it is enabled.
	 *
	 * @param supportRegion the support region
	 */
	@Override
	public void cacheResult(SupportRegion supportRegion) {
		entityCache.putResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey(),
			supportRegion);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { supportRegion.getName() }, supportRegion);

		supportRegion.resetOriginalValues();
	}

	/**
	 * Caches the support regions in the entity cache if it is enabled.
	 *
	 * @param supportRegions the support regions
	 */
	@Override
	public void cacheResult(List<SupportRegion> supportRegions) {
		for (SupportRegion supportRegion : supportRegions) {
			if (entityCache.getResult(
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SupportRegionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support region.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportRegion supportRegion) {
		entityCache.removeResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SupportRegionModelImpl)supportRegion, true);
	}

	@Override
	public void clearCache(List<SupportRegion> supportRegions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportRegion supportRegion : supportRegions) {
			entityCache.removeResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
				SupportRegionImpl.class, supportRegion.getPrimaryKey());

			clearUniqueFindersCache((SupportRegionModelImpl)supportRegion, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SupportRegionModelImpl supportRegionModelImpl) {
		Object[] args = new Object[] { supportRegionModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			supportRegionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SupportRegionModelImpl supportRegionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { supportRegionModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((supportRegionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					supportRegionModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new support region with the primary key. Does not add the support region to the database.
	 *
	 * @param supportRegionId the primary key for the new support region
	 * @return the new support region
	 */
	@Override
	public SupportRegion create(long supportRegionId) {
		SupportRegion supportRegion = new SupportRegionImpl();

		supportRegion.setNew(true);
		supportRegion.setPrimaryKey(supportRegionId);

		supportRegion.setCompanyId(companyProvider.getCompanyId());

		return supportRegion;
	}

	/**
	 * Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region that was removed
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion remove(long supportRegionId)
		throws NoSuchSupportRegionException {
		return remove((Serializable)supportRegionId);
	}

	/**
	 * Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region that was removed
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion remove(Serializable primaryKey)
		throws NoSuchSupportRegionException {
		Session session = null;

		try {
			session = openSession();

			SupportRegion supportRegion = (SupportRegion)session.get(SupportRegionImpl.class,
					primaryKey);

			if (supportRegion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
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
	protected SupportRegion removeImpl(SupportRegion supportRegion) {
		supportRegion = toUnwrappedModel(supportRegion);

		supportRegionToAccountEntryTableMapper.deleteLeftPrimaryKeyTableMappings(supportRegion.getPrimaryKey());

		supportRegionToPartnerEntryTableMapper.deleteLeftPrimaryKeyTableMappings(supportRegion.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportRegion)) {
				supportRegion = (SupportRegion)session.get(SupportRegionImpl.class,
						supportRegion.getPrimaryKeyObj());
			}

			if (supportRegion != null) {
				session.delete(supportRegion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (supportRegion != null) {
			clearCache(supportRegion);
		}

		return supportRegion;
	}

	@Override
	public SupportRegion updateImpl(SupportRegion supportRegion) {
		supportRegion = toUnwrappedModel(supportRegion);

		boolean isNew = supportRegion.isNew();

		SupportRegionModelImpl supportRegionModelImpl = (SupportRegionModelImpl)supportRegion;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (supportRegion.getCreateDate() == null)) {
			if (serviceContext == null) {
				supportRegion.setCreateDate(now);
			}
			else {
				supportRegion.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!supportRegionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				supportRegion.setModifiedDate(now);
			}
			else {
				supportRegion.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (supportRegion.isNew()) {
				session.save(supportRegion);

				supportRegion.setNew(false);
			}
			else {
				supportRegion = (SupportRegion)session.merge(supportRegion);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SupportRegionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportRegionImpl.class, supportRegion.getPrimaryKey(),
			supportRegion, false);

		clearUniqueFindersCache(supportRegionModelImpl, false);
		cacheUniqueFindersCache(supportRegionModelImpl);

		supportRegion.resetOriginalValues();

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
		supportRegionImpl.setCompanyId(supportRegion.getCompanyId());
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
	 * Returns the support region with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSupportRegionException {
		SupportRegion supportRegion = fetchByPrimaryKey(primaryKey);

		if (supportRegion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSupportRegionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return supportRegion;
	}

	/**
	 * Returns the support region with the primary key or throws a {@link NoSuchSupportRegionException} if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion findByPrimaryKey(long supportRegionId)
		throws NoSuchSupportRegionException {
		return findByPrimaryKey((Serializable)supportRegionId);
	}

	/**
	 * Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support region
	 * @return the support region, or <code>null</code> if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
				SupportRegionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SupportRegion supportRegion = (SupportRegion)serializable;

		if (supportRegion == null) {
			Session session = null;

			try {
				session = openSession();

				supportRegion = (SupportRegion)session.get(SupportRegionImpl.class,
						primaryKey);

				if (supportRegion != null) {
					cacheResult(supportRegion);
				}
				else {
					entityCache.putResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
						SupportRegionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
					SupportRegionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return supportRegion;
	}

	/**
	 * Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region, or <code>null</code> if a support region with the primary key could not be found
	 */
	@Override
	public SupportRegion fetchByPrimaryKey(long supportRegionId) {
		return fetchByPrimaryKey((Serializable)supportRegionId);
	}

	@Override
	public Map<Serializable, SupportRegion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SupportRegion> map = new HashMap<Serializable, SupportRegion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SupportRegion supportRegion = fetchByPrimaryKey(primaryKey);

			if (supportRegion != null) {
				map.put(primaryKey, supportRegion);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
					SupportRegionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SupportRegion)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SUPPORTREGION_WHERE_PKS_IN);

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

			for (SupportRegion supportRegion : (List<SupportRegion>)q.list()) {
				map.put(supportRegion.getPrimaryKeyObj(), supportRegion);

				cacheResult(supportRegion);

				uncachedPrimaryKeys.remove(supportRegion.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
					SupportRegionImpl.class, primaryKey, nullModel);
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
	 * Returns all the support regions.
	 *
	 * @return the support regions
	 */
	@Override
	public List<SupportRegion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of support regions
	 */
	@Override
	public List<SupportRegion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions
	 */
	@Override
	public List<SupportRegion> findAll(int start, int end,
		OrderByComparator<SupportRegion> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of support regions
	 */
	@Override
	public List<SupportRegion> findAll(int start, int end,
		OrderByComparator<SupportRegion> orderByComparator,
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

		List<SupportRegion> list = null;

		if (retrieveFromCache) {
			list = (List<SupportRegion>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SUPPORTREGION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTREGION;

				if (pagination) {
					sql = sql.concat(SupportRegionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SupportRegion>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SupportRegion>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the support regions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SupportRegion supportRegion : findAll()) {
			remove(supportRegion);
		}
	}

	/**
	 * Returns the number of support regions.
	 *
	 * @return the number of support regions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTREGION);

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

	/**
	 * Returns the primaryKeys of account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return long[] of the primaryKeys of account entries associated with the support region
	 */
	@Override
	public long[] getAccountEntryPrimaryKeys(long pk) {
		long[] pks = supportRegionToAccountEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the account entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk) {
		return getAccountEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of account entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end) {
		return getAccountEntries(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end,
		OrderByComparator<com.liferay.osb.model.AccountEntry> orderByComparator) {
		return supportRegionToAccountEntryTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the number of account entries associated with the support region
	 */
	@Override
	public int getAccountEntriesSize(long pk) {
		long[] pks = supportRegionToAccountEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the account entry is associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 * @return <code>true</code> if the account entry is associated with the support region; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAccountEntry(long pk, long accountEntryPK) {
		return supportRegionToAccountEntryTableMapper.containsTableMapping(pk,
			accountEntryPK);
	}

	/**
	 * Returns <code>true</code> if the support region has any account entries associated with it.
	 *
	 * @param pk the primary key of the support region to check for associations with account entries
	 * @return <code>true</code> if the support region has any account entries associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsAccountEntries(long pk) {
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
	 */
	@Override
	public void addAccountEntry(long pk, long accountEntryPK) {
		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			supportRegionToAccountEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, accountEntryPK);
		}
		else {
			supportRegionToAccountEntryTableMapper.addTableMapping(supportRegion.getCompanyId(),
				pk, accountEntryPK);
		}
	}

	/**
	 * Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 */
	@Override
	public void addAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry) {
		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			supportRegionToAccountEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, accountEntry.getPrimaryKey());
		}
		else {
			supportRegionToAccountEntryTableMapper.addTableMapping(supportRegion.getCompanyId(),
				pk, accountEntry.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	@Override
	public void addAccountEntries(long pk, long[] accountEntryPKs) {
		long companyId = 0;

		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportRegion.getCompanyId();
		}

		supportRegionToAccountEntryTableMapper.addTableMappings(companyId, pk,
			accountEntryPKs);
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 */
	@Override
	public void addAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries) {
		addAccountEntries(pk,
			ListUtil.toLongArray(accountEntries,
				com.liferay.osb.model.AccountEntry.ACCOUNT_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the support region and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region to clear the associated account entries from
	 */
	@Override
	public void clearAccountEntries(long pk) {
		supportRegionToAccountEntryTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 */
	@Override
	public void removeAccountEntry(long pk, long accountEntryPK) {
		supportRegionToAccountEntryTableMapper.deleteTableMapping(pk,
			accountEntryPK);
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 */
	@Override
	public void removeAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry) {
		supportRegionToAccountEntryTableMapper.deleteTableMapping(pk,
			accountEntry.getPrimaryKey());
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	@Override
	public void removeAccountEntries(long pk, long[] accountEntryPKs) {
		supportRegionToAccountEntryTableMapper.deleteTableMappings(pk,
			accountEntryPKs);
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 */
	@Override
	public void removeAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries) {
		removeAccountEntries(pk,
			ListUtil.toLongArray(accountEntries,
				com.liferay.osb.model.AccountEntry.ACCOUNT_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries to be associated with the support region
	 */
	@Override
	public void setAccountEntries(long pk, long[] accountEntryPKs) {
		Set<Long> newAccountEntryPKsSet = SetUtil.fromArray(accountEntryPKs);
		Set<Long> oldAccountEntryPKsSet = SetUtil.fromArray(supportRegionToAccountEntryTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeAccountEntryPKsSet = new HashSet<Long>(oldAccountEntryPKsSet);

		removeAccountEntryPKsSet.removeAll(newAccountEntryPKsSet);

		supportRegionToAccountEntryTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeAccountEntryPKsSet));

		newAccountEntryPKsSet.removeAll(oldAccountEntryPKsSet);

		long companyId = 0;

		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportRegion.getCompanyId();
		}

		supportRegionToAccountEntryTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newAccountEntryPKsSet));
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries to be associated with the support region
	 */
	@Override
	public void setAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries) {
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
	}

	/**
	 * Returns the primaryKeys of partner entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return long[] of the primaryKeys of partner entries associated with the support region
	 */
	@Override
	public long[] getPartnerEntryPrimaryKeys(long pk) {
		long[] pks = supportRegionToPartnerEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the partner entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the partner entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(long pk) {
		return getPartnerEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the partner entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of partner entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(long pk,
		int start, int end) {
		return getPartnerEntries(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of partner entries associated with the support region
	 */
	@Override
	public List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(long pk,
		int start, int end,
		OrderByComparator<com.liferay.osb.model.PartnerEntry> orderByComparator) {
		return supportRegionToPartnerEntryTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of partner entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the number of partner entries associated with the support region
	 */
	@Override
	public int getPartnerEntriesSize(long pk) {
		long[] pks = supportRegionToPartnerEntryTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the partner entry is associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPK the primary key of the partner entry
	 * @return <code>true</code> if the partner entry is associated with the support region; <code>false</code> otherwise
	 */
	@Override
	public boolean containsPartnerEntry(long pk, long partnerEntryPK) {
		return supportRegionToPartnerEntryTableMapper.containsTableMapping(pk,
			partnerEntryPK);
	}

	/**
	 * Returns <code>true</code> if the support region has any partner entries associated with it.
	 *
	 * @param pk the primary key of the support region to check for associations with partner entries
	 * @return <code>true</code> if the support region has any partner entries associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsPartnerEntries(long pk) {
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
	 */
	@Override
	public void addPartnerEntry(long pk, long partnerEntryPK) {
		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			supportRegionToPartnerEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, partnerEntryPK);
		}
		else {
			supportRegionToPartnerEntryTableMapper.addTableMapping(supportRegion.getCompanyId(),
				pk, partnerEntryPK);
		}
	}

	/**
	 * Adds an association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntry the partner entry
	 */
	@Override
	public void addPartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			supportRegionToPartnerEntryTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, partnerEntry.getPrimaryKey());
		}
		else {
			supportRegionToPartnerEntryTableMapper.addTableMapping(supportRegion.getCompanyId(),
				pk, partnerEntry.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPKs the primary keys of the partner entries
	 */
	@Override
	public void addPartnerEntries(long pk, long[] partnerEntryPKs) {
		long companyId = 0;

		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportRegion.getCompanyId();
		}

		supportRegionToPartnerEntryTableMapper.addTableMappings(companyId, pk,
			partnerEntryPKs);
	}

	/**
	 * Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntries the partner entries
	 */
	@Override
	public void addPartnerEntries(long pk,
		List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
		addPartnerEntries(pk,
			ListUtil.toLongArray(partnerEntries,
				com.liferay.osb.model.PartnerEntry.PARTNER_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the support region and its partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region to clear the associated partner entries from
	 */
	@Override
	public void clearPartnerEntries(long pk) {
		supportRegionToPartnerEntryTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPK the primary key of the partner entry
	 */
	@Override
	public void removePartnerEntry(long pk, long partnerEntryPK) {
		supportRegionToPartnerEntryTableMapper.deleteTableMapping(pk,
			partnerEntryPK);
	}

	/**
	 * Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntry the partner entry
	 */
	@Override
	public void removePartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		supportRegionToPartnerEntryTableMapper.deleteTableMapping(pk,
			partnerEntry.getPrimaryKey());
	}

	/**
	 * Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPKs the primary keys of the partner entries
	 */
	@Override
	public void removePartnerEntries(long pk, long[] partnerEntryPKs) {
		supportRegionToPartnerEntryTableMapper.deleteTableMappings(pk,
			partnerEntryPKs);
	}

	/**
	 * Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntries the partner entries
	 */
	@Override
	public void removePartnerEntries(long pk,
		List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
		removePartnerEntries(pk,
			ListUtil.toLongArray(partnerEntries,
				com.liferay.osb.model.PartnerEntry.PARTNER_ENTRY_ID_ACCESSOR));
	}

	/**
	 * Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntryPKs the primary keys of the partner entries to be associated with the support region
	 */
	@Override
	public void setPartnerEntries(long pk, long[] partnerEntryPKs) {
		Set<Long> newPartnerEntryPKsSet = SetUtil.fromArray(partnerEntryPKs);
		Set<Long> oldPartnerEntryPKsSet = SetUtil.fromArray(supportRegionToPartnerEntryTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removePartnerEntryPKsSet = new HashSet<Long>(oldPartnerEntryPKsSet);

		removePartnerEntryPKsSet.removeAll(newPartnerEntryPKsSet);

		supportRegionToPartnerEntryTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removePartnerEntryPKsSet));

		newPartnerEntryPKsSet.removeAll(oldPartnerEntryPKsSet);

		long companyId = 0;

		SupportRegion supportRegion = fetchByPrimaryKey(pk);

		if (supportRegion == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = supportRegion.getCompanyId();
		}

		supportRegionToPartnerEntryTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newPartnerEntryPKsSet));
	}

	/**
	 * Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param partnerEntries the partner entries to be associated with the support region
	 */
	@Override
	public void setPartnerEntries(long pk,
		List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
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
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SupportRegionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support region persistence.
	 */
	public void afterPropertiesSet() {
		supportRegionToAccountEntryTableMapper = TableMapperFactory.getTableMapper("OSB_AccountEntries_SupportRegions",
				"companyId", "supportRegionId", "accountEntryId", this,
				accountEntryPersistence);

		supportRegionToPartnerEntryTableMapper = TableMapperFactory.getTableMapper("OSB_PartnerEntries_SupportRegions",
				"companyId", "supportRegionId", "partnerEntryId", this,
				partnerEntryPersistence);
	}

	public void destroy() {
		entityCache.removeCache(SupportRegionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_AccountEntries_SupportRegions");
		TableMapperFactory.removeTableMapper(
			"OSB_PartnerEntries_SupportRegions");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	protected TableMapper<SupportRegion, com.liferay.osb.model.AccountEntry> supportRegionToAccountEntryTableMapper;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	protected TableMapper<SupportRegion, com.liferay.osb.model.PartnerEntry> supportRegionToPartnerEntryTableMapper;
	private static final String _SQL_SELECT_SUPPORTREGION = "SELECT supportRegion FROM SupportRegion supportRegion";
	private static final String _SQL_SELECT_SUPPORTREGION_WHERE_PKS_IN = "SELECT supportRegion FROM SupportRegion supportRegion WHERE supportRegionId IN (";
	private static final String _SQL_SELECT_SUPPORTREGION_WHERE = "SELECT supportRegion FROM SupportRegion supportRegion WHERE ";
	private static final String _SQL_COUNT_SUPPORTREGION = "SELECT COUNT(supportRegion) FROM SupportRegion supportRegion";
	private static final String _SQL_COUNT_SUPPORTREGION_WHERE = "SELECT COUNT(supportRegion) FROM SupportRegion supportRegion WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportRegion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportRegion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportRegion exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SupportRegionPersistenceImpl.class);
}
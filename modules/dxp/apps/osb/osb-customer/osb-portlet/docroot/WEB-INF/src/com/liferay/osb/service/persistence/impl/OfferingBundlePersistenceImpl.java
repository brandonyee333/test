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

import com.liferay.osb.exception.NoSuchOfferingBundleException;
import com.liferay.osb.model.OfferingBundle;
import com.liferay.osb.model.impl.OfferingBundleImpl;
import com.liferay.osb.model.impl.OfferingBundleModelImpl;
import com.liferay.osb.service.persistence.OfferingBundlePersistence;
import com.liferay.osb.service.persistence.OfferingDefinitionPersistence;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the offering bundle service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundlePersistence
 * @see com.liferay.osb.service.persistence.OfferingBundleUtil
 * @generated
 */
@ProviderType
public class OfferingBundlePersistenceImpl extends BasePersistenceImpl<OfferingBundle>
	implements OfferingBundlePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OfferingBundleUtil} to access the offering bundle persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OfferingBundleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED,
			OfferingBundleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED,
			OfferingBundleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED,
			OfferingBundleImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			OfferingBundleModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the offering bundle where name = &#63; or throws a {@link NoSuchOfferingBundleException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching offering bundle
	 * @throws NoSuchOfferingBundleException if a matching offering bundle could not be found
	 */
	@Override
	public OfferingBundle findByName(String name)
		throws NoSuchOfferingBundleException {
		OfferingBundle offeringBundle = fetchByName(name);

		if (offeringBundle == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOfferingBundleException(msg.toString());
		}

		return offeringBundle;
	}

	/**
	 * Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	 */
	@Override
	public OfferingBundle fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	 */
	@Override
	public OfferingBundle fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof OfferingBundle) {
			OfferingBundle offeringBundle = (OfferingBundle)result;

			if (!Objects.equals(name, offeringBundle.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_OFFERINGBUNDLE_WHERE);

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

				List<OfferingBundle> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					OfferingBundle offeringBundle = list.get(0);

					result = offeringBundle;

					cacheResult(offeringBundle);

					if ((offeringBundle.getName() == null) ||
							!offeringBundle.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, offeringBundle);
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
			return (OfferingBundle)result;
		}
	}

	/**
	 * Removes the offering bundle where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the offering bundle that was removed
	 */
	@Override
	public OfferingBundle removeByName(String name)
		throws NoSuchOfferingBundleException {
		OfferingBundle offeringBundle = findByName(name);

		return remove(offeringBundle);
	}

	/**
	 * Returns the number of offering bundles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching offering bundles
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGBUNDLE_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "offeringBundle.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "offeringBundle.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(offeringBundle.name IS NULL OR offeringBundle.name = '')";

	public OfferingBundlePersistenceImpl() {
		setModelClass(OfferingBundle.class);
	}

	/**
	 * Caches the offering bundle in the entity cache if it is enabled.
	 *
	 * @param offeringBundle the offering bundle
	 */
	@Override
	public void cacheResult(OfferingBundle offeringBundle) {
		entityCache.putResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleImpl.class, offeringBundle.getPrimaryKey(),
			offeringBundle);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { offeringBundle.getName() }, offeringBundle);

		offeringBundle.resetOriginalValues();
	}

	/**
	 * Caches the offering bundles in the entity cache if it is enabled.
	 *
	 * @param offeringBundles the offering bundles
	 */
	@Override
	public void cacheResult(List<OfferingBundle> offeringBundles) {
		for (OfferingBundle offeringBundle : offeringBundles) {
			if (entityCache.getResult(
						OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
						OfferingBundleImpl.class, offeringBundle.getPrimaryKey()) == null) {
				cacheResult(offeringBundle);
			}
			else {
				offeringBundle.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all offering bundles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OfferingBundleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the offering bundle.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfferingBundle offeringBundle) {
		entityCache.removeResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleImpl.class, offeringBundle.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OfferingBundleModelImpl)offeringBundle, true);
	}

	@Override
	public void clearCache(List<OfferingBundle> offeringBundles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfferingBundle offeringBundle : offeringBundles) {
			entityCache.removeResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
				OfferingBundleImpl.class, offeringBundle.getPrimaryKey());

			clearUniqueFindersCache((OfferingBundleModelImpl)offeringBundle,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OfferingBundleModelImpl offeringBundleModelImpl) {
		Object[] args = new Object[] { offeringBundleModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			offeringBundleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OfferingBundleModelImpl offeringBundleModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { offeringBundleModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((offeringBundleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					offeringBundleModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	 *
	 * @param offeringBundleId the primary key for the new offering bundle
	 * @return the new offering bundle
	 */
	@Override
	public OfferingBundle create(long offeringBundleId) {
		OfferingBundle offeringBundle = new OfferingBundleImpl();

		offeringBundle.setNew(true);
		offeringBundle.setPrimaryKey(offeringBundleId);

		offeringBundle.setCompanyId(companyProvider.getCompanyId());

		return offeringBundle;
	}

	/**
	 * Removes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offeringBundleId the primary key of the offering bundle
	 * @return the offering bundle that was removed
	 * @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	 */
	@Override
	public OfferingBundle remove(long offeringBundleId)
		throws NoSuchOfferingBundleException {
		return remove((Serializable)offeringBundleId);
	}

	/**
	 * Removes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the offering bundle
	 * @return the offering bundle that was removed
	 * @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	 */
	@Override
	public OfferingBundle remove(Serializable primaryKey)
		throws NoSuchOfferingBundleException {
		Session session = null;

		try {
			session = openSession();

			OfferingBundle offeringBundle = (OfferingBundle)session.get(OfferingBundleImpl.class,
					primaryKey);

			if (offeringBundle == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfferingBundleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(offeringBundle);
		}
		catch (NoSuchOfferingBundleException nsee) {
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
	protected OfferingBundle removeImpl(OfferingBundle offeringBundle) {
		offeringBundle = toUnwrappedModel(offeringBundle);

		offeringBundleToOfferingDefinitionTableMapper.deleteLeftPrimaryKeyTableMappings(offeringBundle.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(offeringBundle)) {
				offeringBundle = (OfferingBundle)session.get(OfferingBundleImpl.class,
						offeringBundle.getPrimaryKeyObj());
			}

			if (offeringBundle != null) {
				session.delete(offeringBundle);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (offeringBundle != null) {
			clearCache(offeringBundle);
		}

		return offeringBundle;
	}

	@Override
	public OfferingBundle updateImpl(OfferingBundle offeringBundle) {
		offeringBundle = toUnwrappedModel(offeringBundle);

		boolean isNew = offeringBundle.isNew();

		OfferingBundleModelImpl offeringBundleModelImpl = (OfferingBundleModelImpl)offeringBundle;

		Session session = null;

		try {
			session = openSession();

			if (offeringBundle.isNew()) {
				session.save(offeringBundle);

				offeringBundle.setNew(false);
			}
			else {
				offeringBundle = (OfferingBundle)session.merge(offeringBundle);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OfferingBundleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleImpl.class, offeringBundle.getPrimaryKey(),
			offeringBundle, false);

		clearUniqueFindersCache(offeringBundleModelImpl, false);
		cacheUniqueFindersCache(offeringBundleModelImpl);

		offeringBundle.resetOriginalValues();

		return offeringBundle;
	}

	protected OfferingBundle toUnwrappedModel(OfferingBundle offeringBundle) {
		if (offeringBundle instanceof OfferingBundleImpl) {
			return offeringBundle;
		}

		OfferingBundleImpl offeringBundleImpl = new OfferingBundleImpl();

		offeringBundleImpl.setNew(offeringBundle.isNew());
		offeringBundleImpl.setPrimaryKey(offeringBundle.getPrimaryKey());

		offeringBundleImpl.setOfferingBundleId(offeringBundle.getOfferingBundleId());
		offeringBundleImpl.setCompanyId(offeringBundle.getCompanyId());
		offeringBundleImpl.setUserId(offeringBundle.getUserId());
		offeringBundleImpl.setUserName(offeringBundle.getUserName());
		offeringBundleImpl.setCreateDate(offeringBundle.getCreateDate());
		offeringBundleImpl.setName(offeringBundle.getName());

		return offeringBundleImpl;
	}

	/**
	 * Returns the offering bundle with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering bundle
	 * @return the offering bundle
	 * @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	 */
	@Override
	public OfferingBundle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOfferingBundleException {
		OfferingBundle offeringBundle = fetchByPrimaryKey(primaryKey);

		if (offeringBundle == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOfferingBundleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return offeringBundle;
	}

	/**
	 * Returns the offering bundle with the primary key or throws a {@link NoSuchOfferingBundleException} if it could not be found.
	 *
	 * @param offeringBundleId the primary key of the offering bundle
	 * @return the offering bundle
	 * @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	 */
	@Override
	public OfferingBundle findByPrimaryKey(long offeringBundleId)
		throws NoSuchOfferingBundleException {
		return findByPrimaryKey((Serializable)offeringBundleId);
	}

	/**
	 * Returns the offering bundle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering bundle
	 * @return the offering bundle, or <code>null</code> if a offering bundle with the primary key could not be found
	 */
	@Override
	public OfferingBundle fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
				OfferingBundleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OfferingBundle offeringBundle = (OfferingBundle)serializable;

		if (offeringBundle == null) {
			Session session = null;

			try {
				session = openSession();

				offeringBundle = (OfferingBundle)session.get(OfferingBundleImpl.class,
						primaryKey);

				if (offeringBundle != null) {
					cacheResult(offeringBundle);
				}
				else {
					entityCache.putResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
						OfferingBundleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
					OfferingBundleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return offeringBundle;
	}

	/**
	 * Returns the offering bundle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offeringBundleId the primary key of the offering bundle
	 * @return the offering bundle, or <code>null</code> if a offering bundle with the primary key could not be found
	 */
	@Override
	public OfferingBundle fetchByPrimaryKey(long offeringBundleId) {
		return fetchByPrimaryKey((Serializable)offeringBundleId);
	}

	@Override
	public Map<Serializable, OfferingBundle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OfferingBundle> map = new HashMap<Serializable, OfferingBundle>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OfferingBundle offeringBundle = fetchByPrimaryKey(primaryKey);

			if (offeringBundle != null) {
				map.put(primaryKey, offeringBundle);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
					OfferingBundleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OfferingBundle)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OFFERINGBUNDLE_WHERE_PKS_IN);

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

			for (OfferingBundle offeringBundle : (List<OfferingBundle>)q.list()) {
				map.put(offeringBundle.getPrimaryKeyObj(), offeringBundle);

				cacheResult(offeringBundle);

				uncachedPrimaryKeys.remove(offeringBundle.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
					OfferingBundleImpl.class, primaryKey, nullModel);
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
	 * Returns all the offering bundles.
	 *
	 * @return the offering bundles
	 */
	@Override
	public List<OfferingBundle> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @return the range of offering bundles
	 */
	@Override
	public List<OfferingBundle> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering bundles
	 */
	@Override
	public List<OfferingBundle> findAll(int start, int end,
		OrderByComparator<OfferingBundle> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of offering bundles
	 */
	@Override
	public List<OfferingBundle> findAll(int start, int end,
		OrderByComparator<OfferingBundle> orderByComparator,
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

		List<OfferingBundle> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingBundle>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OFFERINGBUNDLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OFFERINGBUNDLE;

				if (pagination) {
					sql = sql.concat(OfferingBundleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OfferingBundle>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingBundle>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the offering bundles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OfferingBundle offeringBundle : findAll()) {
			remove(offeringBundle);
		}
	}

	/**
	 * Returns the number of offering bundles.
	 *
	 * @return the number of offering bundles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OFFERINGBUNDLE);

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
	 * Returns the primaryKeys of offering definitions associated with the offering bundle.
	 *
	 * @param pk the primary key of the offering bundle
	 * @return long[] of the primaryKeys of offering definitions associated with the offering bundle
	 */
	@Override
	public long[] getOfferingDefinitionPrimaryKeys(long pk) {
		long[] pks = offeringBundleToOfferingDefinitionTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the offering definitions associated with the offering bundle.
	 *
	 * @param pk the primary key of the offering bundle
	 * @return the offering definitions associated with the offering bundle
	 */
	@Override
	public List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk) {
		return getOfferingDefinitions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the offering definitions associated with the offering bundle.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the offering bundle
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @return the range of offering definitions associated with the offering bundle
	 */
	@Override
	public List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end) {
		return getOfferingDefinitions(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions associated with the offering bundle.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the offering bundle
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering definitions associated with the offering bundle
	 */
	@Override
	public List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.OfferingDefinition> orderByComparator) {
		return offeringBundleToOfferingDefinitionTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of offering definitions associated with the offering bundle.
	 *
	 * @param pk the primary key of the offering bundle
	 * @return the number of offering definitions associated with the offering bundle
	 */
	@Override
	public int getOfferingDefinitionsSize(long pk) {
		long[] pks = offeringBundleToOfferingDefinitionTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the offering definition is associated with the offering bundle.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPK the primary key of the offering definition
	 * @return <code>true</code> if the offering definition is associated with the offering bundle; <code>false</code> otherwise
	 */
	@Override
	public boolean containsOfferingDefinition(long pk, long offeringDefinitionPK) {
		return offeringBundleToOfferingDefinitionTableMapper.containsTableMapping(pk,
			offeringDefinitionPK);
	}

	/**
	 * Returns <code>true</code> if the offering bundle has any offering definitions associated with it.
	 *
	 * @param pk the primary key of the offering bundle to check for associations with offering definitions
	 * @return <code>true</code> if the offering bundle has any offering definitions associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsOfferingDefinitions(long pk) {
		if (getOfferingDefinitionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPK the primary key of the offering definition
	 */
	@Override
	public void addOfferingDefinition(long pk, long offeringDefinitionPK) {
		OfferingBundle offeringBundle = fetchByPrimaryKey(pk);

		if (offeringBundle == null) {
			offeringBundleToOfferingDefinitionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, offeringDefinitionPK);
		}
		else {
			offeringBundleToOfferingDefinitionTableMapper.addTableMapping(offeringBundle.getCompanyId(),
				pk, offeringDefinitionPK);
		}
	}

	/**
	 * Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinition the offering definition
	 */
	@Override
	public void addOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		OfferingBundle offeringBundle = fetchByPrimaryKey(pk);

		if (offeringBundle == null) {
			offeringBundleToOfferingDefinitionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, offeringDefinition.getPrimaryKey());
		}
		else {
			offeringBundleToOfferingDefinitionTableMapper.addTableMapping(offeringBundle.getCompanyId(),
				pk, offeringDefinition.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPKs the primary keys of the offering definitions
	 */
	@Override
	public void addOfferingDefinitions(long pk, long[] offeringDefinitionPKs) {
		long companyId = 0;

		OfferingBundle offeringBundle = fetchByPrimaryKey(pk);

		if (offeringBundle == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = offeringBundle.getCompanyId();
		}

		offeringBundleToOfferingDefinitionTableMapper.addTableMappings(companyId,
			pk, offeringDefinitionPKs);
	}

	/**
	 * Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitions the offering definitions
	 */
	@Override
	public void addOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		addOfferingDefinitions(pk,
			ListUtil.toLongArray(offeringDefinitions,
				com.liferay.osb.model.OfferingDefinition.OFFERING_DEFINITION_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the offering bundle and its offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle to clear the associated offering definitions from
	 */
	@Override
	public void clearOfferingDefinitions(long pk) {
		offeringBundleToOfferingDefinitionTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPK the primary key of the offering definition
	 */
	@Override
	public void removeOfferingDefinition(long pk, long offeringDefinitionPK) {
		offeringBundleToOfferingDefinitionTableMapper.deleteTableMapping(pk,
			offeringDefinitionPK);
	}

	/**
	 * Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinition the offering definition
	 */
	@Override
	public void removeOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		offeringBundleToOfferingDefinitionTableMapper.deleteTableMapping(pk,
			offeringDefinition.getPrimaryKey());
	}

	/**
	 * Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPKs the primary keys of the offering definitions
	 */
	@Override
	public void removeOfferingDefinitions(long pk, long[] offeringDefinitionPKs) {
		offeringBundleToOfferingDefinitionTableMapper.deleteTableMappings(pk,
			offeringDefinitionPKs);
	}

	/**
	 * Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitions the offering definitions
	 */
	@Override
	public void removeOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		removeOfferingDefinitions(pk,
			ListUtil.toLongArray(offeringDefinitions,
				com.liferay.osb.model.OfferingDefinition.OFFERING_DEFINITION_ID_ACCESSOR));
	}

	/**
	 * Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPKs the primary keys of the offering definitions to be associated with the offering bundle
	 */
	@Override
	public void setOfferingDefinitions(long pk, long[] offeringDefinitionPKs) {
		Set<Long> newOfferingDefinitionPKsSet = SetUtil.fromArray(offeringDefinitionPKs);
		Set<Long> oldOfferingDefinitionPKsSet = SetUtil.fromArray(offeringBundleToOfferingDefinitionTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeOfferingDefinitionPKsSet = new HashSet<Long>(oldOfferingDefinitionPKsSet);

		removeOfferingDefinitionPKsSet.removeAll(newOfferingDefinitionPKsSet);

		offeringBundleToOfferingDefinitionTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeOfferingDefinitionPKsSet));

		newOfferingDefinitionPKsSet.removeAll(oldOfferingDefinitionPKsSet);

		long companyId = 0;

		OfferingBundle offeringBundle = fetchByPrimaryKey(pk);

		if (offeringBundle == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = offeringBundle.getCompanyId();
		}

		offeringBundleToOfferingDefinitionTableMapper.addTableMappings(companyId,
			pk, ArrayUtil.toLongArray(newOfferingDefinitionPKsSet));
	}

	/**
	 * Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitions the offering definitions to be associated with the offering bundle
	 */
	@Override
	public void setOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		try {
			long[] offeringDefinitionPKs = new long[offeringDefinitions.size()];

			for (int i = 0; i < offeringDefinitions.size(); i++) {
				com.liferay.osb.model.OfferingDefinition offeringDefinition = offeringDefinitions.get(i);

				offeringDefinitionPKs[i] = offeringDefinition.getPrimaryKey();
			}

			setOfferingDefinitions(pk, offeringDefinitionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OfferingBundleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the offering bundle persistence.
	 */
	public void afterPropertiesSet() {
		offeringBundleToOfferingDefinitionTableMapper = TableMapperFactory.getTableMapper("OSB_OfferingBundles_OfferingDefinitions",
				"companyId", "offeringBundleId", "offeringDefinitionId", this,
				offeringDefinitionPersistence);
	}

	public void destroy() {
		entityCache.removeCache(OfferingBundleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_OfferingBundles_OfferingDefinitions");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	protected TableMapper<OfferingBundle, com.liferay.osb.model.OfferingDefinition> offeringBundleToOfferingDefinitionTableMapper;
	private static final String _SQL_SELECT_OFFERINGBUNDLE = "SELECT offeringBundle FROM OfferingBundle offeringBundle";
	private static final String _SQL_SELECT_OFFERINGBUNDLE_WHERE_PKS_IN = "SELECT offeringBundle FROM OfferingBundle offeringBundle WHERE offeringBundleId IN (";
	private static final String _SQL_SELECT_OFFERINGBUNDLE_WHERE = "SELECT offeringBundle FROM OfferingBundle offeringBundle WHERE ";
	private static final String _SQL_COUNT_OFFERINGBUNDLE = "SELECT COUNT(offeringBundle) FROM OfferingBundle offeringBundle";
	private static final String _SQL_COUNT_OFFERINGBUNDLE_WHERE = "SELECT COUNT(offeringBundle) FROM OfferingBundle offeringBundle WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "offeringBundle.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OfferingBundle exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OfferingBundle exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OfferingBundlePersistenceImpl.class);
}
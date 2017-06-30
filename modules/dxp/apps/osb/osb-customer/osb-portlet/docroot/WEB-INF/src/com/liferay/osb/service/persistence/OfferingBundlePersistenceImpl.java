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

import com.liferay.osb.NoSuchOfferingBundleException;
import com.liferay.osb.model.OfferingBundle;
import com.liferay.osb.model.impl.OfferingBundleImpl;
import com.liferay.osb.model.impl.OfferingBundleModelImpl;

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
 * The persistence implementation for the offering bundle service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundlePersistence
 * @see OfferingBundleUtil
 * @generated
 */
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
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED,
			OfferingBundleImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			OfferingBundleModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
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

	/**
	 * Caches the offering bundle in the entity cache if it is enabled.
	 *
	 * @param offeringBundle the offering bundle
	 */
	public void cacheResult(OfferingBundle offeringBundle) {
		EntityCacheUtil.putResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleImpl.class, offeringBundle.getPrimaryKey(),
			offeringBundle);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { offeringBundle.getName() }, offeringBundle);

		offeringBundle.resetOriginalValues();
	}

	/**
	 * Caches the offering bundles in the entity cache if it is enabled.
	 *
	 * @param offeringBundles the offering bundles
	 */
	public void cacheResult(List<OfferingBundle> offeringBundles) {
		for (OfferingBundle offeringBundle : offeringBundles) {
			if (EntityCacheUtil.getResult(
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
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OfferingBundleImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OfferingBundleImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the offering bundle.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfferingBundle offeringBundle) {
		EntityCacheUtil.removeResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleImpl.class, offeringBundle.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(offeringBundle);
	}

	@Override
	public void clearCache(List<OfferingBundle> offeringBundles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfferingBundle offeringBundle : offeringBundles) {
			EntityCacheUtil.removeResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
				OfferingBundleImpl.class, offeringBundle.getPrimaryKey());

			clearUniqueFindersCache(offeringBundle);
		}
	}

	protected void cacheUniqueFindersCache(OfferingBundle offeringBundle) {
		if (offeringBundle.isNew()) {
			Object[] args = new Object[] { offeringBundle.getName() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
				offeringBundle);
		}
		else {
			OfferingBundleModelImpl offeringBundleModelImpl = (OfferingBundleModelImpl)offeringBundle;

			if ((offeringBundleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { offeringBundle.getName() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
					offeringBundle);
			}
		}
	}

	protected void clearUniqueFindersCache(OfferingBundle offeringBundle) {
		OfferingBundleModelImpl offeringBundleModelImpl = (OfferingBundleModelImpl)offeringBundle;

		Object[] args = new Object[] { offeringBundle.getName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

		if ((offeringBundleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			args = new Object[] { offeringBundleModelImpl.getOriginalName() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	 *
	 * @param offeringBundleId the primary key for the new offering bundle
	 * @return the new offering bundle
	 */
	public OfferingBundle create(long offeringBundleId) {
		OfferingBundle offeringBundle = new OfferingBundleImpl();

		offeringBundle.setNew(true);
		offeringBundle.setPrimaryKey(offeringBundleId);

		return offeringBundle;
	}

	/**
	 * Removes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offeringBundleId the primary key of the offering bundle
	 * @return the offering bundle that was removed
	 * @throws com.liferay.osb.NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingBundle remove(long offeringBundleId)
		throws NoSuchOfferingBundleException, SystemException {
		return remove(Long.valueOf(offeringBundleId));
	}

	/**
	 * Removes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the offering bundle
	 * @return the offering bundle that was removed
	 * @throws com.liferay.osb.NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingBundle remove(Serializable primaryKey)
		throws NoSuchOfferingBundleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OfferingBundle offeringBundle = (OfferingBundle)session.get(OfferingBundleImpl.class,
					primaryKey);

			if (offeringBundle == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
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
	protected OfferingBundle removeImpl(OfferingBundle offeringBundle)
		throws SystemException {
		offeringBundle = toUnwrappedModel(offeringBundle);

		try {
			clearOfferingDefinitions.clear(offeringBundle.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, offeringBundle);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(offeringBundle);

		return offeringBundle;
	}

	@Override
	public OfferingBundle updateImpl(
		com.liferay.osb.model.OfferingBundle offeringBundle, boolean merge)
		throws SystemException {
		offeringBundle = toUnwrappedModel(offeringBundle);

		boolean isNew = offeringBundle.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, offeringBundle, merge);

			offeringBundle.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OfferingBundleModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleImpl.class, offeringBundle.getPrimaryKey(),
			offeringBundle);

		clearUniqueFindersCache(offeringBundle);
		cacheUniqueFindersCache(offeringBundle);

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
		offeringBundleImpl.setUserId(offeringBundle.getUserId());
		offeringBundleImpl.setUserName(offeringBundle.getUserName());
		offeringBundleImpl.setCreateDate(offeringBundle.getCreateDate());
		offeringBundleImpl.setName(offeringBundle.getName());

		return offeringBundleImpl;
	}

	/**
	 * Returns the offering bundle with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering bundle
	 * @return the offering bundle
	 * @throws com.liferay.portal.NoSuchModelException if a offering bundle with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingBundle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the offering bundle with the primary key or throws a {@link com.liferay.osb.NoSuchOfferingBundleException} if it could not be found.
	 *
	 * @param offeringBundleId the primary key of the offering bundle
	 * @return the offering bundle
	 * @throws com.liferay.osb.NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingBundle findByPrimaryKey(long offeringBundleId)
		throws NoSuchOfferingBundleException, SystemException {
		OfferingBundle offeringBundle = fetchByPrimaryKey(offeringBundleId);

		if (offeringBundle == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + offeringBundleId);
			}

			throw new NoSuchOfferingBundleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				offeringBundleId);
		}

		return offeringBundle;
	}

	/**
	 * Returns the offering bundle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering bundle
	 * @return the offering bundle, or <code>null</code> if a offering bundle with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingBundle fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the offering bundle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offeringBundleId the primary key of the offering bundle
	 * @return the offering bundle, or <code>null</code> if a offering bundle with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingBundle fetchByPrimaryKey(long offeringBundleId)
		throws SystemException {
		OfferingBundle offeringBundle = (OfferingBundle)EntityCacheUtil.getResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
				OfferingBundleImpl.class, offeringBundleId);

		if (offeringBundle == _nullOfferingBundle) {
			return null;
		}

		if (offeringBundle == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				offeringBundle = (OfferingBundle)session.get(OfferingBundleImpl.class,
						Long.valueOf(offeringBundleId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (offeringBundle != null) {
					cacheResult(offeringBundle);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
						OfferingBundleImpl.class, offeringBundleId,
						_nullOfferingBundle);
				}

				closeSession(session);
			}
		}

		return offeringBundle;
	}

	/**
	 * Returns the offering bundle where name = &#63; or throws a {@link com.liferay.osb.NoSuchOfferingBundleException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching offering bundle
	 * @throws com.liferay.osb.NoSuchOfferingBundleException if a matching offering bundle could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingBundle findByName(String name)
		throws NoSuchOfferingBundleException, SystemException {
		OfferingBundle offeringBundle = fetchByName(name);

		if (offeringBundle == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
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
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingBundle fetchByName(String name) throws SystemException {
		return fetchByName(name, true);
	}

	/**
	 * Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingBundle fetchByName(String name, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof OfferingBundle) {
			OfferingBundle offeringBundle = (OfferingBundle)result;

			if (!Validator.equals(name, offeringBundle.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_OFFERINGBUNDLE_WHERE);

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

			query.append(OfferingBundleModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				List<OfferingBundle> list = q.list();

				result = list;

				OfferingBundle offeringBundle = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					offeringBundle = list.get(0);

					cacheResult(offeringBundle);

					if ((offeringBundle.getName() == null) ||
							!offeringBundle.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, offeringBundle);
					}
				}

				return offeringBundle;
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
				return (OfferingBundle)result;
			}
		}
	}

	/**
	 * Returns all the offering bundles.
	 *
	 * @return the offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingBundle> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @return the range of offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingBundle> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingBundle> findAll(int start, int end,
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

		List<OfferingBundle> list = (List<OfferingBundle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OFFERINGBUNDLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OFFERINGBUNDLE.concat(OfferingBundleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OfferingBundle>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OfferingBundle>)QueryUtil.list(q,
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
	 * Removes the offering bundle where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the offering bundle that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingBundle removeByName(String name)
		throws NoSuchOfferingBundleException, SystemException {
		OfferingBundle offeringBundle = findByName(name);

		return remove(offeringBundle);
	}

	/**
	 * Removes all the offering bundles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OfferingBundle offeringBundle : findAll()) {
			remove(offeringBundle);
		}
	}

	/**
	 * Returns the number of offering bundles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGBUNDLE_WHERE);

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
	 * Returns the number of offering bundles.
	 *
	 * @return the number of offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OFFERINGBUNDLE);

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
	 * Returns all the offering definitions associated with the offering bundle.
	 *
	 * @param pk the primary key of the offering bundle
	 * @return the offering definitions associated with the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk) throws SystemException {
		return getOfferingDefinitions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the offering definitions associated with the offering bundle.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the offering bundle
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @return the range of offering definitions associated with the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end) throws SystemException {
		return getOfferingDefinitions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_OFFERINGDEFINITIONS = new FinderPath(com.liferay.osb.model.impl.OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS,
			com.liferay.osb.model.impl.OfferingDefinitionImpl.class,
			OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME,
			"getOfferingDefinitions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_OFFERINGDEFINITIONS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the offering definitions associated with the offering bundle.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the offering bundle
	 * @param start the lower bound of the range of offering bundles
	 * @param end the upper bound of the range of offering bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering definitions associated with the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.OfferingDefinition> list = (List<com.liferay.osb.model.OfferingDefinition>)FinderCacheUtil.getResult(FINDER_PATH_GET_OFFERINGDEFINITIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETOFFERINGDEFINITIONS.concat(ORDER_BY_CLAUSE)
													 .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETOFFERINGDEFINITIONS.concat(com.liferay.osb.model.impl.OfferingDefinitionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_OfferingDefinition",
					com.liferay.osb.model.impl.OfferingDefinitionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.OfferingDefinition>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_OFFERINGDEFINITIONS,
						finderArgs);
				}
				else {
					offeringDefinitionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_OFFERINGDEFINITIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_OFFERINGDEFINITIONS_SIZE = new FinderPath(com.liferay.osb.model.impl.OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS,
			Long.class,
			OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME,
			"getOfferingDefinitionsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_OFFERINGDEFINITIONS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of offering definitions associated with the offering bundle.
	 *
	 * @param pk the primary key of the offering bundle
	 * @return the number of offering definitions associated with the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public int getOfferingDefinitionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_OFFERINGDEFINITIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETOFFERINGDEFINITIONSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_OFFERINGDEFINITIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_OFFERINGDEFINITION = new FinderPath(com.liferay.osb.model.impl.OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingBundleModelImpl.FINDER_CACHE_ENABLED_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS,
			Boolean.class,
			OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME,
			"containsOfferingDefinition",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the offering definition is associated with the offering bundle.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPK the primary key of the offering definition
	 * @return <code>true</code> if the offering definition is associated with the offering bundle; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsOfferingDefinition(long pk, long offeringDefinitionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, offeringDefinitionPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_OFFERINGDEFINITION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsOfferingDefinition.contains(
							pk, offeringDefinitionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_OFFERINGDEFINITION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the offering bundle has any offering definitions associated with it.
	 *
	 * @param pk the primary key of the offering bundle to check for associations with offering definitions
	 * @return <code>true</code> if the offering bundle has any offering definitions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsOfferingDefinitions(long pk)
		throws SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingDefinition(long pk, long offeringDefinitionPK)
		throws SystemException {
		try {
			addOfferingDefinition.add(pk, offeringDefinitionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinition the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws SystemException {
		try {
			addOfferingDefinition.add(pk, offeringDefinition.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPKs the primary keys of the offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingDefinitions(long pk, long[] offeringDefinitionPKs)
		throws SystemException {
		try {
			for (long offeringDefinitionPK : offeringDefinitionPKs) {
				addOfferingDefinition.add(pk, offeringDefinitionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitions the offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.OfferingDefinition offeringDefinition : offeringDefinitions) {
				addOfferingDefinition.add(pk, offeringDefinition.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the offering bundle and its offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle to clear the associated offering definitions from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearOfferingDefinitions(long pk) throws SystemException {
		try {
			clearOfferingDefinitions.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPK the primary key of the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingDefinition(long pk, long offeringDefinitionPK)
		throws SystemException {
		try {
			removeOfferingDefinition.remove(pk, offeringDefinitionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinition the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws SystemException {
		try {
			removeOfferingDefinition.remove(pk,
				offeringDefinition.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPKs the primary keys of the offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingDefinitions(long pk, long[] offeringDefinitionPKs)
		throws SystemException {
		try {
			for (long offeringDefinitionPK : offeringDefinitionPKs) {
				removeOfferingDefinition.remove(pk, offeringDefinitionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitions the offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.OfferingDefinition offeringDefinition : offeringDefinitions) {
				removeOfferingDefinition.remove(pk,
					offeringDefinition.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitionPKs the primary keys of the offering definitions to be associated with the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public void setOfferingDefinitions(long pk, long[] offeringDefinitionPKs)
		throws SystemException {
		try {
			Set<Long> offeringDefinitionPKSet = SetUtil.fromArray(offeringDefinitionPKs);

			List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions = getOfferingDefinitions(pk);

			for (com.liferay.osb.model.OfferingDefinition offeringDefinition : offeringDefinitions) {
				if (!offeringDefinitionPKSet.remove(
							offeringDefinition.getPrimaryKey())) {
					removeOfferingDefinition.remove(pk,
						offeringDefinition.getPrimaryKey());
				}
			}

			for (Long offeringDefinitionPK : offeringDefinitionPKSet) {
				addOfferingDefinition.add(pk, offeringDefinitionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering bundle
	 * @param offeringDefinitions the offering definitions to be associated with the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public void setOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions)
		throws SystemException {
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
		finally {
			FinderCacheUtil.clearCache(OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Initializes the offering bundle persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.OfferingBundle")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OfferingBundle>> listenersList = new ArrayList<ModelListener<OfferingBundle>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<OfferingBundle>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsOfferingDefinition = new ContainsOfferingDefinition();

		addOfferingDefinition = new AddOfferingDefinition();
		clearOfferingDefinitions = new ClearOfferingDefinitions();
		removeOfferingDefinition = new RemoveOfferingDefinition();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(OfferingBundleImpl.class.getName());
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
	protected ContainsOfferingDefinition containsOfferingDefinition;
	protected AddOfferingDefinition addOfferingDefinition;
	protected ClearOfferingDefinitions clearOfferingDefinitions;
	protected RemoveOfferingDefinition removeOfferingDefinition;

	protected class ContainsOfferingDefinition {
		protected ContainsOfferingDefinition() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSOFFERINGDEFINITION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long offeringBundleId,
			long offeringDefinitionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(offeringBundleId),
						new Long(offeringDefinitionId)
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

	protected class AddOfferingDefinition {
		protected AddOfferingDefinition() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_OfferingBundles_OfferingDefinitions (offeringBundleId, offeringDefinitionId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long offeringBundleId, long offeringDefinitionId)
			throws SystemException {
			if (!containsOfferingDefinition.contains(offeringBundleId,
						offeringDefinitionId)) {
				ModelListener<com.liferay.osb.model.OfferingDefinition>[] offeringDefinitionListeners =
					offeringDefinitionPersistence.getListeners();

				for (ModelListener<OfferingBundle> listener : listeners) {
					listener.onBeforeAddAssociation(offeringBundleId,
						com.liferay.osb.model.OfferingDefinition.class.getName(),
						offeringDefinitionId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingDefinition> listener : offeringDefinitionListeners) {
					listener.onBeforeAddAssociation(offeringDefinitionId,
						OfferingBundle.class.getName(), offeringBundleId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(offeringBundleId),
						new Long(offeringDefinitionId)
					});

				for (ModelListener<OfferingBundle> listener : listeners) {
					listener.onAfterAddAssociation(offeringBundleId,
						com.liferay.osb.model.OfferingDefinition.class.getName(),
						offeringDefinitionId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingDefinition> listener : offeringDefinitionListeners) {
					listener.onAfterAddAssociation(offeringDefinitionId,
						OfferingBundle.class.getName(), offeringBundleId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearOfferingDefinitions {
		protected ClearOfferingDefinitions() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringBundleId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long offeringBundleId) throws SystemException {
			ModelListener<com.liferay.osb.model.OfferingDefinition>[] offeringDefinitionListeners =
				offeringDefinitionPersistence.getListeners();

			List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions = null;

			if ((listeners.length > 0) ||
					(offeringDefinitionListeners.length > 0)) {
				offeringDefinitions = getOfferingDefinitions(offeringBundleId);

				for (com.liferay.osb.model.OfferingDefinition offeringDefinition : offeringDefinitions) {
					for (ModelListener<OfferingBundle> listener : listeners) {
						listener.onBeforeRemoveAssociation(offeringBundleId,
							com.liferay.osb.model.OfferingDefinition.class.getName(),
							offeringDefinition.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.OfferingDefinition> listener : offeringDefinitionListeners) {
						listener.onBeforeRemoveAssociation(offeringDefinition.getPrimaryKey(),
							OfferingBundle.class.getName(), offeringBundleId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(offeringBundleId) });

			if ((listeners.length > 0) ||
					(offeringDefinitionListeners.length > 0)) {
				for (com.liferay.osb.model.OfferingDefinition offeringDefinition : offeringDefinitions) {
					for (ModelListener<OfferingBundle> listener : listeners) {
						listener.onAfterRemoveAssociation(offeringBundleId,
							com.liferay.osb.model.OfferingDefinition.class.getName(),
							offeringDefinition.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.OfferingDefinition> listener : offeringDefinitionListeners) {
						listener.onAfterRemoveAssociation(offeringDefinition.getPrimaryKey(),
							OfferingBundle.class.getName(), offeringBundleId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveOfferingDefinition {
		protected RemoveOfferingDefinition() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringBundleId = ? AND offeringDefinitionId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long offeringBundleId, long offeringDefinitionId)
			throws SystemException {
			if (containsOfferingDefinition.contains(offeringBundleId,
						offeringDefinitionId)) {
				ModelListener<com.liferay.osb.model.OfferingDefinition>[] offeringDefinitionListeners =
					offeringDefinitionPersistence.getListeners();

				for (ModelListener<OfferingBundle> listener : listeners) {
					listener.onBeforeRemoveAssociation(offeringBundleId,
						com.liferay.osb.model.OfferingDefinition.class.getName(),
						offeringDefinitionId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingDefinition> listener : offeringDefinitionListeners) {
					listener.onBeforeRemoveAssociation(offeringDefinitionId,
						OfferingBundle.class.getName(), offeringBundleId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(offeringBundleId),
						new Long(offeringDefinitionId)
					});

				for (ModelListener<OfferingBundle> listener : listeners) {
					listener.onAfterRemoveAssociation(offeringBundleId,
						com.liferay.osb.model.OfferingDefinition.class.getName(),
						offeringDefinitionId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingDefinition> listener : offeringDefinitionListeners) {
					listener.onAfterRemoveAssociation(offeringDefinitionId,
						OfferingBundle.class.getName(), offeringBundleId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_OFFERINGBUNDLE = "SELECT offeringBundle FROM OfferingBundle offeringBundle";
	private static final String _SQL_SELECT_OFFERINGBUNDLE_WHERE = "SELECT offeringBundle FROM OfferingBundle offeringBundle WHERE ";
	private static final String _SQL_COUNT_OFFERINGBUNDLE = "SELECT COUNT(offeringBundle) FROM OfferingBundle offeringBundle";
	private static final String _SQL_COUNT_OFFERINGBUNDLE_WHERE = "SELECT COUNT(offeringBundle) FROM OfferingBundle offeringBundle WHERE ";
	private static final String _SQL_GETOFFERINGDEFINITIONS = "SELECT {OSB_OfferingDefinition.*} FROM OSB_OfferingDefinition INNER JOIN OSB_OfferingBundles_OfferingDefinitions ON (OSB_OfferingBundles_OfferingDefinitions.offeringDefinitionId = OSB_OfferingDefinition.offeringDefinitionId) WHERE (OSB_OfferingBundles_OfferingDefinitions.offeringBundleId = ?)";
	private static final String _SQL_GETOFFERINGDEFINITIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringBundleId = ?";
	private static final String _SQL_CONTAINSOFFERINGDEFINITION = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringBundleId = ? AND offeringDefinitionId = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "offeringBundle.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "offeringBundle.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(offeringBundle.name IS NULL OR offeringBundle.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "offeringBundle.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OfferingBundle exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OfferingBundle exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OfferingBundlePersistenceImpl.class);
	private static OfferingBundle _nullOfferingBundle = new OfferingBundleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OfferingBundle> toCacheModel() {
				return _nullOfferingBundleCacheModel;
			}
		};

	private static CacheModel<OfferingBundle> _nullOfferingBundleCacheModel = new CacheModel<OfferingBundle>() {
			public OfferingBundle toEntityModel() {
				return _nullOfferingBundle;
			}
		};
}
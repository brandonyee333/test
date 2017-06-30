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

import com.liferay.osb.NoSuchAssetRecommendationSetException;
import com.liferay.osb.model.AssetRecommendationSet;
import com.liferay.osb.model.impl.AssetRecommendationSetImpl;
import com.liferay.osb.model.impl.AssetRecommendationSetModelImpl;

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
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the asset recommendation set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetRecommendationSetPersistence
 * @see AssetRecommendationSetUtil
 * @generated
 */
public class AssetRecommendationSetPersistenceImpl extends BasePersistenceImpl<AssetRecommendationSet>
	implements AssetRecommendationSetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetRecommendationSetUtil} to access the asset recommendation set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetRecommendationSetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetModelImpl.FINDER_CACHE_ENABLED,
			AssetRecommendationSetImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetRecommendationSetModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetRecommendationSetModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetModelImpl.FINDER_CACHE_ENABLED,
			AssetRecommendationSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetModelImpl.FINDER_CACHE_ENABLED,
			AssetRecommendationSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset recommendation set in the entity cache if it is enabled.
	 *
	 * @param assetRecommendationSet the asset recommendation set
	 */
	public void cacheResult(AssetRecommendationSet assetRecommendationSet) {
		EntityCacheUtil.putResult(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetImpl.class,
			assetRecommendationSet.getPrimaryKey(), assetRecommendationSet);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				Long.valueOf(assetRecommendationSet.getClassNameId()),
				Long.valueOf(assetRecommendationSet.getClassPK())
			}, assetRecommendationSet);

		assetRecommendationSet.resetOriginalValues();
	}

	/**
	 * Caches the asset recommendation sets in the entity cache if it is enabled.
	 *
	 * @param assetRecommendationSets the asset recommendation sets
	 */
	public void cacheResult(
		List<AssetRecommendationSet> assetRecommendationSets) {
		for (AssetRecommendationSet assetRecommendationSet : assetRecommendationSets) {
			if (EntityCacheUtil.getResult(
						AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
						AssetRecommendationSetImpl.class,
						assetRecommendationSet.getPrimaryKey()) == null) {
				cacheResult(assetRecommendationSet);
			}
			else {
				assetRecommendationSet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset recommendation sets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetRecommendationSetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetRecommendationSetImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset recommendation set.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetRecommendationSet assetRecommendationSet) {
		EntityCacheUtil.removeResult(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetImpl.class,
			assetRecommendationSet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetRecommendationSet);
	}

	@Override
	public void clearCache(List<AssetRecommendationSet> assetRecommendationSets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetRecommendationSet assetRecommendationSet : assetRecommendationSets) {
			EntityCacheUtil.removeResult(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
				AssetRecommendationSetImpl.class,
				assetRecommendationSet.getPrimaryKey());

			clearUniqueFindersCache(assetRecommendationSet);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetRecommendationSet assetRecommendationSet) {
		if (assetRecommendationSet.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(assetRecommendationSet.getClassNameId()),
					Long.valueOf(assetRecommendationSet.getClassPK())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args,
				assetRecommendationSet);
		}
		else {
			AssetRecommendationSetModelImpl assetRecommendationSetModelImpl = (AssetRecommendationSetModelImpl)assetRecommendationSet;

			if ((assetRecommendationSetModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetRecommendationSet.getClassNameId()),
						Long.valueOf(assetRecommendationSet.getClassPK())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args,
					assetRecommendationSet);
			}
		}
	}

	protected void clearUniqueFindersCache(
		AssetRecommendationSet assetRecommendationSet) {
		AssetRecommendationSetModelImpl assetRecommendationSetModelImpl = (AssetRecommendationSetModelImpl)assetRecommendationSet;

		Object[] args = new Object[] {
				Long.valueOf(assetRecommendationSet.getClassNameId()),
				Long.valueOf(assetRecommendationSet.getClassPK())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);

		if ((assetRecommendationSetModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(assetRecommendationSetModelImpl.getOriginalClassNameId()),
					Long.valueOf(assetRecommendationSetModelImpl.getOriginalClassPK())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
		}
	}

	/**
	 * Creates a new asset recommendation set with the primary key. Does not add the asset recommendation set to the database.
	 *
	 * @param assetRecommendationSetId the primary key for the new asset recommendation set
	 * @return the new asset recommendation set
	 */
	public AssetRecommendationSet create(long assetRecommendationSetId) {
		AssetRecommendationSet assetRecommendationSet = new AssetRecommendationSetImpl();

		assetRecommendationSet.setNew(true);
		assetRecommendationSet.setPrimaryKey(assetRecommendationSetId);

		return assetRecommendationSet;
	}

	/**
	 * Removes the asset recommendation set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetRecommendationSetId the primary key of the asset recommendation set
	 * @return the asset recommendation set that was removed
	 * @throws com.liferay.osb.NoSuchAssetRecommendationSetException if a asset recommendation set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetRecommendationSet remove(long assetRecommendationSetId)
		throws NoSuchAssetRecommendationSetException, SystemException {
		return remove(Long.valueOf(assetRecommendationSetId));
	}

	/**
	 * Removes the asset recommendation set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset recommendation set
	 * @return the asset recommendation set that was removed
	 * @throws com.liferay.osb.NoSuchAssetRecommendationSetException if a asset recommendation set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetRecommendationSet remove(Serializable primaryKey)
		throws NoSuchAssetRecommendationSetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetRecommendationSet assetRecommendationSet = (AssetRecommendationSet)session.get(AssetRecommendationSetImpl.class,
					primaryKey);

			if (assetRecommendationSet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetRecommendationSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetRecommendationSet);
		}
		catch (NoSuchAssetRecommendationSetException nsee) {
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
	protected AssetRecommendationSet removeImpl(
		AssetRecommendationSet assetRecommendationSet)
		throws SystemException {
		assetRecommendationSet = toUnwrappedModel(assetRecommendationSet);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetRecommendationSet);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetRecommendationSet);

		return assetRecommendationSet;
	}

	@Override
	public AssetRecommendationSet updateImpl(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet,
		boolean merge) throws SystemException {
		assetRecommendationSet = toUnwrappedModel(assetRecommendationSet);

		boolean isNew = assetRecommendationSet.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetRecommendationSet, merge);

			assetRecommendationSet.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetRecommendationSetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
			AssetRecommendationSetImpl.class,
			assetRecommendationSet.getPrimaryKey(), assetRecommendationSet);

		clearUniqueFindersCache(assetRecommendationSet);
		cacheUniqueFindersCache(assetRecommendationSet);

		return assetRecommendationSet;
	}

	protected AssetRecommendationSet toUnwrappedModel(
		AssetRecommendationSet assetRecommendationSet) {
		if (assetRecommendationSet instanceof AssetRecommendationSetImpl) {
			return assetRecommendationSet;
		}

		AssetRecommendationSetImpl assetRecommendationSetImpl = new AssetRecommendationSetImpl();

		assetRecommendationSetImpl.setNew(assetRecommendationSet.isNew());
		assetRecommendationSetImpl.setPrimaryKey(assetRecommendationSet.getPrimaryKey());

		assetRecommendationSetImpl.setAssetRecommendationSetId(assetRecommendationSet.getAssetRecommendationSetId());
		assetRecommendationSetImpl.setClassNameId(assetRecommendationSet.getClassNameId());
		assetRecommendationSetImpl.setClassPK(assetRecommendationSet.getClassPK());

		return assetRecommendationSetImpl;
	}

	/**
	 * Returns the asset recommendation set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset recommendation set
	 * @return the asset recommendation set
	 * @throws com.liferay.portal.NoSuchModelException if a asset recommendation set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetRecommendationSet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset recommendation set with the primary key or throws a {@link com.liferay.osb.NoSuchAssetRecommendationSetException} if it could not be found.
	 *
	 * @param assetRecommendationSetId the primary key of the asset recommendation set
	 * @return the asset recommendation set
	 * @throws com.liferay.osb.NoSuchAssetRecommendationSetException if a asset recommendation set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetRecommendationSet findByPrimaryKey(
		long assetRecommendationSetId)
		throws NoSuchAssetRecommendationSetException, SystemException {
		AssetRecommendationSet assetRecommendationSet = fetchByPrimaryKey(assetRecommendationSetId);

		if (assetRecommendationSet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assetRecommendationSetId);
			}

			throw new NoSuchAssetRecommendationSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetRecommendationSetId);
		}

		return assetRecommendationSet;
	}

	/**
	 * Returns the asset recommendation set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset recommendation set
	 * @return the asset recommendation set, or <code>null</code> if a asset recommendation set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetRecommendationSet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset recommendation set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetRecommendationSetId the primary key of the asset recommendation set
	 * @return the asset recommendation set, or <code>null</code> if a asset recommendation set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetRecommendationSet fetchByPrimaryKey(
		long assetRecommendationSetId) throws SystemException {
		AssetRecommendationSet assetRecommendationSet = (AssetRecommendationSet)EntityCacheUtil.getResult(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
				AssetRecommendationSetImpl.class, assetRecommendationSetId);

		if (assetRecommendationSet == _nullAssetRecommendationSet) {
			return null;
		}

		if (assetRecommendationSet == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetRecommendationSet = (AssetRecommendationSet)session.get(AssetRecommendationSetImpl.class,
						Long.valueOf(assetRecommendationSetId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetRecommendationSet != null) {
					cacheResult(assetRecommendationSet);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetRecommendationSetModelImpl.ENTITY_CACHE_ENABLED,
						AssetRecommendationSetImpl.class,
						assetRecommendationSetId, _nullAssetRecommendationSet);
				}

				closeSession(session);
			}
		}

		return assetRecommendationSet;
	}

	/**
	 * Returns the asset recommendation set where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.osb.NoSuchAssetRecommendationSetException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset recommendation set
	 * @throws com.liferay.osb.NoSuchAssetRecommendationSetException if a matching asset recommendation set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetRecommendationSet findByC_C(long classNameId, long classPK)
		throws NoSuchAssetRecommendationSetException, SystemException {
		AssetRecommendationSet assetRecommendationSet = fetchByC_C(classNameId,
				classPK);

		if (assetRecommendationSet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetRecommendationSetException(msg.toString());
		}

		return assetRecommendationSet;
	}

	/**
	 * Returns the asset recommendation set where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset recommendation set, or <code>null</code> if a matching asset recommendation set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetRecommendationSet fetchByC_C(long classNameId, long classPK)
		throws SystemException {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the asset recommendation set where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset recommendation set, or <code>null</code> if a matching asset recommendation set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetRecommendationSet fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result instanceof AssetRecommendationSet) {
			AssetRecommendationSet assetRecommendationSet = (AssetRecommendationSet)result;

			if ((classNameId != assetRecommendationSet.getClassNameId()) ||
					(classPK != assetRecommendationSet.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ASSETRECOMMENDATIONSET_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<AssetRecommendationSet> list = q.list();

				result = list;

				AssetRecommendationSet assetRecommendationSet = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, list);
				}
				else {
					assetRecommendationSet = list.get(0);

					cacheResult(assetRecommendationSet);

					if ((assetRecommendationSet.getClassNameId() != classNameId) ||
							(assetRecommendationSet.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, assetRecommendationSet);
					}
				}

				return assetRecommendationSet;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
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
				return (AssetRecommendationSet)result;
			}
		}
	}

	/**
	 * Returns all the asset recommendation sets.
	 *
	 * @return the asset recommendation sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetRecommendationSet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset recommendation sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset recommendation sets
	 * @param end the upper bound of the range of asset recommendation sets (not inclusive)
	 * @return the range of asset recommendation sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetRecommendationSet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset recommendation sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset recommendation sets
	 * @param end the upper bound of the range of asset recommendation sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset recommendation sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetRecommendationSet> findAll(int start, int end,
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

		List<AssetRecommendationSet> list = (List<AssetRecommendationSet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETRECOMMENDATIONSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETRECOMMENDATIONSET;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetRecommendationSet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetRecommendationSet>)QueryUtil.list(q,
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
	 * Removes the asset recommendation set where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the asset recommendation set that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetRecommendationSet removeByC_C(long classNameId, long classPK)
		throws NoSuchAssetRecommendationSetException, SystemException {
		AssetRecommendationSet assetRecommendationSet = findByC_C(classNameId,
				classPK);

		return remove(assetRecommendationSet);
	}

	/**
	 * Removes all the asset recommendation sets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetRecommendationSet assetRecommendationSet : findAll()) {
			remove(assetRecommendationSet);
		}
	}

	/**
	 * Returns the number of asset recommendation sets where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset recommendation sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETRECOMMENDATIONSET_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset recommendation sets.
	 *
	 * @return the number of asset recommendation sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETRECOMMENDATIONSET);

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
	 * Initializes the asset recommendation set persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetRecommendationSet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetRecommendationSet>> listenersList = new ArrayList<ModelListener<AssetRecommendationSet>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetRecommendationSet>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetRecommendationSetImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETRECOMMENDATIONSET = "SELECT assetRecommendationSet FROM AssetRecommendationSet assetRecommendationSet";
	private static final String _SQL_SELECT_ASSETRECOMMENDATIONSET_WHERE = "SELECT assetRecommendationSet FROM AssetRecommendationSet assetRecommendationSet WHERE ";
	private static final String _SQL_COUNT_ASSETRECOMMENDATIONSET = "SELECT COUNT(assetRecommendationSet) FROM AssetRecommendationSet assetRecommendationSet";
	private static final String _SQL_COUNT_ASSETRECOMMENDATIONSET_WHERE = "SELECT COUNT(assetRecommendationSet) FROM AssetRecommendationSet assetRecommendationSet WHERE ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetRecommendationSet.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetRecommendationSet.classPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetRecommendationSet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetRecommendationSet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetRecommendationSet exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetRecommendationSetPersistenceImpl.class);
	private static AssetRecommendationSet _nullAssetRecommendationSet = new AssetRecommendationSetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetRecommendationSet> toCacheModel() {
				return _nullAssetRecommendationSetCacheModel;
			}
		};

	private static CacheModel<AssetRecommendationSet> _nullAssetRecommendationSetCacheModel =
		new CacheModel<AssetRecommendationSet>() {
			public AssetRecommendationSet toEntityModel() {
				return _nullAssetRecommendationSet;
			}
		};
}
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

import com.liferay.osb.NoSuchAssetListException;
import com.liferay.osb.model.AssetList;
import com.liferay.osb.model.impl.AssetListImpl;
import com.liferay.osb.model.impl.AssetListModelImpl;

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
 * The persistence implementation for the asset list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListPersistence
 * @see AssetListUtil
 * @generated
 */
public class AssetListPersistenceImpl extends BasePersistenceImpl<AssetList>
	implements AssetListPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetListUtil} to access the asset list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetListImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETCATEGORYID =
		new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, AssetListImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetCategoryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETCATEGORYID =
		new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, AssetListImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetCategoryId",
			new String[] { Long.class.getName() },
			AssetListModelImpl.ASSETCATEGORYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETCATEGORYID = new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAssetCategoryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_ACI_T = new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, AssetListImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByACI_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AssetListModelImpl.ASSETCATEGORYID_COLUMN_BITMASK |
			AssetListModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACI_T = new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByACI_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, AssetListImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, AssetListImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset list in the entity cache if it is enabled.
	 *
	 * @param assetList the asset list
	 */
	public void cacheResult(AssetList assetList) {
		EntityCacheUtil.putResult(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListImpl.class, assetList.getPrimaryKey(), assetList);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACI_T,
			new Object[] {
				Long.valueOf(assetList.getAssetCategoryId()),
				Integer.valueOf(assetList.getType())
			}, assetList);

		assetList.resetOriginalValues();
	}

	/**
	 * Caches the asset lists in the entity cache if it is enabled.
	 *
	 * @param assetLists the asset lists
	 */
	public void cacheResult(List<AssetList> assetLists) {
		for (AssetList assetList : assetLists) {
			if (EntityCacheUtil.getResult(
						AssetListModelImpl.ENTITY_CACHE_ENABLED,
						AssetListImpl.class, assetList.getPrimaryKey()) == null) {
				cacheResult(assetList);
			}
			else {
				assetList.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset lists.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetListImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetListImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset list.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetList assetList) {
		EntityCacheUtil.removeResult(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListImpl.class, assetList.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetList);
	}

	@Override
	public void clearCache(List<AssetList> assetLists) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetList assetList : assetLists) {
			EntityCacheUtil.removeResult(AssetListModelImpl.ENTITY_CACHE_ENABLED,
				AssetListImpl.class, assetList.getPrimaryKey());

			clearUniqueFindersCache(assetList);
		}
	}

	protected void cacheUniqueFindersCache(AssetList assetList) {
		if (assetList.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(assetList.getAssetCategoryId()),
					Integer.valueOf(assetList.getType())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACI_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACI_T, args,
				assetList);
		}
		else {
			AssetListModelImpl assetListModelImpl = (AssetListModelImpl)assetList;

			if ((assetListModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ACI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetList.getAssetCategoryId()),
						Integer.valueOf(assetList.getType())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACI_T, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACI_T, args,
					assetList);
			}
		}
	}

	protected void clearUniqueFindersCache(AssetList assetList) {
		AssetListModelImpl assetListModelImpl = (AssetListModelImpl)assetList;

		Object[] args = new Object[] {
				Long.valueOf(assetList.getAssetCategoryId()),
				Integer.valueOf(assetList.getType())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACI_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACI_T, args);

		if ((assetListModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ACI_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(assetListModelImpl.getOriginalAssetCategoryId()),
					Integer.valueOf(assetListModelImpl.getOriginalType())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACI_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACI_T, args);
		}
	}

	/**
	 * Creates a new asset list with the primary key. Does not add the asset list to the database.
	 *
	 * @param assetListId the primary key for the new asset list
	 * @return the new asset list
	 */
	public AssetList create(long assetListId) {
		AssetList assetList = new AssetListImpl();

		assetList.setNew(true);
		assetList.setPrimaryKey(assetListId);

		return assetList;
	}

	/**
	 * Removes the asset list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListId the primary key of the asset list
	 * @return the asset list that was removed
	 * @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList remove(long assetListId)
		throws NoSuchAssetListException, SystemException {
		return remove(Long.valueOf(assetListId));
	}

	/**
	 * Removes the asset list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset list
	 * @return the asset list that was removed
	 * @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetList remove(Serializable primaryKey)
		throws NoSuchAssetListException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetList assetList = (AssetList)session.get(AssetListImpl.class,
					primaryKey);

			if (assetList == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetList);
		}
		catch (NoSuchAssetListException nsee) {
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
	protected AssetList removeImpl(AssetList assetList)
		throws SystemException {
		assetList = toUnwrappedModel(assetList);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetList);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetList);

		return assetList;
	}

	@Override
	public AssetList updateImpl(com.liferay.osb.model.AssetList assetList,
		boolean merge) throws SystemException {
		assetList = toUnwrappedModel(assetList);

		boolean isNew = assetList.isNew();

		AssetListModelImpl assetListModelImpl = (AssetListModelImpl)assetList;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetList, merge);

			assetList.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetListModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetListModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETCATEGORYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetListModelImpl.getOriginalAssetCategoryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETCATEGORYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETCATEGORYID,
					args);

				args = new Object[] {
						Long.valueOf(assetListModelImpl.getAssetCategoryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETCATEGORYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETCATEGORYID,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetListModelImpl.ENTITY_CACHE_ENABLED,
			AssetListImpl.class, assetList.getPrimaryKey(), assetList);

		clearUniqueFindersCache(assetList);
		cacheUniqueFindersCache(assetList);

		return assetList;
	}

	protected AssetList toUnwrappedModel(AssetList assetList) {
		if (assetList instanceof AssetListImpl) {
			return assetList;
		}

		AssetListImpl assetListImpl = new AssetListImpl();

		assetListImpl.setNew(assetList.isNew());
		assetListImpl.setPrimaryKey(assetList.getPrimaryKey());

		assetListImpl.setAssetListId(assetList.getAssetListId());
		assetListImpl.setAssetCategoryId(assetList.getAssetCategoryId());
		assetListImpl.setType(assetList.getType());

		return assetListImpl;
	}

	/**
	 * Returns the asset list with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset list
	 * @return the asset list
	 * @throws com.liferay.portal.NoSuchModelException if a asset list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetList findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset list with the primary key or throws a {@link com.liferay.osb.NoSuchAssetListException} if it could not be found.
	 *
	 * @param assetListId the primary key of the asset list
	 * @return the asset list
	 * @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList findByPrimaryKey(long assetListId)
		throws NoSuchAssetListException, SystemException {
		AssetList assetList = fetchByPrimaryKey(assetListId);

		if (assetList == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetListId);
			}

			throw new NoSuchAssetListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetListId);
		}

		return assetList;
	}

	/**
	 * Returns the asset list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset list
	 * @return the asset list, or <code>null</code> if a asset list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetList fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetListId the primary key of the asset list
	 * @return the asset list, or <code>null</code> if a asset list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList fetchByPrimaryKey(long assetListId)
		throws SystemException {
		AssetList assetList = (AssetList)EntityCacheUtil.getResult(AssetListModelImpl.ENTITY_CACHE_ENABLED,
				AssetListImpl.class, assetListId);

		if (assetList == _nullAssetList) {
			return null;
		}

		if (assetList == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetList = (AssetList)session.get(AssetListImpl.class,
						Long.valueOf(assetListId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetList != null) {
					cacheResult(assetList);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetListModelImpl.ENTITY_CACHE_ENABLED,
						AssetListImpl.class, assetListId, _nullAssetList);
				}

				closeSession(session);
			}
		}

		return assetList;
	}

	/**
	 * Returns all the asset lists where assetCategoryId = &#63;.
	 *
	 * @param assetCategoryId the asset category ID
	 * @return the matching asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetList> findByAssetCategoryId(long assetCategoryId)
		throws SystemException {
		return findByAssetCategoryId(assetCategoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset lists where assetCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetCategoryId the asset category ID
	 * @param start the lower bound of the range of asset lists
	 * @param end the upper bound of the range of asset lists (not inclusive)
	 * @return the range of matching asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetList> findByAssetCategoryId(long assetCategoryId,
		int start, int end) throws SystemException {
		return findByAssetCategoryId(assetCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset lists where assetCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetCategoryId the asset category ID
	 * @param start the lower bound of the range of asset lists
	 * @param end the upper bound of the range of asset lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetList> findByAssetCategoryId(long assetCategoryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETCATEGORYID;
			finderArgs = new Object[] { assetCategoryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETCATEGORYID;
			finderArgs = new Object[] {
					assetCategoryId,
					
					start, end, orderByComparator
				};
		}

		List<AssetList> list = (List<AssetList>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetList assetList : list) {
				if ((assetCategoryId != assetList.getAssetCategoryId())) {
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

			query.append(_SQL_SELECT_ASSETLIST_WHERE);

			query.append(_FINDER_COLUMN_ASSETCATEGORYID_ASSETCATEGORYID_2);

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

				qPos.add(assetCategoryId);

				list = (List<AssetList>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first asset list in the ordered set where assetCategoryId = &#63;.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list
	 * @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList findByAssetCategoryId_First(long assetCategoryId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListException, SystemException {
		AssetList assetList = fetchByAssetCategoryId_First(assetCategoryId,
				orderByComparator);

		if (assetList != null) {
			return assetList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetCategoryId=");
		msg.append(assetCategoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetListException(msg.toString());
	}

	/**
	 * Returns the first asset list in the ordered set where assetCategoryId = &#63;.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset list, or <code>null</code> if a matching asset list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList fetchByAssetCategoryId_First(long assetCategoryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetList> list = findByAssetCategoryId(assetCategoryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset list in the ordered set where assetCategoryId = &#63;.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list
	 * @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList findByAssetCategoryId_Last(long assetCategoryId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetListException, SystemException {
		AssetList assetList = fetchByAssetCategoryId_Last(assetCategoryId,
				orderByComparator);

		if (assetList != null) {
			return assetList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetCategoryId=");
		msg.append(assetCategoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetListException(msg.toString());
	}

	/**
	 * Returns the last asset list in the ordered set where assetCategoryId = &#63;.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset list, or <code>null</code> if a matching asset list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList fetchByAssetCategoryId_Last(long assetCategoryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetCategoryId(assetCategoryId);

		List<AssetList> list = findByAssetCategoryId(assetCategoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset lists before and after the current asset list in the ordered set where assetCategoryId = &#63;.
	 *
	 * @param assetListId the primary key of the current asset list
	 * @param assetCategoryId the asset category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset list
	 * @throws com.liferay.osb.NoSuchAssetListException if a asset list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList[] findByAssetCategoryId_PrevAndNext(long assetListId,
		long assetCategoryId, OrderByComparator orderByComparator)
		throws NoSuchAssetListException, SystemException {
		AssetList assetList = findByPrimaryKey(assetListId);

		Session session = null;

		try {
			session = openSession();

			AssetList[] array = new AssetListImpl[3];

			array[0] = getByAssetCategoryId_PrevAndNext(session, assetList,
					assetCategoryId, orderByComparator, true);

			array[1] = assetList;

			array[2] = getByAssetCategoryId_PrevAndNext(session, assetList,
					assetCategoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetList getByAssetCategoryId_PrevAndNext(Session session,
		AssetList assetList, long assetCategoryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETLIST_WHERE);

		query.append(_FINDER_COLUMN_ASSETCATEGORYID_ASSETCATEGORYID_2);

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

		qPos.add(assetCategoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetList);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetList> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the asset list where assetCategoryId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAssetListException} if it could not be found.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param type the type
	 * @return the matching asset list
	 * @throws com.liferay.osb.NoSuchAssetListException if a matching asset list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList findByACI_T(long assetCategoryId, int type)
		throws NoSuchAssetListException, SystemException {
		AssetList assetList = fetchByACI_T(assetCategoryId, type);

		if (assetList == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assetCategoryId=");
			msg.append(assetCategoryId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetListException(msg.toString());
		}

		return assetList;
	}

	/**
	 * Returns the asset list where assetCategoryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param type the type
	 * @return the matching asset list, or <code>null</code> if a matching asset list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList fetchByACI_T(long assetCategoryId, int type)
		throws SystemException {
		return fetchByACI_T(assetCategoryId, type, true);
	}

	/**
	 * Returns the asset list where assetCategoryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param type the type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset list, or <code>null</code> if a matching asset list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList fetchByACI_T(long assetCategoryId, int type,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { assetCategoryId, type };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ACI_T,
					finderArgs, this);
		}

		if (result instanceof AssetList) {
			AssetList assetList = (AssetList)result;

			if ((assetCategoryId != assetList.getAssetCategoryId()) ||
					(type != assetList.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ASSETLIST_WHERE);

			query.append(_FINDER_COLUMN_ACI_T_ASSETCATEGORYID_2);

			query.append(_FINDER_COLUMN_ACI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetCategoryId);

				qPos.add(type);

				List<AssetList> list = q.list();

				result = list;

				AssetList assetList = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACI_T,
						finderArgs, list);
				}
				else {
					assetList = list.get(0);

					cacheResult(assetList);

					if ((assetList.getAssetCategoryId() != assetCategoryId) ||
							(assetList.getType() != type)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACI_T,
							finderArgs, assetList);
					}
				}

				return assetList;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACI_T,
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
				return (AssetList)result;
			}
		}
	}

	/**
	 * Returns all the asset lists.
	 *
	 * @return the asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetList> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset lists
	 * @param end the upper bound of the range of asset lists (not inclusive)
	 * @return the range of asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetList> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset lists
	 * @param end the upper bound of the range of asset lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetList> findAll(int start, int end,
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

		List<AssetList> list = (List<AssetList>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETLIST;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetList>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetList>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the asset lists where assetCategoryId = &#63; from the database.
	 *
	 * @param assetCategoryId the asset category ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssetCategoryId(long assetCategoryId)
		throws SystemException {
		for (AssetList assetList : findByAssetCategoryId(assetCategoryId)) {
			remove(assetList);
		}
	}

	/**
	 * Removes the asset list where assetCategoryId = &#63; and type = &#63; from the database.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param type the type
	 * @return the asset list that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetList removeByACI_T(long assetCategoryId, int type)
		throws NoSuchAssetListException, SystemException {
		AssetList assetList = findByACI_T(assetCategoryId, type);

		return remove(assetList);
	}

	/**
	 * Removes all the asset lists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetList assetList : findAll()) {
			remove(assetList);
		}
	}

	/**
	 * Returns the number of asset lists where assetCategoryId = &#63;.
	 *
	 * @param assetCategoryId the asset category ID
	 * @return the number of matching asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssetCategoryId(long assetCategoryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetCategoryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSETCATEGORYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETLIST_WHERE);

			query.append(_FINDER_COLUMN_ASSETCATEGORYID_ASSETCATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetCategoryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSETCATEGORYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset lists where assetCategoryId = &#63; and type = &#63;.
	 *
	 * @param assetCategoryId the asset category ID
	 * @param type the type
	 * @return the number of matching asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByACI_T(long assetCategoryId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetCategoryId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLIST_WHERE);

			query.append(_FINDER_COLUMN_ACI_T_ASSETCATEGORYID_2);

			query.append(_FINDER_COLUMN_ACI_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetCategoryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset lists.
	 *
	 * @return the number of asset lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETLIST);

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
	 * Initializes the asset list persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetList")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetList>> listenersList = new ArrayList<ModelListener<AssetList>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetList>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetListImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETLIST = "SELECT assetList FROM AssetList assetList";
	private static final String _SQL_SELECT_ASSETLIST_WHERE = "SELECT assetList FROM AssetList assetList WHERE ";
	private static final String _SQL_COUNT_ASSETLIST = "SELECT COUNT(assetList) FROM AssetList assetList";
	private static final String _SQL_COUNT_ASSETLIST_WHERE = "SELECT COUNT(assetList) FROM AssetList assetList WHERE ";
	private static final String _FINDER_COLUMN_ASSETCATEGORYID_ASSETCATEGORYID_2 =
		"assetList.assetCategoryId = ?";
	private static final String _FINDER_COLUMN_ACI_T_ASSETCATEGORYID_2 = "assetList.assetCategoryId = ? AND ";
	private static final String _FINDER_COLUMN_ACI_T_TYPE_2 = "assetList.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetList exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetList exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetListPersistenceImpl.class);
	private static AssetList _nullAssetList = new AssetListImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetList> toCacheModel() {
				return _nullAssetListCacheModel;
			}
		};

	private static CacheModel<AssetList> _nullAssetListCacheModel = new CacheModel<AssetList>() {
			public AssetList toEntityModel() {
				return _nullAssetList;
			}
		};
}
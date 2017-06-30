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

import com.liferay.osb.NoSuchLicenseKeySetException;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.model.impl.LicenseKeySetImpl;
import com.liferay.osb.model.impl.LicenseKeySetModelImpl;

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
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the license key set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetPersistence
 * @see LicenseKeySetUtil
 * @generated
 */
public class LicenseKeySetPersistenceImpl extends BasePersistenceImpl<LicenseKeySet>
	implements LicenseKeySetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LicenseKeySetUtil} to access the license key set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LicenseKeySetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEntryId", new String[] { Long.class.getName() },
			LicenseKeySetModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_N = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N =
		new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			LicenseKeySetModelImpl.USERID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI_N = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the license key set in the entity cache if it is enabled.
	 *
	 * @param licenseKeySet the license key set
	 */
	public void cacheResult(LicenseKeySet licenseKeySet) {
		EntityCacheUtil.putResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey(),
			licenseKeySet);

		licenseKeySet.resetOriginalValues();
	}

	/**
	 * Caches the license key sets in the entity cache if it is enabled.
	 *
	 * @param licenseKeySets the license key sets
	 */
	public void cacheResult(List<LicenseKeySet> licenseKeySets) {
		for (LicenseKeySet licenseKeySet : licenseKeySets) {
			if (EntityCacheUtil.getResult(
						LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey()) == null) {
				cacheResult(licenseKeySet);
			}
			else {
				licenseKeySet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license key sets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LicenseKeySetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LicenseKeySetImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license key set.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseKeySet licenseKeySet) {
		EntityCacheUtil.removeResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LicenseKeySet> licenseKeySets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseKeySet licenseKeySet : licenseKeySets) {
			EntityCacheUtil.removeResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey());
		}
	}

	/**
	 * Creates a new license key set with the primary key. Does not add the license key set to the database.
	 *
	 * @param licenseKeySetId the primary key for the new license key set
	 * @return the new license key set
	 */
	public LicenseKeySet create(long licenseKeySetId) {
		LicenseKeySet licenseKeySet = new LicenseKeySetImpl();

		licenseKeySet.setNew(true);
		licenseKeySet.setPrimaryKey(licenseKeySetId);

		return licenseKeySet;
	}

	/**
	 * Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet remove(long licenseKeySetId)
		throws NoSuchLicenseKeySetException, SystemException {
		return remove(Long.valueOf(licenseKeySetId));
	}

	/**
	 * Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseKeySet remove(Serializable primaryKey)
		throws NoSuchLicenseKeySetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			LicenseKeySet licenseKeySet = (LicenseKeySet)session.get(LicenseKeySetImpl.class,
					primaryKey);

			if (licenseKeySet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseKeySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(licenseKeySet);
		}
		catch (NoSuchLicenseKeySetException nsee) {
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
	protected LicenseKeySet removeImpl(LicenseKeySet licenseKeySet)
		throws SystemException {
		licenseKeySet = toUnwrappedModel(licenseKeySet);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, licenseKeySet);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(licenseKeySet);

		return licenseKeySet;
	}

	@Override
	public LicenseKeySet updateImpl(
		com.liferay.osb.model.LicenseKeySet licenseKeySet, boolean merge)
		throws SystemException {
		licenseKeySet = toUnwrappedModel(licenseKeySet);

		boolean isNew = licenseKeySet.isNew();

		LicenseKeySetModelImpl licenseKeySetModelImpl = (LicenseKeySetModelImpl)licenseKeySet;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, licenseKeySet, merge);

			licenseKeySet.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LicenseKeySetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((licenseKeySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeySetModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeySetModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((licenseKeySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeySetModelImpl.getOriginalUserId()),
						Long.valueOf(licenseKeySetModelImpl.getOriginalAccountEntryId()),
						
						licenseKeySetModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeySetModelImpl.getUserId()),
						Long.valueOf(licenseKeySetModelImpl.getAccountEntryId()),
						
						licenseKeySetModelImpl.getName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N,
					args);
			}
		}

		EntityCacheUtil.putResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey(),
			licenseKeySet);

		return licenseKeySet;
	}

	protected LicenseKeySet toUnwrappedModel(LicenseKeySet licenseKeySet) {
		if (licenseKeySet instanceof LicenseKeySetImpl) {
			return licenseKeySet;
		}

		LicenseKeySetImpl licenseKeySetImpl = new LicenseKeySetImpl();

		licenseKeySetImpl.setNew(licenseKeySet.isNew());
		licenseKeySetImpl.setPrimaryKey(licenseKeySet.getPrimaryKey());

		licenseKeySetImpl.setLicenseKeySetId(licenseKeySet.getLicenseKeySetId());
		licenseKeySetImpl.setUserId(licenseKeySet.getUserId());
		licenseKeySetImpl.setUserName(licenseKeySet.getUserName());
		licenseKeySetImpl.setCreateDate(licenseKeySet.getCreateDate());
		licenseKeySetImpl.setModifiedDate(licenseKeySet.getModifiedDate());
		licenseKeySetImpl.setAccountEntryId(licenseKeySet.getAccountEntryId());
		licenseKeySetImpl.setName(licenseKeySet.getName());

		return licenseKeySetImpl;
	}

	/**
	 * Returns the license key set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set
	 * @throws com.liferay.portal.NoSuchModelException if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseKeySet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the license key set with the primary key or throws a {@link com.liferay.osb.NoSuchLicenseKeySetException} if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet findByPrimaryKey(long licenseKeySetId)
		throws NoSuchLicenseKeySetException, SystemException {
		LicenseKeySet licenseKeySet = fetchByPrimaryKey(licenseKeySetId);

		if (licenseKeySet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + licenseKeySetId);
			}

			throw new NoSuchLicenseKeySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				licenseKeySetId);
		}

		return licenseKeySet;
	}

	/**
	 * Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseKeySet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet fetchByPrimaryKey(long licenseKeySetId)
		throws SystemException {
		LicenseKeySet licenseKeySet = (LicenseKeySet)EntityCacheUtil.getResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeySetImpl.class, licenseKeySetId);

		if (licenseKeySet == _nullLicenseKeySet) {
			return null;
		}

		if (licenseKeySet == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				licenseKeySet = (LicenseKeySet)session.get(LicenseKeySetImpl.class,
						Long.valueOf(licenseKeySetId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (licenseKeySet != null) {
					cacheResult(licenseKeySet);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeySetImpl.class, licenseKeySetId,
						_nullLicenseKeySet);
				}

				closeSession(session);
			}
		}

		return licenseKeySet;
	}

	/**
	 * Returns all the license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] { accountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] {
					accountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKeySet> list = (List<LicenseKeySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKeySet licenseKeySet : list) {
				if ((accountEntryId != licenseKeySet.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeySetException, SystemException {
		LicenseKeySet licenseKeySet = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKeySet> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeySetException, SystemException {
		LicenseKeySet licenseKeySet = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<LicenseKeySet> list = findByAccountEntryId(accountEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet[] findByAccountEntryId_PrevAndNext(
		long licenseKeySetId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeySetException, SystemException {
		LicenseKeySet licenseKeySet = findByPrimaryKey(licenseKeySetId);

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet[] array = new LicenseKeySetImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, licenseKeySet,
					accountEntryId, orderByComparator, true);

			array[1] = licenseKeySet;

			array[2] = getByAccountEntryId_PrevAndNext(session, licenseKeySet,
					accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKeySet getByAccountEntryId_PrevAndNext(Session session,
		LicenseKeySet licenseKeySet, long accountEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

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
			query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKeySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKeySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findByU_AEI_N(long userId, long accountEntryId,
		String name) throws SystemException {
		return findByU_AEI_N(userId, accountEntryId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findByU_AEI_N(long userId, long accountEntryId,
		String name, int start, int end) throws SystemException {
		return findByU_AEI_N(userId, accountEntryId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findByU_AEI_N(long userId, long accountEntryId,
		String name, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI_N;
			finderArgs = new Object[] { userId, accountEntryId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI_N;
			finderArgs = new Object[] {
					userId, accountEntryId, name,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKeySet> list = (List<LicenseKeySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKeySet licenseKeySet : list) {
				if ((userId != licenseKeySet.getUserId()) ||
						(accountEntryId != licenseKeySet.getAccountEntryId()) ||
						!Validator.equals(name, licenseKeySet.getName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet findByU_AEI_N_First(long userId, long accountEntryId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeySetException, SystemException {
		LicenseKeySet licenseKeySet = fetchByU_AEI_N_First(userId,
				accountEntryId, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet fetchByU_AEI_N_First(long userId, long accountEntryId,
		String name, OrderByComparator orderByComparator)
		throws SystemException {
		List<LicenseKeySet> list = findByU_AEI_N(userId, accountEntryId, name,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet findByU_AEI_N_Last(long userId, long accountEntryId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeySetException, SystemException {
		LicenseKeySet licenseKeySet = fetchByU_AEI_N_Last(userId,
				accountEntryId, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeySetException(msg.toString());
	}

	/**
	 * Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet fetchByU_AEI_N_Last(long userId, long accountEntryId,
		String name, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByU_AEI_N(userId, accountEntryId, name);

		List<LicenseKeySet> list = findByU_AEI_N(userId, accountEntryId, name,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws com.liferay.osb.NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKeySet[] findByU_AEI_N_PrevAndNext(long licenseKeySetId,
		long userId, long accountEntryId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeySetException, SystemException {
		LicenseKeySet licenseKeySet = findByPrimaryKey(licenseKeySetId);

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet[] array = new LicenseKeySetImpl[3];

			array[0] = getByU_AEI_N_PrevAndNext(session, licenseKeySet, userId,
					accountEntryId, name, orderByComparator, true);

			array[1] = licenseKeySet;

			array[2] = getByU_AEI_N_PrevAndNext(session, licenseKeySet, userId,
					accountEntryId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKeySet getByU_AEI_N_PrevAndNext(Session session,
		LicenseKeySet licenseKeySet, long userId, long accountEntryId,
		String name, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

		query.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

		query.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_U_AEI_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
			}
		}

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
			query.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(accountEntryId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKeySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKeySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license key sets.
	 *
	 * @return the license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKeySet> findAll(int start, int end,
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

		List<LicenseKeySet> list = (List<LicenseKeySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LICENSEKEYSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEKEYSET.concat(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<LicenseKeySet>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the license key sets where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (LicenseKeySet licenseKeySet : findByAccountEntryId(accountEntryId)) {
			remove(licenseKeySet);
		}
	}

	/**
	 * Removes all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_AEI_N(long userId, long accountEntryId, String name)
		throws SystemException {
		for (LicenseKeySet licenseKeySet : findByU_AEI_N(userId,
				accountEntryId, name)) {
			remove(licenseKeySet);
		}
	}

	/**
	 * Removes all the license key sets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (LicenseKeySet licenseKeySet : findAll()) {
			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEYSET_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the number of matching license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_AEI_N(long userId, long accountEntryId, String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, accountEntryId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_AEI_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEYSET_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_U_AEI_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI_N,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license key sets.
	 *
	 * @return the number of license key sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LICENSEKEYSET);

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
	 * Initializes the license key set persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.LicenseKeySet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<LicenseKeySet>> listenersList = new ArrayList<ModelListener<LicenseKeySet>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<LicenseKeySet>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(LicenseKeySetImpl.class.getName());
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
	private static final String _SQL_SELECT_LICENSEKEYSET = "SELECT licenseKeySet FROM LicenseKeySet licenseKeySet";
	private static final String _SQL_SELECT_LICENSEKEYSET_WHERE = "SELECT licenseKeySet FROM LicenseKeySet licenseKeySet WHERE ";
	private static final String _SQL_COUNT_LICENSEKEYSET = "SELECT COUNT(licenseKeySet) FROM LicenseKeySet licenseKeySet";
	private static final String _SQL_COUNT_LICENSEKEYSET_WHERE = "SELECT COUNT(licenseKeySet) FROM LicenseKeySet licenseKeySet WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "licenseKeySet.accountEntryId = ?";
	private static final String _FINDER_COLUMN_U_AEI_N_USERID_2 = "licenseKeySet.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2 = "licenseKeySet.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_N_NAME_1 = "licenseKeySet.name IS NULL";
	private static final String _FINDER_COLUMN_U_AEI_N_NAME_2 = "licenseKeySet.name = ?";
	private static final String _FINDER_COLUMN_U_AEI_N_NAME_3 = "(licenseKeySet.name IS NULL OR licenseKeySet.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseKeySet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LicenseKeySet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LicenseKeySet exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(LicenseKeySetPersistenceImpl.class);
	private static LicenseKeySet _nullLicenseKeySet = new LicenseKeySetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<LicenseKeySet> toCacheModel() {
				return _nullLicenseKeySetCacheModel;
			}
		};

	private static CacheModel<LicenseKeySet> _nullLicenseKeySetCacheModel = new CacheModel<LicenseKeySet>() {
			public LicenseKeySet toEntityModel() {
				return _nullLicenseKeySet;
			}
		};
}
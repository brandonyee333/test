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

import com.liferay.osb.NoSuchAppAuditException;
import com.liferay.osb.model.AppAudit;
import com.liferay.osb.model.impl.AppAuditImpl;
import com.liferay.osb.model.impl.AppAuditModelImpl;

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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
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
 * The persistence implementation for the app audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppAuditPersistence
 * @see AppAuditUtil
 * @generated
 */
public class AppAuditPersistenceImpl extends BasePersistenceImpl<AppAudit>
	implements AppAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppAuditUtil} to access the app audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, AppAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, AppAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AppAuditModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, AppAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppVersionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID =
		new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, AppAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppVersionId",
			new String[] { Long.class.getName() },
			AppAuditModelImpl.APPVERSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPVERSIONID = new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppVersionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, AppAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, AppAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app audit in the entity cache if it is enabled.
	 *
	 * @param appAudit the app audit
	 */
	public void cacheResult(AppAudit appAudit) {
		EntityCacheUtil.putResult(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditImpl.class, appAudit.getPrimaryKey(), appAudit);

		appAudit.resetOriginalValues();
	}

	/**
	 * Caches the app audits in the entity cache if it is enabled.
	 *
	 * @param appAudits the app audits
	 */
	public void cacheResult(List<AppAudit> appAudits) {
		for (AppAudit appAudit : appAudits) {
			if (EntityCacheUtil.getResult(
						AppAuditModelImpl.ENTITY_CACHE_ENABLED,
						AppAuditImpl.class, appAudit.getPrimaryKey()) == null) {
				cacheResult(appAudit);
			}
			else {
				appAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app audits.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppAuditImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppAuditImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app audit.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppAudit appAudit) {
		EntityCacheUtil.removeResult(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditImpl.class, appAudit.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AppAudit> appAudits) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppAudit appAudit : appAudits) {
			EntityCacheUtil.removeResult(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
				AppAuditImpl.class, appAudit.getPrimaryKey());
		}
	}

	/**
	 * Creates a new app audit with the primary key. Does not add the app audit to the database.
	 *
	 * @param appAuditId the primary key for the new app audit
	 * @return the new app audit
	 */
	public AppAudit create(long appAuditId) {
		AppAudit appAudit = new AppAuditImpl();

		appAudit.setNew(true);
		appAudit.setPrimaryKey(appAuditId);

		String uuid = PortalUUIDUtil.generate();

		appAudit.setUuid(uuid);

		return appAudit;
	}

	/**
	 * Removes the app audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appAuditId the primary key of the app audit
	 * @return the app audit that was removed
	 * @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit remove(long appAuditId)
		throws NoSuchAppAuditException, SystemException {
		return remove(Long.valueOf(appAuditId));
	}

	/**
	 * Removes the app audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app audit
	 * @return the app audit that was removed
	 * @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppAudit remove(Serializable primaryKey)
		throws NoSuchAppAuditException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppAudit appAudit = (AppAudit)session.get(AppAuditImpl.class,
					primaryKey);

			if (appAudit == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appAudit);
		}
		catch (NoSuchAppAuditException nsee) {
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
	protected AppAudit removeImpl(AppAudit appAudit) throws SystemException {
		appAudit = toUnwrappedModel(appAudit);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appAudit);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appAudit);

		return appAudit;
	}

	@Override
	public AppAudit updateImpl(com.liferay.osb.model.AppAudit appAudit,
		boolean merge) throws SystemException {
		appAudit = toUnwrappedModel(appAudit);

		boolean isNew = appAudit.isNew();

		AppAuditModelImpl appAuditModelImpl = (AppAuditModelImpl)appAudit;

		if (Validator.isNull(appAudit.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			appAudit.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appAudit, merge);

			appAudit.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppAuditModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { appAuditModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { appAuditModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((appAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appAuditModelImpl.getOriginalAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);

				args = new Object[] {
						Long.valueOf(appAuditModelImpl.getAppVersionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPVERSIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
			AppAuditImpl.class, appAudit.getPrimaryKey(), appAudit);

		return appAudit;
	}

	protected AppAudit toUnwrappedModel(AppAudit appAudit) {
		if (appAudit instanceof AppAuditImpl) {
			return appAudit;
		}

		AppAuditImpl appAuditImpl = new AppAuditImpl();

		appAuditImpl.setNew(appAudit.isNew());
		appAuditImpl.setPrimaryKey(appAudit.getPrimaryKey());

		appAuditImpl.setUuid(appAudit.getUuid());
		appAuditImpl.setAppAuditId(appAudit.getAppAuditId());
		appAuditImpl.setUserId(appAudit.getUserId());
		appAuditImpl.setUserName(appAudit.getUserName());
		appAuditImpl.setCreateDate(appAudit.getCreateDate());
		appAuditImpl.setAppEntryId(appAudit.getAppEntryId());
		appAuditImpl.setAppVersionId(appAudit.getAppVersionId());
		appAuditImpl.setStatus(appAudit.getStatus());

		return appAuditImpl;
	}

	/**
	 * Returns the app audit with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app audit
	 * @return the app audit
	 * @throws com.liferay.portal.NoSuchModelException if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app audit with the primary key or throws a {@link com.liferay.osb.NoSuchAppAuditException} if it could not be found.
	 *
	 * @param appAuditId the primary key of the app audit
	 * @return the app audit
	 * @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit findByPrimaryKey(long appAuditId)
		throws NoSuchAppAuditException, SystemException {
		AppAudit appAudit = fetchByPrimaryKey(appAuditId);

		if (appAudit == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appAuditId);
			}

			throw new NoSuchAppAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appAuditId);
		}

		return appAudit;
	}

	/**
	 * Returns the app audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app audit
	 * @return the app audit, or <code>null</code> if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppAudit fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appAuditId the primary key of the app audit
	 * @return the app audit, or <code>null</code> if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit fetchByPrimaryKey(long appAuditId)
		throws SystemException {
		AppAudit appAudit = (AppAudit)EntityCacheUtil.getResult(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
				AppAuditImpl.class, appAuditId);

		if (appAudit == _nullAppAudit) {
			return null;
		}

		if (appAudit == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appAudit = (AppAudit)session.get(AppAuditImpl.class,
						Long.valueOf(appAuditId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appAudit != null) {
					cacheResult(appAudit);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppAuditModelImpl.ENTITY_CACHE_ENABLED,
						AppAuditImpl.class, appAuditId, _nullAppAudit);
				}

				closeSession(session);
			}
		}

		return appAudit;
	}

	/**
	 * Returns all the app audits where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app audits where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app audits
	 * @param end the upper bound of the range of app audits (not inclusive)
	 * @return the range of matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app audits where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app audits
	 * @param end the upper bound of the range of app audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<AppAudit> list = (List<AppAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppAudit appAudit : list) {
				if (!Validator.equals(uuid, appAudit.getUuid())) {
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

			query.append(_SQL_SELECT_APPAUDIT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

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

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<AppAudit>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app audit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app audit
	 * @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppAuditException, SystemException {
		AppAudit appAudit = fetchByUuid_First(uuid, orderByComparator);

		if (appAudit != null) {
			return appAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppAuditException(msg.toString());
	}

	/**
	 * Returns the first app audit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app audit, or <code>null</code> if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppAudit> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app audit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app audit
	 * @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppAuditException, SystemException {
		AppAudit appAudit = fetchByUuid_Last(uuid, orderByComparator);

		if (appAudit != null) {
			return appAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppAuditException(msg.toString());
	}

	/**
	 * Returns the last app audit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app audit, or <code>null</code> if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<AppAudit> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app audits before and after the current app audit in the ordered set where uuid = &#63;.
	 *
	 * @param appAuditId the primary key of the current app audit
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app audit
	 * @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit[] findByUuid_PrevAndNext(long appAuditId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppAuditException, SystemException {
		AppAudit appAudit = findByPrimaryKey(appAuditId);

		Session session = null;

		try {
			session = openSession();

			AppAudit[] array = new AppAuditImpl[3];

			array[0] = getByUuid_PrevAndNext(session, appAudit, uuid,
					orderByComparator, true);

			array[1] = appAudit;

			array[2] = getByUuid_PrevAndNext(session, appAudit, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppAudit getByUuid_PrevAndNext(Session session,
		AppAudit appAudit, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPAUDIT_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app audits where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findByAppVersionId(long appVersionId)
		throws SystemException {
		return findByAppVersionId(appVersionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app audits where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of app audits
	 * @param end the upper bound of the range of app audits (not inclusive)
	 * @return the range of matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findByAppVersionId(long appVersionId, int start,
		int end) throws SystemException {
		return findByAppVersionId(appVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app audits where appVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appVersionId the app version ID
	 * @param start the lower bound of the range of app audits
	 * @param end the upper bound of the range of app audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findByAppVersionId(long appVersionId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<AppAudit> list = (List<AppAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppAudit appAudit : list) {
				if ((appVersionId != appAudit.getAppVersionId())) {
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

			query.append(_SQL_SELECT_APPAUDIT_WHERE);

			query.append(_FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2);

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

				qPos.add(appVersionId);

				list = (List<AppAudit>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first app audit in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app audit
	 * @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit findByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchAppAuditException, SystemException {
		AppAudit appAudit = fetchByAppVersionId_First(appVersionId,
				orderByComparator);

		if (appAudit != null) {
			return appAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppAuditException(msg.toString());
	}

	/**
	 * Returns the first app audit in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app audit, or <code>null</code> if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit fetchByAppVersionId_First(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppAudit> list = findByAppVersionId(appVersionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app audit in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app audit
	 * @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit findByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator)
		throws NoSuchAppAuditException, SystemException {
		AppAudit appAudit = fetchByAppVersionId_Last(appVersionId,
				orderByComparator);

		if (appAudit != null) {
			return appAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appVersionId=");
		msg.append(appVersionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppAuditException(msg.toString());
	}

	/**
	 * Returns the last app audit in the ordered set where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app audit, or <code>null</code> if a matching app audit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit fetchByAppVersionId_Last(long appVersionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAppVersionId(appVersionId);

		List<AppAudit> list = findByAppVersionId(appVersionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app audits before and after the current app audit in the ordered set where appVersionId = &#63;.
	 *
	 * @param appAuditId the primary key of the current app audit
	 * @param appVersionId the app version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app audit
	 * @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppAudit[] findByAppVersionId_PrevAndNext(long appAuditId,
		long appVersionId, OrderByComparator orderByComparator)
		throws NoSuchAppAuditException, SystemException {
		AppAudit appAudit = findByPrimaryKey(appAuditId);

		Session session = null;

		try {
			session = openSession();

			AppAudit[] array = new AppAuditImpl[3];

			array[0] = getByAppVersionId_PrevAndNext(session, appAudit,
					appVersionId, orderByComparator, true);

			array[1] = appAudit;

			array[2] = getByAppVersionId_PrevAndNext(session, appAudit,
					appVersionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppAudit getByAppVersionId_PrevAndNext(Session session,
		AppAudit appAudit, long appVersionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPAUDIT_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appVersionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app audits.
	 *
	 * @return the app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app audits
	 * @param end the upper bound of the range of app audits (not inclusive)
	 * @return the range of app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app audits
	 * @param end the upper bound of the range of app audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app audits
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppAudit> findAll(int start, int end,
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

		List<AppAudit> list = (List<AppAudit>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPAUDIT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppAudit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppAudit>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the app audits where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (AppAudit appAudit : findByUuid(uuid)) {
			remove(appAudit);
		}
	}

	/**
	 * Removes all the app audits where appVersionId = &#63; from the database.
	 *
	 * @param appVersionId the app version ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppVersionId(long appVersionId)
		throws SystemException {
		for (AppAudit appAudit : findByAppVersionId(appVersionId)) {
			remove(appAudit);
		}
	}

	/**
	 * Removes all the app audits from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppAudit appAudit : findAll()) {
			remove(appAudit);
		}
	}

	/**
	 * Returns the number of app audits where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPAUDIT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app audits where appVersionId = &#63;.
	 *
	 * @param appVersionId the app version ID
	 * @return the number of matching app audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppVersionId(long appVersionId) throws SystemException {
		Object[] finderArgs = new Object[] { appVersionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPVERSIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPAUDIT_WHERE);

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
	 * Returns the number of app audits.
	 *
	 * @return the number of app audits
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPAUDIT);

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
	 * Initializes the app audit persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppAudit")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppAudit>> listenersList = new ArrayList<ModelListener<AppAudit>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppAudit>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppAuditImpl.class.getName());
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
	private static final String _SQL_SELECT_APPAUDIT = "SELECT appAudit FROM AppAudit appAudit";
	private static final String _SQL_SELECT_APPAUDIT_WHERE = "SELECT appAudit FROM AppAudit appAudit WHERE ";
	private static final String _SQL_COUNT_APPAUDIT = "SELECT COUNT(appAudit) FROM AppAudit appAudit";
	private static final String _SQL_COUNT_APPAUDIT_WHERE = "SELECT COUNT(appAudit) FROM AppAudit appAudit WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "appAudit.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "appAudit.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(appAudit.uuid IS NULL OR appAudit.uuid = ?)";
	private static final String _FINDER_COLUMN_APPVERSIONID_APPVERSIONID_2 = "appAudit.appVersionId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppAudit exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppAudit exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppAuditPersistenceImpl.class);
	private static AppAudit _nullAppAudit = new AppAuditImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppAudit> toCacheModel() {
				return _nullAppAuditCacheModel;
			}
		};

	private static CacheModel<AppAudit> _nullAppAuditCacheModel = new CacheModel<AppAudit>() {
			public AppAudit toEntityModel() {
				return _nullAppAudit;
			}
		};
}
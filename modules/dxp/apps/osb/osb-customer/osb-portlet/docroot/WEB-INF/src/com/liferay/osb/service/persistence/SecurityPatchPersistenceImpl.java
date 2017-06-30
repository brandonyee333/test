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

import com.liferay.osb.NoSuchSecurityPatchException;
import com.liferay.osb.model.SecurityPatch;
import com.liferay.osb.model.impl.SecurityPatchImpl;
import com.liferay.osb.model.impl.SecurityPatchModelImpl;

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
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the security patch service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatchPersistence
 * @see SecurityPatchUtil
 * @generated
 */
public class SecurityPatchPersistenceImpl extends BasePersistenceImpl<SecurityPatch>
	implements SecurityPatchPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SecurityPatchUtil} to access the security patch persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SecurityPatchImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PORTLETID =
		new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPortletId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID =
		new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPortletId", new String[] { String.class.getName() },
			SecurityPatchModelImpl.PORTLETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PORTLETID = new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPortletId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_PI = new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_PI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI =
		new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAEI_PI",
			new String[] { Long.class.getName(), String.class.getName() },
			SecurityPatchModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			SecurityPatchModelImpl.PORTLETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI_PI = new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI_PI",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the security patch in the entity cache if it is enabled.
	 *
	 * @param securityPatch the security patch
	 */
	public void cacheResult(SecurityPatch securityPatch) {
		EntityCacheUtil.putResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchImpl.class, securityPatch.getPrimaryKey(),
			securityPatch);

		securityPatch.resetOriginalValues();
	}

	/**
	 * Caches the security patchs in the entity cache if it is enabled.
	 *
	 * @param securityPatchs the security patchs
	 */
	public void cacheResult(List<SecurityPatch> securityPatchs) {
		for (SecurityPatch securityPatch : securityPatchs) {
			if (EntityCacheUtil.getResult(
						SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
						SecurityPatchImpl.class, securityPatch.getPrimaryKey()) == null) {
				cacheResult(securityPatch);
			}
			else {
				securityPatch.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all security patchs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SecurityPatchImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SecurityPatchImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the security patch.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SecurityPatch securityPatch) {
		EntityCacheUtil.removeResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchImpl.class, securityPatch.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SecurityPatch> securityPatchs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SecurityPatch securityPatch : securityPatchs) {
			EntityCacheUtil.removeResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
				SecurityPatchImpl.class, securityPatch.getPrimaryKey());
		}
	}

	/**
	 * Creates a new security patch with the primary key. Does not add the security patch to the database.
	 *
	 * @param securityPatchId the primary key for the new security patch
	 * @return the new security patch
	 */
	public SecurityPatch create(long securityPatchId) {
		SecurityPatch securityPatch = new SecurityPatchImpl();

		securityPatch.setNew(true);
		securityPatch.setPrimaryKey(securityPatchId);

		return securityPatch;
	}

	/**
	 * Removes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param securityPatchId the primary key of the security patch
	 * @return the security patch that was removed
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch remove(long securityPatchId)
		throws NoSuchSecurityPatchException, SystemException {
		return remove(Long.valueOf(securityPatchId));
	}

	/**
	 * Removes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the security patch
	 * @return the security patch that was removed
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SecurityPatch remove(Serializable primaryKey)
		throws NoSuchSecurityPatchException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SecurityPatch securityPatch = (SecurityPatch)session.get(SecurityPatchImpl.class,
					primaryKey);

			if (securityPatch == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSecurityPatchException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(securityPatch);
		}
		catch (NoSuchSecurityPatchException nsee) {
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
	protected SecurityPatch removeImpl(SecurityPatch securityPatch)
		throws SystemException {
		securityPatch = toUnwrappedModel(securityPatch);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, securityPatch);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(securityPatch);

		return securityPatch;
	}

	@Override
	public SecurityPatch updateImpl(
		com.liferay.osb.model.SecurityPatch securityPatch, boolean merge)
		throws SystemException {
		securityPatch = toUnwrappedModel(securityPatch);

		boolean isNew = securityPatch.isNew();

		SecurityPatchModelImpl securityPatchModelImpl = (SecurityPatchModelImpl)securityPatch;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, securityPatch, merge);

			securityPatch.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SecurityPatchModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((securityPatchModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						securityPatchModelImpl.getOriginalPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PORTLETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID,
					args);

				args = new Object[] { securityPatchModelImpl.getPortletId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PORTLETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID,
					args);
			}

			if ((securityPatchModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(securityPatchModelImpl.getOriginalAccountEntryId()),
						
						securityPatchModelImpl.getOriginalPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_PI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI,
					args);

				args = new Object[] {
						Long.valueOf(securityPatchModelImpl.getAccountEntryId()),
						
						securityPatchModelImpl.getPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI_PI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI,
					args);
			}
		}

		EntityCacheUtil.putResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchImpl.class, securityPatch.getPrimaryKey(),
			securityPatch);

		return securityPatch;
	}

	protected SecurityPatch toUnwrappedModel(SecurityPatch securityPatch) {
		if (securityPatch instanceof SecurityPatchImpl) {
			return securityPatch;
		}

		SecurityPatchImpl securityPatchImpl = new SecurityPatchImpl();

		securityPatchImpl.setNew(securityPatch.isNew());
		securityPatchImpl.setPrimaryKey(securityPatch.getPrimaryKey());

		securityPatchImpl.setSecurityPatchId(securityPatch.getSecurityPatchId());
		securityPatchImpl.setUserId(securityPatch.getUserId());
		securityPatchImpl.setUserName(securityPatch.getUserName());
		securityPatchImpl.setCreateDate(securityPatch.getCreateDate());
		securityPatchImpl.setAccountEntryId(securityPatch.getAccountEntryId());
		securityPatchImpl.setTicketAttachmentId(securityPatch.getTicketAttachmentId());
		securityPatchImpl.setPortletId(securityPatch.getPortletId());
		securityPatchImpl.setEnvLFR(securityPatch.getEnvLFR());
		securityPatchImpl.setName(securityPatch.getName());
		securityPatchImpl.setFileName(securityPatch.getFileName());

		return securityPatchImpl;
	}

	/**
	 * Returns the security patch with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the security patch
	 * @return the security patch
	 * @throws com.liferay.portal.NoSuchModelException if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SecurityPatch findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the security patch with the primary key or throws a {@link com.liferay.osb.NoSuchSecurityPatchException} if it could not be found.
	 *
	 * @param securityPatchId the primary key of the security patch
	 * @return the security patch
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch findByPrimaryKey(long securityPatchId)
		throws NoSuchSecurityPatchException, SystemException {
		SecurityPatch securityPatch = fetchByPrimaryKey(securityPatchId);

		if (securityPatch == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + securityPatchId);
			}

			throw new NoSuchSecurityPatchException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				securityPatchId);
		}

		return securityPatch;
	}

	/**
	 * Returns the security patch with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the security patch
	 * @return the security patch, or <code>null</code> if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SecurityPatch fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the security patch with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param securityPatchId the primary key of the security patch
	 * @return the security patch, or <code>null</code> if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch fetchByPrimaryKey(long securityPatchId)
		throws SystemException {
		SecurityPatch securityPatch = (SecurityPatch)EntityCacheUtil.getResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
				SecurityPatchImpl.class, securityPatchId);

		if (securityPatch == _nullSecurityPatch) {
			return null;
		}

		if (securityPatch == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				securityPatch = (SecurityPatch)session.get(SecurityPatchImpl.class,
						Long.valueOf(securityPatchId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (securityPatch != null) {
					cacheResult(securityPatch);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
						SecurityPatchImpl.class, securityPatchId,
						_nullSecurityPatch);
				}

				closeSession(session);
			}
		}

		return securityPatch;
	}

	/**
	 * Returns all the security patchs where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findByPortletId(String portletId)
		throws SystemException {
		return findByPortletId(portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the security patchs where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @return the range of matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findByPortletId(String portletId, int start,
		int end) throws SystemException {
		return findByPortletId(portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the security patchs where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findByPortletId(String portletId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID;
			finderArgs = new Object[] { portletId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PORTLETID;
			finderArgs = new Object[] { portletId, start, end, orderByComparator };
		}

		List<SecurityPatch> list = (List<SecurityPatch>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SecurityPatch securityPatch : list) {
				if (!Validator.equals(portletId, securityPatch.getPortletId())) {
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

			query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
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

				if (portletId != null) {
					qPos.add(portletId);
				}

				list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first security patch in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching security patch
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch findByPortletId_First(String portletId,
		OrderByComparator orderByComparator)
		throws NoSuchSecurityPatchException, SystemException {
		SecurityPatch securityPatch = fetchByPortletId_First(portletId,
				orderByComparator);

		if (securityPatch != null) {
			return securityPatch;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portletId=");
		msg.append(portletId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSecurityPatchException(msg.toString());
	}

	/**
	 * Returns the first security patch in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch fetchByPortletId_First(String portletId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SecurityPatch> list = findByPortletId(portletId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last security patch in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching security patch
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch findByPortletId_Last(String portletId,
		OrderByComparator orderByComparator)
		throws NoSuchSecurityPatchException, SystemException {
		SecurityPatch securityPatch = fetchByPortletId_Last(portletId,
				orderByComparator);

		if (securityPatch != null) {
			return securityPatch;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portletId=");
		msg.append(portletId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSecurityPatchException(msg.toString());
	}

	/**
	 * Returns the last security patch in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch fetchByPortletId_Last(String portletId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortletId(portletId);

		List<SecurityPatch> list = findByPortletId(portletId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the security patchs before and after the current security patch in the ordered set where portletId = &#63;.
	 *
	 * @param securityPatchId the primary key of the current security patch
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next security patch
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch[] findByPortletId_PrevAndNext(long securityPatchId,
		String portletId, OrderByComparator orderByComparator)
		throws NoSuchSecurityPatchException, SystemException {
		SecurityPatch securityPatch = findByPrimaryKey(securityPatchId);

		Session session = null;

		try {
			session = openSession();

			SecurityPatch[] array = new SecurityPatchImpl[3];

			array[0] = getByPortletId_PrevAndNext(session, securityPatch,
					portletId, orderByComparator, true);

			array[1] = securityPatch;

			array[2] = getByPortletId_PrevAndNext(session, securityPatch,
					portletId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SecurityPatch getByPortletId_PrevAndNext(Session session,
		SecurityPatch securityPatch, String portletId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

		if (portletId == null) {
			query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
		}
		else {
			if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
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

		if (portletId != null) {
			qPos.add(portletId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(securityPatch);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SecurityPatch> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @return the matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findByAEI_PI(long accountEntryId,
		String portletId) throws SystemException {
		return findByAEI_PI(accountEntryId, portletId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @return the range of matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findByAEI_PI(long accountEntryId,
		String portletId, int start, int end) throws SystemException {
		return findByAEI_PI(accountEntryId, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findByAEI_PI(long accountEntryId,
		String portletId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI;
			finderArgs = new Object[] { accountEntryId, portletId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_PI;
			finderArgs = new Object[] {
					accountEntryId, portletId,
					
					start, end, orderByComparator
				};
		}

		List<SecurityPatch> list = (List<SecurityPatch>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SecurityPatch securityPatch : list) {
				if ((accountEntryId != securityPatch.getAccountEntryId()) ||
						!Validator.equals(portletId,
							securityPatch.getPortletId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

			query.append(_FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_2);
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

				qPos.add(accountEntryId);

				if (portletId != null) {
					qPos.add(portletId);
				}

				list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching security patch
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch findByAEI_PI_First(long accountEntryId,
		String portletId, OrderByComparator orderByComparator)
		throws NoSuchSecurityPatchException, SystemException {
		SecurityPatch securityPatch = fetchByAEI_PI_First(accountEntryId,
				portletId, orderByComparator);

		if (securityPatch != null) {
			return securityPatch;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", portletId=");
		msg.append(portletId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSecurityPatchException(msg.toString());
	}

	/**
	 * Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch fetchByAEI_PI_First(long accountEntryId,
		String portletId, OrderByComparator orderByComparator)
		throws SystemException {
		List<SecurityPatch> list = findByAEI_PI(accountEntryId, portletId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching security patch
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch findByAEI_PI_Last(long accountEntryId,
		String portletId, OrderByComparator orderByComparator)
		throws NoSuchSecurityPatchException, SystemException {
		SecurityPatch securityPatch = fetchByAEI_PI_Last(accountEntryId,
				portletId, orderByComparator);

		if (securityPatch != null) {
			return securityPatch;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", portletId=");
		msg.append(portletId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSecurityPatchException(msg.toString());
	}

	/**
	 * Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch fetchByAEI_PI_Last(long accountEntryId,
		String portletId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByAEI_PI(accountEntryId, portletId);

		List<SecurityPatch> list = findByAEI_PI(accountEntryId, portletId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the security patchs before and after the current security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param securityPatchId the primary key of the current security patch
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next security patch
	 * @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SecurityPatch[] findByAEI_PI_PrevAndNext(long securityPatchId,
		long accountEntryId, String portletId,
		OrderByComparator orderByComparator)
		throws NoSuchSecurityPatchException, SystemException {
		SecurityPatch securityPatch = findByPrimaryKey(securityPatchId);

		Session session = null;

		try {
			session = openSession();

			SecurityPatch[] array = new SecurityPatchImpl[3];

			array[0] = getByAEI_PI_PrevAndNext(session, securityPatch,
					accountEntryId, portletId, orderByComparator, true);

			array[1] = securityPatch;

			array[2] = getByAEI_PI_PrevAndNext(session, securityPatch,
					accountEntryId, portletId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SecurityPatch getByAEI_PI_PrevAndNext(Session session,
		SecurityPatch securityPatch, long accountEntryId, String portletId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

		query.append(_FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2);

		if (portletId == null) {
			query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_1);
		}
		else {
			if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_3);
			}
			else {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_2);
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

		qPos.add(accountEntryId);

		if (portletId != null) {
			qPos.add(portletId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(securityPatch);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SecurityPatch> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the security patchs.
	 *
	 * @return the security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the security patchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @return the range of security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the security patchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<SecurityPatch> findAll(int start, int end,
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

		List<SecurityPatch> list = (List<SecurityPatch>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SECURITYPATCH);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SECURITYPATCH;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the security patchs where portletId = &#63; from the database.
	 *
	 * @param portletId the portlet ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortletId(String portletId) throws SystemException {
		for (SecurityPatch securityPatch : findByPortletId(portletId)) {
			remove(securityPatch);
		}
	}

	/**
	 * Removes all the security patchs where accountEntryId = &#63; and portletId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI_PI(long accountEntryId, String portletId)
		throws SystemException {
		for (SecurityPatch securityPatch : findByAEI_PI(accountEntryId,
				portletId)) {
			remove(securityPatch);
		}
	}

	/**
	 * Removes all the security patchs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SecurityPatch securityPatch : findAll()) {
			remove(securityPatch);
		}
	}

	/**
	 * Returns the number of security patchs where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the number of matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortletId(String portletId) throws SystemException {
		Object[] finderArgs = new Object[] { portletId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PORTLETID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SECURITYPATCH_WHERE);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (portletId != null) {
					qPos.add(portletId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PORTLETID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @return the number of matching security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI_PI(long accountEntryId, String portletId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId, portletId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI_PI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SECURITYPATCH_WHERE);

			query.append(_FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (portletId != null) {
					qPos.add(portletId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI_PI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of security patchs.
	 *
	 * @return the number of security patchs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SECURITYPATCH);

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
	 * Initializes the security patch persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.SecurityPatch")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SecurityPatch>> listenersList = new ArrayList<ModelListener<SecurityPatch>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SecurityPatch>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SecurityPatchImpl.class.getName());
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
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SECURITYPATCH = "SELECT securityPatch FROM SecurityPatch securityPatch";
	private static final String _SQL_SELECT_SECURITYPATCH_WHERE = "SELECT securityPatch FROM SecurityPatch securityPatch WHERE ";
	private static final String _SQL_COUNT_SECURITYPATCH = "SELECT COUNT(securityPatch) FROM SecurityPatch securityPatch";
	private static final String _SQL_COUNT_SECURITYPATCH_WHERE = "SELECT COUNT(securityPatch) FROM SecurityPatch securityPatch WHERE ";
	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_1 = "securityPatch.portletId IS NULL";
	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_2 = "securityPatch.portletId = ?";
	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_3 = "(securityPatch.portletId IS NULL OR securityPatch.portletId = ?)";
	private static final String _FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2 = "securityPatch.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PI_PORTLETID_1 = "securityPatch.portletId IS NULL";
	private static final String _FINDER_COLUMN_AEI_PI_PORTLETID_2 = "securityPatch.portletId = ?";
	private static final String _FINDER_COLUMN_AEI_PI_PORTLETID_3 = "(securityPatch.portletId IS NULL OR securityPatch.portletId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "securityPatch.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SecurityPatch exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SecurityPatch exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SecurityPatchPersistenceImpl.class);
	private static SecurityPatch _nullSecurityPatch = new SecurityPatchImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SecurityPatch> toCacheModel() {
				return _nullSecurityPatchCacheModel;
			}
		};

	private static CacheModel<SecurityPatch> _nullSecurityPatchCacheModel = new CacheModel<SecurityPatch>() {
			public SecurityPatch toEntityModel() {
				return _nullSecurityPatch;
			}
		};
}
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

import com.liferay.osb.exception.NoSuchSecurityPatchException;
import com.liferay.osb.model.SecurityPatch;
import com.liferay.osb.model.impl.SecurityPatchImpl;
import com.liferay.osb.model.impl.SecurityPatchModelImpl;
import com.liferay.osb.service.persistence.SecurityPatchPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
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
 * The persistence implementation for the security patch service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatchPersistence
 * @see com.liferay.osb.service.persistence.SecurityPatchUtil
 * @generated
 */
@ProviderType
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PORTLETID =
		new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPortletId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
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

	/**
	 * Returns all the security patchs where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByPortletId(String portletId) {
		return findByPortletId(portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the security patchs where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @return the range of matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByPortletId(String portletId, int start,
		int end) {
		return findByPortletId(portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the security patchs where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByPortletId(String portletId, int start,
		int end, OrderByComparator<SecurityPatch> orderByComparator) {
		return findByPortletId(portletId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the security patchs where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByPortletId(String portletId, int start,
		int end, OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID;
			finderArgs = new Object[] { portletId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PORTLETID;
			finderArgs = new Object[] { portletId, start, end, orderByComparator };
		}

		List<SecurityPatch> list = null;

		if (retrieveFromCache) {
			list = (List<SecurityPatch>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SecurityPatch securityPatch : list) {
					if (!Objects.equals(portletId, securityPatch.getPortletId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SecurityPatchModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPortletId) {
					qPos.add(portletId);
				}

				if (!pagination) {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first security patch in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching security patch
	 * @throws NoSuchSecurityPatchException if a matching security patch could not be found
	 */
	@Override
	public SecurityPatch findByPortletId_First(String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException {
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
	 */
	@Override
	public SecurityPatch fetchByPortletId_First(String portletId,
		OrderByComparator<SecurityPatch> orderByComparator) {
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
	 * @throws NoSuchSecurityPatchException if a matching security patch could not be found
	 */
	@Override
	public SecurityPatch findByPortletId_Last(String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException {
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
	 */
	@Override
	public SecurityPatch fetchByPortletId_Last(String portletId,
		OrderByComparator<SecurityPatch> orderByComparator) {
		int count = countByPortletId(portletId);

		if (count == 0) {
			return null;
		}

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
	 * @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch[] findByPortletId_PrevAndNext(long securityPatchId,
		String portletId, OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException {
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
		OrderByComparator<SecurityPatch> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

		boolean bindPortletId = false;

		if (portletId == null) {
			query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
		}
		else if (portletId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
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
			query.append(SecurityPatchModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPortletId) {
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
	 * Removes all the security patchs where portletId = &#63; from the database.
	 *
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByPortletId(String portletId) {
		for (SecurityPatch securityPatch : findByPortletId(portletId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(securityPatch);
		}
	}

	/**
	 * Returns the number of security patchs where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the number of matching security patchs
	 */
	@Override
	public int countByPortletId(String portletId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PORTLETID;

		Object[] finderArgs = new Object[] { portletId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SECURITYPATCH_WHERE);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPortletId) {
					qPos.add(portletId);
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

	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_1 = "securityPatch.portletId IS NULL";
	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_2 = "securityPatch.portletId = ?";
	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_3 = "(securityPatch.portletId IS NULL OR securityPatch.portletId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI_PI = new FinderPath(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchModelImpl.FINDER_CACHE_ENABLED,
			SecurityPatchImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAEI_PI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
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

	/**
	 * Returns all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @return the matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByAEI_PI(long accountEntryId,
		String portletId) {
		return findByAEI_PI(accountEntryId, portletId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @return the range of matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByAEI_PI(long accountEntryId,
		String portletId, int start, int end) {
		return findByAEI_PI(accountEntryId, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByAEI_PI(long accountEntryId,
		String portletId, int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return findByAEI_PI(accountEntryId, portletId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching security patchs
	 */
	@Override
	public List<SecurityPatch> findByAEI_PI(long accountEntryId,
		String portletId, int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
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

		List<SecurityPatch> list = null;

		if (retrieveFromCache) {
			list = (List<SecurityPatch>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SecurityPatch securityPatch : list) {
					if ((accountEntryId != securityPatch.getAccountEntryId()) ||
							!Objects.equals(portletId,
								securityPatch.getPortletId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

			query.append(_FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SecurityPatchModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (bindPortletId) {
					qPos.add(portletId);
				}

				if (!pagination) {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching security patch
	 * @throws NoSuchSecurityPatchException if a matching security patch could not be found
	 */
	@Override
	public SecurityPatch findByAEI_PI_First(long accountEntryId,
		String portletId, OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException {
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
	 */
	@Override
	public SecurityPatch fetchByAEI_PI_First(long accountEntryId,
		String portletId, OrderByComparator<SecurityPatch> orderByComparator) {
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
	 * @throws NoSuchSecurityPatchException if a matching security patch could not be found
	 */
	@Override
	public SecurityPatch findByAEI_PI_Last(long accountEntryId,
		String portletId, OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException {
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
	 */
	@Override
	public SecurityPatch fetchByAEI_PI_Last(long accountEntryId,
		String portletId, OrderByComparator<SecurityPatch> orderByComparator) {
		int count = countByAEI_PI(accountEntryId, portletId);

		if (count == 0) {
			return null;
		}

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
	 * @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch[] findByAEI_PI_PrevAndNext(long securityPatchId,
		long accountEntryId, String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException {
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
		OrderByComparator<SecurityPatch> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SECURITYPATCH_WHERE);

		query.append(_FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2);

		boolean bindPortletId = false;

		if (portletId == null) {
			query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_1);
		}
		else if (portletId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_2);
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
			query.append(SecurityPatchModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (bindPortletId) {
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
	 * Removes all the security patchs where accountEntryId = &#63; and portletId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByAEI_PI(long accountEntryId, String portletId) {
		for (SecurityPatch securityPatch : findByAEI_PI(accountEntryId,
				portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(securityPatch);
		}
	}

	/**
	 * Returns the number of security patchs where accountEntryId = &#63; and portletId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param portletId the portlet ID
	 * @return the number of matching security patchs
	 */
	@Override
	public int countByAEI_PI(long accountEntryId, String portletId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AEI_PI;

		Object[] finderArgs = new Object[] { accountEntryId, portletId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SECURITYPATCH_WHERE);

			query.append(_FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_AEI_PI_PORTLETID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				if (bindPortletId) {
					qPos.add(portletId);
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

	private static final String _FINDER_COLUMN_AEI_PI_ACCOUNTENTRYID_2 = "securityPatch.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_AEI_PI_PORTLETID_1 = "securityPatch.portletId IS NULL";
	private static final String _FINDER_COLUMN_AEI_PI_PORTLETID_2 = "securityPatch.portletId = ?";
	private static final String _FINDER_COLUMN_AEI_PI_PORTLETID_3 = "(securityPatch.portletId IS NULL OR securityPatch.portletId = '')";

	public SecurityPatchPersistenceImpl() {
		setModelClass(SecurityPatch.class);
	}

	/**
	 * Caches the security patch in the entity cache if it is enabled.
	 *
	 * @param securityPatch the security patch
	 */
	@Override
	public void cacheResult(SecurityPatch securityPatch) {
		entityCache.putResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchImpl.class, securityPatch.getPrimaryKey(),
			securityPatch);

		securityPatch.resetOriginalValues();
	}

	/**
	 * Caches the security patchs in the entity cache if it is enabled.
	 *
	 * @param securityPatchs the security patchs
	 */
	@Override
	public void cacheResult(List<SecurityPatch> securityPatchs) {
		for (SecurityPatch securityPatch : securityPatchs) {
			if (entityCache.getResult(
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SecurityPatchImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the security patch.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SecurityPatch securityPatch) {
		entityCache.removeResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchImpl.class, securityPatch.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SecurityPatch> securityPatchs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SecurityPatch securityPatch : securityPatchs) {
			entityCache.removeResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
				SecurityPatchImpl.class, securityPatch.getPrimaryKey());
		}
	}

	/**
	 * Creates a new security patch with the primary key. Does not add the security patch to the database.
	 *
	 * @param securityPatchId the primary key for the new security patch
	 * @return the new security patch
	 */
	@Override
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
	 * @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch remove(long securityPatchId)
		throws NoSuchSecurityPatchException {
		return remove((Serializable)securityPatchId);
	}

	/**
	 * Removes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the security patch
	 * @return the security patch that was removed
	 * @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch remove(Serializable primaryKey)
		throws NoSuchSecurityPatchException {
		Session session = null;

		try {
			session = openSession();

			SecurityPatch securityPatch = (SecurityPatch)session.get(SecurityPatchImpl.class,
					primaryKey);

			if (securityPatch == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
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
	protected SecurityPatch removeImpl(SecurityPatch securityPatch) {
		securityPatch = toUnwrappedModel(securityPatch);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(securityPatch)) {
				securityPatch = (SecurityPatch)session.get(SecurityPatchImpl.class,
						securityPatch.getPrimaryKeyObj());
			}

			if (securityPatch != null) {
				session.delete(securityPatch);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (securityPatch != null) {
			clearCache(securityPatch);
		}

		return securityPatch;
	}

	@Override
	public SecurityPatch updateImpl(SecurityPatch securityPatch) {
		securityPatch = toUnwrappedModel(securityPatch);

		boolean isNew = securityPatch.isNew();

		SecurityPatchModelImpl securityPatchModelImpl = (SecurityPatchModelImpl)securityPatch;

		Session session = null;

		try {
			session = openSession();

			if (securityPatch.isNew()) {
				session.save(securityPatch);

				securityPatch.setNew(false);
			}
			else {
				securityPatch = (SecurityPatch)session.merge(securityPatch);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SecurityPatchModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { securityPatchModelImpl.getPortletId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PORTLETID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID,
				args);

			args = new Object[] {
					securityPatchModelImpl.getAccountEntryId(),
					securityPatchModelImpl.getPortletId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((securityPatchModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						securityPatchModelImpl.getOriginalPortletId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PORTLETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID,
					args);

				args = new Object[] { securityPatchModelImpl.getPortletId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PORTLETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID,
					args);
			}

			if ((securityPatchModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						securityPatchModelImpl.getOriginalAccountEntryId(),
						securityPatchModelImpl.getOriginalPortletId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI,
					args);

				args = new Object[] {
						securityPatchModelImpl.getAccountEntryId(),
						securityPatchModelImpl.getPortletId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AEI_PI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI_PI,
					args);
			}
		}

		entityCache.putResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
			SecurityPatchImpl.class, securityPatch.getPrimaryKey(),
			securityPatch, false);

		securityPatch.resetOriginalValues();

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
		securityPatchImpl.setPortletId(securityPatch.getPortletId());
		securityPatchImpl.setEnvLFR(securityPatch.getEnvLFR());
		securityPatchImpl.setName(securityPatch.getName());
		securityPatchImpl.setFileName(securityPatch.getFileName());

		return securityPatchImpl;
	}

	/**
	 * Returns the security patch with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the security patch
	 * @return the security patch
	 * @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSecurityPatchException {
		SecurityPatch securityPatch = fetchByPrimaryKey(primaryKey);

		if (securityPatch == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSecurityPatchException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return securityPatch;
	}

	/**
	 * Returns the security patch with the primary key or throws a {@link NoSuchSecurityPatchException} if it could not be found.
	 *
	 * @param securityPatchId the primary key of the security patch
	 * @return the security patch
	 * @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch findByPrimaryKey(long securityPatchId)
		throws NoSuchSecurityPatchException {
		return findByPrimaryKey((Serializable)securityPatchId);
	}

	/**
	 * Returns the security patch with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the security patch
	 * @return the security patch, or <code>null</code> if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
				SecurityPatchImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SecurityPatch securityPatch = (SecurityPatch)serializable;

		if (securityPatch == null) {
			Session session = null;

			try {
				session = openSession();

				securityPatch = (SecurityPatch)session.get(SecurityPatchImpl.class,
						primaryKey);

				if (securityPatch != null) {
					cacheResult(securityPatch);
				}
				else {
					entityCache.putResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
						SecurityPatchImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
					SecurityPatchImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return securityPatch;
	}

	/**
	 * Returns the security patch with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param securityPatchId the primary key of the security patch
	 * @return the security patch, or <code>null</code> if a security patch with the primary key could not be found
	 */
	@Override
	public SecurityPatch fetchByPrimaryKey(long securityPatchId) {
		return fetchByPrimaryKey((Serializable)securityPatchId);
	}

	@Override
	public Map<Serializable, SecurityPatch> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SecurityPatch> map = new HashMap<Serializable, SecurityPatch>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SecurityPatch securityPatch = fetchByPrimaryKey(primaryKey);

			if (securityPatch != null) {
				map.put(primaryKey, securityPatch);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
					SecurityPatchImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SecurityPatch)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SECURITYPATCH_WHERE_PKS_IN);

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

			for (SecurityPatch securityPatch : (List<SecurityPatch>)q.list()) {
				map.put(securityPatch.getPrimaryKeyObj(), securityPatch);

				cacheResult(securityPatch);

				uncachedPrimaryKeys.remove(securityPatch.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SecurityPatchModelImpl.ENTITY_CACHE_ENABLED,
					SecurityPatchImpl.class, primaryKey, nullModel);
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
	 * Returns all the security patchs.
	 *
	 * @return the security patchs
	 */
	@Override
	public List<SecurityPatch> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the security patchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @return the range of security patchs
	 */
	@Override
	public List<SecurityPatch> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the security patchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of security patchs
	 */
	@Override
	public List<SecurityPatch> findAll(int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the security patchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of security patchs
	 * @param end the upper bound of the range of security patchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of security patchs
	 */
	@Override
	public List<SecurityPatch> findAll(int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator,
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

		List<SecurityPatch> list = null;

		if (retrieveFromCache) {
			list = (List<SecurityPatch>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SECURITYPATCH);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SECURITYPATCH;

				if (pagination) {
					sql = sql.concat(SecurityPatchModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SecurityPatch>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the security patchs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SecurityPatch securityPatch : findAll()) {
			remove(securityPatch);
		}
	}

	/**
	 * Returns the number of security patchs.
	 *
	 * @return the number of security patchs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SECURITYPATCH);

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

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SecurityPatchModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the security patch persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SecurityPatchImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SECURITYPATCH = "SELECT securityPatch FROM SecurityPatch securityPatch";
	private static final String _SQL_SELECT_SECURITYPATCH_WHERE_PKS_IN = "SELECT securityPatch FROM SecurityPatch securityPatch WHERE securityPatchId IN (";
	private static final String _SQL_SELECT_SECURITYPATCH_WHERE = "SELECT securityPatch FROM SecurityPatch securityPatch WHERE ";
	private static final String _SQL_COUNT_SECURITYPATCH = "SELECT COUNT(securityPatch) FROM SecurityPatch securityPatch";
	private static final String _SQL_COUNT_SECURITYPATCH_WHERE = "SELECT COUNT(securityPatch) FROM SecurityPatch securityPatch WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "securityPatch.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SecurityPatch exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SecurityPatch exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SecurityPatchPersistenceImpl.class);
}
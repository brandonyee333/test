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

import com.liferay.osb.exception.NoSuchAuditActionException;
import com.liferay.osb.model.AuditAction;
import com.liferay.osb.model.impl.AuditActionImpl;
import com.liferay.osb.model.impl.AuditActionModelImpl;
import com.liferay.osb.service.persistence.AuditActionPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.sql.Timestamp;

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
 * The persistence implementation for the audit action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditActionPersistence
 * @see com.liferay.osb.service.persistence.AuditActionUtil
 * @generated
 */
@ProviderType
public class AuditActionPersistenceImpl extends BasePersistenceImpl<AuditAction>
	implements AuditActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditActionUtil} to access the audit action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtModifiedDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtModifiedDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the audit actions where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching audit actions
	 */
	@Override
	public List<AuditAction> findByLtModifiedDate(Date modifiedDate) {
		return findByLtModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByLtModifiedDate(Date modifiedDate, int start,
		int end) {
		return findByLtModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByLtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator<AuditAction> orderByComparator) {
		return findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByLtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE;
		finderArgs = new Object[] { modifiedDate, start, end, orderByComparator };

		List<AuditAction> list = null;

		if (retrieveFromCache) {
			list = (List<AuditAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditAction auditAction : list) {
					if ((modifiedDate.getTime() <= auditAction.getModifiedDate()
																  .getTime())) {
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

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AuditActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByLtModifiedDate_First(modifiedDate,
				orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator) {
		List<AuditAction> list = findByLtModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByLtModifiedDate_Last(modifiedDate,
				orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator) {
		int count = countByLtModifiedDate(modifiedDate);

		if (count == 0) {
			return null;
		}

		List<AuditAction> list = findByLtModifiedDate(modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction[] findByLtModifiedDate_PrevAndNext(long auditActionId,
		Date modifiedDate, OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByLtModifiedDate_PrevAndNext(session, auditAction,
					modifiedDate, orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByLtModifiedDate_PrevAndNext(session, auditAction,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditAction getByLtModifiedDate_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate,
		OrderByComparator<AuditAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
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
			query.append(AuditActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit actions where modifiedDate &lt; &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByLtModifiedDate(Date modifiedDate) {
		for (AuditAction auditAction : findByLtModifiedDate(modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(auditAction);
		}
	}

	/**
	 * Returns the number of audit actions where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching audit actions
	 */
	@Override
	public int countByLtModifiedDate(Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE;

		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1 = "auditAction.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2 = "auditAction.modifiedDate < ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_MC = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C_MC",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AuditActionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditActionModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditActionModelImpl.MAPPINGCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_MC = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_MC",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or throws a {@link NoSuchAuditActionException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @return the matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByC_C_MC(classNameId, classPK,
				mappingClassPK);

		if (auditAction == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", mappingClassPK=");
			msg.append(mappingClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAuditActionException(msg.toString());
		}

		return auditAction;
	}

	/**
	 * Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) {
		return fetchByC_C_MC(classNameId, classPK, mappingClassPK, true);
	}

	/**
	 * Returns the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByC_C_MC(long classNameId, long classPK,
		long mappingClassPK, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { classNameId, classPK, mappingClassPK };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_C_MC,
					finderArgs, this);
		}

		if (result instanceof AuditAction) {
			AuditAction auditAction = (AuditAction)result;

			if ((classNameId != auditAction.getClassNameId()) ||
					(classPK != auditAction.getClassPK()) ||
					(mappingClassPK != auditAction.getMappingClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_MC_MAPPINGCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

				List<AuditAction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_MC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AuditActionPersistenceImpl.fetchByC_C_MC(long, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AuditAction auditAction = list.get(0);

					result = auditAction;

					cacheResult(auditAction);

					if ((auditAction.getClassNameId() != classNameId) ||
							(auditAction.getClassPK() != classPK) ||
							(auditAction.getMappingClassPK() != mappingClassPK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_MC,
							finderArgs, auditAction);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C_MC, finderArgs);

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
			return (AuditAction)result;
		}
	}

	/**
	 * Removes the audit action where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @return the audit action that was removed
	 */
	@Override
	public AuditAction removeByC_C_MC(long classNameId, long classPK,
		long mappingClassPK) throws NoSuchAuditActionException {
		AuditAction auditAction = findByC_C_MC(classNameId, classPK,
				mappingClassPK);

		return remove(auditAction);
	}

	/**
	 * Returns the number of audit actions where classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @return the number of matching audit actions
	 */
	@Override
	public int countByC_C_MC(long classNameId, long classPK, long mappingClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_MC;

		Object[] finderArgs = new Object[] { classNameId, classPK, mappingClassPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_MC_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_MC_MAPPINGCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

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

	private static final String _FINDER_COLUMN_C_C_MC_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_MC_CLASSPK_2 = "auditAction.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_MC_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_GTMC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtMD_C_GtMC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_GTMC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtMD_C_GtMC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @return the matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action) {
		return findByGtMD_C_GtMC_A(modifiedDate, classNameId, mappingClassPK,
			action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end) {
		return findByGtMD_C_GtMC_A(modifiedDate, classNameId, mappingClassPK,
			action, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator) {
		return findByGtMD_C_GtMC_A(modifiedDate, classNameId, mappingClassPK,
			action, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_GtMC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_GTMC_A;
		finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action,
				
				start, end, orderByComparator
			};

		List<AuditAction> list = null;

		if (retrieveFromCache) {
			list = (List<AuditAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditAction auditAction : list) {
					if ((modifiedDate.getTime() >= auditAction.getModifiedDate()
																  .getTime()) ||
							(classNameId != auditAction.getClassNameId()) ||
							(mappingClassPK >= auditAction.getMappingClassPK()) ||
							(action != auditAction.getAction())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AuditActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

				qPos.add(action);

				if (!pagination) {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByGtMD_C_GtMC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByGtMD_C_GtMC_A_First(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByGtMD_C_GtMC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		List<AuditAction> list = findByGtMD_C_GtMC_A(modifiedDate, classNameId,
				mappingClassPK, action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByGtMD_C_GtMC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByGtMD_C_GtMC_A_Last(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByGtMD_C_GtMC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		int count = countByGtMD_C_GtMC_A(modifiedDate, classNameId,
				mappingClassPK, action);

		if (count == 0) {
			return null;
		}

		List<AuditAction> list = findByGtMD_C_GtMC_A(modifiedDate, classNameId,
				mappingClassPK, action, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction[] findByGtMD_C_GtMC_A_PrevAndNext(long auditActionId,
		Date modifiedDate, long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByGtMD_C_GtMC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
					orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByGtMD_C_GtMC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
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

	protected AuditAction getByGtMD_C_GtMC_A_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate, long classNameId,
		long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2);

		query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2);

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
			query.append(AuditActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		qPos.add(classNameId);

		qPos.add(mappingClassPK);

		qPos.add(action);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 */
	@Override
	public void removeByGtMD_C_GtMC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) {
		for (AuditAction auditAction : findByGtMD_C_GtMC_A(modifiedDate,
				classNameId, mappingClassPK, action, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(auditAction);
		}
	}

	/**
	 * Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK &gt; &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @return the number of matching audit actions
	 */
	@Override
	public int countByGtMD_C_GtMC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_GTMC_A;

		Object[] finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

				qPos.add(action);

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

	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_1 = "auditAction.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_MODIFIEDDATE_2 = "auditAction.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK > ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_GTMC_A_ACTION_2 = "auditAction.action = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGtMD_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtMD_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @return the matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action) {
		return findByGtMD_C_MC_A(modifiedDate, classNameId, mappingClassPK,
			action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end) {
		return findByGtMD_C_MC_A(modifiedDate, classNameId, mappingClassPK,
			action, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator) {
		return findByGtMD_C_MC_A(modifiedDate, classNameId, mappingClassPK,
			action, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByGtMD_C_MC_A(Date modifiedDate,
		long classNameId, long mappingClassPK, int action, int start, int end,
		OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTMD_C_MC_A;
		finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action,
				
				start, end, orderByComparator
			};

		List<AuditAction> list = null;

		if (retrieveFromCache) {
			list = (List<AuditAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditAction auditAction : list) {
					if ((modifiedDate.getTime() >= auditAction.getModifiedDate()
																  .getTime()) ||
							(classNameId != auditAction.getClassNameId()) ||
							(mappingClassPK != auditAction.getMappingClassPK()) ||
							(action != auditAction.getAction())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_ACTION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AuditActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

				qPos.add(action);

				if (!pagination) {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByGtMD_C_MC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByGtMD_C_MC_A_First(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByGtMD_C_MC_A_First(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		List<AuditAction> list = findByGtMD_C_MC_A(modifiedDate, classNameId,
				mappingClassPK, action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByGtMD_C_MC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByGtMD_C_MC_A_Last(modifiedDate,
				classNameId, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByGtMD_C_MC_A_Last(Date modifiedDate,
		long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		int count = countByGtMD_C_MC_A(modifiedDate, classNameId,
				mappingClassPK, action);

		if (count == 0) {
			return null;
		}

		List<AuditAction> list = findByGtMD_C_MC_A(modifiedDate, classNameId,
				mappingClassPK, action, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction[] findByGtMD_C_MC_A_PrevAndNext(long auditActionId,
		Date modifiedDate, long classNameId, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByGtMD_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
					orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByGtMD_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, mappingClassPK, action,
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

	protected AuditAction getByGtMD_C_MC_A_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate, long classNameId,
		long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2);

		query.append(_FINDER_COLUMN_GTMD_C_MC_A_ACTION_2);

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
			query.append(AuditActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		qPos.add(classNameId);

		qPos.add(mappingClassPK);

		qPos.add(action);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 */
	@Override
	public void removeByGtMD_C_MC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) {
		for (AuditAction auditAction : findByGtMD_C_MC_A(modifiedDate,
				classNameId, mappingClassPK, action, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(auditAction);
		}
	}

	/**
	 * Returns the number of audit actions where modifiedDate &gt; &#63; and classNameId = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @return the number of matching audit actions
	 */
	@Override
	public int countByGtMD_C_MC_A(Date modifiedDate, long classNameId,
		long mappingClassPK, int action) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTMD_C_MC_A;

		Object[] finderArgs = new Object[] {
				modifiedDate, classNameId, mappingClassPK, action
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_GTMD_C_MC_A_ACTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(classNameId);

				qPos.add(mappingClassPK);

				qPos.add(action);

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

	private static final String _FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_1 = "auditAction.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_MODIFIEDDATE_2 = "auditAction.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK = ? AND ";
	private static final String _FINDER_COLUMN_GTMD_C_MC_A_ACTION_2 = "auditAction.action = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_C_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMD_C_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A =
		new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, AuditActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMD_C_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName()
			},
			AuditActionModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			AuditActionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditActionModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditActionModelImpl.MAPPINGCLASSPK_COLUMN_BITMASK |
			AuditActionModelImpl.ACTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MD_C_C_MC_A = new FinderPath(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMD_C_C_MC_A",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @return the matching audit actions
	 */
	@Override
	public List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action) {
		return findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end) {
		return findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end, OrderByComparator<AuditAction> orderByComparator) {
		return findByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
			mappingClassPK, action, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching audit actions
	 */
	@Override
	public List<AuditAction> findByMD_C_C_MC_A(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		int start, int end, OrderByComparator<AuditAction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A;
			finderArgs = new Object[] {
					modifiedDate, classNameId, classPK, mappingClassPK, action
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_C_C_MC_A;
			finderArgs = new Object[] {
					modifiedDate, classNameId, classPK, mappingClassPK, action,
					
					start, end, orderByComparator
				};
		}

		List<AuditAction> list = null;

		if (retrieveFromCache) {
			list = (List<AuditAction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditAction auditAction : list) {
					if (!Objects.equals(modifiedDate,
								auditAction.getModifiedDate()) ||
							(classNameId != auditAction.getClassNameId()) ||
							(classPK != auditAction.getClassPK()) ||
							(mappingClassPK != auditAction.getMappingClassPK()) ||
							(action != auditAction.getAction())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_ACTION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AuditActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

				qPos.add(action);

				if (!pagination) {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByMD_C_C_MC_A_First(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByMD_C_C_MC_A_First(modifiedDate,
				classNameId, classPK, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the first audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByMD_C_C_MC_A_First(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		List<AuditAction> list = findByMD_C_C_MC_A(modifiedDate, classNameId,
				classPK, mappingClassPK, action, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action
	 * @throws NoSuchAuditActionException if a matching audit action could not be found
	 */
	@Override
	public AuditAction findByMD_C_C_MC_A_Last(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByMD_C_C_MC_A_Last(modifiedDate,
				classNameId, classPK, mappingClassPK, action, orderByComparator);

		if (auditAction != null) {
			return auditAction;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", mappingClassPK=");
		msg.append(mappingClassPK);

		msg.append(", action=");
		msg.append(action);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAuditActionException(msg.toString());
	}

	/**
	 * Returns the last audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit action, or <code>null</code> if a matching audit action could not be found
	 */
	@Override
	public AuditAction fetchByMD_C_C_MC_A_Last(Date modifiedDate,
		long classNameId, long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator) {
		int count = countByMD_C_C_MC_A(modifiedDate, classNameId, classPK,
				mappingClassPK, action);

		if (count == 0) {
			return null;
		}

		List<AuditAction> list = findByMD_C_C_MC_A(modifiedDate, classNameId,
				classPK, mappingClassPK, action, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit actions before and after the current audit action in the ordered set where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param auditActionId the primary key of the current audit action
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit action
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction[] findByMD_C_C_MC_A_PrevAndNext(long auditActionId,
		Date modifiedDate, long classNameId, long classPK, long mappingClassPK,
		int action, OrderByComparator<AuditAction> orderByComparator)
		throws NoSuchAuditActionException {
		AuditAction auditAction = findByPrimaryKey(auditActionId);

		Session session = null;

		try {
			session = openSession();

			AuditAction[] array = new AuditActionImpl[3];

			array[0] = getByMD_C_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, classPK, mappingClassPK, action,
					orderByComparator, true);

			array[1] = auditAction;

			array[2] = getByMD_C_C_MC_A_PrevAndNext(session, auditAction,
					modifiedDate, classNameId, classPK, mappingClassPK, action,
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

	protected AuditAction getByMD_C_C_MC_A_PrevAndNext(Session session,
		AuditAction auditAction, Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action,
		OrderByComparator<AuditAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_AUDITACTION_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2);

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2);

		query.append(_FINDER_COLUMN_MD_C_C_MC_A_ACTION_2);

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
			query.append(AuditActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(mappingClassPK);

		qPos.add(action);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 */
	@Override
	public void removeByMD_C_C_MC_A(Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action) {
		for (AuditAction auditAction : findByMD_C_C_MC_A(modifiedDate,
				classNameId, classPK, mappingClassPK, action,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(auditAction);
		}
	}

	/**
	 * Returns the number of audit actions where modifiedDate = &#63; and classNameId = &#63; and classPK = &#63; and mappingClassPK = &#63; and action = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param mappingClassPK the mapping class pk
	 * @param action the action
	 * @return the number of matching audit actions
	 */
	@Override
	public int countByMD_C_C_MC_A(Date modifiedDate, long classNameId,
		long classPK, long mappingClassPK, int action) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MD_C_C_MC_A;

		Object[] finderArgs = new Object[] {
				modifiedDate, classNameId, classPK, mappingClassPK, action
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_AUDITACTION_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2);

			query.append(_FINDER_COLUMN_MD_C_C_MC_A_ACTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(mappingClassPK);

				qPos.add(action);

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

	private static final String _FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_1 = "auditAction.modifiedDate IS NULL AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_MODIFIEDDATE_2 = "auditAction.modifiedDate = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_CLASSNAMEID_2 = "auditAction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_CLASSPK_2 = "auditAction.classPK = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_MAPPINGCLASSPK_2 = "auditAction.mappingClassPK = ? AND ";
	private static final String _FINDER_COLUMN_MD_C_C_MC_A_ACTION_2 = "auditAction.action = ?";

	public AuditActionPersistenceImpl() {
		setModelClass(AuditAction.class);
	}

	/**
	 * Caches the audit action in the entity cache if it is enabled.
	 *
	 * @param auditAction the audit action
	 */
	@Override
	public void cacheResult(AuditAction auditAction) {
		entityCache.putResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionImpl.class, auditAction.getPrimaryKey(), auditAction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_MC,
			new Object[] {
				auditAction.getClassNameId(), auditAction.getClassPK(),
				auditAction.getMappingClassPK()
			}, auditAction);

		auditAction.resetOriginalValues();
	}

	/**
	 * Caches the audit actions in the entity cache if it is enabled.
	 *
	 * @param auditActions the audit actions
	 */
	@Override
	public void cacheResult(List<AuditAction> auditActions) {
		for (AuditAction auditAction : auditActions) {
			if (entityCache.getResult(
						AuditActionModelImpl.ENTITY_CACHE_ENABLED,
						AuditActionImpl.class, auditAction.getPrimaryKey()) == null) {
				cacheResult(auditAction);
			}
			else {
				auditAction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit actions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AuditActionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit action.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditAction auditAction) {
		entityCache.removeResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionImpl.class, auditAction.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AuditActionModelImpl)auditAction, true);
	}

	@Override
	public void clearCache(List<AuditAction> auditActions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditAction auditAction : auditActions) {
			entityCache.removeResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
				AuditActionImpl.class, auditAction.getPrimaryKey());

			clearUniqueFindersCache((AuditActionModelImpl)auditAction, true);
		}
	}

	protected void cacheUniqueFindersCache(
		AuditActionModelImpl auditActionModelImpl) {
		Object[] args = new Object[] {
				auditActionModelImpl.getClassNameId(),
				auditActionModelImpl.getClassPK(),
				auditActionModelImpl.getMappingClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_C_MC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C_MC, args,
			auditActionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AuditActionModelImpl auditActionModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					auditActionModelImpl.getClassNameId(),
					auditActionModelImpl.getClassPK(),
					auditActionModelImpl.getMappingClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_MC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C_MC, args);
		}

		if ((auditActionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C_MC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					auditActionModelImpl.getOriginalClassNameId(),
					auditActionModelImpl.getOriginalClassPK(),
					auditActionModelImpl.getOriginalMappingClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_MC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C_MC, args);
		}
	}

	/**
	 * Creates a new audit action with the primary key. Does not add the audit action to the database.
	 *
	 * @param auditActionId the primary key for the new audit action
	 * @return the new audit action
	 */
	@Override
	public AuditAction create(long auditActionId) {
		AuditAction auditAction = new AuditActionImpl();

		auditAction.setNew(true);
		auditAction.setPrimaryKey(auditActionId);

		return auditAction;
	}

	/**
	 * Removes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditActionId the primary key of the audit action
	 * @return the audit action that was removed
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction remove(long auditActionId)
		throws NoSuchAuditActionException {
		return remove((Serializable)auditActionId);
	}

	/**
	 * Removes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit action
	 * @return the audit action that was removed
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction remove(Serializable primaryKey)
		throws NoSuchAuditActionException {
		Session session = null;

		try {
			session = openSession();

			AuditAction auditAction = (AuditAction)session.get(AuditActionImpl.class,
					primaryKey);

			if (auditAction == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditAction);
		}
		catch (NoSuchAuditActionException nsee) {
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
	protected AuditAction removeImpl(AuditAction auditAction) {
		auditAction = toUnwrappedModel(auditAction);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(auditAction)) {
				auditAction = (AuditAction)session.get(AuditActionImpl.class,
						auditAction.getPrimaryKeyObj());
			}

			if (auditAction != null) {
				session.delete(auditAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (auditAction != null) {
			clearCache(auditAction);
		}

		return auditAction;
	}

	@Override
	public AuditAction updateImpl(AuditAction auditAction) {
		auditAction = toUnwrappedModel(auditAction);

		boolean isNew = auditAction.isNew();

		AuditActionModelImpl auditActionModelImpl = (AuditActionModelImpl)auditAction;

		Session session = null;

		try {
			session = openSession();

			if (auditAction.isNew()) {
				session.save(auditAction);

				auditAction.setNew(false);
			}
			else {
				auditAction = (AuditAction)session.merge(auditAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AuditActionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					auditActionModelImpl.getModifiedDate(),
					auditActionModelImpl.getClassNameId(),
					auditActionModelImpl.getClassPK(),
					auditActionModelImpl.getMappingClassPK(),
					auditActionModelImpl.getAction()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MD_C_C_MC_A, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((auditActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						auditActionModelImpl.getOriginalModifiedDate(),
						auditActionModelImpl.getOriginalClassNameId(),
						auditActionModelImpl.getOriginalClassPK(),
						auditActionModelImpl.getOriginalMappingClassPK(),
						auditActionModelImpl.getOriginalAction()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MD_C_C_MC_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A,
					args);

				args = new Object[] {
						auditActionModelImpl.getModifiedDate(),
						auditActionModelImpl.getClassNameId(),
						auditActionModelImpl.getClassPK(),
						auditActionModelImpl.getMappingClassPK(),
						auditActionModelImpl.getAction()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MD_C_C_MC_A, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MD_C_C_MC_A,
					args);
			}
		}

		entityCache.putResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
			AuditActionImpl.class, auditAction.getPrimaryKey(), auditAction,
			false);

		clearUniqueFindersCache(auditActionModelImpl, false);
		cacheUniqueFindersCache(auditActionModelImpl);

		auditAction.resetOriginalValues();

		return auditAction;
	}

	protected AuditAction toUnwrappedModel(AuditAction auditAction) {
		if (auditAction instanceof AuditActionImpl) {
			return auditAction;
		}

		AuditActionImpl auditActionImpl = new AuditActionImpl();

		auditActionImpl.setNew(auditAction.isNew());
		auditActionImpl.setPrimaryKey(auditAction.getPrimaryKey());

		auditActionImpl.setAuditActionId(auditAction.getAuditActionId());
		auditActionImpl.setModifiedDate(auditAction.getModifiedDate());
		auditActionImpl.setClassNameId(auditAction.getClassNameId());
		auditActionImpl.setClassPK(auditAction.getClassPK());
		auditActionImpl.setMappingClassPK(auditAction.getMappingClassPK());
		auditActionImpl.setAction(auditAction.getAction());

		return auditActionImpl;
	}

	/**
	 * Returns the audit action with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit action
	 * @return the audit action
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAuditActionException {
		AuditAction auditAction = fetchByPrimaryKey(primaryKey);

		if (auditAction == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAuditActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return auditAction;
	}

	/**
	 * Returns the audit action with the primary key or throws a {@link NoSuchAuditActionException} if it could not be found.
	 *
	 * @param auditActionId the primary key of the audit action
	 * @return the audit action
	 * @throws NoSuchAuditActionException if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction findByPrimaryKey(long auditActionId)
		throws NoSuchAuditActionException {
		return findByPrimaryKey((Serializable)auditActionId);
	}

	/**
	 * Returns the audit action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit action
	 * @return the audit action, or <code>null</code> if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
				AuditActionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AuditAction auditAction = (AuditAction)serializable;

		if (auditAction == null) {
			Session session = null;

			try {
				session = openSession();

				auditAction = (AuditAction)session.get(AuditActionImpl.class,
						primaryKey);

				if (auditAction != null) {
					cacheResult(auditAction);
				}
				else {
					entityCache.putResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
						AuditActionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
					AuditActionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return auditAction;
	}

	/**
	 * Returns the audit action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditActionId the primary key of the audit action
	 * @return the audit action, or <code>null</code> if a audit action with the primary key could not be found
	 */
	@Override
	public AuditAction fetchByPrimaryKey(long auditActionId) {
		return fetchByPrimaryKey((Serializable)auditActionId);
	}

	@Override
	public Map<Serializable, AuditAction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AuditAction> map = new HashMap<Serializable, AuditAction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AuditAction auditAction = fetchByPrimaryKey(primaryKey);

			if (auditAction != null) {
				map.put(primaryKey, auditAction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
					AuditActionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AuditAction)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_AUDITACTION_WHERE_PKS_IN);

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

			for (AuditAction auditAction : (List<AuditAction>)q.list()) {
				map.put(auditAction.getPrimaryKeyObj(), auditAction);

				cacheResult(auditAction);

				uncachedPrimaryKeys.remove(auditAction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AuditActionModelImpl.ENTITY_CACHE_ENABLED,
					AuditActionImpl.class, primaryKey, nullModel);
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
	 * Returns all the audit actions.
	 *
	 * @return the audit actions
	 */
	@Override
	public List<AuditAction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @return the range of audit actions
	 */
	@Override
	public List<AuditAction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit actions
	 */
	@Override
	public List<AuditAction> findAll(int start, int end,
		OrderByComparator<AuditAction> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit actions
	 * @param end the upper bound of the range of audit actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of audit actions
	 */
	@Override
	public List<AuditAction> findAll(int start, int end,
		OrderByComparator<AuditAction> orderByComparator,
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

		List<AuditAction> list = null;

		if (retrieveFromCache) {
			list = (List<AuditAction>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_AUDITACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITACTION;

				if (pagination) {
					sql = sql.concat(AuditActionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AuditAction>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the audit actions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AuditAction auditAction : findAll()) {
			remove(auditAction);
		}
	}

	/**
	 * Returns the number of audit actions.
	 *
	 * @return the number of audit actions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITACTION);

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
		return AuditActionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the audit action persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AuditActionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_AUDITACTION = "SELECT auditAction FROM AuditAction auditAction";
	private static final String _SQL_SELECT_AUDITACTION_WHERE_PKS_IN = "SELECT auditAction FROM AuditAction auditAction WHERE auditActionId IN (";
	private static final String _SQL_SELECT_AUDITACTION_WHERE = "SELECT auditAction FROM AuditAction auditAction WHERE ";
	private static final String _SQL_COUNT_AUDITACTION = "SELECT COUNT(auditAction) FROM AuditAction auditAction";
	private static final String _SQL_COUNT_AUDITACTION_WHERE = "SELECT COUNT(auditAction) FROM AuditAction auditAction WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AuditAction exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AuditActionPersistenceImpl.class);
}
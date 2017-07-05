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

import com.liferay.osb.exception.NoSuchContractAuditException;
import com.liferay.osb.model.ContractAudit;
import com.liferay.osb.model.impl.ContractAuditImpl;
import com.liferay.osb.model.impl.ContractAuditModelImpl;
import com.liferay.osb.service.persistence.ContractAuditPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the contract audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractAuditPersistence
 * @see com.liferay.osb.service.persistence.ContractAuditUtil
 * @generated
 */
@ProviderType
public class ContractAuditPersistenceImpl extends BasePersistenceImpl<ContractAudit>
	implements ContractAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContractAuditUtil} to access the contract audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContractAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_CEI = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_CEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_CEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			ContractAuditModelImpl.USERID_COLUMN_BITMASK |
			ContractAuditModelImpl.CONTRACTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_CEI = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_CEI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @return the matching contract audits
	 */
	@Override
	public List<ContractAudit> findByU_CEI(long userId, long contractEntryId) {
		return findByU_CEI(userId, contractEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @return the range of matching contract audits
	 */
	@Override
	public List<ContractAudit> findByU_CEI(long userId, long contractEntryId,
		int start, int end) {
		return findByU_CEI(userId, contractEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contract audits
	 */
	@Override
	public List<ContractAudit> findByU_CEI(long userId, long contractEntryId,
		int start, int end, OrderByComparator<ContractAudit> orderByComparator) {
		return findByU_CEI(userId, contractEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contract audits
	 */
	@Override
	public List<ContractAudit> findByU_CEI(long userId, long contractEntryId,
		int start, int end, OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI;
			finderArgs = new Object[] { userId, contractEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_CEI;
			finderArgs = new Object[] {
					userId, contractEntryId,
					
					start, end, orderByComparator
				};
		}

		List<ContractAudit> list = null;

		if (retrieveFromCache) {
			list = (List<ContractAudit>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContractAudit contractAudit : list) {
					if ((userId != contractAudit.getUserId()) ||
							(contractEntryId != contractAudit.getContractEntryId())) {
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

			query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_U_CEI_USERID_2);

			query.append(_FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContractAuditModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(contractEntryId);

				if (!pagination) {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit
	 * @throws NoSuchContractAuditException if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit findByU_CEI_First(long userId, long contractEntryId,
		OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException {
		ContractAudit contractAudit = fetchByU_CEI_First(userId,
				contractEntryId, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", contractEntryId=");
		msg.append(contractEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit fetchByU_CEI_First(long userId, long contractEntryId,
		OrderByComparator<ContractAudit> orderByComparator) {
		List<ContractAudit> list = findByU_CEI(userId, contractEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit
	 * @throws NoSuchContractAuditException if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit findByU_CEI_Last(long userId, long contractEntryId,
		OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException {
		ContractAudit contractAudit = fetchByU_CEI_Last(userId,
				contractEntryId, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", contractEntryId=");
		msg.append(contractEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit fetchByU_CEI_Last(long userId, long contractEntryId,
		OrderByComparator<ContractAudit> orderByComparator) {
		int count = countByU_CEI(userId, contractEntryId);

		if (count == 0) {
			return null;
		}

		List<ContractAudit> list = findByU_CEI(userId, contractEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contract audits before and after the current contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param contractAuditId the primary key of the current contract audit
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contract audit
	 * @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit[] findByU_CEI_PrevAndNext(long contractAuditId,
		long userId, long contractEntryId,
		OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException {
		ContractAudit contractAudit = findByPrimaryKey(contractAuditId);

		Session session = null;

		try {
			session = openSession();

			ContractAudit[] array = new ContractAuditImpl[3];

			array[0] = getByU_CEI_PrevAndNext(session, contractAudit, userId,
					contractEntryId, orderByComparator, true);

			array[1] = contractAudit;

			array[2] = getByU_CEI_PrevAndNext(session, contractAudit, userId,
					contractEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContractAudit getByU_CEI_PrevAndNext(Session session,
		ContractAudit contractAudit, long userId, long contractEntryId,
		OrderByComparator<ContractAudit> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

		query.append(_FINDER_COLUMN_U_CEI_USERID_2);

		query.append(_FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2);

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
			query.append(ContractAuditModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(contractEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contractAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContractAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contract audits where userId = &#63; and contractEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 */
	@Override
	public void removeByU_CEI(long userId, long contractEntryId) {
		for (ContractAudit contractAudit : findByU_CEI(userId, contractEntryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contractAudit);
		}
	}

	/**
	 * Returns the number of contract audits where userId = &#63; and contractEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param contractEntryId the contract entry ID
	 * @return the number of matching contract audits
	 */
	@Override
	public int countByU_CEI(long userId, long contractEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_CEI;

		Object[] finderArgs = new Object[] { userId, contractEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_U_CEI_USERID_2);

			query.append(_FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(contractEntryId);

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

	private static final String _FINDER_COLUMN_U_CEI_USERID_2 = "contractAudit.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_CEI_CONTRACTENTRYID_2 = "contractAudit.contractEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CEI_SCN_SCP =
		new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCEI_SCN_SCP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP =
		new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED,
			ContractAuditImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCEI_SCN_SCP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ContractAuditModelImpl.CONTRACTENTRYID_COLUMN_BITMASK |
			ContractAuditModelImpl.SIGNATORYCLASSNAMEID_COLUMN_BITMASK |
			ContractAuditModelImpl.SIGNATORYCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CEI_SCN_SCP = new FinderPath(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCEI_SCN_SCP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @return the matching contract audits
	 */
	@Override
	public List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK) {
		return findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @return the range of matching contract audits
	 */
	@Override
	public List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end) {
		return findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contract audits
	 */
	@Override
	public List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end,
		OrderByComparator<ContractAudit> orderByComparator) {
		return findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contract audits
	 */
	@Override
	public List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end,
		OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP;
			finderArgs = new Object[] {
					contractEntryId, signatoryClassNameId, signatoryClassPK
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CEI_SCN_SCP;
			finderArgs = new Object[] {
					contractEntryId, signatoryClassNameId, signatoryClassPK,
					
					start, end, orderByComparator
				};
		}

		List<ContractAudit> list = null;

		if (retrieveFromCache) {
			list = (List<ContractAudit>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContractAudit contractAudit : list) {
					if ((contractEntryId != contractAudit.getContractEntryId()) ||
							(signatoryClassNameId != contractAudit.getSignatoryClassNameId()) ||
							(signatoryClassPK != contractAudit.getSignatoryClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContractAuditModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contractEntryId);

				qPos.add(signatoryClassNameId);

				qPos.add(signatoryClassPK);

				if (!pagination) {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit
	 * @throws NoSuchContractAuditException if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit findByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException {
		ContractAudit contractAudit = fetchByCEI_SCN_SCP_First(contractEntryId,
				signatoryClassNameId, signatoryClassPK, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractEntryId=");
		msg.append(contractEntryId);

		msg.append(", signatoryClassNameId=");
		msg.append(signatoryClassNameId);

		msg.append(", signatoryClassPK=");
		msg.append(signatoryClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit fetchByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator) {
		List<ContractAudit> list = findByCEI_SCN_SCP(contractEntryId,
				signatoryClassNameId, signatoryClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit
	 * @throws NoSuchContractAuditException if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit findByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException {
		ContractAudit contractAudit = fetchByCEI_SCN_SCP_Last(contractEntryId,
				signatoryClassNameId, signatoryClassPK, orderByComparator);

		if (contractAudit != null) {
			return contractAudit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractEntryId=");
		msg.append(contractEntryId);

		msg.append(", signatoryClassNameId=");
		msg.append(signatoryClassNameId);

		msg.append(", signatoryClassPK=");
		msg.append(signatoryClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractAuditException(msg.toString());
	}

	/**
	 * Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	 */
	@Override
	public ContractAudit fetchByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator) {
		int count = countByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
				signatoryClassPK);

		if (count == 0) {
			return null;
		}

		List<ContractAudit> list = findByCEI_SCN_SCP(contractEntryId,
				signatoryClassNameId, signatoryClassPK, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contract audits before and after the current contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractAuditId the primary key of the current contract audit
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contract audit
	 * @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit[] findByCEI_SCN_SCP_PrevAndNext(long contractAuditId,
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException {
		ContractAudit contractAudit = findByPrimaryKey(contractAuditId);

		Session session = null;

		try {
			session = openSession();

			ContractAudit[] array = new ContractAuditImpl[3];

			array[0] = getByCEI_SCN_SCP_PrevAndNext(session, contractAudit,
					contractEntryId, signatoryClassNameId, signatoryClassPK,
					orderByComparator, true);

			array[1] = contractAudit;

			array[2] = getByCEI_SCN_SCP_PrevAndNext(session, contractAudit,
					contractEntryId, signatoryClassNameId, signatoryClassPK,
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

	protected ContractAudit getByCEI_SCN_SCP_PrevAndNext(Session session,
		ContractAudit contractAudit, long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE);

		query.append(_FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2);

		query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2);

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
			query.append(ContractAuditModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contractEntryId);

		qPos.add(signatoryClassNameId);

		qPos.add(signatoryClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contractAudit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContractAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63; from the database.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 */
	@Override
	public void removeByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK) {
		for (ContractAudit contractAudit : findByCEI_SCN_SCP(contractEntryId,
				signatoryClassNameId, signatoryClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(contractAudit);
		}
	}

	/**
	 * Returns the number of contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	 *
	 * @param contractEntryId the contract entry ID
	 * @param signatoryClassNameId the signatory class name ID
	 * @param signatoryClassPK the signatory class pk
	 * @return the number of matching contract audits
	 */
	@Override
	public int countByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CEI_SCN_SCP;

		Object[] finderArgs = new Object[] {
				contractEntryId, signatoryClassNameId, signatoryClassPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CONTRACTAUDIT_WHERE);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contractEntryId);

				qPos.add(signatoryClassNameId);

				qPos.add(signatoryClassPK);

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

	private static final String _FINDER_COLUMN_CEI_SCN_SCP_CONTRACTENTRYID_2 = "contractAudit.contractEntryId = ? AND ";
	private static final String _FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSNAMEID_2 =
		"contractAudit.signatoryClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_CEI_SCN_SCP_SIGNATORYCLASSPK_2 = "contractAudit.signatoryClassPK = ?";

	public ContractAuditPersistenceImpl() {
		setModelClass(ContractAudit.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the contract audit in the entity cache if it is enabled.
	 *
	 * @param contractAudit the contract audit
	 */
	@Override
	public void cacheResult(ContractAudit contractAudit) {
		entityCache.putResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditImpl.class, contractAudit.getPrimaryKey(),
			contractAudit);

		contractAudit.resetOriginalValues();
	}

	/**
	 * Caches the contract audits in the entity cache if it is enabled.
	 *
	 * @param contractAudits the contract audits
	 */
	@Override
	public void cacheResult(List<ContractAudit> contractAudits) {
		for (ContractAudit contractAudit : contractAudits) {
			if (entityCache.getResult(
						ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
						ContractAuditImpl.class, contractAudit.getPrimaryKey()) == null) {
				cacheResult(contractAudit);
			}
			else {
				contractAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contract audits.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContractAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contract audit.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContractAudit contractAudit) {
		entityCache.removeResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditImpl.class, contractAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContractAudit> contractAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContractAudit contractAudit : contractAudits) {
			entityCache.removeResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
				ContractAuditImpl.class, contractAudit.getPrimaryKey());
		}
	}

	/**
	 * Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	 *
	 * @param contractAuditId the primary key for the new contract audit
	 * @return the new contract audit
	 */
	@Override
	public ContractAudit create(long contractAuditId) {
		ContractAudit contractAudit = new ContractAuditImpl();

		contractAudit.setNew(true);
		contractAudit.setPrimaryKey(contractAuditId);

		return contractAudit;
	}

	/**
	 * Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contractAuditId the primary key of the contract audit
	 * @return the contract audit that was removed
	 * @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit remove(long contractAuditId)
		throws NoSuchContractAuditException {
		return remove((Serializable)contractAuditId);
	}

	/**
	 * Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contract audit
	 * @return the contract audit that was removed
	 * @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit remove(Serializable primaryKey)
		throws NoSuchContractAuditException {
		Session session = null;

		try {
			session = openSession();

			ContractAudit contractAudit = (ContractAudit)session.get(ContractAuditImpl.class,
					primaryKey);

			if (contractAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContractAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contractAudit);
		}
		catch (NoSuchContractAuditException nsee) {
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
	protected ContractAudit removeImpl(ContractAudit contractAudit) {
		contractAudit = toUnwrappedModel(contractAudit);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contractAudit)) {
				contractAudit = (ContractAudit)session.get(ContractAuditImpl.class,
						contractAudit.getPrimaryKeyObj());
			}

			if (contractAudit != null) {
				session.delete(contractAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contractAudit != null) {
			clearCache(contractAudit);
		}

		return contractAudit;
	}

	@Override
	public ContractAudit updateImpl(ContractAudit contractAudit) {
		contractAudit = toUnwrappedModel(contractAudit);

		boolean isNew = contractAudit.isNew();

		ContractAuditModelImpl contractAuditModelImpl = (ContractAuditModelImpl)contractAudit;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (contractAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				contractAudit.setCreateDate(now);
			}
			else {
				contractAudit.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!contractAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				contractAudit.setModifiedDate(now);
			}
			else {
				contractAudit.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (contractAudit.isNew()) {
				session.save(contractAudit);

				contractAudit.setNew(false);
			}
			else {
				contractAudit = (ContractAudit)session.merge(contractAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContractAuditModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					contractAuditModelImpl.getUserId(),
					contractAuditModelImpl.getContractEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_CEI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI,
				args);

			args = new Object[] {
					contractAuditModelImpl.getContractEntryId(),
					contractAuditModelImpl.getSignatoryClassNameId(),
					contractAuditModelImpl.getSignatoryClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CEI_SCN_SCP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contractAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contractAuditModelImpl.getOriginalUserId(),
						contractAuditModelImpl.getOriginalContractEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_CEI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI,
					args);

				args = new Object[] {
						contractAuditModelImpl.getUserId(),
						contractAuditModelImpl.getContractEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_CEI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_CEI,
					args);
			}

			if ((contractAuditModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contractAuditModelImpl.getOriginalContractEntryId(),
						contractAuditModelImpl.getOriginalSignatoryClassNameId(),
						contractAuditModelImpl.getOriginalSignatoryClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CEI_SCN_SCP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP,
					args);

				args = new Object[] {
						contractAuditModelImpl.getContractEntryId(),
						contractAuditModelImpl.getSignatoryClassNameId(),
						contractAuditModelImpl.getSignatoryClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CEI_SCN_SCP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CEI_SCN_SCP,
					args);
			}
		}

		entityCache.putResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
			ContractAuditImpl.class, contractAudit.getPrimaryKey(),
			contractAudit, false);

		contractAudit.resetOriginalValues();

		return contractAudit;
	}

	protected ContractAudit toUnwrappedModel(ContractAudit contractAudit) {
		if (contractAudit instanceof ContractAuditImpl) {
			return contractAudit;
		}

		ContractAuditImpl contractAuditImpl = new ContractAuditImpl();

		contractAuditImpl.setNew(contractAudit.isNew());
		contractAuditImpl.setPrimaryKey(contractAudit.getPrimaryKey());

		contractAuditImpl.setContractAuditId(contractAudit.getContractAuditId());
		contractAuditImpl.setUserId(contractAudit.getUserId());
		contractAuditImpl.setUserName(contractAudit.getUserName());
		contractAuditImpl.setUserEmailAddress(contractAudit.getUserEmailAddress());
		contractAuditImpl.setCreateDate(contractAudit.getCreateDate());
		contractAuditImpl.setModifiedDate(contractAudit.getModifiedDate());
		contractAuditImpl.setContractEntryId(contractAudit.getContractEntryId());
		contractAuditImpl.setSignatoryClassNameId(contractAudit.getSignatoryClassNameId());
		contractAuditImpl.setSignatoryClassPK(contractAudit.getSignatoryClassPK());
		contractAuditImpl.setProductClassNameId(contractAudit.getProductClassNameId());
		contractAuditImpl.setProductClassPK(contractAudit.getProductClassPK());
		contractAuditImpl.setType(contractAudit.getType());
		contractAuditImpl.setLanguageId(contractAudit.getLanguageId());
		contractAuditImpl.setVersion(contractAudit.getVersion());

		return contractAuditImpl;
	}

	/**
	 * Returns the contract audit with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract audit
	 * @return the contract audit
	 * @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContractAuditException {
		ContractAudit contractAudit = fetchByPrimaryKey(primaryKey);

		if (contractAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContractAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contractAudit;
	}

	/**
	 * Returns the contract audit with the primary key or throws a {@link NoSuchContractAuditException} if it could not be found.
	 *
	 * @param contractAuditId the primary key of the contract audit
	 * @return the contract audit
	 * @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit findByPrimaryKey(long contractAuditId)
		throws NoSuchContractAuditException {
		return findByPrimaryKey((Serializable)contractAuditId);
	}

	/**
	 * Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract audit
	 * @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
				ContractAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContractAudit contractAudit = (ContractAudit)serializable;

		if (contractAudit == null) {
			Session session = null;

			try {
				session = openSession();

				contractAudit = (ContractAudit)session.get(ContractAuditImpl.class,
						primaryKey);

				if (contractAudit != null) {
					cacheResult(contractAudit);
				}
				else {
					entityCache.putResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
						ContractAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
					ContractAuditImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contractAudit;
	}

	/**
	 * Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contractAuditId the primary key of the contract audit
	 * @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	 */
	@Override
	public ContractAudit fetchByPrimaryKey(long contractAuditId) {
		return fetchByPrimaryKey((Serializable)contractAuditId);
	}

	@Override
	public Map<Serializable, ContractAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContractAudit> map = new HashMap<Serializable, ContractAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContractAudit contractAudit = fetchByPrimaryKey(primaryKey);

			if (contractAudit != null) {
				map.put(primaryKey, contractAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
					ContractAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContractAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTRACTAUDIT_WHERE_PKS_IN);

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

			for (ContractAudit contractAudit : (List<ContractAudit>)q.list()) {
				map.put(contractAudit.getPrimaryKeyObj(), contractAudit);

				cacheResult(contractAudit);

				uncachedPrimaryKeys.remove(contractAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContractAuditModelImpl.ENTITY_CACHE_ENABLED,
					ContractAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the contract audits.
	 *
	 * @return the contract audits
	 */
	@Override
	public List<ContractAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @return the range of contract audits
	 */
	@Override
	public List<ContractAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contract audits
	 */
	@Override
	public List<ContractAudit> findAll(int start, int end,
		OrderByComparator<ContractAudit> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract audits
	 * @param end the upper bound of the range of contract audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of contract audits
	 */
	@Override
	public List<ContractAudit> findAll(int start, int end,
		OrderByComparator<ContractAudit> orderByComparator,
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

		List<ContractAudit> list = null;

		if (retrieveFromCache) {
			list = (List<ContractAudit>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTRACTAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTRACTAUDIT;

				if (pagination) {
					sql = sql.concat(ContractAuditModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractAudit>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contract audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContractAudit contractAudit : findAll()) {
			remove(contractAudit);
		}
	}

	/**
	 * Returns the number of contract audits.
	 *
	 * @return the number of contract audits
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTRACTAUDIT);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ContractAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the contract audit persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContractAuditImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CONTRACTAUDIT = "SELECT contractAudit FROM ContractAudit contractAudit";
	private static final String _SQL_SELECT_CONTRACTAUDIT_WHERE_PKS_IN = "SELECT contractAudit FROM ContractAudit contractAudit WHERE contractAuditId IN (";
	private static final String _SQL_SELECT_CONTRACTAUDIT_WHERE = "SELECT contractAudit FROM ContractAudit contractAudit WHERE ";
	private static final String _SQL_COUNT_CONTRACTAUDIT = "SELECT COUNT(contractAudit) FROM ContractAudit contractAudit";
	private static final String _SQL_COUNT_CONTRACTAUDIT_WHERE = "SELECT COUNT(contractAudit) FROM ContractAudit contractAudit WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contractAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractAudit exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContractAudit exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContractAuditPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}
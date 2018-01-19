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

package com.liferay.pulpo.connector.de.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionImpl;
import com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionModelImpl;
import com.liferay.pulpo.connector.de.service.persistence.ConnectorTransactionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * The persistence implementation for the connector transaction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransactionPersistence
 * @see com.liferay.pulpo.connector.de.service.persistence.ConnectorTransactionUtil
 * @generated
 */
@ProviderType
public class ConnectorTransactionPersistenceImpl extends BasePersistenceImpl<ConnectorTransaction>
	implements ConnectorTransactionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ConnectorTransactionUtil} to access the connector transaction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ConnectorTransactionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ConnectorTransactionModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the connector transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the connector transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @return the range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the connector transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid(String uuid, int start,
		int end, OrderByComparator<ConnectorTransaction> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the connector transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid(String uuid, int start,
		int end, OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<ConnectorTransaction> list = null;

		if (retrieveFromCache) {
			list = (List<ConnectorTransaction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConnectorTransaction connectorTransaction : list) {
					if (!Objects.equals(uuid, connectorTransaction.getUuid())) {
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

			query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ConnectorTransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
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
	 * Returns the first connector transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByUuid_First(String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByUuid_First(uuid,
				orderByComparator);

		if (connectorTransaction != null) {
			return connectorTransaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchConnectorTransactionException(msg.toString());
	}

	/**
	 * Returns the first connector transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByUuid_First(String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		List<ConnectorTransaction> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last connector transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByUuid_Last(String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByUuid_Last(uuid,
				orderByComparator);

		if (connectorTransaction != null) {
			return connectorTransaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchConnectorTransactionException(msg.toString());
	}

	/**
	 * Returns the last connector transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByUuid_Last(String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ConnectorTransaction> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the connector transactions before and after the current connector transaction in the ordered set where uuid = &#63;.
	 *
	 * @param connectorTransactionId the primary key of the current connector transaction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next connector transaction
	 * @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction[] findByUuid_PrevAndNext(
		long connectorTransactionId, String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = findByPrimaryKey(connectorTransactionId);

		Session session = null;

		try {
			session = openSession();

			ConnectorTransaction[] array = new ConnectorTransactionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, connectorTransaction,
					uuid, orderByComparator, true);

			array[1] = connectorTransaction;

			array[2] = getByUuid_PrevAndNext(session, connectorTransaction,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ConnectorTransaction getByUuid_PrevAndNext(Session session,
		ConnectorTransaction connectorTransaction, String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(ConnectorTransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(connectorTransaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ConnectorTransaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the connector transactions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ConnectorTransaction connectorTransaction : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(connectorTransaction);
		}
	}

	/**
	 * Returns the number of connector transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching connector transactions
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONNECTORTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "connectorTransaction.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "connectorTransaction.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(connectorTransaction.uuid IS NULL OR connectorTransaction.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ConnectorTransactionModelImpl.UUID_COLUMN_BITMASK |
			ConnectorTransactionModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the connector transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the connector transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @return the range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the connector transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the connector transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<ConnectorTransaction> list = null;

		if (retrieveFromCache) {
			list = (List<ConnectorTransaction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConnectorTransaction connectorTransaction : list) {
					if (!Objects.equals(uuid, connectorTransaction.getUuid()) ||
							(companyId != connectorTransaction.getCompanyId())) {
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

			query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ConnectorTransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
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
	 * Returns the first connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (connectorTransaction != null) {
			return connectorTransaction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchConnectorTransactionException(msg.toString());
	}

	/**
	 * Returns the first connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		List<ConnectorTransaction> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (connectorTransaction != null) {
			return connectorTransaction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchConnectorTransactionException(msg.toString());
	}

	/**
	 * Returns the last connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ConnectorTransaction> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the connector transactions before and after the current connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param connectorTransactionId the primary key of the current connector transaction
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next connector transaction
	 * @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction[] findByUuid_C_PrevAndNext(
		long connectorTransactionId, String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = findByPrimaryKey(connectorTransactionId);

		Session session = null;

		try {
			session = openSession();

			ConnectorTransaction[] array = new ConnectorTransactionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, connectorTransaction,
					uuid, companyId, orderByComparator, true);

			array[1] = connectorTransaction;

			array[2] = getByUuid_C_PrevAndNext(session, connectorTransaction,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ConnectorTransaction getByUuid_C_PrevAndNext(Session session,
		ConnectorTransaction connectorTransaction, String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(ConnectorTransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(connectorTransaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ConnectorTransaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the connector transactions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ConnectorTransaction connectorTransaction : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(connectorTransaction);
		}
	}

	/**
	 * Returns the number of connector transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching connector transactions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONNECTORTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "connectorTransaction.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "connectorTransaction.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(connectorTransaction.uuid IS NULL OR connectorTransaction.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "connectorTransaction.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_C = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ConnectorTransactionModelImpl.COMPANYID_COLUMN_BITMASK |
			ConnectorTransactionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ConnectorTransactionModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_C = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK) {
		return findByC_C_C(companyId, classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @return the range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK, int start, int end) {
		return findByC_C_C(companyId, classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return findByC_C_C(companyId, classNameId, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C;
			finderArgs = new Object[] { companyId, classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_C;
			finderArgs = new Object[] {
					companyId, classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<ConnectorTransaction> list = null;

		if (retrieveFromCache) {
			list = (List<ConnectorTransaction>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConnectorTransaction connectorTransaction : list) {
					if ((companyId != connectorTransaction.getCompanyId()) ||
							(classNameId != connectorTransaction.getClassNameId()) ||
							(classPK != connectorTransaction.getClassPK())) {
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

			query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_C_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ConnectorTransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
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
	 * Returns the first connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByC_C_C_First(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByC_C_C_First(companyId,
				classNameId, classPK, orderByComparator);

		if (connectorTransaction != null) {
			return connectorTransaction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchConnectorTransactionException(msg.toString());
	}

	/**
	 * Returns the first connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByC_C_C_First(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		List<ConnectorTransaction> list = findByC_C_C(companyId, classNameId,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByC_C_C_Last(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByC_C_C_Last(companyId,
				classNameId, classPK, orderByComparator);

		if (connectorTransaction != null) {
			return connectorTransaction;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchConnectorTransactionException(msg.toString());
	}

	/**
	 * Returns the last connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByC_C_C_Last(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		int count = countByC_C_C(companyId, classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<ConnectorTransaction> list = findByC_C_C(companyId, classNameId,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the connector transactions before and after the current connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param connectorTransactionId the primary key of the current connector transaction
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next connector transaction
	 * @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction[] findByC_C_C_PrevAndNext(
		long connectorTransactionId, long companyId, long classNameId,
		long classPK, OrderByComparator<ConnectorTransaction> orderByComparator)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = findByPrimaryKey(connectorTransactionId);

		Session session = null;

		try {
			session = openSession();

			ConnectorTransaction[] array = new ConnectorTransactionImpl[3];

			array[0] = getByC_C_C_PrevAndNext(session, connectorTransaction,
					companyId, classNameId, classPK, orderByComparator, true);

			array[1] = connectorTransaction;

			array[2] = getByC_C_C_PrevAndNext(session, connectorTransaction,
					companyId, classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ConnectorTransaction getByC_C_C_PrevAndNext(Session session,
		ConnectorTransaction connectorTransaction, long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

		query.append(_FINDER_COLUMN_C_C_C_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_C_CLASSPK_2);

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
			query.append(ConnectorTransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(connectorTransaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ConnectorTransaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C_C(long companyId, long classNameId, long classPK) {
		for (ConnectorTransaction connectorTransaction : findByC_C_C(
				companyId, classNameId, classPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(connectorTransaction);
		}
	}

	/**
	 * Returns the number of connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching connector transactions
	 */
	@Override
	public int countByC_C_C(long companyId, long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_C;

		Object[] finderArgs = new Object[] { companyId, classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CONNECTORTRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_C_C_C_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_C_C_C_COMPANYID_2 = "connectorTransaction.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_CLASSNAMEID_2 = "connectorTransaction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_CLASSPK_2 = "connectorTransaction.classPK = ?";

	public ConnectorTransactionPersistenceImpl() {
		setModelClass(ConnectorTransaction.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the connector transaction in the entity cache if it is enabled.
	 *
	 * @param connectorTransaction the connector transaction
	 */
	@Override
	public void cacheResult(ConnectorTransaction connectorTransaction) {
		entityCache.putResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			connectorTransaction.getPrimaryKey(), connectorTransaction);

		connectorTransaction.resetOriginalValues();
	}

	/**
	 * Caches the connector transactions in the entity cache if it is enabled.
	 *
	 * @param connectorTransactions the connector transactions
	 */
	@Override
	public void cacheResult(List<ConnectorTransaction> connectorTransactions) {
		for (ConnectorTransaction connectorTransaction : connectorTransactions) {
			if (entityCache.getResult(
						ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
						ConnectorTransactionImpl.class,
						connectorTransaction.getPrimaryKey()) == null) {
				cacheResult(connectorTransaction);
			}
			else {
				connectorTransaction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all connector transactions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ConnectorTransactionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the connector transaction.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ConnectorTransaction connectorTransaction) {
		entityCache.removeResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionImpl.class, connectorTransaction.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ConnectorTransaction> connectorTransactions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ConnectorTransaction connectorTransaction : connectorTransactions) {
			entityCache.removeResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
				ConnectorTransactionImpl.class,
				connectorTransaction.getPrimaryKey());
		}
	}

	/**
	 * Creates a new connector transaction with the primary key. Does not add the connector transaction to the database.
	 *
	 * @param connectorTransactionId the primary key for the new connector transaction
	 * @return the new connector transaction
	 */
	@Override
	public ConnectorTransaction create(long connectorTransactionId) {
		ConnectorTransaction connectorTransaction = new ConnectorTransactionImpl();

		connectorTransaction.setNew(true);
		connectorTransaction.setPrimaryKey(connectorTransactionId);

		String uuid = PortalUUIDUtil.generate();

		connectorTransaction.setUuid(uuid);

		connectorTransaction.setCompanyId(companyProvider.getCompanyId());

		return connectorTransaction;
	}

	/**
	 * Removes the connector transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param connectorTransactionId the primary key of the connector transaction
	 * @return the connector transaction that was removed
	 * @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction remove(long connectorTransactionId)
		throws NoSuchConnectorTransactionException {
		return remove((Serializable)connectorTransactionId);
	}

	/**
	 * Removes the connector transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the connector transaction
	 * @return the connector transaction that was removed
	 * @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction remove(Serializable primaryKey)
		throws NoSuchConnectorTransactionException {
		Session session = null;

		try {
			session = openSession();

			ConnectorTransaction connectorTransaction = (ConnectorTransaction)session.get(ConnectorTransactionImpl.class,
					primaryKey);

			if (connectorTransaction == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConnectorTransactionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(connectorTransaction);
		}
		catch (NoSuchConnectorTransactionException nsee) {
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
	protected ConnectorTransaction removeImpl(
		ConnectorTransaction connectorTransaction) {
		connectorTransaction = toUnwrappedModel(connectorTransaction);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(connectorTransaction)) {
				connectorTransaction = (ConnectorTransaction)session.get(ConnectorTransactionImpl.class,
						connectorTransaction.getPrimaryKeyObj());
			}

			if (connectorTransaction != null) {
				session.delete(connectorTransaction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (connectorTransaction != null) {
			clearCache(connectorTransaction);
		}

		return connectorTransaction;
	}

	@Override
	public ConnectorTransaction updateImpl(
		ConnectorTransaction connectorTransaction) {
		connectorTransaction = toUnwrappedModel(connectorTransaction);

		boolean isNew = connectorTransaction.isNew();

		ConnectorTransactionModelImpl connectorTransactionModelImpl = (ConnectorTransactionModelImpl)connectorTransaction;

		if (Validator.isNull(connectorTransaction.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			connectorTransaction.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (connectorTransaction.getCreateDate() == null)) {
			if (serviceContext == null) {
				connectorTransaction.setCreateDate(now);
			}
			else {
				connectorTransaction.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!connectorTransactionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				connectorTransaction.setModifiedDate(now);
			}
			else {
				connectorTransaction.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (connectorTransaction.isNew()) {
				session.save(connectorTransaction);

				connectorTransaction.setNew(false);
			}
			else {
				connectorTransaction = (ConnectorTransaction)session.merge(connectorTransaction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ConnectorTransactionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { connectorTransactionModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					connectorTransactionModelImpl.getUuid(),
					connectorTransactionModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					connectorTransactionModelImpl.getCompanyId(),
					connectorTransactionModelImpl.getClassNameId(),
					connectorTransactionModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((connectorTransactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						connectorTransactionModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { connectorTransactionModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((connectorTransactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						connectorTransactionModelImpl.getOriginalUuid(),
						connectorTransactionModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						connectorTransactionModelImpl.getUuid(),
						connectorTransactionModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((connectorTransactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						connectorTransactionModelImpl.getOriginalCompanyId(),
						connectorTransactionModelImpl.getOriginalClassNameId(),
						connectorTransactionModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C,
					args);

				args = new Object[] {
						connectorTransactionModelImpl.getCompanyId(),
						connectorTransactionModelImpl.getClassNameId(),
						connectorTransactionModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_C,
					args);
			}
		}

		entityCache.putResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			connectorTransaction.getPrimaryKey(), connectorTransaction, false);

		connectorTransaction.resetOriginalValues();

		return connectorTransaction;
	}

	protected ConnectorTransaction toUnwrappedModel(
		ConnectorTransaction connectorTransaction) {
		if (connectorTransaction instanceof ConnectorTransactionImpl) {
			return connectorTransaction;
		}

		ConnectorTransactionImpl connectorTransactionImpl = new ConnectorTransactionImpl();

		connectorTransactionImpl.setNew(connectorTransaction.isNew());
		connectorTransactionImpl.setPrimaryKey(connectorTransaction.getPrimaryKey());

		connectorTransactionImpl.setUuid(connectorTransaction.getUuid());
		connectorTransactionImpl.setConnectorTransactionId(connectorTransaction.getConnectorTransactionId());
		connectorTransactionImpl.setCompanyId(connectorTransaction.getCompanyId());
		connectorTransactionImpl.setUserId(connectorTransaction.getUserId());
		connectorTransactionImpl.setUserName(connectorTransaction.getUserName());
		connectorTransactionImpl.setCreateDate(connectorTransaction.getCreateDate());
		connectorTransactionImpl.setModifiedDate(connectorTransaction.getModifiedDate());
		connectorTransactionImpl.setClassNameId(connectorTransaction.getClassNameId());
		connectorTransactionImpl.setClassPK(connectorTransaction.getClassPK());
		connectorTransactionImpl.setOperation(connectorTransaction.getOperation());
		connectorTransactionImpl.setStatus(connectorTransaction.getStatus());

		return connectorTransactionImpl;
	}

	/**
	 * Returns the connector transaction with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the connector transaction
	 * @return the connector transaction
	 * @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByPrimaryKey(primaryKey);

		if (connectorTransaction == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConnectorTransactionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return connectorTransaction;
	}

	/**
	 * Returns the connector transaction with the primary key or throws a {@link NoSuchConnectorTransactionException} if it could not be found.
	 *
	 * @param connectorTransactionId the primary key of the connector transaction
	 * @return the connector transaction
	 * @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction findByPrimaryKey(long connectorTransactionId)
		throws NoSuchConnectorTransactionException {
		return findByPrimaryKey((Serializable)connectorTransactionId);
	}

	/**
	 * Returns the connector transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the connector transaction
	 * @return the connector transaction, or <code>null</code> if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
				ConnectorTransactionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ConnectorTransaction connectorTransaction = (ConnectorTransaction)serializable;

		if (connectorTransaction == null) {
			Session session = null;

			try {
				session = openSession();

				connectorTransaction = (ConnectorTransaction)session.get(ConnectorTransactionImpl.class,
						primaryKey);

				if (connectorTransaction != null) {
					cacheResult(connectorTransaction);
				}
				else {
					entityCache.putResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
						ConnectorTransactionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
					ConnectorTransactionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return connectorTransaction;
	}

	/**
	 * Returns the connector transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param connectorTransactionId the primary key of the connector transaction
	 * @return the connector transaction, or <code>null</code> if a connector transaction with the primary key could not be found
	 */
	@Override
	public ConnectorTransaction fetchByPrimaryKey(long connectorTransactionId) {
		return fetchByPrimaryKey((Serializable)connectorTransactionId);
	}

	@Override
	public Map<Serializable, ConnectorTransaction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ConnectorTransaction> map = new HashMap<Serializable, ConnectorTransaction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ConnectorTransaction connectorTransaction = fetchByPrimaryKey(primaryKey);

			if (connectorTransaction != null) {
				map.put(primaryKey, connectorTransaction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
					ConnectorTransactionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ConnectorTransaction)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ConnectorTransaction connectorTransaction : (List<ConnectorTransaction>)q.list()) {
				map.put(connectorTransaction.getPrimaryKeyObj(),
					connectorTransaction);

				cacheResult(connectorTransaction);

				uncachedPrimaryKeys.remove(connectorTransaction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
					ConnectorTransactionImpl.class, primaryKey, nullModel);
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
	 * Returns all the connector transactions.
	 *
	 * @return the connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the connector transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @return the range of connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the connector transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findAll(int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the connector transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of connector transactions
	 * @param end the upper bound of the range of connector transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of connector transactions
	 */
	@Override
	public List<ConnectorTransaction> findAll(int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator,
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

		List<ConnectorTransaction> list = null;

		if (retrieveFromCache) {
			list = (List<ConnectorTransaction>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONNECTORTRANSACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONNECTORTRANSACTION;

				if (pagination) {
					sql = sql.concat(ConnectorTransactionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ConnectorTransaction>)QueryUtil.list(q,
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
	 * Removes all the connector transactions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ConnectorTransaction connectorTransaction : findAll()) {
			remove(connectorTransaction);
		}
	}

	/**
	 * Returns the number of connector transactions.
	 *
	 * @return the number of connector transactions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONNECTORTRANSACTION);

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
		return ConnectorTransactionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the connector transaction persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ConnectorTransactionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONNECTORTRANSACTION = "SELECT connectorTransaction FROM ConnectorTransaction connectorTransaction";
	private static final String _SQL_SELECT_CONNECTORTRANSACTION_WHERE_PKS_IN = "SELECT connectorTransaction FROM ConnectorTransaction connectorTransaction WHERE connectorTransactionId IN (";
	private static final String _SQL_SELECT_CONNECTORTRANSACTION_WHERE = "SELECT connectorTransaction FROM ConnectorTransaction connectorTransaction WHERE ";
	private static final String _SQL_COUNT_CONNECTORTRANSACTION = "SELECT COUNT(connectorTransaction) FROM ConnectorTransaction connectorTransaction";
	private static final String _SQL_COUNT_CONNECTORTRANSACTION_WHERE = "SELECT COUNT(connectorTransaction) FROM ConnectorTransaction connectorTransaction WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "connectorTransaction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ConnectorTransaction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ConnectorTransaction exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ConnectorTransactionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}
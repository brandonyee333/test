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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionImpl;
import com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionModelImpl;
import com.liferay.pulpo.connector.de.service.persistence.ConnectorTransactionPersistence;

import java.io.Serializable;

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
	public static final FinderPath FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID =
		new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByConnectorTransactionUUID",
			new String[] { String.class.getName() },
			ConnectorTransactionModelImpl.CONNECTORTRANSACTIONUUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONNECTORTRANSACTIONUUID =
		new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByConnectorTransactionUUID",
			new String[] { String.class.getName() });

	/**
	 * Returns the connector transaction where connectorTransactionUuid = &#63; or throws a {@link NoSuchConnectorTransactionException} if it could not be found.
	 *
	 * @param connectorTransactionUuid the connector transaction uuid
	 * @return the matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByConnectorTransactionUUID(
		String connectorTransactionUuid)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByConnectorTransactionUUID(connectorTransactionUuid);

		if (connectorTransaction == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("connectorTransactionUuid=");
			msg.append(connectorTransactionUuid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchConnectorTransactionException(msg.toString());
		}

		return connectorTransaction;
	}

	/**
	 * Returns the connector transaction where connectorTransactionUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param connectorTransactionUuid the connector transaction uuid
	 * @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByConnectorTransactionUUID(
		String connectorTransactionUuid) {
		return fetchByConnectorTransactionUUID(connectorTransactionUuid, true);
	}

	/**
	 * Returns the connector transaction where connectorTransactionUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param connectorTransactionUuid the connector transaction uuid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByConnectorTransactionUUID(
		String connectorTransactionUuid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { connectorTransactionUuid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
					finderArgs, this);
		}

		if (result instanceof ConnectorTransaction) {
			ConnectorTransaction connectorTransaction = (ConnectorTransaction)result;

			if (!Objects.equals(connectorTransactionUuid,
						connectorTransaction.getConnectorTransactionUuid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

			boolean bindConnectorTransactionUuid = false;

			if (connectorTransactionUuid == null) {
				query.append(_FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_1);
			}
			else if (connectorTransactionUuid.equals("")) {
				query.append(_FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_3);
			}
			else {
				bindConnectorTransactionUuid = true;

				query.append(_FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindConnectorTransactionUuid) {
					qPos.add(connectorTransactionUuid);
				}

				List<ConnectorTransaction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
						finderArgs, list);
				}
				else {
					ConnectorTransaction connectorTransaction = list.get(0);

					result = connectorTransaction;

					cacheResult(connectorTransaction);

					if ((connectorTransaction.getConnectorTransactionUuid() == null) ||
							!connectorTransaction.getConnectorTransactionUuid()
													 .equals(connectorTransactionUuid)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
							finderArgs, connectorTransaction);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
					finderArgs);

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
			return (ConnectorTransaction)result;
		}
	}

	/**
	 * Removes the connector transaction where connectorTransactionUuid = &#63; from the database.
	 *
	 * @param connectorTransactionUuid the connector transaction uuid
	 * @return the connector transaction that was removed
	 */
	@Override
	public ConnectorTransaction removeByConnectorTransactionUUID(
		String connectorTransactionUuid)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = findByConnectorTransactionUUID(connectorTransactionUuid);

		return remove(connectorTransaction);
	}

	/**
	 * Returns the number of connector transactions where connectorTransactionUuid = &#63;.
	 *
	 * @param connectorTransactionUuid the connector transaction uuid
	 * @return the number of matching connector transactions
	 */
	@Override
	public int countByConnectorTransactionUUID(String connectorTransactionUuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONNECTORTRANSACTIONUUID;

		Object[] finderArgs = new Object[] { connectorTransactionUuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONNECTORTRANSACTION_WHERE);

			boolean bindConnectorTransactionUuid = false;

			if (connectorTransactionUuid == null) {
				query.append(_FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_1);
			}
			else if (connectorTransactionUuid.equals("")) {
				query.append(_FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_3);
			}
			else {
				bindConnectorTransactionUuid = true;

				query.append(_FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindConnectorTransactionUuid) {
					qPos.add(connectorTransactionUuid);
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

	private static final String _FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_1 =
		"connectorTransaction.connectorTransactionUuid IS NULL";
	private static final String _FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_2 =
		"connectorTransaction.connectorTransactionUuid = ?";
	private static final String _FINDER_COLUMN_CONNECTORTRANSACTIONUUID_CONNECTORTRANSACTIONUUID_3 =
		"(connectorTransaction.connectorTransactionUuid IS NULL OR connectorTransaction.connectorTransactionUuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED,
			ConnectorTransactionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			ConnectorTransactionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ConnectorTransactionModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the connector transaction where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchConnectorTransactionException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching connector transaction
	 * @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction findByC_C(long classNameId, long classPK)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = fetchByC_C(classNameId,
				classPK);

		if (connectorTransaction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchConnectorTransactionException(msg.toString());
		}

		return connectorTransaction;
	}

	/**
	 * Returns the connector transaction where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByC_C(long classNameId, long classPK) {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the connector transaction where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	 */
	@Override
	public ConnectorTransaction fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result instanceof ConnectorTransaction) {
			ConnectorTransaction connectorTransaction = (ConnectorTransaction)result;

			if ((classNameId != connectorTransaction.getClassNameId()) ||
					(classPK != connectorTransaction.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CONNECTORTRANSACTION_WHERE);

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

				List<ConnectorTransaction> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_C, finderArgs,
						list);
				}
				else {
					ConnectorTransaction connectorTransaction = list.get(0);

					result = connectorTransaction;

					cacheResult(connectorTransaction);

					if ((connectorTransaction.getClassNameId() != classNameId) ||
							(connectorTransaction.getClassPK() != classPK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, connectorTransaction);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C, finderArgs);

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
			return (ConnectorTransaction)result;
		}
	}

	/**
	 * Removes the connector transaction where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the connector transaction that was removed
	 */
	@Override
	public ConnectorTransaction removeByC_C(long classNameId, long classPK)
		throws NoSuchConnectorTransactionException {
		ConnectorTransaction connectorTransaction = findByC_C(classNameId,
				classPK);

		return remove(connectorTransaction);
	}

	/**
	 * Returns the number of connector transactions where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching connector transactions
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CONNECTORTRANSACTION_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "connectorTransaction.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "connectorTransaction.classPK = ?";

	public ConnectorTransactionPersistenceImpl() {
		setModelClass(ConnectorTransaction.class);
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

		finderCache.putResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
			new Object[] { connectorTransaction.getConnectorTransactionUuid() },
			connectorTransaction);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				connectorTransaction.getClassNameId(),
				connectorTransaction.getClassPK()
			}, connectorTransaction);

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

		clearUniqueFindersCache((ConnectorTransactionModelImpl)connectorTransaction,
			true);
	}

	@Override
	public void clearCache(List<ConnectorTransaction> connectorTransactions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ConnectorTransaction connectorTransaction : connectorTransactions) {
			entityCache.removeResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
				ConnectorTransactionImpl.class,
				connectorTransaction.getPrimaryKey());

			clearUniqueFindersCache((ConnectorTransactionModelImpl)connectorTransaction,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ConnectorTransactionModelImpl connectorTransactionModelImpl) {
		Object[] args = new Object[] {
				connectorTransactionModelImpl.getConnectorTransactionUuid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CONNECTORTRANSACTIONUUID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
			args, connectorTransactionModelImpl, false);

		args = new Object[] {
				connectorTransactionModelImpl.getClassNameId(),
				connectorTransactionModelImpl.getClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_C, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C, args,
			connectorTransactionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ConnectorTransactionModelImpl connectorTransactionModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					connectorTransactionModelImpl.getConnectorTransactionUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONNECTORTRANSACTIONUUID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
				args);
		}

		if ((connectorTransactionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					connectorTransactionModelImpl.getOriginalConnectorTransactionUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONNECTORTRANSACTIONUUID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CONNECTORTRANSACTIONUUID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					connectorTransactionModelImpl.getClassNameId(),
					connectorTransactionModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
		}

		if ((connectorTransactionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					connectorTransactionModelImpl.getOriginalClassNameId(),
					connectorTransactionModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
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
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(ConnectorTransactionModelImpl.ENTITY_CACHE_ENABLED,
			ConnectorTransactionImpl.class,
			connectorTransaction.getPrimaryKey(), connectorTransaction, false);

		clearUniqueFindersCache(connectorTransactionModelImpl, false);
		cacheUniqueFindersCache(connectorTransactionModelImpl);

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

		connectorTransactionImpl.setConnectorTransactionId(connectorTransaction.getConnectorTransactionId());
		connectorTransactionImpl.setCompanyId(connectorTransaction.getCompanyId());
		connectorTransactionImpl.setUserId(connectorTransaction.getUserId());
		connectorTransactionImpl.setUserName(connectorTransaction.getUserName());
		connectorTransactionImpl.setCreateDate(connectorTransaction.getCreateDate());
		connectorTransactionImpl.setModifiedDate(connectorTransaction.getModifiedDate());
		connectorTransactionImpl.setClassNameId(connectorTransaction.getClassNameId());
		connectorTransactionImpl.setClassPK(connectorTransaction.getClassPK());
		connectorTransactionImpl.setConnectorTransactionUuid(connectorTransaction.getConnectorTransactionUuid());
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
}
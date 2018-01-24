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

package com.liferay.pulpo.connector.de.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;

/**
 * The persistence interface for the connector transaction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.pulpo.connector.de.service.persistence.impl.ConnectorTransactionPersistenceImpl
 * @see ConnectorTransactionUtil
 * @generated
 */
@ProviderType
public interface ConnectorTransactionPersistence extends BasePersistence<ConnectorTransaction> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ConnectorTransactionUtil} to access the connector transaction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the connector transaction where connectorTransactionUuid = &#63; or throws a {@link NoSuchConnectorTransactionException} if it could not be found.
	*
	* @param connectorTransactionUuid the connector transaction uuid
	* @return the matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public ConnectorTransaction findByConnectorTransactionUUID(
		java.lang.String connectorTransactionUuid)
		throws NoSuchConnectorTransactionException;

	/**
	* Returns the connector transaction where connectorTransactionUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param connectorTransactionUuid the connector transaction uuid
	* @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public ConnectorTransaction fetchByConnectorTransactionUUID(
		java.lang.String connectorTransactionUuid);

	/**
	* Returns the connector transaction where connectorTransactionUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param connectorTransactionUuid the connector transaction uuid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public ConnectorTransaction fetchByConnectorTransactionUUID(
		java.lang.String connectorTransactionUuid, boolean retrieveFromCache);

	/**
	* Removes the connector transaction where connectorTransactionUuid = &#63; from the database.
	*
	* @param connectorTransactionUuid the connector transaction uuid
	* @return the connector transaction that was removed
	*/
	public ConnectorTransaction removeByConnectorTransactionUUID(
		java.lang.String connectorTransactionUuid)
		throws NoSuchConnectorTransactionException;

	/**
	* Returns the number of connector transactions where connectorTransactionUuid = &#63;.
	*
	* @param connectorTransactionUuid the connector transaction uuid
	* @return the number of matching connector transactions
	*/
	public int countByConnectorTransactionUUID(
		java.lang.String connectorTransactionUuid);

	/**
	* Returns the connector transaction where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchConnectorTransactionException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public ConnectorTransaction findByC_C(long classNameId, long classPK)
		throws NoSuchConnectorTransactionException;

	/**
	* Returns the connector transaction where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public ConnectorTransaction fetchByC_C(long classNameId, long classPK);

	/**
	* Returns the connector transaction where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public ConnectorTransaction fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache);

	/**
	* Removes the connector transaction where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the connector transaction that was removed
	*/
	public ConnectorTransaction removeByC_C(long classNameId, long classPK)
		throws NoSuchConnectorTransactionException;

	/**
	* Returns the number of connector transactions where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching connector transactions
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Caches the connector transaction in the entity cache if it is enabled.
	*
	* @param connectorTransaction the connector transaction
	*/
	public void cacheResult(ConnectorTransaction connectorTransaction);

	/**
	* Caches the connector transactions in the entity cache if it is enabled.
	*
	* @param connectorTransactions the connector transactions
	*/
	public void cacheResult(
		java.util.List<ConnectorTransaction> connectorTransactions);

	/**
	* Creates a new connector transaction with the primary key. Does not add the connector transaction to the database.
	*
	* @param connectorTransactionId the primary key for the new connector transaction
	* @return the new connector transaction
	*/
	public ConnectorTransaction create(long connectorTransactionId);

	/**
	* Removes the connector transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction that was removed
	* @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	*/
	public ConnectorTransaction remove(long connectorTransactionId)
		throws NoSuchConnectorTransactionException;

	public ConnectorTransaction updateImpl(
		ConnectorTransaction connectorTransaction);

	/**
	* Returns the connector transaction with the primary key or throws a {@link NoSuchConnectorTransactionException} if it could not be found.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction
	* @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	*/
	public ConnectorTransaction findByPrimaryKey(long connectorTransactionId)
		throws NoSuchConnectorTransactionException;

	/**
	* Returns the connector transaction with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction, or <code>null</code> if a connector transaction with the primary key could not be found
	*/
	public ConnectorTransaction fetchByPrimaryKey(long connectorTransactionId);

	@Override
	public java.util.Map<java.io.Serializable, ConnectorTransaction> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the connector transactions.
	*
	* @return the connector transactions
	*/
	public java.util.List<ConnectorTransaction> findAll();

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
	public java.util.List<ConnectorTransaction> findAll(int start, int end);

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
	public java.util.List<ConnectorTransaction> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConnectorTransaction> orderByComparator);

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
	public java.util.List<ConnectorTransaction> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the connector transactions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of connector transactions.
	*
	* @return the number of connector transactions
	*/
	public int countAll();
}
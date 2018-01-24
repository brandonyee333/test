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

package com.liferay.pulpo.connector.de.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ConnectorTransactionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransactionLocalService
 * @generated
 */
@ProviderType
public class ConnectorTransactionLocalServiceWrapper
	implements ConnectorTransactionLocalService,
		ServiceWrapper<ConnectorTransactionLocalService> {
	public ConnectorTransactionLocalServiceWrapper(
		ConnectorTransactionLocalService connectorTransactionLocalService) {
		_connectorTransactionLocalService = connectorTransactionLocalService;
	}

	/**
	* Adds the connector transaction to the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransaction the connector transaction
	* @return the connector transaction that was added
	*/
	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction addConnectorTransaction(
		com.liferay.pulpo.connector.de.model.ConnectorTransaction connectorTransaction) {
		return _connectorTransactionLocalService.addConnectorTransaction(connectorTransaction);
	}

	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction addConnectorTransaction(
		long userId, long classNameId, long classPK, java.lang.String status,
		java.lang.String operation)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _connectorTransactionLocalService.addConnectorTransaction(userId,
			classNameId, classPK, status, operation);
	}

	/**
	* Creates a new connector transaction with the primary key. Does not add the connector transaction to the database.
	*
	* @param connectorTransactionId the primary key for the new connector transaction
	* @return the new connector transaction
	*/
	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction createConnectorTransaction(
		long connectorTransactionId) {
		return _connectorTransactionLocalService.createConnectorTransaction(connectorTransactionId);
	}

	/**
	* Deletes the connector transaction from the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransaction the connector transaction
	* @return the connector transaction that was removed
	*/
	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction deleteConnectorTransaction(
		com.liferay.pulpo.connector.de.model.ConnectorTransaction connectorTransaction) {
		return _connectorTransactionLocalService.deleteConnectorTransaction(connectorTransaction);
	}

	/**
	* Deletes the connector transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction that was removed
	* @throws PortalException if a connector transaction with the primary key could not be found
	*/
	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction deleteConnectorTransaction(
		long connectorTransactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _connectorTransactionLocalService.deleteConnectorTransaction(connectorTransactionId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _connectorTransactionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _connectorTransactionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _connectorTransactionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _connectorTransactionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _connectorTransactionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _connectorTransactionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _connectorTransactionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction fetchConnectorTransaction(
		long connectorTransactionId) {
		return _connectorTransactionLocalService.fetchConnectorTransaction(connectorTransactionId);
	}

	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction fetchConnectorTransaction(
		java.lang.String connectorTransactionUuid) {
		return _connectorTransactionLocalService.fetchConnectorTransaction(connectorTransactionUuid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _connectorTransactionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the connector transaction with the primary key.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction
	* @throws PortalException if a connector transaction with the primary key could not be found
	*/
	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction getConnectorTransaction(
		long connectorTransactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _connectorTransactionLocalService.getConnectorTransaction(connectorTransactionId);
	}

	/**
	* Returns a range of all the connector transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @return the range of connector transactions
	*/
	@Override
	public java.util.List<com.liferay.pulpo.connector.de.model.ConnectorTransaction> getConnectorTransactions(
		int start, int end) {
		return _connectorTransactionLocalService.getConnectorTransactions(start,
			end);
	}

	/**
	* Returns the number of connector transactions.
	*
	* @return the number of connector transactions
	*/
	@Override
	public int getConnectorTransactionsCount() {
		return _connectorTransactionLocalService.getConnectorTransactionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _connectorTransactionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _connectorTransactionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _connectorTransactionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the connector transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param connectorTransaction the connector transaction
	* @return the connector transaction that was updated
	*/
	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction updateConnectorTransaction(
		com.liferay.pulpo.connector.de.model.ConnectorTransaction connectorTransaction) {
		return _connectorTransactionLocalService.updateConnectorTransaction(connectorTransaction);
	}

	@Override
	public com.liferay.pulpo.connector.de.model.ConnectorTransaction updateConnectorTransaction(
		long connectorTransactionId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _connectorTransactionLocalService.updateConnectorTransaction(connectorTransactionId,
			status);
	}

	@Override
	public ConnectorTransactionLocalService getWrappedService() {
		return _connectorTransactionLocalService;
	}

	@Override
	public void setWrappedService(
		ConnectorTransactionLocalService connectorTransactionLocalService) {
		_connectorTransactionLocalService = connectorTransactionLocalService;
	}

	private ConnectorTransactionLocalService _connectorTransactionLocalService;
}
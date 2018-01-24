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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ConnectorTransaction. This utility wraps
 * {@link com.liferay.pulpo.connector.de.service.impl.ConnectorTransactionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransactionLocalService
 * @see com.liferay.pulpo.connector.de.service.base.ConnectorTransactionLocalServiceBaseImpl
 * @see com.liferay.pulpo.connector.de.service.impl.ConnectorTransactionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ConnectorTransactionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.pulpo.connector.de.service.impl.ConnectorTransactionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the connector transaction to the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransaction the connector transaction
	* @return the connector transaction that was added
	*/
	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction addConnectorTransaction(
		com.liferay.pulpo.connector.de.model.ConnectorTransaction connectorTransaction) {
		return getService().addConnectorTransaction(connectorTransaction);
	}

	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction addConnectorTransaction(
		long userId, long classNameId, long classPK, java.lang.String status,
		java.lang.String operation)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addConnectorTransaction(userId, classNameId, classPK,
			status, operation);
	}

	/**
	* Creates a new connector transaction with the primary key. Does not add the connector transaction to the database.
	*
	* @param connectorTransactionId the primary key for the new connector transaction
	* @return the new connector transaction
	*/
	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction createConnectorTransaction(
		long connectorTransactionId) {
		return getService().createConnectorTransaction(connectorTransactionId);
	}

	/**
	* Deletes the connector transaction from the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransaction the connector transaction
	* @return the connector transaction that was removed
	*/
	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction deleteConnectorTransaction(
		com.liferay.pulpo.connector.de.model.ConnectorTransaction connectorTransaction) {
		return getService().deleteConnectorTransaction(connectorTransaction);
	}

	/**
	* Deletes the connector transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction that was removed
	* @throws PortalException if a connector transaction with the primary key could not be found
	*/
	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction deleteConnectorTransaction(
		long connectorTransactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteConnectorTransaction(connectorTransactionId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction fetchConnectorTransaction(
		long connectorTransactionId) {
		return getService().fetchConnectorTransaction(connectorTransactionId);
	}

	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction fetchConnectorTransaction(
		java.lang.String connectorTransactionUuid) {
		return getService().fetchConnectorTransaction(connectorTransactionUuid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the connector transaction with the primary key.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction
	* @throws PortalException if a connector transaction with the primary key could not be found
	*/
	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction getConnectorTransaction(
		long connectorTransactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getConnectorTransaction(connectorTransactionId);
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
	public static java.util.List<com.liferay.pulpo.connector.de.model.ConnectorTransaction> getConnectorTransactions(
		int start, int end) {
		return getService().getConnectorTransactions(start, end);
	}

	/**
	* Returns the number of connector transactions.
	*
	* @return the number of connector transactions
	*/
	public static int getConnectorTransactionsCount() {
		return getService().getConnectorTransactionsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the connector transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param connectorTransaction the connector transaction
	* @return the connector transaction that was updated
	*/
	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction updateConnectorTransaction(
		com.liferay.pulpo.connector.de.model.ConnectorTransaction connectorTransaction) {
		return getService().updateConnectorTransaction(connectorTransaction);
	}

	public static com.liferay.pulpo.connector.de.model.ConnectorTransaction updateConnectorTransaction(
		long connectorTransactionId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateConnectorTransaction(connectorTransactionId, status);
	}

	public static ConnectorTransactionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ConnectorTransactionLocalService, ConnectorTransactionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ConnectorTransactionLocalService.class);
}
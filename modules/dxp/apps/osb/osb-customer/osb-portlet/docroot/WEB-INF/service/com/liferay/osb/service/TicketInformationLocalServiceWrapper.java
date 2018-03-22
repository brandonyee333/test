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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketInformationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformationLocalService
 * @generated
 */
@ProviderType
public class TicketInformationLocalServiceWrapper
	implements TicketInformationLocalService,
		ServiceWrapper<TicketInformationLocalService> {
	public TicketInformationLocalServiceWrapper(
		TicketInformationLocalService ticketInformationLocalService) {
		_ticketInformationLocalService = ticketInformationLocalService;
	}

	/**
	* Adds the ticket information to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformation the ticket information
	* @return the ticket information that was added
	*/
	@Override
	public com.liferay.osb.model.TicketInformation addTicketInformation(
		com.liferay.osb.model.TicketInformation ticketInformation) {
		return _ticketInformationLocalService.addTicketInformation(ticketInformation);
	}

	@Override
	public com.liferay.osb.model.TicketInformation addTicketInformation(
		long ticketEntryId, long fieldId, java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.addTicketInformation(ticketEntryId,
			fieldId, data);
	}

	/**
	* Creates a new ticket information with the primary key. Does not add the ticket information to the database.
	*
	* @param ticketInformationId the primary key for the new ticket information
	* @return the new ticket information
	*/
	@Override
	public com.liferay.osb.model.TicketInformation createTicketInformation(
		long ticketInformationId) {
		return _ticketInformationLocalService.createTicketInformation(ticketInformationId);
	}

	/**
	* Deletes the ticket information from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformation the ticket information
	* @return the ticket information that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketInformation deleteTicketInformation(
		com.liferay.osb.model.TicketInformation ticketInformation) {
		return _ticketInformationLocalService.deleteTicketInformation(ticketInformation);
	}

	/**
	* Deletes the ticket information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information that was removed
	* @throws PortalException if a ticket information with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketInformation deleteTicketInformation(
		long ticketInformationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.deleteTicketInformation(ticketInformationId);
	}

	@Override
	public com.liferay.osb.model.TicketInformation fetchTicketInformation(
		long ticketInformationId) {
		return _ticketInformationLocalService.fetchTicketInformation(ticketInformationId);
	}

	/**
	* Returns the ticket information with the primary key.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information
	* @throws PortalException if a ticket information with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketInformation getTicketInformation(
		long ticketInformationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.getTicketInformation(ticketInformationId);
	}

	/**
	* Updates the ticket information in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketInformation the ticket information
	* @return the ticket information that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketInformation updateTicketInformation(
		com.liferay.osb.model.TicketInformation ticketInformation) {
		return _ticketInformationLocalService.updateTicketInformation(ticketInformation);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketInformationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketInformationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketInformationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ticket informations.
	*
	* @return the number of ticket informations
	*/
	@Override
	public int getTicketInformationsCount() {
		return _ticketInformationLocalService.getTicketInformationsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketInformationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.lang.String getData(long ticketEntryId, long fieldId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.getData(ticketEntryId, fieldId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketInformationLocalService.getOSGiServiceIdentifier();
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
		return _ticketInformationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketInformationLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketInformationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketInformation> getTicketInformationList(
		long ticketEntryId) {
		return _ticketInformationLocalService.getTicketInformationList(ticketEntryId);
	}

	/**
	* Returns a range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of ticket informations
	*/
	@Override
	public java.util.List<com.liferay.osb.model.TicketInformation> getTicketInformations(
		int start, int end) {
		return _ticketInformationLocalService.getTicketInformations(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketInformation> updateTicketInformation(
		long ticketEntryId,
		java.util.Map<java.lang.Long, java.lang.String> fieldsMap)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.updateTicketInformation(ticketEntryId,
			fieldsMap);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketInformation> updateTicketInformation(
		long userId, java.lang.String userName, long ticketEntryId,
		java.util.Map<java.lang.Long, java.lang.String> fieldsMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketInformationLocalService.updateTicketInformation(userId,
			userName, ticketEntryId, fieldsMap, serviceContext);
	}

	@Override
	public java.util.Map<java.lang.Long, java.lang.String> getFieldsMap(
		long ticketEntryId) {
		return _ticketInformationLocalService.getFieldsMap(ticketEntryId);
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
		return _ticketInformationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketInformationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public TicketInformationLocalService getWrappedService() {
		return _ticketInformationLocalService;
	}

	@Override
	public void setWrappedService(
		TicketInformationLocalService ticketInformationLocalService) {
		_ticketInformationLocalService = ticketInformationLocalService;
	}

	private TicketInformationLocalService _ticketInformationLocalService;
}
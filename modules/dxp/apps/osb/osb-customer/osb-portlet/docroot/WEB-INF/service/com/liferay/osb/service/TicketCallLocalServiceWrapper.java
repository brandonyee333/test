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
 * Provides a wrapper for {@link TicketCallLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCallLocalService
 * @generated
 */
@ProviderType
public class TicketCallLocalServiceWrapper implements TicketCallLocalService,
	ServiceWrapper<TicketCallLocalService> {
	public TicketCallLocalServiceWrapper(
		TicketCallLocalService ticketCallLocalService) {
		_ticketCallLocalService = ticketCallLocalService;
	}

	/**
	* Adds the ticket call to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCall the ticket call
	* @return the ticket call that was added
	*/
	@Override
	public com.liferay.osb.model.TicketCall addTicketCall(
		com.liferay.osb.model.TicketCall ticketCall) {
		return _ticketCallLocalService.addTicketCall(ticketCall);
	}

	@Override
	public com.liferay.osb.model.TicketCall addTicketCall(long userId,
		long ticketEntryId, int type, int callDateMonth, int callDateDay,
		int callDateYear, int callDateHour, int callDateMinute,
		long callLength, java.lang.String customerName,
		java.lang.String customerContact, java.lang.String confirmation,
		java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCallLocalService.addTicketCall(userId, ticketEntryId,
			type, callDateMonth, callDateDay, callDateYear, callDateHour,
			callDateMinute, callLength, customerName, customerContact,
			confirmation, instructions);
	}

	/**
	* Creates a new ticket call with the primary key. Does not add the ticket call to the database.
	*
	* @param ticketCallId the primary key for the new ticket call
	* @return the new ticket call
	*/
	@Override
	public com.liferay.osb.model.TicketCall createTicketCall(long ticketCallId) {
		return _ticketCallLocalService.createTicketCall(ticketCallId);
	}

	/**
	* Deletes the ticket call from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCall the ticket call
	* @return the ticket call that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketCall deleteTicketCall(
		com.liferay.osb.model.TicketCall ticketCall) {
		return _ticketCallLocalService.deleteTicketCall(ticketCall);
	}

	/**
	* Deletes the ticket call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call that was removed
	* @throws PortalException if a ticket call with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketCall deleteTicketCall(long ticketCallId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCallLocalService.deleteTicketCall(ticketCallId);
	}

	@Override
	public com.liferay.osb.model.TicketCall fetchTicketCall(long ticketCallId) {
		return _ticketCallLocalService.fetchTicketCall(ticketCallId);
	}

	/**
	* Returns the ticket call with the primary key.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call
	* @throws PortalException if a ticket call with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketCall getTicketCall(long ticketCallId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCallLocalService.getTicketCall(ticketCallId);
	}

	/**
	* Updates the ticket call in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketCall the ticket call
	* @return the ticket call that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketCall updateTicketCall(
		com.liferay.osb.model.TicketCall ticketCall) {
		return _ticketCallLocalService.updateTicketCall(ticketCall);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketCallLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketCallLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketCallLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCallLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCallLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ticket calls.
	*
	* @return the number of ticket calls
	*/
	@Override
	public int getTicketCallsCount() {
		return _ticketCallLocalService.getTicketCallsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCallLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketCallLocalService.getOSGiServiceIdentifier();
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
		return _ticketCallLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketCallLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketCallLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @return the range of ticket calls
	*/
	@Override
	public java.util.List<com.liferay.osb.model.TicketCall> getTicketCalls(
		int start, int end) {
		return _ticketCallLocalService.getTicketCalls(start, end);
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
		return _ticketCallLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketCallLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public TicketCallLocalService getWrappedService() {
		return _ticketCallLocalService;
	}

	@Override
	public void setWrappedService(TicketCallLocalService ticketCallLocalService) {
		_ticketCallLocalService = ticketCallLocalService;
	}

	private TicketCallLocalService _ticketCallLocalService;
}
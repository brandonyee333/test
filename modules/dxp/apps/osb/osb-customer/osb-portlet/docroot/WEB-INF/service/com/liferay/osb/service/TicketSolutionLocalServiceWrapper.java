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
 * Provides a wrapper for {@link TicketSolutionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionLocalService
 * @generated
 */
@ProviderType
public class TicketSolutionLocalServiceWrapper
	implements TicketSolutionLocalService,
		ServiceWrapper<TicketSolutionLocalService> {
	public TicketSolutionLocalServiceWrapper(
		TicketSolutionLocalService ticketSolutionLocalService) {
		_ticketSolutionLocalService = ticketSolutionLocalService;
	}

	/**
	* Adds the ticket solution to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was added
	*/
	@Override
	public com.liferay.osb.model.TicketSolution addTicketSolution(
		com.liferay.osb.model.TicketSolution ticketSolution) {
		return _ticketSolutionLocalService.addTicketSolution(ticketSolution);
	}

	@Override
	public com.liferay.osb.model.TicketSolution addTicketSolution(long userId,
		long ticketEntryId, java.lang.String summary,
		boolean useCustomerSummary, int issueType, java.lang.String solution,
		int type, boolean customerSpecific, boolean environmentSpecific,
		boolean versionSpecific, boolean reviewForKB, int status,
		int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		java.util.List<java.lang.String> ticketLinkURLs,
		java.util.List<java.lang.Integer> ticketLinkTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketSolutionLocalService.addTicketSolution(userId,
			ticketEntryId, summary, useCustomerSummary, issueType, solution,
			type, customerSpecific, environmentSpecific, versionSpecific,
			reviewForKB, status, ticketEntrySubcomponent,
			ticketEntrySubcomponentCustom, ticketLinkURLs, ticketLinkTypes,
			ticketAttachments);
	}

	/**
	* Creates a new ticket solution with the primary key. Does not add the ticket solution to the database.
	*
	* @param ticketSolutionId the primary key for the new ticket solution
	* @return the new ticket solution
	*/
	@Override
	public com.liferay.osb.model.TicketSolution createTicketSolution(
		long ticketSolutionId) {
		return _ticketSolutionLocalService.createTicketSolution(ticketSolutionId);
	}

	/**
	* Deletes the ticket solution from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketSolution deleteTicketSolution(
		com.liferay.osb.model.TicketSolution ticketSolution) {
		return _ticketSolutionLocalService.deleteTicketSolution(ticketSolution);
	}

	/**
	* Deletes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution that was removed
	* @throws PortalException if a ticket solution with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketSolution deleteTicketSolution(
		long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketSolutionLocalService.deleteTicketSolution(ticketSolutionId);
	}

	@Override
	public com.liferay.osb.model.TicketSolution fetchTicketSolution(
		long ticketSolutionId) {
		return _ticketSolutionLocalService.fetchTicketSolution(ticketSolutionId);
	}

	@Override
	public com.liferay.osb.model.TicketSolution getActiveTicketSolution(
		long ticketEntryId) {
		return _ticketSolutionLocalService.getActiveTicketSolution(ticketEntryId);
	}

	/**
	* Returns the ticket solution with the primary key.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution
	* @throws PortalException if a ticket solution with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketSolution getTicketSolution(
		long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketSolutionLocalService.getTicketSolution(ticketSolutionId);
	}

	/**
	* Updates the ticket solution in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketSolution updateTicketSolution(
		com.liferay.osb.model.TicketSolution ticketSolution) {
		return _ticketSolutionLocalService.updateTicketSolution(ticketSolution);
	}

	@Override
	public com.liferay.osb.model.TicketSolution updateTicketSolution(
		long ticketSolutionId, long ticketEntryId, int status,
		long statusByUserId, java.lang.String statusMessage, int statusReason)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketSolutionLocalService.updateTicketSolution(ticketSolutionId,
			ticketEntryId, status, statusByUserId, statusMessage, statusReason);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketSolutionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketSolutionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketSolutionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketSolutionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketSolutionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ticket solutions.
	*
	* @return the number of ticket solutions
	*/
	@Override
	public int getTicketSolutionsCount() {
		return _ticketSolutionLocalService.getTicketSolutionsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketSolutionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketSolutionLocalService.getOSGiServiceIdentifier();
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
		return _ticketSolutionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketSolutionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketSolutionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @return the range of ticket solutions
	*/
	@Override
	public java.util.List<com.liferay.osb.model.TicketSolution> getTicketSolutions(
		int start, int end) {
		return _ticketSolutionLocalService.getTicketSolutions(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketSolution> getTicketSolutions(
		long ticketEntryId) {
		return _ticketSolutionLocalService.getTicketSolutions(ticketEntryId);
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
		return _ticketSolutionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketSolutionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void updateStatus(long ticketEntryId, int ticketEntryStatus,
		int resolution) {
		_ticketSolutionLocalService.updateStatus(ticketEntryId,
			ticketEntryStatus, resolution);
	}

	@Override
	public TicketSolutionLocalService getWrappedService() {
		return _ticketSolutionLocalService;
	}

	@Override
	public void setWrappedService(
		TicketSolutionLocalService ticketSolutionLocalService) {
		_ticketSolutionLocalService = ticketSolutionLocalService;
	}

	private TicketSolutionLocalService _ticketSolutionLocalService;
}
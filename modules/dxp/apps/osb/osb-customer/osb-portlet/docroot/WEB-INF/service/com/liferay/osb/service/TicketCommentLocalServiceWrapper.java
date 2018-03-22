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
 * Provides a wrapper for {@link TicketCommentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentLocalService
 * @generated
 */
@ProviderType
public class TicketCommentLocalServiceWrapper
	implements TicketCommentLocalService,
		ServiceWrapper<TicketCommentLocalService> {
	public TicketCommentLocalServiceWrapper(
		TicketCommentLocalService ticketCommentLocalService) {
		_ticketCommentLocalService = ticketCommentLocalService;
	}

	@Override
	public com.liferay.osb.model.TicketComment addAwayMessageTicketComment(
		long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.addAwayMessageTicketComment(userId,
			ticketEntryId);
	}

	/**
	* Adds the ticket comment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was added
	*/
	@Override
	public com.liferay.osb.model.TicketComment addTicketComment(
		com.liferay.osb.model.TicketComment ticketComment) {
		return _ticketCommentLocalService.addTicketComment(ticketComment);
	}

	@Override
	public com.liferay.osb.model.TicketComment addTicketComment(long userId,
		long ticketEntryId, java.lang.String body, int type, int visibility,
		int status, int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.addTicketComment(userId,
			ticketEntryId, body, type, visibility, status, pendingTypes,
			serviceContext);
	}

	/**
	* Creates a new ticket comment with the primary key. Does not add the ticket comment to the database.
	*
	* @param ticketCommentId the primary key for the new ticket comment
	* @return the new ticket comment
	*/
	@Override
	public com.liferay.osb.model.TicketComment createTicketComment(
		long ticketCommentId) {
		return _ticketCommentLocalService.createTicketComment(ticketCommentId);
	}

	/**
	* Deletes the ticket comment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		com.liferay.osb.model.TicketComment ticketComment) {
		return _ticketCommentLocalService.deleteTicketComment(ticketComment);
	}

	/**
	* Deletes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment that was removed
	* @throws PortalException if a ticket comment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.deleteTicketComment(ticketCommentId);
	}

	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.deleteTicketComment(userId,
			ticketComment);
	}

	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.deleteTicketComment(userId,
			ticketCommentId);
	}

	@Override
	public com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _ticketCommentLocalService.fetchLastTicketComment(userId,
			ticketEntryId, visibility, status, obc);
	}

	@Override
	public com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _ticketCommentLocalService.fetchLastTicketComment(userId,
			ticketEntryId, visibility, status, type, obc);
	}

	@Override
	public com.liferay.osb.model.TicketComment fetchTicketComment(
		long ticketCommentId) {
		return _ticketCommentLocalService.fetchTicketComment(ticketCommentId);
	}

	@Override
	public com.liferay.osb.model.TicketComment getLastTicketComment(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.getLastTicketComment(ticketEntryId,
			visibility, obc);
	}

	/**
	* Returns the ticket comment with the primary key.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment
	* @throws PortalException if a ticket comment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketComment getTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.getTicketComment(ticketCommentId);
	}

	/**
	* Updates the ticket comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketComment updateTicketComment(
		com.liferay.osb.model.TicketComment ticketComment) {
		return _ticketCommentLocalService.updateTicketComment(ticketComment);
	}

	@Override
	public com.liferay.osb.model.TicketComment updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status, int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.updateTicketComment(userId,
			ticketCommentId, ticketEntryId, body, visibility, status,
			pendingTypes, serviceContext);
	}

	@Override
	public com.liferay.osb.model.TicketComment updateTicketCommentType(
		long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.updateTicketCommentType(ticketCommentId,
			type);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketCommentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketCommentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketCommentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getOrganizationTicketCommentsCount(long[] organizationIds,
		long ticketEntryId, int visibility) {
		return _ticketCommentLocalService.getOrganizationTicketCommentsCount(organizationIds,
			ticketEntryId, visibility);
	}

	/**
	* Returns the number of ticket comments.
	*
	* @return the number of ticket comments
	*/
	@Override
	public int getTicketCommentsCount() {
		return _ticketCommentLocalService.getTicketCommentsCount();
	}

	@Override
	public int getTicketCommentsCount(long ticketEntryId, int[] visibilities,
		int[] statuses) {
		return _ticketCommentLocalService.getTicketCommentsCount(ticketEntryId,
			visibilities, statuses);
	}

	@Override
	public int getTicketCommentsCount(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses) {
		return _ticketCommentLocalService.getTicketCommentsCount(userId,
			ticketEntryId, visibilities, statuses);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCommentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketCommentLocalService.getOSGiServiceIdentifier();
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
		return _ticketCommentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketCommentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketCommentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of ticket comments
	*/
	@Override
	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		int start, int end) {
		return _ticketCommentLocalService.getTicketComments(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long ticketEntryId, int[] visibilities, int[] statuses) {
		return _ticketCommentLocalService.getTicketComments(ticketEntryId,
			visibilities, statuses);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses) {
		return _ticketCommentLocalService.getTicketComments(userId,
			ticketEntryId, visibilities, statuses);
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
		return _ticketCommentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketCommentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void resetSolutionTicketComment(long ticketEntryId) {
		_ticketCommentLocalService.resetSolutionTicketComment(ticketEntryId);
	}

	@Override
	public TicketCommentLocalService getWrappedService() {
		return _ticketCommentLocalService;
	}

	@Override
	public void setWrappedService(
		TicketCommentLocalService ticketCommentLocalService) {
		_ticketCommentLocalService = ticketCommentLocalService;
	}

	private TicketCommentLocalService _ticketCommentLocalService;
}
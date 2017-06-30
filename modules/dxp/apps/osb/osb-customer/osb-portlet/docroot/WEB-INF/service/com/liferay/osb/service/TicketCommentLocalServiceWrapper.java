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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TicketCommentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketCommentLocalService
 * @generated
 */
public class TicketCommentLocalServiceWrapper
	implements TicketCommentLocalService,
		ServiceWrapper<TicketCommentLocalService> {
	public TicketCommentLocalServiceWrapper(
		TicketCommentLocalService ticketCommentLocalService) {
		_ticketCommentLocalService = ticketCommentLocalService;
	}

	/**
	* Adds the ticket comment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment addTicketComment(
		com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.addTicketComment(ticketComment);
	}

	/**
	* Creates a new ticket comment with the primary key. Does not add the ticket comment to the database.
	*
	* @param ticketCommentId the primary key for the new ticket comment
	* @return the new ticket comment
	*/
	public com.liferay.osb.model.TicketComment createTicketComment(
		long ticketCommentId) {
		return _ticketCommentLocalService.createTicketComment(ticketCommentId);
	}

	/**
	* Deletes the ticket comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment that was removed
	* @throws PortalException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.deleteTicketComment(ticketCommentId);
	}

	/**
	* Deletes the ticket comment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.deleteTicketComment(ticketComment);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketCommentLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TicketComment fetchTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.fetchTicketComment(ticketCommentId);
	}

	/**
	* Returns the ticket comment with the primary key.
	*
	* @param ticketCommentId the primary key of the ticket comment
	* @return the ticket comment
	* @throws PortalException if a ticket comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment getTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getTicketComment(ticketCommentId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket comments
	* @param end the upper bound of the range of ticket comments (not inclusive)
	* @return the range of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getTicketComments(start, end);
	}

	/**
	* Returns the number of ticket comments.
	*
	* @return the number of ticket comments
	* @throws SystemException if a system exception occurred
	*/
	public int getTicketCommentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getTicketCommentsCount();
	}

	/**
	* Updates the ticket comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @return the ticket comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment updateTicketComment(
		com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.updateTicketComment(ticketComment);
	}

	/**
	* Updates the ticket comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketComment the ticket comment
	* @param merge whether to merge the ticket comment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketComment updateTicketComment(
		com.liferay.osb.model.TicketComment ticketComment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.updateTicketComment(ticketComment,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketCommentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketCommentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCommentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TicketComment addAwayMessageTicketComment(
		long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.addAwayMessageTicketComment(userId,
			ticketEntryId);
	}

	public com.liferay.osb.model.TicketComment addTicketComment(long userId,
		long ticketEntryId, java.lang.String body, int type, int visibility,
		int status, long ticketCannedResponseId, int[] pendingTypes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.addTicketComment(userId,
			ticketEntryId, body, type, visibility, status,
			ticketCannedResponseId, pendingTypes, serviceContext);
	}

	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.deleteTicketComment(userId,
			ticketCommentId);
	}

	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.deleteTicketComment(userId,
			ticketComment);
	}

	public com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.fetchLastTicketComment(userId,
			ticketEntryId, visibility, status, type, obc);
	}

	public com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.fetchLastTicketComment(userId,
			ticketEntryId, visibility, status, obc);
	}

	public com.liferay.osb.model.TicketComment getLastTicketComment(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getLastTicketComment(ticketEntryId,
			visibility, obc);
	}

	public int getOrganizationTicketCommentsCount(long[] organizationIds,
		long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getOrganizationTicketCommentsCount(organizationIds,
			ticketEntryId, visibility);
	}

	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getTicketComments(ticketEntryId,
			visibilities, statuses);
	}

	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getTicketComments(userId,
			ticketEntryId, visibilities, statuses);
	}

	public int getTicketCommentsCount(long ticketEntryId, int[] visibilities,
		int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getTicketCommentsCount(ticketEntryId,
			visibilities, statuses);
	}

	public int getTicketCommentsCount(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getTicketCommentsCount(userId,
			ticketEntryId, visibilities, statuses);
	}

	public int[] getUserVisibilities(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.getUserVisibilities(userId,
			ticketEntryId);
	}

	public boolean hasVisibility(long userId, long ticketEntryId, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.hasVisibility(userId, ticketEntryId,
			visibility);
	}

	public void resetSolutionTicketComment(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketCommentLocalService.resetSolutionTicketComment(ticketEntryId);
	}

	public com.liferay.osb.model.TicketComment updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status,
		long ticketCannedResponseId, int[] pendingTypes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.updateTicketComment(userId,
			ticketCommentId, ticketEntryId, body, visibility, status,
			ticketCannedResponseId, pendingTypes, serviceContext);
	}

	public com.liferay.osb.model.TicketComment updateTicketCommentType(
		long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentLocalService.updateTicketCommentType(ticketCommentId,
			type);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketCommentLocalService getWrappedTicketCommentLocalService() {
		return _ticketCommentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketCommentLocalService(
		TicketCommentLocalService ticketCommentLocalService) {
		_ticketCommentLocalService = ticketCommentLocalService;
	}

	public TicketCommentLocalService getWrappedService() {
		return _ticketCommentLocalService;
	}

	public void setWrappedService(
		TicketCommentLocalService ticketCommentLocalService) {
		_ticketCommentLocalService = ticketCommentLocalService;
	}

	private TicketCommentLocalService _ticketCommentLocalService;
}
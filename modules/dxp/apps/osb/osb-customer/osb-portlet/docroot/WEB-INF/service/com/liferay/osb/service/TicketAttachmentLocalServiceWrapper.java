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
 * Provides a wrapper for {@link TicketAttachmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentLocalService
 * @generated
 */
@ProviderType
public class TicketAttachmentLocalServiceWrapper
	implements TicketAttachmentLocalService,
		ServiceWrapper<TicketAttachmentLocalService> {
	public TicketAttachmentLocalServiceWrapper(
		TicketAttachmentLocalService ticketAttachmentLocalService) {
		_ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	@Override
	public boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.checkAvailability(ticketAttachmentId,
			fileRepositoryId);
	}

	/**
	* Adds the ticket attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was added
	*/
	@Override
	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return _ticketAttachmentLocalService.addTicketAttachment(ticketAttachment);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.addTicketAttachment(userId,
			ticketEntryId, ticketSolutionId, fileName, fileSize, type,
			visibility, fileRepositoryId, status);
	}

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	@Override
	public com.liferay.osb.model.TicketAttachment createTicketAttachment(
		long ticketAttachmentId) {
		return _ticketAttachmentLocalService.createTicketAttachment(ticketAttachmentId);
	}

	/**
	* Deletes the ticket attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was removed
	*/
	@Override
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return _ticketAttachmentLocalService.deleteTicketAttachment(ticketAttachment);
	}

	/**
	* Deletes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws PortalException if a ticket attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.deleteTicketAttachment(ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.deleteTicketAttachment(userId,
			ticketAttachment);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.deleteTicketAttachment(userId,
			ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketAttachmentId) {
		return _ticketAttachmentLocalService.fetchTicketAttachment(ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, int type) {
		return _ticketAttachmentLocalService.fetchTicketAttachment(ticketEntryId,
			type);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status) {
		return _ticketAttachmentLocalService.fetchTicketAttachment(ticketEntryId,
			fileName, visibility, status);
	}

	/**
	* Returns the ticket attachment with the primary key.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws PortalException if a ticket attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.getTicketAttachment(ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.replicateTicketAttachment(userId,
			ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long userId, long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.updateDeleteDate(userId,
			ticketAttachmentId, deleteDate);
	}

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return _ticketAttachmentLocalService.updateTicketAttachment(ticketAttachment);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.updateTicketAttachment(ticketAttachmentId,
			ticketEntryId, type, visibility);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.updateTicketAttachment(ticketAttachmentId,
			ticketEntryId, ticketSolutionId, type, visibility);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketAttachmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketAttachmentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketAttachmentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	*/
	@Override
	public int getTicketAttachmentsCount() {
		return _ticketAttachmentLocalService.getTicketAttachmentsCount();
	}

	@Override
	public int getTicketAttachmentsCount(long ticketEntryId, int[] types,
		int[] visibilities) {
		return _ticketAttachmentLocalService.getTicketAttachmentsCount(ticketEntryId,
			types, visibilities);
	}

	@Override
	public java.io.File getTicketAttachmentsZipFile(long ticketEntryId,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.getTicketAttachmentsZipFile(ticketEntryId,
			visibilities);
	}

	@Override
	public java.io.InputStream getFileAsStream(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.getFileAsStream(ticketAttachment);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketAttachmentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketAttachmentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.addTicketAttachments(userId,
			ticketEntryId, ticketSolutionId, files, types, visibility, status,
			serviceContext);
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
		return _ticketAttachmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketAttachmentLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ticketAttachmentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of ticket attachments
	*/
	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int start, int end) {
		return _ticketAttachmentLocalService.getTicketAttachments(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId) {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities, int status) {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId,
			types, visibilities, status);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, long ticketSolutionId) {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId,
			ticketSolutionId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long userId, long ticketEntryId, int visibility, int status) {
		return _ticketAttachmentLocalService.getTicketAttachments(userId,
			ticketEntryId, visibility, status);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentLocalService.updateTicketAttachments(ticketAttachmentIds,
			ticketEntryId, types, visibilities);
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
		return _ticketAttachmentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketAttachmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void cleanTicketAttachments()
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketAttachmentLocalService.cleanTicketAttachments();
	}

	@Override
	public void deleteTicketAttachment(long userId, long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketAttachmentLocalService.deleteTicketAttachment(userId,
			ticketEntryId, type);
	}

	@Override
	public void updateExtractedText(
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		_ticketAttachmentLocalService.updateExtractedText(ticketAttachment);
	}

	@Override
	public void updateStatus(com.liferay.portal.kernel.model.User user,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		long ticketEntryId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketAttachmentLocalService.updateStatus(user, ticketAttachments,
			ticketEntryId, status, serviceContext);
	}

	@Override
	public TicketAttachmentLocalService getWrappedService() {
		return _ticketAttachmentLocalService;
	}

	@Override
	public void setWrappedService(
		TicketAttachmentLocalService ticketAttachmentLocalService) {
		_ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	private TicketAttachmentLocalService _ticketAttachmentLocalService;
}
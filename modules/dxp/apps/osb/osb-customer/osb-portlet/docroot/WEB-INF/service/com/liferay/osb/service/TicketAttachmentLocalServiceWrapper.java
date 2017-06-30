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
 * This class is a wrapper for {@link TicketAttachmentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketAttachmentLocalService
 * @generated
 */
public class TicketAttachmentLocalServiceWrapper
	implements TicketAttachmentLocalService,
		ServiceWrapper<TicketAttachmentLocalService> {
	public TicketAttachmentLocalServiceWrapper(
		TicketAttachmentLocalService ticketAttachmentLocalService) {
		_ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	/**
	* Adds the ticket attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.addTicketAttachment(ticketAttachment);
	}

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	public com.liferay.osb.model.TicketAttachment createTicketAttachment(
		long ticketAttachmentId) {
		return _ticketAttachmentLocalService.createTicketAttachment(ticketAttachmentId);
	}

	/**
	* Deletes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws PortalException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.deleteTicketAttachment(ticketAttachmentId);
	}

	/**
	* Deletes the ticket attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.deleteTicketAttachment(ticketAttachment);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketAttachmentLocalService.dynamicQuery();
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
		return _ticketAttachmentLocalService.dynamicQuery(dynamicQuery);
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
		return _ticketAttachmentLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _ticketAttachmentLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ticketAttachmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.fetchTicketAttachment(ticketAttachmentId);
	}

	/**
	* Returns the ticket attachment with the primary key.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws PortalException if a ticket attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachment(ticketAttachmentId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(start, end);
	}

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	* @throws SystemException if a system exception occurred
	*/
	public int getTicketAttachmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachmentsCount();
	}

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @return the ticket attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.updateTicketAttachment(ticketAttachment);
	}

	/**
	* Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachment the ticket attachment
	* @param merge whether to merge the ticket attachment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		com.liferay.osb.model.TicketAttachment ticketAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.updateTicketAttachment(ticketAttachment,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketAttachmentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketAttachmentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketAttachmentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.addTicketAttachment(userId,
			ticketEntryId, ticketSolutionId, fileName, fileSize, type,
			visibility, fileRepositoryId, status);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.addTicketAttachments(userId,
			ticketEntryId, ticketSolutionId, files, types, visibility, status,
			serviceContext);
	}

	public boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.checkAvailability(ticketAttachmentId,
			fileRepositoryId);
	}

	public void cleanTicketAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketAttachmentLocalService.cleanTicketAttachments();
	}

	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.deleteTicketAttachment(userId,
			ticketAttachmentId);
	}

	public void deleteTicketAttachment(long userId, long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketAttachmentLocalService.deleteTicketAttachment(userId,
			ticketEntryId, type);
	}

	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long userId, com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.deleteTicketAttachment(userId,
			ticketAttachment);
	}

	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.fetchTicketAttachment(ticketEntryId,
			type);
	}

	public com.liferay.osb.model.TicketAttachment fetchTicketAttachment(
		long ticketEntryId, java.lang.String fileName, int visibility,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.fetchTicketAttachment(ticketEntryId,
			fileName, visibility, status);
	}

	public java.io.InputStream getFileAsStream(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getFileAsStream(ticketAttachment);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		java.util.Date createDate, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(createDate,
			type);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		int[] types) throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(types);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId,
			visibilities, status);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId,
			types, visibilities);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, int[] types, int[] visibilities, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId,
			types, visibilities, status);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long ticketEntryId, long ticketSolutionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(ticketEntryId,
			ticketSolutionId);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> getTicketAttachments(
		long userId, long ticketEntryId, int visibility, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachments(userId,
			ticketEntryId, visibility, status);
	}

	public int getTicketAttachmentsCount(long ticketEntryId, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachmentsCount(ticketEntryId,
			visibilities);
	}

	public int getTicketAttachmentsCount(long ticketEntryId, int[] types,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachmentsCount(ticketEntryId,
			types, visibilities);
	}

	public java.io.File getTicketAttachmentsZipFile(long ticketEntryId,
		int[] visibilities)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.getTicketAttachmentsZipFile(ticketEntryId,
			visibilities);
	}

	public com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long userId, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.replicateTicketAttachment(userId,
			ticketAttachmentId);
	}

	public com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long userId, long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.updateDeleteDate(userId,
			ticketAttachmentId, deleteDate);
	}

	public void updateExtractedText(
		com.liferay.osb.model.TicketAttachment ticketAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketAttachmentLocalService.updateExtractedText(ticketAttachment);
	}

	public void updateStatus(com.liferay.portal.model.User user,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		long ticketEntryId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketAttachmentLocalService.updateStatus(user, ticketAttachments,
			ticketEntryId, status, serviceContext);
	}

	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.updateTicketAttachment(ticketAttachmentId,
			ticketEntryId, type, visibility);
	}

	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		int type, int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.updateTicketAttachment(ticketAttachmentId,
			ticketEntryId, ticketSolutionId, type, visibility);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentLocalService.updateTicketAttachments(ticketAttachmentIds,
			ticketEntryId, types, visibilities);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketAttachmentLocalService getWrappedTicketAttachmentLocalService() {
		return _ticketAttachmentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketAttachmentLocalService(
		TicketAttachmentLocalService ticketAttachmentLocalService) {
		_ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	public TicketAttachmentLocalService getWrappedService() {
		return _ticketAttachmentLocalService;
	}

	public void setWrappedService(
		TicketAttachmentLocalService ticketAttachmentLocalService) {
		_ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	private TicketAttachmentLocalService _ticketAttachmentLocalService;
}
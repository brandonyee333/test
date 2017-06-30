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
 * This class is a wrapper for {@link TicketAttachmentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketAttachmentService
 * @generated
 */
public class TicketAttachmentServiceWrapper implements TicketAttachmentService,
	ServiceWrapper<TicketAttachmentService> {
	public TicketAttachmentServiceWrapper(
		TicketAttachmentService ticketAttachmentService) {
		_ticketAttachmentService = ticketAttachmentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketAttachmentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketAttachmentService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketAttachmentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.addTicketAttachment(userId,
			ticketEntryId, ticketSolutionId, fileName, fileSize, type,
			visibility, fileRepositoryId, status);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		int[] pendingTypes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.addTicketAttachments(userId,
			ticketEntryId, ticketSolutionId, files, types, visibility, status,
			pendingTypes, serviceContext);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.addTicketAttachments(userId,
			ticketEntryId, ticketSolutionId, files, types, visibility, status,
			serviceContext);
	}

	public boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.checkAvailability(ticketAttachmentId,
			fileRepositoryId);
	}

	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.deleteTicketAttachment(ticketAttachmentId);
	}

	public com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.getTicketAttachment(ticketAttachmentId);
	}

	public java.lang.String getUploadToken(long ticketEntryId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.getUploadToken(ticketEntryId,
			fileRepositoryId);
	}

	public com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.replicateTicketAttachment(ticketAttachmentId);
	}

	public com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.updateDeleteDate(ticketAttachmentId,
			deleteDate);
	}

	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.updateTicketAttachment(ticketAttachmentId,
			ticketEntryId, type, visibility, pendingTypes);
	}

	public java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketAttachmentService.updateTicketAttachments(ticketAttachmentIds,
			ticketEntryId, types, visibilities, pendingTypes);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketAttachmentService getWrappedTicketAttachmentService() {
		return _ticketAttachmentService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketAttachmentService(
		TicketAttachmentService ticketAttachmentService) {
		_ticketAttachmentService = ticketAttachmentService;
	}

	public TicketAttachmentService getWrappedService() {
		return _ticketAttachmentService;
	}

	public void setWrappedService(
		TicketAttachmentService ticketAttachmentService) {
		_ticketAttachmentService = ticketAttachmentService;
	}

	private TicketAttachmentService _ticketAttachmentService;
}
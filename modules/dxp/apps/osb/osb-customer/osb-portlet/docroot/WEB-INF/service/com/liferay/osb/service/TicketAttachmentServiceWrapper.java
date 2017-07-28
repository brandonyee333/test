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
 * Provides a wrapper for {@link TicketAttachmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentService
 * @generated
 */
@ProviderType
public class TicketAttachmentServiceWrapper implements TicketAttachmentService,
	ServiceWrapper<TicketAttachmentService> {
	public TicketAttachmentServiceWrapper(
		TicketAttachmentService ticketAttachmentService) {
		_ticketAttachmentService = ticketAttachmentService;
	}

	@Override
	public boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.checkAvailability(ticketAttachmentId,
			fileRepositoryId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.addTicketAttachment(userId,
			ticketEntryId, ticketSolutionId, fileName, fileSize, type,
			visibility, fileRepositoryId, status);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.deleteTicketAttachment(ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.getTicketAttachment(ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.replicateTicketAttachment(ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.updateDeleteDate(ticketAttachmentId,
			deleteDate);
	}

	@Override
	public com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.updateTicketAttachment(ticketAttachmentId,
			ticketEntryId, type, visibility, pendingTypes);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketAttachmentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketAttachmentService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.String getUploadToken(long ticketEntryId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.getUploadToken(ticketEntryId,
			fileRepositoryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.addTicketAttachments(userId,
			ticketEntryId, ticketSolutionId, files, types, visibility, status,
			serviceContext);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.addTicketAttachments(userId,
			ticketEntryId, ticketSolutionId, files, types, visibility, status,
			pendingTypes, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.updateTicketAttachments(ticketAttachmentIds,
			ticketEntryId, types, visibilities, pendingTypes);
	}

	@Override
	public TicketAttachmentService getWrappedService() {
		return _ticketAttachmentService;
	}

	@Override
	public void setWrappedService(
		TicketAttachmentService ticketAttachmentService) {
		_ticketAttachmentService = ticketAttachmentService;
	}

	private TicketAttachmentService _ticketAttachmentService;
}
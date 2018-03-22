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
 * Provides a wrapper for {@link TicketCommentService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentService
 * @generated
 */
@ProviderType
public class TicketCommentServiceWrapper implements TicketCommentService,
	ServiceWrapper<TicketCommentService> {
	public TicketCommentServiceWrapper(
		TicketCommentService ticketCommentService) {
		_ticketCommentService = ticketCommentService;
	}

	@Override
	public com.liferay.osb.model.TicketComment addTicketComment(long userId,
		long ticketEntryId, java.lang.String body, int type, int visibility,
		int status, int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentService.addTicketComment(userId, ticketEntryId,
			body, type, visibility, status, pendingTypes, serviceContext);
	}

	@Override
	public com.liferay.osb.model.TicketComment addTicketComment(long userId,
		long ticketEntryId, java.lang.String body, int type, int visibility,
		int status, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentService.addTicketComment(userId, ticketEntryId,
			body, type, visibility, status, pendingTypes, files, types,
			serviceContext);
	}

	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentService.deleteTicketComment(ticketCommentId);
	}

	@Override
	public com.liferay.osb.model.TicketComment updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentService.updateTicketComment(userId,
			ticketCommentId, ticketEntryId, body, visibility, status,
			pendingTypes, files, types);
	}

	@Override
	public com.liferay.osb.model.TicketComment updateTicketCommentType(
		long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCommentService.updateTicketCommentType(ticketCommentId,
			type);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCommentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketCommentService.getOSGiServiceIdentifier();
	}

	@Override
	public TicketCommentService getWrappedService() {
		return _ticketCommentService;
	}

	@Override
	public void setWrappedService(TicketCommentService ticketCommentService) {
		_ticketCommentService = ticketCommentService;
	}

	private TicketCommentService _ticketCommentService;
}
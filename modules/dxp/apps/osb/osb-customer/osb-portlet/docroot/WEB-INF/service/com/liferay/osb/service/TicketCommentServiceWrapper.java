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
 * This class is a wrapper for {@link TicketCommentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketCommentService
 * @generated
 */
public class TicketCommentServiceWrapper implements TicketCommentService,
	ServiceWrapper<TicketCommentService> {
	public TicketCommentServiceWrapper(
		TicketCommentService ticketCommentService) {
		_ticketCommentService = ticketCommentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketCommentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketCommentService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCommentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TicketComment addTicketComment(long userId,
		long ticketEntryId, java.lang.String body, int type, int visibility,
		int status, long ticketCannedResponseId, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentService.addTicketComment(userId, ticketEntryId,
			body, type, visibility, status, ticketCannedResponseId,
			pendingTypes, files, types, serviceContext);
	}

	public com.liferay.osb.model.TicketComment addTicketComment(long userId,
		long ticketEntryId, java.lang.String body, int type, int visibility,
		int status, long ticketCannedResponseId, int[] pendingTypes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentService.addTicketComment(userId, ticketEntryId,
			body, type, visibility, status, ticketCannedResponseId,
			pendingTypes, serviceContext);
	}

	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentService.deleteTicketComment(ticketCommentId);
	}

	public com.liferay.osb.model.TicketComment updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status,
		long ticketCannedResponseId, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentService.updateTicketComment(userId,
			ticketCommentId, ticketEntryId, body, visibility, status,
			ticketCannedResponseId, pendingTypes, files, types);
	}

	public com.liferay.osb.model.TicketComment updateTicketCommentType(
		long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCommentService.updateTicketCommentType(ticketCommentId,
			type);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketCommentService getWrappedTicketCommentService() {
		return _ticketCommentService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketCommentService(
		TicketCommentService ticketCommentService) {
		_ticketCommentService = ticketCommentService;
	}

	public TicketCommentService getWrappedService() {
		return _ticketCommentService;
	}

	public void setWrappedService(TicketCommentService ticketCommentService) {
		_ticketCommentService = ticketCommentService;
	}

	private TicketCommentService _ticketCommentService;
}
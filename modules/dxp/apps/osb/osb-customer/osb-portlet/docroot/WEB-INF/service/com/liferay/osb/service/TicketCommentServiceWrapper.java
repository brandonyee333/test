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
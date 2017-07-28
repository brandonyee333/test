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
 * Provides a wrapper for {@link TicketWorkerService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerService
 * @generated
 */
@ProviderType
public class TicketWorkerServiceWrapper implements TicketWorkerService,
	ServiceWrapper<TicketWorkerService> {
	public TicketWorkerServiceWrapper(TicketWorkerService ticketWorkerService) {
		_ticketWorkerService = ticketWorkerService;
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketWorkerService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketWorkerService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketWorker> addTicketWorkers(
		long[] userIds, long ticketEntryId, long[] sourceClassNameIds,
		long[] sourceClassPKs, int[] roles, long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketWorkerService.addTicketWorkers(userIds, ticketEntryId,
			sourceClassNameIds, sourceClassPKs, roles, primaryUserId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketWorker> updateTicketWorkers(
		long[] addUserIds, int[] addRoles, long[] removeUserIds,
		long ticketEntryId, long[] sourceClassNameIds, long[] sourceClassPKs,
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketWorkerService.updateTicketWorkers(addUserIds, addRoles,
			removeUserIds, ticketEntryId, sourceClassNameIds, sourceClassPKs,
			primaryUserId);
	}

	@Override
	public void deleteTicketWorkers(long[] userIds, long ticketEntryId,
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketWorkerService.deleteTicketWorkers(userIds, ticketEntryId,
			primaryUserId);
	}

	@Override
	public TicketWorkerService getWrappedService() {
		return _ticketWorkerService;
	}

	@Override
	public void setWrappedService(TicketWorkerService ticketWorkerService) {
		_ticketWorkerService = ticketWorkerService;
	}

	private TicketWorkerService _ticketWorkerService;
}
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
 * This class is a wrapper for {@link TicketWorkerService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketWorkerService
 * @generated
 */
public class TicketWorkerServiceWrapper implements TicketWorkerService,
	ServiceWrapper<TicketWorkerService> {
	public TicketWorkerServiceWrapper(TicketWorkerService ticketWorkerService) {
		_ticketWorkerService = ticketWorkerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketWorkerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketWorkerService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketWorkerService.invokeMethod(name, parameterTypes, arguments);
	}

	public java.util.List<com.liferay.osb.model.TicketWorker> addTicketWorkers(
		long[] userIds, long ticketEntryId, long[] sourceClassNameIds,
		long[] sourceClassPKs, int[] roles, long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketWorkerService.addTicketWorkers(userIds, ticketEntryId,
			sourceClassNameIds, sourceClassPKs, roles, primaryUserId);
	}

	public void deleteTicketWorkers(long[] userIds, long ticketEntryId,
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketWorkerService.deleteTicketWorkers(userIds, ticketEntryId,
			primaryUserId);
	}

	public java.util.List<com.liferay.osb.model.TicketWorker> updateTicketWorkers(
		long[] addUserIds, int[] addRoles, long[] removeUserIds,
		long ticketEntryId, long[] sourceClassNameIds, long[] sourceClassPKs,
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketWorkerService.updateTicketWorkers(addUserIds, addRoles,
			removeUserIds, ticketEntryId, sourceClassNameIds, sourceClassPKs,
			primaryUserId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketWorkerService getWrappedTicketWorkerService() {
		return _ticketWorkerService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketWorkerService(
		TicketWorkerService ticketWorkerService) {
		_ticketWorkerService = ticketWorkerService;
	}

	public TicketWorkerService getWrappedService() {
		return _ticketWorkerService;
	}

	public void setWrappedService(TicketWorkerService ticketWorkerService) {
		_ticketWorkerService = ticketWorkerService;
	}

	private TicketWorkerService _ticketWorkerService;
}
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
 * This class is a wrapper for {@link TicketSolutionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketSolutionService
 * @generated
 */
public class TicketSolutionServiceWrapper implements TicketSolutionService,
	ServiceWrapper<TicketSolutionService> {
	public TicketSolutionServiceWrapper(
		TicketSolutionService ticketSolutionService) {
		_ticketSolutionService = ticketSolutionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketSolutionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketSolutionService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketSolutionService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TicketSolution addTicketSolution(long userId,
		long ticketEntryId, java.lang.String summary,
		boolean useCustomerSummary, int issueType, java.lang.String solution,
		int type, boolean customerSpecific, boolean environmentSpecific,
		boolean versionSpecific, boolean reviewForKB, int status,
		int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		java.util.List<java.lang.String> ticketLinkURLs,
		java.util.List<java.lang.Integer> ticketLinkTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws java.lang.Exception {
		return _ticketSolutionService.addTicketSolution(userId, ticketEntryId,
			summary, useCustomerSummary, issueType, solution, type,
			customerSpecific, environmentSpecific, versionSpecific,
			reviewForKB, status, ticketEntrySubcomponent,
			ticketEntrySubcomponentCustom, ticketLinkURLs, ticketLinkTypes,
			ticketAttachments);
	}

	public com.liferay.osb.model.TicketSolution updateTicketSolution(
		long ticketSolutionId, long ticketEntryId, int status,
		long statusByUserId, java.lang.String statusMessage, int statusReason)
		throws java.lang.Exception {
		return _ticketSolutionService.updateTicketSolution(ticketSolutionId,
			ticketEntryId, status, statusByUserId, statusMessage, statusReason);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketSolutionService getWrappedTicketSolutionService() {
		return _ticketSolutionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketSolutionService(
		TicketSolutionService ticketSolutionService) {
		_ticketSolutionService = ticketSolutionService;
	}

	public TicketSolutionService getWrappedService() {
		return _ticketSolutionService;
	}

	public void setWrappedService(TicketSolutionService ticketSolutionService) {
		_ticketSolutionService = ticketSolutionService;
	}

	private TicketSolutionService _ticketSolutionService;
}
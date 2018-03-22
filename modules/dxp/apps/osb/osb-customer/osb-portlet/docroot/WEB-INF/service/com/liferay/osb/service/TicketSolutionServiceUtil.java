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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for TicketSolution. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketSolutionServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionService
 * @see com.liferay.osb.service.base.TicketSolutionServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketSolutionServiceImpl
 * @generated
 */
@ProviderType
public class TicketSolutionServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketSolutionServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.TicketSolution addTicketSolution(
		long userId, long ticketEntryId, java.lang.String summary,
		boolean useCustomerSummary, int issueType, java.lang.String solution,
		int type, boolean customerSpecific, boolean environmentSpecific,
		boolean versionSpecific, boolean reviewForKB, int status,
		int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		java.util.List<java.lang.String> ticketLinkURLs,
		java.util.List<java.lang.Integer> ticketLinkTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws java.lang.Exception {
		return getService()
				   .addTicketSolution(userId, ticketEntryId, summary,
			useCustomerSummary, issueType, solution, type, customerSpecific,
			environmentSpecific, versionSpecific, reviewForKB, status,
			ticketEntrySubcomponent, ticketEntrySubcomponentCustom,
			ticketLinkURLs, ticketLinkTypes, ticketAttachments);
	}

	public static com.liferay.osb.model.TicketSolution updateTicketSolution(
		long ticketSolutionId, long ticketEntryId, int status,
		long statusByUserId, java.lang.String statusMessage, int statusReason)
		throws java.lang.Exception {
		return getService()
				   .updateTicketSolution(ticketSolutionId, ticketEntryId,
			status, statusByUserId, statusMessage, statusReason);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketSolutionService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketSolutionService.class.getName());

			if (invokableService instanceof TicketSolutionService) {
				_service = (TicketSolutionService)invokableService;
			}
			else {
				_service = new TicketSolutionServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TicketSolutionServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketSolutionService _service;
}
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
 * Provides the remote service utility for TicketLink. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketLinkServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketLinkService
 * @see com.liferay.osb.service.base.TicketLinkServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketLinkServiceImpl
 * @generated
 */
@ProviderType
public class TicketLinkServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketLinkServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.TicketLink addTicketLink(long userId,
		long ticketEntryId, long ticketSolutionId, java.lang.String[] urls,
		java.lang.Integer[] types, int visibility,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketLink(userId, ticketEntryId, ticketSolutionId,
			urls, types, visibility, serviceContext);
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

	public static void deleteTicketLink(long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteTicketLink(ticketLinkId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketLinkService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketLinkService.class.getName());

			if (invokableService instanceof TicketLinkService) {
				_service = (TicketLinkService)invokableService;
			}
			else {
				_service = new TicketLinkServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TicketLinkServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketLinkService _service;
}
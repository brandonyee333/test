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
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for TicketWorker. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketWorkerServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerService
 * @see com.liferay.osb.service.base.TicketWorkerServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketWorkerServiceImpl
 * @generated
 */
@ProviderType
public class TicketWorkerServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketWorkerServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.util.List<com.liferay.osb.model.TicketWorker> addTicketWorkers(
		long[] userIds, long ticketEntryId, long[] sourceClassNameIds,
		long[] sourceClassPKs, int[] roles, long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketWorkers(userIds, ticketEntryId,
			sourceClassNameIds, sourceClassPKs, roles, primaryUserId);
	}

	public static void deleteTicketWorkers(long[] userIds, long ticketEntryId,
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteTicketWorkers(userIds, ticketEntryId, primaryUserId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.model.TicketWorker> updateTicketWorkers(
		long[] addUserIds, int[] addRoles, long[] removeUserIds,
		long ticketEntryId, long[] sourceClassNameIds, long[] sourceClassPKs,
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketWorkers(addUserIds, addRoles, removeUserIds,
			ticketEntryId, sourceClassNameIds, sourceClassPKs, primaryUserId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketWorkerService getService() {
		if (_service == null) {
			_service = (TicketWorkerService)PortletBeanLocatorUtil.locate(ServletContextUtil.getServletContextName(),
					TicketWorkerService.class.getName());

			ReferenceRegistry.registerReference(TicketWorkerServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketWorkerService _service;
}
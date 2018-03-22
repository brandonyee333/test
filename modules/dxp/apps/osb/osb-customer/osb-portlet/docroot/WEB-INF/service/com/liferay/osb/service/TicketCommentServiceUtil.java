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
 * Provides the remote service utility for TicketComment. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketCommentServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCommentService
 * @see com.liferay.osb.service.base.TicketCommentServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketCommentServiceImpl
 * @generated
 */
@ProviderType
public class TicketCommentServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketCommentServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.TicketComment addTicketComment(
		long userId, long ticketEntryId, java.lang.String body, int type,
		int visibility, int status, int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketComment(userId, ticketEntryId, body, type,
			visibility, status, pendingTypes, serviceContext);
	}

	public static com.liferay.osb.model.TicketComment addTicketComment(
		long userId, long ticketEntryId, java.lang.String body, int type,
		int visibility, int status, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketComment(userId, ticketEntryId, body, type,
			visibility, status, pendingTypes, files, types, serviceContext);
	}

	public static com.liferay.osb.model.TicketComment deleteTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketComment(ticketCommentId);
	}

	public static com.liferay.osb.model.TicketComment updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketComment(userId, ticketCommentId, ticketEntryId,
			body, visibility, status, pendingTypes, files, types);
	}

	public static com.liferay.osb.model.TicketComment updateTicketCommentType(
		long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateTicketCommentType(ticketCommentId, type);
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

	public static TicketCommentService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketCommentService.class.getName());

			if (invokableService instanceof TicketCommentService) {
				_service = (TicketCommentService)invokableService;
			}
			else {
				_service = new TicketCommentServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TicketCommentServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketCommentService _service;
}
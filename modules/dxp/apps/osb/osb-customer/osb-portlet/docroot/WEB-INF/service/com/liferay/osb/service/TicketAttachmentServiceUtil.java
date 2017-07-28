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
 * Provides the remote service utility for TicketAttachment. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketAttachmentServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentService
 * @see com.liferay.osb.service.base.TicketAttachmentServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketAttachmentServiceImpl
 * @generated
 */
@ProviderType
public class TicketAttachmentServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketAttachmentServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .checkAvailability(ticketAttachmentId, fileRepositoryId);
	}

	public static com.liferay.osb.model.TicketAttachment addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketAttachment(userId, ticketEntryId,
			ticketSolutionId, fileName, fileSize, type, visibility,
			fileRepositoryId, status);
	}

	public static com.liferay.osb.model.TicketAttachment deleteTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketAttachment(ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketAttachment(ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment replicateTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().replicateTicketAttachment(ticketAttachmentId);
	}

	public static com.liferay.osb.model.TicketAttachment updateDeleteDate(
		long ticketAttachmentId, java.util.Date deleteDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateDeleteDate(ticketAttachmentId, deleteDate);
	}

	public static com.liferay.osb.model.TicketAttachment updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketAttachment(ticketAttachmentId, ticketEntryId,
			type, visibility, pendingTypes);
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

	public static java.lang.String getUploadToken(long ticketEntryId,
		java.lang.String fileRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUploadToken(ticketEntryId, fileRepositoryId);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketAttachments(userId, ticketEntryId,
			ticketSolutionId, files, types, visibility, status, serviceContext);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketAttachments(userId, ticketEntryId,
			ticketSolutionId, files, types, visibility, status, pendingTypes,
			serviceContext);
	}

	public static java.util.List<com.liferay.osb.model.TicketAttachment> updateTicketAttachments(
		java.util.List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketAttachments(ticketAttachmentIds, ticketEntryId,
			types, visibilities, pendingTypes);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketAttachmentService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketAttachmentService.class.getName());

			if (invokableService instanceof TicketAttachmentService) {
				_service = (TicketAttachmentService)invokableService;
			}
			else {
				_service = new TicketAttachmentServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TicketAttachmentServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketAttachmentService _service;
}
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

package com.liferay.osb.customer.ticket.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for TicketAttachment. This utility wraps
 * {@link com.liferay.osb.customer.ticket.service.impl.TicketAttachmentServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentService
 * @see com.liferay.osb.customer.ticket.service.base.TicketAttachmentServiceBaseImpl
 * @see com.liferay.osb.customer.ticket.service.impl.TicketAttachmentServiceImpl
 * @generated
 */
@ProviderType
public class TicketAttachmentServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.ticket.service.impl.TicketAttachmentServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.ticket.model.TicketAttachment addTicketAttachment(
		long accountEntryId, long zendeskTicketId,
		java.lang.String fileRepositoryId, java.lang.String fileName,
		long fileSize, int type,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketAttachment(accountEntryId, zendeskTicketId,
			fileRepositoryId, fileName, fileSize, type, serviceContext);
	}

	public static com.liferay.osb.customer.ticket.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketAttachment(ticketAttachmentId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static TicketAttachmentService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TicketAttachmentService, TicketAttachmentService> _serviceTracker =
		ServiceTrackerFactory.open(TicketAttachmentService.class);
}
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.ticket.service;

import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for TicketAttachment. This utility wraps
 * <code>com.liferay.osb.customer.ticket.service.impl.TicketAttachmentServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentService
 * @generated
 */
public class TicketAttachmentServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.ticket.service.impl.TicketAttachmentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static TicketAttachment addTicketAttachment(
			long accountEntryId, long zendeskTicketId, String fileRepositoryId,
			String fileName, long fileSize, int type, boolean regionRestricted,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addTicketAttachment(
			accountEntryId, zendeskTicketId, fileRepositoryId, fileName,
			fileSize, type, regionRestricted, serviceContext);
	}

	public static TicketAttachment deleteTicketAttachment(
			long ticketAttachmentId)
		throws PortalException {

		return getService().deleteTicketAttachment(ticketAttachmentId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static TicketAttachment getTicketAttachment(long ticketAttachmentId)
		throws PortalException {

		return getService().getTicketAttachment(ticketAttachmentId);
	}

	public static TicketAttachment removeRegionRestriction(
			long ticketAttachmentId)
		throws PortalException {

		return getService().removeRegionRestriction(ticketAttachmentId);
	}

	public static TicketAttachmentService getService() {
		return _service;
	}

	public static void setService(TicketAttachmentService service) {
		_service = service;
	}

	private static volatile TicketAttachmentService _service;

}
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
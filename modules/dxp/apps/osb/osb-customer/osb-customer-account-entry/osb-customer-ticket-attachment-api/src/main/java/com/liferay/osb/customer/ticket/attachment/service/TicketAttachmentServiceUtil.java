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

package com.liferay.osb.customer.ticket.attachment.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for TicketAttachment. This utility wraps
 * {@link com.liferay.osb.customer.ticket.attachment.service.impl.TicketAttachmentServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentService
 * @see com.liferay.osb.customer.ticket.attachment.service.base.TicketAttachmentServiceBaseImpl
 * @see com.liferay.osb.customer.ticket.attachment.service.impl.TicketAttachmentServiceImpl
 * @generated
 */
@ProviderType
public class TicketAttachmentServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.ticket.attachment.service.impl.TicketAttachmentServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static TicketAttachmentService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TicketAttachmentService, TicketAttachmentService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TicketAttachmentService.class);

		ServiceTracker<TicketAttachmentService, TicketAttachmentService> serviceTracker =
			new ServiceTracker<TicketAttachmentService, TicketAttachmentService>(bundle.getBundleContext(),
				TicketAttachmentService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}
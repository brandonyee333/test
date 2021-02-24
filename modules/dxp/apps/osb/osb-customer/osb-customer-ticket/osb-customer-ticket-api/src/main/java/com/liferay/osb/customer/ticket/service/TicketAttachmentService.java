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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the remote service interface for TicketAttachment. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=osbcustomer",
		"json.web.service.context.path=TicketAttachment"
	},
	service = TicketAttachmentService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface TicketAttachmentService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.customer.ticket.service.impl.TicketAttachmentServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the ticket attachment remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link TicketAttachmentServiceUtil} if injection and service tracking are not available.
	 */
	public TicketAttachment addTicketAttachment(
			long accountEntryId, long zendeskTicketId, String fileRepositoryId,
			String fileName, long fileSize, int type, boolean regionRestricted,
			ServiceContext serviceContext)
		throws PortalException;

	public TicketAttachment deleteTicketAttachment(long ticketAttachmentId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketAttachment getTicketAttachment(long ticketAttachmentId)
		throws PortalException;

	public TicketAttachment removeRegionRestriction(long ticketAttachmentId)
		throws PortalException;

}
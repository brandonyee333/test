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

import com.liferay.osb.model.TicketAttachment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.io.File;

import java.util.Date;
import java.util.List;

/**
 * Provides the remote service interface for TicketAttachment. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentServiceUtil
 * @see com.liferay.osb.service.base.TicketAttachmentServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketAttachmentServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketAttachmentService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketAttachmentServiceUtil} to access the ticket attachment remote service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketAttachmentServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId) throws PortalException;

	public TicketAttachment addTicketAttachment(long userId,
		long ticketEntryId, long ticketSolutionId, java.lang.String fileName,
		long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws PortalException;

	public TicketAttachment deleteTicketAttachment(long ticketAttachmentId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketAttachment getTicketAttachment(long ticketAttachmentId)
		throws PortalException;

	public TicketAttachment replicateTicketAttachment(long ticketAttachmentId)
		throws PortalException;

	public TicketAttachment updateDeleteDate(long ticketAttachmentId,
		Date deleteDate) throws PortalException;

	public TicketAttachment updateTicketAttachment(long ticketAttachmentId,
		long ticketEntryId, int type, int visibility, int[] pendingTypes)
		throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getUploadToken(long ticketEntryId,
		java.lang.String fileRepositoryId) throws PortalException;

	public List<TicketAttachment> addTicketAttachments(long userId,
		long ticketEntryId, long ticketSolutionId,
		List<ObjectValuePair<java.lang.String, File>> files,
		List<java.lang.Integer> types, int visibility, int status,
		ServiceContext serviceContext) throws PortalException;

	public List<TicketAttachment> addTicketAttachments(long userId,
		long ticketEntryId, long ticketSolutionId,
		List<ObjectValuePair<java.lang.String, File>> files,
		List<java.lang.Integer> types, int visibility, int status,
		int[] pendingTypes, ServiceContext serviceContext)
		throws PortalException;

	public List<TicketAttachment> updateTicketAttachments(
		List<java.lang.Long> ticketAttachmentIds, long ticketEntryId,
		List<java.lang.Integer> types, List<java.lang.Integer> visibilities,
		int[] pendingTypes) throws PortalException;
}
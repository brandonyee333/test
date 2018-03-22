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

import com.liferay.osb.model.TicketWorker;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for TicketWorker. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerServiceUtil
 * @see com.liferay.osb.service.base.TicketWorkerServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketWorkerServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketWorkerService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketWorkerServiceUtil} to access the ticket worker remote service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketWorkerServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
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

	public List<TicketWorker> addTicketWorkers(long[] userIds,
		long ticketEntryId, long[] sourceClassNameIds, long[] sourceClassPKs,
		int[] roles, long primaryUserId) throws PortalException;

	public List<TicketWorker> updateTicketWorkers(long[] addUserIds,
		int[] addRoles, long[] removeUserIds, long ticketEntryId,
		long[] sourceClassNameIds, long[] sourceClassPKs, long primaryUserId)
		throws PortalException;

	public void deleteTicketWorkers(long[] userIds, long ticketEntryId,
		long primaryUserId) throws PortalException;
}
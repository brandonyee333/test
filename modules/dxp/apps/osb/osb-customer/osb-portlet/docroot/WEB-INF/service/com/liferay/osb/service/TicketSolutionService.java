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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * The interface for the ticket solution remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionServiceUtil
 * @see com.liferay.osb.service.base.TicketSolutionServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketSolutionServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketSolutionService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketSolutionServiceUtil} to access the ticket solution remote service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketSolutionServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.liferay.osb.model.TicketSolution addTicketSolution(long userId,
		long ticketEntryId, java.lang.String summary,
		boolean useCustomerSummary, int issueType, java.lang.String solution,
		int type, boolean customerSpecific, boolean environmentSpecific,
		boolean versionSpecific, boolean reviewForKB, int status,
		int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		java.util.List<java.lang.String> ticketLinkURLs,
		java.util.List<java.lang.Integer> ticketLinkTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws java.lang.Exception;

	public com.liferay.osb.model.TicketSolution updateTicketSolution(
		long ticketSolutionId, long ticketEntryId, int status,
		long statusByUserId, java.lang.String statusMessage, int statusReason)
		throws java.lang.Exception;
}
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

import com.liferay.osb.model.TicketFeedback;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the remote service interface for TicketFeedback. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackServiceUtil
 * @see com.liferay.osb.service.base.TicketFeedbackServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketFeedbackServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketFeedbackService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketFeedbackServiceUtil} to access the ticket feedback remote service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketFeedbackServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public TicketFeedback addTicketFeedback(long ticketEntryId, int subject,
		int satisfied) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketFeedback fetchFirstOpenTicketFeedback(long userId,
		long ticketEntryId, int subject) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketFeedback fetchFirstTicketFeedback(long ticketEntryId,
		int subject) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketFeedback getTicketFeedback(long ticketFeedbackId)
		throws PortalException;

	public TicketFeedback updateTicketFeedback(long ticketFeedbackId,
		int satisfied, int answer1, int answer2, int answer3, int rating1,
		int rating2, int rating3, int rating4, java.lang.String comments)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String name, int createdGTDay,
		int createdGTMonth, int createdGTYear, int createdLTDay,
		int createdLTMonth, int createdLTYear, int modifiedGTDay,
		int modifiedGTMonth, int modifiedGTYear, int modifiedLTDay,
		int modifiedLTMonth, int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch) throws PortalException;

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
	public List<TicketFeedback> getTicketFeedbacks(long ticketEntryId,
		int subject) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketFeedback> search(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator obc) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketFeedback> search(java.lang.String name, int createdGTDay,
		int createdGTMonth, int createdGTYear, int createdLTDay,
		int createdLTMonth, int createdLTYear, int modifiedGTDay,
		int modifiedGTMonth, int modifiedGTYear, int modifiedLTDay,
		int modifiedLTMonth, int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc)
		throws PortalException;
}
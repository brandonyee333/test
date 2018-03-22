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
import com.liferay.osb.model.TicketEntry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides the remote service interface for TicketEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryServiceUtil
 * @see com.liferay.osb.service.base.TicketEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketEntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketEntryService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketEntryServiceUtil} to access the ticket entry remote service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public TicketEntry addTicketEntry(long userId, long offeringEntryId,
		long supportRegionId, java.lang.String languageId, long ticketId,
		java.lang.String subject, java.lang.String description,
		int systemStatus, int status, int weight, int escalationLevel,
		int component, int subcomponent,
		Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		List<TicketAttachment> ticketAttachments) throws PortalException;

	public TicketEntry forwardTicketEntry(long ticketEntryId,
		java.lang.String commentBody) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketEntry getTicketEntry(long accountEntryId, long ticketId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketEntry getTicketEntry(long ticketEntryId)
		throws PortalException;

	public TicketEntry updatePendingTypes(long ticketEntryId, int[] pendingTypes)
		throws PortalException;

	public TicketEntry updateTicketEntry(long userId, long ticketEntryId,
		long assigneeUserId, long supportRegionId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute)
		throws PortalException;

	public TicketEntry updateTicketEntry(long userId, long ticketEntryId,
		long reportedByUserId, long offeringEntryId, long supportRegionId,
		java.lang.String languageId, java.lang.String subject,
		java.lang.String description, java.lang.String reproductionSteps,
		int severity, int status, int weight, int escalationLevel,
		int component, int subcomponent, java.lang.String subcomponentCustom,
		int resolution, int dueDateMonth, int dueDateDay, int dueDateYear,
		int dueDateHour, int dueDateMinute, boolean ignoreDueDate,
		Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes, List<TicketAttachment> ticketAttachments,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long reportedByUserId, long accountEntryId,
		java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort[] sorts) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long reportedByUserId, long accountEntryId,
		java.lang.String name, int[] accountEntryTier,
		java.lang.Boolean satisfiedDueDate, Date createDateGT,
		Date createDateLT, java.lang.String content, int[] status,
		int[] severity, int[] escalationLevel, long[] envOS, long[] envDB,
		long[] envJVM, long[] envAS, long[] envLFR, int[] components,
		int[] resolution, Date closedDateGT, Date closedDateLT, Date dueDateGT,
		Date dueDateLT,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort[] sorts)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long reportedByUserId, java.lang.String name,
		int[] accountEntryTier, java.lang.Boolean satisfiedDueDate,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.String subject, java.lang.String description,
		java.lang.String body, int[] status, int[] severity, int[] weights,
		int[] escalationLevel, long[] envOS, long[] envDB, long[] envJVM,
		long[] envAS, long[] envLFR, int[] components, int[] resolution,
		int closedDateGTDay, int closedDateGTMonth, int closedDateGTYear,
		int closedDateLTDay, int closedDateLTMonth, int closedDateLTYear,
		int dueDateGTDay, int dueDateGTMonth, int dueDateGTYear,
		int dueDateLTDay, int dueDateLTMonth, int dueDateLTYear,
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
	public List<TicketEntry> search(java.lang.String keywords, int start,
		int end, OrderByComparator obc) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketEntry> search(long reportedByUserId,
		java.lang.String name, int[] accountEntryTier,
		java.lang.Boolean satisfiedDueDate, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear, java.lang.String subject,
		java.lang.String description, java.lang.String body, int[] status,
		int[] severity, int[] weights, int[] escalationLevel, long[] envOS,
		long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolution, int closedDateGTDay,
		int closedDateGTMonth, int closedDateGTYear, int closedDateLTDay,
		int closedDateLTMonth, int closedDateLTYear, int dueDateGTDay,
		int dueDateGTMonth, int dueDateGTYear, int dueDateLTDay,
		int dueDateLTMonth, int dueDateLTYear,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc)
		throws PortalException;

	public void closeTicketEntry(long ticketEntryId, int resolution,
		java.lang.String body) throws PortalException;

	public void escalateTicketEntry(long ticketEntryId)
		throws PortalException;
}
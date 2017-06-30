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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TicketEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketEntryService
 * @generated
 */
public class TicketEntryServiceWrapper implements TicketEntryService,
	ServiceWrapper<TicketEntryService> {
	public TicketEntryServiceWrapper(TicketEntryService ticketEntryService) {
		_ticketEntryService = ticketEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.TicketEntry addTicketEntry(long userId,
		long offeringEntryId, long supportRegionId,
		java.lang.String languageId, long ticketId, java.lang.String subject,
		java.lang.String description, int systemStatus, int status, int weight,
		int escalationLevel, int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.addTicketEntry(userId, offeringEntryId,
			supportRegionId, languageId, ticketId, subject, description,
			systemStatus, status, weight, escalationLevel, component,
			subcomponent, ticketInformationFieldsMap, ticketAttachments);
	}

	public void closeTicketEntry(long ticketEntryId, int resolution,
		java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketEntryService.closeTicketEntry(ticketEntryId, resolution, body);
	}

	public void escalateTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketEntryService.escalateTicketEntry(ticketEntryId);
	}

	public com.liferay.osb.model.TicketEntry forwardTicketEntry(
		long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.forwardTicketEntry(ticketEntryId, commentBody);
	}

	public java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.getTicketEntries(accountEntryId, start, end,
			obc);
	}

	public int getTicketEntriesCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.getTicketEntriesCount(accountEntryId);
	}

	public com.liferay.osb.model.TicketEntry getTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.getTicketEntry(ticketEntryId);
	}

	public com.liferay.osb.model.TicketEntry getTicketEntry(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.getTicketEntry(accountEntryId, ticketId);
	}

	public com.liferay.portal.kernel.search.Hits search(long reportedByUserId,
		long accountEntryId, java.lang.String name, int[] accountEntryTier,
		java.lang.Boolean satisfiedDueDate, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.String content, int[] status,
		int[] severity, int[] escalationLevel, long[] envOS, long[] envDB,
		long[] envJVM, long[] envAS, long[] envLFR, int[] components,
		int[] resolution, java.util.Date closedDateGT,
		java.util.Date closedDateLT, java.util.Date dueDateGT,
		java.util.Date dueDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.search(reportedByUserId, accountEntryId,
			name, accountEntryTier, satisfiedDueDate, createDateGT,
			createDateLT, content, status, severity, escalationLevel, envOS,
			envDB, envJVM, envAS, envLFR, components, resolution, closedDateGT,
			closedDateLT, dueDateGT, dueDateLT, params, andSearch, start, end,
			sorts);
	}

	public com.liferay.portal.kernel.search.Hits search(long reportedByUserId,
		long accountEntryId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.search(reportedByUserId, accountEntryId,
			keywords, params, start, end, sorts);
	}

	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		long reportedByUserId, java.lang.String name, int[] accountEntryTier,
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
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.search(reportedByUserId, name,
			accountEntryTier, satisfiedDueDate, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, subject, description, body,
			status, severity, weights, escalationLevel, envOS, envDB, envJVM,
			envAS, envLFR, components, resolution, closedDateGTDay,
			closedDateGTMonth, closedDateGTYear, closedDateLTDay,
			closedDateLTMonth, closedDateLTYear, dueDateGTDay, dueDateGTMonth,
			dueDateGTYear, dueDateLTDay, dueDateLTMonth, dueDateLTYear, params,
			andSearch, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.search(keywords, start, end, obc);
	}

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
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.searchCount(reportedByUserId, name,
			accountEntryTier, satisfiedDueDate, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, subject, description, body,
			status, severity, weights, escalationLevel, envOS, envDB, envJVM,
			envAS, envLFR, components, resolution, closedDateGTDay,
			closedDateGTMonth, closedDateGTYear, closedDateLTDay,
			closedDateLTMonth, closedDateLTYear, dueDateGTDay, dueDateGTMonth,
			dueDateGTYear, dueDateLTDay, dueDateLTMonth, dueDateLTYear, params,
			andSearch);
	}

	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.searchCount(keywords);
	}

	public com.liferay.osb.model.TicketEntry updatePendingTypes(
		long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.updatePendingTypes(ticketEntryId,
			pendingTypes);
	}

	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long assigneeUserId, long supportRegionId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.updateTicketEntry(userId, ticketEntryId,
			assigneeUserId, supportRegionId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute);
	}

	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long reportedByUserId, long offeringEntryId,
		long supportRegionId, java.lang.String languageId,
		java.lang.String subject, java.lang.String description,
		java.lang.String reproductionSteps, int severity, int status,
		int weight, int escalationLevel, int component, int subcomponent,
		java.lang.String subcomponentCustom, int resolution, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean ignoreDueDate,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketEntryService.updateTicketEntry(userId, ticketEntryId,
			reportedByUserId, offeringEntryId, supportRegionId, languageId,
			subject, description, reproductionSteps, severity, status, weight,
			escalationLevel, component, subcomponent, subcomponentCustom,
			resolution, dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
			dueDateMinute, ignoreDueDate, ticketInformationFieldsMap,
			pendingTypes, ticketAttachments, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketEntryService getWrappedTicketEntryService() {
		return _ticketEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketEntryService(
		TicketEntryService ticketEntryService) {
		_ticketEntryService = ticketEntryService;
	}

	public TicketEntryService getWrappedService() {
		return _ticketEntryService;
	}

	public void setWrappedService(TicketEntryService ticketEntryService) {
		_ticketEntryService = ticketEntryService;
	}

	private TicketEntryService _ticketEntryService;
}
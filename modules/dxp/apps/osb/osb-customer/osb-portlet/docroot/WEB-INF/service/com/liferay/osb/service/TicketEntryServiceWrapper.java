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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryService
 * @generated
 */
@ProviderType
public class TicketEntryServiceWrapper implements TicketEntryService,
	ServiceWrapper<TicketEntryService> {
	public TicketEntryServiceWrapper(TicketEntryService ticketEntryService) {
		_ticketEntryService = ticketEntryService;
	}

	@Override
	public com.liferay.osb.model.TicketEntry addTicketEntry(long userId,
		long offeringEntryId, long supportRegionId,
		java.lang.String languageId, long ticketId, java.lang.String subject,
		java.lang.String description, int systemStatus, int status, int weight,
		int escalationLevel, int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.addTicketEntry(userId, offeringEntryId,
			supportRegionId, languageId, ticketId, subject, description,
			systemStatus, status, weight, escalationLevel, component,
			subcomponent, ticketInformationFieldsMap, ticketAttachments);
	}

	@Override
	public com.liferay.osb.model.TicketEntry forwardTicketEntry(
		long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.forwardTicketEntry(ticketEntryId, commentBody);
	}

	@Override
	public com.liferay.osb.model.TicketEntry getTicketEntry(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.getTicketEntry(accountEntryId, ticketId);
	}

	@Override
	public com.liferay.osb.model.TicketEntry getTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.getTicketEntry(ticketEntryId);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updatePendingTypes(
		long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.updatePendingTypes(ticketEntryId,
			pendingTypes);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long assigneeUserId, long supportRegionId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.updateTicketEntry(userId, ticketEntryId,
			assigneeUserId, supportRegionId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute);
	}

	@Override
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
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.updateTicketEntry(userId, ticketEntryId,
			reportedByUserId, offeringEntryId, supportRegionId, languageId,
			subject, description, reproductionSteps, severity, status, weight,
			escalationLevel, component, subcomponent, subcomponentCustom,
			resolution, dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
			dueDateMinute, ignoreDueDate, ticketInformationFieldsMap,
			pendingTypes, ticketAttachments, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long reportedByUserId,
		long accountEntryId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.search(reportedByUserId, accountEntryId,
			keywords, params, start, end, sorts);
	}

	@Override
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
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.search(reportedByUserId, accountEntryId,
			name, accountEntryTier, satisfiedDueDate, createDateGT,
			createDateLT, content, status, severity, escalationLevel, envOS,
			envDB, envJVM, envAS, envLFR, components, resolution, closedDateGT,
			closedDateLT, dueDateGT, dueDateLT, params, andSearch, start, end,
			sorts);
	}

	@Override
	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.searchCount(keywords);
	}

	@Override
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
		throws com.liferay.portal.kernel.exception.PortalException {
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

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryService.search(keywords, start, end, obc);
	}

	@Override
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
		throws com.liferay.portal.kernel.exception.PortalException {
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

	@Override
	public void closeTicketEntry(long ticketEntryId, int resolution,
		java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryService.closeTicketEntry(ticketEntryId, resolution, body);
	}

	@Override
	public void escalateTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryService.escalateTicketEntry(ticketEntryId);
	}

	@Override
	public TicketEntryService getWrappedService() {
		return _ticketEntryService;
	}

	@Override
	public void setWrappedService(TicketEntryService ticketEntryService) {
		_ticketEntryService = ticketEntryService;
	}

	private TicketEntryService _ticketEntryService;
}
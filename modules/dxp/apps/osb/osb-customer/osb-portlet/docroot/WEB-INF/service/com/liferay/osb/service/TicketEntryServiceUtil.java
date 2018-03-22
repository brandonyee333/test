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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for TicketEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryService
 * @see com.liferay.osb.service.base.TicketEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketEntryServiceImpl
 * @generated
 */
@ProviderType
public class TicketEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.TicketEntry addTicketEntry(
		long userId, long offeringEntryId, long supportRegionId,
		java.lang.String languageId, long ticketId, java.lang.String subject,
		java.lang.String description, int systemStatus, int status, int weight,
		int escalationLevel, int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketEntry(userId, offeringEntryId, supportRegionId,
			languageId, ticketId, subject, description, systemStatus, status,
			weight, escalationLevel, component, subcomponent,
			ticketInformationFieldsMap, ticketAttachments);
	}

	public static com.liferay.osb.model.TicketEntry forwardTicketEntry(
		long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().forwardTicketEntry(ticketEntryId, commentBody);
	}

	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketEntry(accountEntryId, ticketId);
	}

	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketEntry(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketEntry updatePendingTypes(
		long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updatePendingTypes(ticketEntryId, pendingTypes);
	}

	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		long userId, long ticketEntryId, long assigneeUserId,
		long supportRegionId, int dueDateMonth, int dueDateDay,
		int dueDateYear, int dueDateHour, int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketEntry(userId, ticketEntryId, assigneeUserId,
			supportRegionId, dueDateMonth, dueDateDay, dueDateYear,
			dueDateHour, dueDateMinute);
	}

	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		long userId, long ticketEntryId, long reportedByUserId,
		long offeringEntryId, long supportRegionId,
		java.lang.String languageId, java.lang.String subject,
		java.lang.String description, java.lang.String reproductionSteps,
		int severity, int status, int weight, int escalationLevel,
		int component, int subcomponent, java.lang.String subcomponentCustom,
		int resolution, int dueDateMonth, int dueDateDay, int dueDateYear,
		int dueDateHour, int dueDateMinute, boolean ignoreDueDate,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketEntry(userId, ticketEntryId, reportedByUserId,
			offeringEntryId, supportRegionId, languageId, subject, description,
			reproductionSteps, severity, status, weight, escalationLevel,
			component, subcomponent, subcomponentCustom, resolution,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			ignoreDueDate, ticketInformationFieldsMap, pendingTypes,
			ticketAttachments, serviceContext);
	}

	public static com.liferay.portal.kernel.search.Hits search(
		long reportedByUserId, long accountEntryId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(reportedByUserId, accountEntryId, keywords, params,
			start, end, sorts);
	}

	public static com.liferay.portal.kernel.search.Hits search(
		long reportedByUserId, long accountEntryId, java.lang.String name,
		int[] accountEntryTier, java.lang.Boolean satisfiedDueDate,
		java.util.Date createDateGT, java.util.Date createDateLT,
		java.lang.String content, int[] status, int[] severity,
		int[] escalationLevel, long[] envOS, long[] envDB, long[] envJVM,
		long[] envAS, long[] envLFR, int[] components, int[] resolution,
		java.util.Date closedDateGT, java.util.Date closedDateLT,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(reportedByUserId, accountEntryId, name,
			accountEntryTier, satisfiedDueDate, createDateGT, createDateLT,
			content, status, severity, escalationLevel, envOS, envDB, envJVM,
			envAS, envLFR, components, resolution, closedDateGT, closedDateLT,
			dueDateGT, dueDateLT, params, andSearch, start, end, sorts);
	}

	public static int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCount(keywords);
	}

	public static int searchCount(long reportedByUserId, java.lang.String name,
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
		return getService()
				   .searchCount(reportedByUserId, name, accountEntryTier,
			satisfiedDueDate, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, subject, description, body, status, severity,
			weights, escalationLevel, envOS, envDB, envJVM, envAS, envLFR,
			components, resolution, closedDateGTDay, closedDateGTMonth,
			closedDateGTYear, closedDateLTDay, closedDateLTMonth,
			closedDateLTYear, dueDateGTDay, dueDateGTMonth, dueDateGTYear,
			dueDateLTDay, dueDateLTMonth, dueDateLTYear, params, andSearch);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().search(keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> search(
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
		return getService()
				   .search(reportedByUserId, name, accountEntryTier,
			satisfiedDueDate, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, subject, description, body, status, severity,
			weights, escalationLevel, envOS, envDB, envJVM, envAS, envLFR,
			components, resolution, closedDateGTDay, closedDateGTMonth,
			closedDateGTYear, closedDateLTDay, closedDateLTMonth,
			closedDateLTYear, dueDateGTDay, dueDateGTMonth, dueDateGTYear,
			dueDateLTDay, dueDateLTMonth, dueDateLTYear, params, andSearch,
			start, end, obc);
	}

	public static void closeTicketEntry(long ticketEntryId, int resolution,
		java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().closeTicketEntry(ticketEntryId, resolution, body);
	}

	public static void escalateTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().escalateTicketEntry(ticketEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketEntryService.class.getName());

			if (invokableService instanceof TicketEntryService) {
				_service = (TicketEntryService)invokableService;
			}
			else {
				_service = new TicketEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TicketEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketEntryService _service;
}
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

package com.liferay.osb.service.impl;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.exception.TicketEntryStatusException;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.service.base.TicketEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.service.permission.OSBTicketAttachmentPermission;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBMailActionKeys;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketEntryServiceImpl extends TicketEntryServiceBaseImpl {

	public TicketEntry addTicketEntry(
			long userId, long offeringEntryId, long supportRegionId,
			String languageId, long ticketId, String subject,
			String description, int systemStatus, int status, int weight,
			int escalationLevel, int component, int subcomponent,
			Map<Long, String> ticketInformationFieldsMap,
			List<TicketAttachment> ticketAttachments)
		throws PortalException, SystemException {

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		OSBAccountEntryPermission.check(
			getPermissionChecker(), offeringEntry.getAccountEntryId(),
			OSBActionKeys.ADD_TICKET);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			OSBTicketAttachmentPermission.check(
				getPermissionChecker(), ticketAttachment,
				OSBActionKeys.ADD_ATTACHMENT);
		}

		return ticketEntryLocalService.addTicketEntry(
			userId, offeringEntryId, supportRegionId, languageId, ticketId,
			subject, description, systemStatus, status, weight, escalationLevel,
			component, subcomponent, ticketInformationFieldsMap,
			ticketAttachments);
	}

	public void closeTicketEntry(
			long ticketEntryId, int resolution, String body)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntry, OSBActionKeys.UPDATE_BASIC);

		if (Validator.isNotNull(body)) {
			OSBTicketEntryPermission.check(
				getPermissionChecker(), ticketEntryId,
				OSBActionKeys.ADD_COMMENT_PUBLIC);
		}

		ticketEntry = ticketEntryLocalService.updateTicketEntry(
			getUserId(), ticketEntryId, ticketEntry.getUserId(),
			ticketEntry.getOfferingEntryId(), ticketEntry.getSupportRegionId(),
			ticketEntry.getLanguageId(), ticketEntry.getSubject(),
			ticketEntry.getDescription(), ticketEntry.getReproductionSteps(),
			ticketEntry.getSeverity(), TicketEntryConstants.STATUS_CLOSED,
			ticketEntry.getWeight(), ticketEntry.getEscalationLevel(),
			ticketEntry.getComponent(), ticketEntry.getSubcomponent(),
			ticketEntry.getSubcomponentCustom(), resolution, 0, 0, 0, 0, 0,
			ticketEntry.getIgnoreDueDate(),
			ticketEntry.getTicketInformationFieldsMap(), new int[0],
			new ArrayList<TicketAttachment>(), new ServiceContext());

		TicketComment ticketComment = null;

		if (Validator.isNotNull(body)) {
			ticketComment = ticketCommentLocalService.addTicketComment(
				getUserId(), ticketEntryId, body,
				TicketCommentConstants.TYPE_NORMAL, VisibilityConstants.PUBLIC,
				WorkflowConstants.STATUS_APPROVED, 0, new int[0],
				new ServiceContext());
		}

		ticketSolutionLocalService.updateStatus(
			ticketEntryId, TicketEntryConstants.STATUS_CLOSED, resolution);

		ticketEntryLocalService.sendEmail(
			getUserId(), ticketEntry, ticketComment, OSBMailActionKeys.CLOSED);
	}

	public void escalateTicketEntry(long ticketEntryId)
		throws PortalException, SystemException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, OSBActionKeys.ESCALATE);

		ticketEntryLocalService.escalateTicketEntry(getUserId(), ticketEntryId);
	}

	public TicketEntry forwardTicketEntry(
			long ticketEntryId, String commentBody)
		throws PortalException, SystemException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, OSBActionKeys.FORWARD);

		return ticketEntryLocalService.forwardTicketEntry(
			getUserId(), ticketEntryId, commentBody);
	}

	public List<TicketEntry> getTicketEntries(
			long accountEntryId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId, OSBActionKeys.VIEW);

		return ticketEntryLocalService.getTicketEntries(
			accountEntryId, start, end, obc);
	}

	public int getTicketEntriesCount(long accountEntryId)
		throws PortalException, SystemException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId, OSBActionKeys.VIEW);

		return ticketEntryLocalService.getTicketEntriesCount(accountEntryId);
	}

	public TicketEntry getTicketEntry(long ticketEntryId)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = ticketEntryLocalService.getTicketEntry(
			ticketEntryId);

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntry, ActionKeys.VIEW);

		return ticketEntry;
	}

	public TicketEntry getTicketEntry(long accountEntryId, long ticketId)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = ticketEntryLocalService.getTicketEntry(
			accountEntryId, ticketId);

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntry, ActionKeys.VIEW);

		return ticketEntry;
	}

	public Hits search(
			long reportedByUserId, long accountEntryId, String name,
			int[] accountEntryTier, Boolean satisfiedDueDate, Date createDateGT,
			Date createDateLT, String content, int[] status, int[] severity,
			int[] escalationLevel, long[] envOS, long[] envDB, long[] envJVM,
			long[] envAS, long[] envLFR, int[] components, int[] resolution,
			Date closedDateGT, Date closedDateLT, Date dueDateGT,
			Date dueDateLT, LinkedHashMap<String, Object> params,
			boolean andSearch, int start, int end, Sort[] sorts)
		throws PortalException, SystemException {

		addAccountMembershipParams(params);

		return ticketEntryLocalService.search(
			getUserId(), reportedByUserId, accountEntryId, name,
			accountEntryTier, satisfiedDueDate, createDateGT, createDateLT,
			content, status, severity, escalationLevel, envOS, envDB, envJVM,
			envAS, envLFR, components, resolution, closedDateGT, closedDateLT,
			dueDateGT, dueDateLT, params, andSearch, start, end, sorts);
	}

	public Hits search(
			long reportedByUserId, long accountEntryId, String keywords,
			LinkedHashMap<String, Object> params, int start, int end,
			Sort[] sorts)
		throws PortalException, SystemException {

		addAccountMembershipParams(params);

		return ticketEntryLocalService.search(
			getUserId(), reportedByUserId, accountEntryId, keywords, params,
			start, end, sorts);
	}

	@JSONWebService
	public List<TicketEntry> search(
			long reportedByUserId, String name, int[] accountEntryTier,
			Boolean satisfiedDueDate, int createDateGTDay,
			int createDateGTMonth, int createDateGTYear, int createDateLTDay,
			int createDateLTMonth, int createDateLTYear, String subject,
			String description, String body, int[] status, int[] severity,
			int[] weights, int[] escalationLevel, long[] envOS, long[] envDB,
			long[] envJVM, long[] envAS, long[] envLFR, int[] components,
			int[] resolution, int closedDateGTDay, int closedDateGTMonth,
			int closedDateGTYear, int closedDateLTDay, int closedDateLTMonth,
			int closedDateLTYear, int dueDateGTDay, int dueDateGTMonth,
			int dueDateGTYear, int dueDateLTDay, int dueDateLTMonth,
			int dueDateLTYear, LinkedHashMap<String, Object> params,
			boolean andSearch, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		addAccountMembershipParams(params);

		return ticketEntryLocalService.search(
			reportedByUserId, name, accountEntryTier, satisfiedDueDate,
			createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear, subject,
			description, body, status, severity, weights, escalationLevel,
			envOS, envDB, envJVM, envAS, envLFR, components, resolution,
			closedDateGTDay, closedDateGTMonth, closedDateGTYear,
			closedDateLTDay, closedDateLTMonth, closedDateLTYear, dueDateGTDay,
			dueDateGTMonth, dueDateGTYear, dueDateLTDay, dueDateLTMonth,
			dueDateLTYear, params, andSearch, start, end, obc);
	}

	public List<TicketEntry> search(
			String keywords, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		addAccountMembershipParams(params);

		return ticketEntryLocalService.search(
			keywords, params, start, end, obc);
	}

	@JSONWebService
	public int searchCount(
			long reportedByUserId, String name, int[] accountEntryTier,
			Boolean satisfiedDueDate, int createDateGTDay,
			int createDateGTMonth, int createDateGTYear, int createDateLTDay,
			int createDateLTMonth, int createDateLTYear, String subject,
			String description, String body, int[] status, int[] severity,
			int[] weights, int[] escalationLevel, long[] envOS, long[] envDB,
			long[] envJVM, long[] envAS, long[] envLFR, int[] components,
			int[] resolution, int closedDateGTDay, int closedDateGTMonth,
			int closedDateGTYear, int closedDateLTDay, int closedDateLTMonth,
			int closedDateLTYear, int dueDateGTDay, int dueDateGTMonth,
			int dueDateGTYear, int dueDateLTDay, int dueDateLTMonth,
			int dueDateLTYear, LinkedHashMap<String, Object> params,
			boolean andSearch)
		throws PortalException, SystemException {

		addAccountMembershipParams(params);

		return ticketEntryLocalService.searchCount(
			reportedByUserId, name, accountEntryTier, satisfiedDueDate,
			createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear, subject,
			description, body, status, severity, weights, escalationLevel,
			envOS, envDB, envJVM, envAS, envLFR, components, resolution,
			closedDateGTDay, closedDateGTMonth, closedDateGTYear,
			closedDateLTDay, closedDateLTMonth, closedDateLTYear, dueDateGTDay,
			dueDateGTMonth, dueDateGTYear, dueDateLTDay, dueDateLTMonth,
			dueDateLTYear, params, andSearch);
	}

	public int searchCount(String keywords)
		throws PortalException, SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		addAccountMembershipParams(params);

		return ticketEntryLocalService.searchCount(keywords, params);
	}

	public TicketEntry updatePendingTypes(
			long ticketEntryId, int[] pendingTypes)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntry, OSBActionKeys.UPDATE_ADVANCED);

		return ticketEntryLocalService.updatePendingTypes(
			getUserId(), ticketEntryId, pendingTypes);
	}

	public TicketEntry updateTicketEntry(
			long userId, long ticketEntryId, long assigneeUserId,
			long supportRegionId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntry, OSBActionKeys.UPDATE_BULK);

		return ticketEntryLocalService.updateTicketEntry(
			userId, ticketEntryId, assigneeUserId, supportRegionId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute);
	}

	public TicketEntry updateTicketEntry(
			long userId, long ticketEntryId, long reportedByUserId,
			long offeringEntryId, long supportRegionId, String languageId,
			String subject, String description, String reproductionSteps,
			int severity, int status, int weight, int escalationLevel,
			int component, int subcomponent, String subcomponentCustom,
			int resolution, int dueDateMonth, int dueDateDay, int dueDateYear,
			int dueDateHour, int dueDateMinute, boolean ignoreDueDate,
			Map<Long, String> ticketInformationFieldsMap, int[] pendingTypes,
			List<TicketAttachment> ticketAttachments,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		if (ticketEntry.getOfferingEntryId() != offeringEntryId) {
			checkOfferingEntry(ticketEntry, offeringEntryId);
		}

		if (ticketEntry.getStatus() != status) {
			validateStatus(status);
		}

		String currentLanguageId = ticketEntry.getLanguageId();
		String currentSubject = ticketEntry.getSubject();
		String currentDescription = ticketEntry.getDescription();
		String currentReproductionSteps = ticketEntry.getReproductionSteps();

		if (!currentReproductionSteps.equals(reproductionSteps) &&
			!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			reproductionSteps = currentReproductionSteps;
		}

		if ((ticketEntry.getWeight() != weight) ||
			Validator.isGregorianDate(dueDateMonth, dueDateDay, dueDateYear) ||
			(ticketEntry.getIgnoreDueDate() != ignoreDueDate)) {

			OSBTicketEntryPermission.check(
				getPermissionChecker(), ticketEntry,
				OSBActionKeys.UPDATE_ADMIN);
		}
		else if ((ticketEntry.getOfferingEntryId() != offeringEntryId) ||
				 (ticketEntry.getUserId() != reportedByUserId) ||
				 (ticketEntry.getSupportRegionId() != supportRegionId) ||
				 !currentLanguageId.equals(languageId) ||
				 !currentSubject.equals(subject) ||
				 !currentDescription.equals(description) ||
				 !currentReproductionSteps.equals(reproductionSteps) ||
				 (ticketEntry.getSeverity() != severity) ||
				 ((ticketEntry.getStatus() != status) &&
				  (status != TicketEntryConstants.STATUS_CLOSED) &&
				  (status != TicketEntryConstants.STATUS_REOPENED)) ||
				 (ticketEntry.getEscalationLevel() != escalationLevel) ||
				 (ticketEntry.getComponent() != component) ||
				 (ticketEntry.getSubcomponent() != subcomponent) ||
				 !ticketInformationFieldsMap.equals(
					ticketEntry.getTicketInformationFieldsMap()) ||
				 ((ticketEntry.getResolution() != resolution) &&
				  (ticketEntry.getStatus() == status) &&
				  (ticketEntry.getStatus() ==
				  	TicketEntryConstants.STATUS_CLOSED))) {

			OSBTicketEntryPermission.check(
				getPermissionChecker(), ticketEntry,
				OSBActionKeys.UPDATE_ADVANCED);
		}
		else {
			OSBTicketEntryPermission.check(
				getPermissionChecker(), ticketEntry,
				OSBActionKeys.UPDATE_BASIC);
		}

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			OSBTicketAttachmentPermission.check(
				getPermissionChecker(), ticketAttachment, OSBActionKeys.UPDATE);
		}

		return ticketEntryLocalService.updateTicketEntry(
			userId, ticketEntryId, reportedByUserId, offeringEntryId,
			supportRegionId, languageId, subject, description,
			reproductionSteps, severity, status, weight, escalationLevel,
			component, subcomponent, subcomponentCustom, resolution,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			ignoreDueDate, ticketInformationFieldsMap, pendingTypes,
			ticketAttachments, serviceContext);
	}

	protected void addAccountMembershipParams(
			LinkedHashMap<String, Object> params)
		throws PortalException, SystemException {

		if (!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			params.put("accountEntryMembership", getUserId());
		}
	}

	protected void checkOfferingEntry(
			TicketEntry ticketEntry, long offeringEntryId)
		throws PortalException, SystemException {

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		if (offeringEntry.getAccountEntryId() !=
				ticketEntry.getAccountEntryId()) {

			OSBAccountEntryPermission.check(
				getPermissionChecker(), offeringEntry.getAccountEntryId(),
				OSBActionKeys.ADD_TICKET);

			OSBTicketEntryPermission.check(
				getPermissionChecker(), ticketEntry,
				OSBActionKeys.UPDATE_ADMIN);
		}
	}

	protected void validateStatus(int status) throws PortalException {
		if (status == TicketEntryConstants.STATUS_INACTIVE) {
			throw new TicketEntryStatusException();
		}
	}

}
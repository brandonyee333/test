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

import com.liferay.osb.exception.TicketEntrySubcomponentException;
import com.liferay.osb.exception.TicketLinkTypeException;
import com.liferay.osb.exception.TicketSolutionBodyException;
import com.liferay.osb.exception.TicketSolutionStatusException;
import com.liferay.osb.exception.TicketSolutionStatusMessageException;
import com.liferay.osb.exception.TicketSolutionSummaryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.model.TicketSolution;
import com.liferay.osb.model.TicketSolutionConstants;
import com.liferay.osb.service.base.TicketSolutionLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBMailActionKeys;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alan Zhang
 */
public class TicketSolutionLocalServiceImpl
	extends TicketSolutionLocalServiceBaseImpl {

	public TicketSolution addTicketSolution(
			long userId, long ticketEntryId, String summary,
			boolean useCustomerSummary, int issueType, String solution,
			int type, boolean customerSpecific, boolean environmentSpecific,
			boolean versionSpecific, boolean reviewForKB, int status,
			int ticketEntrySubcomponent, String ticketEntrySubcomponentCustom,
			List<String> ticketLinkURLs, List<Integer> ticketLinkTypes,
			List<TicketAttachment> ticketAttachments)
		throws PortalException {

		// Ticket solution

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		validate(
			userId, summary, useCustomerSummary, solution, status, null,
			ticketEntrySubcomponent, ticketEntrySubcomponentCustom,
			ticketEntry.getComponent(), ticketLinkURLs, ticketLinkTypes);

		if (ticketEntry.getStatus() ==
				TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) {

			TicketSolution ticketSolution = getActiveTicketSolution(
				ticketEntryId);

			if (ticketSolution != null) {
				ticketSolution.setStatus(
					TicketSolutionConstants.STATUS_INVALID);

				ticketSolutionPersistence.update(ticketSolution);

				ticketCommentLocalService.resetSolutionTicketComment(
					ticketEntryId);
			}
		}

		if (useCustomerSummary) {
			summary = ticketEntry.getDescription();
		}

		long ticketSolutionId = counterLocalService.increment(
			TicketSolution.class.getName());

		TicketSolution ticketSolution = ticketSolutionPersistence.create(
			ticketSolutionId);

		ticketSolution.setUserId(userId);
		ticketSolution.setUserName(user.getFullName());
		ticketSolution.setCreateDate(now);
		ticketSolution.setTicketEntryId(ticketEntryId);
		ticketSolution.setSummary(summary);
		ticketSolution.setUseCustomerSummary(useCustomerSummary);
		ticketSolution.setIssueType(issueType);
		ticketSolution.setSolution(solution);
		ticketSolution.setType(type);
		ticketSolution.setCustomerSpecific(customerSpecific);
		ticketSolution.setEnvironmentSpecific(environmentSpecific);
		ticketSolution.setVersionSpecific(versionSpecific);
		ticketSolution.setReviewForKB(reviewForKB);
		ticketSolution.setStatus(status);

		ticketSolutionPersistence.update(ticketSolution);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(now);

		serviceContext.setAttribute(
			"auditAction", AuditEntryConstants.ACTION_SOLUTION_PROPOSED);

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			TicketEntry.class.getName(), ticketEntryId);

		serviceContext.setAttribute("auditSetId", auditSetId);

		// Ticket attachment

		List<ObjectValuePair<String, File>> fileList = new ArrayList<>();

		List<Integer> fileTypeList = new ArrayList<>();

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if (ticketAttachment.getTicketAttachmentId() > 0) {
				ticketAttachmentLocalService.updateTicketAttachment(
					ticketAttachment.getTicketAttachmentId(), ticketEntryId,
					ticketSolution.getTicketSolutionId(),
					ticketAttachment.getType(), VisibilityConstants.PUBLIC);
			}
			else {
				ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
					ticketAttachment.getFileName(), ticketAttachment.getFile());

				fileList.add(ovp);

				fileTypeList.add(ticketAttachment.getType());
			}
		}

		if (!fileList.isEmpty()) {
			ticketAttachmentLocalService.addTicketAttachments(
				userId, ticketEntryId, ticketSolution.getTicketSolutionId(),
				fileList, fileTypeList, VisibilityConstants.PUBLIC,
				WorkflowConstants.STATUS_APPROVED, serviceContext);
		}

		// Ticket comment

		StringBundler sb = new StringBundler(5);

		sb.append("[b]");
		sb.append(LanguageUtil.get(LocaleUtil.getDefault(), "solution"));
		sb.append(":[/b]");
		sb.append(StringPool.NEW_LINE);
		sb.append(solution);

		int[] pendingTypes = {TicketFlagConstants.TYPE_PENDING_CUSTOMER};

		AccountEntry accountEntry = ticketEntry.getAccountEntry();

		if (accountEntry.isPartnerManagedSupport() &&
			partnerWorkerLocalService.hasPartnerWorker(
				ticketEntry.getUserId(), accountEntry.getPartnerEntryId())) {

			pendingTypes = ArrayUtil.append(
				pendingTypes, TicketFlagConstants.TYPE_PENDING_PARTNER);
		}

		TicketComment ticketComment =
			ticketCommentLocalService.addTicketComment(
				userId, ticketEntryId, sb.toString(),
				TicketCommentConstants.TYPE_SOLUTION,
				VisibilityConstants.PUBLIC, WorkflowConstants.STATUS_APPROVED,
				0, pendingTypes, serviceContext);

		// Ticket link

		if (!ListUtil.isEmpty(ticketLinkURLs)) {
			ticketLinkLocalService.addTicketLink(
				userId, ticketEntryId, ticketSolution.getTicketSolutionId(),
				ticketLinkURLs.toArray(new String[0]),
				ticketLinkTypes.toArray(new Integer[0]),
				VisibilityConstants.PUBLIC, serviceContext);
		}

		// Ticket entry

		ticketEntry = ticketEntryLocalService.updateTicketEntry(
			userId, ticketEntry.getTicketEntryId(), ticketEntry.getUserId(),
			ticketEntry.getOfferingEntryId(), ticketEntry.getSupportRegionId(),
			ticketEntry.getLanguageId(), ticketEntry.getSubject(),
			ticketEntry.getDescription(), ticketEntry.getReproductionSteps(),
			ticketEntry.getSeverity(),
			TicketEntryConstants.STATUS_SOLUTION_PROPOSED,
			ticketEntry.getWeight(), ticketEntry.getEscalationLevel(),
			ticketEntry.getComponent(), ticketEntrySubcomponent,
			ticketEntrySubcomponentCustom, 0, 0, 0, 0, 0, 0,
			ticketEntry.getIgnoreDueDate(),
			ticketEntry.getTicketInformationFieldsMap(), new int[0],
			new ArrayList<TicketAttachment>(), new ServiceContext());

		ticketEntryLocalService.sendEmail(
			userId, ticketEntry, ticketComment,
			OSBMailActionKeys.SOLUTION_PROPOSED);

		return ticketSolution;
	}

	public TicketSolution getActiveTicketSolution(long ticketEntryId) {
		List<TicketSolution> ticketSolutions =
			ticketSolutionPersistence.findByTicketEntryId(ticketEntryId);

		if (!ticketSolutions.isEmpty()) {
			TicketSolution ticketSolution = ticketSolutions.get(
				ticketSolutions.size() - 1);

			if ((ticketSolution.getStatus() !=
					TicketSolutionConstants.STATUS_INVALID) &&
				(ticketSolution.getStatus() !=
					TicketSolutionConstants.STATUS_UNRESOLVED)) {

				return ticketSolution;
			}
		}

		return null;
	}

	public List<TicketSolution> getTicketSolutions(long ticketEntryId) {
		return ticketSolutionPersistence.findByTicketEntryId(ticketEntryId);
	}

	public void updateStatus(
		long ticketEntryId, int ticketEntryStatus, int resolution) {

		if ((ticketEntryStatus ==
				TicketEntryConstants.STATUS_SOLUTION_DELIVERED) ||
			(ticketEntryStatus == TicketEntryConstants.STATUS_ON_HOLD) ||
			((ticketEntryStatus == TicketEntryConstants.STATUS_CLOSED) &&
			 (resolution ==
				 TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER))) {

			return;
		}

		TicketSolution ticketSolution = getActiveTicketSolution(ticketEntryId);

		if ((ticketSolution == null) ||
			(ticketSolution.getStatus() ==
				TicketSolutionConstants.STATUS_UNRESOLVED)) {

			return;
		}

		if ((ticketEntryStatus == TicketEntryConstants.STATUS_CLOSED) &&
			((ticketSolution.getStatus() ==
				TicketSolutionConstants.STATUS_RESOLVED) ||
			 (ticketSolution.getStatus() ==
				 TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION))) {

			ticketSolution.setStatus(
				TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION);

			ticketFlagPersistence.removeByTEI_T(
				ticketEntryId, TicketFlagConstants.TYPE_READ_SOLUTION);
		}
		else if (ticketEntryStatus == TicketEntryConstants.STATUS_RESOLVED) {
			ticketSolution.setStatus(
				TicketSolutionConstants.STATUS_RESOLVED_MANUALLY);
		}
		else if (ticketEntryStatus ==
					TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) {

			ticketSolution.setStatus(
				TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION);
		}
		else {
			ticketSolution.setStatus(TicketSolutionConstants.STATUS_INVALID);

			ticketCommentLocalService.resetSolutionTicketComment(ticketEntryId);

			ticketFlagPersistence.removeByTEI_T(
				ticketEntryId, TicketFlagConstants.TYPE_READ_SOLUTION);
		}

		ticketSolutionPersistence.update(ticketSolution);
	}

	public TicketSolution updateTicketSolution(
			long ticketSolutionId, long ticketEntryId, int status,
			long statusByUserId, String statusMessage, int statusReason)
		throws PortalException {

		// Ticket solution

		User statusByUser = userPersistence.findByPrimaryKey(statusByUserId);

		TicketSolution ticketSolution =
			ticketSolutionPersistence.findByPrimaryKey(ticketSolutionId);

		validateStatus(status, statusMessage);

		ticketSolution.setStatus(status);
		ticketSolution.setStatusByUserId(statusByUserId);
		ticketSolution.setStatusByUserName(statusByUser.getFullName());
		ticketSolution.setStatusDate(new Date());
		ticketSolution.setStatusMessage(statusMessage);
		ticketSolution.setStatusReason(statusReason);

		ticketSolutionPersistence.update(ticketSolution);

		if (status == TicketSolutionConstants.STATUS_UNABLE_TO_TEST) {
			return ticketSolution;
		}

		// Ticket comment

		TicketComment ticketComment = null;

		if (Validator.isNotNull(statusMessage)) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAttribute(
				"auditAction", AuditEntryConstants.ACTION_SOLUTION_REJECTED);
			serviceContext.setAttribute("statusReason", statusReason);

			int[] pendingTypes = new int[0];

			TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
				ticketEntryId);

			if (!accountCustomerLocalService.hasAccountCustomer(
					statusByUserId, ticketEntry.getAccountEntryId())) {

				if (ticketEntry.getEscalationLevel() ==
						TicketEntryConstants.ESCALATION_LEVEL_P1) {

					pendingTypes =
						new int[] {TicketFlagConstants.TYPE_PENDING_PARTNER};
				}
				else {
					pendingTypes =
						new int[] {TicketFlagConstants.TYPE_PENDING_LIFERAY};
				}
			}

			ticketComment = ticketCommentLocalService.addTicketComment(
				statusByUserId, ticketEntryId, statusMessage,
				TicketCommentConstants.TYPE_NORMAL, VisibilityConstants.PUBLIC,
				WorkflowConstants.STATUS_APPROVED, 0, pendingTypes,
				serviceContext);
		}

		// Ticket entry

		int ticketStatus = TicketEntryConstants.STATUS_INVESTIGATING;

		int resolution = 0;

		if (status == TicketSolutionConstants.STATUS_RESOLVED) {
			ticketStatus = TicketEntryConstants.STATUS_RESOLVED;
		}
		else if (status ==
					TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION) {

			if (ticketSolution.getType() == TicketSolutionConstants.TYPE_TEMP) {
				ticketStatus =
					TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION;
			}
			else {
				ticketStatus = TicketEntryConstants.STATUS_CLOSED;

				resolution = TicketEntryConstants.RESOLUTION_COMPLETED;

				ticketFlagPersistence.removeByTEI_T(
					ticketEntryId, TicketFlagConstants.TYPE_READ_SOLUTION);
			}
		}
		else {
			ticketCommentLocalService.resetSolutionTicketComment(ticketEntryId);

			ticketFlagPersistence.removeByTEI_T(
				ticketEntryId, TicketFlagConstants.TYPE_READ_SOLUTION);
		}

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		ticketEntry = ticketEntryLocalService.updateTicketEntry(
			statusByUserId, ticketEntry.getTicketEntryId(),
			ticketEntry.getUserId(), ticketEntry.getOfferingEntryId(),
			ticketEntry.getSupportRegionId(), ticketEntry.getLanguageId(),
			ticketEntry.getSubject(), ticketEntry.getDescription(),
			ticketEntry.getReproductionSteps(), ticketEntry.getSeverity(),
			ticketStatus, ticketEntry.getWeight(),
			ticketEntry.getEscalationLevel(), ticketEntry.getComponent(),
			ticketEntry.getSubcomponent(), ticketEntry.getSubcomponentCustom(),
			resolution, 0, 0, 0, 0, 0, ticketEntry.getIgnoreDueDate(),
			ticketEntry.getTicketInformationFieldsMap(), new int[0],
			new ArrayList<TicketAttachment>(), new ServiceContext());

		if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_RESOLVED) {
			ticketEntryLocalService.sendEmail(
				statusByUserId, ticketEntry, null, OSBMailActionKeys.RESOLVED);
		}
		else if (ticketEntry.getStatus() ==
					TicketEntryConstants.STATUS_CLOSED) {

			ticketEntryLocalService.sendEmail(
				statusByUserId, ticketEntry, null, OSBMailActionKeys.CLOSED);
		}
		else if (status == TicketSolutionConstants.STATUS_UNRESOLVED) {
			ticketEntryLocalService.sendEmail(
				statusByUserId, ticketEntry, ticketComment,
				OSBMailActionKeys.SOLUTION_REJECTED);
		}

		return ticketSolution;
	}

	protected void validate(
			long userId, String summary, boolean useCustomerSummary,
			String solution, int status, String statusMessage,
			int ticketEntrySubcomponent, String ticketEntrySubcomponentCustom,
			int ticketEntryComponent, List<String> ticketLinkURLs,
			List<Integer> ticketLinkTypes)
		throws PortalException {

		if (Validator.isNull(summary) && !useCustomerSummary) {
			throw new TicketSolutionSummaryException();
		}

		if (Validator.isNull(solution)) {
			throw new TicketSolutionBodyException();
		}

		validateStatus(status, statusMessage);

		if (ticketEntrySubcomponent <= 0) {
			int[] subcomponents = TicketEntryConstants.getSubcomponents(
				ticketEntryComponent);

			if (subcomponents.length > 0) {
				throw new TicketEntrySubcomponentException();
			}
		}
		else if ((ticketEntrySubcomponent ==
					TicketEntryConstants.SUBCOMPONENT_OTHER) &&
				 Validator.isNull(ticketEntrySubcomponentCustom)) {

			throw new TicketEntrySubcomponentException();
		}

		for (int i = 0; i < ticketLinkURLs.size(); i++) {
			String ticketLinkURL = ticketLinkURLs.get(i);
			int ticketLinkType = ticketLinkTypes.get(i);

			if (Validator.isNotNull(ticketLinkURL) && (ticketLinkType == 0) &&
				organizationLocalService.hasUserOrganization(
					userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				throw new TicketLinkTypeException();
			}
		}
	}

	protected void validateStatus(int status, String statusMessage)
		throws PortalException {

		if ((status < 0) || (status > 6)) {
			throw new TicketSolutionStatusException();
		}

		if (Validator.isNull(statusMessage) &&
			(status == TicketSolutionConstants.STATUS_UNRESOLVED)) {

			throw new TicketSolutionStatusMessageException();
		}
	}

}
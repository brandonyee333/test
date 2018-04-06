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

import com.liferay.osb.exception.MaximumDraftTicketCommentException;
import com.liferay.osb.exception.TicketCommentBodyException;
import com.liferay.osb.exception.TicketCommentPendingTypeException;
import com.liferay.osb.exception.TicketCommentVisibilityException;
import com.liferay.osb.exception.TicketEntryClosedException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.service.base.TicketCommentLocalServiceBaseImpl;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBMailActionKeys;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 */
public class TicketCommentLocalServiceImpl
	extends TicketCommentLocalServiceBaseImpl {

	public TicketComment addAwayMessageTicketComment(
			long userId, long ticketEntryId)
		throws PortalException {

		Date now = new Date();

		TicketEntry ticketEntry = ticketEntryLocalService.getTicketEntry(
			ticketEntryId);

		if (ticketEntry.getEscalationLevel() !=
				TicketEntryConstants.ESCALATION_LEVEL_P1) {

			return null;
		}

		SupportResponse supportResponse = ticketEntry.getSupportResponse();

		if (supportResponse.isPlatinumLevel() &&
			(ticketEntry.getSeverity() ==
				SupportResponseConstants.SEVERITY_CRITICAL)) {

			return null;
		}

		if (!accountCustomerLocalService.hasAccountCustomer(
				userId, ticketEntry.getAccountEntryId())) {

			return null;
		}

		TicketWorker ticketWorker =
			ticketWorkerLocalService.fetchLatestTicketWorker(ticketEntryId);

		if (ticketWorker == null) {
			return null;
		}

		TicketComment ticketComment = fetchLastTicketComment(
			ticketWorker.getUserId(), ticketEntryId, VisibilityConstants.PUBLIC,
			WorkflowConstants.STATUS_APPROVED,
			TicketCommentConstants.TYPE_AUTOMATED, null);

		if ((ticketComment != null) &&
			(DateUtil.getDaysBetween(ticketComment.getCreateDate(), now) < 1)) {

			return null;
		}

		PortletPreferences preferences = SupportUtil.getUserPreferences(
			ticketWorker.getUserId());

		boolean awayMessageEnabled = GetterUtil.getBoolean(
			preferences.getValue("awayMessageEnabled", StringPool.BLANK));

		int awayMessageStartMonth = GetterUtil.getInteger(
			preferences.getValue("awayMessageStartMonth", null));
		int awayMessageStartDay = GetterUtil.getInteger(
			preferences.getValue("awayMessageStartDay", null));
		int awayMessageStartYear = GetterUtil.getInteger(
			preferences.getValue("awayMessageStartYear", null));

		Date awayMessageStartDate = PortalUtil.getDate(
			awayMessageStartMonth, awayMessageStartDay, awayMessageStartYear);

		int awayMessageEndMonth = GetterUtil.getInteger(
			preferences.getValue("awayMessageEndMonth", null));
		int awayMessageEndDay = GetterUtil.getInteger(
			preferences.getValue("awayMessageEndDay", null));
		int awayMessageEndYear = GetterUtil.getInteger(
			preferences.getValue("awayMessageEndYear", null));

		Date awayMessageEndDate = PortalUtil.getDate(
			awayMessageEndMonth, awayMessageEndDay, awayMessageEndYear);

		if ((!awayMessageEnabled || awayMessageStartDate.after(now) ||
			 (awayMessageEndDate != null)) &&
			awayMessageEndDate.before(now)) {

			return null;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(new Date(now.getTime() + Time.SECOND));

		return addTicketComment(
			ticketWorker.getUserId(), ticketEntryId,
			preferences.getValue("awayMessage", StringPool.BLANK),
			TicketCommentConstants.TYPE_AUTOMATED, VisibilityConstants.PUBLIC,
			WorkflowConstants.STATUS_APPROVED, new int[0], serviceContext);
	}

	public TicketComment addTicketComment(
			long userId, long ticketEntryId, String body, int type,
			int visibility, int status, int[] pendingTypes,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);
		Date now = serviceContext.getCreateDate(new Date());

		validate(
			ticketEntry, userId, body, type, visibility,
			WorkflowConstants.STATUS_ANY, status, pendingTypes);

		long ticketCommentId = counterLocalService.increment();

		TicketComment ticketComment = ticketCommentPersistence.create(
			ticketCommentId);

		ticketComment.setUserId(user.getUserId());
		ticketComment.setUserName(user.getFullName());
		ticketComment.setCreateDate(now);
		ticketComment.setModifiedDate(now);
		ticketComment.setTicketEntryId(ticketEntryId);
		ticketComment.setBody(body);
		ticketComment.setType(type);
		ticketComment.setFormat(TicketCommentConstants.FORMAT_BBCODE);
		ticketComment.setVisibility(visibility);

		if (status == WorkflowConstants.STATUS_DRAFT) {
			ticketComment.setSettingsProperty(
				"pendingTypes", StringUtil.merge(pendingTypes));
		}

		int statusReason = GetterUtil.getInteger(
			serviceContext.getAttribute("statusReason"));

		if (statusReason > 0) {
			ticketComment.setSettingsProperty(
				"statusReason", String.valueOf(statusReason));
		}

		ticketCommentPersistence.update(ticketComment);

		int auditAction = GetterUtil.getInteger(
			serviceContext.getAttribute("auditAction"));

		if (auditAction <= 0) {
			serviceContext.setAttribute(
				"auditAction", AuditEntryConstants.ACTION_ADD);
		}

		updateStatus(
			user, ticketComment, ticketEntry, status, pendingTypes,
			serviceContext);

		if (type != TicketCommentConstants.TYPE_AUTOMATED) {
			addAwayMessageTicketComment(userId, ticketEntryId);
		}

		return ticketComment;
	}

	public TicketComment deleteTicketComment(long userId, long ticketCommentId)
		throws PortalException {

		TicketComment ticketComment = ticketCommentPersistence.findByPrimaryKey(
			ticketCommentId);

		return deleteTicketComment(userId, ticketComment);
	}

	public TicketComment deleteTicketComment(
			long userId, TicketComment ticketComment)
		throws PortalException {

		ticketCommentPersistence.remove(ticketComment);

		if (ticketComment.getStatus() != WorkflowConstants.STATUS_DRAFT) {
			User user = userLocalService.getUser(userId);

			long classNameId = classNameLocalService.getClassNameId(
				TicketEntry.class.getName());
			long fieldClassNameId = classNameLocalService.getClassNameId(
				TicketComment.class.getName());

			auditEntryLocalService.addAuditEntry(
				userId, user.getFullName(), new Date(), classNameId,
				ticketComment.getTicketEntryId(), 0, fieldClassNameId,
				ticketComment.getTicketCommentId(),
				AuditEntryConstants.ACTION_DELETE,
				AuditEntryConstants.FIELD_BODY, ticketComment.getVisibility(),
				StringPool.BLANK, ticketComment.getBody(), StringPool.BLANK,
				StringPool.BLANK);
		}
		else {
			List<TicketAttachment> ticketAttachments =
				ticketAttachmentLocalService.getTicketAttachments(
					userId, ticketComment.getTicketEntryId(),
					ticketComment.getVisibility(),
					WorkflowConstants.STATUS_DRAFT);

			for (TicketAttachment ticketAttachment : ticketAttachments) {
				ticketAttachmentLocalService.deleteTicketAttachment(
					userId, ticketAttachment);
			}
		}

		return ticketComment;
	}

	public TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status, int type,
		OrderByComparator obc) {

		return ticketCommentPersistence.fetchByU_TEI_V_S_T_Last(
			userId, ticketEntryId, visibility, status, type, obc);
	}

	public TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status,
		OrderByComparator obc) {

		return ticketCommentPersistence.fetchByU_TEI_V_S_Last(
			userId, ticketEntryId, visibility, status, obc);
	}

	public TicketComment getLastTicketComment(
			long ticketEntryId, int visibility, OrderByComparator obc)
		throws PortalException {

		return ticketCommentPersistence.findByTEI_V_S_Last(
			ticketEntryId, visibility, WorkflowConstants.STATUS_APPROVED, obc);
	}

	public int getOrganizationTicketCommentsCount(
		long[] organizationIds, long ticketEntryId, int visibility) {

		return ticketCommentFinder.countByTEI_V_S_O(
			ticketEntryId, visibility,
			new int[] {WorkflowConstants.STATUS_APPROVED}, organizationIds);
	}

	public List<TicketComment> getTicketComments(
		long ticketEntryId, int[] visibilities, int[] statuses) {

		return ticketCommentPersistence.findByTEI_V_S(
			ticketEntryId, visibilities, statuses);
	}

	public List<TicketComment> getTicketComments(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses) {

		return ticketCommentPersistence.findByU_TEI_V_S(
			userId, ticketEntryId, visibilities, statuses);
	}

	public int getTicketCommentsCount(
		long ticketEntryId, int[] visibilities, int[] statuses) {

		return ticketCommentPersistence.countByTEI_V_S(
			ticketEntryId, visibilities, statuses);
	}

	public int getTicketCommentsCount(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses) {

		return ticketCommentPersistence.countByU_TEI_V_S(
			userId, ticketEntryId, visibilities, statuses);
	}

	public void resetSolutionTicketComment(long ticketEntryId) {
		List<TicketComment> ticketComments =
			ticketCommentPersistence.findByTEI_T(
				ticketEntryId, TicketCommentConstants.TYPE_SOLUTION);

		Date now = new Date();

		for (TicketComment ticketComment : ticketComments) {
			ticketComment.setModifiedDate(now);
			ticketComment.setType(TicketCommentConstants.TYPE_NORMAL);

			ticketCommentPersistence.update(ticketComment);
		}
	}

	public synchronized TicketComment updateTicketComment(
			long userId, long ticketCommentId, long ticketEntryId, String body,
			int visibility, int status, int[] pendingTypes,
			ServiceContext serviceContext)
		throws PortalException {

		EntityCacheUtil.clearLocalCache();

		User user = userLocalService.getUser(userId);
		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		TicketComment ticketComment = ticketCommentPersistence.findByPrimaryKey(
			ticketCommentId);

		validate(
			ticketEntry, userId, body, ticketComment.getType(), visibility,
			ticketComment.getStatus(), status, pendingTypes);

		ticketComment.setModifiedDate(new Date());
		ticketComment.setTicketEntryId(ticketEntryId);
		ticketComment.setBody(body);
		ticketComment.setFormat(TicketCommentConstants.FORMAT_BBCODE);
		ticketComment.setVisibility(visibility);

		if (status == WorkflowConstants.STATUS_DRAFT) {
			ticketComment.setSettingsProperty(
				"pendingTypes", StringUtil.merge(pendingTypes));
		}

		ticketCommentPersistence.update(ticketComment);

		int auditAction = GetterUtil.getInteger(
			serviceContext.getAttribute("auditAction"));

		if (auditAction <= 0) {
			if (ticketComment.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) {

				serviceContext.setAttribute(
					"auditAction", AuditEntryConstants.ACTION_UPDATE);
			}
			else {
				serviceContext.setAttribute(
					"auditAction", AuditEntryConstants.ACTION_ADD);
			}
		}

		updateStatus(
			user, ticketComment, ticketEntry, status, pendingTypes,
			serviceContext);

		return ticketComment;
	}

	public TicketComment updateTicketCommentType(long ticketCommentId, int type)
		throws PortalException {

		Date now = new Date();

		TicketComment ticketComment = ticketCommentPersistence.findByPrimaryKey(
			ticketCommentId);

		if (type == TicketCommentConstants.TYPE_SOLUTION) {
			resetSolutionTicketComment(ticketComment.getTicketEntryId());
		}

		ticketComment.setModifiedDate(now);
		ticketComment.setType(type);

		return ticketCommentPersistence.update(ticketComment);
	}

	protected void updateStatus(
			User user, TicketComment ticketComment, TicketEntry ticketEntry,
			int status, int[] pendingTypes, ServiceContext serviceContext)
		throws PortalException {

		Date now = serviceContext.getCreateDate(new Date());

		// Ticket comment

		if ((ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) &&
			(status == WorkflowConstants.STATUS_APPROVED)) {

			ticketComment.setCreateDate(now);
		}

		ticketComment.setStatus(status);

		ticketCommentPersistence.update(ticketComment);

		if (status != WorkflowConstants.STATUS_APPROVED) {
			return;
		}

		if (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) {

			// Ticket attachments

			List<TicketAttachment> ticketAttachments =
				ticketAttachmentLocalService.getTicketAttachments(
					user.getUserId(), ticketComment.getTicketEntryId(),
					ticketComment.getVisibility(),
					WorkflowConstants.STATUS_DRAFT);

			if (!ticketAttachments.isEmpty()) {
				ticketAttachmentLocalService.updateStatus(
					user, ticketAttachments, ticketComment.getTicketEntryId(),
					status, serviceContext);
			}
		}

		// Ticket entry

		if ((ticketEntry.getStatus() ==
				TicketEntryConstants.STATUS_SOLUTION_DELIVERED) &&
			!organizationLocalService.hasUserOrganization(
				user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			ticketEntryLocalService.updateTicketEntry(
				user.getUserId(), ticketEntry.getTicketEntryId(),
				ticketEntry.getUserId(), ticketEntry.getOfferingEntryId(),
				ticketEntry.getSupportRegionId(), ticketEntry.getLanguageId(),
				ticketEntry.getSubject(), ticketEntry.getDescription(),
				ticketEntry.getReproductionSteps(), ticketEntry.getSeverity(),
				TicketEntryConstants.STATUS_SOLUTION_PROPOSED,
				ticketEntry.getWeight(), ticketEntry.getEscalationLevel(),
				ticketEntry.getComponent(), ticketEntry.getSubcomponent(),
				ticketEntry.getSubcomponentCustom(),
				ticketEntry.getResolution(), 0, 0, 0, 0, 0,
				ticketEntry.getIgnoreDueDate(),
				ticketEntry.getTicketInformationFieldsMap(), new int[0],
				new ArrayList<TicketAttachment>(), new ServiceContext());
		}

		// Pending types

		if ((ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) &&
			(ticketComment.getVisibility() == VisibilityConstants.PUBLIC) &&
			ArrayUtil.isEmpty(pendingTypes) &&
			accountCustomerLocalService.hasAccountCustomer(
				ticketComment.getUserId(), ticketEntry.getAccountEntryId())) {

			ticketEntryLocalService.updateCustomerModifiedDate(
				user.getUserId(), ticketComment.getTicketEntryId(), new Date());

			AccountEntry accountEntry = ticketEntry.getAccountEntry();

			if (accountEntry.isPartnerManagedSupport()) {
				pendingTypes = ArrayUtil.append(
					pendingTypes, TicketFlagConstants.TYPE_PENDING_PARTNER);
			}

			if (ticketEntry.getEscalationLevel() !=
					TicketEntryConstants.ESCALATION_LEVEL_P1) {

				pendingTypes = ArrayUtil.append(
					pendingTypes, TicketFlagConstants.TYPE_PENDING_LIFERAY);
			}
		}

		if (!ArrayUtil.isEmpty(pendingTypes)) {
			if (ArrayUtil.contains(
					pendingTypes, TicketFlagConstants.TYPE_PENDING_CUSTOMER)) {

				ticketEntryLocalService.updateWorkerModifiedDate(
					ticketComment.getTicketEntryId(), new Date());
			}

			ticketEntryLocalService.updatePendingTypes(
				user.getUserId(), ticketComment.getTicketEntryId(),
				pendingTypes);
		}

		// Audit entry

		boolean auditEnabled = GetterUtil.getBoolean(
			serviceContext.getAttribute("auditEnabled"), true);

		if (auditEnabled) {
			long auditSetId = GetterUtil.getLong(
				serviceContext.getAttribute("auditSetId"));
			int auditAction = GetterUtil.getInteger(
				serviceContext.getAttribute("auditAction"));

			long classNameId = classNameLocalService.getClassNameId(
				TicketEntry.class.getName());
			long fieldClassNameId = classNameLocalService.getClassNameId(
				TicketComment.class.getName());

			auditEntryLocalService.addAuditEntry(
				user.getUserId(), user.getFullName(), now, classNameId,
				ticketComment.getTicketEntryId(), auditSetId, fieldClassNameId,
				ticketComment.getTicketCommentId(), auditAction,
				AuditEntryConstants.FIELD_BODY, ticketComment.getVisibility(),
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				ticketComment.getBody());
		}

		// Email

		String action = OSBMailActionKeys.COMMENTED;

		if (ticketComment.getType() == TicketCommentConstants.TYPE_GAME_PLAN) {
			action = OSBMailActionKeys.GAME_PLAN_COMMENTED;
		}

		ticketEntryLocalService.sendEmail(
			user.getUserId(), ticketEntry, ticketComment, action);

		// Indexer

		ticketEntryLocalService.reindexTicketEntry(
			ticketEntry.getTicketEntryId());
	}

	protected void validate(
			TicketEntry ticketEntry, long userId, String body, int type,
			int visibility, int oldStatus, int status, int[] pendingTypes)
		throws PortalException {

		if (Validator.isNull(body)) {
			throw new TicketCommentBodyException();
		}

		if (Validator.isNull(VisibilityConstants.toLabel(visibility))) {
			throw new TicketCommentVisibilityException();
		}

		if ((oldStatus == WorkflowConstants.STATUS_APPROVED) &&
			(oldStatus != status)) {

			throw new WorkflowException();
		}

		if ((oldStatus != status) &&
			(status == WorkflowConstants.STATUS_DRAFT) &&
			(getTicketCommentsCount(
				userId, ticketEntry.getTicketEntryId(), new int[] {visibility},
				new int[] {WorkflowConstants.STATUS_DRAFT}) > 0)) {

			if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) {
				throw new TicketEntryClosedException();
			}
			else {
				throw new MaximumDraftTicketCommentException();
			}
		}

		if ((type != TicketCommentConstants.TYPE_TICKET_CALL) &&
			(ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) &&
			(ticketEntry.getStatus() !=
				TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) &&
			(oldStatus != status) && ArrayUtil.isEmpty(pendingTypes)) {

			if (visibility == VisibilityConstants.WORKERS) {
				int[] types = {TicketFlagConstants.TYPE_PENDING_CUSTOMER};

				if (ticketFlagPersistence.countByTEI_T_F(
						ticketEntry.getTicketEntryId(), types,
						TicketFlagConstants.FLAG_TRUE) == 0) {

					throw new TicketCommentPendingTypeException();
				}
			}
			else if ((type != TicketCommentConstants.TYPE_AUTOMATED) &&
					 (visibility == VisibilityConstants.PUBLIC)) {

				if (!accountCustomerLocalService.hasAccountCustomer(
						userId, ticketEntry.getAccountEntryId())) {

					throw new TicketCommentPendingTypeException();
				}
			}
		}
	}

}
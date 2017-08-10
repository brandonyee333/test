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

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.exception.MaximumTicketEntryException;
import com.liferay.osb.exception.NoSuchTicketEntryException;
import com.liferay.osb.exception.OfferingEntrySupportExpiredException;
import com.liferay.osb.exception.TicketCommentPendingTypeException;
import com.liferay.osb.exception.TicketEntryAssigneeException;
import com.liferay.osb.exception.TicketEntryComponentException;
import com.liferay.osb.exception.TicketEntryDescriptionException;
import com.liferay.osb.exception.TicketEntryDueDateException;
import com.liferay.osb.exception.TicketEntryForwardingException;
import com.liferay.osb.exception.TicketEntryLanguageIdException;
import com.liferay.osb.exception.TicketEntryResolutionException;
import com.liferay.osb.exception.TicketEntrySeverityException;
import com.liferay.osb.exception.TicketEntryStatusException;
import com.liferay.osb.exception.TicketEntrySubcomponentException;
import com.liferay.osb.exception.TicketEntrySubjectException;
import com.liferay.osb.exception.TicketEntrySystemStatusException;
import com.liferay.osb.exception.TicketEntryWeightException;
import com.liferay.osb.exception.TicketInformationException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.AuditEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.FileRepository;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamConstants;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.TicketFlag;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.model.TicketInformationConstants;
import com.liferay.osb.model.TicketLink;
import com.liferay.osb.model.TicketSolutionConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.TicketWorkerConstants;
import com.liferay.osb.model.impl.TicketEntryImpl;
import com.liferay.osb.service.base.TicketEntryLocalServiceBaseImpl;
import com.liferay.osb.support.util.FileRepositoryUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.support.util.TicketEntryThreadLocal;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBDateUtil;
import com.liferay.osb.util.OSBMailActionKeys;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.Serializable;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TicketEntryLocalServiceImpl
	extends TicketEntryLocalServiceBaseImpl {

	public TicketEntry addTicketEntry(
			long userId, long offeringEntryId, long supportRegionId,
			String languageId, long ticketId, String subject,
			String description, int systemStatus, int status, int weight,
			int escalationLevel, int component, int subcomponent,
			Map<Long, String> ticketInformationFieldsMap,
			List<TicketAttachment> ticketAttachments)
		throws PortalException {

		TicketEntry ticketEntry = null;

		try {
			TicketEntryThreadLocal.setReindex(false);
			TicketEntryThreadLocal.setSyncToJIRA(false);

			ticketEntry = doAddTicketEntry(
				userId, offeringEntryId, supportRegionId, languageId, ticketId,
				subject, description, systemStatus, status, weight,
				escalationLevel, component, subcomponent,
				ticketInformationFieldsMap, ticketAttachments);
		}
		finally {
			TicketEntryThreadLocal.setReindex(true);
			TicketEntryThreadLocal.setSyncToJIRA(true);
		}

		reindexTicketEntry(ticketEntry);

		syncToJIRA(ticketEntry.getTicketEntryId());

		return ticketEntry;
	}

	public void checkInactiveTicketEntries() throws Exception {
		Date now = new Date();

		Date workerModifiedDateLT = new Date(
			now.getTime() - (2 * Time.DAY) + (2 * Time.HOUR) +
				(35 * Time.MINUTE));

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put(
			"pendingTypes",
			new int[] {TicketFlagConstants.TYPE_PENDING_CUSTOMER});

		List<TicketEntry> ticketEntries =
			ticketEntryFinder.findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
				0, (String)null, new int[0], null, null, null, null, null, null,
				TicketEntryConstants.STATUSES_ACTIVE, new int[0], new int[0],
				new int[0], new long[0], new long[0], new long[0], new long[0],
				new long[0], new int[0], new int[0], null, null, null, null,
				null, null, null, workerModifiedDateLT, params, true,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		long inactiveWarningTime = (2 * Time.DAY) - (2 * Time.HOUR);

		PortletPreferences preferences = SupportUtil.getPortletPreferences();

		Map<Locale, String> commentNoticeMap =
			SupportUtil.getCommentTicketEntryNoticeMap(preferences);
		Map<Locale, String> commentInactiveMap =
			SupportUtil.getCommentTicketEntryInactiveMap(preferences);

		Locale defaultLocale = LocaleUtil.getDefault();

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		for (TicketEntry ticketEntry : ticketEntries) {
			Date dueDate = ticketEntry.getDueDate();
			Date workerModifiedDate = ticketEntry.getWorkerModifiedDate();

			SupportRegion supportRegion = ticketEntry.getSupportRegion();

			TimeZone timeZone = supportRegion.getTimeZone();

			long diff = OSBDateUtil.getWeekdayTimeBetween(
				workerModifiedDate, now, timeZone);

			TicketWorker ticketWorker =
				ticketWorkerLocalService.fetchLatestTicketWorker(
					ticketEntry.getTicketEntryId());

			if (ticketWorker != null) {
				List<SupportWorker> supportWorkers =
					supportWorkerLocalService.getUserSupportWorkers(
						ticketWorker.getUserId());

				if (supportWorkers.size() > 0) {
					diff -= OSBDateUtil.getHolidayTime(
						supportWorkers.get(0), workerModifiedDate, now);
				}
			}

			if (diff > (4 * Time.DAY)) {
				TicketEntry oldticketEntry = (TicketEntry)ticketEntry.clone();

				ticketEntry.setModifiedDate(now);
				ticketEntry.setStatus(TicketEntryConstants.STATUS_ON_HOLD);
				ticketEntry.setHoldDate(now);
				ticketEntry.setDueDate(new Date(dueDate.getTime() + Time.DAY));
				ticketEntry.setWorkerModifiedDate(now);

				ticketEntryPersistence.update(ticketEntry, serviceContext);

				updateAuditEntry(
					OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK, 0,
					oldticketEntry, ticketEntry, new ServiceContext());

				Locale locale = LocaleUtil.fromLanguageId(
					ticketEntry.getLanguageId());

				String body = commentInactiveMap.get(locale);

				if (Validator.isNull(body)) {
					body = commentInactiveMap.get(defaultLocale);
				}

				int visibility = VisibilityConstants.LIFERAY_INC;

				if (ticketEntry.getEscalationLevel() ==
						TicketEntryConstants.ESCALATION_LEVEL_P1) {

					visibility = VisibilityConstants.WORKERS;
				}

				TicketComment ticketComment =
					ticketCommentLocalService.addTicketComment(
						OSBConstants.USER_DEFAULT_USER_ID,
						ticketEntry.getTicketEntryId(), body,
						TicketCommentConstants.TYPE_NORMAL, visibility,
						WorkflowConstants.STATUS_APPROVED, 0,
						TicketFlagConstants.TYPES_PENDING,
						new ServiceContext());

				sendEmail(
					0, ticketEntry, ticketComment, OSBMailActionKeys.ON_HOLD);
			}
			else if ((diff > (2 * Time.DAY)) && (diff < (3 * Time.DAY))) {
				Date newCustomerModifiedDate = OSBDateUtil.subtractWeekdayTime(
					now, (4 * Time.DAY), timeZone);
				Date newWorkerModifiedDate = OSBDateUtil.subtractWeekdayTime(
					now, (3 * Time.DAY), timeZone);

				ticketEntry.setModifiedDate(now);
				ticketEntry.setDueDate(
					new Date(dueDate.getTime() + (2 * Time.DAY)));
				ticketEntry.setCustomerModifiedDate(newCustomerModifiedDate);
				ticketEntry.setWorkerModifiedDate(newWorkerModifiedDate);

				ticketEntryPersistence.update(ticketEntry, serviceContext);

				Date newDueDate = ticketEntry.getDueDate();

				auditEntryLocalService.addAuditEntry(
					OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK,
					new Date(),
					PortalUtil.getClassNameId(TicketEntry.class.getName()),
					ticketEntry.getTicketEntryId(), 0,
					PortalUtil.getClassNameId(TicketEntry.class.getName()),
					ticketEntry.getTicketEntryId(),
					AuditEntryConstants.ACTION_UPDATE,
					AuditEntryConstants.FIELD_DUE_DATE,
					VisibilityConstants.WORKERS, String.valueOf(dueDate),
					String.valueOf(dueDate.getTime()),
					String.valueOf(newDueDate),
					String.valueOf(newDueDate.getTime()));

				Locale locale = LocaleUtil.fromLanguageId(
					ticketEntry.getLanguageId());

				String body = commentNoticeMap.get(locale);

				if (Validator.isNull(body)) {
					body = commentNoticeMap.get(defaultLocale);
				}

				int visibility = VisibilityConstants.LIFERAY_INC;

				if (ticketEntry.getEscalationLevel() ==
						TicketEntryConstants.ESCALATION_LEVEL_P1) {

					visibility = VisibilityConstants.WORKERS;
				}

				TicketComment ticketComment =
					ticketCommentLocalService.addTicketComment(
						OSBConstants.USER_DEFAULT_USER_ID,
						ticketEntry.getTicketEntryId(), body,
						TicketCommentConstants.TYPE_NORMAL, visibility,
						WorkflowConstants.STATUS_APPROVED, 0,
						TicketFlagConstants.TYPES_PENDING,
						new ServiceContext());

				sendEmail(
					0, ticketEntry, ticketComment, OSBMailActionKeys.COMMENTED);
			}
			else if ((diff >= (inactiveWarningTime - (35 * Time.MINUTE))) &&
					 (diff < inactiveWarningTime)) {

				Date newWorkerModifiedDate = OSBDateUtil.subtractWeekdayTime(
					now, inactiveWarningTime, timeZone);

				ticketEntry.setModifiedDate(now);
				ticketEntry.setWorkerModifiedDate(newWorkerModifiedDate);
				ticketEntryPersistence.update(ticketEntry, serviceContext);

				sendEmail(
					0, ticketEntry, null, OSBMailActionKeys.INACTIVE_WARNING);
			}

			reindexTicketEntry(ticketEntry.getTicketEntryId());
		}
	}

	public void checkOnHoldTicketEntries() throws Exception {
		Date now = new Date();

		List<TicketEntry> ticketEntries =
			ticketEntryFinder.findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
				0, (String)null, new int[0], null, null, null, null, null, null,
				TicketEntryConstants.STATUSES_ON_HOLD, new int[0], new int[0],
				new int[0], new long[0], new long[0], new long[0], new long[0],
				new long[0], new int[0], new int[0], null, null, null, null,
				null, null, null, null, null, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		for (TicketEntry ticketEntry : ticketEntries) {
			Date dueDate = ticketEntry.getDueDate();
			Date holdDate = ticketEntry.getHoldDate();

			SupportRegion supportRegion = ticketEntry.getSupportRegion();

			TimeZone timeZone = supportRegion.getTimeZone();

			if (holdDate != null) {
				long weekdayTimeBetween = OSBDateUtil.getWeekdayTimeBetween(
					holdDate, now, timeZone);

				dueDate = OSBDateUtil.addWeekdayTime(
					dueDate, weekdayTimeBetween, timeZone);
			}

			ticketEntry.setDueDate(dueDate);
			ticketEntry.setHoldDate(now);

			ticketEntryPersistence.update(ticketEntry, serviceContext);

			reindexTicketEntry(ticketEntry.getTicketEntryId());
		}
	}

	public void deleteTicketEntries(long accountEntryId)
		throws PortalException {

		List<TicketEntry> ticketEntries =
			ticketEntryPersistence.findByAccountEntryId(accountEntryId);

		for (TicketEntry ticketEntry : ticketEntries) {
			deleteTicketEntry(ticketEntry);
		}
	}

	@Override
	public TicketEntry deleteTicketEntry(TicketEntry ticketEntry)
		throws PortalException {

		// Ticket entry

		ticketEntryPersistence.remove(ticketEntry);

		// Ticket attachments

		List<TicketAttachment> ticketAttachments =
			ticketEntry.getTicketAttachments();

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			ticketAttachmentLocalService.deleteTicketAttachment(
				ticketAttachment);
		}

		// Ticket comments

		ticketCommentPersistence.removeByTicketEntryId(
			ticketEntry.getTicketEntryId());

		// Ticket feedback

		ticketFeedbackPersistence.removeByTicketEntryId(
			ticketEntry.getTicketEntryId());

		// Ticket links

		ticketLinkPersistence.removeByTicketEntryId(
			ticketEntry.getTicketEntryId());

		// Ticket workers

		ticketWorkerPersistence.removeByTicketEntryId(
			ticketEntry.getTicketEntryId());

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			TicketEntry.class);

		indexer.delete(ticketEntry);

		return ticketEntry;
	}

	public void escalateTicketEntry(long userId, long ticketEntryId)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		int escalationLevel = ticketEntry.getEscalationLevel();

		if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_P1) {
			ticketEntry.setEscalationLevel(
				TicketEntryConstants.ESCALATION_LEVEL_1);
		}
		else if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_1) {
			ticketEntry.setEscalationLevel(
				TicketEntryConstants.ESCALATION_LEVEL_2);
		}

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		auditEntryLocalService.addAuditEntry(
			user.getUserId(), user.getFullName(), new Date(),
			PortalUtil.getClassNameId(TicketEntry.class.getName()),
			ticketEntryId, 0,
			PortalUtil.getClassNameId(TicketEntry.class.getName()),
			ticketEntryId, AuditEntryConstants.ACTION_ESCALATE,
			AuditEntryConstants.FIELD_ESCALATION_LEVEL,
			VisibilityConstants.PUBLIC,
			TicketEntryConstants.getEscalationLevelLabel(escalationLevel),
			String.valueOf(escalationLevel),
			ticketEntry.getEscalationLevelLabel(),
			String.valueOf(ticketEntry.getEscalationLevel()));

		SupportWorker supportWorker =
			supportWorkerLocalService.getAvailableSupportWorker(ticketEntry);

		if (supportWorker != null) {
			long newPrimaryUserId = supportWorker.getUserId();

			if ((escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_1) &&
				(supportWorker.getEscalationLevel2Role() ==
					SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_OTHER)) {

				newPrimaryUserId = 0;
			}

			ticketWorkerLocalService.addTicketWorkers(
				OSBConstants.USER_DEFAULT_USER_ID,
				new long[] {supportWorker.getUserId()}, ticketEntryId,
				new long[] {PortalUtil.getClassNameId(SupportWorker.class)},
				new long[] {supportWorker.getSupportWorkerId()},
				new int[] {TicketWorkerConstants.ROLE_ESCALATED_DEVELOPER},
				newPrimaryUserId);
		}
		else {
			sendEmail(
				0, ticketEntry, null, OSBMailActionKeys.UNASSIGNED_ESCALATION);
		}

		ticketFlagLocalService.updateTicketFlag(
			userId, ticketEntry.getAccountEntryId(), ticketEntryId,
			TicketFlagConstants.TYPE_PENDING_LIFERAY,
			TicketFlagConstants.FLAG_TRUE);

		if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_P1) {
			TicketFeedback ticketFeedback =
				ticketFeedbackPersistence.fetchByTEI_S_First(
					ticketEntryId, TicketFeedbackConstants.SUBJECT_PARTNER,
					null);

			if (ticketFeedback == null) {
				ticketFeedbackLocalService.addTicketFeedback(
					userId, ticketEntryId,
					TicketFeedbackConstants.SUBJECT_PARTNER,
					TicketFeedbackConstants.SATISFIED_NOT_APPLICABLE);
			}
		}

		reindexTicketEntry(ticketEntryId);
	}

	public TicketEntry forwardTicketEntry(
			long userId, long ticketEntryId, String commentBody)
		throws PortalException {

		// Ticket worker

		User user = userPersistence.findByPrimaryKey(userId);

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		TicketWorker oldTicketWorker =
			ticketWorkerLocalService.fetchPrimaryTicketWorker(ticketEntryId);

		List<SupportWorker> supportWorkers =
			supportWorkerFinder.findByR_STT_SRI(
				SupportWorkerConstants.ROLE_WATCHER,
				SupportTeamConstants.TYPE_PLATINUM_CRITICAL,
				ticketEntry.getSupportRegionId(), StringPool.NOT_EQUAL, true,
				null);

		SupportWorker supportWorker =
			supportWorkerLocalService.getNextOpenSupportWorker(
				supportWorkers, ticketEntry);

		if (supportWorker == null) {
			throw new TicketEntryForwardingException();
		}

		ticketWorkerLocalService.addTicketWorkers(
			OSBConstants.USER_DEFAULT_USER_ID,
			new long[] {supportWorker.getUserId()},
			ticketEntry.getTicketEntryId(),
			new long[] {PortalUtil.getClassNameId(SupportWorker.class)},
			new long[] {supportWorker.getSupportWorkerId()},
			new int[] {TicketWorkerConstants.ROLE_DEVELOPER},
			supportWorker.getUserId());

		// Ticket comment

		TicketComment ticketComment =
			ticketCommentLocalService.addTicketComment(
				userId, ticketEntry.getTicketEntryId(), commentBody,
				TicketCommentConstants.TYPE_FORWARD,
				VisibilityConstants.LIFERAY_INC,
				WorkflowConstants.STATUS_APPROVED, 0,
				new int[] {TicketFlagConstants.TYPE_PENDING_LIFERAY},
				new ServiceContext());

		// Ticket flag

		ticketFlagLocalService.updateTicketFlag(
			userId, ticketEntry.getAccountEntryId(),
			ticketEntry.getTicketEntryId(),
			TicketFlagConstants.TYPE_LIFERAY_COMMENT_UNREAD,
			TicketFlagConstants.FLAG_TRUE);

		// Audit entry

		long classNameId = PortalUtil.getClassNameId(
			TicketEntry.class.getName());

		SupportTeam supportTeam = supportTeamLocalService.getSupportTeam(
			supportWorker.getSupportTeamId());

		String oldLabel = StringPool.BLANK;
		String oldValue = StringPool.BLANK;

		if (oldTicketWorker != null) {
			SupportWorker oldSupportWorker = null;

			if (oldTicketWorker.getSourceClassNameId() ==
					PortalUtil.getClassNameId(SupportWorker.class)) {

				oldSupportWorker = supportWorkerLocalService.fetchSupportWorker(
					oldTicketWorker.getSourceClassPK());
			}

			if (oldSupportWorker == null) {
				List<SupportWorker> oldSupportWorkers =
					supportWorkerLocalService.getUserSupportWorkers(
						oldTicketWorker.getUserId());

				if (!oldSupportWorkers.isEmpty()) {
					oldSupportWorker = oldSupportWorkers.get(0);
				}
			}

			if (oldSupportWorker != null) {
				SupportTeam oldSupportTeam = oldSupportWorker.getSupportTeam();

				oldLabel = oldSupportTeam.getName();
				oldValue = StringUtil.valueOf(
					oldSupportTeam.getSupportTeamId());
			}
		}

		auditEntryLocalService.addAuditEntry(
			ticketEntry.getUserId(), user.getFullName(), new Date(),
			classNameId, ticketEntryId, 0,
			PortalUtil.getClassNameId(SupportTeam.class.getName()),
			supportTeam.getSupportTeamId(), AuditEntryConstants.ACTION_ASSIGN,
			AuditEntryConstants.FIELD_SUPPORT_TEAM,
			VisibilityConstants.LIFERAY_INC, oldLabel, oldValue,
			supportTeam.getName(),
			StringUtil.valueOf(supportTeam.getSupportTeamId()));

		auditEntryLocalService.addAuditEntry(
			user.getUserId(), user.getFullName(), new Date(), classNameId,
			ticketEntryId, 0,
			PortalUtil.getClassNameId(TicketComment.class.getName()),
			ticketComment.getTicketCommentId(),
			AuditEntryConstants.ACTION_FORWARD, AuditEntryConstants.FIELD_BODY,
			ticketComment.getVisibility(), StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, ticketComment.getBody());

		// Email

		try {
			doSendEmail(
				0, ticketEntry, ticketComment, OSBMailActionKeys.FORWARDED);
		}
		catch (Exception e) {
			throw new PortalException(e.getMessage());
		}

		return ticketEntry;
	}

	public List<TicketEntry> getTicketEntries(
		Date modifiedDate, int start, int end) {

		return ticketEntryPersistence.findByGtModifiedDate(
			modifiedDate, start, end);
	}

	public Tuple getTicketEntries(Hits hits) throws PortalException {
		List<TicketEntry> ticketEntries = new ArrayList<>();

		boolean corruptIndex = false;

		List<Document> documents = hits.toList();

		for (Document document : documents) {
			long ticketEntryId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			try {
				TicketEntry ticketEntry = getTicketEntry(ticketEntryId);

				ticketEntries.add(ticketEntry);
			}
			catch (NoSuchTicketEntryException nstee) {
				corruptIndex = true;
			}
		}

		return new Tuple(ticketEntries, corruptIndex);
	}

	public List<TicketEntry> getTicketEntries(long accountEntryId) {
		return ticketEntryPersistence.findByAccountEntryId(accountEntryId);
	}

	public List<TicketEntry> getTicketEntries(
		long accountEntryId, int start, int end, OrderByComparator obc) {

		return ticketEntryPersistence.findByAccountEntryId(
			accountEntryId, start, end, obc);
	}

	public int getTicketEntriesCount(Date modifiedDate) {
		return ticketEntryPersistence.countByGtModifiedDate(modifiedDate);
	}

	public int getTicketEntriesCount(long accountEntryId) {
		return ticketEntryPersistence.countByAccountEntryId(accountEntryId);
	}

	public TicketEntry getTicketEntry(long accountEntryId, long ticketId)
		throws PortalException {

		return ticketEntryPersistence.findByAEI_TI(accountEntryId, ticketId);
	}

	public List<TicketEntry> getTicketFeedbackTicketEntries(
			long userId, int createdGTDay, int createdGTMonth,
			int createdGTYear, int status)
		throws PortalException {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", userId);

		List<TicketFeedback> ticketFeedbacks =
			ticketFeedbackLocalService.search(
				(String)null, createdGTDay, createdGTMonth, createdGTYear, 0, 0,
				0, 0, 0, 0, 0, 0, 0, null, null, status, null, null, null, null,
				params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		List<TicketEntry> ticketEntries = new ArrayList<>(
			ticketFeedbacks.size());

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			ticketEntries.add(ticketFeedback.getTicketEntry());
		}

		return ticketEntries;
	}

	public int[] getUserVisibilities(long userId, long ticketEntryId)
		throws PortalException {

		List<Integer> userVisibilities = new ArrayList<>();

		for (int i = 1; i < 4; i++) {
			if (!hasVisibility(userId, ticketEntryId, i)) {
				continue;
			}

			userVisibilities.add(i);
		}

		return ArrayUtil.toArray(userVisibilities.toArray(new Integer[0]));
	}

	public List<TicketEntry> getValidTicketEntries(long offeringEntryId) {
		return ticketEntryPersistence.findByOEI_NotR(
			offeringEntryId, TicketEntryConstants.RESOLUTION_REDIRECTED);
	}

	public int getValidTicketEntriesCount(long offeringEntryId) {
		return ticketEntryPersistence.countByOEI_NotR(
			offeringEntryId, TicketEntryConstants.RESOLUTION_REDIRECTED);
	}

	public boolean hasParticipant(long userId, long ticketEntryId)
		throws PortalException {

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		return hasParticipant(userId, ticketEntry);
	}

	public boolean hasParticipant(long userId, TicketEntry ticketEntry) {
		if (ticketEntry.getUserId() == userId) {
			return true;
		}

		TicketWorker ticketWorker = ticketWorkerLocalService.fetchTicketWorker(
			userId, ticketEntry.getTicketEntryId());

		if ((ticketWorker != null) &&
			(ticketWorker.getRole() != TicketWorkerConstants.ROLE_NONE)) {

			return true;
		}

		if (ticketCommentLocalService.getTicketCommentsCount(
				userId, ticketEntry.getTicketEntryId(),
				new int[] {
					VisibilityConstants.LIFERAY_INC, VisibilityConstants.PUBLIC,
				VisibilityConstants.WORKERS
					},
				new int[] {WorkflowConstants.STATUS_APPROVED}) > 0) {

			return true;
		}

		List<TicketAttachment> ticketAttachments =
			ticketAttachmentLocalService.getTicketAttachments(
				ticketEntry.getTicketEntryId());

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if (ticketAttachment.getUserId() == userId) {
				return true;
			}
		}

		List<TicketLink> ticketLinks = ticketLinkLocalService.getTicketLinks(
			ticketEntry.getTicketEntryId(),
			new int[] {
				VisibilityConstants.LIFERAY_INC, VisibilityConstants.PUBLIC,
				VisibilityConstants.WORKERS
			});

		for (TicketLink ticketLink : ticketLinks) {
			if (ticketLink.getUserId() == userId) {
				return true;
			}
		}

		return false;
	}

	public boolean hasVisibility(
			long userId, long ticketEntryId, int visibility)
		throws PortalException {

		if (visibility == VisibilityConstants.PUBLIC) {
			return true;
		}

		if (organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		if (visibility == VisibilityConstants.WORKERS) {
			TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
				ticketEntryId);

			AccountEntry accountEntry = ticketEntry.getAccountEntry();

			if (partnerWorkerLocalService.hasPartnerWorker(
					userId, accountEntry.getPartnerEntryId())) {

				return true;
			}
		}

		return false;
	}

	public void reindexTicketEntry(long ticketEntryId) throws PortalException {
		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		if (ticketEntry != null) {
			reindexTicketEntry(ticketEntry);
		}
	}

	public void reindexTicketEntry(TicketEntry ticketEntry)
		throws PortalException {

		if (TicketEntryThreadLocal.isReindex()) {
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				TicketEntry.class);

			indexer.reindex(ticketEntry);
		}
	}

	public Hits search(
		long searchUserId, long reportedByUserId, long accountEntryId,
		String name, int[] accountEntryTiers, Boolean satisfiedDueDate,
		Date createDateGT, Date createDateLT, String content, int[] status,
		int[] severity, int[] escalationLevel, long[] envOS, long[] envDB,
		long[] envJVM, long[] envAS, long[] envLFR, int[] components,
		int[] resolution, Date closedDateGT, Date closedDateLT, Date dueDateGT,
		Date dueDateLT, LinkedHashMap<String, Object> params, boolean andSearch,
		int start, int end, Sort[] sorts) {

		try {
			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(andSearch);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put("accountEntryCode", name);
			attributes.put("accountEntryId", accountEntryId);
			attributes.put("accountEntryName", name);
			attributes.put("accountEntryTier", accountEntryTiers);
			attributes.put("closedDateGT", closedDateGT);
			attributes.put("closedDateLT", closedDateLT);
			attributes.put("component", components);
			attributes.put("content", content);
			attributes.put("createDateGT", createDateGT);
			attributes.put("createDateLT", createDateLT);
			attributes.put("dueDateGT", dueDateGT);
			attributes.put("dueDateLT", dueDateLT);
			attributes.put("envAS", envAS);
			attributes.put("envDB", envDB);
			attributes.put("envJVM", envJVM);
			attributes.put("envLFR", envLFR);
			attributes.put("envOS", envOS);
			attributes.put("escalationLevel", escalationLevel);
			attributes.put("params", params);
			attributes.put("resolution", resolution);
			attributes.put("satisfiedDueDate", satisfiedDueDate);
			attributes.put("searchUserId", searchUserId);
			attributes.put("severity", severity);
			attributes.put("status", status);
			attributes.put("userId", reportedByUserId);

			searchContext.setAttributes(attributes);

			searchContext.setCompanyId(OSBConstants.COMPANY_ID);
			searchContext.setEnd(end);

			QueryConfig queryConfig = new QueryConfig();

			queryConfig.setAttribute(
				"selectedFieldNames", new String[] {Field.ENTRY_CLASS_PK});

			searchContext.setQueryConfig(queryConfig);

			searchContext.setSorts(sorts);
			searchContext.setStart(start);

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				TicketEntry.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public Hits search(
		long searchUserId, long reportedByUserId, long accountEntryId,
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, Sort[] sorts) {

		String name = null;
		String content = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			name = keywords;
			content = keywords;
		}
		else {
			andOperator = true;
		}

		return search(
			searchUserId, reportedByUserId, accountEntryId, name, new int[0],
			null, null, null, content, new int[0], new int[0], new int[0],
			new long[0], new long[0], new long[0], new long[0], new long[0],
			new int[0], new int[0], null, null, null, null, params, andOperator,
			start, end, sorts);
	}

	public List<TicketEntry> search(
		long reportedByUserId, String name, int[] accountEntryTiers,
		Boolean satisfiedDueDate, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, String subject, String description, String body,
		int[] status, int[] severity, int[] weights, int[] escalationLevel,
		long[] envOS, long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolution, int closedDateGTDay,
		int closedDateGTMonth, int closedDateGTYear, int closedDateLTDay,
		int closedDateLTMonth, int closedDateLTYear, int dueDateGTDay,
		int dueDateGTMonth, int dueDateGTYear, int dueDateLTDay,
		int dueDateLTMonth, int dueDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, OrderByComparator obc) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date closedDateGT = PortalUtil.getDate(
			closedDateGTMonth, closedDateGTDay, closedDateGTYear);
		Date closedDateLT = PortalUtil.getDate(
			closedDateLTMonth, closedDateLTDay, closedDateLTYear);
		Date dueDateGT = PortalUtil.getDate(
			dueDateGTMonth, dueDateGTDay, dueDateGTYear);
		Date dueDateLT = PortalUtil.getDate(
			dueDateLTMonth, dueDateLTDay, dueDateLTYear);

		return ticketEntryFinder.findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			reportedByUserId, name, accountEntryTiers, satisfiedDueDate,
			createDateGT, createDateLT, subject, description, body, status,
			severity, weights, escalationLevel, envOS, envDB, envJVM, envAS,
			envLFR, components, resolution, closedDateGT, closedDateLT,
			dueDateGT, dueDateLT, null, null, null, null, params, andSearch,
			start, end, obc);
	}

	public List<TicketEntry> search(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		return ticketEntryFinder.findByKeywords(
			keywords, params, start, end, obc);
	}

	public int searchCount(
		long reportedByUserId, String name, int[] accountEntryTiers,
		Boolean satisfiedDueDate, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, String subject, String description, String body,
		int[] status, int[] severity, int[] weights, int[] escalationLevel,
		long[] envOS, long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolution, int closedDateGTDay,
		int closedDateGTMonth, int closedDateGTYear, int closedDateLTDay,
		int closedDateLTMonth, int closedDateLTYear, int dueDateGTDay,
		int dueDateGTMonth, int dueDateGTYear, int dueDateLTDay,
		int dueDateLTMonth, int dueDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date closedDateGT = PortalUtil.getDate(
			closedDateGTMonth, closedDateGTDay, closedDateGTYear);
		Date closedDateLT = PortalUtil.getDate(
			closedDateLTMonth, closedDateLTDay, closedDateLTYear);
		Date dueDateGT = PortalUtil.getDate(
			dueDateGTMonth, dueDateGTDay, dueDateGTYear);
		Date dueDateLT = PortalUtil.getDate(
			dueDateLTMonth, dueDateLTDay, dueDateLTYear);

		return ticketEntryFinder.countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			reportedByUserId, name, accountEntryTiers, satisfiedDueDate,
			createDateGT, createDateLT, subject, description, body, status,
			severity, weights, escalationLevel, envOS, envDB, envJVM, envAS,
			envLFR, components, resolution, closedDateGT, closedDateLT,
			dueDateGT, dueDateLT, null, null, null, null, params, andSearch);
	}

	public int searchCount(
		String keywords, LinkedHashMap<String, Object> params) {

		return ticketEntryFinder.countByKeywords(keywords, params);
	}

	public void sendEmail(
			long userId, long ticketEntryId, TicketComment ticketComment,
			String action)
		throws PortalException {

		TicketEntry ticketEntry = ticketEntryPersistence.fetchByPrimaryKey(
			ticketEntryId);

		sendEmail(userId, ticketEntry, ticketComment, action);
	}

	public void sendEmail(
			long userId, TicketEntry ticketEntry, TicketComment ticketComment,
			String action)
		throws PortalException {

		try {
			doSendEmail(userId, ticketEntry, ticketComment, action);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public void syncToJIRA(long ticketEntryId) throws PortalException {
		if (!TicketEntryThreadLocal.isSyncToJIRA()) {
			return;
		}

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		AccountEntry accountEntry = ticketEntry.getAccountEntry();

		if ((accountEntry.getType() ==
				AccountEntryConstants.TYPE_INTERNAL_TEST) ||
			(accountEntry.getType() ==
				AccountEntryConstants.TYPE_TRIAL)) {

			return;
		}

		Message message = new Message();

		message.put("accountEntryCode", accountEntry.getCode());
		message.put("component", ticketEntry.getComponent());

		Date createDate = ticketEntry.getCreateDate();

		message.put("createDate", createDate.getTime());

		message.put("displayId", ticketEntry.getDisplayId());

		Date dueDate = ticketEntry.getDueDate();

		message.put("dueDate", dueDate.getTime());

		message.put("escalationLevel", ticketEntry.getEscalationLevel());

		long classNameId = PortalUtil.getClassNameId(TicketEntry.class);

		ExternalIdMapper externalIdMapper =
			externalIdMapperPersistence.fetchByC_C_T_First(
				classNameId, ticketEntryId, ExternalIdMapperConstants.TYPE_JIRA,
				null);

		if (externalIdMapper != null) {
			message.put("jiraTicketKey", externalIdMapper.getExternalId());
		}

		Date modifiedDate = ticketEntry.getModifiedDate();

		message.put("modifiedDate", modifiedDate.getTime());

		TicketWorker primaryTicketWorker =
			ticketWorkerLocalService.fetchPrimaryTicketWorker(ticketEntryId);

		List<TicketWorker> ticketWorkers =
			ticketWorkerLocalService.getTicketWorkers(ticketEntryId);

		Set<String> otherTicketWorkerScreenNames = new HashSet<>();

		for (TicketWorker ticketWorker : ticketWorkers) {
			if ((primaryTicketWorker != null) &&
				(primaryTicketWorker.getUserId() == ticketWorker.getUserId())) {

				continue;
			}

			try {
				User user = userPersistence.findByPrimaryKey(
					ticketWorker.getUserId());

				if (partnerWorkerLocalService.hasPartnerWorker(
						user.getUserId(), accountEntry.getPartnerEntryId())) {

					otherTicketWorkerScreenNames.add("support-partner");
				}
				else {
					otherTicketWorkerScreenNames.add(user.getScreenName());
				}
			}
			catch (NoSuchUserException nsue) {
			}
		}

		if (!otherTicketWorkerScreenNames.isEmpty()) {
			message.put(
				"otherTicketWorkerScreenNames",
				StringUtil.merge(otherTicketWorkerScreenNames));
		}

		PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

		if (partnerEntry != null) {
			message.put("partnerEntryCode", partnerEntry.getCode());
		}

		if (primaryTicketWorker != null) {
			User user = userPersistence.findByPrimaryKey(
				primaryTicketWorker.getUserId());

			if (partnerWorkerLocalService.hasPartnerWorker(
					user.getUserId(), accountEntry.getPartnerEntryId())) {

				message.put("primaryTicketWorkerScreenName", "support-partner");
			}
			else {
				message.put(
					"primaryTicketWorkerScreenName", user.getScreenName());
			}
		}

		message.put("resolution", ticketEntry.getResolution());
		message.put("severity", ticketEntry.getSeverity());
		message.put("status", ticketEntry.getStatus());

		SupportRegion supportRegion = ticketEntry.getSupportRegion();

		message.put("supportRegionName", supportRegion.getName());

		SupportResponse supportResponse = ticketEntry.getSupportResponse();

		message.put(
			"supportResponseSupportLevel", supportResponse.getSupportLevel());

		message.put("ticketEntryId", ticketEntry.getTicketEntryId());

		MessageBusUtil.sendMessage("liferay/osb_synchronize_jira", message);
	}

	public TicketEntry updateCustomerModifiedDate(
			long userId, long ticketEntryId, Date customerModifiedDate)
		throws PortalException {

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		if ((ticketEntry.getStatus() == TicketEntryConstants.STATUS_INACTIVE) ||
			(ticketEntry.getStatus() == TicketEntryConstants.STATUS_ON_HOLD)) {

			Date dueDate = getProgressingDueDate(
				ticketEntry, ticketEntry.getSupportResponse(),
				ticketEntry.getOfferingEntryId(), ticketEntry.getSeverity(),
				TicketEntryConstants.STATUS_PENDING_WORKER);

			updateSupportWorkerAssignedWork(
				ticketEntryId, ticketEntry.getStatus(),
				TicketEntryConstants.STATUS_PENDING_WORKER,
				ticketEntry.getWeight(), ticketEntry.getWeight());

			String oldStatusLabel = ticketEntry.getStatusLabel();
			String oldStatusValue = String.valueOf(ticketEntry.getStatus());

			ticketEntry.setStatus(TicketEntryConstants.STATUS_PENDING_WORKER);
			ticketEntry.setHoldDate(getHoldDate(ticketEntry));
			ticketEntry.setDueDate(dueDate);

			User user = userPersistence.findByPrimaryKey(userId);

			long auditSetId = auditEntryLocalService.getNextAuditSetId(
				TicketEntry.class.getName(), ticketEntryId);
			long classNameId = PortalUtil.getClassNameId(
				TicketEntry.class.getName());

			auditEntryLocalService.addAuditEntry(
				userId, user.getFullName(), customerModifiedDate, classNameId,
				ticketEntryId, auditSetId, classNameId, ticketEntryId,
				AuditEntryConstants.ACTION_UPDATE,
				AuditEntryConstants.FIELD_STATUS, VisibilityConstants.PUBLIC,
				oldStatusLabel, oldStatusValue, ticketEntry.getStatusLabel(),
				String.valueOf(ticketEntry.getStatus()));
		}

		ticketEntry.setCustomerModifiedDate(customerModifiedDate);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		return ticketEntry;
	}

	public TicketEntry updatePendingTypes(
			long userId, long ticketEntryId, int[] pendingTypes)
		throws PortalException {

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		if ((ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) ||
			(ticketEntry.getStatus() ==
				TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION)) {

			return ticketEntry;
		}

		validatePendingTypes(
			userId, ticketEntry.getAccountEntryId(), pendingTypes);

		return updatePendingTypes(userId, ticketEntry, pendingTypes);
	}

	public TicketEntry updateTicketEntry(
			long userId, long ticketEntryId, long assigneeUserId,
			long supportRegionId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute)
		throws PortalException {

		TicketEntry ticketEntry = null;

		try {
			TicketEntryThreadLocal.setReindex(false);
			TicketEntryThreadLocal.setSyncToJIRA(false);

			ticketEntry = doUpdateTicketEntry(
				userId, ticketEntryId, assigneeUserId, supportRegionId,
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute);
		}
		finally {
			TicketEntryThreadLocal.setReindex(true);
			TicketEntryThreadLocal.setSyncToJIRA(true);
		}

		reindexTicketEntry(ticketEntry);

		syncToJIRA(ticketEntry.getTicketEntryId());

		return ticketEntry;
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
		throws PortalException {

		TicketEntry ticketEntry = null;

		try {
			TicketEntryThreadLocal.setReindex(false);
			TicketEntryThreadLocal.setSyncToJIRA(false);

			ticketEntry = doUpdateTicketEntry(
				userId, ticketEntryId, reportedByUserId, offeringEntryId,
				supportRegionId, languageId, subject, description,
				reproductionSteps, severity, status, weight, escalationLevel,
				component, subcomponent, subcomponentCustom, resolution,
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, ignoreDueDate, ticketInformationFieldsMap,
				pendingTypes, ticketAttachments, serviceContext);
		}
		finally {
			TicketEntryThreadLocal.setReindex(true);
			TicketEntryThreadLocal.setSyncToJIRA(true);
		}

		reindexTicketEntry(ticketEntry);

		syncToJIRA(ticketEntry.getTicketEntryId());

		return ticketEntry;
	}

	public TicketEntry updateTicketId(long ticketEntryId, long ticketId)
		throws PortalException {

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		ticketEntry.setTicketId(ticketId);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		reindexTicketEntry(ticketEntry);

		return ticketEntry;
	}

	public TicketEntry updateWorkerModifiedDate(
			long ticketEntryId, Date workerModifiedDate)
		throws PortalException {

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		ticketEntry.setWorkerModifiedDate(workerModifiedDate);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		return ticketEntry;
	}

	protected boolean assignAvailableTicketWorker(
			AccountEntry accountEntry, TicketEntry ticketEntry)
		throws PortalException {

		long userId = 0;
		long sourceClassNameId = 0;
		long sourceClassPK = 0;

		if (ticketEntry.getEscalationLevel() ==
				TicketEntryConstants.ESCALATION_LEVEL_P1) {

			List<PartnerWorker> partnerWorkers =
				partnerWorkerLocalService.getPartnerWorkers(
					accountEntry.getPartnerEntryId(),
					PartnerWorkerConstants.ROLE_MANAGER);

			if (partnerWorkers.isEmpty()) {
				partnerWorkers = partnerWorkerLocalService.getPartnerWorkers(
					accountEntry.getPartnerEntryId(),
					PartnerWorkerConstants.ROLE_MEMBER);

				if (partnerWorkers.isEmpty()) {
					return false;
				}
			}

			PartnerWorker partnerWorker = null;

			for (PartnerWorker curPartnerWorker : partnerWorkers) {
				if (curPartnerWorker.isActive()) {
					partnerWorker = curPartnerWorker;

					break;
				}
			}

			userId = partnerWorker.getUserId();
			sourceClassNameId = PortalUtil.getClassNameId(PartnerWorker.class);
			sourceClassPK = partnerWorker.getPartnerWorkerId();
		}
		else {
			SupportWorker supportWorker =
				supportWorkerLocalService.getAvailableSupportWorker(
					ticketEntry);

			if (supportWorker == null) {
				return false;
			}

			User user = userLocalService.getUser(
				OSBConstants.USER_DEFAULT_USER_ID);

			SupportTeam supportTeam = supportTeamLocalService.getSupportTeam(
				supportWorker.getSupportTeamId());

			auditEntryLocalService.addAuditEntry(
				user.getUserId(), user.getFullName(), new Date(),
				PortalUtil.getClassNameId(TicketEntry.class.getName()),
				ticketEntry.getTicketEntryId(), 0,
				PortalUtil.getClassNameId(SupportTeam.class.getName()),
				supportTeam.getSupportTeamId(),
				AuditEntryConstants.ACTION_ASSIGN,
				AuditEntryConstants.FIELD_SUPPORT_TEAM,
				VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
				StringPool.BLANK, supportTeam.getName(),
				StringUtil.valueOf(supportTeam.getSupportTeamId()));

			userId = supportWorker.getUserId();
			sourceClassNameId = PortalUtil.getClassNameId(SupportWorker.class);
			sourceClassPK = supportWorker.getSupportWorkerId();
		}

		ticketWorkerLocalService.addTicketWorkers(
			OSBConstants.USER_DEFAULT_USER_ID, new long[] {userId},
			ticketEntry.getTicketEntryId(), new long[] {sourceClassNameId},
			new long[] {sourceClassPK},
			new int[] {TicketWorkerConstants.ROLE_DEVELOPER}, userId);

		try {
			doSendEmail(0, ticketEntry, null, OSBMailActionKeys.ASSIGNED);
		}
		catch (Exception e) {
			throw new PortalException(e.getMessage());
		}

		return true;
	}

	protected TicketEntry doAddTicketEntry(
			long userId, long offeringEntryId, long supportRegionId,
			String languageId, long ticketId, String subject,
			String description, int systemStatus, int status, int weight,
			int escalationLevel, int component, int subcomponent,
			Map<Long, String> ticketInformationFieldsMap,
			List<TicketAttachment> ticketAttachments)
		throws PortalException {

		// Ticket entry

		User user = userPersistence.findByPrimaryKey(userId);
		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		ProductEntry productEntry = offeringEntry.getProductEntry();
		Date now = new Date();

		int severity = getSeverity(systemStatus, productEntry.getEnvironment());

		String validateType = getValidateType(true, userId);

		validate(
			validateType, offeringEntry, languageId, subject, description,
			systemStatus, severity, status, weight, component, subcomponent,
			null, 0, ticketInformationFieldsMap, ticketAttachments);

		AccountEntry accountEntry = offeringEntry.getAccountEntry();

		if (accountEntry.isPartnerManagedSupport() &&
			(component !=
				TicketEntryConstants.
					COMPONENT_ACTIVATION_KEY_PRODUCT_ADMINISTRATION) &&
			(component != TicketEntryConstants.COMPONENT_LICENSE) &&
			(component != TicketEntryConstants.COMPONENT_PATCH_MANAGEMENT) &&
			(component !=
				TicketEntryConstants.COMPONENT_PROJECT_ADMINISTRATION)) {

			if (!partnerWorkerLocalService.hasPartnerWorker(
					userId, accountEntry.getPartnerEntryId())) {

				escalationLevel = TicketEntryConstants.ESCALATION_LEVEL_P1;
			}
		}

		if (supportRegionId <= 0) {
			List<SupportRegion> supportRegions =
				accountEntry.getSupportRegions();

			if (!supportRegions.isEmpty()) {
				SupportRegion supportRegion = supportRegions.get(0);

				supportRegionId = supportRegion.getSupportRegionId();
			}
		}

		if (Validator.isNull(languageId)) {
			String[] languageIds = accountEntry.getLanguageIds();

			if (!ArrayUtil.isEmpty(languageIds)) {
				languageId = languageIds[0];
			}
		}

		long ticketEntryId = counterLocalService.increment(
			TicketEntry.class.getName());

		TicketEntry ticketEntry = ticketEntryPersistence.create(ticketEntryId);

		ticketEntry.setUserId(user.getUserId());
		ticketEntry.setUserName(user.getFullName());
		ticketEntry.setCreateDate(now);
		ticketEntry.setModifiedDate(now);
		ticketEntry.setAccountEntryId(offeringEntry.getAccountEntryId());
		ticketEntry.setOrderEntryId(offeringEntry.getOrderEntryId());
		ticketEntry.setOfferingEntryId(offeringEntryId);
		ticketEntry.setProductEntryId(offeringEntry.getProductEntryId());
		ticketEntry.setSupportResponseId(getSupportResponseId(offeringEntry));
		ticketEntry.setSupportRegionId(supportRegionId);
		ticketEntry.setLanguageId(languageId);
		ticketEntry.setSubject(subject);
		ticketEntry.setDescription(description);
		ticketEntry.setSeverity(severity);
		ticketEntry.setStatus(status);
		ticketEntry.setWeight(weight);
		ticketEntry.setEscalationLevel(escalationLevel);
		ticketEntry.setComponent(component);
		ticketEntry.setSubcomponent(subcomponent);
		ticketEntry.setDueDate(getDefaultDueDate(now, ticketEntry, severity));
		ticketEntry.setIgnoreDueDate(false);
		ticketEntry.setCustomerModifiedDate(now);
		ticketEntry.setWorkerModifiedDate(now);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		// Ticket information

		ticketInformationLocalService.updateTicketInformation(
			ticketEntryId, ticketInformationFieldsMap);

		// Ticket attachments

		List<ObjectValuePair<String, File>> files = new ArrayList<>();
		List<Integer> types = new ArrayList<>();

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if (ticketAttachment.getTicketAttachmentId() > 0) {
				ticketAttachmentLocalService.updateTicketAttachment(
					ticketAttachment.getTicketAttachmentId(),
					ticketEntry.getTicketEntryId(), ticketAttachment.getType(),
					ticketAttachment.getVisibility());

				continue;
			}

			ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
				ticketAttachment.getFileName(), ticketAttachment.getFile());

			files.add(ovp);

			types.add(ticketAttachment.getType());
		}

		if (!files.isEmpty()) {
			ticketAttachmentLocalService.addTicketAttachments(
				userId, ticketEntry.getTicketEntryId(),
				TicketSolutionConstants.DEFAULT_SOLUTION_ID, files, types,
				VisibilityConstants.PUBLIC, WorkflowConstants.STATUS_APPROVED,
				new ServiceContext());
		}

		// Ticket ID

		if (ticketId <= 0) {
			ticketId = counterLocalService.increment(
				getCounterName(offeringEntry.getAccountEntryId()));
		}

		ticketEntry.setTicketId(ticketId);

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		// Ticket worker

		boolean assigned = assignAvailableTicketWorker(
			accountEntry, ticketEntry);

		if (assigned) {

			// Ticket flags

			if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_P1) {
				updatePendingTypes(
					userId, ticketEntry,
					new int[] {TicketFlagConstants.TYPE_PENDING_PARTNER});
			}
			else {
				updatePendingTypes(
					userId, ticketEntry,
					new int[] {TicketFlagConstants.TYPE_PENDING_LIFERAY});
			}
		}
		else {
			SupportRegion supportRegion = ticketEntry.getSupportRegion();

			User managerUser = supportRegion.getManagerUser();

			if (managerUser != null) {
				long sourceClassNameId = PortalUtil.getClassNameId(
					SupportRegion.class);

				ticketWorkerLocalService.addTicketWorkers(
					OSBConstants.USER_DEFAULT_USER_ID,
					new long[] {managerUser.getUserId()},
					ticketEntry.getTicketEntryId(),
					new long[] {sourceClassNameId},
					new long[] {managerUser.getUserId()},
					new int[] {TicketWorkerConstants.ROLE_MANAGER}, userId);
			}

			sendEmail(
				0, ticketEntry, null, OSBMailActionKeys.UNASSIGNED_ESCALATION);
		}

		// Ticket feedback

		if ((escalationLevel != TicketEntryConstants.ESCALATION_LEVEL_P1) &&
			accountEntry.isPartnerManagedSupport() &&
			partnerWorkerLocalService.hasPartnerWorker(
				userId, accountEntry.getPartnerEntryId())) {

			PartnerWorker partnerWorker =
				partnerWorkerLocalService.getPartnerWorker(
					userId, accountEntry.getPartnerEntryId());

			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				ticketFeedbackLocalService.addTicketFeedback(
					userId, ticketEntryId,
					TicketFeedbackConstants.SUBJECT_PARTNER,
					TicketFeedbackConstants.SATISFIED_NOT_APPLICABLE);
			}
		}

		// Audit entry

		long classNameId = PortalUtil.getClassNameId(
			TicketEntry.class.getName());

		auditEntryLocalService.addAuditEntry(
			user.getUserId(), user.getFullName(), now, classNameId,
			ticketEntryId, 0, classNameId, ticketEntryId,
			AuditEntryConstants.ACTION_ADD,
			AuditEntryConstants.FIELD_SYSTEM_STATUS, VisibilityConstants.PUBLIC,
			StringPool.BLANK, StringPool.BLANK,
			TicketEntryConstants.getSystemStatusLabel(systemStatus),
			String.valueOf(systemStatus));

		return ticketEntry;
	}

	protected void doSendEmail(
			long userId, TicketEntry ticketEntry, TicketComment ticketComment,
			String action)
		throws Exception {

		PortletPreferences preferences = SupportUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			SupportUtil.getEmailTicketEntrySubjectMap(preferences);
		Map<Locale, String> bodyMap = SupportUtil.getEmailTicketEntryBodyMap(
			preferences);
		Map<Locale, String> actionInformationMap =
			SupportUtil.getActionInformationMap(preferences);
		Map<Locale, String> commentMap =
			SupportUtil.getEmailTicketEntryCommentMap(preferences);
		Map<Locale, String> dueDateMap =
			SupportUtil.getEmailTicketEntryDueDateMap(preferences);

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

		String ticketEntryURL =
			layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR + "support/ticket/" +
				ticketEntry.getDisplayId();

		AccountEntry accountEntry = ticketEntry.getAccountEntry();
		OfferingEntry offeringEntry = ticketEntry.getOfferingEntry();
		SupportResponse supportResponse = ticketEntry.getSupportResponse();
		ProductEntry productEntry = ticketEntry.getProductEntry();

		Date createDate = ticketEntry.getCreateDate();
		Date supportEndDate = offeringEntry.getSupportEndDate();

		String accountEntrySalesReps = SupportUtil.getAccountWorkers(
			accountEntry.getAccountEntryId(),
			AccountWorkerConstants.ROLE_SALES);
		String accountEntrySpecialists = SupportUtil.getAccountWorkers(
			accountEntry.getAccountEntryId(),
			AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST);

		String actionInformation = StringPool.BLANK;

		if (action.equals(OSBMailActionKeys.FORWARDED)) {
			AuditEntry auditEntry = auditEntryPersistence.findByC_C_F_A_Last(
				PortalUtil.getClassNameId(TicketEntry.class.getName()),
				ticketEntry.getTicketEntryId(),
				AuditEntryConstants.FIELD_SUPPORT_TEAM,
				AuditEntryConstants.ACTION_ASSIGN, null);

			StringBundler sb = new StringBundler(5);

			sb.append(action);
			sb.append(StringPool.COLON);
			sb.append(auditEntry.getOldLabel());
			sb.append(" > ");
			sb.append(auditEntry.getNewLabel());

			actionInformation = sb.toString();
		}

		for (Map.Entry<Locale, String> subjectEntry : subjectMap.entrySet()) {
			Locale locale = subjectEntry.getKey();

			String accountEntrySalesRepsLabel = StringPool.BLANK;

			if (Validator.isNotNull(accountEntrySalesReps)) {
				accountEntrySalesRepsLabel = LanguageUtil.get(
					locale, "sales-reps") + StringPool.COLON;
			}

			String accountEntrySpecialistsLabel = StringPool.BLANK;

			if (Validator.isNotNull(accountEntrySpecialists)) {
				accountEntrySpecialistsLabel =
					LanguageUtil.get(locale, "advocacy-specialists") +
						StringPool.COLON;
			}

			String envBrowserLabel = LanguageUtil.get(
				locale, ticketEntry.getEnvBrowserLabel());

			if (Validator.isNotNull(ticketEntry.getEnvBrowserCustom())) {
				envBrowserLabel +=
					" - " + HtmlUtil.escape(ticketEntry.getEnvBrowserCustom());
			}

			String envOSLabel = LanguageUtil.get(
				locale, ticketEntry.getEnvOSLabel());

			if (Validator.isNotNull(ticketEntry.getEnvOSCustom())) {
				envOSLabel +=
					" - " + HtmlUtil.escape(ticketEntry.getEnvOSCustom());
			}

			String subject = StringUtil.replace(
				subjectEntry.getValue(),
				new String[] {
					"[$ACCOUNT_ENTRY_NAME$]", "[$ACCOUNT_ENTRY_SALES_REPS$]",
					"[$ACCOUNT_ENTRY_SALES_REPS_LABEL$]",
					"[$ACCOUNT_ENTRY_SPECIALISTS$]",
					"[$ACCOUNT_ENTRY_SPECIALISTS_LABEL$]", "[$ACTION$]",
					"[$APPLICATION_SERVER$]", "[$BROWSER$]", "[$COMPONENT$]",
					"[$DATABASE$]", "[$DESCRIPTION$]", "[$ESCALATION_LEVEL$]",
					"[$ESCALATION_LEVEL_COLOR$]", "[$JAVA_VIRTUAL_MACHINE$]",
					"[$OFFERING_ENTRY_STATUS$]", "[$OPERATING_SYSTEM$]",
					"[$OTHER_ASSIGNEES$]", "[$PRIMARY_ASSIGNEE$]",
					"[$PRODUCT_ENTRY_NAME$]", "[$REPORTED_BY$]",
					"[$RESOLUTION$]", "[$SEVERITY$]", "[$SEVERITY_COLOR$]",
					"[$STATUS$]", "[$SUBJECT$]", "[$SUPPORT_RESPONSE_NAME$]",
					"[$TICKET_DISPLAY_ID$]", "[$TICKET_ENTRY_URL$]",
					"[$VERSION$]", "[$VERSION_TYPE$]"
				},
				new String[] {
					accountEntry.getName(), accountEntrySalesReps,
					accountEntrySalesRepsLabel, accountEntrySpecialists,
					accountEntrySpecialistsLabel,
					action.equals(OSBMailActionKeys.FORWARDED) ?
						actionInformation : action,
					LanguageUtil.get(locale, ticketEntry.getEnvASLabel()),
					envBrowserLabel,
					LanguageUtil.get(locale, ticketEntry.getComponentLabel()),
					LanguageUtil.get(locale, ticketEntry.getEnvDBLabel()),
					SupportUtil.getHTML(ticketEntry.getDescription()),
					LanguageUtil.get(
						locale, ticketEntry.getEscalationLevelLabel()),
					getColorEscalationLevel(
						GetterUtil.getInteger(
							ticketEntry.getEscalationLevelLabel())),
					LanguageUtil.get(locale, ticketEntry.getEnvJVMLabel()),
					getOfferingEntryStatus(createDate, supportEndDate, locale),
					envOSLabel,
					SupportUtil.getOtherAssigneesUserNames(
						ticketEntry.getTicketEntryId()),
					SupportUtil.getPrimaryAssigneeUserName(
						ticketEntry.getTicketEntryId()),
					productEntry.getName(),
					HtmlUtil.escape(ticketEntry.getUserName()),
					LanguageUtil.get(locale, ticketEntry.getResolutionLabel()),
					LanguageUtil.get(locale, ticketEntry.getSeverityLabel()),
					getColorSeverity(ticketEntry.getSeverity()),
					LanguageUtil.get(locale, ticketEntry.getStatusLabel()),
					HtmlUtil.escape(ticketEntry.getSubject()),
					supportResponse.getName(), ticketEntry.getDisplayId(),
					ticketEntryURL,
					LanguageUtil.get(locale, ticketEntry.getEnvLFRLabel()),
					productEntry.getName() + " Version"
				});

			subjectEntry.setValue(subject);
		}

		for (Map.Entry<Locale, String> bodyEntry : bodyMap.entrySet()) {
			Locale locale = bodyEntry.getKey();

			String accountEntrySalesRepsLabel = StringPool.BLANK;

			if (Validator.isNotNull(accountEntrySalesReps)) {
				accountEntrySalesRepsLabel = LanguageUtil.get(
					locale, "sales-reps") + StringPool.COLON;
			}

			String accountEntrySpecialistsLabel = StringPool.BLANK;

			if (Validator.isNotNull(accountEntrySpecialists)) {
				accountEntrySpecialistsLabel =
					LanguageUtil.get(locale, "advocacy-specialists") +
						StringPool.COLON;
			}

			String envBrowserLabel = LanguageUtil.get(
				locale, ticketEntry.getEnvBrowserLabel());

			if (Validator.isNotNull(ticketEntry.getEnvBrowserCustom())) {
				envBrowserLabel +=
					" - " + HtmlUtil.escape(ticketEntry.getEnvBrowserCustom());
			}

			String envOSLabel = LanguageUtil.get(
				locale, ticketEntry.getEnvOSLabel());

			if (Validator.isNotNull(ticketEntry.getEnvOSCustom())) {
				envOSLabel +=
					" - " + HtmlUtil.escape(ticketEntry.getEnvOSCustom());
			}

			String body = StringUtil.replace(
				bodyEntry.getValue(),
				new String[] {
					"[$ACCOUNT_ENTRY_NAME$]", "[$ACCOUNT_ENTRY_SALES_REPS$]",
					"[$ACCOUNT_ENTRY_SALES_REPS_LABEL$]",
					"[$ACCOUNT_ENTRY_SPECIALISTS$]",
					"[$ACCOUNT_ENTRY_SPECIALISTS_LABEL$]", "[$ACTION$]",
					"[$APPLICATION_SERVER$]", "[$BROWSER$]", "[$COMPONENT$]",
					"[$DATABASE$]", "[$DESCRIPTION$]", "[$ESCALATION_LEVEL$]",
					"[$ESCALATION_LEVEL_COLOR$]", "[$JAVA_VIRTUAL_MACHINE$]",
					"[$OFFERING_ENTRY_STATUS$]", "[$OPERATING_SYSTEM$]",
					"[$OTHER_ASSIGNEES$]", "[$PRIMARY_ASSIGNEE$]",
					"[$PRODUCT_ENTRY_NAME$]", "[$REPORTED_BY$]",
					"[$RESOLUTION$]", "[$SEVERITY$]", "[$SEVERITY_COLOR$]",
					"[$STATUS$]", "[$SUBJECT$]", "[$SUPPORT_RESPONSE_NAME$]",
					"[$TICKET_DISPLAY_ID$]", "[$TICKET_ENTRY_URL$]",
					"[$VERSION$]", "[$VERSION_TYPE$]"
				},
				new String[] {
					accountEntry.getName(), accountEntrySalesReps,
					accountEntrySalesRepsLabel, accountEntrySpecialists,
					accountEntrySpecialistsLabel,
					action.equals(OSBMailActionKeys.FORWARDED) ?
						actionInformation : action,
					LanguageUtil.get(locale, ticketEntry.getEnvASLabel()),
					envBrowserLabel,
					LanguageUtil.get(locale, ticketEntry.getComponentLabel()),
					LanguageUtil.get(locale, ticketEntry.getEnvDBLabel()),
					SupportUtil.getHTML(ticketEntry.getDescription()),
					LanguageUtil.get(
						locale, ticketEntry.getEscalationLevelLabel()),
					getColorEscalationLevel(
						GetterUtil.getInteger(
							ticketEntry.getEscalationLevelLabel())),
					LanguageUtil.get(locale, ticketEntry.getEnvJVMLabel()),
					getOfferingEntryStatus(createDate, supportEndDate, locale),
					envOSLabel,
					SupportUtil.getOtherAssigneesUserNames(
						ticketEntry.getTicketEntryId()),
					SupportUtil.getPrimaryAssigneeUserName(
						ticketEntry.getTicketEntryId()),
					productEntry.getName(),
					HtmlUtil.escape(ticketEntry.getUserName()),
					LanguageUtil.get(locale, ticketEntry.getResolutionLabel()),
					LanguageUtil.get(locale, ticketEntry.getSeverityLabel()),
					getColorSeverity(ticketEntry.getSeverity()),
					LanguageUtil.get(locale, ticketEntry.getStatusLabel()),
					HtmlUtil.escape(ticketEntry.getSubject()),
					supportResponse.getName(), ticketEntry.getDisplayId(),
					ticketEntryURL,
					LanguageUtil.get(locale, ticketEntry.getEnvLFRLabel()),
					productEntry.getName() + " Version"
				});

			bodyEntry.setValue(body);
		}

		Set<Long> workerUserIds = getWorkerUserIds(
			ticketEntry, supportResponse, ticketComment, action);

		sendMail(
			userId, workerUserIds, subjectMap, bodyMap, actionInformationMap,
			commentMap, dueDateMap, ticketEntry, ticketComment, true, action,
			actionInformation);

		Set<Long> customerUserIds = getCustomerUserIds(
			ticketEntry, ticketComment, action, workerUserIds);

		sendMail(
			userId, customerUserIds, subjectMap, bodyMap, actionInformationMap,
			commentMap, dueDateMap, ticketEntry, ticketComment, false, action,
			actionInformation);
	}

	protected TicketEntry doUpdateTicketEntry(
			long userId, long ticketEntryId, long assigneeUserId,
			long supportRegionId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date dueDate = PortalUtil.getDate(
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			user.getTimeZone(), (Class<? extends PortalException>)null);
		Date now = new Date();

		if (((assigneeUserId != 0) && (supportRegionId != 0)) ||
			((assigneeUserId == 0) && (supportRegionId == 0))) {

			throw new TicketEntryAssigneeException();
		}

		if ((dueDate != null) && dueDate.before(now)) {
			throw new TicketEntryDueDateException();
		}

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		if (assigneeUserId != 0) {
			ticketWorkerLocalService.addTicketWorkers(
				userId, new long[] {assigneeUserId}, ticketEntryId, new long[0],
				new long[0], new int[] {TicketWorkerConstants.ROLE_DEVELOPER},
				assigneeUserId);
		}
		else if (supportRegionId != 0) {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("accountTier", ticketEntry.getAccountTier());

			if (!ArrayUtil.contains(
					TicketEntryConstants.COMPONENTS_DEPRECATED,
					ticketEntry.getComponent())) {

				params.put("component", ticketEntry.getComponent());
			}

			params.put("severity", ticketEntry.getSeverity());
			params.put("supportRegion", supportRegionId);
			params.put("supportTeamType", SupportTeamConstants.TYPE_NORMAL);

			SupportWorker supportWorker =
				supportWorkerLocalService.getMostAvailableSupportWorker(
					ticketEntry, params);

			ticketWorkerLocalService.addTicketWorkers(
				userId, new long[] {supportWorker.getUserId()}, ticketEntryId,
				new long[] {PortalUtil.getClassNameId(SupportWorker.class)},
				new long[] {supportWorker.getSupportWorkerId()},
				new int[] {TicketWorkerConstants.ROLE_DEVELOPER},
				supportWorker.getUserId());
		}

		if (dueDate != null) {
			Date oldDueDate = ticketEntry.getDueDate();

			ticketEntry.setDueDate(dueDate);

			//TODO implement serviceContext how needed

			ServiceContext serviceContext = new ServiceContext();

			ticketEntryPersistence.update(ticketEntry, serviceContext);

			if (oldDueDate.getTime() != dueDate.getTime()) {
				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now,
					PortalUtil.getClassNameId(TicketEntry.class.getName()),
					ticketEntryId, 0,
					PortalUtil.getClassNameId(TicketEntry.class.getName()),
					ticketEntryId, AuditEntryConstants.ACTION_UPDATE,
					AuditEntryConstants.FIELD_DUE_DATE,
					VisibilityConstants.WORKERS, String.valueOf(oldDueDate),
					String.valueOf(oldDueDate.getTime()),
					String.valueOf(dueDate), String.valueOf(dueDate.getTime()));
			}
		}

		return ticketEntry;
	}

	protected TicketEntry doUpdateTicketEntry(
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
		throws PortalException {

		// Ticket entry

		User user = userPersistence.findByPrimaryKey(userId);
		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		ProductEntry productEntry = offeringEntry.getProductEntry();
		Date dueDate = PortalUtil.getDate(
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			user.getTimeZone(), (Class<? extends PortalException>)null);
		Date now = new Date();

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);

		TicketEntry oldTicketEntry = (TicketEntry)ticketEntry.clone();

		String validateType = getValidateType(false, userId);

		validate(
			validateType, productEntry, languageId, subject, description,
			severity, status, weight, component, subcomponent, resolution,
			ticketInformationFieldsMap, ticketAttachments);

		ProductEntry oldProductEntry = oldTicketEntry.getProductEntry();

		int oldEnvironment = oldProductEntry.getEnvironment();

		int environment = productEntry.getEnvironment();

		if ((ticketEntry.getSeverity() == severity) &&
			(ticketEntry.getSeverity() ==
				SupportResponseConstants.SEVERITY_CRITICAL) &&
			((oldEnvironment == ProductEntryConstants.ENVIRONMENT_ANY) ||
			 (oldEnvironment ==
				 ProductEntryConstants.ENVIRONMENT_PRODUCTION)) &&
			((environment != ProductEntryConstants.ENVIRONMENT_ANY) &&
			 (environment != ProductEntryConstants.ENVIRONMENT_PRODUCTION))) {

			severity = SupportResponseConstants.SEVERITY_MAJOR;
		}

		Date customerModifiedDate = ticketEntry.getCustomerModifiedDate();

		if (ArrayUtil.contains(
				TicketEntryConstants.STATUSES_CLOSED,
				ticketEntry.getStatus()) &&
			(status == TicketEntryConstants.STATUS_REOPENED)) {

			customerModifiedDate = now;

			if (!hasAvailableTicketWorker(ticketEntry)) {
				boolean assigned = assignAvailableTicketWorker(
					offeringEntry.getAccountEntry(), ticketEntry);

				if (!assigned) {
					sendEmail(
						0, ticketEntry, null, OSBMailActionKeys.UNASSIGNED);
				}
			}

			updateTicketAttachmentDeleteDates(ticketEntryId, null);
		}

		long dueDateDiff = 0;

		if (dueDate != null) {
			Date curDueDate = ticketEntry.getDueDate();

			dueDateDiff = Math.abs(curDueDate.getTime() - dueDate.getTime());
		}

		if (dueDateDiff < (60 * Time.SECOND)) {
			dueDate = getProgressingDueDate(
				ticketEntry, offeringEntry.getSupportResponse(),
				offeringEntryId, severity, status);
		}

		Date workerModifiedDate = getWorkerModifiedDate(ticketEntry, status);

		if (ticketEntry.getUserId() != reportedByUserId) {
			User reportedByUser = userPersistence.findByPrimaryKey(
				reportedByUserId);

			ticketEntry.setUserId(reportedByUserId);
			ticketEntry.setUserName(reportedByUser.getFullName());
		}

		ticketEntry.setModifiedDate(now);
		ticketEntry.setAccountEntryId(offeringEntry.getAccountEntryId());
		ticketEntry.setOrderEntryId(offeringEntry.getOrderEntryId());
		ticketEntry.setOfferingEntryId(offeringEntryId);
		ticketEntry.setProductEntryId(offeringEntry.getProductEntryId());
		ticketEntry.setSupportResponseId(getSupportResponseId(offeringEntry));
		ticketEntry.setSupportRegionId(supportRegionId);
		ticketEntry.setLanguageId(languageId);
		ticketEntry.setSubject(subject);
		ticketEntry.setDescription(description);
		ticketEntry.setReproductionSteps(reproductionSteps);
		ticketEntry.setSeverity(severity);
		ticketEntry.setStatus(status);
		ticketEntry.setWeight(weight);
		ticketEntry.setEscalationLevel(escalationLevel);
		ticketEntry.setComponent(component);
		ticketEntry.setSubcomponent(subcomponent);
		ticketEntry.setSubcomponentCustom(subcomponentCustom);
		ticketEntry.setResolution(resolution);
		ticketEntry.setHoldDate(getHoldDate(ticketEntry));
		ticketEntry.setClosedDate(getClosedDate(userId, ticketEntry));
		ticketEntry.setDueDate(dueDate);
		ticketEntry.setIgnoreDueDate(ignoreDueDate);
		ticketEntry.setCustomerModifiedDate(customerModifiedDate);
		ticketEntry.setWorkerModifiedDate(workerModifiedDate);

		//TODO implement serviceContext how needed

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			TicketEntry.class.getName(), ticketEntryId);

		serviceContext.setAttribute("auditSetId", auditSetId);
		serviceContext.setCreateDate(now);

		// Ticket information

		ticketInformationLocalService.updateTicketInformation(
			userId, user.getFullName(), ticketEntryId,
			ticketInformationFieldsMap, serviceContext);

		// Ticket attachments

		List<ObjectValuePair<String, File>> files = new ArrayList<>();
		List<Integer> types = new ArrayList<>();

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if (ticketAttachment.getTicketAttachmentId() > 0) {
				ticketAttachmentLocalService.updateTicketAttachment(
					ticketAttachment.getTicketAttachmentId(),
					ticketEntry.getTicketEntryId(), ticketAttachment.getType(),
					ticketAttachment.getVisibility());

				continue;
			}

			ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
				ticketAttachment.getFileName(), ticketAttachment.getFile());

			files.add(ovp);

			types.add(ticketAttachment.getType());
		}

		if (!files.isEmpty()) {
			ticketAttachmentLocalService.addTicketAttachments(
				userId, ticketEntry.getTicketEntryId(),
				TicketSolutionConstants.DEFAULT_SOLUTION_ID, files, types,
				VisibilityConstants.PUBLIC, WorkflowConstants.STATUS_APPROVED,
				new ServiceContext());
		}

		// Ticket ID

		long ticketId = getTicketId(ticketEntry, offeringEntry);

		ticketEntry.setTicketId(ticketId);

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		if (oldTicketEntry.getStatus() != status) {

			// Ticket solution

			boolean updateTicketSolutionStatus = GetterUtil.getBoolean(
				serviceContext.getAttribute("updateTicketSolutionStatus"));

			if (updateTicketSolutionStatus) {
				ticketSolutionLocalService.updateStatus(
					ticketEntryId, status, resolution);
			}

			if ((status == TicketEntryConstants.STATUS_CLOSED) &&
				(resolution !=
					TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER)) {

				// File repository files

				FileRepository fileRepository = SupportUtil.getFileRepository(
					ticketEntry);

				FileRepositoryUtil.cleanTempFiles(
					fileRepository, ticketEntry.getTicketEntryId());

				updateTicketAttachmentDeleteDates(
					ticketEntryId, new Date(now.getTime() + (30 * Time.DAY)));
			}
			else if (status == TicketEntryConstants.STATUS_REOPENED) {
				List<TicketFeedback> ticketFeedbacks =
					ticketFeedbackPersistence.findByTEI_S_NotS(
						ticketEntryId, TicketFeedbackConstants.SUBJECT_LIFERAY,
						TicketFeedbackConstants.STATUS_CLOSED);

				for (TicketFeedback ticketFeedback : ticketFeedbacks) {
					ticketFeedback.setStatus(
						TicketFeedbackConstants.STATUS_CLOSED);

					ticketFeedbackPersistence.update(
						ticketFeedback, serviceContext);
				}
			}
		}

		// Ticket flags

		if ((pendingTypes.length > 0) &&
			(status != TicketEntryConstants.STATUS_CLOSED) &&
			(oldTicketEntry.getStatus() !=
				TicketEntryConstants.STATUS_CLOSED)) {

			updatePendingTypes(userId, ticketEntryId, pendingTypes);
		}
		else {
			pendingTypes = getPendingTypes(
				oldTicketEntry.getStatus(), status, resolution);

			updatePendingTypes(userId, ticketEntry, pendingTypes);
		}

		// Support workers

		updateSupportWorkerAssignedWork(
			ticketEntryId, oldTicketEntry.getStatus(), status,
			oldTicketEntry.getWeight(), weight);

		// Audit entry

		updateAuditEntry(
			user.getUserId(), user.getFullName(), auditSetId, oldTicketEntry,
			ticketEntry, serviceContext);

		return ticketEntry;
	}

	protected String getActionInformationTemplate(
		User user, Map<Locale, String> actionInformationMap,
		Locale defaultLocale, String action, String actionInformation) {

		if (!action.equals(OSBMailActionKeys.FORWARDED)) {
			return StringPool.BLANK;
		}

		String actionInformationTemplate = actionInformationMap.get(
			user.getLocale());

		if (Validator.isNull(actionInformationTemplate)) {
			actionInformationTemplate = actionInformationMap.get(defaultLocale);
		}

		actionInformationTemplate = StringUtil.replace(
			actionInformationTemplate, "[$ACTION_INFORMATION$]",
			actionInformation);

		return actionInformationTemplate;
	}

	protected Date getClosedDate(long userId, TicketEntry ticketEntry) {
		if (ArrayUtil.contains(
				TicketEntryConstants.STATUSES_CLOSED,
				ticketEntry.getStatus())) {

			if (ticketEntry.getClosedDate() == null) {
				return new Date();
			}
			else {
				return ticketEntry.getClosedDate();
			}
		}
		else {
			return null;
		}
	}

	protected String getColorEscalationLevel(int escalationLevel) {
		if (escalationLevel == 2) {
			return "#F08000";
		}
		else {
			return "#00ADEF";
		}
	}

	protected String getColorSeverity(int severity) {
		if (severity == SupportResponseConstants.SEVERITY_CRITICAL) {
			return "#EF000B";
		}
		else if (severity == SupportResponseConstants.SEVERITY_MAJOR) {
			return "#F08000";
		}
		else {
			return "#00ADEF";
		}
	}

	protected String getCommentTemplate(
			User user, Map<Locale, String> commentMap, Locale defaultLocale,
			TicketEntry ticketEntry, TicketComment ticketComment)
		throws Exception {

		if (ticketComment == null) {
			return StringPool.BLANK;
		}

		String commentTemplate = commentMap.get(user.getLocale());

		if (Validator.isNull(commentTemplate)) {
			commentTemplate = commentMap.get(defaultLocale);
		}

		Format fullDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.FULL, DateFormat.FULL, user.getLocale(),
			user.getTimeZone());

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

		String ticketEntryURL =
			layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR + "support/ticket/" +
				ticketEntry.getDisplayId();

		String permalinkURL =
			ticketEntryURL + "/comment/" + ticketComment.getTicketCommentId();

		Company company = companyLocalService.getCompany(
			OSBConstants.COMPANY_ID);

		User commentUser = userPersistence.findByPrimaryKey(
			ticketComment.getUserId());

		String portalURL = PortalUtil.getPortalURL(
			company.getVirtualHostname(), PortalUtil.getPortalPort(false),
			false);

		String portraitURL = UserConstants.getPortraitURL(
			portalURL + PortalUtil.getPathImage(), commentUser.isMale(),
			commentUser.getPortraitId());

		commentTemplate = StringUtil.replace(
			commentTemplate,
			new String[] {
				"[$BODY$]", "[$CREATE_DATE$]", "[$PERMALINK_URL$]",
				"[$USER_FULL_NAME$]", "[$USER_PORTRAIT_URL$]"
			},
			new String[] {
				SupportUtil.getHTML(ticketComment),
				fullDateFormatDateTime.format(ticketComment.getCreateDate()),
				permalinkURL, HtmlUtil.escape(commentUser.getFullName()),
				portraitURL
			});

		return commentTemplate;
	}

	protected String getCounterName(long accountEntryId) {
		return TicketEntry.class.getName().concat(StringPool.POUND).concat(
			String.valueOf(accountEntryId));
	}

	protected Set<Long> getCustomerUserIds(
			TicketEntry ticketEntry, TicketComment ticketComment, String action,
			Set<Long> workerUserIds)
		throws PortalException {

		Set<Long> customerUserIds = new HashSet<>();

		if (action.equals(OSBMailActionKeys.ASSIGNED) ||
			action.equals(OSBMailActionKeys.ESCALATED) ||
			action.equals(OSBMailActionKeys.GAME_PLAN_COMMENTED) ||
			action.equals(OSBMailActionKeys.INACTIVE_WARNING) ||
			(action.equals(OSBMailActionKeys.LOGGED_CALL) &&
			 (ticketComment.getVisibility() ==
				 VisibilityConstants.LIFERAY_INC)) ||
			action.equals(OSBMailActionKeys.UNASSIGNED) ||
			action.equals(OSBMailActionKeys.UNASSIGNED_ESCALATION)) {

			return customerUserIds;
		}

		// Ticket reporter

		if (!workerUserIds.contains(ticketEntry.getUserId())) {
			customerUserIds.add(ticketEntry.getUserId());
		}

		// Account customers

		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByAccountEntryId(
				ticketEntry.getAccountEntryId());

		for (AccountCustomer accountCustomer : accountCustomers) {
			User user = userLocalService.getUserById(
				accountCustomer.getUserId());

			boolean subscribed = subscriptionLocalService.isSubscribed(
				user.getCompanyId(), user.getUserId(),
				TicketEntry.class.getName(), ticketEntry.getTicketEntryId());

			if ((accountCustomer.getNotifications() ==
					AccountCustomerConstants.NOTIFICATIONS_NONE) &&
				!subscribed &&
				!ticketEntry.hasParticipant(accountCustomer.getUserId())) {

				continue;
			}

			customerUserIds.add(accountCustomer.getUserId());
		}

		if ((ticketComment != null) &&
			(ticketComment.getVisibility() != VisibilityConstants.PUBLIC)) {

			Iterator<Long> itr = customerUserIds.iterator();

			while (itr.hasNext()) {
				Long customerUserId = itr.next();

				if (!ticketCommentLocalService.hasVisibility(
						customerUserId, ticketEntry.getTicketEntryId(),
						ticketComment.getVisibility())) {

					itr.remove();
				}
			}
		}

		return customerUserIds;
	}

	protected Date getDefaultDueDate(
			Date createDate, TicketEntry ticketEntry, int severity)
		throws PortalException {

		SupportResponse supportResponse = ticketEntry.getSupportResponse();

		SupportRegion supportRegion = ticketEntry.getSupportRegion();

		TimeZone timeZone = supportRegion.getTimeZone();

		int severityResolution = supportResponse.getSeverityResolution(
			severity);

		return OSBDateUtil.addWeekdayTime(
			createDate, (severityResolution * Time.DAY), timeZone);
	}

	protected String getDueDateTemplate(
		User user, Map<Locale, String> dueDateMap, Locale defaultLocale,
		TicketEntry ticketEntry, boolean worker) {

		if (!worker) {
			return StringPool.BLANK;
		}

		if (ticketEntry.getComponent() ==
				TicketEntryConstants.COMPONENT_LIFERAY_DEVELOPER_STUDIO) {

			return StringPool.BLANK;
		}

		String dueDateTemplate = dueDateMap.get(user.getLocale());

		if (Validator.isNull(dueDateTemplate)) {
			dueDateTemplate = dueDateMap.get(defaultLocale);
		}

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			user.getLocale(), user.getTimeZone());

		dueDateTemplate = StringUtil.replace(
			dueDateTemplate, new String[] {"[$DUE_DATE$]"},
			new String[] {dateFormatDateTime.format(ticketEntry.getDueDate())});

		return dueDateTemplate;
	}

	protected Date getHoldDate(TicketEntry ticketEntry) {
		int status = ticketEntry.getStatus();

		if (ArrayUtil.contains(TicketEntryConstants.STATUSES_ON_HOLD, status)) {
			if (ticketEntry.getHoldDate() == null) {
				return new Date();
			}
			else {
				return ticketEntry.getHoldDate();
			}
		}
		else {
			return null;
		}
	}

	protected String getOfferingEntryStatus(
		Date createDate, Date supportEndDate, Locale locale) {

		if (createDate.after(supportEndDate)) {
			return "<span style='color: red;'> (".concat(
				LanguageUtil.get(locale, "expired")).concat(") </span>");
		}
		else {
			return StringPool.BLANK;
		}
	}

	protected int[] getPendingTypes(int oldStatus, int status, int resolution) {
		if ((oldStatus != TicketEntryConstants.STATUS_CLOSED) &&
			(oldStatus != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) &&
			((status == TicketEntryConstants.STATUS_CLOSED) ||
			 (status == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION))) {

			if (resolution ==
					TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER) {

				return new int[] {TicketFlagConstants.TYPE_PENDING_CUSTOMER};
			}

			return new int[0];
		}
		else if ((oldStatus !=
					TicketEntryConstants.STATUS_SOLUTION_DELIVERED) &&
				 (status == TicketEntryConstants.STATUS_SOLUTION_DELIVERED)) {

			return new int[] {TicketFlagConstants.TYPE_PENDING_CUSTOMER};
		}
		else if ((oldStatus != TicketEntryConstants.STATUS_REOPENED) &&
				 (status == TicketEntryConstants.STATUS_REOPENED)) {

			int[] pendingTypes = {
				TicketFlagConstants.TYPE_PENDING_LIFERAY,
				TicketFlagConstants.TYPE_PENDING_PARTNER
			};

			return pendingTypes;
		}

		return null;
	}

	protected Date getProgressingDueDate(
			TicketEntry ticketEntry, SupportResponse supportResponse,
			long offeringEntryId, int severity, int status)
		throws PortalException {

		Date dueDate = ticketEntry.getDueDate();
		Date now = new Date();

		SupportRegion supportRegion = ticketEntry.getSupportRegion();

		TimeZone timeZone = supportRegion.getTimeZone();

		if ((ticketEntry.getOfferingEntryId() != offeringEntryId) ||
			(ticketEntry.getSeverity() != severity)) {

			int curSeverityResolution = supportResponse.getSeverityResolution(
				ticketEntry.getSeverity());

			int severityResolution = supportResponse.getSeverityResolution(
				severity);

			long diff = (severityResolution - curSeverityResolution) * Time.DAY;

			if (diff > 0) {
				dueDate = OSBDateUtil.addWeekdayTime(dueDate, diff, timeZone);
			}
			else {
				dueDate = OSBDateUtil.subtractWeekdayTime(
					dueDate, Math.abs(diff), timeZone);
			}
		}

		if (!ArrayUtil.contains(TicketEntryConstants.STATUSES_CLOSED, status)) {
			Date closedDate = ticketEntry.getClosedDate();

			if (closedDate != null) {
				long weekdayTimeBetween = OSBDateUtil.getWeekdayTimeBetween(
					closedDate, now, timeZone);

				return OSBDateUtil.addWeekdayTime(
					dueDate, weekdayTimeBetween, timeZone);
			}
		}

		if (!ArrayUtil.contains(
				TicketEntryConstants.STATUSES_ON_HOLD, status)) {

			Date holdDate = ticketEntry.getHoldDate();

			if (holdDate != null) {
				long weekdayTimeBetween = OSBDateUtil.getWeekdayTimeBetween(
					holdDate, now, timeZone);

				return OSBDateUtil.addWeekdayTime(
					dueDate, weekdayTimeBetween, timeZone);
			}
		}

		return dueDate;
	}

	protected int getSeverity(int systemStatus, int environment) {
		if ((systemStatus == TicketEntryConstants.SYSTEM_STATUS_INOPERABLE) &&
			((environment == ProductEntryConstants.ENVIRONMENT_ANY) ||
			 (environment == ProductEntryConstants.ENVIRONMENT_PRODUCTION))) {

			return SupportResponseConstants.SEVERITY_CRITICAL;
		}
		else if ((systemStatus == TicketEntryConstants.SYSTEM_STATUS_MINOR) ||
				 (systemStatus ==
					 TicketEntryConstants.SYSTEM_STATUS_NEW_ACTIVATION) ||
				 (systemStatus ==
					 TicketEntryConstants.SYSTEM_STATUS_UPGRADING)) {

			return SupportResponseConstants.SEVERITY_MINOR;
		}
		else {
			return SupportResponseConstants.SEVERITY_MAJOR;
		}
	}

	protected long getSupportResponseId(OfferingEntry offeringEntry)
		throws PortalException {

		SupportResponse supportResponse = offeringEntry.getSupportResponse();

		if (supportResponse.getSupportLevel() !=
				SupportResponseConstants.SUPPORT_LEVEL_FLOATING) {

			return supportResponse.getSupportResponseId();
		}

		SupportResponse highestSupportResponse =
			supportResponseFinder.fetchByAccountEntry(
				offeringEntry.getAccountEntryId());

		if ((highestSupportResponse != null) &&
			(highestSupportResponse.getSupportLevel() !=
				SupportResponseConstants.SUPPORT_LEVEL_FLOATING)) {

			return highestSupportResponse.getSupportResponseId();
		}

		SupportResponse limitedSupportResponse =
			supportResponseLocalService.fetchSupportResponseByName("Limited");

		if (limitedSupportResponse != null) {
			return limitedSupportResponse.getSupportResponseId();
		}

		return supportResponse.getSupportResponseId();
	}

	protected long getTicketId(
		TicketEntry ticketEntry, OfferingEntry offeringEntry) {

		if (ticketEntry.getAccountEntryId() !=
				offeringEntry.getAccountEntryId()) {

			return counterLocalService.increment(
				getCounterName(offeringEntry.getAccountEntryId()));
		}

		return ticketEntry.getTicketId();
	}

	protected String getValidateType(boolean isNew, long userId) {
		if (isNew) {
			return "add";
		}
		else {
			return "update";
		}
	}

	protected Date getWorkerModifiedDate(TicketEntry ticketEntry, int status) {
		if (((status == TicketEntryConstants.STATUS_CLOSED) &&
			 (ticketEntry.getClosedDate() == null)) ||
			(ArrayUtil.contains(TicketEntryConstants.STATUSES_ACTIVE, status) &&
			 !ArrayUtil.contains(
				 TicketEntryConstants.STATUSES_ACTIVE,
				 ticketEntry.getStatus()))) {

			return new Date();
		}

		return ticketEntry.getWorkerModifiedDate();
	}

	protected Set<Long> getWorkerUserIds(
			TicketEntry ticketEntry, SupportResponse supportResponse,
			TicketComment ticketComment, String action)
		throws PortalException {

		Set<Long> workerUserIds = new HashSet<>();

		if (action.equals(OSBMailActionKeys.LOGGED_CALL) &&
			(ticketComment.getVisibility() == VisibilityConstants.PUBLIC)) {

			return workerUserIds;
		}

		if (action.equals(OSBMailActionKeys.UNASSIGNED) ||
			action.equals(OSBMailActionKeys.UNASSIGNED_ESCALATION)) {

			workerUserIds.add(OSBConstants.USER_SUPPORT_PM_USER_ID);

			return workerUserIds;
		}

		// Ticket subscribers

		List<Subscription> subscriptions =
			subscriptionLocalService.getSubscriptions(
				OSBConstants.COMPANY_ID, TicketEntry.class.getName(),
				ticketEntry.getTicketEntryId());

		for (Subscription subscription : subscriptions) {
			if (accountCustomerLocalService.hasAccountCustomer(
					subscription.getUserId(),
					ticketEntry.getAccountEntryId()) ||
				ticketWorkerLocalService.hasTicketWorker(
					subscription.getUserId(), ticketEntry.getTicketEntryId()) ||
				accountWorkerLocalService.hasAccountWorker(
					subscription.getUserId(),
					ticketEntry.getAccountEntryId()) ||
				partnerWorkerLocalService.hasPartnerWorker(
					subscription.getUserId(), ticketEntry.getTicketEntryId()) ||
				supportWorkerLocalService.hasSupportWorker(
					subscription.getUserId(), ticketEntry.getTicketEntryId())) {

				continue;
			}

			workerUserIds.add(subscription.getUserId());
		}

		// Ticket workers

		List<TicketWorker> ticketWorkers =
			ticketWorkerPersistence.findByTicketEntryId(
				ticketEntry.getTicketEntryId());

		for (TicketWorker ticketWorker : ticketWorkers) {
			if (action.equals(OSBMailActionKeys.INACTIVE_WARNING) &&
				(ticketWorker.getRole() !=
					TicketWorkerConstants.ROLE_DEVELOPER)) {

				continue;
			}

			workerUserIds.add(ticketWorker.getUserId());
		}

		if (action.equals(OSBMailActionKeys.INACTIVE_WARNING)) {
			return workerUserIds;
		}

		// Account workers

		List<AccountWorker> accountWorkers =
			accountWorkerPersistence.findByAEI_NotN(
				ticketEntry.getAccountEntryId(),
				AccountWorkerConstants.NOTIFICATIONS_NONE);

		for (AccountWorker accountWorker : accountWorkers) {
			int notifications = accountWorker.getNotifications();

			if ((notifications != AccountWorkerConstants.NOTIFICATIONS_ALL) &&
				(notifications !=
					AccountWorkerConstants.NOTIFICATIONS_TICKETS)) {

				continue;
			}

			workerUserIds.add(accountWorker.getUserId());
		}

		// Partner workers

		AccountEntry accountEntry = ticketEntry.getAccountEntry();

		if (accountEntry.isPartnerManagedSupport()) {
			List<PartnerWorker>partnerWorkers =
				partnerWorkerPersistence.findByPEI_NotN(
					accountEntry.getPartnerEntryId(),
					PartnerWorkerConstants.NOTIFICATIONS_NONE);

			for (PartnerWorker partnerWorker : partnerWorkers) {
				workerUserIds.add(partnerWorker.getUserId());
			}
		}

		// Support workers

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("supportRegion", ticketEntry.getSupportRegionId());
		params.put(
			"supportTeamLanguage",
			new String[] {
				ticketEntry.getLanguageId(), ticketEntry.getLanguageId()
			});
		params.put("supportTeamType", SupportTeamConstants.TYPE_NORMAL);

		List<SupportWorker> supportWorkers = supportWorkerFinder.findByU_E(
			null, 0, params);

		for (SupportWorker supportWorker : supportWorkers) {
			int notifications = supportWorker.getNotifications();

			if (notifications == SupportWorkerConstants.NOTIFICATIONS_NONE) {
				continue;
			}

			if ((notifications ==
					SupportWorkerConstants.NOTIFICATIONS_ESCALATION_LEVEL) &&
				(supportWorker.getEscalationLevel() !=
					ticketEntry.getEscalationLevel())) {

				continue;
			}

			workerUserIds.add(supportWorker.getUserId());
		}

		if (supportResponse.isPlatinumLevel() &&
			(ticketEntry.getSeverity() ==
				SupportResponseConstants.SEVERITY_CRITICAL)) {

			List<SupportWorker> chinaSupportWorkers =
				supportWorkerLocalService.getSupportWorkersBySupportRegionId(
					OSBConstants.SUPPORT_REGION_CHINA_ID);

			for (SupportWorker supportWorker : chinaSupportWorkers) {
				workerUserIds.add(supportWorker.getUserId());
			}

			List<SupportWorker> hungarySupportWorkers =
				supportWorkerLocalService.getSupportWorkersBySupportRegionId(
					OSBConstants.SUPPORT_REGION_HUNGARY_ID);

			for (SupportWorker supportWorker : hungarySupportWorkers) {
				workerUserIds.add(supportWorker.getUserId());
			}

			List<SupportWorker> usSupportWorkers =
				supportWorkerLocalService.getSupportWorkersBySupportRegionId(
					OSBConstants.SUPPORT_REGION_US_ID);

			for (SupportWorker supportWorker : usSupportWorkers) {
				workerUserIds.add(supportWorker.getUserId());
			}
		}

		if ((ticketComment != null) &&
			(ticketComment.getVisibility() != VisibilityConstants.PUBLIC)) {

			Iterator<Long> itr = workerUserIds.iterator();

			while (itr.hasNext()) {
				Long workerUserId = itr.next();

				if (!ticketCommentLocalService.hasVisibility(
						workerUserId, ticketEntry.getTicketEntryId(),
						ticketComment.getVisibility())) {

					itr.remove();
				}
			}
		}

		return workerUserIds;
	}

	protected boolean hasAvailableTicketWorker(TicketEntry ticketEntry)
		throws PortalException {

		if (ticketEntry.getEscalationLevel() ==
				TicketEntryConstants.ESCALATION_LEVEL_P1) {

			long[] ticketWorkerUserIds = SupportUtil.getTicketWorkerUserIds(
				ticketEntry.getTicketEntryId());

			if (ArrayUtil.isEmpty(ticketWorkerUserIds)) {
				return false;
			}

			if (partnerWorkerPersistence.countByU_R(
					ticketWorkerUserIds,
					new int[] {PartnerWorkerConstants.ROLE_MANAGER}) > 0) {

				return true;
			}
		}
		else {
			TicketWorker ticketWorker =
				ticketWorkerLocalService.fetchPrimaryTicketWorker(
					ticketEntry.getTicketEntryId());

			if (ticketWorker == null) {
				return false;
			}

			if (supportWorkerLocalService.isClockedIn(
					ticketWorker.getUserId())) {

				return true;
			}
		}

		return false;
	}

	protected void sendMail(
			long userId, Set<Long> sendUserIds, Map<Locale, String> subjectMap,
			Map<Locale, String> bodyMap,
			Map<Locale, String> actionInformationMap,
			Map<Locale, String> commentMap, Map<Locale, String> dueDateMap,
			TicketEntry ticketEntry, TicketComment ticketComment,
			boolean worker, String action, String actionInformation)
		throws Exception {

		String fromName = null;

		if (userId > 0) {
			User user = userPersistence.findByPrimaryKey(userId);

			fromName = user.getFullName();
		}
		else {
			fromName = PrefsPropsUtil.getString(
				OSBConstants.COMPANY_ID, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		}

		fromName += OSBConstants.EMAIL_SUPPORT_FROM_NAME_SUFFIX;

		InternetAddress from = new InternetAddress(
			PortletPropsValues.SUPPORT_EMAIL_ADDRESS_FROM, fromName);

		Locale defaultLocale = LocaleUtil.getDefault();

		for (long sendUserId : sendUserIds) {
			User user = null;

			try {
				user = userLocalService.getUserById(sendUserId);
			}
			catch (NoSuchUserException nsue) {
				continue;
			}

			if (!user.isActive()) {
				continue;
			}

			InternetAddress to = new InternetAddress(
				user.getEmailAddress(), user.getFullName());

			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
				user.getLocale(), user.getTimeZone());

			String subject = subjectMap.get(user.getLocale());

			if (Validator.isNull(subject)) {
				subject = subjectMap.get(defaultLocale);
			}

			String actionInformationTemplate = getActionInformationTemplate(
				user, actionInformationMap, defaultLocale, action,
				actionInformation);
			String commentTemplate = getCommentTemplate(
				user, commentMap, defaultLocale, ticketEntry, ticketComment);
			String dueDateTemplate = getDueDateTemplate(
				user, dueDateMap, defaultLocale, ticketEntry, worker);

			String curSubject = StringUtil.replace(
				subject,
				new String[] {
					"[$COMMENT_TEMPLATE$]", "[$CREATE_DATE$]",
					"[$DUE_DATE_TEMPLATE$]", "[$MODIFIED_DATE$]"
				},
				new String[] {
					commentTemplate,
					dateFormatDateTime.format(ticketEntry.getCreateDate()),
					dueDateTemplate,
					dateFormatDateTime.format(ticketEntry.getModifiedDate())
				});

			if (worker &&
				(action.equals(OSBMailActionKeys.COMMENTED) ||
				 action.equals(OSBMailActionKeys.GAME_PLAN_COMMENTED) ||
				 action.equals(OSBMailActionKeys.LOGGED_CALL))) {

				StringBundler sb = new StringBundler(5);

				sb.append(curSubject);
				sb.append(StringPool.SPACE);
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(
					StringUtil.toUpperCase(ticketComment.getVisibilityLabel()));
				sb.append(StringPool.CLOSE_PARENTHESIS);

				curSubject = sb.toString();
			}

			String body = bodyMap.get(user.getLocale());

			if (Validator.isNull(body)) {
				body = bodyMap.get(defaultLocale);
			}

			String curBody = StringUtil.replace(
				body,
				new String[] {
					"[$ACTION_INFORMATION_TEMPLATE$]", "[$COMMENT_TEMPLATE$]",
					"[$CREATE_DATE$]", "[$DUE_DATE_TEMPLATE$]",
					"[$MODIFIED_DATE$]"
				},
				new String[] {
					actionInformationTemplate, commentTemplate,
					dateFormatDateTime.format(ticketEntry.getCreateDate()),
					dueDateTemplate,
					dateFormatDateTime.format(ticketEntry.getModifiedDate())
				});

			AccountCustomer accountCustomer =
				accountCustomerLocalService.fetchAccountCustomer(
					sendUserId, ticketEntry.getAccountEntryId());

			if ((accountCustomer != null) &&
				(accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER)) {

				StringBundler sb = new StringBundler(6);

				sb.append("Click <a href=\"");
				sb.append(
					PortalUtil.getLayoutFullURL(
						user.getGroupId(), OSBPortletKeys.OSB_SUPPORT));
				sb.append("?p_p_id=");
				sb.append(OSBPortletKeys.OSB_SUPPORT);
				sb.append("&tabs1=preferences\">here</a> to change email ");
				sb.append("preferences.");

				curBody = StringUtil.replace(
					curBody, "[$EMAIL_PREFERENCES_LINK$]", sb.toString());
			}
			else {
				curBody = StringUtil.replace(
					curBody, "[$EMAIL_PREFERENCES_LINK$]", StringPool.BLANK);
			}

			MailMessage mailMessage = new MailMessage(
				from, to, curSubject, curBody, true);

			mailMessage.setMessageId(
				PortalUtil.getMailId(
					user.getCompanyMx(), "ticket_entry",
					ticketEntry.getTicketEntryId(), PortalUUIDUtil.generate()));

			mailService.sendEmail(mailMessage);
		}
	}

	protected void updateAuditEntry(
			long userId, String userName, long auditSetId,
			TicketEntry oldTicketEntry, TicketEntry ticketEntry,
			ServiceContext serviceContext)
		throws PortalException {

		long classPK = ticketEntry.getTicketEntryId();
		Date createDate = ticketEntry.getModifiedDate();

		if (auditSetId == 0) {
			auditSetId = auditEntryLocalService.getNextAuditSetId(
				TicketEntry.class.getName(), classPK);
		}

		int auditAction = AuditEntryConstants.ACTION_UPDATE;

		long classNameId = PortalUtil.getClassNameId(
			TicketEntry.class.getName());

		if (oldTicketEntry.getUserId() != ticketEntry.getUserId()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_REPORTED_BY,
				VisibilityConstants.PUBLIC, oldTicketEntry.getUserName(),
				String.valueOf(oldTicketEntry.getUserId()),
				ticketEntry.getUserName(),
				String.valueOf(ticketEntry.getUserId()));
		}

		long oldOfferingEntryId = oldTicketEntry.getOfferingEntryId();

		if (oldOfferingEntryId != ticketEntry.getOfferingEntryId()) {
			OfferingEntry originalOfferingEntry =
				offeringEntryPersistence.findByPrimaryKey(oldOfferingEntryId);

			SupportResponse orginalSupportResponse =
				originalOfferingEntry.getSupportResponse();

			OfferingEntry offeringEntry = ticketEntry.getOfferingEntry();

			SupportResponse supportResponse =
				offeringEntry.getSupportResponse();

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_SUPPORT, VisibilityConstants.PUBLIC,
				orginalSupportResponse.getName(),
				String.valueOf(oldOfferingEntryId), supportResponse.getName(),
				String.valueOf(ticketEntry.getOfferingEntryId()));
		}

		long oldSupportRegionId = oldTicketEntry.getSupportRegionId();

		if (oldSupportRegionId != ticketEntry.getSupportRegionId()) {
			SupportRegion oldSupportRegion = oldTicketEntry.getSupportRegion();

			String oldSupportRegionName = oldSupportRegion.getName();

			SupportRegion supportRegion = ticketEntry.getSupportRegion();

			String supportRegionName = supportRegion.getName();

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_SUPPORT_REGION,
				VisibilityConstants.WORKERS, oldSupportRegionName,
				String.valueOf(oldSupportRegionId), supportRegionName,
				String.valueOf(ticketEntry.getSupportRegionId()));
		}

		String oldSubject = oldTicketEntry.getSubject();

		if (!oldSubject.equals(ticketEntry.getSubject())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_SUBJECT, VisibilityConstants.PUBLIC,
				StringPool.BLANK, oldSubject, StringPool.BLANK,
				ticketEntry.getSubject());
		}

		String oldDescription = oldTicketEntry.getDescription();

		if (!oldDescription.equals(ticketEntry.getDescription())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_DESCRIPTION,
				VisibilityConstants.PUBLIC, StringPool.BLANK, oldDescription,
				StringPool.BLANK, ticketEntry.getDescription());
		}

		String oldReproductionSteps = oldTicketEntry.getReproductionSteps();

		if (!oldReproductionSteps.equals(ticketEntry.getReproductionSteps())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_REPRODUCTION_STEPS,
				VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
				oldReproductionSteps, StringPool.BLANK,
				ticketEntry.getReproductionSteps());
		}

		if (oldTicketEntry.getSeverity() != ticketEntry.getSeverity()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_SEVERITY, VisibilityConstants.PUBLIC,
				oldTicketEntry.getSeverityLabel(),
				String.valueOf(oldTicketEntry.getSeverity()),
				ticketEntry.getSeverityLabel(),
				String.valueOf(ticketEntry.getSeverity()));

			String severityReason = (String)serviceContext.getAttribute(
				"severityReason");

			if (Validator.isNotNull(severityReason)) {
				auditEntryLocalService.addAuditEntry(
					userId, userName, createDate, classNameId, classPK,
					auditSetId, classNameId, classPK, auditAction,
					AuditEntryConstants.FIELD_SEVERITY_REASON,
					VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
					StringPool.BLANK, severityReason, severityReason, true,
					false);

				String severityReasonComments =
					(String)serviceContext.getAttribute(
						"severityReasonComments");

				if (Validator.isNotNull(severityReasonComments)) {
					auditEntryLocalService.addAuditEntry(
						userId, userName, createDate, classNameId, classPK,
						auditSetId, classNameId, classPK, auditAction,
						AuditEntryConstants.FIELD_SEVERITY_REASON_COMMENTS,
						VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
						StringPool.BLANK, StringPool.BLANK,
						severityReasonComments, true, false);
				}
			}
		}

		if (oldTicketEntry.getStatus() != ticketEntry.getStatus()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_STATUS, VisibilityConstants.PUBLIC,
				oldTicketEntry.getStatusLabel(),
				String.valueOf(oldTicketEntry.getStatus()),
				ticketEntry.getStatusLabel(),
				String.valueOf(ticketEntry.getStatus()));
		}

		if (oldTicketEntry.getWeight() != ticketEntry.getWeight()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_WEIGHT, VisibilityConstants.ADMIN,
				oldTicketEntry.getWeightLabel(),
				String.valueOf(oldTicketEntry.getWeight()),
				ticketEntry.getWeightLabel(),
				String.valueOf(ticketEntry.getWeight()));
		}

		if (oldTicketEntry.getEscalationLevel() !=
				ticketEntry.getEscalationLevel()) {

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_ESCALATION_LEVEL,
				VisibilityConstants.PUBLIC,
				oldTicketEntry.getEscalationLevelLabel(),
				String.valueOf(oldTicketEntry.getEscalationLevel()),
				ticketEntry.getEscalationLevelLabel(),
				String.valueOf(ticketEntry.getEscalationLevel()));
		}

		if (oldTicketEntry.getComponent() != ticketEntry.getComponent()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_COMPONENT, VisibilityConstants.PUBLIC,
				oldTicketEntry.getComponentLabel(),
				String.valueOf(oldTicketEntry.getComponent()),
				ticketEntry.getComponentLabel(),
				String.valueOf(ticketEntry.getComponent()));
		}

		int oldSubcomponent = oldTicketEntry.getSubcomponent();
		int subcomponent = ticketEntry.getSubcomponent();

		String oldSubcomponentCustom = oldTicketEntry.getSubcomponentCustom();
		String subcomponentCustom = ticketEntry.getSubcomponentCustom();

		if ((oldSubcomponent != subcomponent) ||
			!oldSubcomponentCustom.equals(subcomponentCustom)) {

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_SUBCOMPONENT,
				VisibilityConstants.LIFERAY_INC, oldSubcomponentCustom,
				String.valueOf(oldSubcomponent), subcomponentCustom,
				String.valueOf(subcomponent));
		}

		if (oldTicketEntry.getResolution() != ticketEntry.getResolution()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_RESOLUTION,
				VisibilityConstants.PUBLIC, oldTicketEntry.getResolutionLabel(),
				String.valueOf(oldTicketEntry.getResolution()),
				ticketEntry.getResolutionLabel(),
				String.valueOf(ticketEntry.getResolution()));
		}

		Date oldDueDate = oldTicketEntry.getDueDate();
		Date dueDate = ticketEntry.getDueDate();

		if (DateUtil.compareTo(oldDueDate, dueDate) != 0) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_DUE_DATE, VisibilityConstants.WORKERS,
				String.valueOf(oldDueDate),
				String.valueOf(oldDueDate.getTime()), String.valueOf(dueDate),
				String.valueOf(dueDate.getTime()));
		}

		if (oldTicketEntry.getIgnoreDueDate() !=
				ticketEntry.getIgnoreDueDate()) {

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_IGNORE_DUE_DATE,
				VisibilityConstants.ADMIN, StringPool.BLANK,
				String.valueOf(oldTicketEntry.getIgnoreDueDate()),
				StringPool.BLANK,
				String.valueOf(ticketEntry.getIgnoreDueDate()));
		}
	}

	protected TicketEntry updatePendingTypes(
			long userId, TicketEntry ticketEntry, int[] pendingTypes)
		throws PortalException {

		if (pendingTypes == null) {
			return ticketEntry;
		}

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long classNameId = PortalUtil.getClassNameId(
			TicketEntry.class.getName());
		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			TicketEntry.class.getName(), ticketEntry.getTicketEntryId());
		long fieldClassNameId = PortalUtil.getClassNameId(
			TicketFlag.class.getName());

		Set<Integer> previousPendingTypes = new HashSet<>();

		List<TicketFlag> ticketFlags = ticketFlagPersistence.findByTEI_T_F(
			ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING,
			TicketFlagConstants.FLAG_TRUE);

		for (TicketFlag ticketFlag : ticketFlags) {
			previousPendingTypes.add(ticketFlag.getType());

			if (!ArrayUtil.contains(pendingTypes, ticketFlag.getType())) {
				int field = 0;

				if (ticketFlag.getType() ==
						TicketFlagConstants.TYPE_PENDING_CUSTOMER) {

					field = AuditEntryConstants.FIELD_PENDING_CUSTOMER;
				}
				else if (ticketFlag.getType() ==
							TicketFlagConstants.TYPE_PENDING_LIFERAY) {

					field = AuditEntryConstants.FIELD_PENDING_LIFERAY;
				}
				else if (ticketFlag.getType() ==
							TicketFlagConstants.TYPE_PENDING_PARTNER) {

					field = AuditEntryConstants.FIELD_PENDING_PARTNER;
				}

				ticketFlagPersistence.remove(ticketFlag.getTicketFlagId());

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					ticketEntry.getTicketEntryId(), auditSetId,
					fieldClassNameId, ticketFlag.getTicketFlagId(),
					AuditEntryConstants.ACTION_DELETE, field,
					VisibilityConstants.PUBLIC,
					TicketFlagConstants.getTypeLabel(ticketFlag.getType()),
					String.valueOf(ticketFlag.getType()), StringPool.BLANK,
					StringPool.BLANK);
			}
		}

		auditSetId = auditEntryLocalService.getNextAuditSetId(
			TicketEntry.class.getName(), ticketEntry.getTicketEntryId());

		for (int pendingType : pendingTypes) {
			if (!previousPendingTypes.contains(pendingType)) {
				int field = 0;

				if (pendingType == TicketFlagConstants.TYPE_PENDING_CUSTOMER) {
					field = AuditEntryConstants.FIELD_PENDING_CUSTOMER;
				}
				else if (pendingType ==
							TicketFlagConstants.TYPE_PENDING_LIFERAY) {

					field = AuditEntryConstants.FIELD_PENDING_LIFERAY;
				}
				else if (pendingType ==
							TicketFlagConstants.TYPE_PENDING_PARTNER) {

					field = AuditEntryConstants.FIELD_PENDING_PARTNER;
				}

				TicketFlag ticketFlag = ticketFlagLocalService.updateTicketFlag(
					userId, ticketEntry.getAccountEntryId(),
					ticketEntry.getTicketEntryId(), pendingType,
					TicketFlagConstants.FLAG_TRUE);

				auditEntryLocalService.addAuditEntry(
					userId, user.getFullName(), now, classNameId,
					ticketEntry.getTicketEntryId(), auditSetId,
					fieldClassNameId, ticketFlag.getTicketFlagId(),
					AuditEntryConstants.ACTION_ADD, field,
					VisibilityConstants.PUBLIC, StringPool.BLANK,
					StringPool.BLANK,
					TicketFlagConstants.getTypeLabel(pendingType),
					String.valueOf(pendingType));
			}
		}

		ticketEntry.setModifiedDate(now);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		ticketEntryPersistence.update(ticketEntry, serviceContext);

		reindexTicketEntry(ticketEntry);

		return ticketEntry;
	}

	protected void updateSupportWorkerAssignedWork(
			long ticketEntryId, int oldStatus, int status, int oldWeight,
			int weight)
		throws PortalException {

		if ((oldStatus == status) && (oldWeight == weight)) {
			return;
		}

		if (ArrayUtil.contains(
				TicketEntryConstants.STATUSES_INACTIVE, oldStatus) &&
			!ArrayUtil.contains(
				TicketEntryConstants.STATUSES_INACTIVE, status)) {

			supportWorkerLocalService.increaseTicketEntryAssignedWork(
				ticketEntryId, TicketEntryImpl.getWork(weight));
		}
		else if (!ArrayUtil.contains(
					TicketEntryConstants.STATUSES_INACTIVE, oldStatus) &&
				 ArrayUtil.contains(
					 TicketEntryConstants.STATUSES_INACTIVE, status)) {

			supportWorkerLocalService.decreaseTicketEntryAssignedWork(
				ticketEntryId, TicketEntryImpl.getWork(oldWeight));
		}
		else if (oldWeight != weight) {
			double work = TicketEntryImpl.getWork(weight);
			double originalWork = TicketEntryImpl.getWork(oldWeight);

			supportWorkerLocalService.increaseTicketEntryAssignedWork(
				ticketEntryId, work - originalWork);
		}
	}

	protected void updateTicketAttachmentDeleteDates(
			long ticketEntryId, Date deleteDate)
		throws PortalException {

		int[] visibilities = {
			VisibilityConstants.LIFERAY_INC, VisibilityConstants.PUBLIC,
			VisibilityConstants.WORKERS
		};

		List<TicketAttachment> ticketAttachments =
			ticketAttachmentLocalService.getTicketAttachments(
				ticketEntryId, TicketAttachmentConstants.TYPES_LARGE,
				visibilities, WorkflowConstants.STATUS_APPROVED);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			ticketAttachmentLocalService.updateDeleteDate(
				OSBConstants.USER_DEFAULT_USER_ID,
				ticketAttachment.getTicketAttachmentId(), deleteDate);
		}
	}

	protected void validate(
			String validateType, OfferingEntry offeringEntry, String languageId,
			String subject, String description, int systemStatus, int severity,
			int status, int weight, int component, int subcomponent,
			String subcomponentCustom, int resolution,
			Map<Long, String> ticketInformationFieldsMap,
			List<TicketAttachment> ticketAttachments)
		throws PortalException {

		if (offeringEntry.getStatus() != OfferingEntryConstants.STATUS_ACTIVE) {
			throw new OfferingEntrySupportExpiredException();
		}

		if (!offeringEntry.isSupportTickets()) {
			throw new MaximumTicketEntryException();
		}

		ProductEntry productEntry = offeringEntry.getProductEntry();

		if (((systemStatus < 1) || (systemStatus > 8)) &&
			!productEntry.isSocialOffice()) {

			throw new TicketEntrySystemStatusException();
		}

		validate(
			validateType, productEntry, languageId, subject, description,
			severity, status, weight, component, subcomponent, resolution,
			ticketInformationFieldsMap, ticketAttachments);
	}

	protected void validate(
			String validateType, ProductEntry productEntry, String languageId,
			String subject, String description, int severity, int status,
			int weight, int component, int subcomponent, int resolution,
			Map<Long, String> ticketInformationFieldsMap,
			List<TicketAttachment> ticketAttachments)
		throws PortalException {

		if (validateType.startsWith("update") ||
			Validator.isNotNull(languageId)) {

			if (!ArrayUtil.contains(
					AccountEntryConstants.LANGUAGES, languageId)) {

				throw new TicketEntryLanguageIdException();
			}
		}

		if (Validator.isNull(subject)) {
			throw new TicketEntrySubjectException();
		}

		if (Validator.isNull(description)) {
			throw new TicketEntryDescriptionException();
		}

		if ((severity < 1) || (severity > 3)) {
			throw new TicketEntrySeverityException();
		}

		if (!ArrayUtil.contains(TicketEntryConstants.WEIGHTS, weight)) {
			throw new TicketEntryWeightException();
		}

		try {
			listTypeLocalService.validate(
				status, TicketEntryConstants.LIST_TYPE_STATUS);
		}
		catch (NoSuchListTypeException nslte) {
			throw new TicketEntryStatusException();
		}

		if (status == TicketEntryConstants.STATUS_CLOSED) {
			try {
				listTypeLocalService.validate(
					resolution, TicketEntryConstants.LIST_TYPE_RESOLUTION);
			}
			catch (NoSuchListTypeException nslte) {
				throw new TicketEntryResolutionException();
			}
		}
		else {
			try {
				listTypeLocalService.validate(
					resolution, TicketEntryConstants.LIST_TYPE_RESOLUTION);

				throw new TicketEntryResolutionException();
			}
			catch (NoSuchListTypeException nslte) {
			}
		}

		try {
			listTypeLocalService.validate(
				component, TicketEntryConstants.LIST_TYPE_COMPONENT);
		}
		catch (NoSuchListTypeException nslte) {
			if (!productEntry.isSocialOffice()) {
				throw new TicketEntryComponentException();
			}
		}

		if ((component ==
				TicketEntryConstants.COMPONENT_LIFERAY_CONNECTED_SERVICES) &&
			(subcomponent == 0)) {

			throw new TicketEntrySubcomponentException();
		}

		if (validateType.startsWith("update") && (subcomponent > 0)) {
			int[] subcomponents = TicketEntryConstants.getSubcomponents(
				component);

			if (!ArrayUtil.contains(subcomponents, subcomponent) &&
				(subcomponent != TicketEntryConstants.SUBCOMPONENT_OTHER)) {

				throw new TicketEntrySubcomponentException();
			}
		}

		validateFields(
			validateType, productEntry, component, ticketInformationFieldsMap,
			ticketAttachments);
	}

	protected void validateClusterFields(
			String validateType, int serverCommunicationType)
		throws PortalException {

		if ((validateType.startsWith("add") || (serverCommunicationType > 0)) &&
			!ArrayUtil.contains(
				TicketEntryConstants.CLUSTER_SERVER_COMMUNICATION_TYPES,
				serverCommunicationType)) {

			throw new TicketInformationException("server-communication-type");
		}
	}

	protected void validateFields(
			String validateType, ProductEntry productEntry, int component,
			Map<Long, String> ticketInformationFieldsMap,
			List<TicketAttachment> ticketAttachments)
		throws PortalException {

		List<Integer> ticketAttachmentTypes = new ArrayList<>();

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			ticketAttachmentTypes.add(ticketAttachment.getType());
		}

		if (component == TicketEntryConstants.COMPONENT_LICENSE) {
			int licensePurpose = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_LICENSE_PURPOSE));
			int licenseType = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_LICENSE_TYPE));

			validateLicenseFields(
				validateType, licensePurpose, licenseType,
				ticketAttachmentTypes);
		}
		else {
			int envOS = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_OS));
			String envOSCustom = ticketInformationFieldsMap.get(
				TicketInformationConstants.FIELD_ENV_OS_CUSTOM);
			int envDB = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_DB));
			int envAS = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_AS));
			int envJVM = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_JVM));
			int envLFR = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_LFR));
			int envBrowser = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_BROWSER));
			String envBrowserCustom = ticketInformationFieldsMap.get(
				TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM);
			int envCS = GetterUtil.getInteger(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_CS));
			String envSearch = GetterUtil.getString(
				ticketInformationFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_SEARCH));

			if ((component == TicketEntryConstants.COMPONENT_UPGRADE) &&
				productEntry.isDigitalEnterprise()) {

				envLFR = GetterUtil.getInteger(
					ticketInformationFieldsMap.get(
						TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR));
			}

			validateGenericFields(
				validateType, productEntry, envOS, envOSCustom, envDB, envAS,
				envLFR, envJVM, envBrowser, envBrowserCustom, envCS, envSearch);

			if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
				int dataFolderUploadMethod = GetterUtil.getInteger(
					ticketInformationFieldsMap.get(
						TicketInformationConstants.
							FIELD_DATA_FOLDER_UPLOAD_METHOD));
				int databaseUploadMethod = GetterUtil.getInteger(
					ticketInformationFieldsMap.get(
						TicketInformationConstants.
							FIELD_DATABASE_UPLOAD_METHOD));
				int docLibPersistence = GetterUtil.getInteger(
					ticketInformationFieldsMap.get(
						TicketInformationConstants.FIELD_DOC_LIB_PERSISTENCE));
				String stepsToUpgrade = GetterUtil.getString(
					ticketInformationFieldsMap.get(
						TicketInformationConstants.FIELD_STEPS_TO_UPGRADE));
				int toEnvLFR = GetterUtil.getInteger(
					ticketInformationFieldsMap.get(
						TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR));

				validateUpgradeFields(
					validateType, productEntry, toEnvLFR, docLibPersistence,
					stepsToUpgrade, databaseUploadMethod,
					dataFolderUploadMethod, ticketAttachmentTypes);
			}
			else {
				if (component == TicketEntryConstants.COMPONENT_CLUSTERING) {
					int serverCommunicationType = GetterUtil.getInteger(
						ticketInformationFieldsMap.get(
							TicketInformationConstants.
								FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE));

					validateClusterFields(
						validateType, serverCommunicationType);
				}

				validateGenericUploads(validateType, ticketAttachmentTypes);
			}
		}
	}

	protected void validateGenericFields(
			String validateType, ProductEntry productEntry, int envOS,
			String envOSCustom, int envDB, int envAS, int envLFR, int envJVM,
			int envBrowser, String envBrowserCustom, int envCS,
			String envSearch)
		throws PortalException {

		if (envOS <= 0) {
			throw new TicketInformationException("operating-system");
		}

		try {
			listTypeLocalService.validate(
				envOS, TicketEntryConstants.LIST_TYPE_ENV_OS);
		}
		catch (NoSuchListTypeException nslte) {
			throw new TicketInformationException("operating-system");
		}

		if (envOS == TicketEntryConstants.ENV_OS_OTHER) {
			if (Validator.isNull(envOSCustom)) {
				throw new TicketInformationException("operating-system");
			}
			else {
				int maxLength = TicketInformationConstants.getMaxLength(
					TicketInformationConstants.FIELD_ENV_OS_CUSTOM);

				if (envOSCustom.length() > maxLength) {
					throw new TicketInformationException("operating-system");
				}
			}
		}

		if (envDB <= 0) {
			throw new TicketInformationException("database");
		}

		try {
			listTypeLocalService.validate(
				envDB, TicketEntryConstants.LIST_TYPE_ENV_DB);
		}
		catch (NoSuchListTypeException nslte) {
			throw new TicketInformationException("database");
		}

		if (envAS <= 0) {
			throw new TicketInformationException("application-server");
		}

		try {
			listTypeLocalService.validate(
				envAS, TicketEntryConstants.LIST_TYPE_ENV_AS);
		}
		catch (NoSuchListTypeException nslte) {
			throw new TicketInformationException("application-server");
		}

		if (envLFR <= 0) {
			throw new TicketInformationException("liferay-version");
		}

		if (productEntry.isSocialOffice()) {
			try {
				listTypeLocalService.validate(
					envLFR,
					ProductEntryConstants.LIST_TYPE_SOCIAL_OFFICE_ALL_VERSIONS);
			}
			catch (NoSuchListTypeException nslte) {
				throw new TicketInformationException("social-office-version");
			}
		}
		else {
			try {
				ListType listType = listTypeLocalService.getListType(envLFR);

				String type = listType.getType();

				if (!type.equals(
						ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS) &&
					!type.equals(
						ProductEntryConstants.
							LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS)) {

					throw new TicketInformationException("liferay-version");
				}
			}
			catch (NoSuchListTypeException nslte) {
				throw new TicketInformationException("liferay-version");
			}
		}

		if (envJVM <= 0) {
			throw new TicketInformationException("jvm");
		}

		try {
			listTypeLocalService.validate(
				envJVM, TicketEntryConstants.LIST_TYPE_ENV_JVM);
		}
		catch (NoSuchListTypeException nslte) {
			throw new TicketInformationException("jvm");
		}

		if (envBrowser > 0) {
			try {
				listTypeLocalService.validate(
					envBrowser, TicketEntryConstants.LIST_TYPE_ENV_BROWSER);
			}
			catch (NoSuchListTypeException nslte) {
				throw new TicketInformationException("browser");
			}

			if (envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER) {
				if (Validator.isNull(envBrowserCustom)) {
					throw new TicketInformationException("browser-custom");
				}
				else {
					int maxLength = TicketInformationConstants.getMaxLength(
						TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM);

					if (envBrowserCustom.length() > maxLength) {
						throw new TicketInformationException("browser-custom");
					}
				}
			}
		}

		if (envCS > 0) {
			try {
				listTypeLocalService.validate(
					envCS, TicketEntryConstants.LIST_TYPE_ENV_CS);
			}
			catch (NoSuchListTypeException nslte) {
				throw new TicketInformationException("cloud-services");
			}

			if (!ProductEntryConstants.isPortalVersion6_2(envLFR) &&
				!ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {

				throw new TicketInformationException("portal-version");
			}
		}

		if (Validator.isNotNull(envSearch)) {
			try {
				int[] envSearchesList = StringUtil.split(
					envSearch, StringPool.NEW_LINE, 0);

				for (int envSearches : envSearchesList) {
					listTypeLocalService.validate(
						envSearches, TicketEntryConstants.LIST_TYPE_ENV_SEARCH);
				}
			}
			catch (NoSuchListTypeException nslte) {
				throw new TicketInformationException("search");
			}

			if (!ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {
				throw new TicketInformationException("portal-version");
			}
		}
	}

	protected void validateGenericUploads(
			String validateType, List<Integer> ticketAttachmentTypes)
		throws PortalException {

		if (validateType.startsWith("add") &&
			!ticketAttachmentTypes.contains(
				TicketAttachmentConstants.TYPE_PATCH_LEVEL)) {

			throw new RequiredFieldException("patch-level", "patch-level");
		}

		if (validateType.startsWith("add") &&
			!ticketAttachmentTypes.contains(
				TicketAttachmentConstants.TYPE_PORTAL_EXT)) {

			throw new RequiredFieldException("portal-ext", "portal-ext");
		}
	}

	protected void validateLicenseFields(
			String validateType, int licensePurpose, int licenseType,
			List<Integer> ticketAttachmentTypes)
		throws PortalException {

		if ((validateType.startsWith("add") || (licensePurpose > 0)) &&
			!ArrayUtil.contains(
				TicketEntryConstants.getLicensePurposes(), licensePurpose)) {

			throw new TicketInformationException("purpose");
		}

		if ((validateType.startsWith("add") || (licenseType > 0)) &&
			!ArrayUtil.contains(
				TicketEntryConstants.getLicenseTypes(), licenseType)) {

			throw new TicketInformationException("type-of-key");
		}
	}

	protected void validatePendingTypes(
			long userId, long accountEntryId, int[] pendingTypes)
		throws PortalException {

		if (ArrayUtil.isEmpty(pendingTypes) &&
			!accountCustomerLocalService.hasAccountCustomer(
				userId, accountEntryId)) {

			throw new TicketCommentPendingTypeException();
		}
	}

	protected void validateUpgradeFields(
			String validateType, ProductEntry productEntry, int toEnvLFR,
			int docLibPersistence, String stepsToUpgrade,
			int databaseUploadMethod, int dataFolderUploadMethod,
			List<Integer> ticketAttachmentTypes)
		throws PortalException {

		if (toEnvLFR <= 0) {
			throw new TicketInformationException(
				"to-upgrading-to-liferay-version");
		}

		try {
			ListType listType = listTypeLocalService.getListType(toEnvLFR);

			String type = listType.getType();

			if (!type.equals(
					ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS) &&
				!type.equals(
					ProductEntryConstants.
						LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS)) {

				throw new TicketInformationException(
					"to-upgrading-to-liferay-version");
			}
		}
		catch (NoSuchListTypeException nslte) {
			throw new TicketInformationException(
				"to-upgrading-to-liferay-version");
		}

		if (validateType.startsWith("add") || (docLibPersistence > 0)) {
			if (!ArrayUtil.contains(
					TicketEntryConstants.DOC_LIB_PERSISTENCES,
					docLibPersistence)) {

				throw new TicketInformationException(
					"how-is-the-document-library-persisting-documents");
			}
		}

		if (validateType.startsWith("add") &&
			Validator.isNull(stepsToUpgrade)) {

			throw new TicketInformationException(
				"please-provide-the-steps-used-to-perform-the-upgrade");
		}

		if (validateType.equals("add") &&
			!ArrayUtil.contains(
				TicketEntryConstants.UPLOAD_METHODS, databaseUploadMethod)) {

			throw new TicketInformationException(
				"invalid-database-upload-method");
		}

		if (validateType.equals("add") &&
			!ArrayUtil.contains(
				TicketEntryConstants.UPLOAD_METHODS, dataFolderUploadMethod)) {

			throw new TicketInformationException(
				"invalid-data-folder-upload-method");
		}

		if (validateType.startsWith("add") &&
			!ticketAttachmentTypes.contains(
				TicketAttachmentConstants.TYPE_PATCH_LEVEL)) {

			throw new RequiredFieldException(
				"from-patch-level", "from-patch-level");
		}

		if (validateType.startsWith("add") &&
			!ticketAttachmentTypes.contains(
				TicketAttachmentConstants.TYPE_PORTAL_EXT)) {

			throw new RequiredFieldException(
				"from-portal-ext", "from-portal-ext");
		}

		if (validateType.startsWith("add") &&
			!productEntry.isDigitalEnterprise() &&
			!ticketAttachmentTypes.contains(
				TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL)) {

			throw new RequiredFieldException(
				"to-patch-level", "to-patch-level");
		}

		if (validateType.startsWith("add") &&
			!productEntry.isDigitalEnterprise() &&
			!ticketAttachmentTypes.contains(
				TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT)) {

			throw new RequiredFieldException("to-portal-ext", "to-portal-ext");
		}
	}

	@BeanReference(type = MailService.class)
	protected MailService mailService;

}
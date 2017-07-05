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

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.exception.DuplicateTicketFeedbackException;
import com.liferay.osb.exception.TicketFeedbackAnswerException;
import com.liferay.osb.exception.TicketFeedbackRatingException;
import com.liferay.osb.exception.TicketFeedbackSatisfiedException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.TicketWorkerConstants;
import com.liferay.osb.service.base.TicketFeedbackLocalServiceBaseImpl;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.SubscriptionSender;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Mate Thurzo
 * @author Amos Fong
 */
public class TicketFeedbackLocalServiceImpl
	extends TicketFeedbackLocalServiceBaseImpl {

	public TicketFeedback addTicketFeedback(
			long userId, long ticketEntryId, int subject, int satisfied)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketEntryId);
		Date now = new Date();

		validate(userId, ticketEntryId, subject, satisfied, false);

		long ticketFeedbackId = counterLocalService.increment();

		TicketFeedback ticketFeedback = ticketFeedbackPersistence.create(
			ticketFeedbackId);

		ticketFeedback.setUserId(userId);
		ticketFeedback.setUserName(user.getFullName());
		ticketFeedback.setCreateDate(now);
		ticketFeedback.setModifiedDate(now);
		ticketFeedback.setTicketEntryId(ticketEntryId);
		ticketFeedback.setSubject(subject);
		ticketFeedback.setSatisfied(satisfied);
		ticketFeedback.setAccountEntryId(ticketEntry.getAccountEntryId());
		ticketFeedback.setStatus(TicketFeedbackConstants.STATUS_ANSWERED);

		ticketFeedbackPersistence.update(ticketFeedback, false);

		if (!accountCustomerLocalService.hasAccountCustomer(
				userId, ticketEntry.getAccountEntryId()) ||
			organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			try {
				if (subject == TicketFeedbackConstants.SUBJECT_LIFERAY) {
					sendCustomerEmail(ticketFeedback, 0);
				}
				else if (subject == TicketFeedbackConstants.SUBJECT_PARTNER) {
					sendLiferayWorkerEmail(ticketFeedback, 0);
				}
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

		ticketEntryLocalService.reindexTicketEntry(ticketEntryId);

		return ticketFeedback;
	}

	public TicketFeedback fetchFirstOpenTicketFeedback(
			long userId, long ticketEntryId, int subject)
		throws SystemException {

		return ticketFeedbackPersistence.fetchByU_TEI_S_NotS_First(
			userId, ticketEntryId, subject,
			TicketFeedbackConstants.STATUS_CLOSED, null);
	}

	public List<TicketFeedback> getTicketFeedbacks(
			long ticketEntryId, int subject)
		throws SystemException {

		return ticketFeedbackPersistence.findByTEI_S(ticketEntryId, subject);
	}

	public List<TicketFeedback> getTicketFeedbacks(
			long ticketEntryId, int subject, int status)
		throws SystemException {

		return ticketFeedbackPersistence.findByTEI_S_S(
			ticketEntryId, subject, status);
	}

	public List<TicketFeedback> search(
			String name, int createdGTDay, int createdGTMonth,
			int createdGTYear, int createdLTDay, int createdLTMonth,
			int createdLTYear, int modifiedGTDay, int modifiedGTMonth,
			int modifiedGTYear, int modifiedLTDay, int modifiedLTMonth,
			int modifiedLTYear, Integer satisfied, String comments,
			Integer status, Integer[] ratings1, Integer[] ratings2,
			Integer[] ratings3, Integer[] ratings4,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		Date createDateGT = PortalUtil.getDate(
			createdGTMonth, createdGTDay, createdGTYear);
		Date createDateLT = PortalUtil.getDate(
			createdLTMonth, createdLTDay, createdLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedGTMonth, modifiedGTDay, modifiedGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedLTMonth, modifiedLTDay, modifiedLTYear);

		return ticketFeedbackFinder.findByAE_CD_MD_S_S_C_S_R_R_R_R(
			name, createDateGT, createDateLT, modifiedDateGT, modifiedDateLT,
			TicketFeedbackConstants.SUBJECT_LIFERAY, satisfied, comments,
			status, ratings1, ratings2, ratings3, ratings4, params, andSearch,
			start, end, obc);
	}

	public List<TicketFeedback> search(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		return ticketFeedbackFinder.findByKeywords(
			keywords, params, start, end, obc);
	}

	public int searchCount(
			String name, int createdGTDay, int createdGTMonth,
			int createdGTYear, int createdLTDay, int createdLTMonth,
			int createdLTYear, int modifiedGTDay, int modifiedGTMonth,
			int modifiedGTYear, int modifiedLTDay, int modifiedLTMonth,
			int modifiedLTYear, Integer satisfied, String comments,
			Integer status, Integer[] ratings1, Integer[] ratings2,
			Integer[] ratings3, Integer[] ratings4,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws SystemException {

		Date createDateGT = PortalUtil.getDate(
			createdGTMonth, createdGTDay, createdGTYear);
		Date createDateLT = PortalUtil.getDate(
			createdLTMonth, createdLTDay, createdLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedGTMonth, modifiedGTDay, modifiedGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedLTMonth, modifiedLTDay, modifiedLTYear);

		return ticketFeedbackFinder.countByAE_CD_MD_S_S_C_S_R_R_R_R(
			name, createDateGT, createDateLT, modifiedDateGT, modifiedDateLT,
			TicketFeedbackConstants.SUBJECT_LIFERAY, satisfied, comments,
			status, ratings1, ratings2, ratings3, ratings4, params, andSearch);
	}

	public int searchCount(
			String keywords, LinkedHashMap<String, Object> params)
		throws SystemException {

		return ticketFeedbackFinder.countByKeywords(keywords, params);
	}

	public void sendCustomerNotifications() throws Exception {
		Date now = new Date();

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, -7);

		Date createDateGT = CalendarUtil.getGTDate(cal);
		Date createDateLT = CalendarUtil.getLTDate(cal);

		List<TicketFeedback> ticketFeedbacks =
			ticketFeedbackFinder.findByAE_CD_MD_S_S_C_S_R_R_R_R(
				(String)null, createDateGT, createDateLT, null, null,
				TicketFeedbackConstants.SUBJECT_LIFERAY, null, null,
				TicketFeedbackConstants.STATUS_UNANSWERED, null, null, null,
				null, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			Date modifiedDate = ticketFeedback.getModifiedDate();

			if ((now.getTime() - modifiedDate.getTime()) > Time.DAY) {
				sendCustomerEmail(ticketFeedback, 1);
			}
		}

		cal.add(Calendar.DATE, -14);

		createDateGT = CalendarUtil.getGTDate(cal);
		createDateLT = CalendarUtil.getLTDate(cal);

		ticketFeedbacks = ticketFeedbackFinder.findByAE_CD_MD_S_S_C_S_R_R_R_R(
			(String)null, createDateGT, createDateLT, null, null,
			TicketFeedbackConstants.SUBJECT_LIFERAY, null, null,
			TicketFeedbackConstants.STATUS_UNANSWERED, null, null, null, null,
			null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			Date modifiedDate = ticketFeedback.getModifiedDate();

			if ((now.getTime() - modifiedDate.getTime()) > Time.DAY) {
				sendCustomerEmail(ticketFeedback, 3);
			}
		}
	}

	public void sendLiferayWorkerNotifications() throws Exception {
		Date now = new Date();

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, -7);

		Date createDateGT = CalendarUtil.getGTDate(cal);
		Date createDateLT = CalendarUtil.getLTDate(cal);

		List<TicketFeedback> ticketFeedbacks =
			ticketFeedbackFinder.findByAE_CD_MD_S_S_C_S_R_R_R_R(
				(String)null, createDateGT, createDateLT, null, null,
				TicketFeedbackConstants.SUBJECT_PARTNER, null, null,
				TicketFeedbackConstants.STATUS_UNANSWERED, null, null, null,
				null, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			Date modifiedDate = ticketFeedback.getModifiedDate();

			if ((now.getTime() - modifiedDate.getTime()) > Time.DAY) {
				sendLiferayWorkerEmail(ticketFeedback, 1);
			}
		}

		cal.add(Calendar.DATE, -14);

		createDateGT = CalendarUtil.getGTDate(cal);
		createDateLT = CalendarUtil.getLTDate(cal);

		ticketFeedbacks = ticketFeedbackFinder.findByAE_CD_MD_S_S_C_S_R_R_R_R(
			(String)null, createDateGT, createDateLT, null, null,
			TicketFeedbackConstants.SUBJECT_PARTNER, null, null,
			TicketFeedbackConstants.STATUS_UNANSWERED, null, null, null, null,
			null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (TicketFeedback ticketFeedback : ticketFeedbacks) {
			Date modifiedDate = ticketFeedback.getModifiedDate();

			if ((now.getTime() - modifiedDate.getTime()) > Time.DAY) {
				sendLiferayWorkerEmail(ticketFeedback, 3);
			}
		}
	}

	public void sendSupportTeamNotifications() throws Exception {
		PortletPreferences preferences = SupportUtil.getPortletPreferences();

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, -7);

		Date modifiedDateGT = CalendarUtil.getGTDate(cal);

		List<SupportTeam> supportTeams =
			supportTeamLocalService.getSupportTeams(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (SupportTeam supportTeam : supportTeams) {
			List<SupportTeam> childSupportTeams =
				supportTeamLocalService.getChildSupportTeams(
					supportTeam.getSupportTeamId(), true);

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put(
				"supportTeam", new Long[] {supportTeam.getSupportTeamId()});

			List<TicketFeedback> ticketFeedbacks =
				ticketFeedbackFinder.findByAE_CD_MD_S_S_C_S_R_R_R_R(
					(String)null, null, null, modifiedDateGT, null,
					TicketFeedbackConstants.SUBJECT_LIFERAY, null, null,
					TicketFeedbackConstants.STATUS_ANSWERED, null, null, null,
					null, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null);

			if (ticketFeedbacks.isEmpty()) {
				continue;
			}

			StringBundler sb = new StringBundler(ticketFeedbacks.size() * 10);

			for (TicketFeedback ticketFeedback : ticketFeedbacks) {
				TicketEntry ticketEntry = ticketFeedback.getTicketEntry();

				String layoutFullURL = PortalUtil.getLayoutFullURL(
					OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

				String ticketEntryURL =
					layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR +
						"support/ticket/" + ticketEntry.getDisplayId();

				sb.append(ticketEntry.getDisplayId());
				sb.append("<br /> - <a href=\"");
				sb.append(ticketEntryURL);
				sb.append("\">");
				sb.append(ticketEntryURL);
				sb.append("</a><br /> - Satisfied: ");
				sb.append(ticketFeedback.getSatisfiedLabel());
				sb.append("<br /> - Comments: <pre>");
				sb.append(ticketFeedback.getComments());
				sb.append("</pre><br /><br />");
			}

			Map<Locale, String> subjectMap =
				SupportUtil.getEmailTeamFeedbackSubjectMap(preferences);

			Map<Locale, String> bodyMap =
				SupportUtil.getEmailTeamFeedbackBodyMap(preferences);

			for (Map.Entry<Locale, String> bodyEntry : bodyMap.entrySet()) {
				String body = StringUtil.replace(
					bodyEntry.getValue(),
					new String[] {
						"[$FEEDBACK_ENTRIES$]"
					},
					new String[] {
						sb.toString()
					});

				bodyEntry.setValue(body);
			}

			String fromName = PrefsPropsUtil.getString(
				OSBConstants.COMPANY_ID, PropsKeys.ADMIN_EMAIL_FROM_NAME);

			fromName += OSBConstants.EMAIL_SUPPORT_FROM_NAME_SUFFIX;

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
			subscriptionSender.setFrom(
				PortletPropsValues.SUPPORT_EMAIL_ADDRESS_FROM, fromName);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setLocalizedBodyMap(bodyMap);
			subscriptionSender.setLocalizedSubjectMap(subjectMap);
			subscriptionSender.setMailId(
				"ticket_feedback", supportTeam.getSupportTeamId());

			List<SupportWorker> supportWorkers =
				supportWorkerLocalService.getTeamSupportWorkers(
					supportTeam.getSupportTeamId());

			for (SupportWorker supportWorker : supportWorkers) {
				if (supportWorker.getNotifications() ==
						SupportWorkerConstants.NOTIFICATIONS_NONE) {

					continue;
				}

				User user = null;

				try {
					user = userLocalService.getUserById(
						supportWorker.getUserId());
				}
				catch (NoSuchUserException nsue) {
					continue;
				}

				if (!user.isActive()) {
					continue;
				}

				subscriptionSender.addRuntimeSubscribers(
					user.getEmailAddress(), user.getFullName());
			}

			for (SupportTeam childSupportTeam : childSupportTeams) {
				List<SupportWorker> childSupportWorkers =
					supportWorkerLocalService.getTeamSupportWorkers(
						childSupportTeam.getSupportTeamId());

				for (SupportWorker childSupportWorker : childSupportWorkers) {
					if (childSupportWorker.getNotifications() ==
							SupportWorkerConstants.NOTIFICATIONS_NONE) {

						continue;
					}

					User user = null;

					try {
						user = userLocalService.getUserById(
							childSupportWorker.getUserId());
					}
					catch (NoSuchUserException nsue) {
						continue;
					}

					if (!user.isActive()) {
						continue;
					}

					subscriptionSender.addRuntimeSubscribers(
						user.getEmailAddress(), user.getFullName());
				}
			}

			subscriptionSender.flushNotificationsAsync();
		}
	}

	public TicketFeedback updateTicketFeedback(
			long userId, long ticketFeedbackId, int satisfied, int answer1,
			int answer2, int answer3, int rating1, int rating2, int rating3,
			int rating4, String comments)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		TicketFeedback ticketFeedback =
			ticketFeedbackPersistence.findByPrimaryKey(ticketFeedbackId);

		validate(
			userId, ticketFeedback.getTicketEntryId(),
			ticketFeedback.getStatus(), satisfied, true);

		if (ticketFeedback.getSubject() ==
				TicketFeedbackConstants.SUBJECT_PARTNER) {

			validatePartner(
				answer1, answer2, answer3, rating1, rating2, rating3, rating4);
		}

		ticketFeedback.setUserId(userId);
		ticketFeedback.setUserName(user.getFullName());
		ticketFeedback.setModifiedDate(now);
		ticketFeedback.setSatisfied(satisfied);
		ticketFeedback.setAnswer1(answer1);
		ticketFeedback.setAnswer2(answer2);
		ticketFeedback.setAnswer3(answer3);
		ticketFeedback.setRating1(rating1);
		ticketFeedback.setRating2(rating2);
		ticketFeedback.setRating3(rating3);
		ticketFeedback.setRating4(rating4);
		ticketFeedback.setComments(comments);
		ticketFeedback.setStatus(TicketFeedbackConstants.STATUS_ANSWERED);

		ticketFeedbackPersistence.update(ticketFeedback, false);

		if (ticketFeedback.getSubject() ==
				TicketFeedbackConstants.SUBJECT_LIFERAY) {

			try {
				sendTicketWorkerEmail(ticketFeedback);
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

		return ticketFeedback;
	}

	protected void sendCustomerEmail(
			TicketFeedback ticketFeedback, int notificationPeriod)
		throws Exception {

		if (ticketFeedback.isClosed()) {
			return;
		}

		ticketFeedback.setModifiedDate(new Date());

		ticketFeedbackPersistence.update(ticketFeedback, false);

		PortletPreferences preferences = SupportUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			SupportUtil.getEmailCustomerFeedbackSubjectMap(preferences);
		Map<Locale, String> bodyMap =
			SupportUtil.getEmailCustomerFeedbackBodyMap(preferences);

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

		TicketEntry ticketEntry = ticketFeedback.getTicketEntry();

		String ticketEntryURL =
			layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR +
				"support/ticket/" + ticketEntry.getDisplayId();

		String fromName = null;

		fromName = PrefsPropsUtil.getString(
			OSBConstants.COMPANY_ID, PropsKeys.ADMIN_EMAIL_FROM_NAME);

		fromName += OSBConstants.EMAIL_SUPPORT_FROM_NAME_SUFFIX;

		InternetAddress from = new InternetAddress(
			PortletPropsValues.SUPPORT_EMAIL_ADDRESS_FROM, fromName);

		List<AccountCustomer> accountCustomers =
			accountCustomerLocalService.getAccountCustomers(
				ticketEntry.getAccountEntryId());

		for (AccountCustomer accountCustomer : accountCustomers) {
			if ((accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_SALES) ||
				(accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER)) {

				continue;
			}

			if (!ticketEntry.hasParticipant(accountCustomer.getUserId())) {
				continue;
			}

			User user = null;

			try {
				user = userLocalService.getUserById(
					accountCustomer.getUserId());
			}
			catch (NoSuchUserException nsue) {
				continue;
			}

			if (!user.isActive() ||
				organizationLocalService.hasUserOrganization(
					user.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				continue;
			}

			InternetAddress to = new InternetAddress(
				user.getEmailAddress(), user.getFullName());

			Locale defaultLocale = LocaleUtil.getDefault();

			String subject = subjectMap.get(user.getLocale());

			if (Validator.isNull(subject)) {
				subject = subjectMap.get(defaultLocale);
			}

			subject = StringUtil.replace(
				subject,
				new String[] {
					"[$TICKET_DISPLAY_ID$]"
				},
				new String[] {
					ticketEntry.getDisplayId()
				});

			String body = bodyMap.get(user.getLocale());

			if (Validator.isNull(body)) {
				body = bodyMap.get(defaultLocale);
			}

			String closeTime = StringPool.BLANK;

			if (notificationPeriod > 0) {
				closeTime = StringPool.SPACE;

				if (notificationPeriod > 1) {
					closeTime += LanguageUtil.format(
						user.getLocale(), "x-weeks-ago", notificationPeriod);
				}
				else {
					closeTime += LanguageUtil.format(
						user.getLocale(), "x-ago", "1-week", true);
				}
			}

			body = StringUtil.replace(
				body,
				new String[] {
					"[$CLOSE_TIME$]", "[$CUSTOMER_NAME$]",
					"[$TICKET_DISPLAY_ID$]", "[$TICKET_ENTRY_URL$]"
				},
				new String[] {
					closeTime, user.getFullName(), ticketEntry.getDisplayId(),
					ticketEntryURL
				});

			MailMessage mailMessage = new MailMessage(
				from, to, subject, body, true);

			mailMessage.setMessageId(
				PortalUtil.getMailId(
					user.getCompanyMx(), "customer_feedback_reminder",
					ticketFeedback.getTicketFeedbackId(),
					PortalUUIDUtil.generate()));

			mailService.sendEmail(mailMessage);
		}
	}

	protected void sendLiferayWorkerEmail(
			TicketFeedback ticketFeedback, int notificationPeriod)
		throws Exception {

		if (ticketFeedback.isClosed()) {
			return;
		}

		ticketFeedback.setModifiedDate(new Date());

		ticketFeedbackPersistence.update(ticketFeedback, false);

		PortletPreferences preferences = SupportUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			SupportUtil.getEmailPartnerFeedbackReminderSubjectMap(preferences);
		Map<Locale, String> bodyMap =
			SupportUtil.getEmailPartnerFeedbackReminderBodyMap(preferences);

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

		String ticketFeedbackURL =
			layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR +
				"support/partner_feedback/" +
					ticketFeedback.getTicketFeedbackId();

		TicketEntry ticketEntry = ticketFeedback.getTicketEntry();

		String ticketEntryURL =
			layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR +
				"support/ticket/" + ticketEntry.getDisplayId();

		String escalationTime = StringPool.BLANK;

		if (notificationPeriod > 0) {
			if (notificationPeriod > 1) {
				escalationTime = notificationPeriod + " weeks ago";
			}
			else {
				escalationTime = notificationPeriod + " week ago";
			}
		}
		else {
			escalationTime = "recently";
		}

		for (Map.Entry<Locale, String> subjectEntry : subjectMap.entrySet()) {
			String subject = StringUtil.replace(
				subjectEntry.getValue(),
				new String[] {
					"[$TICKET_DISPLAY_ID$]"
				},
				new String[] {
					ticketEntry.getDisplayId()
				});

			subjectEntry.setValue(subject);
		}

		for (Map.Entry<Locale, String> bodyEntry : bodyMap.entrySet()) {
			String body = bodyEntry.getValue();

			body = StringUtil.replace(
				body,
				new String[] {
					"[$ESCALATION_TIME$]", "[$TICKET_FEEDBACK_URL$]",
					"[$TICKET_DISPLAY_ID$]", "[$TICKET_ENTRY_URL$]",
				},
				new String[] {
					escalationTime, ticketFeedbackURL,
					ticketEntry.getDisplayId(), ticketEntryURL
				});

			bodyEntry.setValue(body);
		}

		String fromName = null;

		fromName = PrefsPropsUtil.getString(
			OSBConstants.COMPANY_ID, PropsKeys.ADMIN_EMAIL_FROM_NAME);

		fromName += OSBConstants.EMAIL_SUPPORT_FROM_NAME_SUFFIX;

		InternetAddress from = new InternetAddress(
			PortletPropsValues.SUPPORT_EMAIL_ADDRESS_FROM, fromName);

		Locale defaultLocale = LocaleUtil.getDefault();

		List<TicketWorker> ticketWorkers =
			ticketWorkerLocalService.getTicketWorkers(
				ticketEntry.getTicketEntryId());

		for (TicketWorker ticketWorker : ticketWorkers) {
			if ((ticketWorker.getRole() !=
					TicketWorkerConstants.ROLE_DEVELOPER) &&
				(ticketWorker.getRole() !=
					TicketWorkerConstants.ROLE_ESCALATED_DEVELOPER)) {

				continue;
			}

			if (!organizationLocalService.hasUserOrganization(
					ticketWorker.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				continue;
			}

			User user = null;

			try {
				user = userLocalService.getUserById(ticketWorker.getUserId());
			}
			catch (NoSuchUserException nsue) {
				continue;
			}

			if (!user.isActive()) {
				continue;
			}

			InternetAddress to = new InternetAddress(
				user.getEmailAddress(), user.getFullName());

			String subject = subjectMap.get(user.getLocale());

			if (Validator.isNull(subject)) {
				subject = subjectMap.get(defaultLocale);
			}

			String body = bodyMap.get(user.getLocale());

			if (Validator.isNull(body)) {
				body = bodyMap.get(defaultLocale);
			}

			body = StringUtil.replace(
				body, "[$EMPLOYEE_NAME$]", user.getFullName());

			MailMessage mailMessage = new MailMessage(
				from, to, subject, body, true);

			mailMessage.setMessageId(
				PortalUtil.getMailId(
					user.getCompanyMx(), "liferay_worker_feedback_reminder",
					ticketFeedback.getTicketFeedbackId(),
					PortalUUIDUtil.generate()));

			mailService.sendEmail(mailMessage);
		}
	}

	protected void sendTicketWorkerEmail(TicketFeedback ticketFeedback)
		throws Exception {

		PortletPreferences preferences = SupportUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			SupportUtil.getEmailWorkerFeedbackSubjectMap(preferences);
		Map<Locale, String> bodyMap = SupportUtil.getEmailWorkerFeedbackBodyMap(
			preferences);

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

		TicketEntry ticketEntry = ticketFeedback.getTicketEntry();

		String ticketEntryURL =
			layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR +
				"support/ticket/" + ticketEntry.getDisplayId();

		for (Map.Entry<Locale, String> subjectEntry : subjectMap.entrySet()) {
			String subject = StringUtil.replace(
				subjectEntry.getValue(),
				new String[] {
					"[$TICKET_DISPLAY_ID$]"
				},
				new String[] {
					ticketEntry.getDisplayId()
				});

			subjectEntry.setValue(subject);
		}

		for (Map.Entry<Locale, String> bodyEntry : bodyMap.entrySet()) {
			Locale locale = bodyEntry.getKey();

			String body = StringUtil.replace(
				bodyEntry.getValue(),
				new String[] {
					"[$FEEDBACK_COMMENTS$]", "[$FEEDBACK_SATISFIED$]",
					"[$TICKET_DISPLAY_ID$]", "[$TICKET_ENTRY_URL$]"
				},
				new String[] {
					ticketFeedback.getComments(),
					LanguageUtil.get(
						locale, ticketFeedback.getSatisfiedLabel()),
					ticketEntry.getDisplayId(), ticketEntryURL
				});

			bodyEntry.setValue(body);
		}

		String fromName = PrefsPropsUtil.getString(
			OSBConstants.COMPANY_ID, PropsKeys.ADMIN_EMAIL_FROM_NAME);

		fromName += OSBConstants.EMAIL_SUPPORT_FROM_NAME_SUFFIX;

		InternetAddress from = new InternetAddress(
			PortletPropsValues.SUPPORT_EMAIL_ADDRESS_FROM, fromName);

		Locale defaultLocale = LocaleUtil.getDefault();

		List<TicketWorker> ticketWorkers =
			ticketWorkerLocalService.getTicketWorkers(
				ticketEntry.getTicketEntryId());

		for (TicketWorker ticketWorker : ticketWorkers) {
			User user = null;

			try {
				user = userLocalService.getUserById(ticketWorker.getUserId());
			}
			catch (NoSuchUserException nsue) {
				continue;
			}

			if (!user.isActive()) {
				continue;
			}

			List<PartnerWorker> partnerWorkers =
				partnerWorkerLocalService.getUserPartnerWorkers(
					user.getUserId());

			if (!partnerWorkers.isEmpty()) {
				continue;
			}

			InternetAddress to = new InternetAddress(
				user.getEmailAddress(), user.getFullName());

			String subject = subjectMap.get(user.getLocale());

			if (Validator.isNull(subject)) {
				subject = subjectMap.get(defaultLocale);
			}

			String body = bodyMap.get(user.getLocale());

			if (Validator.isNull(body)) {
				body = bodyMap.get(defaultLocale);
			}

			MailMessage mailMessage = new MailMessage(
				from, to, subject, body, true);

			mailMessage.setMessageId(
				PortalUtil.getMailId(
					user.getCompanyMx(), "ticket_feedback_update",
					ticketFeedback.getTicketFeedbackId(),
					PortalUUIDUtil.generate()));

			mailService.sendEmail(mailMessage);
		}
	}

	protected void validate(
			long userId, long ticketEntryId, int subject, int satisfied,
			boolean update)
		throws PortalException, SystemException {

		if ((satisfied < TicketFeedbackConstants.SATISFIED_YES) ||
			(satisfied > TicketFeedbackConstants.SATISFIED_NOT_APPLICABLE)) {

			throw new TicketFeedbackSatisfiedException();
		}

		if (!update) {
			TicketFeedback openTicketFeedback = fetchFirstOpenTicketFeedback(
				userId, ticketEntryId, subject);

			if (openTicketFeedback != null) {
				throw new DuplicateTicketFeedbackException();
			}
		}
	}

	protected void validatePartner(
			int answer1, int answer2, int answer3, int rating1, int rating2,
			int rating3, int rating4)
		throws PortalException {

		if ((answer1 < 1) || (answer1 > 3)) {
			throw new TicketFeedbackAnswerException();
		}

		if ((answer2 < 1) || (answer2 > 3)) {
			throw new TicketFeedbackAnswerException();
		}

		if ((answer3 < 1) || (answer3 > 3)) {
			throw new TicketFeedbackAnswerException();
		}

		if ((rating1 < 1) || (rating1 > 5)) {
			throw new TicketFeedbackRatingException();
		}

		if ((rating2 < 1) || (rating2 > 5)) {
			throw new TicketFeedbackRatingException();
		}

		if ((rating3 < 1) || (rating3 > 5)) {
			throw new TicketFeedbackRatingException();
		}

		if (rating4 > 0) {
			throw new TicketFeedbackRatingException();
		}
	}

}
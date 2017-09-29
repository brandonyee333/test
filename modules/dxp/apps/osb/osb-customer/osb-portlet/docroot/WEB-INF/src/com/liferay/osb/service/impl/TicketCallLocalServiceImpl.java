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

import com.liferay.osb.exception.TicketCallCustomerNameException;
import com.liferay.osb.exception.TicketCallDateException;
import com.liferay.osb.exception.TicketCallLengthException;
import com.liferay.osb.exception.TicketCallTypeException;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketCall;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.base.TicketCallLocalServiceBaseImpl;
import com.liferay.osb.util.OSBMailActionKeys;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.Date;

/**
 * @author Sharon Li
 */
public class TicketCallLocalServiceImpl extends TicketCallLocalServiceBaseImpl {

	public TicketCall addTicketCall(
			long userId, long ticketEntryId, int type, int callDateMonth,
			int callDateDay, int callDateYear, int callDateHour,
			int callDateMinute, long callLength, String customerName,
			String customerContact, String confirmation, String instructions)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Date callDate = PortalUtil.getDate(
			callDateMonth, callDateDay, callDateYear, callDateHour,
			callDateMinute, user.getTimeZone(),
			(Class<? extends PortalException>)null);

		Date now = new Date();

		// Ticket call

		validate(type, callDate, callLength, customerName);

		long ticketCallId = counterLocalService.increment();

		TicketCall ticketCall = ticketCallPersistence.create(ticketCallId);

		ticketCall.setUserId(user.getUserId());
		ticketCall.setUserName(user.getFullName());
		ticketCall.setCreateDate(now);
		ticketCall.setTicketEntryId(ticketEntryId);
		ticketCall.setType(type);
		ticketCall.setCallDate(callDate);
		ticketCall.setCallLength(callLength);
		ticketCall.setCustomerName(customerName);
		ticketCall.setCustomerContact(customerContact);
		ticketCall.setConfirmation(confirmation);
		ticketCall.setInstructions(instructions);

		ticketCallPersistence.update(ticketCall);

		// Ticket comments

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAttribute("auditEnabled", Boolean.FALSE);

		String commentBodyLiferay = getTicketCommentBody(
			ticketCall, VisibilityConstants.LIFERAY_INC);

		TicketComment ticketCommentLiferay =
			ticketCommentLocalService.addTicketComment(
				userId, ticketEntryId, commentBodyLiferay,
				TicketCommentConstants.TYPE_TICKET_CALL,
				VisibilityConstants.LIFERAY_INC,
				WorkflowConstants.STATUS_APPROVED, 0, new int[0],
				serviceContext);

		ticketEntryLocalService.sendEmail(
			userId, ticketEntryId, ticketCommentLiferay,
			OSBMailActionKeys.LOGGED_CALL);

		String commentBodyPublic = getTicketCommentBody(
			ticketCall, VisibilityConstants.PUBLIC);

		TicketComment ticketCommentPublic =
			ticketCommentLocalService.addTicketComment(
				userId, ticketEntryId, commentBodyPublic,
				TicketCommentConstants.TYPE_TICKET_CALL,
				VisibilityConstants.PUBLIC, WorkflowConstants.STATUS_APPROVED,
				0, new int[0], serviceContext);

		ticketEntryLocalService.sendEmail(
			userId, ticketEntryId, ticketCommentPublic,
			OSBMailActionKeys.LOGGED_CALL);

		// Audit entry

		auditEntryLocalService.addAuditEntry(
			user.getUserId(), user.getFullName(), now,
			classNameLocalService.getClassNameId(TicketEntry.class.getName()),
			ticketEntryId, 0,
			classNameLocalService.getClassNameId(TicketCall.class.getName()),
			ticketCall.getTicketCallId(), AuditEntryConstants.ACTION_ADD,
			AuditEntryConstants.FIELD_TICKET_CALL,
			VisibilityConstants.LIFERAY_INC, StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, commentBodyLiferay);

		return ticketCall;
	}

	protected String getTicketCommentBody(
		TicketCall ticketCall, int visibility) {

		String commentBody = null;

		if (visibility == VisibilityConstants.LIFERAY_INC) {
			commentBody = ContentUtil.get(
				TicketCallLocalServiceImpl.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/comment_ticket_call_liferay_body.tmpl");
		}
		else if (visibility == VisibilityConstants.PUBLIC) {
			commentBody = ContentUtil.get(
				TicketCallLocalServiceImpl.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/comment_ticket_call_public_body.tmpl");
		}

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

		commentBody = StringUtil.replace(
			commentBody,
			new String[] {
				"[$CALL_DATE$]", "[$CALL_LENGTH$]", "[$CONFIRMATION$]",
				"[$CUSTOMER_NAME$]", "[$CUSTOMER_CONTACT$]", "[$INSTRUCTIONS$]",
				"[$TYPE$]"
			},
			new String[] {
				dateFormat.format(ticketCall.getCallDate()),
				ticketCall.getCallLengthLabel(), ticketCall.getConfirmation(),
				ticketCall.getCustomerName(), ticketCall.getCustomerContact(),
				ticketCall.getInstructions(), ticketCall.getTypeLabel()
			});

		return commentBody;
	}

	protected void validate(
			int type, Date callDate, long callLength, String customerName)
		throws PortalException {

		if ((type <= 0) || (type > 2)) {
			throw new TicketCallTypeException();
		}

		if (callDate.after(new Date())) {
			throw new TicketCallDateException();
		}

		if (callLength <= 0) {
			throw new TicketCallLengthException();
		}

		if (Validator.isNull(customerName)) {
			throw new TicketCallCustomerNameException();
		}
	}

}
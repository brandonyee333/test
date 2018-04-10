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

package com.liferay.akismet.internal.service;

import com.liferay.akismet.client.AkismetClient;
import com.liferay.akismet.client.constants.AkismetConstants;
import com.liferay.akismet.client.util.AkismetServiceConfigurationUtil;
import com.liferay.akismet.model.AkismetEntry;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.InputStream;
import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jamie Sammons
 */
@Component(immediate = true, property = {}, service = ServiceWrapper.class)
public class AkismetMBMessageLocalServiceWrapper
	extends MBMessageLocalServiceWrapper {

	public AkismetMBMessageLocalServiceWrapper() {
		super(null);
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			long threadId, long parentMessageId, String subject, String body,
			String format,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException {

		boolean enabled = isMessageBoardsEnabled(
			userId, groupId, serviceContext);

		if (enabled) {
			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		MBMessage message = super.addMessage(
			userId, userName, groupId, categoryId, threadId, parentMessageId,
			subject, body, format, inputStreamOVPs, anonymous, priority,
			allowPingbacks, serviceContext);

		AkismetEntry akismetData = updateAkismetData(message, serviceContext);

		if (!enabled) {
			return message;
		}

		String content = subject + "\n\n" + body;

		int status = WorkflowConstants.STATUS_APPROVED;

		if (_akismetClient.isSpam(userId, content, akismetData)) {
			status = WorkflowConstants.STATUS_DENIED;
		}

		return super.updateStatus(
			userId, message.getMessageId(), status, serviceContext,
			new HashMap<String, Serializable>());
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			String subject, String body, String format,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException {

		boolean enabled = isMessageBoardsEnabled(
			userId, groupId, serviceContext);

		if (enabled) {
			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		MBMessage message = super.addMessage(
			userId, userName, groupId, categoryId, subject, body, format,
			inputStreamOVPs, anonymous, priority, allowPingbacks,
			serviceContext);

		Akismet akismetData = updateAkismetData(message, serviceContext);

		if (!enabled) {
			return message;
		}

		String content = subject + "\n\n" + body;

		int status = WorkflowConstants.STATUS_APPROVED;

		if (_akismetClient.isSpam(userId, content, akismetData)) {
			status = WorkflowConstants.STATUS_DENIED;
		}

		return super.updateStatus(
			userId, message.getMessageId(), status, serviceContext,
			new HashMap<String, Serializable>());
	}

	@Override
	public MBMessage updateMessage(
			long userId, long messageId, String subject, String body,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			List<String> existingFiles, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException {

		MBMessage message = super.getMBMessage(messageId);

		boolean enabled = isMessageBoardsEnabled(
			userId, message.getGroupId(), serviceContext);

		if (enabled) {
			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		message = super.updateMessage(
			userId, messageId, subject, body, inputStreamOVPs, existingFiles,
			priority, allowPingbacks, serviceContext);

		Akismet akismetData = updateAkismetData(message, serviceContext);

		if (!enabled) {
			return message;
		}

		String content = subject + "\n\n" + body;

		int status = WorkflowConstants.STATUS_APPROVED;

		if (_akismetClient.isSpam(userId, content, akismetData)) {
			status = WorkflowConstants.STATUS_DENIED;
		}

		return super.updateStatus(
			userId, message.getMessageId(), status, serviceContext,
			new HashMap<String, Serializable>());
	}

	protected String getPermalink(
		MBMessage message, ServiceContext serviceContext) {

		String contentURL = (String)serviceContext.getAttribute("contentURL");

		if (Validator.isNotNull(contentURL)) {
			return contentURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(serviceContext.getPortalURL());
		sb.append(serviceContext.getPathMain());
		sb.append("/message_boards/find_entry?messageId=");
		sb.append(message.getMessageId());

		return sb.toString();
	}

	protected boolean isMessageBoardsEnabled(
			long userId, long groupId, ServiceContext serviceContext)
		throws PortalException {

		if (serviceContext.getWorkflowAction() !=
				WorkflowConstants.ACTION_PUBLISH) {

			return false;
		}

		if (!_akismetClient.hasRequiredInfo(
				serviceContext.getRemoteAddr(), serviceContext.getHeaders())) {

			return false;
		}

		if (!AkismetServiceConfigurationUtil.isMessageBoardsEnabled()) {
			return false;
		}

		int checkThreshold =
			AkismetServiceConfigurationUtil.getAkismetCheckThreshold();

		if (checkThreshold > 0) {
			int count = super.getGroupMessagesCount(
				groupId, userId, WorkflowConstants.STATUS_APPROVED);

			if (count > checkThreshold) {
				return false;
			}
		}

		return true;
	}

	protected Akismet updateAkismetData(
		MBMessage message, ServiceContext serviceContext) {

		if (!_akismetClient.hasRequiredInfo(serviceContext)) {
			return null;
		}

		String permalink = getPermalink(message, serviceContext);

		Map<String, String> headers = serviceContext.getHeaders();

		String referrer = headers.get("referer");
		String userAgent = headers.get(
			StringUtil.toLowerCase(HttpHeaders.USER_AGENT));

		String userIP = serviceContext.getRemoteAddr();

		return _akismetLocalService.updateAkismetData(
			MBMessage.class.getName(), message.getMessageId(),
			AkismetConstants.TYPE_COMMENT, permalink, referrer, userAgent,
			userIP, StringPool.BLANK);
	}

	@Reference
	private AkismetClient _akismetClient;

	@Reference
	private AkismetLocalService _akismetLocalService;

}
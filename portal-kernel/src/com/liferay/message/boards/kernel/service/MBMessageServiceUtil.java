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

package com.liferay.message.boards.kernel.service;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

import java.util.List;

/**
 * Provides the remote service utility for MBMessage. This utility wraps
 * <code>com.liferay.portlet.messageboards.service.impl.MBMessageServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MBMessageService
 * @generated
 */
public class MBMessageServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.messageboards.service.impl.MBMessageServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static MBMessage addDiscussionMessage(
			long groupId, String className, long classPK, long threadId,
			long parentMessageId, String subject, String body,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addDiscussionMessage(
			groupId, className, classPK, threadId, parentMessageId, subject,
			body, serviceContext);
	}

	public static MBMessage addMessage(
			long groupId, long categoryId, String subject, String body,
			String format,
			List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addMessage(
			groupId, categoryId, subject, body, format, inputStreamOVPs,
			anonymous, priority, allowPingbacks, serviceContext);
	}

	public static MBMessage addMessage(
			long groupId, long categoryId, String subject, String body,
			String format, String fileName, java.io.File file,
			boolean anonymous, double priority, boolean allowPingbacks,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.io.FileNotFoundException, PortalException {

		return getService().addMessage(
			groupId, categoryId, subject, body, format, fileName, file,
			anonymous, priority, allowPingbacks, serviceContext);
	}

	public static MBMessage addMessage(
			long categoryId, String subject, String body,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addMessage(
			categoryId, subject, body, serviceContext);
	}

	public static MBMessage addMessage(
			long parentMessageId, String subject, String body, String format,
			List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addMessage(
			parentMessageId, subject, body, format, inputStreamOVPs, anonymous,
			priority, allowPingbacks, serviceContext);
	}

	public static void addMessageAttachment(
			long messageId, String fileName, java.io.File file, String mimeType)
		throws PortalException {

		getService().addMessageAttachment(messageId, fileName, file, mimeType);
	}

	public static void deleteDiscussionMessage(long messageId)
		throws PortalException {

		getService().deleteDiscussionMessage(messageId);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #deleteDiscussionMessage(long)}
	 */
	@Deprecated
	public static void deleteDiscussionMessage(
			long groupId, String className, long classPK,
			String permissionClassName, long permissionClassPK,
			long permissionOwnerId, long messageId)
		throws PortalException {

		getService().deleteDiscussionMessage(
			groupId, className, classPK, permissionClassName, permissionClassPK,
			permissionOwnerId, messageId);
	}

	public static void deleteMessage(long messageId) throws PortalException {
		getService().deleteMessage(messageId);
	}

	public static void deleteMessageAttachment(long messageId, String fileName)
		throws PortalException {

		getService().deleteMessageAttachment(messageId, fileName);
	}

	public static void deleteMessageAttachments(long messageId)
		throws PortalException {

		getService().deleteMessageAttachments(messageId);
	}

	public static void emptyMessageAttachments(long messageId)
		throws PortalException {

		getService().emptyMessageAttachments(messageId);
	}

	public static List<MBMessage> getCategoryMessages(
			long groupId, long categoryId, int status, int start, int end)
		throws PortalException {

		return getService().getCategoryMessages(
			groupId, categoryId, status, start, end);
	}

	public static int getCategoryMessagesCount(
		long groupId, long categoryId, int status) {

		return getService().getCategoryMessagesCount(
			groupId, categoryId, status);
	}

	public static String getCategoryMessagesRSS(
			long groupId, long categoryId, int status, int max, String type,
			double version, String displayStyle, String feedURL,
			String entryURL,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().getCategoryMessagesRSS(
			groupId, categoryId, status, max, type, version, displayStyle,
			feedURL, entryURL, themeDisplay);
	}

	public static String getCompanyMessagesRSS(
			long companyId, int status, int max, String type, double version,
			String displayStyle, String feedURL, String entryURL,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().getCompanyMessagesRSS(
			companyId, status, max, type, version, displayStyle, feedURL,
			entryURL, themeDisplay);
	}

	public static int getGroupMessagesCount(long groupId, int status) {
		return getService().getGroupMessagesCount(groupId, status);
	}

	public static String getGroupMessagesRSS(
			long groupId, int status, int max, String type, double version,
			String displayStyle, String feedURL, String entryURL,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().getGroupMessagesRSS(
			groupId, status, max, type, version, displayStyle, feedURL,
			entryURL, themeDisplay);
	}

	public static String getGroupMessagesRSS(
			long groupId, long userId, int status, int max, String type,
			double version, String displayStyle, String feedURL,
			String entryURL,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().getGroupMessagesRSS(
			groupId, userId, status, max, type, version, displayStyle, feedURL,
			entryURL, themeDisplay);
	}

	public static MBMessage getMessage(long messageId) throws PortalException {
		return getService().getMessage(messageId);
	}

	public static com.liferay.message.boards.kernel.model.MBMessageDisplay
			getMessageDisplay(long messageId, int status)
		throws PortalException {

		return getService().getMessageDisplay(messageId, status);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 #getMessageDisplay(long, int)}
	 */
	@Deprecated
	public static com.liferay.message.boards.kernel.model.MBMessageDisplay
			getMessageDisplay(
				long messageId, int status, String threadView,
				boolean includePrevAndNext)
		throws PortalException {

		return getService().getMessageDisplay(
			messageId, status, threadView, includePrevAndNext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static int getThreadAnswersCount(
		long groupId, long categoryId, long threadId) {

		return getService().getThreadAnswersCount(
			groupId, categoryId, threadId);
	}

	public static List<MBMessage> getThreadMessages(
		long groupId, long categoryId, long threadId, int status, int start,
		int end) {

		return getService().getThreadMessages(
			groupId, categoryId, threadId, status, start, end);
	}

	public static int getThreadMessagesCount(
		long groupId, long categoryId, long threadId, int status) {

		return getService().getThreadMessagesCount(
			groupId, categoryId, threadId, status);
	}

	public static String getThreadMessagesRSS(
			long threadId, int status, int max, String type, double version,
			String displayStyle, String feedURL, String entryURL,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws PortalException {

		return getService().getThreadMessagesRSS(
			threadId, status, max, type, version, displayStyle, feedURL,
			entryURL, themeDisplay);
	}

	public static void restoreMessageAttachmentFromTrash(
			long messageId, String fileName)
		throws PortalException {

		getService().restoreMessageAttachmentFromTrash(messageId, fileName);
	}

	public static void subscribeMessage(long messageId) throws PortalException {
		getService().subscribeMessage(messageId);
	}

	public static void unsubscribeMessage(long messageId)
		throws PortalException {

		getService().unsubscribeMessage(messageId);
	}

	public static void updateAnswer(
			long messageId, boolean answer, boolean cascade)
		throws PortalException {

		getService().updateAnswer(messageId, answer, cascade);
	}

	public static MBMessage updateDiscussionMessage(
			String className, long classPK, long messageId, String subject,
			String body,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateDiscussionMessage(
			className, classPK, messageId, subject, body, serviceContext);
	}

	public static MBMessage updateMessage(
			long messageId, String subject, String body,
			List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, InputStream>> inputStreamOVPs,
			List<String> existingFiles, double priority, boolean allowPingbacks,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateMessage(
			messageId, subject, body, inputStreamOVPs, existingFiles, priority,
			allowPingbacks, serviceContext);
	}

	public static MBMessageService getService() {
		return _service;
	}

	public static void setService(MBMessageService service) {
		_service = service;
	}

	private static volatile MBMessageService _service;

}
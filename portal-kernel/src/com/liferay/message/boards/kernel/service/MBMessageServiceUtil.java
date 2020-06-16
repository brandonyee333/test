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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

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
	public static com.liferay.message.boards.kernel.model.MBMessage
			addDiscussionMessage(
				long groupId, String className, long classPK, long threadId,
				long parentMessageId, String subject, String body,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addDiscussionMessage(
			groupId, className, classPK, threadId, parentMessageId, subject,
			body, serviceContext);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage addMessage(
			long groupId, long categoryId, String subject, String body,
			String format,
			java.util.List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addMessage(
			groupId, categoryId, subject, body, format, inputStreamOVPs,
			anonymous, priority, allowPingbacks, serviceContext);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage addMessage(
			long groupId, long categoryId, String subject, String body,
			String format, String fileName, java.io.File file,
			boolean anonymous, double priority, boolean allowPingbacks,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   java.io.FileNotFoundException {

		return getService().addMessage(
			groupId, categoryId, subject, body, format, fileName, file,
			anonymous, priority, allowPingbacks, serviceContext);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage addMessage(
			long categoryId, String subject, String body,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addMessage(
			categoryId, subject, body, serviceContext);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage addMessage(
			long parentMessageId, String subject, String body, String format,
			java.util.List
				<com.liferay.portal.kernel.util.ObjectValuePair
					<String, java.io.InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addMessage(
			parentMessageId, subject, body, format, inputStreamOVPs, anonymous,
			priority, allowPingbacks, serviceContext);
	}

	public static void addMessageAttachment(
			long messageId, String fileName, java.io.File file, String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().addMessageAttachment(messageId, fileName, file, mimeType);
	}

	public static void deleteDiscussionMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

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
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteDiscussionMessage(
			groupId, className, classPK, permissionClassName, permissionClassPK,
			permissionOwnerId, messageId);
	}

	public static void deleteMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteMessage(messageId);
	}

	public static void deleteMessageAttachment(long messageId, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteMessageAttachment(messageId, fileName);
	}

	public static void deleteMessageAttachments(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteMessageAttachments(messageId);
	}

	public static void emptyMessageAttachments(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().emptyMessageAttachments(messageId);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBMessage> getCategoryMessages(
				long groupId, long categoryId, int status, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

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
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCategoryMessagesRSS(
			groupId, categoryId, status, max, type, version, displayStyle,
			feedURL, entryURL, themeDisplay);
	}

	public static String getCompanyMessagesRSS(
			long companyId, int status, int max, String type, double version,
			String displayStyle, String feedURL, String entryURL,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {

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
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getGroupMessagesRSS(
			groupId, status, max, type, version, displayStyle, feedURL,
			entryURL, themeDisplay);
	}

	public static String getGroupMessagesRSS(
			long groupId, long userId, int status, int max, String type,
			double version, String displayStyle, String feedURL,
			String entryURL,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getGroupMessagesRSS(
			groupId, userId, status, max, type, version, displayStyle, feedURL,
			entryURL, themeDisplay);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage getMessage(
			long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMessage(messageId);
	}

	public static com.liferay.message.boards.kernel.model.MBMessageDisplay
			getMessageDisplay(long messageId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

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
		throws com.liferay.portal.kernel.exception.PortalException {

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

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBMessage> getThreadMessages(
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
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getThreadMessagesRSS(
			threadId, status, max, type, version, displayStyle, feedURL,
			entryURL, themeDisplay);
	}

	public static void restoreMessageAttachmentFromTrash(
			long messageId, String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().restoreMessageAttachmentFromTrash(messageId, fileName);
	}

	public static void subscribeMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().subscribeMessage(messageId);
	}

	public static void unsubscribeMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().unsubscribeMessage(messageId);
	}

	public static void updateAnswer(
			long messageId, boolean answer, boolean cascade)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateAnswer(messageId, answer, cascade);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage
			updateDiscussionMessage(
				String className, long classPK, long messageId, String subject,
				String body,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateDiscussionMessage(
			className, classPK, messageId, subject, body, serviceContext);
	}

	public static com.liferay.message.boards.kernel.model.MBMessage
			updateMessage(
				long messageId, String subject, String body,
				java.util.List
					<com.liferay.portal.kernel.util.ObjectValuePair
						<String, java.io.InputStream>> inputStreamOVPs,
				java.util.List<String> existingFiles, double priority,
				boolean allowPingbacks,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateMessage(
			messageId, subject, body, inputStreamOVPs, existingFiles, priority,
			allowPingbacks, serviceContext);
	}

	public static MBMessageService getService() {
		if (_service == null) {
			_service = (MBMessageService)PortalBeanLocatorUtil.locate(
				MBMessageService.class.getName());
		}

		return _service;
	}

	private static MBMessageService _service;

}
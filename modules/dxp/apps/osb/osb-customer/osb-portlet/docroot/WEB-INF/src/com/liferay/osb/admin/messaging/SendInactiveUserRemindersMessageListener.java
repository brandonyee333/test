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

package com.liferay.osb.admin.messaging;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Douglas Wong
 */
public class SendInactiveUserRemindersMessageListener
	extends BaseMessageListener {

	protected static String getBlogEntriesHTML(Locale locale)
		throws PortalException, SystemException {

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		long[] assetCategoryIds =
			{OSBConstants.ASSET_CATEGORY_HIGHLIGHTED_BLOG_STREAM_ID};

		assetEntryQuery.setAnyCategoryIds(assetCategoryIds);

		assetEntryQuery.setClassName(BlogsEntry.class.getName());
		assetEntryQuery.setEnablePermissions(true);
		assetEntryQuery.setEnd(
			PortletPropsValues.
				USER_INACTIVE_NOTIFICATION_BLOG_ENTRIES_DISPLAYED);
		assetEntryQuery.setOrderByCol1("publishDate");
		assetEntryQuery.setOrderByType1("DESC");
		assetEntryQuery.setStart(0);
		assetEntryQuery.setVisible(true);

		List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil.getEntries(
			assetEntryQuery);

		StringBundler sb = new StringBundler((12 * assetEntries.size()) + 2);

		sb.append("<ul style='list-style-type: none; padding: 0;'>");

		for (AssetEntry assetEntry : assetEntries) {
			sb.append("<li style=\"padding-bottom: 10px;\"><div ");
			sb.append("style=\"font-weight: bold;\"><a href=\"");
			sb.append(getBlogsEntryURL(assetEntry));
			sb.append("\" style=\"text-decoration: none;\">");
			sb.append(HtmlUtil.escape(assetEntry.getTitle()));
			sb.append("</a></div><div style=\"color: #888;\">");
			sb.append(LanguageUtil.get(locale, "by"));
			sb.append(StringPool.SPACE);
			sb.append(HtmlUtil.escape(assetEntry.getUserName()));
			sb.append("</div><div>");
			sb.append(
				StringUtil.shorten(
					HtmlUtil.stripHtml(assetEntry.getSummary()), 280));
			sb.append("</div></li>");
		}

		sb.append("</ul>");

		return sb.toString();
	}

	protected static String getBlogsEntryURL(AssetEntry assetEntry)
		throws PortalException, SystemException {

		BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(
			assetEntry.getClassPK());

		StringBundler sb = new StringBundler(4);

		sb.append(
			PortalUtil.getLayoutFullURL(
				blogsEntry.getGroupId(), PortletKeys.BLOGS));
		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("blogs/");
		sb.append(blogsEntry.getUrlTitle());

		return sb.toString();
	}

	protected static String getMBCategoryHTML(MBCategory mbCategory)
		throws PortalException, SystemException {

		List<MBCategory> ancestorCategories = mbCategory.getAncestors();

		Collections.reverse(ancestorCategories);

		StringBundler sb = new StringBundler(
			(ancestorCategories.size() * 2) + 1);

		for (MBCategory ancestorCategory : ancestorCategories) {
			sb.append(ancestorCategory.getName());
			sb.append(" > ");
		}

		sb.append(mbCategory.getName());

		return sb.toString();
	}

	protected static MBMessage getMBMessage(User user, long mbMessageId) {
		PermissionChecker permissionChecker = null;

		try {
			permissionChecker = PermissionThreadLocal.getPermissionChecker();

			PermissionChecker userPermissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(userPermissionChecker);

			return MBMessageServiceUtil.getMessage(mbMessageId);
		}
		catch (Exception e) {
			return null;
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	protected static String getMBMessageURL(MBMessage mbMessage)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(4);

		sb.append(
			PortalUtil.getLayoutFullURL(
				mbMessage.getGroupId(), PortletKeys.MESSAGE_BOARDS));
		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("message_boards/message/");
		sb.append(mbMessage.getMessageId());

		return sb.toString();
	}

	protected static String getMessageBoardThreadsHTML(User user)
		throws PortalException, SystemException {

		Locale locale = user.getLocale();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(12);

			sb.append("select mbMessage.messageId from MBThread ");
			sb.append("mbThread inner join MBMessage mbMessage on ");
			sb.append("mbThread.rootMessageId = mbMessage.messageId inner ");
			sb.append("join MBCategory mbCategory on mbThread.categoryId = ");
			sb.append("mbCategory.categoryId where mbThread.messageCount >= ");
			sb.append(
				PortletPropsValues.
					USER_INACTIVE_NOTIFICATION_MB_THREADS_MIN_MESSAGE_COUNT);
			sb.append(" and mbThread.lastPostDate > DATE_SUB(CURDATE(), ");
			sb.append("INTERVAL 1 MONTH) and mbThread.status = ");
			sb.append(WorkflowConstants.STATUS_APPROVED);
			sb.append(" and mbThread.groupId = ");
			sb.append(OSBConstants.GROUP_GUEST_ID);
			sb.append(" order by mbThread.lastPostDate DESC");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			sb = new StringBundler(
				(PortletPropsValues.
					USER_INACTIVE_NOTIFICATION_MB_THREADS_DISPLAYED * 12) + 2);

			sb.append("<ul style='list-style-type: none; padding: 0;'>");

			int count = 0;

			while (rs.next() &&
				   (count <
						PortletPropsValues.
							USER_INACTIVE_NOTIFICATION_MB_THREADS_DISPLAYED)) {

				long mbMessageId = rs.getLong("messageId");

				MBMessage mbMessage = getMBMessage(user, mbMessageId);

				MBCategory mbCategory = mbMessage.getCategory();

				sb.append("<li style=\"padding-bottom: 10px;\"><div ");
				sb.append("style=\"font-weight: bold;\"><a href=\"");
				sb.append(getMBMessageURL(mbMessage));
				sb.append("\" style=\"text-decoration: none;\">");
				sb.append(mbMessage.getSubject());
				sb.append("</a></div><div>");
				sb.append(getMBCategoryHTML(mbCategory));
				sb.append("</div><div style=\"color: #888;\">");
				sb.append(LanguageUtil.get(locale, "by"));
				sb.append(StringPool.SPACE);
				sb.append(mbMessage.getUserName());
				sb.append("</div></li>");

				count++;
			}

			sb.append("</ul>");

			return sb.toString();
		}
		catch (SQLException se) {
			throw new PortalException(se);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected static void sendMail(User user)
		throws PortalException, SystemException {

		String fromName = "Liferay Community Team";
		String fromAddress = "noreply@liferay.com";

		PortletPreferences preferences = AdminUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			AdminUtil.getEmailInactiveUserSubjectMap(preferences);
		Map<Locale, String> bodyMap = AdminUtil.getEmailInactiveUserBodyMap(
			preferences);

		String toName = user.getFullName();
		String toAddress = user.getEmailAddress();

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttribute(
			"[$BLOG_ENTRIES$]", getBlogEntriesHTML(user.getLocale()), false);
		subscriptionSender.setContextAttribute(
			"[$MB_THREADS$]", getMessageBoardThreadsHTML(user), false);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId("user", user.getUserId());
		subscriptionSender.setReplyToAddress("webmaster@liferay.com");
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(toAddress, toName);

		subscriptionSender.flushNotificationsAsync();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("select userId from User_ where DATE(lastLoginDate) = ");
			sb.append("DATE_SUB(CURDATE(), INTERVAL ");
			sb.append(PortletPropsValues.USER_INACTIVE_NOTIFICATION_PERIOD);
			sb.append(" DAY)");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				User user = UserLocalServiceUtil.getUser(userId);

				ExpandoBridge expandoBridge = user.getExpandoBridge();

				boolean sentInactiveEmail = GetterUtil.getBoolean(
					expandoBridge.getAttribute("osbSentInactiveEmail", false));

				if (sentInactiveEmail) {
					continue;
				}

				boolean agreedToContactEvents = GetterUtil.getBoolean(
					expandoBridge.getAttribute(
						"osbAgreedToContactEvents", false));
				boolean agreedToContactSales = GetterUtil.getBoolean(
					expandoBridge.getAttribute(
						"osbAgreedToContactSales", false));
				boolean agreedToContactTrainings = GetterUtil.getBoolean(
					expandoBridge.getAttribute(
						"osbAgreedToContactTrainings", false));

				if (!agreedToContactEvents || !agreedToContactSales ||
					!agreedToContactTrainings) {

					continue;
				}

				sendMail(user);

				expandoBridge.setAttribute("osbSentInactiveEmail", true, false);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}
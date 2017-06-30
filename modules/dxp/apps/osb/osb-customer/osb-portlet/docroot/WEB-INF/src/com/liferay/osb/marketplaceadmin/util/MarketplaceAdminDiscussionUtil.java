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

package com.liferay.osb.marketplaceadmin.util;

import com.liferay.osb.model.AppEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.MBThreadConstants;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;

import java.util.List;

/**
 * @author Ryan Park
 */
public class MarketplaceAdminDiscussionUtil {

	public static final String CLASS_NAME_POSTFIX = "#MARKETPLACE_ADMIN";

	public static MBMessage addMBMessage(
			long userId, String className, long classPK, String body,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBMessageDisplay mbMessageDisplay = getDiscussionMBMessageDisplay(
			className, classPK);

		MBThread mbThread = mbMessageDisplay.getThread();

		return MBMessageServiceUtil.addDiscussionMessage(
			OSBConstants.GROUP_GLOBAL_ID, getNamespacedClassName(className),
			classPK, className, classPK, userId, mbThread.getThreadId(),
			mbThread.getRootMessageId(), StringPool.BLANK, body,
			serviceContext);
	}

	public static Subscription addSubscription(
			long userId, String className, long classPK)
		throws PortalException, SystemException {

		return SubscriptionLocalServiceUtil.addSubscription(
			userId, OSBConstants.GROUP_GLOBAL_ID,
			getNamespacedClassName(className), classPK);
	}

	public static void addSystemDiscussionMessage(
			long userId, String userName, long appEntryId, int status,
			String statusMessage, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String className = getNamespacedClassName(AppEntry.class.getName());

		MBMessageDisplay mbMessageDisplay = getDiscussionMBMessageDisplay(
			AppEntry.class.getName(), appEntryId);

		MBThread mbThread = mbMessageDisplay.getThread();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("status", status);
		jsonObject.put("statusMessage", statusMessage);
		jsonObject.put("userId", userId);
		jsonObject.put("userName", userName);

		MBMessageLocalServiceUtil.addDiscussionMessage(
			OSBConstants.USER_DEFAULT_USER_ID, "System",
			OSBConstants.GROUP_GLOBAL_ID, className, appEntryId,
			mbThread.getThreadId(), mbThread.getRootMessageId(),
			StringPool.BLANK, jsonObject.toString(), serviceContext);
	}

	public static MBMessage deleteMBMessage(
			long userId, String className, long classPK, long mbMessageId)
		throws PortalException, SystemException {

		MBMessage mbMessage = MBMessageLocalServiceUtil.getMessage(mbMessageId);

		String body = "<i>This message has been removed</i>";

		return MBMessageServiceUtil.updateDiscussionMessage(
			getNamespacedClassName(className), classPK, className, classPK,
			userId, mbMessageId, mbMessage.getSubject(), body,
			new ServiceContext());
	}

	public static void deleteSubscription(
			long userId, String className, long classPK)
		throws PortalException, SystemException {

		SubscriptionLocalServiceUtil.deleteSubscription(
			userId, getNamespacedClassName(className), classPK);
	}

	public static MBMessageDisplay getDiscussionMBMessageDisplay(
			String className, long classPK)
		throws PortalException, SystemException {

		return MBMessageLocalServiceUtil.getDiscussionMessageDisplay(
			OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.GROUP_GLOBAL_ID,
			getNamespacedClassName(className), classPK,
			WorkflowConstants.STATUS_ANY, MBThreadConstants.THREAD_VIEW_FLAT);
	}

	public static List<MBMessage> getDiscussionMBMessages(
			String className, long classPK, int start, int end)
		throws PortalException, SystemException {

		MBMessageDisplay mbMessageDisplay = getDiscussionMBMessageDisplay(
			className, classPK);

		MBThread mbThread = mbMessageDisplay.getThread();

		DynamicQuery dynamicQuery = getMBMessageDynamicQuery(
			mbThread.getThreadId());

		dynamicQuery.addOrder(OrderFactoryUtil.desc("modifiedDate"));

		return MBMessageLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
	}

	public static int getDiscussionMBMessagesCount(
			String className, long classPK)
		throws PortalException, SystemException {

		MBMessageDisplay mbMessageDisplay = getDiscussionMBMessageDisplay(
			className, classPK);

		MBThread mbThread = mbMessageDisplay.getThread();

		DynamicQuery dynamicQuery = getMBMessageDynamicQuery(
			mbThread.getThreadId());

		return (int)MBMessageLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	public static String getNamespacedClassName(String className) {
		return className.concat(CLASS_NAME_POSTFIX);
	}

	public static boolean isSubscribed(
			long userId, String className, long classPK)
		throws SystemException {

		return SubscriptionLocalServiceUtil.isSubscribed(
			OSBConstants.COMPANY_ID, userId, getNamespacedClassName(className),
			classPK);
	}

	protected static DynamicQuery getMBMessageDynamicQuery(long mbThreadId) {
		DynamicQuery dynamicQuery = MBMessageLocalServiceUtil.dynamicQuery();

		Property threadIdProperty = PropertyFactoryUtil.forName("threadId");

		dynamicQuery.add(threadIdProperty.eq(mbThreadId));

		Property parentMessageIdProperty = PropertyFactoryUtil.forName(
			"parentMessageId");

		dynamicQuery.add(
			parentMessageIdProperty.ne(
				MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID));

		return dynamicQuery;
	}

}
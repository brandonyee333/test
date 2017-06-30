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

package com.liferay.osb.hook.service.impl;

import com.liferay.osb.marketplace.social.MarketplaceActivityKeys;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceWrapper;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

/**
 * @author Douglas Wong
 */
public class OSBMBMessageLocalServiceImpl extends MBMessageLocalServiceWrapper {

	public OSBMBMessageLocalServiceImpl(
		MBMessageLocalService mbMessageLocalService) {

		super(mbMessageLocalService);
	}

	@Override
	public MBMessage addDiscussionMessage(
			long userId, String userName, long groupId, String className,
			long classPK, long threadId, long parentMessageId, String subject,
			String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBMessage mbMessage = super.addDiscussionMessage(
			userId, userName, groupId, className, classPK, threadId,
			parentMessageId, subject, body, serviceContext);

		if (!className.equals(AppEntry.class.getName())) {
			return mbMessage;
		}

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(classPK);

		SocialActivityLocalServiceUtil.addActivity(
			userId, OSBConstants.GROUP_GUEST_ID, AppEntry.class.getName(),
			appEntry.getAppEntryId(), MarketplaceActivityKeys.ADD_REVIEW,
			StringPool.BLANK, appEntry.getUserId());

		return mbMessage;
	}

	@Override
	public MBMessage updateDiscussionMessage(
			long userId, long messageId, String className, long classPK,
			String subject, String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBMessage mbMessage = super.updateDiscussionMessage(
			userId, messageId, className, classPK, subject, body,
			serviceContext);

		if (!className.equals(AppEntry.class.getName())) {
			return mbMessage;
		}

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(classPK);

		SocialActivityLocalServiceUtil.addActivity(
			userId, OSBConstants.GROUP_GUEST_ID, AppEntry.class.getName(),
			appEntry.getAppEntryId(), MarketplaceActivityKeys.UPDATE_REVIEW,
			StringPool.BLANK, appEntry.getUserId());

		return mbMessage;
	}

}
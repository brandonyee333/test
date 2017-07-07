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

import java.util.Date;

import com.liferay.osb.model.FeedbackEntry;
import com.liferay.osb.service.base.FeedbackEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Jenny Chen
 * @author Kyle Bischof
 */
public class FeedbackEntryLocalServiceImpl
	extends FeedbackEntryLocalServiceBaseImpl {

	public FeedbackEntry addFeedbackEntry(
			long userId, long classNameId, long classPK, int satisfied,
			String pageURL)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long feedbackEntryId = counterLocalService.increment();

		FeedbackEntry feedbackEntry = feedbackEntryPersistence.create(
			feedbackEntryId);

		feedbackEntry.setUserId(user.getUserId());
		feedbackEntry.setUserName(user.getFullName());
		feedbackEntry.setCreateDate(now);
		feedbackEntry.setClassNameId(classNameId);
		feedbackEntry.setClassPK(classPK);
		feedbackEntry.setSatisfied(satisfied);
		feedbackEntry.setPageURL(pageURL);
		
		//TODO implement serviceContext as needed
		
		ServiceContext serviceContext = new ServiceContext();

		feedbackEntryPersistence.update(feedbackEntry, serviceContext);

		return feedbackEntry;
	}

	public FeedbackEntry updateFeedbackEntry(
			long feedbackEntryId, String comments)
		throws PortalException, SystemException {

		FeedbackEntry feedbackEntry = feedbackEntryPersistence.findByPrimaryKey(
			feedbackEntryId);

		feedbackEntry.setComments(comments);
		
		//TODO implement serviceContext as needed
		
		ServiceContext serviceContext = new ServiceContext();

		feedbackEntryPersistence.update(feedbackEntry, serviceContext);

		return feedbackEntry;
	}

}
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.model.FeedbackEntry;
import com.liferay.osb.service.base.FeedbackEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

/**
 * @author Jenny Chen
 * @author Kyle Bischof
 */
public class FeedbackEntryLocalServiceImpl
	extends FeedbackEntryLocalServiceBaseImpl {

	public FeedbackEntry addFeedbackEntry(
			long userId, long classNameId, long classPK, int satisfied,
			String pageURL)
		throws PortalException {

		User user = userLocalService.getUser(userId);
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

		return feedbackEntryPersistence.update(feedbackEntry);
	}

	public FeedbackEntry updateFeedbackEntry(
			long feedbackEntryId, String comments)
		throws PortalException {

		FeedbackEntry feedbackEntry = feedbackEntryPersistence.findByPrimaryKey(
			feedbackEntryId);

		feedbackEntry.setComments(comments);

		return feedbackEntryPersistence.update(feedbackEntry);
	}

}
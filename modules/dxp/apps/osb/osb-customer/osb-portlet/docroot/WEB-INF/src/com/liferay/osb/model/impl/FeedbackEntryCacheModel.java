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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.FeedbackEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing FeedbackEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntry
 * @generated
 */
public class FeedbackEntryCacheModel implements CacheModel<FeedbackEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{feedbackEntryId=");
		sb.append(feedbackEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", satisfied=");
		sb.append(satisfied);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", pageURL=");
		sb.append(pageURL);
		sb.append("}");

		return sb.toString();
	}

	public FeedbackEntry toEntityModel() {
		FeedbackEntryImpl feedbackEntryImpl = new FeedbackEntryImpl();

		feedbackEntryImpl.setFeedbackEntryId(feedbackEntryId);
		feedbackEntryImpl.setUserId(userId);

		if (userName == null) {
			feedbackEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			feedbackEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			feedbackEntryImpl.setCreateDate(null);
		}
		else {
			feedbackEntryImpl.setCreateDate(new Date(createDate));
		}

		feedbackEntryImpl.setClassNameId(classNameId);
		feedbackEntryImpl.setClassPK(classPK);
		feedbackEntryImpl.setSatisfied(satisfied);

		if (comments == null) {
			feedbackEntryImpl.setComments(StringPool.BLANK);
		}
		else {
			feedbackEntryImpl.setComments(comments);
		}

		if (pageURL == null) {
			feedbackEntryImpl.setPageURL(StringPool.BLANK);
		}
		else {
			feedbackEntryImpl.setPageURL(pageURL);
		}

		feedbackEntryImpl.resetOriginalValues();

		return feedbackEntryImpl;
	}

	public long feedbackEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long classNameId;
	public long classPK;
	public int satisfied;
	public String comments;
	public String pageURL;
}
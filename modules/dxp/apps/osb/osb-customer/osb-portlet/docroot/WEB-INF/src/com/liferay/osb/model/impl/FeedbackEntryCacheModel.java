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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.FeedbackEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FeedbackEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntry
 * @generated
 */
@ProviderType
public class FeedbackEntryCacheModel implements CacheModel<FeedbackEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FeedbackEntryCacheModel)) {
			return false;
		}

		FeedbackEntryCacheModel feedbackEntryCacheModel = (FeedbackEntryCacheModel)obj;

		if (feedbackEntryId == feedbackEntryCacheModel.feedbackEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, feedbackEntryId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		feedbackEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		satisfied = objectInput.readInt();
		comments = objectInput.readUTF();
		pageURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(feedbackEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(satisfied);

		if (comments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comments);
		}

		if (pageURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pageURL);
		}
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
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.twitter.model.Feed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Feed in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FeedCacheModel implements CacheModel<Feed>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FeedCacheModel)) {
			return false;
		}

		FeedCacheModel feedCacheModel = (FeedCacheModel)object;

		if (feedId == feedCacheModel.feedId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, feedId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{feedId=");
		sb.append(feedId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", twitterUserId=");
		sb.append(twitterUserId);
		sb.append(", twitterScreenName=");
		sb.append(twitterScreenName);
		sb.append(", lastStatusId=");
		sb.append(lastStatusId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Feed toEntityModel() {
		FeedImpl feedImpl = new FeedImpl();

		feedImpl.setFeedId(feedId);
		feedImpl.setCompanyId(companyId);
		feedImpl.setUserId(userId);

		if (userName == null) {
			feedImpl.setUserName("");
		}
		else {
			feedImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			feedImpl.setCreateDate(null);
		}
		else {
			feedImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			feedImpl.setModifiedDate(null);
		}
		else {
			feedImpl.setModifiedDate(new Date(modifiedDate));
		}

		feedImpl.setTwitterUserId(twitterUserId);

		if (twitterScreenName == null) {
			feedImpl.setTwitterScreenName("");
		}
		else {
			feedImpl.setTwitterScreenName(twitterScreenName);
		}

		feedImpl.setLastStatusId(lastStatusId);

		feedImpl.resetOriginalValues();

		return feedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		feedId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		twitterUserId = objectInput.readLong();
		twitterScreenName = objectInput.readUTF();

		lastStatusId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(feedId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(twitterUserId);

		if (twitterScreenName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(twitterScreenName);
		}

		objectOutput.writeLong(lastStatusId);
	}

	public long feedId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long twitterUserId;
	public String twitterScreenName;
	public long lastStatusId;

}
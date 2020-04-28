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

package com.liferay.osb.loop.model.impl;

import com.liferay.osb.loop.model.LoopUserNotificationEvent;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopUserNotificationEvent in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopUserNotificationEventCacheModel
	implements CacheModel<LoopUserNotificationEvent>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopUserNotificationEventCacheModel)) {
			return false;
		}

		LoopUserNotificationEventCacheModel
			loopUserNotificationEventCacheModel =
				(LoopUserNotificationEventCacheModel)obj;

		if (loopUserNotificationEventId ==
				loopUserNotificationEventCacheModel.
					loopUserNotificationEventId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopUserNotificationEventId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{loopUserNotificationEventId=");
		sb.append(loopUserNotificationEventId);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", recipientUserId=");
		sb.append(recipientUserId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", groupClassNameId=");
		sb.append(groupClassNameId);
		sb.append(", groupClassPK=");
		sb.append(groupClassPK);
		sb.append(", groupKey=");
		sb.append(groupKey);
		sb.append(", type=");
		sb.append(type);
		sb.append(", received=");
		sb.append(received);
		sb.append(", opened=");
		sb.append(opened);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopUserNotificationEvent toEntityModel() {
		LoopUserNotificationEventImpl loopUserNotificationEventImpl =
			new LoopUserNotificationEventImpl();

		loopUserNotificationEventImpl.setLoopUserNotificationEventId(
			loopUserNotificationEventId);
		loopUserNotificationEventImpl.setCreateTime(createTime);
		loopUserNotificationEventImpl.setRecipientUserId(recipientUserId);
		loopUserNotificationEventImpl.setClassNameId(classNameId);
		loopUserNotificationEventImpl.setClassPK(classPK);
		loopUserNotificationEventImpl.setGroupClassNameId(groupClassNameId);
		loopUserNotificationEventImpl.setGroupClassPK(groupClassPK);
		loopUserNotificationEventImpl.setGroupKey(groupKey);
		loopUserNotificationEventImpl.setType(type);
		loopUserNotificationEventImpl.setReceived(received);
		loopUserNotificationEventImpl.setOpened(opened);

		loopUserNotificationEventImpl.resetOriginalValues();

		return loopUserNotificationEventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopUserNotificationEventId = objectInput.readLong();

		createTime = objectInput.readLong();

		recipientUserId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		groupClassNameId = objectInput.readLong();

		groupClassPK = objectInput.readLong();

		groupKey = objectInput.readLong();

		type = objectInput.readInt();

		received = objectInput.readBoolean();

		opened = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopUserNotificationEventId);

		objectOutput.writeLong(createTime);

		objectOutput.writeLong(recipientUserId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(groupClassNameId);

		objectOutput.writeLong(groupClassPK);

		objectOutput.writeLong(groupKey);

		objectOutput.writeInt(type);

		objectOutput.writeBoolean(received);

		objectOutput.writeBoolean(opened);
	}

	public long loopUserNotificationEventId;
	public long createTime;
	public long recipientUserId;
	public long classNameId;
	public long classPK;
	public long groupClassNameId;
	public long groupClassPK;
	public long groupKey;
	public int type;
	public boolean received;
	public boolean opened;

}
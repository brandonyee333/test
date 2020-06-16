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

import com.liferay.osb.loop.model.LoopUserNotificationRecord;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopUserNotificationRecord in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopUserNotificationRecordCacheModel
	implements CacheModel<LoopUserNotificationRecord>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopUserNotificationRecordCacheModel)) {
			return false;
		}

		LoopUserNotificationRecordCacheModel
			loopUserNotificationRecordCacheModel =
				(LoopUserNotificationRecordCacheModel)obj;

		if (loopUserNotificationRecordId ==
				loopUserNotificationRecordCacheModel.
					loopUserNotificationRecordId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopUserNotificationRecordId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{loopUserNotificationRecordId=");
		sb.append(loopUserNotificationRecordId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", deliveryType=");
		sb.append(deliveryType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopUserNotificationRecord toEntityModel() {
		LoopUserNotificationRecordImpl loopUserNotificationRecordImpl =
			new LoopUserNotificationRecordImpl();

		loopUserNotificationRecordImpl.setLoopUserNotificationRecordId(
			loopUserNotificationRecordId);
		loopUserNotificationRecordImpl.setUserId(userId);
		loopUserNotificationRecordImpl.setCreateTime(createTime);
		loopUserNotificationRecordImpl.setClassNameId(classNameId);
		loopUserNotificationRecordImpl.setClassPK(classPK);
		loopUserNotificationRecordImpl.setDeliveryType(deliveryType);

		loopUserNotificationRecordImpl.resetOriginalValues();

		return loopUserNotificationRecordImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopUserNotificationRecordId = objectInput.readLong();

		userId = objectInput.readLong();

		createTime = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		deliveryType = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopUserNotificationRecordId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(createTime);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(deliveryType);
	}

	public long loopUserNotificationRecordId;
	public long userId;
	public long createTime;
	public long classNameId;
	public long classPK;
	public int deliveryType;

}
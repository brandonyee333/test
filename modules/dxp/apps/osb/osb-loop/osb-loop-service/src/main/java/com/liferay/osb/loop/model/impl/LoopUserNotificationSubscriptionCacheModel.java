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

import com.liferay.osb.loop.model.LoopUserNotificationSubscription;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopUserNotificationSubscription in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopUserNotificationSubscriptionCacheModel
	implements CacheModel<LoopUserNotificationSubscription>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopUserNotificationSubscriptionCacheModel)) {
			return false;
		}

		LoopUserNotificationSubscriptionCacheModel
			loopUserNotificationSubscriptionCacheModel =
				(LoopUserNotificationSubscriptionCacheModel)obj;

		if (loopUserNotificationSubscriptionId ==
				loopUserNotificationSubscriptionCacheModel.
					loopUserNotificationSubscriptionId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopUserNotificationSubscriptionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{loopUserNotificationSubscriptionId=");
		sb.append(loopUserNotificationSubscriptionId);
		sb.append(", loopPersonId=");
		sb.append(loopPersonId);
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
	public LoopUserNotificationSubscription toEntityModel() {
		LoopUserNotificationSubscriptionImpl
			loopUserNotificationSubscriptionImpl =
				new LoopUserNotificationSubscriptionImpl();

		loopUserNotificationSubscriptionImpl.
			setLoopUserNotificationSubscriptionId(
				loopUserNotificationSubscriptionId);
		loopUserNotificationSubscriptionImpl.setLoopPersonId(loopPersonId);
		loopUserNotificationSubscriptionImpl.setClassNameId(classNameId);
		loopUserNotificationSubscriptionImpl.setClassPK(classPK);
		loopUserNotificationSubscriptionImpl.setDeliveryType(deliveryType);

		loopUserNotificationSubscriptionImpl.resetOriginalValues();

		return loopUserNotificationSubscriptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopUserNotificationSubscriptionId = objectInput.readLong();

		loopPersonId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		deliveryType = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopUserNotificationSubscriptionId);

		objectOutput.writeLong(loopPersonId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(deliveryType);
	}

	public long loopUserNotificationSubscriptionId;
	public long loopPersonId;
	public long classNameId;
	public long classPK;
	public int deliveryType;

}
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

package com.liferay.osb.customer.account.entry.details.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.account.entry.details.model.Event;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Event in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Event
 * @generated
 */
@ProviderType
public class EventCacheModel implements CacheModel<Event>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventCacheModel)) {
			return false;
		}

		EventCacheModel eventCacheModel = (EventCacheModel)obj;

		if (eventId == eventCacheModel.eventId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{eventId=");
		sb.append(eventId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", occurDate=");
		sb.append(occurDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeClassNameId=");
		sb.append(typeClassNameId);
		sb.append(", typeClassPK=");
		sb.append(typeClassPK);
		sb.append(", title=");
		sb.append(title);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", additionalInfo=");
		sb.append(additionalInfo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Event toEntityModel() {
		EventImpl eventImpl = new EventImpl();

		eventImpl.setEventId(eventId);
		eventImpl.setUserId(userId);

		if (userName == null) {
			eventImpl.setUserName("");
		}
		else {
			eventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			eventImpl.setCreateDate(null);
		}
		else {
			eventImpl.setCreateDate(new Date(createDate));
		}

		if (occurDate == Long.MIN_VALUE) {
			eventImpl.setOccurDate(null);
		}
		else {
			eventImpl.setOccurDate(new Date(occurDate));
		}

		eventImpl.setAccountEntryId(accountEntryId);
		eventImpl.setClassNameId(classNameId);
		eventImpl.setClassPK(classPK);
		eventImpl.setType(type);
		eventImpl.setTypeClassNameId(typeClassNameId);
		eventImpl.setTypeClassPK(typeClassPK);

		if (title == null) {
			eventImpl.setTitle("");
		}
		else {
			eventImpl.setTitle(title);
		}

		if (summary == null) {
			eventImpl.setSummary("");
		}
		else {
			eventImpl.setSummary(summary);
		}

		if (additionalInfo == null) {
			eventImpl.setAdditionalInfo("");
		}
		else {
			eventImpl.setAdditionalInfo(additionalInfo);
		}

		eventImpl.resetOriginalValues();

		return eventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		eventId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		occurDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		type = objectInput.readInt();

		typeClassNameId = objectInput.readLong();

		typeClassPK = objectInput.readLong();
		title = objectInput.readUTF();
		summary = objectInput.readUTF();
		additionalInfo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(eventId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(occurDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(type);

		objectOutput.writeLong(typeClassNameId);

		objectOutput.writeLong(typeClassPK);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (summary == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(summary);
		}

		if (additionalInfo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(additionalInfo);
		}
	}

	public long eventId;
	public long userId;
	public String userName;
	public long createDate;
	public long occurDate;
	public long accountEntryId;
	public long classNameId;
	public long classPK;
	public int type;
	public long typeClassNameId;
	public long typeClassPK;
	public String title;
	public String summary;
	public String additionalInfo;
}
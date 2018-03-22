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

import com.liferay.osb.model.TicketFeedback;

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
 * The cache model class for representing TicketFeedback in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedback
 * @generated
 */
@ProviderType
public class TicketFeedbackCacheModel implements CacheModel<TicketFeedback>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketFeedbackCacheModel)) {
			return false;
		}

		TicketFeedbackCacheModel ticketFeedbackCacheModel = (TicketFeedbackCacheModel)obj;

		if (ticketFeedbackId == ticketFeedbackCacheModel.ticketFeedbackId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketFeedbackId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{ticketFeedbackId=");
		sb.append(ticketFeedbackId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", satisfied=");
		sb.append(satisfied);
		sb.append(", answer1=");
		sb.append(answer1);
		sb.append(", answer2=");
		sb.append(answer2);
		sb.append(", answer3=");
		sb.append(answer3);
		sb.append(", rating1=");
		sb.append(rating1);
		sb.append(", rating2=");
		sb.append(rating2);
		sb.append(", rating3=");
		sb.append(rating3);
		sb.append(", rating4=");
		sb.append(rating4);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TicketFeedback toEntityModel() {
		TicketFeedbackImpl ticketFeedbackImpl = new TicketFeedbackImpl();

		ticketFeedbackImpl.setTicketFeedbackId(ticketFeedbackId);
		ticketFeedbackImpl.setUserId(userId);

		if (userName == null) {
			ticketFeedbackImpl.setUserName(StringPool.BLANK);
		}
		else {
			ticketFeedbackImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketFeedbackImpl.setCreateDate(null);
		}
		else {
			ticketFeedbackImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ticketFeedbackImpl.setModifiedDate(null);
		}
		else {
			ticketFeedbackImpl.setModifiedDate(new Date(modifiedDate));
		}

		ticketFeedbackImpl.setAccountEntryId(accountEntryId);
		ticketFeedbackImpl.setTicketEntryId(ticketEntryId);
		ticketFeedbackImpl.setSubject(subject);
		ticketFeedbackImpl.setSatisfied(satisfied);
		ticketFeedbackImpl.setAnswer1(answer1);
		ticketFeedbackImpl.setAnswer2(answer2);
		ticketFeedbackImpl.setAnswer3(answer3);
		ticketFeedbackImpl.setRating1(rating1);
		ticketFeedbackImpl.setRating2(rating2);
		ticketFeedbackImpl.setRating3(rating3);
		ticketFeedbackImpl.setRating4(rating4);

		if (comments == null) {
			ticketFeedbackImpl.setComments(StringPool.BLANK);
		}
		else {
			ticketFeedbackImpl.setComments(comments);
		}

		ticketFeedbackImpl.setStatus(status);

		ticketFeedbackImpl.resetOriginalValues();

		return ticketFeedbackImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketFeedbackId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		ticketEntryId = objectInput.readLong();

		subject = objectInput.readInt();

		satisfied = objectInput.readInt();

		answer1 = objectInput.readInt();

		answer2 = objectInput.readInt();

		answer3 = objectInput.readInt();

		rating1 = objectInput.readInt();

		rating2 = objectInput.readInt();

		rating3 = objectInput.readInt();

		rating4 = objectInput.readInt();
		comments = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketFeedbackId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(ticketEntryId);

		objectOutput.writeInt(subject);

		objectOutput.writeInt(satisfied);

		objectOutput.writeInt(answer1);

		objectOutput.writeInt(answer2);

		objectOutput.writeInt(answer3);

		objectOutput.writeInt(rating1);

		objectOutput.writeInt(rating2);

		objectOutput.writeInt(rating3);

		objectOutput.writeInt(rating4);

		if (comments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comments);
		}

		objectOutput.writeInt(status);
	}

	public long ticketFeedbackId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEntryId;
	public long ticketEntryId;
	public int subject;
	public int satisfied;
	public int answer1;
	public int answer2;
	public int answer3;
	public int rating1;
	public int rating2;
	public int rating3;
	public int rating4;
	public String comments;
	public int status;
}
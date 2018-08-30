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

package com.liferay.osb.customer.ticket.attachment.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.ticket.attachment.model.TicketAttachment;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TicketAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachment
 * @generated
 */
@ProviderType
public class TicketAttachmentCacheModel implements CacheModel<TicketAttachment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketAttachmentCacheModel)) {
			return false;
		}

		TicketAttachmentCacheModel ticketAttachmentCacheModel = (TicketAttachmentCacheModel)obj;

		if (ticketAttachmentId == ticketAttachmentCacheModel.ticketAttachmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketAttachmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{ticketAttachmentId=");
		sb.append(ticketAttachmentId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", zendeskTicketId=");
		sb.append(zendeskTicketId);
		sb.append(", fileRepositoryId=");
		sb.append(fileRepositoryId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", fileSize=");
		sb.append(fileSize);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TicketAttachment toEntityModel() {
		TicketAttachmentImpl ticketAttachmentImpl = new TicketAttachmentImpl();

		ticketAttachmentImpl.setTicketAttachmentId(ticketAttachmentId);
		ticketAttachmentImpl.setUserId(userId);

		if (userName == null) {
			ticketAttachmentImpl.setUserName("");
		}
		else {
			ticketAttachmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketAttachmentImpl.setCreateDate(null);
		}
		else {
			ticketAttachmentImpl.setCreateDate(new Date(createDate));
		}

		ticketAttachmentImpl.setAccountEntryId(accountEntryId);
		ticketAttachmentImpl.setZendeskTicketId(zendeskTicketId);

		if (fileRepositoryId == null) {
			ticketAttachmentImpl.setFileRepositoryId("");
		}
		else {
			ticketAttachmentImpl.setFileRepositoryId(fileRepositoryId);
		}

		if (fileName == null) {
			ticketAttachmentImpl.setFileName("");
		}
		else {
			ticketAttachmentImpl.setFileName(fileName);
		}

		ticketAttachmentImpl.setFileSize(fileSize);
		ticketAttachmentImpl.setType(type);

		ticketAttachmentImpl.resetOriginalValues();

		return ticketAttachmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketAttachmentId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		zendeskTicketId = objectInput.readLong();
		fileRepositoryId = objectInput.readUTF();
		fileName = objectInput.readUTF();

		fileSize = objectInput.readLong();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketAttachmentId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(zendeskTicketId);

		if (fileRepositoryId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileRepositoryId);
		}

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(fileSize);

		objectOutput.writeInt(type);
	}

	public long ticketAttachmentId;
	public long userId;
	public String userName;
	public long createDate;
	public long accountEntryId;
	public long zendeskTicketId;
	public String fileRepositoryId;
	public String fileName;
	public long fileSize;
	public int type;
}
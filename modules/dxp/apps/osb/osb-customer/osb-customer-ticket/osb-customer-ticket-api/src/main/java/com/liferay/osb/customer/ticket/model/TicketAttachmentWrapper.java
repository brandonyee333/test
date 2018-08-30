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

package com.liferay.osb.customer.ticket.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link TicketAttachment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachment
 * @generated
 */
@ProviderType
public class TicketAttachmentWrapper implements TicketAttachment,
	ModelWrapper<TicketAttachment> {
	public TicketAttachmentWrapper(TicketAttachment ticketAttachment) {
		_ticketAttachment = ticketAttachment;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return TicketAttachment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketAttachmentId", getTicketAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("zendeskTicketId", getZendeskTicketId());
		attributes.put("fileRepositoryId", getFileRepositoryId());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketAttachmentId = (Long)attributes.get("ticketAttachmentId");

		if (ticketAttachmentId != null) {
			setTicketAttachmentId(ticketAttachmentId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long zendeskTicketId = (Long)attributes.get("zendeskTicketId");

		if (zendeskTicketId != null) {
			setZendeskTicketId(zendeskTicketId);
		}

		String fileRepositoryId = (String)attributes.get("fileRepositoryId");

		if (fileRepositoryId != null) {
			setFileRepositoryId(fileRepositoryId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long fileSize = (Long)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public Object clone() {
		return new TicketAttachmentWrapper((TicketAttachment)_ticketAttachment.clone());
	}

	@Override
	public int compareTo(TicketAttachment ticketAttachment) {
		return _ticketAttachment.compareTo(ticketAttachment);
	}

	/**
	* Returns the account entry ID of this ticket attachment.
	*
	* @return the account entry ID of this ticket attachment
	*/
	@Override
	public long getAccountEntryId() {
		return _ticketAttachment.getAccountEntryId();
	}

	/**
	* Returns the create date of this ticket attachment.
	*
	* @return the create date of this ticket attachment
	*/
	@Override
	public Date getCreateDate() {
		return _ticketAttachment.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketAttachment.getExpandoBridge();
	}

	/**
	* Returns the file name of this ticket attachment.
	*
	* @return the file name of this ticket attachment
	*/
	@Override
	public String getFileName() {
		return _ticketAttachment.getFileName();
	}

	@Override
	public String getFilePath() {
		return _ticketAttachment.getFilePath();
	}

	/**
	* Returns the file repository ID of this ticket attachment.
	*
	* @return the file repository ID of this ticket attachment
	*/
	@Override
	public String getFileRepositoryId() {
		return _ticketAttachment.getFileRepositoryId();
	}

	/**
	* Returns the file size of this ticket attachment.
	*
	* @return the file size of this ticket attachment
	*/
	@Override
	public long getFileSize() {
		return _ticketAttachment.getFileSize();
	}

	/**
	* Returns the primary key of this ticket attachment.
	*
	* @return the primary key of this ticket attachment
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketAttachment.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketAttachment.getPrimaryKeyObj();
	}

	/**
	* Returns the ticket attachment ID of this ticket attachment.
	*
	* @return the ticket attachment ID of this ticket attachment
	*/
	@Override
	public long getTicketAttachmentId() {
		return _ticketAttachment.getTicketAttachmentId();
	}

	/**
	* Returns the type of this ticket attachment.
	*
	* @return the type of this ticket attachment
	*/
	@Override
	public int getType() {
		return _ticketAttachment.getType();
	}

	/**
	* Returns the user ID of this ticket attachment.
	*
	* @return the user ID of this ticket attachment
	*/
	@Override
	public long getUserId() {
		return _ticketAttachment.getUserId();
	}

	/**
	* Returns the user name of this ticket attachment.
	*
	* @return the user name of this ticket attachment
	*/
	@Override
	public String getUserName() {
		return _ticketAttachment.getUserName();
	}

	/**
	* Returns the user uuid of this ticket attachment.
	*
	* @return the user uuid of this ticket attachment
	*/
	@Override
	public String getUserUuid() {
		return _ticketAttachment.getUserUuid();
	}

	/**
	* Returns the zendesk ticket ID of this ticket attachment.
	*
	* @return the zendesk ticket ID of this ticket attachment
	*/
	@Override
	public long getZendeskTicketId() {
		return _ticketAttachment.getZendeskTicketId();
	}

	@Override
	public int hashCode() {
		return _ticketAttachment.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ticketAttachment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketAttachment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketAttachment.isNew();
	}

	@Override
	public void persist() {
		_ticketAttachment.persist();
	}

	/**
	* Sets the account entry ID of this ticket attachment.
	*
	* @param accountEntryId the account entry ID of this ticket attachment
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_ticketAttachment.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketAttachment.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this ticket attachment.
	*
	* @param createDate the create date of this ticket attachment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketAttachment.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketAttachment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketAttachment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this ticket attachment.
	*
	* @param fileName the file name of this ticket attachment
	*/
	@Override
	public void setFileName(String fileName) {
		_ticketAttachment.setFileName(fileName);
	}

	/**
	* Sets the file repository ID of this ticket attachment.
	*
	* @param fileRepositoryId the file repository ID of this ticket attachment
	*/
	@Override
	public void setFileRepositoryId(String fileRepositoryId) {
		_ticketAttachment.setFileRepositoryId(fileRepositoryId);
	}

	/**
	* Sets the file size of this ticket attachment.
	*
	* @param fileSize the file size of this ticket attachment
	*/
	@Override
	public void setFileSize(long fileSize) {
		_ticketAttachment.setFileSize(fileSize);
	}

	@Override
	public void setNew(boolean n) {
		_ticketAttachment.setNew(n);
	}

	/**
	* Sets the primary key of this ticket attachment.
	*
	* @param primaryKey the primary key of this ticket attachment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketAttachment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ticket attachment ID of this ticket attachment.
	*
	* @param ticketAttachmentId the ticket attachment ID of this ticket attachment
	*/
	@Override
	public void setTicketAttachmentId(long ticketAttachmentId) {
		_ticketAttachment.setTicketAttachmentId(ticketAttachmentId);
	}

	/**
	* Sets the type of this ticket attachment.
	*
	* @param type the type of this ticket attachment
	*/
	@Override
	public void setType(int type) {
		_ticketAttachment.setType(type);
	}

	/**
	* Sets the user ID of this ticket attachment.
	*
	* @param userId the user ID of this ticket attachment
	*/
	@Override
	public void setUserId(long userId) {
		_ticketAttachment.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket attachment.
	*
	* @param userName the user name of this ticket attachment
	*/
	@Override
	public void setUserName(String userName) {
		_ticketAttachment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket attachment.
	*
	* @param userUuid the user uuid of this ticket attachment
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_ticketAttachment.setUserUuid(userUuid);
	}

	/**
	* Sets the zendesk ticket ID of this ticket attachment.
	*
	* @param zendeskTicketId the zendesk ticket ID of this ticket attachment
	*/
	@Override
	public void setZendeskTicketId(long zendeskTicketId) {
		_ticketAttachment.setZendeskTicketId(zendeskTicketId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketAttachment> toCacheModel() {
		return _ticketAttachment.toCacheModel();
	}

	@Override
	public TicketAttachment toEscapedModel() {
		return new TicketAttachmentWrapper(_ticketAttachment.toEscapedModel());
	}

	@Override
	public String toString() {
		return _ticketAttachment.toString();
	}

	@Override
	public TicketAttachment toUnescapedModel() {
		return new TicketAttachmentWrapper(_ticketAttachment.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _ticketAttachment.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketAttachmentWrapper)) {
			return false;
		}

		TicketAttachmentWrapper ticketAttachmentWrapper = (TicketAttachmentWrapper)obj;

		if (Objects.equals(_ticketAttachment,
					ticketAttachmentWrapper._ticketAttachment)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketAttachment getWrappedModel() {
		return _ticketAttachment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketAttachment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketAttachment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketAttachment.resetOriginalValues();
	}

	private final TicketAttachment _ticketAttachment;
}
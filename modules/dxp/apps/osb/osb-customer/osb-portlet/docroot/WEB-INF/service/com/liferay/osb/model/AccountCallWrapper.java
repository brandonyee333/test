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

package com.liferay.osb.model;

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
 * This class is a wrapper for {@link AccountCall}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCall
 * @generated
 */
@ProviderType
public class AccountCallWrapper implements AccountCall,
	ModelWrapper<AccountCall> {
	public AccountCallWrapper(AccountCall accountCall) {
		_accountCall = accountCall;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountCall.class;
	}

	@Override
	public String getModelClassName() {
		return AccountCall.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountCallId", getAccountCallId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("type", getType());
		attributes.put("callDate", getCallDate());
		attributes.put("callLength", getCallLength());
		attributes.put("summary", getSummary());
		attributes.put("clientsPresent", getClientsPresent());
		attributes.put("notes", getNotes());
		attributes.put("actionItems", getActionItems());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountCallId = (Long)attributes.get("accountCallId");

		if (accountCallId != null) {
			setAccountCallId(accountCallId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Date callDate = (Date)attributes.get("callDate");

		if (callDate != null) {
			setCallDate(callDate);
		}

		Long callLength = (Long)attributes.get("callLength");

		if (callLength != null) {
			setCallLength(callLength);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		String clientsPresent = (String)attributes.get("clientsPresent");

		if (clientsPresent != null) {
			setClientsPresent(clientsPresent);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		String actionItems = (String)attributes.get("actionItems");

		if (actionItems != null) {
			setActionItems(actionItems);
		}
	}

	@Override
	public AccountCall toEscapedModel() {
		return new AccountCallWrapper(_accountCall.toEscapedModel());
	}

	@Override
	public AccountCall toUnescapedModel() {
		return new AccountCallWrapper(_accountCall.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _accountCall.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountCall.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountCall.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountCall.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountCall> toCacheModel() {
		return _accountCall.toCacheModel();
	}

	@Override
	public int compareTo(AccountCall accountCall) {
		return _accountCall.compareTo(accountCall);
	}

	@Override
	public int getCallLengthHours() {
		return _accountCall.getCallLengthHours();
	}

	@Override
	public int getCallLengthMinutes() {
		return _accountCall.getCallLengthMinutes();
	}

	@Override
	public int getCallLengthSeconds() {
		return _accountCall.getCallLengthSeconds();
	}

	/**
	* Returns the type of this account call.
	*
	* @return the type of this account call
	*/
	@Override
	public int getType() {
		return _accountCall.getType();
	}

	@Override
	public int hashCode() {
		return _accountCall.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountCall.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountCallWrapper((AccountCall)_accountCall.clone());
	}

	/**
	* Returns the action items of this account call.
	*
	* @return the action items of this account call
	*/
	@Override
	public java.lang.String getActionItems() {
		return _accountCall.getActionItems();
	}

	@Override
	public java.lang.String getCallLengthLabel() {
		return _accountCall.getCallLengthLabel();
	}

	/**
	* Returns the clients present of this account call.
	*
	* @return the clients present of this account call
	*/
	@Override
	public java.lang.String getClientsPresent() {
		return _accountCall.getClientsPresent();
	}

	/**
	* Returns the modified user name of this account call.
	*
	* @return the modified user name of this account call
	*/
	@Override
	public java.lang.String getModifiedUserName() {
		return _accountCall.getModifiedUserName();
	}

	/**
	* Returns the modified user uuid of this account call.
	*
	* @return the modified user uuid of this account call
	*/
	@Override
	public java.lang.String getModifiedUserUuid() {
		return _accountCall.getModifiedUserUuid();
	}

	/**
	* Returns the notes of this account call.
	*
	* @return the notes of this account call
	*/
	@Override
	public java.lang.String getNotes() {
		return _accountCall.getNotes();
	}

	/**
	* Returns the summary of this account call.
	*
	* @return the summary of this account call
	*/
	@Override
	public java.lang.String getSummary() {
		return _accountCall.getSummary();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _accountCall.getTypeLabel();
	}

	@Override
	public java.lang.String toString() {
		return _accountCall.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountCall.toXmlString();
	}

	/**
	* Returns the call date of this account call.
	*
	* @return the call date of this account call
	*/
	@Override
	public Date getCallDate() {
		return _accountCall.getCallDate();
	}

	/**
	* Returns the create date of this account call.
	*
	* @return the create date of this account call
	*/
	@Override
	public Date getCreateDate() {
		return _accountCall.getCreateDate();
	}

	/**
	* Returns the modified date of this account call.
	*
	* @return the modified date of this account call
	*/
	@Override
	public Date getModifiedDate() {
		return _accountCall.getModifiedDate();
	}

	/**
	* Returns the account call ID of this account call.
	*
	* @return the account call ID of this account call
	*/
	@Override
	public long getAccountCallId() {
		return _accountCall.getAccountCallId();
	}

	/**
	* Returns the account entry ID of this account call.
	*
	* @return the account entry ID of this account call
	*/
	@Override
	public long getAccountEntryId() {
		return _accountCall.getAccountEntryId();
	}

	/**
	* Returns the call length of this account call.
	*
	* @return the call length of this account call
	*/
	@Override
	public long getCallLength() {
		return _accountCall.getCallLength();
	}

	/**
	* Returns the modified user ID of this account call.
	*
	* @return the modified user ID of this account call
	*/
	@Override
	public long getModifiedUserId() {
		return _accountCall.getModifiedUserId();
	}

	/**
	* Returns the primary key of this account call.
	*
	* @return the primary key of this account call
	*/
	@Override
	public long getPrimaryKey() {
		return _accountCall.getPrimaryKey();
	}

	@Override
	public void persist() {
		_accountCall.persist();
	}

	/**
	* Sets the account call ID of this account call.
	*
	* @param accountCallId the account call ID of this account call
	*/
	@Override
	public void setAccountCallId(long accountCallId) {
		_accountCall.setAccountCallId(accountCallId);
	}

	/**
	* Sets the account entry ID of this account call.
	*
	* @param accountEntryId the account entry ID of this account call
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountCall.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the action items of this account call.
	*
	* @param actionItems the action items of this account call
	*/
	@Override
	public void setActionItems(java.lang.String actionItems) {
		_accountCall.setActionItems(actionItems);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountCall.setCachedModel(cachedModel);
	}

	/**
	* Sets the call date of this account call.
	*
	* @param callDate the call date of this account call
	*/
	@Override
	public void setCallDate(Date callDate) {
		_accountCall.setCallDate(callDate);
	}

	/**
	* Sets the call length of this account call.
	*
	* @param callLength the call length of this account call
	*/
	@Override
	public void setCallLength(long callLength) {
		_accountCall.setCallLength(callLength);
	}

	/**
	* Sets the clients present of this account call.
	*
	* @param clientsPresent the clients present of this account call
	*/
	@Override
	public void setClientsPresent(java.lang.String clientsPresent) {
		_accountCall.setClientsPresent(clientsPresent);
	}

	/**
	* Sets the create date of this account call.
	*
	* @param createDate the create date of this account call
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_accountCall.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountCall.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountCall.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountCall.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this account call.
	*
	* @param modifiedDate the modified date of this account call
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accountCall.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the modified user ID of this account call.
	*
	* @param modifiedUserId the modified user ID of this account call
	*/
	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_accountCall.setModifiedUserId(modifiedUserId);
	}

	/**
	* Sets the modified user name of this account call.
	*
	* @param modifiedUserName the modified user name of this account call
	*/
	@Override
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountCall.setModifiedUserName(modifiedUserName);
	}

	/**
	* Sets the modified user uuid of this account call.
	*
	* @param modifiedUserUuid the modified user uuid of this account call
	*/
	@Override
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountCall.setModifiedUserUuid(modifiedUserUuid);
	}

	@Override
	public void setNew(boolean n) {
		_accountCall.setNew(n);
	}

	/**
	* Sets the notes of this account call.
	*
	* @param notes the notes of this account call
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_accountCall.setNotes(notes);
	}

	/**
	* Sets the primary key of this account call.
	*
	* @param primaryKey the primary key of this account call
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountCall.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountCall.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the summary of this account call.
	*
	* @param summary the summary of this account call
	*/
	@Override
	public void setSummary(java.lang.String summary) {
		_accountCall.setSummary(summary);
	}

	/**
	* Sets the type of this account call.
	*
	* @param type the type of this account call
	*/
	@Override
	public void setType(int type) {
		_accountCall.setType(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountCallWrapper)) {
			return false;
		}

		AccountCallWrapper accountCallWrapper = (AccountCallWrapper)obj;

		if (Objects.equals(_accountCall, accountCallWrapper._accountCall)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountCall getWrappedModel() {
		return _accountCall;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountCall.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountCall.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountCall.resetOriginalValues();
	}

	private final AccountCall _accountCall;
}
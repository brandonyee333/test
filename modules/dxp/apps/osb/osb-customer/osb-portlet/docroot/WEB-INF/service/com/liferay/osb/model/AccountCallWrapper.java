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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccountCall}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountCall
 * @generated
 */
public class AccountCallWrapper implements AccountCall,
	ModelWrapper<AccountCall> {
	public AccountCallWrapper(AccountCall accountCall) {
		_accountCall = accountCall;
	}

	public Class<?> getModelClass() {
		return AccountCall.class;
	}

	public String getModelClassName() {
		return AccountCall.class.getName();
	}

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

	/**
	* Returns the primary key of this account call.
	*
	* @return the primary key of this account call
	*/
	public long getPrimaryKey() {
		return _accountCall.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account call.
	*
	* @param primaryKey the primary key of this account call
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountCall.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account call ID of this account call.
	*
	* @return the account call ID of this account call
	*/
	public long getAccountCallId() {
		return _accountCall.getAccountCallId();
	}

	/**
	* Sets the account call ID of this account call.
	*
	* @param accountCallId the account call ID of this account call
	*/
	public void setAccountCallId(long accountCallId) {
		_accountCall.setAccountCallId(accountCallId);
	}

	/**
	* Returns the create date of this account call.
	*
	* @return the create date of this account call
	*/
	public java.util.Date getCreateDate() {
		return _accountCall.getCreateDate();
	}

	/**
	* Sets the create date of this account call.
	*
	* @param createDate the create date of this account call
	*/
	public void setCreateDate(java.util.Date createDate) {
		_accountCall.setCreateDate(createDate);
	}

	/**
	* Returns the modified user ID of this account call.
	*
	* @return the modified user ID of this account call
	*/
	public long getModifiedUserId() {
		return _accountCall.getModifiedUserId();
	}

	/**
	* Sets the modified user ID of this account call.
	*
	* @param modifiedUserId the modified user ID of this account call
	*/
	public void setModifiedUserId(long modifiedUserId) {
		_accountCall.setModifiedUserId(modifiedUserId);
	}

	/**
	* Returns the modified user uuid of this account call.
	*
	* @return the modified user uuid of this account call
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getModifiedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCall.getModifiedUserUuid();
	}

	/**
	* Sets the modified user uuid of this account call.
	*
	* @param modifiedUserUuid the modified user uuid of this account call
	*/
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountCall.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Returns the modified user name of this account call.
	*
	* @return the modified user name of this account call
	*/
	public java.lang.String getModifiedUserName() {
		return _accountCall.getModifiedUserName();
	}

	/**
	* Sets the modified user name of this account call.
	*
	* @param modifiedUserName the modified user name of this account call
	*/
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountCall.setModifiedUserName(modifiedUserName);
	}

	/**
	* Returns the modified date of this account call.
	*
	* @return the modified date of this account call
	*/
	public java.util.Date getModifiedDate() {
		return _accountCall.getModifiedDate();
	}

	/**
	* Sets the modified date of this account call.
	*
	* @param modifiedDate the modified date of this account call
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_accountCall.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this account call.
	*
	* @return the account entry ID of this account call
	*/
	public long getAccountEntryId() {
		return _accountCall.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account call.
	*
	* @param accountEntryId the account entry ID of this account call
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountCall.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the type of this account call.
	*
	* @return the type of this account call
	*/
	public int getType() {
		return _accountCall.getType();
	}

	/**
	* Sets the type of this account call.
	*
	* @param type the type of this account call
	*/
	public void setType(int type) {
		_accountCall.setType(type);
	}

	/**
	* Returns the call date of this account call.
	*
	* @return the call date of this account call
	*/
	public java.util.Date getCallDate() {
		return _accountCall.getCallDate();
	}

	/**
	* Sets the call date of this account call.
	*
	* @param callDate the call date of this account call
	*/
	public void setCallDate(java.util.Date callDate) {
		_accountCall.setCallDate(callDate);
	}

	/**
	* Returns the call length of this account call.
	*
	* @return the call length of this account call
	*/
	public long getCallLength() {
		return _accountCall.getCallLength();
	}

	/**
	* Sets the call length of this account call.
	*
	* @param callLength the call length of this account call
	*/
	public void setCallLength(long callLength) {
		_accountCall.setCallLength(callLength);
	}

	/**
	* Returns the summary of this account call.
	*
	* @return the summary of this account call
	*/
	public java.lang.String getSummary() {
		return _accountCall.getSummary();
	}

	/**
	* Sets the summary of this account call.
	*
	* @param summary the summary of this account call
	*/
	public void setSummary(java.lang.String summary) {
		_accountCall.setSummary(summary);
	}

	/**
	* Returns the clients present of this account call.
	*
	* @return the clients present of this account call
	*/
	public java.lang.String getClientsPresent() {
		return _accountCall.getClientsPresent();
	}

	/**
	* Sets the clients present of this account call.
	*
	* @param clientsPresent the clients present of this account call
	*/
	public void setClientsPresent(java.lang.String clientsPresent) {
		_accountCall.setClientsPresent(clientsPresent);
	}

	/**
	* Returns the notes of this account call.
	*
	* @return the notes of this account call
	*/
	public java.lang.String getNotes() {
		return _accountCall.getNotes();
	}

	/**
	* Sets the notes of this account call.
	*
	* @param notes the notes of this account call
	*/
	public void setNotes(java.lang.String notes) {
		_accountCall.setNotes(notes);
	}

	/**
	* Returns the action items of this account call.
	*
	* @return the action items of this account call
	*/
	public java.lang.String getActionItems() {
		return _accountCall.getActionItems();
	}

	/**
	* Sets the action items of this account call.
	*
	* @param actionItems the action items of this account call
	*/
	public void setActionItems(java.lang.String actionItems) {
		_accountCall.setActionItems(actionItems);
	}

	public boolean isNew() {
		return _accountCall.isNew();
	}

	public void setNew(boolean n) {
		_accountCall.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountCall.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountCall.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountCall.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountCall.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountCall.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountCall.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountCall.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountCallWrapper((AccountCall)_accountCall.clone());
	}

	public int compareTo(com.liferay.osb.model.AccountCall accountCall) {
		return _accountCall.compareTo(accountCall);
	}

	@Override
	public int hashCode() {
		return _accountCall.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountCall> toCacheModel() {
		return _accountCall.toCacheModel();
	}

	public com.liferay.osb.model.AccountCall toEscapedModel() {
		return new AccountCallWrapper(_accountCall.toEscapedModel());
	}

	public com.liferay.osb.model.AccountCall toUnescapedModel() {
		return new AccountCallWrapper(_accountCall.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountCall.toString();
	}

	public java.lang.String toXmlString() {
		return _accountCall.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountCall.persist();
	}

	public int getCallLengthHours() {
		return _accountCall.getCallLengthHours();
	}

	public java.lang.String getCallLengthLabel() {
		return _accountCall.getCallLengthLabel();
	}

	public int getCallLengthMinutes() {
		return _accountCall.getCallLengthMinutes();
	}

	public int getCallLengthSeconds() {
		return _accountCall.getCallLengthSeconds();
	}

	public java.lang.String getTypeLabel() {
		return _accountCall.getTypeLabel();
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

		if (Validator.equals(_accountCall, accountCallWrapper._accountCall)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountCall getWrappedAccountCall() {
		return _accountCall;
	}

	public AccountCall getWrappedModel() {
		return _accountCall;
	}

	public void resetOriginalValues() {
		_accountCall.resetOriginalValues();
	}

	private AccountCall _accountCall;
}
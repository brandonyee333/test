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
 * This class is a wrapper for {@link OrderEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntry
 * @generated
 */
@ProviderType
public class OrderEntryWrapper implements OrderEntry, ModelWrapper<OrderEntry> {
	public OrderEntryWrapper(OrderEntry orderEntry) {
		_orderEntry = orderEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return OrderEntry.class;
	}

	@Override
	public String getModelClassName() {
		return OrderEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("orderEntryId", getOrderEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("purchaseOrderKey", getPurchaseOrderKey());
		attributes.put("startDate", getStartDate());
		attributes.put("prorated", getProrated());
		attributes.put("actualStartDate", getActualStartDate());
		attributes.put("renewCount", getRenewCount());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long orderEntryId = (Long)attributes.get("orderEntryId");

		if (orderEntryId != null) {
			setOrderEntryId(orderEntryId);
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

		String purchaseOrderKey = (String)attributes.get("purchaseOrderKey");

		if (purchaseOrderKey != null) {
			setPurchaseOrderKey(purchaseOrderKey);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Boolean prorated = (Boolean)attributes.get("prorated");

		if (prorated != null) {
			setProrated(prorated);
		}

		Date actualStartDate = (Date)attributes.get("actualStartDate");

		if (actualStartDate != null) {
			setActualStartDate(actualStartDate);
		}

		Integer renewCount = (Integer)attributes.get("renewCount");

		if (renewCount != null) {
			setRenewCount(renewCount);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}
	}

	@Override
	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntry.getAccountEntry();
	}

	@Override
	public OrderEntry toEscapedModel() {
		return new OrderEntryWrapper(_orderEntry.toEscapedModel());
	}

	@Override
	public OrderEntry toUnescapedModel() {
		return new OrderEntryWrapper(_orderEntry.toUnescapedModel());
	}

	/**
	* Returns the prorated of this order entry.
	*
	* @return the prorated of this order entry
	*/
	@Override
	public boolean getProrated() {
		return _orderEntry.getProrated();
	}

	/**
	* Returns <code>true</code> if this order entry is approved.
	*
	* @return <code>true</code> if this order entry is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _orderEntry.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _orderEntry.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this order entry is denied.
	*
	* @return <code>true</code> if this order entry is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _orderEntry.isDenied();
	}

	/**
	* Returns <code>true</code> if this order entry is a draft.
	*
	* @return <code>true</code> if this order entry is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _orderEntry.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _orderEntry.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this order entry is expired.
	*
	* @return <code>true</code> if this order entry is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _orderEntry.isExpired();
	}

	/**
	* Returns <code>true</code> if this order entry is inactive.
	*
	* @return <code>true</code> if this order entry is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _orderEntry.isInactive();
	}

	/**
	* Returns <code>true</code> if this order entry is incomplete.
	*
	* @return <code>true</code> if this order entry is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _orderEntry.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _orderEntry.isNew();
	}

	/**
	* Returns <code>true</code> if this order entry is pending.
	*
	* @return <code>true</code> if this order entry is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _orderEntry.isPending();
	}

	/**
	* Returns <code>true</code> if this order entry is prorated.
	*
	* @return <code>true</code> if this order entry is prorated; <code>false</code> otherwise
	*/
	@Override
	public boolean isProrated() {
		return _orderEntry.isProrated();
	}

	/**
	* Returns <code>true</code> if this order entry is scheduled.
	*
	* @return <code>true</code> if this order entry is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _orderEntry.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _orderEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OrderEntry> toCacheModel() {
		return _orderEntry.toCacheModel();
	}

	@Override
	public int compareTo(OrderEntry orderEntry) {
		return _orderEntry.compareTo(orderEntry);
	}

	/**
	* Returns the renew count of this order entry.
	*
	* @return the renew count of this order entry
	*/
	@Override
	public int getRenewCount() {
		return _orderEntry.getRenewCount();
	}

	/**
	* Returns the status of this order entry.
	*
	* @return the status of this order entry
	*/
	@Override
	public int getStatus() {
		return _orderEntry.getStatus();
	}

	@Override
	public int hashCode() {
		return _orderEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _orderEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OrderEntryWrapper((OrderEntry)_orderEntry.clone());
	}

	/**
	* Returns the modified user name of this order entry.
	*
	* @return the modified user name of this order entry
	*/
	@Override
	public java.lang.String getModifiedUserName() {
		return _orderEntry.getModifiedUserName();
	}

	/**
	* Returns the modified user uuid of this order entry.
	*
	* @return the modified user uuid of this order entry
	*/
	@Override
	public java.lang.String getModifiedUserUuid() {
		return _orderEntry.getModifiedUserUuid();
	}

	/**
	* Returns the purchase order key of this order entry.
	*
	* @return the purchase order key of this order entry
	*/
	@Override
	public java.lang.String getPurchaseOrderKey() {
		return _orderEntry.getPurchaseOrderKey();
	}

	@Override
	public java.lang.String getSalesforceOpportunityKey() {
		return _orderEntry.getSalesforceOpportunityKey();
	}

	/**
	* Returns the status by user name of this order entry.
	*
	* @return the status by user name of this order entry
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _orderEntry.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this order entry.
	*
	* @return the status by user uuid of this order entry
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _orderEntry.getStatusByUserUuid();
	}

	@Override
	public java.lang.String getStatusLabel() {
		return _orderEntry.getStatusLabel();
	}

	/**
	* Returns the status message of this order entry.
	*
	* @return the status message of this order entry
	*/
	@Override
	public java.lang.String getStatusMessage() {
		return _orderEntry.getStatusMessage();
	}

	/**
	* Returns the user name of this order entry.
	*
	* @return the user name of this order entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _orderEntry.getUserName();
	}

	/**
	* Returns the user uuid of this order entry.
	*
	* @return the user uuid of this order entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _orderEntry.getUserUuid();
	}

	/**
	* Returns the uuid of this order entry.
	*
	* @return the uuid of this order entry
	*/
	@Override
	public java.lang.String getUuid() {
		return _orderEntry.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _orderEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _orderEntry.toXmlString();
	}

	/**
	* Returns the actual start date of this order entry.
	*
	* @return the actual start date of this order entry
	*/
	@Override
	public Date getActualStartDate() {
		return _orderEntry.getActualStartDate();
	}

	/**
	* Returns the create date of this order entry.
	*
	* @return the create date of this order entry
	*/
	@Override
	public Date getCreateDate() {
		return _orderEntry.getCreateDate();
	}

	/**
	* Returns the modified date of this order entry.
	*
	* @return the modified date of this order entry
	*/
	@Override
	public Date getModifiedDate() {
		return _orderEntry.getModifiedDate();
	}

	/**
	* Returns the start date of this order entry.
	*
	* @return the start date of this order entry
	*/
	@Override
	public Date getStartDate() {
		return _orderEntry.getStartDate();
	}

	/**
	* Returns the status date of this order entry.
	*
	* @return the status date of this order entry
	*/
	@Override
	public Date getStatusDate() {
		return _orderEntry.getStatusDate();
	}

	@Override
	public java.util.List<OfferingEntry> getOfferingEntries() {
		return _orderEntry.getOfferingEntries();
	}

	/**
	* Returns the account entry ID of this order entry.
	*
	* @return the account entry ID of this order entry
	*/
	@Override
	public long getAccountEntryId() {
		return _orderEntry.getAccountEntryId();
	}

	/**
	* Returns the modified user ID of this order entry.
	*
	* @return the modified user ID of this order entry
	*/
	@Override
	public long getModifiedUserId() {
		return _orderEntry.getModifiedUserId();
	}

	/**
	* Returns the order entry ID of this order entry.
	*
	* @return the order entry ID of this order entry
	*/
	@Override
	public long getOrderEntryId() {
		return _orderEntry.getOrderEntryId();
	}

	/**
	* Returns the primary key of this order entry.
	*
	* @return the primary key of this order entry
	*/
	@Override
	public long getPrimaryKey() {
		return _orderEntry.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this order entry.
	*
	* @return the status by user ID of this order entry
	*/
	@Override
	public long getStatusByUserId() {
		return _orderEntry.getStatusByUserId();
	}

	/**
	* Returns the user ID of this order entry.
	*
	* @return the user ID of this order entry
	*/
	@Override
	public long getUserId() {
		return _orderEntry.getUserId();
	}

	@Override
	public void persist() {
		_orderEntry.persist();
	}

	/**
	* Sets the account entry ID of this order entry.
	*
	* @param accountEntryId the account entry ID of this order entry
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_orderEntry.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the actual start date of this order entry.
	*
	* @param actualStartDate the actual start date of this order entry
	*/
	@Override
	public void setActualStartDate(Date actualStartDate) {
		_orderEntry.setActualStartDate(actualStartDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_orderEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this order entry.
	*
	* @param createDate the create date of this order entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_orderEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_orderEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_orderEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_orderEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this order entry.
	*
	* @param modifiedDate the modified date of this order entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_orderEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the modified user ID of this order entry.
	*
	* @param modifiedUserId the modified user ID of this order entry
	*/
	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_orderEntry.setModifiedUserId(modifiedUserId);
	}

	/**
	* Sets the modified user name of this order entry.
	*
	* @param modifiedUserName the modified user name of this order entry
	*/
	@Override
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_orderEntry.setModifiedUserName(modifiedUserName);
	}

	/**
	* Sets the modified user uuid of this order entry.
	*
	* @param modifiedUserUuid the modified user uuid of this order entry
	*/
	@Override
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_orderEntry.setModifiedUserUuid(modifiedUserUuid);
	}

	@Override
	public void setNew(boolean n) {
		_orderEntry.setNew(n);
	}

	@Override
	public void setOfferingEntries(
		java.util.List<OfferingEntry> offeringEntries) {
		_orderEntry.setOfferingEntries(offeringEntries);
	}

	/**
	* Sets the order entry ID of this order entry.
	*
	* @param orderEntryId the order entry ID of this order entry
	*/
	@Override
	public void setOrderEntryId(long orderEntryId) {
		_orderEntry.setOrderEntryId(orderEntryId);
	}

	/**
	* Sets the primary key of this order entry.
	*
	* @param primaryKey the primary key of this order entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_orderEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_orderEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this order entry is prorated.
	*
	* @param prorated the prorated of this order entry
	*/
	@Override
	public void setProrated(boolean prorated) {
		_orderEntry.setProrated(prorated);
	}

	/**
	* Sets the purchase order key of this order entry.
	*
	* @param purchaseOrderKey the purchase order key of this order entry
	*/
	@Override
	public void setPurchaseOrderKey(java.lang.String purchaseOrderKey) {
		_orderEntry.setPurchaseOrderKey(purchaseOrderKey);
	}

	/**
	* Sets the renew count of this order entry.
	*
	* @param renewCount the renew count of this order entry
	*/
	@Override
	public void setRenewCount(int renewCount) {
		_orderEntry.setRenewCount(renewCount);
	}

	/**
	* Sets the start date of this order entry.
	*
	* @param startDate the start date of this order entry
	*/
	@Override
	public void setStartDate(Date startDate) {
		_orderEntry.setStartDate(startDate);
	}

	/**
	* Sets the status of this order entry.
	*
	* @param status the status of this order entry
	*/
	@Override
	public void setStatus(int status) {
		_orderEntry.setStatus(status);
	}

	/**
	* Sets the status by user ID of this order entry.
	*
	* @param statusByUserId the status by user ID of this order entry
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_orderEntry.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this order entry.
	*
	* @param statusByUserName the status by user name of this order entry
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_orderEntry.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this order entry.
	*
	* @param statusByUserUuid the status by user uuid of this order entry
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_orderEntry.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this order entry.
	*
	* @param statusDate the status date of this order entry
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_orderEntry.setStatusDate(statusDate);
	}

	/**
	* Sets the status message of this order entry.
	*
	* @param statusMessage the status message of this order entry
	*/
	@Override
	public void setStatusMessage(java.lang.String statusMessage) {
		_orderEntry.setStatusMessage(statusMessage);
	}

	/**
	* Sets the user ID of this order entry.
	*
	* @param userId the user ID of this order entry
	*/
	@Override
	public void setUserId(long userId) {
		_orderEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this order entry.
	*
	* @param userName the user name of this order entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_orderEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this order entry.
	*
	* @param userUuid the user uuid of this order entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_orderEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this order entry.
	*
	* @param uuid the uuid of this order entry
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_orderEntry.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OrderEntryWrapper)) {
			return false;
		}

		OrderEntryWrapper orderEntryWrapper = (OrderEntryWrapper)obj;

		if (Objects.equals(_orderEntry, orderEntryWrapper._orderEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public OrderEntry getWrappedModel() {
		return _orderEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _orderEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _orderEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_orderEntry.resetOriginalValues();
	}

	private final OrderEntry _orderEntry;
}
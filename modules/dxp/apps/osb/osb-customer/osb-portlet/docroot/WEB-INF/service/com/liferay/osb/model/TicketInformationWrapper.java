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
 * This class is a wrapper for {@link TicketInformation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformation
 * @generated
 */
@ProviderType
public class TicketInformationWrapper implements TicketInformation,
	ModelWrapper<TicketInformation> {
	public TicketInformationWrapper(TicketInformation ticketInformation) {
		_ticketInformation = ticketInformation;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketInformation.class;
	}

	@Override
	public String getModelClassName() {
		return TicketInformation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketInformationId", getTicketInformationId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("fieldId", getFieldId());
		attributes.put("data", getData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketInformationId = (Long)attributes.get("ticketInformationId");

		if (ticketInformationId != null) {
			setTicketInformationId(ticketInformationId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long fieldId = (Long)attributes.get("fieldId");

		if (fieldId != null) {
			setFieldId(fieldId);
		}

		String data = (String)attributes.get("data");

		if (data != null) {
			setData(data);
		}
	}

	@Override
	public TicketInformation toEscapedModel() {
		return new TicketInformationWrapper(_ticketInformation.toEscapedModel());
	}

	@Override
	public TicketInformation toUnescapedModel() {
		return new TicketInformationWrapper(_ticketInformation.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _ticketInformation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketInformation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketInformation.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketInformation.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketInformation> toCacheModel() {
		return _ticketInformation.toCacheModel();
	}

	@Override
	public int compareTo(TicketInformation ticketInformation) {
		return _ticketInformation.compareTo(ticketInformation);
	}

	@Override
	public int hashCode() {
		return _ticketInformation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketInformation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketInformationWrapper((TicketInformation)_ticketInformation.clone());
	}

	/**
	* Returns the data of this ticket information.
	*
	* @return the data of this ticket information
	*/
	@Override
	public java.lang.String getData() {
		return _ticketInformation.getData();
	}

	@Override
	public java.lang.String toString() {
		return _ticketInformation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketInformation.toXmlString();
	}

	/**
	* Returns the create date of this ticket information.
	*
	* @return the create date of this ticket information
	*/
	@Override
	public Date getCreateDate() {
		return _ticketInformation.getCreateDate();
	}

	/**
	* Returns the modified date of this ticket information.
	*
	* @return the modified date of this ticket information
	*/
	@Override
	public Date getModifiedDate() {
		return _ticketInformation.getModifiedDate();
	}

	/**
	* Returns the field ID of this ticket information.
	*
	* @return the field ID of this ticket information
	*/
	@Override
	public long getFieldId() {
		return _ticketInformation.getFieldId();
	}

	/**
	* Returns the primary key of this ticket information.
	*
	* @return the primary key of this ticket information
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketInformation.getPrimaryKey();
	}

	/**
	* Returns the ticket entry ID of this ticket information.
	*
	* @return the ticket entry ID of this ticket information
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketInformation.getTicketEntryId();
	}

	/**
	* Returns the ticket information ID of this ticket information.
	*
	* @return the ticket information ID of this ticket information
	*/
	@Override
	public long getTicketInformationId() {
		return _ticketInformation.getTicketInformationId();
	}

	@Override
	public void persist() {
		_ticketInformation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketInformation.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this ticket information.
	*
	* @param createDate the create date of this ticket information
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketInformation.setCreateDate(createDate);
	}

	/**
	* Sets the data of this ticket information.
	*
	* @param data the data of this ticket information
	*/
	@Override
	public void setData(java.lang.String data) {
		_ticketInformation.setData(data);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketInformation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketInformation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketInformation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field ID of this ticket information.
	*
	* @param fieldId the field ID of this ticket information
	*/
	@Override
	public void setFieldId(long fieldId) {
		_ticketInformation.setFieldId(fieldId);
	}

	/**
	* Sets the modified date of this ticket information.
	*
	* @param modifiedDate the modified date of this ticket information
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ticketInformation.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ticketInformation.setNew(n);
	}

	/**
	* Sets the primary key of this ticket information.
	*
	* @param primaryKey the primary key of this ticket information
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketInformation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketInformation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ticket entry ID of this ticket information.
	*
	* @param ticketEntryId the ticket entry ID of this ticket information
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketInformation.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket information ID of this ticket information.
	*
	* @param ticketInformationId the ticket information ID of this ticket information
	*/
	@Override
	public void setTicketInformationId(long ticketInformationId) {
		_ticketInformation.setTicketInformationId(ticketInformationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketInformationWrapper)) {
			return false;
		}

		TicketInformationWrapper ticketInformationWrapper = (TicketInformationWrapper)obj;

		if (Objects.equals(_ticketInformation,
					ticketInformationWrapper._ticketInformation)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketInformation getWrappedModel() {
		return _ticketInformation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketInformation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketInformation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketInformation.resetOriginalValues();
	}

	private final TicketInformation _ticketInformation;
}
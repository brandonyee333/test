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
 * This class is a wrapper for {@link TicketInformation}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketInformation
 * @generated
 */
public class TicketInformationWrapper implements TicketInformation,
	ModelWrapper<TicketInformation> {
	public TicketInformationWrapper(TicketInformation ticketInformation) {
		_ticketInformation = ticketInformation;
	}

	public Class<?> getModelClass() {
		return TicketInformation.class;
	}

	public String getModelClassName() {
		return TicketInformation.class.getName();
	}

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

	/**
	* Returns the primary key of this ticket information.
	*
	* @return the primary key of this ticket information
	*/
	public long getPrimaryKey() {
		return _ticketInformation.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket information.
	*
	* @param primaryKey the primary key of this ticket information
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketInformation.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket information ID of this ticket information.
	*
	* @return the ticket information ID of this ticket information
	*/
	public long getTicketInformationId() {
		return _ticketInformation.getTicketInformationId();
	}

	/**
	* Sets the ticket information ID of this ticket information.
	*
	* @param ticketInformationId the ticket information ID of this ticket information
	*/
	public void setTicketInformationId(long ticketInformationId) {
		_ticketInformation.setTicketInformationId(ticketInformationId);
	}

	/**
	* Returns the create date of this ticket information.
	*
	* @return the create date of this ticket information
	*/
	public java.util.Date getCreateDate() {
		return _ticketInformation.getCreateDate();
	}

	/**
	* Sets the create date of this ticket information.
	*
	* @param createDate the create date of this ticket information
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticketInformation.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this ticket information.
	*
	* @return the modified date of this ticket information
	*/
	public java.util.Date getModifiedDate() {
		return _ticketInformation.getModifiedDate();
	}

	/**
	* Sets the modified date of this ticket information.
	*
	* @param modifiedDate the modified date of this ticket information
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_ticketInformation.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the ticket entry ID of this ticket information.
	*
	* @return the ticket entry ID of this ticket information
	*/
	public long getTicketEntryId() {
		return _ticketInformation.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket information.
	*
	* @param ticketEntryId the ticket entry ID of this ticket information
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketInformation.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the field ID of this ticket information.
	*
	* @return the field ID of this ticket information
	*/
	public long getFieldId() {
		return _ticketInformation.getFieldId();
	}

	/**
	* Sets the field ID of this ticket information.
	*
	* @param fieldId the field ID of this ticket information
	*/
	public void setFieldId(long fieldId) {
		_ticketInformation.setFieldId(fieldId);
	}

	/**
	* Returns the data of this ticket information.
	*
	* @return the data of this ticket information
	*/
	public java.lang.String getData() {
		return _ticketInformation.getData();
	}

	/**
	* Sets the data of this ticket information.
	*
	* @param data the data of this ticket information
	*/
	public void setData(java.lang.String data) {
		_ticketInformation.setData(data);
	}

	public boolean isNew() {
		return _ticketInformation.isNew();
	}

	public void setNew(boolean n) {
		_ticketInformation.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketInformation.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketInformation.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketInformation.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketInformation.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketInformation.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketInformation.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketInformation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketInformationWrapper((TicketInformation)_ticketInformation.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TicketInformation ticketInformation) {
		return _ticketInformation.compareTo(ticketInformation);
	}

	@Override
	public int hashCode() {
		return _ticketInformation.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketInformation> toCacheModel() {
		return _ticketInformation.toCacheModel();
	}

	public com.liferay.osb.model.TicketInformation toEscapedModel() {
		return new TicketInformationWrapper(_ticketInformation.toEscapedModel());
	}

	public com.liferay.osb.model.TicketInformation toUnescapedModel() {
		return new TicketInformationWrapper(_ticketInformation.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketInformation.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketInformation.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketInformation.persist();
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

		if (Validator.equals(_ticketInformation,
					ticketInformationWrapper._ticketInformation)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketInformation getWrappedTicketInformation() {
		return _ticketInformation;
	}

	public TicketInformation getWrappedModel() {
		return _ticketInformation;
	}

	public void resetOriginalValues() {
		_ticketInformation.resetOriginalValues();
	}

	private TicketInformation _ticketInformation;
}
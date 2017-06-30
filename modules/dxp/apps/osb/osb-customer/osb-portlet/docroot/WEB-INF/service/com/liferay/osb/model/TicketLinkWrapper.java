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
 * This class is a wrapper for {@link TicketLink}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketLink
 * @generated
 */
public class TicketLinkWrapper implements TicketLink, ModelWrapper<TicketLink> {
	public TicketLinkWrapper(TicketLink ticketLink) {
		_ticketLink = ticketLink;
	}

	public Class<?> getModelClass() {
		return TicketLink.class;
	}

	public String getModelClassName() {
		return TicketLink.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketLinkId", getTicketLinkId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("ticketSolutionId", getTicketSolutionId());
		attributes.put("url", getUrl());
		attributes.put("type", getType());
		attributes.put("visibility", getVisibility());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketLinkId = (Long)attributes.get("ticketLinkId");

		if (ticketLinkId != null) {
			setTicketLinkId(ticketLinkId);
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

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long ticketSolutionId = (Long)attributes.get("ticketSolutionId");

		if (ticketSolutionId != null) {
			setTicketSolutionId(ticketSolutionId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}
	}

	/**
	* Returns the primary key of this ticket link.
	*
	* @return the primary key of this ticket link
	*/
	public long getPrimaryKey() {
		return _ticketLink.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket link.
	*
	* @param primaryKey the primary key of this ticket link
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketLink.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket link ID of this ticket link.
	*
	* @return the ticket link ID of this ticket link
	*/
	public long getTicketLinkId() {
		return _ticketLink.getTicketLinkId();
	}

	/**
	* Sets the ticket link ID of this ticket link.
	*
	* @param ticketLinkId the ticket link ID of this ticket link
	*/
	public void setTicketLinkId(long ticketLinkId) {
		_ticketLink.setTicketLinkId(ticketLinkId);
	}

	/**
	* Returns the user ID of this ticket link.
	*
	* @return the user ID of this ticket link
	*/
	public long getUserId() {
		return _ticketLink.getUserId();
	}

	/**
	* Sets the user ID of this ticket link.
	*
	* @param userId the user ID of this ticket link
	*/
	public void setUserId(long userId) {
		_ticketLink.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket link.
	*
	* @return the user uuid of this ticket link
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketLink.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket link.
	*
	* @param userUuid the user uuid of this ticket link
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketLink.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this ticket link.
	*
	* @return the user name of this ticket link
	*/
	public java.lang.String getUserName() {
		return _ticketLink.getUserName();
	}

	/**
	* Sets the user name of this ticket link.
	*
	* @param userName the user name of this ticket link
	*/
	public void setUserName(java.lang.String userName) {
		_ticketLink.setUserName(userName);
	}

	/**
	* Returns the create date of this ticket link.
	*
	* @return the create date of this ticket link
	*/
	public java.util.Date getCreateDate() {
		return _ticketLink.getCreateDate();
	}

	/**
	* Sets the create date of this ticket link.
	*
	* @param createDate the create date of this ticket link
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticketLink.setCreateDate(createDate);
	}

	/**
	* Returns the ticket entry ID of this ticket link.
	*
	* @return the ticket entry ID of this ticket link
	*/
	public long getTicketEntryId() {
		return _ticketLink.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket link.
	*
	* @param ticketEntryId the ticket entry ID of this ticket link
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketLink.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the ticket solution ID of this ticket link.
	*
	* @return the ticket solution ID of this ticket link
	*/
	public long getTicketSolutionId() {
		return _ticketLink.getTicketSolutionId();
	}

	/**
	* Sets the ticket solution ID of this ticket link.
	*
	* @param ticketSolutionId the ticket solution ID of this ticket link
	*/
	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketLink.setTicketSolutionId(ticketSolutionId);
	}

	/**
	* Returns the url of this ticket link.
	*
	* @return the url of this ticket link
	*/
	public java.lang.String getUrl() {
		return _ticketLink.getUrl();
	}

	/**
	* Sets the url of this ticket link.
	*
	* @param url the url of this ticket link
	*/
	public void setUrl(java.lang.String url) {
		_ticketLink.setUrl(url);
	}

	/**
	* Returns the type of this ticket link.
	*
	* @return the type of this ticket link
	*/
	public int getType() {
		return _ticketLink.getType();
	}

	/**
	* Sets the type of this ticket link.
	*
	* @param type the type of this ticket link
	*/
	public void setType(int type) {
		_ticketLink.setType(type);
	}

	/**
	* Returns the visibility of this ticket link.
	*
	* @return the visibility of this ticket link
	*/
	public int getVisibility() {
		return _ticketLink.getVisibility();
	}

	/**
	* Sets the visibility of this ticket link.
	*
	* @param visibility the visibility of this ticket link
	*/
	public void setVisibility(int visibility) {
		_ticketLink.setVisibility(visibility);
	}

	public boolean isNew() {
		return _ticketLink.isNew();
	}

	public void setNew(boolean n) {
		_ticketLink.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketLink.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketLink.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketLink.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketLink.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketLink.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketLink.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketLinkWrapper((TicketLink)_ticketLink.clone());
	}

	public int compareTo(com.liferay.osb.model.TicketLink ticketLink) {
		return _ticketLink.compareTo(ticketLink);
	}

	@Override
	public int hashCode() {
		return _ticketLink.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketLink> toCacheModel() {
		return _ticketLink.toCacheModel();
	}

	public com.liferay.osb.model.TicketLink toEscapedModel() {
		return new TicketLinkWrapper(_ticketLink.toEscapedModel());
	}

	public com.liferay.osb.model.TicketLink toUnescapedModel() {
		return new TicketLinkWrapper(_ticketLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketLink.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketLink.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketLink.persist();
	}

	public java.lang.String getKey() {
		return _ticketLink.getKey();
	}

	public com.liferay.osb.model.TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketLink.getTicketEntry();
	}

	public java.lang.String getVisibilityLabel() {
		return _ticketLink.getVisibilityLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketLinkWrapper)) {
			return false;
		}

		TicketLinkWrapper ticketLinkWrapper = (TicketLinkWrapper)obj;

		if (Validator.equals(_ticketLink, ticketLinkWrapper._ticketLink)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketLink getWrappedTicketLink() {
		return _ticketLink;
	}

	public TicketLink getWrappedModel() {
		return _ticketLink;
	}

	public void resetOriginalValues() {
		_ticketLink.resetOriginalValues();
	}

	private TicketLink _ticketLink;
}
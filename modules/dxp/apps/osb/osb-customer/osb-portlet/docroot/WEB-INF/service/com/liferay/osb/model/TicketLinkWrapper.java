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
 * This class is a wrapper for {@link TicketLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketLink
 * @generated
 */
@ProviderType
public class TicketLinkWrapper implements TicketLink, ModelWrapper<TicketLink> {
	public TicketLinkWrapper(TicketLink ticketLink) {
		_ticketLink = ticketLink;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketLink.class;
	}

	@Override
	public String getModelClassName() {
		return TicketLink.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketLink.getTicketEntry();
	}

	@Override
	public TicketLink toEscapedModel() {
		return new TicketLinkWrapper(_ticketLink.toEscapedModel());
	}

	@Override
	public TicketLink toUnescapedModel() {
		return new TicketLinkWrapper(_ticketLink.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _ticketLink.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketLink.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketLink.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketLink.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketLink> toCacheModel() {
		return _ticketLink.toCacheModel();
	}

	@Override
	public int compareTo(TicketLink ticketLink) {
		return _ticketLink.compareTo(ticketLink);
	}

	/**
	* Returns the type of this ticket link.
	*
	* @return the type of this ticket link
	*/
	@Override
	public int getType() {
		return _ticketLink.getType();
	}

	/**
	* Returns the visibility of this ticket link.
	*
	* @return the visibility of this ticket link
	*/
	@Override
	public int getVisibility() {
		return _ticketLink.getVisibility();
	}

	@Override
	public int hashCode() {
		return _ticketLink.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketLink.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketLinkWrapper((TicketLink)_ticketLink.clone());
	}

	@Override
	public java.lang.String getKey() {
		return _ticketLink.getKey();
	}

	/**
	* Returns the url of this ticket link.
	*
	* @return the url of this ticket link
	*/
	@Override
	public java.lang.String getUrl() {
		return _ticketLink.getUrl();
	}

	/**
	* Returns the user name of this ticket link.
	*
	* @return the user name of this ticket link
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketLink.getUserName();
	}

	/**
	* Returns the user uuid of this ticket link.
	*
	* @return the user uuid of this ticket link
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketLink.getUserUuid();
	}

	@Override
	public java.lang.String getVisibilityLabel() {
		return _ticketLink.getVisibilityLabel();
	}

	@Override
	public java.lang.String toString() {
		return _ticketLink.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketLink.toXmlString();
	}

	/**
	* Returns the create date of this ticket link.
	*
	* @return the create date of this ticket link
	*/
	@Override
	public Date getCreateDate() {
		return _ticketLink.getCreateDate();
	}

	/**
	* Returns the primary key of this ticket link.
	*
	* @return the primary key of this ticket link
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketLink.getPrimaryKey();
	}

	/**
	* Returns the ticket entry ID of this ticket link.
	*
	* @return the ticket entry ID of this ticket link
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketLink.getTicketEntryId();
	}

	/**
	* Returns the ticket link ID of this ticket link.
	*
	* @return the ticket link ID of this ticket link
	*/
	@Override
	public long getTicketLinkId() {
		return _ticketLink.getTicketLinkId();
	}

	/**
	* Returns the ticket solution ID of this ticket link.
	*
	* @return the ticket solution ID of this ticket link
	*/
	@Override
	public long getTicketSolutionId() {
		return _ticketLink.getTicketSolutionId();
	}

	/**
	* Returns the user ID of this ticket link.
	*
	* @return the user ID of this ticket link
	*/
	@Override
	public long getUserId() {
		return _ticketLink.getUserId();
	}

	@Override
	public void persist() {
		_ticketLink.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketLink.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this ticket link.
	*
	* @param createDate the create date of this ticket link
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketLink.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_ticketLink.setNew(n);
	}

	/**
	* Sets the primary key of this ticket link.
	*
	* @param primaryKey the primary key of this ticket link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketLink.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketLink.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ticket entry ID of this ticket link.
	*
	* @param ticketEntryId the ticket entry ID of this ticket link
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketLink.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket link ID of this ticket link.
	*
	* @param ticketLinkId the ticket link ID of this ticket link
	*/
	@Override
	public void setTicketLinkId(long ticketLinkId) {
		_ticketLink.setTicketLinkId(ticketLinkId);
	}

	/**
	* Sets the ticket solution ID of this ticket link.
	*
	* @param ticketSolutionId the ticket solution ID of this ticket link
	*/
	@Override
	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketLink.setTicketSolutionId(ticketSolutionId);
	}

	/**
	* Sets the type of this ticket link.
	*
	* @param type the type of this ticket link
	*/
	@Override
	public void setType(int type) {
		_ticketLink.setType(type);
	}

	/**
	* Sets the url of this ticket link.
	*
	* @param url the url of this ticket link
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_ticketLink.setUrl(url);
	}

	/**
	* Sets the user ID of this ticket link.
	*
	* @param userId the user ID of this ticket link
	*/
	@Override
	public void setUserId(long userId) {
		_ticketLink.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket link.
	*
	* @param userName the user name of this ticket link
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketLink.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket link.
	*
	* @param userUuid the user uuid of this ticket link
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketLink.setUserUuid(userUuid);
	}

	/**
	* Sets the visibility of this ticket link.
	*
	* @param visibility the visibility of this ticket link
	*/
	@Override
	public void setVisibility(int visibility) {
		_ticketLink.setVisibility(visibility);
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

		if (Objects.equals(_ticketLink, ticketLinkWrapper._ticketLink)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketLink getWrappedModel() {
		return _ticketLink;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketLink.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketLink.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketLink.resetOriginalValues();
	}

	private final TicketLink _ticketLink;
}
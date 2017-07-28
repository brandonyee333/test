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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link TicketWorker}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorker
 * @generated
 */
@ProviderType
public class TicketWorkerWrapper implements TicketWorker,
	ModelWrapper<TicketWorker> {
	public TicketWorkerWrapper(TicketWorker ticketWorker) {
		_ticketWorker = ticketWorker;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketWorker.class;
	}

	@Override
	public String getModelClassName() {
		return TicketWorker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketWorkerId", getTicketWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("sourceClassNameId", getSourceClassNameId());
		attributes.put("sourceClassPK", getSourceClassPK());
		attributes.put("role", getRole());
		attributes.put("primary", getPrimary());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketWorkerId = (Long)attributes.get("ticketWorkerId");

		if (ticketWorkerId != null) {
			setTicketWorkerId(ticketWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long sourceClassNameId = (Long)attributes.get("sourceClassNameId");

		if (sourceClassNameId != null) {
			setSourceClassNameId(sourceClassNameId);
		}

		Long sourceClassPK = (Long)attributes.get("sourceClassPK");

		if (sourceClassPK != null) {
			setSourceClassPK(sourceClassPK);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}

		Boolean primary = (Boolean)attributes.get("primary");

		if (primary != null) {
			setPrimary(primary);
		}
	}

	@Override
	public TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketWorker.getTicketEntry();
	}

	@Override
	public TicketWorker toEscapedModel() {
		return new TicketWorkerWrapper(_ticketWorker.toEscapedModel());
	}

	@Override
	public TicketWorker toUnescapedModel() {
		return new TicketWorkerWrapper(_ticketWorker.toUnescapedModel());
	}

	/**
	* Returns the primary of this ticket worker.
	*
	* @return the primary of this ticket worker
	*/
	@Override
	public boolean getPrimary() {
		return _ticketWorker.getPrimary();
	}

	@Override
	public boolean isCachedModel() {
		return _ticketWorker.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketWorker.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketWorker.isNew();
	}

	/**
	* Returns <code>true</code> if this ticket worker is primary.
	*
	* @return <code>true</code> if this ticket worker is primary; <code>false</code> otherwise
	*/
	@Override
	public boolean isPrimary() {
		return _ticketWorker.isPrimary();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketWorker.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketWorker> toCacheModel() {
		return _ticketWorker.toCacheModel();
	}

	@Override
	public int compareTo(TicketWorker ticketWorker) {
		return _ticketWorker.compareTo(ticketWorker);
	}

	/**
	* Returns the role of this ticket worker.
	*
	* @return the role of this ticket worker
	*/
	@Override
	public int getRole() {
		return _ticketWorker.getRole();
	}

	@Override
	public int hashCode() {
		return _ticketWorker.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketWorker.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketWorkerWrapper((TicketWorker)_ticketWorker.clone());
	}

	@Override
	public java.lang.String getRoleLabel() {
		return _ticketWorker.getRoleLabel();
	}

	/**
	* Returns the user uuid of this ticket worker.
	*
	* @return the user uuid of this ticket worker
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketWorker.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ticketWorker.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketWorker.toXmlString();
	}

	/**
	* Returns the primary key of this ticket worker.
	*
	* @return the primary key of this ticket worker
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketWorker.getPrimaryKey();
	}

	/**
	* Returns the source class name ID of this ticket worker.
	*
	* @return the source class name ID of this ticket worker
	*/
	@Override
	public long getSourceClassNameId() {
		return _ticketWorker.getSourceClassNameId();
	}

	/**
	* Returns the source class pk of this ticket worker.
	*
	* @return the source class pk of this ticket worker
	*/
	@Override
	public long getSourceClassPK() {
		return _ticketWorker.getSourceClassPK();
	}

	/**
	* Returns the ticket entry ID of this ticket worker.
	*
	* @return the ticket entry ID of this ticket worker
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketWorker.getTicketEntryId();
	}

	/**
	* Returns the ticket worker ID of this ticket worker.
	*
	* @return the ticket worker ID of this ticket worker
	*/
	@Override
	public long getTicketWorkerId() {
		return _ticketWorker.getTicketWorkerId();
	}

	/**
	* Returns the user ID of this ticket worker.
	*
	* @return the user ID of this ticket worker
	*/
	@Override
	public long getUserId() {
		return _ticketWorker.getUserId();
	}

	@Override
	public void persist() {
		_ticketWorker.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketWorker.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketWorker.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketWorker.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_ticketWorker.setNew(n);
	}

	/**
	* Sets whether this ticket worker is primary.
	*
	* @param primary the primary of this ticket worker
	*/
	@Override
	public void setPrimary(boolean primary) {
		_ticketWorker.setPrimary(primary);
	}

	/**
	* Sets the primary key of this ticket worker.
	*
	* @param primaryKey the primary key of this ticket worker
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketWorker.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role of this ticket worker.
	*
	* @param role the role of this ticket worker
	*/
	@Override
	public void setRole(int role) {
		_ticketWorker.setRole(role);
	}

	/**
	* Sets the source class name ID of this ticket worker.
	*
	* @param sourceClassNameId the source class name ID of this ticket worker
	*/
	@Override
	public void setSourceClassNameId(long sourceClassNameId) {
		_ticketWorker.setSourceClassNameId(sourceClassNameId);
	}

	/**
	* Sets the source class pk of this ticket worker.
	*
	* @param sourceClassPK the source class pk of this ticket worker
	*/
	@Override
	public void setSourceClassPK(long sourceClassPK) {
		_ticketWorker.setSourceClassPK(sourceClassPK);
	}

	/**
	* Sets the ticket entry ID of this ticket worker.
	*
	* @param ticketEntryId the ticket entry ID of this ticket worker
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketWorker.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket worker ID of this ticket worker.
	*
	* @param ticketWorkerId the ticket worker ID of this ticket worker
	*/
	@Override
	public void setTicketWorkerId(long ticketWorkerId) {
		_ticketWorker.setTicketWorkerId(ticketWorkerId);
	}

	/**
	* Sets the user ID of this ticket worker.
	*
	* @param userId the user ID of this ticket worker
	*/
	@Override
	public void setUserId(long userId) {
		_ticketWorker.setUserId(userId);
	}

	/**
	* Sets the user uuid of this ticket worker.
	*
	* @param userUuid the user uuid of this ticket worker
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketWorker.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketWorkerWrapper)) {
			return false;
		}

		TicketWorkerWrapper ticketWorkerWrapper = (TicketWorkerWrapper)obj;

		if (Objects.equals(_ticketWorker, ticketWorkerWrapper._ticketWorker)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketWorker getWrappedModel() {
		return _ticketWorker;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketWorker.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketWorker.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketWorker.resetOriginalValues();
	}

	private final TicketWorker _ticketWorker;
}
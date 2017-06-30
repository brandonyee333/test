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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TicketWorker}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketWorker
 * @generated
 */
public class TicketWorkerWrapper implements TicketWorker,
	ModelWrapper<TicketWorker> {
	public TicketWorkerWrapper(TicketWorker ticketWorker) {
		_ticketWorker = ticketWorker;
	}

	public Class<?> getModelClass() {
		return TicketWorker.class;
	}

	public String getModelClassName() {
		return TicketWorker.class.getName();
	}

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

	/**
	* Returns the primary key of this ticket worker.
	*
	* @return the primary key of this ticket worker
	*/
	public long getPrimaryKey() {
		return _ticketWorker.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket worker.
	*
	* @param primaryKey the primary key of this ticket worker
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticketWorker.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket worker ID of this ticket worker.
	*
	* @return the ticket worker ID of this ticket worker
	*/
	public long getTicketWorkerId() {
		return _ticketWorker.getTicketWorkerId();
	}

	/**
	* Sets the ticket worker ID of this ticket worker.
	*
	* @param ticketWorkerId the ticket worker ID of this ticket worker
	*/
	public void setTicketWorkerId(long ticketWorkerId) {
		_ticketWorker.setTicketWorkerId(ticketWorkerId);
	}

	/**
	* Returns the user ID of this ticket worker.
	*
	* @return the user ID of this ticket worker
	*/
	public long getUserId() {
		return _ticketWorker.getUserId();
	}

	/**
	* Sets the user ID of this ticket worker.
	*
	* @param userId the user ID of this ticket worker
	*/
	public void setUserId(long userId) {
		_ticketWorker.setUserId(userId);
	}

	/**
	* Returns the user uuid of this ticket worker.
	*
	* @return the user uuid of this ticket worker
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ticketWorker.getUserUuid();
	}

	/**
	* Sets the user uuid of this ticket worker.
	*
	* @param userUuid the user uuid of this ticket worker
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ticketWorker.setUserUuid(userUuid);
	}

	/**
	* Returns the ticket entry ID of this ticket worker.
	*
	* @return the ticket entry ID of this ticket worker
	*/
	public long getTicketEntryId() {
		return _ticketWorker.getTicketEntryId();
	}

	/**
	* Sets the ticket entry ID of this ticket worker.
	*
	* @param ticketEntryId the ticket entry ID of this ticket worker
	*/
	public void setTicketEntryId(long ticketEntryId) {
		_ticketWorker.setTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the source class name ID of this ticket worker.
	*
	* @return the source class name ID of this ticket worker
	*/
	public long getSourceClassNameId() {
		return _ticketWorker.getSourceClassNameId();
	}

	/**
	* Sets the source class name ID of this ticket worker.
	*
	* @param sourceClassNameId the source class name ID of this ticket worker
	*/
	public void setSourceClassNameId(long sourceClassNameId) {
		_ticketWorker.setSourceClassNameId(sourceClassNameId);
	}

	/**
	* Returns the source class p k of this ticket worker.
	*
	* @return the source class p k of this ticket worker
	*/
	public long getSourceClassPK() {
		return _ticketWorker.getSourceClassPK();
	}

	/**
	* Sets the source class p k of this ticket worker.
	*
	* @param sourceClassPK the source class p k of this ticket worker
	*/
	public void setSourceClassPK(long sourceClassPK) {
		_ticketWorker.setSourceClassPK(sourceClassPK);
	}

	/**
	* Returns the role of this ticket worker.
	*
	* @return the role of this ticket worker
	*/
	public int getRole() {
		return _ticketWorker.getRole();
	}

	/**
	* Sets the role of this ticket worker.
	*
	* @param role the role of this ticket worker
	*/
	public void setRole(int role) {
		_ticketWorker.setRole(role);
	}

	/**
	* Returns the primary of this ticket worker.
	*
	* @return the primary of this ticket worker
	*/
	public boolean getPrimary() {
		return _ticketWorker.getPrimary();
	}

	/**
	* Returns <code>true</code> if this ticket worker is primary.
	*
	* @return <code>true</code> if this ticket worker is primary; <code>false</code> otherwise
	*/
	public boolean isPrimary() {
		return _ticketWorker.isPrimary();
	}

	/**
	* Sets whether this ticket worker is primary.
	*
	* @param primary the primary of this ticket worker
	*/
	public void setPrimary(boolean primary) {
		_ticketWorker.setPrimary(primary);
	}

	public boolean isNew() {
		return _ticketWorker.isNew();
	}

	public void setNew(boolean n) {
		_ticketWorker.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticketWorker.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticketWorker.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticketWorker.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticketWorker.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticketWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticketWorker.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticketWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketWorkerWrapper((TicketWorker)_ticketWorker.clone());
	}

	public int compareTo(com.liferay.osb.model.TicketWorker ticketWorker) {
		return _ticketWorker.compareTo(ticketWorker);
	}

	@Override
	public int hashCode() {
		return _ticketWorker.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TicketWorker> toCacheModel() {
		return _ticketWorker.toCacheModel();
	}

	public com.liferay.osb.model.TicketWorker toEscapedModel() {
		return new TicketWorkerWrapper(_ticketWorker.toEscapedModel());
	}

	public com.liferay.osb.model.TicketWorker toUnescapedModel() {
		return new TicketWorkerWrapper(_ticketWorker.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticketWorker.toString();
	}

	public java.lang.String toXmlString() {
		return _ticketWorker.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticketWorker.persist();
	}

	public java.lang.String getRoleLabel() {
		return _ticketWorker.getRoleLabel();
	}

	public com.liferay.osb.model.TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketWorker.getTicketEntry();
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

		if (Validator.equals(_ticketWorker, ticketWorkerWrapper._ticketWorker)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TicketWorker getWrappedTicketWorker() {
		return _ticketWorker;
	}

	public TicketWorker getWrappedModel() {
		return _ticketWorker;
	}

	public void resetOriginalValues() {
		_ticketWorker.resetOriginalValues();
	}

	private TicketWorker _ticketWorker;
}
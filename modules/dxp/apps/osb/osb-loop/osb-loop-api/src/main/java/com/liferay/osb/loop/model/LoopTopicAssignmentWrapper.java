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

package com.liferay.osb.loop.model;

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
 * This class is a wrapper for {@link LoopTopicAssignment}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopTopicAssignment
 * @generated
 */
public class LoopTopicAssignmentWrapper
	implements LoopTopicAssignment, ModelWrapper<LoopTopicAssignment> {

	public LoopTopicAssignmentWrapper(LoopTopicAssignment loopTopicAssignment) {
		_loopTopicAssignment = loopTopicAssignment;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopTopicAssignment.class;
	}

	@Override
	public String getModelClassName() {
		return LoopTopicAssignment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopTopicAssignmentId", getLoopTopicAssignmentId());
		attributes.put("loopPersonId", getLoopPersonId());
		attributes.put("loopTopicId", getLoopTopicId());
		attributes.put("statusByDate", getStatusByDate());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopTopicAssignmentId = (Long)attributes.get(
			"loopTopicAssignmentId");

		if (loopTopicAssignmentId != null) {
			setLoopTopicAssignmentId(loopTopicAssignmentId);
		}

		Long loopPersonId = (Long)attributes.get("loopPersonId");

		if (loopPersonId != null) {
			setLoopPersonId(loopPersonId);
		}

		Long loopTopicId = (Long)attributes.get("loopTopicId");

		if (loopTopicId != null) {
			setLoopTopicId(loopTopicId);
		}

		Date statusByDate = (Date)attributes.get("statusByDate");

		if (statusByDate != null) {
			setStatusByDate(statusByDate);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new LoopTopicAssignmentWrapper(
			(LoopTopicAssignment)_loopTopicAssignment.clone());
	}

	@Override
	public int compareTo(LoopTopicAssignment loopTopicAssignment) {
		return _loopTopicAssignment.compareTo(loopTopicAssignment);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopTopicAssignment.getExpandoBridge();
	}

	/**
	 * Returns the loop person ID of this loop topic assignment.
	 *
	 * @return the loop person ID of this loop topic assignment
	 */
	@Override
	public long getLoopPersonId() {
		return _loopTopicAssignment.getLoopPersonId();
	}

	/**
	 * Returns the loop topic assignment ID of this loop topic assignment.
	 *
	 * @return the loop topic assignment ID of this loop topic assignment
	 */
	@Override
	public long getLoopTopicAssignmentId() {
		return _loopTopicAssignment.getLoopTopicAssignmentId();
	}

	/**
	 * Returns the loop topic ID of this loop topic assignment.
	 *
	 * @return the loop topic ID of this loop topic assignment
	 */
	@Override
	public long getLoopTopicId() {
		return _loopTopicAssignment.getLoopTopicId();
	}

	/**
	 * Returns the primary key of this loop topic assignment.
	 *
	 * @return the primary key of this loop topic assignment
	 */
	@Override
	public long getPrimaryKey() {
		return _loopTopicAssignment.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopTopicAssignment.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this loop topic assignment.
	 *
	 * @return the status of this loop topic assignment
	 */
	@Override
	public int getStatus() {
		return _loopTopicAssignment.getStatus();
	}

	/**
	 * Returns the status by date of this loop topic assignment.
	 *
	 * @return the status by date of this loop topic assignment
	 */
	@Override
	public Date getStatusByDate() {
		return _loopTopicAssignment.getStatusByDate();
	}

	/**
	 * Returns the status by user ID of this loop topic assignment.
	 *
	 * @return the status by user ID of this loop topic assignment
	 */
	@Override
	public long getStatusByUserId() {
		return _loopTopicAssignment.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this loop topic assignment.
	 *
	 * @return the status by user name of this loop topic assignment
	 */
	@Override
	public String getStatusByUserName() {
		return _loopTopicAssignment.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this loop topic assignment.
	 *
	 * @return the status by user uuid of this loop topic assignment
	 */
	@Override
	public String getStatusByUserUuid() {
		return _loopTopicAssignment.getStatusByUserUuid();
	}

	@Override
	public int hashCode() {
		return _loopTopicAssignment.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopTopicAssignment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopTopicAssignment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopTopicAssignment.isNew();
	}

	@Override
	public void persist() {
		_loopTopicAssignment.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopTopicAssignment.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopTopicAssignment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopTopicAssignment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopTopicAssignment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop person ID of this loop topic assignment.
	 *
	 * @param loopPersonId the loop person ID of this loop topic assignment
	 */
	@Override
	public void setLoopPersonId(long loopPersonId) {
		_loopTopicAssignment.setLoopPersonId(loopPersonId);
	}

	/**
	 * Sets the loop topic assignment ID of this loop topic assignment.
	 *
	 * @param loopTopicAssignmentId the loop topic assignment ID of this loop topic assignment
	 */
	@Override
	public void setLoopTopicAssignmentId(long loopTopicAssignmentId) {
		_loopTopicAssignment.setLoopTopicAssignmentId(loopTopicAssignmentId);
	}

	/**
	 * Sets the loop topic ID of this loop topic assignment.
	 *
	 * @param loopTopicId the loop topic ID of this loop topic assignment
	 */
	@Override
	public void setLoopTopicId(long loopTopicId) {
		_loopTopicAssignment.setLoopTopicId(loopTopicId);
	}

	@Override
	public void setNew(boolean n) {
		_loopTopicAssignment.setNew(n);
	}

	/**
	 * Sets the primary key of this loop topic assignment.
	 *
	 * @param primaryKey the primary key of this loop topic assignment
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopTopicAssignment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopTopicAssignment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this loop topic assignment.
	 *
	 * @param status the status of this loop topic assignment
	 */
	@Override
	public void setStatus(int status) {
		_loopTopicAssignment.setStatus(status);
	}

	/**
	 * Sets the status by date of this loop topic assignment.
	 *
	 * @param statusByDate the status by date of this loop topic assignment
	 */
	@Override
	public void setStatusByDate(Date statusByDate) {
		_loopTopicAssignment.setStatusByDate(statusByDate);
	}

	/**
	 * Sets the status by user ID of this loop topic assignment.
	 *
	 * @param statusByUserId the status by user ID of this loop topic assignment
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_loopTopicAssignment.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this loop topic assignment.
	 *
	 * @param statusByUserName the status by user name of this loop topic assignment
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_loopTopicAssignment.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this loop topic assignment.
	 *
	 * @param statusByUserUuid the status by user uuid of this loop topic assignment
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_loopTopicAssignment.setStatusByUserUuid(statusByUserUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopTopicAssignment>
		toCacheModel() {

		return _loopTopicAssignment.toCacheModel();
	}

	@Override
	public LoopTopicAssignment toEscapedModel() {
		return new LoopTopicAssignmentWrapper(
			_loopTopicAssignment.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopTopicAssignment.toString();
	}

	@Override
	public LoopTopicAssignment toUnescapedModel() {
		return new LoopTopicAssignmentWrapper(
			_loopTopicAssignment.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopTopicAssignment.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopTopicAssignmentWrapper)) {
			return false;
		}

		LoopTopicAssignmentWrapper loopTopicAssignmentWrapper =
			(LoopTopicAssignmentWrapper)obj;

		if (Objects.equals(
				_loopTopicAssignment,
				loopTopicAssignmentWrapper._loopTopicAssignment)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopTopicAssignment getWrappedModel() {
		return _loopTopicAssignment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopTopicAssignment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopTopicAssignment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopTopicAssignment.resetOriginalValues();
	}

	private final LoopTopicAssignment _loopTopicAssignment;

}
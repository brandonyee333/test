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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LoopParticipantAssignment}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopParticipantAssignment
 * @generated
 */
public class LoopParticipantAssignmentWrapper
	implements LoopParticipantAssignment,
			   ModelWrapper<LoopParticipantAssignment> {

	public LoopParticipantAssignmentWrapper(
		LoopParticipantAssignment loopParticipantAssignment) {

		_loopParticipantAssignment = loopParticipantAssignment;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopParticipantAssignment.class;
	}

	@Override
	public String getModelClassName() {
		return LoopParticipantAssignment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put(
			"loopParticipantAssignmentId", getLoopParticipantAssignmentId());
		attributes.put("loopDivisionId", getLoopDivisionId());
		attributes.put("loopPersonId", getLoopPersonId());
		attributes.put("description", getDescription());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopParticipantAssignmentId = (Long)attributes.get(
			"loopParticipantAssignmentId");

		if (loopParticipantAssignmentId != null) {
			setLoopParticipantAssignmentId(loopParticipantAssignmentId);
		}

		Long loopDivisionId = (Long)attributes.get("loopDivisionId");

		if (loopDivisionId != null) {
			setLoopDivisionId(loopDivisionId);
		}

		Long loopPersonId = (Long)attributes.get("loopPersonId");

		if (loopPersonId != null) {
			setLoopPersonId(loopPersonId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public Object clone() {
		return new LoopParticipantAssignmentWrapper(
			(LoopParticipantAssignment)_loopParticipantAssignment.clone());
	}

	@Override
	public int compareTo(LoopParticipantAssignment loopParticipantAssignment) {
		return _loopParticipantAssignment.compareTo(loopParticipantAssignment);
	}

	/**
	 * Returns the description of this loop participant assignment.
	 *
	 * @return the description of this loop participant assignment
	 */
	@Override
	public String getDescription() {
		return _loopParticipantAssignment.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopParticipantAssignment.getExpandoBridge();
	}

	/**
	 * Returns the loop division ID of this loop participant assignment.
	 *
	 * @return the loop division ID of this loop participant assignment
	 */
	@Override
	public long getLoopDivisionId() {
		return _loopParticipantAssignment.getLoopDivisionId();
	}

	/**
	 * Returns the loop participant assignment ID of this loop participant assignment.
	 *
	 * @return the loop participant assignment ID of this loop participant assignment
	 */
	@Override
	public long getLoopParticipantAssignmentId() {
		return _loopParticipantAssignment.getLoopParticipantAssignmentId();
	}

	/**
	 * Returns the loop person ID of this loop participant assignment.
	 *
	 * @return the loop person ID of this loop participant assignment
	 */
	@Override
	public long getLoopPersonId() {
		return _loopParticipantAssignment.getLoopPersonId();
	}

	/**
	 * Returns the primary key of this loop participant assignment.
	 *
	 * @return the primary key of this loop participant assignment
	 */
	@Override
	public long getPrimaryKey() {
		return _loopParticipantAssignment.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopParticipantAssignment.getPrimaryKeyObj();
	}

	/**
	 * Returns the type of this loop participant assignment.
	 *
	 * @return the type of this loop participant assignment
	 */
	@Override
	public int getType() {
		return _loopParticipantAssignment.getType();
	}

	@Override
	public int hashCode() {
		return _loopParticipantAssignment.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopParticipantAssignment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopParticipantAssignment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopParticipantAssignment.isNew();
	}

	@Override
	public void persist() {
		_loopParticipantAssignment.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopParticipantAssignment.setCachedModel(cachedModel);
	}

	/**
	 * Sets the description of this loop participant assignment.
	 *
	 * @param description the description of this loop participant assignment
	 */
	@Override
	public void setDescription(String description) {
		_loopParticipantAssignment.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopParticipantAssignment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopParticipantAssignment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopParticipantAssignment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop division ID of this loop participant assignment.
	 *
	 * @param loopDivisionId the loop division ID of this loop participant assignment
	 */
	@Override
	public void setLoopDivisionId(long loopDivisionId) {
		_loopParticipantAssignment.setLoopDivisionId(loopDivisionId);
	}

	/**
	 * Sets the loop participant assignment ID of this loop participant assignment.
	 *
	 * @param loopParticipantAssignmentId the loop participant assignment ID of this loop participant assignment
	 */
	@Override
	public void setLoopParticipantAssignmentId(
		long loopParticipantAssignmentId) {

		_loopParticipantAssignment.setLoopParticipantAssignmentId(
			loopParticipantAssignmentId);
	}

	/**
	 * Sets the loop person ID of this loop participant assignment.
	 *
	 * @param loopPersonId the loop person ID of this loop participant assignment
	 */
	@Override
	public void setLoopPersonId(long loopPersonId) {
		_loopParticipantAssignment.setLoopPersonId(loopPersonId);
	}

	@Override
	public void setNew(boolean n) {
		_loopParticipantAssignment.setNew(n);
	}

	/**
	 * Sets the primary key of this loop participant assignment.
	 *
	 * @param primaryKey the primary key of this loop participant assignment
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopParticipantAssignment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopParticipantAssignment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the type of this loop participant assignment.
	 *
	 * @param type the type of this loop participant assignment
	 */
	@Override
	public void setType(int type) {
		_loopParticipantAssignment.setType(type);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopParticipantAssignment>
		toCacheModel() {

		return _loopParticipantAssignment.toCacheModel();
	}

	@Override
	public LoopParticipantAssignment toEscapedModel() {
		return new LoopParticipantAssignmentWrapper(
			_loopParticipantAssignment.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopParticipantAssignment.toString();
	}

	@Override
	public LoopParticipantAssignment toUnescapedModel() {
		return new LoopParticipantAssignmentWrapper(
			_loopParticipantAssignment.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopParticipantAssignment.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopParticipantAssignmentWrapper)) {
			return false;
		}

		LoopParticipantAssignmentWrapper loopParticipantAssignmentWrapper =
			(LoopParticipantAssignmentWrapper)obj;

		if (Objects.equals(
				_loopParticipantAssignment,
				loopParticipantAssignmentWrapper._loopParticipantAssignment)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopParticipantAssignment getWrappedModel() {
		return _loopParticipantAssignment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopParticipantAssignment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopParticipantAssignment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopParticipantAssignment.resetOriginalValues();
	}

	private final LoopParticipantAssignment _loopParticipantAssignment;

}
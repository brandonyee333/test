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
 * This class is a wrapper for {@link LoopPersonRel}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPersonRel
 * @generated
 */
public class LoopPersonRelWrapper
	implements LoopPersonRel, ModelWrapper<LoopPersonRel> {

	public LoopPersonRelWrapper(LoopPersonRel loopPersonRel) {
		_loopPersonRel = loopPersonRel;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopPersonRel.class;
	}

	@Override
	public String getModelClassName() {
		return LoopPersonRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopPersonRelId", getLoopPersonRelId());
		attributes.put("childLoopPersonId", getChildLoopPersonId());
		attributes.put("parentLoopPersonId", getParentLoopPersonId());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopPersonRelId = (Long)attributes.get("loopPersonRelId");

		if (loopPersonRelId != null) {
			setLoopPersonRelId(loopPersonRelId);
		}

		Long childLoopPersonId = (Long)attributes.get("childLoopPersonId");

		if (childLoopPersonId != null) {
			setChildLoopPersonId(childLoopPersonId);
		}

		Long parentLoopPersonId = (Long)attributes.get("parentLoopPersonId");

		if (parentLoopPersonId != null) {
			setParentLoopPersonId(parentLoopPersonId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public Object clone() {
		return new LoopPersonRelWrapper((LoopPersonRel)_loopPersonRel.clone());
	}

	@Override
	public int compareTo(LoopPersonRel loopPersonRel) {
		return _loopPersonRel.compareTo(loopPersonRel);
	}

	/**
	 * Returns the child loop person ID of this loop person rel.
	 *
	 * @return the child loop person ID of this loop person rel
	 */
	@Override
	public long getChildLoopPersonId() {
		return _loopPersonRel.getChildLoopPersonId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopPersonRel.getExpandoBridge();
	}

	/**
	 * Returns the loop person rel ID of this loop person rel.
	 *
	 * @return the loop person rel ID of this loop person rel
	 */
	@Override
	public long getLoopPersonRelId() {
		return _loopPersonRel.getLoopPersonRelId();
	}

	/**
	 * Returns the parent loop person ID of this loop person rel.
	 *
	 * @return the parent loop person ID of this loop person rel
	 */
	@Override
	public long getParentLoopPersonId() {
		return _loopPersonRel.getParentLoopPersonId();
	}

	/**
	 * Returns the primary key of this loop person rel.
	 *
	 * @return the primary key of this loop person rel
	 */
	@Override
	public long getPrimaryKey() {
		return _loopPersonRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopPersonRel.getPrimaryKeyObj();
	}

	/**
	 * Returns the type of this loop person rel.
	 *
	 * @return the type of this loop person rel
	 */
	@Override
	public int getType() {
		return _loopPersonRel.getType();
	}

	@Override
	public int hashCode() {
		return _loopPersonRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopPersonRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopPersonRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopPersonRel.isNew();
	}

	@Override
	public void persist() {
		_loopPersonRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopPersonRel.setCachedModel(cachedModel);
	}

	/**
	 * Sets the child loop person ID of this loop person rel.
	 *
	 * @param childLoopPersonId the child loop person ID of this loop person rel
	 */
	@Override
	public void setChildLoopPersonId(long childLoopPersonId) {
		_loopPersonRel.setChildLoopPersonId(childLoopPersonId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopPersonRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopPersonRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopPersonRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop person rel ID of this loop person rel.
	 *
	 * @param loopPersonRelId the loop person rel ID of this loop person rel
	 */
	@Override
	public void setLoopPersonRelId(long loopPersonRelId) {
		_loopPersonRel.setLoopPersonRelId(loopPersonRelId);
	}

	@Override
	public void setNew(boolean n) {
		_loopPersonRel.setNew(n);
	}

	/**
	 * Sets the parent loop person ID of this loop person rel.
	 *
	 * @param parentLoopPersonId the parent loop person ID of this loop person rel
	 */
	@Override
	public void setParentLoopPersonId(long parentLoopPersonId) {
		_loopPersonRel.setParentLoopPersonId(parentLoopPersonId);
	}

	/**
	 * Sets the primary key of this loop person rel.
	 *
	 * @param primaryKey the primary key of this loop person rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopPersonRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopPersonRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the type of this loop person rel.
	 *
	 * @param type the type of this loop person rel
	 */
	@Override
	public void setType(int type) {
		_loopPersonRel.setType(type);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopPersonRel>
		toCacheModel() {

		return _loopPersonRel.toCacheModel();
	}

	@Override
	public LoopPersonRel toEscapedModel() {
		return new LoopPersonRelWrapper(_loopPersonRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopPersonRel.toString();
	}

	@Override
	public LoopPersonRel toUnescapedModel() {
		return new LoopPersonRelWrapper(_loopPersonRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopPersonRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopPersonRelWrapper)) {
			return false;
		}

		LoopPersonRelWrapper loopPersonRelWrapper = (LoopPersonRelWrapper)obj;

		if (Objects.equals(
				_loopPersonRel, loopPersonRelWrapper._loopPersonRel)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopPersonRel getWrappedModel() {
		return _loopPersonRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopPersonRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopPersonRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopPersonRel.resetOriginalValues();
	}

	private final LoopPersonRel _loopPersonRel;

}
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
 * This class is a wrapper for {@link LoopDivisionRel}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopDivisionRel
 * @generated
 */
public class LoopDivisionRelWrapper
	implements LoopDivisionRel, ModelWrapper<LoopDivisionRel> {

	public LoopDivisionRelWrapper(LoopDivisionRel loopDivisionRel) {
		_loopDivisionRel = loopDivisionRel;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopDivisionRel.class;
	}

	@Override
	public String getModelClassName() {
		return LoopDivisionRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopDivisionRelId", getLoopDivisionRelId());
		attributes.put("childLoopDivisionId", getChildLoopDivisionId());
		attributes.put("loopPersonId", getLoopPersonId());
		attributes.put("parentLoopDivisionId", getParentLoopDivisionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopDivisionRelId = (Long)attributes.get("loopDivisionRelId");

		if (loopDivisionRelId != null) {
			setLoopDivisionRelId(loopDivisionRelId);
		}

		Long childLoopDivisionId = (Long)attributes.get("childLoopDivisionId");

		if (childLoopDivisionId != null) {
			setChildLoopDivisionId(childLoopDivisionId);
		}

		Long loopPersonId = (Long)attributes.get("loopPersonId");

		if (loopPersonId != null) {
			setLoopPersonId(loopPersonId);
		}

		Long parentLoopDivisionId = (Long)attributes.get(
			"parentLoopDivisionId");

		if (parentLoopDivisionId != null) {
			setParentLoopDivisionId(parentLoopDivisionId);
		}
	}

	@Override
	public Object clone() {
		return new LoopDivisionRelWrapper(
			(LoopDivisionRel)_loopDivisionRel.clone());
	}

	@Override
	public int compareTo(LoopDivisionRel loopDivisionRel) {
		return _loopDivisionRel.compareTo(loopDivisionRel);
	}

	/**
	 * Returns the child loop division ID of this loop division rel.
	 *
	 * @return the child loop division ID of this loop division rel
	 */
	@Override
	public long getChildLoopDivisionId() {
		return _loopDivisionRel.getChildLoopDivisionId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopDivisionRel.getExpandoBridge();
	}

	/**
	 * Returns the loop division rel ID of this loop division rel.
	 *
	 * @return the loop division rel ID of this loop division rel
	 */
	@Override
	public long getLoopDivisionRelId() {
		return _loopDivisionRel.getLoopDivisionRelId();
	}

	/**
	 * Returns the loop person ID of this loop division rel.
	 *
	 * @return the loop person ID of this loop division rel
	 */
	@Override
	public long getLoopPersonId() {
		return _loopDivisionRel.getLoopPersonId();
	}

	/**
	 * Returns the parent loop division ID of this loop division rel.
	 *
	 * @return the parent loop division ID of this loop division rel
	 */
	@Override
	public long getParentLoopDivisionId() {
		return _loopDivisionRel.getParentLoopDivisionId();
	}

	/**
	 * Returns the primary key of this loop division rel.
	 *
	 * @return the primary key of this loop division rel
	 */
	@Override
	public long getPrimaryKey() {
		return _loopDivisionRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopDivisionRel.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _loopDivisionRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopDivisionRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopDivisionRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopDivisionRel.isNew();
	}

	@Override
	public void persist() {
		_loopDivisionRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopDivisionRel.setCachedModel(cachedModel);
	}

	/**
	 * Sets the child loop division ID of this loop division rel.
	 *
	 * @param childLoopDivisionId the child loop division ID of this loop division rel
	 */
	@Override
	public void setChildLoopDivisionId(long childLoopDivisionId) {
		_loopDivisionRel.setChildLoopDivisionId(childLoopDivisionId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopDivisionRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopDivisionRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopDivisionRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop division rel ID of this loop division rel.
	 *
	 * @param loopDivisionRelId the loop division rel ID of this loop division rel
	 */
	@Override
	public void setLoopDivisionRelId(long loopDivisionRelId) {
		_loopDivisionRel.setLoopDivisionRelId(loopDivisionRelId);
	}

	/**
	 * Sets the loop person ID of this loop division rel.
	 *
	 * @param loopPersonId the loop person ID of this loop division rel
	 */
	@Override
	public void setLoopPersonId(long loopPersonId) {
		_loopDivisionRel.setLoopPersonId(loopPersonId);
	}

	@Override
	public void setNew(boolean n) {
		_loopDivisionRel.setNew(n);
	}

	/**
	 * Sets the parent loop division ID of this loop division rel.
	 *
	 * @param parentLoopDivisionId the parent loop division ID of this loop division rel
	 */
	@Override
	public void setParentLoopDivisionId(long parentLoopDivisionId) {
		_loopDivisionRel.setParentLoopDivisionId(parentLoopDivisionId);
	}

	/**
	 * Sets the primary key of this loop division rel.
	 *
	 * @param primaryKey the primary key of this loop division rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopDivisionRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopDivisionRel.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopDivisionRel>
		toCacheModel() {

		return _loopDivisionRel.toCacheModel();
	}

	@Override
	public LoopDivisionRel toEscapedModel() {
		return new LoopDivisionRelWrapper(_loopDivisionRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopDivisionRel.toString();
	}

	@Override
	public LoopDivisionRel toUnescapedModel() {
		return new LoopDivisionRelWrapper(_loopDivisionRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopDivisionRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopDivisionRelWrapper)) {
			return false;
		}

		LoopDivisionRelWrapper loopDivisionRelWrapper =
			(LoopDivisionRelWrapper)obj;

		if (Objects.equals(
				_loopDivisionRel, loopDivisionRelWrapper._loopDivisionRel)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopDivisionRel getWrappedModel() {
		return _loopDivisionRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopDivisionRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopDivisionRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopDivisionRel.resetOriginalValues();
	}

	private final LoopDivisionRel _loopDivisionRel;

}
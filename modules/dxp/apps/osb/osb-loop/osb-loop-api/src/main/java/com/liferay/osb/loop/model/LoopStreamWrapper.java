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
 * This class is a wrapper for {@link LoopStream}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStream
 * @generated
 */
public class LoopStreamWrapper implements LoopStream, ModelWrapper<LoopStream> {

	public LoopStreamWrapper(LoopStream loopStream) {
		_loopStream = loopStream;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopStream.class;
	}

	@Override
	public String getModelClassName() {
		return LoopStream.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopStreamId", getLoopStreamId());
		attributes.put("loopPersonId", getLoopPersonId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopStreamId = (Long)attributes.get("loopStreamId");

		if (loopStreamId != null) {
			setLoopStreamId(loopStreamId);
		}

		Long loopPersonId = (Long)attributes.get("loopPersonId");

		if (loopPersonId != null) {
			setLoopPersonId(loopPersonId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public Object clone() {
		return new LoopStreamWrapper((LoopStream)_loopStream.clone());
	}

	@Override
	public int compareTo(LoopStream loopStream) {
		return _loopStream.compareTo(loopStream);
	}

	/**
	 * Returns the description of this loop stream.
	 *
	 * @return the description of this loop stream
	 */
	@Override
	public String getDescription() {
		return _loopStream.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopStream.getExpandoBridge();
	}

	/**
	 * Returns the loop person ID of this loop stream.
	 *
	 * @return the loop person ID of this loop stream
	 */
	@Override
	public long getLoopPersonId() {
		return _loopStream.getLoopPersonId();
	}

	/**
	 * Returns the loop stream ID of this loop stream.
	 *
	 * @return the loop stream ID of this loop stream
	 */
	@Override
	public long getLoopStreamId() {
		return _loopStream.getLoopStreamId();
	}

	/**
	 * Returns the name of this loop stream.
	 *
	 * @return the name of this loop stream
	 */
	@Override
	public String getName() {
		return _loopStream.getName();
	}

	/**
	 * Returns the primary key of this loop stream.
	 *
	 * @return the primary key of this loop stream
	 */
	@Override
	public long getPrimaryKey() {
		return _loopStream.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopStream.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _loopStream.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopStream.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopStream.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopStream.isNew();
	}

	@Override
	public void persist() {
		_loopStream.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopStream.setCachedModel(cachedModel);
	}

	/**
	 * Sets the description of this loop stream.
	 *
	 * @param description the description of this loop stream
	 */
	@Override
	public void setDescription(String description) {
		_loopStream.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopStream.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopStream.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopStream.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop person ID of this loop stream.
	 *
	 * @param loopPersonId the loop person ID of this loop stream
	 */
	@Override
	public void setLoopPersonId(long loopPersonId) {
		_loopStream.setLoopPersonId(loopPersonId);
	}

	/**
	 * Sets the loop stream ID of this loop stream.
	 *
	 * @param loopStreamId the loop stream ID of this loop stream
	 */
	@Override
	public void setLoopStreamId(long loopStreamId) {
		_loopStream.setLoopStreamId(loopStreamId);
	}

	/**
	 * Sets the name of this loop stream.
	 *
	 * @param name the name of this loop stream
	 */
	@Override
	public void setName(String name) {
		_loopStream.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_loopStream.setNew(n);
	}

	/**
	 * Sets the primary key of this loop stream.
	 *
	 * @param primaryKey the primary key of this loop stream
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopStream.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopStream.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopStream>
		toCacheModel() {

		return _loopStream.toCacheModel();
	}

	@Override
	public LoopStream toEscapedModel() {
		return new LoopStreamWrapper(_loopStream.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopStream.toString();
	}

	@Override
	public LoopStream toUnescapedModel() {
		return new LoopStreamWrapper(_loopStream.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopStream.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopStreamWrapper)) {
			return false;
		}

		LoopStreamWrapper loopStreamWrapper = (LoopStreamWrapper)obj;

		if (Objects.equals(_loopStream, loopStreamWrapper._loopStream)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopStream getWrappedModel() {
		return _loopStream;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopStream.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopStream.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopStream.resetOriginalValues();
	}

	private final LoopStream _loopStream;

}
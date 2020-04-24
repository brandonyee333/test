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

package com.liferay.osb.loop.model;

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
 * This class is a wrapper for {@link LoopStreamEntry}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStreamEntry
 * @generated
 */
@ProviderType
public class LoopStreamEntryWrapper implements LoopStreamEntry,
	ModelWrapper<LoopStreamEntry> {
	public LoopStreamEntryWrapper(LoopStreamEntry loopStreamEntry) {
		_loopStreamEntry = loopStreamEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopStreamEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LoopStreamEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopStreamEntryId", getLoopStreamEntryId());
		attributes.put("loopPersonId", getLoopPersonId());
		attributes.put("loopStreamId", getLoopStreamId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("following", isFollowing());
		attributes.put("followingType", getFollowingType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopStreamEntryId = (Long)attributes.get("loopStreamEntryId");

		if (loopStreamEntryId != null) {
			setLoopStreamEntryId(loopStreamEntryId);
		}

		Long loopPersonId = (Long)attributes.get("loopPersonId");

		if (loopPersonId != null) {
			setLoopPersonId(loopPersonId);
		}

		Long loopStreamId = (Long)attributes.get("loopStreamId");

		if (loopStreamId != null) {
			setLoopStreamId(loopStreamId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Boolean following = (Boolean)attributes.get("following");

		if (following != null) {
			setFollowing(following);
		}

		Integer followingType = (Integer)attributes.get("followingType");

		if (followingType != null) {
			setFollowingType(followingType);
		}
	}

	@Override
	public Object clone() {
		return new LoopStreamEntryWrapper((LoopStreamEntry)_loopStreamEntry.clone());
	}

	@Override
	public int compareTo(LoopStreamEntry loopStreamEntry) {
		return _loopStreamEntry.compareTo(loopStreamEntry);
	}

	/**
	* Returns the fully qualified class name of this loop stream entry.
	*
	* @return the fully qualified class name of this loop stream entry
	*/
	@Override
	public String getClassName() {
		return _loopStreamEntry.getClassName();
	}

	/**
	* Returns the class name ID of this loop stream entry.
	*
	* @return the class name ID of this loop stream entry
	*/
	@Override
	public long getClassNameId() {
		return _loopStreamEntry.getClassNameId();
	}

	/**
	* Returns the class pk of this loop stream entry.
	*
	* @return the class pk of this loop stream entry
	*/
	@Override
	public long getClassPK() {
		return _loopStreamEntry.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopStreamEntry.getExpandoBridge();
	}

	/**
	* Returns the following of this loop stream entry.
	*
	* @return the following of this loop stream entry
	*/
	@Override
	public boolean getFollowing() {
		return _loopStreamEntry.getFollowing();
	}

	/**
	* Returns the following type of this loop stream entry.
	*
	* @return the following type of this loop stream entry
	*/
	@Override
	public int getFollowingType() {
		return _loopStreamEntry.getFollowingType();
	}

	/**
	* Returns the loop person ID of this loop stream entry.
	*
	* @return the loop person ID of this loop stream entry
	*/
	@Override
	public long getLoopPersonId() {
		return _loopStreamEntry.getLoopPersonId();
	}

	/**
	* Returns the loop stream entry ID of this loop stream entry.
	*
	* @return the loop stream entry ID of this loop stream entry
	*/
	@Override
	public long getLoopStreamEntryId() {
		return _loopStreamEntry.getLoopStreamEntryId();
	}

	/**
	* Returns the loop stream ID of this loop stream entry.
	*
	* @return the loop stream ID of this loop stream entry
	*/
	@Override
	public long getLoopStreamId() {
		return _loopStreamEntry.getLoopStreamId();
	}

	/**
	* Returns the primary key of this loop stream entry.
	*
	* @return the primary key of this loop stream entry
	*/
	@Override
	public long getPrimaryKey() {
		return _loopStreamEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopStreamEntry.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _loopStreamEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopStreamEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopStreamEntry.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this loop stream entry is following.
	*
	* @return <code>true</code> if this loop stream entry is following; <code>false</code> otherwise
	*/
	@Override
	public boolean isFollowing() {
		return _loopStreamEntry.isFollowing();
	}

	@Override
	public boolean isNew() {
		return _loopStreamEntry.isNew();
	}

	@Override
	public void persist() {
		_loopStreamEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopStreamEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_loopStreamEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this loop stream entry.
	*
	* @param classNameId the class name ID of this loop stream entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_loopStreamEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this loop stream entry.
	*
	* @param classPK the class pk of this loop stream entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_loopStreamEntry.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_loopStreamEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopStreamEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopStreamEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this loop stream entry is following.
	*
	* @param following the following of this loop stream entry
	*/
	@Override
	public void setFollowing(boolean following) {
		_loopStreamEntry.setFollowing(following);
	}

	/**
	* Sets the following type of this loop stream entry.
	*
	* @param followingType the following type of this loop stream entry
	*/
	@Override
	public void setFollowingType(int followingType) {
		_loopStreamEntry.setFollowingType(followingType);
	}

	/**
	* Sets the loop person ID of this loop stream entry.
	*
	* @param loopPersonId the loop person ID of this loop stream entry
	*/
	@Override
	public void setLoopPersonId(long loopPersonId) {
		_loopStreamEntry.setLoopPersonId(loopPersonId);
	}

	/**
	* Sets the loop stream entry ID of this loop stream entry.
	*
	* @param loopStreamEntryId the loop stream entry ID of this loop stream entry
	*/
	@Override
	public void setLoopStreamEntryId(long loopStreamEntryId) {
		_loopStreamEntry.setLoopStreamEntryId(loopStreamEntryId);
	}

	/**
	* Sets the loop stream ID of this loop stream entry.
	*
	* @param loopStreamId the loop stream ID of this loop stream entry
	*/
	@Override
	public void setLoopStreamId(long loopStreamId) {
		_loopStreamEntry.setLoopStreamId(loopStreamId);
	}

	@Override
	public void setNew(boolean n) {
		_loopStreamEntry.setNew(n);
	}

	/**
	* Sets the primary key of this loop stream entry.
	*
	* @param primaryKey the primary key of this loop stream entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopStreamEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopStreamEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopStreamEntry> toCacheModel() {
		return _loopStreamEntry.toCacheModel();
	}

	@Override
	public LoopStreamEntry toEscapedModel() {
		return new LoopStreamEntryWrapper(_loopStreamEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopStreamEntry.toString();
	}

	@Override
	public LoopStreamEntry toUnescapedModel() {
		return new LoopStreamEntryWrapper(_loopStreamEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopStreamEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopStreamEntryWrapper)) {
			return false;
		}

		LoopStreamEntryWrapper loopStreamEntryWrapper = (LoopStreamEntryWrapper)obj;

		if (Objects.equals(_loopStreamEntry,
					loopStreamEntryWrapper._loopStreamEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopStreamEntry getWrappedModel() {
		return _loopStreamEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopStreamEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopStreamEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopStreamEntry.resetOriginalValues();
	}

	private final LoopStreamEntry _loopStreamEntry;
}
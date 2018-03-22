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
 * This class is a wrapper for {@link HolidayCalendarRel}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRel
 * @generated
 */
@ProviderType
public class HolidayCalendarRelWrapper implements HolidayCalendarRel,
	ModelWrapper<HolidayCalendarRel> {
	public HolidayCalendarRelWrapper(HolidayCalendarRel holidayCalendarRel) {
		_holidayCalendarRel = holidayCalendarRel;
	}

	@Override
	public Class<?> getModelClass() {
		return HolidayCalendarRel.class;
	}

	@Override
	public String getModelClassName() {
		return HolidayCalendarRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayCalendarRelId", getHolidayCalendarRelId());
		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayCalendarRelId = (Long)attributes.get("holidayCalendarRelId");

		if (holidayCalendarRelId != null) {
			setHolidayCalendarRelId(holidayCalendarRelId);
		}

		Long holidayCalendarId = (Long)attributes.get("holidayCalendarId");

		if (holidayCalendarId != null) {
			setHolidayCalendarId(holidayCalendarId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public HolidayCalendarRel toEscapedModel() {
		return new HolidayCalendarRelWrapper(_holidayCalendarRel.toEscapedModel());
	}

	@Override
	public HolidayCalendarRel toUnescapedModel() {
		return new HolidayCalendarRelWrapper(_holidayCalendarRel.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _holidayCalendarRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _holidayCalendarRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _holidayCalendarRel.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _holidayCalendarRel.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HolidayCalendarRel> toCacheModel() {
		return _holidayCalendarRel.toCacheModel();
	}

	@Override
	public int compareTo(HolidayCalendarRel holidayCalendarRel) {
		return _holidayCalendarRel.compareTo(holidayCalendarRel);
	}

	@Override
	public int hashCode() {
		return _holidayCalendarRel.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holidayCalendarRel.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new HolidayCalendarRelWrapper((HolidayCalendarRel)_holidayCalendarRel.clone());
	}

	/**
	* Returns the user uuid of this holiday calendar rel.
	*
	* @return the user uuid of this holiday calendar rel
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _holidayCalendarRel.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _holidayCalendarRel.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _holidayCalendarRel.toXmlString();
	}

	/**
	* Returns the holiday calendar ID of this holiday calendar rel.
	*
	* @return the holiday calendar ID of this holiday calendar rel
	*/
	@Override
	public long getHolidayCalendarId() {
		return _holidayCalendarRel.getHolidayCalendarId();
	}

	/**
	* Returns the holiday calendar rel ID of this holiday calendar rel.
	*
	* @return the holiday calendar rel ID of this holiday calendar rel
	*/
	@Override
	public long getHolidayCalendarRelId() {
		return _holidayCalendarRel.getHolidayCalendarRelId();
	}

	/**
	* Returns the primary key of this holiday calendar rel.
	*
	* @return the primary key of this holiday calendar rel
	*/
	@Override
	public long getPrimaryKey() {
		return _holidayCalendarRel.getPrimaryKey();
	}

	/**
	* Returns the user ID of this holiday calendar rel.
	*
	* @return the user ID of this holiday calendar rel
	*/
	@Override
	public long getUserId() {
		return _holidayCalendarRel.getUserId();
	}

	@Override
	public void persist() {
		_holidayCalendarRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_holidayCalendarRel.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_holidayCalendarRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_holidayCalendarRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_holidayCalendarRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the holiday calendar ID of this holiday calendar rel.
	*
	* @param holidayCalendarId the holiday calendar ID of this holiday calendar rel
	*/
	@Override
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendarRel.setHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Sets the holiday calendar rel ID of this holiday calendar rel.
	*
	* @param holidayCalendarRelId the holiday calendar rel ID of this holiday calendar rel
	*/
	@Override
	public void setHolidayCalendarRelId(long holidayCalendarRelId) {
		_holidayCalendarRel.setHolidayCalendarRelId(holidayCalendarRelId);
	}

	@Override
	public void setNew(boolean n) {
		_holidayCalendarRel.setNew(n);
	}

	/**
	* Sets the primary key of this holiday calendar rel.
	*
	* @param primaryKey the primary key of this holiday calendar rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_holidayCalendarRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_holidayCalendarRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this holiday calendar rel.
	*
	* @param userId the user ID of this holiday calendar rel
	*/
	@Override
	public void setUserId(long userId) {
		_holidayCalendarRel.setUserId(userId);
	}

	/**
	* Sets the user uuid of this holiday calendar rel.
	*
	* @param userUuid the user uuid of this holiday calendar rel
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_holidayCalendarRel.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayCalendarRelWrapper)) {
			return false;
		}

		HolidayCalendarRelWrapper holidayCalendarRelWrapper = (HolidayCalendarRelWrapper)obj;

		if (Objects.equals(_holidayCalendarRel,
					holidayCalendarRelWrapper._holidayCalendarRel)) {
			return true;
		}

		return false;
	}

	@Override
	public HolidayCalendarRel getWrappedModel() {
		return _holidayCalendarRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _holidayCalendarRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _holidayCalendarRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_holidayCalendarRel.resetOriginalValues();
	}

	private final HolidayCalendarRel _holidayCalendarRel;
}
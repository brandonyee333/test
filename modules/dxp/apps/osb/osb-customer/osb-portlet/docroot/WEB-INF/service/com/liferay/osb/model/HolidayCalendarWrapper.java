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
 * This class is a wrapper for {@link HolidayCalendar}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendar
 * @generated
 */
@ProviderType
public class HolidayCalendarWrapper implements HolidayCalendar,
	ModelWrapper<HolidayCalendar> {
	public HolidayCalendarWrapper(HolidayCalendar holidayCalendar) {
		_holidayCalendar = holidayCalendar;
	}

	@Override
	public Class<?> getModelClass() {
		return HolidayCalendar.class;
	}

	@Override
	public String getModelClassName() {
		return HolidayCalendar.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayCalendarId = (Long)attributes.get("holidayCalendarId");

		if (holidayCalendarId != null) {
			setHolidayCalendarId(holidayCalendarId);
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
	public HolidayCalendar toEscapedModel() {
		return new HolidayCalendarWrapper(_holidayCalendar.toEscapedModel());
	}

	@Override
	public HolidayCalendar toUnescapedModel() {
		return new HolidayCalendarWrapper(_holidayCalendar.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _holidayCalendar.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _holidayCalendar.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _holidayCalendar.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _holidayCalendar.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HolidayCalendar> toCacheModel() {
		return _holidayCalendar.toCacheModel();
	}

	@Override
	public int compareTo(HolidayCalendar holidayCalendar) {
		return _holidayCalendar.compareTo(holidayCalendar);
	}

	@Override
	public int hashCode() {
		return _holidayCalendar.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holidayCalendar.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new HolidayCalendarWrapper((HolidayCalendar)_holidayCalendar.clone());
	}

	/**
	* Returns the description of this holiday calendar.
	*
	* @return the description of this holiday calendar
	*/
	@Override
	public java.lang.String getDescription() {
		return _holidayCalendar.getDescription();
	}

	/**
	* Returns the name of this holiday calendar.
	*
	* @return the name of this holiday calendar
	*/
	@Override
	public java.lang.String getName() {
		return _holidayCalendar.getName();
	}

	@Override
	public java.lang.String toString() {
		return _holidayCalendar.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _holidayCalendar.toXmlString();
	}

	/**
	* Returns the holiday calendar ID of this holiday calendar.
	*
	* @return the holiday calendar ID of this holiday calendar
	*/
	@Override
	public long getHolidayCalendarId() {
		return _holidayCalendar.getHolidayCalendarId();
	}

	/**
	* Returns the primary key of this holiday calendar.
	*
	* @return the primary key of this holiday calendar
	*/
	@Override
	public long getPrimaryKey() {
		return _holidayCalendar.getPrimaryKey();
	}

	@Override
	public void persist() {
		_holidayCalendar.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_holidayCalendar.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this holiday calendar.
	*
	* @param description the description of this holiday calendar
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_holidayCalendar.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_holidayCalendar.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_holidayCalendar.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_holidayCalendar.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the holiday calendar ID of this holiday calendar.
	*
	* @param holidayCalendarId the holiday calendar ID of this holiday calendar
	*/
	@Override
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendar.setHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Sets the name of this holiday calendar.
	*
	* @param name the name of this holiday calendar
	*/
	@Override
	public void setName(java.lang.String name) {
		_holidayCalendar.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_holidayCalendar.setNew(n);
	}

	/**
	* Sets the primary key of this holiday calendar.
	*
	* @param primaryKey the primary key of this holiday calendar
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_holidayCalendar.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_holidayCalendar.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayCalendarWrapper)) {
			return false;
		}

		HolidayCalendarWrapper holidayCalendarWrapper = (HolidayCalendarWrapper)obj;

		if (Objects.equals(_holidayCalendar,
					holidayCalendarWrapper._holidayCalendar)) {
			return true;
		}

		return false;
	}

	@Override
	public HolidayCalendar getWrappedModel() {
		return _holidayCalendar;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _holidayCalendar.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _holidayCalendar.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_holidayCalendar.resetOriginalValues();
	}

	private final HolidayCalendar _holidayCalendar;
}
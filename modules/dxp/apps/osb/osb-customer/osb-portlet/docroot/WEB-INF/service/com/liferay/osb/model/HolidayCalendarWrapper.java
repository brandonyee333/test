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
 * This class is a wrapper for {@link HolidayCalendar}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HolidayCalendar
 * @generated
 */
public class HolidayCalendarWrapper implements HolidayCalendar,
	ModelWrapper<HolidayCalendar> {
	public HolidayCalendarWrapper(HolidayCalendar holidayCalendar) {
		_holidayCalendar = holidayCalendar;
	}

	public Class<?> getModelClass() {
		return HolidayCalendar.class;
	}

	public String getModelClassName() {
		return HolidayCalendar.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

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

	/**
	* Returns the primary key of this holiday calendar.
	*
	* @return the primary key of this holiday calendar
	*/
	public long getPrimaryKey() {
		return _holidayCalendar.getPrimaryKey();
	}

	/**
	* Sets the primary key of this holiday calendar.
	*
	* @param primaryKey the primary key of this holiday calendar
	*/
	public void setPrimaryKey(long primaryKey) {
		_holidayCalendar.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the holiday calendar ID of this holiday calendar.
	*
	* @return the holiday calendar ID of this holiday calendar
	*/
	public long getHolidayCalendarId() {
		return _holidayCalendar.getHolidayCalendarId();
	}

	/**
	* Sets the holiday calendar ID of this holiday calendar.
	*
	* @param holidayCalendarId the holiday calendar ID of this holiday calendar
	*/
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendar.setHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Returns the name of this holiday calendar.
	*
	* @return the name of this holiday calendar
	*/
	public java.lang.String getName() {
		return _holidayCalendar.getName();
	}

	/**
	* Sets the name of this holiday calendar.
	*
	* @param name the name of this holiday calendar
	*/
	public void setName(java.lang.String name) {
		_holidayCalendar.setName(name);
	}

	/**
	* Returns the description of this holiday calendar.
	*
	* @return the description of this holiday calendar
	*/
	public java.lang.String getDescription() {
		return _holidayCalendar.getDescription();
	}

	/**
	* Sets the description of this holiday calendar.
	*
	* @param description the description of this holiday calendar
	*/
	public void setDescription(java.lang.String description) {
		_holidayCalendar.setDescription(description);
	}

	public boolean isNew() {
		return _holidayCalendar.isNew();
	}

	public void setNew(boolean n) {
		_holidayCalendar.setNew(n);
	}

	public boolean isCachedModel() {
		return _holidayCalendar.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_holidayCalendar.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _holidayCalendar.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _holidayCalendar.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_holidayCalendar.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _holidayCalendar.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_holidayCalendar.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HolidayCalendarWrapper((HolidayCalendar)_holidayCalendar.clone());
	}

	public int compareTo(com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		return _holidayCalendar.compareTo(holidayCalendar);
	}

	@Override
	public int hashCode() {
		return _holidayCalendar.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.HolidayCalendar> toCacheModel() {
		return _holidayCalendar.toCacheModel();
	}

	public com.liferay.osb.model.HolidayCalendar toEscapedModel() {
		return new HolidayCalendarWrapper(_holidayCalendar.toEscapedModel());
	}

	public com.liferay.osb.model.HolidayCalendar toUnescapedModel() {
		return new HolidayCalendarWrapper(_holidayCalendar.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _holidayCalendar.toString();
	}

	public java.lang.String toXmlString() {
		return _holidayCalendar.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_holidayCalendar.persist();
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

		if (Validator.equals(_holidayCalendar,
					holidayCalendarWrapper._holidayCalendar)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public HolidayCalendar getWrappedHolidayCalendar() {
		return _holidayCalendar;
	}

	public HolidayCalendar getWrappedModel() {
		return _holidayCalendar;
	}

	public void resetOriginalValues() {
		_holidayCalendar.resetOriginalValues();
	}

	private HolidayCalendar _holidayCalendar;
}
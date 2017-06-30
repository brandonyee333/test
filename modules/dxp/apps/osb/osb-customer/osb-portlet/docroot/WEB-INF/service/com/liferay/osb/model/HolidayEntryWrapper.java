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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HolidayEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HolidayEntry
 * @generated
 */
public class HolidayEntryWrapper implements HolidayEntry,
	ModelWrapper<HolidayEntry> {
	public HolidayEntryWrapper(HolidayEntry holidayEntry) {
		_holidayEntry = holidayEntry;
	}

	public Class<?> getModelClass() {
		return HolidayEntry.class;
	}

	public String getModelClassName() {
		return HolidayEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayEntryId", getHolidayEntryId());
		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("repeatYearly", getRepeatYearly());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayEntryId = (Long)attributes.get("holidayEntryId");

		if (holidayEntryId != null) {
			setHolidayEntryId(holidayEntryId);
		}

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

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean repeatYearly = (Boolean)attributes.get("repeatYearly");

		if (repeatYearly != null) {
			setRepeatYearly(repeatYearly);
		}
	}

	/**
	* Returns the primary key of this holiday entry.
	*
	* @return the primary key of this holiday entry
	*/
	public long getPrimaryKey() {
		return _holidayEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this holiday entry.
	*
	* @param primaryKey the primary key of this holiday entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_holidayEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the holiday entry ID of this holiday entry.
	*
	* @return the holiday entry ID of this holiday entry
	*/
	public long getHolidayEntryId() {
		return _holidayEntry.getHolidayEntryId();
	}

	/**
	* Sets the holiday entry ID of this holiday entry.
	*
	* @param holidayEntryId the holiday entry ID of this holiday entry
	*/
	public void setHolidayEntryId(long holidayEntryId) {
		_holidayEntry.setHolidayEntryId(holidayEntryId);
	}

	/**
	* Returns the holiday calendar ID of this holiday entry.
	*
	* @return the holiday calendar ID of this holiday entry
	*/
	public long getHolidayCalendarId() {
		return _holidayEntry.getHolidayCalendarId();
	}

	/**
	* Sets the holiday calendar ID of this holiday entry.
	*
	* @param holidayCalendarId the holiday calendar ID of this holiday entry
	*/
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayEntry.setHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Returns the name of this holiday entry.
	*
	* @return the name of this holiday entry
	*/
	public java.lang.String getName() {
		return _holidayEntry.getName();
	}

	/**
	* Sets the name of this holiday entry.
	*
	* @param name the name of this holiday entry
	*/
	public void setName(java.lang.String name) {
		_holidayEntry.setName(name);
	}

	/**
	* Returns the description of this holiday entry.
	*
	* @return the description of this holiday entry
	*/
	public java.lang.String getDescription() {
		return _holidayEntry.getDescription();
	}

	/**
	* Sets the description of this holiday entry.
	*
	* @param description the description of this holiday entry
	*/
	public void setDescription(java.lang.String description) {
		_holidayEntry.setDescription(description);
	}

	/**
	* Returns the start date of this holiday entry.
	*
	* @return the start date of this holiday entry
	*/
	public java.util.Date getStartDate() {
		return _holidayEntry.getStartDate();
	}

	/**
	* Sets the start date of this holiday entry.
	*
	* @param startDate the start date of this holiday entry
	*/
	public void setStartDate(java.util.Date startDate) {
		_holidayEntry.setStartDate(startDate);
	}

	/**
	* Returns the end date of this holiday entry.
	*
	* @return the end date of this holiday entry
	*/
	public java.util.Date getEndDate() {
		return _holidayEntry.getEndDate();
	}

	/**
	* Sets the end date of this holiday entry.
	*
	* @param endDate the end date of this holiday entry
	*/
	public void setEndDate(java.util.Date endDate) {
		_holidayEntry.setEndDate(endDate);
	}

	/**
	* Returns the repeat yearly of this holiday entry.
	*
	* @return the repeat yearly of this holiday entry
	*/
	public boolean getRepeatYearly() {
		return _holidayEntry.getRepeatYearly();
	}

	/**
	* Returns <code>true</code> if this holiday entry is repeat yearly.
	*
	* @return <code>true</code> if this holiday entry is repeat yearly; <code>false</code> otherwise
	*/
	public boolean isRepeatYearly() {
		return _holidayEntry.isRepeatYearly();
	}

	/**
	* Sets whether this holiday entry is repeat yearly.
	*
	* @param repeatYearly the repeat yearly of this holiday entry
	*/
	public void setRepeatYearly(boolean repeatYearly) {
		_holidayEntry.setRepeatYearly(repeatYearly);
	}

	public boolean isNew() {
		return _holidayEntry.isNew();
	}

	public void setNew(boolean n) {
		_holidayEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _holidayEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_holidayEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _holidayEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _holidayEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_holidayEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _holidayEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_holidayEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HolidayEntryWrapper((HolidayEntry)_holidayEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.HolidayEntry holidayEntry) {
		return _holidayEntry.compareTo(holidayEntry);
	}

	@Override
	public int hashCode() {
		return _holidayEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.HolidayEntry> toCacheModel() {
		return _holidayEntry.toCacheModel();
	}

	public com.liferay.osb.model.HolidayEntry toEscapedModel() {
		return new HolidayEntryWrapper(_holidayEntry.toEscapedModel());
	}

	public com.liferay.osb.model.HolidayEntry toUnescapedModel() {
		return new HolidayEntryWrapper(_holidayEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _holidayEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _holidayEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_holidayEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayEntryWrapper)) {
			return false;
		}

		HolidayEntryWrapper holidayEntryWrapper = (HolidayEntryWrapper)obj;

		if (Validator.equals(_holidayEntry, holidayEntryWrapper._holidayEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public HolidayEntry getWrappedHolidayEntry() {
		return _holidayEntry;
	}

	public HolidayEntry getWrappedModel() {
		return _holidayEntry;
	}

	public void resetOriginalValues() {
		_holidayEntry.resetOriginalValues();
	}

	private HolidayEntry _holidayEntry;
}
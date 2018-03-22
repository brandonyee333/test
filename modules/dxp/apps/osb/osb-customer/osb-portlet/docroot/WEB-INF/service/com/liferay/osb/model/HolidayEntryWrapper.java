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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link HolidayEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayEntry
 * @generated
 */
@ProviderType
public class HolidayEntryWrapper implements HolidayEntry,
	ModelWrapper<HolidayEntry> {
	public HolidayEntryWrapper(HolidayEntry holidayEntry) {
		_holidayEntry = holidayEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return HolidayEntry.class;
	}

	@Override
	public String getModelClassName() {
		return HolidayEntry.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public HolidayEntry toEscapedModel() {
		return new HolidayEntryWrapper(_holidayEntry.toEscapedModel());
	}

	@Override
	public HolidayEntry toUnescapedModel() {
		return new HolidayEntryWrapper(_holidayEntry.toUnescapedModel());
	}

	/**
	* Returns the repeat yearly of this holiday entry.
	*
	* @return the repeat yearly of this holiday entry
	*/
	@Override
	public boolean getRepeatYearly() {
		return _holidayEntry.getRepeatYearly();
	}

	@Override
	public boolean isCachedModel() {
		return _holidayEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _holidayEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _holidayEntry.isNew();
	}

	/**
	* Returns <code>true</code> if this holiday entry is repeat yearly.
	*
	* @return <code>true</code> if this holiday entry is repeat yearly; <code>false</code> otherwise
	*/
	@Override
	public boolean isRepeatYearly() {
		return _holidayEntry.isRepeatYearly();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _holidayEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HolidayEntry> toCacheModel() {
		return _holidayEntry.toCacheModel();
	}

	@Override
	public int compareTo(HolidayEntry holidayEntry) {
		return _holidayEntry.compareTo(holidayEntry);
	}

	@Override
	public int hashCode() {
		return _holidayEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holidayEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new HolidayEntryWrapper((HolidayEntry)_holidayEntry.clone());
	}

	/**
	* Returns the description of this holiday entry.
	*
	* @return the description of this holiday entry
	*/
	@Override
	public java.lang.String getDescription() {
		return _holidayEntry.getDescription();
	}

	/**
	* Returns the name of this holiday entry.
	*
	* @return the name of this holiday entry
	*/
	@Override
	public java.lang.String getName() {
		return _holidayEntry.getName();
	}

	@Override
	public java.lang.String toString() {
		return _holidayEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _holidayEntry.toXmlString();
	}

	/**
	* Returns the end date of this holiday entry.
	*
	* @return the end date of this holiday entry
	*/
	@Override
	public Date getEndDate() {
		return _holidayEntry.getEndDate();
	}

	/**
	* Returns the start date of this holiday entry.
	*
	* @return the start date of this holiday entry
	*/
	@Override
	public Date getStartDate() {
		return _holidayEntry.getStartDate();
	}

	/**
	* Returns the holiday calendar ID of this holiday entry.
	*
	* @return the holiday calendar ID of this holiday entry
	*/
	@Override
	public long getHolidayCalendarId() {
		return _holidayEntry.getHolidayCalendarId();
	}

	/**
	* Returns the holiday entry ID of this holiday entry.
	*
	* @return the holiday entry ID of this holiday entry
	*/
	@Override
	public long getHolidayEntryId() {
		return _holidayEntry.getHolidayEntryId();
	}

	/**
	* Returns the primary key of this holiday entry.
	*
	* @return the primary key of this holiday entry
	*/
	@Override
	public long getPrimaryKey() {
		return _holidayEntry.getPrimaryKey();
	}

	@Override
	public void persist() {
		_holidayEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_holidayEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this holiday entry.
	*
	* @param description the description of this holiday entry
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_holidayEntry.setDescription(description);
	}

	/**
	* Sets the end date of this holiday entry.
	*
	* @param endDate the end date of this holiday entry
	*/
	@Override
	public void setEndDate(Date endDate) {
		_holidayEntry.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_holidayEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_holidayEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_holidayEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the holiday calendar ID of this holiday entry.
	*
	* @param holidayCalendarId the holiday calendar ID of this holiday entry
	*/
	@Override
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayEntry.setHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Sets the holiday entry ID of this holiday entry.
	*
	* @param holidayEntryId the holiday entry ID of this holiday entry
	*/
	@Override
	public void setHolidayEntryId(long holidayEntryId) {
		_holidayEntry.setHolidayEntryId(holidayEntryId);
	}

	/**
	* Sets the name of this holiday entry.
	*
	* @param name the name of this holiday entry
	*/
	@Override
	public void setName(java.lang.String name) {
		_holidayEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_holidayEntry.setNew(n);
	}

	/**
	* Sets the primary key of this holiday entry.
	*
	* @param primaryKey the primary key of this holiday entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_holidayEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_holidayEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this holiday entry is repeat yearly.
	*
	* @param repeatYearly the repeat yearly of this holiday entry
	*/
	@Override
	public void setRepeatYearly(boolean repeatYearly) {
		_holidayEntry.setRepeatYearly(repeatYearly);
	}

	/**
	* Sets the start date of this holiday entry.
	*
	* @param startDate the start date of this holiday entry
	*/
	@Override
	public void setStartDate(Date startDate) {
		_holidayEntry.setStartDate(startDate);
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

		if (Objects.equals(_holidayEntry, holidayEntryWrapper._holidayEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public HolidayEntry getWrappedModel() {
		return _holidayEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _holidayEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _holidayEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_holidayEntry.resetOriginalValues();
	}

	private final HolidayEntry _holidayEntry;
}
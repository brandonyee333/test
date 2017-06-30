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
 * This class is a wrapper for {@link HolidayCalendarRel}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HolidayCalendarRel
 * @generated
 */
public class HolidayCalendarRelWrapper implements HolidayCalendarRel,
	ModelWrapper<HolidayCalendarRel> {
	public HolidayCalendarRelWrapper(HolidayCalendarRel holidayCalendarRel) {
		_holidayCalendarRel = holidayCalendarRel;
	}

	public Class<?> getModelClass() {
		return HolidayCalendarRel.class;
	}

	public String getModelClassName() {
		return HolidayCalendarRel.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayCalendarRelId", getHolidayCalendarRelId());
		attributes.put("holidayCalendarId", getHolidayCalendarId());
		attributes.put("userId", getUserId());

		return attributes;
	}

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

	/**
	* Returns the primary key of this holiday calendar rel.
	*
	* @return the primary key of this holiday calendar rel
	*/
	public long getPrimaryKey() {
		return _holidayCalendarRel.getPrimaryKey();
	}

	/**
	* Sets the primary key of this holiday calendar rel.
	*
	* @param primaryKey the primary key of this holiday calendar rel
	*/
	public void setPrimaryKey(long primaryKey) {
		_holidayCalendarRel.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the holiday calendar rel ID of this holiday calendar rel.
	*
	* @return the holiday calendar rel ID of this holiday calendar rel
	*/
	public long getHolidayCalendarRelId() {
		return _holidayCalendarRel.getHolidayCalendarRelId();
	}

	/**
	* Sets the holiday calendar rel ID of this holiday calendar rel.
	*
	* @param holidayCalendarRelId the holiday calendar rel ID of this holiday calendar rel
	*/
	public void setHolidayCalendarRelId(long holidayCalendarRelId) {
		_holidayCalendarRel.setHolidayCalendarRelId(holidayCalendarRelId);
	}

	/**
	* Returns the holiday calendar ID of this holiday calendar rel.
	*
	* @return the holiday calendar ID of this holiday calendar rel
	*/
	public long getHolidayCalendarId() {
		return _holidayCalendarRel.getHolidayCalendarId();
	}

	/**
	* Sets the holiday calendar ID of this holiday calendar rel.
	*
	* @param holidayCalendarId the holiday calendar ID of this holiday calendar rel
	*/
	public void setHolidayCalendarId(long holidayCalendarId) {
		_holidayCalendarRel.setHolidayCalendarId(holidayCalendarId);
	}

	/**
	* Returns the user ID of this holiday calendar rel.
	*
	* @return the user ID of this holiday calendar rel
	*/
	public long getUserId() {
		return _holidayCalendarRel.getUserId();
	}

	/**
	* Sets the user ID of this holiday calendar rel.
	*
	* @param userId the user ID of this holiday calendar rel
	*/
	public void setUserId(long userId) {
		_holidayCalendarRel.setUserId(userId);
	}

	/**
	* Returns the user uuid of this holiday calendar rel.
	*
	* @return the user uuid of this holiday calendar rel
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRel.getUserUuid();
	}

	/**
	* Sets the user uuid of this holiday calendar rel.
	*
	* @param userUuid the user uuid of this holiday calendar rel
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_holidayCalendarRel.setUserUuid(userUuid);
	}

	public boolean isNew() {
		return _holidayCalendarRel.isNew();
	}

	public void setNew(boolean n) {
		_holidayCalendarRel.setNew(n);
	}

	public boolean isCachedModel() {
		return _holidayCalendarRel.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_holidayCalendarRel.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _holidayCalendarRel.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _holidayCalendarRel.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_holidayCalendarRel.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _holidayCalendarRel.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_holidayCalendarRel.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HolidayCalendarRelWrapper((HolidayCalendarRel)_holidayCalendarRel.clone());
	}

	public int compareTo(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel) {
		return _holidayCalendarRel.compareTo(holidayCalendarRel);
	}

	@Override
	public int hashCode() {
		return _holidayCalendarRel.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.HolidayCalendarRel> toCacheModel() {
		return _holidayCalendarRel.toCacheModel();
	}

	public com.liferay.osb.model.HolidayCalendarRel toEscapedModel() {
		return new HolidayCalendarRelWrapper(_holidayCalendarRel.toEscapedModel());
	}

	public com.liferay.osb.model.HolidayCalendarRel toUnescapedModel() {
		return new HolidayCalendarRelWrapper(_holidayCalendarRel.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _holidayCalendarRel.toString();
	}

	public java.lang.String toXmlString() {
		return _holidayCalendarRel.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_holidayCalendarRel.persist();
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

		if (Validator.equals(_holidayCalendarRel,
					holidayCalendarRelWrapper._holidayCalendarRel)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public HolidayCalendarRel getWrappedHolidayCalendarRel() {
		return _holidayCalendarRel;
	}

	public HolidayCalendarRel getWrappedModel() {
		return _holidayCalendarRel;
	}

	public void resetOriginalValues() {
		_holidayCalendarRel.resetOriginalValues();
	}

	private HolidayCalendarRel _holidayCalendarRel;
}
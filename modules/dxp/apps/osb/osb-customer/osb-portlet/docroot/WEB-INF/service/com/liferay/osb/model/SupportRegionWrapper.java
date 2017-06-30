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
 * This class is a wrapper for {@link SupportRegion}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportRegion
 * @generated
 */
public class SupportRegionWrapper implements SupportRegion,
	ModelWrapper<SupportRegion> {
	public SupportRegionWrapper(SupportRegion supportRegion) {
		_supportRegion = supportRegion;
	}

	public Class<?> getModelClass() {
		return SupportRegion.class;
	}

	public String getModelClassName() {
		return SupportRegion.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportRegionId", getSupportRegionId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("managerUserId", getManagerUserId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportRegionId = (Long)attributes.get("supportRegionId");

		if (supportRegionId != null) {
			setSupportRegionId(supportRegionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String timeZoneId = (String)attributes.get("timeZoneId");

		if (timeZoneId != null) {
			setTimeZoneId(timeZoneId);
		}

		Long managerUserId = (Long)attributes.get("managerUserId");

		if (managerUserId != null) {
			setManagerUserId(managerUserId);
		}
	}

	/**
	* Returns the primary key of this support region.
	*
	* @return the primary key of this support region
	*/
	public long getPrimaryKey() {
		return _supportRegion.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support region.
	*
	* @param primaryKey the primary key of this support region
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportRegion.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support region ID of this support region.
	*
	* @return the support region ID of this support region
	*/
	public long getSupportRegionId() {
		return _supportRegion.getSupportRegionId();
	}

	/**
	* Sets the support region ID of this support region.
	*
	* @param supportRegionId the support region ID of this support region
	*/
	public void setSupportRegionId(long supportRegionId) {
		_supportRegion.setSupportRegionId(supportRegionId);
	}

	/**
	* Returns the user ID of this support region.
	*
	* @return the user ID of this support region
	*/
	public long getUserId() {
		return _supportRegion.getUserId();
	}

	/**
	* Sets the user ID of this support region.
	*
	* @param userId the user ID of this support region
	*/
	public void setUserId(long userId) {
		_supportRegion.setUserId(userId);
	}

	/**
	* Returns the user uuid of this support region.
	*
	* @return the user uuid of this support region
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegion.getUserUuid();
	}

	/**
	* Sets the user uuid of this support region.
	*
	* @param userUuid the user uuid of this support region
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_supportRegion.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this support region.
	*
	* @return the user name of this support region
	*/
	public java.lang.String getUserName() {
		return _supportRegion.getUserName();
	}

	/**
	* Sets the user name of this support region.
	*
	* @param userName the user name of this support region
	*/
	public void setUserName(java.lang.String userName) {
		_supportRegion.setUserName(userName);
	}

	/**
	* Returns the create date of this support region.
	*
	* @return the create date of this support region
	*/
	public java.util.Date getCreateDate() {
		return _supportRegion.getCreateDate();
	}

	/**
	* Sets the create date of this support region.
	*
	* @param createDate the create date of this support region
	*/
	public void setCreateDate(java.util.Date createDate) {
		_supportRegion.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this support region.
	*
	* @return the modified date of this support region
	*/
	public java.util.Date getModifiedDate() {
		return _supportRegion.getModifiedDate();
	}

	/**
	* Sets the modified date of this support region.
	*
	* @param modifiedDate the modified date of this support region
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_supportRegion.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this support region.
	*
	* @return the name of this support region
	*/
	public java.lang.String getName() {
		return _supportRegion.getName();
	}

	/**
	* Sets the name of this support region.
	*
	* @param name the name of this support region
	*/
	public void setName(java.lang.String name) {
		_supportRegion.setName(name);
	}

	/**
	* Returns the description of this support region.
	*
	* @return the description of this support region
	*/
	public java.lang.String getDescription() {
		return _supportRegion.getDescription();
	}

	/**
	* Sets the description of this support region.
	*
	* @param description the description of this support region
	*/
	public void setDescription(java.lang.String description) {
		_supportRegion.setDescription(description);
	}

	/**
	* Returns the time zone ID of this support region.
	*
	* @return the time zone ID of this support region
	*/
	public java.lang.String getTimeZoneId() {
		return _supportRegion.getTimeZoneId();
	}

	/**
	* Sets the time zone ID of this support region.
	*
	* @param timeZoneId the time zone ID of this support region
	*/
	public void setTimeZoneId(java.lang.String timeZoneId) {
		_supportRegion.setTimeZoneId(timeZoneId);
	}

	/**
	* Returns the manager user ID of this support region.
	*
	* @return the manager user ID of this support region
	*/
	public long getManagerUserId() {
		return _supportRegion.getManagerUserId();
	}

	/**
	* Sets the manager user ID of this support region.
	*
	* @param managerUserId the manager user ID of this support region
	*/
	public void setManagerUserId(long managerUserId) {
		_supportRegion.setManagerUserId(managerUserId);
	}

	/**
	* Returns the manager user uuid of this support region.
	*
	* @return the manager user uuid of this support region
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getManagerUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegion.getManagerUserUuid();
	}

	/**
	* Sets the manager user uuid of this support region.
	*
	* @param managerUserUuid the manager user uuid of this support region
	*/
	public void setManagerUserUuid(java.lang.String managerUserUuid) {
		_supportRegion.setManagerUserUuid(managerUserUuid);
	}

	public boolean isNew() {
		return _supportRegion.isNew();
	}

	public void setNew(boolean n) {
		_supportRegion.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportRegion.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportRegion.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportRegion.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportRegion.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportRegion.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportRegion.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportRegion.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportRegionWrapper((SupportRegion)_supportRegion.clone());
	}

	public int compareTo(com.liferay.osb.model.SupportRegion supportRegion) {
		return _supportRegion.compareTo(supportRegion);
	}

	@Override
	public int hashCode() {
		return _supportRegion.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportRegion> toCacheModel() {
		return _supportRegion.toCacheModel();
	}

	public com.liferay.osb.model.SupportRegion toEscapedModel() {
		return new SupportRegionWrapper(_supportRegion.toEscapedModel());
	}

	public com.liferay.osb.model.SupportRegion toUnescapedModel() {
		return new SupportRegionWrapper(_supportRegion.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportRegion.toString();
	}

	public java.lang.String toXmlString() {
		return _supportRegion.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegion.persist();
	}

	public com.liferay.portal.model.User getManagerUser()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegion.getManagerUser();
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegion.getSupportTeams();
	}

	public java.util.TimeZone getTimeZone() {
		return _supportRegion.getTimeZone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportRegionWrapper)) {
			return false;
		}

		SupportRegionWrapper supportRegionWrapper = (SupportRegionWrapper)obj;

		if (Validator.equals(_supportRegion, supportRegionWrapper._supportRegion)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportRegion getWrappedSupportRegion() {
		return _supportRegion;
	}

	public SupportRegion getWrappedModel() {
		return _supportRegion;
	}

	public void resetOriginalValues() {
		_supportRegion.resetOriginalValues();
	}

	private SupportRegion _supportRegion;
}
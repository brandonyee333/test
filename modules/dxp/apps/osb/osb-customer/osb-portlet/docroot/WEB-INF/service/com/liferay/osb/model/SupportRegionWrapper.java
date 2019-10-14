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
 * This class is a wrapper for {@link SupportRegion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegion
 * @generated
 */
@ProviderType
public class SupportRegionWrapper implements SupportRegion,
	ModelWrapper<SupportRegion> {
	public SupportRegionWrapper(SupportRegion supportRegion) {
		_supportRegion = supportRegion;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportRegion.class;
	}

	@Override
	public String getModelClassName() {
		return SupportRegion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportRegionId", getSupportRegionId());
		attributes.put("companyId", getCompanyId());
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

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportRegionId = (Long)attributes.get("supportRegionId");

		if (supportRegionId != null) {
			setSupportRegionId(supportRegionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

	@Override
	public boolean isCachedModel() {
		return _supportRegion.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportRegion.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportRegion.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportRegion.getExpandoBridge();
	}

	@Override
	public SupportRegion toEscapedModel() {
		return new SupportRegionWrapper(_supportRegion.toEscapedModel());
	}

	@Override
	public SupportRegion toUnescapedModel() {
		return new SupportRegionWrapper(_supportRegion.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportRegion> toCacheModel() {
		return _supportRegion.toCacheModel();
	}

	@Override
	public com.liferay.portal.kernel.model.User getManagerUser() {
		return _supportRegion.getManagerUser();
	}

	@Override
	public int compareTo(SupportRegion supportRegion) {
		return _supportRegion.compareTo(supportRegion);
	}

	@Override
	public int hashCode() {
		return _supportRegion.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportRegion.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportRegionWrapper((SupportRegion)_supportRegion.clone());
	}

	/**
	* Returns the description of this support region.
	*
	* @return the description of this support region
	*/
	@Override
	public java.lang.String getDescription() {
		return _supportRegion.getDescription();
	}

	/**
	* Returns the manager user uuid of this support region.
	*
	* @return the manager user uuid of this support region
	*/
	@Override
	public java.lang.String getManagerUserUuid() {
		return _supportRegion.getManagerUserUuid();
	}

	/**
	* Returns the name of this support region.
	*
	* @return the name of this support region
	*/
	@Override
	public java.lang.String getName() {
		return _supportRegion.getName();
	}

	/**
	* Returns the time zone ID of this support region.
	*
	* @return the time zone ID of this support region
	*/
	@Override
	public java.lang.String getTimeZoneId() {
		return _supportRegion.getTimeZoneId();
	}

	/**
	* Returns the user name of this support region.
	*
	* @return the user name of this support region
	*/
	@Override
	public java.lang.String getUserName() {
		return _supportRegion.getUserName();
	}

	/**
	* Returns the user uuid of this support region.
	*
	* @return the user uuid of this support region
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _supportRegion.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _supportRegion.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportRegion.toXmlString();
	}

	/**
	* Returns the create date of this support region.
	*
	* @return the create date of this support region
	*/
	@Override
	public Date getCreateDate() {
		return _supportRegion.getCreateDate();
	}

	/**
	* Returns the modified date of this support region.
	*
	* @return the modified date of this support region
	*/
	@Override
	public Date getModifiedDate() {
		return _supportRegion.getModifiedDate();
	}

	@Override
	public java.util.TimeZone getTimeZone() {
		return _supportRegion.getTimeZone();
	}

	/**
	* Returns the company ID of this support region.
	*
	* @return the company ID of this support region
	*/
	@Override
	public long getCompanyId() {
		return _supportRegion.getCompanyId();
	}

	/**
	* Returns the manager user ID of this support region.
	*
	* @return the manager user ID of this support region
	*/
	@Override
	public long getManagerUserId() {
		return _supportRegion.getManagerUserId();
	}

	/**
	* Returns the primary key of this support region.
	*
	* @return the primary key of this support region
	*/
	@Override
	public long getPrimaryKey() {
		return _supportRegion.getPrimaryKey();
	}

	/**
	* Returns the support region ID of this support region.
	*
	* @return the support region ID of this support region
	*/
	@Override
	public long getSupportRegionId() {
		return _supportRegion.getSupportRegionId();
	}

	/**
	* Returns the user ID of this support region.
	*
	* @return the user ID of this support region
	*/
	@Override
	public long getUserId() {
		return _supportRegion.getUserId();
	}

	@Override
	public void persist() {
		_supportRegion.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportRegion.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this support region.
	*
	* @param companyId the company ID of this support region
	*/
	@Override
	public void setCompanyId(long companyId) {
		_supportRegion.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this support region.
	*
	* @param createDate the create date of this support region
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_supportRegion.setCreateDate(createDate);
	}

	/**
	* Sets the description of this support region.
	*
	* @param description the description of this support region
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_supportRegion.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportRegion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportRegion.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportRegion.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the manager user ID of this support region.
	*
	* @param managerUserId the manager user ID of this support region
	*/
	@Override
	public void setManagerUserId(long managerUserId) {
		_supportRegion.setManagerUserId(managerUserId);
	}

	/**
	* Sets the manager user uuid of this support region.
	*
	* @param managerUserUuid the manager user uuid of this support region
	*/
	@Override
	public void setManagerUserUuid(java.lang.String managerUserUuid) {
		_supportRegion.setManagerUserUuid(managerUserUuid);
	}

	/**
	* Sets the modified date of this support region.
	*
	* @param modifiedDate the modified date of this support region
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_supportRegion.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this support region.
	*
	* @param name the name of this support region
	*/
	@Override
	public void setName(java.lang.String name) {
		_supportRegion.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_supportRegion.setNew(n);
	}

	/**
	* Sets the primary key of this support region.
	*
	* @param primaryKey the primary key of this support region
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportRegion.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportRegion.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the support region ID of this support region.
	*
	* @param supportRegionId the support region ID of this support region
	*/
	@Override
	public void setSupportRegionId(long supportRegionId) {
		_supportRegion.setSupportRegionId(supportRegionId);
	}

	/**
	* Sets the time zone ID of this support region.
	*
	* @param timeZoneId the time zone ID of this support region
	*/
	@Override
	public void setTimeZoneId(java.lang.String timeZoneId) {
		_supportRegion.setTimeZoneId(timeZoneId);
	}

	/**
	* Sets the user ID of this support region.
	*
	* @param userId the user ID of this support region
	*/
	@Override
	public void setUserId(long userId) {
		_supportRegion.setUserId(userId);
	}

	/**
	* Sets the user name of this support region.
	*
	* @param userName the user name of this support region
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_supportRegion.setUserName(userName);
	}

	/**
	* Sets the user uuid of this support region.
	*
	* @param userUuid the user uuid of this support region
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_supportRegion.setUserUuid(userUuid);
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

		if (Objects.equals(_supportRegion, supportRegionWrapper._supportRegion)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportRegion getWrappedModel() {
		return _supportRegion;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportRegion.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportRegion.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportRegion.resetOriginalValues();
	}

	private final SupportRegion _supportRegion;
}
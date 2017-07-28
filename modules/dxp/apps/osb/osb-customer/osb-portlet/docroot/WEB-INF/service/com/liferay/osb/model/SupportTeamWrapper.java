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
 * This class is a wrapper for {@link SupportTeam}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeam
 * @generated
 */
@ProviderType
public class SupportTeamWrapper implements SupportTeam,
	ModelWrapper<SupportTeam> {
	public SupportTeamWrapper(SupportTeam supportTeam) {
		_supportTeam = supportTeam;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportTeam.class;
	}

	@Override
	public String getModelClassName() {
		return SupportTeam.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportTeamId", getSupportTeamId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentSupportTeamId", getParentSupportTeamId());
		attributes.put("supportLaborId", getSupportLaborId());
		attributes.put("locationSupportRegionId", getLocationSupportRegionId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("type", getType());
		attributes.put("assignedWork", getAssignedWork());
		attributes.put("maxWork", getMaxWork());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportTeamId = (Long)attributes.get("supportTeamId");

		if (supportTeamId != null) {
			setSupportTeamId(supportTeamId);
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

		Long parentSupportTeamId = (Long)attributes.get("parentSupportTeamId");

		if (parentSupportTeamId != null) {
			setParentSupportTeamId(parentSupportTeamId);
		}

		Long supportLaborId = (Long)attributes.get("supportLaborId");

		if (supportLaborId != null) {
			setSupportLaborId(supportLaborId);
		}

		Long locationSupportRegionId = (Long)attributes.get(
				"locationSupportRegionId");

		if (locationSupportRegionId != null) {
			setLocationSupportRegionId(locationSupportRegionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Double assignedWork = (Double)attributes.get("assignedWork");

		if (assignedWork != null) {
			setAssignedWork(assignedWork);
		}

		Double maxWork = (Double)attributes.get("maxWork");

		if (maxWork != null) {
			setMaxWork(maxWork);
		}
	}

	@Override
	public SupportTeam toEscapedModel() {
		return new SupportTeamWrapper(_supportTeam.toEscapedModel());
	}

	@Override
	public SupportTeam toUnescapedModel() {
		return new SupportTeamWrapper(_supportTeam.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _supportTeam.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportTeam.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportTeam.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportTeam.getExpandoBridge();
	}

	@Override
	public SupportTeam getParentSupportTeam()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeam.getParentSupportTeam();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportTeam> toCacheModel() {
		return _supportTeam.toCacheModel();
	}

	/**
	* Returns the assigned work of this support team.
	*
	* @return the assigned work of this support team
	*/
	@Override
	public double getAssignedWork() {
		return _supportTeam.getAssignedWork();
	}

	/**
	* Returns the max work of this support team.
	*
	* @return the max work of this support team
	*/
	@Override
	public double getMaxWork() {
		return _supportTeam.getMaxWork();
	}

	@Override
	public int compareTo(SupportTeam supportTeam) {
		return _supportTeam.compareTo(supportTeam);
	}

	/**
	* Returns the type of this support team.
	*
	* @return the type of this support team
	*/
	@Override
	public int getType() {
		return _supportTeam.getType();
	}

	@Override
	public int hashCode() {
		return _supportTeam.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportTeam.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportTeamWrapper((SupportTeam)_supportTeam.clone());
	}

	/**
	* Returns the description of this support team.
	*
	* @return the description of this support team
	*/
	@Override
	public java.lang.String getDescription() {
		return _supportTeam.getDescription();
	}

	/**
	* Returns the name of this support team.
	*
	* @return the name of this support team
	*/
	@Override
	public java.lang.String getName() {
		return _supportTeam.getName();
	}

	/**
	* Returns the user name of this support team.
	*
	* @return the user name of this support team
	*/
	@Override
	public java.lang.String getUserName() {
		return _supportTeam.getUserName();
	}

	/**
	* Returns the user uuid of this support team.
	*
	* @return the user uuid of this support team
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _supportTeam.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _supportTeam.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportTeam.toXmlString();
	}

	/**
	* Returns the create date of this support team.
	*
	* @return the create date of this support team
	*/
	@Override
	public Date getCreateDate() {
		return _supportTeam.getCreateDate();
	}

	/**
	* Returns the modified date of this support team.
	*
	* @return the modified date of this support team
	*/
	@Override
	public Date getModifiedDate() {
		return _supportTeam.getModifiedDate();
	}

	@Override
	public java.util.List<AccountEntry> getAccountEntries() {
		return _supportTeam.getAccountEntries();
	}

	@Override
	public java.util.List<SupportTeam> getChildSupportTeams() {
		return _supportTeam.getChildSupportTeams();
	}

	@Override
	public java.util.List<SupportTeam> getChildSupportTeams(boolean recursive) {
		return _supportTeam.getChildSupportTeams(recursive);
	}

	@Override
	public java.util.List<java.lang.String> getLanguageIds() {
		return _supportTeam.getLanguageIds();
	}

	@Override
	public java.util.List<SupportRegion> getSupportRegions() {
		return _supportTeam.getSupportRegions();
	}

	/**
	* Returns the company ID of this support team.
	*
	* @return the company ID of this support team
	*/
	@Override
	public long getCompanyId() {
		return _supportTeam.getCompanyId();
	}

	/**
	* Returns the location support region ID of this support team.
	*
	* @return the location support region ID of this support team
	*/
	@Override
	public long getLocationSupportRegionId() {
		return _supportTeam.getLocationSupportRegionId();
	}

	/**
	* Returns the parent support team ID of this support team.
	*
	* @return the parent support team ID of this support team
	*/
	@Override
	public long getParentSupportTeamId() {
		return _supportTeam.getParentSupportTeamId();
	}

	/**
	* Returns the primary key of this support team.
	*
	* @return the primary key of this support team
	*/
	@Override
	public long getPrimaryKey() {
		return _supportTeam.getPrimaryKey();
	}

	/**
	* Returns the support labor ID of this support team.
	*
	* @return the support labor ID of this support team
	*/
	@Override
	public long getSupportLaborId() {
		return _supportTeam.getSupportLaborId();
	}

	/**
	* Returns the support team ID of this support team.
	*
	* @return the support team ID of this support team
	*/
	@Override
	public long getSupportTeamId() {
		return _supportTeam.getSupportTeamId();
	}

	/**
	* Returns the user ID of this support team.
	*
	* @return the user ID of this support team
	*/
	@Override
	public long getUserId() {
		return _supportTeam.getUserId();
	}

	@Override
	public void persist() {
		_supportTeam.persist();
	}

	/**
	* Sets the assigned work of this support team.
	*
	* @param assignedWork the assigned work of this support team
	*/
	@Override
	public void setAssignedWork(double assignedWork) {
		_supportTeam.setAssignedWork(assignedWork);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportTeam.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this support team.
	*
	* @param companyId the company ID of this support team
	*/
	@Override
	public void setCompanyId(long companyId) {
		_supportTeam.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this support team.
	*
	* @param createDate the create date of this support team
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_supportTeam.setCreateDate(createDate);
	}

	/**
	* Sets the description of this support team.
	*
	* @param description the description of this support team
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_supportTeam.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportTeam.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportTeam.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportTeam.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the location support region ID of this support team.
	*
	* @param locationSupportRegionId the location support region ID of this support team
	*/
	@Override
	public void setLocationSupportRegionId(long locationSupportRegionId) {
		_supportTeam.setLocationSupportRegionId(locationSupportRegionId);
	}

	/**
	* Sets the max work of this support team.
	*
	* @param maxWork the max work of this support team
	*/
	@Override
	public void setMaxWork(double maxWork) {
		_supportTeam.setMaxWork(maxWork);
	}

	/**
	* Sets the modified date of this support team.
	*
	* @param modifiedDate the modified date of this support team
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_supportTeam.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this support team.
	*
	* @param name the name of this support team
	*/
	@Override
	public void setName(java.lang.String name) {
		_supportTeam.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_supportTeam.setNew(n);
	}

	/**
	* Sets the parent support team ID of this support team.
	*
	* @param parentSupportTeamId the parent support team ID of this support team
	*/
	@Override
	public void setParentSupportTeamId(long parentSupportTeamId) {
		_supportTeam.setParentSupportTeamId(parentSupportTeamId);
	}

	/**
	* Sets the primary key of this support team.
	*
	* @param primaryKey the primary key of this support team
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportTeam.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportTeam.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the support labor ID of this support team.
	*
	* @param supportLaborId the support labor ID of this support team
	*/
	@Override
	public void setSupportLaborId(long supportLaborId) {
		_supportTeam.setSupportLaborId(supportLaborId);
	}

	/**
	* Sets the support team ID of this support team.
	*
	* @param supportTeamId the support team ID of this support team
	*/
	@Override
	public void setSupportTeamId(long supportTeamId) {
		_supportTeam.setSupportTeamId(supportTeamId);
	}

	/**
	* Sets the type of this support team.
	*
	* @param type the type of this support team
	*/
	@Override
	public void setType(int type) {
		_supportTeam.setType(type);
	}

	/**
	* Sets the user ID of this support team.
	*
	* @param userId the user ID of this support team
	*/
	@Override
	public void setUserId(long userId) {
		_supportTeam.setUserId(userId);
	}

	/**
	* Sets the user name of this support team.
	*
	* @param userName the user name of this support team
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_supportTeam.setUserName(userName);
	}

	/**
	* Sets the user uuid of this support team.
	*
	* @param userUuid the user uuid of this support team
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_supportTeam.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportTeamWrapper)) {
			return false;
		}

		SupportTeamWrapper supportTeamWrapper = (SupportTeamWrapper)obj;

		if (Objects.equals(_supportTeam, supportTeamWrapper._supportTeam)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportTeam getWrappedModel() {
		return _supportTeam;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportTeam.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportTeam.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportTeam.resetOriginalValues();
	}

	private final SupportTeam _supportTeam;
}
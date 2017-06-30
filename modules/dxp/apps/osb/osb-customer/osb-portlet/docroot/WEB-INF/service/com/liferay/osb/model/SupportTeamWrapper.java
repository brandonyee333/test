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
 * This class is a wrapper for {@link SupportTeam}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportTeam
 * @generated
 */
public class SupportTeamWrapper implements SupportTeam,
	ModelWrapper<SupportTeam> {
	public SupportTeamWrapper(SupportTeam supportTeam) {
		_supportTeam = supportTeam;
	}

	public Class<?> getModelClass() {
		return SupportTeam.class;
	}

	public String getModelClassName() {
		return SupportTeam.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportTeamId", getSupportTeamId());
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

	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportTeamId = (Long)attributes.get("supportTeamId");

		if (supportTeamId != null) {
			setSupportTeamId(supportTeamId);
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

	/**
	* Returns the primary key of this support team.
	*
	* @return the primary key of this support team
	*/
	public long getPrimaryKey() {
		return _supportTeam.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support team.
	*
	* @param primaryKey the primary key of this support team
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportTeam.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support team ID of this support team.
	*
	* @return the support team ID of this support team
	*/
	public long getSupportTeamId() {
		return _supportTeam.getSupportTeamId();
	}

	/**
	* Sets the support team ID of this support team.
	*
	* @param supportTeamId the support team ID of this support team
	*/
	public void setSupportTeamId(long supportTeamId) {
		_supportTeam.setSupportTeamId(supportTeamId);
	}

	/**
	* Returns the user ID of this support team.
	*
	* @return the user ID of this support team
	*/
	public long getUserId() {
		return _supportTeam.getUserId();
	}

	/**
	* Sets the user ID of this support team.
	*
	* @param userId the user ID of this support team
	*/
	public void setUserId(long userId) {
		_supportTeam.setUserId(userId);
	}

	/**
	* Returns the user uuid of this support team.
	*
	* @return the user uuid of this support team
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeam.getUserUuid();
	}

	/**
	* Sets the user uuid of this support team.
	*
	* @param userUuid the user uuid of this support team
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_supportTeam.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this support team.
	*
	* @return the user name of this support team
	*/
	public java.lang.String getUserName() {
		return _supportTeam.getUserName();
	}

	/**
	* Sets the user name of this support team.
	*
	* @param userName the user name of this support team
	*/
	public void setUserName(java.lang.String userName) {
		_supportTeam.setUserName(userName);
	}

	/**
	* Returns the create date of this support team.
	*
	* @return the create date of this support team
	*/
	public java.util.Date getCreateDate() {
		return _supportTeam.getCreateDate();
	}

	/**
	* Sets the create date of this support team.
	*
	* @param createDate the create date of this support team
	*/
	public void setCreateDate(java.util.Date createDate) {
		_supportTeam.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this support team.
	*
	* @return the modified date of this support team
	*/
	public java.util.Date getModifiedDate() {
		return _supportTeam.getModifiedDate();
	}

	/**
	* Sets the modified date of this support team.
	*
	* @param modifiedDate the modified date of this support team
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_supportTeam.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the parent support team ID of this support team.
	*
	* @return the parent support team ID of this support team
	*/
	public long getParentSupportTeamId() {
		return _supportTeam.getParentSupportTeamId();
	}

	/**
	* Sets the parent support team ID of this support team.
	*
	* @param parentSupportTeamId the parent support team ID of this support team
	*/
	public void setParentSupportTeamId(long parentSupportTeamId) {
		_supportTeam.setParentSupportTeamId(parentSupportTeamId);
	}

	/**
	* Returns the support labor ID of this support team.
	*
	* @return the support labor ID of this support team
	*/
	public long getSupportLaborId() {
		return _supportTeam.getSupportLaborId();
	}

	/**
	* Sets the support labor ID of this support team.
	*
	* @param supportLaborId the support labor ID of this support team
	*/
	public void setSupportLaborId(long supportLaborId) {
		_supportTeam.setSupportLaborId(supportLaborId);
	}

	/**
	* Returns the location support region ID of this support team.
	*
	* @return the location support region ID of this support team
	*/
	public long getLocationSupportRegionId() {
		return _supportTeam.getLocationSupportRegionId();
	}

	/**
	* Sets the location support region ID of this support team.
	*
	* @param locationSupportRegionId the location support region ID of this support team
	*/
	public void setLocationSupportRegionId(long locationSupportRegionId) {
		_supportTeam.setLocationSupportRegionId(locationSupportRegionId);
	}

	/**
	* Returns the name of this support team.
	*
	* @return the name of this support team
	*/
	public java.lang.String getName() {
		return _supportTeam.getName();
	}

	/**
	* Sets the name of this support team.
	*
	* @param name the name of this support team
	*/
	public void setName(java.lang.String name) {
		_supportTeam.setName(name);
	}

	/**
	* Returns the description of this support team.
	*
	* @return the description of this support team
	*/
	public java.lang.String getDescription() {
		return _supportTeam.getDescription();
	}

	/**
	* Sets the description of this support team.
	*
	* @param description the description of this support team
	*/
	public void setDescription(java.lang.String description) {
		_supportTeam.setDescription(description);
	}

	/**
	* Returns the type of this support team.
	*
	* @return the type of this support team
	*/
	public int getType() {
		return _supportTeam.getType();
	}

	/**
	* Sets the type of this support team.
	*
	* @param type the type of this support team
	*/
	public void setType(int type) {
		_supportTeam.setType(type);
	}

	/**
	* Returns the assigned work of this support team.
	*
	* @return the assigned work of this support team
	*/
	public double getAssignedWork() {
		return _supportTeam.getAssignedWork();
	}

	/**
	* Sets the assigned work of this support team.
	*
	* @param assignedWork the assigned work of this support team
	*/
	public void setAssignedWork(double assignedWork) {
		_supportTeam.setAssignedWork(assignedWork);
	}

	/**
	* Returns the max work of this support team.
	*
	* @return the max work of this support team
	*/
	public double getMaxWork() {
		return _supportTeam.getMaxWork();
	}

	/**
	* Sets the max work of this support team.
	*
	* @param maxWork the max work of this support team
	*/
	public void setMaxWork(double maxWork) {
		_supportTeam.setMaxWork(maxWork);
	}

	public boolean isNew() {
		return _supportTeam.isNew();
	}

	public void setNew(boolean n) {
		_supportTeam.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportTeam.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportTeam.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportTeam.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportTeam.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportTeam.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportTeam.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportTeam.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportTeamWrapper((SupportTeam)_supportTeam.clone());
	}

	public int compareTo(com.liferay.osb.model.SupportTeam supportTeam) {
		return _supportTeam.compareTo(supportTeam);
	}

	@Override
	public int hashCode() {
		return _supportTeam.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportTeam> toCacheModel() {
		return _supportTeam.toCacheModel();
	}

	public com.liferay.osb.model.SupportTeam toEscapedModel() {
		return new SupportTeamWrapper(_supportTeam.toEscapedModel());
	}

	public com.liferay.osb.model.SupportTeam toUnescapedModel() {
		return new SupportTeamWrapper(_supportTeam.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportTeam.toString();
	}

	public java.lang.String toXmlString() {
		return _supportTeam.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeam.persist();
	}

	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeam.getAccountEntries();
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeam.getChildSupportTeams();
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams(
		boolean recursive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeam.getChildSupportTeams(recursive);
	}

	public java.util.List<java.lang.String> getLanguageIds()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeam.getLanguageIds();
	}

	public com.liferay.osb.model.SupportTeam getParentSupportTeam()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeam.getParentSupportTeam();
	}

	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeam.getSupportRegions();
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

		if (Validator.equals(_supportTeam, supportTeamWrapper._supportTeam)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportTeam getWrappedSupportTeam() {
		return _supportTeam;
	}

	public SupportTeam getWrappedModel() {
		return _supportTeam;
	}

	public void resetOriginalValues() {
		_supportTeam.resetOriginalValues();
	}

	private SupportTeam _supportTeam;
}
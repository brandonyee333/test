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
 * This class is a wrapper for {@link CorpProject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProject
 * @generated
 */
@ProviderType
public class CorpProjectWrapper implements CorpProject,
	ModelWrapper<CorpProject> {
	public CorpProjectWrapper(CorpProject corpProject) {
		_corpProject = corpProject;
	}

	@Override
	public Class<?> getModelClass() {
		return CorpProject.class;
	}

	@Override
	public String getModelClassName() {
		return CorpProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("corpProjectId", getCorpProjectId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossieraProjectKey", getDossieraProjectKey());
		attributes.put("salesforceProjectKey", getSalesforceProjectKey());
		attributes.put("name", getName());
		attributes.put("organizationId", getOrganizationId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long corpProjectId = (Long)attributes.get("corpProjectId");

		if (corpProjectId != null) {
			setCorpProjectId(corpProjectId);
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

		String dossieraProjectKey = (String)attributes.get("dossieraProjectKey");

		if (dossieraProjectKey != null) {
			setDossieraProjectKey(dossieraProjectKey);
		}

		String salesforceProjectKey = (String)attributes.get(
				"salesforceProjectKey");

		if (salesforceProjectKey != null) {
			setSalesforceProjectKey(salesforceProjectKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long organizationId = (Long)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
		}
	}

	@Override
	public CorpProject toEscapedModel() {
		return new CorpProjectWrapper(_corpProject.toEscapedModel());
	}

	@Override
	public CorpProject toUnescapedModel() {
		return new CorpProjectWrapper(_corpProject.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _corpProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _corpProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _corpProject.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _corpProject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CorpProject> toCacheModel() {
		return _corpProject.toCacheModel();
	}

	@Override
	public com.liferay.portal.kernel.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProject.getGroup();
	}

	@Override
	public int compareTo(CorpProject corpProject) {
		return _corpProject.compareTo(corpProject);
	}

	@Override
	public int hashCode() {
		return _corpProject.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _corpProject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CorpProjectWrapper((CorpProject)_corpProject.clone());
	}

	/**
	* Returns the dossiera project key of this corp project.
	*
	* @return the dossiera project key of this corp project
	*/
	@Override
	public java.lang.String getDossieraProjectKey() {
		return _corpProject.getDossieraProjectKey();
	}

	/**
	* Returns the name of this corp project.
	*
	* @return the name of this corp project
	*/
	@Override
	public java.lang.String getName() {
		return _corpProject.getName();
	}

	@Override
	public java.lang.String getOrganizationUuid()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProject.getOrganizationUuid();
	}

	/**
	* Returns the salesforce project key of this corp project.
	*
	* @return the salesforce project key of this corp project
	*/
	@Override
	public java.lang.String getSalesforceProjectKey() {
		return _corpProject.getSalesforceProjectKey();
	}

	/**
	* Returns the user name of this corp project.
	*
	* @return the user name of this corp project
	*/
	@Override
	public java.lang.String getUserName() {
		return _corpProject.getUserName();
	}

	/**
	* Returns the user uuid of this corp project.
	*
	* @return the user uuid of this corp project
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _corpProject.getUserUuid();
	}

	/**
	* Returns the uuid of this corp project.
	*
	* @return the uuid of this corp project
	*/
	@Override
	public java.lang.String getUuid() {
		return _corpProject.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _corpProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _corpProject.toXmlString();
	}

	/**
	* Returns the create date of this corp project.
	*
	* @return the create date of this corp project
	*/
	@Override
	public Date getCreateDate() {
		return _corpProject.getCreateDate();
	}

	/**
	* Returns the modified date of this corp project.
	*
	* @return the modified date of this corp project
	*/
	@Override
	public Date getModifiedDate() {
		return _corpProject.getModifiedDate();
	}

	/**
	* Returns the corp project ID of this corp project.
	*
	* @return the corp project ID of this corp project
	*/
	@Override
	public long getCorpProjectId() {
		return _corpProject.getCorpProjectId();
	}

	/**
	* Returns the organization ID of this corp project.
	*
	* @return the organization ID of this corp project
	*/
	@Override
	public long getOrganizationId() {
		return _corpProject.getOrganizationId();
	}

	/**
	* Returns the primary key of this corp project.
	*
	* @return the primary key of this corp project
	*/
	@Override
	public long getPrimaryKey() {
		return _corpProject.getPrimaryKey();
	}

	/**
	* Returns the user ID of this corp project.
	*
	* @return the user ID of this corp project
	*/
	@Override
	public long getUserId() {
		return _corpProject.getUserId();
	}

	@Override
	public void persist() {
		_corpProject.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_corpProject.setCachedModel(cachedModel);
	}

	/**
	* Sets the corp project ID of this corp project.
	*
	* @param corpProjectId the corp project ID of this corp project
	*/
	@Override
	public void setCorpProjectId(long corpProjectId) {
		_corpProject.setCorpProjectId(corpProjectId);
	}

	/**
	* Sets the create date of this corp project.
	*
	* @param createDate the create date of this corp project
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_corpProject.setCreateDate(createDate);
	}

	/**
	* Sets the dossiera project key of this corp project.
	*
	* @param dossieraProjectKey the dossiera project key of this corp project
	*/
	@Override
	public void setDossieraProjectKey(java.lang.String dossieraProjectKey) {
		_corpProject.setDossieraProjectKey(dossieraProjectKey);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_corpProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_corpProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_corpProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this corp project.
	*
	* @param modifiedDate the modified date of this corp project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_corpProject.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this corp project.
	*
	* @param name the name of this corp project
	*/
	@Override
	public void setName(java.lang.String name) {
		_corpProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_corpProject.setNew(n);
	}

	/**
	* Sets the organization ID of this corp project.
	*
	* @param organizationId the organization ID of this corp project
	*/
	@Override
	public void setOrganizationId(long organizationId) {
		_corpProject.setOrganizationId(organizationId);
	}

	@Override
	public void setOrganizationUuid(java.lang.String organizationUuid) {
		_corpProject.setOrganizationUuid(organizationUuid);
	}

	/**
	* Sets the primary key of this corp project.
	*
	* @param primaryKey the primary key of this corp project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_corpProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_corpProject.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the salesforce project key of this corp project.
	*
	* @param salesforceProjectKey the salesforce project key of this corp project
	*/
	@Override
	public void setSalesforceProjectKey(java.lang.String salesforceProjectKey) {
		_corpProject.setSalesforceProjectKey(salesforceProjectKey);
	}

	/**
	* Sets the user ID of this corp project.
	*
	* @param userId the user ID of this corp project
	*/
	@Override
	public void setUserId(long userId) {
		_corpProject.setUserId(userId);
	}

	/**
	* Sets the user name of this corp project.
	*
	* @param userName the user name of this corp project
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_corpProject.setUserName(userName);
	}

	/**
	* Sets the user uuid of this corp project.
	*
	* @param userUuid the user uuid of this corp project
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_corpProject.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this corp project.
	*
	* @param uuid the uuid of this corp project
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_corpProject.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CorpProjectWrapper)) {
			return false;
		}

		CorpProjectWrapper corpProjectWrapper = (CorpProjectWrapper)obj;

		if (Objects.equals(_corpProject, corpProjectWrapper._corpProject)) {
			return true;
		}

		return false;
	}

	@Override
	public CorpProject getWrappedModel() {
		return _corpProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _corpProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _corpProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_corpProject.resetOriginalValues();
	}

	private final CorpProject _corpProject;
}
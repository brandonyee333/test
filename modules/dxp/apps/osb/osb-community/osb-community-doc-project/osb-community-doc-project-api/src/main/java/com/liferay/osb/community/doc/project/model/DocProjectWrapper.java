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

package com.liferay.osb.community.doc.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DocProject}.
 * </p>
 *
 * @author Ryan Park
 * @see DocProject
 * @generated
 */
@ProviderType
public class DocProjectWrapper implements DocProject, ModelWrapper<DocProject> {
	public DocProjectWrapper(DocProject docProject) {
		_docProject = docProject;
	}

	@Override
	public Class<?> getModelClass() {
		return DocProject.class;
	}

	@Override
	public String getModelClassName() {
		return DocProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("docProjectId", getDocProjectId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("iconFileName", getIconFileName());
		attributes.put("unlisted", getUnlisted());
		attributes.put("type", getType());
		attributes.put("typeSettings", getTypeSettings());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long docProjectId = (Long)attributes.get("docProjectId");

		if (docProjectId != null) {
			setDocProjectId(docProjectId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String iconFileName = (String)attributes.get("iconFileName");

		if (iconFileName != null) {
			setIconFileName(iconFileName);
		}

		Boolean unlisted = (Boolean)attributes.get("unlisted");

		if (unlisted != null) {
			setUnlisted(unlisted);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public DocProject toEscapedModel() {
		return new DocProjectWrapper(_docProject.toEscapedModel());
	}

	@Override
	public DocProject toUnescapedModel() {
		return new DocProjectWrapper(_docProject.toUnescapedModel());
	}

	@Override
	public DocProjectTypeSettings getDocProjectTypeSettings() {
		return _docProject.getDocProjectTypeSettings();
	}

	/**
	* Returns the unlisted of this doc project.
	*
	* @return the unlisted of this doc project
	*/
	@Override
	public boolean getUnlisted() {
		return _docProject.getUnlisted();
	}

	@Override
	public boolean isCachedModel() {
		return _docProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _docProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _docProject.isNew();
	}

	/**
	* Returns <code>true</code> if this doc project is unlisted.
	*
	* @return <code>true</code> if this doc project is unlisted; <code>false</code> otherwise
	*/
	@Override
	public boolean isUnlisted() {
		return _docProject.isUnlisted();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _docProject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DocProject> toCacheModel() {
		return _docProject.toCacheModel();
	}

	@Override
	public int compareTo(DocProject docProject) {
		return _docProject.compareTo(docProject);
	}

	/**
	* Returns the status of this doc project.
	*
	* @return the status of this doc project
	*/
	@Override
	public int getStatus() {
		return _docProject.getStatus();
	}

	@Override
	public int hashCode() {
		return _docProject.hashCode();
	}

	@Override
	public java.io.InputStream getIconInputStream()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _docProject.getIconInputStream();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _docProject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DocProjectWrapper((DocProject)_docProject.clone());
	}

	/**
	* Returns the description of this doc project.
	*
	* @return the description of this doc project
	*/
	@Override
	public java.lang.String getDescription() {
		return _docProject.getDescription();
	}

	/**
	* Returns the icon file name of this doc project.
	*
	* @return the icon file name of this doc project
	*/
	@Override
	public java.lang.String getIconFileName() {
		return _docProject.getIconFileName();
	}

	/**
	* Returns the name of this doc project.
	*
	* @return the name of this doc project
	*/
	@Override
	public java.lang.String getName() {
		return _docProject.getName();
	}

	/**
	* Returns the type of this doc project.
	*
	* @return the type of this doc project
	*/
	@Override
	public java.lang.String getType() {
		return _docProject.getType();
	}

	/**
	* Returns the type settings of this doc project.
	*
	* @return the type settings of this doc project
	*/
	@Override
	public java.lang.String getTypeSettings() {
		return _docProject.getTypeSettings();
	}

	/**
	* Returns the user name of this doc project.
	*
	* @return the user name of this doc project
	*/
	@Override
	public java.lang.String getUserName() {
		return _docProject.getUserName();
	}

	/**
	* Returns the user uuid of this doc project.
	*
	* @return the user uuid of this doc project
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _docProject.getUserUuid();
	}

	/**
	* Returns the uuid of this doc project.
	*
	* @return the uuid of this doc project
	*/
	@Override
	public java.lang.String getUuid() {
		return _docProject.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _docProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _docProject.toXmlString();
	}

	/**
	* Returns the create date of this doc project.
	*
	* @return the create date of this doc project
	*/
	@Override
	public Date getCreateDate() {
		return _docProject.getCreateDate();
	}

	/**
	* Returns the modified date of this doc project.
	*
	* @return the modified date of this doc project
	*/
	@Override
	public Date getModifiedDate() {
		return _docProject.getModifiedDate();
	}

	/**
	* Returns the company ID of this doc project.
	*
	* @return the company ID of this doc project
	*/
	@Override
	public long getCompanyId() {
		return _docProject.getCompanyId();
	}

	/**
	* Returns the doc project ID of this doc project.
	*
	* @return the doc project ID of this doc project
	*/
	@Override
	public long getDocProjectId() {
		return _docProject.getDocProjectId();
	}

	/**
	* Returns the group ID of this doc project.
	*
	* @return the group ID of this doc project
	*/
	@Override
	public long getGroupId() {
		return _docProject.getGroupId();
	}

	/**
	* Returns the primary key of this doc project.
	*
	* @return the primary key of this doc project
	*/
	@Override
	public long getPrimaryKey() {
		return _docProject.getPrimaryKey();
	}

	/**
	* Returns the user ID of this doc project.
	*
	* @return the user ID of this doc project
	*/
	@Override
	public long getUserId() {
		return _docProject.getUserId();
	}

	@Override
	public void persist() {
		_docProject.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_docProject.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this doc project.
	*
	* @param companyId the company ID of this doc project
	*/
	@Override
	public void setCompanyId(long companyId) {
		_docProject.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this doc project.
	*
	* @param createDate the create date of this doc project
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_docProject.setCreateDate(createDate);
	}

	/**
	* Sets the description of this doc project.
	*
	* @param description the description of this doc project
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_docProject.setDescription(description);
	}

	/**
	* Sets the doc project ID of this doc project.
	*
	* @param docProjectId the doc project ID of this doc project
	*/
	@Override
	public void setDocProjectId(long docProjectId) {
		_docProject.setDocProjectId(docProjectId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_docProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_docProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_docProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this doc project.
	*
	* @param groupId the group ID of this doc project
	*/
	@Override
	public void setGroupId(long groupId) {
		_docProject.setGroupId(groupId);
	}

	/**
	* Sets the icon file name of this doc project.
	*
	* @param iconFileName the icon file name of this doc project
	*/
	@Override
	public void setIconFileName(java.lang.String iconFileName) {
		_docProject.setIconFileName(iconFileName);
	}

	/**
	* Sets the modified date of this doc project.
	*
	* @param modifiedDate the modified date of this doc project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_docProject.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this doc project.
	*
	* @param name the name of this doc project
	*/
	@Override
	public void setName(java.lang.String name) {
		_docProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_docProject.setNew(n);
	}

	/**
	* Sets the primary key of this doc project.
	*
	* @param primaryKey the primary key of this doc project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_docProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_docProject.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this doc project.
	*
	* @param status the status of this doc project
	*/
	@Override
	public void setStatus(int status) {
		_docProject.setStatus(status);
	}

	/**
	* Sets the type of this doc project.
	*
	* @param type the type of this doc project
	*/
	@Override
	public void setType(java.lang.String type) {
		_docProject.setType(type);
	}

	/**
	* Sets the type settings of this doc project.
	*
	* @param typeSettings the type settings of this doc project
	*/
	@Override
	public void setTypeSettings(java.lang.String typeSettings) {
		_docProject.setTypeSettings(typeSettings);
	}

	/**
	* Sets whether this doc project is unlisted.
	*
	* @param unlisted the unlisted of this doc project
	*/
	@Override
	public void setUnlisted(boolean unlisted) {
		_docProject.setUnlisted(unlisted);
	}

	/**
	* Sets the user ID of this doc project.
	*
	* @param userId the user ID of this doc project
	*/
	@Override
	public void setUserId(long userId) {
		_docProject.setUserId(userId);
	}

	/**
	* Sets the user name of this doc project.
	*
	* @param userName the user name of this doc project
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_docProject.setUserName(userName);
	}

	/**
	* Sets the user uuid of this doc project.
	*
	* @param userUuid the user uuid of this doc project
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_docProject.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this doc project.
	*
	* @param uuid the uuid of this doc project
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_docProject.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocProjectWrapper)) {
			return false;
		}

		DocProjectWrapper docProjectWrapper = (DocProjectWrapper)obj;

		if (Objects.equals(_docProject, docProjectWrapper._docProject)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _docProject.getStagedModelType();
	}

	@Override
	public DocProject getWrappedModel() {
		return _docProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _docProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _docProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_docProject.resetOriginalValues();
	}

	private final DocProject _docProject;
}
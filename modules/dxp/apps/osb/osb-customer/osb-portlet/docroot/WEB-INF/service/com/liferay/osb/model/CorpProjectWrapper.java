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
 * This class is a wrapper for {@link CorpProject}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpProject
 * @generated
 */
public class CorpProjectWrapper implements CorpProject,
	ModelWrapper<CorpProject> {
	public CorpProjectWrapper(CorpProject corpProject) {
		_corpProject = corpProject;
	}

	public Class<?> getModelClass() {
		return CorpProject.class;
	}

	public String getModelClassName() {
		return CorpProject.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

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

	public void setModelAttributes(Map<String, Object> attributes) {
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

	/**
	* Returns the primary key of this corp project.
	*
	* @return the primary key of this corp project
	*/
	public long getPrimaryKey() {
		return _corpProject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this corp project.
	*
	* @param primaryKey the primary key of this corp project
	*/
	public void setPrimaryKey(long primaryKey) {
		_corpProject.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the corp project ID of this corp project.
	*
	* @return the corp project ID of this corp project
	*/
	public long getCorpProjectId() {
		return _corpProject.getCorpProjectId();
	}

	/**
	* Sets the corp project ID of this corp project.
	*
	* @param corpProjectId the corp project ID of this corp project
	*/
	public void setCorpProjectId(long corpProjectId) {
		_corpProject.setCorpProjectId(corpProjectId);
	}

	/**
	* Returns the user ID of this corp project.
	*
	* @return the user ID of this corp project
	*/
	public long getUserId() {
		return _corpProject.getUserId();
	}

	/**
	* Sets the user ID of this corp project.
	*
	* @param userId the user ID of this corp project
	*/
	public void setUserId(long userId) {
		_corpProject.setUserId(userId);
	}

	/**
	* Returns the user uuid of this corp project.
	*
	* @return the user uuid of this corp project
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpProject.getUserUuid();
	}

	/**
	* Sets the user uuid of this corp project.
	*
	* @param userUuid the user uuid of this corp project
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_corpProject.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this corp project.
	*
	* @return the user name of this corp project
	*/
	public java.lang.String getUserName() {
		return _corpProject.getUserName();
	}

	/**
	* Sets the user name of this corp project.
	*
	* @param userName the user name of this corp project
	*/
	public void setUserName(java.lang.String userName) {
		_corpProject.setUserName(userName);
	}

	/**
	* Returns the create date of this corp project.
	*
	* @return the create date of this corp project
	*/
	public java.util.Date getCreateDate() {
		return _corpProject.getCreateDate();
	}

	/**
	* Sets the create date of this corp project.
	*
	* @param createDate the create date of this corp project
	*/
	public void setCreateDate(java.util.Date createDate) {
		_corpProject.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this corp project.
	*
	* @return the modified date of this corp project
	*/
	public java.util.Date getModifiedDate() {
		return _corpProject.getModifiedDate();
	}

	/**
	* Sets the modified date of this corp project.
	*
	* @param modifiedDate the modified date of this corp project
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_corpProject.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the dossiera project key of this corp project.
	*
	* @return the dossiera project key of this corp project
	*/
	public java.lang.String getDossieraProjectKey() {
		return _corpProject.getDossieraProjectKey();
	}

	/**
	* Sets the dossiera project key of this corp project.
	*
	* @param dossieraProjectKey the dossiera project key of this corp project
	*/
	public void setDossieraProjectKey(java.lang.String dossieraProjectKey) {
		_corpProject.setDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns the salesforce project key of this corp project.
	*
	* @return the salesforce project key of this corp project
	*/
	public java.lang.String getSalesforceProjectKey() {
		return _corpProject.getSalesforceProjectKey();
	}

	/**
	* Sets the salesforce project key of this corp project.
	*
	* @param salesforceProjectKey the salesforce project key of this corp project
	*/
	public void setSalesforceProjectKey(java.lang.String salesforceProjectKey) {
		_corpProject.setSalesforceProjectKey(salesforceProjectKey);
	}

	/**
	* Returns the name of this corp project.
	*
	* @return the name of this corp project
	*/
	public java.lang.String getName() {
		return _corpProject.getName();
	}

	/**
	* Sets the name of this corp project.
	*
	* @param name the name of this corp project
	*/
	public void setName(java.lang.String name) {
		_corpProject.setName(name);
	}

	/**
	* Returns the organization ID of this corp project.
	*
	* @return the organization ID of this corp project
	*/
	public long getOrganizationId() {
		return _corpProject.getOrganizationId();
	}

	/**
	* Sets the organization ID of this corp project.
	*
	* @param organizationId the organization ID of this corp project
	*/
	public void setOrganizationId(long organizationId) {
		_corpProject.setOrganizationId(organizationId);
	}

	public boolean isNew() {
		return _corpProject.isNew();
	}

	public void setNew(boolean n) {
		_corpProject.setNew(n);
	}

	public boolean isCachedModel() {
		return _corpProject.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_corpProject.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _corpProject.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _corpProject.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_corpProject.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _corpProject.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_corpProject.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CorpProjectWrapper((CorpProject)_corpProject.clone());
	}

	public int compareTo(com.liferay.osb.model.CorpProject corpProject) {
		return _corpProject.compareTo(corpProject);
	}

	@Override
	public int hashCode() {
		return _corpProject.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.CorpProject> toCacheModel() {
		return _corpProject.toCacheModel();
	}

	public com.liferay.osb.model.CorpProject toEscapedModel() {
		return new CorpProjectWrapper(_corpProject.toEscapedModel());
	}

	public com.liferay.osb.model.CorpProject toUnescapedModel() {
		return new CorpProjectWrapper(_corpProject.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _corpProject.toString();
	}

	public java.lang.String toXmlString() {
		return _corpProject.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpProject.persist();
	}

	public java.util.List<com.liferay.osb.model.CorpProjectMessage> getCorpProjectMessages()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpProject.getCorpProjectMessages();
	}

	public com.liferay.portal.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProject.getGroup();
	}

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProject.getGroupId();
	}

	public com.liferay.portal.model.Organization getOrganization()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProject.getOrganization();
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

		if (Validator.equals(_corpProject, corpProjectWrapper._corpProject)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CorpProject getWrappedCorpProject() {
		return _corpProject;
	}

	public CorpProject getWrappedModel() {
		return _corpProject;
	}

	public void resetOriginalValues() {
		_corpProject.resetOriginalValues();
	}

	private CorpProject _corpProject;
}
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

package com.liferay.osb.customer.release.tool.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link JIRAComponent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponent
 * @generated
 */
@ProviderType
public class JIRAComponentWrapper implements JIRAComponent,
	ModelWrapper<JIRAComponent> {
	public JIRAComponentWrapper(JIRAComponent jiraComponent) {
		_jiraComponent = jiraComponent;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAComponent.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAComponent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraComponentId", getJiraComponentId());
		attributes.put("remoteId", getRemoteId());
		attributes.put("remoteProject", getRemoteProject());
		attributes.put("name", getName());
		attributes.put("visible", getVisible());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraComponentId = (Long)attributes.get("jiraComponentId");

		if (jiraComponentId != null) {
			setJiraComponentId(jiraComponentId);
		}

		Long remoteId = (Long)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		String remoteProject = (String)attributes.get("remoteProject");

		if (remoteProject != null) {
			setRemoteProject(remoteProject);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean visible = (Boolean)attributes.get("visible");

		if (visible != null) {
			setVisible(visible);
		}
	}

	@Override
	public JIRAComponent toEscapedModel() {
		return new JIRAComponentWrapper(_jiraComponent.toEscapedModel());
	}

	@Override
	public JIRAComponent toUnescapedModel() {
		return new JIRAComponentWrapper(_jiraComponent.toUnescapedModel());
	}

	/**
	* Returns the visible of this jira component.
	*
	* @return the visible of this jira component
	*/
	@Override
	public boolean getVisible() {
		return _jiraComponent.getVisible();
	}

	@Override
	public boolean isCachedModel() {
		return _jiraComponent.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraComponent.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jiraComponent.isNew();
	}

	/**
	* Returns <code>true</code> if this jira component is visible.
	*
	* @return <code>true</code> if this jira component is visible; <code>false</code> otherwise
	*/
	@Override
	public boolean isVisible() {
		return _jiraComponent.isVisible();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jiraComponent.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JIRAComponent> toCacheModel() {
		return _jiraComponent.toCacheModel();
	}

	@Override
	public int compareTo(JIRAComponent jiraComponent) {
		return _jiraComponent.compareTo(jiraComponent);
	}

	@Override
	public int hashCode() {
		return _jiraComponent.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraComponent.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAComponentWrapper((JIRAComponent)_jiraComponent.clone());
	}

	/**
	* Returns the name of this jira component.
	*
	* @return the name of this jira component
	*/
	@Override
	public java.lang.String getName() {
		return _jiraComponent.getName();
	}

	/**
	* Returns the remote project of this jira component.
	*
	* @return the remote project of this jira component
	*/
	@Override
	public java.lang.String getRemoteProject() {
		return _jiraComponent.getRemoteProject();
	}

	@Override
	public java.lang.String toString() {
		return _jiraComponent.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _jiraComponent.toXmlString();
	}

	/**
	* Returns the jira component ID of this jira component.
	*
	* @return the jira component ID of this jira component
	*/
	@Override
	public long getJiraComponentId() {
		return _jiraComponent.getJiraComponentId();
	}

	/**
	* Returns the primary key of this jira component.
	*
	* @return the primary key of this jira component
	*/
	@Override
	public long getPrimaryKey() {
		return _jiraComponent.getPrimaryKey();
	}

	/**
	* Returns the remote ID of this jira component.
	*
	* @return the remote ID of this jira component
	*/
	@Override
	public long getRemoteId() {
		return _jiraComponent.getRemoteId();
	}

	@Override
	public void persist() {
		_jiraComponent.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraComponent.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jiraComponent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_jiraComponent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jiraComponent.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the jira component ID of this jira component.
	*
	* @param jiraComponentId the jira component ID of this jira component
	*/
	@Override
	public void setJiraComponentId(long jiraComponentId) {
		_jiraComponent.setJiraComponentId(jiraComponentId);
	}

	/**
	* Sets the name of this jira component.
	*
	* @param name the name of this jira component
	*/
	@Override
	public void setName(java.lang.String name) {
		_jiraComponent.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_jiraComponent.setNew(n);
	}

	/**
	* Sets the primary key of this jira component.
	*
	* @param primaryKey the primary key of this jira component
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraComponent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jiraComponent.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote ID of this jira component.
	*
	* @param remoteId the remote ID of this jira component
	*/
	@Override
	public void setRemoteId(long remoteId) {
		_jiraComponent.setRemoteId(remoteId);
	}

	/**
	* Sets the remote project of this jira component.
	*
	* @param remoteProject the remote project of this jira component
	*/
	@Override
	public void setRemoteProject(java.lang.String remoteProject) {
		_jiraComponent.setRemoteProject(remoteProject);
	}

	/**
	* Sets whether this jira component is visible.
	*
	* @param visible the visible of this jira component
	*/
	@Override
	public void setVisible(boolean visible) {
		_jiraComponent.setVisible(visible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAComponentWrapper)) {
			return false;
		}

		JIRAComponentWrapper jiraComponentWrapper = (JIRAComponentWrapper)obj;

		if (Objects.equals(_jiraComponent, jiraComponentWrapper._jiraComponent)) {
			return true;
		}

		return false;
	}

	@Override
	public JIRAComponent getWrappedModel() {
		return _jiraComponent;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraComponent.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraComponent.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraComponent.resetOriginalValues();
	}

	private final JIRAComponent _jiraComponent;
}
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.release.notes.jira.model;

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
public class JIRAComponentWrapper
	implements JIRAComponent, ModelWrapper<JIRAComponent> {

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
		attributes.put("jiraProjectId", getJiraProjectId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraComponentId = (Long)attributes.get("jiraComponentId");

		if (jiraComponentId != null) {
			setJiraComponentId(jiraComponentId);
		}

		Long jiraProjectId = (Long)attributes.get("jiraProjectId");

		if (jiraProjectId != null) {
			setJiraProjectId(jiraProjectId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Object clone() {
		return new JIRAComponentWrapper((JIRAComponent)_jiraComponent.clone());
	}

	@Override
	public int compareTo(JIRAComponent jiraComponent) {
		return _jiraComponent.compareTo(jiraComponent);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jiraComponent.getExpandoBridge();
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
	 * Returns the jira project ID of this jira component.
	 *
	 * @return the jira project ID of this jira component
	 */
	@Override
	public long getJiraProjectId() {
		return _jiraComponent.getJiraProjectId();
	}

	/**
	 * Returns the name of this jira component.
	 *
	 * @return the name of this jira component
	 */
	@Override
	public String getName() {
		return _jiraComponent.getName();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraComponent.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _jiraComponent.hashCode();
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

	@Override
	public void persist() {
		_jiraComponent.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraComponent.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_jiraComponent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jiraComponent.setExpandoBridgeAttributes(expandoBridge);
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
	 * Sets the jira project ID of this jira component.
	 *
	 * @param jiraProjectId the jira project ID of this jira component
	 */
	@Override
	public void setJiraProjectId(long jiraProjectId) {
		_jiraComponent.setJiraProjectId(jiraProjectId);
	}

	/**
	 * Sets the name of this jira component.
	 *
	 * @param name the name of this jira component
	 */
	@Override
	public void setName(String name) {
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

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JIRAComponent>
		toCacheModel() {

		return _jiraComponent.toCacheModel();
	}

	@Override
	public JIRAComponent toEscapedModel() {
		return new JIRAComponentWrapper(_jiraComponent.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jiraComponent.toString();
	}

	@Override
	public JIRAComponent toUnescapedModel() {
		return new JIRAComponentWrapper(_jiraComponent.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jiraComponent.toXmlString();
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

		if (Objects.equals(
				_jiraComponent, jiraComponentWrapper._jiraComponent)) {

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